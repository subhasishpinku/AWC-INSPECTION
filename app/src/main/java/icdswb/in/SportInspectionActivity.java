package icdswb.in;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.HttpHandler;
import icdswb.in.ActivityVolley.RequestHandler;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.OtherActivity.Consts;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSUSERID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NAMEAWCSDTL;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERID;

public class SportInspectionActivity extends AppCompatActivity implements View.OnClickListener,Spinner.OnItemSelectedListener{
    private String TAG = SportInspectionActivity.class.getSimpleName();
    Toolbar toolbar;
    Spinner sp,sp1,sp2,sp3;
    ArrayList<String> stateName;
    ArrayList stateNamee = new ArrayList();
    private int countGetData;
    private int countGetData1;
    private int countGetData2;
    private int countGetData3;
    ArrayList<String> projectname;
    private ArrayList<String> sectorName;
    private ArrayList<String> centerName;
    private JSONArray resultt;
    private JSONArray project;
    private JSONArray sector;
    private JSONArray center;
//    String distID ="0";
//    String projectID = "0";
//    String sectorID = "0";
//    String centerID = "0";
//    String DbdistID = "0";
//    String DbprojectID ="0";
//    String DbsectorID ="0";
//    String DbcenterID = "0";
    String distID ="";
    String projectID = "";
    String sectorID = "";
    String centerID = "";
    String DbdistID = "";
    String DbprojectID ="";
    String DbsectorID ="";
    String DbcenterID = "";
    Button searchID,procedId;
    SharedPreferences spp;
    String LOADSPINER="1";
    EditText edt;
    String awcs_code;
    String discode,usertyp,userid;
    private DatabaseHelper db;
    String ddistrict = "";
    String pproject = "";
    String ssector = "";
    String awcs_name = "";
    String usertypp = "STA";
    String sectorId,projectId,districtId,awcs_id;
    Button load;
    String projectnameDB,districnameDB,sectorrnameDB,centernameDB;
    String districcheck= "1";
    String projeccheckk = "1";
    String sectorcheckk = "1";
    String centercheckk = "1";
    String systenDate;
    String userID,curDate,curTime,planid;
    String water ="NA";
    String cdponame ="NA";
    String acdpocont ="NA";
    String buildstruc="NA";
    String electric="NA";
    String acdponame="NA";
    String kitchen="NA";
    String cdpocontact="NA";
    String workerno="NA";
    String worker_nm="NA";
    String toilet="NA";
    String awcslat="NA";
    String supvsrname="NA";
    String awcsslong="NA";
    String helperno="NA";
    String awcs_adrs="NA";
    String foodspace="NA";
    String helpernm="NA";
    String buildon="NA";
    String adqutspacepse="NA";
    String supvsrno="NA";
    String awcsid="NA";
    String awcscode="NA";
    String awcsname="NA";
    String lstinspctnbuldrep="NA";
    String lstinspctntoiletrep="NA";
    String lstinspctnkitchenrep="NA";
    String lstinspctnpserep="NA";
    String lstinspctnelectricrep="NA";
    String lstinspctndrnkwaterrep="NA";
    String lstinspctnfoodrep = "NA";
    String inspactionid ="0";
    String awcsidDB = "0";
    String centercode = "0";
    String insid;
    String flag = "0";
    LinearLayout elevendigitVisableNotvisableId;
    User user;
    private DatabaseHelper helper;
    //////////////////////////lat lang/////////////////
    private String mLastUpdateTime;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    private static final int REQUEST_CHECK_SETTINGS = 100;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;
    String llat2,llng2;
    float flat2 = 0;
    float flng2 = 0;
    TextView awccodeId;
    String buildingdetails = "0";
    String informationoftoilet= "0";
    String informationofkitchen= "0";
    String adequatespaceforpse= "0";
    String electricity= "0";
    String drinkingwater= "0";
    String foodstoreddetails= "0";
    String conditionofequipmentotherawc= "0";
    String shishualoy= "0";
    String snpserved= "0";
    String nutritionalstatusobserved= "0";
    String otherinspection= "0";
    String allinspactionid= "";
    String flaggrecord = "1";
    String message;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sportinspaction);
        sp =(Spinner)findViewById(R.id.sp);
        sp1 =(Spinner)findViewById(R.id.sp1);
        sp2 =(Spinner)findViewById(R.id.sp2);
        sp3 =(Spinner)findViewById(R.id.sp3);
        edt = (EditText)findViewById(R.id.edt);
        awccodeId = (TextView) findViewById(R.id.awccodeId);
        elevendigitVisableNotvisableId = (LinearLayout)findViewById(R.id.elevendigitVisableNotvisableId);
        elevendigitVisableNotvisableId.setVisibility(View.GONE);
        user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        usertyp = String.valueOf(user.getUsertyp());
        if (usertyp.equals("STA")){
            elevendigitVisableNotvisableId.setVisibility(View.GONE);
            awccodeId.setVisibility(View.GONE);
        }
        else {
            elevendigitVisableNotvisableId.setVisibility(View.VISIBLE);
            awccodeId.setVisibility(View.VISIBLE);
        }
        edt.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        TextWatcher m_MobileWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!s.toString().contains("19")) {
//                    edt.setText("19");
//                    Selection.setSelection(edt.getText(), edt.getText().length());
//                }
//            }
//        };
//        edt.addTextChangedListener(m_MobileWatcher);
        procedId = (Button)findViewById(R.id.procedId);
        countGetData = 0;
        countGetData1 = 0;
        countGetData2 = 0;
        countGetData3 = 0;
        procedId.setOnClickListener(this);
        db = new DatabaseHelper(this);
        edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return true;
                }
                return false;
            }
        });
        stateName=new ArrayList<>();
        projectname = new ArrayList<>();
        sectorName = new ArrayList<>();
        centerName = new ArrayList<>();
        searchID = (Button)findViewById(R.id.searchID);
        helper = new DatabaseHelper(this);
        Cursor cc = helper.getLoginData();
        if (cc.moveToFirst()) {
            do {
                userid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                usertyp = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USER_TYP));
                discode = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DISCODE));

            } while (cc.moveToNext());
        }
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        discode = String.valueOf(user.getUserDist());
        usertyp = String.valueOf(user.getUsertyp());
        userid = String.valueOf(user.getUserID());
        Log.e("usertyp",usertyp+ " "+discode);
        searchID.setOnClickListener(this);
        load = (Button)findViewById(R.id.load);
        // loadSpinnerData();
        spp = getSharedPreferences(LOADSPINER, MODE_PRIVATE);
        SharedPreferences.Editor edit1 = spp.edit();
        edit1.putString("LOADSPINER", "0");
        edit1.commit();
        if (LOADSPINER==spp.getString("LOADSPINER","")){
            //  Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_SHORT).show();

        }
        else {
            if (isNetworkAvailable()){
                loadSpinnerData();
                //  Toast.makeText(getApplicationContext(),"1Hi",Toast.LENGTH_SHORT).show();
            }

        }
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateName.clear();
                projectname.clear();
                sectorName.clear();
                centerName.clear();

            }
        });
        spp = getSharedPreferences(Consts.DISTRICCHECK, MODE_PRIVATE);
        SharedPreferences.Editor edit11 = spp.edit();
        edit11.putString("Discheck", "0");
        edit11.commit();
        sp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spp = getSharedPreferences(Consts.DISTRICCHECK, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = spp.edit();
                edit1.putString("Discheck", "1");
                edit1.commit();
                return false;
            }
        });
        spp = getSharedPreferences(Consts.PROJECTCHECK, MODE_PRIVATE);
        SharedPreferences.Editor edit12 = spp.edit();
        edit12.putString("check", "0");
        edit12.commit();
        sp1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                loadsector();
