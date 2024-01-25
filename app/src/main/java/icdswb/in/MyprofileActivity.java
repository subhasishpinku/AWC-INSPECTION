package icdswb.in;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.OfflineLoginSetGet;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.CustomVolleyRequest;
import icdswb.in.ActivityVolley.HttpHandler;
import icdswb.in.ActivityVolley.RequestHandler;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.OtherActivity.Consts;

public class MyprofileActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG = MyprofileActivity.class.getSimpleName();
    ImageView backId, profileId;
    Toolbar toolbar;
    Button ImgUploadId, ImgRotedId, ImgdeleteId;
    private TextView userName, userDesig;
    private EditText userPhone, userWhatsapp, usermail, userPass;
    String url;
    private NetworkImageView imageView;
    ImageView imageView1;
    private ImageLoader imageLoader;
    private static final int PICK_FROM_FILE = 1;
    int screenWidth, screenHeight;
    public static final String UPLOAD_URL = "http://icdswb.in/inspection_service/user_dtl_updt.php";
    public static final String UPLOAD_KEY = "tmp_name";
    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Bitmap bitmap;
    private Uri filePath;
    //String pathImage;
    Button submitID;
    String file_extn;
    String filePathImg;
    String id;
    String userdist;
    String userNamee, userDesigg, user_name, user_img, user_desig, user_phn, user_watsapp, user_mail,
            user_pwd, userPhonee, userWhatsappp, usermaill, userPasss,ddistid,distname,projid,projname,secid,secname,usertype;
    String PROFILEIMG = "1";
    SharedPreferences sp;
    String usertyp;
    DatabaseHelper helper;
    String loginid, userid, usertyplogin, userdistlogin, userimg, username,
            userdesig, userphn, userwatsapp, usermaillogin, userpwd, distid, disname,discode,
            distiduser,distnameuser,projiduser,projnameuser,seciduser,secnameuser;
    User user;
    List<OfflineLoginSetGet> offlineLoginSetGetList;
    OfflineLoginSetGet offlineLoginSetGet;
    private static final int Image_Capture_Code = 2;
    private AsyncTask mMyTask;
    TextView userlavelId,distId,projectId,sectorId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
//        backId = (ImageView)findViewById(R.id.backId);
//        backId.setOnClickListener(this);
        ImgUploadId = (Button) findViewById(R.id.ImgUploadId);
        ImgRotedId = (Button) findViewById(R.id.ImgRotedId);
        ImgdeleteId = (Button) findViewById(R.id.ImgdeleteId);
        userlavelId =  (TextView) findViewById(R.id.userlavelId);
        distId =  (TextView) findViewById(R.id.distId);
        projectId =  (TextView) findViewById(R.id.projectId);
        sectorId =  (TextView) findViewById(R.id.sectorId);
        ImgUploadId.setOnClickListener(this);
        ImgRotedId.setOnClickListener(this);
        ImgdeleteId.setOnClickListener(this);
        userName = (TextView) findViewById(R.id.userName);
        userDesig = (TextView) findViewById(R.id.userDesig);
        userPhone = (EditText) findViewById(R.id.userPhone);
        userWhatsapp = (EditText) findViewById(R.id.userWhatsapp);
        usermail = (EditText) findViewById(R.id.usermail);
        userPass = (EditText) findViewById(R.id.userPass);
        //   profileId =(ImageView)findViewById(R.id.profileId);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView = (NetworkImageView) findViewById(R.id.imageView);
        submitID = (Button) findViewById(R.id.submitID);
        submitID.setOnClickListener(this);
        helper = new DatabaseHelper(this);
        offlineLoginSetGetList = new ArrayList<>();
        Cursor c = helper.getLoginData();
        if (c.moveToFirst()) {
            do {
                        offlineLoginSetGet = new OfflineLoginSetGet(
                        loginid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_LOGINID)),
                        userid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_lID)),
                        usertyplogin = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_TYP)),
                        userdistlogin = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_DIST)),
                        userimg = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_IMG)),
                        username = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_NAME)),
                        userdesig = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_DESIG)),
                        userphn = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_PHN)),
                        userwatsapp = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_WATSAPP)),
                        usermaillogin = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_MAIL)),
                        userpwd = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_PWD)),
                        distid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_DISTID)),
                        disname = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_DISNAME)),
                        discode = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_DISCODE)),
                        distiduser = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USERDISTID)),
                        distnameuser = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USERDISTIDNAME)),
                        projiduser = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USERPROJECTID)),
                        projnameuser = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USERPROJECTNAME)),
                        seciduser = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USERSECTORID)),
                        secnameuser = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USERSECTORNAME)

                ));
                offlineLoginSetGetList.add(offlineLoginSetGet);

            } while (c.moveToNext());
            Log.e("OfflineProfile", loginid + " " + userid + " " + usertyplogin + " " + userdistlogin + " " + userimg + " " + username + " " + userdesig + " " + userphn + " " + userwatsapp + " " + usermaillogin + " " + userpwd + " " + distid + " " + disname + " ");
        }
        user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userNamee = String.valueOf(user.getUserName());
        userDesigg = String.valueOf(user.getUserDesig());
        userPhonee = String.valueOf(user.getUserPhone());
        userWhatsappp = String.valueOf(user.getUserWhatsapp());
        usermaill = String.valueOf(user.getUsermail());
        userPasss = String.valueOf(user.getUserPass());
        userdist = String.valueOf(user.getUserDist());
        id = String.valueOf(user.getUserID());
        usertyp = String.valueOf(user.getUsertyp());
        Log.e("usertyp", usertyp);
