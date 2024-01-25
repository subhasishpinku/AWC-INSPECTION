package icdswb.in;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.DistricSetGet;
import icdswb.in.ActivitySetGet.OOwnbuildingsetget;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

public class Login extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {
    private Button loginId,regisId;
    private Spinner spID,spIDD;
    private EditText userID,userPass;
    ArrayList<String> stateName;
    ArrayList<String> stateID = new ArrayList<>();
    private JSONArray result;
    String distID,disName;
    String user, pass;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private DatabaseHelper db;
    String fid,fname;
    OOwnbuildingsetget ownobj;
    DistricSetGet districSetGet;
    List<DistricSetGet> districSetGetss;
    ArrayList<OOwnbuildingsetget> arrayList = new ArrayList();
    String disID,distname;
    List<String> Disname = new ArrayList<String>();
    List<String> DisID = new ArrayList<>();
    String[] spinnerArray;
    HashMap<Integer,String> spinnerMap;
    String userid,usertyp,userdist,userimg,username,userdesig,userphn,userwatsapp,usermail,
            userpwd,discode,distiduser,distnameuser,projiduser,projnameuser,seciduser,secnameuser,usertype;
    DatabaseHelper DB = null;
    DatabaseHelper helper;
    String spdisid="";
    LinearLayout lvolineID,lvolineIDD;
    boolean doubleBackToExitPressedOnce = false;
    private ArrayList<WeakReference<OnBackClickListener>> backClickListenersList = new ArrayList<>();
    //////permission//////
    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final int REQUEST_PERMISSIONS = 100;
    boolean boolean_permissionn;
    boolean boolean_permission;
    private static long back_pressed;
    String currentVersion;
    CoordinatorLayout coordinatorLayoutt;
    private int inter = 0;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout lvrefressId;
    Button refressId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        loginId = (Button)findViewById(R.id.loginId);
        regisId = (Button)findViewById(R.id.regisId);
        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        lvrefressId =(LinearLayout) findViewById(R.id.lvrefressId);
        refressId =(Button) findViewById(R.id.refressId);
        refressId.setOnClickListener(this);
        lvrefressId.setVisibility(View.GONE);
        regisId.setOnClickListener(this);
        db = new DatabaseHelper(this);
        stateName=new ArrayList<>();
        Disname =new ArrayList<>();
        DisID = new ArrayList<>();
        districSetGetss = new ArrayList<>();
        spID = (Spinner)findViewById(R.id.spID);
        spIDD = (Spinner)findViewById(R.id.spIDD);
        userID = (EditText)findViewById(R.id.userID);
        userPass = (EditText)findViewById(R.id.userPass);
        lvolineID = (LinearLayout)findViewById(R.id.lvolineID);
        lvolineIDD =(LinearLayout)findViewById(R.id.lvolineIDD);
        helper = new DatabaseHelper(this);
        loginId.setOnClickListener(this);
        spID.setOnItemSelectedListener(this);
        if (isNetworkAvailable()){
            lvolineID.setVisibility(View.VISIBLE);
            lvolineIDD.setVisibility(View.GONE);
            showdata();
            districDataSp();
        }
        else {
            lvolineID.setVisibility(View.GONE);
            lvolineIDD.setVisibility(View.VISIBLE);
            ownBulding();
            districDataSpOffline();
            spIDD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    distID = spinnerMap.get(spIDD.getSelectedItemPosition());
                    disName = spIDD.getSelectedItem().toString();
                   // Toast.makeText(parent.getContext(),"OFF"+disName+" "+" "+distID, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });

        }
        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        loginId.setOnClickListener(this);
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            userID.setText(loginPreferences.getString("user_name", ""));
            userPass.setText(loginPreferences.getString("user_pwd", ""));
            saveLoginCheckBox.setChecked(true);
        }
        ownBuildingData();
       // clearApplicationData();
        requestStoragePermission();
        if (isNetworkAvailable()){
            try {
                currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            new GetVersionCode().execute();
        }
        else {

        }
        new  checkconne().execute();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                //your code on swipe refresh
                //we are checking networking connectivity
                boolean connection=isNetworkAvailable();
                if(connection){
                    lvolineID.setVisibility(View.VISIBLE);
                    lvolineIDD.setVisibility(View.GONE);
                 }
                else{
                    lvolineID.setVisibility(View.GONE);
                    lvolineIDD.setVisibility(View.VISIBLE);
                    finish();
                    startActivity(getIntent());
                }

            }
        });
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        refress();
    }
    public void  refress(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.REFRESS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("REFRESS",response);

                        try {
                         JSONObject   j = new JSONObject(response);
                         String status = j.getString("status");
                         if (status.equals("1")){
                             lvrefressId.setVisibility(View.VISIBLE);
                             clearApplicationData();

                         }
                         else {
                             lvrefressId.setVisibility(View.GONE);
                         }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }
    ////////////clear_app for cache/////////////////////////////////////
    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if(appDir.exists()){
            String[] children = appDir.list();
            for(String s : children){
                if(!s.equals("lib")){
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "File /data/data/APP_PACKAGE/" + s +" DELETED ");
                }
            }
        }
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    ////////////////////////////////////////////////////////////
    public void ownBuildingData() {
        Cursor res = db.getownbuildingdetails();
        if(res.getCount() == 0) {
            Log.e("Error","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("idfun :"+ res.getString(0)+"\n");
            buffer.append("fid :"+ res.getString(1)+"\n");
            buffer.append("fname :"+ res.getString(2)+"\n");
            removeoBuilding();
        }
        Log.e("ownbuildingdata",buffer.toString());
    }
    public void removeoBuilding(){
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        Cursor c = sqlDB.rawQuery("DELETE FROM ownbuildingfun", null);
        String idfun="";
        String fid= "";
        String fname ="";
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                idfun = c.getString( c.getColumnIndex("idfun"));
                fid = c.getString( c.getColumnIndex("fid"));
                fname = c.getString( c.getColumnIndex("fname"));
                if(!idfun.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+idfun+"'");
                }
                if(!fid.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+fid+"'");
                }
                if(!fname.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+fname+"'");
                }
                c.moveToNext();
            } }
            c.close();
    }
    public boolean isNetworkAvailable() {
        boolean connect=false;
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            connect=false;
        }else{
            connect= true;
        }
        return connect;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginId:
                if (isNetworkAvailable()){
//                    if (boolean_permissionn==true && boolean_permission==true){
//
//                    }
//                    else {
//
//                    }
                    if (spID.getSelectedItem().toString().trim().equals("Select District")){
                        String title = "Message Box";
                        String msg = "Select District";
                        createDialog(title,msg);
                    }
                    else {
                        UserLogin();
                        OwnBulidingFund();
                        requestStoragePermission();
                    }
                }
                else {
//                    if (boolean_permissionn==true && boolean_permission==true){
//
//                    }
//                    else {
//
//                    }

                    if (spIDD.getSelectedItem().toString().trim().equals("Select District")){
                        String title = "Message Box";
                        String msg = "Select District";
                        createDialog(title,msg);
                    }
                    else {
                        OfflineLogin();
                        requestStoragePermission();
                    }

                }
                break;
            case R.id.regisId:
                if (isNetworkAvailable()){
                    Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                    startActivity(intent);
                }
                else {
                    final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), "No internet connection!", Snackbar.LENGTH_LONG);
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
                break;
            case R.id.refressId:
                clearApplicationData();
                finish();
                startActivity(getIntent());
                break;
                default:
        }
    }
    private void  districDataSp(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     //   progressBar.setVisibility(View.GONE);
                        Log.e("SP11",response);
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray(Config.JSON_ARRAY);
                            for (int i = 0;i<result.length(); i++){
                                JSONObject jsonObject = result.getJSONObject(i);
                                disID = jsonObject.getString("id");
                                distname  = jsonObject.getString("dist");
                                Log.e("SPDATA"," "+disID+" "+distname+" ");
                                db.districNameInsert(disID,distname);
                            }
                            Log.e("result"," "+result);
                            getdis(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
   }
    private void getdis(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                stateName.add(json.getString(Config.TAG_DIS));
                Log.e("stateName"," "+stateName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spID.setAdapter(new ArrayAdapter<String>(Login.this, android.R.layout.simple_spinner_dropdown_item, stateName));
    }
    private String getID(int position){
        try {
            JSONObject json = result.getJSONObject(position);
            spdisid = json.getString(Config.TAG_ID);
            Log.e("ide"," "+spdisid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return spdisid;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        distID = getID(position);
        disName = spID.getSelectedItem().toString();
       /// Toast.makeText(parent.getContext(),disName+" "+" "+distID, Toast.LENGTH_LONG).show();
        if (isNetworkAvailable()) {

        }
        else {
//            distID = spinnerMap.get(spID.getSelectedItemPosition());
//            disName = spID.getSelectedItem().toString();
//            Toast.makeText(parent.getContext(),"OFF"+disName+" "+" "+distID, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        finish();
        startActivity(getIntent());
    }
    public void showdata() {
        Cursor res = db.getdistric();
        if(res.getCount() == 0) {
            Log.e("Error","Nothing DIS found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("tableisid :"+ res.getString(0)+"\n");
            buffer.append("disid :"+ res.getString(1)+"\n");
            buffer.append("disname :"+ res.getString(2)+"\n");
            removeDistricData();
        }
        Log.e("Datasp",buffer.toString());
    }
    public void removeDistricData(){
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        Cursor c = sqlDB.rawQuery("DELETE FROM districsp", null);
        String tableisid="";
        String disid= "";
        String disname ="";
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                tableisid = c.getString( c.getColumnIndex("tableisid"));
                disid = c.getString( c.getColumnIndex("disid"));
                disname = c.getString( c.getColumnIndex("disname"));
                if(!tableisid.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+tableisid+"'"); }
                if(!disid.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+disid+"'"); }
                if(!disname.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+disname+"'"); }
                c.moveToNext();
            } }
        c.close();
    }
    private void ownBulding(){
        Cursor cursor = db.getdistric();
        if (cursor.moveToFirst()){
            do {
                districSetGet = new DistricSetGet(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLEID_DISID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DISTRICID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DISTRICNAME))
                );

                districSetGetss.add(districSetGet);
                Disname.add(districSetGet.getDistname());
                DisID.add(districSetGet.getDisID());

            }
            while (cursor.moveToNext());
        }
    }
    private void districDataSpOffline() {
        spinnerArray = new String[districSetGetss.size()];
        spinnerMap = new HashMap<Integer, String>();
        for (int i = 0; i < districSetGetss.size(); i++)
        {
            spinnerMap.put(i,DisID.get(i));
            spinnerArray[i] = Disname.get(i);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        Log.e("DISNamelist", String.valueOf(Disname));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIDD.setAdapter(dataAdapter);
    }
    private void UserLogin(){
        user = userID.getText().toString().trim();
        pass = userPass.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            userID.setError("Please enter username");
            userID.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            userPass.setError("Please enter password");
            userPass.requestFocus();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("response"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("login"," "+array);
                          //  JSONArray array1 = array.getJSONArray(0);
                            JSONObject obj = array.getJSONObject(0);
                            JSONObject userJson = array.getJSONObject(1);
                            String message = obj.getString("message");
                            String error = obj.getString("error");
                            Log.e("data",message+" "+ error);
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                userid = userJson.getString("user_id");
                                usertyp = userJson.getString("user_typ");
                                userdist = userJson.getString("user_dist");
                                userimg = userJson.getString("user_img");
                                username = userJson.getString("user_name");
                                userdesig = userJson.getString("user_desig");
                                userphn = userJson.getString("user_phn");
                                userwatsapp = userJson.getString("user_watsapp");
                                usermail = userJson.getString("user_mail");
                                userpwd = userJson.getString("user_pwd");
                                discode = userJson.getString("dis_code");
                                distiduser = userJson.getString("dist_id");
                                // distname = userJson.getString("dist_name");
                                distnameuser = userJson.optString("dist_name").replace("null", "NA");
                                projiduser = userJson.getString("proj_id");
                                //projname = userJson.getString("proj_name");
                                projnameuser = userJson.optString("proj_name").replace("null", "NA");
                                seciduser = userJson.getString("sec_id");
                                // secname = userJson.getString("sec_name");
                                secnameuser = userJson.optString("sec_name").replace("null", "NA");
                               // usertype = userJson.optString("user_type").replace("null", "NA");
                                // usertype = userJson.getString("user_type");
                                Log.e("UImage",userimg);
                                Log.e("Logindata",userid+" "+usertyp+" "+userdist+" "+userimg+" "+username+" "+userdesig+" "+userphn+" "+userwatsapp+" "+usermail+" "+userpwd+" "+discode);
                                boolean isUpdate =  db.IcdsLogin(userid,usertyp,userdist,
                                        userimg,username,userdesig,userphn,userwatsapp,
                                        usermail,userpwd,distID,disName,discode,distiduser,distnameuser,projiduser,projnameuser,seciduser,secnameuser);
                                db = new DatabaseHelper(getApplicationContext());
                                if (isUpdate==true){
                                    helper = new DatabaseHelper(getApplicationContext());
                                    SQLiteDatabase db = helper.getReadableDatabase();
                                    db.execSQL("UPDATE icdslogin SET userid = userid,usertyp = usertyp,userdist = userdist,userimg = userimg, username = username,userdesig = userdesig,userphn = userphn,userwatsapp = userwatsapp,usermail = usermail, userpwd = userpwd,distid = distID, disname = disName , discode = discode , distiduser = distiduser , projiduser = projiduser , projnameuser = projnameuser , seciduser = seciduser , secnameuser = secnameuser WHERE loginid=" +"1");
                                }
                                else {

                                }
                                User user = new User(
                                        userJson.getString("user_id"),
                                        userJson.getString("user_typ"),
                                        userJson.getString("user_dist"),
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
                                //finish();
                                if (saveLoginCheckBox.isChecked()) {
                                    loginPrefsEditor.putBoolean("saveLogin", true);
                                    loginPrefsEditor.putString("user_name",  userJson.getString("user_name"));
                                    loginPrefsEditor.putString("user_pwd",  userJson.getString("user_pwd"));
                                    loginPrefsEditor.commit();
                                } else {
                                    loginPrefsEditor.clear();
                                    loginPrefsEditor.commit();
                                }
                                startActivity(new Intent(getApplicationContext(), NavigationDrawerActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "No Valid UserName And Password", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "Wrong UserId And Password";
                            createDialog(title,msg);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", user);
                params.put("user_pwd", pass);
                params.put("user_dist", distID);
                Log.e("show",user+" "+pass+" "+distID);
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

     private void  OwnBulidingFund(){
         arrayList = new ArrayList<>();
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.OWNBUlIDINGFUND,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         //   progressBar.setVisibility(View.GONE);
                         Log.e("fund"," "+response);
                         try {
                             JSONObject obj =new JSONObject(response);
                             Log.e("fundobj"," "+obj);
                             JSONArray fund = obj.getJSONArray("fund");
                             Log.e("fundarray"," "+fund);
                             for (int i=0; i<fund.length(); i++){
                                 JSONObject funobj = fund.getJSONObject(i);
                                 fid = funobj.getString("f_id");
                                 fname = funobj.getString("f_name");
                                 Log.e("ALLFUNDATA"," "+fid+" "+fname);
                                 ownobj = new OOwnbuildingsetget();
                                 ownobj.setIdfun("1");
                                 ownobj.setFid(fid);
                                 ownobj.setFname(fname);
                                 arrayList.add(ownobj);
                                 boolean isUpdate =  db.ownbuildingdetailsInsert(ownobj.getFid(),ownobj.getFname());
                                 db = new DatabaseHelper(getApplicationContext());
                                 if (isUpdate==true){
                                    //  db.ownbuildingdetailsUpdate("0",ownobj.getFid(),ownobj.getFname());
                                   //  Toast.makeText(Login.this,"INSERT",Toast.LENGTH_SHORT).show();
                                 }
                                 else {
                                    // Toast.makeText(Login.this,"NO INSERT",Toast.LENGTH_SHORT).show();
                                 }
                             }
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Toast.makeText(getApplicationContext(), "No Valid UserName And Password", Toast.LENGTH_SHORT).show();
                         String title = "Message Box";
                         String msg = "Wrong UserId And Password";
                         createDialog(title,msg);
                     }
                 }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();
                 return params;

             }
         };
         stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
         VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
         stringRequest.setShouldCache(false);
         volleySingleton.addToRequestQueue(stringRequest);
     }


     private void OfflineLogin(){
         user = userID.getText().toString().trim();
         pass = userPass.getText().toString().trim();
         if (TextUtils.isEmpty(user)) {
             userID.setError("Please enter username");
             userID.requestFocus();
             return;
         }
         if (TextUtils.isEmpty(pass)) {
             userPass.setError("Please enter password");
             userPass.requestFocus();
             return;
         }
         boolean validLogin = validateLogin(user, pass,disName, getBaseContext());
         if(validLogin)
         {
             Intent in = new Intent(getBaseContext(), NavigationDrawerActivity.class);
             startActivity(in);
         }

     }

    private boolean validateLogin(String user, String pass, String DisID, Context baseContext) {
        DB = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = DB.getReadableDatabase();
        String[] columns = {"userpwd"};
        String selection = "username=? AND userpwd=? AND disname=?";
        String[] selectionArgs = {user,pass,DisID};
        Cursor cursor = null;
        try{
            cursor = db.query(DatabaseHelper.TABLE_NAMEICDSLOGIN, columns, selection, selectionArgs, null, null, null);
            startManagingCursor(cursor);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        int numberOfRows = cursor.getCount();
        if(numberOfRows <= 0)
        {
            Toast.makeText(getApplicationContext(), "User Id and Password Not Matching", Toast.LENGTH_LONG).show();
            String title = "Message Box";
            String msg = "Wrong UserId And Password";
            createDialog(title,msg);
            Intent intent = new Intent(getBaseContext(), Login.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

        @SuppressLint("NewApi")
        @Override
    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            doubleBackToExitPressedOnce=false;
////            Intent intent_info = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
////            startActivity(intent_info);
////            overridePendingTransition(R.anim.slide_up_info,R.anim.no_change);
//            }
//        }, 2000);
//            if (back_pressed + 2000 > System.currentTimeMillis()){
//                super.onBackPressed();
//            }
//            else{
//                Toast.makeText(getBaseContext(), "Press once again to exit",
//                        Toast.LENGTH_SHORT).show();
//                back_pressed = System.currentTimeMillis();
//            }

            finishAffinity();
            finish();
    }
    //////////////////////////////all permission app ///
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    private void checkServiceStatus() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        REQUEST_PERMISSIONS);
            }
        } else {
            boolean_permission = true;

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boolean_permissionn = true;
                //Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
                checkServiceStatus();
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
        ///////////////////////////gps permission///////
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean_permission = true;
                   // Toast.makeText(getApplicationContext(), "enable services to get gps", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enable services to get gps", Toast.LENGTH_LONG).show();
                }
            }


        }
    }
    class GetVersionCode extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;

            try {
                Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + Login.this.getPackageName()  + "&hl=en")
                        .timeout(1000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get();
                if (document != null) {
                    Elements element = document.getElementsContainingOwnText("Current Version");
                    for (Element ele : element) {
                        if (ele.siblingElements() != null) {
                            Elements sibElemets = ele.siblingElements();
                            for (Element sibElemet : sibElemets) {
                                newVersion = sibElemet.text();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newVersion;

        }
        @Override
        protected void onPostExecute(String onlineVersion) {
            super.onPostExecute(onlineVersion);
            if (onlineVersion != null && !onlineVersion.isEmpty()) {
                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                    if (onlineVersion.equals(currentVersion)) {

                    } else {
                        // bookId.setVisibility(View.VISIBLE);
                        //   bookIdd.setVisibility(View.GONE);
                        AlertDialog alertDialog = new AlertDialog.Builder( Login.this).create();
                        alertDialog.setTitle("Update");
                        alertDialog.setIcon(R.mipmap.logo);
                        alertDialog.setMessage("New Update is available");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Login.this.getPackageName())));
                                    Log.e("U","U");
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Login.this.getPackageName())));
                                    Log.e("U","U1");
                                }
                            }
                        });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        alertDialog.show();
                    }
                }
                else {
                    // bookId.setVisibility(View.GONE);
                    // bookIdd.setVisibility(View.VISIBLE);
//                    Intent intent_info = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
//                    startActivity(intent_info);
//                    overridePendingTransition(R.anim.slide_up_info, R.anim.no_change);
                }
            }
            Log.e("update", "Current version " + currentVersion + "playstore version "+" " + onlineVersion);

        }
    }
    class checkconne extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... args) {
            int kk=0;
            try {
                HttpURLConnection urlc = (HttpURLConnection)
                        (new URL("http://clients3.google.com/generate_204")
                                .openConnection());
                urlc.setRequestProperty("User-Agent", "Android");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                kk= urlc.getResponseCode();
            } catch (IOException e) {

                Log.e("qweqwe", "Error checking internet connection", e);
            }
            inter=kk;
            return null;
        }
        @Override
        protected void onPostExecute(String file_url) {
            if (inter == 204){
                // Toast.makeText(EntersideActivity.this, "is connected", Toast.LENGTH_LONG).show();

            }else{

                //Toast.makeText(EntersideActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
                //bookIdd.setVisibility(View.VISIBLE);
                final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), "No internet connection!", Snackbar.LENGTH_LONG);
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
        }
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