//                sectorName.clear();
//                centerName.clear();
                spp = getSharedPreferences(Consts.PROJECTCHECK, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = spp.edit();
                edit1.putString("check", "1");
                edit1.commit();
                return false;
            }
        });
        spp = getSharedPreferences(Consts.SECTORCHECK, MODE_PRIVATE);
        SharedPreferences.Editor edit13 = spp.edit();
        edit13.putString("sectorcheck", "0");
        edit13.commit();
        sp2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                loadcenter();
//                centerName.clear();
                spp = getSharedPreferences(Consts.SECTORCHECK, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = spp.edit();
                edit1.putString("sectorcheck", "1");
                edit1.commit();
                return false;
            }
        });
        spp = getSharedPreferences(Consts.CENTERCHECK, MODE_PRIVATE);
        SharedPreferences.Editor edit14 = spp.edit();
        edit14.putString("centercheck", "0");
        edit14.commit();
        sp3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spp = getSharedPreferences(Consts.CENTERCHECK, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = spp.edit();
                edit1.putString("centercheck", "1");
                edit1.commit();
                return false;
            }
        });

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        initToolBar();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        systenDate = df3.format(c.getTime());
        Log.e("time"," "+systenDate);
        init();
        restoreValuesFromBundle(savedInstanceState);
        startLocationButtonClick();
        stopLocationButtonClick();

    }

    public boolean isNetworkAvailable() {
        boolean connect=false;
        ConnectivityManager conMgr =  (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            connect=false;
        }else{
            connect= true;
        }
        return connect;
    }


    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Spot");
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchID:
//                int elevendigitcode = edt.getText().length();
//                if (elevendigitcode>0) {
//                    stateName.clear();
//                    projectname.clear();
//                    sectorName.clear();
//                    centerName.clear();
//                    spp = getSharedPreferences(LOADSPINER, MODE_PRIVATE);
//                    SharedPreferences.Editor edit1 = spp.edit();
//                    edit1.putString("LOADSPINER", "1");
//                    edit1.commit();
//                    getUserData();
//                    hideKeyboard(this);
//                }
//                else {
//                    Toast.makeText(SportInspectionActivity.this,"Enter Value",Toast.LENGTH_SHORT).show();
//                }

                int elevendigitcode = edt.getText().length();
                String phoneNumber = edt.getText().toString().trim();
                //String AWCCODELASTFOURDIGIT = phoneNumber.length() >= 2 ? phoneNumber.substring(phoneNumber.length() - 2): "";
                String input = phoneNumber;     //input string
                String firstFourChars = "";     //substring containing first 4 characters

                if (input.length() > 2)
                {
                    firstFourChars = input.substring(0, 2);
                }
                else
                {
                    firstFourChars = input;
                }
                Log.e("AWCCODE",firstFourChars);
                String inputt = phoneNumber;
                String firstFourCharss = "";     //substring containing first 4 characters

                if (inputt.length() > 5)
                {
                    firstFourCharss = inputt.substring(0, 5);
                }
                else
                {
                    firstFourCharss = inputt;
                }
                Log.e("AWCCODEE",firstFourCharss);
                String AWCCODELASTFOURDIGIT = firstFourCharss.length() >= 2 ? firstFourCharss.substring(firstFourCharss.length() - 2): "";
                if (elevendigitcode>0) {
                    if (edt.getText().length()>10){
                        if (firstFourChars.equals("19")){
//                            if (AWCCODELASTFOURDIGIT.equals(discode)){
//
//                            }
//                            else {
//                                Toast.makeText(PlaningActivity.this,"Invalid Center Code",Toast.LENGTH_SHORT).show();
//                            }
                            stateName.clear();
                            projectname.clear();
                            sectorName.clear();
                            centerName.clear();
                            spp = getSharedPreferences(LOADSPINER, MODE_PRIVATE);
                            SharedPreferences.Editor edit1 = spp.edit();
                            edit1.putString("LOADSPINER", "1");
                            edit1.commit();
                            getUserData();
                            hideKeyboard(this);
                        }
                        else {
                            Toast.makeText(SportInspectionActivity.this,"Enter Start With 19",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(SportInspectionActivity.this,"Enter 11 Digit Code",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(SportInspectionActivity.this,"Enter Value",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.procedId:
                if (sp.getSelectedItem().toString().trim().equals("Select District")){
                    String title = "Message Box";
                    String msg = "Select District";
                    createDialog(title,msg);
                }
                else {
                    if (sp1.getSelectedItem().toString().trim().equals("Select Project")){
                        String title = "Message Box";
                        String msg = "Select Project";
                        createDialog(title,msg);
                    }
                    else {
                        if (sp2.getSelectedItem().toString().trim().equals("Select Sector")){
                            String title = "Message Box";
                            String msg = "Select Sector";
                            createDialog(title,msg);
                        }
                        else {
                            if (sp3.getSelectedItem().toString().trim().equals("Select Center")){
                                String title = "Message Box";
                                String msg = "Select Center";
                                createDialog(title,msg);

                            }
                            else {
                                getproced();
                            }
                        }
                    }
                }

                break;
            default:
        }
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void  loadSpinnerData(){
        class DistricData extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                // String url = "http://202.0.103.141/inspection_service/login_district.php";
                String url = "http://icdswb.in/inspection_service/get_district.php?dist="+discode+"&&user_typ="+usertyp;
                Log.e("url",url);
                String response = sh.makeServiceCall(url);
                Log.e(TAG, "Response from url attandence: " + response);
                if (response != null) {
                    JSONObject j = null;
                    try {
                        j = new JSONObject(response);
                        resultt = j.getJSONArray(Config.JSON_ARRAY);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                spp = getSharedPreferences(DDISTRICTID, MODE_PRIVATE);
//                SharedPreferences.Editor edit1 = spp.edit();
//                edit1.putString("DDISTRICTID", distID);
//                edit1.commit();
                        if (LOADSPINER==spp.getString("LOADSPINER","")){
                            districnameDB = stateName.get(position);
                            DbdistID = getID(position);
                            // Toast.makeText(getApplicationContext(),districnameDB+" "+DbdistID,Toast.LENGTH_SHORT).show();
                        }
                        else {
                            distID = getID(position);
                            //  Toast.makeText(getApplicationContext(),"DisId"+distID,Toast.LENGTH_SHORT).show();
                            loadproject();
                            projectname.clear();
                            sectorName.clear();
                            centerName.clear();
                        }
                        if(districcheck==spp.getString("Discheck","")) {
                            districnameDB = stateName.get(position);
                            DbdistID = getID(position);
                            Log.e("districtname",districnameDB+" "+DbdistID);
                            //Toast.makeText(getApplicationContext(),districnameDB+" "+DbdistID, Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                getdis(resultt);
                super.onPostExecute(result);
            }
        }
        DistricData ru = new DistricData();
        ru.execute();
    }
    private void getdis(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                //  stateName.add("Select District");
                stateName.add(json.getString(Config.TAG_DIS));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        if(countGetData == 0) {
//            stateName.add(0, "Select District");
//            sp.setAdapter(new ArrayAdapter<String>(PlaningActivity.this,  android.R.layout.simple_spinner_dropdown_item, stateName));
//            countGetData += 1;
//        }
        sp.setAdapter(new ArrayAdapter<String>(SportInspectionActivity.this,  android.R.layout.simple_spinner_dropdown_item, stateName));
    }
    private String getID(int position){
        String name="";
        try {
            JSONObject json = resultt.getJSONObject(position);
            name = json.getString(Config.TAG_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.miProfile:
//                Toast.makeText(PlaningActivity.this, "clicking on Logout", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void loadproject(){
        class ProjectData extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                //     DDISTRICTID = spp.getString("DDISTRICTID","");
                //   Log.e("dis",spp.getString("DDISTRICTID",""));
             //   String url = "http://icdswb.in/inspection_service/filter_project.php?dist="+distID;
                String url = "http://icdswb.in/inspection_service/filter_project.php?dist="+distID+"&&user_id="+userid+"&&user_typ="+usertyp;
                String response = sh.makeServiceCall(url);
                Log.e(TAG, "Response from url attandence: " + response);
                if (response != null) {
                    JSONObject j = null;
                    try {
                        j = new JSONObject(response);
                        Log.e("project"," " +j);
                        project = j.getJSONArray(Config.JSON_ARRAYPROJECT);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        if (LOADSPINER==spp.getString("LOADSPINER","")){
                            projectnameDB = projectname.get(position);
                            DbprojectID = getprojectID(position);
                            Log.e("projectname",projectnameDB+ " "+DbprojectID);

                        }
                        else {
                            projectID = getprojectID(position);
                            loadsector();
                            sectorName.clear();
                            centerName.clear();
                        }
                        if(projeccheckk==spp.getString("check","")) {
                            projectnameDB = projectname.get(position);
                            DbprojectID = getprojectID(position);
                            Log.e("projectname",projectnameDB+ " "+DbprojectID);
                          //  Toast.makeText(getApplicationContext(),projectnameDB+" "+DbprojectID, Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Toast.makeText(getApplicationContext(),"HI", Toast.LENGTH_SHORT).show();

                    }


                });
                getproject(project);
                super.onPostExecute(result);
            }
        }
        ProjectData ru = new ProjectData();
        ru.execute();
    }

    private void getproject(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                //    projectname.add("Select Project");
                projectname.add(json.getString(Config.TAG_PROJECT_NAME));
                projectnameDB = json.getString("project_name");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        if(countGetData1 == 0) {
//            projectname.add(0, "Select Project");
//            sp1.setAdapter(new ArrayAdapter<String>(PlaningActivity.this, android.R.layout.simple_spinner_dropdown_item, projectname));
//            countGetData1 += 1;
//        }
        sp1.setAdapter(new ArrayAdapter<String>(SportInspectionActivity.this, android.R.layout.simple_spinner_dropdown_item, projectname));

    }
    private String getprojectID(int position){
        String projername="";
        try {
            JSONObject json = project.getJSONObject(position);
            projername = json.getString(Config.TAG_PROJECT_ID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return projername;
    }
    private void loadsector(){
        class sector extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
            //    String url = "http://icdswb.in/inspection_service/filter_sector.php?dist="+distID+"&&proj_id="+projectID;
                String url = "http://icdswb.in/inspection_service/filter_sector.php?dist="+distID+"&&proj_id="+projectID+"&&user_id="+userid+"&&user_typ="+usertyp;

                String response = sh.makeServiceCall(url);

                Log.e(TAG, "Response from url attandence: " + response);
                if (response != null) {
                    JSONObject j = null;
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = new JSONObject(response);

                        //Storing the Array of JSON String to our JSON Array
                        sector = j.getJSONArray(Config.JSON_ARRAY_SECTOR);

                        //Calling method getStudents to get the students from the JSON Array

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



                        if (LOADSPINER==spp.getString("LOADSPINER","")){
                            sectorrnameDB = sectorName.get(position);
                            DbsectorID = getsectorID(position);
                            Log.e("sectorcheckk",sectorrnameDB+" "+DbsectorID);

                        }
                        else {
                            sectorID =  getsectorID(position);
                            //    Toast.makeText(getApplicationContext(),"SectorID"+sectorID,Toast.LENGTH_SHORT).show();
                            loadcenter();
                            centerName.clear();
                        }
                        if(sectorcheckk==spp.getString("sectorcheck","")) {
                            sectorrnameDB = sectorName.get(position);
                            DbsectorID = getsectorID(position);
                            Log.e("sectorcheckk",sectorrnameDB+" "+DbsectorID);
                          //  Toast.makeText(getApplicationContext(),sectorrnameDB+ " "+DbsectorID, Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                getsector(sector);
                super.onPostExecute(result);

            }
        }
        sector ru = new sector();
        ru.execute();
    }
    private void getsector(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                //    sectorName.add("Select Sector");
                sectorName.add(json.getString(Config.TAG_SECTORNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        if(countGetData2 == 0) {
//            sectorName.add(0, "Select Sector");
//            sp2.setAdapter(new ArrayAdapter<String>(PlaningActivity.this, android.R.layout.simple_spinner_dropdown_item, sectorName));
//            countGetData2 += 1;
//        }
        //Setting adapter to show the items in the spinner
        sp2.setAdapter(new ArrayAdapter<String>(SportInspectionActivity.this, android.R.layout.simple_spinner_dropdown_item, sectorName));
    }

    private String getsectorID(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = sector.getJSONObject(position);

            //Fetching name from that object
            name = json.getString(Config.TAG_SECTORID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }

    private void loadcenter(){
        class center extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                String url = "http://icdswb.in/inspection_service/filter_awcs.php?dist="+distID+"&&sec_id="+sectorID;
                String response = sh.makeServiceCall(url);

                Log.e(TAG, "Response from url attandence: " + response);
                if (response != null) {
                    JSONObject j = null;
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = new JSONObject(response);

                        //Storing the Array of JSON String to our JSON Array
                        center = j.getJSONArray(Config.JSON_ARRAY_CENTER);

                        //Calling method getStudents to get the students from the JSON Array

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                        if (LOADSPINER==spp.getString("LOADSPINER","")){
                            centernameDB = centerName.get(position);
                            DbcenterID = getcenterID(position);
                            Log.e("sectorcheckk",centernameDB+" "+DbcenterID);
                        }
                        else {
                            centerID = getcenterID(position);
                            //   Toast.makeText(getApplicationContext(),"cenID"+centerID,Toast.LENGTH_SHORT).show();
                        }
                        if(centercheckk==spp.getString("centercheck","")) {
                            centernameDB = centerName.get(position);
                            DbcenterID = getcenterID(position);
                            Log.e("sectorcheckk",centernameDB+" "+DbcenterID);
                          //  Toast.makeText(getApplicationContext(),centernameDB+ " "+DbcenterID, Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                getcenter(center);
                super.onPostExecute(result);

            }
        }
        center ru = new center();
        ru.execute();
    }
    private void getcenter(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                //    centerName.add("Select Center");
                centerName.add(json.getString(Config.TAG_CENTERNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        if(countGetData3 == 0) {
//            centerName.add(0, "Select Center");
//            sp3.setAdapter(new ArrayAdapter<String>(PlaningActivity.this, android.R.layout.simple_spinner_dropdown_item, centerName));
//            countGetData3 += 1;
//        }
        //Setting adapter to show the items in the spinner
        sp3.setAdapter(new ArrayAdapter<String>(SportInspectionActivity.this, android.R.layout.simple_spinner_dropdown_item, centerName));

    }

    private String getcenterID(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = center.getJSONObject(position);

            //Fetching name from that object
            name = json.getString(Config.TAG_CENTERID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }

    private void getUserData(){
        awcs_code = edt.getText().toString();
        class UserData extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                //  String url = "http://202.0.103.141/inspection_service/srch_awcs_dtl_frm_code.php?awcs_code="+awcs_code+"&&dist="+discode;
               // String url = "http://icdswb.in/inspection_service/srch_awcs_dtl_frm_code.php?awcs_code="+awcs_code+"&&dist="+discode;
                String url = "http://icdswb.in/inspection_service/srch_awcs_dtl_frm_code.php?awcs_code="+awcs_code+"&&dist="+discode+"&&user_id="+userid+"&&user_typ="+usertyp;

                String response = sh.makeServiceCall(url);
                Log.e(TAG, "Response from url attandence: " + response);
                if (response != null) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        Log.e("code"," "+obj);
                        JSONObject object = obj.getJSONObject("response");
                         message = object.getString("message");
                        String error = object.getString("error");
                        Log.e("awcscode"," "+message+ " "+error+" ");
                        if (message.equals("Invalid AWCS Code Given")){
//                            finish();
//                            startActivity(getIntent());
                            if (LOADSPINER==spp.getString("LOADSPINER","")){
                            }
                            else {
                                if (isNetworkAvailable()){
                                    loadSpinnerData();
                                }
                            }
                        }
                        if (!object.getBoolean("error")){
                            JSONObject awcs_dtl = object.getJSONObject("awcs_dtl");
                            Log.e("awcs_dtl"," "+awcs_dtl);
                            ddistrict = awcs_dtl.getString("district");
                            pproject = awcs_dtl.getString("project");
                            ssector = awcs_dtl.getString("sector");
                            awcs_name = awcs_dtl.getString("awcs_name");


                            distID = awcs_dtl.getString("district_id");
                            projectID = awcs_dtl.getString("project_id");
                            sectorID  = awcs_dtl.getString("sector_id");
                            centerID = awcs_dtl.getString("awcs_id");

//// Log.e("pickudata"," "+water+ " "+ssector+" "+cdponame+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+cdpocontact+" "+workerno+" "+pproject+" "+awcsname+" "+worker_nm+" "+toilet+ " "+supvsrname+" "+helperno+" "+foodspace+" "+helpernm+" "+buildon+" "+ddistrict+" "+adqutspacepse+" "+supvsrno+" ");
                            spp = getSharedPreferences(LOADSPINER, MODE_PRIVATE);
                            SharedPreferences.Editor edit1 = spp.edit();
                            edit1.putString("LOADSPINER", "1");
                            edit1.commit();
                            Log.e("message", object.getString("message"));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            stateName.clear();
                            projectname.clear();
                            sectorName.clear();
                            centerName.clear();
                            stateName.add("");
                            projectname.add("");
                            sectorName.add("");
                            centerName.add("");
                            Log.e("message", object.getString("message"));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                stateName.add(ddistrict);
                projectname.add(pproject);
                sectorName.add(ssector);
                centerName.add(awcs_name);
                Log.e("setdataarray"," "+ddistrict+" "+pproject+" "+ssector+" "+awcs_name+" ");
                sp.setAdapter(new ArrayAdapter<String>(SportInspectionActivity.this, android.R.layout.simple_spinner_dropdown_item, stateName));
                sp1.setAdapter(new ArrayAdapter<String>(SportInspectionActivity.this, android.R.layout.simple_spinner_dropdown_item, projectname));
                sp2.setAdapter(new ArrayAdapter<String>(SportInspectionActivity.this, android.R.layout.simple_spinner_dropdown_item, sectorName));
                sp3.setAdapter(new ArrayAdapter<String>(SportInspectionActivity.this, android.R.layout.simple_spinner_dropdown_item, centerName));
            }
        }
        UserData ru = new UserData();
        ru.execute();
    }
    private void getproced(){

        class Process extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("user_id", userid);
                params.put("dist_id", distID);
                params.put("proj_id", projectID);
                params.put("sectr_id", sectorID);
                params.put("centre_id", centerID);
                params.put("plan_typ", "sp");
                Log.e("proTag"," "+userid+" "+distID+" "+projectID+" "+sectorID+" "+centerID+" ");
                //returing the response
                return requestHandler.sendPostRequest(Config.PROCESS_URL, params);
            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                //progressBar.setVisibility(View.VISIBLE);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                //     progressBar.setVisibility(View.GONE);
                try {
                    JSONArray array =new JSONArray(s);
                    Log.e("process"," "+array);
                    JSONObject obj = array.getJSONObject(0);
                    String error = obj.getString("error");
                    String message = obj.getString("message");
                    if (!obj.getBoolean("error")){
                        JSONObject awcs_dtl = obj.getJSONObject("awcs_dtl");
                        Log.e("awcs_dtl"," "+awcs_dtl);
                        water = awcs_dtl.getString("water");
                        cdponame = awcs_dtl.getString("cdpo_name");
                        acdpocont = awcs_dtl.getString("acdpo_cont");
                        buildstruc = awcs_dtl.getString("build_struc");
                        electric = awcs_dtl.getString("electric");
                        acdponame = awcs_dtl.getString("acdpo_name");
                        kitchen  = awcs_dtl.getString("kitchen");
                        cdpocontact = awcs_dtl.getString("cdpo_contact");
                        workerno = awcs_dtl.getString("worker_no");
                         worker_nm = awcs_dtl.getString("worker_nm");
                        //  String awcsname = awcs_dtl.getString("awcs_name");
                        toilet = awcs_dtl.getString("toilet");
                        awcslat = awcs_dtl.getString("awcs_lat");
                        supvsrname = awcs_dtl.getString("supvsr_name");
                        awcsslong = awcs_dtl.getString("awcs_long");
                        helperno = awcs_dtl.getString("helper_no");
                        awcs_adrs = awcs_dtl.getString("awcs_adrs");
                        foodspace = awcs_dtl.getString("food_space");
                        helpernm = awcs_dtl.getString("helper_nm");
                        buildon = awcs_dtl.getString("build_on");
                        adqutspacepse = awcs_dtl.getString("adqut_space_pse");
                        supvsrno = awcs_dtl.getString("supvsr_no");
                        awcsid = awcs_dtl.getString("awcs_id");
                        awcscode = awcs_dtl.getString("awcs_code");
                        awcsname = awcs_dtl.getString("awcs_name");
                        lstinspctnbuldrep = awcs_dtl.getString("lst_inspctn_buld_rep");
                        lstinspctntoiletrep = awcs_dtl.getString("lst_inspctn_toilet_rep");
                        lstinspctnkitchenrep = awcs_dtl.getString("lst_inspctn_kitchen_rep");
                        lstinspctnpserep = awcs_dtl.getString("lst_inspctn_pse_rep");
                        lstinspctnelectricrep = awcs_dtl.getString("lst_inspctn_electric_rep");
                        lstinspctndrnkwaterrep = awcs_dtl.getString("lst_inspctn_drnkwater_rep");
                        lstinspctnfoodrep = awcs_dtl.getString("lst_inspctn_food_rep");
                        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
                        userID = String.valueOf(user.getUserID());
                        String datee = systenDate.replaceAll("[-+.^:,]", "");
                        insid = DbcenterID+userID+datee;
                        allinspactionid = insid;
                        Log.e("INSS",insid);
                        if (message.equals("Latitude and Longitude is not in correct format")){
                            String title = "Message Box";
                            String msg = obj.getString("message");
                            finalsubmit(title,msg);
                        }
                        else {
                            OwnBulidingFund(insid);
                        }
                        Log.e("pickudata"," "+water+ " "+cdponame+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+cdpocontact+" "+workerno+" "+worker_nm+" "+toilet+" "+awcslat+" "+toilet+ " "+supvsrname+" "+awcsslong+" "+helperno+" "+awcs_adrs+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+" "+awcsid+" "+awcscode+" "+awcsname+" ");
                     // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        Log.e("SELECTRECORD"," "+DbdistID+" "+DbprojectID+" "+DbsectorID+" "+DbcenterID+" "+projectnameDB+" "+districnameDB+" "+sectorrnameDB+" "+centernameDB+" "+systenDate+ " ");
                        Log.e("lastIns"," "+lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep);
                        //  Intent intent = new Intent(getApplicationContext(),ProcedActivity.class);
//                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        Process ru = new Process();
        ru.execute();
    }
    public AlertDialog createDialog(String title, String msg){
        Log.e("METHOD"," "+title+" "+msg);
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
    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                updateLocationUI();
            }
        };
        mRequestingLocationUpdates = false;
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }
    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }
            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }
        updateLocationUI();
    }
    private void updateLocationUI() {
        if (mCurrentLocation != null) {
            Log.e("MyLoc","Lat: " + mCurrentLocation.getLatitude() + ", " + "Lng: " + mCurrentLocation.getLongitude()
            );
            llat2 = String.valueOf(mCurrentLocation.getLatitude());
            llng2 = String.valueOf(mCurrentLocation.getLongitude());
            Log.e("second",llat2+"  "+"  "+llng2);
            String sss = llat2;
            String ssss = llng2;
            try {
                flat2 = Float.valueOf(sss.trim()).floatValue();
                System.out.println("float f = " + flat2);
            } catch (NumberFormatException nfe) {
                System.err.println("NumberFormatException: " + nfe.getMessage());
            }
            try {
                flng2 = Float.valueOf(ssss.trim()).floatValue();
                System.out.println("float f = " + flng2);
            } catch (NumberFormatException nfe) {
                System.err.println("NumberFormatException: " + nfe.getMessage());
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);

    }
    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());
                        updateLocationUI();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(SportInspectionActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(SportInspectionActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }
    public void startLocationButtonClick() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            openSettings();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    public void stopLocationButtonClick() {
        mRequestingLocationUpdates = false;
        stopLocationUpdates();
    }
    public void stopLocationUpdates() {
        mFusedLocationClient
                .removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }
    public void showLastKnownLocation() {
        if (mCurrentLocation != null) {
            Toast.makeText(getApplicationContext(), "Lat: " + mCurrentLocation.getLatitude()
                    + ", Lng: " + mCurrentLocation.getLongitude(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Last known location is not available!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e(TAG, "User agreed to make required location settings changes.");
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }
        updateLocationUI();
    }
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mRequestingLocationUpdates) {
            stopLocationUpdates(); }
    }
    private void  OwnBulidingFund(final String insid){
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(c.getTime());
        Calendar cc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(cc.getTime());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PLANINGPROCESS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("planigprocess"," "+response);
                        try {
                            JSONArray arraymesg =new JSONArray(response);
                            Log.e("planingprocessobj"," "+arraymesg);
                            for (int i=0; i<arraymesg.length(); i++){
                                JSONObject objmess = arraymesg.getJSONObject(i);
                                String message = objmess.getString("message");
                                planid = objmess.getString("plan_id");
                                Log.e("planID",planid);
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                LatLong(awcslat,awcsslong,insid);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "No Valid UserName And Password", Toast.LENGTH_SHORT).show();

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
                params.put("dist", DbdistID);
                params.put("project", DbprojectID);
                params.put("sector", DbsectorID);
                params.put("center", DbcenterID);
                params.put("plan_date", curDate);
                params.put("plan_time", curTime);
                params.put("user_id", userID);
                params.put("plan_typ", "sp");
                Log.e("PLANARPITA",DbdistID+" "+DbprojectID+" "+DbsectorID+" "+DbcenterID+" "+curDate+" "+curTime+" "+userID+" ");
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    public void LatLong(String lat,String lang,String insid){
        Log.e("LatLong",lat+" "+lang);
        String s = lat;
        String ss = lang;
        float lat1 = 0;
        float lng1 = 0;
        try {
            lat1 = Float.valueOf(s.trim()).floatValue();
            System.out.println("float f = " + lat1);
        } catch (NumberFormatException nfe) {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }
        try {
            lng1 = Float.valueOf(ss.trim()).floatValue();
            System.out.println("float f = " + lng1);
        } catch (NumberFormatException nfe) {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }
        distance(lat1, lng1, flat2, flng2,insid);
    }
    public float distance(float lat1, float lng1, float flat2, float flng2,String insid) {
        int Radius = 6371000;
        //int Radius = 127563200;
        double lat11 = lat1;
        double lat22 = flat2;
        double lon1 = lng1;
        double lon2 = flng2;
        double dLat = Math.toRadians(lat22 - lat11);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat11))
                * Math.cos(Math.toRadians(lat22)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.e("METER", " "+km);
        Log.e("Radius_Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);
        Log.e("DISTANCE"," "+meterInDec);
        if(meterInDec<200){
            Toast.makeText(SportInspectionActivity.this, "You are  in Center", Toast.LENGTH_SHORT).show();
            String query = "SELECT * FROM " + TABLE_NAMEAWCSDTL + " where " + TABLE_AWCSUSERID + "=" +userid;
            helper = new DatabaseHelper(getApplicationContext());
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor  res = db.rawQuery(query,null);
            if (res.moveToFirst()) {
                do {
                    awcsidDB = res.getString(res.getColumnIndex(DatabaseHelper.TABLE_AWCSID));
                    Log.e("awcsID"," "+awcsidDB);
                }
                while (res.moveToNext());
            }
            String queryy = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid;
            helper = new DatabaseHelper(getApplicationContext());
            SQLiteDatabase dbd = helper.getReadableDatabase();
            Cursor  ress = dbd.rawQuery(queryy,null);
            if (ress.moveToFirst()) {
                do {
                    centercode = ress.getString(ress.getColumnIndex(DatabaseHelper.TABLE_DBCENTERID));
                    Log.e("awcsCen"," "+centercode);
                }
                while (ress.moveToNext());
            }
            Log.e("AWCSCODE"," "+awcsidDB+" "+awcsid+" "+"CENTER"+centercode+""+" "+DbcenterID);
            if (awcsidDB.equals(awcsid)){
                //Toast.makeText(getApplicationContext(), "DUPLICATE CENTER", Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "DUPLICATE CENTER";
            createDialog(title,msg);
            }
            else {
                senddata(insid);
            }

        }
        else {

            Toast.makeText(SportInspectionActivity.this, "You are not in Center", Toast.LENGTH_SHORT).show();

        }
        return (float) (Radius * c);
        //  return dist;
    }

    public void senddata(final String insid){
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
            userID = String.valueOf(user.getUserID());
            helper.awcsprocessInsert(DbdistID,
                    DbprojectID,
                    DbsectorID,
                    DbcenterID,
                    projectnameDB,
                    districnameDB,
                    sectorrnameDB,
                    centernameDB,
                    systenDate,
                    userID,
                    flag,
                    awcslat,
                    awcsslong,
                    insid,
                    awcscode,
                    curTime,flaggrecord);
            helper.AllinspactionInsert(buildingdetails,
                    informationoftoilet,
                    informationofkitchen,
                    adequatespaceforpse,
                    electricity,
                    drinkingwater,
                    foodstoreddetails,
                    conditionofequipmentotherawc,
                    shishualoy,
                    snpserved,
                    nutritionalstatusobserved,
                    otherinspection,
                    allinspactionid,
                    userID);
            Log.e("inspactionid",insid+" "+allinspactionid);
            helper.awcsdtlInsert(water,
                    cdponame,
                    acdpocont,
                    buildstruc,
                    electric,
                    acdponame,
                    kitchen,
                    cdpocontact,
                    workerno,
                    worker_nm,
                    toilet,
                    awcslat,
                    supvsrname,
                    awcsslong,
                    helperno,
                    awcs_adrs,
                    foodspace,
                    helpernm,
                    buildon,
                    adqutspacepse,
                    supvsrno,
                    awcsid,
                    awcscode,
                    awcsname,
                    planid,
                    lstinspctnbuldrep,
                    lstinspctntoiletrep,
                    lstinspctnkitchenrep,
                    lstinspctnpserep,
                    lstinspctnelectricrep,
                    lstinspctndrnkwaterrep,
                    lstinspctnfoodrep,
                    userID,
                    flag,
                    insid);
            Log.e("CENTERPLAN"," "+water+" "
                    +cdponame+" "+acdpocont+" "+buildstruc+" "+
                    electric+" "+acdponame+" "+kitchen+" "+cdpocontact+" "+workerno+" "+worker_nm+" "+toilet+" "
                    +awcslat+" "+supvsrname+" "+awcsslong+" "+helperno+" "+awcs_adrs+" "+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+" "+awcsid+" "+awcscode+" "+awcsname+" "+planid+" "+lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep+" "+userID+" "+flag);
            Log.e("DISSID",DbdistID+" "+DbprojectID+" "+DbsectorID+" "+DbcenterID+" ");
            Intent intent = new Intent(getApplicationContext(), ProcedActivity.class);
          Bundle bundle_edit  =   new Bundle();
          bundle_edit.putString("water",water);
          bundle_edit.putString("cdponame",cdponame);
          bundle_edit.putString("acdpocont",acdpocont);
          bundle_edit.putString("buildstruc",buildstruc);
          bundle_edit.putString("electric",electric);
          bundle_edit.putString("acdponame",acdponame);
          bundle_edit.putString("kitchen",kitchen);
          bundle_edit.putString("cdpocontact",cdpocontact);
          bundle_edit.putString("workerno",workerno);
          bundle_edit.putString("worker_nm",worker_nm);
          bundle_edit.putString("toilet",toilet);
          bundle_edit.putString("awcslat",awcslat);
          bundle_edit.putString("supvsrname",supvsrname);
          bundle_edit.putString("awcsslong",awcsslong);
          bundle_edit.putString("helperno",helperno);
          bundle_edit.putString("awcs_adrs",awcs_adrs);
          bundle_edit.putString("foodspace",foodspace);
          bundle_edit.putString("helpernm",helpernm);
          bundle_edit.putString("buildon",buildon);
          bundle_edit.putString("adqutspacepse",adqutspacepse);
          bundle_edit.putString("supvsrno",supvsrno);
          bundle_edit.putString("awcsid",awcsid);
          bundle_edit.putString("awcscode",awcscode);
          bundle_edit.putString("awcsname",awcsname);
          bundle_edit.putString("DbdistID",DbdistID);
          bundle_edit.putString("DbprojectID",DbprojectID);
          bundle_edit.putString("DbsectorID",DbsectorID);
          bundle_edit.putString("DbcenterID",DbcenterID);
          bundle_edit.putString("projectnameDB",projectnameDB);
          bundle_edit.putString("districnameDB",districnameDB);
          bundle_edit.putString("sectorrnameDB",sectorrnameDB);
          bundle_edit.putString("centernameDB",centernameDB);
          bundle_edit.putString("systenDate",systenDate);
          bundle_edit.putString("planid",planid);
          bundle_edit.putString("lstinspctnbuldrep",lstinspctnbuldrep);
          bundle_edit.putString("lstinspctntoiletrep",lstinspctntoiletrep);
          bundle_edit.putString("lstinspctnkitchenrep",lstinspctnkitchenrep);
          bundle_edit.putString("lstinspctnpserep",lstinspctnpserep);
          bundle_edit.putString("lstinspctnelectricrep",lstinspctnelectricrep);
          bundle_edit.putString("lstinspctndrnkwaterrep",lstinspctndrnkwaterrep);
          bundle_edit.putString("lstinspctnfoodrep",lstinspctnfoodrep);
          bundle_edit.putString("allinspactionid",allinspactionid);
          bundle_edit.putString("flaggrecord",flaggrecord);
          intent.putExtras(bundle_edit);
          startActivity(intent);
      }
    public AlertDialog finalsubmit(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.finalsubmitdiolog, null);
        ((TextView)dialogView.findViewById(R.id.dialog_title)).setText(title);
        ((TextView)dialogView.findViewById(R.id.dialog_msg)).setText(msg);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        ((Button)dialogView.findViewById(R.id.yId)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        ((Button)dialogView.findViewById(R.id.nId)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
                Intent intent = new Intent(SportInspectionActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }
}
