package icdswb.in;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityDatabase.ShishuAloyNetwokchecker;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SHISHUALOYINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;

public class ShishuAloyActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    TextView blockcornercogitiveId,bookcornerId,drawingcornerId,playcorner,awcstvId;
    AlertDialog.Builder builder;
    private AlertDialog alertDialog = null;
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
    String kitchen,adqutspacepse,electric,water,foodspace;
    String planid;
    RadioGroup sishualoyID,GbccID,GbcID,GdcID,GpcID,GwerunID,
            GwwuekpID,GciarfID,awcdecceID,GPYawcId,GpawctlmId,
            GpchildrenartworkId,GppseId,GPawwroutingId,GpassesmentcardId,GpinusenotinuseId,
            NGPparticipentpscId,NGPifassenmentId,NGpinusenotinuseId,GpchildrenceeeprocessId;

    RadioButton YsishualoyID,NsishualoyID,bccGoodID,bccAverage,bccPoor,bccNA,bcgoodID,bcaverageID,
            bcpoorID,bcnaID,dcgoodID,dcaverageID,dcPoorID,dcnaID,pcgoodID,pcaverageID,pcpoorID,
            pcnaID,YwerunID,NwerunID,properlyID,naID,YciarfID,NciarfID,YawcdecceID,
            NawcdecceID,Yawcfound,Nawcfound,tlmYId,tlmNId,YartworkId,NartworkId,
            YpseId,NpseId,YawwroutingId,NawwroutingId,YassesmentcardId,
            NassesmentcardId,InuseId,NotinuseId,NNYparticipentpscId,
            NNNparticipentpscId,NYifassenmentId,NNifassenmentId,NInuseId,NNotinuseId,YchildrenceeeprocessId,NchildrenceeeprocessId;
    EditText ecceawcfoundID,psenmID,ncpotID,responchildID,GvCID;
    Button saveID;
    String curDate,curTime,userID,dbid;
    String sishualoy = "NA";
    String cornercgnitv = "NA";
    String bookcorner = "NA";
    String drawcornr = "NA";
    String playcorners = "NA";
    String eccrun = "NA";
    String eccactvtytyp = "NA";
    String tlam = "NA";
    String avaleindvsualchildactvtyrcd = "NA";
    String wheterawwriting,assesmentcard = "NA";
    String assesmentcard_use = "NA";
    String eccactvtytypRep = "NA";
    String proprecckit = "NA";
    String indvsualchildactvtyrcd = "NA";
    String awcdecortnfrecce = "NA";
    String pseactivitynm = "NA";
    String chldparticipatpse = "NA";
    String totchldprsnt = "NA";
    String childenrolled = "NA";
    String childfoundtodayy = "NA";
    String chldrspnse = "NA";
    String inspectrcmnt = "NA";
    String Idshishualoy = "NA";
    String shishualoy = "NA";
    String Esishualoy = "NA";
    String Ecornercgnitv = "NA";
    String Ebookcorner = "NA";
    String Edrawcornr = "NA";
    String Eplaycorner = "NA";
    String Eeccrun = "NA";
    String Eeccactvtytyp = "NA";
    String Eproprecckit = "NA";
    String pseactvfound = "NA";
    String Eindvsualchildactvtyrcd = "NA";
    String Eawcdecortnfrecce = "NA";
    String Epseactivitynm = "NA";
    String Etotchldprsnt = "NA";
    String Echldrspnse = "NA";
    String awwfollowroutine = "NA";
    String Einspectrcmnt = "NA";
    String ecceprocess = "NA";
    String eccroutntheme ="0";
    String eccactivtyfound = "0";
    String awwtlm = "NA";
    String indvsualchildartwork = "NA";
    String awwroutinfollow = "NA";
    String totchldenroll = "NA";
    String totchldfoundtoday = "NA";
    String chldparticipatecc = "NA";
    String assmntcard = "NA";
    String assmntcardst = "NA";
    String lastecckitrcvdt = "NA";
    String lasteccdayobserv = "NA";
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
    public static final int SHISHUALOY_SYNCED_WITH_SERVER = 1;
    public static final int SHISHUALOY_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceivereshishualoy;
    public static final String DATA_SAVED_BROADCAST_SHISHUALOY = "icdswb.in.shishualoysaved";
    private ShishuAloyNetwokchecker shishuAloyNetwokchecker;
    String SHISHUALOYSTATUS ="0";
    String  sishualoyDB = "";
    String cornercgnitvDB ="";
    String bookcornerDB ="";
    String drawcornrDB ="";
    String playcornersDB ="";
    String eccrunDB ="";
    String valuesutingDB ="0";
    String eccactvtytypDB ="";
    String awcvaluestringDB = "0";
    String tlamDB="";
    String eccactvtytyprep ="";
    String proprecckitDB ="";
    String avaleindvsualchildactvtyrcdDB ="";
    String indvsualchildactvtyrcdDB ="";
    String awcdecortnfrecceDB = "";
    String fitorecccheckDB = "";
    String childenrolledDB ="";
    String childfoundtodayyDB = "";
    String ecceprocessDB ="";
    String assesmentcardDB ="";
    String assesmentcarduseDB ="";
    String ecckitdateDB ="";
    String eccobserdateDB ="";
    String pseactvfoundDB ="";
    String pseactivitynmDB = "";
    String totchldprsntDB ="";
    String chldparticipatpseDB ="";
    String wheterawwritingDB ="";
    String shishualoyinspectrcmntDB = "";
    private final int REQ_CODE_SPEECH_PSEACTIVITYFOUND= 100;
    private final int REQ_CODE_SPEECH_PSEACTIVITYSEEN= 101;
    private final int REQ_CODE_SPEECH_PSEPRSENTOUTOFTOTAL= 102;
    private final int REQ_CODE_SPEECH_PSECHILDREN= 104;
    private final int REQ_CODE_SPEECH_PSECOMMAND= 105;
    ImageButton ecceawcfoundspak,psenmspak,ncpotIDspak,cmdspak,commandspk;
    LinearLayout lv2,llv,lvroutId,lveccacfoundId,awcecceId,Nsishuid1,LVinuseId,NLVinuseId;
    Spinner routingId,eccAfSp;
    String routing = "NA";
    String valuesuting = "NA";
    String awcfound = "NA";
    String awcvaluestring = "NA";
    CheckBox chatId,labelId,rulesId,routId,calId,childnameId,childrenfolderId,displaychId,thembasewordwallId;
    String chart_according ="0";
    String chart_accordingg ="00";
    String labelling = "0";
    String rules = "0";
    String routine = "0";
    String calender = "0";
    String childrenname = "0";
    String childrenfolder ="0";
    String displaywork = "0";
    String themebaseworld = "0";
    ImageView searchID,searchIDD;
    TextView FdateId,TdateId,printId,totalId;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    Calendar myCalendarr;
    DatePickerDialog.OnDateSetListener datee;
    String ecckitdate = "NA";
    String eccobserdate = "NA";
    EditText NenrolledchildrenId,NchildfoundId,NGvCID,childenrolledId,childfounId;
    ImageButton NsoundenrolledchildrenId,NsoundchildfoundId,Ncommandspk,spchildenrolledId,childfounspId;
    String[] list1;
    String[] list;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter;
    String curdate;
    int childenrolledd;
    int childfoundtodayyy;
    String FITORECCCHECK;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shishualoy);
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
        //  Log.e("dbid",dbid);
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
        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        Log.e("shA",planid);
        Log.e("SHISUALY"," "+toilet+" "+awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+insid+" ");
     //   kitchen = intent.getStringExtra("kitchen");
     //   adqutspacepse = intent.getStringExtra("adqutspacepse");
      //  electric = intent.getStringExtra("electric");
      //  water = intent.getStringExtra("water");
      //  foodspace = intent.getStringExtra("foodspace");
        Log.e("SHISUALY"," "+kitchen+" "+adqutspacepse+" "+electric+" "+water+" "+foodspace+" ");
        blockcornercogitiveId = (TextView)findViewById(R.id.blockcornercogitiveId);
        bookcornerId = (TextView)findViewById(R.id.bookcornerId);
        drawingcornerId = (TextView)findViewById(R.id.drawingcornerId);
        playcorner = (TextView)findViewById(R.id.playcorner);
