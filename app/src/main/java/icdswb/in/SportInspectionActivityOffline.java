package icdswb.in;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityDatabase.Sportnetworkcheck;
import icdswb.in.ActivitySetGet.DistricSetGet;
import icdswb.in.ActivitySetGet.OOwnbuildingsetget;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

public class SportInspectionActivityOffline extends AppCompatActivity implements View.OnClickListener{
    private String TAG = SportInspectionActivityOffline.class.getSimpleName();
    Toolbar toolbar;
    EditText edt,edId1,edI2,edId3;
    Spinner spIDD;
    DistricSetGet districSetGet;
    List<DistricSetGet> districSetGetss;
    ArrayList<OOwnbuildingsetget> arrayList = new ArrayList();
    String disID,distname;
    List<String> Disname = new ArrayList<String>();
    List<String> DisID = new ArrayList<>();
    String[] spinnerArray;
    HashMap<Integer,String> spinnerMap;
    private DatabaseHelper db;
    ArrayList<String> stateName;
    String awccode,project,sector,center;
    Button procedId;
    public static final int SPORT_SYNCED_WITH_SERVER = 1;
    public static final int SPORT_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiverssport;
    public static final String DATA_SAVED_BROADCAST_SPORT = "icdswb.in.sportsaved";
    private Sportnetworkcheck sportnetworkcheck;
    User user;
    String usertyp,systenDate,sysdate;
    private DatabaseHelper helper;
    String distID ="";
    String projectID = "";
    String sectorID = "";
    String centerID = "";
    String DbdistID = "";
    String DbprojectID ="";
    String DbsectorID ="";
    String DbcenterID = "";
    String disName = "";
    String projectnameDB,districnameDB,sectorrnameDB,centernameDB;
    String flag = "0";
    String awcslat="NA";
    String awcsslong="NA";
    String awcscode="NA";
    String flaggrecord= "2";
    String flaggrecord3= "3";
    String curTime,curDate,userID;
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
    String allinspactionid= "0";
    String insid;
    ////////////////
    String planId;
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
   // String awcslat="NA";
    String supvsrname="NA";
    //String awcsslong="NA";
    String helperno="NA";
    String awcs_adrs="NA";
    String foodspace="NA";
    String helpernm="NA";
    String buildon="NA";
    String adqutspacepse="NA";
    String supvsrno="NA";
    String awcsid="NA";
   // String awcscode="NA";
    String awcsname="NA";
    String lstinspctnbuldrep="NA";
    String lstinspctntoiletrep="NA";
    String lstinspctnkitchenrep="NA";
    String lstinspctnpserep="NA";
    String lstinspctnelectricrep="NA";
    String lstinspctndrnkwaterrep="NA";
    String lstinspctnfoodrep = "NA";
    String planid;
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
    String systime;
    String bdawcode="0";
    String flaggrecord1 = "00";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sportinspectionactivityoffline);
        edt = (EditText)findViewById(R.id.edt);
        edId1 = (EditText)findViewById(R.id.edId1);
        edI2 = (EditText)findViewById(R.id.edI2);
        edId3 = (EditText)findViewById(R.id.edId3);
        edt.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        edId1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        edI2.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        edId3.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT){

                }
                return false;
            }
        });
        edId1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT){

                }
                return false;
            }
        });
        edI2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT){

                }
                return false;
            }
        });
        edId3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT){

                }
                return false;
            }
        });
        procedId =(Button)findViewById(R.id.procedId);
        procedId.setOnClickListener(this);
        spIDD =(Spinner)findViewById(R.id.spIDD);
        db = new DatabaseHelper(this);
        helper = new DatabaseHelper(this);
        stateName=new ArrayList<>();
        Disname =new ArrayList<>();
        DisID = new ArrayList<>();
        districSetGetss = new ArrayList<>();
        initToolBar();
        ownBulding();
        districDataSpOffline();
        broadcastReceiverssport = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        registerReceiver(broadcastReceiverssport, new IntentFilter(DATA_SAVED_BROADCAST_SPORT));
        sportnetworkcheck = new Sportnetworkcheck();
        registerReceiver(sportnetworkcheck, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        usertyp = String.valueOf(user.getUsertyp());
        Log.e("usertype"," "+usertyp);
        spIDD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DbdistID = spinnerMap.get(spIDD.getSelectedItemPosition());
                districnameDB = spIDD.getSelectedItem().toString();
                //Toast.makeText(parent.getContext(),"OFF"+DbdistID+" "+" "+districnameDB, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
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
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        systenDate = df3.format(c.getTime());
        Log.e("time"," "+systenDate);
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        String[] dateParts = curTime.split(":");
        String h = dateParts[0];
        String m = dateParts[1];
        systime= h+m;
        Log.e("systime",systime);
        Cursor us = helper.getLoginData();
        if (us.moveToFirst()) {
            do {
                userID = us.getString(us.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                Log.e("userid",userID);
            } while (us.moveToNext());
        }
        init();
        restoreValuesFromBundle(savedInstanceState);
        startLocationButtonClick();
        stopLocationButtonClick();
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Offline Planing");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.procedId:
                int elevendigitcode = edt.getText().length();
                String phoneNumberr = edt.getText().toString().trim();
                //String AWCCODELASTFOURDIGIT = phoneNumber.length() >= 2 ? phoneNumber.substring(phoneNumber.length() - 2): "";
                String input = phoneNumberr;     //input string
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
                if (elevendigitcode>0) {
                    if (edt.getText().length()>10) {
                        if (firstFourChars.equals("19")){
                            if (spIDD.getSelectedItem().toString().trim().equals("Select District")) {
                                // Toast.makeText(getApplicationContext(), "Select District", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "Select District";
                                createDialog(title, msg);
                            } else {
                                //String AWCCODE = edt.getText().toString().trim();
                                EditText mEdtPhoneNumber = (EditText) findViewById(R.id.edt);
                                String phoneNumber = mEdtPhoneNumber.getText().toString().trim();
                                String AWCCODE = phoneNumber.length() >= 4 ? phoneNumber.substring(phoneNumber.length() - 4) : "";
                                Log.e("AWCCODE", AWCCODE);
                                sysdate = systenDate.replaceAll("[-+.^:,]", "");
//                                planId = "P" + sysdate + DbdistID + userID + edt.getText().toString();
                                 planId = "P" + sysdate + DbdistID + userID+systime;
                                Log.e("planId", planId);
//                                insid = sysdate + DbdistID + edt.getText().toString();
                                insid = sysdate+DbdistID+systime;
                                sportofflinesync(DbdistID, insid, districnameDB, planId);
                                hideKeyboard(this);

                            }
                        }
                        else {
                            //Toast.makeText(getApplicationContext(),"Enter Start With 19",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "Enter Start With 19";
                            createDialog(title, msg);
                        }

                    } else {
                      //  Toast.makeText(getApplicationContext(), "Enter eleven digit code", Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "Enter eleven digit code";
                        createDialog(title, msg);
                    }
                }
                else {
                    //Toast.makeText(getApplicationContext(),"Enter Value",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "Enter Value";
                    createDialog(title, msg);
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
    public void sportofflinesync(final String DbdistID,final String insid,final String districnameDB,final String planId){
        Log.e("insid"," "+insid);
        awccode = edt.getText().toString().trim();
        project = edId1.getText().toString().trim();
        sector = edI2.getText().toString().trim();
        center = edId3.getText().toString().trim();
        if (TextUtils.isEmpty(awccode)) {
            edt.setError("Please enter awc code");
            edt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(project)) {
            edId1.setError("Please enter project name");
            edId1.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(sector)) {
            edI2.setError("Please enter sector name");
            edI2.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(center)) {
            edId3.setError("Please enter center name");
            edId3.requestFocus();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SPORTDATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.e("response",response);
                            String dist = obj.getString("dist");
                            String distname = obj.getString("dist_name");
                            awcslat = obj.getString("latitude");
                            awcsslong = obj.getString("longitude");
                            String projectid = obj.getString("project_id");
                            String projectname = obj.getString("project_name");
                            acdponame = obj.getString("acdpo_name");
                            acdpocont = obj.getString("acdpo_cont");
                            cdponame = obj.getString("cdpo_name");
                            cdpocontact = obj.getString("cdpo_contact");
                            String sectorid = obj.getString("sector_id");
                            String sectorname = obj.getString("sector_name");
                            supvsrname = obj.getString("supvsr_name");
                            awcscode = obj.getString("awcs_code");
                            worker_nm = obj.getString("worker_nm");
                            workerno = obj.getString("worker_no");
                            helpernm = obj.getString("helper_nm");
                            helperno = obj.getString("helper_no");
                            String status = obj.getString("status");
                            buildon = obj.getString("build_on");
                            electric = obj.getString("electric");
                            water = obj.getString("water");
                            foodspace = obj.getString("food_space");
                            lstinspctnbuldrep = obj.getString("lst_inspctn_buld_rep");
                            lstinspctntoiletrep = obj.getString("lst_inspctn_toilet_rep");
                            lstinspctnkitchenrep = obj.getString("lst_inspctn_kitchen_rep");
                            lstinspctnpserep = obj.getString("lst_inspctn_pse_rep");
                            lstinspctnelectricrep = obj.getString("lst_inspctn_electric_rep");
                            lstinspctndrnkwaterrep = obj.getString("lst_inspctn_drnkwater_rep");
                            lstinspctnfoodrep = obj.getString("lst_inspctn_food_rep");
                            buildstruc = obj.getString("build_struc");
                            toilet = obj.getString("toilet");
                            kitchen  = obj.getString("kitchen");
                            adqutspacepse = obj.getString("adqut_space_pse");
                            awcs_adrs = obj.getString("awcs_adrs");
                            supvsrno = obj.getString("supvsr_no");
                            //awcsid = obj.getString("awcs_id");
                           // awcscode = obj.getString("awcs_code");
                           // awcsname = obj.getString("center_name");
                            awcsid = obj.getString("center_id");
                            awcsname = obj.getString("center_name");
                            String  insidd = obj.getString("ins_id");
                            String planIdd = obj.getString("plan_id");
                            Log.e("Updatedtasyncc",dist+" "+distname+" "
                                    +awcslat+" "+awcsslong+" "+projectid+" "
                                    +projectname+" "+sectorid+" "+sectorid+" "
                                    +sectorname+" "+""+" "+""+" "+awcscode+" "+status+
                                    water+" "+cdponame+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+
                                    cdpocontact+" "+workerno+" "+worker_nm+" "
                                    +toilet+" "+awcslat+" "+supvsrname+" "+awcsslong+" "+helperno+" "
                                    +awcs_adrs+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+" "+awcsid+" "
                                    +awcsname+" "+lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "
                                    +lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep+" "+insidd+" "+planIdd);
                            if (status.equals("1")){
                                helper.awcsprocessInsert(dist,
                                        projectid,
                                        sectorid,
                                        awcsid,
                                        projectname,
                                        distname,
                                        sectorname,
                                        awcsname,
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
                                        insidd,
                                        userID);
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
                                        planIdd,
                                        lstinspctnbuldrep,
                                        lstinspctntoiletrep,
                                        lstinspctnkitchenrep,
                                        lstinspctnpserep,
                                        lstinspctnelectricrep,
                                        lstinspctndrnkwaterrep,
                                        lstinspctnfoodrep,
                                        userID,
                                        flag,
                                        insidd);
                                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                                startActivity(intent);

                            }
                            sportsyncsavedata(insid,awccode,DbdistID,planId,systenDate,curTime,userID, SPORT_NOT_SYNCED_WITH_SERVER);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        init();
                        sportsyncsavedata(insid,awccode,DbdistID,planId,systenDate,curTime,userID, SPORT_NOT_SYNCED_WITH_SERVER);
                        saverecord(insid,awccode,DbdistID,planId);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dist_id", DbdistID);
                params.put("awcs_code", awccode);
                params.put("plan_id", planId);
                params.put("ins_id", insid);
                params.put("plan_date",systenDate);
                params.put("plan_time",curTime);
                params.put("user_id",userID);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }
    private void sportsyncsavedata(String insid,String awccode,String DbdistID,String planId ,String systenDate,String curTime,String userIDint, int sportstatus){
        db.SPORTINSER(insid,awccode,DbdistID,planId,systenDate,curTime,userIDint,sportstatus);

    }
    public void saverecord(final String insid,final String awccode,final String DbdistID,final String planId) {
        //projectnameDB districnameDB sectorrnameDB centernameDB
        db = new DatabaseHelper(getApplicationContext());
        Cursor cc = db.getReadableDatabase().
                rawQuery("select * from awcsprocess where awcscodeid = ? and flaggrecord = ?", new String[]{awccode,flaggrecord});
        if (cc.moveToFirst()) {
            do {
                bdawcode = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_AWCSCODEID));
                flaggrecord1 = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FLAGGRECORD));


            } while (cc.moveToNext());
        }
        Log.e("Eqaldataplan", bdawcode + " " + flaggrecord1);
        Cursor res = db.getawcsprocessInsert();
        if(res.getCount() == 0) {
            Log.e("procestaleerror","Nothing found");
            Log.e("Insert","0");
            helper.awcsprocessInsert(DbdistID,
                    DbprojectID,
                    DbsectorID,
                    DbcenterID,
                    edId1.getText().toString().trim(),
                    districnameDB,
                    edI2.getText().toString().trim(),
                    edId3.getText().toString().trim(),
                    systenDate,
                    userID,
                    flag,
                    awcslat,
                    awcsslong,
                    insid,
                    edt.getText().toString().trim(),
                    curTime, flaggrecord);
            Log.e("procestaleerror", awcslat + " " + awcsslong);
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
                    insid,
                    userID);
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
                    planId,
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
            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
            startActivity(intent);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

        }

