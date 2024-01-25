package icdswb.in;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.HttpHandler;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.OtherActivity.Consts;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener,Spinner.OnItemSelectedListener{
    RadioGroup GploadprojectId;
    String sta,dis,proj,sec;
    LinearLayout lv6,lv7,lv8,lv5;
    private EditText userID,userPass,contractId,nameId;
    Button subId;
    Spinner spdisgID,spdistricID,spprojectId,spsectorID;
  //  private String TAG = PlaningActivity.class.getSimpleName();
    Toolbar toolbar;
    ArrayList<String> stateName;
    ArrayList<String> DISGNAME;
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
    String distID,projectID,sectorID,centerID;
    String DbdistID = "NA";
    String DbprojectID = "NA";
    String DbsectorID = "NA";
    String DbcenterID = "NA";
 //   Button searchID,procedId;
    SharedPreferences spp;
    String LOADSPINER="1";
    EditText edt;
    String awcs_code;
    String userid;
    String  discode ="30";
    String usertyp = "STA";
    private DatabaseHelper db;
    private DatabaseHelper helper;
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
    String curTime,curDate;
    String planid;
    String flag = "0";
    String water,cdponame,acdpocont,buildstruc,electric,acdponame,kitchen,cdpocontact,workerno,worker_nm,toilet,awcslat,supvsrname,awcsslong,helperno,awcs_adrs,foodspace,helpernm,buildon,adqutspacepse,supvsrno,awcsid,awcscode,awcsname,lstinspctnbuldrep,lstinspctntoiletrep,lstinspctnkitchenrep,lstinspctnpserep,lstinspctnelectricrep,lstinspctndrnkwaterrep,lstinspctnfoodrep;
    String inspactionid ="0";
    String awcsidDB = "0";
    String centercode = "0";
    LinearLayout elevendigitVisableNotvisableId;
    TextView awctextId;
    User user;
    private JSONArray res;
    String desigid,designame;
    //ImageView imageView;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int Image_Capture_Code = 2;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Bitmap bitmap;
    private Uri filePath;
    String filePathImg;
    String file_extn = "pinku";
    public static final String UPLOAD_KEY = "tmp_name";
    private File actualImage;
    private static final String TAG = "IMAGEUPLOAD";
    Button ImgUploadId,ImgdeleteId;
    String url;
    private ImageLoader imageLoader;
   // private NetworkImageView imageView;
    ImageView imageView;
    String userr,pass,contract,namee;
    SharedPreferences sp;
    String UPLOADIMAGE;
    String UPLOADIMAGEE = "1";
    RadioButton staId,disId,projId,secId;
    String curdate;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    TextView FdateId;
    String ecckitdate;
    ImageView searchID;
    RadioGroup malefemaleId;
    RadioButton maleId,fmaleId;
    String malefemal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.registration_activity);
        GploadprojectId = (RadioGroup)findViewById(R.id.GploadprojectId);
        lv6 = (LinearLayout)findViewById(R.id.lv6);
        lv7 = (LinearLayout)findViewById(R.id.lv7);
        lv8 = (LinearLayout)findViewById(R.id.lv8);
        lv5 = (LinearLayout)findViewById(R.id.lv5);
        lv5.setVisibility(View.VISIBLE);
        userID = (EditText)findViewById(R.id.userID);
        nameId = (EditText)findViewById(R.id.nameId);
        userPass = (EditText)findViewById(R.id.userPass);
        contractId = (EditText)findViewById(R.id.contractId);
        subId =(Button)findViewById(R.id.subId);
        FdateId =(TextView)findViewById(R.id.FdateId);
        searchID =(ImageView)findViewById(R.id.searchID);
        subId.setOnClickListener(this);
        staId =(RadioButton)findViewById(R.id.staId);
        disId =(RadioButton)findViewById(R.id.disId);
        projId =(RadioButton)findViewById(R.id.projId);
        secId =(RadioButton)findViewById(R.id.secId);
        malefemaleId = (RadioGroup)findViewById(R.id.malefemaleId);
        maleId = (RadioButton)findViewById(R.id.maleId);
        fmaleId = (RadioButton)findViewById(R.id.fmaleId);
        imageView = (ImageView) findViewById(R.id.imageView);
        ImgUploadId =(Button)findViewById(R.id.ImgUploadId);
        ImgdeleteId =(Button)findViewById(R.id.ImgdeleteId);
        ImgUploadId.setOnClickListener(this);
        ImgdeleteId.setOnClickListener(this);
        lv6.setVisibility(View.GONE);
        lv7.setVisibility(View.GONE);
        lv8.setVisibility(View.GONE);
        spdisgID =(Spinner)findViewById(R.id.spdisgID);
        spdistricID =(Spinner)findViewById(R.id.spdistricID);
        spprojectId =(Spinner)findViewById(R.id.spprojectId);
        spsectorID =(Spinner)findViewById(R.id.spsectorID);
        spp = getSharedPreferences(LOADSPINER, MODE_PRIVATE);
        SharedPreferences.Editor edit1 = spp.edit();
        edit1.putString("LOADSPINER", "0");
        edit1.commit();
        if (LOADSPINER==spp.getString("LOADSPINER","")){
        }
        else {
            if (isNetworkAvailable()){
                loadSpinnerData(discode,usertyp);
            }
        }
        malefemaleId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.maleId){
                  malefemal = "M";
                }
                else if (checkedId== R.id.fmaleId){
                    malefemal = "F";

                }
                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
