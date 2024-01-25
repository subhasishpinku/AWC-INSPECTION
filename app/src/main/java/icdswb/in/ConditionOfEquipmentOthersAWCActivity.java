package icdswb.in;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import icdswb.in.ActivityDatabase.ConditionNetwokchecker;
import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.Activity_Fragment.InputFilterMinMax;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CONDITIONOFEQIPMENTINSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;


public class ConditionOfEquipmentOthersAWCActivity extends AppCompatActivity implements View.OnClickListener {
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
    String insid;
    String kitchen = "NA";
    String adqutspacepse = "NA";
    String electric = "NA";
    String water = "NA";
    String foodspace = "NA";
    RadioGroup GutensilcondtnID, GmatchataiID, GelevenID, babyWmachineID, GadltID, GmuactapeID,
            hmtID, GgrowthID, GsrotingconID, srotingconditionID,usefunctionId,adultgp,hightgpId,grothgpId,suficentgpId,gphandshopId;
    RadioButton goodutensilID, averageutensilID, poorutensilID, notavalutensilID, goodmcID,
            averagemcID, poormcID, notavelemcID, goodelevenID, averageelevenID, poorelevenID,
            NableevenID, babygoodID, babyaverageID, babypoorID, babynotAvID, goodadlID, averageadlID,
            pooradlID, notaadlID, goodmuacID, averagemuacID, pooremuacID, naemuacID, goodhmtID,
            averagehmtID, poorhmtID, nahmtID, properiyfilledID, improperiyfilledID, notproperiyfilledID,
            avablesuffID, avablenotsuffID, notavalableID, auoID, nAID,useId,nouseId,useadult,nouseadultId,
            heightusedId,hightnotuseId,grothproperId,notgrothproperId,saficentId,notsuficientId,gandshopyesId,handshpnoId;
    Button saveID;
    String curDate, curTime;
    String utensilcondtn = "NA";
    String matconditn = "NA";
    String elevnregstr = "NA";
    String babywmachin = "NA";
    String adultwmachin = "NA";
    String muactape = "NA";
    String heightmeasurtyp = "NA";
    String growthchrt = "NA";
    String storecontainr = "NA";
    String handwashsoap = "NA";
    String dbid = "NA";
    String Eutensilcondtn = "NA";
    String Ematconditn = "NA";
    String Eelevnregstr = "NA";
    String elevnregstrno = "NA";
    String Ebabywmachin = "NA";
    String babywmachinuse = "NA";
    String Eadultwmachin = "NA";
    String adultwmachinuse = "NA";
    String Emuactape = "NA";
    String Eheightmeasurtyp = "NA";
    String heightmeasurtypuse = "NA";
    String Egrowthchrt = "NA";
    String Estorecontainr = "NA";
    String suffistorecontainr = "NA";
    String Ehandwashsoap = "NA";
    String handwashsoapuse = "NA";
    String growthchrtfillup = "NA";
    String planid = "NA";
    String Idcondition = "NA";
    String conditions = "NA";
    String cdponame ="NA";
    String acdpocont ="NA";
    String buildstruc ="NA";
    String acdponame ="NA";
    String cdpocontact ="NA";
    String workerno ="NA";
    String worker_nm = "NA";
    String awcslat ="NA";
    String supvsrname ="NA";
    String awcsslong = "NA";
    String helperno ="NA";
    String awcs_adrs = "NA";
    String helpernm = "NA";
    String buildon = "NA";
    String supvsrno ="NA";
    String awcsid = "NA";
    String currentdate = "NA";
    String yncdpo = "NA";
    String ynacdpio = "NA";
    String YnSupervisor = "NA";
    String Ynworker = "NA";
    String Yhelper = "NA";
    String lstinspctnbuldrep = "NA";
    String lstinspctntoiletrep ="NA";
    String lstinspctnkitchenrep = "NA";
    String lstinspctnpserep = "NA";
    String lstinspctnelectricrep = "NA";
    String lstinspctndrnkwaterrep = "NA";
    String lstinspctnfoodrep = "NA";
    DatabaseHelper helper;
    public static final int CONDITION_SYNCED_WITH_SERVER = 1;
    public static final int CONDITION_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiverecondition;
    public static final String DATA_SAVED_BROADCAST_CONDITION = "icdswb.in.eletricitysaved";
    private ConditionNetwokchecker conditionNetwokchecker;
    String CONDITIONSTATUS = "0";
    String utensilcondtnsync ="";
    String matconditnsync ="";
    String elevnregstrsync ="";
    String elevnregstrsyncdb ="";
    String conditionbabyweighingmechinusednotoused ="";
    String babywmachinsync = "";
    String adultwmachinsync ="";
    String adualtweighingmachineusednotuseddb ="";
    String heightmeasurtypsync ="";
    String heightmeasuringtype ="";
    String growthchrtsync="";
    String properfilledupdb ="";
    String storecontainrsync ="";
    String suficentdb ="";
    String handwashsoapsync="";
    String handwashingsopedb ="";
    TextView awcstvId;
    boolean isUpdate;
    String userID;
    LinearLayout lvuseId,lvadultId,LvheigthtId,lvgrothId,lvsuficentId,lvhandshpId,LVelevenrgisId;
    EditText ElevenregisterId;
    String elavenregister = "NA";
    String conditionbabyweighingmechin_used_notoused = "NA";
    String adualtweighingmachine_used_notused = "NA";
    String height_measuring_type = "NA";
    String proper_filled_up = "NA";
    String suficent = "NA";
    String hand_washing_sope = "NA";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_conditionofequipmentothersawc);
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
//        planid = intent.getStringExtra("planid");
//        dbid = intent.getStringExtra("dbid");
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
       // Log.e("dbid", dbid);
        ElevenregisterId = (EditText) findViewById(R.id.ElevenregisterId);
        ElevenregisterId.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "11")});
        ElevenregisterId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    elavenregister = ElevenregisterId.getText().toString();
                    if (elavenregister.equals("0")) {
                        LVelevenrgisId.setVisibility(View.GONE);
                    } else if (elavenregister.equals("1") || elavenregister.equals("2")
                            || elavenregister.equals("3")
                            || elavenregister.equals("4")
                            || elavenregister.equals("5")
                            || elavenregister.equals("6")
                            || elavenregister.equals("7")
                            || elavenregister.equals("8")
                            || elavenregister.equals("9")
                            || elavenregister.equals("10")
                            || elavenregister.equals("11")) {
                        LVelevenrgisId.setVisibility(View.VISIBLE);
                    }
                   else if (elavenregister.equals("12") || elavenregister.equals("99")){
                        ElevenregisterId.setText("");
                    }
                }
                return false;
            }
        });

        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        awcstvId =(TextView)findViewById(R.id.awcstvId);
        lvuseId = (LinearLayout)findViewById(R.id.lvuseId);
        lvuseId.setVisibility(View.GONE);
        lvadultId =(LinearLayout)findViewById(R.id.lvadultId);
        lvadultId.setVisibility(View.GONE);
        LvheigthtId = (LinearLayout)findViewById(R.id.LvheigthtId);
        LvheigthtId.setVisibility(View.GONE);
        lvgrothId =(LinearLayout)findViewById(R.id.lvgrothId);
        lvgrothId.setVisibility(View.GONE);
        lvsuficentId = (LinearLayout)findViewById(R.id.lvsuficentId);
        lvsuficentId.setVisibility(View.GONE);
        lvhandshpId = (LinearLayout)findViewById(R.id.lvhandshpId);
        lvhandshpId.setVisibility(View.GONE);
        LVelevenrgisId = (LinearLayout)findViewById(R.id.LVelevenrgisId);
        LVelevenrgisId.setVisibility(View.GONE);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        Log.e("CoEawcP", planid);
        Log.e("OTHERAWCACTIVITY", " " + toilet + " " + awcscode + " " + awcsname + " " + dbdistid + " " + dbprojectid + " " + dbsectorid + " " + dbcenterid + " " + projectnamedb + " " + districnamedb + " " + sectorrnamedb + " " + centernamedb + " " + insid + " ");
        kitchen = intent.getStringExtra("kitchen");
        adqutspacepse = intent.getStringExtra("adqutspacepse");
        electric = intent.getStringExtra("electric");
        water = intent.getStringExtra("water");
        foodspace = intent.getStringExtra("foodspace");
        Log.e("OTHERAWCACTIVITY", " " + kitchen + " " + adqutspacepse + " " + electric + " " + water + " " + foodspace + " ");
        //saveID = (Button) findViewById(R.id.saveID);
      //  saveID.setOnClickListener(this);
        helper = new DatabaseHelper(this);
        initToolBar();
        radibuitton();
        helper = new DatabaseHelper(this);
      //  Cursor cursor = helper.getReadableDatabase().
          //      rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
      //  String queryy = "SELECT * FROM insflag WHERE allinspactionid=" + insid;
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
        if (cursor.moveToFirst()) {
            do {
                Idcondition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                conditions = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONOFEQUIPMENTOTHERAWC));
                Log.e("DRINKINGWATER", " " + Idcondition + " " + conditions);
            }
            while (cursor.moveToNext());
        }
        if (conditions.equals("0")) {

        } else {
            editConditionOfEquipment();
        }
        broadcastReceiverecondition = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiverecondition, new IntentFilter(DATA_SAVED_BROADCAST_CONDITION));
        conditionNetwokchecker = new ConditionNetwokchecker();
        registerReceiver(conditionNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        if (isNetworkAvailable()) {

        } else {
            //String query = "SELECT * FROM " + TABLE_CONDITIONOFEQIPMENT + " where " + TABLE_CONDITIONOFEQIPMENTINSIDSYNC + "=" + insid + " and " + TABLE_CONDITIONSTATUS + "=" + CONDITIONSTATUS;
            String query = "SELECT * FROM conditionofeqipment WHERE conditionofeqipmentinsidsync=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cc = db.rawQuery(query, null);
            if (cc.moveToFirst()) {
                do {
                    String conditionofeqipmentid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONOFEQIPMENTID));
                    String conditionofeqipmentinsidsync = cc.getString(cc.getColumnIndex(TABLE_CONDITIONOFEQIPMENTINSIDSYNC));
                    utensilcondtnsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONUTENSILCONDTNSYNC));
                    matconditnsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONMATCONDITNSYNC));
                    elevnregstrsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONELEVNREGSTRSYNC));
                    elevnregstrsyncdb  = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONELEVNREGSTRNOSYNC));
                    babywmachinsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONBABYWMACHINSYNC));
                    conditionbabyweighingmechinusednotoused  = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONELEVNREGSTRBABYWMACHINSYNC));
                    adultwmachinsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONADULTWMACHINSYNC));
                    adualtweighingmachineusednotuseddb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ADUALTWEIGHINGMACHINEUSEDNOTUSED));
                    heightmeasurtypsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONHEIGHTMEASURTYPSYNC));
                    heightmeasuringtype =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_HEIGHTMESURINGTYPE));
                    growthchrtsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONGROWTHCHRTSYNC));
                    properfilledupdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PROPERFILLEDUP));
                    storecontainrsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONSTORECONTAINRSYNC));
                    suficentdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SUFICENT));
                    handwashsoapsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONHANDWASHSOAPSYNC));
                    handwashingsopedb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_HANDWASHINGSOPE));
                    String conditioncurtimesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONCURTIMESYNC));
                    String conditionstatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CONDITIONSTATUS));
                    Log.e("CONDITIONSYNC", " "
                            + conditionofeqipmentid + " "
                            + conditionofeqipmentinsidsync + " "
                            + utensilcondtnsync + " "
                            + matconditnsync + " "
                            + elevnregstrsync + " "
                            + elevnregstrsyncdb + " "

                            + babywmachinsync + " "
                            + conditionbabyweighingmechinusednotoused + " "
                            + adultwmachinsync + " " + adualtweighingmachineusednotuseddb + " "
                            + heightmeasurtypsync + " " + heightmeasuringtype+" "
                            +growthchrtsync+" "
                            +properfilledupdb+" "
                            +storecontainrsync+" "
                            +suficentdb+" "+handwashsoapsync+" "+handwashingsopedb+" "+conditioncurtimesync+" "+conditionstatus+" ");
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
                                update_equipment_condition_inspctn();
                                break;
                            default:
                        }
                        return true;
                    }
                });
    }
    public void showdata(){
        if (utensilcondtnsync.equals("good")){
            goodutensilID.setChecked(true);
        }
        else if (utensilcondtnsync.equals("average")){
            averageutensilID.setChecked(true);
        }
        else if (utensilcondtnsync.equals("poor")){
            poorutensilID.setChecked(true);
        }
        else if (utensilcondtnsync.equals("na")){
            notavalutensilID.setChecked(true);
        }
        else {
            goodutensilID.setChecked(false);
            averageutensilID.setChecked(false);
            poorutensilID.setChecked(false);
            notavalutensilID.setChecked(false);
        }
        if (matconditnsync.equals("good")){
            goodmcID.setChecked(true);
        }
        else if (matconditnsync.equals("average")){
            averagemcID.setChecked(true);
        }
        else if (matconditnsync.equals("poor")){
            poormcID.setChecked(true);
        }
        else if (matconditnsync.equals("na")){
            notavelemcID.setChecked(true);
        }
        else {
            goodmcID.setChecked(false);
            averagemcID.setChecked(false);
            poormcID.setChecked(false);
            notavelemcID.setChecked(false);
        }
        ElevenregisterId.setText(elevnregstrsyncdb);
          if (elevnregstrsyncdb.equals("0")){
              LVelevenrgisId.setVisibility(View.GONE);
        }
        else  if (elevnregstrsyncdb.equals("1")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("2")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("3")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("4")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("4")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("6")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("7")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("8")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("9")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsyncdb.equals("10")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else if (elevnregstrsync.equals("11")){
              LVelevenrgisId.setVisibility(View.VISIBLE);
        }
        else {

        }
        if (elevnregstrsync.equals("mutd")){
            goodelevenID.setChecked(true);
        }
        else if (elevnregstrsync.equals("nm")){
            averageelevenID.setChecked(true);
        }

        else {
            goodelevenID.setChecked(false);
            averageelevenID.setChecked(false);

        }

        if (babywmachinsync.equals("f")){
            babygoodID.setChecked(true);
            lvuseId.setVisibility(View.VISIBLE);
        }
        else if (babywmachinsync.equals("nf")){
            babyaverageID.setChecked(true);
            lvuseId.setVisibility(View.GONE);
        }

        else if (babywmachinsync.equals("na")){
            babynotAvID.setChecked(true);
            lvuseId.setVisibility(View.GONE);
        }
        else {
            babygoodID.setChecked(false);
            babyaverageID.setChecked(false);
            babynotAvID.setChecked(false);
        }

        if (conditionbabyweighingmechinusednotoused.equals("u")){
            useId.setChecked(true);
        }
        else if (conditionbabyweighingmechinusednotoused.equals("nu")){
            nouseId.setChecked(true);
        }
        else {
            useId.setChecked(false);
            nouseId.setChecked(false);
        }

        if (adultwmachinsync.equals("f")){
            goodadlID.setChecked(true);
        }
        else if (adultwmachinsync.equals("nf")){
            averageadlID.setChecked(true);
        }

        else if (adultwmachinsync.equals("na")){
            notaadlID.setChecked(true);
        }
        else {
            goodadlID.setChecked(false);
            averageadlID.setChecked(false);
            notaadlID.setChecked(false);
        }

        if (adualtweighingmachineusednotuseddb.equals("u")){
            useadult.setChecked(true);
        }
        else if (adualtweighingmachineusednotuseddb.equals("nu")){
            nouseadultId.setChecked(true);
        }
        else {
            useadult.setChecked(false);
            nouseadultId.setChecked(false);
        }



        if (heightmeasurtypsync.equals("f")){
            goodhmtID.setChecked(true);
            LvheigthtId.setVisibility(View.VISIBLE);
        }
        else if (heightmeasurtypsync.equals("nu")){
            averagehmtID.setChecked(true);
            LvheigthtId.setVisibility(View.GONE);
        }
        else if (heightmeasurtypsync.equals("na")){
            LvheigthtId.setVisibility(View.GONE);
            nahmtID.setChecked(true);
        }
        else {
            goodhmtID.setChecked(false);
            averagehmtID.setChecked(false);
            nahmtID.setChecked(false);
        }


        if (heightmeasuringtype.equals("u")){
            heightusedId.setChecked(true);
        }
        else if (heightmeasuringtype.equals("nu")){
            hightnotuseId.setChecked(true);
        }
        else {
            heightusedId.setChecked(false);
            hightnotuseId.setChecked(false);
        }
        if (growthchrtsync.equals("a")){
            properiyfilledID.setChecked(true);
            lvgrothId.setVisibility(View.VISIBLE);
        }
        else if (growthchrtsync.equals("na")){
            notproperiyfilledID.setChecked(true);
            lvgrothId.setVisibility(View.GONE);
        }
        else {
            properiyfilledID.setChecked(false);
            notproperiyfilledID.setChecked(false);
        }

        if (properfilledupdb.equals("pfu")){
            grothproperId.setChecked(true);
        }
        else if (properfilledupdb.equals("npfu")){
            notgrothproperId.setChecked(true);
        }
        else {
            grothproperId.setChecked(false);
            notgrothproperId.setChecked(false);
        }

        if (storecontainrsync.equals("a")){
            avablesuffID.setChecked(true);
            lvsuficentId.setVisibility(View.VISIBLE);
        }
        else if (storecontainrsync.equals("na")){
            notavalableID.setChecked(true);
            lvsuficentId.setVisibility(View.GONE);
        }
        else {
            avablesuffID.setChecked(false);
            notavalableID.setChecked(false);
        }

        if (suficentdb.equals("s")){
            saficentId.setChecked(true);
        }
        else if (suficentdb.equals("ns")){
            notsuficientId.setChecked(true);
        }
        else {
            saficentId.setChecked(false);
            notsuficientId.setChecked(false);

        }
        if (handwashsoapsync.equals("a")){
            auoID.setChecked(true);
            lvhandshpId.setVisibility(View.VISIBLE);
        }
        else if (handwashsoapsync.equals("na")){
            nAID.setChecked(true);
            lvhandshpId.setVisibility(View.GONE);
        }
        else {
            auoID.setChecked(false);
            nAID.setChecked(false);
        }

        if (handwashingsopedb.equals("u")){
            gandshopyesId.setChecked(true);
        }
        else if (handwashingsopedb.equals("nu")){
            handshpnoId.setSelected(true);
        }
        else {
            gandshopyesId.setChecked(false);
            handshpnoId.setSelected(false);
        }


    }
    public void initToolBar() {
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
    private void radibuitton(){
        GutensilcondtnID = (RadioGroup)findViewById(R.id.GutensilcondtnID);
        GmatchataiID = (RadioGroup)findViewById(R.id.GmatchataiID);
        GelevenID = (RadioGroup)findViewById(R.id.GelevenID);
        babyWmachineID = (RadioGroup)findViewById(R.id.babyWmachineID);
        GadltID = (RadioGroup)findViewById(R.id.GadltID);
        GmuactapeID =(RadioGroup)findViewById(R.id.GmuactapeID);
        hmtID= (RadioGroup)findViewById(R.id.hmtID);
        GgrowthID= (RadioGroup)findViewById(R.id.GgrowthID);
        GsrotingconID = (RadioGroup)findViewById(R.id.GsrotingconID);
        srotingconditionID =(RadioGroup)findViewById(R.id.srotingconditionID);
        usefunctionId  = (RadioGroup)findViewById(R.id.usefunctionId);
        adultgp = (RadioGroup)findViewById(R.id.adultgp);
        hightgpId = (RadioGroup)findViewById(R.id.hightgpId);
        grothgpId = (RadioGroup)findViewById(R.id.grothgpId);
        suficentgpId = (RadioGroup)findViewById(R.id.suficentgpId);
        gphandshopId = (RadioGroup)findViewById(R.id.gphandshopId);
        goodutensilID = (RadioButton)findViewById(R.id.goodutensilID);
        averageutensilID = (RadioButton)findViewById(R.id.averageutensilID);
        poorutensilID = (RadioButton)findViewById(R.id.poorutensilID);
        notavalutensilID = (RadioButton)findViewById(R.id.notavalutensilID);
        goodmcID = (RadioButton)findViewById(R.id.goodmcID);
        averagemcID =(RadioButton)findViewById(R.id.averagemcID);
        poormcID= (RadioButton)findViewById(R.id.poormcID);
        notavelemcID= (RadioButton)findViewById(R.id.notavelemcID);
        goodelevenID= (RadioButton)findViewById(R.id.goodelevenID);
        averageelevenID= (RadioButton)findViewById(R.id.averageelevenID);
        poorelevenID =(RadioButton)findViewById(R.id.poorelevenID);
        NableevenID= (RadioButton)findViewById(R.id.NableevenID);
        babygoodID =(RadioButton)findViewById(R.id.babygoodID);
        babyaverageID =(RadioButton)findViewById(R.id.babyaverageID);
        babypoorID= (RadioButton)findViewById(R.id.babypoorID);
        babynotAvID= (RadioButton)findViewById(R.id.babynotAvID);
        goodadlID =(RadioButton)findViewById(R.id.goodadlID);
        averageadlID =(RadioButton)findViewById(R.id.averageadlID);
        pooradlID= (RadioButton)findViewById(R.id.pooradlID);
        notaadlID= (RadioButton)findViewById(R.id.notaadlID);
        goodmuacID= (RadioButton)findViewById(R.id.goodmuacID);
        averagemuacID =(RadioButton)findViewById(R.id.averagemuacID);
        pooremuacID= (RadioButton)findViewById(R.id.pooremuacID);
        naemuacID=(RadioButton)findViewById(R.id.naemuacID);
        goodhmtID= (RadioButton)findViewById(R.id.goodhmtID);
        averagehmtID= (RadioButton)findViewById(R.id.averagehmtID);
        poorhmtID= (RadioButton)findViewById(R.id.poorhmtID);
        nahmtID= (RadioButton)findViewById(R.id.nahmtID);
        properiyfilledID =(RadioButton)findViewById(R.id.properiyfilledID);
        improperiyfilledID= (RadioButton)findViewById(R.id.improperiyfilledID);
        notproperiyfilledID= (RadioButton)findViewById(R.id.notproperiyfilledID);
        avablesuffID =(RadioButton)findViewById(R.id.avablesuffID);
        avablenotsuffID=(RadioButton)findViewById(R.id.avablenotsuffID);
        notavalableID =(RadioButton)findViewById(R.id.notavalableID);
        auoID =(RadioButton)findViewById(R.id.auoID);
        nAID =(RadioButton)findViewById(R.id.nAID);
        useId =(RadioButton)findViewById(R.id.useId);
        nouseId =(RadioButton)findViewById(R.id.nouseId);
        useadult =(RadioButton)findViewById(R.id.useadult);
        nouseadultId = (RadioButton)findViewById(R.id.nouseadultId);
        heightusedId = (RadioButton)findViewById(R.id.heightusedId);
        hightnotuseId = (RadioButton)findViewById(R.id.hightnotuseId);
        grothproperId = (RadioButton)findViewById(R.id.grothproperId);
        notgrothproperId  = (RadioButton)findViewById(R.id.notgrothproperId);
        saficentId  = (RadioButton)findViewById(R.id.saficentId);
        notsuficientId  = (RadioButton)findViewById(R.id.notsuficientId);
        gandshopyesId = (RadioButton)findViewById(R.id.gandshopyesId);
        handshpnoId  = (RadioButton)findViewById(R.id.handshpnoId);
        GutensilcondtnID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.goodutensilID){
                    utensilcondtn = "good";
                }
                else if (checkedId== R.id.averageutensilID){
                    utensilcondtn = "average";
                }
                else if (checkedId== R.id.poorutensilID){

                    utensilcondtn = "poor";
                }
                else if (checkedId== R.id.notavalutensilID){
                    utensilcondtn = "na";
                }

            }
        });
        GmatchataiID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.goodmcID){
                    matconditn = "good";
                }
                else if (checkedId== R.id.averagemcID){
                    matconditn = "average";
                }
                else if (checkedId== R.id.poormcID){
                    matconditn = "poor";
                }
                else if (checkedId== R.id.notavelemcID){
                    matconditn = "na";
                }
            }
        });
        GelevenID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.goodelevenID){
                    ////change/////
                    elevnregstr = "mutd";
                }
                else if (checkedId== R.id.averageelevenID){
                    elevnregstr = "nm";
                }
