package icdswb.in;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.HttpHandler;
import icdswb.in.ActivityVolley.RequestHandler;
import icdswb.in.OtherActivity.Consts;

public class NearestCenterActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener{
    private String TAG = PlaningActivity.class.getSimpleName();
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
    String distID ="0";
    String projectID = "0";
    String sectorID = "0";
    String centerID = "0";
    String DbdistID = "0";
    String DbprojectID ="0";
    String DbsectorID ="0";
    String DbcenterID = "0";
    Button searchID,procedId;
    SharedPreferences spp;
    String LOADSPINER="1";
    EditText edt;
    String awcs_code;
    String discode,usertyp,userid;
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
    String userID;
    String planid;
    String flag = "0";
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
    LinearLayout elevendigitVisableNotvisableId;
    User user;
    TextView awctextId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.nearestcenter_activity);
        sp =(Spinner)findViewById(R.id.sp);
        sp1 =(Spinner)findViewById(R.id.sp1);
        sp2 =(Spinner)findViewById(R.id.sp2);
        sp3 =(Spinner)findViewById(R.id.sp3);
        edt = (EditText)findViewById(R.id.edt);
        awctextId = (TextView)findViewById(R.id.awctextId);
        elevendigitVisableNotvisableId = (LinearLayout)findViewById(R.id.elevendigitVisableNotvisableId);
        elevendigitVisableNotvisableId.setVisibility(View.GONE);
        user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        usertyp = String.valueOf(user.getUsertyp());
        if (usertyp.equals("STA")){
            elevendigitVisableNotvisableId.setVisibility(View.GONE);
            awctextId.setVisibility(View.GONE);
        }
        else {
            elevendigitVisableNotvisableId.setVisibility(View.VISIBLE);
            awctextId.setVisibility(View.VISIBLE);
        }
        edt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        TextWatcher m_MobileWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().contains("19")) {
                    edt.setText("19");
                    Selection.setSelection(edt.getText(), edt.getText().length());
                }
            }
        };
        edt.addTextChangedListener(m_MobileWatcher);
        procedId = (Button)findViewById(R.id.procedId);
        countGetData = 0;
        countGetData1 = 0;
        countGetData2 = 0;
        countGetData3 = 0;
        procedId.setOnClickListener(this);
        db = new DatabaseHelper(this);
        helper = new DatabaseHelper(this);
        stateName=new ArrayList<>();
        projectname = new ArrayList<>();
        sectorName = new ArrayList<>();
        centerName = new ArrayList<>();
        searchID = (Button)findViewById(R.id.searchID);
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
        AWCDETAILS();
        initToolBar();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        systenDate = df3.format(c.getTime());
        Log.e("time"," "+systenDate);
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
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
    public void AWCDETAILS() {
        Cursor res = db.getAwcDtls();
        if(res.getCount() == 0) {
            // show message
            Log.e("Error","Nothing found");
         //   Toast.makeText(NearestCenterActivity.this,"NO DATA", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("water :"+ res.getString(1)+"\n");
            buffer.append("cdponame :"+ res.getString(2)+"\n");
            buffer.append("acdpocont :"+ res.getString(3)+"\n\n");
            buffer.append("buildstruc :"+ res.getString(4)+"\n\n");
            buffer.append("electric :"+ res.getString(5)+"\n\n");
            buffer.append("acdponame :"+ res.getString(6)+"\n\n");
            buffer.append("kitchen :"+ res.getString(7)+"\n\n");
            buffer.append("cdpocontact :"+ res.getString(8)+"\n\n");
            buffer.append("workerno :"+ res.getString(9)+"\n\n");
            buffer.append("workernm :"+ res.getString(10)+"\n\n");
            buffer.append("toilet :"+ res.getString(11)+"\n\n");
            buffer.append("awcslat :"+ res.getString(12)+"\n\n");
            buffer.append("supvsr_name :"+ res.getString(13)+"\n\n");
            buffer.append("awcslong :"+ res.getString(14)+"\n\n");
            buffer.append("helperno :"+ res.getString(15)+"\n\n");
            buffer.append("awcsadrs :"+ res.getString(16)+"\n\n");
            buffer.append("foodspace :"+ res.getString(17)+"\n\n");
            buffer.append("helpernm :"+ res.getString(18)+"\n\n");
            buffer.append("buildon :"+ res.getString(19)+"\n\n");
            buffer.append("adqutspacepse :"+ res.getString(20)+"\n\n");
            buffer.append("supvsrno :"+ res.getString(21)+"\n\n");
        }
        Log.e("Data",buffer.toString());

    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Update LAT/LONG");
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
//               if (elevendigitcode>0) {
//                stateName.clear();
//                projectname.clear();
//                sectorName.clear();
//                centerName.clear();
//                spp = getSharedPreferences(LOADSPINER, MODE_PRIVATE);
//                SharedPreferences.Editor edit1 = spp.edit();
//                edit1.putString("LOADSPINER", "1");
//                edit1.commit();
//                getUserData();
//                hideKeyboard(this);
//            }
//            else {
//                Toast.makeText(NearestCenterActivity.this,"Enter Value",Toast.LENGTH_SHORT).show();
//            }

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
                            Toast.makeText(NearestCenterActivity.this,"Enter Start With 19",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(NearestCenterActivity.this,"Enter 11 Digit Code",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(NearestCenterActivity.this,"Enter Value",Toast.LENGTH_SHORT).show();
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
                           // Toast.makeText(getApplicationContext(),districnameDB+" "+DbdistID, Toast.LENGTH_SHORT).show();

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
        sp.setAdapter(new ArrayAdapter<String>(NearestCenterActivity.this,  android.R.layout.simple_spinner_dropdown_item, stateName));
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
        sp1.setAdapter(new ArrayAdapter<String>(NearestCenterActivity.this, android.R.layout.simple_spinner_dropdown_item, projectname));

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
              //  String url = "http://icdswb.in/inspection_service/filter_sector.php?dist="+distID+"&&proj_id="+projectID;
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
                         //   Toast.makeText(getApplicationContext(),sectorrnameDB+ " "+DbsectorID, Toast.LENGTH_SHORT).show();
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
        sp2.setAdapter(new ArrayAdapter<String>(NearestCenterActivity.this, android.R.layout.simple_spinner_dropdown_item, sectorName));
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
        sp3.setAdapter(new ArrayAdapter<String>(NearestCenterActivity.this, android.R.layout.simple_spinner_dropdown_item, centerName));

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
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

                Log.e("WUL",url);
                String response = sh.makeServiceCall(url);
                Log.e(TAG, "Response from url attandence: " + response);
                if (response != null) {

                    try {
                        JSONObject obj = new JSONObject(response);
                        Log.e("code"," "+obj);
                        JSONObject object = obj.getJSONObject("response");
                        final String message = object.getString("message");
                        String error = object.getString("error");
                        Log.e("awcscode"," "+message+ " "+error+" ");
                        if (message.equals("Invalid AWCS Code Given")){
                            finish();
                            startActivity(getIntent());
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

//
                            // Log.e("pickudata"," "+water+ " "+ssector+" "+cdponame+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+cdpocontact+" "+workerno+" "+pproject+" "+awcsname+" "+worker_nm+" "+toilet+ " "+supvsrname+" "+helperno+" "+foodspace+" "+helpernm+" "+buildon+" "+ddistrict+" "+adqutspacepse+" "+supvsrno+" ");

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
                sp.setAdapter(new ArrayAdapter<String>(NearestCenterActivity.this, android.R.layout.simple_spinner_dropdown_item, stateName));
                sp1.setAdapter(new ArrayAdapter<String>(NearestCenterActivity.this, android.R.layout.simple_spinner_dropdown_item, projectname));
                sp2.setAdapter(new ArrayAdapter<String>(NearestCenterActivity.this, android.R.layout.simple_spinner_dropdown_item, sectorName));
                sp3.setAdapter(new ArrayAdapter<String>(NearestCenterActivity.this, android.R.layout.simple_spinner_dropdown_item, centerName));
            }
        }
        UserData ru = new UserData();
        ru.execute();
    }
    private void getproced(){
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
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
                Log.e("proTag"," "+userid+" "+distID+" "+projectID+" "+sectorID+" "+centerID+" ");
                //returing the response
                return requestHandler.sendPostRequest(Config.PROCESS_FILTERCENTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server

                //     progressBar.setVisibility(View.VISIBLE);
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
                        //String awcsname = awcs_dtl.getString("awcs_name");
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
                        Log.e("pickudata"," "+water+ " "+cdponame+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+cdpocontact+" "+workerno+" "+worker_nm+" "+toilet+" "+awcslat+" "+toilet+ " "+supvsrname+" "+awcsslong+" "+helperno+" "+awcs_adrs+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+" "+awcsid+" "+awcscode+" "+awcsname+" ");
                        Log.e("last_in"," "+lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep+" ");
                        Log.e("awcsid",awcsid+" "+awcscode+" "+awcslat+" "+awcsslong);
                        Intent intent = new Intent(getApplicationContext(),CapturelogMap.class);
                        Bundle bundle_edit  =   new Bundle();
                        bundle_edit.putString("awcsid",awcsid);
                        bundle_edit.putString("awcscode",awcscode);
                        bundle_edit.putString("awcslat",awcslat);
                        bundle_edit.putString("awcsslong",awcsslong);
                        bundle_edit.putString("distID",distID);
                        bundle_edit.putString("userid",userid);
                        bundle_edit.putString("projectID",projectID);
                        bundle_edit.putString("sectorID",sectorID);
                        intent.putExtras(bundle_edit);
                        startActivity(intent);



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

}