//        userName.setText(userNamee);
//        userDesig.setText(userDesigg);
//        userPhone.setText(userPhonee);
//        userWhatsapp.setText(userWhatsappp);
//        usermail.setText(usermaill);
//        userPass.setText(userPasss);
        sp = getSharedPreferences(Consts.PROFILEIMG, MODE_PRIVATE);
        SharedPreferences.Editor edit1 = sp.edit();
        edit1.putString("PROFILEIMG", "0");
        edit1.commit();
        initToolBar();
        url = String.valueOf(user.getUserImg());

//        Log.e("img",url);
//        Glide.with(getApplicationContext())
//                .load(url)
//                .into(profileId);
        DisplayMetrics displaymetrics;
        displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels;
        screenHeight = displaymetrics.heightPixels;
        requestStoragePermission();
        if (isNetworkAvailable()){

        }
        else {
            //loadImage();
        }

        getUserData();
    }
    public boolean isNetworkAvailable() {
        boolean connect = false;
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            connect = false;
        } else {
            connect = true;
        }
        return connect;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        String path = "";
        Log.e("requestCode", String.valueOf(requestCode));
        if (requestCode == PICK_IMAGE_REQUEST) {
            Uri mImageCaptureUri = data.getData();
            Uri selectedImage = data.getData();
            filePathImg = getPath(selectedImage);
            Log.e("IMGPATH"," "+filePathImg);
            path = getRealPathFromURI(mImageCaptureUri);
            if (path == null) {
                path = mImageCaptureUri.getPath(); //from File Manager
            }
            url = path;
            Log.e("path", url);
            // Log.e("file_extn", file_extn);
            imageView.setImageBitmap(getBitmap(path));
        }
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            filePath = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bp);
                Uri tempUri = getImageUri(getApplicationContext(), bp);
                File finalFile = new File(getRealPathFromURII(tempUri));
                Log.e("IMGPATH"," "+finalFile);
                url = String.valueOf(finalFile);
                Log.e("path", url);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

//    public String getPath(Uri uri) {
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        String document_id = cursor.getString(0);
//        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
//        cursor.close();
//
//        cursor = getContentResolver().query(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
//        cursor.moveToFirst();
//        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//        cursor.close();
//
//        return path;
//    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURII(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
      public String getPath(Uri uri) {
          Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
          cursor.moveToFirst();
          String document_id = cursor.getString(0);
          document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
          cursor.close();
          cursor = getApplicationContext().getContentResolver().query(
                  MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                  null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
//        cursor.moveToFirst();
//        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//        cursor.close();
//
//        return path;
          cursor.moveToFirst();
          if(cursor.getCount() > 0){
              String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
              cursor.close();
              return path;
          }
          return null;
    }
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    @SuppressLint("UnsupportedChromeOsCameraSystemFeature")
    private void takePhotoFromCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, Image_Capture_Code);

        } else {

        }
    }
