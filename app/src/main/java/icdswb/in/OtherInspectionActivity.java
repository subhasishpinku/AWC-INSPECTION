package icdswb.in;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityDatabase.FinalSubmitNetworkcheck;
import icdswb.in.ActivityDatabase.OtherinspactionNetworkchecker;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSUSERID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPECTIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NAMEAWCSDTL;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;


public class OtherInspectionActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
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
    String planid = "NA";
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
    //String currentdate = "NA";
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
    String currentdate = "NA";
    EditText dfiEditID,afasEditID,acpnEditID,vsaEditID,mklrecivedID,presklrecvID,gclreciveID,cmdID;
    RadioGroup GmmorID,amcfamhID,visitofunID,mrID,fevcrrID,GPmedicinkitId,GPuseId,GbccID;
    RadioButton YmmorID,NmmorID,YamcfamhID,NamcfamhID,YvisitofunID,NvisitofunID,YmrID,NmrID,YfevcrrID,
            NfevcrrID,YmedicinkitId,NmedicinkitId,UuseId,NUuseId,bccGoodID,bccAverage,bccPoor;
    Button savebtn;
    String curDate,curTime,userID,dbid;
    String mthrmeet = "NA";
    String awmonitr = "NA";
    String othrfunctnvst = "NA";
    String rcdmaintain = "NA";
    String regulrchrgrcv = "NA";
    String feedintrupduratn = "NA";
    String supactn = "NA";
    String cmuntyprticptnnotice= "NA";
    String supvisit= "NA";
    String medcinchrglstrcv= "NA";
    String preschlchrglstrcv= "NA";
    String grwthchrtlstrcv= "NA";
    String awcremark= "NA";
    String IdOther= "NA";
    String buildingdetails= "NA";
    String informationoftoilet= "NA";
    String informationofkitchen= "NA";
    String adequatespaceforpse= "NA";
    String electricity= "NA";
    String drinkingwater= "NA";
    String foodstoreddetails= "NA";
    String conditionofequipmentotherawc= "NA";
    String shishualoy= "NA";
    String snpserved= "NA";
    String nutritionalstatusobserved= "NA";
    String allinspactionid= "NA";
    String otherName= "NA";
    String Efeedintrupduratn= "NA";
    String Esupactn= "NA";
    String Ecmuntyprticptnnotice= "NA";
    String Esupvisit= "NA";
    String lastsupustidate= "NA";
    String Emthrmeet= "NA";
    String Eawmonitr= "NA";
    String Eothrfunctnvst= "NA";
    String Ercdmaintain= "NA";
    String Eregulrchrgrcv= "NA";
    String Emedcinchrglstrcv= "NA";
    String Epreschlchrglstrcv= "NA";
    String Egrwthchrtlstrcv= "NA";
    String Eawcremark= "NA";
    String last_recived= "NA";
    String used_of_awc= "NA";
    String gen_goth= "NA";
    String medcinkitlstrcv= "NA";
    String medcinkitst= "NA";
    String medcinkitusest= "NA";
    String awcgnrlclr= "NA";
    DatabaseHelper helper;
    public static final int OTHER_SYNCED_WITH_SERVER = 1;
    public static final int OTHER_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiverother;
    public static final String DATA_SAVED_BROADCAST_OTHER = "icdswb.in.othersaved";
    private OtherinspactionNetworkchecker otherinspactionNetworkchecker;

    public static final int FINAL_SUB_WITH_SERVER = 1;
    public static final int FINAL_SUB_WITH_SERVER_NOT = 0;
    public static final String DATA_SAVED_BROADCAST_FINAL = "icdswb.in.othersaved";
    private BroadcastReceiver broadcastReceiverotherr;
    private FinalSubmitNetworkcheck finalSubmitNetworkcheck;
    String OTHERTATUS = "0";
    String feedintrupduratndb ="";
    String supactndb = "";
    String cmuntyprticptnnoticedb ="";
    String supvisitdb = "";
    String mthrmeetdb ="";
    String awmonitrdb ="";
    String othrfunctnvstdb ="";
    String rcdmaintaindb ="";
    String regulrchrgrcvdb ="";
    String medcinchrglstrcvdb ="";
    String preschlchrglstrcvdb = "";
    String grwthchrtlstrcvdb = "";
    String awcremarkdb ="";
    TextView awcstvId;
    ImageButton dfispack,afasspak,acpnspak,vsaspak,mklrecivedspeak,presklrecvspeak,gclrecivespake,cmdspak;
    private final int REQ_CODE_SPEECH_DFISPACK = 100;
    private final int REQ_CODE_SPEECH_AFASSPAK = 101;
    private final int REQ_CODE_SPEECH_ACPNSPAK = 102;
    private final int REQ_CODE_SPEECH_VSASPAK = 103;
    private final int REQ_CODE_SPEECH_MKLRECIVEDSPEAK = 104;
    private final int REQ_CODE_SPEECH_PRESKLRECVSPEAK = 105;
    private final int REQ_CODE_SPEECH_GCLRECIVESPAKE = 106;
    private final int REQ_CODE_SPEECH_CMDSPAK = 107;
    ImageView searchID,SPmothermeetingId,medicindateIdd;
    TextView FdateId,mothermeetingId,medicindateId;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    Calendar myCalendarr;
    DatePickerDialog.OnDateSetListener datee;
    Calendar myCalendarrr;
    DatePickerDialog.OnDateSetListener dateee;
    String ecckitdate = "Date";
    String eccobserdate = "Date";
    String medicinkitlaastrecived = "Date";
    LinearLayout LVinuseId;
    String curdate;
    String discode,userid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otherinspection);
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
        Toast.makeText(getApplicationContext(),dbid,Toast.LENGTH_SHORT).show();
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
//        dbid = intent.getStringExtra("dbid");
//        Log.e("other_dbid",dbid);

        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        Log.e("OtherP",planid);
        Log.e("Other"," "+toilet+" "+awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+insid+" ");
       // kitchen = intent.getStringExtra("kitchen");
      //  adqutspacepse = intent.getStringExtra("adqutspacepse");
      //  electric = intent.getStringExtra("electric");
      //  water = intent.getStringExtra("water");
      //  foodspace = intent.getStringExtra("foodspace");
        Log.e("Other"," "+kitchen+" "+adqutspacepse+" "+electric+" "+water+" "+foodspace+" ");
        dfiEditID = (EditText)findViewById(R.id.dfiEditID);
        afasEditID = (EditText)findViewById(R.id.afasEditID);
        acpnEditID =(EditText)findViewById(R.id.acpnEditID);
        acpnEditID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        acpnEditID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        vsaEditID = (EditText)findViewById(R.id.vsaEditID);
        mklrecivedID = (EditText)findViewById(R.id.mklrecivedID);
        presklrecvID = (EditText)findViewById(R.id.presklrecvID);
        gclreciveID = (EditText)findViewById(R.id.gclreciveID);
        cmdID = (EditText)findViewById(R.id.cmdID);
        cmdID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        cmdID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        //savebtn = (Button)findViewById(R.id.savebtn);
        //savebtn.setOnClickListener(this);
        FdateId =(TextView)findViewById(R.id.FdateId);
        mothermeetingId = (TextView)findViewById(R.id.mothermeetingId);
        medicindateId = (TextView)findViewById(R.id.medicindateId);
        searchID = (ImageView)findViewById(R.id.searchID);
        SPmothermeetingId = (ImageView)findViewById(R.id.SPmothermeetingId);
        medicindateIdd = (ImageView)findViewById(R.id.medicindateIdd);
        LVinuseId = (LinearLayout)findViewById(R.id.LVinuseId);
        LVinuseId.setVisibility(View.GONE);
        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
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
                Fromdate(curdate);


            }
        };
        myCalendarr = Calendar.getInstance();
        datee = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarr.set(Calendar.YEAR, year);
                myCalendarr.set(Calendar.MONTH, monthOfYear);
                myCalendarr.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Todate(curdate);
            }
        };
        myCalendarrr = Calendar.getInstance();
        dateee = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarrr.set(Calendar.YEAR, year);
                myCalendarrr.set(Calendar.MONTH, monthOfYear);
                myCalendarrr.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                lastrecived(curdate);
            }
        };
        searchID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(OtherInspectionActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        SPmothermeetingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(OtherInspectionActivity.this, datee, myCalendarr
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendarr.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        medicindateIdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(OtherInspectionActivity.this, dateee, myCalendarrr
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendarrr.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        initToolBar();
        radibuttonID();

        helper = new DatabaseHelper(this);
     //   Cursor cursor = helper.getReadableDatabase().
         //       rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
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
        if (cursor.moveToFirst()){
            do {
                //IdOther = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
              //  otherName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTION));
                IdOther =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                buildingdetails =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILDINGDETAILS));
                informationoftoilet = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INFORMATIONOFTOILET));
                informationofkitchen= cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INFORMATIONOFKITCHEN));
                adequatespaceforpse = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADEQUATESPACEFORPSE));
                electricity =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECTRICITY));
                drinkingwater = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATER));
                foodstoreddetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSTOREDDETAILS));
                conditionofequipmentotherawc= cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONOFEQUIPMENTOTHERAWC));
                shishualoy = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOY));
                snpserved = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SNPSERVED));
                nutritionalstatusobserved = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALSTATUSOBSERVED));
                otherName =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTION));
                allinspactionid = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPACATIONID));
                Log.e("ALLINSFLAG", " "+IdOther+" "+buildingdetails + " " + informationoftoilet + " " + informationofkitchen + " " + adequatespaceforpse + " " + electricity + " " + drinkingwater + " " + foodstoreddetails + " " + conditionofequipmentotherawc + " " + shishualoy + " " + snpserved + " " + nutritionalstatusobserved+" "+otherName+" "+allinspactionid);


            }
            while (cursor.moveToNext());
        }

        if (otherName.equals("0")){

        }

        else {
            editOther();
        }
        broadcastReceiverother = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            }
        };
        registerReceiver(broadcastReceiverother, new IntentFilter(DATA_SAVED_BROADCAST_OTHER));
        otherinspactionNetworkchecker = new OtherinspactionNetworkchecker();
        registerReceiver(otherinspactionNetworkchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        broadcastReceiverotherr = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiverotherr, new IntentFilter(DATA_SAVED_BROADCAST_FINAL));
        finalSubmitNetworkcheck = new FinalSubmitNetworkcheck();
        registerReceiver(finalSubmitNetworkcheck, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        if (isNetworkAvailable()) {
            mothermeetingId.setText("Date");
            FdateId.setText("Date");
            medicindateId.setText("Date");
        } else {
            // String query = "SELECT * FROM " + TABLE_OTHERINSPECTIONTABLENAME + " where " + TABLE_OTHERINSPECTIONINSID + "=" + insid + " and " + TABLE_OTHERINSPECTIONSTATUS + "=" + OTHERTATUS;
            String query = "SELECT * FROM otherinspection WHERE otherinspectioninsid=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cc = db.rawQuery(query, null);
            if (cc.moveToFirst()) {
                do {
                    String otherinspectionid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONTABLEID));
                    String otherinspectioninsid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONINSID));
                    String otherinspectioninscurtime = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONINSCURTIME));
                    cmuntyprticptnnoticedb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CMUNTYPRTICPTNNOTICE));
                    supvisitdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SUPVISIT));
                    mthrmeetdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LASTSUPUSTIDATE));
                    awmonitrdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_MEDCINKITLSTRCV));
                    othrfunctnvstdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LASTRECIVED));
                    rcdmaintaindb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USEDOFAWC));
                    regulrchrgrcvdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_GENGOTH));
                    awcremarkdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_AWCREMARK));
                    String otherinspectionstatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONSTATUS));
                    Log.e("OTHERSYNC", " "
                            + otherinspectionid + " "
                            + otherinspectioninsid + " "
                            + otherinspectioninscurtime + " "
                            + cmuntyprticptnnoticedb + " "
                            + supvisitdb + " "
                            + mthrmeetdb + " "
                            + awmonitrdb + " "
                            + othrfunctnvstdb + " "
                            + rcdmaintaindb + " "
                            + regulrchrgrcvdb+" "
                            +awcremarkdb);
                }
                while (cc.moveToNext());
            }

            Show();
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

