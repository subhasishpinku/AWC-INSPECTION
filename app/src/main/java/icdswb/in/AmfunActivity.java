package icdswb.in;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

public class AmfunActivity extends AppCompatActivity {
    Toolbar toolbar;
    WebView webView;
    WebChromeClient ChromeView;
    public static final String url = "http://icdswb.in/AWCS/portal/Amphan/direct?user_id=";
    boolean isHomePage = false;
    String usertyp,useridd;
    int successstatus;
    String successmsg, userid,username,userpwd,distid,projid,secid;
    TextView headerName;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.amfan_layout);
        initToolBar();
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        usertyp = user.getUsertyp();
        userid = user.getUserID();
        username = user.getUserName();
        userpwd = user.getUserPass();
        distid = user.getDdistid();
        projid = user.getProjid();
        secid = user.getSecid();
        Log.e("USERDATA"," "+usertyp+" "
                +userid+" "+username+" "+userpwd+" "+distid+" "+projid+" "+secid);
        webView = (WebView) findViewById(R.id.webView);
        loginuservalid(userid);
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new myWebClient());
        //webView.loadUrl(url + userid);
//        headerName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("HIIIII","HI");
//                Intent intent_info = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
//                startActivity(intent_info);
//            }
//        });
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("HIIIII","HI");
//                Intent intent_info = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
//                startActivity(intent_info);
//            }
//        });
//
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Amphan Damage Assessment");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

    public void loginuservalid(final String userId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.AMFUN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Validuser", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            successstatus = obj.getInt("successstatus");
                            successmsg = obj.getString("successmsg");
                            userid = obj.getString("user_id");
                            if (successstatus == 1) {
                                webView.getSettings().setJavaScriptEnabled(true);
                                webView.getSettings().setAppCacheEnabled(true);
                                webView.getSettings().setDomStorageEnabled(true);
                                webView.setWebChromeClient(new WebChromeClient());
                                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                                webView.getSettings().setSupportMultipleWindows(true);
                                webView.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                        if (url.contains("android_asset")) {
                                            isHomePage = false;
                                            return false;
                                        }
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                        startActivity(intent);
                                        return true;
                                    }
                                });

                            //    webView.loadUrl(url + userid + "&&app_val=1");
                                webView.loadUrl(url + userid);

                                showmessageok(successmsg);
                            } else {
                                showmessage(successmsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), "UserName Password Not Valid", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                Log.e("VALIDUSER", userId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(getApplicationContext());
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        // if not home page go to home page.
        if (isHomePage == false) {
            if (successstatus == 1) {
            //    webView.loadUrl(url + userid + "&&app_val=1");
                webView.loadUrl(url + userid);
                isHomePage = true;
            } else {

            }
            // if home page exit app.
            Log.d("CDA", "onBackPressed");
        } else {
            Log.d("CDA", "onBackPressed Called");
//            Intent intent_info = new Intent(getApplicationContext(), DashboardActivity.class);
//            startActivity(intent_info);
//            overridePendingTransition(R.anim.slide_up_info, R.anim.no_change);
        }
    }

    public void showmessage(final String msg) {
        final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        snackBar.setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call your action method here
                snackBar.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        snackBar.setActionTextColor(Color.RED);
        View sbView = snackBar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackBar.show();
    }

    public void showmessageok(final String msg) {
        final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        snackBar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call your action method here
                snackBar.dismiss();
                // finish();
                // startActivity(getIntent());
            }
        });
        snackBar.setActionTextColor(Color.RED);
        View sbView = snackBar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackBar.show();
    }

    private class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //view.loadUrl(url);
           // Log.e("THISPAGE"," "+url);
            return true;
           // return (false);
        }

    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("Inside onResume");
    }

    @Override
    public void onStart(){
        super.onStart();
        System.out.println("Inside onStart");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        System.out.println("Inside onReStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        System.out.println("Inside onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("Inside onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