//    public String getPath(Uri uri) {
//        String[] projection = {MediaStore.MediaColumns.DATA};
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//       int column_index = cursor
//                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        cursor.moveToFirst();
//        url = cursor.getString(column_index);
//
//        return cursor.getString(column_index);
//    }
    private void loadImage(){
        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.user, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ImgUploadId:
                sp = getSharedPreferences(Consts.PROFILEIMG, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = sp.edit();
                edit1.putString("PROFILEIMG", "1");
                edit1.commit();
                loadImg();
                break;
            case R.id.ImgRotedId:
                if (PROFILEIMG==sp.getString("PROFILEIMG","")) {
                    if (isNetworkAvailable()) {
                        rotateImage (url);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No Network Device", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Upload Image", Toast.LENGTH_SHORT).show();
                 }
                break;
            case R.id.ImgdeleteId:
                sp = getSharedPreferences(Consts.PROFILEIMG, MODE_PRIVATE);
                SharedPreferences.Editor edit2 = sp.edit();
                edit2.putString("PROFILEIMG", "1");
                edit2.commit();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
                } else {
                    takePhotoFromCamera();
                }
                break;
            case R.id.submitID:
                if (PROFILEIMG==sp.getString("PROFILEIMG","")) {
                    if (isNetworkAvailable()) {
                        uploadImage();

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No Network Device", Toast.LENGTH_SHORT).show();
                    }

                }
                else {

                    //Toast.makeText(getApplicationContext(), "Please Upload Image", Toast.LENGTH_SHORT).show();

                }
                if (isNetworkAvailable()) {
                    uploadImage();

                }
                else {
                    //Toast.makeText(getApplicationContext(), "No Network Device", Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "No Network Device";
                    createDialog(title,msg);
                }

                break;
            default:
        }
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Profile");
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
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj 		= {MediaStore.Images.Media.DATA};
        Cursor cursor 		= managedQuery( contentUri, proj, null, null,null);
        if (cursor == null) return null;
        int column_index 	= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private Bitmap getBitmap(String path) {
        Log.e("inside of", "getBitmap = "+path);
        try {
            Bitmap b = null;
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            Matrix matrix = new Matrix();
            ExifInterface exifReader = new ExifInterface(path);
            int orientation = exifReader.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            int rotate = 0;
            if (orientation == ExifInterface.ORIENTATION_NORMAL) {
                // Do nothing. The original image is fine.
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                rotate = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                rotate = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                rotate = 270;
            }
            matrix.postRotate(rotate);
            ImgRotedId = (Button) findViewById(R.id.ImgRotedId);
            try {
                b = loadBitmap(path, rotate, screenWidth, screenHeight);
                ImgRotedId.setEnabled(true);
            } catch (OutOfMemoryError e) {
                ImgRotedId.setEnabled(false);
            }
            System.gc();
            return b;
        } catch (Exception e) {
            Log.e("my tag", e.getMessage(), e);
            return null;
        }
    }

    public static Bitmap loadBitmap(String path, int orientation, final int targetWidth, final int targetHeight) {
        Bitmap bitmap = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int sourceWidth, sourceHeight;
            if (orientation == 90 || orientation == 270) {
                sourceWidth = options.outHeight;
                sourceHeight = options.outWidth;
            } else {
                sourceWidth = options.outWidth;
                sourceHeight = options.outHeight;
            }
            if (sourceWidth > targetWidth || sourceHeight > targetHeight) {
                float widthRatio = (float)sourceWidth / (float)targetWidth;
                float heightRatio = (float)sourceHeight / (float)targetHeight;
                float maxRatio = Math.max(widthRatio, heightRatio);
                options.inJustDecodeBounds = false;
                options.inSampleSize = (int)maxRatio;
                bitmap = BitmapFactory.decodeFile(path, options);
            } else {
                bitmap = BitmapFactory.decodeFile(path);
            }
            if (orientation > 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(orientation);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
            sourceWidth = bitmap.getWidth();
            sourceHeight = bitmap.getHeight();
            if (sourceWidth != targetWidth || sourceHeight != targetHeight) {
                float widthRatio = (float)sourceWidth / (float)targetWidth;
                float heightRatio = (float)sourceHeight / (float)targetHeight;
                float maxRatio = Math.max(widthRatio, heightRatio);
                sourceWidth = (int)((float)sourceWidth / maxRatio);
                sourceHeight = (int)((float)sourceHeight / maxRatio);
                bitmap = Bitmap.createScaledBitmap(bitmap, sourceWidth, sourceHeight, true);
            }
        } catch (Exception e) {
        }
        return bitmap;
    }
    public void rotateImage(String path){
        File file = new File(path);
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(file.getPath());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        if ( (orientation == ExifInterface.ORIENTATION_NORMAL) | (orientation == 0) ) {
            exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, ""+ ExifInterface.ORIENTATION_ROTATE_90);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
            exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, ""+ ExifInterface.ORIENTATION_ROTATE_180);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
            exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, ""+ ExifInterface.ORIENTATION_ROTATE_270);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, ""+ ExifInterface.ORIENTATION_NORMAL);
        }
        try {
            exifInterface.saveAttributes();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        imageView.setImageBitmap(getBitmap(path))  ;
    }

    public void loadImg(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_IMAGE_REQUEST);
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void uploadImage(){
        final String suserPhone = userPhone.getText().toString().trim();
        final String suserWhatsapp = userWhatsapp.getText().toString().trim();
        final String susermail = usermail.getText().toString().trim();
        final String suserPass = userPass.getText().toString().trim();
        if (TextUtils.isEmpty(suserPhone)) {
            userPhone.setError("Please enter phone");
            userPhone.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(suserWhatsapp)) {
            userWhatsapp.setError("Please enter whatsapp");
            userWhatsapp.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(susermail)) {
            usermail.setError("Please enter mail");
            usermail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(suserPass)) {
            userPass.setError("Please enter password");
            userPass.requestFocus();
            return;
        }
//        class UploadImage extends AsyncTask<Bitmap,Void,String> {
//            ProgressDialog loading;
//            RequestHandler rh = new RequestHandler();
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                try {
//                    String uploadId = UUID.randomUUID().toString();
//                    file_extn = filePathImg.substring(filePathImg.lastIndexOf("/") + 1);
//                    Log.e("file_extn", file_extn);
//                    new MultipartUploadRequest(getApplicationContext(), uploadId,UPLOAD_URL)
//                            .addFileToUpload(url, UPLOAD_KEY) //Adding file
//                            .addParameter("name", file_extn) //Adding text parameter to the request
//                            .addParameter("id", id)
//                            .addParameter("usr_nm", userNamee)
//                            .addParameter("phn_no", suserPhone)
//                            .addParameter("wats_ap", suserWhatsapp)
//                            .addParameter("mail", susermail)
//                            .addParameter("usr_pwd", suserPass)
//                            .setNotificationConfig(new UploadNotificationConfig())
//                            .setMaxRetries(2)
//                            .startUpload(); //Starting the upload
//                    Log.e("uploadd",url+" "+id+""+userNamee+" "+suserPhone+" "+suserWhatsapp+" "+susermail+" "+suserPass+" "+file_extn);
//                    //     loading = ProgressDialog.show(MyprofileActivity.this, "Uploading...", null,true,true);
//                    //getUserData();
//                    Intent intent = new Intent(getApplication(),NavigationDrawerActivity.class);
//                    startActivity(intent);
//                } catch (Exception exc) {
//
//                }
//            }
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                if(s!=null){
//                    try {
//                        String res = String.valueOf(s);
//                        Log.e("URLTEST",res);
//                        JSONObject obj =new JSONObject(res);
//                        Log.e("URLTEST2",""+obj);
//                        String status = obj.getString("status");
//                        String msg = obj.getString("msg");
//                        if (status.equals("false")){
//                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
//                            startActivity(intent);
//                        }
//                        else {
//                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//                        }
//                        String uploadId = UUID.randomUUID().toString();
//                        file_extn = url.substring(url.lastIndexOf("/") + 1);
//                        Log.e("file_extn", file_extn);
//                        new MultipartUploadRequest(getApplicationContext(), uploadId,UPLOAD_URL)
//                                .addFileToUpload(url, UPLOAD_KEY) //Adding file
//                                .addParameter("name", file_extn) //Adding text parameter to the request
//                                .addParameter("id", id)
//                                .addParameter("usr_nm", userNamee)
//                                .addParameter("phn_no", suserPhone)
//                                .addParameter("wats_ap", suserWhatsapp)
//                                .addParameter("mail", susermail)
//                                .addParameter("usr_pwd", suserPass)
//                                .setNotificationConfig(new UploadNotificationConfig())
//                                .setMaxRetries(2)
//                                .startUpload(); //Starting the upload
//                        Log.e("uploadd",url+" "+id+""+userNamee+" "+suserPhone+" "+suserWhatsapp+" "+susermail+" "+suserPass+" "+file_extn);
//
//                      //  Intent intent = new Intent(getApplication(),NavigationDrawerActivity.class);
//                     //   startActivity(intent);
//
//                    } catch (Exception exc) {
//
//                    }
//                }else{
//
//                }
//               // Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            protected String doInBackground(Bitmap... params) {
//                //  Bitmap bitmap = params[0];
//                // String uploadImage = getStringImage(bitmap);
//                file_extn = url.substring(url.lastIndexOf("/") + 1);
//                RequestHandler requestHandler = new RequestHandler();
//                HashMap<String,String> data = new HashMap<>();
//                data.put(UPLOAD_KEY, url);
//                data.put("name", file_extn);
//                data.put("id", id);
//                data.put("usr_nm", userNamee);
//                data.put("phn_no", suserPhone);
//                data.put("wats_ap", suserWhatsapp);
//                data.put("mail", susermail);
//                data.put("usr_pwd", suserPass);
//                Log.e("upload",url+" "+id+" "+userNamee+" "+suserPhone+" "+suserWhatsapp+" "+susermail+" "+suserPass+" "+file_extn);
//                String result = rh.sendPostRequest(UPLOAD_URL,data);
//                //return result;
//                return requestHandler.sendPostRequest(UPLOAD_URL, data);
//            }
//        }
//        UploadImage ui = new UploadImage();
//        ui.execute();

        if (PROFILEIMG==sp.getString("PROFILEIMG","")) {
            UpdateProfile(url,suserPhone,suserWhatsapp,susermail,suserPass);
            Log.e("PATH",url);
        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            // progressBar.setVisibility(View.GONE);
                            Log.e("response", s);
                            if(s!=null){
                                try {
                                    String res = String.valueOf(s);
                                    Log.e("URLTEST",res);
                                    JSONObject obj =new JSONObject(res);
                                    Log.e("URLTEST2",""+obj);
                                    String status = obj.getString("status");
                                    String msg = obj.getString("msg");
                                    if (status.equals("false")){
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    }
                                    String uploadId = UUID.randomUUID().toString();
                                    file_extn = url.substring(url.lastIndexOf("/") + 1);
                                    Log.e("file_extn", file_extn);
                                    new MultipartUploadRequest(getApplicationContext(), uploadId,UPLOAD_URL)
                                            .addFileToUpload(url, UPLOAD_KEY) //Adding file
                                            .addParameter("name", file_extn) //Adding text parameter to the request
                                            .addParameter("id", id)
                                            .addParameter("usr_nm", userNamee)
                                            .addParameter("phn_no", suserPhone)
                                            .addParameter("wats_ap", suserWhatsapp)
                                            .addParameter("mail", susermail)
                                            .addParameter("usr_pwd", suserPass)
                                            .setNotificationConfig(new UploadNotificationConfig())
                                            .setMaxRetries(2)
                                            .startUpload(); //Starting the upload
                                    Log.e("uploadd",url+" "+id+""+userNamee+" "+suserPhone+" "+suserWhatsapp+" "+susermail+" "+suserPass+" "+file_extn);
                                    //  Intent intent = new Intent(getApplication(),NavigationDrawerActivity.class);
                                    //   startActivity(intent);
                                } catch (Exception exc) {
                                } }else{
                            } }},
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    file_extn = url.substring(url.lastIndexOf("/") + 1);
                    RequestHandler requestHandler = new RequestHandler();
                    HashMap<String,String> data = new HashMap<>();
                    data.put(UPLOAD_KEY, url);
                    data.put("name", file_extn);
                    data.put("id", id);
                    data.put("usr_nm", userNamee);
                    data.put("phn_no", suserPhone);
                    data.put("wats_ap", suserWhatsapp);
                    data.put("mail", susermail);
                    data.put("usr_pwd", suserPass);
                    Log.e("upload",url+" "+id+" "+userNamee+" "+suserPhone+" "+suserWhatsapp+" "+susermail+" "+suserPass+" "+file_extn);
                    return data;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
            stringRequest.setShouldCache(false);
            volleySingleton.addToRequestQueue(stringRequest);
        }
    }
    public void UpdateProfile(final String url,final String suserPhone,final String suserWhatsapp,final String susermail,final String suserPass){
        String uploadId = UUID.randomUUID().toString();
        file_extn = url.substring(url.lastIndexOf("/") + 1);
        Log.e("file_extn", file_extn);
        Log.e("file_extn"," "+url+" "+" "+file_extn+" "+id+" "+suserPhone+" "+suserWhatsapp+" "+susermail+" "+suserPass);
        AndroidNetworking.upload(UPLOAD_URL)
                .addMultipartFile(UPLOAD_KEY, new File(url))
                //.addMultipartParameter("name",file_extn)
                .addMultipartParameter("name",file_extn)
                .addMultipartParameter("id",id)
                .addMultipartParameter("usr_nm",userNamee)
                .addMultipartParameter("phn_no",suserPhone)
                .addMultipartParameter("wats_ap",suserWhatsapp)
                .addMultipartParameter("mail",susermail)
                .addMultipartParameter("usr_pwd",suserPass)
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                        Log.e(TAG, "onProgress: called..." );
                        Log.e("SHOWDATA"," "+url+" "+" "+file_extn+" "+id+" "+suserPhone+" "+suserWhatsapp+" "+susermail+" "+suserPass);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.e(TAG, "onResponse: "+response );
                        String res = String.valueOf(response);
                        Log.e("rex",res);
                        try {
                            JSONObject obj =new JSONObject(res);
                            String status = obj.getString("status");
                            String msg = obj.getString("msg");
                            if (status.equals("false")){
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();


                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.e(TAG, "onError: "+error.getErrorDetail() );
                    }
                });

    }
    private void getUserData(){
        class UserData extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                String urll = "http://icdswb.in/inspection_service/user_details.php?user_id="+id;
                String response = sh.makeServiceCall(urll);
                Log.e(TAG, "Response from url attandence: " + response);
                if (response != null) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        JSONObject userJson = obj.getJSONObject("user");
                        Log.e("userJson"," "+userJson);
                        user_img = userJson.getString("user_img");
                        url = userJson.getString("user_img");
                        user_name = userJson.getString("user_name");
                        user_desig = userJson.getString("user_desig");
                        user_phn = userJson.getString("user_phn");
                        user_watsapp = userJson.getString("user_watsapp");
                        user_mail = userJson.getString("user_mail");
                        user_pwd = userJson.getString("user_pwd");
                        ddistid = userJson.getString("dist_id");
                       // distname = userJson.getString("dist_name");
                        distname = userJson.optString("dist_name").replace("null", "NA");
                        projid = userJson.getString("proj_id");
                        //projname = userJson.getString("proj_name");
                        projname = userJson.optString("proj_name").replace("null", "NA");
                        secid = userJson.getString("sec_id");
                       // secname = userJson.getString("sec_name");
                        secname = userJson.optString("sec_name").replace("null", "NA");
                        usertype = userJson.optString("user_type").replace("null", "NA");
                       // usertype = userJson.getString("user_type");

                        Log.e("userData"," "+userdist+" "+user_img+" "+user_name+" "+user_desig+" "+user_phn+" "+user_watsapp+" "+user_mail+" "+user_pwd);
                        User user = new User(
                                userJson.getString(id),
                                userJson.getString(usertyp),
                                userJson.getString(userdist),
                                userJson.getString("user_img"),
                                userJson.getString("user_name"),
                                userJson.getString("user_desig"),
                                userJson.getString("user_phn"),
                                userJson.getString("user_watsapp"),
                                userJson.getString("user_mail"),
                                userJson.getString("user_pwd"),
                                userJson.getString("dis_code"),
                                userJson.getString("dist_id"),
                                userJson.getString("dist_name"),
                                userJson.getString("proj_id"),
                                userJson.getString("proj_name"),
                                userJson.getString("sec_id"),
                                userJson.getString("sec_name")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//                        mMyTask = new DownloadTask()
//                                .execute(stringToURL(
//                                        url
//                                ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                if (isNetworkAvailable()){
                    userName.setText(user_name);
                    userDesig.setText(user_desig);
                    userPhone.setText(user_phn);
                    userWhatsapp.setText(user_watsapp);
                    usermail.setText(user_mail);
                    userPass.setText(user_pwd);
                    if (usertype.contains("PROJ")){
                        userlavelId.setText("PROJECT LEVEL");
                    }
                    else if (usertype.contains("DIS")){
                        userlavelId.setText("DISTRICT LEVEL");
                    }
                    else if (usertype.contains("SEC")){
                        userlavelId.setText("SECTOR LEVEL");
                    }
                    else if (usertype.contains("STA")){
                        userlavelId.setText("STATE LEVEL");
                    }
                    distId.setText(distname);
                    sectorId.setText(secname);
                    projectId.setText(projname);
                    imageLoader = CustomVolleyRequest.getInstance(MyprofileActivity.this).getImageLoader();
                    imageLoader.get(user_img, ImageLoader.getImageListener(imageView, R.drawable.user, android.R.drawable.ic_dialog_alert));
                    imageView.setImageUrl(user_img, imageLoader);
                    imageView1.setVisibility(View.GONE);
                }
                else {
                    imageView.setVisibility(View.GONE);
                    url = offlineLoginSetGet.getUserimg();;
                    user_img = offlineLoginSetGet.getUserimg();
                    userName.setText(offlineLoginSetGet.getUsername());
                    userDesig.setText(offlineLoginSetGet.getUserdesig());
                    userPhone.setText(offlineLoginSetGet.getUserphn());
                    userWhatsapp.setText(offlineLoginSetGet.getUserwatsapp());
                    usermail.setText(offlineLoginSetGet.getUsermaillogin());
                    userPass.setText(offlineLoginSetGet.getUserpwd());
                    if (offlineLoginSetGet.getUsertyplogin().contains("PROJ")){
                        userlavelId.setText("PROJECT LEVEL");
                    }
                    else if (offlineLoginSetGet.getUsertyplogin().contains("DIS")){
                        userlavelId.setText("DISTRICT LEVEL");
                    }
                    else if (offlineLoginSetGet.getUsertyplogin().contains("SEC")){
                        userlavelId.setText("SECTOR LEVEL");
                    }
                    else if (offlineLoginSetGet.getUsertyplogin().contains("STA")){
                        userlavelId.setText("STATE LEVEL");
                    }
                  //  userlavelId.setText(offlineLoginSetGet.getUsertyplogin());
                    distId.setText(offlineLoginSetGet.getDisname());
                    sectorId.setText(offlineLoginSetGet.getSecnameuser());
                    projectId.setText(offlineLoginSetGet.getProjnameuser());
                    String fileImage = String.valueOf(url);
                    file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);
                }
                super.onPostExecute(result);
            }
        }
        UserData ru = new UserData();
        ru.execute();
    }

    private class DownloadTask extends AsyncTask<URL,Void,Bitmap>{
        protected void onPreExecute(){
          //  mProgressDialog.show();
        }
        protected Bitmap doInBackground(URL...urls){
            URL url = urls[0];
            HttpURLConnection connection = null;

            try{
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                return bmp;

            }catch(IOException e){
                e.printStackTrace();
            }finally{
                connection.disconnect();
            }
            return null;
        }
        protected void onPostExecute(Bitmap result){
            //mProgressDialog.dismiss();
            if(result!=null){
                imageView.setImageBitmap(result);
                Uri imageInternalUri = saveImageToInternalStorage(result);
                imageView.setImageURI(imageInternalUri);
            }else {
            }
        }
    }

    // Custom method to convert string to url
    protected URL stringToURL(String urlString){
        try{
            URL url = new URL(urlString);
            return url;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
    protected Uri saveImageToInternalStorage(Bitmap bitmap){
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = wrapper.getDir("Images",MODE_PRIVATE);
        file = new File(file, "UniqueFileName"+".jpg");

        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();

        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        return savedImageURI;
    }

    public AlertDialog createDialog(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.messagedialog, null);
        ((TextView)dialogView.findViewById(R.id.dialog_title)).setText(title);
        ((TextView)dialogView.findViewById(R.id.dialog_msg)).setText(msg);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        ((Button)dialogView.findViewById(R.id.dialog_button)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }
}