//                                InputFilter filterr1 = new InputFilter() {
//                                    public CharSequence filter(CharSequence source, int start, int end,
//                                                               Spanned dest, int dstart, int dend) {
//                                        char[] chars = {'\'','"'};
//                                        for (int i = start; i < end; i++) {
//                                            if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                return "";
//                                            }
//                                        }
//                                        return null;
//                                    }
//                                };
//                                dfiEditID.setFilters(new InputFilter[] { filterr1 });
//                                feedintrupduratn = dfiEditID.getText().toString().trim();
//                                if (TextUtils.isEmpty(feedintrupduratn)) {
//                                    dfiEditID.setError("Please Enter Duration Of Feeding");
//                                    dfiEditID.requestFocus();
////                                    return;
//                                }
//                                InputFilter filterr2 = new InputFilter() {
//                                    public CharSequence filter(CharSequence source, int start, int end,
//                                                               Spanned dest, int dstart, int dend) {
//                                        char[] chars = {'\'','"'};
//                                        for (int i = start; i < end; i++) {
//                                            if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                return "";
//                                            }
//                                        }
//                                        return null;
//                                    }
//                                };
//                                afasEditID.setFilters(new InputFilter[] { filterr2 });
//                                supactn = afasEditID.getText().toString().trim();
//                                if (TextUtils.isEmpty(supactn)) {
//                                    afasEditID.setError("Please Enter Supervisior");
//                                    afasEditID.requestFocus();
//                                  //  return;
//                                }
//
//                                InputFilter filterr4 = new InputFilter() {
//                                    public CharSequence filter(CharSequence source, int start, int end,
//                                                               Spanned dest, int dstart, int dend) {
//                                        char[] chars = {'\'','"'};
//                                        for (int i = start; i < end; i++) {
//                                            if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                return "";
//                                            }
//                                        }
//                                        return null;
//                                    }
//                                };
//                                vsaEditID.setFilters(new InputFilter[] { filterr4 });
//                                supvisit = vsaEditID.getText().toString().trim();
//                                if (TextUtils.isEmpty(supvisit)) {
//                                    vsaEditID.setError("Please Enter Visit Supervisior");
//                                    vsaEditID.requestFocus();
//                                   // return;
//                                }
                                 savedate();

                                break;
                            default:
                        }
                        return true;
                    }
                });

        dfispack = (ImageButton)findViewById(R.id.dfispack);
        dfispack.setOnClickListener(this);
        afasspak = (ImageButton)findViewById(R.id.afasspak);
        afasspak.setOnClickListener(this);
        acpnspak = (ImageButton)findViewById(R.id.acpnspak);
        acpnspak.setOnClickListener(this);
        vsaspak = (ImageButton)findViewById(R.id.vsaspak);
        vsaspak.setOnClickListener(this);
        mklrecivedspeak = (ImageButton)findViewById(R.id.mklrecivedspeak);
        mklrecivedspeak.setOnClickListener(this);
        presklrecvspeak = (ImageButton)findViewById(R.id.presklrecvspeak);
        presklrecvspeak.setOnClickListener(this);
        gclrecivespake = (ImageButton)findViewById(R.id.gclrecivespake);
        gclrecivespake.setOnClickListener(this);
        cmdspak = (ImageButton)findViewById(R.id.cmdspak);
        cmdspak.setOnClickListener(this);
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        discode = String.valueOf(user.getUserDist());
        userid = String.valueOf(user.getUserID());
        Log.e("userid",userid);
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
            Toast.makeText(getApplicationContext(), "No Valid Date", Toast.LENGTH_SHORT).show();

        }
        /////////////////////////////////////////////
    }
    private void Todate(final String curdate) {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdff = new SimpleDateFormat(myFormat, Locale.US);
        mothermeetingId.setText(sdff.format(myCalendarr.getTime()));
        eccobserdate = mothermeetingId.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(mothermeetingId.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (curdate.equals(mothermeetingId.getText().toString()) || new Date().after(strDate)) {
            mothermeetingId.setText(sdff.format(myCalendarr.getTime()));
            eccobserdate = mothermeetingId.getText().toString();
        } else {
            mothermeetingId.setText("Date");
            eccobserdate = mothermeetingId.getText().toString();
            Toast.makeText(getApplicationContext(), "No Valid Date", Toast.LENGTH_SHORT).show();

        }


    }
    private void lastrecived(final String curdate) {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdff = new SimpleDateFormat(myFormat, Locale.US);
        medicindateId.setText(sdff.format(myCalendarrr.getTime()));
        medicinkitlaastrecived = medicindateId.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(medicindateId.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (curdate.equals(medicindateId.getText().toString()) || new Date().after(strDate)) {
            medicindateId.setText(sdff.format(myCalendarrr.getTime()));
            medicinkitlaastrecived = medicindateId.getText().toString();
        } else {
            medicindateId.setText("Date");
            medicinkitlaastrecived = medicindateId.getText().toString();
            Toast.makeText(getApplicationContext(), "No Valid Date", Toast.LENGTH_SHORT).show();

        }
    }
         public void Show(){
             acpnEditID.setText(cmuntyprticptnnoticedb);
             FdateId.setText(supvisitdb);
             mothermeetingId.setText(mthrmeetdb);
             medicindateId.setText(awmonitrdb);
             if (othrfunctnvstdb.equals("a")){
                 YmedicinkitId.setChecked(true);
             }
             else if (othrfunctnvstdb.equals("na")){
                 NmedicinkitId.setChecked(true);
             }
             else {
                 YmedicinkitId.setChecked(false);
                 NmedicinkitId.setChecked(false);
             }

             if (rcdmaintaindb.equals("u")){
                 UuseId.setChecked(true);
             }
             else if (rcdmaintaindb.equals("nu")){
                 NUuseId.setChecked(true);
             }
             else {
                 UuseId.setChecked(false);
                 NUuseId.setChecked(false);
             }

             if (regulrchrgrcvdb.equals("good")){
                 bccGoodID.setChecked(true);
             }
             else if (regulrchrgrcvdb.equals("Average")){
                 bccAverage.setChecked(true);
             }
             else if (regulrchrgrcvdb.equals("poor")){
                 bccPoor.setChecked(true);
             }
             else {
                 bccGoodID.setChecked(false);
                 bccAverage.setChecked(false);
                 bccPoor.setChecked(false);
             }
             cmdID.setText(awcremarkdb);

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
    private void radibuttonID(){
        GmmorID = (RadioGroup)findViewById(R.id.GmmorID);
        amcfamhID =(RadioGroup)findViewById(R.id.amcfamhID);
        visitofunID =(RadioGroup)findViewById(R.id.visitofunID);
        mrID =(RadioGroup)findViewById(R.id.mrID);
        fevcrrID =(RadioGroup)findViewById(R.id.fevcrrID);
        GPmedicinkitId =(RadioGroup)findViewById(R.id.GPmedicinkitId);
        GPuseId =(RadioGroup)findViewById(R.id.GPuseId);
        GbccID =(RadioGroup)findViewById(R.id.GbccID);
        YmmorID = (RadioButton)findViewById(R.id.YmmorID);
        NmmorID =(RadioButton)findViewById(R.id.NmmorID);
        YamcfamhID= (RadioButton)findViewById(R.id.YamcfamhID);
        NamcfamhID = (RadioButton)findViewById(R.id.NamcfamhID);
        YvisitofunID =(RadioButton)findViewById(R.id.YvisitofunID);
        NvisitofunID =(RadioButton)findViewById(R.id.NvisitofunID);
        YmrID =(RadioButton)findViewById(R.id.YmrID);
        NmrID =(RadioButton)findViewById(R.id.NmrID);
        YfevcrrID =(RadioButton)findViewById(R.id.YfevcrrID);
        NfevcrrID =(RadioButton)findViewById(R.id.NfevcrrID);
        YmedicinkitId = (RadioButton)findViewById(R.id.YmedicinkitId);
        NmedicinkitId = (RadioButton)findViewById(R.id.NmedicinkitId);
        UuseId = (RadioButton)findViewById(R.id.UuseId);
        NUuseId = (RadioButton)findViewById(R.id.NUuseId);
        bccGoodID = (RadioButton)findViewById(R.id.bccGoodID);
        bccAverage = (RadioButton)findViewById(R.id.bccAverage);
        bccPoor = (RadioButton)findViewById(R.id.bccPoor);
        GmmorID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
             if (checkedId== R.id.YmmorID){
                 mthrmeet = "y";
                 Log.e("mthrmeet",mthrmeet);
             }
             else if (checkedId== R.id.NmmorID){
                 mthrmeet = "n";
                 Log.e("mthrmeet",mthrmeet);
             }
            }
        });
        amcfamhID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YamcfamhID){
                    awmonitr = "y";
                    Log.e("awmonitr",awmonitr);
                }
                else if (checkedId== R.id.NamcfamhID){
                    awmonitr = "n";
                    Log.e("awmonitr",awmonitr);
                }

            }
        });
        visitofunID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YvisitofunID){
                    othrfunctnvst = "y";
                    Log.e("othrfunctnvst",othrfunctnvst);
                }
                else if (checkedId== R.id.NvisitofunID){
                    othrfunctnvst = "n";
                    Log.e("othrfunctnvst",othrfunctnvst);
                }


            }
        });
        mrID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YmrID){
                    rcdmaintain = "y";
                    Log.e("rcdmaintain",rcdmaintain);
                }
                else if (checkedId== R.id.NmrID){
                    rcdmaintain = "n";
                    Log.e("rcdmaintain",rcdmaintain);
                }


            }
        });
        fevcrrID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YfevcrrID){
                    regulrchrgrcv = "y";
                    Log.e("regulrchrgrcv",regulrchrgrcv);
                }
                else if (checkedId== R.id.NfevcrrID){
                    regulrchrgrcv = "n";
                    Log.e("regulrchrgrcv",regulrchrgrcv);
                }

            }
        });
        GPmedicinkitId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YmedicinkitId){
                    LVinuseId.setVisibility(View.VISIBLE);
                    last_recived = "a";
                }
                else if (checkedId== R.id.NmedicinkitId){
                    LVinuseId.setVisibility(View.GONE);
                    last_recived = "na";
                }


            }
        });
        GPuseId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.UuseId){
                 used_of_awc= "u";
                }
                else if (checkedId== R.id.NUuseId){
                    used_of_awc= "nu";
                }

            }
        });
        GbccID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.bccGoodID){
                 gen_goth = "good";
                }
                else if (checkedId== R.id.bccAverage){
                    gen_goth = "Average";
                }
                else if (checkedId== R.id.bccPoor){
                    gen_goth = "poor";
                }

            }
        });
    }
    private void editOther(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.OTHEREDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("OTHERView"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray othrawcinspectn = object.getJSONArray("othr_awc_inspectn");
                            for (int i =0;i<othrawcinspectn.length(); i++){
                                JSONObject jsonObject = othrawcinspectn.getJSONObject(i);
                                Ecmuntyprticptnnotice = jsonObject.getString("comm_participation");
                                Esupvisit = jsonObject.getString("sup_visit");
                                lastsupustidate = jsonObject.getString("last_supusti_date");
                                medcinkitlstrcv = jsonObject.getString("medcin_kit_lst_rcv");
                                medcinkitst = jsonObject.getString("medcin_kit_st");
                                medcinkitusest = jsonObject.getString("medcin_kit_use_st");
                                awcgnrlclr = jsonObject.getString("awc_gnrl_clr");

//                                Emthrmeet = jsonObject.getString("mthr_meet");
//                                Eawmonitr = jsonObject.getString("aw_monitr");
//                                Eothrfunctnvst = jsonObject.getString("othr_functn_vst");
//                                Ercdmaintain = jsonObject.getString("rcd_maintain");
//                                Eregulrchrgrcv = jsonObject.getString("regulr_chrg_rcv");
//                                Emedcinchrglstrcv = jsonObject.getString("medcin_chrg_lst_rcv");
//                                Epreschlchrglstrcv = jsonObject.getString("preschl_chrg_lst_rcv");
//                                Egrwthchrtlstrcv = jsonObject.getString("grwth_chrt_lst_rcv");
                                Eawcremark = jsonObject.getString("awc_remark");
                                acpnEditID.setText(Ecmuntyprticptnnotice);
                                FdateId.setText(Esupvisit);
                                mothermeetingId.setText(lastsupustidate);
                                medicindateId.setText(medcinkitlstrcv);
                                if (medcinkitst.equals("a")){
                                    YmedicinkitId.setChecked(true);
                                }
                                else if (medcinkitst.equals("na")){
                                    NmedicinkitId.setChecked(true);
                                }
                                else {
                                    YmedicinkitId.setChecked(false);
                                    NmedicinkitId.setChecked(false);
                                }
                                if (medcinkitusest.equals("u")){
                                    UuseId.setChecked(true);
                                }
                                else if (medcinkitusest.equals("nu")){
                                    NUuseId.setChecked(true);
                                }
                                else {
                                    UuseId.setChecked(false);
                                    NUuseId.setChecked(false);
                                }
                                 if (awcgnrlclr.equals("good")){
                                     bccGoodID.setChecked(true);
                                 }
                                 else if (awcgnrlclr.equals("average")){
                                     bccAverage.setChecked(true);
                                 }
                                 else if (awcgnrlclr.equals("poor")){
                                     bccPoor.setChecked(true);
                                 }
                                 else {
                                     bccGoodID.setChecked(false);
                                     bccAverage.setChecked(false);
                                     bccPoor.setChecked(false);
                                 }
                                cmdID.setText(Eawcremark);

//                                dfiEditID.setText(Efeedintrupduratn);
//                                afasEditID.setText(Esupactn);
//                                acpnEditID.setText(Ecmuntyprticptnnotice);
//                                vsaEditID.setText(Esupvisit);
//                                if (Emthrmeet.equals("y")){
//                                    YmmorID.setChecked(true);
//                                }
//                                else if (Emthrmeet.equals("n")){
//                                    NmmorID.setChecked(true);
//                                }
//                                else {
//                                    YmmorID.setChecked(false);
//                                    NmmorID.setChecked(false);
//                                }
//                                if (Eawmonitr.equals("y")){
//                                    YamcfamhID.setChecked(true);
//                                }
//                                else if (Eawmonitr.equals("n")){
//                                    NamcfamhID.setChecked(true);
//                                }
//                                else {
//                                    YamcfamhID.setChecked(false);
//                                    NamcfamhID.setChecked(false);
//                                }
//                                if (Eothrfunctnvst.equals("y")){
//                                    YvisitofunID.setChecked(true);
//                                }
//                                else if (Eothrfunctnvst.equals("n")){
//                                    NvisitofunID.setChecked(true);
//                                }
//                                else {
//                                    YvisitofunID.setChecked(false);
//                                    NvisitofunID.setChecked(false);
//                                }
//                                if (Ercdmaintain.equals("y")){
//                                    YmrID.setChecked(true);
//                                }
//                                else if (Ercdmaintain.equals("n")){
//                                    NmrID.setChecked(true);
//                                }
//                                else {
//                                    YmrID.setChecked(false);
//                                    NmrID.setChecked(false);
//                                }
//                                if (Eregulrchrgrcv.equals("y")){
//                                    YfevcrrID.setChecked(true);
//                                }
//                                else if (Eregulrchrgrcv.equals("n")){
//                                    NfevcrrID.setChecked(true);
//                                }
//                                else {
//                                    YfevcrrID.setChecked(false);
//                                    NfevcrrID.setChecked(false);
//                                }
//                                mklrecivedID.setText(Emedcinchrglstrcv);
//                                presklrecvID.setText(Epreschlchrglstrcv);
//                                gclreciveID.setText(Egrwthchrtlstrcv);


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
                Log.e("OTHERINS",insid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    public void savedate(){
        InputFilter filterr3 = new InputFilter() {
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
        acpnEditID.setFilters(new InputFilter[] { filterr3 });
        cmuntyprticptnnotice = acpnEditID.getText().toString().trim();
        if (TextUtils.isEmpty(cmuntyprticptnnotice)) {
            acpnEditID.setError("Please Enter Any Coommunity Notice");
            acpnEditID.requestFocus();
             return;
        }

//        InputFilter filter1 = new InputFilter() {
//            public CharSequence filter(CharSequence source, int start, int end,
//                                       Spanned dest, int dstart, int dend) {
//                char[] chars = {'\'','"'};
//                for (int i = start; i < end; i++) {
//                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                        return "";
//                    }
//                }
//                return null;
//            }
//        };
//        mklrecivedID.setFilters(new InputFilter[] { filter1 });
//        medcinchrglstrcv = mklrecivedID.getText().toString().trim();
//        if (TextUtils.isEmpty(medcinchrglstrcv)) {
//            mklrecivedID.setError("Please Enter Medicine Kit Last Recived");
//            mklrecivedID.requestFocus();
//            return;
//        }




//        InputFilter filter3 = new InputFilter() {
//            public CharSequence filter(CharSequence source, int start, int end,
//                                       Spanned dest, int dstart, int dend) {
//                char[] chars = {'\'','"'};
//                for (int i = start; i < end; i++) {
//                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                        return "";
//                    }
//                }
//                return null;
//            }
//        };
//        gclreciveID.setFilters(new InputFilter[] { filter3 });
//        grwthchrtlstrcv = gclreciveID.getText().toString().trim();
//        if (TextUtils.isEmpty(grwthchrtlstrcv)) {
//            gclreciveID.setError("Please Enter Growth Chart Last Recived");
//            gclreciveID.requestFocus();
//            return;
//        }


        if (FdateId.getText().toString().equals("Date")){
            Toast.makeText(OtherInspectionActivity.this,"Select Date of last svisit of the supervisor to the awc",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT DATE OF LAST SVISIT OF THE SUPERVISOR TO THE AWC";
            createDialog(title,msg);
        }
        else {
            if (mothermeetingId.getText().toString().equals("Date")){
                Toast.makeText(OtherInspectionActivity.this,"SELECT DATE OF LAST SUPUSTI DIWAS OBSERVED",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT DATE OF LAST SUPUSTI DIWAS OBSERVED";
                createDialog(title,msg);

            }
            else {
                if (medicindateId.getText().toString().equals("Date")){
                    Toast.makeText(OtherInspectionActivity.this,"SELECT LAST RECEIVED DATE",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT LAST RECEIVED DATE";
                    createDialog(title,msg);

                }
                else {
                    if (YmedicinkitId.isChecked() || NmedicinkitId.isChecked()){
//                        InputFilter filter2 = new InputFilter() {
//                            public CharSequence filter(CharSequence source, int start, int end,
//                                                       Spanned dest, int dstart, int dend) {
//                                char[] chars = {'\'','"'};
//                                for (int i = start; i < end; i++) {
//                                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                        return "";
//                                    }
//                                }
//                                return null;
//                            }
//                        };
//                        presklrecvID.setFilters(new InputFilter[] { filter2 });
//                        preschlchrglstrcv = presklrecvID.getText().toString().trim();
//                        if (TextUtils.isEmpty(preschlchrglstrcv)) {
//                            presklrecvID.setError("Please Enter Pre_School Kit Last Recived");
//                            presklrecvID.requestFocus();
//                            return;
//                        }
                        if (bccGoodID.isChecked() || bccAverage.isChecked() || bccPoor.isChecked()){
                            InputFilter filter4 = new InputFilter() {
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
                            cmdID.setFilters(new InputFilter[] { filter4 });
                            awcremark = cmdID.getText().toString().trim();
                            if (TextUtils.isEmpty(awcremark)) {
                                cmdID.setError("Please Enter Command");
                                cmdID.requestFocus();
                                return;
                            }
                            if (last_recived.equals("a")){
                                if (UuseId.isChecked() || NUuseId.isChecked()){
                                    if (buildingdetails.equals("1")
                                            && informationoftoilet.equals("1")
                                            && informationofkitchen.equals("1")
                                            && adequatespaceforpse.equals("1")
                                            && electricity.equals("1")
                                            && drinkingwater.equals("1")
                                            && foodstoreddetails.equals("1")
                                            && conditionofequipmentotherawc.equals("1")
                                            && shishualoy.equals("1")
                                            && snpserved.equals("1")
                                            && nutritionalstatusobserved.equals("1")
                                            && otherName.equals("0")){
                                            OtherInspaction();
                                        Toast.makeText(getApplicationContext(),"SAVE ALL DATA SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SAVE ALL DATA SUCCESSFULLY";
                                        createDialog(title,msg);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"SAVE ALL DATA",Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SAVE ALL DATA";
                                        createDialog(title,msg);
                                    }

                                }
                                else {
                                    Toast.makeText(OtherInspectionActivity.this,"SELECT LAST RECEIVED DATE USED FOR AWC OR NOT",Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT LAST RECEIVED DATE USED FOR AWC OR NOT";
                                    createDialog(title,msg);
                                }

                            }
                            else if (last_recived.equals("na")){
                                if (buildingdetails.equals("1")
                                        && informationoftoilet.equals("1")
                                        && informationofkitchen.equals("1")
                                        && adequatespaceforpse.equals("1")
                                        && electricity.equals("1")
                                        && drinkingwater.equals("1")
                                        && foodstoreddetails.equals("1")
                                        && conditionofequipmentotherawc.equals("1")
                                        && shishualoy.equals("1")
                                        && snpserved.equals("1")
                                        && nutritionalstatusobserved.equals("1")
                                        && otherName.equals("0")){
                                        OtherInspaction();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"SAVE ALL DATA",Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SAVE ALL DATA";
                                    createDialog(title,msg);
                                }

                            }

                        }
                        else {
                            Toast.makeText(OtherInspectionActivity.this,"Select General clearness of awc and surrounding",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT GENERAL CLEARNESS OF AWC AND SURROUNDING";
                            createDialog(title,msg);
                        }
                    }
                    else {
                        Toast.makeText(OtherInspectionActivity.this,"SELECT LAST RECEIVED DATE",Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT LAST RECEIVED DATE";
                        createDialog(title,msg);

                    }
                }

            }

        }
        if (YmedicinkitId.isChecked()){
            if (UuseId.isChecked() || NUuseId.isChecked()){

            }
            else {
                Toast.makeText(OtherInspectionActivity.this,"SELECT LAST RECEIVED DATE USED FOR AWC OR NOT",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT LAST RECEIVED DATE USED FOR AWC OR NOT";
                createDialog(title,msg);

            }
        }


//        if (YmmorID.isChecked() || NmmorID.isChecked()){
//            if (YamcfamhID.isChecked() || NamcfamhID.isChecked()){
//                if (YvisitofunID.isChecked() || NvisitofunID.isChecked()){
//                    if (YmrID.isChecked() || NmrID.isChecked()){
//                        if (YfevcrrID.isChecked() || NfevcrrID.isChecked()){
//
//                            OtherInspaction();
//
//                        }
//                        else {
//                            Toast.makeText(OtherInspectionActivity.this,"Select Fuel Egg Vegetable Charges Received Regularly",Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                    else {
//                        Toast.makeText(OtherInspectionActivity.this,"Select Maintenance Of Records",Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//                else {
//                    Toast.makeText(OtherInspectionActivity.this,"Select Visit Of Other Functionaries",Toast.LENGTH_SHORT).show();
//                }
//            }
//            else {
//                Toast.makeText(OtherInspectionActivity.this,"Select Aw Level Monitoring Commttee Formed and meeting Held",Toast.LENGTH_SHORT).show();
//            }
//        }
//        else {
//
//            Toast.makeText(OtherInspectionActivity.this,"Select Mothers Meeting Organised Regulary",Toast.LENGTH_SHORT).show();
//        }



    }
    private void OtherInspaction(){
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.OTHER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Nutritional"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("NutritionalOBJ"," "+array);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET otherinspection='1' WHERE allinspactionid=" +insid);
                                syncSnpDatabase(insid,
                                        curTime,
                                        cmuntyprticptnnotice,
                                        FdateId.getText().toString(),
                                        mothermeetingId.getText().toString(),
                                        medicindateId.getText().toString(),
                                        last_recived,
                                        used_of_awc,
                                        gen_goth,
                                        awcremark,
                                        OTHER_SYNCED_WITH_SERVER);


                                String title = "Message Box";
                                String msg = "ARE YOU SURE FOR YOUR FINAL SUBMIT";
                                finalsubmit(title,msg);

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
                        dbb.execSQL("UPDATE insflag SET otherinspection='1' WHERE allinspactionid=" +insid);
                        syncSnpDatabase(insid,
                                curTime,
                                cmuntyprticptnnotice,
                                FdateId.getText().toString(),
                                mothermeetingId.getText().toString(),
                                medicindateId.getText().toString(),
                                last_recived,
                                used_of_awc,
                                gen_goth,
                                awcremark,
                                OTHER_NOT_SYNCED_WITH_SERVER);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // sup_visit,
                // medcin_kit_lst_rcv,
                // medcin_kit_st,
                // medcin_kit_use_st,
                // awc_gnrl_clr,
                // awc_remark
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("ins_time",curTime);

               // params.put("feed_intrup_duratn",feedintrupduratn);
               // params.put("sup_actn",supactn);

                params.put("comm_participation",cmuntyprticptnnotice);
                params.put("sup_visit",FdateId.getText().toString());

                params.put("last_supusti_date",mothermeetingId.getText().toString());
                params.put("medcin_kit_lst_rcv",medicindateId.getText().toString());
                params.put("medcin_kit_st",last_recived);
                params.put("medcin_kit_use_st",used_of_awc);
                params.put("awc_gnrl_clr", gen_goth);
//                params.put("mthr_meet",mthrmeet);
//                params.put("aw_monitr",awmonitr);
//                params.put("othr_functn_vst",othrfunctnvst);
//                params.put("rcd_maintain", rcdmaintain);
//                params.put("regulr_chrg_rcv",regulrchrgrcv);
//                params.put("medcin_chrg_lst_rcv",medcinchrglstrcv);
//                params.put("preschl_chrg_lst_rcv",preschlchrglstrcv);
//                params.put("grwth_chrt_lst_rcv",grwthchrtlstrcv);
                params.put("awc_remark",awcremark);
               Log.e("OTHER",insid+" "+curTime+" "+cmuntyprticptnnotice+" "
                       +FdateId.getText().toString()+" "+mothermeetingId.getText().toString()+" "+medicindateId.getText().toString()+" "+last_recived+" "+used_of_awc+" "+gen_goth+" "+awcremark);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }


    private void syncSnpDatabase(String insid,
                                 String curTime,
                                 String cmuntyprticptnnotice,
                                 String supvisit,
                                 String lastsupustidate,
                                 String medcinkitlstrcv,
                                 String lastrecived,
                                 String usedofawc,
                                 String gengoth,
                                 String awcremark,
                                 int otherinspectionstatus){
        if (otherName.equals("0")){
            helper.OTHERINSPECTIONINSERT(insid,
                    curTime,
                    cmuntyprticptnnotice,
                    supvisit,
                    lastsupustidate,
                    medcinkitlstrcv,
                    lastrecived,
                    usedofawc,
                    gengoth,
                    awcremark,otherinspectionstatus);
        }
        else {
            helper.OTHERINSPECTIONUPDATE(dbid,insid,
                    curTime,
                    cmuntyprticptnnotice,
                    supvisit,
                    lastsupustidate,
                    medcinkitlstrcv,
                    lastrecived,
                    usedofawc,
                    gengoth,
                    awcremark,otherinspectionstatus);

        }
        String title = "Message Box";
        String msg = "ARE YOU SURE FOR YOUR FINAL SUBMIT";
        finalsubmit(title,msg);

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
                if (isNetworkAvailable()){
                    DataSendNEXTActivity();
                }
                else {
                    DataSendNEXTActivity();
                }
            }
        });
        ((Button)dialogView.findViewById(R.id.nId)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
                Intent intent = new Intent(OtherInspectionActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }

    public AlertDialog finalsubmit1(String title, String msg){
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
                if (isNetworkAvailable()){
                    BuildingReturndata();
                }
                else {
                    BuildingReturndata();
                }
            }
        });
        ((Button)dialogView.findViewById(R.id.nId)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
                Intent intent = new Intent(OtherInspectionActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }
    public void DataSendNEXTActivity(){
        helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqldb = helper.getReadableDatabase();
        sqldb.execSQL("UPDATE awcsprocess SET flag='2' WHERE idprocess=" + dbid);
        //awcsdtl(dbid);
        awcsdtl(insid,userid);
        myInspaction(insid);
        processDelete(dbid,userid);
        listcheckboxDelete(insid,userid);
        Intent intent = new Intent(OtherInspectionActivity.this, NavigationDrawerActivity.class);
        startActivity(intent);
    }
    public void myInspaction(final String inspctnid){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.FINALSUBMIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("insresponse"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("obj"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                syncfinalsub(inspctnid,FINAL_SUB_WITH_SERVER);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Update",error.getMessage());
                        syncfinalsub(inspctnid,FINAL_SUB_WITH_SERVER_NOT);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",inspctnid);
                Log.e("insresponse",inspctnid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    public void syncfinalsub(String inspctnid,int finalsubmitstatus){
        if (otherName.equals("0")){
            helper.FINALSUBMITINSERT(inspctnid,finalsubmitstatus);
        }
        else {
            helper.FINALSUBMITUPDATE(dbid,inspctnid,finalsubmitstatus);
        }


    }

    public void awcsdtl(String inspctnid,String Userid){
        String FLAG = "2";
//        Log.e("STATUSSTAWCS",dbid);
//        String query = "DELETE  FROM " + TABLE_NAMEAWCSDTL + " where " + TABLE_ID + "=" +dbid+ " and " +TABLE_AWCSFLAG+ "=" +FLAG;
//        SQLiteDatabase sqlDB = helper.getReadableDatabase();
//        Cursor  c = sqlDB.rawQuery(query,null);
        String query = "DELETE  FROM " + TABLE_NAMEAWCSDTL + " where " + TABLE_INSPECTIONID + "=" +inspctnid+ " and " +TABLE_AWCSUSERID+ "=" +Userid;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String id = "";
        String water = "";
        String cdponame = "";
        String acdpocont = "";
        String buildstruc = "";
        String electric = "";
        String acdponame = "";
        String kitchen = "";
        String cdpocontact = "";
        String workerno = "";
        String workernm = "";
        String toilet = "";
        String awcslat = "";
        String supvsrname = "";
        String awcsslong = "";
        String helperno = "";
        String awcsadrs = "";
        String foodspace = "";
        String helpernm = "";
        String buildon ="";
        String adqutspacepse ="";
        String supvsrno = "";
        String awcsid ="";
        String awcscode ="";
        String awcsname ="";
        String planid ="";
        String lstinspctnbuldrep ="";
        String lstinspctntoiletrep ="";
        String lstinspctnkitchenrep ="";
        String lstinspctnpserep ="";
        String lstinspctnelectricrep ="";
        String lstinspctndrnkwaterrep ="";
        String lstinspctnfoodrep = "";
        String awcsuserid = "";
        String awcsflag = "";
        String allinspactionid ="";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                id = c.getString(c.getColumnIndex("id"));
                water = c.getString(c.getColumnIndex("water"));
                cdponame = c.getString(c.getColumnIndex("cdponame"));
                acdpocont = c.getString(c.getColumnIndex("acdpocont"));
                buildstruc = c.getString(c.getColumnIndex("buildstruc"));
                electric = c.getString(c.getColumnIndex("electric"));
                acdponame = c.getString(c.getColumnIndex("acdponame"));
                kitchen = c.getString(c.getColumnIndex("kitchen"));
                cdpocontact = c.getString(c.getColumnIndex("cdpocontact"));
                workerno = c.getString(c.getColumnIndex("workerno"));
                workernm = c.getString(c.getColumnIndex("workernm"));
                toilet = c.getString(c.getColumnIndex("toilet"));
                awcslat = c.getString(c.getColumnIndex("awcslat"));
                supvsrname = c.getString(c.getColumnIndex("supvsrname"));
                awcsslong = c.getString(c.getColumnIndex("awcsslong"));
                helperno = c.getString(c.getColumnIndex("helperno"));
                awcsadrs = c.getString(c.getColumnIndex("awcsadrs"));
                foodspace = c.getString(c.getColumnIndex("foodspace"));
                helpernm = c.getString(c.getColumnIndex("helpernm"));
                buildon = c.getString(c.getColumnIndex("buildon"));
                adqutspacepse = c.getString(c.getColumnIndex("adqutspacepse"));
                supvsrno = c.getString(c.getColumnIndex("supvsrno"));
                awcsid = c.getString(c.getColumnIndex("awcsid"));
                awcscode = c.getString(c.getColumnIndex("awcscode"));
                awcsname = c.getString(c.getColumnIndex("awcsname"));
                planid = c.getString(c.getColumnIndex("planid"));
                lstinspctnbuldrep = c.getString(c.getColumnIndex("lstinspctnbuldrep"));
                lstinspctntoiletrep = c.getString(c.getColumnIndex("lstinspctntoiletrep"));
                lstinspctnkitchenrep = c.getString(c.getColumnIndex("lstinspctnkitchenrep"));
                lstinspctnpserep = c.getString(c.getColumnIndex("lstinspctnpserep"));
                lstinspctnelectricrep = c.getString(c.getColumnIndex("lstinspctnelectricrep"));
                lstinspctndrnkwaterrep = c.getString(c.getColumnIndex("lstinspctndrnkwaterrep"));
                lstinspctnfoodrep = c.getString(c.getColumnIndex("lstinspctnfoodrep"));
                awcsuserid = c.getString(c.getColumnIndex("awcsuserid"));
                awcsflag = c.getString(c.getColumnIndex("awcsflag"));
                allinspactionid = c.getString(c.getColumnIndex("allinspactionid"));
                if (!id.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + id + "'");
                }
                if (!water.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + water + "'");
                }
                if (!cdponame.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + cdponame + "'");
                }
                if (!acdpocont.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + acdpocont + "'");
                }
                if (!buildstruc.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + buildstruc + "'");
                }
                if (!electric.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + electric + "'");
                }
                if (!acdponame.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + acdponame + "'");
                }
                if (!kitchen.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + kitchen + "'");
                }
                if (!cdpocontact.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + cdpocontact + "'");
                }
                if (!workerno.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + workerno + "'");
                }
                if (!workernm.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + workernm + "'");
                }
                if (!toilet.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + toilet + "'");
                }
                if (!awcslat.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcslat + "'");
                }
                if (!supvsrname.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + supvsrname + "'");
                }
                if (!awcsslong.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcsslong + "'");
                }
                if (!helperno.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + helperno + "'");
                }
                if (!awcsadrs.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcsadrs + "'");
                }
                if (!foodspace.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodspace + "'");
                }
                if (!helpernm.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + helpernm + "'");
                }
                if (!buildon.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + buildon + "'");
                }
                if (!adqutspacepse.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adqutspacepse + "'");
                }
                if (!supvsrno.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + supvsrno + "'");
                }
                if (!awcsid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcsid + "'");
                }
                if (!awcscode.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcscode + "'");
                }
                if (!awcsname.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcsname + "'");
                }
                if (!planid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + planid + "'");
                }
                if (!lstinspctnbuldrep.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctnbuldrep + "'");
                }
                if (!lstinspctntoiletrep.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctntoiletrep + "'");
                }
                if (!lstinspctnkitchenrep.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctnkitchenrep + "'");
                }
                if (!lstinspctnpserep.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctnpserep + "'");
                }
                if (!lstinspctnelectricrep.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctnelectricrep + "'");
                }
                if (!lstinspctndrnkwaterrep.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctndrnkwaterrep + "'");
                }
                if (!lstinspctnfoodrep.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctnfoodrep + "'");
                }
                if (!awcsuserid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcsuserid + "'");
                }
                if (!awcsflag.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcsflag + "'");
                }
                if (!allinspactionid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + allinspactionid + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }
    public void processDelete(String dbid,String Userid){
        Log.e("PROCESSDELETE",dbid+" "+Userid);
        String query = "DELETE  FROM " + TABLE_PROCESS + " where " + TABLE_PROCESSID + "=" +dbid+ " and " +TABLE_USERID+ "=" +Userid;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String idprocess = "";
        String dbdistid = "";
        String dbprojectid = "";
        String dbsectorid = "";
        String dbcenterid = "";
        String districnamedb = "";
        String projectnamedb = "";
        String sectorrnamedb = "";
        String centernamedb = "";
        String currentdate = "";
        String userid = "";
        String flag = "";
        String awcslatlocation = "";
        String awcsslonglocation = "";
        String inspactionid = "";
        String awcscodeid = "";
        String awcstime = "";
        String flaggrecord ="";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                idprocess = c.getString(c.getColumnIndex("idprocess"));
                dbdistid = c.getString(c.getColumnIndex("dbdistid"));
                dbprojectid = c.getString(c.getColumnIndex("dbprojectid"));
                dbsectorid = c.getString(c.getColumnIndex("dbsectorid"));
                dbcenterid = c.getString(c.getColumnIndex("dbcenterid"));
                districnamedb = c.getString(c.getColumnIndex("districnamedb"));
                projectnamedb = c.getString(c.getColumnIndex("projectnamedb"));
                sectorrnamedb = c.getString(c.getColumnIndex("sectorrnamedb"));
                centernamedb = c.getString(c.getColumnIndex("centernamedb"));
                currentdate = c.getString(c.getColumnIndex("currentdate"));
                userid = c.getString(c.getColumnIndex("userid"));
                flag = c.getString(c.getColumnIndex("flag"));
                awcslatlocation = c.getString(c.getColumnIndex("awcslatlocation"));
                awcsslonglocation = c.getString(c.getColumnIndex("awcsslonglocation"));
                inspactionid = c.getString(c.getColumnIndex("inspactionid"));
                awcscodeid = c.getString(c.getColumnIndex("awcscodeid"));
                awcstime = c.getString(c.getColumnIndex("awcstime"));

                if (!idprocess.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + idprocess + "'");
                }
                if (!dbdistid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + dbdistid + "'");
                }
                if (!dbprojectid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + dbprojectid + "'");
                }
                if (!dbsectorid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + dbsectorid + "'");
                }
                if (!dbcenterid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + dbcenterid + "'");
                }
                if (!districnamedb.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + districnamedb + "'");
                }
                if (!projectnamedb.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + projectnamedb + "'");
                }
                if (!sectorrnamedb.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + sectorrnamedb + "'");
                }
                if (!centernamedb.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + centernamedb + "'");
                }
                if (!currentdate.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + currentdate + "'");
                }
                if (!userid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + userid + "'");
                }
                if (!flag.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + flag + "'");
                }
                if (!awcslatlocation.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcslatlocation + "'");
                }
                if (!awcsslonglocation.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcsslonglocation + "'");
                }
                if (!inspactionid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspactionid + "'");
                }
                if (!awcscodeid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcscodeid + "'");
                }
                if (!awcstime.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcstime + "'");
                }
                if (!flaggrecord.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + flaggrecord + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }
    public void listcheckboxDelete(String inspctnid,String Userid){
        Log.e("FLAGDELETE",inspctnid+" "+Userid);
        String query = "DELETE  FROM " + TABLE_ALLINSPECTIONFLAG + " where " + TABLE_ALLINSPACATIONID + "=" +inspctnid+ " and " +TABLE_USERIDFLA+ "=" +Userid;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String insflagid = "";
        String buildingdetails = "";
        String informationoftoilet = "";
        String informationofkitchen = "";
        String adequatespaceforpse = "";
        String electricity = "";
        String drinkingwater = "";
        String foodstoreddetails = "";
        String conditionofequipmentotherawc = "";
        String shishualoy = "";
        String snpserved = "";
        String nutritionalstatusobserved = "";
        String otherinspection = "";
        String allinspactionid = "";
        String useridflag = "";
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                insflagid = c.getString(c.getColumnIndex("insflagid"));
                buildingdetails = c.getString(c.getColumnIndex("buildingdetails"));
                informationoftoilet = c.getString(c.getColumnIndex("informationoftoilet"));
                informationofkitchen = c.getString(c.getColumnIndex("informationofkitchen"));
                adequatespaceforpse = c.getString(c.getColumnIndex("adequatespaceforpse"));
                electricity = c.getString(c.getColumnIndex("electricity"));
                drinkingwater = c.getString(c.getColumnIndex("drinkingwater"));
                foodstoreddetails = c.getString(c.getColumnIndex("foodstoreddetails"));
                conditionofequipmentotherawc = c.getString(c.getColumnIndex("conditionofequipmentotherawc"));
                shishualoy = c.getString(c.getColumnIndex("shishualoy"));
                snpserved = c.getString(c.getColumnIndex("snpserved"));
                nutritionalstatusobserved = c.getString(c.getColumnIndex("nutritionalstatusobserved"));
                otherinspection = c.getString(c.getColumnIndex("otherinspection"));
                allinspactionid = c.getString(c.getColumnIndex("allinspactionid"));
                useridflag = c.getString(c.getColumnIndex("useridflag"));
                if (!insflagid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + insflagid + "'");
                }
                if (!buildingdetails.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + buildingdetails + "'");
                }
                if (!informationoftoilet.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + informationoftoilet + "'");
                }
                if (!informationofkitchen.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + informationofkitchen + "'");
                }
                if (!adequatespaceforpse.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adequatespaceforpse + "'");
                }
                if (!electricity.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + electricity + "'");
                }
                if (!drinkingwater.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + drinkingwater + "'");
                }
                if (!foodstoreddetails.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodstoreddetails + "'");
                }
                if (!conditionofequipmentotherawc.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + conditionofequipmentotherawc + "'");
                }
                if (!shishualoy.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + shishualoy + "'");
                }
                if (!userid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + userid + "'");
                }
                if (!snpserved.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + snpserved + "'");
                }
                if (!nutritionalstatusobserved.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + nutritionalstatusobserved + "'");
                }
                if (!otherinspection.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + otherinspection + "'");
                }
                if (!allinspactionid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + allinspactionid + "'");
                }
                if (!useridflag.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + useridflag + "'");
                }
                c.moveToNext();
            }
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
//          case R.id.savebtn:
//
//              InputFilter filterr1 = new InputFilter() {
//                  public CharSequence filter(CharSequence source, int start, int end,
//                                             Spanned dest, int dstart, int dend) {
//                      char[] chars = {'\'','"'};
//                      for (int i = start; i < end; i++) {
//                          if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                              return "";
//                          }
//                      }
//                      return null;
//                  }
//              };
//              dfiEditID.setFilters(new InputFilter[] { filterr1 });
//              feedintrupduratn = dfiEditID.getText().toString().trim();
//              if (TextUtils.isEmpty(feedintrupduratn)) {
//                  dfiEditID.setError("Please Enter Duration Of Feeding");
//                  dfiEditID.requestFocus();
//                  return;
//              }
//              InputFilter filterr2 = new InputFilter() {
//                  public CharSequence filter(CharSequence source, int start, int end,
//                                             Spanned dest, int dstart, int dend) {
//                      char[] chars = {'\'','"'};
//                      for (int i = start; i < end; i++) {
//                          if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                              return "";
//                          }
//                      }
//                      return null;
//                  }
//              };
//              afasEditID.setFilters(new InputFilter[] { filterr2 });
//              supactn = afasEditID.getText().toString().trim();
//              if (TextUtils.isEmpty(supactn)) {
//                  afasEditID.setError("Please Enter Supervisior");
//                  afasEditID.requestFocus();
//                  return;
//              }
//              InputFilter filterr3 = new InputFilter() {
//                  public CharSequence filter(CharSequence source, int start, int end,
//                                             Spanned dest, int dstart, int dend) {
//                      char[] chars = {'\'','"'};
//                      for (int i = start; i < end; i++) {
//                          if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                              return "";
//                          }
//                      }
//                      return null;
//                  }
//              };
//              acpnEditID.setFilters(new InputFilter[] { filterr3 });
//              cmuntyprticptnnotice = acpnEditID.getText().toString().trim();
//              if (TextUtils.isEmpty(cmuntyprticptnnotice)) {
//                  acpnEditID.setError("Please Enter Any Coommunity Notice");
//                  acpnEditID.requestFocus();
//                  return;
//              }
//              InputFilter filterr4 = new InputFilter() {
//                  public CharSequence filter(CharSequence source, int start, int end,
//                                             Spanned dest, int dstart, int dend) {
//                      char[] chars = {'\'','"'};
//                      for (int i = start; i < end; i++) {
//                          if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                              return "";
//                          }
//                      }
//                      return null;
//                  }
//              };
//              vsaEditID.setFilters(new InputFilter[] { filterr4 });
//              supvisit = vsaEditID.getText().toString().trim();
//              if (TextUtils.isEmpty(supvisit)) {
//                  vsaEditID.setError("Please Enter Visit Supervisior");
//                  vsaEditID.requestFocus();
//                  return;
//              }
//              if (YmmorID.isChecked() || NmmorID.isChecked()){
//                  if (YamcfamhID.isChecked() || NamcfamhID.isChecked()){
//                      if (YvisitofunID.isChecked() || NvisitofunID.isChecked()){
//                          if (YmrID.isChecked() || NmrID.isChecked()){
//                              if (YfevcrrID.isChecked() || NfevcrrID.isChecked()){
//
//                                  OtherInspaction();
//
//                              }
//                              else {
//                                  Toast.makeText(OtherInspectionActivity.this,"Select Fuel Egg Vegetable Charges Received Regularly",Toast.LENGTH_SHORT).show();
//
//                              }
//
//                          }
//                          else {
//                              Toast.makeText(OtherInspectionActivity.this,"Select Maintenance Of Records",Toast.LENGTH_SHORT).show();
//
//                          }
//                      }
//                      else {
//                          Toast.makeText(OtherInspectionActivity.this,"Select Visit Of Other Functionaries",Toast.LENGTH_SHORT).show();
//                      }
//                  }
//                  else {
//                      Toast.makeText(OtherInspectionActivity.this,"Select Aw Level Monitoring Commttee Formed and meeting Held",Toast.LENGTH_SHORT).show();
//                  }
//              }
//              else {
//
//                  Toast.makeText(OtherInspectionActivity.this,"Select Mothers Meeting Organised Regulary",Toast.LENGTH_SHORT).show();
//              }
//
//              break;


          case R.id.dfispack:
              dfispack();
              break;
          case R.id.afasspak:
              afasspak();
              break;
          case R.id.acpnspak:
              acpnspak();
              break;
          case R.id.vsaspak:
              vsaspak();
              break;
          case  R.id.mklrecivedspeak:
              mklrecivedspeak();
              break;
          case R.id.presklrecvspeak:
              presklrecvspeak();
              break;
          case R.id.gclrecivespake:
              gclrecivespake();
              break;
          case R.id.cmdspak:
              cmdspak();
              break;
             default:
     }
    }

    private void dfispack(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_DFISPACK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void afasspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_AFASSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void acpnspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_ACPNSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void vsaspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_VSASPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void mklrecivedspeak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_MKLRECIVEDSPEAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void presklrecvspeak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_PRESKLRECVSPEAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void gclrecivespake(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_GCLRECIVESPAKE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cmdspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_CMDSPAK);
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
            case REQ_CODE_SPEECH_DFISPACK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    dfiEditID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_AFASSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    afasEditID.setText(result.get(0));
                }
                break;
            }
        }
        switch (requestCode) {
            case REQ_CODE_SPEECH_ACPNSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    acpnEditID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_VSASPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    vsaEditID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_MKLRECIVEDSPEAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mklrecivedID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_PRESKLRECVSPEAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    presklrecvID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_GCLRECIVESPAKE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    gclreciveID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_CMDSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    cmdID.setText(result.get(0));
                }
                break;
            }
        }
    }
    public void homesave(){
        InputFilter filterr3 = new InputFilter() {
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
        acpnEditID.setFilters(new InputFilter[] { filterr3 });
        cmuntyprticptnnotice = acpnEditID.getText().toString().trim();
        if (TextUtils.isEmpty(cmuntyprticptnnotice)) {
            acpnEditID.setError("Please Enter Any Coommunity Notice");
            acpnEditID.requestFocus();
            return;
        }

//        InputFilter filter1 = new InputFilter() {
//            public CharSequence filter(CharSequence source, int start, int end,
//                                       Spanned dest, int dstart, int dend) {
//                char[] chars = {'\'','"'};
//                for (int i = start; i < end; i++) {
//                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                        return "";
//                    }
//                }
//                return null;
//            }
//        };
//        mklrecivedID.setFilters(new InputFilter[] { filter1 });
//        medcinchrglstrcv = mklrecivedID.getText().toString().trim();
//        if (TextUtils.isEmpty(medcinchrglstrcv)) {
//            mklrecivedID.setError("Please Enter Medicine Kit Last Recived");
//            mklrecivedID.requestFocus();
//            return;
//        }




//        InputFilter filter3 = new InputFilter() {
//            public CharSequence filter(CharSequence source, int start, int end,
//                                       Spanned dest, int dstart, int dend) {
//                char[] chars = {'\'','"'};
//                for (int i = start; i < end; i++) {
//                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                        return "";
//                    }
//                }
//                return null;
//            }
//        };
//        gclreciveID.setFilters(new InputFilter[] { filter3 });
//        grwthchrtlstrcv = gclreciveID.getText().toString().trim();
//        if (TextUtils.isEmpty(grwthchrtlstrcv)) {
//            gclreciveID.setError("Please Enter Growth Chart Last Recived");
//            gclreciveID.requestFocus();
//            return;
//        }


        if (FdateId.getText().toString().equals("Date")){
            Toast.makeText(OtherInspectionActivity.this,"Select Date of last svisit of the supervisor to the awc",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT DATE OF LAST SVISIT OF THE SUPERVISOR TO THE AWC";
            createDialog(title,msg);
        }
        else {
            if (mothermeetingId.getText().toString().equals("Date")){
                Toast.makeText(OtherInspectionActivity.this,"SELECT DATE OF LAST SUPUSTI DIWAS OBSERVED",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT DATE OF LAST SUPUSTI DIWAS OBSERVED";
                createDialog(title,msg);

            }
            else {
                if (medicindateId.getText().toString().equals("Date")){
                    Toast.makeText(OtherInspectionActivity.this,"SELECT LAST RECEIVED DATE",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT LAST RECEIVED DATE";
                    createDialog(title,msg);

                }
                else {
                    if (YmedicinkitId.isChecked() || NmedicinkitId.isChecked()){
//                        InputFilter filter2 = new InputFilter() {
//                            public CharSequence filter(CharSequence source, int start, int end,
//                                                       Spanned dest, int dstart, int dend) {
//                                char[] chars = {'\'','"'};
//                                for (int i = start; i < end; i++) {
//                                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                        return "";
//                                    }
//                                }
//                                return null;
//                            }
//                        };
//                        presklrecvID.setFilters(new InputFilter[] { filter2 });
//                        preschlchrglstrcv = presklrecvID.getText().toString().trim();
//                        if (TextUtils.isEmpty(preschlchrglstrcv)) {
//                            presklrecvID.setError("Please Enter Pre_School Kit Last Recived");
//                            presklrecvID.requestFocus();
//                            return;
//                        }
                        if (bccGoodID.isChecked() || bccAverage.isChecked() || bccPoor.isChecked()){
                            InputFilter filter4 = new InputFilter() {
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
                            cmdID.setFilters(new InputFilter[] { filter4 });
                            awcremark = cmdID.getText().toString().trim();
                            if (TextUtils.isEmpty(awcremark)) {
                                cmdID.setError("Please Enter Command");
                                cmdID.requestFocus();
                                return;
                            }
                            if (last_recived.equals("a")){
                                if (UuseId.isChecked() || NUuseId.isChecked()){
                                    if (buildingdetails.equals("1")
                                            && informationoftoilet.equals("1")
                                            && informationofkitchen.equals("1")
                                            && adequatespaceforpse.equals("1")
                                            && electricity.equals("1")
                                            && drinkingwater.equals("1")
                                            && foodstoreddetails.equals("1")
                                            && conditionofequipmentotherawc.equals("1")
                                            && shishualoy.equals("1")
                                            && snpserved.equals("1")
                                            && nutritionalstatusobserved.equals("1")
                                            && otherName.equals("0")){
                                        OtherInspaction1();
                                        Toast.makeText(getApplicationContext(),"SAVE ALL DATA SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SAVE ALL DATA SUCCESSFULLY";
                                        createDialog(title,msg);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"SAVE ALL DATA",Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SAVE ALL DATA";
                                        createDialog(title,msg);
                                    }

                                }
                                else {
                                    Toast.makeText(OtherInspectionActivity.this,"SELECT LAST RECEIVED DATE USED FOR AWC OR NOT",Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT LAST RECEIVED DATE USED FOR AWC OR NOT";
                                    createDialog(title,msg);
                                }

                            }
                            else if (last_recived.equals("na")){
                                if (buildingdetails.equals("1")
                                        && informationoftoilet.equals("1")
                                        && informationofkitchen.equals("1")
                                        && adequatespaceforpse.equals("1")
                                        && electricity.equals("1")
                                        && drinkingwater.equals("1")
                                        && foodstoreddetails.equals("1")
                                        && conditionofequipmentotherawc.equals("1")
                                        && shishualoy.equals("1")
                                        && snpserved.equals("1")
                                        && nutritionalstatusobserved.equals("1")
                                        && otherName.equals("0")){
                                    OtherInspaction1();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"SAVE ALL DATA",Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SAVE ALL DATA";
                                    createDialog(title,msg);
                                }

                            }

                        }
                        else {
                            Toast.makeText(OtherInspectionActivity.this,"Select General clearness of awc and surrounding",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT GENERAL CLEARNESS OF AWC AND SURROUNDING";
                            createDialog(title,msg);
                        }
                    }
                    else {
                        Toast.makeText(OtherInspectionActivity.this,"SELECT LAST RECEIVED DATE",Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT LAST RECEIVED DATE";
                        createDialog(title,msg);

                    }
                }

            }

        }
        if (YmedicinkitId.isChecked()){
            if (UuseId.isChecked() || NUuseId.isChecked()){

            }
            else {
                Toast.makeText(OtherInspectionActivity.this,"SELECT LAST RECEIVED DATE USED FOR AWC OR NOT",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT LAST RECEIVED DATE USED FOR AWC OR NOT";
                createDialog(title,msg);

            }
        }


//        if (YmmorID.isChecked() || NmmorID.isChecked()){
//            if (YamcfamhID.isChecked() || NamcfamhID.isChecked()){
//                if (YvisitofunID.isChecked() || NvisitofunID.isChecked()){
//                    if (YmrID.isChecked() || NmrID.isChecked()){
//                        if (YfevcrrID.isChecked() || NfevcrrID.isChecked()){
//
//                            OtherInspaction();
//
//                        }
//                        else {
//                            Toast.makeText(OtherInspectionActivity.this,"Select Fuel Egg Vegetable Charges Received Regularly",Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                    else {
//                        Toast.makeText(OtherInspectionActivity.this,"Select Maintenance Of Records",Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//                else {
//                    Toast.makeText(OtherInspectionActivity.this,"Select Visit Of Other Functionaries",Toast.LENGTH_SHORT).show();
//                }
//            }
//            else {
//                Toast.makeText(OtherInspectionActivity.this,"Select Aw Level Monitoring Commttee Formed and meeting Held",Toast.LENGTH_SHORT).show();
//            }
//        }
//        else {
//
//            Toast.makeText(OtherInspectionActivity.this,"Select Mothers Meeting Organised Regulary",Toast.LENGTH_SHORT).show();
//        }


    }

    private void OtherInspaction1(){
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.OTHER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Nutritional"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("NutritionalOBJ"," "+array);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET otherinspection='1' WHERE allinspactionid=" +insid);
                                syncSnpDatabase1(insid,
                                        curTime,
                                        cmuntyprticptnnotice,
                                        FdateId.getText().toString(),
                                        mothermeetingId.getText().toString(),
                                        medicindateId.getText().toString(),
                                        last_recived,
                                        used_of_awc,
                                        gen_goth,
                                        awcremark,
                                        OTHER_SYNCED_WITH_SERVER);


                                String title = "Message Box";
                                String msg = "ARE YOU SURE FOR YOUR FINAL SUBMIT";
                                finalsubmit1(title,msg);

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
                        dbb.execSQL("UPDATE insflag SET otherinspection='1' WHERE allinspactionid=" +insid);
                        syncSnpDatabase1(insid,
                                curTime,
                                cmuntyprticptnnotice,
                                FdateId.getText().toString(),
                                mothermeetingId.getText().toString(),
                                medicindateId.getText().toString(),
                                last_recived,
                                used_of_awc,
                                gen_goth,
                                awcremark,
                                OTHER_NOT_SYNCED_WITH_SERVER);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // sup_visit,
                // medcin_kit_lst_rcv,
                // medcin_kit_st,
                // medcin_kit_use_st,
                // awc_gnrl_clr,
                // awc_remark
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("ins_time",curTime);

                // params.put("feed_intrup_duratn",feedintrupduratn);
                // params.put("sup_actn",supactn);

                params.put("comm_participation",cmuntyprticptnnotice);
                params.put("sup_visit",FdateId.getText().toString());

                params.put("last_supusti_date",mothermeetingId.getText().toString());
                params.put("medcin_kit_lst_rcv",medicindateId.getText().toString());
                params.put("medcin_kit_st",last_recived);
                params.put("medcin_kit_use_st",used_of_awc);
                params.put("awc_gnrl_clr", gen_goth);
//                params.put("mthr_meet",mthrmeet);
//                params.put("aw_monitr",awmonitr);
//                params.put("othr_functn_vst",othrfunctnvst);
//                params.put("rcd_maintain", rcdmaintain);
//                params.put("regulr_chrg_rcv",regulrchrgrcv);
//                params.put("medcin_chrg_lst_rcv",medcinchrglstrcv);
//                params.put("preschl_chrg_lst_rcv",preschlchrglstrcv);
//                params.put("grwth_chrt_lst_rcv",grwthchrtlstrcv);
                params.put("awc_remark",awcremark);
                Log.e("OTHER",insid+" "+curTime+" "+cmuntyprticptnnotice+" "
                        +FdateId.getText().toString()+" "+mothermeetingId.getText().toString()+" "+medicindateId.getText().toString()+" "+last_recived+" "+used_of_awc+" "+gen_goth+" "+awcremark);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }

    private void syncSnpDatabase1(String insid,
                                 String curTime,
                                 String cmuntyprticptnnotice,
                                 String supvisit,
                                 String lastsupustidate,
                                 String medcinkitlstrcv,
                                 String lastrecived,
                                 String usedofawc,
                                 String gengoth,
                                 String awcremark,
                                 int otherinspectionstatus){
        if (otherName.equals("0")){
            helper.OTHERINSPECTIONINSERT(insid,
                    curTime,
                    cmuntyprticptnnotice,
                    supvisit,
                    lastsupustidate,
                    medcinkitlstrcv,
                    lastrecived,
                    usedofawc,
                    gengoth,
                    awcremark,otherinspectionstatus);
        }
        else {
            helper.OTHERINSPECTIONUPDATE(dbid,insid,
                    curTime,
                    cmuntyprticptnnotice,
                    supvisit,
                    lastsupustidate,
                    medcinkitlstrcv,
                    lastrecived,
                    usedofawc,
                    gengoth,
                    awcremark,otherinspectionstatus);

        }
        String title = "Message Box";
        String msg = "ARE YOU SURE FOR YOUR FINAL SUBMIT";
        finalsubmit1(title,msg);

    }
    public void BuildingReturndata(){
        helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqldb = helper.getReadableDatabase();
        sqldb.execSQL("UPDATE awcsprocess SET flag='2' WHERE idprocess=" + dbid);
        //awcsdtl(dbid);
        awcsdtl(insid,userid);
        myInspaction(insid);
        processDelete(dbid,userid);
        listcheckboxDelete(insid,userid);
       //Intent intent = new Intent(OtherInspectionActivity.this,NavigationDrawerActivity.class);
      //startActivity(intent);
        Intent intent = new Intent(OtherInspectionActivity.this, NavigationDrawerActivity.class);
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
    public void onBackPressed() {
      Intent intent = new Intent(OtherInspectionActivity.this, NavigationDrawerActivity.class);
       startActivity(intent);
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
        try{
            if(otherinspactionNetworkchecker!=null)
                unregisterReceiver(otherinspactionNetworkchecker);
            if (broadcastReceiverother!=null)
                unregisterReceiver(broadcastReceiverother);
            if(finalSubmitNetworkcheck!=null)
                unregisterReceiver(finalSubmitNetworkcheck);
            if(broadcastReceiverotherr!=null)
                unregisterReceiver(broadcastReceiverotherr);
        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