/////////////////////////////
        GploadprojectId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.staId){
                    sta = "STA";
                    lv6.setVisibility(View.GONE);
                    lv7.setVisibility(View.GONE);
                    lv8.setVisibility(View.GONE);
                    lv5.setVisibility(View.VISIBLE);
                    //usertyp = "STA";
                    if (isNetworkAvailable()){
                        usertyp = "STA";
                        discode ="30";
                        //loadSpinnerData(discode,usertyp);
                        DISGNAME.clear();
                        submitdata(usertyp);
                       // DISGNAME.clear();
                        //  Toast.makeText(getApplicationContext(),"1Hi",Toast.LENGTH_SHORT).show();

                    }
                }
                else if (checkedId== R.id.disId){
                    dis = "DIS";
                    lv6.setVisibility(View.VISIBLE);
                    lv7.setVisibility(View.GONE);
                    lv8.setVisibility(View.GONE);
                    lv5.setVisibility(View.VISIBLE);
                        if (isNetworkAvailable()){
                            usertyp = "DIS";
                            discode ="30";
                           // loadSpinnerData(discode,usertyp);
                            DISGNAME.clear();
                            submitdata(usertyp);
                           // DISGNAME.clear();
                            if (disId.isChecked()){
                                loadproject();
                                projectname.clear();
                                sectorName.clear();
                            }
                        }
                }
                else if (checkedId== R.id.projId){
                    proj = "PROJ";
                    lv6.setVisibility(View.VISIBLE);
                    lv7.setVisibility(View.VISIBLE);
                    lv8.setVisibility(View.GONE);
                    lv5.setVisibility(View.VISIBLE);
                    if (isNetworkAvailable()){
                            usertyp = "PROJ";
                            discode ="30";
                           // loadSpinnerData(discode,usertyp);
                            DISGNAME.clear();
                            submitdata(usertyp);
                           // DISGNAME.clear();
                    }
                }
                else if (checkedId== R.id.secId){
                    sec = "SEC";
                    lv6.setVisibility(View.VISIBLE);
                    lv7.setVisibility(View.VISIBLE);
                    lv8.setVisibility(View.VISIBLE);
                    lv5.setVisibility(View.VISIBLE);
                    if (isNetworkAvailable()){
                            usertyp = "SEC";
                            discode ="30";
                        //  loadSpinnerData(discode,usertyp);
                            DISGNAME.clear();
                            submitdata(usertyp);
                           // DISGNAME.clear();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        spp = getSharedPreferences(Consts.DISTRICCHECK, MODE_PRIVATE);
        SharedPreferences.Editor edit11 = spp.edit();
        edit11.putString("Discheck", "0");
        edit11.commit();
        spdistricID.setOnTouchListener(new View.OnTouchListener() {
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
        spprojectId.setOnTouchListener(new View.OnTouchListener() {
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
        spsectorID.setOnTouchListener(new View.OnTouchListener() {
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
        stateName=new ArrayList<>();
        projectname = new ArrayList<>();
        sectorName = new ArrayList<>();
        DISGNAME = new ArrayList<>();
      // DISGNAME.clear();
        submitdata(usertyp);
        DISGNAME.clear();
        spdisgID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                desigid = getIDDIS(position);
                designame = spdisgID.getSelectedItem().toString();
               // Toast.makeText(parent.getContext(),desigid+" "+" "+designame, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                finish();
                startActivity(getIntent());
            }
        });
       // loadImage();
        sp = getApplicationContext().getSharedPreferences(SharePreferanceSaveData.UPLOADIMAGE, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("UPLOADIMAGE","0");
        edit.commit();
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        curdate = format.format(today);
        Log.e("CUR", curdate);
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                ////////////////////////////////////////////////
                Fromdate(curdate);
            }
        };
        searchID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegistrationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void Fromdate(final String curdate) {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdff = new SimpleDateFormat(myFormat, Locale.US);
        FdateId.setText(sdff.format(myCalendar.getTime()));
        ecckitdate = FdateId.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(FdateId.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (curdate.equals(FdateId.getText().toString()) || new Date().after(strDate)) {
            FdateId.setText(sdff.format(myCalendar.getTime()));
            ecckitdate = FdateId.getText().toString();
        } else {
            FdateId.setText("Date");
            ecckitdate = FdateId.getText().toString();
            Toast.makeText(getApplicationContext(), "NO VALID DATE", Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "NO VALID DATE";
            createDialog(title,msg);

        }
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.subId:
                if (staId.isChecked() || disId.isChecked() || projId.isChecked() || secId.isChecked()){
                    sp = getApplicationContext().getSharedPreferences(SharePreferanceSaveData.UPLOADIMAGE, MODE_PRIVATE);
                    UPLOADIMAGE = sp.getString("UPLOADIMAGE","");
                    Log.e("PsaveDat",UPLOADIMAGE);
                    if (UPLOADIMAGE.equals(UPLOADIMAGEE)){
                        valitionsavedata();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"SELECT CAMERA",Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT CAMERA";
                        createDialog(title,msg);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"SELECT LAVEL",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT LAVEL";
                    createDialog(title,msg);
                }
                break;
            case R.id.ImgUploadId:
                sp = getApplicationContext().getSharedPreferences(SharePreferanceSaveData.UPLOADIMAGE, MODE_PRIVATE);
                SharedPreferences.Editor editt = sp.edit();
                editt.putString("UPLOADIMAGE","1");
                editt.commit();
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
                } else {
                    loadImg();
                }
                break;
            case R.id.ImgdeleteId:
                sp = getApplicationContext().getSharedPreferences(SharePreferanceSaveData.UPLOADIMAGE, MODE_PRIVATE);
                SharedPreferences.Editor edittt = sp.edit();
                edittt.putString("UPLOADIMAGE","1");
                edittt.commit();
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 110);
                } else {
                    takePhotoFromCamera();
                }
                break;
                default:
        }
    }
    private void  loadSpinnerData(final String discode,final String usertyp){
        class DistricData extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                // String url = "http://202.0.103.141/inspection_service/login_district.php";
                //String url = "http://icdswb.in/inspection_service/get_district.php?dist="+discode+"&&user_typ="+usertyp;
                String url = "http://icdswb.in/inspection_service/get_district.php?dist=30&&user_typ=STA";
                Log.e("url",url);
                String response = sh.makeServiceCall(url);
                Log.e("TAG", "Response from url attandence: " + response);
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
                spdistricID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        districnameDB = stateName.get(position);
                        DbdistID = getID(position);
                        Log.e("districtname",districnameDB+" "+DbdistID);

                        ///////////////////////////////////////////////////////////////////////////
                      //  Toast.makeText(getApplicationContext(),districnameDB+" "+DbdistID+"Dis",Toast.LENGTH_SHORT).show();
                        if (LOADSPINER==spp.getString("LOADSPINER","")){
                            districnameDB = stateName.get(position);
                            DbdistID = getID(position);
                            //Toast.makeText(getApplicationContext(),districnameDB+" "+DbdistID+"Dis",Toast.LENGTH_SHORT).show();
                        }
                        else {

                            distID = getID(position);
                            //  Toast.makeText(getApplicationContext(),"DisId"+distID,Toast.LENGTH_SHORT).show();
                            loadproject();
                            projectname.clear();
                            sectorName.clear();
//                            centerName.clear();
                        }
                        if(districcheck==spp.getString("Discheck","")) {
                            districnameDB = stateName.get(position);
                            DbdistID = getID(position);
                            Log.e("districtname",districnameDB+" "+DbdistID);
                           // Toast.makeText(getApplicationContext(),districnameDB+" "+DbdistID+"Dis",Toast.LENGTH_SHORT).show();

                        }
                        else {

                        }

                        //////////////////////////////////////////////////
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
        spdistricID.setAdapter(new ArrayAdapter<String>(RegistrationActivity.this,  android.R.layout.simple_spinner_dropdown_item, stateName));
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
                String url = "http://icdswb.in/inspection_service/filter_project2.php?dist="+distID;
                String response = sh.makeServiceCall(url);
                Log.e("TAG", "Response from url attandence: " + response);
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
                spprojectId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        projectnameDB = projectname.get(position);
                        DbprojectID = getprojectID(position);
                        Log.e("projectname",projectnameDB+ " "+DbprojectID);
                       // Toast.makeText(getApplicationContext(),projectnameDB+" "+DbprojectID+"Pro", Toast.LENGTH_SHORT).show();
                        if (LOADSPINER==spp.getString("LOADSPINER","")){
                            projectnameDB = projectname.get(position);
                            DbprojectID = getprojectID(position);
                            Log.e("projectname",projectnameDB+ " "+DbprojectID);
                        }
                        else {
                            projectID = getprojectID(position);
                            loadsector();
                            sectorName.clear();
                          //  centerName.clear();
                        }
                        if(projeccheckk==spp.getString("check","")) {
                            projectnameDB = projectname.get(position);
                            DbprojectID = getprojectID(position);
                            Log.e("projectname",projectnameDB+ " "+DbprojectID);
                           // Toast.makeText(getApplicationContext(),projectnameDB+" "+DbprojectID+"Pro", Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                     //   Toast.makeText(getApplicationContext(),"HI", Toast.LENGTH_SHORT).show();
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
        spprojectId.setAdapter(new ArrayAdapter<String>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, projectname));

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
                String url = "http://icdswb.in/inspection_service/filter_sector1.php?dist="+distID+"&&proj_id="+projectID;
                String response = sh.makeServiceCall(url);

                Log.e("TAG", "Response from url attandence: " + response);
                if (response != null) {
                    JSONObject j = null;
                    try {
                        j = new JSONObject(response);
                        sector = j.getJSONArray(Config.JSON_ARRAY_SECTOR);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                spsectorID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        sectorrnameDB = sectorName.get(position);
                        DbsectorID = getsectorID(position);
                        Log.e("sectorcheckk",sectorrnameDB+" "+DbsectorID);
                      //  Toast.makeText(getApplicationContext(),sectorrnameDB+ " "+DbsectorID+"sector", Toast.LENGTH_SHORT).show();
                        if (LOADSPINER==spp.getString("LOADSPINER","")){
                            sectorrnameDB = sectorName.get(position);
                            DbsectorID = getsectorID(position);
                            Log.e("sectorcheckk",sectorrnameDB+" "+DbsectorID);
                        }
                        else {
                            sectorID =  getsectorID(position);
                            //    Toast.makeText(getApplicationContext(),"SectorID"+sectorID,Toast.LENGTH_SHORT).show();
                           // loadcenter();
                           // centerName.clear();
                        }
                        if(sectorcheckk==spp.getString("sectorcheck","")) {
                            sectorrnameDB = sectorName.get(position);
                            DbsectorID = getsectorID(position);
                            Log.e("sectorcheckk",sectorrnameDB+" "+DbsectorID);
                          //  Toast.makeText(getApplicationContext(),sectorrnameDB+ " "+DbsectorID+"sector", Toast.LENGTH_SHORT).show();
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
                JSONObject json = j.getJSONObject(i);
                sectorName.add(json.getString(Config.TAG_SECTORNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spsectorID.setAdapter(new ArrayAdapter<String>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, sectorName));
    }
    private String getsectorID(int position){
        String name="";
        try {
            JSONObject json = sector.getJSONObject(position);
            name = json.getString(Config.TAG_SECTORID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                Log.e("PrintData"," "+url);
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
//                sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//
//
//                        if (LOADSPINER==spp.getString("LOADSPINER","")){
//                            centernameDB = centerName.get(position);
//                            DbcenterID = getcenterID(position);
//                            Log.e("sectorcheckk",centernameDB+" "+DbcenterID);
//                        }
//                        else {
//                            centerID = getcenterID(position);
//                            //   Toast.makeText(getApplicationContext(),"cenID"+centerID,Toast.LENGTH_SHORT).show();
//                        }
//                        if(centercheckk==spp.getString("centercheck","")) {
//                            centernameDB = centerName.get(position);
//                            DbcenterID = getcenterID(position);
//                            Log.e("sectorcheckk",centernameDB+" "+DbcenterID);
//                            Toast.makeText(getApplicationContext(),centernameDB+ " "+DbcenterID+"center", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//                getcenter(center);
//                super.onPostExecute(result);

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
       // sp3.setAdapter(new ArrayAdapter<String>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, centerName));

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


    public void submitdata(final String usertyp){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://icdswb.in/inspection_service/designation_for.php?user_type="+usertyp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("resDATA",response);
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            res = j.getJSONArray(Config.JSON_ARRAYDISG);
                            Log.e("ddd"," "+res);
                            for (int i = 0;i<res.length(); i++){
                                JSONObject jsonObject = res.getJSONObject(i);
                                desigid = jsonObject.getString("desig_id");
                                designame  = jsonObject.getString("desig_name");
                                Log.e("resDATA22"," "+desigid+" "+designame+" ");

                            }
                            Log.e("res"," "+res);
                            getdisg(res);
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
    private void getdisg(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                DISGNAME.add(json.getString(Config.TAG_DISG));
                Log.e("DISGNAME"," "+stateName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spdisgID.setAdapter(new ArrayAdapter<String>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, DISGNAME));
    }
    private String getIDDIS(int position){
        try {
            JSONObject json = res.getJSONObject(position);
            desigid = json.getString(Config.TAG_DISGID);
            Log.e("desigid"," "+desigid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desigid;
    }

    public void registrationactivity(){


    }
    //////////////////////////////////////////////
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                actualImage = FileUtil.from(getApplicationContext(),data.getData());
                //  Log.e("Path", String.valueOf(actualImage));
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.fromFile(actualImage));
                imageView.setImageBitmap(bitmap);
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
                imageView.setImageBitmap(bitmap);
                Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                File finalFile = new File(getRealPathFromURII(tempUri));
                //Log.e("IMGPATH"," "+finalFile);
                url = String.valueOf(finalFile);
                actualImage = finalFile;
                Log.e("path", String.valueOf(actualImage));
                String fileImage = String.valueOf(actualImage);
                file_extn = fileImage.substring(fileImage.lastIndexOf("/") + 1);;
            } else if (resultCode == RESULT_CANCELED) {

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
    public String getPath(Uri uri) {
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return path;
        }
        return null;
    }
    @SuppressLint("UnsupportedChromeOsCameraSystemFeature")
    private void takePhotoFromCamera() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, Image_Capture_Code);
        } else {

        }
    }
    private void showFileChooser() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }
    public void loadImg(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_IMAGE_REQUEST);
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

//    private void loadImage(){
//        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
//                .getImageLoader();
//        imageLoader.get(url, ImageLoader.getImageListener(imageView,
//                R.drawable.user, android.R.drawable
//                        .ic_dialog_alert));
//        imageView.setImageUrl(url, imageLoader);
//    }

        public void valitionsavedata(){
        Log.e("HHHH","HI");
            userr = userID.getText().toString().trim();
            pass = userPass.getText().toString().trim();
            contract = contractId.getText().toString().trim();
            namee = nameId.getText().toString().trim();
            if (TextUtils.isEmpty(namee)) {
                nameId.setError("Please enter Name");
                nameId.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                userPass.setError("Please enter password");
                userPass.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(userr)) {
                userID.setError("Please enter username");
                userID.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(contract)) {
                contractId.setError("Please enter phone Number");
                contractId.requestFocus();
                return;
            }
            if (staId.isChecked()){
                if (spdisgID.getSelectedItem().toString().trim().equals("Select Designation")) {
                    Toast.makeText(getApplicationContext(), "SELECT DESIGNATION", Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT DESIGNATION";
                    createDialog(title,msg);
                }
                else {
                    if (contract.length()>9){
                        if (maleId.isChecked() ||fmaleId.isChecked()){
                            if (FdateId.getText().toString().equals("Date")){
                                Toast.makeText(getApplicationContext(), "SELECT DATE", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT DATE";
                                createDialog(title,msg);
                            }
                            else {
                                saveregistration(actualImage, usertyp, userr, pass, contract, desigid, DbdistID, DbprojectID, DbsectorID, namee, malefemal, ecckitdate);
                            }
                        }

                       else {
                            Toast.makeText(getApplicationContext(), "SELECT GENDER", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT GENDER";
                            createDialog(title,msg);

                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "ENTER 10 DIGIT NUMBER", Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "ENTER 10 DIGIT NUMBER";
                        createDialog(title,msg);
                    }

                }
            }
            /////////////////////////
            else if (disId.isChecked()){
                if (spdisgID.getSelectedItem().toString().trim().equals("Select Designation")) {
                    Toast.makeText(getApplicationContext(), "SELECT DESIGNATION", Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT DESIGNATION";
                    createDialog(title,msg);
                }
                else {
                    if (spdistricID.getSelectedItem().toString().trim().equals("Select District")) {
                        Toast.makeText(getApplicationContext(), "SELECT DISTRICT", Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT DISTRICT";
                        createDialog(title,msg);
                    }
                    else {
                        if (contract.length()>9){
                            if (maleId.isChecked() ||fmaleId.isChecked()){
                                if (FdateId.getText().toString().equals("Date")){
                                    Toast.makeText(getApplicationContext(), "SELECT DATE", Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT DATE";
                                    createDialog(title,msg);
                                }
                                else {
                                    saveregistration(actualImage, usertyp, userr, pass, contract, desigid, DbdistID, DbprojectID, DbsectorID, namee, malefemal, ecckitdate);
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "SELECT GENDER", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT GENDER";
                                createDialog(title,msg);

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "ENTER 10 DIGIT NUMBER", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "ENTER 10 DIGIT NUMBER";
                            createDialog(title,msg);
                        }
                    }
                }

            }
            /////////////////////////////////
            else if (projId.isChecked()){
                if (spdisgID.getSelectedItem().toString().trim().equals("Select Designation")) {
                    Toast.makeText(getApplicationContext(), "SELECT DESIGNATION", Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT DESIGNATION";
                    createDialog(title,msg);
                }
                else {
                    if (spdistricID.getSelectedItem().toString().trim().equals("Select District")) {
                        Toast.makeText(getApplicationContext(), "SELECT DISTRICT", Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT DISTRICT";
                        createDialog(title,msg);
                    }
                    else {
                        if (spprojectId.getSelectedItem().toString().trim().equals("Select Project")) {
                            Toast.makeText(getApplicationContext(), "SELECT PROJECT", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT DISTRICT";
                            createDialog(title,msg);
                        }
                        else {
                            if (contract.length()>9){
                                if (maleId.isChecked() ||fmaleId.isChecked()){
                                    if (FdateId.getText().toString().equals("Date")){
                                        Toast.makeText(getApplicationContext(), "SELECT DATE", Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SELECT DATE";
                                        createDialog(title,msg);
                                    }
                                    else {
                                        saveregistration(actualImage, usertyp, userr, pass, contract, desigid, DbdistID, DbprojectID, DbsectorID, namee, malefemal, ecckitdate);
                                    }
                                }

                                else {
                                    Toast.makeText(getApplicationContext(), "SELECT GENDER", Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT GENDER";
                                    createDialog(title,msg);

                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "ENTER 10 DIGIT NUMBER", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "ENTER 10 DIGIT NUMBER";
                                createDialog(title,msg);
                            }
                        }
                    }
                }
            }
            ///////////////////////////////
            else if (secId.isChecked()){
                if (spdisgID.getSelectedItem().toString().trim().equals("Select Designation")) {
                    Toast.makeText(getApplicationContext(), "SELECT DESIGNATION", Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT DESIGNATION";
                    createDialog(title,msg);
                }
                else {
                    if (spdistricID.getSelectedItem().toString().trim().equals("Select District")) {
                        Toast.makeText(getApplicationContext(), "SELECT DISTRICT", Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT DISTRICT";
                        createDialog(title,msg);
                    }
                    else {
                        if (spprojectId.getSelectedItem().toString().trim().equals("Select Project")) {
                            Toast.makeText(getApplicationContext(), "SELECT PROJECT", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT DISTRICT";
                            createDialog(title,msg);
                        }
                        else {
                            if (spsectorID.getSelectedItem().toString().trim().equals("Select Sector")) {
                                Toast.makeText(getApplicationContext(), "SELECT SECTOR", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT SECTOR";
                                createDialog(title,msg);
                            }
                            else {
                                if (contract.length()>9){
                                    if (maleId.isChecked() ||fmaleId.isChecked()){
                                        if (FdateId.getText().toString().equals("Date")){
                                            Toast.makeText(getApplicationContext(), "SELECT DATE", Toast.LENGTH_SHORT).show();
                                            String title = "Message Box";
                                            String msg = "SELECT DATE";
                                            createDialog(title,msg);
                                        }
                                        else {
                                            saveregistration(actualImage, usertyp, userr, pass, contract, desigid, DbdistID, DbprojectID, DbsectorID, namee, malefemal, ecckitdate);
                                        }
                                    }

                                    else {
                                        Toast.makeText(getApplicationContext(), "SELECT GENDER", Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SELECT GENDER";
                                        createDialog(title,msg);

                                    }

                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "ENTER 10 DIGIT NUMBER", Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "ENTER 10 DIGIT NUMBER";
                                    createDialog(title,msg);
                                }
                            }
                        }
                    }
                }
            }
////////////////////////////////////////////////////////////////////////////////
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
    public void saveregistration(final File imagePath,final String usertyp,final String userr,final String pass,
                                 final String contract,final String desigid,final String DbdistID,final String DbprojectID,final String DbsectorID,
                              final  String namee,final String malefemal,final String ecckitdate){
        Log.e("OKKK","HI");
        Log.e("SHOWDATA"," "+imagePath+" "+" "
                +usertyp+" "+userr+" "+pass+" "+contract+" "+desigid+" "+DbdistID+" "+DbprojectID+" "+DbsectorID+" "+namee+" "+malefemal+" "+ecckitdate);
        AndroidNetworking.upload(Config.REGISTRATION)
                .addMultipartFile(UPLOAD_KEY,imagePath)
                .addMultipartParameter("user_type",usertyp)
                .addMultipartParameter("usr_nm",userr)
                .addMultipartParameter("usr_pwd",pass)
                .addMultipartParameter("usr_phn",contract)
                .addMultipartParameter("usr_desig",desigid)
                .addMultipartParameter("dist",DbdistID)
                .addMultipartParameter("proj_id",DbprojectID)
                .addMultipartParameter("sec_id",DbsectorID)
                .addMultipartParameter("name",namee)
                .addMultipartParameter("sex",malefemal)
                .addMultipartParameter("dob",ecckitdate)
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                        Log.e(TAG, "onProgress: called..." );
                        Log.e("SHOWDATA"," "+imagePath+" "+" "
                                +usertyp+" "+userr+" "+pass+" "+contract+" "+desigid+" "+DbdistID+" "+DbprojectID+" "+DbsectorID+" "+namee+" "+malefemal+" "+ecckitdate);
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
                            if (status.equals("1")){
                           //     Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msgg = msg;
                                Register(title,msgg);
                            }
                            else {
                                //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msgg = msg;
                                createDialog(title,msgg);
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

    public AlertDialog Register(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.finalsubmitdiolog_register, null);
        ((TextView)dialogView.findViewById(R.id.dialog_title)).setText(title);
        ((TextView)dialogView.findViewById(R.id.dialog_msg)).setText(msg);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        ((Button)dialogView.findViewById(R.id.yId)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
        ((Button)dialogView.findViewById(R.id.nId)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }
}


