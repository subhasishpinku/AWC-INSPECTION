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
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityDatabase.SPNNetwokchecker;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;

public class SNPServedActivity extends AppCompatActivity implements View.OnClickListener {
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
    String planid;
    RadioGroup GsnpID,snpservedawcID,GPasperId,GPspnId,GPGIvthreemonthId;
    RadioButton YsnpID,NGsnpID,YsnpservedawcID,NsnpservedawcID,YasperId,NasperId,YspnId,NspnId,YGIvthreemonthId,NGIvthreemonthId;
    EditText menumorningSnkID,smtdID,ncpdID,nofcpdayID,lastthreemonthID,
            interaptionlastthreemonthID,interaptionlastthreemonthIDD,
            interaptionlastthreemonthIDDD,grsnpservedID,givecmdID,ncpdIDD,nofcpdayIDD;
    Button saveID;
    String userID,curDate,curTime;
    String mrngsnacks = "NA";
    String snpserve = "NA";
    String snacksserved = "NA";
    String hcmaspermnu = "NA";
    String menuofmorningsnacks = "NA";
    String snpmenu = "NA";
    String chldprsnt = "NA";
    String chldprsnt_today = "NA";
    String pmlmprsnt,pmlmprsnt_today = "NA";
    String avrgfeednglstthremnth = "NA";
    String anyfeedingintruptnlstthre_mnth = "NA";
    String anyfeedingintruptnlstthre_mnth2 = "NA";
    String anyfeedingintruptnlstthre_mnth3 = "NA";
    String snpntsrvreasn = "NA";
    String inspctncmnt = "NA";
    String dbid = "NA";
    String currentdate = "NA";
    String IdSnp = "NA";
    String snpName = "NA";
    String Emrngsnacks = "NA";
    String mrngsnacktyp = "NA";
    String Emrngsnacksmenu = "NA";
    String Esnpserve = "NA";
    String Esnpmenu = "NA";
    String Echldprsnt = "NA";
    String Epmlmprsnt = "NA";
    String Eavrgfeednglstthremnth = "NA";
    String snpservetyp = "NA";
    String hcmmenutoday = "NA";
    String chldprsntenroll = "NA";
    String Eanyfeedingintruptnlstthremnth = "NA";
    String Esnpntsrvreasn = "NA";
    String Einspctncmnt = "NA";
    String feedintrpt3days = "NA";
    String any_free_inter_last_three = "NA";
    String chldprsnttoday = "NA";
    String pmlmprsntenroll = "NA";
    String pmlmprsnttoday = "NA";
    String feedintrpt1days = "NA";
    String feedintrpt2days = "NA";
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
    DatabaseHelper helper;
    public static final int SNP_SYNCED_WITH_SERVER = 1;
    public static final int SNP_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiversnp;
    public static final String DATA_SAVED_BROADCAST_SNP = "icdswb.in.snpsaved";
    private SPNNetwokchecker spnNetwokchecker;
    String SPNSTATUS = "0";
    String mrngsnacksdb="";
    String menuofmorningsnacksdb ="";
    String mrngsnacktypdb ="";
    String snpservedb = "";
    String hcmaspermnudb = "";
    String snpmenudb ="";
    String chldprsntdb = "";
    String chldprsnttodaydb = "";
    String pmlmprsntdb ="";
    String pmlmprsnttodaydb ="";
    String avrgfeednglstthremnthdb ="";
    String anyfeedingintruptnlstthremnthdb = "";
    String snpntsrvreasndb ="";
    String feedintrpt2mdb = "";
    String anyfeedingintruptnlstthremnth2db ="";
    String feedintrpt3mdb ="";
    String anyfeedingintruptnlstthremnth3db ="";
    String snpntsrvreasnDB ="";
    String pninspctncmntdb ="";
    TextView awcstvId;
    ImageButton menumorningSnkspak,smtdspak,ncpdspak,nofcpdayspak,
            lastthreemonthspak,interaptionlastthreemonthspak,grsnpservedspak,givecmdspak,interaptionlastthreemonthspakK,
            interaptionlastthreemonthspakKK,ncpdspakk,nofcpdayspakk;
    private final int REQ_CODE_SPEECH_MENUMORNINGSNKSPAK= 100;
    private final int REQ_CODE_SPEECH_SMTDSPAK = 101;
    private final int REQ_CODE_SPEECH_NCPDSPAK = 102;
    private final int REQ_CODE_SPEECH_NOFCPDAYSPAK = 103;
    private final int REQ_CODE_SPEECH_LASTTHREEMONTHSPAK = 104;
    private final int REQ_CODE_SPEECH_INTERAPTIONLASTTHREEMONTHSPAK = 105;
    private final int REQ_CODE_SPEECH_GRSNPSERVEDSPAK = 106;
    private final int REQ_CODE_SPEECH_GIVECMDSPAK = 107;
    private final int REQ_CODE_SPEECH_GIVECMDSPAKPRESENT = 108;
    private final int REQ_CODE_SPEECH_GIVECMDSPAKPRESENTPLLM = 109;
    private final int REQ_CODE_SPEECH_TWOMONTH = 109;
    private final int REQ_CODE_SPEECH_THREEMONTH = 110;
    LinearLayout LVmornigsnkId,LVspnId,threemonthId;
    String feedintrpt1m = "";
    String feedintrpt2m = "";
    String feedintrpt3m = "";
    TextView mothId,mothIdd,mothIddd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_snpserved);
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
//        dbid = intent.getStringExtra("dbid");
//        currentdate= intent.getStringExtra("currentdate");
//        Log.e("currentdate",currentdate);

        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        mothId = (TextView)findViewById(R.id.mothId);
        mothIdd = (TextView)findViewById(R.id.mothIdd);
        mothIddd = (TextView)findViewById(R.id.mothIddd);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        // SimpleDateFormat format = new SimpleDateFormat(currentdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -3);
        Date d = cal.getTime();
        String res = format.format(d);
        String date=res;
        String[] items1 = date.split("-");
        String date1=items1[0];
        feedintrpt1m =items1[1];
        String year=items1[2];
        Log.e("Break_date"," "+date1+" "+feedintrpt1m+" "+" "+year+" ");
        //////////////////////////////////////////////////////////
        Calendar call = Calendar.getInstance();
        call.setTime(new Date());
        call.add(Calendar.MONTH, -2);
        Date dd = call.getTime();
        String ress = format.format(dd);
        String datee=ress;
        String[] items11 = datee.split("-");
        String date11=items11[0];
        feedintrpt2m =items11[1];
        String yeary=items11[2];
        ///////////////////////////////////////////////////////////////////////
        Calendar calll = Calendar.getInstance();
        calll.setTime(new Date());
        calll.add(Calendar.MONTH, -1);
        Date ddd = calll.getTime();
        String resss = format.format(ddd);
        String dateee=resss;
        String[] items111 = dateee.split("-");
        String date111=items111[0];
        feedintrpt3m =items111[1];
        String yearyy=items111[2];
        Log.e("MONTHMINUS", feedintrpt1m+ " "+feedintrpt2m+" "+feedintrpt3m);
        if (feedintrpt1m.equals("01")){
            mothId.setText("January");
        }
        else if (feedintrpt1m.equals("02")){
            mothId.setText("February");
        }
        else if (feedintrpt1m.equals("03")){
            mothId.setText("March");
        }
        else if (feedintrpt1m.equals("04")){
            mothId.setText("April");
        }
        else if (feedintrpt1m.equals("05")){
            mothId.setText("May");
        }
        else if (feedintrpt1m.equals("06")){
            mothId.setText("June");
        }
        else if (feedintrpt1m.equals("07")){
            mothId.setText("July");
        }
        else if (feedintrpt1m.equals("08")){
            mothId.setText("August");
        }
        else if (feedintrpt1m.equals("09")){
            mothId.setText("September");
        }
        else if (feedintrpt1m.equals("10")){
            mothId.setText("October");
        }
        else if (feedintrpt1m.equals("11")){
            mothId.setText("November");
        }
        else if (feedintrpt1m.equals("12")){
            mothId.setText("December");
        }
        else {

        }
        /////////////////////////////////////////////////////////
        if (feedintrpt2m.equals("01")){
            mothIdd.setText("January");
        }
        else if (feedintrpt2m.equals("02")){
            mothIdd.setText("February");
        }
        else if (feedintrpt2m.equals("03")){
            mothIdd.setText("March");
        }
        else if (feedintrpt2m.equals("04")){
            mothIdd.setText("April");
        }
        else if (feedintrpt2m.equals("05")){
            mothIdd.setText("May");
        }
        else if (feedintrpt2m.equals("06")){
            mothIdd.setText("June");
        }
        else if (feedintrpt2m.equals("07")){
            mothIdd.setText("July");
        }
        else if (feedintrpt2m.equals("08")){
            mothIdd.setText("August");
        }
        else if (feedintrpt2m.equals("09")){
            mothIdd.setText("September");
        }
        else if (feedintrpt2m.equals("10")){
            mothIdd.setText("October");
        }
        else if (feedintrpt2m.equals("11")){
            mothIdd.setText("November");
        }
        else if (feedintrpt2m.equals("12")){
            mothIdd.setText("December");
        }
        else {

        }
        //////////////////////////////////////////////////////////////////////////
        if (feedintrpt3m.equals("01")){
            mothIddd.setText("January");
        }
        else if (feedintrpt3m.equals("02")){
            mothIddd.setText("February");
        }
        else if (feedintrpt3m.equals("03")){
            mothIddd.setText("March");
        }
        else if (feedintrpt3m.equals("04")){
            mothIddd.setText("April");
        }
        else if (feedintrpt3m.equals("05")){
            mothIddd.setText("May");
        }
        else if (feedintrpt3m.equals("06")){
            mothIddd.setText("June");
        }
        else if (feedintrpt3m.equals("07")){
            mothIddd.setText("July");
        }
        else if (feedintrpt3m.equals("08")){
            mothIddd.setText("August");
        }
        else if (feedintrpt3m.equals("09")){
            mothIddd.setText("September");
        }
        else if (feedintrpt3m.equals("10")){
            mothIddd.setText("October");
        }
        else if (feedintrpt3m.equals("11")){
            mothIddd.setText("November");
        }
        else if (feedintrpt3m.equals("12")){
            mothIddd.setText("December");
        }
        else {

        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        Log.e("SNAP",planid);
        Log.e("SNA"," "+toilet+" "+awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+insid+" ");
        kitchen = intent.getStringExtra("kitchen");
        adqutspacepse = intent.getStringExtra("adqutspacepse");
        electric = intent.getStringExtra("electric");
        water = intent.getStringExtra("water");
        foodspace = intent.getStringExtra("foodspace");
        Log.e("SNAA"," "+kitchen+" "+adqutspacepse+" "+electric+" "+water+" "+foodspace+" ");
        menumorningSnkID = (EditText)findViewById(R.id.menumorningSnkID);
        smtdID = (EditText)findViewById(R.id.smtdID);
        ncpdID = (EditText)findViewById(R.id.ncpdID);
        nofcpdayID = (EditText)findViewById(R.id.nofcpdayID);
        lastthreemonthID = (EditText)findViewById(R.id.lastthreemonthID);
        interaptionlastthreemonthID = (EditText)findViewById(R.id.interaptionlastthreemonthID);
        grsnpservedID= (EditText)findViewById(R.id.grsnpservedID);
        grsnpservedID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        grsnpservedID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        givecmdID= (EditText)findViewById(R.id.givecmdID);
        givecmdID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        givecmdID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        ncpdIDD =(EditText)findViewById(R.id.ncpdIDD);
        nofcpdayIDD =(EditText)findViewById(R.id.nofcpdayIDD);
        nofcpdayIDD.setImeOptions(EditorInfo.IME_ACTION_DONE);
        interaptionlastthreemonthIDD = (EditText)findViewById(R.id.interaptionlastthreemonthIDD);
        interaptionlastthreemonthIDDD = (EditText)findViewById(R.id.interaptionlastthreemonthIDDD);
        LVmornigsnkId = (LinearLayout)findViewById(R.id.LVmornigsnkId);
        LVmornigsnkId.setVisibility(View.GONE);
        LVspnId = (LinearLayout)findViewById(R.id.LVspnId);
        LVspnId.setVisibility(View.GONE);
        threemonthId = (LinearLayout)findViewById(R.id.threemonthId);
        threemonthId.setVisibility(View.GONE);
        //saveID = (Button)findViewById(R.id.saveID);
       // saveID.setOnClickListener(this);
        initToolBar();
        radiobutton();
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
                IdSnp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                snpName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SNPSERVED));
                Log.e("SHISHUALOY"," "+IdSnp+" "+snpName);
            }
            while (cursor.moveToNext());
        }

        if (snpName.equals("0")){

        }
        else {
            editSnp();
        }

        broadcastReceiversnp = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        registerReceiver(broadcastReceiversnp, new IntentFilter(DATA_SAVED_BROADCAST_SNP));
        spnNetwokchecker = new SPNNetwokchecker();
        registerReceiver(spnNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        if (isNetworkAvailable()) {

        } else {
         //   String query = "SELECT * FROM " + TABLE_SPNTABLE + " where " + TABLE_SPNINSID + "=" + insid + " and " + TABLE_SPNSTATUS + "=" + SPNSTATUS;
            String query = "SELECT * FROM spntable WHERE spninsid=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cc = db.rawQuery(query, null);
            if (cc.moveToFirst()) {
                do {
                    String spntableid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SPNTABLEID));
                    String spninsid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SPNINSID));
                    mrngsnacksdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_MRNGSNACKS));
                    mrngsnacktypdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_MRNGSNACKTYP));
                    snpservedb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SNPSERVE));
                    hcmaspermnudb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_HCMASPERMNU));
                    snpmenudb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SNPMENU));
                    chldprsntdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CHLDPRSNT));
                    chldprsnttodaydb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CHIDPRSNTTODAY));
                    pmlmprsntdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PMLMPRSNT));
                    pmlmprsnttodaydb =    cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PMLMPRSNTTODAY));
                    avrgfeednglstthremnthdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_AVRGFEEDNGLSTTHREMNTH));
                    anyfeedingintruptnlstthremnthdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FEEDINTRPTLM));
                    snpntsrvreasndb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ANYFEEDINGINTRUPTNLSTTHREMNTH));
                    feedintrpt2mdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FEEDINTRPT2M));
                    anyfeedingintruptnlstthremnth2db = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH2));
                    feedintrpt3mdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FEEDINTRPT3M));
                    anyfeedingintruptnlstthremnth3db = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH3));
                    snpntsrvreasnDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SNPNTSRVREASN));
                    pninspctncmntdb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SPNINSPCTNCMNT));
                    String spncurtime = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SPNCURTIME));
                    String spnstatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SPNSTATUS));
                    Log.e("SNPSYNC", " " + spntableid
                            + " " + spninsid + " "
                            + mrngsnacksdb + " "
                            + mrngsnacktypdb
                            + " " + snpservedb + " "
                            + hcmaspermnudb + " "
                            + snpmenudb + " "
                            + chldprsntdb + " "

                            + chldprsnttodaydb
                            + " " + pmlmprsntdb
                            + " " + pmlmprsnttodaydb + " "
                            + avrgfeednglstthremnthdb+" "
                            +anyfeedingintruptnlstthremnthdb+" "
                            +snpntsrvreasndb+" "+feedintrpt2mdb+" "+anyfeedingintruptnlstthremnth2db+" "
                            +feedintrpt3mdb+" "
                            +anyfeedingintruptnlstthremnth3db+" "
                            +snpntsrvreasnDB+" "+pninspctncmntdb+" "+spncurtime+" "+spnstatus);
                    Log.e("anyfreeinterlastthree1",avrgfeednglstthremnthdb);
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
                                update_snp_inspection();
                                break;
                            default:
                        }
                        return true;
                    }
                });

        menumorningSnkspak = (ImageButton)findViewById(R.id.menumorningSnkspak);
        menumorningSnkspak.setOnClickListener(this);
        smtdspak = (ImageButton)findViewById(R.id.smtdspak);
        smtdspak.setOnClickListener(this);
        ncpdspak =(ImageButton)findViewById(R.id.ncpdspak);
        ncpdspak.setOnClickListener(this);
        nofcpdayspak = (ImageButton)findViewById(R.id.nofcpdayspak);
        nofcpdayspak.setOnClickListener(this);
        lastthreemonthspak = (ImageButton)findViewById(R.id.lastthreemonthspak);
        lastthreemonthspak.setOnClickListener(this);
        interaptionlastthreemonthspak = (ImageButton)findViewById(R.id.interaptionlastthreemonthspak);
        interaptionlastthreemonthspak.setOnClickListener(this);
        grsnpservedspak = (ImageButton)findViewById(R.id.grsnpservedspak);
        grsnpservedspak.setOnClickListener(this);
        givecmdspak = (ImageButton)findViewById(R.id.givecmdspak);
        givecmdspak.setOnClickListener(this);
        interaptionlastthreemonthspakK = (ImageButton)findViewById(R.id.interaptionlastthreemonthspakK);
        interaptionlastthreemonthspakKK = (ImageButton)findViewById(R.id.interaptionlastthreemonthspakKK);
        interaptionlastthreemonthspakKK.setOnClickListener(this);
        interaptionlastthreemonthspakK.setOnClickListener(this);
        ncpdspakk = (ImageButton)findViewById(R.id.ncpdspakk);
        ncpdspakk.setOnClickListener(this);
        nofcpdayspakk = (ImageButton)findViewById(R.id.nofcpdayspakk);
        nofcpdayspakk.setOnClickListener(this);
    }

    public void Show(){
        if (mrngsnacksdb.equals("Y")){
            YsnpID.setChecked(true);
        }
        else if (mrngsnacksdb.equals("N")){
            NGsnpID.setChecked(true);
        }
        else {
            YsnpID.setChecked(false);
            NGsnpID.setChecked(false);
        }

        if (mrngsnacktypdb.equals("ASM")){
            YasperId.setChecked(true);
        }
        else if (mrngsnacktypdb.equals("NASM")){
            NasperId.setChecked(true);
        }
        else {
            YasperId.setChecked(false);
            NasperId.setChecked(false);
        }


        if (snpservedb.equals("Y")){
            YsnpservedawcID.setChecked(true);
        }
        else if (snpservedb.equals("N")){
            NsnpservedawcID.setChecked(true);
        }
        else {
            YsnpservedawcID.setChecked(false);
            NsnpservedawcID.setChecked(false);
        }

        if (hcmaspermnudb.equals("ASM")){
            YspnId.setChecked(true);
        }
        else if (hcmaspermnudb.equals("NASM")){
            NspnId.setChecked(true);
        }
        else {
            YspnId.setChecked(false);
            NspnId.setChecked(false);
        }
        smtdID.setText(snpmenudb);
        ncpdID.setText(chldprsntdb);
        ncpdIDD.setText(chldprsnttodaydb);
        nofcpdayID.setText(pmlmprsntdb);
        nofcpdayIDD.setText(pmlmprsnttodaydb);
        if (avrgfeednglstthremnthdb.equals("Y")){
            YGIvthreemonthId.setChecked(true);
        }
        else if (avrgfeednglstthremnthdb.equals("N")){
            NGIvthreemonthId.setChecked(true);
        }
        else {
            YGIvthreemonthId.setChecked(false);
            NGIvthreemonthId.setChecked(false);
        }
        interaptionlastthreemonthID.setText(snpntsrvreasndb);
        interaptionlastthreemonthIDD.setText(anyfeedingintruptnlstthremnth2db);
        interaptionlastthreemonthIDDD.setText(anyfeedingintruptnlstthremnth3db);
        grsnpservedID.setText(snpntsrvreasnDB);
        givecmdID.setText(pninspctncmntdb);
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
    private void radiobutton(){
        GsnpID = (RadioGroup)findViewById(R.id.GsnpID);
        snpservedawcID = (RadioGroup)findViewById(R.id.snpservedawcID);
        GPasperId = (RadioGroup)findViewById(R.id.GPasperId);
        GPspnId = (RadioGroup)findViewById(R.id.GPspnId);
        GPGIvthreemonthId  = (RadioGroup)findViewById(R.id.GPGIvthreemonthId);
        YsnpID = (RadioButton)findViewById(R.id.YsnpID);
        NGsnpID= (RadioButton)findViewById(R.id.NGsnpID);
        YsnpservedawcID= (RadioButton)findViewById(R.id.YsnpservedawcID);
        NsnpservedawcID = (RadioButton)findViewById(R.id.NsnpservedawcID);
        YasperId  = (RadioButton)findViewById(R.id.YasperId);
        NasperId = (RadioButton)findViewById(R.id.NasperId);
        YspnId  = (RadioButton)findViewById(R.id.YspnId);
        NspnId = (RadioButton)findViewById(R.id.NspnId);
        YGIvthreemonthId = (RadioButton)findViewById(R.id.YGIvthreemonthId);
        NGIvthreemonthId = (RadioButton)findViewById(R.id.NGIvthreemonthId);
        GsnpID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
             if (YsnpID.isChecked()){
                 mrngsnacks = "Y";
                 Log.e("mrngsnacks",mrngsnacks);
                 LVmornigsnkId.setVisibility(View.VISIBLE);
             }
             else if (NGsnpID.isChecked()){
                 mrngsnacks = "N";
                 Log.e("mrngsnacks",mrngsnacks);
                 LVmornigsnkId.setVisibility(View.GONE);
             }
            }
        });

        snpservedawcID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (YsnpservedawcID.isChecked()){
                    snpserve = "Y";
                    LVspnId.setVisibility(View.VISIBLE);
                    Log.e("snpserve",snpserve);
                }
                else if (NsnpservedawcID.isChecked()){
                    snpserve = "N";
                    LVspnId.setVisibility(View.GONE);
                    Log.e("snpserve",snpserve);
                }
            }
        });
        GPasperId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (YasperId.isChecked()){
                    snacksserved = "ASM";
                }
                else if (NasperId.isChecked()){
                    snacksserved = "NASM";
                }

            }
        });
        GPspnId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (YspnId.isChecked()){
                    hcmaspermnu = "ASM";
                }
                else if (NspnId.isChecked()){
                    hcmaspermnu = "NASM";
                }
            }
        });

        GPGIvthreemonthId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (YGIvthreemonthId.isChecked()){
                    threemonthId.setVisibility(View.VISIBLE);
                    any_free_inter_last_three = "Y";
                }
                else if (NGIvthreemonthId.isChecked()){
                    threemonthId.setVisibility(View.GONE);
                    any_free_inter_last_three = "N";
                    interaptionlastthreemonthID.setText("");
                    interaptionlastthreemonthIDD.setText("");
                    interaptionlastthreemonthIDDD.setText("");
                    grsnpservedID.setText("");
                }

            }
        });

    }
    private void editSnp(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SNPEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SNPLOG"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray jsonArray = object.getJSONArray("snp_inspectn_dtl");
                            for (int i =0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Emrngsnacks = jsonObject.getString("mrng_snacks");
                                mrngsnacktyp = jsonObject.getString("mrng_snack_typ");
                                Esnpserve = jsonObject.getString("snp_serve");
                                snpservetyp = jsonObject.getString("snp_serve_typ");
                                hcmmenutoday = jsonObject.getString("hcm_menu_today");
                                chldprsntenroll = jsonObject.getString("chld_prsnt_enroll");
                                chldprsnttoday = jsonObject.getString("chld_prsnt_today");
                                pmlmprsntenroll = jsonObject.getString("pm_lm_prsnt_enroll");
                                pmlmprsnttoday = jsonObject.getString("pm_lm_prsnt_today");
                                Eanyfeedingintruptnlstthremnth = jsonObject.getString("any_feeding_intruptn_lst_thre_mnth");
                                feedintrpt1m = jsonObject.getString("feed_intrpt_1m");
                                feedintrpt1days = jsonObject.getString("feed_intrpt_1days");
                                feedintrpt2m = jsonObject.getString("feed_intrpt_2m");
                                feedintrpt2days = jsonObject.getString("feed_intrpt_2days");
                                feedintrpt3m = jsonObject.getString("feed_intrpt_3m");
                                feedintrpt3days = jsonObject.getString("feed_intrpt_3days");
                                Esnpntsrvreasn = jsonObject.getString("snp_nt_srv_reasn");
                                Einspctncmnt = jsonObject.getString("inspctn_cmnt");
                                if (Emrngsnacks.equals("y")){
                                    YsnpID.setChecked(true);
                                }
                                else if (Emrngsnacks.equals("n")){
                                    NGsnpID.setChecked(true);
                                }
                                else {
                                    YsnpID.setChecked(false);
                                    NGsnpID.setChecked(false);
                                }
                                if (mrngsnacktyp.equals("ASM")){
                                    YasperId.setChecked(true);
                                }
                                else if (mrngsnacktyp.equals("NASM")){
                                    NasperId.setChecked(true);
                                }

                                else {
                                    YasperId.setChecked(false);
                                    NasperId.setChecked(false);
                                }

                                menumorningSnkID.setText(Emrngsnacksmenu);
                                if (Esnpserve.equals("y")){
                                    YsnpservedawcID.setChecked(true);
                                }
                                else if (Esnpserve.equals("n")){
                                    NsnpservedawcID.setChecked(true);
                                }
                                else {
                                    YsnpservedawcID.setChecked(false);
                                    NsnpservedawcID.setChecked(false);
                                }


                                if (snpservetyp.equals("ASM")){
                                    YspnId.setChecked(true);
                                }
                                else if (snpservetyp.equals("NASM")){
                                    NspnId.setChecked(true);
                                }
                                else {
                                    YspnId.setChecked(false);
                                    NspnId.setChecked(false);
                                }

                                smtdID.setText(hcmmenutoday);

                                ncpdID.setText(chldprsntenroll);
                                ncpdIDD.setText(chldprsnttoday);
                                nofcpdayID.setText(pmlmprsntenroll);
                                nofcpdayIDD.setText(pmlmprsnttoday);
                                if (Eanyfeedingintruptnlstthremnth.equals("y")){
                                    YGIvthreemonthId.setChecked(true);
                                }
                                else if (Eanyfeedingintruptnlstthremnth.equals("n")){
                                    NGIvthreemonthId.setChecked(true);
                                }
                                else {
                                    YGIvthreemonthId.setChecked(false);
                                    NGIvthreemonthId.setChecked(false);
                                }
                                interaptionlastthreemonthID.setText(feedintrpt1days);
                                interaptionlastthreemonthIDD.setText(feedintrpt2days);
                                interaptionlastthreemonthIDDD.setText(feedintrpt3days);
                                grsnpservedID.setText(Esnpntsrvreasn);
                                givecmdID.setText(Einspctncmnt);
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
    private  void update_snp_inspection(){
        if (YsnpID.isChecked() || NGsnpID.isChecked()){
//                                    InputFilter filter = new InputFilter() {
//                                        public CharSequence filter(CharSequence source, int start, int end,
//                                                                   Spanned dest, int dstart, int dend) {
//                                            char[] chars = {'\'','"'};
//                                            for (int i = start; i < end; i++) {
//                                                if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                    return "";
//                                                }
//                                            }
//                                            return null;
//                                        }
//                                    };
//                                    menumorningSnkID.setFilters(new InputFilter[] { filter });
//                                    menuofmorningsnacks = menumorningSnkID.getText().toString().trim();
//                                    if (TextUtils.isEmpty(menuofmorningsnacks)) {
//                                        menumorningSnkID.setError("Please Enter Morning Snacks");
//                                        menumorningSnkID.requestFocus();
//                                       // return;
//                                    }

            if (YsnpservedawcID.isChecked() || NsnpservedawcID.isChecked()){
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
                smtdID.setFilters(new InputFilter[] { filter1 });
                snpmenu = smtdID.getText().toString().trim();
                if (TextUtils.isEmpty(snpmenu)) {
                    smtdID.setError("Please Enter Snp Menu that Day");
                    smtdID.requestFocus();
                    return;
                }
                InputFilter filter2 = new InputFilter() {
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
                ncpdID.setFilters(new InputFilter[] { filter2 });
                chldprsnt = ncpdID.getText().toString().trim();
                if (TextUtils.isEmpty(chldprsnt)) {
                    ncpdID.setError("Please Enter No");
                    ncpdID.requestFocus();
                    return;
                }
                InputFilter filter8 = new InputFilter() {
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
                ncpdIDD.setFilters(new InputFilter[] { filter8 });
                chldprsnt_today =  ncpdIDD.getText().toString().trim();
                if (TextUtils.isEmpty(chldprsnt_today)) {
                    ncpdIDD.setError("Please Enter No");
                    ncpdIDD.requestFocus();
                    return;
                }

                InputFilter filter3 = new InputFilter() {
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
                nofcpdayID.setFilters(new InputFilter[] { filter3 });
                pmlmprsnt = nofcpdayID.getText().toString().trim();
                if (TextUtils.isEmpty(pmlmprsnt)) {
                    nofcpdayID.setError("Please Enter No");
                    nofcpdayID.requestFocus();
                    return;
                }
                InputFilter filter33 = new InputFilter() {
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
                nofcpdayIDD.setFilters(new InputFilter[] { filter33 });
                pmlmprsnt_today = nofcpdayIDD.getText().toString().trim();
                if (TextUtils.isEmpty(pmlmprsnt)) {
                    nofcpdayIDD.setError("Please Enter No");
                    nofcpdayIDD.requestFocus();
                    return;
                }

//                InputFilter filter4 = new InputFilter() {
//                    public CharSequence filter(CharSequence source, int start, int end,
//                                               Spanned dest, int dstart, int dend) {
//                        char[] chars = {'\'','"'};
//                        for (int i = start; i < end; i++) {
//                            if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                return "";
//                            }
//                        }
//                        return null;
//                    }
//                };
//                lastthreemonthID.setFilters(new InputFilter[] { filter4 });
//                avrgfeednglstthremnth = lastthreemonthID.getText().toString().trim();
//                if (TextUtils.isEmpty(avrgfeednglstthremnth)) {
//                    lastthreemonthID.setError("Please Enter Average no of Feeding Days");
//                    lastthreemonthID.requestFocus();
//                    return;
//                }
//                if (any_free_inter_last_three.equals("Y")){
//
//                }
                if (YGIvthreemonthId.isChecked() || NGIvthreemonthId.isChecked()){
                    if (YGIvthreemonthId.isChecked()){
                        InputFilter filter5 = new InputFilter() {
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
                        interaptionlastthreemonthID.setFilters(new InputFilter[] { filter5 });
                        anyfeedingintruptnlstthre_mnth = interaptionlastthreemonthID.getText().toString().trim();
                        if (TextUtils.isEmpty(anyfeedingintruptnlstthre_mnth)) {
                            interaptionlastthreemonthID.setError("Please Enter Month One");
                            interaptionlastthreemonthID.requestFocus();
                            return;
                        }
                        //interaptionlastthreemonthIDD
                        InputFilter filter55 = new InputFilter() {
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
                        interaptionlastthreemonthIDD.setFilters(new InputFilter[] { filter55 });
                        anyfeedingintruptnlstthre_mnth2 = interaptionlastthreemonthIDD.getText().toString().trim();
                        if (TextUtils.isEmpty(anyfeedingintruptnlstthre_mnth2)) {
                            interaptionlastthreemonthIDD.setError("Please Enter Month One");
                            interaptionlastthreemonthIDD.requestFocus();
                            return;
                        }
                        //interaptionlastthreemonthIDDD

                        InputFilter filter555 = new InputFilter() {
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
                        interaptionlastthreemonthIDDD.setFilters(new InputFilter[] { filter555 });
                        anyfeedingintruptnlstthre_mnth3 = interaptionlastthreemonthIDDD.getText().toString().trim();
                        if (TextUtils.isEmpty(anyfeedingintruptnlstthre_mnth3)) {
                            interaptionlastthreemonthIDDD.setError("Please Enter Month One");
                            interaptionlastthreemonthIDDD.requestFocus();
                            return;
                        }
                        InputFilter filter6 = new InputFilter() {
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
                        grsnpservedID.setFilters(new InputFilter[] { filter6 });
                        snpntsrvreasn =  grsnpservedID.getText().toString().trim();
                        if (TextUtils.isEmpty(snpntsrvreasn)) {
                            grsnpservedID.setError("Please Enter Give Reson");
                            grsnpservedID.requestFocus();
                            return;
                        }

                    }

                    /////////////////////////////////////
                    InputFilter filter7 = new InputFilter() {
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
                    givecmdID.setFilters(new InputFilter[] { filter7 });
                    inspctncmnt =  givecmdID.getText().toString().trim();
                    if (TextUtils.isEmpty(inspctncmnt)) {
                        givecmdID.setError("Please Enter Command");
                        givecmdID.requestFocus();
                        return;
                    }
                    savedata();
                }
                else {
                    Toast.makeText(SNPServedActivity.this,"SELECT ANY FRRDING INTERRUPATION LAST THREE MONTH",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT ANY FRRDING INTERRUPATION LAST THREE MONTH";
                    createDialog(title,msg);
                }

            }
            else {
                Toast.makeText(SNPServedActivity.this,"SELECT SNP SERVED AT THE AWC",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT SNP SERVED AT THE AWC";
                createDialog(title,msg);
            }
        }
        else {
            Toast.makeText(SNPServedActivity.this,"SELECT MORNING SNACKS SERVED",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT MORNING SNACKS SERVED";
            createDialog(title,msg);
        }

        if (YasperId.isChecked() || NasperId.isChecked()){

        }
        else {
            Toast.makeText(SNPServedActivity.this,"SELECT MORNING SNACKS AS PER MENU",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT MORNING SNACKS AS PER MENU";
            createDialog(title,msg);
        }
        if (YspnId.isChecked() || NspnId.isChecked()){


        }
        else {
            if (YsnpservedawcID.isChecked()){
                Toast.makeText(SNPServedActivity.this,"SELECT HCM AS PER MENU",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT HCM AS PER MENU";
                createDialog(title,msg);
            }
        }

    }
    public void savedata(){
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SNP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SNP"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("SNPOBJ"," "+array);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET snpserved='1' WHERE allinspactionid=" +insid);
                                syncSnpDatabase(insid,
                                        mrngsnacks,
                                        snacksserved,
                                        snpserve,
                                        hcmaspermnu,
                                        snpmenu,
                                        chldprsnt,
                                        chldprsnt_today,
                                        pmlmprsnt,
                                        pmlmprsnt_today,
                                        any_free_inter_last_three,
                                        feedintrpt1m,
                                        anyfeedingintruptnlstthre_mnth,
                                        feedintrpt2m,
                                        anyfeedingintruptnlstthre_mnth2,
                                        feedintrpt3m,
                                        anyfeedingintruptnlstthre_mnth3,
                                        snpntsrvreasn,
                                        inspctncmnt,
                                        curTime,
                                        SNP_SYNCED_WITH_SERVER);
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
                        dbb.execSQL("UPDATE insflag SET snpserved='1' WHERE allinspactionid=" +insid);
                        syncSnpDatabase(insid,
                                mrngsnacks,
                                snacksserved,
                                snpserve,
                                hcmaspermnu,
                                snpmenu,
                                chldprsnt,
                                chldprsnt_today,
                                pmlmprsnt,
                                pmlmprsnt_today,
                                any_free_inter_last_three,
                                feedintrpt1m,
                                anyfeedingintruptnlstthre_mnth,
                                feedintrpt2m,
                                anyfeedingintruptnlstthre_mnth2,
                                feedintrpt3m,
                                anyfeedingintruptnlstthre_mnth3,
                                snpntsrvreasn,
                                inspctncmnt,
                                curTime,
                                SNP_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // mrng_snack_typ,
                // mrng_snacks_menu,
                // snp_serve,
                // snp_serve_typ,
                // hcm_menu_today,
                // chld_prsnt_today,
                // chld_prsnt_enroll,
                // pm_lm_prsnt_today,
                // pm_lm_prsnt_enroll,
                // feed_intrpt_1m,
                // feed_intrpt_1days,
                // feed_intrpt_2m,
                // feed_intrpt_2days,
                // feed_intrpt_3m,
                // feed_intrpt_3days ,
                // snp_nt_srv_reasn
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("mrng_snacks",mrngsnacks); //y
                params.put("mrng_snack_typ",snacksserved);
                params.put("snp_serve",snpserve);
                //params.put("snp_menu",snpmenu);
                params.put("snp_serve_typ",hcmaspermnu);
                /////FILD NOT NOT RECURED mrng_snacks_menu
               // params.put("mrng_snacks_menu",snpmenu);
                params.put("hcm_menu_today",snpmenu);
                params.put("chld_prsnt_enroll",chldprsnt);
                params.put("chld_prsnt_today",chldprsnt_today);
                params.put("pm_lm_prsnt_enroll",pmlmprsnt);
                params.put("pm_lm_prsnt_today",pmlmprsnt_today);
                params.put("any_feeding_intruptn_lst_thre_mnth",any_free_inter_last_three);
                params.put("feed_intrpt_1m",feedintrpt1m);
                params.put("feed_intrpt_1days",anyfeedingintruptnlstthre_mnth);
                params.put("feed_intrpt_2m",feedintrpt2m);
                params.put("feed_intrpt_2days",anyfeedingintruptnlstthre_mnth2);
                params.put("feed_intrpt_3m",feedintrpt3m);
                params.put("feed_intrpt_3days",anyfeedingintruptnlstthre_mnth3);
               // params.put("chld_prsnt",chldprsnt);
               // params.put("pm_lm_prsnt",pmlmprsnt);
              //  params.put("avrg_feedng_lst_thre_mnth",avrgfeednglstthremnth);
                // params.put("any_feeding_intruptn_lst_thre_mnth",anyfeedingintruptnlstthre_mnth);
                params.put("snp_nt_srv_reasn",snpntsrvreasn);
                params.put("inspctn_cmnt",inspctncmnt);
                params.put("ins_time",curTime);
                Log.e("SNPLOG","inspctn_id"+" "+insid
                        +"mrng_snacks"+" "+mrngsnacks
                        +"mrng_snack_typ"+" "+snacksserved
                        +"snp_serve"+"  "+snpserve
                        +"snp_serve_typ"+"  "+hcmaspermnu
                        +"mrng_snacks_menu"+" "+snpmenu
                        +"hcm_menu_today"+" "+snpmenu
                        +"chld_prsnt_enroll"+" "+chldprsnt
                        +"chld_prsnt_today"+"  "+chldprsnt_today
                        +"pm_lm_prsnt_enroll"+"  "+pmlmprsnt
                        +"pm_lm_prsnt_today"+"  "+pmlmprsnt_today
                        +"any_feeding_intruptn_lst_thre_mnth"+" "+any_free_inter_last_three
                        +"feed_intrpt_1m"+" "+feedintrpt1m
                        +"feed_intrpt_1days"+" "+anyfeedingintruptnlstthre_mnth
                        +"feed_intrpt_2m"+" "+feedintrpt2m
                        +"feed_intrpt_2days"+" "+anyfeedingintruptnlstthre_mnth2
                        +"feed_intrpt_3m"+" "+feedintrpt3m
                        +"feed_intrpt_3days"+" "+anyfeedingintruptnlstthre_mnth3
                        +"snp_nt_srv_reasn"+" "+snpntsrvreasn
                        +"inspctn_cmnt"+" "+inspctncmnt
                        +"ins_time"+" "+curTime);
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
                                 String mrngsnacks,
                                 String mrngsnacktyp,
                                 String snpserve,
                                 String hcmaspermnu,
                                 String snpmenu,
                                 String chldprsnt,
                                 String chldprsnttoday,
                                 String pmlmprsnt,
                                 String  pmlmprsnttoday,
                                 String anyfreeinterlastthree,
                                 String feedintrpt1m,
                                 String anyfeedingintruptnlstthremnth,
                                 String feedintrpt2m,
                                 String anyfeedingintruptnlstthremnth2,
                                 String feedintrpt3m,
                                 String anyfeedingintruptnlstthremnth3,
                                 String snpntsrvreasn,
                                 String inspctncmnt,
                                 String curTime,
                                 int spnstatus){

         Log.e("anyfreeinterlastthree",anyfreeinterlastthree);
                if (snpName.equals("0")){
                    helper.SPNINSERT(insid,
                            mrngsnacks,
                            mrngsnacktyp,
                            snpserve,
                            hcmaspermnu,
                            snpmenu,
                            chldprsnt,
                            chldprsnttoday,
                            pmlmprsnt,
                            pmlmprsnttoday,

                            anyfreeinterlastthree,

                            feedintrpt1m,
                            anyfeedingintruptnlstthremnth,
                            feedintrpt2m,
                            anyfeedingintruptnlstthremnth2,
                            feedintrpt3m,
                            anyfeedingintruptnlstthremnth3,
                            snpntsrvreasn,
                            inspctncmnt,
                            curTime,
                            spnstatus);
                }
           else {
                    helper.SPNUPDATE(dbid,
                            insid,
                            mrngsnacks,
                            mrngsnacktyp,
                            snpserve,
                            hcmaspermnu,
                            snpmenu,
                            chldprsnt,
                            chldprsnttoday,
                            pmlmprsnt,
                            pmlmprsnttoday,

                            anyfreeinterlastthree,

                            feedintrpt1m,
                            anyfeedingintruptnlstthremnth,
                            feedintrpt2m,
                            anyfeedingintruptnlstthremnth2,
                            feedintrpt3m,
                            anyfeedingintruptnlstthremnth3,
                            snpntsrvreasn,
                            inspctncmnt,
                            curTime,
                            spnstatus);
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
//        Intent intent = new Intent(SNPServedActivity.this,NutritionalStatusObservedActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("kitchen",kitchen);
//        bundle.putString("adqutspacepse",adqutspacepse);
//        bundle.putString("electric",electric);
//        bundle.putString("water",water);
//        bundle.putString("foodspace",foodspace);
//        bundle.putString("toilet",toilet);
//        bundle.putString("awcscode",awcscode);
//        bundle.putString("awcsname",awcsname);
//        bundle.putString("dbdistid",dbdistid);
//        bundle.putString("dbprojectid",dbprojectid);
//        bundle.putString("dbsectorid",dbsectorid);
//        bundle.putString("dbcenterid",dbcenterid);
//        bundle.putString("projectnamedb",projectnamedb);
//        bundle.putString("districnamedb",districnamedb);
//        bundle.putString("sectorrnamedb",sectorrnamedb);
//        bundle.putString("centernamedb",centernamedb);
//        bundle.putString("insid",insid);
//        bundle.putString("planid",planid);
//        bundle.putString("dbid",dbid);
//        intent.putExtras(bundle);
//        startActivity(intent);

        Intent intent = new Intent(SNPServedActivity.this, NutritionalStatusObservedActivity.class);
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
        if (YsnpID.isChecked() || NGsnpID.isChecked()){
//                                    InputFilter filter = new InputFilter() {
//                                        public CharSequence filter(CharSequence source, int start, int end,
//                                                                   Spanned dest, int dstart, int dend) {
//                                            char[] chars = {'\'','"'};
//                                            for (int i = start; i < end; i++) {
//                                                if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                    return "";
//                                                }
//                                            }
//                                            return null;
//                                        }
//                                    };
//                                    menumorningSnkID.setFilters(new InputFilter[] { filter });
//                                    menuofmorningsnacks = menumorningSnkID.getText().toString().trim();
//                                    if (TextUtils.isEmpty(menuofmorningsnacks)) {
//                                        menumorningSnkID.setError("Please Enter Morning Snacks");
//                                        menumorningSnkID.requestFocus();
//                                       // return;
//                                    }

            if (YsnpservedawcID.isChecked() || NsnpservedawcID.isChecked()){
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
                smtdID.setFilters(new InputFilter[] { filter1 });
                snpmenu = smtdID.getText().toString().trim();
                if (TextUtils.isEmpty(snpmenu)) {
                    smtdID.setError("Please Enter Snp Menu that Day");
                    smtdID.requestFocus();
                    return;
                }
                InputFilter filter2 = new InputFilter() {
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
                ncpdID.setFilters(new InputFilter[] { filter2 });
                chldprsnt = ncpdID.getText().toString().trim();
                if (TextUtils.isEmpty(chldprsnt)) {
                    ncpdID.setError("Please Enter No");
                    ncpdID.requestFocus();
                    return;
                }
                InputFilter filter8 = new InputFilter() {
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
                ncpdIDD.setFilters(new InputFilter[] { filter8 });
                chldprsnt_today =  ncpdIDD.getText().toString().trim();
                if (TextUtils.isEmpty(chldprsnt_today)) {
                    ncpdIDD.setError("Please Enter No");
                    ncpdIDD.requestFocus();
                    return;
                }

                InputFilter filter3 = new InputFilter() {
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
                nofcpdayID.setFilters(new InputFilter[] { filter3 });
                pmlmprsnt = nofcpdayID.getText().toString().trim();
                if (TextUtils.isEmpty(pmlmprsnt)) {
                    nofcpdayID.setError("Please Enter No");
                    nofcpdayID.requestFocus();
                    return;
                }
                InputFilter filter33 = new InputFilter() {
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
                nofcpdayIDD.setFilters(new InputFilter[] { filter33 });
                pmlmprsnt_today = nofcpdayIDD.getText().toString().trim();
                if (TextUtils.isEmpty(pmlmprsnt)) {
                    nofcpdayIDD.setError("Please Enter No");
                    nofcpdayIDD.requestFocus();
                    return;
                }

//                InputFilter filter4 = new InputFilter() {
//                    public CharSequence filter(CharSequence source, int start, int end,
//                                               Spanned dest, int dstart, int dend) {
//                        char[] chars = {'\'','"'};
//                        for (int i = start; i < end; i++) {
//                            if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                return "";
//                            }
//                        }
//                        return null;
//                    }
//                };
//                lastthreemonthID.setFilters(new InputFilter[] { filter4 });
//                avrgfeednglstthremnth = lastthreemonthID.getText().toString().trim();
//                if (TextUtils.isEmpty(avrgfeednglstthremnth)) {
//                    lastthreemonthID.setError("Please Enter Average no of Feeding Days");
//                    lastthreemonthID.requestFocus();
//                    return;
//                }
//                if (any_free_inter_last_three.equals("Y")){
//
//                }
                if (YGIvthreemonthId.isChecked() || NGIvthreemonthId.isChecked()){
                    if (YGIvthreemonthId.isChecked()){
                        InputFilter filter5 = new InputFilter() {
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
                        interaptionlastthreemonthID.setFilters(new InputFilter[] { filter5 });
                        anyfeedingintruptnlstthre_mnth = interaptionlastthreemonthID.getText().toString().trim();
                        if (TextUtils.isEmpty(anyfeedingintruptnlstthre_mnth)) {
                            interaptionlastthreemonthID.setError("Please Enter Month One");
                            interaptionlastthreemonthID.requestFocus();
                            return;
                        }
                        //interaptionlastthreemonthIDD
                        InputFilter filter55 = new InputFilter() {
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
                        interaptionlastthreemonthIDD.setFilters(new InputFilter[] { filter55 });
                        anyfeedingintruptnlstthre_mnth2 = interaptionlastthreemonthIDD.getText().toString().trim();
                        if (TextUtils.isEmpty(anyfeedingintruptnlstthre_mnth2)) {
                            interaptionlastthreemonthIDD.setError("Please Enter Month One");
                            interaptionlastthreemonthIDD.requestFocus();
                            return;
                        }
                        //interaptionlastthreemonthIDDD

                        InputFilter filter555 = new InputFilter() {
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
                        interaptionlastthreemonthIDDD.setFilters(new InputFilter[] { filter555 });
                        anyfeedingintruptnlstthre_mnth3 = interaptionlastthreemonthIDDD.getText().toString().trim();
                        if (TextUtils.isEmpty(anyfeedingintruptnlstthre_mnth3)) {
                            interaptionlastthreemonthIDDD.setError("Please Enter Month One");
                            interaptionlastthreemonthIDDD.requestFocus();
                            return;
                        }
                        InputFilter filter6 = new InputFilter() {
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
                        grsnpservedID.setFilters(new InputFilter[] { filter6 });
                        snpntsrvreasn =  grsnpservedID.getText().toString().trim();
                        if (TextUtils.isEmpty(snpntsrvreasn)) {
                            grsnpservedID.setError("Please Enter Give Reson");
                            grsnpservedID.requestFocus();
                            return;
                        }

                    }

                    /////////////////////////////////////
                    InputFilter filter7 = new InputFilter() {
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
                    givecmdID.setFilters(new InputFilter[] { filter7 });
                    inspctncmnt =  givecmdID.getText().toString().trim();
                    if (TextUtils.isEmpty(inspctncmnt)) {
                        givecmdID.setError("Please Enter Command");
                        givecmdID.requestFocus();
                        return;
                    }
                    savedata1();
                }
                else {
                    Toast.makeText(SNPServedActivity.this,"SELECT ANY FRRDING INTERRUPATION LAST THREE MONTH",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT ANY FRRDING INTERRUPATION LAST THREE MONTH";
                    createDialog(title,msg);
                }

            }
            else {
                Toast.makeText(SNPServedActivity.this,"SELECT SNP SERVED AT THE AWC",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT SNP SERVED AT THE AWC";
                createDialog(title,msg);
            }
        }
        else {
            Toast.makeText(SNPServedActivity.this,"SELECT MORNING SNACKS SERVED",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT MORNING SNACKS SERVED";
            createDialog(title,msg);
        }

        if (YasperId.isChecked() || NasperId.isChecked()){

        }
        else {
            Toast.makeText(SNPServedActivity.this,"SELECT MORNING SNACKS AS PER MENU",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT MORNING SNACKS AS PER MENU";
            createDialog(title,msg);
        }
        if (YspnId.isChecked() || NspnId.isChecked()){


        }
        else {
            if (YsnpservedawcID.isChecked()){
                Toast.makeText(SNPServedActivity.this,"SELECT HCM AS PER MENU",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT HCM AS PER MENU";
                createDialog(title,msg);
            }
        }

    }

    public void savedata1(){
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SNP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SNP"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("SNPOBJ"," "+array);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET snpserved='1' WHERE allinspactionid=" +insid);
                                syncSnpDatabase1(insid,
                                        mrngsnacks,
                                        snacksserved,
                                        snpserve,
                                        hcmaspermnu,
                                        snpmenu,
                                        chldprsnt,
                                        chldprsnt_today,
                                        pmlmprsnt,
                                        pmlmprsnt_today,
                                        any_free_inter_last_three,
                                        feedintrpt1m,
                                        anyfeedingintruptnlstthre_mnth,
                                        feedintrpt2m,
                                        anyfeedingintruptnlstthre_mnth2,
                                        feedintrpt3m,
                                        anyfeedingintruptnlstthre_mnth3,
                                        snpntsrvreasn,
                                        inspctncmnt,
                                        curTime,
                                        SNP_SYNCED_WITH_SERVER);
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
                        dbb.execSQL("UPDATE insflag SET snpserved='1' WHERE allinspactionid=" +insid);
                        syncSnpDatabase1(insid,
                                mrngsnacks,
                                snacksserved,
                                snpserve,
                                hcmaspermnu,
                                snpmenu,
                                chldprsnt,
                                chldprsnt_today,
                                pmlmprsnt,
                                pmlmprsnt_today,
                                any_free_inter_last_three,
                                feedintrpt1m,
                                anyfeedingintruptnlstthre_mnth,
                                feedintrpt2m,
                                anyfeedingintruptnlstthre_mnth2,
                                feedintrpt3m,
                                anyfeedingintruptnlstthre_mnth3,
                                snpntsrvreasn,
                                inspctncmnt,
                                curTime,
                                SNP_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // mrng_snack_typ,
                // mrng_snacks_menu,
                // snp_serve,
                // snp_serve_typ,
                // hcm_menu_today,
                // chld_prsnt_today,
                // chld_prsnt_enroll,
                // pm_lm_prsnt_today,
                // pm_lm_prsnt_enroll,
                // feed_intrpt_1m,
                // feed_intrpt_1days,
                // feed_intrpt_2m,
                // feed_intrpt_2days,
                // feed_intrpt_3m,
                // feed_intrpt_3days ,
                // snp_nt_srv_reasn
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("mrng_snacks",mrngsnacks); //y
                params.put("mrng_snack_typ",snacksserved);
                params.put("snp_serve",snpserve);
                //params.put("snp_menu",snpmenu);
                params.put("snp_serve_typ",hcmaspermnu);
                /////FILD NOT NOT RECURED mrng_snacks_menu
                // params.put("mrng_snacks_menu",snpmenu);
                params.put("hcm_menu_today",snpmenu);
                params.put("chld_prsnt_enroll",chldprsnt);
                params.put("chld_prsnt_today",chldprsnt_today);
                params.put("pm_lm_prsnt_enroll",pmlmprsnt);
                params.put("pm_lm_prsnt_today",pmlmprsnt_today);
                params.put("any_feeding_intruptn_lst_thre_mnth",any_free_inter_last_three);
                params.put("feed_intrpt_1m",feedintrpt1m);
                params.put("feed_intrpt_1days",anyfeedingintruptnlstthre_mnth);
                params.put("feed_intrpt_2m",feedintrpt2m);
                params.put("feed_intrpt_2days",anyfeedingintruptnlstthre_mnth2);
                params.put("feed_intrpt_3m",feedintrpt3m);
                params.put("feed_intrpt_3days",anyfeedingintruptnlstthre_mnth3);
                // params.put("chld_prsnt",chldprsnt);
                // params.put("pm_lm_prsnt",pmlmprsnt);
                //  params.put("avrg_feedng_lst_thre_mnth",avrgfeednglstthremnth);
                // params.put("any_feeding_intruptn_lst_thre_mnth",anyfeedingintruptnlstthre_mnth);
                params.put("snp_nt_srv_reasn",snpntsrvreasn);
                params.put("inspctn_cmnt",inspctncmnt);
                params.put("ins_time",curTime);
                Log.e("SNPLOG","inspctn_id"+" "+insid
                        +"mrng_snacks"+" "+mrngsnacks
                        +"mrng_snack_typ"+" "+snacksserved
                        +"snp_serve"+"  "+snpserve
                        +"snp_serve_typ"+"  "+hcmaspermnu
                        +"mrng_snacks_menu"+" "+snpmenu
                        +"hcm_menu_today"+" "+snpmenu
                        +"chld_prsnt_enroll"+" "+chldprsnt
                        +"chld_prsnt_today"+"  "+chldprsnt_today
                        +"pm_lm_prsnt_enroll"+"  "+pmlmprsnt
                        +"pm_lm_prsnt_today"+"  "+pmlmprsnt_today
                        +"any_feeding_intruptn_lst_thre_mnth"+" "+any_free_inter_last_three
                        +"feed_intrpt_1m"+" "+feedintrpt1m
                        +"feed_intrpt_1days"+" "+anyfeedingintruptnlstthre_mnth
                        +"feed_intrpt_2m"+" "+feedintrpt2m
                        +"feed_intrpt_2days"+" "+anyfeedingintruptnlstthre_mnth2
                        +"feed_intrpt_3m"+" "+feedintrpt3m
                        +"feed_intrpt_3days"+" "+anyfeedingintruptnlstthre_mnth3
                        +"snp_nt_srv_reasn"+" "+snpntsrvreasn
                        +"inspctn_cmnt"+" "+inspctncmnt
                        +"ins_time"+" "+curTime);
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
                                 String mrngsnacks,
                                 String mrngsnacktyp,
                                 String snpserve,
                                 String hcmaspermnu,
                                 String snpmenu,
                                 String chldprsnt,
                                 String chldprsnttoday,
                                 String pmlmprsnt,
                                 String  pmlmprsnttoday,
                                 String anyfreeinterlastthree,
                                 String feedintrpt1m,
                                 String anyfeedingintruptnlstthremnth,
                                 String feedintrpt2m,
                                 String anyfeedingintruptnlstthremnth2,
                                 String feedintrpt3m,
                                 String anyfeedingintruptnlstthremnth3,
                                 String snpntsrvreasn,
                                 String inspctncmnt,
                                 String curTime,
                                 int spnstatus){

        Log.e("anyfreeinterlastthree",anyfreeinterlastthree);
        if (snpName.equals("0")){
            helper.SPNINSERT(insid,
                    mrngsnacks,
                    mrngsnacktyp,
                    snpserve,
                    hcmaspermnu,
                    snpmenu,
                    chldprsnt,
                    chldprsnttoday,
                    pmlmprsnt,
                    pmlmprsnttoday,

                    anyfreeinterlastthree,

                    feedintrpt1m,
                    anyfeedingintruptnlstthremnth,
                    feedintrpt2m,
                    anyfeedingintruptnlstthremnth2,
                    feedintrpt3m,
                    anyfeedingintruptnlstthremnth3,
                    snpntsrvreasn,
                    inspctncmnt,
                    curTime,
                    spnstatus);
        }
        else {
            helper.SPNUPDATE(dbid,
                    insid,
                    mrngsnacks,
                    mrngsnacktyp,
                    snpserve,
                    hcmaspermnu,
                    snpmenu,
                    chldprsnt,
                    chldprsnttoday,
                    pmlmprsnt,
                    pmlmprsnttoday,

                    anyfreeinterlastthree,

                    feedintrpt1m,
                    anyfeedingintruptnlstthremnth,
                    feedintrpt2m,
                    anyfeedingintruptnlstthremnth2,
                    feedintrpt3m,
                    anyfeedingintruptnlstthremnth3,
                    snpntsrvreasn,
                    inspctncmnt,
                    curTime,
                    spnstatus);
        }
        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(SNPServedActivity.this, InspectionListActivity.class);
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
        switch (v.getId()){
//            case R.id.saveID:
//                if (YsnpID.isChecked() || NGsnpID.isChecked()){
//                    InputFilter filter = new InputFilter() {
//                        public CharSequence filter(CharSequence source, int start, int end,
//                                                   Spanned dest, int dstart, int dend) {
//                            char[] chars = {'\'','"'};
//                            for (int i = start; i < end; i++) {
//                                if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                    return "";
//                                }
//                            }
//                            return null;
//                        }
//                    };
//                    menumorningSnkID.setFilters(new InputFilter[] { filter });
//                    menuofmorningsnacks = menumorningSnkID.getText().toString().trim();
//                    if (TextUtils.isEmpty(menuofmorningsnacks)) {
//                        menumorningSnkID.setError("Please Enter Morning Snacks");
//                        menumorningSnkID.requestFocus();
//                        return;
//                    }
//
//                    if (YsnpservedawcID.isChecked() || NsnpservedawcID.isChecked()){
//                    update_snp_inspection();
//                }
//                else {
//                    Toast.makeText(SNPServedActivity.this,"Select Snp Served at The Awc",Toast.LENGTH_SHORT).show();
//                }
//                }
//            else {
//                Toast.makeText(SNPServedActivity.this,"Select Morning Snacks Served",Toast.LENGTH_SHORT).show();
//            }
//
//            break;

            case R.id.menumorningSnkspak:
                menumorningSnkspak();
                break;
            case R.id.smtdspak:
                smtdspak();
                break;
            case R.id.ncpdspak:
                ncpdspak();
                break;
            case R.id.nofcpdayspak:
                nofcpdayspak();
                break;
            case R.id.lastthreemonthspak:
                lastthreemonthspak();
                break;
            case R.id.interaptionlastthreemonthspak:
                interaptionlastthreemonthspak();
                   break;
            case R.id.grsnpservedspak:
                grsnpservedspak();
                break;
            case R.id.givecmdspak:
                givecmdspak();
                break;
            case R.id.ncpdspakk:
                givechildrenpresent();
                break;
            case R.id.nofcpdayspakk:
                givepmlmpresent();
                break;
            case R.id.interaptionlastthreemonthspakK:
                giveinteraptionlastthreemonthspakK();
                break;
            case R.id.interaptionlastthreemonthspakKK:
                giveinteraptionlastthreemonthspakKK();
                break;
            default:
        }
    }

    private void menumorningSnkspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_MENUMORNINGSNKSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void smtdspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_SMTDSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void ncpdspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_NCPDSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void nofcpdayspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_NOFCPDAYSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void lastthreemonthspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_LASTTHREEMONTHSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void interaptionlastthreemonthspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INTERAPTIONLASTTHREEMONTHSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void grsnpservedspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_GRSNPSERVEDSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void givecmdspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_GIVECMDSPAK);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void givechildrenpresent(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_GIVECMDSPAKPRESENT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void givepmlmpresent(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_GIVECMDSPAKPRESENTPLLM);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void giveinteraptionlastthreemonthspakK(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_TWOMONTH);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void giveinteraptionlastthreemonthspakKK(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_THREEMONTH);
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
            case REQ_CODE_SPEECH_MENUMORNINGSNKSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    menumorningSnkID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_SMTDSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    smtdID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_NCPDSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   // ncpdID.setText(result.get(0));
                    isNumber(result.get(0));
                }
                break;
            }
        }
        switch (requestCode) {
            case REQ_CODE_SPEECH_NOFCPDAYSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   // nofcpdayID.setText(result.get(0));
                    isNumberrrr(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_LASTTHREEMONTHSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    lastthreemonthID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_INTERAPTIONLASTTHREEMONTHSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   // interaptionlastthreemonthID.setText(result.get(0));
                    interaptionlastthreemonthspakK(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_GRSNPSERVEDSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    grsnpservedID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_GIVECMDSPAK: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    givecmdID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_GIVECMDSPAKPRESENT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //ncpdIDD.setText(result.get(0));
                    isNumberr(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_GIVECMDSPAKPRESENTPLLM: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //nofcpdayIDD.setText(result.get(0));
                    isNumberrr(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_TWOMONTH: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //interaptionlastthreemonthIDD.setText(result.get(0));
                    twomonth(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_THREEMONTH: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //interaptionlastthreemonthIDDD.setText(result.get(0));
                    threemonth(result.get(0));
                }
                break;
            }
        }
    }

    private boolean isNumber(String word)
    {
        boolean isNumber = false;
        try
        {
            Integer.parseInt(word);
            isNumber = true;
            ncpdID.setText(word);
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }
    private boolean isNumberr(String word)
    {
        boolean isNumber = false;
        try
        {
            Integer.parseInt(word);
            isNumber = true;
            ncpdIDD.setText(word);
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }

    private boolean isNumberrr(String word)
    {
        boolean isNumber = false;
        try
        {
            Integer.parseInt(word);
            isNumber = true;
            nofcpdayIDD.setText(word);
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }

    private boolean isNumberrrr(String word)
    {
        boolean isNumber = false;
        try
        {
            Integer.parseInt(word);
            isNumber = true;
            nofcpdayID.setText(word);
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }

    private boolean interaptionlastthreemonthspakK(String word){
        boolean isNumber = false;
        try
        {
            Integer.parseInt(word);
            isNumber = true;
            interaptionlastthreemonthID.setText(word);
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;

    }

    private boolean twomonth(String word){
        boolean isNumber = false;
        try
        {
            Integer.parseInt(word);
            isNumber = true;
            interaptionlastthreemonthIDD.setText(word);
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;

    }
    private boolean threemonth(String word){
        boolean isNumber = false;
        try
        {
            Integer.parseInt(word);
            isNumber = true;
            interaptionlastthreemonthIDDD.setText(word);
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
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
            if(spnNetwokchecker!=null)
                unregisterReceiver(spnNetwokchecker);
            if (broadcastReceiversnp!=null)
                unregisterReceiver(broadcastReceiversnp);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
