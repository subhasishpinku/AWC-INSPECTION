package icdswb.in.AMFUNWWEBVIEW;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.NavigationDrawerActivity;
import icdswb.in.R;

public class AmfunActivitynWebview extends AppCompatActivity {
    private String postUrl;
    private WebView webView;
    private ProgressBar progressBar;
    private ProgressDialog progressBarr;
    private float m_downX;
    private ImageView imgHeader;
    String  msg = "No Internet!";
    boolean doubleBackToExitPressedOnce = false;
    SwipeRefreshLayout mySwipeRefreshLayout;
    private ValueCallback<Uri> mUploadMessage;
    //private final static int FILECHOOSER_RESULTCODE=1;
    private static final String TAG = "IMAGEUPLOAD";
    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Bitmap bitmap;
    private Uri filePath;
    String filePathImg;
    String file_extn = "pinku";
    String url;
    SharedPreferences sp;
    public static final String UPLOAD_KEY = "tmp_name";
    private File actualImage;
    private static final int Image_Capture_Code = 2;
    private Uri imageUri;
    final Activity activity = this;
    private static final int FILECHOOSER_RESULTCODEE   = 2888;
    private ValueCallback<Uri> mUploadMessagee;
    private Uri mCapturedImageURI = null;
    private String mCM;
    private ValueCallback<Uri> mUM;
    private ValueCallback<Uri[]> mUMA;
    private final static int FCR = 1;
    private final static int FILECHOOSER_RESULTCODE = 1;
   // public static final String urll = "http://icdswb.in/AWCS/portal/Amphan/direct?user_id=";
    public static final String urll ="https://project.primacyinfotech.com/mlm1/user/wallet?username=skaanoyar09";
    String successmsg, userid,username,userpwd,distid,projid,secid,usertyp;
    Toolbar toolbarr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  setContentView(R.layout.amfun_webview);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (!DetectConnection.checkInternetConnection(AmfunActivitynWebview.this)) {
            showmessage(msg);
        } else {
            setContentView(R.layout.amfun_webview);
            //mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);
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
            postUrl = urll+userid;
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            webView = (WebView) findViewById(R.id.webView);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            imgHeader = (ImageView) findViewById(R.id.backdrop);
            initToolBar();
            if (!TextUtils.isEmpty(getIntent().getStringExtra("postUrl"))) {
                postUrl = getIntent().getStringExtra("postUrl");
            }
            initWebView();
            initCollapsingToolbar();
            renderPost();
//            mySwipeRefreshLayout.setOnRefreshListener(
//                    new SwipeRefreshLayout.OnRefreshListener() {
//                        @Override
//                        public void onRefresh() {
//                            webView.reload();
//                            initWebView();
//                            initCollapsingToolbar();
//                            renderPost();
//                        }
//                    });
        }
        requestStoragePermission();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
        } else {

        }

    }
    public void initToolBar() {
        toolbarr = (Toolbar) findViewById(R.id.toolbarr);
        toolbarr.setTitle("Amphan Damage Assessment");
        setSupportActionBar(toolbarr);
        toolbarr.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarr.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // finish();
                        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                        startActivity(intent);
                    }
                }
        );
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


    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                actualImage = FileUtil.from(getApplicationContext(), data.getData());
                //  Log.e("Path", String.valueOf(actualImage));
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), Uri.fromFile(actualImage));
                //imageview_account_profile.setImageBitmap(bitmap);
                String fileImage = String.valueOf(actualImage);
                file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                File finalFile = new File(getRealPathFromURII(tempUri));
                //Log.e("IMGPATH"," "+finalFile);
                url = String.valueOf(finalFile);
                actualImage = finalFile;
//                Log.e("path", String.valueOf(actualImage));
                String fileImage = String.valueOf(actualImage);
                file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
//        if (requestCode == FILECHOOSER_RESULTCODE) {
//            if (null == this.mUploadMessage) {
//                return;
//            }
//            Uri result;
//            if (resultCode != RESULT_OK) {
//                result = null;
//            } else {
//                result = data == null ? this.imageUri : data.getData();
//            }
//            this.mUploadMessage.onReceiveValue(result);
//            this.mUploadMessage = null;
//        }
//
//        if(requestCode==FILECHOOSER_RESULTCODEE)
//        {
//            if (null == this.mUploadMessagee) {
//                return;
//            }
//            Uri result=null;
//            try{
//                if (resultCode != RESULT_OK) {
//                    result = null;
//                } else {
//                    // retrieve from the private variable if the intent is null
//                    result = data == null ? mCapturedImageURI : data.getData();
//                } }
//            catch(Exception e)
//            {
//                Toast.makeText(getApplicationContext(), "activity :"+e,
//                        Toast.LENGTH_LONG).show();
//            }
//
//            mUploadMessagee.onReceiveValue(result);
//            mUploadMessagee = null;
//
//        }


        if (Build.VERSION.SDK_INT >= 21) {
            Uri[] results = null;
            //Check if response is positive
            if (resultCode == RESULT_OK) {
                if (requestCode == FCR) {
                    if (null == mUMA) {
                        return;
                    }
                    if (data == null) {
                        //Capture Photo if no image available
                        if (mCM != null) {
                            results = new Uri[]{Uri.parse(mCM)};
                        }
                    } else {
                        String dataString = data.getDataString();
                        if (dataString != null) {
                            results = new Uri[]{Uri.parse(dataString)};
                        }
                    }
                }
            }
            mUMA.onReceiveValue(results);
            mUMA = null;
        } else {
            if (requestCode == FCR) {
                if (null == mUM) return;
                Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
                mUM.onReceiveValue(result);
                mUM = null;
            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURII(Uri uri) {
        String path = "";
        if (getApplicationContext().getContentResolver() != null) {
            Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        ////////////////////////////////////
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setSupportZoom(true);
        webSettings.setAllowContentAccess(true);
        webView.requestFocus(View.FOCUS_DOWN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //webView.loadUrl("http://demo.mdfcapps.online/");
        webView.setWebChromeClient(new MyWebChromeClient(this));
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (mUMA != null) {
                    mUMA.onReceiveValue(null);
                }
                mUMA = filePathCallback;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(AmfunActivitynWebview.this.getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCM);
                    } catch (IOException ex) {
                        Log.e("Webview", "Image file creation failed", ex);
                    }
                    if (photoFile != null) {
                        mCM = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }

                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("*/*");
                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, FCR);
                return true;
            }
        });


