package icdswb.in;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityDatabase.InspactioninsertNetworkercheker;
import icdswb.in.ActivityDatabase.InspectionpersonpresentNetworkcheker;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAGID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPACTIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERID;


public class ProcedActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button nextId,nextIdd;
    EditText cdponameID,numberID,acdponameID,acdponumberIDD,supervisiorID,supervisiorNumberID,workernameID,workernumberID,helpernameID,helpernumberID;
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
    String awcsuserid;
    String awcsflag;
    String currentdate ="NA";
    TextView dateId,timeID,disID,projectID,sectorID,awcstvId;
    String curTime,curDate;
    String flag;
    DatabaseHelper helper;
    String dbid,dist,project,sector,center;
    String inspactionid,awcscodeid;
    //   String awcsid,awcsname,awcscode;
    String userID;
    String Cdponame = "NA";
    String Cdponumber = "NA";
    String acdpoName = "NA";
    String acdpoContract = "NA";
    String supervisorName = "NA";
    String supervisorNo = "NA";
    String workerName = "NA";
    String workerNo = "NA";
    String helperName = "NA";
    String helperNo = "NA";
    String dbdistid ="NA";
    String processid = "NA";
    String dbprojectid ="NA";
    String dbsectorid ="NA";
    String districnamedb ="NA";
    String projectnamedb ="NA";
    String sectorrnamedb ="NA";
    String centernamedb = "NA";
    String dbcenterid;
    //  String lstinspctnbuldrep,lstinspctntoiletrep,lstinspctnkitchenrep,lstinspctnpserep,lstinspctnelectricrep,lstinspctndrnkwaterrep,lstinspctnfoodrep;
    RadioButton YredioID,NredioID,YacdpoID,NacdpoID,supervisorYID,SupervisorNID,workerradioYID,workerradioNID,helperradioYID,helperradioNID,awcsradioYID,awcsradioNID;
    RadioGroup CdporadioGroup,radioGroupacdpo,SupervisiorradioGroup,WorkerradioGroup,HelperradioGroup,awcsradioID;
    String yncdpo = "NA";
    String  ynacdpio ="NA";
    String  YnSupervisor ="NA";
    String  Ynworker ="NA";
    String  Yhelper = "NA";
    String Ynawcs ="NA";
    String planid = "NA";
    String insid;
    String allinspactionid;
    ImageButton cdponamespack,numbercdpospk,acdponamespack,acdponumberspak,supervisiorspk,supervisiorNumberspk,workernamespak,workernumberspak,helpernamespak,helpernumberspak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int REQ_CODE_SPEECH_INPUTCDPONUMBER = 101;
    private final int REQ_CODE_SPEECH_INPUTACDPONAME = 102;
    private final int REQ_CODE_SPEECH_INPUTACDPONUMBER = 103;
    private final int REQ_CODE_SPEECH_INPUTSUPERVISORNAME = 104;
    private final int REQ_CODE_SPEECH_INPUTSUPERVISORNUMBER = 105;
    private final int REQ_CODE_SPEECH_INPUTWORKERNAME = 106;
    private final int REQ_CODE_SPEECH_INPUTWORKERNUMBER = 107;
    private final int REQ_CODE_SPEECH_INPUTHELPERNAME = 108;
    private final int REQ_CODE_SPEECH_INPUTHELPERNUMBER = 109;
    private final int REQ_CODE_SPEECH_INPUTCOMMAND = 110;
    ImageView cdpoId,acdpoId,supervisorId,workerId,helperId,commandspak;
    LinearLayout lv4,lv5,lv6;
    EditText cmdinformation;
    String cmt;
    String userid;
    private DatabaseHelper db;
    public static final int UPDATECENTREPERSNDTL_SYNCED_WITH_SERVER = 1;
    public static final int UPDATECENTREPERSNDTL_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiverupdatecentrepersndtl;
    public static final String DATA_SAVED_BROADCAST_UPDATECENTREPERSNDTL = "icdswb.in.pdatecentrepersndtlsaved";
    private InspactioninsertNetworkercheker inspactioninsertNetworkercheker;
    public static final int INSPACTIONPERSONPESENT_SYNCED_WITH_SERVER = 1;
    public static final int INSPACTIONPERSONPESENT_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiverinspactionpersonpesent;
    public static final String DATA_SAVED_BROADCAST_INSPACTIONPERSONPESENT = "icdswb.in.inspactionpersonpesentlsaved";
    private InspectionpersonpresentNetworkcheker inspectionpersonpresentNetworkcheker;
    String flaggrecord;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_proced);
        Intent intent = getIntent();
        water = intent.getStringExtra("water");
        cdponame = intent.getStringExtra("cdponame");
        acdpocont = intent.getStringExtra("acdpocont");
        buildstruc = intent.getStringExtra("buildstruc");
        electric = intent.getStringExtra("electric");
        acdponame = intent.getStringExtra("acdponame");
        kitchen = intent.getStringExtra("kitchen");
        cdpocontact = intent.getStringExtra("cdpocontact");
        workerno = intent.getStringExtra("workerno");
        worker_nm = intent.getStringExtra("worker_nm");
        toilet = intent.getStringExtra("toilet");
        awcslat = intent.getStringExtra("awcslat");
        supvsrname = intent.getStringExtra("supvsrname");
        awcsslong = intent.getStringExtra("awcsslong");
        helperno = intent.getStringExtra("helperno");
        awcs_adrs = intent.getStringExtra("awcs_adrs");
        foodspace = intent.getStringExtra("foodspace");
        helpernm = intent.getStringExtra("helpernm");
        buildon = intent.getStringExtra("buildon");
        adqutspacepse = intent.getStringExtra("adqutspacepse");
        supvsrno = intent.getStringExtra("supvsrno");
        awcsid = intent.getStringExtra("awcsid");
        awcscode = intent.getStringExtra("awcscode");
        awcsname = intent.getStringExtra("awcsname");
        dbdistid = intent.getStringExtra("DbdistID");
        dbprojectid = intent.getStringExtra("DbprojectID");
        dbsectorid = intent.getStringExtra("DbsectorID");
        dbcenterid = intent.getStringExtra("DbcenterID");
        flaggrecord = intent.getStringExtra("flaggrecord");
        Log.e("flaggrecord"," "+"ProcedActivity"+flaggrecord);
        Log.e("LOGDIDD",dbdistid+" "+dbprojectid+" "+" "+dbsectorid+" "+dbcenterid);
        districnamedb = intent.getStringExtra("districnameDB");
        projectnamedb = intent.getStringExtra("projectnameDB");
        sectorrnamedb = intent.getStringExtra("sectorrnameDB");
        centernamedb = intent.getStringExtra("centernameDB");
        currentdate = intent.getStringExtra("systenDate");
        allinspactionid = intent.getStringExtra("allinspactionid");
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        String datee = currentdate.replaceAll("[-+.^:,]", "");
        insid = dbcenterid+userID+datee;
        Log.e("INSS",insid +" "+" "+allinspactionid);
        helper = new DatabaseHelper(getApplicationContext());
        lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
        lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
        lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
        lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
        lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
        lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
        lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        Log.e("LASTIN",lstinspctnbuldrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep);
        Log.e("SELECTPROJECT", districnamedb+" "+projectnamedb+" "+sectorrnamedb+" "+centernamedb+" "+currentdate);
        Log.e("ALLPROCESS",water+" "+cdponame+" "+acdpocont+" "+buildstruc+ " "+electric+" "+acdponame+" "+kitchen+" "+cdpocontact+" "+workerno+" "+worker_nm+" "+toilet+" "+awcslat+" "+supvsrname+" "+awcsslong+" "+helperno+" "+awcs_adrs+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+awcsid+" "+""+awcscode+" "+awcsname+" ");
        Log.e("ACDPO",acdponame+" "+acdpocont);
        planid = intent.getStringExtra("planid");
        Log.e("planID",planid);
        lv4 =(LinearLayout)findViewById(R.id.lv4);
        lv5 =(LinearLayout)findViewById(R.id.lv5);
        lv6 =(LinearLayout)findViewById(R.id.lv6);
        lv4.setVisibility(View.GONE);
        lv5.setVisibility(View.GONE);
        lv6.setVisibility(View.GONE);
        db = new DatabaseHelper(this);
        nextId = (Button)findViewById(R.id.nextId);
        nextId.setOnClickListener(this);
        cmdinformation =(EditText)findViewById(R.id.cmdinformation);
        cdponameID = (EditText)findViewById(R.id.cdponameID);
        numberID = (EditText)findViewById(R.id.numberID);
        acdponameID = (EditText)findViewById(R.id.acdponameID);
        acdponumberIDD = (EditText)findViewById(R.id.acdponumberIDD);
        dateId = (TextView)findViewById(R.id.dateId);
        timeID = (TextView)findViewById(R.id.timeID);
        disID = (TextView)findViewById(R.id.disID);
        projectID = (TextView)findViewById(R.id.projectID);
        sectorID = (TextView)findViewById(R.id.sectorID);
        supervisiorID =(EditText) findViewById(R.id.supervisiorID);
        supervisiorNumberID = (EditText)findViewById(R.id.supervisiorNumberID);
        workernameID = (EditText)findViewById(R.id.workernameID);
        workernumberID = (EditText)findViewById(R.id.workernumberID);
        helpernameID = (EditText)findViewById(R.id.helpernameID);
        helpernumberID = (EditText)findViewById(R.id.helpernumberID);
        CdporadioGroup = (RadioGroup) findViewById(R.id.CdporadioGroup);
        // CdporadioGroup.clearCheck();
        radioGroupacdpo = (RadioGroup)findViewById(R.id.radioGroupacdpo);
        SupervisiorradioGroup = (RadioGroup) findViewById(R.id.SupervisiorradioGroup);
        WorkerradioGroup = (RadioGroup)findViewById(R.id.WorkerradioGroup);
        HelperradioGroup = (RadioGroup)findViewById(R.id.HelperradioGroup);
        awcsradioID = (RadioGroup)findViewById(R.id.awcsradioID);
        awcstvId = (TextView)findViewById(R.id.awcstvId);
        YredioID = (RadioButton)findViewById(R.id.YredioID);
        NredioID = (RadioButton)findViewById(R.id.NredioID);
        YacdpoID = (RadioButton)findViewById(R.id.YacdpoID);
        NacdpoID = (RadioButton)findViewById(R.id.NacdpoID);
        supervisorYID = (RadioButton)findViewById(R.id.supervisorYID);
        SupervisorNID = (RadioButton)findViewById(R.id.SupervisorNID);
        workerradioYID = (RadioButton)findViewById(R.id.workerradioYID);
        workerradioNID = (RadioButton)findViewById(R.id.workerradioNID);
        helperradioYID = (RadioButton)findViewById(R.id.helperradioYID);
        helperradioNID = (RadioButton)findViewById(R.id.helperradioNID);
        awcsradioYID = (RadioButton)findViewById(R.id.awcsradioYID);
        awcsradioNID = (RadioButton)findViewById(R.id.awcsradioNID);
        nextIdd = (Button)findViewById(R.id.nextIdd);
        nextIdd.setOnClickListener(this);
        nextId.setVisibility(View.VISIBLE);
        nextIdd.setVisibility(View.GONE);
        cdpoId = (ImageView)findViewById(R.id.cdpoId);
        cdpoId.setOnClickListener(this);
        acdpoId = (ImageView)findViewById(R.id.acdpoId);
        acdpoId.setOnClickListener(this);
        supervisorId = (ImageView)findViewById(R.id.supervisorId);
        supervisorId.setOnClickListener(this);
        workerId = (ImageView)findViewById(R.id.workerId);
        workerId.setOnClickListener(this);
        helperId = (ImageView)findViewById(R.id.helperId);
        helperId.setOnClickListener(this);
        commandspak = (ImageView)findViewById(R.id.commandspak);
        commandspak.setOnClickListener(this);
        CdporadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.YredioID) {
                    yncdpo = "Y";
                    Log.e("CDPO",yncdpo);
                } else if(checkedId == R.id.NredioID) {
                    yncdpo = "N";
                    Log.e("CDPO",yncdpo);
                } else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        radioGroupacdpo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.YacdpoID) {
                    ynacdpio = "Y";
                    Log.e("ACDPO",ynacdpio);
                } else if(checkedId == R.id.NacdpoID) {
                    ynacdpio = "N";
                    Log.e("ACDPO",ynacdpio);
                } else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        SupervisiorradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.supervisorYID){
                    YnSupervisor = "Y";
                    Log.e("SUPER",YnSupervisor);
                }
                else if (checkedId== R.id.SupervisorNID){
                    YnSupervisor = "N";
                    Log.e("SUPER",YnSupervisor);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        WorkerradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.workerradioYID){
                    Ynworker = "Y";
                    Log.e("WORKER",Ynworker);
                }
                else if (checkedId== R.id.workerradioNID){
                    Ynworker = "N";
                    Log.e("WORKER",Ynworker);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        HelperradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.helperradioYID){
                    Yhelper = "Y";
                    Log.e("HELPER",Yhelper);
                }
                else if (checkedId== R.id.helperradioNID){
                    Yhelper = "N";
                    Log.e("HELPER",Yhelper);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        awcsradioID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.awcsradioYID){
                    Ynawcs = "Y";
                    Log.e("AWCS",Ynawcs);
                    nextId.setVisibility(View.VISIBLE);
                    nextIdd.setVisibility(View.GONE);
                    lv4.setVisibility(View.GONE);
                    lv5.setVisibility(View.GONE);
                    lv6.setVisibility(View.GONE);
                }
                else if (checkedId== R.id.awcsradioNID){
                    Ynawcs = "N";
                    Log.e("AWCS",Ynawcs);
                    nextId.setVisibility(View.GONE);
                    nextIdd.setVisibility(View.VISIBLE);
                    lv4.setVisibility(View.VISIBLE);
                    lv5.setVisibility(View.VISIBLE);
                    lv6.setVisibility(View.VISIBLE);
                }

                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        awcstvId.setText(awcsname);
        disID.setText(districnamedb);
        projectID.setText(projectnamedb);
        sectorID.setText(sectorrnamedb);
        cdponameID.setText(cdponame);
        numberID.setText(cdpocontact);
        acdponameID.setText(acdponame);
        acdponumberIDD.setText(acdpocont);
        supervisiorID.setText(supvsrname);
        supervisiorNumberID.setText(supvsrno);
        workernameID.setText(worker_nm);
        workernumberID.setText(workerno);
        helpernameID.setText(helpernm);
        helpernumberID.setText(helperno);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(c.getTime());
        dateId.setText(curDate);
        Calendar cc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(cc.getTime());
        timeID.setText(curTime);
        nextId.setOnClickListener(this);
        initToolBar();
        //////////////////////////pickuprecyclerview//////////////////////////////
        nextId.setVisibility(View.VISIBLE);
        nextIdd.setVisibility(View.GONE);
        cdponamespack = (ImageButton)findViewById(R.id.cdponamespack);
        cdponamespack.setOnClickListener(this);
        numbercdpospk = (ImageButton)findViewById(R.id.numbercdpospk);
        numbercdpospk.setOnClickListener(this);
        acdponamespack = (ImageButton)findViewById(R.id.acdponamespack);
        acdponamespack.setOnClickListener(this);
        acdponumberspak = (ImageButton)findViewById(R.id.acdponumberspak);
        acdponumberspak.setOnClickListener(this);
        supervisiorspk = (ImageButton)findViewById(R.id.supervisiorspk);
        supervisiorspk.setOnClickListener(this);
        supervisiorNumberspk = (ImageButton)findViewById(R.id.supervisiorNumberspk);
        supervisiorNumberspk.setOnClickListener(this);
        workernamespak = (ImageButton)findViewById(R.id.workernamespak);
        workernamespak.setOnClickListener(this);
        workernumberspak = (ImageButton)findViewById(R.id.workernumberspak);
        workernumberspak.setOnClickListener(this);
        helpernamespak = (ImageButton)findViewById(R.id.helpernamespak);
        helpernamespak.setOnClickListener(this);
        helpernumberspak = (ImageButton)findViewById(R.id.helpernumberspak);
        helpernumberspak.setOnClickListener(this);
        YredioID.setChecked(true);
        YacdpoID.setChecked(true);
        supervisorYID.setChecked(true);
        Cursor us = helper.getLoginData();
        if (us.moveToFirst()) {
            do {
                userid = us.getString(us.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                Log.e("userid",userid);
            } while (us.moveToNext());
        }
        String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid+ " and " +TABLE_INSPACTIONID+ "=" +allinspactionid;
        helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                dbid = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROCESSID));
                Log.e("dbid",dbid);
            }
            while (cursor.moveToNext());
        }
        helper = new DatabaseHelper(this);
        Cursor cursorr = helper.getReadableDatabase().
                rawQuery("select * from awcsdtl where id = ?", new String[]{dbid});
        if (cursorr.moveToFirst()) {
            do {
                water = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_WATER));
                cdponame = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_CDPONAME));
                acdpocont = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_ACDPOCONT));
                buildstruc = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_BUILDSTRUC));
                electric = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_ELECTRIC));
                acdponame = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_ACDPONAME));
                kitchen = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_KITCHEN));
                cdpocontact = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_CDPOCONTACT));
                workerno = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_WORKERNO));
                worker_nm = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_WORKERNM));
                toilet = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_TOILET));
                awcslat = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_AWCSLAT));
                supvsrname = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_SUPVSRNAME));
                awcsslong = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_AWCSLONG));
                helperno = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_HELPERNO));
                awcs_adrs = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_AWCSADRS));
                foodspace = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_FOODSPACE));
                helpernm = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_HELPERNM));
                buildon = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_BUILDON));
                adqutspacepse = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPACEPSE));
                supvsrno = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_SUPVSRNO));
                awcsid = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_AWCSID));
                awcscode = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_AWCSCODE));
                awcsname = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_AWCSNAME));
                planid = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_PLANID));
                lstinspctnbuldrep = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_BULD_REP));
                lstinspctntoiletrep = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_TOILET_REP));
                lstinspctnkitchenrep = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_KITCHEN_REP));
                lstinspctnpserep = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_PSE_REP));
                lstinspctnelectricrep = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_ELECTRIC_REP));
                lstinspctndrnkwaterrep = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_DRNKWATER_REP));
                lstinspctnfoodrep = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_FOOD_REP));
                awcsuserid = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_AWCSUSERID));
                awcsflag = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_AWCSFLAG));
                allinspactionid = cursorr.getString(cursorr.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONID));
            } while (cursorr.moveToNext());
            Log.e("awcsid",awcsid+" "+awcscode+" "+awcsname+" "+awcsuserid+" "+awcsflag+" "+allinspactionid);
            Log.e("ValueDb", water + " " + cdponame + " " + acdpocont + " " + buildstruc + " " + electric + acdponame + " " + kitchen + " " + cdpocontact + workerno + " " + worker_nm + " " + toilet + " " + awcslat + " " + supvsrname + " " + awcsslong + " " + helperno + " " + awcs_adrs + " " + foodspace + " " + helpernm + " " + buildon + " " + adqutspacepse + " " + supvsrno + " ");
            Log.e("AWCSLATLONG",awcslat+" "+awcsslong);
            Log.e("planid",planid);
            cdponameID.setText(cdponame);
            numberID.setText(cdpocontact);
            acdponameID.setText(acdponame);
            acdponumberIDD.setText(acdpocont);
            supervisiorID.setText(supvsrname);
            supervisiorNumberID.setText(supvsrno);
            workernameID.setText(worker_nm);
            workernumberID.setText(workerno);
            helpernameID.setText(helpernm);
            helpernumberID.setText(helperno);
            awcstvId.setText(awcsname+" "+ "("+awcscode+")");

        }
        Cursor cr = helper.getReadableDatabase().
                rawQuery("select * from awcsprocess where idprocess = ?", new String[]{dbid});
        if (cr.moveToFirst()) {
            do {
                processid = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_PROCESSID));
                dbdistid = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_DBDISTID));
                dbprojectid = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_DBPROJECTID));
                dbsectorid = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_DBSECTORID));
                dbcenterid = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_DBCENTERID));
                projectnamedb = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_PROJECT));
                districnamedb = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_DISTRIC));
                sectorrnamedb = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_SECTOR));
                centernamedb = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_CENTER));
                curDate = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_CURRENDATE));
                flag = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_FLAG));
                awcslat = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_AWCSLATLOCATION));
                awcsslong = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_AWCSLONGLOCATION));
                inspactionid = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_INSPACTIONID));
                awcscodeid = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_AWCSCODEID));
                curTime = cr.getString(cr.getColumnIndex(DatabaseHelper.TABLE_AWCSTIME));
                dateId.setText(curDate);
                timeID.setText(curTime);
                Log.e("AWCSLATLONG",awcslat+" "+awcsslong+" "+curDate+" "+curTime);
            }while (cr.moveToNext());

        }
        Log.e("ALLID",processid+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+currentdate+" "+" "+flag+awcslat+" "+awcsslong);

        broadcastReceiverupdatecentrepersndtl = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiverupdatecentrepersndtl, new IntentFilter(DATA_SAVED_BROADCAST_UPDATECENTREPERSNDTL));
        inspactioninsertNetworkercheker = new InspactioninsertNetworkercheker();
        registerReceiver(inspactioninsertNetworkercheker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        broadcastReceiverinspactionpersonpesent = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            }
        };
        registerReceiver(broadcastReceiverinspactionpersonpesent, new IntentFilter(DATA_SAVED_BROADCAST_UPDATECENTREPERSNDTL));
        inspectionpersonpresentNetworkcheker = new InspectionpersonpresentNetworkcheker();
        registerReceiver(inspectionpersonpresentNetworkcheker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
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
        toolbar.setTitle("ICDS");
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nextId:
                if (YredioID.isChecked() || NredioID.isChecked()){
                    if (YacdpoID.isChecked() || NacdpoID.isChecked()){
                        if (supervisorYID.isChecked() || SupervisorNID.isChecked()){
                            if (workerradioYID.isChecked() || workerradioNID.isChecked()){
                                if (helperradioYID.isChecked() || helperradioNID.isChecked()){
                                    if (awcsradioYID.isChecked() || awcsradioNID.isChecked()){
                                        update_centre_persn_dtl();
                                    }
                                    else {
                                        Toast.makeText(ProcedActivity.this, "SELECT AWC OPEN", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(ProcedActivity.this, "SELECT WORKER DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(ProcedActivity.this, "SELECT WORKER DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(ProcedActivity.this, "SELECT SUPERVISOR DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ProcedActivity.this, "SELECT ACDPO DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ProcedActivity.this, "SELECT CDPO DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nextIdd:
                if (YredioID.isChecked() || NredioID.isChecked()){
                    if (YacdpoID.isChecked() || NacdpoID.isChecked()){
                        if (supervisorYID.isChecked() || SupervisorNID.isChecked()){
                            if (workerradioYID.isChecked() || workerradioNID.isChecked()){
                                if (helperradioYID.isChecked() || helperradioNID.isChecked()){
                                    if (awcsradioYID.isChecked() || awcsradioNID.isChecked()){
                                        update_centre_persn_dtl();
                                    }
                                    else {
                                        Toast.makeText(ProcedActivity.this, "SELECT AWC OPEN", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(ProcedActivity.this, "SELECT WORKER DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(ProcedActivity.this, "SELECT WORKER DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(ProcedActivity.this, "SELECT SUPERVISOR DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ProcedActivity.this, "SELECT ACDPO DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ProcedActivity.this, "SELECT CDPO DETAILS PRESENT ON DAY", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cdponamespack:
                promptSpeechcdpoName();
                break;
            case R.id.numbercdpospk:
                promptSpeechcdponumber();
                break;
            case R.id.acdponamespack:
                promptSpeechAcdpoName();
                break;
            case R.id.acdponumberspak:
                promptSpeechAcdpoNumber();
                break;
            case R.id.supervisiorspk:
                promptSpeechsupervisorName();
                break;
            case R.id.supervisiorNumberspk:
                promptSpeechsupervisorNumer();
                break;
            case R.id.workernamespak:
                promptSpeechWorkerName();
                break;
            case R.id.workernumberspak:
                promptSpeechWorkerNumber();
                break;
            case R.id.helpernamespak:
                promptSpeechHelperName();
                break;
            case R.id.helpernumberspak:
                promptSpeechHelperNumber();
                break;
            case R.id.cdpoId:
                String phone = numberID.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
                break;
            case R.id.acdpoId:
                String phone1 = acdponumberIDD.getText().toString();
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone1, null));
                startActivity(intent1);
                break;
            case R.id.supervisorId:
                String phone2 = supervisiorNumberID.getText().toString();
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone2, null));
                startActivity(intent2);
                break;
            case R.id.workerId:
                String phone3 = workernumberID.getText().toString();
                Intent intent3 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone3, null));
                startActivity(intent3);
                break;
            case R.id.helperId:
                String phone4 = helpernumberID.getText().toString();
                Intent intent4 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone4, null));
                startActivity(intent4);
                break;
            case R.id.commandspak:
                promptSpeechCommand();
                break;
            default:
        }
    }

    private void promptSpeechcdpoName() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void promptSpeechcdponumber() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_number));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTCDPONUMBER);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void promptSpeechAcdpoName() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTACDPONAME);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void promptSpeechAcdpoNumber() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_number));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTACDPONUMBER);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void promptSpeechsupervisorName(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTSUPERVISORNAME);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void promptSpeechsupervisorNumer(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_number));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTSUPERVISORNUMBER);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void promptSpeechWorkerName(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTWORKERNAME);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void promptSpeechWorkerNumber(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_number));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTWORKERNUMBER);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void promptSpeechHelperName(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTHELPERNAME);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void promptSpeechHelperNumber(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_number));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTHELPERNUMBER);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void promptSpeechCommand(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_number));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUTCOMMAND);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    cdponameID.setText(result.get(0));
                }
                break;
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTCDPONUMBER:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String word = result.get(0);
                    isNumbercdpo(word);
                }
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTACDPONAME:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    acdponameID.setText(result.get(0));
                }
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTACDPONUMBER:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String word = result.get(0);
                    isNumberacdpo(word);
                }
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTSUPERVISORNAME:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    supervisiorID.setText(result.get(0));
                }
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTSUPERVISORNUMBER:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String word = result.get(0);
                    isNumberasupervisor(word);
                }
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTWORKERNAME:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    workernameID.setText(result.get(0));
                }
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTWORKERNUMBER:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String word = result.get(0);
                    isNumberWorker(word);
                }
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTHELPERNAME:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    helpernameID.setText(result.get(0));
                }
            }
        }
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTHELPERNUMBER:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String word = result.get(0);
                    isNumberHelper(word);
                }
            }
        }
        //////////////////////////////
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUTCOMMAND:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String word = result.get(0);
                    cmdinformation.setText(word);
                }
            }
        }
    }
    private boolean isNumbercdpo(String word)
    {
        boolean isNumber = false;
        try
        {
            int w =  Integer.parseInt(word);
            String s = String.valueOf(w);
            numberID.setText(s);
            isNumber = true;
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }
    private boolean isNumberacdpo(String word)
    {
        boolean isNumber = false;
        try
        {
            int w =  Integer.parseInt(word);
            String s = String.valueOf(w);
            acdponumberIDD.setText(s);
            isNumber = true;
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }
    private boolean isNumberasupervisor(String word)
    {
        boolean isNumber = false;
        try
        {
            int w =  Integer.parseInt(word);
            String s = String.valueOf(w);
            supervisiorNumberID.setText(s);
            isNumber = true;
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }
    private boolean isNumberWorker(String word)
    {
        boolean isNumber = false;
        try
        {
            int w =  Integer.parseInt(word);
            String s = String.valueOf(w);
            workernumberID.setText(s);
            isNumber = true;
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }
    private boolean isNumberHelper(String word)
    {
        boolean isNumber = false;
        try
        {
            int w =  Integer.parseInt(word);
            String s = String.valueOf(w);
            helpernumberID.setText(s);
            isNumber = true;
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }
//    private void update_centre_persn_dtl(){
//        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
//        userID = String.valueOf(user.getUserID());
//        Cdponame = cdponameID.getText().toString().trim();
//        Cdponumber = numberID.getText().toString().trim();
//        acdpoName = acdponameID.getText().toString().trim();
//        acdpoContract = acdponumberIDD.getText().toString().trim();
//        supervisorName = supervisiorID.getText().toString().trim();
//        supervisorNo = supervisiorNumberID.getText().toString().trim();
//        workerName = workernameID.getText().toString().trim();
//        workerNo = workernumberID.getText().toString().trim();
//        helperName = helpernameID.getText().toString().trim();
//        helperNo = helpernumberID.getText().toString().trim();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.UPDATECENRER,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //   progressBar.setVisibility(View.GONE);
//                        Log.e("responseup"," "+response);
//                        try {
//                            JSONArray array =new JSONArray(response);
//                            Log.e("objup"," "+array);
//                            //  JSONArray array1 = array.getJSONArray(0);
//                            for (int i=0; i<array.length(); i++) {
//                                JSONObject object = array.getJSONObject(i);
//                                String message = object.getString("message");
//                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                                inspection_person_present();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), "NO Network Device", Toast.LENGTH_SHORT).show();
//                        Log.e("Update",error.getMessage());
//
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("dist_id", dbdistid);
//                params.put("project_id", dbprojectid);
//                params.put("cdpo_nm", Cdponame);
//                params.put("cdpo_cont",Cdponumber);
//                params.put("acdpo_nm", acdpoName);
//                params.put("acdpo_cont", acdpoContract);
//                params.put("sector_id", dbsectorid);
//                params.put("supervisor_name", supervisorName);
//                params.put("supvsr_no", supervisorNo);
//                params.put("awcs_id",awcsid);
//                params.put("angan_wrkr",workerName);
//                params.put("worker_no", workerNo);
//                params.put("angan_hlpr", helperName);
//                params.put("hlpr_no", helperNo);
//                Log.e("update_centre"," "+dbdistid+" "+dbprojectid+" "+Cdponame+" " +Cdponumber+" "+acdpoName+" "+acdpoContract+" "+dbsectorid+" "+supervisorName+" " +supervisorNo+" "+awcsid+" "+workerName+" "+workerNo+" "+helperName+" "+helperNo+" ");
//                return params;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
//        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
//        stringRequest.setShouldCache(false);
//        volleySingleton.addToRequestQueue(stringRequest);
//    }


    private void update_centre_persn_dtl(){
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        Cdponame = cdponameID.getText().toString().trim();
        Cdponumber = numberID.getText().toString().trim();
        acdpoName = acdponameID.getText().toString().trim();
        acdpoContract = acdponumberIDD.getText().toString().trim();
        supervisorName = supervisiorID.getText().toString().trim();
        supervisorNo = supervisiorNumberID.getText().toString().trim();
        workerName = workernameID.getText().toString().trim();
        workerNo = workernumberID.getText().toString().trim();
        helperName = helpernameID.getText().toString().trim();
        helperNo = helpernumberID.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.UPDATECENRER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("responseup"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("objup"," "+array);
                            //  JSONArray array1 = array.getJSONArray(0);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                String flag ="1";
                                db.awcsdtlupdateData(dbid,water,Cdponame,acdpoContract,buildstruc,electric,acdpoName,kitchen,
                                        Cdponumber,workerNo,workerName,toilet,awcslat,
                                        supervisorName,awcsslong,helperNo,awcs_adrs,
                                        foodspace,helperName,buildon,adqutspacepse,
                                        supervisorNo,awcsid,awcscode,awcsname,
                                        planid,lstinspctnbuldrep,
                                        lstinspctntoiletrep,lstinspctnkitchenrep,lstinspctnpserep,lstinspctnelectricrep,lstinspctndrnkwaterrep,lstinspctnfoodrep,userID,flag,insid);
                                db.awcsprocessUpdate(processid,dbdistid,dbprojectid,dbsectorid,
                                        dbcenterid,projectnamedb,districnamedb,sectorrnamedb,centernamedb,curDate,userID,awcslat,awcsslong,
                                        insid);

//                                db.awcsprocessUpdate(insid,dbdistid,dbprojectid,dbsectorid,
//                                        dbcenterid,projectnamedb,districnamedb,sectorrnamedb,centernamedb,curDate,userID,awcslat,awcsslong,
//                                        processid,awcscode,timeID.getText().toString(),flaggrecord);
                                Log.e("PLAN"," "+processid+" "+flaggrecord);
                                Log.e("flaggrecord"," "+"ProcedActivity"+" "+processid+" "+flaggrecord+" "+insid);
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE awcsprocess SET flag='1' WHERE idprocess=" +dbid);
                                SQLiteDatabase awcs = helper.getReadableDatabase();
                                awcs.execSQL("UPDATE awcsdtl SET awcsflag='1' WHERE id=" +dbid);
                                SQLiteDatabase database = helper.getReadableDatabase();
                                database.execSQL( "UPDATE "+TABLE_ALLINSPECTIONFLAG +" SET " + TABLE_ALLINSPACATIONID+ " = '"+insid+"' WHERE "+TABLE_ALLINSPECTIONFLAGID+ " = "+dbid);
                                //  db.AllinspactionUpdate(dbid,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,insid);
                                if (flag.equals("0")){
                                    db.InspactionInsert(yncdpo,ynacdpio,YnSupervisor,Ynworker,Yhelper,Ynawcs);
                                }
                                else {
                                    db.InspactionUpdate(dbid,yncdpo,ynacdpio,YnSupervisor,Ynworker,Yhelper,Ynawcs);
                                }
                                savedataupdatecenrer(dbdistid,dbprojectid,Cdponame,Cdponumber,acdpoName,acdpoContract,dbsectorid,supervisorName,supervisorNo,awcsid,workerName,workerNo,helperName,helperNo,UPDATECENTREPERSNDTL_SYNCED_WITH_SERVER);
                                inspection_person_present();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Toast.makeText(getApplicationContext(), "NO Network Device", Toast.LENGTH_SHORT).show();
                        Log.e("Update",error.getMessage());
                        String flag ="1";
                        db.awcsdtlupdateData(dbid,water,Cdponame,acdpoContract,buildstruc,electric,
                                acdpoName,kitchen,Cdponumber,workerNo,
                                workerName,toilet,awcslat,supervisorName,
                                awcsslong,helperNo,awcs_adrs,foodspace,
                                helperName,buildon,adqutspacepse,supervisorNo,
                                awcsid,awcscode,awcsname,planid,lstinspctnbuldrep,
                                lstinspctntoiletrep,lstinspctnkitchenrep,lstinspctnpserep,lstinspctnelectricrep,lstinspctndrnkwaterrep,lstinspctnfoodrep,userID,flag,insid);
                        /////gg/
//
                        db.awcsprocessUpdate(processid,dbdistid,dbprojectid,dbsectorid,
                                dbcenterid,projectnamedb,districnamedb,sectorrnamedb,centernamedb,curDate,userID,awcslat,awcsslong,
                                insid);

//                        db.awcsprocessUpdate(insid,dbdistid,dbprojectid,dbsectorid,
//                                dbcenterid,projectnamedb,districnamedb,sectorrnamedb,centernamedb,curDate,userID,awcslat,awcsslong,
//                                processid,awcscode,timeID.getText().toString(),flaggrecord);
                        Log.e("PLAN"," "+processid+" "+flaggrecord);
                        Log.e("PLAN"," "+processid+" "+flaggrecord);
                        Log.e("flaggrecord"," "+"ProcedActivity"+" "+processid+" "+flaggrecord+" "+insid);
                        helper = new DatabaseHelper(getApplicationContext());
                        SQLiteDatabase dbb = helper.getReadableDatabase();
                        dbb.execSQL("UPDATE awcsprocess SET flag='1' WHERE idprocess=" +dbid);
                        SQLiteDatabase awcs = helper.getReadableDatabase();
                        awcs.execSQL("UPDATE awcsdtl SET awcsflag='1' WHERE id=" +dbid);
                        SQLiteDatabase database = helper.getReadableDatabase();
                        database.execSQL( "UPDATE "+TABLE_ALLINSPECTIONFLAG +" SET " + TABLE_ALLINSPACATIONID+ " = '"+insid+"' WHERE "+TABLE_ALLINSPECTIONFLAGID+ " = "+dbid);
                        if (flag.equals("0")){
                            db.InspactionInsert(yncdpo,ynacdpio,YnSupervisor,Ynworker,Yhelper,Ynawcs);
                        }
                        else {
                            db.InspactionUpdate(dbid,yncdpo,ynacdpio,YnSupervisor,Ynworker,Yhelper,Ynawcs);
                        }
                        savedataupdatecenrer(dbdistid,dbprojectid,Cdponame,Cdponumber,acdpoName,acdpoContract,dbsectorid,supervisorName,supervisorNo,awcsid,workerName,workerNo,helperName,helperNo,UPDATECENTREPERSNDTL_NOT_SYNCED_WITH_SERVER);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dist_id", dbdistid);
                params.put("project_id", dbprojectid);
                params.put("cdpo_nm", Cdponame);
                params.put("cdpo_cont",Cdponumber);
                params.put("acdpo_nm", acdpoName);
                params.put("acdpo_cont", acdpoContract);
                params.put("sector_id", dbsectorid);
                params.put("supervisor_name", supervisorName);
                params.put("supvsr_no", supervisorNo);
                params.put("awcs_id",awcsid);
                params.put("angan_wrkr",workerName);
                params.put("worker_no", workerNo);
                params.put("angan_hlpr", helperName);
                params.put("hlpr_no", helperNo);
                params.put("hlpr_no", "");
                Log.e("update_centre",
                        "dist_id"+" " +dbdistid+" "+
                                "project_id"+" "+dbprojectid+" "+
                                "cdpo_nm"+" "+Cdponame+" "+
                                "cdpo_cont"+" "+Cdponumber+" "+
                                "acdpo_nm"+" "+acdpoName+" "+
                                "acdpo_cont"+" "+acdpoContract+" "+
                                "sector_id"+" "+dbsectorid+" "+
                                "supervisor_name"+" "+supervisorName+" "+
                                "supvsr_no"+" "+supervisorNo+" "+
                                "awcs_id"+" "+awcsid+" "+
                                "angan_wrkr"+" "+workerName+" "+
                                "worker_no"+" "+workerNo+" "+
                                "angan_hlpr"+" "+helperName+" "+
                                "hlpr_no"+" "+helperNo+" ");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    private void savedataupdatecenrer(String dbdistid,String dbprojectid,String Cdponame,String Cdponumber,String acdpoName,String acdpoContract,String dbsectorid,String supervisorName,String supervisorNo,String awcsid,String workerName,String workerNo,String helperName,String helperNo,int otherinspectionstatus) {
        if (flag.equals("0")) {
            helper.INSPACTIONINSERT(dbdistid, dbprojectid, Cdponame, Cdponumber, acdpoName, acdpoContract, dbsectorid, supervisorName, supervisorNo, awcsid, workerName, workerNo, helperName, helperNo, otherinspectionstatus);
            inspection_person_present();
            Log.e("UPDATECENRERDB", "Insert");
        } else if (flag.equals("1")) {
            helper.INSPACTIONINSERT(dbdistid, dbprojectid, Cdponame, Cdponumber, acdpoName, acdpoContract, dbsectorid, supervisorName, supervisorNo, awcsid, workerName, workerNo, helperName, helperNo, otherinspectionstatus);
            helper.INSPACTIONUPDATE(dbid, dbdistid, dbprojectid, Cdponame, Cdponumber, acdpoName, acdpoContract, dbsectorid, supervisorName, supervisorNo, awcsid, workerName, workerNo, helperName, helperNo, otherinspectionstatus);
            inspection_person_present();
            Log.e("UPDATECENRERDB", "Update");
        }

    }
        private void inspection_person_present(){
        InputFilter filter1 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                char[] chars = {'\'','"'};
                for (int i = start; i < end; i++) {
                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                        return "";
                    }
                }
                return null;
            }
        };
        cmdinformation.setFilters(new InputFilter[] { filter1 });
        cmt = cmdinformation.getText().toString().trim();
        if (TextUtils.isEmpty(cmt)) {
            cmdinformation.setError("Please Enter Comment");
            cmdinformation.requestFocus();
            return;
        }
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.INSPACTIONPERSONPESENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("responsein"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("objin"," "+array);
                            //  JSONArray array1 = array.getJSONArray(0);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                            if (isNetworkAvailable()) {
                                if (Ynawcs.equals("Y")) {
                                    SQLiteDatabase database = helper.getReadableDatabase();
                                    database.execSQL( "UPDATE "+TABLE_ALLINSPECTIONFLAG +" SET " + TABLE_ALLINSPACATIONID+ " = '"+insid+"' WHERE "+TABLE_ALLINSPACATIONID+ " = "+insid);
                                    User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
                                    userID = String.valueOf(user.getUserID());
                                    Cdponame = cdponameID.getText().toString().trim();
                                    Cdponumber = numberID.getText().toString().trim();
                                    acdpoName = acdponameID.getText().toString().trim();
                                    acdpoContract = acdponumberIDD.getText().toString().trim();
                                    supervisorName = supervisiorID.getText().toString().trim();
                                    supervisorNo = supervisiorNumberID.getText().toString().trim();
                                    workerName = workernameID.getText().toString().trim();
                                    workerNo = workernumberID.getText().toString().trim();
                                    helperName = helpernameID.getText().toString().trim();
                                    helperNo = helpernumberID.getText().toString().trim();
                                    Intent intent = new Intent(ProcedActivity.this, InspectionListActivity.class);
                                    Bundle bundle_edit = new Bundle();
                                    bundle_edit.putString("water", water);
                                    bundle_edit.putString("cdponame", Cdponame);
                                    bundle_edit.putString("acdpocont", acdpoContract);
                                    bundle_edit.putString("buildstruc", buildstruc);
                                    bundle_edit.putString("electric", electric);
                                    bundle_edit.putString("acdponame", acdpoName);
                                    bundle_edit.putString("kitchen", kitchen);
                                    bundle_edit.putString("cdpocontact", Cdponumber);
                                    bundle_edit.putString("workerno", workerNo);
                                    bundle_edit.putString("worker_nm", workerName);
                                    bundle_edit.putString("toilet", toilet);
                                    bundle_edit.putString("awcslat", awcslat);
                                    bundle_edit.putString("supvsrname", supervisorName);
                                    bundle_edit.putString("awcsslong", awcsslong);
                                    bundle_edit.putString("helperno", helperNo);
                                    bundle_edit.putString("awcs_adrs", awcs_adrs);
                                    bundle_edit.putString("foodspace", foodspace);
                                    bundle_edit.putString("helpernm", helperName);
                                    bundle_edit.putString("buildon", buildon);
                                    bundle_edit.putString("adqutspacepse", adqutspacepse);
                                    bundle_edit.putString("supvsrno", supervisorNo);
                                    bundle_edit.putString("awcsid", awcsid);
                                    bundle_edit.putString("awcscode", awcscode);
                                    bundle_edit.putString("awcsname", awcsname);
                                    bundle_edit.putString("dbdistid", dbdistid);
                                    bundle_edit.putString("dbprojectid", dbprojectid);
                                    bundle_edit.putString("dbsectorid", dbsectorid);
                                    bundle_edit.putString("dbcenterid", dbcenterid);
                                    bundle_edit.putString("projectnamedb", projectnamedb);
                                    bundle_edit.putString("districnamedb", districnamedb);
                                    bundle_edit.putString("sectorrnamedb", sectorrnamedb);
                                    bundle_edit.putString("centernamedb", centernamedb);
                                    //bundle_edit.putString("currentdate", currentdate);
                                    bundle_edit.putString("currentdate",dateId.getText().toString());
                                    bundle_edit.putString("yncdpo", yncdpo);
                                    bundle_edit.putString("ynacdpio", ynacdpio);
                                    bundle_edit.putString("YnSupervisor", YnSupervisor);
                                    bundle_edit.putString("Ynworker", Ynworker);
                                    bundle_edit.putString("Yhelper", Yhelper);
                                    bundle_edit.putString("Ynawcs", Ynawcs);
                                    bundle_edit.putString("insid", insid);
                                    bundle_edit.putString("planid", planid);
                                    bundle_edit.putString("lstinspctnbuldrep", lstinspctnbuldrep);
                                    bundle_edit.putString("lstinspctntoiletrep", lstinspctntoiletrep);
                                    bundle_edit.putString("lstinspctnkitchenrep", lstinspctnkitchenrep);
                                    bundle_edit.putString("lstinspctnpserep", lstinspctnpserep);
                                    bundle_edit.putString("lstinspctnelectricrep", lstinspctnelectricrep);
                                    bundle_edit.putString("lstinspctndrnkwaterrep", lstinspctndrnkwaterrep);
                                    bundle_edit.putString("lstinspctnfoodrep", lstinspctnfoodrep);
                                    bundle_edit.putString("dbid",dbid);
                                    intent.putExtras(bundle_edit);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "AWCS NOT OPEN", Toast.LENGTH_SHORT).show();
                                }
                                saveInspactionpersonpesent(dbdistid,
                                        dbprojectid,
                                        dbsectorid,
                                        dbcenterid,
                                        yncdpo,
                                        ynacdpio,
                                        YnSupervisor,
                                        Ynworker,
                                        Yhelper,
                                        curDate,
                                        curTime,
                                        Ynawcs,
                                        userID,
                                        insid,
                                        planid,
                                        cmdinformation.getText().toString().trim(),
                                        INSPACTIONPERSONPESENT_SYNCED_WITH_SERVER);

                                if (Ynawcs.equals("N")) {
                                    String insidd = "0";
                                    SQLiteDatabase database = helper.getReadableDatabase();
                                    database.execSQL( "UPDATE "+TABLE_ALLINSPECTIONFLAG +" SET " + TABLE_ALLINSPACATIONID+ " = '"+insidd+"' WHERE "+TABLE_ALLINSPACATIONID+ " = "+insid);
                                    Intent intent = new Intent(ProcedActivity.this, NavigationDrawerActivity.class);
                                    startActivity(intent);
                                }

                            }

                            else {
                                //Toast.makeText(getApplicationContext(), "No Network Device", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        saveInspactionpersonpesent(dbdistid,
                                dbprojectid,
                                dbsectorid,
                                dbcenterid,
                                yncdpo,
                                ynacdpio,
                                YnSupervisor,
                                Ynworker,
                                Yhelper,
                                curDate,
                                curTime,
                                Ynawcs,
                                userID,
                                insid,
                                planid,
                                cmdinformation.getText().toString().trim(),
                                INSPACTIONPERSONPESENT_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("district", dbdistid);
                params.put("project", dbprojectid);
                params.put("sector", dbsectorid);
                params.put("centre",dbcenterid);
                params.put("cdpo_prsnt", yncdpo);
                params.put("acdpo_prsnt",ynacdpio);
                params.put("supvsr_prsnt", YnSupervisor);
                params.put("worker_prsnt",Ynworker);
                params.put("helper_prsnt", Yhelper);
                params.put("inspctn_date",curDate);
                params.put("inspectn_time",curTime);
                params.put("awcs_open", Ynawcs);
                params.put("user_id",userID);
                //  params.put("inspctn_date", curDate);
                params.put("inspctn_id",insid);
                params.put("plan_id",planid);
                params.put("cmnt",cmt);
                Log.e("Inspection_Data",dbdistid+ " "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+yncdpo+" "+ynacdpio+" "+YnSupervisor+" "+Ynworker+" "+Yhelper+" "+curDate+" "+curTime+" "+Ynawcs+" "+userID+" "+curDate+" "+insid+" "+planid);
                Log.e("YnawcsLog",Ynawcs);
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

    private void saveInspactionpersonpesent(String dbdistid,String dbprojectid,
                                            String dbsectorid,String dbcenterid,
                                            String yncdpo,String ynacdpio,
                                            String YnSupervisor,String Ynworker,
                                            String Yhelper,String curDate,
                                            String curTime,String Ynawcs,
                                            String userID,String insid,String planid,String cmt,int inspectionpersonpresentstatus){
        if (flag.equals("0")){
            helper.INSPECTIONPERSONPRESENTINSERT(dbdistid,
                    dbprojectid,
                    dbsectorid,
                    dbcenterid,
                    yncdpo,
                    ynacdpio,
                    YnSupervisor,
                    Ynworker,Yhelper,curDate,curTime,Ynawcs,userID,insid,planid,cmt,inspectionpersonpresentstatus);
            Log.e("savedat","OK"+" "+" "+dbdistid+" "+dbprojectid+" "
                    +dbsectorid+" "+dbcenterid+" "+yncdpo+" "+ynacdpio+" "+YnSupervisor+" "
                    +Ynworker+" "+Yhelper+" "+curDate+" "+curTime+" "+Ynawcs+" "+userID+" "+insid+" "+planid+" "+" "+cmt+" "+inspectionpersonpresentstatus+" ");
        }
        else {
            helper.INSPECTIONPERSONPRESENTUPDATE(dbid,dbdistid,dbprojectid,dbsectorid,dbcenterid,yncdpo,ynacdpio,YnSupervisor,Ynworker,Yhelper,curDate,curTime,Ynawcs,userID,insid,planid,cmt,inspectionpersonpresentstatus);
            Log.e("savedat","OKK"+dbid+" "+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+yncdpo+" "+ynacdpio+" "+YnSupervisor+" "+Ynworker+" "+Yhelper+" "+curDate+" "+curTime+" "+Ynawcs+" "+userID+" "+insid+" "+planid+" "+inspectionpersonpresentstatus+" ");

        }

    }

    //    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(ProcedActivityy.this,NavigationDrawerActivity.class);
//        startActivity(intent);
//    }
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
        System.out.println("Inside onDestroy");
        try{
            if(inspactioninsertNetworkercheker!=null)
                unregisterReceiver(inspactioninsertNetworkercheker);
            if (broadcastReceiverupdatecentrepersndtl!=null)
                unregisterReceiver(broadcastReceiverupdatecentrepersndtl);
            if (inspectionpersonpresentNetworkcheker!=null)
                unregisterReceiver(inspectionpersonpresentNetworkcheker);
            if (broadcastReceiverinspactionpersonpesent!=null)
                unregisterReceiver(broadcastReceiverinspactionpersonpesent);
        }catch(Exception e){}
        super.onDestroy();
    }

}