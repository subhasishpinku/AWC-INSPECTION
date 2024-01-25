package icdswb.in;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
import icdswb.in.ActivityDatabase.EletricityNetwokchecker;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ELECTRITYINSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;


public class ElectricityActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    String cdponame = "NA";
    String acdpocont ="NA";
    String buildstruc ="NA";
    String acdponame ="NA";
    String cdpocontact ="NA";
    String workerno ="NA";
    String worker_nm ="NA";
    String awcslat ="NA";
    String supvsrname ="NA";
    String awcsslong ="NA";
    String helperno ="NA";
    String awcs_adrs ="NA";
    String helpernm ="NA";
    String buildon ="NA";
    String supvsrno ="NA";
    String awcsid ="NA";
    String currentdate ="NA";
    String yncdpo ="NA";
    String ynacdpio = "NA";
    String YnSupervisor ="NA";
    String Ynworker ="NA";
    String Yhelper ="NA";
    String toilet = "NA";
    String awcscode = "NA";
    String awcsname = "NA";
    String dbdistid = "NA";
    String dbprojectid = "NA";
    String dbsectorid = "NA";
    String dbcenterid = "NA";
    String projectnamedb = "NA";
    String districnamedb = "NA";
    String sectorrnamedb = "NA";
    String centernamedb = "NA";
    String inspctrcmnt = "NA";
    EditText cmdedittextID;
    String kitchen = "NA";
    String adqutspacepse = "NA";
    String electric = "NA";
    String water = "NA";
    String foodspace = "NA";
    String electricavail = "NA";
    String emode = "NA";
    String lighttype = "NA";
    String fantype = "NA";
    String Pumpovrhd = "NA";
    Button savenextID;
    String curDate,curTime;
    RadioGroup GelectityID,GmodeID,GlightID,GfanID,pumpforoverheadID;
    RadioButton YelectricavailID,NelectricavailID,conmodeID,solarmodeID,awID,anwID,lnaID,fawID,fanwID,fnaID,pawID,panwID,pnwID;
    TextView electricityID,electriID,awcstvId;
    String planid = "NA";
    String insid;
    String lstinspctnbuldrep = "NA";
    String lstinspctntoiletrep = "NA";
    String lstinspctnkitchenrep = "NA";
    String lstinspctnpserep = "NA";
    String lstinspctnelectricrep = "NA";
    String lstinspctndrnkwaterrep = "NA";
    String lstinspctnfoodrep = "NA";
    String dbid = "NA";
    DatabaseHelper helper;
    String Eelectricavail = "NA";
    String Eemode = "NA";
    String light = "NA";
    String fan = "NA";
    String pumpovrhd = "NA";
    String Einspctrcmnt = "NA";
    String ideletricity = "NA";
    String electricity = "NA";
    public static final int ELETRICITY_SYNCED_WITH_SERVER = 1;
    public static final int ELETRICITY_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceivereletricity;
    public static final String DATA_SAVED_BROADCAST_ELETRICITY = "icdswb.in.eletricitysaved";
    private EletricityNetwokchecker eletricityNetwokchecker;
    String ELECTRICITYSTATUS = "0";
    String electricavailsync = "";
    String emodesync ="";
    String lighttypesync ="";
    String fantypesync ="";
    String pumpovrhdsync ="";
    String inspctrcmntelectricitysync ="";
    String userID;
    private final int REQ_ELETRICITY = 100;
    LinearLayout lv6,lv7,lv8,lv9;
    ImageButton commandspak;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_electricity);
        Intent intent = getIntent();
        water = intent.getStringExtra("water");
        cdponame = intent.getStringExtra("cdponame");
        acdpocont = intent.getStringExtra("acdpocont");
        buildstruc = intent.getStringExtra("buildstruc");
        electric = intent.getStringExtra("electric");
        acdponame  = intent.getStringExtra("acdponame");
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
        dbdistid = intent.getStringExtra("dbdistid");
        dbprojectid = intent.getStringExtra("dbprojectid");
        dbsectorid = intent.getStringExtra("dbsectorid");
        dbcenterid = intent.getStringExtra("dbcenterid");
        projectnamedb = intent.getStringExtra("projectnamedb");
        districnamedb = intent.getStringExtra("districnamedb");
        sectorrnamedb = intent.getStringExtra("sectorrnamedb");
        centernamedb = intent.getStringExtra("centernamedb");
        currentdate = intent.getStringExtra("currentdate");
        planid = intent.getStringExtra("planid");
        yncdpo =   intent.getStringExtra("yncdpo");
        ynacdpio =   intent.getStringExtra("ynacdpio");
        YnSupervisor =   intent.getStringExtra("YnSupervisor");
        Ynworker =  intent.getStringExtra("Ynworker");
        Yhelper =  intent.getStringExtra("Yhelper");
        insid = intent.getStringExtra("insid");
        lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
        lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
        lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
        lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
        lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
        lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
        lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        dbid = intent.getStringExtra("dbid");