///////////////////////////
        if (bdawcode.equals(awccode) && flaggrecord.equals(flaggrecord1)) {
            String title = "Message Box";
            String msg = "DUPLICATE CENTER";
            finalsubmit(title, msg);
        } else {
            Log.e("Insert", "1");
            helper.awcsprocessInsert(DbdistID,
                    DbprojectID,
                    DbsectorID,
                    DbcenterID,
                    edId1.getText().toString().trim(),
                    districnameDB,
                    edI2.getText().toString().trim(),
                    edId3.getText().toString().trim(),
                    systenDate,
                    userID,
                    flag,
                    awcslat,
                    awcsslong,
                    insid,
                    edt.getText().toString().trim(),
                    curTime, flaggrecord);
            Log.e("Eqaldataplan", awcslat + " " + awcsslong);
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
                    insid,
                    userID);
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
                    planId,
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
            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
            startActivity(intent);
        }



    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
//        startActivity(intent);
//    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        System.out.println("Inside onResume");
//
//    }

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

//    @Override
//    public void onPause(){
//        super.onPause();
//        System.out.println("Inside onPause");
//    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("Inside onStop");
    }

    @Override
    public void onDestroy(){
        try{
            if(sportnetworkcheck!=null)
                unregisterReceiver(sportnetworkcheck);
            if (sportnetworkcheck!=null)
                unregisterReceiver(sportnetworkcheck);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
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
            awcslat = String.valueOf(mCurrentLocation.getLatitude());
            awcsslong = String.valueOf(mCurrentLocation.getLongitude());
            Log.e("second",llat2+"  "+"  "+llng2);
            Log.e("Offlinelatlang",awcslat+" "+awcsslong);
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
                                    rae.startResolutionForResult(SportInspectionActivityOffline.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);
                                Toast.makeText(SportInspectionActivityOffline.this, errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }
    public void startLocationButtonClick(){
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
    public void LatLong(String id,String dist,String  project,String sector,String center,String insid,String flagg,String lat,String lang,
                        String dbdistid,String dbprojectid,String dbsectorid,String dbcenterid,String awcscode,String flaggrecord){
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
        distance(lat1, lng1, flat2, flng2,id,dist,project,sector,center,insid,flagg,dbdistid,dbprojectid,dbsectorid,dbcenterid,awcscode,flaggrecord);
    }
    public float distance(float lat1, float lng1, float flat2, float flng2,String id,String distt,String project,String sector,
                          String center,String insid,String flagg,String dbdistid,String dbprojectid,
                          String dbsectorid,String dbcenterid,String awcscode,String flaggrecord) {
        //double earthRadius = 6371000; //meters
        // double earthRadius = 127563200; //meters
//       double earthRadius = 6371;
//        double dLat = Math.toRadians(flat2 - lat1);
//        double dLng = Math.toRadians(flng2 - lng1);
//        Log.e("tdis", ""+dLat+  " "+ ""+dLng);
//        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
//                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(flat2)) *
//                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        Log.e("tdis1", ""+c);
//        float dist = (float) (earthRadius * c);
//        Log.e("dist", String.valueOf(dist));
//        float strDouble = Float.parseFloat(String.format("%.0f", dist));
//        System.out.println(strDouble);
//        Log.e("Startloc",""+strDouble);
        //int Radius = 6371;// radius of earth in Km
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
//        if(meterInDec<200){
//            Intent intent = new Intent(getApplicationContext(),ProcedActivityy.class);
//            Bundle bundle_edit  =   new Bundle();
//            bundle_edit.putString("id",id);
//            bundle_edit.putString("dist",distt);
//            bundle_edit.putString("dbdistid",dbdistid);
//            bundle_edit.putString("project",project);
//            bundle_edit.putString("dbprojectid",dbprojectid);
//            bundle_edit.putString("sector",sector);
//            bundle_edit.putString("dbsectorid",dbsectorid);
//            bundle_edit.putString("center",center);
//            bundle_edit.putString("dbcenterid",dbcenterid);
//            bundle_edit.putString("awcscode",awcscode);
//            bundle_edit.putString("insid",insid);
//            bundle_edit.putString("flag",flagg);
//            bundle_edit.putString("flaggrecord",flaggrecord);
//            intent.putExtras(bundle_edit);
//            startActivity(intent);
//            Toast.makeText(SportInspectionActivityOffline.this, "You are  in Center", Toast.LENGTH_SHORT).show();
//        }
//        else {
//
//            Toast.makeText(SportInspectionActivityOffline.this, "You are not in Center", Toast.LENGTH_SHORT).show();
//        }
        return (float) (Radius * c);
        //  return dist;
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
                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }
}
