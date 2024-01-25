package icdswb.in;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

public class ViewImageActivity extends AppCompatActivity {
    ImageView imageviewId;
    String img;
    String awcsid,awcscode,discode,userid;
    Toolbar toolbar;
    String imge;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.image_view);
        imageviewId =(ImageView)findViewById(R.id.imageviewId);
        Intent intent = getIntent();
        awcsid = intent.getStringExtra("awcsid");
        awcscode = intent.getStringExtra("awcscode");
        discode = intent.getStringExtra("discode");
        userid = intent.getStringExtra("userid");
        imge = intent.getStringExtra("imge");
        Glide.with(getApplicationContext())
                .load(imge)
                .into(imageviewId);
        Log.e("ViewInage",awcsid+" "+awcscode+" "+discode+" "+imge);
        captureimagedata(awcsid,awcscode,discode,userid);
        initToolBar();
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("View Image");
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
    public void captureimagedata(final String awcsid,final String awcscode,final String discode,final String userid){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PHOTOUPLOADVIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("dtl");
                            for (int i = 0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String centerimage = jsonObject.getString("center_image");
                                String latitude = jsonObject.getString("latitude");
                                String longitude = jsonObject.getString("longitude");
                                String awcscode = jsonObject.getString("awcs_code");
                                String id = jsonObject.getString("id");
                                String builtrunin = jsonObject.getString("built_run_in");;
                                Log.e("CAPTUREINAMEVIEW"," "+centerimage+" "+latitude+" "+longitude+" "+awcscode);
//                                Glide.with(getApplicationContext())
//                                        .load(centerimage)
//                                        .into(imageviewId);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dist_id", discode);
                params.put("awcs_id", awcsid);
                params.put("awcs_code", awcscode);
                params.put("user_id", userid);
                Log.e("PHOTOVIEW", " "+awcsid+" "+awcscode+" "+discode+" "+userid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(getApplicationContext());
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);


    }
}