//                else if (checkedId==R.id.poorelevenID){
//                    elevnregstr = "poor";
//                }
//                else if (checkedId==R.id.NableevenID){
//                    elevnregstr = "na";
//
//                }

            }
        });
        babyWmachineID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.babygoodID){
                    babywmachin = "f";
                    lvuseId.setVisibility(View.VISIBLE);
                }
                else if (checkedId== R.id.babyaverageID){
                    babywmachin = "nf";
                    lvuseId.setVisibility(View.GONE);
                    if (useId.isChecked()){
                        useId.setChecked(false);
                        useId.setSelected(false);
                    }
                    if (nouseId.isChecked()){
                        nouseId.setChecked(false);
                        nouseId.setSelected(false);
                    }
                }
//                else if (checkedId==R.id.babypoorID){
//                    babywmachin = "poor";
//                    lvuseId.setVisibility(View.GONE);
//                }
                else if (checkedId== R.id.babynotAvID){
                    babywmachin = "na";
                    lvuseId.setVisibility(View.GONE);
                    if (useId.isChecked()){
                        useId.setChecked(false);
                        useId.setSelected(false);
                    }
                    if (nouseId.isChecked()){
                        nouseId.setChecked(false);
                        nouseId.setSelected(false);
                    }
                }

            }
        });
        usefunctionId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.useId){
                   conditionbabyweighingmechin_used_notoused = "u";
                }
                else if (checkedId== R.id.nouseId){
                    //handwashsoap = "n";
                    conditionbabyweighingmechin_used_notoused = "nu";
                }

            }
        });
        GadltID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.goodadlID){
                    adultwmachin = "f";
                    lvadultId.setVisibility(View.VISIBLE);
                }
                else if (checkedId== R.id.averageadlID){
                    adultwmachin = "nf";
                    lvadultId.setVisibility(View.GONE);
                    if (useadult.isChecked()){
                        useadult.setChecked(false);
                        useadult.setSelected(false);
                    }
                    if (nouseadultId.isChecked()){
                        nouseadultId.setChecked(false);
                        nouseadultId.setSelected(false);
                    }
                }
//                else if (checkedId==R.id.pooradlID){
//                    adultwmachin = "poor";
//                    lvadultId.setVisibility(View.GONE);
//                }
                else if (checkedId== R.id.notaadlID){
                    adultwmachin = "na";
                    lvadultId.setVisibility(View.GONE);
                    if (useadult.isChecked()){
                        useadult.setChecked(false);
                        useadult.setSelected(false);
                    }
                    if (nouseadultId.isChecked()){
                        nouseadultId.setChecked(false);
                        nouseadultId.setSelected(false);
                    }
                }

            }
        });
        adultgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.useadult){
                   adualtweighingmachine_used_notused = "u";
                }
                else if (checkedId== R.id.nouseadultId){
                    adualtweighingmachine_used_notused = "nu";
                }

            }
        });

        GmuactapeID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.goodmuacID){
                    muactape = "good";
                }
                else if (checkedId== R.id.averagemuacID){
                    muactape = "average";
                }
                else if (checkedId== R.id.pooremuacID){
                    muactape = "poor";
                }
                else if (checkedId== R.id.naemuacID){
                    muactape = "na";
                }

            }
        });
        hmtID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.goodhmtID){
                    heightmeasurtyp = "f";
                    LvheigthtId.setVisibility(View.VISIBLE);
                }
                else if (checkedId== R.id.averagehmtID){
                    heightmeasurtyp = "nf";
                    LvheigthtId.setVisibility(View.GONE);
                    if (heightusedId.isChecked()){
                        heightusedId.setChecked(false);
                        heightusedId.setSelected(false);
                    }
                    if (heightusedId.isChecked()){
                        heightusedId.setChecked(false);
                        heightusedId.setSelected(false);
                    }
                }
//                else if (checkedId==R.id.poorhmtID){
//                    heightmeasurtyp = "poor";
//                    LvheigthtId.setVisibility(View.GONE);
//                }
                else if (checkedId== R.id.nahmtID){
                    heightmeasurtyp = "na";
                    LvheigthtId.setVisibility(View.GONE);
                    if (hightnotuseId.isChecked()){
                        hightnotuseId.setChecked(false);
                        hightnotuseId.setSelected(false);
                    }
                    if (hightnotuseId.isChecked()){
                        hightnotuseId.setChecked(false);
                        hightnotuseId.setSelected(false);
                    }
                }

            }
        });
        hightgpId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.heightusedId){
                   height_measuring_type = "u";
                }
                else if (checkedId== R.id.hightnotuseId){
                    height_measuring_type = "nu";
                }

            }
        });
        GgrowthID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.properiyfilledID){
                    growthchrt = "a";
                    lvgrothId.setVisibility(View.VISIBLE);
                }
//                else if (checkedId==R.id.improperiyfilledID){
//                    growthchrt = "ipf";
//                    lvgrothId.setVisibility(View.GONE);
//                }
                else if (checkedId== R.id.notproperiyfilledID){
                    growthchrt = "na";
                    lvgrothId.setVisibility(View.GONE);
                    if (grothproperId.isChecked()){
                        grothproperId.setChecked(false);
                        grothproperId.setSelected(false);
                    }
                    if (notgrothproperId.isChecked()){
                        notgrothproperId.setChecked(false);
                        notgrothproperId.setSelected(false);
                    }
                }
            }
        });
        grothgpId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.grothproperId){
                 proper_filled_up = "pfu";
                }
                else if (checkedId== R.id.notgrothproperId){
                    proper_filled_up = "npfu";
                }

            }
        });
        GsrotingconID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.avablesuffID){
                    storecontainr = "a";
                    lvsuficentId.setVisibility(View.VISIBLE);
                }