//        webView.setWebChromeClient(new WebChromeClient(){
//            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType )  {
//                File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
//                // Create the storage directory if it does not exist
//                if (! imageStorageDir.exists()){
//                    imageStorageDir.mkdirs();
//                }
//                File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
//                imageUri = Uri.fromFile(file);
//
//                final List<Intent> cameraIntents = new ArrayList<Intent>();
//                final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                final PackageManager packageManager = getPackageManager();
//                final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
//                for(ResolveInfo res : listCam) {
//                    final String packageName = res.activityInfo.packageName;
//                    final Intent i = new Intent(captureIntent);
//                    i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//                    i.setPackage(packageName);
//                    i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                    cameraIntents.add(i);
//
//                }
//                mUploadMessage = uploadMsg;
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setType("image/*");
//                Intent chooserIntent = Intent.createChooser(i,"Image Chooser");
//                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
//                MainActivity.this.startActivityForResult(chooserIntent,  FILECHOOSER_RESULTCODE);
//            }});



//        webView.setWebChromeClient(new WebChromeClient() {
//            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType){
//                mUploadMessage = uploadMsg;
//                try{
//                    File imageStorageDir = new File(
//                            Environment.getExternalStoragePublicDirectory(
//                                    Environment.DIRECTORY_PICTURES)
//                            , "AndroidExampleFolder");
//                    if (!imageStorageDir.exists()) {
//                        // Create AndroidExampleFolder at sdcard
//                        imageStorageDir.mkdirs();
//                    }
//                    File file = new File(
//                            imageStorageDir + File.separator + "IMG_"
//                                    + String.valueOf(System.currentTimeMillis())
//                                    + ".jpg");
//                    mCapturedImageURI = Uri.fromFile(file);
//                    final Intent captureIntent = new Intent(
//                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
//                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                    i.addCategory(Intent.CATEGORY_OPENABLE);
//                    i.setType("image/*");
//                    Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
//                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS
//                            , new Parcelable[] { captureIntent });
//                    startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODEE);
//                }
//                catch(Exception e){
//                    Toast.makeText(getBaseContext(), "Exception:"+e,
//                            Toast.LENGTH_LONG).show();
//                } }
//                public void openFileChooser(ValueCallback<Uri> uploadMsg){
//                openFileChooser(uploadMsg, "");
//            }
//
//            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
//                openFileChooser(uploadMsg, acceptType);
//            }
//            public boolean onConsoleMessage(ConsoleMessage cm) {
//                onConsoleMessage(cm.message(), cm.lineNumber(), cm.sourceId());
//                return true;
//            }
//            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
//                Log.e("androidruntime", "Show console messages, Used for debugging: " + message);
//
//            }
//        });


        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading (WebView view, String url) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                Log.e("LINK", String.valueOf(Uri.parse(url)));
                return true;


            }
        });
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                Log.e("LINK1", String.valueOf(Uri.parse(url)));
            }
        });
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        progressBarr = ProgressDialog.show(AmfunActivitynWebview.this, "Please Wait", "Loading...");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!DetectConnection.checkInternetConnection(AmfunActivitynWebview.this)) {
                    showmessage(msg);
                } else {
                    if (Utils.isSameDomain(postUrl, url)) {
                        Intent intent = new Intent(AmfunActivitynWebview.this, AmfunActivitynWebview.class);
                        intent.putExtra("postUrl", url);
                        startActivity(intent);
                    } else {
                        openInAppBrowser(url);
                        view.loadUrl(url);
                    }

                }
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                mySwipeRefreshLayout.setRefreshing(false);
                if (progressBarr.isShowing()) {
                    progressBarr.dismiss();
                }
                progressBar.setVisibility(View.GONE);
                postUrl = url;
                super.onPageFinished(view, url);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(AmfunActivitynWebview.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
        });
        // webView.loadUrl("http://demo.mdfcapps.online/");
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getPointerCount() > 1) {
                    return true;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        m_downX = event.getX();
                    }
                    break;
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        event.setLocation(m_downX, event.getY());
                    }
                    break;
                }
                return false;
            }
        });
    }
    private void renderPost() {
        webView.loadUrl(postUrl);
        // webView.loadUrl("file:///android_asset/sample.html");
    }
    private void openInAppBrowser(String url) {
        Intent intent = new Intent(AmfunActivitynWebview.this, BrowserActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the txtPostTitle when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Web View");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
        // loading toolbar header image
//        Glide.with(getApplicationContext()).load("http://api.androidhive.info/webview/nougat.jpg")
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imgHeader);
    }
    private class MyWebChromeClient extends WebChromeClient {
        Context context;
        public MyWebChromeClient(Context context) {
            super();
            this.context = context;
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click Back again to exit", Toast.LENGTH_SHORT).show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }


    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        this.openFileChooser(uploadMsg, "*/*");
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        this.openFileChooser(uploadMsg, acceptType, null);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        AmfunActivitynWebview.this.startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
    }
    // Create an image file
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    public void onBackPressed()
    {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}