//        saveID = (Button)findViewById(R.id.saveID);
       // saveID.setOnClickListener(this);
        ecceawcfoundID = (EditText)findViewById(R.id.ecceawcfoundID);
        psenmID = (EditText)findViewById(R.id.psenmID);
        ncpotID = (EditText)findViewById(R.id.ncpotID);
        responchildID = (EditText)findViewById(R.id.responchildID);
        GvCID= (EditText)findViewById(R.id.GvCID);
        GvCID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        GvCID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        lv2 = (LinearLayout)findViewById(R.id.lv2);
        lv2.setVisibility(View.GONE);
        llv = (LinearLayout)findViewById(R.id.llv);
        llv.setVisibility(View.GONE);
        lvroutId = (LinearLayout)findViewById(R.id.lvroutId);
        lvroutId.setVisibility(View.GONE);
        lveccacfoundId = (LinearLayout)findViewById(R.id.lveccacfoundId);
        lveccacfoundId.setVisibility(View.GONE);
        awcecceId =  (LinearLayout)findViewById(R.id.awcecceId);
        awcecceId.setVisibility(View.GONE);
        Nsishuid1 = (LinearLayout)findViewById(R.id.Nsishuid1);
        Nsishuid1.setVisibility(View.GONE);
        LVinuseId = (LinearLayout)findViewById(R.id.LVinuseId);
        LVinuseId.setVisibility(View.GONE);
        NLVinuseId = (LinearLayout)findViewById(R.id.NLVinuseId);
        NLVinuseId.setVisibility(View.GONE);
        chatId = (CheckBox)findViewById(R.id.chatId);
        labelId = (CheckBox)findViewById(R.id.labelId);
        rulesId = (CheckBox)findViewById(R.id.rulesId);
        routId = (CheckBox)findViewById(R.id.routId);
        calId = (CheckBox)findViewById(R.id.calId);
        childnameId = (CheckBox)findViewById(R.id.childnameId);
        childrenfolderId = (CheckBox)findViewById(R.id.childrenfolderId);
        displaychId = (CheckBox)findViewById(R.id.displaychId);
        thembasewordwallId = (CheckBox)findViewById(R.id.thembasewordwallId);
        routingId = (Spinner)findViewById(R.id.routingId);
        eccAfSp =(Spinner)findViewById(R.id.eccAfSp);
        FdateId = (TextView)findViewById(R.id.FdateId);
        TdateId = (TextView)findViewById(R.id.TdateId);
        searchID = (ImageView)findViewById(R.id.searchID);
        searchID.setOnClickListener(this);
        searchIDD = (ImageView)findViewById(R.id.searchIDD);
        NGvCID = (EditText)findViewById(R.id.NGvCID);
        NGvCID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        NGvCID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        NsoundenrolledchildrenId =(ImageButton)findViewById(R.id.NsoundenrolledchildrenId);
        NsoundchildfoundId = (ImageButton)findViewById(R.id.NsoundchildfoundId);
        Ncommandspk = (ImageButton)findViewById(R.id.Ncommandspk);
        spchildenrolledId = (ImageButton)findViewById(R.id.spchildenrolledId);
        childfounspId = (ImageButton)findViewById(R.id.childfounspId);
        searchIDD.setOnClickListener(this);
        childenrolledId = (EditText)findViewById(R.id.childenrolledId);
        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        childenrolledId.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        childenrolledId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT){
                    childenrolled = childenrolledId.getText().toString();
                    //Log.e("childenrolled", childenrolled);
                }
                return false;
            }
        });
        childfounId = (EditText)findViewById(R.id.childfounId);
        childfounId.setImeOptions(EditorInfo.IME_ACTION_DONE);
        childfounId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    int childenrollvalue = childenrolledId.getText().length();
                    int phycallyfountoday = childfounId.getText().length();
                    if (childenrollvalue>0) {
                        if (phycallyfountoday>0) {
                            childfoundtodayy = childfounId.getText().toString();
                            childenrolledd = Integer.parseInt(childenrolled);
                            childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                            //Log.e("childenrolled", childfoundtodayy);
                            if (childenrolledd >= childfoundtodayyy) {
                                childfounId.setText(childfounId.getText().toString());
                                Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
//                                String title = "Message Box";
//                                String msg = "VALID DATA";
//                                createDialog(title,msg);
                            } else {
                                childfounId.setText("");
                                Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "NO VALID DATA";
                                createDialog(title,msg);
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "ENTER NO. OF CHILDREN PHYSICALLY FOUND TODAY", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "ENTER NO. OF CHILDREN PHYSICALLY FOUND TODAY";
                            createDialog(title,msg);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "ENTER NO OF CHILDREN ENROLLED", Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "NO VALID DATA";
                        createDialog(title,msg);
                    }
                }
                return false;
            }
        });
        NenrolledchildrenId =(EditText)findViewById(R.id.NenrolledchildrenId);
        NchildfoundId = (EditText)findViewById(R.id.NchildfoundId);
        NenrolledchildrenId.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        NenrolledchildrenId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT){
                    childenrolled = NenrolledchildrenId.getText().toString();
                    //Log.e("childenrolled", childenrolled);
                }
                return false;
            }
        });
        NchildfoundId.setImeOptions(EditorInfo.IME_ACTION_DONE);
        NchildfoundId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    int childfoundtodyvalue = NenrolledchildrenId.getText().length();
                    int phycallvalue = NchildfoundId.getText().length();
                    if (childfoundtodyvalue>0) {
                        if (phycallvalue>0) {
                            childfoundtodayy = NchildfoundId.getText().toString();
                            childenrolledd = Integer.parseInt(childenrolled);
                            childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                            //Log.e("childenrolled", childfoundtodayy);
                            if (childenrolledd >= childfoundtodayyy) {
                                NchildfoundId.setText(NchildfoundId.getText().toString());
                                Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
//                                String title = "Message Box";
//                                String msg = "VALID DATA";
//                                createDialog(title,msg);
                            } else {
                                NchildfoundId.setText("");
                                Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "NO VALID DATA";
                                createDialog(title,msg);
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "ENTER NO. OF CHILDREN PHYSICALLY FOUND TODAY", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "ENTER NO. OF CHILDREN PHYSICALLY FOUND TODAY";
                            createDialog(title,msg);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "ENTER NO OF CHILDREN ENROLLED", Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "ENTER NO OF CHILDREN ENROLLED";
                        createDialog(title,msg);
                    }

                }
                return false;
            }
        });

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        curdate = format.format(today);
        Log.e("CUR", curdate);
        myCalendar = Calendar.getInstance();
        myCalendarr = Calendar.getInstance();
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
        //loadspiner();
        initToolBar();
        alertdiolog();
        alertdiologblockcorner();
        alertdiologdrawingcorner();
        alertplaycorner();
        shishualoygroup();
        helper = new DatabaseHelper(this);
     //   Cursor cursor = helper.getReadableDatabase().
        //        rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
       // String queryy = "SELECT * FROM insflag WHERE allinspactionid=" + insid;
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
                Idshishualoy = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                shishualoy = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOY));
                Log.e("SHISHUALOY"," "+Idshishualoy+" "+shishualoy);
            }
            while (cursor.moveToNext());
        }
        if (shishualoy.equals("0")){

        }
        else {
            editFood();
        }

        broadcastReceivereshishualoy = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceivereshishualoy, new IntentFilter(DATA_SAVED_BROADCAST_SHISHUALOY));
        shishuAloyNetwokchecker = new ShishuAloyNetwokchecker();
        registerReceiver(shishuAloyNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        if (isNetworkAvailable()){
            loadspiner();
        }
        else {
           // String query = "SELECT * FROM " + TABLE_SHISHUALOYNAME + " where " + TABLE_SHISHUALOYINSID + "=" + insid + " and " + TABLE_SHISHUALOYSTATUS + "=" + SHISHUALOYSTATUS;
            String query = "SELECT * FROM shishualoy WHERE hishualoyinsid=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cc = db.rawQuery(query, null);
            if (cc.moveToFirst()) {
                do {
                    String shishualoyid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYID));
                    String hishualoyinsid = cc.getString(cc.getColumnIndex(TABLE_SHISHUALOYINSID));
                    sishualoyDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SISHUALOY));
                    cornercgnitvDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CORNERCGNITV));
                    bookcornerDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_BOOKCORNER));
                    drawcornrDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DRAWCORNR));
                    playcornersDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PLAYCORNERS));
                    eccrunDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ECCRUN));
                    valuesutingDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_VALUESUTING));
                    eccactvtytypDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ECCACTIVITYTYPE));
                    awcvaluestringDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_AWCVALUESSTRING));
                    tlamDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_TLAM));
                    proprecckitDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PROPRECCKIT));
                    avaleindvsualchildactvtyrcdDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_AVALEINDVSUALCHILDACTIVEYRCD));
                    indvsualchildactvtyrcdDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_INDVSUALCHILDACTVTYRCD));
                    awcdecortnfrecceDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_AWCDECORTNFRECCE));
                    fitorecccheckDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FITORECCCHECK));
                    childenrolledDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CHILDENROLLED));
                    childfoundtodayyDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CHILDFOUNDTODAYY));
                    ecceprocessDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ECCEPROCESS));
                    assesmentcardDB  = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ASSESMENTCARD));
                    assesmentcarduseDB  = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ASSESMENTCARDUSE));
                    ecckitdateDB  = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ECCKITDATE));
                    eccobserdateDB  = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ECCOBSERDATE));
                    pseactvfoundDB  = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PSEACTFOUND));
                    pseactivitynmDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PSEACTIVITYNM));
                    chldparticipatpseDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CHAILDPARTICIPATPSE));
                    wheterawwritingDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_AWWFOLLOWINGTHEROUTING));
                    shishualoyinspectrcmntDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYINSPECTRCMNT));
                    String shishualoycurtime = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYCURTIME));
                    String shishualoystatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYSTATUS));
                    Log.e("SHISHUALOYSYNC", " " + shishualoyid + " " + hishualoyinsid + " "
                            + sishualoyDB + " "
                            + cornercgnitvDB + " "
                            + bookcornerDB + " "
                            + drawcornrDB + " "
                            + playcornersDB + " "
                            + eccrunDB + " "
                            + valuesutingDB + " "
                            +eccactvtytypDB +" "
                            + awcvaluestringDB + " "
                            + tlamDB + " "
                            + proprecckitDB+" "
                            +avaleindvsualchildactvtyrcdDB+" "
                            +indvsualchildactvtyrcdDB+" "
                            +awcdecortnfrecceDB+" "
                            +fitorecccheckDB
                            +childenrolledDB+ " "
                             +childfoundtodayyDB+ " "
                             +ecceprocessDB+" "+
                              assesmentcardDB+" "+
                            assesmentcarduseDB+" "+
                            ecckitdateDB+" "+
                            eccobserdateDB+" "+
                            pseactvfoundDB+" "+
                            pseactivitynmDB+" "+
                            chldparticipatpseDB+" "+
                                    wheterawwritingDB+" "+
                            shishualoyinspectrcmntDB+" "+
                            shishualoycurtime+" "+shishualoystatus+" "
                            );
                }
                while (cc.moveToNext());
            }
            Show();
            offlinespinner();
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
                                savedate();
                                break;
                            default:
                        }
                        return true;
                    }
                });

        ecceawcfoundspak = (ImageButton)findViewById(R.id.ecceawcfoundspak);
        ecceawcfoundspak.setOnClickListener(this);
        psenmspak = (ImageButton)findViewById(R.id.psenmspak);
        psenmspak.setOnClickListener(this);
        ncpotIDspak = (ImageButton)findViewById(R.id.ncpotIDspak);
        ncpotIDspak.setOnClickListener(this);
        cmdspak = (ImageButton)findViewById(R.id.cmdspak);
        cmdspak.setOnClickListener(this);
        commandspk = (ImageButton)findViewById(R.id.commandspk);
        commandspk.setOnClickListener(this);

    }
    public void loadspiner(){
        if (eccroutntheme.equals("0")){
            list1 = new String[] {"Select Routine Theme","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
        else if (eccroutntheme.equals("1")){
            list1 = new String[] {"Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};

            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
        else if (eccroutntheme.equals("2")){
            list1 = new String[] {"Birds and Animals","Me and My Family","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
       else if (eccroutntheme.equals("3")){
            list1 = new String[] {"Trees and Plants","Me and My Family","Birds and Animals","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
       else if (eccroutntheme.equals("4")){
            list1 = new String[] {"Transport","Me and My Family","Birds and Animals","Trees and Plants","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
       else if (eccroutntheme.equals("5")){
            list1 = new String[] {"Natural Enviroment","Me and My Family","Birds and Animals","Trees and Plants","Transport","My Enviroment","Me and my Surroundings","Festival","Others"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
       else if (eccroutntheme.equals("6")){
            list1 = new String[] {"My Enviroment","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","Me and my Surroundings","Festival","Others"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
        else if (eccroutntheme.equals("7")){
            list1 = new String[] {"Me and my Surroundings","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Festival","Others"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
        else if (eccroutntheme.equals("8")){
            list1 = new String[] {"Festival","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Others"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
        else if (eccroutntheme.equals("9")){
            list1 = new String[] {"Others","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival"};
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list1);
            routingId.setAdapter(adapter1);
        }
        routingId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                routing = routingId.getSelectedItem().toString();
                routing  = routingId.getItemAtPosition(routingId.getSelectedItemPosition()).toString();
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.parseColor("#21aad8"));
                Log.e("ROUTING",routing+"");
                if (routing =="Select Routine Theme"){
                    valuesuting = "0";
                }
                if (routing=="Me and My Family"){
                    valuesuting = "1";
                }
                if (routing=="Birds and Animals"){
                    valuesuting = "2";
                }
                if (routing=="Trees and Plants"){
                    valuesuting = "3";
                }
                if (routing=="Transport"){
                    valuesuting = "4";
                }
                if (routing=="Natural Enviroment"){
                    valuesuting = "5";
                }
                if (routing=="My Enviroment"){
                    valuesuting = "6";
                }
                if (routing=="Me and my Surroundings"){
                    valuesuting = "7";
                }
                if (routing=="Festival"){
                    valuesuting = "8";
                }
                if (routing=="Others"){
                    valuesuting = "9";
                }
                //Toast.makeText(getApplicationContext(),routing+" "+" "+valuesuting,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        ////////////////////////////////////////////////////////////////////////////////
        if (eccactivtyfound.equals("0")){
            list = new String[] {"Select ECCE Activity","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            eccAfSp.setAdapter(adapter);
        }
        else if (eccactivtyfound.equals("1")){
            list = new String[] {"Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            eccAfSp.setAdapter(adapter);
        }
        else if (eccactivtyfound.equals("2")){
            list = new String[] {"Guided Activity and Free Play","Morning circle Time","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            eccAfSp.setAdapter(adapter);
        }
        else if (eccactivtyfound.equals("3")){
            list = new String[] {"Outdoor Indoor Activity","Morning circle Time","Guided Activity and Free Play","Goodbye circle time","School Readiness","Others"};
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            eccAfSp.setAdapter(adapter);
        }
        else if (eccactivtyfound.equals("4")){
            list = new String[] {"Goodbye circle time","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","School Readiness","Others"};
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            eccAfSp.setAdapter(adapter);
        }
        else if (eccactivtyfound.equals("5")){
            list = new String[] {"School Readiness","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","Others"};
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            eccAfSp.setAdapter(adapter);
        }
        else if (eccactivtyfound.equals("6")){
            list = new String[] {"Others","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness"};
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            eccAfSp.setAdapter(adapter);
        }
        eccAfSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                awcfound = eccAfSp.getSelectedItem().toString();
                awcfound  = eccAfSp.getItemAtPosition(eccAfSp.getSelectedItemPosition()).toString();
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.parseColor("#21aad8"));
                Log.e("AWCFOUND",awcfound+"");
                if (awcfound =="Select ECCE Activity"){
                    awcvaluestring = "0";
                }
                if (awcfound=="Morning circle Time"){
                    awcvaluestring = "1";
                }
                if (awcfound=="Guided Activity and Free Play"){
                    awcvaluestring = "2";
                }
                if (awcfound=="Outdoor Indoor Activity"){
                    awcvaluestring = "3";
                }
                if (awcfound=="Goodbye circle time"){
                    awcvaluestring = "4";
                }
                if (awcfound=="School Readiness"){
                    awcvaluestring = "5";
                }
                if (awcfound=="Others"){
                    awcvaluestring = "6";
                }

               // Toast.makeText(getApplicationContext(),awcfound+" "+" "+awcvaluestring,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void Fromdate(final String curdate){
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
            Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "NO VALID DATA";
            createDialog(title,msg);

        }
        /////////////////////////////////////////////

    }
    private void Todate(final String curdate) {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdff = new SimpleDateFormat(myFormat, Locale.US);
        TdateId.setText(sdff.format(myCalendarr.getTime()));
        eccobserdate = TdateId.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(TdateId.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (curdate.equals(TdateId.getText().toString()) || new Date().after(strDate)) {
            TdateId.setText(sdff.format(myCalendarr.getTime()));
            eccobserdate = TdateId.getText().toString();
        } else {
            TdateId.setText("Date");
            eccobserdate = TdateId.getText().toString();
            Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "NO VALID DATA";
            createDialog(title,msg);

        }
        /////////////////////////////////////////////

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
    public void Show(){
            if (sishualoyDB.equals("y")){
                YsishualoyID.setChecked(true);
                FdateId.setText("Date");
                TdateId.setText("Date");
            }
            else if (sishualoyDB.equals("n")){
                NsishualoyID.setChecked(true);
            }
            else {
                YsishualoyID.setChecked(false);
                NsishualoyID.setChecked(false);
            }

            if (cornercgnitvDB.equals("good")){
                bccGoodID.setChecked(true);
            }
            else if (cornercgnitvDB.equals("average")){
                bccAverage.setChecked(true);
            }
            else if (cornercgnitvDB.equals("poor")){
                bccPoor.setChecked(true);
            }
            else if (cornercgnitvDB.equals("na")){
                bccNA.setChecked(true);
            }
            else {
                bccGoodID.setChecked(false);
                bccAverage.setChecked(false);
                bccPoor.setChecked(false);
                bccNA.setChecked(false);
            }
            if (bookcornerDB.equals("good")){
                bcgoodID.setChecked(true);
            }
            else if (bookcornerDB.equals("average")){
                bcaverageID.setChecked(true);
            }
            else if (bookcornerDB.equals("poor")){
                bcpoorID.setChecked(true);
            }
            else if (bookcornerDB.equals("na")){
                bcnaID.setChecked(true);
            }
            else {
                bcgoodID.setChecked(false);
                bcaverageID.setChecked(false);
                bcpoorID.setChecked(false);
                bcnaID.setChecked(false);
            }

            if (drawcornrDB.equals("good")){
                dcgoodID.setChecked(true);
            }
            else if (drawcornrDB.equals("average")){
                dcaverageID.setChecked(true);
            }
            else if (drawcornrDB.equals("poor")){
                dcPoorID.setChecked(true);
            }
            else if (drawcornrDB.equals("na")){
                dcnaID.setChecked(true);
            }
            else {
                dcgoodID.setChecked(false);
                dcaverageID.setChecked(false);
                dcPoorID.setChecked(false);
                dcnaID.setChecked(false);
            }

            if (playcornersDB.equals("good")){
                pcgoodID.setChecked(true);
            }
            else if (playcornersDB.equals("average")){
                pcaverageID.setChecked(true);
            }
            else if (playcornersDB.equals("poor")){
                pcpoorID.setChecked(true);
            }
            else if (playcornersDB.equals("na")){
                pcnaID.setChecked(true);
            }
            else {
                pcgoodID.setChecked(false);
                pcaverageID.setChecked(false);
                pcpoorID.setChecked(false);
                pcnaID.setChecked(false);
            }

            if (eccrunDB.equals("y")){
                YwerunID.setChecked(true);
            }
            else if (eccrunDB.equals("n")){
                NwerunID.setChecked(true);
            }
            else {
                YwerunID.setChecked(false);
                NwerunID.setChecked(false);
            }
///////////////////////////////////////////////////////
            if (eccactvtytypDB.equals("y")){
                Yawcfound.setChecked(true);
            }
            else if (eccactvtytypDB.equals("n")){
                Nawcfound.setChecked(true);
            }
            else {
                Yawcfound.setChecked(false);
                Nawcfound.setChecked(false);
            }

            if (tlamDB.equals("y")){
                tlmYId.setChecked(true);
            }
            else if (tlamDB.equals("n")){
                tlmNId.setChecked(true);
            }
            else {
                tlmYId.setChecked(false);
                tlmNId.setChecked(false);
            }


            ecceawcfoundID.setText(eccactvtytyprep);
            if (proprecckitDB.equals("p")){
                properlyID.setChecked(true);
            }
            else if (proprecckitDB.equals("np")){
                naID.setChecked(true);
            }
            else {
                properlyID.setChecked(false);
                naID.setChecked(false);
            }

            if (avaleindvsualchildactvtyrcdDB.equals("y")){
                YartworkId.setChecked(true);
            }
            else if (avaleindvsualchildactvtyrcdDB.equals("n")){
                NartworkId.setChecked(true);
            }
            else {
                YartworkId.setChecked(false);
                NartworkId.setChecked(false);
            }


            if (indvsualchildactvtyrcdDB.equals("y")){
                YciarfID.setChecked(true);
            }
            else if (indvsualchildactvtyrcdDB.equals("n")){
                NciarfID.setChecked(true);
            }
            else {
                YciarfID.setChecked(false);
                NciarfID.setChecked(false);
            }

            if (awcdecortnfrecceDB.equals("y")){
                YawcdecceID.setChecked(true);
            }
            else if (awcdecortnfrecceDB.equals("n")){
                NawcdecceID.setChecked(true);
            }
            else {
                YawcdecceID.setChecked(false);
                NawcdecceID.setChecked(false);
            }

            String[] firstArray = fitorecccheckDB.split(",");
            List<String> list = new ArrayList<String>();
            for(String s : firstArray) {
                if(s != null && s.length() > 0) {
                    list.add(s);
                    Log.e("ROUTINGFLOWE",""+s);
                    // firstArray = list.toArray(new String[list.size()]);
                    //Log.e("ROUTINGFLOWEE",""+firstArray);
                    if (s.equals("1")){
                        chatId.setChecked(true);
                    }
                    else if (s.equals("2")){
                        labelId.setChecked(true);
                    }
                    else if (s.equals("3")){
                        rulesId.setChecked(true);
                    }
                    else if (s.equals("4")){
                        routId.setChecked(true);
                    }
                    else if (s.equals("5")){
                        calId.setChecked(true);
                    }
                    else if (s.equals("6")){
                        childnameId.setChecked(true);
                    }
                    else if (s.equals("7")){
                        childrenfolderId.setChecked(true);
                    }
                    else if (s.equals("8")){
                        displaychId.setChecked(true);
                    }
                    else if (s.equals("9")){
                        thembasewordwallId.setChecked(true);
                    }
                    else {

                    }
                }
            }
            if (wheterawwritingDB.equals("y")){
                YawwroutingId.setChecked(true);
            }
            else if (wheterawwritingDB.equals("n")){
                NawwroutingId.setChecked(true);
            }
            else {
                YawwroutingId.setChecked(false);
                NawwroutingId.setChecked(false);
            }
            childenrolledId.setText(childenrolledDB);
            childfounId.setText(childfoundtodayyDB);
            if (ecceprocessDB.equals("y")){
                YchildrenceeeprocessId.setChecked(true);
            }
            else if (ecceprocessDB.equals("n")){
                NchildrenceeeprocessId.setChecked(true);
            }
            else {
                YchildrenceeeprocessId.setChecked(false);
                NchildrenceeeprocessId.setChecked(false);
            }

            if (assesmentcardDB.equals("y")){
                YassesmentcardId.setChecked(true);
            }
            else if (assesmentcardDB.equals("n")){
                NassesmentcardId.setChecked(true);
            }
            else {
                YassesmentcardId.setChecked(false);
                NassesmentcardId.setChecked(false);
            }
            if (assesmentcarduseDB.equals("iu")){
                InuseId.setChecked(true);
            }
            else if (assesmentcarduseDB.equals("niu")){
                NotinuseId.setChecked(true);
            }
            else {
                InuseId.setChecked(false);
                NotinuseId.setChecked(false);
            }
            FdateId.setText(ecckitdateDB);
            TdateId.setText(eccobserdateDB);
            GvCID.setText(shishualoyinspectrcmntDB);




    }
    public void offlinespinner(){
        if (valuesutingDB.equals("0")){
                list1 = new String[] {"Select Routine Theme","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("1")){
                list1 = new String[] {"Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};

                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("2")){
                list1 = new String[] {"Birds and Animals","Me and My Family","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("3")){
                list1 = new String[] {"Trees and Plants","Me and My Family","Birds and Animals","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("4")){
                list1 = new String[] {"Transport","Me and My Family","Birds and Animals","Trees and Plants","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("5")){
                list1 = new String[] {"Natural Enviroment","Me and My Family","Birds and Animals","Trees and Plants","Transport","My Enviroment","Me and my Surroundings","Festival","Others"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("6")){
                list1 = new String[] {"My Enviroment","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","Me and my Surroundings","Festival","Others"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("7")){
                list1 = new String[] {"Me and my Surroundings","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Festival","Others"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("8")){
                list1 = new String[] {"Festival","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Others"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            else if (valuesutingDB.equals("9")){
                list1 = new String[] {"Others","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival"};
                adapter1 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list1);
                routingId.setAdapter(adapter1);
            }
            routingId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    routing = routingId.getSelectedItem().toString();
                    routing  = routingId.getItemAtPosition(routingId.getSelectedItemPosition()).toString();
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.parseColor("#21aad8"));
                    Log.e("ROUTING",routing+"");
                    if (routing =="Select Routine Theme"){
                        valuesuting = "0";
                    }
                    if (routing=="Me and My Family"){
                        valuesuting = "1";
                    }
                    if (routing=="Birds and Animals"){
                        valuesuting = "2";
                    }
                    if (routing=="Trees and Plants"){
                        valuesuting = "3";
                    }
                    if (routing=="Transport"){
                        valuesuting = "4";
                    }
                    if (routing=="Natural Enviroment"){
                        valuesuting = "5";
                    }
                    if (routing=="My Enviroment"){
                        valuesuting = "6";
                    }
                    if (routing=="Me and my Surroundings"){
                        valuesuting = "7";
                    }
                    if (routing=="Festival"){
                        valuesuting = "8";
                    }
                    if (routing=="Others"){
                        valuesuting = "9";
                    }
                   // Toast.makeText(getApplicationContext(),routing+" "+" "+valuesuting,Toast.LENGTH_LONG).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
            if (awcvaluestringDB.equals("0")){
                list = new String[] {"Select ECCE Activity","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list);
                eccAfSp.setAdapter(adapter);
            }
            else if (awcvaluestringDB.equals("1")){
                list = new String[] {"Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list);
                eccAfSp.setAdapter(adapter);
            }
            else if (awcvaluestringDB.equals("2")){
                list = new String[] {"Guided Activity and Free Play","Morning circle Time","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list);
                eccAfSp.setAdapter(adapter);
            }
            else if (awcvaluestringDB.equals("3")){
                list = new String[] {"Outdoor Indoor Activity","Morning circle Time","Guided Activity and Free Play","Goodbye circle time","School Readiness","Others"};
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list);
                eccAfSp.setAdapter(adapter);
            }
            else if (awcvaluestringDB.equals("4")){
                list = new String[] {"Goodbye circle time","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","School Readiness","Others"};
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list);
                eccAfSp.setAdapter(adapter);
            }
            else if (awcvaluestringDB.equals("5")){
                list = new String[] {"School Readiness","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","Others"};
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list);
                eccAfSp.setAdapter(adapter);
            }
            else if (awcvaluestringDB.equals("6")){
                list = new String[] {"Others","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness"};
                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, list);
                eccAfSp.setAdapter(adapter);
            }
            eccAfSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                    awcfound = eccAfSp.getSelectedItem().toString();
                    awcfound  = eccAfSp.getItemAtPosition(eccAfSp.getSelectedItemPosition()).toString();
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.parseColor("#21aad8"));
                    Log.e("AWCFOUND",awcfound+"");
                    if (awcfound =="Select ECCE Activity"){
                        awcvaluestring = "0";
                    }
                    if (awcfound=="Morning circle Time"){
                        awcvaluestring = "1";
                    }
                    if (awcfound=="Guided Activity and Free Play"){
                        awcvaluestring = "2";
                    }
                    if (awcfound=="Outdoor Indoor Activity"){
                        awcvaluestring = "3";
                    }
                    if (awcfound=="Goodbye circle time"){
                        awcvaluestring = "4";
                    }
                    if (awcfound=="School Readiness"){
                        awcvaluestring = "5";
                    }
                    if (awcfound=="Others"){
                        awcvaluestring = "6";
                    }

                   // Toast.makeText(getApplicationContext(),awcfound+" "+" "+awcvaluestring,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
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
    private void shishualoygroup(){
        sishualoyID = (RadioGroup)findViewById(R.id.sishualoyID);
        GbccID = (RadioGroup)findViewById(R.id.GbccID);
        GbcID= (RadioGroup)findViewById(R.id.GbcID);
        GdcID= (RadioGroup)findViewById(R.id.GdcID);
        GpcID = (RadioGroup)findViewById(R.id.GpcID);
        GwerunID =(RadioGroup)findViewById(R.id.GwerunID);
        GwwuekpID = (RadioGroup)findViewById(R.id.GwwuekpID);
        GciarfID = (RadioGroup)findViewById(R.id.GciarfID);
        awcdecceID = (RadioGroup)findViewById(R.id.awcdecceID);
        GPYawcId = (RadioGroup)findViewById(R.id.GPYawcId);
        GpawctlmId  = (RadioGroup)findViewById(R.id.GpawctlmId);
        GpchildrenartworkId  = (RadioGroup)findViewById(R.id.GpchildrenartworkId);
        GppseId = (RadioGroup)findViewById(R.id.GppseId);
        GPawwroutingId = (RadioGroup)findViewById(R.id.GPawwroutingId);
        GpassesmentcardId = (RadioGroup)findViewById(R.id.GpassesmentcardId);
        GpinusenotinuseId = (RadioGroup)findViewById(R.id.GpinusenotinuseId);
        NGPparticipentpscId = (RadioGroup)findViewById(R.id.NGPparticipentpscId);
        NGPifassenmentId = (RadioGroup)findViewById(R.id.NGPifassenmentId);
        NGpinusenotinuseId = (RadioGroup)findViewById(R.id.NGpinusenotinuseId);
        GpchildrenceeeprocessId = (RadioGroup)findViewById(R.id.GpchildrenceeeprocessId);
        YchildrenceeeprocessId  = (RadioButton)findViewById(R.id.YchildrenceeeprocessId);
        NchildrenceeeprocessId = (RadioButton)findViewById(R.id.NchildrenceeeprocessId);
        YsishualoyID = (RadioButton)findViewById(R.id.YsishualoyID);
        NsishualoyID = (RadioButton)findViewById(R.id.NsishualoyID);
        bccGoodID = (RadioButton)findViewById(R.id.bccGoodID);
        bccAverage = (RadioButton)findViewById(R.id.bccAverage);
        bccPoor= (RadioButton)findViewById(R.id.bccPoor);
        bccNA = (RadioButton)findViewById(R.id.bccNA);
        bcgoodID = (RadioButton)findViewById(R.id.bcgoodID);
        bcaverageID = (RadioButton)findViewById(R.id.bcaverageID);
        bcpoorID = (RadioButton)findViewById(R.id.bcpoorID);
        bcnaID = (RadioButton)findViewById(R.id.bcnaID);
        dcgoodID = (RadioButton)findViewById(R.id.dcgoodID);
        dcaverageID = (RadioButton)findViewById(R.id.dcaverageID);
        dcPoorID = (RadioButton)findViewById(R.id.dcPoorID);
        dcnaID = (RadioButton)findViewById(R.id.dcnaID);
        pcgoodID = (RadioButton)findViewById(R.id.pcgoodID);
        pcaverageID = (RadioButton)findViewById(R.id.pcaverageID);
        pcpoorID = (RadioButton)findViewById(R.id.pcpoorID);
        pcnaID = (RadioButton)findViewById(R.id.pcnaID);
        YwerunID = (RadioButton)findViewById(R.id.YwerunID);
        NwerunID = (RadioButton)findViewById(R.id.NwerunID);
        properlyID = (RadioButton)findViewById(R.id.properlyID);
        naID = (RadioButton)findViewById(R.id.naID);
        YciarfID = (RadioButton)findViewById(R.id.YciarfID);
        NciarfID = (RadioButton)findViewById(R.id.NciarfID);
        YawcdecceID = (RadioButton)findViewById(R.id.YawcdecceID);
        NawcdecceID = (RadioButton)findViewById(R.id.NawcdecceID);
        Yawcfound  = (RadioButton)findViewById(R.id.Yawcfound);
        Nawcfound = (RadioButton)findViewById(R.id.Nawcfound);
        tlmYId = (RadioButton)findViewById(R.id.tlmYId);
        tlmNId = (RadioButton)findViewById(R.id.tlmNId);
        YartworkId = (RadioButton)findViewById(R.id.YartworkId);
        NartworkId = (RadioButton)findViewById(R.id.NartworkId);
        YpseId = (RadioButton)findViewById(R.id.YpseId);
        NpseId = (RadioButton)findViewById(R.id.NpseId);
        YawwroutingId = (RadioButton)findViewById(R.id.YawwroutingId);
        NawwroutingId = (RadioButton)findViewById(R.id.NawwroutingId);
        YassesmentcardId = (RadioButton)findViewById(R.id.YassesmentcardId);
        NassesmentcardId = (RadioButton)findViewById(R.id.NassesmentcardId);
        InuseId  = (RadioButton)findViewById(R.id.InuseId);
        NotinuseId  = (RadioButton)findViewById(R.id.NotinuseId);
        NNYparticipentpscId  = (RadioButton)findViewById(R.id.NNYparticipentpscId);
        NNNparticipentpscId  = (RadioButton)findViewById(R.id.NNNparticipentpscId);
        NYifassenmentId  = (RadioButton)findViewById(R.id.NYifassenmentId);
        NNifassenmentId = (RadioButton)findViewById(R.id.NNifassenmentId);
        NInuseId  = (RadioButton)findViewById(R.id.NInuseId);
        NNotinuseId  = (RadioButton)findViewById(R.id.NNotinuseId);
        sishualoyID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YsishualoyID){
                    sishualoy = "y";
                    Log.e("sishualoy",sishualoy);
                    lv2.setVisibility(View.VISIBLE);
                    llv.setVisibility(View.GONE);
                    NLVinuseId.setVisibility(View.GONE);
                    FdateId.setText("Date");
                    TdateId.setText("Date");
                    if (isNetworkAvailable()){
                        loadspiner();
                    }
                    {
                        offlinespinner();
                    }
                    if (YpseId.isChecked()){
                        YpseId.setChecked(false);
                        YpseId.setSelected(false);
                    }
                    if (NpseId.isChecked()){
                        NpseId.setChecked(false);
                        NpseId.setSelected(false);
                    }
                    psenmID.setText("");
                    NenrolledchildrenId.setText("");
                    NchildfoundId.setText("");
                    if (NNYparticipentpscId.isChecked()){
                        NNYparticipentpscId.setChecked(false);
                        NNYparticipentpscId.setSelected(false);
                    }
                    if (NNNparticipentpscId.isChecked()){
                        NNNparticipentpscId.setChecked(false);
                        NNNparticipentpscId.setSelected(false);
                    }

                    if (NYifassenmentId.isChecked()){
                        NYifassenmentId.setChecked(false);
                        NYifassenmentId.setSelected(false);
                    }
                    if (NNifassenmentId.isChecked()){
                        NNifassenmentId.setChecked(false);
                        NNifassenmentId.setSelected(false);
                    }
                    if (NInuseId.isChecked()){
                        NInuseId.setChecked(false);
                        NInuseId.setSelected(false);
                    }
                    if (NNotinuseId.isChecked()){
                        NNotinuseId.setChecked(false);
                        NNotinuseId.setSelected(false);
                    }
                    NGvCID.setText("");
                }
                else if (checkedId== R.id.NsishualoyID){
                    sishualoy = "n";
                    Log.e("sishualoy",sishualoy);
                    lvroutId.setVisibility(View.GONE);
                    lveccacfoundId.setVisibility(View.GONE);
                    awcecceId.setVisibility(View.GONE);
                    LVinuseId.setVisibility(View.GONE);
                    lv2.setVisibility(View.GONE);
                    llv.setVisibility(View.VISIBLE);
                    if (bccGoodID.isChecked()){
                        bccGoodID.setChecked(false);
                        bccGoodID.setSelected(false);
                    }
                    if (bccAverage.isChecked()){
                        bccAverage.setChecked(false);
                        bccAverage.setSelected(false);
                    }
                    if (bccPoor.isChecked()){
                        bccPoor.setChecked(false);
                        bccPoor.setSelected(false);
                    }
                    if (bccNA.isChecked()){
                        bccNA.setChecked(false);
                        bccNA.setSelected(false);
                    }

                    if (bcgoodID.isChecked()){
                        bcgoodID.setChecked(false);
                        bcgoodID.setSelected(false);
                    }
                    if (bcaverageID.isChecked()){
                        bcaverageID.setChecked(false);
                        bcaverageID.setSelected(false);
                    }

                    if (bcpoorID.isChecked()){
                        bcpoorID.setChecked(false);
                        bcpoorID.setSelected(false);
                    }
                    if (bcnaID.isChecked()){
                        bcnaID.setChecked(false);
                        bcnaID.setSelected(false);
                    }
                    if (dcgoodID.isChecked()){
                        dcgoodID.setChecked(false);
                        dcgoodID.setSelected(false);
                    }
                    if (dcaverageID.isChecked()){
                        dcaverageID.setChecked(false);
                        dcaverageID.setSelected(false);
                    }
                    if (dcPoorID.isChecked()){
                        dcPoorID.setChecked(false);
                        dcPoorID.setSelected(false);
                    }
                    if (dcnaID.isChecked()){
                        dcnaID.setChecked(false);
                        dcnaID.setSelected(false);
                    }
                    if (pcgoodID.isChecked()){
                        pcgoodID.setChecked(false);
                        pcgoodID.setSelected(false);
                    }

                    if (pcaverageID.isChecked()){
                        pcaverageID.setChecked(false);
                        pcaverageID.setSelected(false);
                    }

                    if (pcpoorID.isChecked()){
                        pcpoorID.setChecked(false);
                        pcpoorID.setSelected(false);
                    }

                    if (pcnaID.isChecked()){
                        pcnaID.setChecked(false);
                        pcnaID.setSelected(false);
                    }

                    if (YwerunID.isChecked()){
                        YwerunID.setChecked(false);
                        YwerunID.setSelected(false);
                    }

                    if (NwerunID.isChecked()){
                        NwerunID.setChecked(false);
                        NwerunID.setSelected(false);
                    }
                    routingId.setAdapter(null);
                    eccAfSp.setAdapter(null);
                    if (Yawcfound.isChecked()){
                        Yawcfound.setChecked(false);
                        Yawcfound.setSelected(false);
                    }
                    if (Nawcfound.isChecked()){
                        Nawcfound.setChecked(false);
                        Nawcfound.setSelected(false);
                    }
                    if (tlmYId.isChecked()){
                        tlmYId.setChecked(false);
                        tlmYId.setSelected(false);
                    }
                    if (tlmNId.isChecked()){
                        tlmNId.setChecked(false);
                        tlmNId.setSelected(false);
                    }

                    if (YartworkId.isChecked()){
                        YartworkId.setChecked(false);
                        YartworkId.setSelected(false);
                    }
                    if (NartworkId.isChecked()){
                        NartworkId.setChecked(false);
                        NartworkId.setSelected(false);
                    }

                    if (YciarfID.isChecked()){
                        YciarfID.setChecked(false);
                        YciarfID.setSelected(false);
                    }

                    if (NciarfID.isChecked()){
                        NciarfID.setChecked(false);
                        NciarfID.setSelected(false);
                    }

                    if (YawcdecceID.isChecked()){
                        YawcdecceID.setChecked(false);
                        YawcdecceID.setSelected(false);
                    }

                    if (NawcdecceID.isChecked()){
                        NawcdecceID.setChecked(false);
                        NawcdecceID.setSelected(false);
                    }

                    if (chatId.isChecked()){
                        chatId.setChecked(false);
                        chatId.setSelected(false);
                    }
                    if (labelId.isChecked()){
                        labelId.setChecked(false);
                        labelId.setSelected(false);
                    }
                    if (rulesId.isChecked()){
                        rulesId.setChecked(false);
                        rulesId.setSelected(false);
                    }
                    if (routId.isChecked()){
                        routId.setChecked(false);
                        routId.setSelected(false);
                    }
                    if (calId.isChecked()){
                        calId.setChecked(false);
                        calId.setSelected(false);
                    }
                    if (childnameId.isChecked()){
                        childnameId.setChecked(false);
                        childnameId.setSelected(false);
                    }
                    if (childrenfolderId.isChecked()){
                        childrenfolderId.setChecked(false);
                        childrenfolderId.setSelected(false);
                    }
                    if (displaychId.isChecked()){
                        displaychId.setChecked(false);
                        displaychId.setSelected(false);
                    }
                    if (thembasewordwallId.isChecked()){
                        thembasewordwallId.setChecked(false);
                        thembasewordwallId.setSelected(false);
                    }
                    if (YawwroutingId.isChecked()){
                        YawwroutingId.setChecked(false);
                        YawwroutingId.setSelected(false);
                    }
                    if (NawwroutingId.isChecked()){
                        NawwroutingId.setChecked(false);
                        NawwroutingId.setSelected(false);
                    }

                    childenrolledId.setText("");
                    childfounId.setText("");

                    if (YchildrenceeeprocessId.isChecked()){
                        YchildrenceeeprocessId.setChecked(false);
                        YchildrenceeeprocessId.setSelected(false);
                    }
                    if (NchildrenceeeprocessId.isChecked()){
                        NchildrenceeeprocessId.setChecked(false);
                        NchildrenceeeprocessId.setSelected(false);
                    }
                    if (YassesmentcardId.isChecked()){
                        YassesmentcardId.setChecked(false);
                        YassesmentcardId.setSelected(false);
                    }

                    if (NassesmentcardId.isChecked()){
                        NassesmentcardId.setChecked(false);
                        NassesmentcardId.setSelected(false);
                    }

                    if (InuseId.isChecked()){
                        InuseId.setChecked(false);
                        InuseId.setSelected(false);
                    }
                    if (NotinuseId.isChecked()){
                        NotinuseId.setChecked(false);
                        NotinuseId.setSelected(false);
                    }

                    if (properlyID.isChecked()){
                        properlyID.setChecked(false);
                        properlyID.setSelected(false);
                    }
                    if (naID.isChecked()){
                        naID.setChecked(false);
                        naID.setSelected(false);
                    }
                    FdateId.setText("Date");
                    TdateId.setText("Date");
                    responchildID.setText("");
                    GvCID.setText("");
                }
            }
        });
        GbccID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
             if (checkedId== R.id.bccGoodID){
                 cornercgnitv = "good";
                 Log.e("cornercgnitv",cornercgnitv);
             }
             else if (checkedId== R.id.bccAverage){
                 cornercgnitv = "average";
                 Log.e("cornercgnitv",cornercgnitv);
             }
             else if (checkedId== R.id.bccPoor){
                 cornercgnitv = "poor";
                 Log.e("cornercgnitv",cornercgnitv);
             }
             else if (checkedId== R.id.bccNA){
                 cornercgnitv = "na";
                 Log.e("cornercgnitv",cornercgnitv);
             }

            }
        });
        GbcID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.bcgoodID){
                    bookcorner = "good";
                    Log.e("bookcorner",bookcorner);
                }
                else if (checkedId== R.id.bcaverageID){
                    bookcorner = "average";
                    Log.e("bookcorner",bookcorner);
                }
                else if (checkedId== R.id.bcpoorID){
                    bookcorner = "poor";
                    Log.e("bookcorner",bookcorner);
                }
                else if (checkedId== R.id.bcnaID){
                    bookcorner = "na";
                    Log.e("bookcorner",bookcorner);
                }
            }
        });
        GdcID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.dcgoodID){
                    drawcornr = "good";
                    Log.e("drawcornr",drawcornr);
                }
                else if (checkedId== R.id.dcaverageID){
                    drawcornr = "average";
                    Log.e("drawcornr",drawcornr);
                }
                else if (checkedId== R.id.dcPoorID){
                    drawcornr = "poor";
                    Log.e("drawcornr",drawcornr);
                }
                else if (checkedId== R.id.dcnaID){
                    drawcornr = "na";
                    Log.e("drawcornr",drawcornr);
                }

            }
        });

        GpcID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.pcgoodID){
                    playcorners = "good";
                    Log.e("playcorners",playcorners);
                }
                else if (checkedId== R.id.pcaverageID){
                    playcorners = "average";
                    Log.e("playcorners",playcorners);
                }
                else if (checkedId== R.id.pcpoorID){
                    playcorners = "poor";
                    Log.e("playcorners",playcorners);
                }
                else if (checkedId== R.id.pcnaID){
                    playcorners = "na";
                    Log.e("playcorners",playcorners);
                }

            }
        });

        GwerunID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YwerunID){
                    eccrun = "y";
                    Log.e("eccrun",eccrun);
                    lvroutId.setVisibility(View.VISIBLE);
//                    list1 = new String[] {"Select Routine Theme","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
//                    adapter1 = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_dropdown_item, list1);
//                    routingId.setAdapter(adapter1);
                }
                else if (checkedId== R.id.NwerunID){
                    eccrun = "n";
                    Log.e("eccrun",eccrun);
                    lvroutId.setVisibility(View.GONE);
                    //routingId.setAdapter(null);
                    //adapter1.notifyDataSetChanged();
                    list1 = new String[] {"Select Routine Theme","Me and My Family","Birds and Animals","Trees and Plants","Transport","Natural Enviroment","My Enviroment","Me and my Surroundings","Festival","Others"};
                    adapter1 = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_dropdown_item, list1);
                    routingId.setAdapter(adapter1);
                }

            }
        });

        GwwuekpID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.properlyID){
                    proprecckit = "p";
                    Log.e("proprecckit",proprecckit);
                }
                else if (checkedId== R.id.naID){
                    proprecckit = "np";
                    Log.e("proprecckit",proprecckit);
                }

            }
        });

        GciarfID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YciarfID){
                    indvsualchildactvtyrcd = "y";
                    Log.e("indvsualchildactvtyrcd",indvsualchildactvtyrcd);
                }
                else if (checkedId== R.id.NciarfID){
                    indvsualchildactvtyrcd = "n";
                    Log.e("indvsualchildactvtyrcd",indvsualchildactvtyrcd);
                }

            }
        });

        awcdecceID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YawcdecceID){
                    awcdecortnfrecce = "y";
                    awcecceId.setVisibility(View.VISIBLE);
                    Log.e("awcdecortnfrecce",awcdecortnfrecce);
                }
                else if (checkedId== R.id.NawcdecceID){
                    awcdecortnfrecce = "n";
                    awcecceId.setVisibility(View.GONE);
                    Log.e("awcdecortnfrecce",awcdecortnfrecce);
                    if (chatId.isChecked()){
                        chatId.setChecked(false);
                        chatId.setSelected(false);
                    }
                    if (labelId.isChecked()){
                        labelId.setChecked(false);
                        labelId.setSelected(false);
                    }
                    if (rulesId.isChecked()){
                        rulesId.setChecked(false);
                        rulesId.setSelected(false);
                    }
                    if (routId.isChecked()){
                        routId.setChecked(false);
                        routId.setSelected(false);
                    }
                    if (calId.isChecked()){
                        calId.setChecked(false);
                        calId.setSelected(false);
                    }
                    if (childnameId.isChecked()){
                        childnameId.setChecked(false);
                        childnameId.setSelected(false);
                    }
                    if (childrenfolderId.isChecked()){
                        childrenfolderId.setChecked(false);
                        childrenfolderId.setSelected(false);
                    }
                    if (displaychId.isChecked()){
                        displaychId.setChecked(false);
                        displaychId.setSelected(false);
                    }
                    if (thembasewordwallId.isChecked()){
                        thembasewordwallId.setChecked(false);
                        thembasewordwallId.setSelected(false);
                    }
                }

            }
        });
        GPYawcId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.Yawcfound){
                    eccactvtytyp = "y";
                    lveccacfoundId.setVisibility(View.VISIBLE);
//                    list = new String[] {"Select ECCE Activity","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
//                    adapter = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_dropdown_item, list);
//                    eccAfSp.setAdapter(adapter);

                }
                else if (checkedId== R.id.Nawcfound){
                    lveccacfoundId.setVisibility(View.GONE);
                    eccactvtytyp = "n";
                 //   eccAfSp.setAdapter(null);

//                    if (isNetworkAvailable()){
//                        loadspiner();
//                    }
//                    else {
//                        offlinespinner();
//                    }
                    list = new String[] {"Select ECCE Activity","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
                    adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_dropdown_item, list);
                    eccAfSp.setAdapter(adapter);

                }

            }
        });
        GpawctlmId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.tlmYId){
                    tlam = "y";
                }
                else if (checkedId== R.id.tlmNId){
                    tlam = "n";
                }

            }
        });
        GpchildrenartworkId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YartworkId){
                    avaleindvsualchildactvtyrcd = "y";
                }
                else if (checkedId== R.id.NartworkId){
                    avaleindvsualchildactvtyrcd = "n";
                }

            }
        });
        GppseId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YpseId){
                    Nsishuid1.setVisibility(View.VISIBLE);
                    pseactvfound = "y";
                }
                else if (checkedId== R.id.NpseId){
                    Nsishuid1.setVisibility(View.GONE);
                    pseactvfound = "n";
                }

            }
        });
        GPawwroutingId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YawwroutingId){
                wheterawwriting = "y";
                }
                else if (checkedId== R.id.NawwroutingId){
                    wheterawwriting = "n";
                }

            }
        });
        GpassesmentcardId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YassesmentcardId){
                    LVinuseId.setVisibility(View.VISIBLE);
                    assesmentcard = "y";
                }
                else if (checkedId== R.id.NassesmentcardId){
                    LVinuseId.setVisibility(View.GONE);
                    assesmentcard = "n";
                    if (InuseId.isChecked()){
                        InuseId.setChecked(false);
                        InuseId.setSelected(false);
                    }
                    if (NotinuseId.isChecked()){
                        NotinuseId.setChecked(false);
                        NotinuseId.setSelected(false);
                    }


                }


            }
        });
        GpinusenotinuseId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.InuseId){
                    assesmentcard_use = "iu";
                    Log.e("ASSMENT",assesmentcard_use);

                }
                else if (checkedId== R.id.NotinuseId){
                    assesmentcard_use = "niu";
                    Log.e("ASSMENT",assesmentcard_use);
                }

            }
        });
        NGPparticipentpscId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.NNYparticipentpscId){
                    chldparticipatpse ="Y";
                    Log.e("Childpart", chldparticipatpse);
                }
                else if (checkedId== R.id.NNNparticipentpscId){
                    chldparticipatpse ="N";
                    Log.e("Childpart", chldparticipatpse);
                }


            }
        });
        NGPifassenmentId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.NYifassenmentId){
                    NLVinuseId.setVisibility(View.VISIBLE);
                    assesmentcard = "y";
                }
                else if (checkedId== R.id.NNifassenmentId){
                    NLVinuseId.setVisibility(View.GONE);
                    assesmentcard = "n";
                    if (NInuseId.isChecked()){
                        NInuseId.setChecked(false);
                        NInuseId.setSelected(false);
                    }
                    if (NNotinuseId.isChecked()){
                        NNotinuseId.setChecked(false);
                        NNotinuseId.setSelected(false);
                    }
                }

            }
        });
        NGpinusenotinuseId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.NInuseId){
                    assesmentcard_use = "iu";

                }
                else if (checkedId== R.id.NNotinuseId){
                    assesmentcard_use = "niu";
                }


            }
        });

        GpchildrenceeeprocessId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YchildrenceeeprocessId){
                   ecceprocess ="y";
                }
                else if (checkedId== R.id.NchildrenceeeprocessId){
                    ecceprocess ="n";
                }


            }
        });

        chatId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chart_according = "1";
                    chart_accordingg = "11";

                }else{
                    chart_according ="0";
                }
            }
        });

        labelId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
               labelling ="2";
                    chart_accordingg = "11";
                }else{
                    labelling ="0";
                }
            }
        });
        rulesId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rules = "3";
                    chart_accordingg = "11";
                }else{
                    rules = "0";
                }
            }
        });
        routId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    routine = "4";
                    chart_accordingg = "11";
                }else{
                    routine = "0";
                }
            }
        });
        calId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    calender= "5";
                    chart_accordingg = "11";
                }else{
                    calender= "0";
                }
            }
        });
        childnameId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    childrenname = "6";
                    chart_accordingg = "11";
                }else{
                    childrenname = "0";
                }
            }
        });

        childrenfolderId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    childrenfolder = "7";
                    chart_accordingg = "11";
                }else{
                    childrenfolder = "0";
                }
            }
        });

        displaychId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    displaywork = "8";
                    chart_accordingg = "11";

                }else{
                    displaywork = "0";
                }
            }
        });

        thembasewordwallId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   themebaseworld ="9";
                    chart_accordingg = "11";

                }else{
                    themebaseworld ="0";
                }
            }
        });
    }
    private void editFood(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SHISHUALOYEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SHISHULAYEDIT"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray sishualoydtl = object.getJSONArray("sishu_aloy_dtl");
                            for (int i = 0; i<sishualoydtl.length(); i++){
                                JSONObject jsonObject = sishualoydtl.getJSONObject(i);
                                Esishualoy = jsonObject.getString("sishu_aloy");
                                if (Esishualoy.equals("y")){
                                    Ecornercgnitv = jsonObject.getString("corner_cgnitv");
                                    Ebookcorner = jsonObject.getString("book_corner");
                                    Edrawcornr = jsonObject.getString("draw_cornr");
                                    Eplaycorner = jsonObject.getString("play_corner");
                                    Eeccrun = jsonObject.getString("ecc_run");
                                    eccroutntheme = jsonObject.getString("ecc_routn_theme");
                                    Eeccactvtytyp =jsonObject.getString("ecc_actvty_typ");
                                    eccactivtyfound = jsonObject.getString("ecc_activty_found");
                                    awwtlm = jsonObject.getString("aww_tlm");
                                    Eproprecckit = jsonObject.getString("propr_ecc_kit");
                                    indvsualchildartwork = jsonObject.getString("indvsual_child_artwork");
                                    Eindvsualchildactvtyrcd = jsonObject.getString("indvsual_child_actvty_rcd");
                                    Eawcdecortnfrecce = jsonObject.getString("awc_decortn_fr_ecce");
                                    awwroutinfollow = jsonObject.getString("aww_routin_follow");
                                    totchldenroll = jsonObject.getString("tot_chld_enroll");
                                    totchldfoundtoday = jsonObject.getString("tot_chld_found_today");
                                    chldparticipatecc = jsonObject.getString("chld_participat_ecc");
                                    assmntcard = jsonObject.getString("assmnt_card");
                                    assmntcardst = jsonObject.getString("assmnt_card_st");
                                    lastecckitrcvdt = jsonObject.getString("last_ecc_kit_rcv_dt");
                                    lasteccdayobserv = jsonObject.getString("last_ecc_day_observ");
                                    awwfollowroutine = jsonObject.getString("aww_follow_routine");
                                    Einspectrcmnt = jsonObject.getString("inspectr_cmnt");
//                                Eeccactvtytyp = jsonObject.getString("ecc_actvty_typ");
//                                Epseactivitynm = jsonObject.getString("pse_activity_nm");
//                                Etotchldprsnt = jsonObject.getString("tot_chld_prsnt");
//                                Echldrspnse = jsonObject.getString("chld_rspnse");
                                    Log.e("ShishAloy"," "+Esishualoy+" "
                                            +Ecornercgnitv+" "
                                            +Ebookcorner+" "
                                            +Edrawcornr+" "
                                            +Eplaycorner+" "
                                            +Eeccrun+" "
                                            +eccroutntheme+" "
                                            +Eeccactvtytyp+" "
                                            +eccactivtyfound+" "
                                            +awwtlm+" "
                                            +indvsualchildartwork+" "
                                            +Eindvsualchildactvtyrcd+" "
                                            +Eawcdecortnfrecce+" "
                                            +awwroutinfollow+" "
                                            +totchldenroll+" "
                                            +totchldfoundtoday+" "
                                            +chldparticipatecc+" "
                                            +assmntcard+" "
                                            +assmntcardst+" "
                                            +lastecckitrcvdt+" "
                                            +lasteccdayobserv+" "
                                            +Einspectrcmnt);
                                    if (Esishualoy.equals("y")){
                                        YsishualoyID.setChecked(true);
                                    }
                                    else if (Esishualoy.equals("n")){
                                        NsishualoyID.setChecked(true);
                                    }
                                    else {
                                        YsishualoyID.setChecked(false);
                                        NsishualoyID.setChecked(false);
                                    }
                                    if (Ecornercgnitv.equals("good")){
                                        bccGoodID.setChecked(true);
                                    }
                                    else if (Ecornercgnitv.equals("average")){
                                        bccAverage.setChecked(true);
                                    }
                                    else if (Ecornercgnitv.equals("poor")){
                                        bccPoor.setChecked(true);
                                    }
                                    else if (Ecornercgnitv.equals("na")){
                                        bccNA.setChecked(true);
                                    }
                                    else {
                                        bccGoodID.setChecked(false);
                                        bccAverage.setChecked(false);
                                        bccPoor.setChecked(false);
                                        bccNA.setChecked(false);
                                    }

                                    if (Ebookcorner.equals("good")){
                                        bcgoodID.setChecked(true);
                                    }
                                    else if (Ebookcorner.equals("average")){
                                        bcaverageID.setChecked(true);
                                    }
                                    else if (Ebookcorner.equals("poor")){
                                        bcpoorID.setChecked(true);
                                    }
                                    else if (Ebookcorner.equals("na")){
                                        bcnaID.setChecked(true);
                                    }
                                    else {
                                        bcgoodID.setChecked(false);
                                        bcaverageID.setChecked(false);
                                        bcpoorID.setChecked(false);
                                        bcnaID.setChecked(false);
                                    }

                                    if (Edrawcornr.equals("good")){
                                        dcgoodID.setChecked(true);
                                    }
                                    else if (Edrawcornr.equals("average")){
                                        dcaverageID.setChecked(true);
                                    }
                                    else if (Edrawcornr.equals("poor")){
                                        dcPoorID.setChecked(true);
                                    }
                                    else if (Edrawcornr.equals("na")){
                                        dcnaID.setChecked(true);
                                    }
                                    else {
                                        dcgoodID.setChecked(false);
                                        dcaverageID.setChecked(false);
                                        dcPoorID.setChecked(false);
                                        dcnaID.setChecked(false);
                                    }

                                    if (Eplaycorner.equals("good")){
                                        pcgoodID.setChecked(true);
                                    }
                                    else if (Eplaycorner.equals("average")){
                                        pcaverageID.setChecked(true);
                                    }
                                    else if (Eplaycorner.equals("poor")){
                                        pcpoorID.setChecked(true);
                                    }
                                    else if (Eplaycorner.equals("na")){
                                        pcnaID.setChecked(true);
                                    }
                                    else {
                                        pcgoodID.setChecked(false);
                                        pcaverageID.setChecked(false);
                                        pcpoorID.setChecked(false);
                                        pcnaID.setChecked(false);
                                    }

                                    if (Eeccrun.equals("y")){
                                        YwerunID.setChecked(true);
                                    }
                                    else if (Eeccrun.equals("n")){
                                        NwerunID.setChecked(true);
                                    }
                                    else {
                                        YwerunID.setChecked(false);
                                        NwerunID.setChecked(false);
                                    }
                                    if (eccroutntheme.equals("1")){
                                        loadspiner();
                                    }
                                    else if (eccroutntheme.equals("2")){
                                        loadspiner();
                                    }
                                    else if (eccroutntheme.equals("3")){
                                        loadspiner();
                                    }
                                    else if (eccroutntheme.equals("4")){
                                        loadspiner();
                                    }
                                    else if (eccroutntheme.equals("5")){
                                        loadspiner();
                                    }
                                    else if (eccroutntheme.equals("6")){
                                        loadspiner();
                                    }
                                    else if (eccroutntheme.equals("7")){
                                        loadspiner();
                                    }
                                    else if (eccroutntheme.equals("8")){
                                        loadspiner();
                                    }
                                    else if (eccroutntheme.equals("9")){
                                        loadspiner();
                                    }
                                    else {

                                    }
                                    if (eccactivtyfound.equals("0")){
                                        list = new String[] {"Select ECCE Activity","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
                                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item, list);
                                        eccAfSp.setAdapter(adapter);
                                    }
                                    else if (eccactivtyfound.equals("1")){
                                       // loadspiner();
                                        list = new String[] {"Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
                                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item, list);
                                        eccAfSp.setAdapter(adapter);
                                    }
                                    else if (eccactivtyfound.equals("2")){
                                        //loadspiner();
                                        list = new String[] {"Guided Activity and Free Play","Morning circle Time","Outdoor Indoor Activity","Goodbye circle time","School Readiness","Others"};
                                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item, list);
                                        eccAfSp.setAdapter(adapter);
                                    }
                                    else if (eccactivtyfound.equals("3")){
                                        //loadspiner();
                                        list = new String[] {"Outdoor Indoor Activity","Morning circle Time","Guided Activity and Free Play","Goodbye circle time","School Readiness","Others"};
                                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item, list);
                                        eccAfSp.setAdapter(adapter);
                                    }
                                    else if (eccactivtyfound.equals("4")){
                                        //loadspiner();
                                        list = new String[] {"Goodbye circle time","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","School Readiness","Others"};
                                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item, list);
                                        eccAfSp.setAdapter(adapter);
                                    }
                                    else if (eccactivtyfound.equals("5")){
                                        //loadspiner();
                                        list = new String[] {"School Readiness","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","Others"};
                                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item, list);
                                        eccAfSp.setAdapter(adapter);
                                    }
                                    else if (eccactivtyfound.equals("6")){
                                       // loadspiner();
                                        list = new String[] {"Others","Morning circle Time","Guided Activity and Free Play","Outdoor Indoor Activity","Goodbye circle time","School Readiness"};
                                        adapter = new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item, list);
                                        eccAfSp.setAdapter(adapter);
                                    }
                                    else {

                                    }
                                    if (Eeccactvtytyp.equals("y")){
                                        Yawcfound.setChecked(true);
                                    }
                                    else if (Eeccactvtytyp.equals("n")){
                                        Nawcfound.setChecked(true);
                                    }
                                    else {
                                        Yawcfound.setChecked(false);
                                        Nawcfound.setChecked(false);
                                    }
                                    if (awwtlm.equals("y")){
                                        tlmYId.setChecked(true);
                                    }
                                    else if (awwtlm.equals("n")){
                                        tlmNId.setChecked(true);
                                    }
                                    else {
                                        tlmYId.setChecked(false);
                                        tlmNId.setChecked(false);
                                    }
                                    if (Eproprecckit.equals("p")){
                                        properlyID.setChecked(true);
                                    }
                                    else if (Eproprecckit.equals("np")){
                                        naID.setChecked(true);
                                    }
                                    else {
                                        properlyID.setChecked(false);
                                        naID.setChecked(false);
                                    }
                                    if (indvsualchildartwork.equals("y")){
                                        YartworkId.setChecked(true);
                                    }
                                    else if (indvsualchildartwork.equals("n")){
                                        NartworkId.setChecked(true);
                                    }
                                    else {
                                        YartworkId.setChecked(false);
                                        NartworkId.setChecked(false);
                                    }
                                    if (Eindvsualchildactvtyrcd.equals("y")){
                                        YciarfID.setChecked(true);
                                    }
                                    else if (Eindvsualchildactvtyrcd.equals("n")){
                                        NciarfID.setChecked(true);
                                    }
                                    else {
                                        YciarfID.setChecked(false);
                                        NciarfID.setChecked(false);
                                    }
                                    if (Eawcdecortnfrecce.equals("y")){
                                        YawcdecceID.setChecked(true);
                                    }
                                    else if (Eawcdecortnfrecce.equals("n")){
                                        NawcdecceID.setChecked(true);
                                    }
                                    else {
                                        YawcdecceID.setChecked(false);
                                        NawcdecceID.setChecked(false);
                                    }
                                    String[] firstArray = awwroutinfollow.split(",");
                                    List<String> list = new ArrayList<String>();
                                    for(String s : firstArray) {
                                        if(s != null && s.length() > 0) {
                                            list.add(s);
                                            Log.e("ROUTINGFLOWE",""+s);
                                            // firstArray = list.toArray(new String[list.size()]);
                                            //Log.e("ROUTINGFLOWEE",""+firstArray);
                                            if (s.equals("1")){
                                                chatId.setChecked(true);
                                            }
                                            else if (s.equals("2")){
                                                labelId.setChecked(true);
                                            }
                                            else if (s.equals("3")){
                                                rulesId.setChecked(true);
                                            }
                                            else if (s.equals("4")){
                                                routId.setChecked(true);
                                            }
                                            else if (s.equals("5")){
                                                calId.setChecked(true);
                                            }
                                            else if (s.equals("6")){
                                                childnameId.setChecked(true);
                                            }
                                            else if (s.equals("7")){
                                                childrenfolderId.setChecked(true);
                                            }
                                            else if (s.equals("8")){
                                                displaychId.setChecked(true);
                                            }
                                            else if (s.equals("9")){
                                                thembasewordwallId.setChecked(true);
                                            }
                                            else {

                                            }
                                        }
                                    }

                                    // YawwroutingId.setChecked(true);
                                    // NawwroutingId.setChecked(true);
                                    childenrolledId.setText(totchldenroll);
                                    childfounId.setText(totchldfoundtoday);
                                    if (chldparticipatecc.equals("y")){
                                        YchildrenceeeprocessId.setChecked(true);
                                    }
                                    else if (chldparticipatecc.equals("n")){
                                        NchildrenceeeprocessId.setChecked(true);
                                    }
                                    else {
                                        YchildrenceeeprocessId.setChecked(false);
                                        NchildrenceeeprocessId.setChecked(false);
                                    }

                                    if (assmntcard.equals("y")){
                                        YassesmentcardId.setChecked(true);
                                    }
                                    else if (assmntcard.equals("n")){
                                        NassesmentcardId.setChecked(true);
                                    }
                                    else {
                                        YassesmentcardId.setChecked(false);
                                        NassesmentcardId.setChecked(false);
                                    }
                                    if (assmntcardst.equals("In Use")){
                                        InuseId.setChecked(true);

                                    }
                                    else if (assmntcardst.equals("Not in Use")){
                                        NotinuseId.setChecked(true);
                                    }
                                    else {
                                        InuseId.setChecked(false);
                                        NotinuseId.setChecked(false);
                                    }
                                    FdateId.setText(lastecckitrcvdt);
                                    TdateId.setText(lasteccdayobserv);