//                else if (checkedId==R.id.avablenotsuffID){
//                    storecontainr = "ans";
//                    lvsuficentId.setVisibility(View.GONE);
//                }
                else if (checkedId== R.id.notavalableID){
                    storecontainr = "na";
                    lvsuficentId.setVisibility(View.GONE);
                    if (saficentId.isChecked()){
                        saficentId.setChecked(false);
                        saficentId.setSelected(false);
                    }
                    if (notsuficientId.isChecked()){
                        notsuficientId.setChecked(false);
                        notsuficientId.setSelected(false);
                    }
                }
            }
        });
        suficentgpId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.saficentId){
                   suficent = "s";
                }
                else if (checkedId== R.id.notsuficientId){
                    suficent = "ns";
                }

            }
        });
        srotingconditionID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.auoID){
                    handwashsoap = "a";
                    lvhandshpId.setVisibility(View.VISIBLE);
                }
                else if (checkedId== R.id.nAID){
                    handwashsoap = "na";
                   // hand_washing_sope ="";
                    lvhandshpId.setVisibility(View.GONE);
                    if (gandshopyesId.isChecked()){
                        gandshopyesId.setChecked(false);
                        gandshopyesId.setSelected(false);
                    }
                    if (handshpnoId.isChecked()){
                        handshpnoId.setChecked(false);
                        handshpnoId.setSelected(false);
                    }
                }
            }
        });
        gphandshopId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.gandshopyesId){
                    hand_washing_sope = "u";
                    Log.e("HAND", hand_washing_sope);
                }
                else if (checkedId== R.id.handshpnoId){
                    hand_washing_sope = "nu";
                    Log.e("HAND", hand_washing_sope);
                }

            }
        });

    }
    private void editConditionOfEquipment(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CONDITIONEQUIPMENTOTHERSAWCEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("CONDITIONSEDIT"," "+response);
                        //108026127122019  good good mutd  1 fu f u    f u a pfu a s a u 17:46
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray equipmentdtl = object.getJSONArray("equipment_dtl");
                            for (int i= 0; i<equipmentdtl.length();i++){
                                JSONObject jsonObject = equipmentdtl.getJSONObject(i);
                                Eutensilcondtn = jsonObject.getString("utensil_condtn");
                                Ematconditn = jsonObject.getString("mat_conditn");
                                Eelevnregstr = jsonObject.getString("elevn_regstr");
                                elevnregstrno = jsonObject.getString("elevn_regstr_no");
                                Ebabywmachin = jsonObject.getString("baby_wmachin");
                                babywmachinuse = jsonObject.getString("baby_wmachin_use");
                                Eadultwmachin = jsonObject.getString("adult_wmachin");
                                adultwmachinuse = jsonObject.getString("adult_wmachin_use");
                                //String heightmeasurtyp = jsonObject.getString("height_measur_typ");
                                Eheightmeasurtyp = jsonObject.getString("height_measur_typ");
                                heightmeasurtypuse = jsonObject.getString("height_measur_typ_use");

                                //Emuactape = jsonObject.getString("muac_tape");

                                Egrowthchrt = jsonObject.getString("growth_chrt");
                                Estorecontainr = jsonObject.getString("store_containr");
                                suffistorecontainr = jsonObject.getString("suffi_store_containr");
                                Ehandwashsoap = jsonObject.getString("hand_wash_soap");
                                handwashsoapuse = jsonObject.getString("hand_wash_soap_use");
                                growthchrtfillup = jsonObject.getString("growth_chrt_fillup");
                                Log.e("CONDITIONSEDIT"," "+Eutensilcondtn+" "+Ematconditn+" "+Eelevnregstr+" "
                                        +Ebabywmachin+" "+" "+babywmachinuse+" "+Eadultwmachin+" "+adultwmachinuse+" "+" "+" "+" "+Eheightmeasurtyp+" "+heightmeasurtypuse+" "
                                        +Egrowthchrt+" "+Estorecontainr+" "+suffistorecontainr+" "+Ehandwashsoap+" "+handwashsoapuse);
                                if (Eutensilcondtn.equals("good")){
                                    goodutensilID.setChecked(true);
                                }
                                else if (Eutensilcondtn.equals("average")){
                                    averageutensilID.setChecked(true);
                                }
                                else if (Eutensilcondtn.equals("poor")){
                                    poorutensilID.setChecked(true);
                                }
                                else if (Eutensilcondtn.equals("na")){
                                    notavalutensilID.setChecked(true);
                                }
                                else {
                                    goodutensilID.setChecked(false);
                                    averageutensilID.setChecked(false);
                                    poorutensilID.setChecked(false);
                                    notavalutensilID.setChecked(false);
                                }

                                if (Ematconditn.equals("good")){
                                    goodmcID.setChecked(true);
                                }
                                else if (Ematconditn.equals("average")){
                                    averagemcID.setChecked(true);
                                }
                                else if (Ematconditn.equals("poor")){
                                    poormcID.setChecked(true);
                                }
                                else if (Ematconditn.equals("na")){
                                    notavelemcID.setChecked(true);
                                }
                                else {
                                    goodmcID.setChecked(false);
                                    averagemcID.setChecked(false);
                                    poormcID.setChecked(false);
                                    notavelemcID.setChecked(false);
                                }
                                ElevenregisterId.setText(elevnregstrno);
                                if (elevnregstrno.equals("1")){
                                    LVelevenrgisId.setVisibility(View.VISIBLE);
                                }
                                else {
                                    LVelevenrgisId.setVisibility(View.GONE);
                                }

                                if (Eelevnregstr.equals("mutd")){
                                    goodelevenID.setChecked(true);
                                }
                                else if (Eelevnregstr.equals("nm")){
                                    averageelevenID.setChecked(true);
                                }
                                else {
                                    goodelevenID.setChecked(false);
                                    averageelevenID.setChecked(false);
                                   // poorelevenID.setChecked(false);
                                   // NableevenID.setChecked(false);
                                }

                                if (Ebabywmachin.equals("f")){
                                    babygoodID.setChecked(true);
                                    lvuseId.setVisibility(View.VISIBLE);
                                }
                                else if (Ebabywmachin.equals("nf")){
                                    babyaverageID.setChecked(true);
                                }
//                                else if (Ebabywmachin.equals("poor")){
//                                    babypoorID.setChecked(true);
//                                }
                                else if (Ebabywmachin.equals("na")){
                                    babynotAvID.setChecked(true);
                                }
                                else {
                                    babygoodID.setChecked(false);
                                    babyaverageID.setChecked(false);
                                    //babypoorID.setChecked(false);
                                    babynotAvID.setChecked(false);
                                }

                                if (babywmachinuse.equals("u")){
                                    useId.setChecked(true);
                                }
                                else if (babywmachinuse.equals("nu")){
                                    nouseId.setChecked(true);
                                }
                                else {
                                    useId.setChecked(false);
                                    nouseId.setChecked(false);
                                }



                                if (Eadultwmachin.equals("f")){
                                    goodadlID.setChecked(true);
                                    lvadultId.setVisibility(View.VISIBLE);
                                }
                                else if (Eadultwmachin.equals("nf")){
                                    averageadlID.setChecked(true);
                                    lvadultId.setVisibility(View.GONE);
                                }
//                                else if (Eadultwmachin.equals("poor")){
//                                    pooradlID.setChecked(true);
//                                }
                                else if (Eadultwmachin.equals("na")){
                                    notaadlID.setChecked(true);
                                    lvadultId.setVisibility(View.GONE);
                                }
                                else {
                                    goodadlID.setChecked(false);
                                    averageadlID.setChecked(false);
                                   // pooradlID.setChecked(false);
                                    notaadlID.setChecked(false);
                                }

                                if (heightmeasurtypuse.equals("u")){
                                    useadult.setChecked(true);
                                }
                                else if (heightmeasurtypuse.equals("nu")){
                                    nouseadultId.setChecked(true);
                                }
                                else {
                                    useadult.setChecked(false);
                                    nouseadultId.setChecked(false);
                                }

//                                if (Emuactape.equals("good")){
//                                    goodmuacID.setChecked(true);
//                                }
//                                else if (Emuactape.equals("average")){
//                                    averagemuacID.setChecked(true);
//                                }
//                                else if (Emuactape.equals("poor")){
//                                    pooremuacID.setChecked(true);
//                                }
//                                else if (Emuactape.equals("na")){
//                                    naemuacID.setChecked(true);
//                                }
//                                else {
//                                    goodmuacID.setChecked(false);
//                                    averagemuacID.setChecked(false);
//                                    pooremuacID.setChecked(false);
//                                    naemuacID.setChecked(false);
//                                }

                                if (Eheightmeasurtyp.equals("f")){
                                    goodhmtID.setChecked(true);
                                    LvheigthtId.setVisibility(View.VISIBLE);
                                }
                                else if (Eheightmeasurtyp.equals("nf")){
                                    averagehmtID.setChecked(true);
                                    LvheigthtId.setVisibility(View.GONE);
                                }
//                                else if (Eheightmeasurtyp.equals("poor")){
//                                    poorhmtID.setChecked(true);
//                                }
                                else if (Eheightmeasurtyp.equals("na")){
                                    nahmtID.setChecked(true);
                                    LvheigthtId.setVisibility(View.GONE);
                                }
                                else {
                                    goodhmtID.setChecked(false);
                                    averagehmtID.setChecked(false);
                                   // poorhmtID.setChecked(false);
                                    nahmtID.setChecked(false);
                                }

                                if (heightmeasurtypuse.equals("u")){
                                    heightusedId.setChecked(true);
                                }
                                else if (heightmeasurtypuse.equals("nu")){
                                    hightnotuseId.setChecked(true);
                                }
                                else {
                                    heightusedId.setChecked(false);
                                    hightnotuseId.setChecked(false);
                                }



                                if (Egrowthchrt.equals("a")){
                                    properiyfilledID.setChecked(true);
                                    lvgrothId.setVisibility(View.VISIBLE);
                                }
//                                else  if (Egrowthchrt.equals("ipf")){
//                                    improperiyfilledID.setChecked(true);
//                                }
                                else if (Egrowthchrt.equals("na")){
                                    notproperiyfilledID.setChecked(true);
                                    lvgrothId.setVisibility(View.GONE);
                                }
                                else {
                                    properiyfilledID.setChecked(false);
                                   // improperiyfilledID.setChecked(false);
                                    notproperiyfilledID.setChecked(false);
                                }

                                if (growthchrtfillup.equals("pfu")){
                                    grothproperId.setChecked(true);
                                }
                                else if (growthchrtfillup.equals("npfu")){
                                    notgrothproperId.setChecked(true);
                                }
                                else {
                                    grothproperId.setChecked(false);
                                    notgrothproperId.setChecked(false);
                                }



                                if (Estorecontainr.equals("a")){
                                    avablesuffID.setChecked(true);
                                    lvsuficentId.setVisibility(View.VISIBLE);
                                }
                                else if (Estorecontainr.equals("na")){
                                    avablenotsuffID.setChecked(true);
                                    lvsuficentId.setVisibility(View.GONE);
                                }
//                                else if (Estorecontainr.equals("na")){
//                                    notavalableID.setChecked(true);
//                                }
                                else {
                                    avablesuffID.setChecked(false);
                                //    avablenotsuffID.setChecked(false);
                                    notavalableID.setChecked(false);
                                }
                                if (suffistorecontainr.equals("s")){
                                    saficentId.setChecked(true);
                                }
                                else if (suffistorecontainr.equals("ns")){
                                    notsuficientId.setChecked(true);
                                }
                                else {
                                    saficentId.setChecked(false);
                                    notsuficientId.setChecked(false);
                                }

                                if (Ehandwashsoap.equals("a")){
                                    auoID.setChecked(true);
                                    lvhandshpId.setVisibility(View.VISIBLE);
                                }
                                else if (Ehandwashsoap.equals("na")){
                                    nAID.setChecked(true);
                                    lvhandshpId.setVisibility(View.GONE);
                                }
                                else {
                                    auoID.setChecked(false);
                                    nAID.setChecked(false);

                                }

                                if (handwashsoapuse.equals("u")){
                                    gandshopyesId.setChecked(true);
                                }
                                else if (handwashsoapuse.equals("nu")){
                                    handshpnoId.setChecked(true);
                                }
                                else {
                                    gandshopyesId.setChecked(false);
                                    handshpnoId.setChecked(false);
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
    private void update_equipment_condition_inspctn(){
        if (goodutensilID.isChecked() || averageutensilID.isChecked() || poorutensilID.isChecked() || notavalutensilID.isChecked()){
            if (goodmcID.isChecked() || averagemcID.isChecked() || poormcID.isChecked() || notavelemcID.isChecked()){
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
                ElevenregisterId.setFilters(new InputFilter[] { filter });
                elavenregister = ElevenregisterId.getText().toString().trim();
                if (TextUtils.isEmpty(elavenregister)) {
                    ElevenregisterId.setError("Enter Number of Eleven register");
                    ElevenregisterId.requestFocus();
                    return;
                }
//                ElevenregisterId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        elavenregister = ElevenregisterId.getText().toString();
//                    }
//                });
//                ElevenregisterId.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        if (actionId == EditorInfo.IME_ACTION_DONE) {
//                            elavenregister = ElevenregisterId.getText().toString();
//                            return true;
//                        }
//                        return false;
//                    }
//                });
                elavenregister = ElevenregisterId.getText().toString();

                if (elavenregister.equals("0")){
                    LVelevenrgisId.setVisibility(View.GONE);
                        if (goodelevenID.isChecked()){
                            goodelevenID.setChecked(false);
                            goodelevenID.setSelected(false);
                        }
                        if (averageelevenID.isChecked()){
                            averageelevenID.setChecked(false);
                            averageelevenID.setSelected(false);
                        }
                        if (babygoodID.isChecked() || babyaverageID.isChecked() || babynotAvID.isChecked()){
                         if (goodadlID.isChecked() || averageadlID.isChecked() || notaadlID.isChecked()){
//                             if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//
//                             }
//                             else {
//                                 Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT MUAC TAPE", Toast.LENGTH_SHORT).show();
//
//                             }

                             if (goodhmtID.isChecked() || averagehmtID.isChecked() || nahmtID.isChecked()){
                                 if (properiyfilledID.isChecked() || notproperiyfilledID.isChecked()){
                                     if (avablesuffID.isChecked() || notavalableID.isChecked()){
                                         if (auoID.isChecked() || nAID.isChecked()){
                                             /////////////////////SUB
                                             if (auoID.isChecked()){
                                                 if (gandshopyesId.isChecked() || handshpnoId.isChecked()) {
                                                     String val = "r1";
                                                     save_date(val);
                                                 }
                                             }
                                             if (nAID.isChecked()){
                                                 String val = "r2";
                                                 save_date(val);
                                             }

                                             /////////////////////SUB///////

                                         }
                                         else {
                                             Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
                                             String title = "Message Box";
                                             String msg = "SELECT HAND WASHING SOAP";
                                             createDialog(title,msg);
                                         }
                                     }
                                     else {
                                         Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
                                         String title = "Message Box";
                                         String msg = "SELECT CONTAINERS FOR STORING";
                                         createDialog(title,msg);
                                     }

                                 }
                                 else {
                                     Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART", Toast.LENGTH_SHORT).show();
                                     String title = "Message Box";
                                     String msg = "SELECT GROTH CHART";
                                     createDialog(title,msg);
                                 }

                             }
                             else {
                                 Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HEIGHT MEASURING TAPE", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT HEIGHT MEASURING TAPE";
                                 createDialog(title,msg);

                             }
                         }
                         else {
                             Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADULT WEIGHING MACHINE", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT ADULT WEIGHING MACHINE";
                             createDialog(title,msg);
                         }
                        }
                        else {
                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONDITION OF BABY WEIGHING MACHINE", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT CONDITION OF BABY WEIGHING MACHINE";
                            createDialog(title,msg);

                        }

                    if (babygoodID.isChecked()){
                        if (useId.isChecked() || nouseId.isChecked()){
                            if (goodadlID.isChecked()){
                                if (useadult.isChecked() || nouseadultId.isChecked()){
                                    if (goodhmtID.isChecked()){
                                        if (heightusedId.isChecked() || hightnotuseId.isChecked()){
                                            if (properiyfilledID.isChecked()){
                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()){
                                                    if (avablesuffID.isChecked()){
                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
                                                            if (auoID.isChecked()){
                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){


                                                                }
                                                                else {
                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                                                    String title = "Message Box";
                                                                    String msg = "SELECT HAND WASHING SOAP USED OR NOT USED";
                                                                    createDialog(title,msg);
                                                                }
                                                            }


                                                        }
                                                        else {
                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FORSTORINGSUFFICIENT OR NOT SUFFICIENT", Toast.LENGTH_SHORT).show();
                                                            String title = "Message Box";
                                                            String msg = "SELECT CONTAINERS FORSTORINGSUFFICIENT OR NOT SUFFICIENT";
                                                            createDialog(title,msg);
                                                        }

                                                    }

                                                }
                                                else {
                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY OR NOT PROPERLY", Toast.LENGTH_SHORT).show();
                                                    String title = "Message Box";
                                                    String msg = "SELECT GROTH CHART PROPERLY OR NOT PROPERLY";
                                                    createDialog(title,msg);
                                                }
                                            }

                                        }
                                        else {
                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HEIGHT MEASURING TAPE USE OR NO USE", Toast.LENGTH_SHORT).show();
                                            String title = "Message Box";
                                            String msg = "SELECT HEIGHT MEASURING TAPE USE OR NO USE";
                                            createDialog(title,msg);
                                        }
                                    }

                                }
                                else {
                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADULT WEIGHING USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT ADULT WEIGHING USED OR NOT USED";
                                    createDialog(title,msg);
                                }
                            }

                        }
                        else {
                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONDITION OF BABAY WEIGHING MACHINE USED OR NOT USED", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT CONDITION OF BABAY WEIGHING MACHINE USED OR NOT USED";
                            createDialog(title,msg);
                        }
                    }

                }
                else if (elavenregister.equals("1") || elavenregister.equals("2")
                        ||elavenregister.equals("3")
                        || elavenregister.equals("4")
                        || elavenregister.equals("5")
                        || elavenregister.equals("6")
                        || elavenregister.equals("7")
                        || elavenregister.equals("8")
                        || elavenregister.equals("9")
                        || elavenregister.equals("10")
                        || elavenregister.equals("11")
                ){
                    LVelevenrgisId.setVisibility(View.VISIBLE);
                    if (goodelevenID.isChecked() || averageelevenID.isChecked()){

//                        if (babygoodID.isChecked() || babyaverageID.isChecked()  || babynotAvID.isChecked()){
//                            if (babygoodID.isChecked()){
//                                if (useId.isChecked() || nouseId.isChecked()){
//                                    if (goodadlID.isChecked() || averageadlID.isChecked() ||  notaadlID.isChecked()){
//                                        if (goodadlID.isChecked()) {
//                                            if (useadult.isChecked() || nouseadultId.isChecked()) {
//                                                //////////////////////////////////////////////////////////////////////////
//                                                if (goodadlID.isChecked() || averageadlID.isChecked() ||  notaadlID.isChecked()){
//                                                    if (goodadlID.isChecked()) {
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()) {
//                                                            if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//                                                                if (goodhmtID.isChecked() || averagehmtID.isChecked() || nahmtID.isChecked()){
//                                                                    if(goodhmtID.isChecked()){
//                                                                        if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT GROWTH CHART",Toast.LENGTH_SHORT).show();
//                                                                            if (properiyfilledID.isChecked()){
//                                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONTAINERS FOR STORING",Toast.LENGTH_SHORT).show();
//                                                                                    //Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
//                                                                                    if (avablesuffID.isChecked()){
//                                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT 1HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                                                            if (auoID.isChecked()){
//                                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                                    save_date();
//
//                                                                                                }
//                                                                                                else {
//                                                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                                                }
//                                                                                            }
//                                                                                        }
//                                                                                        else {
//                                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING SUFFICIENT NOT-SUFFICIENT", Toast.LENGTH_SHORT).show();
//
//                                                                                        }
//                                                                                    }
//
//
//                                                                                } else {
//                                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY FILED UP NOT PROPERLY FILED UP", Toast.LENGTH_SHORT).show();
//
//                                                                                }
//                                                                            }
//                                                                            if (nAID.isChecked()){
//                                                                                save_date();
//
//                                                                                    }
//
//
//
//                                                                        }
//                                                                        else {
//                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE USED OR NOT USED",Toast.LENGTH_SHORT).show();
//
//                                                                        }
//                                                                    }
//
//
//
//
//
//
//                                                                }
//                                                                else {
//                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE",Toast.LENGTH_SHORT).show();
//
//                                                                }
//
//
//                                                            }
//                                                            else {
//                                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT MUAC TAPE",Toast.LENGTH_SHORT).show();
//
//                                                            }
//
//
//                                                        } else {
//                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADLT. USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                        }
//                                                    }
//
//
//
//
//                                                }
//
//                                                else {
//                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT 1ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                                                }
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                            } else {
//                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADLT. USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                            }
//                                        }
//
//
//                                    }
//                                    else {
//                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                else {
//                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONDITION OF BABAY WEIGHING MACHINE USED OR NOT USED",Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//
//                            ////////check condition baby weihing mechine not function  not avable
//                            if (babyaverageID.isChecked() || babynotAvID.isChecked()){
//                                if (useId.isChecked()){
//                                    useId.setChecked(false);
//                                    useId.setSelected(false);
//                                }
//                                if (nouseId.isChecked()){
//                                    nouseId.setChecked(false);
//                                    nouseId.setSelected(false);
//                                }
//                                //Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                                if (goodadlID.isChecked() || averageadlID.isChecked() ||  notaadlID.isChecked()){
//                                    if (goodadlID.isChecked()) {
//                                        if (useadult.isChecked() || nouseadultId.isChecked()) {
//
//
//
//                                        } else {
//                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADLT. USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                        }
//                                    }
//                                    if (averageadlID.isChecked() ||  notaadlID.isChecked()){
//                                        if (useadult.isChecked()){
//                                            useadult.setChecked(false);
//                                            useadult.setSelected(false);
//                                        }
//                                        if (nouseadultId.isChecked()){
//                                            nouseadultId.setChecked(false);
//                                            nouseadultId.setSelected(false);
//                                        }
//                                            if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//                                            if (goodhmtID.isChecked() || averagehmtID.isChecked() || nahmtID.isChecked()){
//                                                if(goodhmtID.isChecked()){
//                                                    if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                       /////////////////////////grothchart/////////
//                                                        if (properiyfilledID.isChecked() || notproperiyfilledID.isChecked()){
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                      if (avablesuffID.isChecked() || notavalableID.isChecked()){
//                                                                          if (avablesuffID.isChecked()){
//                                                                              if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                                  Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                                                  if (auoID.isChecked() || nAID.isChecked()){
//                                                                                      if (auoID.isChecked()){
//                                                                                          if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                              save_date();
//                                                                                          }
//                                                                                          else {
//                                                                                              Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                                          }
//                                                                                      }
//
//                                                                                      if (nAID.isChecked()){
//                                                                                          if (gandshopyesId.isChecked()){
//                                                                                              gandshopyesId.setChecked(false);
//                                                                                              gandshopyesId.setSelected(false);
//                                                                                          }
//                                                                                          if (handshpnoId.isChecked()){
//                                                                                              handshpnoId.setChecked(false);
//                                                                                              handshpnoId.setSelected(false);
//                                                                                          }
//                                                                                          save_date();
//                                                                                      }
//                                                                                  }
//                                                                                  else {
//
//                                                                                  }
//                                                                              }
//                                                                              else {
//                                                                                  Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING SUFFICIENT NOT-SUFFICIENT", Toast.LENGTH_SHORT).show();
//
//                                                                              }
//                                                                          }
//                                                                          if (notavalableID.isChecked()){
//                                                                              Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                                              if (saficentId.isChecked()){
//                                                                                  saficentId.setChecked(false);
//                                                                                  saficentId.setSelected(false);
//                                                                              }
//                                                                              if (notsuficientId.isChecked()){
//                                                                                  notsuficientId.setChecked(false);
//                                                                                  notsuficientId.setSelected(false);
//                                                                              }
//                                                                              if (auoID.isChecked()){
//                                                                                  if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                      save_date();
//                                                                                  }
//                                                                                  else {
//                                                                                      Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                                  }
//                                                                              }
//
//                                                                              if (nAID.isChecked()){
//                                                                                  if (gandshopyesId.isChecked()){
//                                                                                      gandshopyesId.setChecked(false);
//                                                                                      gandshopyesId.setSelected(false);
//                                                                                  }
//                                                                                  if (handshpnoId.isChecked()){
//                                                                                      handshpnoId.setChecked(false);
//                                                                                      handshpnoId.setSelected(false);
//                                                                                  }
//                                                                                  save_date();
//                                                                              }
//                                                                          }
//                                                                    }
//                                                                    else {
//                                                                          Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONTAINERS FOR STORING",Toast.LENGTH_SHORT).show();
//
//                                                                      }
//                                                                } else {
//                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY FILED UP NOT PROPERLY FILED UP", Toast.LENGTH_SHORT).show();
//
//                                                                }
//                                                            }
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
//                                                                if (grothproperId.isChecked()){
//                                                                    grothproperId.setChecked(false);
//                                                                    grothproperId.setSelected(false);
//                                                                }
//                                                                if (hightnotuseId.isChecked()){
//                                                                    hightnotuseId.setChecked(false);
//                                                                    hightnotuseId.setSelected(false);
//                                                                }
//
//                                                                if (avablesuffID.isChecked()){
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//
//                                                                    }
//                                                                    else {
//                                                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING SUFFICIENT NOT-SUFFICIENT", Toast.LENGTH_SHORT).show();
//
//                                                                    }
//                                                                }
//                                                                if (notavalableID.isChecked()){
//                                                                    //Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                                    if (saficentId.isChecked()){
//                                                                        saficentId.setChecked(false);
//                                                                        saficentId.setSelected(false);
//                                                                    }
//                                                                    if (notsuficientId.isChecked()){
//                                                                        notsuficientId.setChecked(false);
//                                                                        notsuficientId.setSelected(false);
//                                                                    }
//                                                                    if (auoID.isChecked()){
//                                                                        if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                            save_date();
//                                                                        }
//                                                                        else {
//                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                        }
//                                                                    }
//
//                                                                    if (nAID.isChecked()){
//                                                                        if (gandshopyesId.isChecked()){
//                                                                            gandshopyesId.setChecked(false);
//                                                                            gandshopyesId.setSelected(false);
//                                                                        }
//                                                                        if (handshpnoId.isChecked()){
//                                                                            handshpnoId.setChecked(false);
//                                                                            handshpnoId.setSelected(false);
//                                                                        }
//                                                                        save_date();
//                                                                    }
//                                                                }
//                                                            }
//                                                        }
//                                                        else {
//                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT GROWTH CHART",Toast.LENGTH_SHORT).show();
//
//                                                        }
//
//                                                    }
//                                                    else {
//                                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE USED OR NOT USED",Toast.LENGTH_SHORT).show();
//
//                                                    }
//                                                }
//                                                if (averagehmtID.isChecked() || nahmtID.isChecked()){
//                                                    if (heightusedId.isChecked()){
//                                                        heightusedId.setChecked(false);
//                                                        heightusedId.setSelected(false);
//                                                    }
//                                                    if (hightnotuseId.isChecked()){
//                                                        hightnotuseId.setChecked(false);
//                                                        hightnotuseId.setSelected(false);
//                                                    }
//                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT GROWTH CHART",Toast.LENGTH_SHORT).show();
//                                                    if (properiyfilledID.isChecked()){
//                                                            if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONTAINERS FOR STORING",Toast.LENGTH_SHORT).show();
//
//                                                            } else {
//                                                              Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY FILED UP NOT PROPERLY FILED UP", Toast.LENGTH_SHORT).show();
//
//                                                          }
//                                                      }
//
//                                                      if (notproperiyfilledID.isChecked()){
//                                                          Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
//                                                          if (grothproperId.isChecked()){
//                                                              grothproperId.setChecked(false);
//                                                              grothproperId.setSelected(false);
//                                                          }
//                                                          if (hightnotuseId.isChecked()){
//                                                              hightnotuseId.setChecked(false);
//                                                              hightnotuseId.setSelected(false);
//                                                          }
//
//                                                          if (avablesuffID.isChecked()){
//                                                              if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                  Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//
//                                                              }
//                                                              else {
//                                                                  Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING SUFFICIENT NOT-SUFFICIENT", Toast.LENGTH_SHORT).show();
//
//                                                              }
//                                                          }
//                                                          if (notavalableID.isChecked()){
//                                                              //Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                              if (saficentId.isChecked()){
//                                                                  saficentId.setChecked(false);
//                                                                  saficentId.setSelected(false);
//                                                              }
//                                                              if (notsuficientId.isChecked()){
//                                                                  notsuficientId.setChecked(false);
//                                                                  notsuficientId.setSelected(false);
//                                                              }
//                                                              if (auoID.isChecked()){
//                                                                  if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                      save_date();
//                                                                  }
//                                                                  else {
//                                                                      Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                  }
//                                                              }
//
//                                                              if (nAID.isChecked()){
//                                                                  if (gandshopyesId.isChecked()){
//                                                                      gandshopyesId.setChecked(false);
//                                                                      gandshopyesId.setSelected(false);
//                                                                  }
//                                                                  if (handshpnoId.isChecked()){
//                                                                      handshpnoId.setChecked(false);
//                                                                      handshpnoId.setSelected(false);
//                                                                  }
//                                                                  save_date();
//                                                              }
//                                                          }
//                                                      }
//
//                                                }
//
//
//                                            }
//                                            else {
//                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE",Toast.LENGTH_SHORT).show();
//
//                                            }
//
//
//                                        }
//                                        else {
//                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT MUAC TAPE",Toast.LENGTH_SHORT).show();
//
//                                            }
//
//                                    }
//                                }
//
//                                else {
//                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT 1ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//
//
//                            ////////check condition baby weihing mechine not function  not avable
//                        }
//                        else {
//                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"1SELECT CONDITION OF BABAY WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                        }


                        if (babygoodID.isChecked() || babyaverageID.isChecked() || babynotAvID.isChecked()){
                            if (goodadlID.isChecked() || averageadlID.isChecked() || notaadlID.isChecked()){
//                                if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//
//                                }
//                                else {
//                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT MUAC TAPE", Toast.LENGTH_SHORT).show();
//
//                                }
                                if (goodhmtID.isChecked() || averagehmtID.isChecked() || nahmtID.isChecked()){
                                    if (properiyfilledID.isChecked() || notproperiyfilledID.isChecked()){
                                        if (avablesuffID.isChecked() || notavalableID.isChecked()){
                                            if (auoID.isChecked() || nAID.isChecked()){
                                                /////////////////////SUB
                                                if (auoID.isChecked()){
                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()) {
                                                        String val = "rr";
                                                        save_date(val);
                                                    }
                                                }
                                                if (nAID.isChecked()){
                                                    String val = "rrr";
                                                    save_date(val);
                                                }

////////////////////////////////////////////////////////////////////////
//                                                //FFF
//                                                if (babygoodID.isChecked()){
//                                                    if (useId.isChecked() || nouseId.isChecked()){
//                                                        if (goodadlID.isChecked()){
//                                                            if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                               if (goodhmtID.isChecked()){
//                                                                   if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                                       ////////
//                                                                       //AAA
//                                                                       if (properiyfilledID.isChecked()){
//                                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                               //AAA
//                                                                               if (avablesuffID.isChecked()){
//                                                                                   if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                                       //AAA
//                                                                                       if (auoID.isChecked()){
//                                                                                           if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                               String val = "0";
//                                                                                               save_date(val);
//                                                                                           }
//                                                                                       }
//
//                                                                                   }
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                       //NNN
//                                                                       if (notproperiyfilledID.isChecked()){
//                                                                           if (notavalableID.isChecked()){
//                                                                               if (nAID.isChecked()){
//                                                                                   String val = "00";
//                                                                                   save_date(val);
//                                                                               }
//
//                                                                           }
//                                                                       }
//                                                                       //AANN
//                                                                       if (properiyfilledID.isChecked()){
//                                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                               if (avablesuffID.isChecked()) {
//                                                                                   if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                       if (nAID.isChecked()){
//                                                                                               String val = "000";
//                                                                                               save_date(val);
//                                                                                       }
//                                                                                   }
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                       //NNA
//                                                                       if (notproperiyfilledID.isChecked()){
//                                                                           if (notavalableID.isChecked()){
//                                                                               if (auoID.isChecked()){
//                                                                                   if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                       String val = "0000";
//                                                                                       save_date(val);
//                                                                                   }
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                       //ANA
//                                                                       if (properiyfilledID.isChecked()) {
//                                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                               if (notavalableID.isChecked()){
//                                                                                   if (auoID.isChecked()){
//                                                                                       if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                           String val = "00000";
//                                                                                           save_date(val);
//                                                                                       }
//                                                                                   }
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                      //NAN
//                                                                       if (notproperiyfilledID.isChecked()){
//                                                                           if (avablesuffID.isChecked()) {
//                                                                               if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                   if (nAID.isChecked()){
//                                                                                       String val = "000000";
//                                                                                       save_date(val);
//                                                                                   }
//                                                                               }}
//                                                                       }
//                                                                       ///////////////////////
//                                                                   }
//                                                               }
//                                                            }
//                                                        }
//                                                    }
//                                                }
////////////////////////////////////////////////////////////////////////////////////////
//                                                //NF NF NF
//                                                if (babyaverageID.isChecked()){
//                                                    if (averageadlID.isChecked()){
//                                                        if (averagehmtID.isChecked()){
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                    //AAA
//                                                                    if (avablesuffID.isChecked()){
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                            //AAA
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "1";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    ///////////////////////////////////////////////////
//                                                                    //NNN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "11";
//                                                                                save_date(val);
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //AANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                            if (avablesuffID.isChecked()) {
//                                                                                if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                    if (nAID.isChecked()){
//                                                                                        String val = "111";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NNA
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "1111";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //ANA
//                                                                    if (properiyfilledID.isChecked()) {
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (auoID.isChecked()){
//                                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                        String val = "11111";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NAN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "111111";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }}
//                                                                    }
//                                                                    //ANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "1111111";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    ///////////////////////
//                                                                }
//                                                            }
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "11k";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                ///NNN
//                                                if (babynotAvID.isChecked()){
//                                                    if (notaadlID.isChecked()){
//                                                        if (nahmtID.isChecked()){
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                    //AAA
//                                                                    if (avablesuffID.isChecked()){
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                            //AAA
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "2";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //NNN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "22";
//                                                                                save_date(val);
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //AANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                            if (avablesuffID.isChecked()) {
//                                                                                if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                    if (nAID.isChecked()){
//                                                                                        String val = "222";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NNA
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "2222";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //ANA
//                                                                    if (properiyfilledID.isChecked()) {
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (auoID.isChecked()){
//                                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                        String val = "22222";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NAN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "222222";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }}
//                                                                    }
//                                                                    //ANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "2222222";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    /////////////////////////////////////////////
//                                                                }
//                                                            }
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "22";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                //check
//                                                ///F NF F
//                                               if (babygoodID.isChecked()){
//                                                   if (useId.isChecked() || nouseId.isChecked()){
//                                                       if (averageadlID.isChecked()){
//                                                       if (goodhmtID.isChecked()){
//                                                           if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                               //AAA
//                                                               if (avablesuffID.isChecked()){
//                                                                   if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                       //AAA
//                                                                       if (auoID.isChecked()){
//                                                                           if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                               String val = "3";
//                                                                               save_date(val);
//                                                                           }
//                                                                       }
//
//                                                                   }
//                                                               }
//                                                               //NNN
//                                                               if (notproperiyfilledID.isChecked()){
//                                                                   if (notavalableID.isChecked()){
//                                                                       if (nAID.isChecked()){
//                                                                           String val = "33";
//                                                                           save_date(val);
//                                                                       }
//
//                                                                   }
//                                                               }
//                                                               //AAN
//                                                               if (properiyfilledID.isChecked()){
//                                                                   if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                       if (avablesuffID.isChecked()) {
//                                                                           if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                               if (nAID.isChecked()){
//                                                                                   String val = "333";
//                                                                                   save_date(val);
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                   }
//                                                               }
//                                                               //NNA
//                                                               if (notproperiyfilledID.isChecked()){
//                                                                   if (notavalableID.isChecked()){
//                                                                       if (auoID.isChecked()){
//                                                                           if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                               String val = "3333";
//                                                                               save_date(val);
//                                                                           }
//                                                                       }
//                                                                   }
//                                                               }
//                                                               //ANA
//                                                               if (properiyfilledID.isChecked()) {
//                                                                   if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                       if (notavalableID.isChecked()){
//                                                                           if (auoID.isChecked()){
//                                                                               if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                   String val = "33333";
//                                                                                   save_date(val);
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                   }
//                                                               }
//                                                               //NAN
//                                                               if (notproperiyfilledID.isChecked()){
//                                                                   if (avablesuffID.isChecked()) {
//                                                                       if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                           if (nAID.isChecked()){
//                                                                               String val = "333333";
//                                                                               save_date(val);
//                                                                           }
//                                                                       }}
//                                                               }
//                                                               //ANN
//                                                               if (properiyfilledID.isChecked()){
//                                                                   if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                       if (notavalableID.isChecked()){
//                                                                           if (nAID.isChecked()){
//                                                                               String val = "3333333";
//                                                                               save_date(val);
//                                                                           }
//                                                                       }
//                                                                   }
//                                                               }
//                                                               /////////////////////////////////////////////
//
//                                                           }
//                                                           if (notproperiyfilledID.isChecked()){
//                                                               if (notavalableID.isChecked()){
//                                                                   if (nAID.isChecked()){
//                                                                       String val = "33";
//                                                                       save_date(val);
//                                                                   }
//
//                                                               }
//                                                           }
//                                                       }
//                                                       }
//                                                   }
//
//                                               }
//                                                //FF NF
//                                                if (babygoodID.isChecked()) {
//                                                    if (useId.isChecked() || nouseId.isChecked()) {
//                                                        if (goodadlID.isChecked()){
//                                                            if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                                if (averagehmtID.isChecked()){
//                                                                    //AAA
//                                                                    if (avablesuffID.isChecked()){
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                            //AAA
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "4";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //NNN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "44";
//                                                                                save_date(val);
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //AAN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                            if (avablesuffID.isChecked()) {
//                                                                                if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                    if (nAID.isChecked()){
//                                                                                        String val = "444";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NNA
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "4444";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //ANA
//                                                                    if (properiyfilledID.isChecked()) {
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (auoID.isChecked()){
//                                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                        String val = "44444";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NAN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "444444";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }}
//                                                                    }
//                                                                    //ANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "4444444";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//
//                                                                    /////////////////////////////////////////////
//
//                                                                }
//                                                            }
//
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "44";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//
//                                                }
//                                                //NF F F
//                                                if (babyaverageID.isChecked()) {
//                                                    if (goodadlID.isChecked()) {
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()) {
//                                                            if (goodhmtID.isChecked()) {
//                                                                if (heightusedId.isChecked() || hightnotuseId.isChecked()) {
//                                                                    //AAA
//                                                                    if (avablesuffID.isChecked()){
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                            //AAA
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "5";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //NNN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "55";
//                                                                                save_date(val);
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //AAN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                            if (avablesuffID.isChecked()) {
//                                                                                if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                    if (nAID.isChecked()){
//                                                                                        String val = "555";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NNA
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "5555";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //ANA
//                                                                    if (properiyfilledID.isChecked()) {
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (auoID.isChecked()){
//                                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                        String val = "55555";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NAN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "55555";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }}
//                                                                    }
//                                                                    //ANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "555555";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//
//                                                                    /////////////////////////////////////////////
//
//                                                                }
//
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "55";
//                                                                            save_date(val);
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                            } } }
//                                                }
//
//                                           ////NNF
//                                                if (babynotAvID.isChecked()){
//                                                    if (notaadlID.isChecked()){
//                                                        if (goodhmtID.isChecked()){
//                                                            if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                                //AAA
//                                                                if (avablesuffID.isChecked()){
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                        //AAA
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "6";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //NNN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "66";
//                                                                            save_date(val);
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //AAN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "666";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NNA
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "6666";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //ANA
//                                                                if (properiyfilledID.isChecked()) {
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "66666";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NAN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (avablesuffID.isChecked()) {
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "666666";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }}
//                                                                }
//                                                                //ANN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "6666666";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//
//                                                                /////////////////////////////////////////////
//                                                            }
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "66";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                //NN NF
//                                                if (babynotAvID.isChecked()) {
//                                                    if (notaadlID.isChecked()) {
//                                                     if (averagehmtID.isChecked()){
//                                                         //AAA
//                                                         if (avablesuffID.isChecked()){
//                                                             if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                 //AAA
//                                                                 if (auoID.isChecked()){
//                                                                     if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                         String val = "7";
//                                                                         save_date(val);
//                                                                     }
//                                                                 }
//
//                                                             }
//                                                         }
//                                                         //NNN
//                                                         if (notproperiyfilledID.isChecked()){
//                                                             if (notavalableID.isChecked()){
//                                                                 if (nAID.isChecked()){
//                                                                     String val = "77";
//                                                                     save_date(val);
//                                                                 }
//
//                                                             }
//                                                         }
//                                                         //AAN
//                                                         if (properiyfilledID.isChecked()){
//                                                             if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                 if (avablesuffID.isChecked()) {
//                                                                     if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                         if (nAID.isChecked()){
//                                                                             String val = "777";
//                                                                             save_date(val);
//                                                                         }
//                                                                     }
//                                                                 }
//                                                             }
//                                                         }
//                                                         //NNA
//                                                         if (notproperiyfilledID.isChecked()){
//                                                             if (notavalableID.isChecked()){
//                                                                 if (auoID.isChecked()){
//                                                                     if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                         String val = "7777";
//                                                                         save_date(val);
//                                                                     }
//                                                                 }
//                                                             }
//                                                         }
//                                                         //ANA
//                                                         if (properiyfilledID.isChecked()) {
//                                                             if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                 if (notavalableID.isChecked()){
//                                                                     if (auoID.isChecked()){
//                                                                         if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                             String val = "77777";
//                                                                             save_date(val);
//                                                                         }
//                                                                     }
//                                                                 }
//                                                             }
//                                                         }
//                                                         //NAN
//                                                         if (notproperiyfilledID.isChecked()){
//                                                             if (avablesuffID.isChecked()) {
//                                                                 if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                     if (nAID.isChecked()){
//                                                                         String val = "777777";
//                                                                         save_date(val);
//                                                                     }
//                                                                 }}
//                                                         }
//                                                         //ANN
//                                                         if (properiyfilledID.isChecked()){
//                                                             if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                 if (notavalableID.isChecked()){
//                                                                     if (nAID.isChecked()){
//                                                                         String val = "7777777";
//                                                                         save_date(val);
//                                                                     }
//                                                                 }
//                                                             }
//                                                         }
//
//                                                         /////////////////////////////////////////////
//                                                     }
//
//                                                        if (notproperiyfilledID.isChecked()){
//                                                            if (notavalableID.isChecked()){
//                                                                if (nAID.isChecked()){
//                                                                    String val = "77";
//                                                                    save_date(val);
//                                                                }
//
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                //N F NF
//                                                if (babynotAvID.isChecked()){
//                                                    if (goodadlID.isChecked()){
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                            if (averagehmtID.isChecked()){
//                                                                //AAA
//                                                                if (avablesuffID.isChecked()){
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                        //AAA
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "8";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //NNN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "88";
//                                                                            save_date(val);
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //AAN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "888";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NNA
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "8888";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //ANA
//                                                                if (properiyfilledID.isChecked()) {
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "88888";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NAN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (avablesuffID.isChecked()) {
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "888888";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }}
//                                                                }
//                                                                //ANN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "8888888";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//
//                                                                /////////////////////////////////////////////
//
//                                                            }
//
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "88";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                //N F N
//                                                if (babynotAvID.isChecked()){
//                                                    if (goodadlID.isChecked()){
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                            if (nahmtID.isChecked()){
//                                                                //AAA
//                                                                if (avablesuffID.isChecked()){
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                        //AAA
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "9";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //NNN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "99";
//                                                                            save_date(val);
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //AAN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "999";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NNA
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "9999";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //ANA
//                                                                if (properiyfilledID.isChecked()) {
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "99999";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NAN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (avablesuffID.isChecked()) {
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "999999";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }}
//                                                                }
//                                                                //ANN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "9999999";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//
//                                                                /////////////////////////////////////////////
//
//                                                            }
//
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "99";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//
//                                                    }
//                                                }
//                                               // N F F
//                                                if (babynotAvID.isChecked()){
//                                                    if (goodadlID.isChecked()){
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                            //AAA
//                                                            if (avablesuffID.isChecked()){
//                                                                if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                    //AAA
//                                                                    if (auoID.isChecked()){
//                                                                        if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                            String val = "9A";
//                                                                            save_date(val);
//                                                                        }
//                                                                    }
//
//                                                                }
//                                                            }
//                                                            //NNN
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "99A";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                            //AAN
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                    if (avablesuffID.isChecked()) {
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "999A";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                            //NNA
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (auoID.isChecked()){
//                                                                        if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                            String val = "9999A";
//                                                                            save_date(val);
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                            //ANA
//                                                            if (properiyfilledID.isChecked()) {
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "99999A";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                            //NAN
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (avablesuffID.isChecked()) {
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "999999A";
//                                                                            save_date(val);
//                                                                        }
//                                                                    }}
//                                                            }
//                                                            //ANN
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "9999999A";
//                                                                            save_date(val);
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//
//                                                            /////////////////////////////////////////////
//                                                        }
//
//                                                        if (notproperiyfilledID.isChecked()){
//                                                            if (notavalableID.isChecked()){
//                                                                if (nAID.isChecked()){
//                                                                    String val = "99A";
//                                                                    save_date(val);
//                                                                }
//
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                // N NF NF
//                                           if (babynotAvID.isChecked()){
//                                               if (averageadlID.isChecked()){
//                                                   if (averagehmtID.isChecked()){
//                                                       //AAA
//                                                       if (avablesuffID.isChecked()){
//                                                           if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                               //AAA
//                                                               if (auoID.isChecked()){
//                                                                   if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                       String val = "9B";
//                                                                       save_date(val);
//                                                                   }
//                                                               }
//
//                                                           }
//                                                       }
//                                                       //NNN
//                                                       if (notproperiyfilledID.isChecked()){
//                                                           if (notavalableID.isChecked()){
//                                                               if (nAID.isChecked()){
//                                                                   String val = "99B";
//                                                                   save_date(val);
//                                                               }
//
//                                                           }
//                                                       }
//                                                       //AAN
//                                                       if (properiyfilledID.isChecked()){
//                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                               if (avablesuffID.isChecked()) {
//                                                                   if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                       if (nAID.isChecked()){
//                                                                           String val = "999B";
//                                                                           save_date(val);
//                                                                       }
//                                                                   }
//                                                               }
//                                                           }
//                                                       }
//                                                       //NNA
//                                                       if (notproperiyfilledID.isChecked()){
//                                                           if (notavalableID.isChecked()){
//                                                               if (auoID.isChecked()){
//                                                                   if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                       String val = "9999B";
//                                                                       save_date(val);
//                                                                   }
//                                                               }
//                                                           }
//                                                       }
//                                                       //ANA
//                                                       if (properiyfilledID.isChecked()) {
//                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                               if (notavalableID.isChecked()){
//                                                                   if (auoID.isChecked()){
//                                                                       if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                           String val = "99999B";
//                                                                           save_date(val);
//                                                                       }
//                                                                   }
//                                                               }
//                                                           }
//                                                       }
//                                                       //NAN
//                                                       if (notproperiyfilledID.isChecked()){
//                                                           if (avablesuffID.isChecked()) {
//                                                               if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                   if (nAID.isChecked()){
//                                                                       String val = "999999B";
//                                                                       save_date(val);
//                                                                   }
//                                                               }}
//                                                       }
//                                                       //ANN
//                                                       if (properiyfilledID.isChecked()){
//                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                               if (notavalableID.isChecked()){
//                                                                   if (nAID.isChecked()){
//                                                                       String val = "9999999B";
//                                                                       save_date(val);
//                                                                   }
//                                                               }
//                                                           }
//                                                       }
//                                                       /////////////////////////////////////////////
//                                                   }
//
//                                                   if (notproperiyfilledID.isChecked()){
//                                                       if (notavalableID.isChecked()){
//                                                           if (nAID.isChecked()){
//                                                               String val = "99B";
//                                                               save_date(val);
//                                                           }
//
//                                                       }
//                                                   }
//                                               }
//                                           }
                                                /////////////////////SUB///////

                                            }
                                            else {
                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
                                                String title = "Message Box";
                                                String msg = "SELECT HAND WASHING SOAP";
                                                createDialog(title,msg);
                                            }
                                        }
                                        else {
                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
                                            String title = "Message Box";
                                            String msg = "SELECT CONTAINERS FOR STORING";
                                            createDialog(title,msg);
                                        }

                                    }
                                    else {
                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART", Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SELECT GROTH CHART";
                                        createDialog(title,msg);
                                    }

                                }
                                else {
                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HEIGHT MEASURING TAPE", Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT HEIGHT MEASURING TAPE";
                                    createDialog(title,msg);

                                }
                            }
                            else {
                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADULT WEIGHING MACHINE", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT ADULT WEIGHING MACHINE";
                                createDialog(title,msg);
                            }
                        }
                        else {
                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONDITION OF BABY WEIGHING MACHINE", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT CONDITION OF BABY WEIGHING MACHINE";
                            createDialog(title,msg);

                        }

                        if (babygoodID.isChecked()){
                            if (useId.isChecked() || nouseId.isChecked()){
                                if (goodadlID.isChecked()){
                                    if (useadult.isChecked() || nouseadultId.isChecked()){
                                        if (goodhmtID.isChecked()){
                                            if (heightusedId.isChecked() || hightnotuseId.isChecked()){
                                                if (properiyfilledID.isChecked()){
                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()){
                                                        if (avablesuffID.isChecked()){
                                                            if (saficentId.isChecked() || notsuficientId.isChecked()){
                                                                if (auoID.isChecked()){
                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){


                                                                    }
                                                                    else {
                                                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                                                        String title = "Message Box";
                                                                        String msg = "SELECT HAND WASHING SOAP USED OR NOT USED";
                                                                        createDialog(title,msg);
                                                                    }
                                                                }


                                                            }
                                                            else {
                                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FORSTORINGSUFFICIENT OR NOT SUFFICIENT", Toast.LENGTH_SHORT).show();
                                                                String title = "Message Box";
                                                                String msg = "SELECT CONTAINERS FORSTORINGSUFFICIENT OR NOT SUFFICIENT";
                                                                createDialog(title,msg);
                                                            }

                                                        }

                                                    }
                                                    else {
                                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY OR NOT PROPERLY", Toast.LENGTH_SHORT).show();
                                                        String title = "Message Box";
                                                        String msg = "SELECT GROTH CHART PROPERLY OR NOT PROPERLY";
                                                        createDialog(title,msg);
                                                    }
                                                }


                                            }
                                            else {
                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HEIGHT MEASURING TAPE USE OR NO USE", Toast.LENGTH_SHORT).show();
                                                String title = "Message Box";
                                                String msg = "SELECT ADULT WEIGHING USED OR NOT USED";
                                                createDialog(title,msg);
                                            }
                                        }

                                    }
                                    else {
                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADULT WEIGHING USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SELECT ADULT WEIGHING USED OR NOT USED";
                                        createDialog(title,msg);
                                    }
                                }
                            }
                            else {
                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONDITION OF BABAY WEIGHING MACHINE USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT MAINTAINED UP TO DATE OR NOT MAINTAINED";
                                createDialog(title,msg);
                            }
                        }

                    } else {

                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT MAINTAINED UP TO DATE OR NOT MAINTAINED",Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT MAINTAINED UP TO DATE OR NOT MAINTAINED";
                        createDialog(title,msg);
                    }

                }

            }
            else {
                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONDITION OF MAT / CHATAI",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT CONDITION OF MAT / CHATAI";
                createDialog(title,msg);
            }
        }
        else {
            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT UTENSIL CONDITION",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT UTENSIL CONDITION";
            createDialog(title,msg);
        }

    }

     public void save_date(final String val){
        Log.e("SUBMIT",val);
        Calendar cc = Calendar.getInstance();
         System.out.println("Current time => " + cc.getTime());
         SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
         curDate = df3.format(cc.getTime());
         Calendar ccc = Calendar.getInstance();
         SimpleDateFormat time = new SimpleDateFormat("HH:mm");
         curTime = time.format(ccc.getTime());
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CONDITIONEQUIPMENTOTHERSAWC,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         Log.e("SHIAYE"," "+response);
                         try {
                             JSONArray array =new JSONArray(response);
                             Log.e("SHIAYEOBJ"," "+array);
                             for (int i=0; i<array.length(); i++) {
                                 JSONObject object = array.getJSONObject(i);
                                 String message = object.getString("message");
                                 Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                 Log.e("UPDATE"," "+message);
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET conditionofequipmentotherawc='1' WHERE allinspactionid=" +insid);
                               //syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,babywmachin,adultwmachin,muactape,heightmeasurtyp,growthchrt,storecontainr,handwashsoap,curTime,CONDITION_SYNCED_WITH_SERVER);

                                 if (babywmachin.equals("f")){
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);

                                 }
                                 else {
                                     conditionbabyweighingmechin_used_notoused ="nna";
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 if (adultwmachin.equals("f")){
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 else {
                                     adualtweighingmachine_used_notused = "nna";
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 if (heightmeasurtyp.equals("f")){
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 else {
                                     height_measuring_type ="nna";
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 if (growthchrt.equals("a")){
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 else {
                                     proper_filled_up ="nna";
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 if (storecontainr.equals("a")){
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 else {
                                     suficent ="nna";
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }

                                 if (handwashsoap.equals("a")){
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                 }
                                 else {
                                     hand_washing_sope = "nna";
                                     syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
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
                         dbb.execSQL("UPDATE insflag SET conditionofequipmentotherawc='1' WHERE allinspactionid=" +insid);
                         if (babywmachin.equals("f")){
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);

                         }
                         else {
                             conditionbabyweighingmechin_used_notoused ="nna";
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         if (adultwmachin.equals("f")){
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         else {
                             adualtweighingmachine_used_notused = "nna";
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         if (heightmeasurtyp.equals("f")){
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         else {
                             height_measuring_type ="nna";
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         if (growthchrt.equals("a")){
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         else {
                             proper_filled_up ="nna";
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         if (storecontainr.equals("a")){
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         else {
                             suficent ="nna";
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }

                         if (handwashsoap.equals("a")){
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                         else {
                             hand_washing_sope = "nna";
                             syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                         }
                     }
                 }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();
                 params.put("inspctn_id",insid);
                 params.put("utensil_condtn",utensilcondtn);
                 params.put("mat_conditn",matconditn);
                 params.put("elevn_regstr",elevnregstr);
                 params.put("elevn_regstr_no",elavenregister);
                 params.put("baby_wmachin",babywmachin);
                 if (babywmachin.equals("f")){
                     params.put("baby_wmachin_use",conditionbabyweighingmechin_used_notoused);
                 }
                 else {
                     conditionbabyweighingmechin_used_notoused ="nna";
                     params.put("baby_wmachin_use",conditionbabyweighingmechin_used_notoused);
                 }
                 params.put("adult_wmachin",adultwmachin);
                 // params.put("adult_wmachin","");
                 if (adultwmachin.equals("f")){
                     params.put("adult_wmachin_use",adualtweighingmachine_used_notused);
                 }
                 else {
                     adualtweighingmachine_used_notused = "nna";
                     params.put("adult_wmachin_use",adualtweighingmachine_used_notused);
                 }

                 //params.put("muac_tape",muactape);
                 params.put("height_measur_typ",heightmeasurtyp);
                 if (heightmeasurtyp.equals("f")){
                     params.put("height_measur_typ_use",height_measuring_type);
                 }
                 else {
                     height_measuring_type ="nna";
                     params.put("height_measur_typ_use",height_measuring_type);
                 }

                 params.put("growth_chrt",growthchrt);
                 if (growthchrt.equals("a")){
                     params.put("growth_chrt_fillup",proper_filled_up);
                 }
                 else {
                     proper_filled_up ="nna";
                     params.put("growth_chrt_fillup",proper_filled_up);
                 }
                 params.put("store_containr",storecontainr);
                 if (storecontainr.equals("a")){
                     params.put("suffi_store_containr",suficent);
                 }
                 else {
                     suficent ="nna";
                     params.put("suffi_store_containr",suficent);
                 }

                 params.put("hand_wash_soap",handwashsoap);
                 if (handwashsoap.equals("a")){
                     params.put("hand_wash_soap_use",hand_washing_sope);
                 }
                 else {
                     hand_washing_sope = "nna";
                     params.put("hand_wash_soap_use",hand_washing_sope);
                 }
                 params.put("ins_time",curTime);
                 Log.e("STORELOG",insid+" "+" "+utensilcondtn+" "+matconditn+" "+elevnregstr+" "+" "+elavenregister+" "+babywmachin+""+conditionbabyweighingmechin_used_notoused+
                         " "+adultwmachin+" "+adualtweighingmachine_used_notused+" "+" "+"  "+heightmeasurtyp+" "+height_measuring_type+" "+growthchrt+" "+proper_filled_up+" "+storecontainr+" "+suficent+" "+handwashsoap+" "+hand_washing_sope+" "+curTime);
                 return params;
             }
         };
         //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
         stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
         stringRequest.setShouldCache(false);
         volleySingleton.addToRequestQueue(stringRequest);
     }
    private void syncConditionsaveDatabase(String insid,
                                           String utensilcondtn,
                                           String matconditn,
                                           String elevnregstr,
                                           String elavenregister,
                                           String babywmachin,
                                           String conditionbabyweighingmechinusednotoused,
                                           String adultwmachin,
                                           String adualtweighingmachineusednotused,
                                           String heightmeasurtyp,
                                           String heightmeasuringtype,
                                           String growthchrt,
                                           String properfilledup,
                                           String storecontainr,
                                           String suficent,
                                           String handwashsoap,
                                           String hand_washing_sope,
                                           String curTime,
                                           int conditionstatus){

                if (conditions.equals("0")) {
              helper.CONDITIONINSERT(
                      insid,
                      utensilcondtn,
                      matconditn,
                      elevnregstr,
                      elavenregister,
                      babywmachin,
                      conditionbabyweighingmechinusednotoused,
                      adultwmachin,
                      adualtweighingmachineusednotused,
                      heightmeasurtyp,
                      heightmeasuringtype,
                      growthchrt,
                      properfilledup,
                      storecontainr,
                      suficent,
                      handwashsoap,
                      hand_washing_sope,
                      curTime,
                      conditionstatus);
                }
                else {
                    helper.CONDITIONUPDATE(dbid,insid,
                    utensilcondtn,
                    matconditn,
                    elevnregstr,
                    elavenregister,
                    babywmachin,
                    conditionbabyweighingmechinusednotoused,
                    adultwmachin,
                    adualtweighingmachineusednotused,
                    heightmeasurtyp,
                    heightmeasuringtype,
                    growthchrt,
                    properfilledup,
                    storecontainr,
                    suficent,
                    handwashsoap,
                    hand_washing_sope,
                    curTime,
                    conditionstatus);
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
//    Intent intent = new Intent(ConditionOfEquipmentOthersAWCActivity.this,ShishuAloyActivity.class);
//    Bundle bundle = new Bundle();
//    bundle.putString("kitchen",kitchen);
//    bundle.putString("adqutspacepse",adqutspacepse);
//    bundle.putString("electric",electric);
//    bundle.putString("water",water);
//    bundle.putString("foodspace",foodspace);
//    bundle.putString("toilet",toilet);
//    bundle.putString("awcscode",awcscode);
//    bundle.putString("awcsname",awcsname);
//    bundle.putString("dbdistid",dbdistid);
//    bundle.putString("dbprojectid",dbprojectid);
//    bundle.putString("dbsectorid",dbsectorid);
//    bundle.putString("dbcenterid",dbcenterid);
//    bundle.putString("projectnamedb",projectnamedb);
//    bundle.putString("districnamedb",districnamedb);
//    bundle.putString("sectorrnamedb",sectorrnamedb);
//    bundle.putString("centernamedb",centernamedb);
//    bundle.putString("insid",insid);
//    bundle.putString("planid",planid);
//    bundle.putString("dbid",dbid);
//    intent.putExtras(bundle);
//    startActivity(intent);

       Intent intent = new Intent(ConditionOfEquipmentOthersAWCActivity.this,ShishuAloyActivity.class);
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
       if (goodutensilID.isChecked() || averageutensilID.isChecked() || poorutensilID.isChecked() || notavalutensilID.isChecked()){
           if (goodmcID.isChecked() || averagemcID.isChecked() || poormcID.isChecked() || notavelemcID.isChecked()){
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
               ElevenregisterId.setFilters(new InputFilter[] { filter });
               elavenregister = ElevenregisterId.getText().toString().trim();
               if (TextUtils.isEmpty(elavenregister)) {
                   ElevenregisterId.setError("Enter Number of Eleven register");
                   ElevenregisterId.requestFocus();
                   return;
               }
//                ElevenregisterId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        elavenregister = ElevenregisterId.getText().toString();
//                    }
//                });
//                ElevenregisterId.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        if (actionId == EditorInfo.IME_ACTION_DONE) {
//                            elavenregister = ElevenregisterId.getText().toString();
//                            return true;
//                        }
//                        return false;
//                    }
//                });
               elavenregister = ElevenregisterId.getText().toString();

               if (elavenregister.equals("0")){
                   LVelevenrgisId.setVisibility(View.GONE);
                   if (goodelevenID.isChecked()){
                       goodelevenID.setChecked(false);
                       goodelevenID.setSelected(false);
                   }
                   if (averageelevenID.isChecked()){
                       averageelevenID.setChecked(false);
                       averageelevenID.setSelected(false);
                   }
                   if (babygoodID.isChecked() || babyaverageID.isChecked() || babynotAvID.isChecked()){
                       if (goodadlID.isChecked() || averageadlID.isChecked() || notaadlID.isChecked()){
//                             if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//
//                             }
//                             else {
//                                 Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT MUAC TAPE", Toast.LENGTH_SHORT).show();
//
//                             }

                           if (goodhmtID.isChecked() || averagehmtID.isChecked() || nahmtID.isChecked()){
                               if (properiyfilledID.isChecked() || notproperiyfilledID.isChecked()){
                                   if (avablesuffID.isChecked() || notavalableID.isChecked()){
                                       if (auoID.isChecked() || nAID.isChecked()){
                                           /////////////////////SUB
                                           if (auoID.isChecked()){
                                               if (gandshopyesId.isChecked() || handshpnoId.isChecked()) {
                                                   String val = "r1";
                                                   save_date1(val);
                                               }
                                           }
                                           if (nAID.isChecked()){
                                               String val = "r2";
                                               save_date1(val);
                                           }

                                           /////////////////////SUB///////

                                       }
                                       else {
                                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
                                           String title = "Message Box";
                                           String msg = "SELECT HAND WASHING SOAP";
                                           createDialog(title,msg);
                                       }
                                   }
                                   else {
                                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
                                       String title = "Message Box";
                                       String msg = "SELECT CONTAINERS FOR STORING";
                                       createDialog(title,msg);
                                   }

                               }
                               else {
                                   Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART", Toast.LENGTH_SHORT).show();
                                   String title = "Message Box";
                                   String msg = "SELECT GROTH CHART";
                                   createDialog(title,msg);
                               }

                           }
                           else {
                               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HEIGHT MEASURING TAPE", Toast.LENGTH_SHORT).show();
                               String title = "Message Box";
                               String msg = "SELECT HEIGHT MEASURING TAPE";
                               createDialog(title,msg);

                           }
                       }
                       else {
                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADULT WEIGHING MACHINE", Toast.LENGTH_SHORT).show();
                           String title = "Message Box";
                           String msg = "SELECT ADULT WEIGHING MACHINE";
                           createDialog(title,msg);
                       }
                   }
                   else {
                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONDITION OF BABY WEIGHING MACHINE", Toast.LENGTH_SHORT).show();
                       String title = "Message Box";
                       String msg = "SELECT CONDITION OF BABY WEIGHING MACHINE";
                       createDialog(title,msg);

                   }

                   if (babygoodID.isChecked()){
                       if (useId.isChecked() || nouseId.isChecked()){
                           if (goodadlID.isChecked()){
                               if (useadult.isChecked() || nouseadultId.isChecked()){
                                   if (goodhmtID.isChecked()){
                                       if (heightusedId.isChecked() || hightnotuseId.isChecked()){
                                           if (properiyfilledID.isChecked()){
                                               if (grothproperId.isChecked() || notgrothproperId.isChecked()){
                                                   if (avablesuffID.isChecked()){
                                                       if (saficentId.isChecked() || notsuficientId.isChecked()){
                                                           if (auoID.isChecked()){
                                                               if (gandshopyesId.isChecked() || handshpnoId.isChecked()){


                                                               }
                                                               else {
                                                                   Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                                                   String title = "Message Box";
                                                                   String msg = "SELECT HAND WASHING SOAP USED OR NOT USED";
                                                                   createDialog(title,msg);
                                                               }
                                                           }


                                                       }
                                                       else {
                                                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FORSTORINGSUFFICIENT OR NOT SUFFICIENT", Toast.LENGTH_SHORT).show();
                                                           String title = "Message Box";
                                                           String msg = "SELECT CONTAINERS FORSTORINGSUFFICIENT OR NOT SUFFICIENT";
                                                           createDialog(title,msg);
                                                       }

                                                   }

                                               }
                                               else {
                                                   Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY OR NOT PROPERLY", Toast.LENGTH_SHORT).show();
                                                   String title = "Message Box";
                                                   String msg = "SELECT GROTH CHART PROPERLY OR NOT PROPERLY";
                                                   createDialog(title,msg);
                                               }
                                           }

                                       }
                                       else {
                                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HEIGHT MEASURING TAPE USE OR NO USE", Toast.LENGTH_SHORT).show();
                                           String title = "Message Box";
                                           String msg = "SELECT HEIGHT MEASURING TAPE USE OR NO USE";
                                           createDialog(title,msg);
                                       }
                                   }

                               }
                               else {
                                   Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADULT WEIGHING USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                   String title = "Message Box";
                                   String msg = "SELECT ADULT WEIGHING USED OR NOT USED";
                                   createDialog(title,msg);
                               }
                           }

                       }
                       else {
                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONDITION OF BABAY WEIGHING MACHINE USED OR NOT USED", Toast.LENGTH_SHORT).show();
                           String title = "Message Box";
                           String msg = "SELECT CONDITION OF BABAY WEIGHING MACHINE USED OR NOT USED";
                           createDialog(title,msg);
                       }
                   }

               }
               else if (elavenregister.equals("1") || elavenregister.equals("2")
                       ||elavenregister.equals("3")
                       || elavenregister.equals("4")
                       || elavenregister.equals("5")
                       || elavenregister.equals("6")
                       || elavenregister.equals("7")
                       || elavenregister.equals("8")
                       || elavenregister.equals("9")
                       || elavenregister.equals("10")
                       || elavenregister.equals("11")
               ){
                   LVelevenrgisId.setVisibility(View.VISIBLE);
                   if (goodelevenID.isChecked() || averageelevenID.isChecked()){

//                        if (babygoodID.isChecked() || babyaverageID.isChecked()  || babynotAvID.isChecked()){
//                            if (babygoodID.isChecked()){
//                                if (useId.isChecked() || nouseId.isChecked()){
//                                    if (goodadlID.isChecked() || averageadlID.isChecked() ||  notaadlID.isChecked()){
//                                        if (goodadlID.isChecked()) {
//                                            if (useadult.isChecked() || nouseadultId.isChecked()) {
//                                                //////////////////////////////////////////////////////////////////////////
//                                                if (goodadlID.isChecked() || averageadlID.isChecked() ||  notaadlID.isChecked()){
//                                                    if (goodadlID.isChecked()) {
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()) {
//                                                            if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//                                                                if (goodhmtID.isChecked() || averagehmtID.isChecked() || nahmtID.isChecked()){
//                                                                    if(goodhmtID.isChecked()){
//                                                                        if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT GROWTH CHART",Toast.LENGTH_SHORT).show();
//                                                                            if (properiyfilledID.isChecked()){
//                                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONTAINERS FOR STORING",Toast.LENGTH_SHORT).show();
//                                                                                    //Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
//                                                                                    if (avablesuffID.isChecked()){
//                                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT 1HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                                                            if (auoID.isChecked()){
//                                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                                    save_date();
//
//                                                                                                }
//                                                                                                else {
//                                                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                                                }
//                                                                                            }
//                                                                                        }
//                                                                                        else {
//                                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING SUFFICIENT NOT-SUFFICIENT", Toast.LENGTH_SHORT).show();
//
//                                                                                        }
//                                                                                    }
//
//
//                                                                                } else {
//                                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY FILED UP NOT PROPERLY FILED UP", Toast.LENGTH_SHORT).show();
//
//                                                                                }
//                                                                            }
//                                                                            if (nAID.isChecked()){
//                                                                                save_date();
//
//                                                                                    }
//
//
//
//                                                                        }
//                                                                        else {
//                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE USED OR NOT USED",Toast.LENGTH_SHORT).show();
//
//                                                                        }
//                                                                    }
//
//
//
//
//
//
//                                                                }
//                                                                else {
//                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE",Toast.LENGTH_SHORT).show();
//
//                                                                }
//
//
//                                                            }
//                                                            else {
//                                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT MUAC TAPE",Toast.LENGTH_SHORT).show();
//
//                                                            }
//
//
//                                                        } else {
//                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADLT. USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                        }
//                                                    }
//
//
//
//
//                                                }
//
//                                                else {
//                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT 1ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                                                }
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                            } else {
//                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADLT. USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                            }
//                                        }
//
//
//                                    }
//                                    else {
//                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                else {
//                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONDITION OF BABAY WEIGHING MACHINE USED OR NOT USED",Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//
//                            ////////check condition baby weihing mechine not function  not avable
//                            if (babyaverageID.isChecked() || babynotAvID.isChecked()){
//                                if (useId.isChecked()){
//                                    useId.setChecked(false);
//                                    useId.setSelected(false);
//                                }
//                                if (nouseId.isChecked()){
//                                    nouseId.setChecked(false);
//                                    nouseId.setSelected(false);
//                                }
//                                //Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                                if (goodadlID.isChecked() || averageadlID.isChecked() ||  notaadlID.isChecked()){
//                                    if (goodadlID.isChecked()) {
//                                        if (useadult.isChecked() || nouseadultId.isChecked()) {
//
//
//
//                                        } else {
//                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADLT. USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                        }
//                                    }
//                                    if (averageadlID.isChecked() ||  notaadlID.isChecked()){
//                                        if (useadult.isChecked()){
//                                            useadult.setChecked(false);
//                                            useadult.setSelected(false);
//                                        }
//                                        if (nouseadultId.isChecked()){
//                                            nouseadultId.setChecked(false);
//                                            nouseadultId.setSelected(false);
//                                        }
//                                            if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//                                            if (goodhmtID.isChecked() || averagehmtID.isChecked() || nahmtID.isChecked()){
//                                                if(goodhmtID.isChecked()){
//                                                    if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                       /////////////////////////grothchart/////////
//                                                        if (properiyfilledID.isChecked() || notproperiyfilledID.isChecked()){
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                      if (avablesuffID.isChecked() || notavalableID.isChecked()){
//                                                                          if (avablesuffID.isChecked()){
//                                                                              if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                                  Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                                                  if (auoID.isChecked() || nAID.isChecked()){
//                                                                                      if (auoID.isChecked()){
//                                                                                          if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                              save_date();
//                                                                                          }
//                                                                                          else {
//                                                                                              Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                                          }
//                                                                                      }
//
//                                                                                      if (nAID.isChecked()){
//                                                                                          if (gandshopyesId.isChecked()){
//                                                                                              gandshopyesId.setChecked(false);
//                                                                                              gandshopyesId.setSelected(false);
//                                                                                          }
//                                                                                          if (handshpnoId.isChecked()){
//                                                                                              handshpnoId.setChecked(false);
//                                                                                              handshpnoId.setSelected(false);
//                                                                                          }
//                                                                                          save_date();
//                                                                                      }
//                                                                                  }
//                                                                                  else {
//
//                                                                                  }
//                                                                              }
//                                                                              else {
//                                                                                  Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING SUFFICIENT NOT-SUFFICIENT", Toast.LENGTH_SHORT).show();
//
//                                                                              }
//                                                                          }
//                                                                          if (notavalableID.isChecked()){
//                                                                              Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                                              if (saficentId.isChecked()){
//                                                                                  saficentId.setChecked(false);
//                                                                                  saficentId.setSelected(false);
//                                                                              }
//                                                                              if (notsuficientId.isChecked()){
//                                                                                  notsuficientId.setChecked(false);
//                                                                                  notsuficientId.setSelected(false);
//                                                                              }
//                                                                              if (auoID.isChecked()){
//                                                                                  if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                      save_date();
//                                                                                  }
//                                                                                  else {
//                                                                                      Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                                  }
//                                                                              }
//
//                                                                              if (nAID.isChecked()){
//                                                                                  if (gandshopyesId.isChecked()){
//                                                                                      gandshopyesId.setChecked(false);
//                                                                                      gandshopyesId.setSelected(false);
//                                                                                  }
//                                                                                  if (handshpnoId.isChecked()){
//                                                                                      handshpnoId.setChecked(false);
//                                                                                      handshpnoId.setSelected(false);
//                                                                                  }
//                                                                                  save_date();
//                                                                              }
//                                                                          }
//                                                                    }
//                                                                    else {
//                                                                          Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONTAINERS FOR STORING",Toast.LENGTH_SHORT).show();
//
//                                                                      }
//                                                                } else {
//                                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY FILED UP NOT PROPERLY FILED UP", Toast.LENGTH_SHORT).show();
//
//                                                                }
//                                                            }
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
//                                                                if (grothproperId.isChecked()){
//                                                                    grothproperId.setChecked(false);
//                                                                    grothproperId.setSelected(false);
//                                                                }
//                                                                if (hightnotuseId.isChecked()){
//                                                                    hightnotuseId.setChecked(false);
//                                                                    hightnotuseId.setSelected(false);
//                                                                }
//
//                                                                if (avablesuffID.isChecked()){
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//
//                                                                    }
//                                                                    else {
//                                                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING SUFFICIENT NOT-SUFFICIENT", Toast.LENGTH_SHORT).show();
//
//                                                                    }
//                                                                }
//                                                                if (notavalableID.isChecked()){
//                                                                    //Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                                    if (saficentId.isChecked()){
//                                                                        saficentId.setChecked(false);
//                                                                        saficentId.setSelected(false);
//                                                                    }
//                                                                    if (notsuficientId.isChecked()){
//                                                                        notsuficientId.setChecked(false);
//                                                                        notsuficientId.setSelected(false);
//                                                                    }
//                                                                    if (auoID.isChecked()){
//                                                                        if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                            save_date();
//                                                                        }
//                                                                        else {
//                                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                        }
//                                                                    }
//
//                                                                    if (nAID.isChecked()){
//                                                                        if (gandshopyesId.isChecked()){
//                                                                            gandshopyesId.setChecked(false);
//                                                                            gandshopyesId.setSelected(false);
//                                                                        }
//                                                                        if (handshpnoId.isChecked()){
//                                                                            handshpnoId.setChecked(false);
//                                                                            handshpnoId.setSelected(false);
//                                                                        }
//                                                                        save_date();
//                                                                    }
//                                                                }
//                                                            }
//                                                        }
//                                                        else {
//                                                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT GROWTH CHART",Toast.LENGTH_SHORT).show();
//
//                                                        }
//
//                                                    }
//                                                    else {
//                                                        Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE USED OR NOT USED",Toast.LENGTH_SHORT).show();
//
//                                                    }
//                                                }
//                                                if (averagehmtID.isChecked() || nahmtID.isChecked()){
//                                                    if (heightusedId.isChecked()){
//                                                        heightusedId.setChecked(false);
//                                                        heightusedId.setSelected(false);
//                                                    }
//                                                    if (hightnotuseId.isChecked()){
//                                                        hightnotuseId.setChecked(false);
//                                                        hightnotuseId.setSelected(false);
//                                                    }
//                                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT GROWTH CHART",Toast.LENGTH_SHORT).show();
//                                                    if (properiyfilledID.isChecked()){
//                                                            if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONTAINERS FOR STORING",Toast.LENGTH_SHORT).show();
//
//                                                            } else {
//                                                              Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY FILED UP NOT PROPERLY FILED UP", Toast.LENGTH_SHORT).show();
//
//                                                          }
//                                                      }
//
//                                                      if (notproperiyfilledID.isChecked()){
//                                                          Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
//                                                          if (grothproperId.isChecked()){
//                                                              grothproperId.setChecked(false);
//                                                              grothproperId.setSelected(false);
//                                                          }
//                                                          if (hightnotuseId.isChecked()){
//                                                              hightnotuseId.setChecked(false);
//                                                              hightnotuseId.setSelected(false);
//                                                          }
//
//                                                          if (avablesuffID.isChecked()){
//                                                              if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                  Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//
//                                                              }
//                                                              else {
//                                                                  Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING SUFFICIENT NOT-SUFFICIENT", Toast.LENGTH_SHORT).show();
//
//                                                              }
//                                                          }
//                                                          if (notavalableID.isChecked()){
//                                                              //Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
//                                                              if (saficentId.isChecked()){
//                                                                  saficentId.setChecked(false);
//                                                                  saficentId.setSelected(false);
//                                                              }
//                                                              if (notsuficientId.isChecked()){
//                                                                  notsuficientId.setChecked(false);
//                                                                  notsuficientId.setSelected(false);
//                                                              }
//                                                              if (auoID.isChecked()){
//                                                                  if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                      save_date();
//                                                                  }
//                                                                  else {
//                                                                      Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT USED OR NOT USED", Toast.LENGTH_SHORT).show();
//
//                                                                  }
//                                                              }
//
//                                                              if (nAID.isChecked()){
//                                                                  if (gandshopyesId.isChecked()){
//                                                                      gandshopyesId.setChecked(false);
//                                                                      gandshopyesId.setSelected(false);
//                                                                  }
//                                                                  if (handshpnoId.isChecked()){
//                                                                      handshpnoId.setChecked(false);
//                                                                      handshpnoId.setSelected(false);
//                                                                  }
//                                                                  save_date();
//                                                              }
//                                                          }
//                                                      }
//
//                                                }
//
//
//                                            }
//                                            else {
//                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE",Toast.LENGTH_SHORT).show();
//
//                                            }
//
//
//                                        }
//                                        else {
//                                                Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT MUAC TAPE",Toast.LENGTH_SHORT).show();
//
//                                            }
//
//                                    }
//                                }
//
//                                else {
//                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT 1ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//
//
//                            ////////check condition baby weihing mechine not function  not avable
//                        }
//                        else {
//                            Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"1SELECT CONDITION OF BABAY WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                        }


                       if (babygoodID.isChecked() || babyaverageID.isChecked() || babynotAvID.isChecked()){
                           if (goodadlID.isChecked() || averageadlID.isChecked() || notaadlID.isChecked()){
//                                if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//
//                                }
//                                else {
//                                    Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT MUAC TAPE", Toast.LENGTH_SHORT).show();
//
//                                }
                               if (goodhmtID.isChecked() || averagehmtID.isChecked() || nahmtID.isChecked()){
                                   if (properiyfilledID.isChecked() || notproperiyfilledID.isChecked()){
                                       if (avablesuffID.isChecked() || notavalableID.isChecked()){
                                           if (auoID.isChecked() || nAID.isChecked()){
                                               /////////////////////SUB
                                               if (auoID.isChecked()){
                                                   if (gandshopyesId.isChecked() || handshpnoId.isChecked()) {
                                                       String val = "rr";
                                                       save_date1(val);
                                                   }
                                               }
                                               if (nAID.isChecked()){
                                                   String val = "rrr";
                                                   save_date1(val);
                                               }

////////////////////////////////////////////////////////////////////////
//                                                //FFF
//                                                if (babygoodID.isChecked()){
//                                                    if (useId.isChecked() || nouseId.isChecked()){
//                                                        if (goodadlID.isChecked()){
//                                                            if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                               if (goodhmtID.isChecked()){
//                                                                   if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                                       ////////
//                                                                       //AAA
//                                                                       if (properiyfilledID.isChecked()){
//                                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                               //AAA
//                                                                               if (avablesuffID.isChecked()){
//                                                                                   if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                                       //AAA
//                                                                                       if (auoID.isChecked()){
//                                                                                           if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                               String val = "0";
//                                                                                               save_date(val);
//                                                                                           }
//                                                                                       }
//
//                                                                                   }
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                       //NNN
//                                                                       if (notproperiyfilledID.isChecked()){
//                                                                           if (notavalableID.isChecked()){
//                                                                               if (nAID.isChecked()){
//                                                                                   String val = "00";
//                                                                                   save_date(val);
//                                                                               }
//
//                                                                           }
//                                                                       }
//                                                                       //AANN
//                                                                       if (properiyfilledID.isChecked()){
//                                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                               if (avablesuffID.isChecked()) {
//                                                                                   if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                       if (nAID.isChecked()){
//                                                                                               String val = "000";
//                                                                                               save_date(val);
//                                                                                       }
//                                                                                   }
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                       //NNA
//                                                                       if (notproperiyfilledID.isChecked()){
//                                                                           if (notavalableID.isChecked()){
//                                                                               if (auoID.isChecked()){
//                                                                                   if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                       String val = "0000";
//                                                                                       save_date(val);
//                                                                                   }
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                       //ANA
//                                                                       if (properiyfilledID.isChecked()) {
//                                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                               if (notavalableID.isChecked()){
//                                                                                   if (auoID.isChecked()){
//                                                                                       if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                           String val = "00000";
//                                                                                           save_date(val);
//                                                                                       }
//                                                                                   }
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                      //NAN
//                                                                       if (notproperiyfilledID.isChecked()){
//                                                                           if (avablesuffID.isChecked()) {
//                                                                               if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                   if (nAID.isChecked()){
//                                                                                       String val = "000000";
//                                                                                       save_date(val);
//                                                                                   }
//                                                                               }}
//                                                                       }
//                                                                       ///////////////////////
//                                                                   }
//                                                               }
//                                                            }
//                                                        }
//                                                    }
//                                                }
////////////////////////////////////////////////////////////////////////////////////////
//                                                //NF NF NF
//                                                if (babyaverageID.isChecked()){
//                                                    if (averageadlID.isChecked()){
//                                                        if (averagehmtID.isChecked()){
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                    //AAA
//                                                                    if (avablesuffID.isChecked()){
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                            //AAA
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "1";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    ///////////////////////////////////////////////////
//                                                                    //NNN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "11";
//                                                                                save_date(val);
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //AANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                            if (avablesuffID.isChecked()) {
//                                                                                if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                    if (nAID.isChecked()){
//                                                                                        String val = "111";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NNA
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "1111";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //ANA
//                                                                    if (properiyfilledID.isChecked()) {
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (auoID.isChecked()){
//                                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                        String val = "11111";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NAN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "111111";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }}
//                                                                    }
//                                                                    //ANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "1111111";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    ///////////////////////
//                                                                }
//                                                            }
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "11k";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                ///NNN
//                                                if (babynotAvID.isChecked()){
//                                                    if (notaadlID.isChecked()){
//                                                        if (nahmtID.isChecked()){
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                    //AAA
//                                                                    if (avablesuffID.isChecked()){
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                            //AAA
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "2";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //NNN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "22";
//                                                                                save_date(val);
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //AANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                            if (avablesuffID.isChecked()) {
//                                                                                if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                    if (nAID.isChecked()){
//                                                                                        String val = "222";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NNA
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "2222";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //ANA
//                                                                    if (properiyfilledID.isChecked()) {
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (auoID.isChecked()){
//                                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                        String val = "22222";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NAN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "222222";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }}
//                                                                    }
//                                                                    //ANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "2222222";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    /////////////////////////////////////////////
//                                                                }
//                                                            }
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "22";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                //check
//                                                ///F NF F
//                                               if (babygoodID.isChecked()){
//                                                   if (useId.isChecked() || nouseId.isChecked()){
//                                                       if (averageadlID.isChecked()){
//                                                       if (goodhmtID.isChecked()){
//                                                           if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                               //AAA
//                                                               if (avablesuffID.isChecked()){
//                                                                   if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                       //AAA
//                                                                       if (auoID.isChecked()){
//                                                                           if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                               String val = "3";
//                                                                               save_date(val);
//                                                                           }
//                                                                       }
//
//                                                                   }
//                                                               }
//                                                               //NNN
//                                                               if (notproperiyfilledID.isChecked()){
//                                                                   if (notavalableID.isChecked()){
//                                                                       if (nAID.isChecked()){
//                                                                           String val = "33";
//                                                                           save_date(val);
//                                                                       }
//
//                                                                   }
//                                                               }
//                                                               //AAN
//                                                               if (properiyfilledID.isChecked()){
//                                                                   if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                       if (avablesuffID.isChecked()) {
//                                                                           if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                               if (nAID.isChecked()){
//                                                                                   String val = "333";
//                                                                                   save_date(val);
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                   }
//                                                               }
//                                                               //NNA
//                                                               if (notproperiyfilledID.isChecked()){
//                                                                   if (notavalableID.isChecked()){
//                                                                       if (auoID.isChecked()){
//                                                                           if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                               String val = "3333";
//                                                                               save_date(val);
//                                                                           }
//                                                                       }
//                                                                   }
//                                                               }
//                                                               //ANA
//                                                               if (properiyfilledID.isChecked()) {
//                                                                   if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                       if (notavalableID.isChecked()){
//                                                                           if (auoID.isChecked()){
//                                                                               if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                   String val = "33333";
//                                                                                   save_date(val);
//                                                                               }
//                                                                           }
//                                                                       }
//                                                                   }
//                                                               }
//                                                               //NAN
//                                                               if (notproperiyfilledID.isChecked()){
//                                                                   if (avablesuffID.isChecked()) {
//                                                                       if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                           if (nAID.isChecked()){
//                                                                               String val = "333333";
//                                                                               save_date(val);
//                                                                           }
//                                                                       }}
//                                                               }
//                                                               //ANN
//                                                               if (properiyfilledID.isChecked()){
//                                                                   if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                       if (notavalableID.isChecked()){
//                                                                           if (nAID.isChecked()){
//                                                                               String val = "3333333";
//                                                                               save_date(val);
//                                                                           }
//                                                                       }
//                                                                   }
//                                                               }
//                                                               /////////////////////////////////////////////
//
//                                                           }
//                                                           if (notproperiyfilledID.isChecked()){
//                                                               if (notavalableID.isChecked()){
//                                                                   if (nAID.isChecked()){
//                                                                       String val = "33";
//                                                                       save_date(val);
//                                                                   }
//
//                                                               }
//                                                           }
//                                                       }
//                                                       }
//                                                   }
//
//                                               }
//                                                //FF NF
//                                                if (babygoodID.isChecked()) {
//                                                    if (useId.isChecked() || nouseId.isChecked()) {
//                                                        if (goodadlID.isChecked()){
//                                                            if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                                if (averagehmtID.isChecked()){
//                                                                    //AAA
//                                                                    if (avablesuffID.isChecked()){
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                            //AAA
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "4";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //NNN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "44";
//                                                                                save_date(val);
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //AAN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                            if (avablesuffID.isChecked()) {
//                                                                                if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                    if (nAID.isChecked()){
//                                                                                        String val = "444";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NNA
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "4444";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //ANA
//                                                                    if (properiyfilledID.isChecked()) {
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (auoID.isChecked()){
//                                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                        String val = "44444";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NAN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "444444";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }}
//                                                                    }
//                                                                    //ANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "4444444";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//
//                                                                    /////////////////////////////////////////////
//
//                                                                }
//                                                            }
//
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "44";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//
//                                                }
//                                                //NF F F
//                                                if (babyaverageID.isChecked()) {
//                                                    if (goodadlID.isChecked()) {
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()) {
//                                                            if (goodhmtID.isChecked()) {
//                                                                if (heightusedId.isChecked() || hightnotuseId.isChecked()) {
//                                                                    //AAA
//                                                                    if (avablesuffID.isChecked()){
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                            //AAA
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "5";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //NNN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "55";
//                                                                                save_date(val);
//                                                                            }
//
//                                                                        }
//                                                                    }
//                                                                    //AAN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                            if (avablesuffID.isChecked()) {
//                                                                                if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                    if (nAID.isChecked()){
//                                                                                        String val = "555";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NNA
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "5555";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //ANA
//                                                                    if (properiyfilledID.isChecked()) {
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (auoID.isChecked()){
//                                                                                    if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                        String val = "55555";
//                                                                                        save_date(val);
//                                                                                    }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                    //NAN
//                                                                    if (notproperiyfilledID.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "55555";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }}
//                                                                    }
//                                                                    //ANN
//                                                                    if (properiyfilledID.isChecked()){
//                                                                        if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                            if (notavalableID.isChecked()){
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "555555";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//
//                                                                    /////////////////////////////////////////////
//
//                                                                }
//
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "55";
//                                                                            save_date(val);
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                            } } }
//                                                }
//
//                                           ////NNF
//                                                if (babynotAvID.isChecked()){
//                                                    if (notaadlID.isChecked()){
//                                                        if (goodhmtID.isChecked()){
//                                                            if (heightusedId.isChecked() || hightnotuseId.isChecked()){
//                                                                //AAA
//                                                                if (avablesuffID.isChecked()){
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                        //AAA
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "6";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //NNN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "66";
//                                                                            save_date(val);
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //AAN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "666";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NNA
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "6666";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //ANA
//                                                                if (properiyfilledID.isChecked()) {
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "66666";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NAN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (avablesuffID.isChecked()) {
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "666666";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }}
//                                                                }
//                                                                //ANN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "6666666";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//
//                                                                /////////////////////////////////////////////
//                                                            }
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "66";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                //NN NF
//                                                if (babynotAvID.isChecked()) {
//                                                    if (notaadlID.isChecked()) {
//                                                     if (averagehmtID.isChecked()){
//                                                         //AAA
//                                                         if (avablesuffID.isChecked()){
//                                                             if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                 //AAA
//                                                                 if (auoID.isChecked()){
//                                                                     if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                         String val = "7";
//                                                                         save_date(val);
//                                                                     }
//                                                                 }
//
//                                                             }
//                                                         }
//                                                         //NNN
//                                                         if (notproperiyfilledID.isChecked()){
//                                                             if (notavalableID.isChecked()){
//                                                                 if (nAID.isChecked()){
//                                                                     String val = "77";
//                                                                     save_date(val);
//                                                                 }
//
//                                                             }
//                                                         }
//                                                         //AAN
//                                                         if (properiyfilledID.isChecked()){
//                                                             if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                 if (avablesuffID.isChecked()) {
//                                                                     if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                         if (nAID.isChecked()){
//                                                                             String val = "777";
//                                                                             save_date(val);
//                                                                         }
//                                                                     }
//                                                                 }
//                                                             }
//                                                         }
//                                                         //NNA
//                                                         if (notproperiyfilledID.isChecked()){
//                                                             if (notavalableID.isChecked()){
//                                                                 if (auoID.isChecked()){
//                                                                     if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                         String val = "7777";
//                                                                         save_date(val);
//                                                                     }
//                                                                 }
//                                                             }
//                                                         }
//                                                         //ANA
//                                                         if (properiyfilledID.isChecked()) {
//                                                             if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                 if (notavalableID.isChecked()){
//                                                                     if (auoID.isChecked()){
//                                                                         if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                             String val = "77777";
//                                                                             save_date(val);
//                                                                         }
//                                                                     }
//                                                                 }
//                                                             }
//                                                         }
//                                                         //NAN
//                                                         if (notproperiyfilledID.isChecked()){
//                                                             if (avablesuffID.isChecked()) {
//                                                                 if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                     if (nAID.isChecked()){
//                                                                         String val = "777777";
//                                                                         save_date(val);
//                                                                     }
//                                                                 }}
//                                                         }
//                                                         //ANN
//                                                         if (properiyfilledID.isChecked()){
//                                                             if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                 if (notavalableID.isChecked()){
//                                                                     if (nAID.isChecked()){
//                                                                         String val = "7777777";
//                                                                         save_date(val);
//                                                                     }
//                                                                 }
//                                                             }
//                                                         }
//
//                                                         /////////////////////////////////////////////
//                                                     }
//
//                                                        if (notproperiyfilledID.isChecked()){
//                                                            if (notavalableID.isChecked()){
//                                                                if (nAID.isChecked()){
//                                                                    String val = "77";
//                                                                    save_date(val);
//                                                                }
//
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                //N F NF
//                                                if (babynotAvID.isChecked()){
//                                                    if (goodadlID.isChecked()){
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                            if (averagehmtID.isChecked()){
//                                                                //AAA
//                                                                if (avablesuffID.isChecked()){
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                        //AAA
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "8";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //NNN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "88";
//                                                                            save_date(val);
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //AAN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "888";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NNA
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "8888";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //ANA
//                                                                if (properiyfilledID.isChecked()) {
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "88888";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NAN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (avablesuffID.isChecked()) {
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "888888";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }}
//                                                                }
//                                                                //ANN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "8888888";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//
//                                                                /////////////////////////////////////////////
//
//                                                            }
//
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "88";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                //N F N
//                                                if (babynotAvID.isChecked()){
//                                                    if (goodadlID.isChecked()){
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                            if (nahmtID.isChecked()){
//                                                                //AAA
//                                                                if (avablesuffID.isChecked()){
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                        //AAA
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "9";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //NNN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "99";
//                                                                            save_date(val);
//                                                                        }
//
//                                                                    }
//                                                                }
//                                                                //AAN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                        if (avablesuffID.isChecked()) {
//                                                                            if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                                if (nAID.isChecked()){
//                                                                                    String val = "999";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NNA
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "9999";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //ANA
//                                                                if (properiyfilledID.isChecked()) {
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (auoID.isChecked()){
//                                                                                if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                    String val = "99999";
//                                                                                    save_date(val);
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                                //NAN
//                                                                if (notproperiyfilledID.isChecked()){
//                                                                    if (avablesuffID.isChecked()) {
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "999999";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }}
//                                                                }
//                                                                //ANN
//                                                                if (properiyfilledID.isChecked()){
//                                                                    if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                        if (notavalableID.isChecked()){
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "9999999";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//
//                                                                /////////////////////////////////////////////
//
//                                                            }
//
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "99";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                        }
//
//                                                    }
//                                                }
//                                               // N F F
//                                                if (babynotAvID.isChecked()){
//                                                    if (goodadlID.isChecked()){
//                                                        if (useadult.isChecked() || nouseadultId.isChecked()){
//                                                            //AAA
//                                                            if (avablesuffID.isChecked()){
//                                                                if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                                    //AAA
//                                                                    if (auoID.isChecked()){
//                                                                        if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                            String val = "9A";
//                                                                            save_date(val);
//                                                                        }
//                                                                    }
//
//                                                                }
//                                                            }
//                                                            //NNN
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (nAID.isChecked()){
//                                                                        String val = "99A";
//                                                                        save_date(val);
//                                                                    }
//
//                                                                }
//                                                            }
//                                                            //AAN
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                                    if (avablesuffID.isChecked()) {
//                                                                        if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                            if (nAID.isChecked()){
//                                                                                String val = "999A";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                            //NNA
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (notavalableID.isChecked()){
//                                                                    if (auoID.isChecked()){
//                                                                        if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                            String val = "9999A";
//                                                                            save_date(val);
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                            //ANA
//                                                            if (properiyfilledID.isChecked()) {
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (auoID.isChecked()){
//                                                                            if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                                String val = "99999A";
//                                                                                save_date(val);
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                            //NAN
//                                                            if (notproperiyfilledID.isChecked()){
//                                                                if (avablesuffID.isChecked()) {
//                                                                    if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "999999A";
//                                                                            save_date(val);
//                                                                        }
//                                                                    }}
//                                                            }
//                                                            //ANN
//                                                            if (properiyfilledID.isChecked()){
//                                                                if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                                    if (notavalableID.isChecked()){
//                                                                        if (nAID.isChecked()){
//                                                                            String val = "9999999A";
//                                                                            save_date(val);
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//
//                                                            /////////////////////////////////////////////
//                                                        }
//
//                                                        if (notproperiyfilledID.isChecked()){
//                                                            if (notavalableID.isChecked()){
//                                                                if (nAID.isChecked()){
//                                                                    String val = "99A";
//                                                                    save_date(val);
//                                                                }
//
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                                // N NF NF
//                                           if (babynotAvID.isChecked()){
//                                               if (averageadlID.isChecked()){
//                                                   if (averagehmtID.isChecked()){
//                                                       //AAA
//                                                       if (avablesuffID.isChecked()){
//                                                           if (saficentId.isChecked() || notsuficientId.isChecked()){
//                                                               //AAA
//                                                               if (auoID.isChecked()){
//                                                                   if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                       String val = "9B";
//                                                                       save_date(val);
//                                                                   }
//                                                               }
//
//                                                           }
//                                                       }
//                                                       //NNN
//                                                       if (notproperiyfilledID.isChecked()){
//                                                           if (notavalableID.isChecked()){
//                                                               if (nAID.isChecked()){
//                                                                   String val = "99B";
//                                                                   save_date(val);
//                                                               }
//
//                                                           }
//                                                       }
//                                                       //AAN
//                                                       if (properiyfilledID.isChecked()){
//                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()){
//                                                               if (avablesuffID.isChecked()) {
//                                                                   if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                       if (nAID.isChecked()){
//                                                                           String val = "999B";
//                                                                           save_date(val);
//                                                                       }
//                                                                   }
//                                                               }
//                                                           }
//                                                       }
//                                                       //NNA
//                                                       if (notproperiyfilledID.isChecked()){
//                                                           if (notavalableID.isChecked()){
//                                                               if (auoID.isChecked()){
//                                                                   if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                       String val = "9999B";
//                                                                       save_date(val);
//                                                                   }
//                                                               }
//                                                           }
//                                                       }
//                                                       //ANA
//                                                       if (properiyfilledID.isChecked()) {
//                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                               if (notavalableID.isChecked()){
//                                                                   if (auoID.isChecked()){
//                                                                       if (gandshopyesId.isChecked() || handshpnoId.isChecked()){
//                                                                           String val = "99999B";
//                                                                           save_date(val);
//                                                                       }
//                                                                   }
//                                                               }
//                                                           }
//                                                       }
//                                                       //NAN
//                                                       if (notproperiyfilledID.isChecked()){
//                                                           if (avablesuffID.isChecked()) {
//                                                               if (saficentId.isChecked() || notsuficientId.isChecked()) {
//                                                                   if (nAID.isChecked()){
//                                                                       String val = "999999B";
//                                                                       save_date(val);
//                                                                   }
//                                                               }}
//                                                       }
//                                                       //ANN
//                                                       if (properiyfilledID.isChecked()){
//                                                           if (grothproperId.isChecked() || notgrothproperId.isChecked()) {
//                                                               if (notavalableID.isChecked()){
//                                                                   if (nAID.isChecked()){
//                                                                       String val = "9999999B";
//                                                                       save_date(val);
//                                                                   }
//                                                               }
//                                                           }
//                                                       }
//                                                       /////////////////////////////////////////////
//                                                   }
//
//                                                   if (notproperiyfilledID.isChecked()){
//                                                       if (notavalableID.isChecked()){
//                                                           if (nAID.isChecked()){
//                                                               String val = "99B";
//                                                               save_date(val);
//                                                           }
//
//                                                       }
//                                                   }
//                                               }
//                                           }
                                               /////////////////////SUB///////

                                           }
                                           else {
                                               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP", Toast.LENGTH_SHORT).show();
                                               String title = "Message Box";
                                               String msg = "SELECT HAND WASHING SOAP";
                                               createDialog(title,msg);
                                           }
                                       }
                                       else {
                                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FOR STORING", Toast.LENGTH_SHORT).show();
                                           String title = "Message Box";
                                           String msg = "SELECT CONTAINERS FOR STORING";
                                           createDialog(title,msg);
                                       }

                                   }
                                   else {
                                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART", Toast.LENGTH_SHORT).show();
                                       String title = "Message Box";
                                       String msg = "SELECT GROTH CHART";
                                       createDialog(title,msg);
                                   }

                               }
                               else {
                                   Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HEIGHT MEASURING TAPE", Toast.LENGTH_SHORT).show();
                                   String title = "Message Box";
                                   String msg = "SELECT HEIGHT MEASURING TAPE";
                                   createDialog(title,msg);

                               }
                           }
                           else {
                               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADULT WEIGHING MACHINE", Toast.LENGTH_SHORT).show();
                               String title = "Message Box";
                               String msg = "SELECT ADULT WEIGHING MACHINE";
                               createDialog(title,msg);
                           }
                       }
                       else {
                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONDITION OF BABY WEIGHING MACHINE", Toast.LENGTH_SHORT).show();
                           String title = "Message Box";
                           String msg = "SELECT CONDITION OF BABY WEIGHING MACHINE";
                           createDialog(title,msg);

                       }

                       if (babygoodID.isChecked()){
                           if (useId.isChecked() || nouseId.isChecked()){
                               if (goodadlID.isChecked()){
                                   if (useadult.isChecked() || nouseadultId.isChecked()){
                                       if (goodhmtID.isChecked()){
                                           if (heightusedId.isChecked() || hightnotuseId.isChecked()){
                                               if (properiyfilledID.isChecked()){
                                                   if (grothproperId.isChecked() || notgrothproperId.isChecked()){
                                                       if (avablesuffID.isChecked()){
                                                           if (saficentId.isChecked() || notsuficientId.isChecked()){
                                                               if (auoID.isChecked()){
                                                                   if (gandshopyesId.isChecked() || handshpnoId.isChecked()){


                                                                   }
                                                                   else {
                                                                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HAND WASHING SOAP USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                                                       String title = "Message Box";
                                                                       String msg = "SELECT HAND WASHING SOAP USED OR NOT USED";
                                                                       createDialog(title,msg);
                                                                   }
                                                               }


                                                           }
                                                           else {
                                                               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONTAINERS FORSTORINGSUFFICIENT OR NOT SUFFICIENT", Toast.LENGTH_SHORT).show();
                                                               String title = "Message Box";
                                                               String msg = "SELECT CONTAINERS FORSTORINGSUFFICIENT OR NOT SUFFICIENT";
                                                               createDialog(title,msg);
                                                           }

                                                       }

                                                   }
                                                   else {
                                                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT GROTH CHART PROPERLY OR NOT PROPERLY", Toast.LENGTH_SHORT).show();
                                                       String title = "Message Box";
                                                       String msg = "SELECT GROTH CHART PROPERLY OR NOT PROPERLY";
                                                       createDialog(title,msg);
                                                   }
                                               }


                                           }
                                           else {
                                               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT HEIGHT MEASURING TAPE USE OR NO USE", Toast.LENGTH_SHORT).show();
                                               String title = "Message Box";
                                               String msg = "SELECT ADULT WEIGHING USED OR NOT USED";
                                               createDialog(title,msg);
                                           }
                                       }

                                   }
                                   else {
                                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT ADULT WEIGHING USED OR NOT USED", Toast.LENGTH_SHORT).show();
                                       String title = "Message Box";
                                       String msg = "SELECT ADULT WEIGHING USED OR NOT USED";
                                       createDialog(title,msg);
                                   }
                               }
                           }
                           else {
                               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this, "SELECT CONDITION OF BABAY WEIGHING MACHINE USED OR NOT USED", Toast.LENGTH_SHORT).show();
                               String title = "Message Box";
                               String msg = "SELECT MAINTAINED UP TO DATE OR NOT MAINTAINED";
                               createDialog(title,msg);
                           }
                       }

                   } else {

                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT MAINTAINED UP TO DATE OR NOT MAINTAINED",Toast.LENGTH_SHORT).show();
                       String title = "Message Box";
                       String msg = "SELECT MAINTAINED UP TO DATE OR NOT MAINTAINED";
                       createDialog(title,msg);
                   }

               }

           }
           else {
               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT CONDITION OF MAT / CHATAI",Toast.LENGTH_SHORT).show();
               String title = "Message Box";
               String msg = "SELECT CONDITION OF MAT / CHATAI";
               createDialog(title,msg);
           }
       }
       else {
           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT UTENSIL CONDITION",Toast.LENGTH_SHORT).show();
           String title = "Message Box";
           String msg = "SELECT UTENSIL CONDITION";
           createDialog(title,msg);
       }

   }
    public void save_date1(final String val){
        Log.e("SUBMIT",val);
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CONDITIONEQUIPMENTOTHERSAWC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SHIAYE"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("SHIAYEOBJ"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                Log.e("UPDATE"," "+message);
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET conditionofequipmentotherawc='1' WHERE allinspactionid=" +insid);
                                //syncConditionsaveDatabase(insid,utensilcondtn,matconditn,elevnregstr,babywmachin,adultwmachin,muactape,heightmeasurtyp,growthchrt,storecontainr,handwashsoap,curTime,CONDITION_SYNCED_WITH_SERVER);

                                if (babywmachin.equals("f")){
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);

                                }
                                else {
                                    conditionbabyweighingmechin_used_notoused ="nna";
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                if (adultwmachin.equals("f")){
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                else {
                                    adualtweighingmachine_used_notused = "nna";
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                if (heightmeasurtyp.equals("f")){
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                else {
                                    height_measuring_type ="nna";
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                if (growthchrt.equals("a")){
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                else {
                                    proper_filled_up ="nna";
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                if (storecontainr.equals("a")){
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                else {
                                    suficent ="nna";
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }

                                if (handwashsoap.equals("a")){
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
                                }
                                else {
                                    hand_washing_sope = "nna";
                                    syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_SYNCED_WITH_SERVER);
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
                        dbb.execSQL("UPDATE insflag SET conditionofequipmentotherawc='1' WHERE allinspactionid=" +insid);
                        if (babywmachin.equals("f")){
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);

                        }
                        else {
                            conditionbabyweighingmechin_used_notoused ="nna";
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        if (adultwmachin.equals("f")){
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        else {
                            adualtweighingmachine_used_notused = "nna";
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        if (heightmeasurtyp.equals("f")){
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        else {
                            height_measuring_type ="nna";
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        if (growthchrt.equals("a")){
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        else {
                            proper_filled_up ="nna";
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        if (storecontainr.equals("a")){
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        else {
                            suficent ="nna";
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }

                        if (handwashsoap.equals("a")){
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                        else {
                            hand_washing_sope = "nna";
                            syncConditionsaveDatabase1(insid,utensilcondtn,matconditn,elevnregstr,elavenregister,babywmachin,conditionbabyweighingmechin_used_notoused,adultwmachin, adualtweighingmachine_used_notused,heightmeasurtyp,height_measuring_type,growthchrt,proper_filled_up,storecontainr,suficent,handwashsoap,hand_washing_sope,curTime, CONDITION_NOT_SYNCED_WITH_SERVER);
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("utensil_condtn",utensilcondtn);
                params.put("mat_conditn",matconditn);
                params.put("elevn_regstr",elevnregstr);
                params.put("elevn_regstr_no",elavenregister);
                params.put("baby_wmachin",babywmachin);
                if (babywmachin.equals("f")){
                    params.put("baby_wmachin_use",conditionbabyweighingmechin_used_notoused);
                }
                else {
                    conditionbabyweighingmechin_used_notoused ="nna";
                    params.put("baby_wmachin_use",conditionbabyweighingmechin_used_notoused);
                }
                params.put("adult_wmachin",adultwmachin);
                // params.put("adult_wmachin","");
                if (adultwmachin.equals("f")){
                    params.put("adult_wmachin_use",adualtweighingmachine_used_notused);
                }
                else {
                    adualtweighingmachine_used_notused = "nna";
                    params.put("adult_wmachin_use",adualtweighingmachine_used_notused);
                }

                //params.put("muac_tape",muactape);
                params.put("height_measur_typ",heightmeasurtyp);
                if (heightmeasurtyp.equals("f")){
                    params.put("height_measur_typ_use",height_measuring_type);
                }
                else {
                    height_measuring_type ="nna";
                    params.put("height_measur_typ_use",height_measuring_type);
                }

                params.put("growth_chrt",growthchrt);
                if (growthchrt.equals("a")){
                    params.put("growth_chrt_fillup",proper_filled_up);
                }
                else {
                    proper_filled_up ="nna";
                    params.put("growth_chrt_fillup",proper_filled_up);
                }
                params.put("store_containr",storecontainr);
                if (storecontainr.equals("a")){
                    params.put("suffi_store_containr",suficent);
                }
                else {
                    suficent ="nna";
                    params.put("suffi_store_containr",suficent);
                }

                params.put("hand_wash_soap",handwashsoap);
                if (handwashsoap.equals("a")){
                    params.put("hand_wash_soap_use",hand_washing_sope);
                }
                else {
                    hand_washing_sope = "nna";
                    params.put("hand_wash_soap_use",hand_washing_sope);
                }
                params.put("ins_time",curTime);
                Log.e("STORELOG",insid+" "+" "+utensilcondtn+" "+matconditn+" "+elevnregstr+" "+" "+elavenregister+" "+babywmachin+""+conditionbabyweighingmechin_used_notoused+
                        " "+adultwmachin+" "+adualtweighingmachine_used_notused+" "+" "+"  "+heightmeasurtyp+" "+height_measuring_type+" "+growthchrt+" "+proper_filled_up+" "+storecontainr+" "+suficent+" "+handwashsoap+" "+hand_washing_sope+" "+curTime);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    private void syncConditionsaveDatabase1(String insid,
                                            String utensilcondtn,
                                            String matconditn,
                                            String elevnregstr,
                                            String elavenregister,
                                            String babywmachin,
                                            String conditionbabyweighingmechinusednotoused,
                                            String adultwmachin,
                                            String adualtweighingmachineusednotused,
                                            String heightmeasurtyp,
                                            String heightmeasuringtype,
                                            String growthchrt,
                                            String properfilledup,
                                            String storecontainr,
                                            String suficent,
                                            String handwashsoap,
                                            String hand_washing_sope,
                                            String curTime,
                                            int conditionstatus){

        if (conditions.equals("0")) {
            helper.CONDITIONINSERT(
                    insid,
                    utensilcondtn,
                    matconditn,
                    elevnregstr,
                    elavenregister,
                    babywmachin,
                    conditionbabyweighingmechinusednotoused,
                    adultwmachin,
                    adualtweighingmachineusednotused,
                    heightmeasurtyp,
                    heightmeasuringtype,
                    growthchrt,
                    properfilledup,
                    storecontainr,
                    suficent,
                    handwashsoap,
                    hand_washing_sope,
                    curTime,
                    conditionstatus);
        }
        else {
            helper.CONDITIONUPDATE(dbid,insid,
                    utensilcondtn,
                    matconditn,
                    elevnregstr,
                    elavenregister,
                    babywmachin,
                    conditionbabyweighingmechinusednotoused,
                    adultwmachin,
                    adualtweighingmachineusednotused,
                    heightmeasurtyp,
                    heightmeasuringtype,
                    growthchrt,
                    properfilledup,
                    storecontainr,
                    suficent,
                    handwashsoap,
                    hand_washing_sope,
                    curTime,
                    conditionstatus);
        }
        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(ConditionOfEquipmentOthersAWCActivity.this, InspectionListActivity.class);
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
//            case R.id.saveID:
//               if (goodutensilID.isChecked() || averageutensilID.isChecked() || poorutensilID.isChecked() || notavalutensilID.isChecked()){
//                   if (goodmcID.isChecked() || averagemcID.isChecked() || poormcID.isChecked() || notavelemcID.isChecked()){
//                       if (goodelevenID.isChecked() || averageelevenID.isChecked() || poorelevenID.isChecked() || NableevenID.isChecked()){
//                           if (babygoodID.isChecked() || babyaverageID.isChecked() || babypoorID.isChecked() || babynotAvID.isChecked()){
//                               if (goodadlID.isChecked() || averageadlID.isChecked() || pooradlID.isChecked() || notaadlID.isChecked()){
//                                   if (goodmuacID.isChecked() || averagemuacID.isChecked() || pooremuacID.isChecked() || naemuacID.isChecked()){
//                                       if (goodhmtID.isChecked() || averagehmtID.isChecked() || poorhmtID.isChecked() || nahmtID.isChecked()){
//                                           if (properiyfilledID.isChecked() || improperiyfilledID.isChecked() || notproperiyfilledID.isChecked()){
//                                               if (avablesuffID.isChecked() || avablenotsuffID.isChecked() || notavalableID.isChecked()){
//                                                   if (auoID.isChecked() || nAID.isChecked()){
//                                                       update_equipment_condition_inspctn();
//                                                   }
//                                                   else {
//                                                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HAND WASHING SOAP",Toast.LENGTH_SHORT).show();
//                                                   }
//                                               }
//                                               else {
//                                                   Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT STRONG CONTAINERS",Toast.LENGTH_SHORT).show();
//                                               }
//                                           }
//                                           else {
//                                               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT GROWTHCHART",Toast.LENGTH_SHORT).show();
//                                           }
//                                       }
//                                       else {
//                                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT HEIGHT MEASURING TAPE",Toast.LENGTH_SHORT).show();
//                                       }
//                                   }
//                                   else {
//                                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT MUAC TAPE",Toast.LENGTH_SHORT).show();
//                                   }
//                               }
//                               else {
//                                   Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT ADLT. WEIGHING MACHINE",Toast.LENGTH_SHORT).show();
//                               }
//                           }
//                           else {
//                               Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT BABY W MACHINE",Toast.LENGTH_SHORT).show();
//                           }
//                       }
//                       else {
//                           Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT ELEVEN REGISTYERS",Toast.LENGTH_SHORT).show();
//                       }
//                   }
//                   else {
//                       Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT MAT CHATAI",Toast.LENGTH_SHORT).show();
//                   }
//               }
//               else {
//                   Toast.makeText(ConditionOfEquipmentOthersAWCActivity.this,"SELECT UTENSIL CONDITION",Toast.LENGTH_SHORT).show();
//               }
//               break;
//                default:
//        }
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
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
        try{
            if(conditionNetwokchecker!=null)
                unregisterReceiver(conditionNetwokchecker);
            if (broadcastReceiverecondition!=null)
                unregisterReceiver(broadcastReceiverecondition);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