//        Intent intent = getIntent();
//        toilet = intent.getStringExtra("toilet");
//        awcscode = intent.getStringExtra("awcscode");
//        awcsname = intent.getStringExtra("awcsname");
//        dbdistid = intent.getStringExtra("dbdistid");
//        dbprojectid = intent.getStringExtra("dbprojectid");
//        dbsectorid = intent.getStringExtra("dbsectorid");
//        dbcenterid = intent.getStringExtra("dbcenterid");
//        projectnamedb = intent.getStringExtra("projectnamedb");
//        districnamedb = intent.getStringExtra("districnamedb");
//        sectorrnamedb = intent.getStringExtra("sectorrnamedb");
//        centernamedb = intent.getStringExtra("centernamedb");
//        insid = intent.getStringExtra("insid");
//        planid= intent.getStringExtra("planid");
      //  dbid = intent.getStringExtra("dbid");
       // Log.e("dbid",dbid);

        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        Log.e("ElP",planid);
        Log.e("ELETCITRY"," "+toilet+" "+awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+insid+" ");
//        adqutspacepse = intent.getStringExtra("adqutspacepse");
     //   electric = intent.getStringExtra("electric");
      //  water = intent.getStringExtra("water");
      //  foodspace = intent.getStringExtra("foodspace");
        Log.e("ELETCITRY"," "+kitchen+" "+adqutspacepse+" "+electric+" "+water+" "+foodspace+" ");
      //  lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
       // lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
      //  lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
       // lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
       // lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
       // lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
      //  lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        Log.e("LAST_ELECTROREPO",lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep);
        electriID = (TextView)findViewById(R.id.electriID);
        electriID.setText(lstinspctnelectricrep);
       // savenextID = (Button)findViewById(R.id.savenextID);
        //savenextID.setOnClickListener(this);
        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        cmdedittextID = (EditText)findViewById(R.id.cmdedittextID);
        cmdedittextID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        cmdedittextID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        electricityID = (TextView)findViewById(R.id.electricityID);
        lv6 = (LinearLayout)findViewById(R.id.lv6);
        lv7 = (LinearLayout)findViewById(R.id.lv7);
        lv8 = (LinearLayout)findViewById(R.id.lv8);
        lv9 = (LinearLayout)findViewById(R.id.lv9);
        lv6.setVisibility(View.GONE);
        lv7.setVisibility(View.GONE);
        lv8.setVisibility(View.GONE);
        lv9.setVisibility(View.GONE);
        electricityID.setText(electric);
        helper = new DatabaseHelper(this);
        initToolBar();
        rediogroupID();
       // Cursor cursor = helper.getReadableDatabase().
           //     rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
        //String queryy = "SELECT * FROM insflag WHERE allinspactionid=" + insid;
        Cursor c = helper.getLoginData();
        if (c.moveToFirst()){
            do {
                userID = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                Log.e("DBUSERID",userID);
            }while (c.moveToNext());
        }
        String queryy = "SELECT * FROM " + TABLE_ALLINSPECTIONFLAG + " where " + TABLE_ALLINSPACATIONID + "=" +insid+ " and " +TABLE_USERIDFLA+ "=" +userID;
        SQLiteDatabase dbd = helper.getReadableDatabase();
        Cursor  cursor = dbd.rawQuery(queryy,null);
        if (cursor.moveToFirst()){
            do {
                ideletricity = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                electricity = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECTRICITY));
                Log.e("ELECTRICITY"," "+ideletricity+" "+electricity);
            }
            while (cursor.moveToNext());
        }
        if (electricity.equals("0")){

        }
        else {
            editEletricity();
        }

        broadcastReceivereletricity = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceivereletricity, new IntentFilter(DATA_SAVED_BROADCAST_ELETRICITY));
        eletricityNetwokchecker = new EletricityNetwokchecker();
        registerReceiver(eletricityNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        if (isNetworkAvailable()){

        }
        else {
           // String query = "SELECT * FROM " + TABLE_ELECTRITY + " where " + TABLE_ELECTRITYINSIDSYNC + "=" + insid + " and " + TABLE_ELECTRICITYSTATUS + "=" + ELECTRICITYSTATUS;
            String query = "SELECT * FROM electrity WHERE electrityinsidsync=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cc = db.rawQuery(query, null);
            if (cc.moveToFirst()) {
                do {
                    String electrityid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ELECTRITYID));
                    String electrityinsidsync = cc.getString(cc.getColumnIndex(TABLE_ELECTRITYINSIDSYNC));
                    electricavailsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ELECTRICAVAILSYNC));
                    emodesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_EMODESYNC));
                    lighttypesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LIGHTTYPESYNC));
                    fantypesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FANTYPESYNC));
                    pumpovrhdsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PUMPOVRHDSYNC));
                    inspctrcmntelectricitysync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ELECRITYINSPCTRCMNTSYNC));
                    String cuttimeeletricitysync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ELECTRITYCUTTIMESYNC));
                    String lastisacrepeletricitysync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LASTISACREPELECTRICITYSYNC));
                    String lastinspctnrepeletricitysync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LASTINSPCTNREPELETRICITYSYNC));
                    String eletricitystatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ELECTRICITYSTATUS));
                    Log.e("ELECTRICITYSYNC", " " + electrityid + " " + electrityinsidsync + " " + electricavailsync + " " + emodesync + " " + lighttypesync + " " + fantypesync + " " + pumpovrhdsync + " " + inspctrcmntelectricitysync + " " + cuttimeeletricitysync+" "+lastisacrepeletricitysync+" "+lastinspctnrepeletricitysync+" "+eletricitystatus);
                }
                while (cc.moveToNext());
            }
            showdata();
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.homeID:
                                homesave();
                                break;
                            case R.id.saveID:
                                update_electricity_inspection();
                                break;
                            default:
                        }
                        return true;
                    }
                });

        commandspak = (ImageButton)findViewById(R.id.commandspak);
        commandspak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechHelperNumber();
            }
        });
    }
    private void promptSpeechHelperNumber(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_command));
        try {
            startActivityForResult(intent,REQ_ELETRICITY);
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
            case REQ_ELETRICITY: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    cmdedittextID.setText(result.get(0));
                }
                break;
            }
        }
    }
    public void showdata(){
        if (electricavailsync.equals("y")){
            YelectricavailID.setChecked(true);
        }
        else if (electricavailsync.equals("n")){
            NelectricavailID.setChecked(true);
        }
        else {
            YelectricavailID.setChecked(false);
            NelectricavailID.setChecked(false);
        }

        if (emodesync.equals("conv")){
            conmodeID.setChecked(true);
        }
        else if (emodesync.equals("solar")){
            solarmodeID.setChecked(true);
        }

        else {
            conmodeID.setChecked(false);
            solarmodeID.setChecked(false);
        }

        if (lighttypesync.equals("AW")){
            awID.setChecked(true);
        }
        else if (lighttypesync.equals("ANW")){
            anwID.setChecked(true);
        }
        else if (lighttypesync.equals("NA")){
            lnaID.setChecked(true);
        }
        else {
            awID.setChecked(false);
            anwID.setChecked(false);
            lnaID.setChecked(false);

        }
        if (fantypesync.equals("AW")){
            fawID.setChecked(true);
        }
        else if (fantypesync.equals("ANW")){
            fanwID.setChecked(true);
        }
        else if (fantypesync.equals("NA")){
            fnaID.setChecked(true);
        }
        else {
            fawID.setChecked(false);
            fanwID.setChecked(false);
            fnaID.setChecked(false);
        }
        if (pumpovrhdsync.equals("AW")){
            pawID.setChecked(true);
        }
        else if (pumpovrhdsync.equals("ANW")){
            panwID.setChecked(true);
        }
        else if (pumpovrhdsync.equals("NA")){
            pnwID.setChecked(true);
        }
        else {
            pawID.setChecked(false);
            panwID.setChecked(false);
            pnwID.setChecked(false);
        }
        cmdedittextID.setText(inspctrcmntelectricitysync);

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
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("AWC INSPECTION");
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
    public void rediogroupID(){
        GelectityID = (RadioGroup)findViewById(R.id.GelectityID);
        GmodeID = (RadioGroup)findViewById(R.id.GmodeID);
        GlightID = (RadioGroup)findViewById(R.id.GlightID);
        GfanID = (RadioGroup)findViewById(R.id.GfanID);
        pumpforoverheadID = (RadioGroup)findViewById(R.id.pumpforoverheadID);
        YelectricavailID = (RadioButton)findViewById(R.id.YelectricavailID);
        NelectricavailID = (RadioButton)findViewById(R.id.NelectricavailID);
        conmodeID = (RadioButton)findViewById(R.id.conmodeID);
        solarmodeID = (RadioButton)findViewById(R.id.solarmodeID);
        awID = (RadioButton)findViewById(R.id.awID);
        anwID = (RadioButton)findViewById(R.id.anwID);
        lnaID = (RadioButton)findViewById(R.id.lnaID);
        fawID = (RadioButton)findViewById(R.id.fawID);
        fanwID = (RadioButton)findViewById(R.id.fanwID);
        fnaID = (RadioButton)findViewById(R.id.fnaID);
        pawID = (RadioButton)findViewById(R.id.pawID);
        panwID = (RadioButton)findViewById(R.id.panwID);
        pnwID = (RadioButton)findViewById(R.id.pnwID);
        GelectityID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YelectricavailID){
                    electricavail = "y";
                    Log.e("electricavail",electricavail);
                   // awID.setVisibility(View.VISIBLE);
                 //   fawID.setVisibility(View.VISIBLE);
                  //  pawID.setVisibility(View.VISIBLE);
                    if (electricavail.equals("y")){
                        lv6.setVisibility(View.VISIBLE);
                        lv7.setVisibility(View.VISIBLE);
                        lv8.setVisibility(View.VISIBLE);
                        lv9.setVisibility(View.VISIBLE);
                    }
                }
                else if (checkedId== R.id.NelectricavailID){
                    electricavail = "n";
                    Log.e("electricavail",electricavail);
                 //   awID.setVisibility(View.GONE);
                 //   fawID.setVisibility(View.GONE);
                  //  pawID.setVisibility(View.GONE);
                    if (electricavail.equals("n")){
                        lv6.setVisibility(View.GONE);
                        lv7.setVisibility(View.GONE);
                        lv8.setVisibility(View.GONE);
                        lv9.setVisibility(View.GONE);
                    }
                    if (conmodeID.isChecked()){
                        conmodeID.setChecked(false);
                        conmodeID.setSelected(false);
                    }
                    if (solarmodeID.isChecked()){
                        solarmodeID.setChecked(false);
                        solarmodeID.setSelected(false);
                    }
                    if (awID.isChecked()){
                        awID.setChecked(false);
                        awID.setSelected(false);
                    }

                    if (anwID.isChecked()){
                        anwID.setChecked(false);
                        anwID.setSelected(false);
                    }
                    if (lnaID.isChecked()){
                        lnaID.setChecked(false);
                        lnaID.setSelected(false);
                    }

                    if (fawID.isChecked()){
                        fawID.setChecked(false);
                        fawID.setSelected(false);
                    }
                    if (fanwID.isChecked()){
                        fanwID.setChecked(false);
                        fanwID.setSelected(false);
                    }

                    if (fnaID.isChecked()){
                        fnaID.setChecked(false);
                        fnaID.setSelected(false);
                    }
                    if (pawID.isChecked()){
                        pawID.setChecked(false);
                        pawID.setSelected(false);
                    }
                    if (panwID.isChecked()){
                        panwID.setChecked(false);
                        panwID.setSelected(false);
                    }
                    if (pnwID.isChecked()){
                        pnwID.setChecked(false);
                        pnwID.setSelected(false);
                    }
                    cmdedittextID.setText("");
                }
            }});
        GmodeID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.conmodeID){
                    emode = "conv";
                    Log.e("emode",emode);
                }
                else if (checkedId== R.id.solarmodeID){
                    emode = "solar";
                    Log.e("emode",emode);
                }


            }
        });
        GlightID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.awID){
                    lighttype = "AW";
                    Log.e("lighttype",lighttype);

                }
                else if (checkedId== R.id.anwID){
                    lighttype = "ANW";
                    Log.e("lighttype",lighttype);
                }

                else if (checkedId== R.id.lnaID){
                    lighttype = "NA";
                    Log.e("lighttype",lighttype);
                }


            }
        });
        GfanID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.fawID){
                    fantype = "AW";
                    Log.e("fantype",fantype);
                }
                else if (checkedId== R.id.fanwID){
                    fantype = "ANW";
                    Log.e("fantype",fantype);
                }

                else if (checkedId== R.id.fnaID){
                    fantype = "NA";
                    Log.e("fantype",fantype);
                }

            }
        });
        pumpforoverheadID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.pawID){
                    Pumpovrhd = "AW";
                    Log.e("Pumpovrhd",Pumpovrhd);
                }
                else if (checkedId== R.id.panwID){
                    Pumpovrhd = "ANW";
                    Log.e("Pumpovrhd",Pumpovrhd);
                }
                else if (checkedId== R.id.pnwID){
                    Pumpovrhd = "NA";
                    Log.e("Pumpovrhd",Pumpovrhd);
                }
            }
        });
    }

    private void editEletricity(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ELECTRICITYEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("ELECTRIEDIT"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray jsonArray = object.getJSONArray("electric_dtl");
                            for (int i =0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Eelectricavail = jsonObject.getString("electric_avail");
                                Eemode = jsonObject.getString("e_mode");
                                light = jsonObject.getString("light");
                                fan  = jsonObject.getString("fan");
                                pumpovrhd = jsonObject.getString("pump_ovrhd");
                                Einspctrcmnt = jsonObject.getString("inspctr_cmnt");
                                Log.e("ELECTRIY"," "+Eelectricavail+" "+Eemode+" "+light+" "+fan+' '+pumpovrhd+" "+Einspctrcmnt);
                                if (Eelectricavail.equals("y")){
                                    YelectricavailID.setChecked(true);
                                }
                                else if (Eelectricavail.equals("n")){
                                    NelectricavailID.setChecked(true);
                                }
                                else {
                                    YelectricavailID.setChecked(false);
                                    NelectricavailID.setChecked(false);
                                }

                                if (Eemode.equals("conv")){
                                    conmodeID.setChecked(true);
                                }
                                else if (Eemode.equals("solar")){
                                    solarmodeID.setChecked(true);
                                }

                                else {
                                    conmodeID.setChecked(false);
                                    solarmodeID.setChecked(false);
                                }

                                if (light.equals("AW")){
                                    awID.setChecked(true);
                                }
                                else if (light.equals("ANW")){
                                    anwID.setChecked(true);
                                }
                                else if (light.equals("NA")){
                                    lnaID.setChecked(true);
                                }
                                else {
                                    awID.setChecked(false);
                                    anwID.setChecked(false);
                                    lnaID.setChecked(false);

                                }
                                if (fan.equals("AW")){
                                    fawID.setChecked(true);
                                }
                                else if (fan.equals("ANW")){
                                    fanwID.setChecked(true);
                                }
                                else if (fan.equals("NA")){
                                    fnaID.setChecked(true);
                                }
                                else {
                                    fawID.setChecked(false);
                                    fanwID.setChecked(false);
                                    fnaID.setChecked(false);
                                }
                                if (pumpovrhd.equals("AW")){
                                    pawID.setChecked(true);
                                }
                                else if (pumpovrhd.equals("ANW")){
                                    panwID.setChecked(true);
                                }
                                else if (pumpovrhd.equals("NA")){
                                    pnwID.setChecked(true);
                                }
                                else {
                                    pawID.setChecked(false);
                                    panwID.setChecked(false);
                                    pnwID.setChecked(false);
                                }
                                cmdedittextID.setText(Einspctrcmnt);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

    private void update_electricity_inspection(){
        InputFilter filter = new InputFilter() {
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
        cmdedittextID.setFilters(new InputFilter[] { filter });
        inspctrcmnt = cmdedittextID.getText().toString().trim();
        if (YelectricavailID.isChecked() || NelectricavailID.isChecked()){
        if (electricavail.equals("y")){
                if (YelectricavailID.isChecked() || NelectricavailID.isChecked()){
                    if (conmodeID.isChecked() || solarmodeID.isChecked()){
                        if (awID.isChecked() || anwID.isChecked() || lnaID.isChecked()){
                            if (fawID.isChecked()|| fanwID.isChecked() || fnaID.isChecked()){
                                if (pawID.isChecked() || panwID.isChecked() || pnwID.isChecked()){
                                    if (TextUtils.isEmpty(inspctrcmnt)) {
                                        cmdedittextID.setError("Please enter Command");
                                        cmdedittextID.requestFocus();
                                        return;
                                    }
                                    senddataeletricity();
                                }
                                else {
                                    Toast.makeText(ElectricityActivity.this,"SELECT PUMP FOR OVERHEAD WATER STORAGE",Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT PUMP FOR OVERHEAD WATER STORAGE";
                                    createDialog(title,msg);
                                }
                            }
                            else {
                                Toast.makeText(ElectricityActivity.this,"SELECT FAN",Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT FAN";
                                createDialog(title,msg);
                            }

                        }
                        else {
                            Toast.makeText(ElectricityActivity.this,"SELECT LIGHT",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT LIGHT";
                            createDialog(title,msg);
                        }

                    }
                    else {

                        Toast.makeText(ElectricityActivity.this,"SELECT MODE",Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT MODE";
                        createDialog(title,msg);
                    }

                }
                else {
                    Toast.makeText(ElectricityActivity.this,"SELECT ELECTRICITY AVAILABLE",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT ELECTRICITY AVAILABLE";
                    createDialog(title,msg);
                }



        }
        if (electricavail.equals("n")) {
            if (TextUtils.isEmpty(inspctrcmnt)) {
                cmdedittextID.setError("Please enter Command");
                cmdedittextID.requestFocus();
                return;
            }
            senddataeletricity();
        }
        else {

        }

        }
        else {
            Toast.makeText(ElectricityActivity.this,"SELECT ELECTRICITY AVAILABLE",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT ELECTRICITY AVAILABLE";
            createDialog(title,msg);
        }

    }
    public void senddataeletricity(){
        Log.e("TEST","0");
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ELECTRICITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("PSE"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("PSEOBJ"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET electricity='1' WHERE allinspactionid=" +insid);
                                if (electricavail.equals("y")){
                                    syncEletricitysaveDatabase(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,ELETRICITY_SYNCED_WITH_SERVER);
                                }
                                if (electricavail.equals("n")){
                                    emode = "NA";
                                    lighttype = "NA";
                                    fantype = "NA";
                                    Pumpovrhd ="NA";
                                    syncEletricitysaveDatabase(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,ELETRICITY_SYNCED_WITH_SERVER);
                                }
                                DataSendNEXTActivity();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.getMessage());
                        helper = new DatabaseHelper(getApplicationContext());
                        SQLiteDatabase dbb = helper.getReadableDatabase();
                        dbb.execSQL("UPDATE insflag SET electricity='1' WHERE allinspactionid=" +insid);
                        if (electricavail.equals("y")){
                            syncEletricitysaveDatabase(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,ELETRICITY_NOT_SYNCED_WITH_SERVER);
                        }
                        if (electricavail.equals("n")){
                            emode = "NAA";
                            lighttype = "NAA";
                            fantype = "NAA";
                            Pumpovrhd ="NAA";
                            syncEletricitysaveDatabase(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,ELETRICITY_NOT_SYNCED_WITH_SERVER);

                        }

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                if (electricavail.equals("y")){
                    params.put("inspctn_id",insid);
                    params.put("electric_avail",electricavail);
                    params.put("e_mode",emode);
                    params.put("light", lighttype);
                    params.put("fan",fantype);
                    params.put("pump_ovrhd",Pumpovrhd);
                    params.put("inspctr_cmnt",inspctrcmnt);
                    params.put("ins_time",curTime);
                    params.put("last_isac_rep",electric);
                    params.put("last_inspctn_rep",lstinspctnelectricrep);
                }
                if (electricavail.equals("n")){
                    emode = "NAA";
                    lighttype = "NAA";
                    fantype = "NAA";
                    Pumpovrhd ="NAA";
                    params.put("inspctn_id",insid);
                    params.put("electric_avail",electricavail);
                    params.put("e_mode",emode);
                    params.put("light", lighttype);
                    params.put("fan",fantype);
                    params.put("pump_ovrhd",Pumpovrhd);
                    params.put("inspctr_cmnt",inspctrcmnt);
                    params.put("ins_time",curTime);
                    params.put("last_isac_rep",electric);
                    params.put("last_inspctn_rep",lstinspctnelectricrep);
                }
                Log.e("ELETRICITY",insid+" "+electricavail+" "+emode+" "+" "+lighttype+" "+fantype+" "+Pumpovrhd+" "+inspctrcmnt+" "+curTime+" "+electric+" "+lstinspctnelectricrep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

    private void syncEletricitysaveDatabase(String insid,String electricavail,String emode,String lighttype,String fantype,String Pumpovrhd,String inspctrcmnt,String curTime,String electric,String lstinspctnelectricrep, int eletricitystatus){
        if (electricity.equals("0")) {
            helper.ELETRICITYINSERT(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,eletricitystatus);

        }
        else {
            helper.ELETRICITYUPDATE(dbid,insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,eletricitystatus);

        }
        DataSendNEXTActivity();
    }
    public AlertDialog createDialog(String title, String msg){
        Log.e("METHOD"," "+title+" "+msg);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//       // AlertDialog dialog = builder.create();
//        AlertDialog dialog = builder.create();
//        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
//                (Context.LAYOUT_INFLATER_SERVICE);
//        View dialogView = inflater.inflate(R.layout.messagedialog, null);
//        ((TextView)dialogView.findViewById(R.id.dialog_title)).setText(title);
//        ((TextView)dialogView.findViewById(R.id.dialog_msg)).setText(msg);
//        builder.setView(dialogView);
//        final AlertDialog finalDialog = dialog;
//        ((Button)dialogView.findViewById(R.id.dialog_button)).setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                finalDialog.dismiss();
//                Log.e("HHHH","0");
//            }
//        });
//        builder.setView(dialogView);
//        dialog = builder.create();
//        dialog.show();
//        return dialog;

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
    public void DataSendNEXTActivity(){
        Intent intent = new Intent(ElectricityActivity.this, DrinkingWaterActivity.class);
        Bundle bundle_edit = new Bundle();
        bundle_edit.putString("water", water);
        bundle_edit.putString("cdponame", cdponame);
        bundle_edit.putString("acdpocont", acdpocont);
        bundle_edit.putString("buildstruc", buildstruc);
        bundle_edit.putString("electric", electric);
        bundle_edit.putString("acdponame", acdponame);
        bundle_edit.putString("kitchen", kitchen);
        bundle_edit.putString("cdpocontact", cdpocontact);
        bundle_edit.putString("workerno", workerno);
        bundle_edit.putString("worker_nm", worker_nm);
        bundle_edit.putString("toilet", toilet);
        bundle_edit.putString("awcslat", awcslat);
        bundle_edit.putString("supvsrname", supvsrname);
        bundle_edit.putString("awcsslong", awcsslong);
        bundle_edit.putString("helperno", helperno);
        bundle_edit.putString("awcs_adrs", awcs_adrs);
        bundle_edit.putString("foodspace", foodspace);
        bundle_edit.putString("helpernm", helpernm);
        bundle_edit.putString("buildon", buildon);
        bundle_edit.putString("adqutspacepse", adqutspacepse);
        bundle_edit.putString("supvsrno", supvsrno);
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
        bundle_edit.putString("currentdate", currentdate);
        bundle_edit.putString("planid", planid);
        bundle_edit.putString("yncdpo", yncdpo);
        bundle_edit.putString("ynacdpio", ynacdpio);
        bundle_edit.putString("YnSupervisor", YnSupervisor);
        bundle_edit.putString("Ynworker", Ynworker);
        bundle_edit.putString("Yhelper", Yhelper);
        bundle_edit.putString("insid", insid);
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
    }
        public void homesave(){
            InputFilter filter = new InputFilter() {
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
            cmdedittextID.setFilters(new InputFilter[] { filter });
            inspctrcmnt = cmdedittextID.getText().toString().trim();
            if (YelectricavailID.isChecked() || NelectricavailID.isChecked()){
                if (electricavail.equals("y")){
                    if (YelectricavailID.isChecked() || NelectricavailID.isChecked()){
                        if (conmodeID.isChecked() || solarmodeID.isChecked()){
                            if (awID.isChecked() || anwID.isChecked() || lnaID.isChecked()){
                                if (fawID.isChecked()|| fanwID.isChecked() || fnaID.isChecked()){
                                    if (pawID.isChecked() || panwID.isChecked() || pnwID.isChecked()){
                                        if (TextUtils.isEmpty(inspctrcmnt)) {
                                            cmdedittextID.setError("Please enter Command");
                                            cmdedittextID.requestFocus();
                                            return;
                                        }
                                        senddataeletricity1();
                                    }
                                    else {
                                        Toast.makeText(ElectricityActivity.this,"SELECT PUMP FOR OVERHEAD WATER STORAGE",Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SELECT PUMP FOR OVERHEAD WATER STORAGE";
                                        createDialog(title,msg);
                                    }
                                }
                                else {
                                    Toast.makeText(ElectricityActivity.this,"SELECT FAN",Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT FAN";
                                    createDialog(title,msg);
                                }

                            }
                            else {
                                Toast.makeText(ElectricityActivity.this,"SELECT LIGHT",Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT LIGHT";
                                createDialog(title,msg);
                            }

                        }
                        else {

                            Toast.makeText(ElectricityActivity.this,"SELECT MODE",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT MODE";
                            createDialog(title,msg);
                        }

                    }
                    else {
                        Toast.makeText(ElectricityActivity.this,"SELECT ELECTRICITY AVAILABLE",Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT ELECTRICITY AVAILABLE";
                        createDialog(title,msg);
                    }
                }
                if (electricavail.equals("n")) {
                    if (TextUtils.isEmpty(inspctrcmnt)) {
                        cmdedittextID.setError("Please enter Command");
                        cmdedittextID.requestFocus();
                        return;
                    }
                    senddataeletricity1();
                }
                else {

                }

            }
            else {
                Toast.makeText(ElectricityActivity.this,"SELECT ELECTRICITY AVAILABLE",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT ELECTRICITY AVAILABLE";
                createDialog(title,msg);
            }

        }
    public void senddataeletricity1(){
        Log.e("TEST","0");
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ELECTRICITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("PSE"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("PSEOBJ"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET electricity='1' WHERE allinspactionid=" +insid);
                                if (electricavail.equals("y")){
                                    syncEletricitysaveDatabase1(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,ELETRICITY_SYNCED_WITH_SERVER);
                                }
                                if (electricavail.equals("n")){
                                    emode = "NA";
                                    lighttype = "NA";
                                    fantype = "NA";
                                    Pumpovrhd ="NA";
                                    syncEletricitysaveDatabase1(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,ELETRICITY_SYNCED_WITH_SERVER);
                                }
                                BuildingReturndata();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.getMessage());
                        helper = new DatabaseHelper(getApplicationContext());
                        SQLiteDatabase dbb = helper.getReadableDatabase();
                        dbb.execSQL("UPDATE insflag SET electricity='1' WHERE allinspactionid=" +insid);
                        if (electricavail.equals("y")){
                            syncEletricitysaveDatabase1(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,ELETRICITY_NOT_SYNCED_WITH_SERVER);
                        }
                        if (electricavail.equals("n")){
                            emode = "NAA";
                            lighttype = "NAA";
                            fantype = "NAA";
                            Pumpovrhd ="NAA";
                            syncEletricitysaveDatabase1(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,ELETRICITY_NOT_SYNCED_WITH_SERVER);

                        }

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                if (electricavail.equals("y")){
                    params.put("inspctn_id",insid);
                    params.put("electric_avail",electricavail);
                    params.put("e_mode",emode);
                    params.put("light", lighttype);
                    params.put("fan",fantype);
                    params.put("pump_ovrhd",Pumpovrhd);
                    params.put("inspctr_cmnt",inspctrcmnt);
                    params.put("ins_time",curTime);
                    params.put("last_isac_rep",electric);
                    params.put("last_inspctn_rep",lstinspctnelectricrep);
                }
                if (electricavail.equals("n")){
                    emode = "NAA";
                    lighttype = "NAA";
                    fantype = "NAA";
                    Pumpovrhd ="NAA";
                    params.put("inspctn_id",insid);
                    params.put("electric_avail",electricavail);
                    params.put("e_mode",emode);
                    params.put("light", lighttype);
                    params.put("fan",fantype);
                    params.put("pump_ovrhd",Pumpovrhd);
                    params.put("inspctr_cmnt",inspctrcmnt);
                    params.put("ins_time",curTime);
                    params.put("last_isac_rep",electric);
                    params.put("last_inspctn_rep",lstinspctnelectricrep);
                }
                Log.e("ELETRICITY",insid+" "+electricavail+" "+emode+" "+" "+lighttype+" "+fantype+" "+Pumpovrhd+" "+inspctrcmnt+" "+curTime+" "+electric+" "+lstinspctnelectricrep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    private void syncEletricitysaveDatabase1(String insid,String electricavail,String emode,String lighttype,String fantype,String Pumpovrhd,String inspctrcmnt,String curTime,String electric,String lstinspctnelectricrep, int eletricitystatus){
        if (electricity.equals("0")) {
            helper.ELETRICITYINSERT(insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,eletricitystatus);

        }
        else {
            helper.ELETRICITYUPDATE(dbid,insid,electricavail,emode,lighttype,fantype,Pumpovrhd,inspctrcmnt,curTime,electric,lstinspctnelectricrep,eletricitystatus);

        }
        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(ElectricityActivity.this, InspectionListActivity.class);
        Bundle bundle_edit = new Bundle();
        bundle_edit.putString("water", water);
        bundle_edit.putString("cdponame", cdponame);
        bundle_edit.putString("acdpocont", acdpocont);
        bundle_edit.putString("buildstruc", buildstruc);
        bundle_edit.putString("electric", electric);
        bundle_edit.putString("acdponame", acdponame);
        bundle_edit.putString("kitchen", kitchen);
        bundle_edit.putString("cdpocontact", cdpocontact);
        bundle_edit.putString("workerno", workerno);
        bundle_edit.putString("worker_nm", worker_nm);
        bundle_edit.putString("toilet", toilet);
        bundle_edit.putString("awcslat", awcslat);
        bundle_edit.putString("supvsrname", supvsrname);
        bundle_edit.putString("awcsslong", awcsslong);
        bundle_edit.putString("helperno", helperno);
        bundle_edit.putString("awcs_adrs", awcs_adrs);
        bundle_edit.putString("foodspace", foodspace);
        bundle_edit.putString("helpernm", helpernm);
        bundle_edit.putString("buildon", buildon);
        bundle_edit.putString("adqutspacepse", adqutspacepse);
        bundle_edit.putString("supvsrno", supvsrno);
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
        bundle_edit.putString("currentdate", currentdate);
        bundle_edit.putString("planid", planid);
        bundle_edit.putString("yncdpo", yncdpo);
        bundle_edit.putString("ynacdpio", ynacdpio);
        bundle_edit.putString("YnSupervisor", YnSupervisor);
        bundle_edit.putString("Ynworker", Ynworker);
        bundle_edit.putString("Yhelper", Yhelper);
        bundle_edit.putString("insid", insid);
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
    }
    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.savenextID:
//                update_electricity_inspection();
//                break;
//                default:
//        }
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
//       startActivity(intent);
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
        try{
            if(eletricityNetwokchecker!=null)
                unregisterReceiver(eletricityNetwokchecker);
            if (broadcastReceivereletricity!=null)
                unregisterReceiver(broadcastReceivereletricity);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