//                                ecceawcfoundID.setText(Eeccactvtytyp);
//                                if (Eproprecckit.equals("p")){
//                                    properlyID.setChecked(true);
//                                }
//                                else if (Eproprecckit.equals("np")){
//                                    naID.setChecked(true);
//                                }
//                                else {
//                                    properlyID.setChecked(false);
//                                    naID.setChecked(false);
//                                }
//
//                                if (Eindvsualchildactvtyrcd.equals("y")){
//                                    YciarfID.setChecked(true);
//                                }
//                                else if (Eindvsualchildactvtyrcd.equals("n")){
//                                    NciarfID.setChecked(true);
//                                }
//                                else {
//                                    YciarfID.setChecked(false);
//                                    NciarfID.setChecked(false);
//                                }
//
//                                if (Eawcdecortnfrecce.equals("y")){
//                                    YawcdecceID.setChecked(true);
//                                }
//                                else if (Eawcdecortnfrecce.equals("n")){
//                                    NawcdecceID.setChecked(true);
//                                }
//                                else {
//                                    YawcdecceID.setChecked(false);
//                                    NawcdecceID.setChecked(false);
//                                }
//                                psenmID.setText(Epseactivitynm);
//                                ncpotID.setText(Etotchldprsnt);
//                                responchildID.setText(Echldrspnse);
                                    if (awwfollowroutine.equals("y")){
                                        YawwroutingId.setChecked(true);
                                    }
                                    else if (awwfollowroutine.equals("n")){
                                        NawwroutingId.setChecked(true);
                                    }
                                    else {
                                        YawwroutingId.setChecked(false);
                                        NawwroutingId.setChecked(false);
                                    }
                                    GvCID.setText(Einspectrcmnt);
                                }

                                if (Esishualoy.equals("n")){
                                    pseactvfound = jsonObject.getString("pse_actv_found");
                                    pseactivitynm = jsonObject.getString("pse_activity_nm");
                                    totchldenroll = jsonObject.getString("tot_chld_enroll");
                                    totchldfoundtoday = jsonObject.getString("tot_chld_found_today");
                                    chldparticipatpse = jsonObject.getString("chld_participat_pse");
                                    assmntcard = jsonObject.getString("assmnt_card");
                                    assmntcardst = jsonObject.getString("assmnt_card_st");
                                    Einspectrcmnt = jsonObject.getString("inspectr_cmnt");
                                    Log.e("SHISHUALY_NO", Esishualoy+" "+pseactvfound+" "
                                            +pseactivitynm+" "+totchldenroll+" "
                                            +totchldfoundtoday+" "+chldparticipatpse+" "+assmntcard+" "+assmntcardst+" "+Einspectrcmnt+" ");
                                    if (Esishualoy.equals("y")){
                                        YsishualoyID.setChecked(true);
                                    }
                                    else if (Esishualoy.equals("n")){
                                        NsishualoyID.setChecked(true);
                                    }
                                    if (pseactvfound.equals("y")){
                                        YpseId.setChecked(true);
                                    }
                                    else if (pseactvfound.equals("n")){
                                        NpseId.setChecked(true);
                                    }
                                    else {
                                        YpseId.setChecked(false);
                                        NpseId.setChecked(false);
                                    }

                                    psenmID.setText(pseactivitynm);
                                    NenrolledchildrenId.setText(totchldenroll);
                                    NchildfoundId.setText(totchldfoundtoday);
                                    if (chldparticipatpse.equals("Y")){
                                        NNYparticipentpscId.setChecked(true);
                                    }
                                    else if (chldparticipatpse.equals("N")){
                                        NNNparticipentpscId.setChecked(true);
                                    }
                                    else {
                                        NNYparticipentpscId.setChecked(false);
                                        NNNparticipentpscId.setChecked(false);
                                    }

                                     if (assmntcard.equals("y")){
                                         NYifassenmentId.setChecked(true);
                                     }
                                     else if (assmntcard.equals("n")){
                                         NNifassenmentId.setChecked(true);
                                     }
                                     else {
                                         NYifassenmentId.setChecked(false);
                                         NNifassenmentId.setChecked(false);
                                     }

                                     if (assmntcardst.equals("In Use")){
                                         NInuseId.setChecked(true);
                                     }
                                     else if (assmntcardst.equals("Not in Use")){
                                         NNotinuseId.setChecked(true);
                                     }
                                     else {
                                         NInuseId.setChecked(false);
                                         NNotinuseId.setChecked(false);
                                     }
                                    Einspectrcmnt = jsonObject.getString("inspectr_cmnt");
                                    NGvCID.setText(Einspectrcmnt);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.saveID:
//                if (YsishualoyID.isChecked() || NsishualoyID.isChecked()){
//                    if (bccGoodID.isChecked() || bccAverage.isChecked() || bccPoor.isChecked() || bccNA.isChecked()){
//                        if (bcgoodID.isChecked() || bcaverageID.isChecked() || bcpoorID.isChecked() || bcnaID.isChecked()){
//                            if (dcgoodID.isChecked() || dcaverageID.isChecked() || dcPoorID.isChecked() || dcnaID.isChecked()){
//                                if (pcgoodID.isChecked() || pcaverageID.isChecked() || pcpoorID.isChecked() || pcnaID.isChecked()){
//                                    if (YwerunID.isChecked() || NwerunID.isChecked()){
//                                        InputFilter filter = new InputFilter() {
//                                            public CharSequence filter(CharSequence source, int start, int end,
//                                                                       Spanned dest, int dstart, int dend) {
//                                                char[] chars = {'\'','"'};
//                                                for (int i = start; i < end; i++) {
//                                                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                        return "";
//                                                    }
//                                                }
//                                                return null;
//                                            }
//                                        };
//                                        ecceawcfoundID.setFilters(new InputFilter[] { filter });
//                                        eccactvtytypRep = ecceawcfoundID.getText().toString().trim();
//                                        if (TextUtils.isEmpty(eccactvtytypRep)) {
//                                            ecceawcfoundID.setError("Please enter Ecce Activity At Awc Found");
//                                            ecceawcfoundID.requestFocus();
//                                            return;
//                                        }
//                                        if (properlyID.isChecked() || naID.isChecked()){
//                                            if (YciarfID.isChecked() || NciarfID.isChecked()){
//                                                if (YawcdecceID.isChecked() || NawcdecceID.isChecked()){
//                                                    InputFilter filter1 = new InputFilter() {
//                                                        public CharSequence filter(CharSequence source, int start, int end,
//                                                                                   Spanned dest, int dstart, int dend) {
//                                                            char[] chars = {'\'','"'};
//                                                            for (int i = start; i < end; i++) {
//                                                                if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                                    return "";
//                                                                }
//                                                            }
//                                                            return null;
//                                                        }
//                                                    };
//                                                    psenmID.setFilters(new InputFilter[] { filter1 });
//                                                    pseactivitynm = psenmID.getText().toString().trim();
//                                                    if (TextUtils.isEmpty(pseactivitynm)) {
//                                                        psenmID.setError("Please enter Name Pse activity is Seen");
//                                                        psenmID.requestFocus();
//                                                        return;
//                                                    }
//                                                    InputFilter filter2 = new InputFilter() {
//                                                        public CharSequence filter(CharSequence source, int start, int end,
//                                                                                   Spanned dest, int dstart, int dend) {
//                                                            char[] chars = {'\'','"'};
//                                                            for (int i = start; i < end; i++) {
//                                                                if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                                    return "";
//                                                                }
//                                                            }
//                                                            return null;
//                                                        }
//                                                    };
//                                                    ncpotID.setFilters(new InputFilter[] { filter2 });
//                                                    totchldprsnt = ncpotID.getText().toString().trim();
//                                                    if (TextUtils.isEmpty(totchldprsnt)) {
//                                                        ncpotID.setError("Please enter Name Pse activity is Seen");
//                                                        ncpotID.requestFocus();
//                                                        return;
//                                                    }
//                                                    InputFilter filter3 = new InputFilter() {
//                                                        public CharSequence filter(CharSequence source, int start, int end,
//                                                                                   Spanned dest, int dstart, int dend) {
//                                                            char[] chars = {'\'','"'};
//                                                            for (int i = start; i < end; i++) {
//                                                                if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                                    return "";
//                                                                }
//                                                            }
//                                                            return null;
//                                                        }
//                                                    };
//                                                    responchildID.setFilters(new InputFilter[] { filter3 });
//                                                    chldrspnse = responchildID.getText().toString().trim();
//                                                    if (TextUtils.isEmpty(chldrspnse)) {
//                                                        responchildID.setError("Please enter Response From Children");
//                                                        responchildID.requestFocus();
//                                                        return;
//                                                    }
//                                                    update_shishu_aloy_activity();
//                                                }
//
//                                                else {
//                                                    Toast.makeText(ShishuAloyActivity.this, "SELECT AWC DECORATION IS FIT FOR ECCE", Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                            else {
//                                                Toast.makeText(ShishuAloyActivity.this, "SELECT CHILDREN INDIVIDUAL ACTIVITY RECORD FOUND", Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        }
//                                        else {
//                                            Toast.makeText(ShishuAloyActivity.this, "SELECT WW USING ECCE KIT PROPERLY OR NOT", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                    else {
//                                        Toast.makeText(ShishuAloyActivity.this, "SELECT WHETHER ECCE IS RUNNING", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                }
//                                else {
//                                    Toast.makeText(ShishuAloyActivity.this, "SELECT PLAY CORNER", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            else {
//                                Toast.makeText(ShishuAloyActivity.this, "SELECT DRAWING CORNER", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                        else {
//                            Toast.makeText(ShishuAloyActivity.this, "SELECT BLOCK CORNER", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else {
//                        Toast.makeText(ShishuAloyActivity.this, "SELECT BLOCK CORNER COGNITIVE", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//                    Toast.makeText(ShishuAloyActivity.this, "SELECT SHISHU ALOY", Toast.LENGTH_SHORT).show();
//                }
//                break;

            case R.id.ecceawcfoundspak:
                ecceawcfoundspak();
                break;
            case R.id.psenmspak:
                psenmspak();
                break;
            case R.id.ncpotIDspak:
                ncpotIDspak();
                break;
            case R.id.cmdspak:
                responchildID();
                break;
            case R.id.commandspk:
                GvCID();
                break;
            case R.id.searchID:
                new DatePickerDialog(ShishuAloyActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.searchIDD:
                new DatePickerDialog(ShishuAloyActivity.this, datee, myCalendarr
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendarr.get(Calendar.DAY_OF_MONTH)).show();
                break;
            default:
        }
    }

    private void ecceawcfoundspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_PSEACTIVITYFOUND);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void psenmspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_PSEACTIVITYSEEN);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void ncpotIDspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_PSEPRSENTOUTOFTOTAL);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void responchildID(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_PSECHILDREN);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void GvCID(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_text));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_PSECOMMAND);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_text),
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_PSEACTIVITYFOUND: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ecceawcfoundID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_PSEACTIVITYSEEN: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    psenmID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_PSEPRSENTOUTOFTOTAL: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ncpotID.setText(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_PSECHILDREN: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    responchildID.setText(result.get(0));
                }
                break;
            }
        }
        switch (requestCode) {
            case REQ_CODE_SPEECH_PSECOMMAND: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    GvCID.setText(result.get(0));
                }
                break;
            }
        }
    }
             public void savedate(){
                 if (YsishualoyID.isChecked() || NsishualoyID.isChecked()) {
                     if (sishualoy.equals("y")){
                         if (bccGoodID.isChecked() || bccAverage.isChecked() || bccPoor.isChecked() || bccNA.isChecked()) {
                             if (bcgoodID.isChecked() || bcaverageID.isChecked() || bcpoorID.isChecked() || bcnaID.isChecked()) {
                                 if (dcgoodID.isChecked() || dcaverageID.isChecked() || dcPoorID.isChecked() || dcnaID.isChecked()) {
                                     if (pcgoodID.isChecked() || pcaverageID.isChecked() || pcpoorID.isChecked() || pcnaID.isChecked()) {
                                         if (YwerunID.isChecked() || NwerunID.isChecked()) {
//                                                        InputFilter filter = new InputFilter() {
//                                                            public CharSequence filter(CharSequence source, int start, int end,
//                                                                                       Spanned dest, int dstart, int dend) {
//                                                                char[] chars = {'\'','"'};
//                                                                for (int i = start; i < end; i++) {
//                                                                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                                        return "";
//                                                                    }
//                                                                }
//                                                                return null;
//                                                            }
//                                                        };
//
//                                                        ecceawcfoundID.setFilters(new InputFilter[] { filter });
//                                                        eccactvtytypRep = ecceawcfoundID.getText().toString().trim();
//                                                        if (TextUtils.isEmpty(eccactvtytypRep)) {
//                                                            ecceawcfoundID.setError("Please enter Ecce Activity At Awc Found");
//                                                            ecceawcfoundID.requestFocus();
//                                                           // return;
//                                                        }


                                             if (Yawcfound.isChecked() || Nawcfound.isChecked()) {
                                                 if (tlmYId.isChecked() || tlmNId.isChecked()) {
                                                     if (properlyID.isChecked() || naID.isChecked()) {
                                                         if (YartworkId.isChecked() || NartworkId.isChecked()) {
                                                             if (YciarfID.isChecked() || NciarfID.isChecked()) {
                                                                 if (YawcdecceID.isChecked() || NawcdecceID.isChecked()) {
                                                                     if (YawwroutingId.isChecked() || NawwroutingId.isChecked()) {
                                                                         InputFilter filter2 = new InputFilter() {
                                                                             public CharSequence filter(CharSequence source, int start, int end,
                                                                                                        Spanned dest, int dstart, int dend) {
                                                                                 char[] chars = {'\'', '"'};
                                                                                 for (int i = start; i < end; i++) {
                                                                                     if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                                                                         return "";
                                                                                     }
                                                                                 }
                                                                                 return null;
                                                                             }
                                                                         };
                                                                         childenrolledId.setFilters(new InputFilter[]{filter2});
                                                                         childenrolled = childenrolledId.getText().toString().trim();
                                                                         if (TextUtils.isEmpty(childenrolled)) {
                                                                             childenrolledId.setError("Please enter Number");
                                                                             childenrolledId.requestFocus();
                                                                             // return;
                                                                         }

                                                                         InputFilter filter4 = new InputFilter() {
                                                                             public CharSequence filter(CharSequence source, int start, int end,
                                                                                                        Spanned dest, int dstart, int dend) {
                                                                                 char[] chars = {'\'', '"'};
                                                                                 for (int i = start; i < end; i++) {
                                                                                     if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                                                                         return "";
                                                                                     }
                                                                                 }
                                                                                 return null;
                                                                             }
                                                                         };
                                                                         childenrolledId.setFilters(new InputFilter[]{filter4});
                                                                         childfoundtodayy = childfounId.getText().toString().trim();
                                                                         if (TextUtils.isEmpty(childfoundtodayy)) {
                                                                             childfounId.setError("Please enter Number");
                                                                             childfounId.requestFocus();
                                                                             // return; ecckitdate
                                                                         }

                                                                         if (YchildrenceeeprocessId.isChecked() || NchildrenceeeprocessId.isChecked()) {
                                                                             if (YassesmentcardId.isChecked() || NassesmentcardId.isChecked()) {
                                                                                 if (FdateId.getText().toString().equals("Date")) {
                                                                                     Toast.makeText(ShishuAloyActivity.this, "SELECT DATE OF LAST RECEVED OF ECCE KIT", Toast.LENGTH_SHORT).show();
                                                                                     String title = "Message Box";
                                                                                     String msg = "SELECT DATE OF LAST RECEVED OF ECCE KIT";
                                                                                     createDialog(title,msg);

                                                                                 } else {
                                                                                     if (TdateId.getText().toString().equals("Date")) {
                                                                                         Toast.makeText(ShishuAloyActivity.this, "SELECT DATE OF LAST ECCE OBSERVED", Toast.LENGTH_SHORT).show();
                                                                                         String title = "Message Box";
                                                                                         String msg = "SELECT DATE OF LAST ECCE OBSERVED";
                                                                                         createDialog(title,msg);

                                                                                     } else {
                                                                                         InputFilter filter1 = new InputFilter() {
                                                                                             public CharSequence filter(CharSequence source, int start, int end,
                                                                                                                        Spanned dest, int dstart, int dend) {
                                                                                                 char[] chars = {'\'', '"'};
                                                                                                 for (int i = start; i < end; i++) {
                                                                                                     if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                                                                                         return "";
                                                                                                     }
                                                                                                 }
                                                                                                 return null;
                                                                                             }
                                                                                         };

                                                                                         GvCID.setFilters(new InputFilter[]{filter1});
                                                                                         inspectrcmnt = GvCID.getText().toString().trim();
                                                                                         if (TextUtils.isEmpty(inspectrcmnt)) {
                                                                                             GvCID.setError("Please enter Comment");
                                                                                             GvCID.requestFocus();
                                                                                                return;
                                                                                         }
//
//                                                                                                        InputFilter filter3 = new InputFilter() {
//                                                                                                            public CharSequence filter(CharSequence source, int start, int end,
//                                                                                                                                       Spanned dest, int dstart, int dend) {
//                                                                                                                char[] chars = {'\'', '"'};
//                                                                                                                for (int i = start; i < end; i++) {
//                                                                                                                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                                                                                        return "";
//                                                                                                                    }
//                                                                                                                }
//                                                                                                                return null;
//                                                                                                            }
//                                                                                                        };
//                                                                                                        responchildID.setFilters(new InputFilter[]{filter3});
//                                                                                                        chldrspnse = responchildID.getText().toString().trim();
//                                                                                                        if (TextUtils.isEmpty(chldrspnse)) {
//                                                                                                            responchildID.setError("Please enter Response From Children");
//                                                                                                            responchildID.requestFocus();
//                                                                                                            //return;
//                                                                                                        }
                                                                                         //YYYY
                                                                                         if (YawcdecceID.isChecked()
                                                                                                 && chatId.isChecked() || labelId.isChecked() || rulesId.isChecked() || routId.isChecked()
                                                                                                 || calId.isChecked()
                                                                                                 || childnameId.isChecked()
                                                                                                 || childrenfolderId.isChecked()
                                                                                                 || displaychId.isChecked()
                                                                                                 || thembasewordwallId.isChecked()
                                                                                                 && YassesmentcardId.isChecked() && InuseId.isChecked()) {
                                                                                             if (YwerunID.isChecked()){
                                                                                                 if (Yawcfound.isChecked()){
                                                                                                     if (routing.equals("Select Routine Theme")) {
                                                                                                     }
                                                                                                     else {
                                                                                                         if (awcfound.equals("Select ECCE Activity")) {
                                                                                                         }
                                                                                                         else {
                                                                                                             childfoundtodayy = childfounId.getText().toString();
                                                                                                             childenrolledd = Integer.parseInt(childenrolled);
                                                                                                             childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                             Log.e("childenrolled", childfoundtodayy);
                                                                                                             if (childenrolledd>=childfoundtodayyy ){
                                                                                                                 childfounId.setText(childfounId.getText().toString());
                                                                                                                 Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                                 String val = "0";
                                                                                                                 update_shishu_aloy_activity(val);
                                                                                                             }
                                                                                                             else {
                                                                                                                 childfounId.setText("");
                                                                                                                 Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                                 String title = "Message Box";
                                                                                                                 String msg = "NO VALID DATA";
                                                                                                                 createDialog(title,msg);
                                                                                                             }

                                                                                                         } }
                                                                                                 }
                                                                                             }

                                                                                         }
                                                                                         //NNNN
                                                                                         if (NwerunID.isChecked() && Nawcfound.isChecked() && NawcdecceID.isChecked() && NassesmentcardId.isChecked()){
                                                                                             childfoundtodayy = childfounId.getText().toString();
                                                                                             childenrolledd = Integer.parseInt(childenrolled);
                                                                                             childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                             Log.e("childenrolled", childfoundtodayy);
                                                                                             if (childenrolledd>=childfoundtodayyy ){
                                                                                                 childfounId.setText(childfounId.getText().toString());
                                                                                                 Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                 String val = "1";
                                                                                                 update_shishu_aloy_activity(val);
                                                                                             }
                                                                                             else {
                                                                                                 childfounId.setText("");
                                                                                                 Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                 String title = "Message Box";
                                                                                                 String msg = "NO VALID DATA";
                                                                                                 createDialog(title,msg);
                                                                                             }

                                                                                         }
                                                                                         //YNYN
                                                                                             if (YwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                                 if (YawcdecceID.isChecked() && NassesmentcardId.isChecked()){
                                                                                                     if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                             || routId.isChecked() || calId.isChecked()
                                                                                                             || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                             || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                         if (YwerunID.isChecked()){
                                                                                                             if (routing.equals("Select Routine Theme")) {
                                                                                                             }
                                                                                                             else {
                                                                                                                 childfoundtodayy = childfounId.getText().toString();
                                                                                                                 childenrolledd = Integer.parseInt(childenrolled);
                                                                                                                 childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                                 Log.e("childenrolled", childfoundtodayy);
                                                                                                                 if (childenrolledd>=childfoundtodayyy ){
                                                                                                                     childfounId.setText(childfounId.getText().toString());
                                                                                                                     Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                                     String val = "2";
                                                                                                                     update_shishu_aloy_activity(val);
                                                                                                                 }
                                                                                                                 else {
                                                                                                                     childfounId.setText("");
                                                                                                                     Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                                     String title = "Message Box";
                                                                                                                     String msg = "NO VALID DATA";
                                                                                                                     createDialog(title,msg);
                                                                                                                 }
                                                                                                             }
                                                                                                         }

                                                                                                     } } }
                                                                                             //YNNY
                                                                                         if (YwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                             if (NawcdecceID.isChecked() && YassesmentcardId.isChecked()){
                                                                                                 if (InuseId.isChecked() || NotinuseId.isChecked()){
                                                                                                     if (routing.equals("Select Routine Theme")) {
                                                                                                     }
                                                                                                     else {
                                                                                                         childfoundtodayy = childfounId.getText().toString();
                                                                                                         childenrolledd = Integer.parseInt(childenrolled);
                                                                                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                         Log.e("childenrolled", childfoundtodayy);
                                                                                                         if (childenrolledd>=childfoundtodayyy ){
                                                                                                             childfounId.setText(childfounId.getText().toString());
                                                                                                             Toast.makeText(getApplicationContext(), "Valid Data", Toast.LENGTH_SHORT).show();
                                                                                                             String val = "3";
                                                                                                             update_shishu_aloy_activity(val);
                                                                                                         }
                                                                                                         else {
                                                                                                             childfounId.setText("");
                                                                                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String title = "Message Box";
                                                                                                             String msg = "NO VALID DATA";
                                                                                                             createDialog(title,msg);
                                                                                                         }
                                                                                                     }
                                                                                                 }
                                                                                             }
                                                                                         }
                                                                                         //YNYY
                                                                                         if (YwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                             if (YawcdecceID.isChecked() && YassesmentcardId.isChecked()){
                                                                                                 if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                         || routId.isChecked() || calId.isChecked()
                                                                                                         || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                         || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                     if (YwerunID.isChecked()) {
                                                                                                         if (routing.equals("Select Routine Theme")) {
                                                                                                         } else {
                                                                                                             childfoundtodayy = childfounId.getText().toString();
                                                                                                             childenrolledd = Integer.parseInt(childenrolled);
                                                                                                             childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                             Log.e("childenrolled", childfoundtodayy);
                                                                                                             if (childenrolledd>=childfoundtodayyy ){
                                                                                                                 childfounId.setText(childfounId.getText().toString());
                                                                                                                 Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                                 String val = "2";
                                                                                                                 update_shishu_aloy_activity(val);
                                                                                                             }
                                                                                                             else {
                                                                                                                 childfounId.setText("");
                                                                                                                 Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                                 String title = "Message Box";
                                                                                                                 String msg = "NO VALID DATA";
                                                                                                                 createDialog(title,msg);
                                                                                                             }

                                                                                                         }

                                                                                                     }

                                                                                                 }
                                                                                             }
                                                                                         }
                                                                                         //NNYY
                                                                                         if (NwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                             if (YawcdecceID.isChecked() && YassesmentcardId.isChecked()){
                                                                                                 if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                         || routId.isChecked() || calId.isChecked()
                                                                                                         || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                         || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                     if (InuseId.isChecked() || NotinuseId.isChecked()) {
                                                                                                         childfoundtodayy = childfounId.getText().toString();
                                                                                                         childenrolledd = Integer.parseInt(childenrolled);
                                                                                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                         Log.e("childenrolled", childfoundtodayy);
                                                                                                         if (childenrolledd>=childfoundtodayyy ){
                                                                                                             childfounId.setText(childfounId.getText().toString());
                                                                                                             Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String val = "4";
                                                                                                             update_shishu_aloy_activity(val);
                                                                                                         }
                                                                                                         else {
                                                                                                             childfounId.setText("");
                                                                                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String title = "Message Box";
                                                                                                             String msg = "NO VALID DATA";
                                                                                                             createDialog(title,msg);
                                                                                                         }
                                                                                                     }
                                                                                                     }


                                                                                                 }
                                                                                             }
                                                                                         //NYNY
                                                                                         if (NwerunID.isChecked() && Yawcfound.isChecked()){
                                                                                             if (NawcdecceID.isChecked() && YassesmentcardId.isChecked()){
                                                                                                 if (InuseId.isChecked() || NotinuseId.isChecked()) {
                                                                                                     if (Yawcfound.isChecked()){
                                                                                                         if (awcfound.equals("Select ECCE Activity")) {
                                                                                                             }
                                                                                                             else {
                                                                                                             childfoundtodayy = childfounId.getText().toString();
                                                                                                             childenrolledd = Integer.parseInt(childenrolled);
                                                                                                             childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                             Log.e("childenrolled", childfoundtodayy);
                                                                                                             if (childenrolledd>=childfoundtodayyy ){
                                                                                                                 childfounId.setText(childfounId.getText().toString());
                                                                                                                 Toast.makeText(getApplicationContext(), "Valid Data", Toast.LENGTH_SHORT).show();
                                                                                                                 String val = "5";
                                                                                                                 update_shishu_aloy_activity(val);
                                                                                                             }
                                                                                                             else {
                                                                                                                 childfounId.setText("");
                                                                                                                 Toast.makeText(getApplicationContext(), "No Valid Data", Toast.LENGTH_SHORT).show();
                                                                                                             }
                                                                                                             } } } } }
                                                                                         //NYYN
                                                                                         if (NwerunID.isChecked() && Yawcfound.isChecked()){
                                                                                             if (YawcdecceID.isChecked() && NassesmentcardId.isChecked()){
                                                                                                 if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                         || routId.isChecked() || calId.isChecked()
                                                                                                         || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                         || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                     if (Yawcfound.isChecked()) {
                                                                                                         if (awcfound.equals("Select ECCE Activity")) {
                                                                                                         } else {
                                                                                                             childfoundtodayy = childfounId.getText().toString();
                                                                                                             childenrolledd = Integer.parseInt(childenrolled);
                                                                                                             childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                             Log.e("childenrolled", childfoundtodayy);
                                                                                                             if (childenrolledd>=childfoundtodayyy ){
                                                                                                                 childfounId.setText(childfounId.getText().toString());
                                                                                                                 Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                                 String val = "6";
                                                                                                                 update_shishu_aloy_activity(val);
                                                                                                             }
                                                                                                             else {
                                                                                                                 childfounId.setText("");
                                                                                                                 Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                                 String title = "Message Box";
                                                                                                                 String msg = "NO VALID DATA";
                                                                                                                 createDialog(title,msg);
                                                                                                             }
                                                                                                         }
                                                                                                     }
                                                                                                 }
                                                                                             }
                                                                                         }

                                                                                         //NNYN
                                                                                         if (NwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                             if (YawcdecceID.isChecked() && NassesmentcardId.isChecked()){
                                                                                                 if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                         || routId.isChecked() || calId.isChecked()
                                                                                                         || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                         || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                     childfoundtodayy = childfounId.getText().toString();
                                                                                                     childenrolledd = Integer.parseInt(childenrolled);
                                                                                                     childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                     Log.e("childenrolled", childfoundtodayy);
                                                                                                     if (childenrolledd>=childfoundtodayyy ){
                                                                                                         childfounId.setText(childfounId.getText().toString());
                                                                                                         Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                         String val = "7";
                                                                                                         update_shishu_aloy_activity(val);
                                                                                                     }
                                                                                                     else {
                                                                                                         childfounId.setText("");
                                                                                                         Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                         String title = "Message Box";
                                                                                                         String msg = "NO VALID DATA";
                                                                                                         createDialog(title,msg);
                                                                                                     }
                                                                                                 }
                                                                                             }

                                                                                         }





                                                                                     }
                                                                                 }

                                                                             } else {
                                                                                 Toast.makeText(ShishuAloyActivity.this, "SELECT IF ASSESSMENT CARD IS AVAILBLE", Toast.LENGTH_SHORT).show();
                                                                                 String title = "Message Box";
                                                                                 String msg = "SELECT IF ASSESSMENT CARD IS AVAILBLE";
                                                                                 createDialog(title,msg);

                                                                             }

                                                                         } else {
                                                                             Toast.makeText(ShishuAloyActivity.this, "SELECT CHILDREN ARE ACTIVITY AND PARTICIPATING IN ECCE PROCCESS", Toast.LENGTH_SHORT).show();
                                                                             String title = "Message Box";
                                                                             String msg = "SELECT CHILDREN ARE ACTIVITY AND PARTICIPATING IN ECCE PROCCESS";
                                                                             createDialog(title,msg);
                                                                         }

                                                                     } else {
                                                                         Toast.makeText(ShishuAloyActivity.this, "SELECT WHETHER AWW IS FOLLOWING THE ROTINE", Toast.LENGTH_SHORT).show();
                                                                         String title = "Message Box";
                                                                         String msg = "SELECT WHETHER AWW IS FOLLOWING THE ROTINE";
                                                                         createDialog(title,msg);

                                                                     }

                                                                 } else {
                                                                     Toast.makeText(ShishuAloyActivity.this, "SELECT AWC DECORATION IS FIT FOR ECCE", Toast.LENGTH_SHORT).show();
                                                                     String title = "Message Box";
                                                                     String msg = "SELECT AWC DECORATION IS FIT FOR ECCE";
                                                                     createDialog(title,msg);
                                                                 }
                                                             } else {
                                                                 Toast.makeText(ShishuAloyActivity.this, "SELECT CHILDREN INDIVIDUAL ACTIVITY RECORD FOUND", Toast.LENGTH_SHORT).show();
                                                                 String title = "Message Box";
                                                                 String msg = "SELECT CHILDREN INDIVIDUAL ACTIVITY RECORD FOUND";
                                                                 createDialog(title,msg);
                                                             }
                                                         } else {
                                                             Toast.makeText(ShishuAloyActivity.this, "SELECT AVAILABILITY OF INDIVIDUAL CHILD FILE CONSISTING CHILDREN'S ARTWORKS", Toast.LENGTH_SHORT).show();
                                                             String title = "Message Box";
                                                             String msg = "SELECT AVAILABILITY OF INDIVIDUAL CHILD FILE CONSISTING CHILDREN'S ARTWORKS";
                                                             createDialog(title,msg);
                                                         }


                                                     } else {
                                                         Toast.makeText(ShishuAloyActivity.this, "SELECT AWW USING ECCE KIT PROPERLY OR NOT", Toast.LENGTH_SHORT).show();
                                                         String title = "Message Box";
                                                         String msg = "SELECT AWW USING ECCE KIT PROPERLY OR NOT";
                                                         createDialog(title,msg);
                                                     }

                                                 } else {
                                                     Toast.makeText(ShishuAloyActivity.this, "SELECT AWW IS USING APPROPRIATE TLM", Toast.LENGTH_SHORT).show();
                                                     String title = "Message Box";
                                                     String msg = "SELECT AWW IS USING APPROPRIATE TLM";
                                                     createDialog(title,msg);
                                                 }
                                             } else {
                                                 Toast.makeText(ShishuAloyActivity.this, "SELECT ECCE ACTIVITY AT AWC FOUND DURING VISIT", Toast.LENGTH_SHORT).show();
                                                 String title = "Message Box";
                                                 String msg = "SELECT ECCE ACTIVITY AT AWC FOUND DURING VISIT";
                                                 createDialog(title,msg);
                                             }
                                         } else {
                                             Toast.makeText(ShishuAloyActivity.this, "SELECT WHETHER ECCE IS RUNNING", Toast.LENGTH_SHORT).show();
                                             String title = "Message Box";
                                             String msg = "SELECT WHETHER ECCE IS RUNNING";
                                             createDialog(title,msg);
                                         }

                                     } else {
                                         Toast.makeText(ShishuAloyActivity.this, "SELECT PLAY CORNER", Toast.LENGTH_SHORT).show();
                                         String title = "Message Box";
                                         String msg = "SELECT PLAY CORNER";
                                         createDialog(title,msg);
                                     }
                                 } else {
                                     Toast.makeText(ShishuAloyActivity.this, "SELECT DRAWING CORNER", Toast.LENGTH_SHORT).show();
                                     String title = "Message Box";
                                     String msg = "SELECT DRAWING CORNER";
                                     createDialog(title,msg);
                                 }

                             } else {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECT BOOK CORNER", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT BOOK CORNER";
                                 createDialog(title,msg);
                             }
                         } else {
                             Toast.makeText(ShishuAloyActivity.this, "SELECT BLOCK CORNER", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT BLOCK CORNER";
                             createDialog(title,msg);
                         }
                     }
                     if (sishualoy.equals("y")) {
                         if (YwerunID.isChecked()) {
                             Log.e("eccrun", " " + eccrun);
                             if (routing.equals("Select Routine Theme")) {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECT ROUTINE THEME", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT ROUTINE THEME";
                                 createDialog(title,msg);
                             }
                         }
                         if (Yawcfound.isChecked()) {
                             if (awcfound.equals("Select ECCE Activity")) {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECT ECCE ACTIVITY", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT ECCE ACTIVITY";
                                 createDialog(title,msg);
                             }
                         }
                        if (YawcdecceID.isChecked()){
                             if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked() || routId.isChecked() || calId.isChecked() || childnameId.isChecked() || childrenfolderId.isChecked() || displaychId.isChecked() || thembasewordwallId.isChecked()) {


                             } else {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECT AWC DECORATION IS FIT FOR ECCE CHECK BOX", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT AWC DECORATION IS FIT FOR ECCE CHECK BOX";
                                 createDialog(title,msg);
                             }
                         }
                         if (YassesmentcardId.isChecked()){
                             if (InuseId.isChecked() || NotinuseId.isChecked()){

                             }
                             else {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECTI F ASSESSMENT CARD IS AVAILABLE", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECTI F ASSESSMENT CARD IS AVAILABLE";
                                 createDialog(title,msg);

                             }
                         }

                     }
                     if (sishualoy.equals("n")){
                         if (YpseId.isChecked() || NpseId.isChecked()){
                             InputFilter filterpse = new InputFilter() {
                                 public CharSequence filter(CharSequence source, int start, int end,
                                                            Spanned dest, int dstart, int dend) {
                                     char[] chars = {'\'', '"'};
                                     for (int i = start; i < end; i++) {
                                         if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                             return "";
                                         }
                                     }
                                     return null;
                                 }
                             };
                             psenmID.setFilters(new InputFilter[]{filterpse});
                             pseactivitynm = psenmID.getText().toString().trim();
                             if (TextUtils.isEmpty(chldrspnse)) {
                                 psenmID.setError("Please enter pse name");
                                 psenmID.requestFocus();
                                 //return;
                             }

                             InputFilter filterpse1 = new InputFilter() {
                                 public CharSequence filter(CharSequence source, int start, int end,
                                                            Spanned dest, int dstart, int dend) {
                                     char[] chars = {'\'', '"'};
                                     for (int i = start; i < end; i++) {
                                         if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                             return "";
                                         }
                                     }
                                     return null;
                                 }
                             };
                             psenmID.setFilters(new InputFilter[]{filterpse1});
                             childenrolled = NenrolledchildrenId.getText().toString().trim();
                             if (TextUtils.isEmpty(childenrolled)) {
                                 NenrolledchildrenId.setError("Please enter");
                                 NenrolledchildrenId.requestFocus();
                                 return;
                             }
                             InputFilter filterpse2 = new InputFilter() {
                                 public CharSequence filter(CharSequence source, int start, int end,
                                                            Spanned dest, int dstart, int dend) {
                                     char[] chars = {'\'', '"'};
                                     for (int i = start; i < end; i++) {
                                         if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                             return "";
                                         }
                                     }
                                     return null;
                                 }
                             };
                             psenmID.setFilters(new InputFilter[]{filterpse2});
                             childfoundtodayy = NchildfoundId.getText().toString().trim();
                             if (TextUtils.isEmpty(childfoundtodayy)) {
                                 NchildfoundId.setError("Please enter");
                                 NchildfoundId.requestFocus();
                                 return;
                             }
                             if (NNYparticipentpscId.isChecked() || NNNparticipentpscId.isChecked()){
                                 if (NYifassenmentId.isChecked() || NNifassenmentId.isChecked()){
                                     InputFilter filterpse3 = new InputFilter() {
                                         public CharSequence filter(CharSequence source, int start, int end,
                                                                    Spanned dest, int dstart, int dend) {
                                             char[] chars = {'\'', '"'};
                                             for (int i = start; i < end; i++) {
                                                 if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                                     return "";
                                                 }
                                             }
                                             return null;
                                         }
                                     };
                                     NGvCID.setFilters(new InputFilter[]{filterpse3});
                                     inspectrcmnt = NGvCID.getText().toString().trim();
                                     if (TextUtils.isEmpty(inspectrcmnt)) {
                                         NGvCID.setError("Please Comment");
                                         NGvCID.requestFocus();
                                         return;
                                     }
                                     if (NYifassenmentId.isChecked()){
                                         if (NInuseId.isChecked() || NNotinuseId.isChecked()){
                                             childfoundtodayy = NchildfoundId.getText().toString();
                                             childenrolledd = Integer.parseInt(childenrolled);
                                             childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                             //Log.e("childenrolled", childfoundtodayy);
                                             if (childenrolledd>=childfoundtodayyy ){
                                                 NchildfoundId.setText(NchildfoundId.getText().toString());
                                                 Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                 String val = "00";
                                                 update_shishu_aloy_activity(val);
                                             }
                                             else {
                                                 NchildfoundId.setText("");
                                                 Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                 String title = "Message Box";
                                                 String msg = "NO VALID DATA";
                                                 createDialog(title,msg);
                                             }

                                         }
                                     }
                                     if (NNifassenmentId.isChecked()){
                                         childfoundtodayy = NchildfoundId.getText().toString();
                                         childenrolledd = Integer.parseInt(childenrolled);
                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                         //Log.e("childenrolled", childfoundtodayy);
                                         if (childenrolledd>=childfoundtodayyy ){
                                             NchildfoundId.setText(NchildfoundId.getText().toString());
                                             Toast.makeText(getApplicationContext(), "Valid Data", Toast.LENGTH_SHORT).show();
                                             String val = "01";
                                             update_shishu_aloy_activity(val);

                                         }
                                         else {
                                             NchildfoundId.setText("");
                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                             String title = "Message Box";
                                             String msg = "NO VALID DATA";
                                             createDialog(title,msg);
                                         }
                                     }

                                 }
                                 else {
                                     Toast.makeText(ShishuAloyActivity.this, "SELECT IF ASSESSMENT CARD IS AVAILABLE", Toast.LENGTH_SHORT).show();

                                     String title = "Message Box";
                                     String msg = "SELECT IF ASSESSMENT CARD IS AVAILABLE";
                                     createDialog(title,msg);
                                 }
                             }
                             else {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECT CHILDREN ARE ACTIVITY AND PARTICIPATION IN PSC ACTIVITY", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT CHILDREN ARE ACTIVITY AND PARTICIPATION IN PSC ACTIVITY";
                                 createDialog(title,msg);
                             }

                         }
                         else {
                             Toast.makeText(ShishuAloyActivity.this, "SELECT PSE ACTIVITY FOUND TODAY", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT PSE ACTIVITY FOUND TODAY";
                             createDialog(title,msg);
                         }
                     }
                     if (sishualoy.equals("n")){
                         if (NYifassenmentId.isChecked()){
                             if (NInuseId.isChecked() || NNotinuseId.isChecked()){

                             }
                             else {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECT IF ASSESMENT IN USE OR NOT USE", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT IF ASSESMENT IN USE OR NOT USE";
                                 createDialog(title,msg);

                             }
                         }

                     }

                 }
                 else {
                     Toast.makeText(ShishuAloyActivity.this, "SELECT SHISHU ALOY YES OR NO", Toast.LENGTH_SHORT).show();
                     String title = "Message Box";
                     String msg = "SELECT SHISHU ALOY YES OR NO";
                     createDialog(title,msg);
                 }




                 ////////////////////////////////////shualoy no////////////////////////////////

             }
    private void update_shishu_aloy_activity(final String val){
        Log.e("DATAVALUESANE",val);
        FITORECCCHECK = chart_according+","+labelling+","+rules+","+routine+","+calender+","+childrenname+","+childrenfolder+","+displaywork+","+themebaseworld;
        eccobserdate =  TdateId.getText().toString();
        ecckitdate =  FdateId.getText().toString();
//        InputFilter filter4 = new InputFilter() {
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
//        GvCID.setFilters(new InputFilter[] { filter4 });
//        inspectrcmnt = GvCID.getText().toString().trim();
//        if (TextUtils.isEmpty(inspectrcmnt)) {
//            inspectrcmnt = GvCID.getText().toString().trim();
//            GvCID.setError("Please enter Give Comment");
//            GvCID.requestFocus();
//            return;
//        }

                Calendar cc = Calendar.getInstance();
                System.out.println("Current time => " + cc.getTime());
                SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
                curDate = df3.format(cc.getTime());
                Calendar ccc = Calendar.getInstance();
                SimpleDateFormat time = new SimpleDateFormat("HH:mm");
                curTime = time.format(ccc.getTime());
                User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
                userID = String.valueOf(user.getUserID());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SHISHUALOY,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("SHISHUALOY"," "+response);
                                try {

                                    JSONArray array =new JSONArray(response);
                                    Log.e("SHISHUALOYOBJJ"," "+array);
                                    for (int i=0; i<array.length(); i++){
                                        JSONObject object = array.getJSONObject(i);
                                        String message = object.getString("message");
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                        helper = new DatabaseHelper(getApplicationContext());
                                        SQLiteDatabase dbb = helper.getReadableDatabase();
                                        dbb.execSQL("UPDATE insflag SET shishualoy='1' WHERE allinspactionid=" +insid);
                                        if (sishualoy.equals("y")) {
//                                            pseactvfound = "NNA";
//                                            pseactivitynm ="NNA";
//                                            chldparticipatpse = "NNA";
//                                            syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
//                                                    valuesuting,
//                                                    eccactvtytyp,
//                                                    awcvaluestring,
//                                                    tlam,
//                                                    proprecckit,
//                                                    avaleindvsualchildactvtyrcd,
//                                                    indvsualchildactvtyrcd,
//                                                    awcdecortnfrecce,
//                                                    FITORECCCHECK,
//                                                    childenrolled,
//                                                    childfoundtodayy,
//                                                    ecceprocess,
//                                                    assesmentcard,
//                                                    assesmentcard_use,
//                                                    ecckitdate,
//                                                    eccobserdate,
//                                                    pseactvfound,
//                                                    pseactivitynm,
//                                                    chldparticipatpse,
//                                                    inspectrcmnt,
//                                                    curTime,
//                                                    SHISHUALOY_SYNCED_WITH_SERVER);

                                            if (eccrun.equals("y")){
                                                pseactvfound = "NNA";
                                                pseactivitynm ="NNA";
                                                chldparticipatpse = "NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }
                                            if (eccrun.equals("n")){
                                                valuesuting ="NNA";
                                                pseactvfound = "NNA";
                                                pseactivitynm ="NNA";
                                                chldparticipatpse = "NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }

                                            if (eccactvtytyp.equals("y")){
                                                pseactvfound = "NNA";
                                                pseactivitynm ="NNA";
                                                chldparticipatpse = "NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }

                                            if (eccactvtytyp.equals("n")){
                                                awcvaluestring ="NNA";
                                                pseactvfound = "NNA";
                                                pseactivitynm ="NNA";
                                                chldparticipatpse = "NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }
//                                            if (awcdecortnfrecce.equals('y')){
//                                                pseactvfound = "NNA";
//                                                pseactivitynm ="NNA";
//                                                chldparticipatpse = "NNA";
//                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
//                                                        awcvaluestring,
//                                                        tlam,
//                                                        proprecckit,
//                                                        avaleindvsualchildactvtyrcd,
//                                                        indvsualchildactvtyrcd,
//                                                        awcdecortnfrecce,
//                                                        FITORECCCHECK,
//                                                        childenrolled,
//                                                        childfoundtodayy,
//                                                        ecceprocess,
//                                                        assesmentcard,
//                                                        assesmentcard_use,
//                                                        ecckitdate,
//                                                        eccobserdate,
//                                                        pseactvfound,
//                                                        pseactivitynm,
//                                                        chldparticipatpse,
//                                                        inspectrcmnt,
//                                                        curTime,
//                                                        SHISHUALOY_SYNCED_WITH_SERVER);
//                                            }
//                                            if (awcdecortnfrecce.equals('n')){
//                                                pseactvfound = "NNA";
//                                                pseactivitynm ="NNA";
//                                                chldparticipatpse = "NNA";
//                                                //FITORECCCHECK= "0,0,0,0,0,0,0,0,0";
//                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
//                                                        awcvaluestring,
//                                                        tlam,
//                                                        proprecckit,
//                                                        avaleindvsualchildactvtyrcd,
//                                                        indvsualchildactvtyrcd,
//                                                        awcdecortnfrecce,
//                                                        FITORECCCHECK,
//                                                        childenrolled,
//                                                        childfoundtodayy,
//                                                        ecceprocess,
//                                                        assesmentcard,
//                                                        assesmentcard_use,
//                                                        ecckitdate,
//                                                        eccobserdate,
//                                                        pseactvfound,
//                                                        pseactivitynm,
//                                                        chldparticipatpse,
//                                                        inspectrcmnt,
//                                                        curTime,
//                                                        SHISHUALOY_SYNCED_WITH_SERVER);
//                                            }

                                            if (assesmentcard.equals("y")){
                                                pseactvfound = "NNA";
                                                pseactivitynm ="NNA";
                                                chldparticipatpse = "NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }
                                            if (assesmentcard.equals("n")){
                                                pseactvfound = "NNA";
                                                pseactivitynm ="NNA";
                                                chldparticipatpse = "NNA";
                                                assesmentcard_use = "NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }

                                        }
                                        else if (sishualoy.equals("n")) {
//                                            cornercgnitv = "NNA";
//                                            bookcorner ="NNA";
//                                            drawcornr = "NNA";
//                                            playcorners = "NNA";
//                                            eccrun = "NA";
//                                            valuesuting ="NA";
//                                            eccactvtytyp ="NA";
//                                            awcvaluestring ="NAA";
//                                            tlam = "NNA";
//                                            proprecckit ="NNA";
//                                            avaleindvsualchildactvtyrcd = "NNA";
//                                            indvsualchildactvtyrcd = "NNA";
//                                            awcdecortnfrecce = "NNA";
//                                            ecckitdate ="NNA";
//                                            eccobserdate ="NNA";
//                                            ecceprocess ="NNA";
//                                            syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
//                                                    valuesuting,
//                                                    eccactvtytyp,
//                                                    awcvaluestring,
//                                                    tlam,
//                                                    proprecckit,
//                                                    avaleindvsualchildactvtyrcd,
//                                                    indvsualchildactvtyrcd,
//                                                    awcdecortnfrecce,
//                                                    FITORECCCHECK,
//                                                    childenrolled,
//                                                    childfoundtodayy,
//                                                    ecceprocess,
//                                                    assesmentcard,
//                                                    assesmentcard_use,
//                                                    ecckitdate,
//                                                    eccobserdate,
//                                                    pseactvfound,
//                                                    pseactivitynm,
//                                                    chldparticipatpse,
//                                                    inspectrcmnt,
//                                                    curTime,
//                                                    SHISHUALOY_SYNCED_WITH_SERVER);


                                            if (assesmentcard.equals("y")){
                                                cornercgnitv = "NNA";
                                                bookcorner ="NNA";
                                                drawcornr = "NNA";
                                                playcorners = "NNA";
                                                eccrun = "NA";
                                                valuesuting ="NA";
                                                eccactvtytyp ="NA";
                                                awcvaluestring ="NAA";
                                                tlam = "NNA";
                                                proprecckit ="NNA";
                                                avaleindvsualchildactvtyrcd = "NNA";
                                                indvsualchildactvtyrcd = "NNA";
                                                awcdecortnfrecce = "NNA";
                                                ecckitdate ="NNA";
                                                eccobserdate ="NNA";
                                                ecceprocess ="NNA";
                                                wheterawwriting ="NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                        valuesuting,
                                                        eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }


                                            if (assesmentcard.equals("n")){
                                                cornercgnitv = "NNA";
                                                bookcorner ="NNA";
                                                drawcornr = "NNA";
                                                playcorners = "NNA";
                                                eccrun = "NA";
                                                valuesuting ="NA";
                                                eccactvtytyp ="NA";
                                                awcvaluestring ="NAA";
                                                tlam = "NNA";
                                                proprecckit ="NNA";
                                                avaleindvsualchildactvtyrcd = "NNA";
                                                indvsualchildactvtyrcd = "NNA";
                                                awcdecortnfrecce = "NNA";
                                                ecckitdate ="NNA";
                                                eccobserdate ="NNA";
                                                ecceprocess ="NNA";
                                                assesmentcard_use = "NNA";
                                                wheterawwriting ="NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                        valuesuting,
                                                        eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }
                                            if (pseactvfound.equals("y")){
                                                cornercgnitv = "NNA";
                                                bookcorner ="NNA";
                                                drawcornr = "NNA";
                                                playcorners = "NNA";
                                                eccrun = "NA";
                                                valuesuting ="NA";
                                                eccactvtytyp ="NA";
                                                awcvaluestring ="NAA";
                                                tlam = "NNA";
                                                proprecckit ="NNA";
                                                avaleindvsualchildactvtyrcd = "NNA";
                                                indvsualchildactvtyrcd = "NNA";
                                                awcdecortnfrecce = "NNA";
                                                ecckitdate ="NNA";
                                                eccobserdate ="NNA";
                                                ecceprocess ="NNA";
                                                wheterawwriting ="NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                        valuesuting,
                                                        eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }

                                            if (pseactvfound.equals("n")){
                                                cornercgnitv = "NNA";
                                                bookcorner ="NNA";
                                                drawcornr = "NNA";
                                                playcorners = "NNA";
                                                eccrun = "NA";
                                                valuesuting ="NA";
                                                eccactvtytyp ="NA";
                                                awcvaluestring ="NAA";
                                                tlam = "NNA";
                                                proprecckit ="NNA";
                                                avaleindvsualchildactvtyrcd = "NNA";
                                                indvsualchildactvtyrcd = "NNA";
                                                awcdecortnfrecce = "NNA";
                                                ecckitdate ="NNA";
                                                eccobserdate ="NNA";
                                                ecceprocess ="NNA";
                                                pseactivitynm = "NNA";
                                                wheterawwriting ="NNA";
                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                        valuesuting,
                                                        eccactvtytyp,
                                                        awcvaluestring,
                                                        tlam,
                                                        proprecckit,
                                                        avaleindvsualchildactvtyrcd,
                                                        indvsualchildactvtyrcd,
                                                        awcdecortnfrecce,
                                                        FITORECCCHECK,
                                                        childenrolled,
                                                        childfoundtodayy,
                                                        ecceprocess,
                                                        assesmentcard,
                                                        assesmentcard_use,
                                                        ecckitdate,
                                                        eccobserdate,
                                                        pseactvfound,
                                                        pseactivitynm,
                                                        chldparticipatpse,
                                                        wheterawwriting,
                                                        inspectrcmnt,
                                                        curTime,
                                                        SHISHUALOY_SYNCED_WITH_SERVER);
                                            }
                                        }
                                        else {

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
                                dbb.execSQL("UPDATE insflag SET shishualoy='1' WHERE allinspactionid=" +insid);
                                if (sishualoy.equals("y")) {
                                    if (eccrun.equals("y")){
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }
                                    if (eccrun.equals("n")){
                                        valuesuting ="NNA";
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }

                                    if (eccactvtytyp.equals("y")){
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }

                                    if (eccactvtytyp.equals("n")){
                                        awcvaluestring ="NNA";
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }
//                                    if (awcdecortnfrecce.equals('y')){
//                                        pseactvfound = "NNA";
//                                        pseactivitynm ="NNA";
//                                        chldparticipatpse = "NNA";
//                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
//                                                awcvaluestring,
//                                                tlam,
//                                                proprecckit,
//                                                avaleindvsualchildactvtyrcd,
//                                                indvsualchildactvtyrcd,
//                                                awcdecortnfrecce,
//                                                FITORECCCHECK,
//                                                childenrolled,
//                                                childfoundtodayy,
//                                                ecceprocess,
//                                                assesmentcard,
//                                                assesmentcard_use,
//                                                ecckitdate,
//                                                eccobserdate,
//                                                pseactvfound,
//                                                pseactivitynm,
//                                                chldparticipatpse,
//                                                inspectrcmnt,
//                                                curTime,
//                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
//                                    }
//                                    if (awcdecortnfrecce.equals('n')){
//                                        pseactvfound = "NNA";
//                                        pseactivitynm ="NNA";
//                                        chldparticipatpse = "NNA";
//                                       // String FITORECCCHECK= "0,0,0,0,0,0,0,0,0";
//                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
//                                                awcvaluestring,
//                                                tlam,
//                                                proprecckit,
//                                                avaleindvsualchildactvtyrcd,
//                                                indvsualchildactvtyrcd,
//                                                awcdecortnfrecce,
//                                                FITORECCCHECK,
//                                                childenrolled,
//                                                childfoundtodayy,
//                                                ecceprocess,
//                                                assesmentcard,
//                                                assesmentcard_use,
//                                                ecckitdate,
//                                                eccobserdate,
//                                                pseactvfound,
//                                                pseactivitynm,
//                                                chldparticipatpse,
//                                                inspectrcmnt,
//                                                curTime,
//                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
//                                    }

                                    if (assesmentcard.equals("y")){
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }
                                    if (assesmentcard.equals("n")){
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        assesmentcard_use = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }

                                }
                                /////////////////////////////////////
                               else if (sishualoy.equals("n")) {
                                   ////////////////////////////////////////////////////////
                                    if (assesmentcard.equals("y")){
                                        cornercgnitv = "NNA";
                                        bookcorner ="NNA";
                                        drawcornr = "NNA";
                                        playcorners = "NNA";
                                        eccrun = "NA";
                                        valuesuting ="NA";
                                        eccactvtytyp ="NA";
                                        awcvaluestring ="NAA";
                                        tlam = "NNA";
                                        proprecckit ="NNA";
                                        avaleindvsualchildactvtyrcd = "NNA";
                                        indvsualchildactvtyrcd = "NNA";
                                        awcdecortnfrecce = "NNA";
                                        ecckitdate ="NNA";
                                        eccobserdate ="NNA";
                                        ecceprocess ="NNA";
                                        wheterawwriting = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                valuesuting,
                                                eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }


                                    if (assesmentcard.equals("n")){
                                        cornercgnitv = "NNA";
                                        bookcorner ="NNA";
                                        drawcornr = "NNA";
                                        playcorners = "NNA";
                                        eccrun = "NA";
                                        valuesuting ="NA";
                                        eccactvtytyp ="NA";
                                        awcvaluestring ="NAA";
                                        tlam = "NNA";
                                        proprecckit ="NNA";
                                        avaleindvsualchildactvtyrcd = "NNA";
                                        indvsualchildactvtyrcd = "NNA";
                                        awcdecortnfrecce = "NNA";
                                        ecckitdate ="NNA";
                                        eccobserdate ="NNA";
                                        ecceprocess ="NNA";
                                        assesmentcard_use = "NNA";
                                        wheterawwriting = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                valuesuting,
                                                eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }
                                    if (pseactvfound.equals("y")){
                                        cornercgnitv = "NNA";
                                        bookcorner ="NNA";
                                        drawcornr = "NNA";
                                        playcorners = "NNA";
                                        eccrun = "NA";
                                        valuesuting ="NA";
                                        eccactvtytyp ="NA";
                                        awcvaluestring ="NAA";
                                        tlam = "NNA";
                                        proprecckit ="NNA";
                                        avaleindvsualchildactvtyrcd = "NNA";
                                        indvsualchildactvtyrcd = "NNA";
                                        awcdecortnfrecce = "NNA";
                                        ecckitdate ="NNA";
                                        eccobserdate ="NNA";
                                        ecceprocess ="NNA";
                                        wheterawwriting = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                valuesuting,
                                                eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }

                                    if (pseactvfound.equals("n")){
                                        cornercgnitv = "NNA";
                                        bookcorner ="NNA";
                                        drawcornr = "NNA";
                                        playcorners = "NNA";
                                        eccrun = "NA";
                                        valuesuting ="NA";
                                        eccactvtytyp ="NA";
                                        awcvaluestring ="NAA";
                                        tlam = "NNA";
                                        proprecckit ="NNA";
                                        avaleindvsualchildactvtyrcd = "NNA";
                                        indvsualchildactvtyrcd = "NNA";
                                        awcdecortnfrecce = "NNA";
                                        ecckitdate ="NNA";
                                        eccobserdate ="NNA";
                                        ecceprocess ="NNA";
                                        pseactivitynm = "NNA";
                                        wheterawwriting = "NNA";
                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                valuesuting,
                                                eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                                    }


                                    ///////////////////////////////////////////////////////////
                                }
                                else {

                                }
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
//                        params.put("corner_cgnitv",cornercgnitv);
//                        params.put("book_corner",bookcorner);
//                        params.put("draw_cornr",drawcornr);
//                        params.put("play_corner",playcorners);
//                        params.put("ecc_run",eccrun);
//                        params.put("ecc_routn_theme", valuesuting);
                        /////ecce activity at awc found during visit edittext
//                        params.put("ecc_actvty_typ",eccactvtytypRep);
                        //params.put("ecc_actvty_typ",eccactvtytyp);
                       // params.put("ecc_activty_found",awcvaluestring);
//                        params.put("aww_tlm",tlam);
                       // params.put("propr_ecc_kit",proprecckit);
//                        params.put("indvsual_child_artwork" ,avaleindvsualchildactvtyrcd);
//                        params.put("indvsual_child_actvty_rcd",indvsualchildactvtyrcd);
//                        params.put("awc_decortn_fr_ecce",awcdecortnfrecce);
//                        params.put("aww_routin_follow",FITORECCCHECK);
//                       // params.put("",wheterawwriting);
//                        params.put("tot_chld_enroll",childenrolled);
//                        params.put("tot_chld_found_today",childfoundtodayy);
//                        params.put("chld_participat_ecc",ecceprocess);
//                        params.put("assmnt_card",assesmentcard);
//                        params.put("assmnt_card_st",assesmentcard_use);
//                        params.put("last_ecc_kit_rcv_dt",ecckitdate);
//                        params.put("last_ecc_day_observ",eccobserdate);
                        if (sishualoy.equals("y")){
                            pseactvfound = "NNA";
                            pseactivitynm ="NNA";
                            chldparticipatpse = "NNA";
                            params.put("inspctn_id",insid);
                            params.put("sishu_aloy",sishualoy);
                            params.put("corner_cgnitv",cornercgnitv);
                            params.put("book_corner",bookcorner);
                            params.put("draw_cornr",drawcornr);
                            params.put("play_corner",playcorners);
                            params.put("ecc_run",eccrun);
                            if (eccrun.equals("y")){
                                params.put("ecc_routn_theme", valuesuting);
                            }
                            if (eccrun.equals("n")){
                                valuesuting ="NNA";
                                params.put("ecc_routn_theme", valuesuting);
                            }
                            params.put("ecc_actvty_typ",eccactvtytyp);
                            if (eccactvtytyp.equals("y")){
                                params.put("ecc_activty_found",awcvaluestring);
                            }
                            if (eccactvtytyp.equals("n")){
                                awcvaluestring ="NNA";
                                params.put("ecc_activty_found",awcvaluestring);
                            }
                            params.put("aww_tlm",tlam);
                            params.put("propr_ecc_kit",proprecckit);
                            params.put("indvsual_child_artwork" ,avaleindvsualchildactvtyrcd);
                            params.put("indvsual_child_actvty_rcd",indvsualchildactvtyrcd);
                            params.put("awc_decortn_fr_ecce",awcdecortnfrecce);
                            params.put("aww_routin_follow",FITORECCCHECK);
//                            if (awcdecortnfrecce.equals('y')){
//
//                                Log.e("FITORECCCHECK",FITORECCCHECK);
//                            }
//                            if (awcdecortnfrecce.equals("n")){
//                              //  String FITORECCCHECK= "0,0,0,0,0,0,0,0,0";
//                                params.put("aww_routin_follow",FITORECCCHECK);
//                                Log.e("FITORECCCHECK",FITORECCCHECK);
//                            }

                            // params.put("",wheterawwriting);
                            params.put("tot_chld_enroll",childenrolled);
                            params.put("tot_chld_found_today",childfoundtodayy);
                            params.put("chld_participat_ecc",ecceprocess);
                            params.put("assmnt_card",assesmentcard);
                            if (assesmentcard.equals("y")){
                                params.put("assmnt_card_st",assesmentcard_use);
                            }
                            if (assesmentcard.equals("n")){
                                assesmentcard_use = "NNA";
                                params.put("assmnt_card_st",assesmentcard_use);
                            }

                            params.put("last_ecc_kit_rcv_dt",ecckitdate);
                            params.put("last_ecc_day_observ",eccobserdate);
                            params.put("pse_actv_found",pseactvfound);
                            params.put("pse_activity_nm",pseactivitynm);
                            params.put("chld_participat_pse",chldparticipatpse);
                            params.put("aww_follow_routine",wheterawwriting);
                            params.put("inspectr_cmnt",inspectrcmnt);
                            params.put("ins_time",curTime);
                        }
                        if (sishualoy.equals("n")){
                            cornercgnitv = "NNA";
                            bookcorner ="NNA";
                            drawcornr = "NNA";
                            playcorners = "NNA";
                            eccrun = "NA";
                            valuesuting ="NA";
                            eccactvtytyp ="NA";
                            awcvaluestring ="NAA";
                            tlam = "NNA";
                            proprecckit ="NNA";
                            avaleindvsualchildactvtyrcd = "NNA";
                            indvsualchildactvtyrcd = "NNA";
                            awcdecortnfrecce = "NNA";
                            ecckitdate ="NNA";
                            eccobserdate ="NNA";
                            ecceprocess ="NNA";
                            wheterawwriting = "NNA";
                            ///////////////yes fild/////////////////
                            params.put("inspctn_id",insid);
                            params.put("sishu_aloy",sishualoy);
                            params.put("corner_cgnitv",cornercgnitv);
                            params.put("book_corner",bookcorner);
                            params.put("draw_cornr",drawcornr);
                            params.put("play_corner",playcorners);
                            params.put("ecc_run",eccrun);
                            params.put("ecc_routn_theme", valuesuting);
                            params.put("ecc_actvty_typ",eccactvtytyp);
                            params.put("ecc_activty_found",awcvaluestring);
                            params.put("aww_tlm",tlam);
                            params.put("propr_ecc_kit",proprecckit);
                            params.put("indvsual_child_artwork" ,avaleindvsualchildactvtyrcd);
                            params.put("indvsual_child_actvty_rcd",indvsualchildactvtyrcd);
                            params.put("awc_decortn_fr_ecce",awcdecortnfrecce);
                            params.put("aww_routin_follow",FITORECCCHECK);
                            params.put("tot_chld_enroll",childenrolled);
                            params.put("tot_chld_found_today",childfoundtodayy);
                            params.put("chld_participat_ecc",ecceprocess);
                            params.put("assmnt_card",assesmentcard);
                            if (assesmentcard.equals("y")){
                                params.put("assmnt_card_st",assesmentcard_use);
                            }
                            if (assesmentcard.equals("n")){
                                assesmentcard_use = "NNA";
                                params.put("assmnt_card_st",assesmentcard_use);
                            }

                            params.put("last_ecc_kit_rcv_dt",ecckitdate);
                            params.put("last_ecc_day_observ",eccobserdate);
                            params.put("pse_actv_found",pseactvfound);
                            if (pseactvfound.equals("y")){
                                params.put("pse_activity_nm",pseactivitynm);
                            }
                            if (pseactvfound.equals("n")){
                                pseactivitynm = "NNA";
                                params.put("pse_activity_nm",pseactivitynm);
                            }
                            params.put("chld_participat_pse",chldparticipatpse);
                            params.put("aww_follow_routine",wheterawwriting);
                            params.put("inspectr_cmnt",inspectrcmnt);
                            params.put("ins_time",curTime);


                        }
                        //params.put("tot_chld_prsnt",totchldprsnt);
                       //params.put("chld_rspnse",chldrspnse);
                     //params.put("inspectr_cmnt",inspectrcmnt);
                    // inspctn_id 107173129122019 sishu_aloy y corner_cgnitv good book_corner average draw_cornr average play_corner poorecc_run y ecc_routn_theme 2ecc_actvty_typ nullecc_activty_found 4aww_tlm ypropr_ecc_kit pindvsual_child_artwork yindvsual_child_actvty_rcd yawc_decortn_fr_ecce yaww_routin_follow 1,2,3,4,5,6,7,8,9tot_chld_enroll 1tot_chld_found_today 2chld_participat_ecc yassmnt_card yassmnt_card_st iulast_ecc_kit_rcv_dt 26-12-2019last_ecc_day_observ 29-12-2019pse_actv_found nullpse_activity_nm nullchld_participat_pse nullchld_rspnse Response childreninspectr_cmnt hiins_time 11:40
                        Log.e("SHISHULAYEDIT","inspctn_id"+" "+"  "+insid+" "+"  "+
                               "sishu_aloy"+" "+"  "+sishualoy+" "+" "+
                                "corner_cgnitv"+" "+cornercgnitv+" "+" "+
                               "book_corner"+" "+" "+bookcorner+" "+" "+
                                "draw_cornr"+"  "+drawcornr+" "+
                                "play_corner"+"   "+playcorners+
                                "ecc_run"+" "+eccrun+" "
                                +"ecc_routn_theme"+"  "+valuesuting+
                                "ecc_actvty_typ"+" "+"  "+" "+eccactvtytyp+
                                "ecc_activty_found"+"   "+awcvaluestring
                                +"aww_tlm"+"  "+tlam
                                +"propr_ecc_kit"+"   "+proprecckit
                                +"indvsual_child_artwork"+"   "+avaleindvsualchildactvtyrcd
                                +"indvsual_child_actvty_rcd"+"   "+indvsualchildactvtyrcd
                                +"awc_decortn_fr_ecce"+"   "+awcdecortnfrecce
                                +"aww_routin_follow"+"   "+FITORECCCHECK
                                +"tot_chld_enroll"+"   "+childenrolled
                                +"tot_chld_found_today"+"   "+childfoundtodayy
                                +"chld_participat_ecc"+"   "+ecceprocess
                                +"assmnt_card"+"   "+assesmentcard
                                +"assmnt_card_st"+" "+assesmentcard_use
                                +"last_ecc_kit_rcv_dt"+"  "+ecckitdate
                                +"last_ecc_day_observ"+"   "+eccobserdate
                                +"pse_actv_found"+"   "+pseactvfound
                                +"pse_activity_nm"+"   "+pseactivitynm
                                +"chld_participat_pse"+"   "+chldparticipatpse
                                +"aww_follow_routine"+"   "+wheterawwriting
                                +"inspectr_cmnt"+"    "+inspectrcmnt
                                +"ins_time"+" "+curTime
                               );
                        return params;
                    }
                };
                //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
                stringRequest.setShouldCache(false);
                volleySingleton.addToRequestQueue(stringRequest);
              }

            private void syncShishualoysaveDatabase(String insid,
                                                    String sishualoy,
                                                    String cornercgnitv,
                                                    String bookcorner,
                                                    String drawcornr,
                                                    String playcorners,
                                                    String eccrun,
                                                    String valuesuting,
                                                    String eccactvtytyp,
                                                    String awcvaluestring,
                                                    String tlam,
                                                    String proprecckit,
                                                    String avaleindvsualchildactvtyrcd,
                                                    String indvsualchildactvtyrcd,
                                                    String awcdecortnfrecce,
                                                    String fitorecccheck,
                                                    String childenrolled,
                                                    String childfoundtodayy,
                                                    String ecceprocess,
                                                    String assesmentcard,
                                                    String assesmentcarduse,
                                                    String ecckitdate,
                                                    String eccobserdate,
                                                    String pseactvfound,
                                                    String pseactivitynm,
                                                    String chldparticipatpse,
                                                    String wheterawwriting,
                                                    String inspectrcmnt,
                                                    String curTime,
                                                    int shishualoystatus){

             if (shishualoy.equals("0")){
                   helper.SHISHUALOYINSERT(
                            insid,
                            sishualoy,
                            cornercgnitv,
                            bookcorner,
                            drawcornr,
                            playcorners,
                            eccrun,
                            valuesuting,
                            eccactvtytyp,
                            awcvaluestring,
                            tlam,
                            proprecckit,
                            avaleindvsualchildactvtyrcd,
                            indvsualchildactvtyrcd,
                            awcdecortnfrecce,
                            fitorecccheck,
                            childenrolled,
                            childfoundtodayy,
                            ecceprocess,
                            assesmentcard,
                            assesmentcarduse,
                            ecckitdate,
                            eccobserdate,
                            pseactvfound,
                            pseactivitynm,
                            chldparticipatpse,
                            wheterawwriting,
                            inspectrcmnt,
                            curTime,
                            shishualoystatus);
                 Log.e("SHISHU","HI");
                }
                else {
                    helper.SHISHUALOYUPDATE(dbid,
                            insid,
                            sishualoy,
                            cornercgnitv,
                            bookcorner,
                            drawcornr,
                            playcorners,
                            eccrun,
                            valuesuting,
                            eccactvtytyp,
                            awcvaluestring,
                            tlam,
                            proprecckit,
                            avaleindvsualchildactvtyrcd,
                            indvsualchildactvtyrcd,
                            awcdecortnfrecce,
                            fitorecccheck,
                            childenrolled,
                            childfoundtodayy,
                            ecceprocess,
                            assesmentcard,
                            assesmentcarduse,
                            ecckitdate,
                            eccobserdate,
                            pseactvfound,
                            pseactivitynm,
                            chldparticipatpse,
                            wheterawwriting,
                            inspectrcmnt,
                            curTime,
                            shishualoystatus);
                 Log.e("SHISHU","HI1");
                }
                Log.e("SHISHU"," "+dbid+" "
                        +insid+" "
                        +sishualoy+" "
                        +cornercgnitv+" "
                        +bookcorner+" "
                        +drawcornr+" "
                        +playcorners+" "
                        +eccrun+" "
                        +valuesuting+" "
                        +eccactvtytyp+" "
                        +awcvaluestring+" "
                        +tlam+" "
                        +proprecckit+ " "
                        +avaleindvsualchildactvtyrcd+" "
                        +indvsualchildactvtyrcd+" "
                        +awcdecortnfrecce+" "
                        +fitorecccheck+" "
                        +childenrolled+" "
                        +childfoundtodayy
                        +ecceprocess+" "
                        +assesmentcard+" "
                        +assesmentcarduse+" "
                        +ecckitdate+" "
                        +eccobserdate+" "
                        +pseactvfound+" "
                        +pseactivitynm+" "
                        +chldparticipatpse+" "
                        +wheterawwriting+" "
                        +inspectrcmnt+" "
                         +curTime+" "+
                         +shishualoystatus+" ");

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
//          Intent intent = new Intent(ShishuAloyActivity.this,SNPServedActivity.class);
//          Bundle bundle = new Bundle();
//          bundle.putString("kitchen",kitchen);
//          bundle.putString("adqutspacepse",adqutspacepse);
//          bundle.putString("electric",electric);
//          bundle.putString("water",water);
//          bundle.putString("foodspace",foodspace);
//          bundle.putString("toilet",toilet);
//          bundle.putString("awcscode",awcscode);
//          bundle.putString("awcsname",awcsname);
//          bundle.putString("dbdistid",dbdistid);
//          bundle.putString("dbprojectid",dbprojectid);
//          bundle.putString("dbsectorid",dbsectorid);
//          bundle.putString("dbcenterid",dbcenterid);
//          bundle.putString("projectnamedb",projectnamedb);
//          bundle.putString("districnamedb",districnamedb);
//          bundle.putString("sectorrnamedb",sectorrnamedb);
//          bundle.putString("centernamedb",centernamedb);
//          bundle.putString("insid",insid);
//          bundle.putString("planid",planid);
//          bundle.putString("dbid",dbid);
//          intent.putExtras(bundle);
//          startActivity(intent);
           Intent intent = new Intent(ShishuAloyActivity.this, SNPServedActivity.class);
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
             if (YsishualoyID.isChecked() || NsishualoyID.isChecked()) {
                 if (sishualoy.equals("y")){
                     if (bccGoodID.isChecked() || bccAverage.isChecked() || bccPoor.isChecked() || bccNA.isChecked()) {
                         if (bcgoodID.isChecked() || bcaverageID.isChecked() || bcpoorID.isChecked() || bcnaID.isChecked()) {
                             if (dcgoodID.isChecked() || dcaverageID.isChecked() || dcPoorID.isChecked() || dcnaID.isChecked()) {
                                 if (pcgoodID.isChecked() || pcaverageID.isChecked() || pcpoorID.isChecked() || pcnaID.isChecked()) {
                                     if (YwerunID.isChecked() || NwerunID.isChecked()) {
//                                                        InputFilter filter = new InputFilter() {
//                                                            public CharSequence filter(CharSequence source, int start, int end,
//                                                                                       Spanned dest, int dstart, int dend) {
//                                                                char[] chars = {'\'','"'};
//                                                                for (int i = start; i < end; i++) {
//                                                                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                                        return "";
//                                                                    }
//                                                                }
//                                                                return null;
//                                                            }
//                                                        };
//
//                                                        ecceawcfoundID.setFilters(new InputFilter[] { filter });
//                                                        eccactvtytypRep = ecceawcfoundID.getText().toString().trim();
//                                                        if (TextUtils.isEmpty(eccactvtytypRep)) {
//                                                            ecceawcfoundID.setError("Please enter Ecce Activity At Awc Found");
//                                                            ecceawcfoundID.requestFocus();
//                                                           // return;
//                                                        }


                                         if (Yawcfound.isChecked() || Nawcfound.isChecked()) {
                                             if (tlmYId.isChecked() || tlmNId.isChecked()) {
                                                 if (properlyID.isChecked() || naID.isChecked()) {
                                                     if (YartworkId.isChecked() || NartworkId.isChecked()) {
                                                         if (YciarfID.isChecked() || NciarfID.isChecked()) {
                                                             if (YawcdecceID.isChecked() || NawcdecceID.isChecked()) {
                                                                 if (YawwroutingId.isChecked() || NawwroutingId.isChecked()) {
                                                                     InputFilter filter2 = new InputFilter() {
                                                                         public CharSequence filter(CharSequence source, int start, int end,
                                                                                                    Spanned dest, int dstart, int dend) {
                                                                             char[] chars = {'\'', '"'};
                                                                             for (int i = start; i < end; i++) {
                                                                                 if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                                                                     return "";
                                                                                 }
                                                                             }
                                                                             return null;
                                                                         }
                                                                     };
                                                                     childenrolledId.setFilters(new InputFilter[]{filter2});
                                                                     childenrolled = childenrolledId.getText().toString().trim();
                                                                     if (TextUtils.isEmpty(childenrolled)) {
                                                                         childenrolledId.setError("Please enter Number");
                                                                         childenrolledId.requestFocus();
                                                                         // return;
                                                                     }
                                                                     InputFilter filter4 = new InputFilter() {
                                                                         public CharSequence filter(CharSequence source, int start, int end,
                                                                                                    Spanned dest, int dstart, int dend) {
                                                                             char[] chars = {'\'', '"'};
                                                                             for (int i = start; i < end; i++) {
                                                                                 if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                                                                     return "";
                                                                                 }
                                                                             }
                                                                             return null;
                                                                         }
                                                                     };
                                                                     childenrolledId.setFilters(new InputFilter[]{filter4});
                                                                     childfoundtodayy = childfounId.getText().toString().trim();
                                                                     if (TextUtils.isEmpty(childfoundtodayy)) {
                                                                         childfounId.setError("Please enter Number");
                                                                         childfounId.requestFocus();
                                                                         // return; ecckitdate
                                                                     }

                                                                     if (YchildrenceeeprocessId.isChecked() || NchildrenceeeprocessId.isChecked()) {
                                                                         if (YassesmentcardId.isChecked() || NassesmentcardId.isChecked()) {
                                                                             if (FdateId.getText().toString().equals("Date")) {
                                                                                 Toast.makeText(ShishuAloyActivity.this, "SELECT DATE OF LAST RECEVED OF ECCE KIT", Toast.LENGTH_SHORT).show();
                                                                                 String title = "Message Box";
                                                                                 String msg = "SELECT DATE OF LAST RECEVED OF ECCE KIT";
                                                                                 createDialog(title,msg);

                                                                             } else {
                                                                                 if (TdateId.getText().toString().equals("Date")) {
                                                                                     Toast.makeText(ShishuAloyActivity.this, "SELECT DATE OF LAST ECCE OBSERVED", Toast.LENGTH_SHORT).show();
                                                                                     String title = "Message Box";
                                                                                     String msg = "SELECT DATE OF LAST ECCE OBSERVED";
                                                                                     createDialog(title,msg);

                                                                                 } else {
                                                                                     InputFilter filter1 = new InputFilter() {
                                                                                         public CharSequence filter(CharSequence source, int start, int end,
                                                                                                                    Spanned dest, int dstart, int dend) {
                                                                                             char[] chars = {'\'', '"'};
                                                                                             for (int i = start; i < end; i++) {
                                                                                                 if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                                                                                     return "";
                                                                                                 }
                                                                                             }
                                                                                             return null;
                                                                                         }
                                                                                     };

                                                                                     GvCID.setFilters(new InputFilter[]{filter1});
                                                                                     inspectrcmnt = GvCID.getText().toString().trim();
                                                                                     if (TextUtils.isEmpty(inspectrcmnt)) {
                                                                                         GvCID.setError("Please enter Comment");
                                                                                         GvCID.requestFocus();
                                                                                         return;
                                                                                     }
//
//                                                                                                        InputFilter filter3 = new InputFilter() {
//                                                                                                            public CharSequence filter(CharSequence source, int start, int end,
//                                                                                                                                       Spanned dest, int dstart, int dend) {
//                                                                                                                char[] chars = {'\'', '"'};
//                                                                                                                for (int i = start; i < end; i++) {
//                                                                                                                    if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
//                                                                                                                        return "";
//                                                                                                                    }
//                                                                                                                }
//                                                                                                                return null;
//                                                                                                            }
//                                                                                                        };
//                                                                                                        responchildID.setFilters(new InputFilter[]{filter3});
//                                                                                                        chldrspnse = responchildID.getText().toString().trim();
//                                                                                                        if (TextUtils.isEmpty(chldrspnse)) {
//                                                                                                            responchildID.setError("Please enter Response From Children");
//                                                                                                            responchildID.requestFocus();
//                                                                                                            //return;
//                                                                                                        }
                                                                                     //YYYY
                                                                                     if (YawcdecceID.isChecked()
                                                                                             && chatId.isChecked() || labelId.isChecked() || rulesId.isChecked() || routId.isChecked()
                                                                                             || calId.isChecked()
                                                                                             || childnameId.isChecked()
                                                                                             || childrenfolderId.isChecked()
                                                                                             || displaychId.isChecked()
                                                                                             || thembasewordwallId.isChecked()
                                                                                             && YassesmentcardId.isChecked() && InuseId.isChecked()) {
                                                                                         if (YwerunID.isChecked()){
                                                                                             if (Yawcfound.isChecked()){
                                                                                                 if (routing.equals("Select Routine Theme")) {
                                                                                                 }
                                                                                                 else {
                                                                                                     if (awcfound.equals("Select ECCE Activity")) {
                                                                                                     }
                                                                                                     else {
                                                                                                         childfoundtodayy = childfounId.getText().toString();
                                                                                                         childenrolledd = Integer.parseInt(childenrolled);
                                                                                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                         Log.e("childenrolled", childfoundtodayy);
                                                                                                         if (childenrolledd>=childfoundtodayyy ){
                                                                                                             childfounId.setText(childfounId.getText().toString());
                                                                                                             Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String val = "0";
                                                                                                             update_shishu_aloy_activity1(val);
                                                                                                         }
                                                                                                         else {
                                                                                                             childfounId.setText("");
                                                                                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String title = "Message Box";
                                                                                                             String msg = "NO VALID DATA";
                                                                                                             createDialog(title,msg);
                                                                                                         }

                                                                                                     } }
                                                                                             }
                                                                                         }

                                                                                     }
                                                                                     //NNNN
                                                                                     if (NwerunID.isChecked() && Nawcfound.isChecked() && NawcdecceID.isChecked() && NassesmentcardId.isChecked()){
                                                                                         childfoundtodayy = childfounId.getText().toString();
                                                                                         childenrolledd = Integer.parseInt(childenrolled);
                                                                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                         Log.e("childenrolled", childfoundtodayy);
                                                                                         if (childenrolledd>=childfoundtodayyy ){
                                                                                             childfounId.setText(childfounId.getText().toString());
                                                                                             Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                             String val = "1";
                                                                                             update_shishu_aloy_activity1(val);
                                                                                         }
                                                                                         else {
                                                                                             childfounId.setText("");
                                                                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                             String title = "Message Box";
                                                                                             String msg = "NO VALID DATA";
                                                                                             createDialog(title,msg);
                                                                                         }

                                                                                     }
                                                                                     //YNYN
                                                                                     if (YwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                         if (YawcdecceID.isChecked() && NassesmentcardId.isChecked()){
                                                                                             if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                     || routId.isChecked() || calId.isChecked()
                                                                                                     || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                     || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                 if (YwerunID.isChecked()){
                                                                                                     if (routing.equals("Select Routine Theme")) {
                                                                                                     }
                                                                                                     else {
                                                                                                         childfoundtodayy = childfounId.getText().toString();
                                                                                                         childenrolledd = Integer.parseInt(childenrolled);
                                                                                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                         Log.e("childenrolled", childfoundtodayy);
                                                                                                         if (childenrolledd>=childfoundtodayyy ){
                                                                                                             childfounId.setText(childfounId.getText().toString());
                                                                                                             Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String val = "2";
                                                                                                             update_shishu_aloy_activity1(val);
                                                                                                         }
                                                                                                         else {
                                                                                                             childfounId.setText("");
                                                                                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String title = "Message Box";
                                                                                                             String msg = "NO VALID DATA";
                                                                                                             createDialog(title,msg);
                                                                                                         }
                                                                                                     }
                                                                                                 }

                                                                                             } } }

                                                                                     //YNYY
                                                                                     if (YwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                         if (YawcdecceID.isChecked() && YassesmentcardId.isChecked()){
                                                                                             if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                     || routId.isChecked() || calId.isChecked()
                                                                                                     || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                     || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                 if (YwerunID.isChecked()) {
                                                                                                     if (routing.equals("Select Routine Theme")) {
                                                                                                     } else {
                                                                                                         childfoundtodayy = childfounId.getText().toString();
                                                                                                         childenrolledd = Integer.parseInt(childenrolled);
                                                                                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                         Log.e("childenrolled", childfoundtodayy);
                                                                                                         if (childenrolledd>=childfoundtodayyy ){
                                                                                                             childfounId.setText(childfounId.getText().toString());
                                                                                                             Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String val = "2";
                                                                                                             update_shishu_aloy_activity1(val);
                                                                                                         }
                                                                                                         else {
                                                                                                             childfounId.setText("");
                                                                                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String title = "Message Box";
                                                                                                             String msg = "NO VALID DATA";
                                                                                                             createDialog(title,msg);
                                                                                                         }

                                                                                                     }

                                                                                                 }

                                                                                             }
                                                                                         }
                                                                                     }
                                                                                     //YNNY
                                                                                     if (YwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                         if (NawcdecceID.isChecked() && YassesmentcardId.isChecked()){
                                                                                             if (InuseId.isChecked() || NotinuseId.isChecked()){
                                                                                                 if (routing.equals("Select Routine Theme")) {
                                                                                                 }
                                                                                                 else {
                                                                                                     childfoundtodayy = childfounId.getText().toString();
                                                                                                     childenrolledd = Integer.parseInt(childenrolled);
                                                                                                     childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                     Log.e("childenrolled", childfoundtodayy);
                                                                                                     if (childenrolledd>=childfoundtodayyy ){
                                                                                                         childfounId.setText(childfounId.getText().toString());
                                                                                                         Toast.makeText(getApplicationContext(), "Valid Data", Toast.LENGTH_SHORT).show();
                                                                                                         String val = "3";
                                                                                                         update_shishu_aloy_activity1(val);
                                                                                                     }
                                                                                                     else {
                                                                                                         childfounId.setText("");
                                                                                                         Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                         String title = "Message Box";
                                                                                                         String msg = "NO VALID DATA";
                                                                                                         createDialog(title,msg);
                                                                                                     }
                                                                                                 }
                                                                                             }
                                                                                         }
                                                                                     }
                                                                                     //NNYY
                                                                                     if (NwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                         if (YawcdecceID.isChecked() && YassesmentcardId.isChecked()){
                                                                                             if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                     || routId.isChecked() || calId.isChecked()
                                                                                                     || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                     || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                 if (InuseId.isChecked() || NotinuseId.isChecked()) {
                                                                                                     childfoundtodayy = childfounId.getText().toString();
                                                                                                     childenrolledd = Integer.parseInt(childenrolled);
                                                                                                     childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                     Log.e("childenrolled", childfoundtodayy);
                                                                                                     if (childenrolledd>=childfoundtodayyy ){
                                                                                                         childfounId.setText(childfounId.getText().toString());
                                                                                                         Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                         String val = "4";
                                                                                                         update_shishu_aloy_activity1(val);
                                                                                                     }
                                                                                                     else {
                                                                                                         childfounId.setText("");
                                                                                                         Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                         String title = "Message Box";
                                                                                                         String msg = "NO VALID DATA";
                                                                                                         createDialog(title,msg);
                                                                                                     }
                                                                                                 }
                                                                                             }


                                                                                         }
                                                                                     }
                                                                                     //NYNY
                                                                                     if (NwerunID.isChecked() && Yawcfound.isChecked()){
                                                                                         if (NawcdecceID.isChecked() && YassesmentcardId.isChecked()){
                                                                                             if (InuseId.isChecked() || NotinuseId.isChecked()) {
                                                                                                 if (Yawcfound.isChecked()){
                                                                                                     if (awcfound.equals("Select ECCE Activity")) {
                                                                                                     }
                                                                                                     else {
                                                                                                         childfoundtodayy = childfounId.getText().toString();
                                                                                                         childenrolledd = Integer.parseInt(childenrolled);
                                                                                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                         Log.e("childenrolled", childfoundtodayy);
                                                                                                         if (childenrolledd>=childfoundtodayyy ){
                                                                                                             childfounId.setText(childfounId.getText().toString());
                                                                                                             Toast.makeText(getApplicationContext(), "Valid Data", Toast.LENGTH_SHORT).show();
                                                                                                             String val = "5";
                                                                                                             update_shishu_aloy_activity1(val);
                                                                                                         }
                                                                                                         else {
                                                                                                             childfounId.setText("");
                                                                                                             Toast.makeText(getApplicationContext(), "No Valid Data", Toast.LENGTH_SHORT).show();
                                                                                                         }
                                                                                                     } } } } }
                                                                                     //NYYN
                                                                                     if (NwerunID.isChecked() && Yawcfound.isChecked()){
                                                                                         if (YawcdecceID.isChecked() && NassesmentcardId.isChecked()){
                                                                                             if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                     || routId.isChecked() || calId.isChecked()
                                                                                                     || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                     || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                 if (Yawcfound.isChecked()) {
                                                                                                     if (awcfound.equals("Select ECCE Activity")) {
                                                                                                     } else {
                                                                                                         childfoundtodayy = childfounId.getText().toString();
                                                                                                         childenrolledd = Integer.parseInt(childenrolled);
                                                                                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                         Log.e("childenrolled", childfoundtodayy);
                                                                                                         if (childenrolledd>=childfoundtodayyy ){
                                                                                                             childfounId.setText(childfounId.getText().toString());
                                                                                                             Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String val = "6";
                                                                                                             update_shishu_aloy_activity1(val);
                                                                                                         }
                                                                                                         else {
                                                                                                             childfounId.setText("");
                                                                                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                             String title = "Message Box";
                                                                                                             String msg = "NO VALID DATA";
                                                                                                             createDialog(title,msg);
                                                                                                         }
                                                                                                     }
                                                                                                 }
                                                                                             }
                                                                                         }
                                                                                     }

                                                                                     //NNYN
                                                                                     if (NwerunID.isChecked() && Nawcfound.isChecked()){
                                                                                         if (YawcdecceID.isChecked() && NassesmentcardId.isChecked()){
                                                                                             if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked()
                                                                                                     || routId.isChecked() || calId.isChecked()
                                                                                                     || childnameId.isChecked() || childrenfolderId.isChecked()
                                                                                                     || displaychId.isChecked() || thembasewordwallId.isChecked()) {
                                                                                                 childfoundtodayy = childfounId.getText().toString();
                                                                                                 childenrolledd = Integer.parseInt(childenrolled);
                                                                                                 childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                                                                                 Log.e("childenrolled", childfoundtodayy);
                                                                                                 if (childenrolledd>=childfoundtodayyy ){
                                                                                                     childfounId.setText(childfounId.getText().toString());
                                                                                                     Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                     String val = "7";
                                                                                                     update_shishu_aloy_activity1(val);
                                                                                                 }
                                                                                                 else {
                                                                                                     childfounId.setText("");
                                                                                                     Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                                                                                     String title = "Message Box";
                                                                                                     String msg = "NO VALID DATA";
                                                                                                     createDialog(title,msg);
                                                                                                 }
                                                                                             }
                                                                                         }

                                                                                     }





                                                                                 }
                                                                             }

                                                                         } else {
                                                                             Toast.makeText(ShishuAloyActivity.this, "SELECT IF ASSESSMENT CARD IS AVAILBLE", Toast.LENGTH_SHORT).show();
                                                                             String title = "Message Box";
                                                                             String msg = "SELECT IF ASSESSMENT CARD IS AVAILBLE";
                                                                             createDialog(title,msg);

                                                                         }

                                                                     } else {
                                                                         Toast.makeText(ShishuAloyActivity.this, "SELECT CHILDREN ARE ACTIVITY AND PARTICIPATING IN ECCE PROCCESS", Toast.LENGTH_SHORT).show();
                                                                         String title = "Message Box";
                                                                         String msg = "SELECT CHILDREN ARE ACTIVITY AND PARTICIPATING IN ECCE PROCCESS";
                                                                         createDialog(title,msg);
                                                                     }

                                                                 } else {
                                                                     Toast.makeText(ShishuAloyActivity.this, "SELECT WHETHER AWW IS FOLLOWING THE ROTINE", Toast.LENGTH_SHORT).show();
                                                                     String title = "Message Box";
                                                                     String msg = "SELECT WHETHER AWW IS FOLLOWING THE ROTINE";
                                                                     createDialog(title,msg);

                                                                 }

                                                             } else {
                                                                 Toast.makeText(ShishuAloyActivity.this, "SELECT AWC DECORATION IS FIT FOR ECCE", Toast.LENGTH_SHORT).show();
                                                                 String title = "Message Box";
                                                                 String msg = "SELECT AWC DECORATION IS FIT FOR ECCE";
                                                                 createDialog(title,msg);
                                                             }
                                                         } else {
                                                             Toast.makeText(ShishuAloyActivity.this, "SELECT CHILDREN INDIVIDUAL ACTIVITY RECORD FOUND", Toast.LENGTH_SHORT).show();
                                                             String title = "Message Box";
                                                             String msg = "SELECT CHILDREN INDIVIDUAL ACTIVITY RECORD FOUND";
                                                             createDialog(title,msg);
                                                         }
                                                     } else {
                                                         Toast.makeText(ShishuAloyActivity.this, "SELECT AVAILABILITY OF INDIVIDUAL CHILD FILE CONSISTING CHILDREN'S ARTWORKS", Toast.LENGTH_SHORT).show();
                                                         String title = "Message Box";
                                                         String msg = "SELECT AVAILABILITY OF INDIVIDUAL CHILD FILE CONSISTING CHILDREN'S ARTWORKS";
                                                         createDialog(title,msg);
                                                     }


                                                 } else {
                                                     Toast.makeText(ShishuAloyActivity.this, "SELECT AWW USING ECCE KIT PROPERLY OR NOT", Toast.LENGTH_SHORT).show();
                                                     String title = "Message Box";
                                                     String msg = "SELECT AWW USING ECCE KIT PROPERLY OR NOT";
                                                     createDialog(title,msg);
                                                 }

                                             } else {
                                                 Toast.makeText(ShishuAloyActivity.this, "SELECT AWW IS USING APPROPRIATE TLM", Toast.LENGTH_SHORT).show();
                                                 String title = "Message Box";
                                                 String msg = "SELECT AWW IS USING APPROPRIATE TLM";
                                                 createDialog(title,msg);
                                             }
                                         } else {
                                             Toast.makeText(ShishuAloyActivity.this, "SELECT ECCE ACTIVITY AT AWC FOUND DURING VISIT", Toast.LENGTH_SHORT).show();
                                             String title = "Message Box";
                                             String msg = "SELECT ECCE ACTIVITY AT AWC FOUND DURING VISIT";
                                             createDialog(title,msg);
                                         }
                                     } else {
                                         Toast.makeText(ShishuAloyActivity.this, "SELECT WHETHER ECCE IS RUNNING", Toast.LENGTH_SHORT).show();
                                         String title = "Message Box";
                                         String msg = "SELECT WHETHER ECCE IS RUNNING";
                                         createDialog(title,msg);
                                     }

                                 } else {
                                     Toast.makeText(ShishuAloyActivity.this, "SELECT PLAY CORNER", Toast.LENGTH_SHORT).show();
                                     String title = "Message Box";
                                     String msg = "SELECT PLAY CORNER";
                                     createDialog(title,msg);
                                 }
                             } else {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECT DRAWING CORNER", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT DRAWING CORNER";
                                 createDialog(title,msg);
                             }

                         } else {
                             Toast.makeText(ShishuAloyActivity.this, "SELECT BOOK CORNER", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT BOOK CORNER";
                             createDialog(title,msg);
                         }
                     } else {
                         Toast.makeText(ShishuAloyActivity.this, "SELECT BLOCK CORNER", Toast.LENGTH_SHORT).show();
                         String title = "Message Box";
                         String msg = "SELECT BLOCK CORNER";
                         createDialog(title,msg);
                     }
                 }
                 if (sishualoy.equals("y")) {
                     if (YwerunID.isChecked()) {
                         Log.e("eccrun", " " + eccrun);
                         if (routing.equals("Select Routine Theme")) {
                             Toast.makeText(ShishuAloyActivity.this, "SELECT ROUTINE THEME", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT ROUTINE THEME";
                             createDialog(title,msg);
                         }
                     }
                     if (Yawcfound.isChecked()) {
                         if (awcfound.equals("Select ECCE Activity")) {
                             Toast.makeText(ShishuAloyActivity.this, "SELECT ECCE ACTIVITY", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT ECCE ACTIVITY";
                             createDialog(title,msg);
                         }
                     }
                     if (YawcdecceID.isChecked()){
                         if (chatId.isChecked() || labelId.isChecked() || rulesId.isChecked() || routId.isChecked() || calId.isChecked() || childnameId.isChecked() || childrenfolderId.isChecked() || displaychId.isChecked() || thembasewordwallId.isChecked()) {


                         } else {
                             Toast.makeText(ShishuAloyActivity.this, "SELECT AWC DECORATION IS FIT FOR ECCE CHECK BOX", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT AWC DECORATION IS FIT FOR ECCE CHECK BOX";
                             createDialog(title,msg);
                         }
                     }
                     if (YassesmentcardId.isChecked()){
                         if (InuseId.isChecked() || NotinuseId.isChecked()){

                         }
                         else {
                             Toast.makeText(ShishuAloyActivity.this, "SELECTI F ASSESSMENT CARD IS AVAILABLE", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECTI F ASSESSMENT CARD IS AVAILABLE";
                             createDialog(title,msg);

                         }
                     }

                 }
                 if (sishualoy.equals("n")){
                     if (YpseId.isChecked() || NpseId.isChecked()){
                         InputFilter filterpse = new InputFilter() {
                             public CharSequence filter(CharSequence source, int start, int end,
                                                        Spanned dest, int dstart, int dend) {
                                 char[] chars = {'\'', '"'};
                                 for (int i = start; i < end; i++) {
                                     if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                         return "";
                                     }
                                 }
                                 return null;
                             }
                         };
                         psenmID.setFilters(new InputFilter[]{filterpse});
                         pseactivitynm = psenmID.getText().toString().trim();
                         if (TextUtils.isEmpty(chldrspnse)) {
                             psenmID.setError("Please enter pse name");
                             psenmID.requestFocus();
                             //return;
                         }

                         InputFilter filterpse1 = new InputFilter() {
                             public CharSequence filter(CharSequence source, int start, int end,
                                                        Spanned dest, int dstart, int dend) {
                                 char[] chars = {'\'', '"'};
                                 for (int i = start; i < end; i++) {
                                     if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                         return "";
                                     }
                                 }
                                 return null;
                             }
                         };
                         psenmID.setFilters(new InputFilter[]{filterpse1});
                         childenrolled = NenrolledchildrenId.getText().toString().trim();
                         if (TextUtils.isEmpty(childenrolled)) {
                             NenrolledchildrenId.setError("Please enter");
                             NenrolledchildrenId.requestFocus();
                             return;
                         }
                         InputFilter filterpse2 = new InputFilter() {
                             public CharSequence filter(CharSequence source, int start, int end,
                                                        Spanned dest, int dstart, int dend) {
                                 char[] chars = {'\'', '"'};
                                 for (int i = start; i < end; i++) {
                                     if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                         return "";
                                     }
                                 }
                                 return null;
                             }
                         };
                         psenmID.setFilters(new InputFilter[]{filterpse2});
                         childfoundtodayy = NchildfoundId.getText().toString().trim();
                         if (TextUtils.isEmpty(childfoundtodayy)) {
                             NchildfoundId.setError("Please enter");
                             NchildfoundId.requestFocus();
                             return;
                         }
                         if (NNYparticipentpscId.isChecked() || NNNparticipentpscId.isChecked()){
                             if (NYifassenmentId.isChecked() || NNifassenmentId.isChecked()){
                                 InputFilter filterpse3 = new InputFilter() {
                                     public CharSequence filter(CharSequence source, int start, int end,
                                                                Spanned dest, int dstart, int dend) {
                                         char[] chars = {'\'', '"'};
                                         for (int i = start; i < end; i++) {
                                             if (new String(chars).contains(String.valueOf(source.charAt(0)))) {
                                                 return "";
                                             }
                                         }
                                         return null;
                                     }
                                 };
                                 NGvCID.setFilters(new InputFilter[]{filterpse3});
                                 inspectrcmnt = NGvCID.getText().toString().trim();
                                 if (TextUtils.isEmpty(inspectrcmnt)) {
                                     NGvCID.setError("Please Comment");
                                     NGvCID.requestFocus();
                                     return;
                                 }
                                 if (NYifassenmentId.isChecked()){
                                     if (NInuseId.isChecked() || NNotinuseId.isChecked()){
                                         childfoundtodayy = NchildfoundId.getText().toString();
                                         childenrolledd = Integer.parseInt(childenrolled);
                                         childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                         //Log.e("childenrolled", childfoundtodayy);
                                         if (childenrolledd>=childfoundtodayyy ){
                                             NchildfoundId.setText(NchildfoundId.getText().toString());
                                             Toast.makeText(getApplicationContext(), "VALID DATA", Toast.LENGTH_SHORT).show();
                                             String val = "00";
                                             update_shishu_aloy_activity1(val);
                                         }
                                         else {
                                             NchildfoundId.setText("");
                                             Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                             String title = "Message Box";
                                             String msg = "NO VALID DATA";
                                             createDialog(title,msg);
                                         }

                                     }
                                 }
                                 if (NNifassenmentId.isChecked()){
                                     childfoundtodayy = NchildfoundId.getText().toString();
                                     childenrolledd = Integer.parseInt(childenrolled);
                                     childfoundtodayyy = Integer.parseInt(childfoundtodayy);
                                     //Log.e("childenrolled", childfoundtodayy);
                                     if (childenrolledd>=childfoundtodayyy ){
                                         NchildfoundId.setText(NchildfoundId.getText().toString());
                                         Toast.makeText(getApplicationContext(), "Valid Data", Toast.LENGTH_SHORT).show();
                                         String val = "01";
                                         update_shishu_aloy_activity1(val);

                                     }
                                     else {
                                         NchildfoundId.setText("");
                                         Toast.makeText(getApplicationContext(), "NO VALID DATA", Toast.LENGTH_SHORT).show();
                                         String title = "Message Box";
                                         String msg = "NO VALID DATA";
                                         createDialog(title,msg);
                                     }
                                 }

                             }
                             else {
                                 Toast.makeText(ShishuAloyActivity.this, "SELECT IF ASSESSMENT CARD IS AVAILABLE", Toast.LENGTH_SHORT).show();

                                 String title = "Message Box";
                                 String msg = "SELECT IF ASSESSMENT CARD IS AVAILABLE";
                                 createDialog(title,msg);
                             }
                         }
                         else {
                             Toast.makeText(ShishuAloyActivity.this, "SELECT CHILDREN ARE ACTIVITY AND PARTICIPATION IN PSC ACTIVITY", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT CHILDREN ARE ACTIVITY AND PARTICIPATION IN PSC ACTIVITY";
                             createDialog(title,msg);
                         }

                     }
                     else {
                         Toast.makeText(ShishuAloyActivity.this, "SELECT PSE ACTIVITY FOUND TODAY", Toast.LENGTH_SHORT).show();
                         String title = "Message Box";
                         String msg = "SELECT PSE ACTIVITY FOUND TODAY";
                         createDialog(title,msg);
                     }
                 }
                 if (sishualoy.equals("n")){
                     if (NYifassenmentId.isChecked()){
                         if (NInuseId.isChecked() || NNotinuseId.isChecked()){

                         }
                         else {
                             Toast.makeText(ShishuAloyActivity.this, "SELECT IF ASSESMENT IN USE OR NOT USE", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT IF ASSESMENT IN USE OR NOT USE";
                             createDialog(title,msg);

                         }
                     }

                 }

             }
             else {
                 Toast.makeText(ShishuAloyActivity.this, "SELECT SHISHU ALOY YES OR NO", Toast.LENGTH_SHORT).show();
                 String title = "Message Box";
                 String msg = "SELECT SHISHU ALOY YES OR NO";
                 createDialog(title,msg);
             }




             ////////////////////////////////////shualoy no////////////////////////////////

         }
    private void update_shishu_aloy_activity1(final String val){
        Log.e("DATAVALUESANE",val);
        FITORECCCHECK = chart_according+","+labelling+","+rules+","+routine+","+calender+","+childrenname+","+childrenfolder+","+displaywork+","+themebaseworld;
        eccobserdate =  TdateId.getText().toString();
        ecckitdate =  FdateId.getText().toString();
//        InputFilter filter4 = new InputFilter() {
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
//        GvCID.setFilters(new InputFilter[] { filter4 });
//        inspectrcmnt = GvCID.getText().toString().trim();
//        if (TextUtils.isEmpty(inspectrcmnt)) {
//            inspectrcmnt = GvCID.getText().toString().trim();
//            GvCID.setError("Please enter Give Comment");
//            GvCID.requestFocus();
//            return;
//        }

        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userID = String.valueOf(user.getUserID());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SHISHUALOY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("SHISHUALOY"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("SHISHUALOYOBJJ"," "+array);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET shishualoy='1' WHERE allinspactionid=" +insid);
                                if (sishualoy.equals("y")) {
//                                            pseactvfound = "NNA";
//                                            pseactivitynm ="NNA";
//                                            chldparticipatpse = "NNA";
//                                            syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
//                                                    valuesuting,
//                                                    eccactvtytyp,
//                                                    awcvaluestring,
//                                                    tlam,
//                                                    proprecckit,
//                                                    avaleindvsualchildactvtyrcd,
//                                                    indvsualchildactvtyrcd,
//                                                    awcdecortnfrecce,
//                                                    FITORECCCHECK,
//                                                    childenrolled,
//                                                    childfoundtodayy,
//                                                    ecceprocess,
//                                                    assesmentcard,
//                                                    assesmentcard_use,
//                                                    ecckitdate,
//                                                    eccobserdate,
//                                                    pseactvfound,
//                                                    pseactivitynm,
//                                                    chldparticipatpse,
//                                                    inspectrcmnt,
//                                                    curTime,
//                                                    SHISHUALOY_SYNCED_WITH_SERVER);

                                    if (eccrun.equals("y")){
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }
                                    if (eccrun.equals("n")){
                                        valuesuting ="NNA";
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }

                                    if (eccactvtytyp.equals("y")){
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }

                                    if (eccactvtytyp.equals("n")){
                                        awcvaluestring ="NNA";
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }
//                                            if (awcdecortnfrecce.equals('y')){
//                                                pseactvfound = "NNA";
//                                                pseactivitynm ="NNA";
//                                                chldparticipatpse = "NNA";
//                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
//                                                        awcvaluestring,
//                                                        tlam,
//                                                        proprecckit,
//                                                        avaleindvsualchildactvtyrcd,
//                                                        indvsualchildactvtyrcd,
//                                                        awcdecortnfrecce,
//                                                        FITORECCCHECK,
//                                                        childenrolled,
//                                                        childfoundtodayy,
//                                                        ecceprocess,
//                                                        assesmentcard,
//                                                        assesmentcard_use,
//                                                        ecckitdate,
//                                                        eccobserdate,
//                                                        pseactvfound,
//                                                        pseactivitynm,
//                                                        chldparticipatpse,
//                                                        inspectrcmnt,
//                                                        curTime,
//                                                        SHISHUALOY_SYNCED_WITH_SERVER);
//                                            }
//                                            if (awcdecortnfrecce.equals('n')){
//                                                pseactvfound = "NNA";
//                                                pseactivitynm ="NNA";
//                                                chldparticipatpse = "NNA";
//                                                //FITORECCCHECK= "0,0,0,0,0,0,0,0,0";
//                                                syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
//                                                        awcvaluestring,
//                                                        tlam,
//                                                        proprecckit,
//                                                        avaleindvsualchildactvtyrcd,
//                                                        indvsualchildactvtyrcd,
//                                                        awcdecortnfrecce,
//                                                        FITORECCCHECK,
//                                                        childenrolled,
//                                                        childfoundtodayy,
//                                                        ecceprocess,
//                                                        assesmentcard,
//                                                        assesmentcard_use,
//                                                        ecckitdate,
//                                                        eccobserdate,
//                                                        pseactvfound,
//                                                        pseactivitynm,
//                                                        chldparticipatpse,
//                                                        inspectrcmnt,
//                                                        curTime,
//                                                        SHISHUALOY_SYNCED_WITH_SERVER);
//                                            }

                                    if (assesmentcard.equals("y")){
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }
                                    if (assesmentcard.equals("n")){
                                        pseactvfound = "NNA";
                                        pseactivitynm ="NNA";
                                        chldparticipatpse = "NNA";
                                        assesmentcard_use = "NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }

                                }
                                else if (sishualoy.equals("n")) {
//                                            cornercgnitv = "NNA";
//                                            bookcorner ="NNA";
//                                            drawcornr = "NNA";
//                                            playcorners = "NNA";
//                                            eccrun = "NA";
//                                            valuesuting ="NA";
//                                            eccactvtytyp ="NA";
//                                            awcvaluestring ="NAA";
//                                            tlam = "NNA";
//                                            proprecckit ="NNA";
//                                            avaleindvsualchildactvtyrcd = "NNA";
//                                            indvsualchildactvtyrcd = "NNA";
//                                            awcdecortnfrecce = "NNA";
//                                            ecckitdate ="NNA";
//                                            eccobserdate ="NNA";
//                                            ecceprocess ="NNA";
//                                            syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
//                                                    valuesuting,
//                                                    eccactvtytyp,
//                                                    awcvaluestring,
//                                                    tlam,
//                                                    proprecckit,
//                                                    avaleindvsualchildactvtyrcd,
//                                                    indvsualchildactvtyrcd,
//                                                    awcdecortnfrecce,
//                                                    FITORECCCHECK,
//                                                    childenrolled,
//                                                    childfoundtodayy,
//                                                    ecceprocess,
//                                                    assesmentcard,
//                                                    assesmentcard_use,
//                                                    ecckitdate,
//                                                    eccobserdate,
//                                                    pseactvfound,
//                                                    pseactivitynm,
//                                                    chldparticipatpse,
//                                                    inspectrcmnt,
//                                                    curTime,
//                                                    SHISHUALOY_SYNCED_WITH_SERVER);


                                    if (assesmentcard.equals("y")){
                                        cornercgnitv = "NNA";
                                        bookcorner ="NNA";
                                        drawcornr = "NNA";
                                        playcorners = "NNA";
                                        eccrun = "NA";
                                        valuesuting ="NA";
                                        eccactvtytyp ="NA";
                                        awcvaluestring ="NAA";
                                        tlam = "NNA";
                                        proprecckit ="NNA";
                                        avaleindvsualchildactvtyrcd = "NNA";
                                        indvsualchildactvtyrcd = "NNA";
                                        awcdecortnfrecce = "NNA";
                                        ecckitdate ="NNA";
                                        eccobserdate ="NNA";
                                        ecceprocess ="NNA";
                                        wheterawwriting ="NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                valuesuting,
                                                eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }


                                    if (assesmentcard.equals("n")){
                                        cornercgnitv = "NNA";
                                        bookcorner ="NNA";
                                        drawcornr = "NNA";
                                        playcorners = "NNA";
                                        eccrun = "NA";
                                        valuesuting ="NA";
                                        eccactvtytyp ="NA";
                                        awcvaluestring ="NAA";
                                        tlam = "NNA";
                                        proprecckit ="NNA";
                                        avaleindvsualchildactvtyrcd = "NNA";
                                        indvsualchildactvtyrcd = "NNA";
                                        awcdecortnfrecce = "NNA";
                                        ecckitdate ="NNA";
                                        eccobserdate ="NNA";
                                        ecceprocess ="NNA";
                                        assesmentcard_use = "NNA";
                                        wheterawwriting ="NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                valuesuting,
                                                eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }
                                    if (pseactvfound.equals("y")){
                                        cornercgnitv = "NNA";
                                        bookcorner ="NNA";
                                        drawcornr = "NNA";
                                        playcorners = "NNA";
                                        eccrun = "NA";
                                        valuesuting ="NA";
                                        eccactvtytyp ="NA";
                                        awcvaluestring ="NAA";
                                        tlam = "NNA";
                                        proprecckit ="NNA";
                                        avaleindvsualchildactvtyrcd = "NNA";
                                        indvsualchildactvtyrcd = "NNA";
                                        awcdecortnfrecce = "NNA";
                                        ecckitdate ="NNA";
                                        eccobserdate ="NNA";
                                        ecceprocess ="NNA";
                                        wheterawwriting ="NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                valuesuting,
                                                eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }

                                    if (pseactvfound.equals("n")){
                                        cornercgnitv = "NNA";
                                        bookcorner ="NNA";
                                        drawcornr = "NNA";
                                        playcorners = "NNA";
                                        eccrun = "NA";
                                        valuesuting ="NA";
                                        eccactvtytyp ="NA";
                                        awcvaluestring ="NAA";
                                        tlam = "NNA";
                                        proprecckit ="NNA";
                                        avaleindvsualchildactvtyrcd = "NNA";
                                        indvsualchildactvtyrcd = "NNA";
                                        awcdecortnfrecce = "NNA";
                                        ecckitdate ="NNA";
                                        eccobserdate ="NNA";
                                        ecceprocess ="NNA";
                                        pseactivitynm = "NNA";
                                        wheterawwriting ="NNA";
                                        syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                                valuesuting,
                                                eccactvtytyp,
                                                awcvaluestring,
                                                tlam,
                                                proprecckit,
                                                avaleindvsualchildactvtyrcd,
                                                indvsualchildactvtyrcd,
                                                awcdecortnfrecce,
                                                FITORECCCHECK,
                                                childenrolled,
                                                childfoundtodayy,
                                                ecceprocess,
                                                assesmentcard,
                                                assesmentcard_use,
                                                ecckitdate,
                                                eccobserdate,
                                                pseactvfound,
                                                pseactivitynm,
                                                chldparticipatpse,
                                                wheterawwriting,
                                                inspectrcmnt,
                                                curTime,
                                                SHISHUALOY_SYNCED_WITH_SERVER);
                                    }
                                }
                                else {

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
                        dbb.execSQL("UPDATE insflag SET shishualoy='1' WHERE allinspactionid=" +insid);
                        if (sishualoy.equals("y")) {
                            if (eccrun.equals("y")){
                                pseactvfound = "NNA";
                                pseactivitynm ="NNA";
                                chldparticipatpse = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }
                            if (eccrun.equals("n")){
                                valuesuting ="NNA";
                                pseactvfound = "NNA";
                                pseactivitynm ="NNA";
                                chldparticipatpse = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }

                            if (eccactvtytyp.equals("y")){
                                pseactvfound = "NNA";
                                pseactivitynm ="NNA";
                                chldparticipatpse = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }

                            if (eccactvtytyp.equals("n")){
                                awcvaluestring ="NNA";
                                pseactvfound = "NNA";
                                pseactivitynm ="NNA";
                                chldparticipatpse = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }
//                                    if (awcdecortnfrecce.equals('y')){
//                                        pseactvfound = "NNA";
//                                        pseactivitynm ="NNA";
//                                        chldparticipatpse = "NNA";
//                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
//                                                awcvaluestring,
//                                                tlam,
//                                                proprecckit,
//                                                avaleindvsualchildactvtyrcd,
//                                                indvsualchildactvtyrcd,
//                                                awcdecortnfrecce,
//                                                FITORECCCHECK,
//                                                childenrolled,
//                                                childfoundtodayy,
//                                                ecceprocess,
//                                                assesmentcard,
//                                                assesmentcard_use,
//                                                ecckitdate,
//                                                eccobserdate,
//                                                pseactvfound,
//                                                pseactivitynm,
//                                                chldparticipatpse,
//                                                inspectrcmnt,
//                                                curTime,
//                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
//                                    }
//                                    if (awcdecortnfrecce.equals('n')){
//                                        pseactvfound = "NNA";
//                                        pseactivitynm ="NNA";
//                                        chldparticipatpse = "NNA";
//                                       // String FITORECCCHECK= "0,0,0,0,0,0,0,0,0";
//                                        syncShishualoysaveDatabase(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
//                                                awcvaluestring,
//                                                tlam,
//                                                proprecckit,
//                                                avaleindvsualchildactvtyrcd,
//                                                indvsualchildactvtyrcd,
//                                                awcdecortnfrecce,
//                                                FITORECCCHECK,
//                                                childenrolled,
//                                                childfoundtodayy,
//                                                ecceprocess,
//                                                assesmentcard,
//                                                assesmentcard_use,
//                                                ecckitdate,
//                                                eccobserdate,
//                                                pseactvfound,
//                                                pseactivitynm,
//                                                chldparticipatpse,
//                                                inspectrcmnt,
//                                                curTime,
//                                                SHISHUALOY_NOT_SYNCED_WITH_SERVER);
//                                    }

                            if (assesmentcard.equals("y")){
                                pseactvfound = "NNA";
                                pseactivitynm ="NNA";
                                chldparticipatpse = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }
                            if (assesmentcard.equals("n")){
                                pseactvfound = "NNA";
                                pseactivitynm ="NNA";
                                chldparticipatpse = "NNA";
                                assesmentcard_use = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun, valuesuting, eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }

                        }
                        /////////////////////////////////////
                        else if (sishualoy.equals("n")) {
                            ////////////////////////////////////////////////////////
                            if (assesmentcard.equals("y")){
                                cornercgnitv = "NNA";
                                bookcorner ="NNA";
                                drawcornr = "NNA";
                                playcorners = "NNA";
                                eccrun = "NA";
                                valuesuting ="NA";
                                eccactvtytyp ="NA";
                                awcvaluestring ="NAA";
                                tlam = "NNA";
                                proprecckit ="NNA";
                                avaleindvsualchildactvtyrcd = "NNA";
                                indvsualchildactvtyrcd = "NNA";
                                awcdecortnfrecce = "NNA";
                                ecckitdate ="NNA";
                                eccobserdate ="NNA";
                                ecceprocess ="NNA";
                                wheterawwriting = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                        valuesuting,
                                        eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }


                            if (assesmentcard.equals("n")){
                                cornercgnitv = "NNA";
                                bookcorner ="NNA";
                                drawcornr = "NNA";
                                playcorners = "NNA";
                                eccrun = "NA";
                                valuesuting ="NA";
                                eccactvtytyp ="NA";
                                awcvaluestring ="NAA";
                                tlam = "NNA";
                                proprecckit ="NNA";
                                avaleindvsualchildactvtyrcd = "NNA";
                                indvsualchildactvtyrcd = "NNA";
                                awcdecortnfrecce = "NNA";
                                ecckitdate ="NNA";
                                eccobserdate ="NNA";
                                ecceprocess ="NNA";
                                assesmentcard_use = "NNA";
                                wheterawwriting = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                        valuesuting,
                                        eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }
                            if (pseactvfound.equals("y")){
                                cornercgnitv = "NNA";
                                bookcorner ="NNA";
                                drawcornr = "NNA";
                                playcorners = "NNA";
                                eccrun = "NA";
                                valuesuting ="NA";
                                eccactvtytyp ="NA";
                                awcvaluestring ="NAA";
                                tlam = "NNA";
                                proprecckit ="NNA";
                                avaleindvsualchildactvtyrcd = "NNA";
                                indvsualchildactvtyrcd = "NNA";
                                awcdecortnfrecce = "NNA";
                                ecckitdate ="NNA";
                                eccobserdate ="NNA";
                                ecceprocess ="NNA";
                                wheterawwriting = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                        valuesuting,
                                        eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }

                            if (pseactvfound.equals("n")){
                                cornercgnitv = "NNA";
                                bookcorner ="NNA";
                                drawcornr = "NNA";
                                playcorners = "NNA";
                                eccrun = "NA";
                                valuesuting ="NA";
                                eccactvtytyp ="NA";
                                awcvaluestring ="NAA";
                                tlam = "NNA";
                                proprecckit ="NNA";
                                avaleindvsualchildactvtyrcd = "NNA";
                                indvsualchildactvtyrcd = "NNA";
                                awcdecortnfrecce = "NNA";
                                ecckitdate ="NNA";
                                eccobserdate ="NNA";
                                ecceprocess ="NNA";
                                pseactivitynm = "NNA";
                                wheterawwriting = "NNA";
                                syncShishualoysaveDatabase1(insid, sishualoy, cornercgnitv, bookcorner, drawcornr, playcorners, eccrun,
                                        valuesuting,
                                        eccactvtytyp,
                                        awcvaluestring,
                                        tlam,
                                        proprecckit,
                                        avaleindvsualchildactvtyrcd,
                                        indvsualchildactvtyrcd,
                                        awcdecortnfrecce,
                                        FITORECCCHECK,
                                        childenrolled,
                                        childfoundtodayy,
                                        ecceprocess,
                                        assesmentcard,
                                        assesmentcard_use,
                                        ecckitdate,
                                        eccobserdate,
                                        pseactvfound,
                                        pseactivitynm,
                                        chldparticipatpse,
                                        wheterawwriting,
                                        inspectrcmnt,
                                        curTime,
                                        SHISHUALOY_NOT_SYNCED_WITH_SERVER);
                            }


                            ///////////////////////////////////////////////////////////
                        }
                        else {

                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                        params.put("corner_cgnitv",cornercgnitv);
//                        params.put("book_corner",bookcorner);
//                        params.put("draw_cornr",drawcornr);
//                        params.put("play_corner",playcorners);
//                        params.put("ecc_run",eccrun);
//                        params.put("ecc_routn_theme", valuesuting);
                /////ecce activity at awc found during visit edittext
//                        params.put("ecc_actvty_typ",eccactvtytypRep);
                //params.put("ecc_actvty_typ",eccactvtytyp);
                // params.put("ecc_activty_found",awcvaluestring);
//                        params.put("aww_tlm",tlam);
                // params.put("propr_ecc_kit",proprecckit);
//                        params.put("indvsual_child_artwork" ,avaleindvsualchildactvtyrcd);
//                        params.put("indvsual_child_actvty_rcd",indvsualchildactvtyrcd);
//                        params.put("awc_decortn_fr_ecce",awcdecortnfrecce);
//                        params.put("aww_routin_follow",FITORECCCHECK);
//                       // params.put("",wheterawwriting);
//                        params.put("tot_chld_enroll",childenrolled);
//                        params.put("tot_chld_found_today",childfoundtodayy);
//                        params.put("chld_participat_ecc",ecceprocess);
//                        params.put("assmnt_card",assesmentcard);
//                        params.put("assmnt_card_st",assesmentcard_use);
//                        params.put("last_ecc_kit_rcv_dt",ecckitdate);
//                        params.put("last_ecc_day_observ",eccobserdate);
                if (sishualoy.equals("y")){
                    pseactvfound = "NNA";
                    pseactivitynm ="NNA";
                    chldparticipatpse = "NNA";
                    params.put("inspctn_id",insid);
                    params.put("sishu_aloy",sishualoy);
                    params.put("corner_cgnitv",cornercgnitv);
                    params.put("book_corner",bookcorner);
                    params.put("draw_cornr",drawcornr);
                    params.put("play_corner",playcorners);
                    params.put("ecc_run",eccrun);
                    if (eccrun.equals("y")){
                        params.put("ecc_routn_theme", valuesuting);
                    }
                    if (eccrun.equals("n")){
                        valuesuting ="NNA";
                        params.put("ecc_routn_theme", valuesuting);
                    }
                    params.put("ecc_actvty_typ",eccactvtytyp);
                    if (eccactvtytyp.equals("y")){
                        params.put("ecc_activty_found",awcvaluestring);
                    }
                    if (eccactvtytyp.equals("n")){
                        awcvaluestring ="NNA";
                        params.put("ecc_activty_found",awcvaluestring);
                    }
                    params.put("aww_tlm",tlam);
                    params.put("propr_ecc_kit",proprecckit);
                    params.put("indvsual_child_artwork" ,avaleindvsualchildactvtyrcd);
                    params.put("indvsual_child_actvty_rcd",indvsualchildactvtyrcd);
                    params.put("awc_decortn_fr_ecce",awcdecortnfrecce);
                    params.put("aww_routin_follow",FITORECCCHECK);
//                            if (awcdecortnfrecce.equals('y')){
//
//                                Log.e("FITORECCCHECK",FITORECCCHECK);
//                            }
//                            if (awcdecortnfrecce.equals("n")){
//                              //  String FITORECCCHECK= "0,0,0,0,0,0,0,0,0";
//                                params.put("aww_routin_follow",FITORECCCHECK);
//                                Log.e("FITORECCCHECK",FITORECCCHECK);
//                            }

                    // params.put("",wheterawwriting);
                    params.put("tot_chld_enroll",childenrolled);
                    params.put("tot_chld_found_today",childfoundtodayy);
                    params.put("chld_participat_ecc",ecceprocess);
                    params.put("assmnt_card",assesmentcard);
                    if (assesmentcard.equals("y")){
                        params.put("assmnt_card_st",assesmentcard_use);
                    }
                    if (assesmentcard.equals("n")){
                        assesmentcard_use = "NNA";
                        params.put("assmnt_card_st",assesmentcard_use);
                    }

                    params.put("last_ecc_kit_rcv_dt",ecckitdate);
                    params.put("last_ecc_day_observ",eccobserdate);
                    params.put("pse_actv_found",pseactvfound);
                    params.put("pse_activity_nm",pseactivitynm);
                    params.put("chld_participat_pse",chldparticipatpse);
                    params.put("aww_follow_routine",wheterawwriting);
                    params.put("inspectr_cmnt",inspectrcmnt);
                    params.put("ins_time",curTime);
                }
                if (sishualoy.equals("n")){
                    cornercgnitv = "NNA";
                    bookcorner ="NNA";
                    drawcornr = "NNA";
                    playcorners = "NNA";
                    eccrun = "NA";
                    valuesuting ="NA";
                    eccactvtytyp ="NA";
                    awcvaluestring ="NAA";
                    tlam = "NNA";
                    proprecckit ="NNA";
                    avaleindvsualchildactvtyrcd = "NNA";
                    indvsualchildactvtyrcd = "NNA";
                    awcdecortnfrecce = "NNA";
                    ecckitdate ="NNA";
                    eccobserdate ="NNA";
                    ecceprocess ="NNA";
                    wheterawwriting = "NNA";
                    ///////////////yes fild/////////////////
                    params.put("inspctn_id",insid);
                    params.put("sishu_aloy",sishualoy);
                    params.put("corner_cgnitv",cornercgnitv);
                    params.put("book_corner",bookcorner);
                    params.put("draw_cornr",drawcornr);
                    params.put("play_corner",playcorners);
                    params.put("ecc_run",eccrun);
                    params.put("ecc_routn_theme", valuesuting);
                    params.put("ecc_actvty_typ",eccactvtytyp);
                    params.put("ecc_activty_found",awcvaluestring);
                    params.put("aww_tlm",tlam);
                    params.put("propr_ecc_kit",proprecckit);
                    params.put("indvsual_child_artwork" ,avaleindvsualchildactvtyrcd);
                    params.put("indvsual_child_actvty_rcd",indvsualchildactvtyrcd);
                    params.put("awc_decortn_fr_ecce",awcdecortnfrecce);
                    params.put("aww_routin_follow",FITORECCCHECK);
                    params.put("tot_chld_enroll",childenrolled);
                    params.put("tot_chld_found_today",childfoundtodayy);
                    params.put("chld_participat_ecc",ecceprocess);
                    params.put("assmnt_card",assesmentcard);
                    if (assesmentcard.equals("y")){
                        params.put("assmnt_card_st",assesmentcard_use);
                    }
                    if (assesmentcard.equals("n")){
                        assesmentcard_use = "NNA";
                        params.put("assmnt_card_st",assesmentcard_use);
                    }

                    params.put("last_ecc_kit_rcv_dt",ecckitdate);
                    params.put("last_ecc_day_observ",eccobserdate);
                    params.put("pse_actv_found",pseactvfound);
                    if (pseactvfound.equals("y")){
                        params.put("pse_activity_nm",pseactivitynm);
                    }
                    if (pseactvfound.equals("n")){
                        pseactivitynm = "NNA";
                        params.put("pse_activity_nm",pseactivitynm);
                    }
                    params.put("chld_participat_pse",chldparticipatpse);
                    params.put("aww_follow_routine",wheterawwriting);
                    params.put("inspectr_cmnt",inspectrcmnt);
                    params.put("ins_time",curTime);


                }
                //params.put("tot_chld_prsnt",totchldprsnt);
                //params.put("chld_rspnse",chldrspnse);
                //params.put("inspectr_cmnt",inspectrcmnt);
                // inspctn_id 107173129122019 sishu_aloy y corner_cgnitv good book_corner average draw_cornr average play_corner poorecc_run y ecc_routn_theme 2ecc_actvty_typ nullecc_activty_found 4aww_tlm ypropr_ecc_kit pindvsual_child_artwork yindvsual_child_actvty_rcd yawc_decortn_fr_ecce yaww_routin_follow 1,2,3,4,5,6,7,8,9tot_chld_enroll 1tot_chld_found_today 2chld_participat_ecc yassmnt_card yassmnt_card_st iulast_ecc_kit_rcv_dt 26-12-2019last_ecc_day_observ 29-12-2019pse_actv_found nullpse_activity_nm nullchld_participat_pse nullchld_rspnse Response childreninspectr_cmnt hiins_time 11:40
                Log.e("SHISHULAYEDIT","inspctn_id"+" "+"  "+insid+" "+"  "+
                        "sishu_aloy"+" "+"  "+sishualoy+" "+" "+
                        "corner_cgnitv"+" "+cornercgnitv+" "+" "+
                        "book_corner"+" "+" "+bookcorner+" "+" "+
                        "draw_cornr"+"  "+drawcornr+" "+
                        "play_corner"+"   "+playcorners+
                        "ecc_run"+" "+eccrun+" "
                        +"ecc_routn_theme"+"  "+valuesuting+
                        "ecc_actvty_typ"+" "+"  "+" "+eccactvtytyp+
                        "ecc_activty_found"+"   "+awcvaluestring
                        +"aww_tlm"+"  "+tlam
                        +"propr_ecc_kit"+"   "+proprecckit
                        +"indvsual_child_artwork"+"   "+avaleindvsualchildactvtyrcd
                        +"indvsual_child_actvty_rcd"+"   "+indvsualchildactvtyrcd
                        +"awc_decortn_fr_ecce"+"   "+awcdecortnfrecce
                        +"aww_routin_follow"+"   "+FITORECCCHECK
                        +"tot_chld_enroll"+"   "+childenrolled
                        +"tot_chld_found_today"+"   "+childfoundtodayy
                        +"chld_participat_ecc"+"   "+ecceprocess
                        +"assmnt_card"+"   "+assesmentcard
                        +"assmnt_card_st"+" "+assesmentcard_use
                        +"last_ecc_kit_rcv_dt"+"  "+ecckitdate
                        +"last_ecc_day_observ"+"   "+eccobserdate
                        +"pse_actv_found"+"   "+pseactvfound
                        +"pse_activity_nm"+"   "+pseactivitynm
                        +"chld_participat_pse"+"   "+chldparticipatpse
                        +"aww_follow_routine"+"   "+wheterawwriting
                        +"inspectr_cmnt"+"    "+inspectrcmnt
                        +"ins_time"+" "+curTime
                );
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

    private void syncShishualoysaveDatabase1(String insid,
                                            String sishualoy,
                                            String cornercgnitv,
                                            String bookcorner,
                                            String drawcornr,
                                            String playcorners,
                                            String eccrun,
                                            String valuesuting,
                                            String eccactvtytyp,
                                            String awcvaluestring,
                                            String tlam,
                                            String proprecckit,
                                            String avaleindvsualchildactvtyrcd,
                                            String indvsualchildactvtyrcd,
                                            String awcdecortnfrecce,
                                            String fitorecccheck,
                                            String childenrolled,
                                            String childfoundtodayy,
                                            String ecceprocess,
                                            String assesmentcard,
                                            String assesmentcarduse,
                                            String ecckitdate,
                                            String eccobserdate,
                                            String pseactvfound,
                                            String pseactivitynm,
                                            String chldparticipatpse,
                                            String wheterawwriting,
                                            String inspectrcmnt,
                                            String curTime,
                                            int shishualoystatus){

        if (shishualoy.equals("0")){
            helper.SHISHUALOYINSERT(
                    insid,
                    sishualoy,
                    cornercgnitv,
                    bookcorner,
                    drawcornr,
                    playcorners,
                    eccrun,
                    valuesuting,
                    eccactvtytyp,
                    awcvaluestring,
                    tlam,
                    proprecckit,
                    avaleindvsualchildactvtyrcd,
                    indvsualchildactvtyrcd,
                    awcdecortnfrecce,
                    fitorecccheck,
                    childenrolled,
                    childfoundtodayy,
                    ecceprocess,
                    assesmentcard,
                    assesmentcarduse,
                    ecckitdate,
                    eccobserdate,
                    pseactvfound,
                    pseactivitynm,
                    chldparticipatpse,
                    wheterawwriting,
                    inspectrcmnt,
                    curTime,
                    shishualoystatus);
            Log.e("SHISHU","HI");
        }
        else {
            helper.SHISHUALOYUPDATE(dbid,
                    insid,
                    sishualoy,
                    cornercgnitv,
                    bookcorner,
                    drawcornr,
                    playcorners,
                    eccrun,
                    valuesuting,
                    eccactvtytyp,
                    awcvaluestring,
                    tlam,
                    proprecckit,
                    avaleindvsualchildactvtyrcd,
                    indvsualchildactvtyrcd,
                    awcdecortnfrecce,
                    fitorecccheck,
                    childenrolled,
                    childfoundtodayy,
                    ecceprocess,
                    assesmentcard,
                    assesmentcarduse,
                    ecckitdate,
                    eccobserdate,
                    pseactvfound,
                    pseactivitynm,
                    chldparticipatpse,
                    wheterawwriting,
                    inspectrcmnt,
                    curTime,
                    shishualoystatus);
            Log.e("SHISHU","HI1");
        }
        Log.e("SHISHU"," "+dbid+" "
                +insid+" "
                +sishualoy+" "
                +cornercgnitv+" "
                +bookcorner+" "
                +drawcornr+" "
                +playcorners+" "
                +eccrun+" "
                +valuesuting+" "
                +eccactvtytyp+" "
                +awcvaluestring+" "
                +tlam+" "
                +proprecckit+ " "
                +avaleindvsualchildactvtyrcd+" "
                +indvsualchildactvtyrcd+" "
                +awcdecortnfrecce+" "
                +fitorecccheck+" "
                +childenrolled+" "
                +childfoundtodayy
                +ecceprocess+" "
                +assesmentcard+" "
                +assesmentcarduse+" "
                +ecckitdate+" "
                +eccobserdate+" "
                +pseactvfound+" "
                +pseactivitynm+" "
                +chldparticipatpse+" "
                +wheterawwriting+" "
                +inspectrcmnt+" "
                +curTime+" "+
                +shishualoystatus+" ");

        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(ShishuAloyActivity.this, InspectionListActivity.class);
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
        public void alertdiolog(){
        builder = new AlertDialog.Builder(this);
        blockcornercogitiveId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ShishuAloyActivity.this);
                // Get custom login form view.
                final View loginFormView = getLayoutInflater().inflate(R.layout.alert_block_and_cognitive_corner, null);
                // Set above view in alert dialog.
                builder.setView(loginFormView);

                // Register button click listener.
                ImageView closeId = (ImageView) loginFormView.findViewById(R.id.closeId);
                closeId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            // Close Alert Dialog.
                            alertDialog.cancel();
                        }catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                });
                builder.setCancelable(true);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
        }
     public void alertdiologblockcorner(){
         builder = new AlertDialog.Builder(this);
         bookcornerId.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final AlertDialog.Builder builder = new AlertDialog.Builder(ShishuAloyActivity.this);
                 // Get custom login form view.
                 final View loginFormView = getLayoutInflater().inflate(R.layout.alert_bookcorner, null);
                 // Set above view in alert dialog.
                 builder.setView(loginFormView);

                 // Register button click listener.
                 ImageView closeId = (ImageView) loginFormView.findViewById(R.id.closeId);
                 closeId.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         try {
                             // Close Alert Dialog.
                             alertDialog.cancel();
                         }catch(Exception ex)
                         {
                             ex.printStackTrace();
                         }
                     }
                 });
                 builder.setCancelable(true);
                 alertDialog = builder.create();
                 alertDialog.show();
             }
         });
         }

         public void alertdiologdrawingcorner(){
             builder = new AlertDialog.Builder(this);
             drawingcornerId.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     final AlertDialog.Builder builder = new AlertDialog.Builder(ShishuAloyActivity.this);
                     // Get custom login form view.
                     final View loginFormView = getLayoutInflater().inflate(R.layout.alert_dringkingcorner, null);
                     // Set above view in alert dialog.
                     builder.setView(loginFormView);

                     // Register button click listener.
                     ImageView closeId = (ImageView) loginFormView.findViewById(R.id.closeId);
                     closeId.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             try {
                                 // Close Alert Dialog.
                                 alertDialog.cancel();
                             }catch(Exception ex)
                             {
                                 ex.printStackTrace();
                             }
                         }
                     });
                     builder.setCancelable(true);
                     alertDialog = builder.create();
                     alertDialog.show();
                 }
             });
         }

         public void alertplaycorner(){
             builder = new AlertDialog.Builder(this);
             playcorner.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     final AlertDialog.Builder builder = new AlertDialog.Builder(ShishuAloyActivity.this);
                     // Get custom login form view.
                     final View loginFormView = getLayoutInflater().inflate(R.layout.alert_playcorner, null);
                     // Set above view in alert dialog.
                     builder.setView(loginFormView);

                     // Register button click listener.
                     ImageView closeId = (ImageView) loginFormView.findViewById(R.id.closeId);
                     closeId.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             try {
                                 // Close Alert Dialog.
                                 alertDialog.cancel();
                             }catch(Exception ex)
                             {
                                 ex.printStackTrace();
                             }
                         }
                     });
                     builder.setCancelable(true);
                     alertDialog = builder.create();
                     alertDialog.show();
                 }
             });

         }

//    @Override
//    public void onBackPressed() {
//       Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
//        startActivity(intent);
//
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
            if(shishuAloyNetwokchecker!=null)
                unregisterReceiver(shishuAloyNetwokchecker);
            if (broadcastReceivereshishualoy!=null)
                unregisterReceiver(broadcastReceivereshishualoy);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
