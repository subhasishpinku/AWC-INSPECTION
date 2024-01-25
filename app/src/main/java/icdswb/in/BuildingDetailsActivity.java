package icdswb.in;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.Ownbuildingsetget;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_BUILDINGIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_BUILDINGSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_STATUSBUILDING;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;

public class BuildingDetailsActivity extends Activity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    Toolbar toolbar;
    private static DatabaseHelper db;
    Spinner sp3,spp3;
    List<Ownbuildingsetget> ownbuildingsetgets;
    Ownbuildingsetget ownbuildingsetget;
    List<String> ownbildName = new ArrayList<String>();
    List<String> ownbildID = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    HashMap<Integer,String> spinnerMap;
    String awcsid ="NA";
    String awcscode = "NA";
    String awcsname = "NA";
    String dbdistid ="NA";
    String dbprojectid ="NA";
    String dbsectorid ="NA";
    String dbcenterid ="NA";
    String projectnamedb ="NA";
    String districnamedb ="NA";
    String sectorrnamedb ="NA";
    String centernamedb ="NA";
    String currentdate ="NA";
    String yncdpo ="NA";
    String ynacdpio ="NA";
    String YnSupervisor ="NA";
    String Ynworker ="NA";
    String Yhelper ="NA";
    String Ynawcs ="NA";
    RadioGroup Gbuildingstatus,GrentedbuildigID,Grententedbuilding,othergovbuilding,buildingconditionID;
    String kutcha = "NA";
    String pucca = "NA";
    String ownbuilding = "NA";
    String rentedbuilding = "NA";
    String othergovtbuilding = "NA";
    String privatenonrentedbuilding = "NA";
    String clubngo = "NA";
    String cooperative = "NA";
    String privaterented = "NA";
    String otherrented = "NA";
    String primaryschool = "NA";
    String ssk = "NA";
    String msk = "NA";
    String madrashassk = "NA";
    String madrashamsk = "NA";
    String Othergovt = "NA";
    String ggood = "NA";
    String aavg = "NA";
    String ppoor = "NA";
    LinearLayout LVownbuilding,Lvrentedbuildingin,Lvothergovtbuildingin,lvv2;
    String RBB = "RB";
    RadioButton kutchaID,puccaID,ownbuildingID,rentedbuildingID,othergovebuildingID,privatebuildingID,cludngoID,cooperativeID,privateID,otherID,primaryschoolID,sskID,mskID,madrashasskId,madrashamskId,otherId,goodId,averageId,poorId;
    String userID,inspctrcmnt;
    EditText cmdinformation;
    String builttyp;
    String buildconditn ="null";
    String rentbuildin = "null";
    String othrgovtbuildin ="null";
    String builtrunin = "null";
    String ownbuildfund ="null";
    String DistricNamee = "null";
    Button saveID;
    String[] spinnerArray;
    String water ="NA";
    String cdponame ="NA";
    String acdpocont ="NA";
    String buildstruc ="NA";
    String electric ="NA";
    String acdponame ="NA";
    String kitchen ="NA";
    String cdpocontact ="NA";
    String workerno ="NA";
    String worker_nm ="NA";
    String toilet ="NA";
    String awcslat ="NA";
    String supvsrname ="NA";
    String awcsslong ="NA";
    String helperno ="NA";
    String awcs_adrs ="NA";
    String foodspace ="NA";
    String helpernm ="NA";
    String buildon ="NA";
    String adqutspacepse ="NA";
    String supvsrno ="NA";
    TextView tvbuildonID,tvbuildstrucID,buildinglastID,awcstvId;
    String curDate,curTime;
    String planid ="NA";
    String insid;
    String lstinspctnbuldrep ="NA";
    String lstinspctntoiletrep ="NA";
    String lstinspctnkitchenrep ="NA";
    String lstinspctnpserep ="NA";
    String lstinspctnelectricrep ="NA";
    String lstinspctndrnkwaterrep ="NA";
    String lstinspctnfoodrep ="NA";
    String latisacreport ="NA";
    String buildingdetails, informationoftoilet, informationofkitchen, adequatespaceforpse, electricity, drinkingwater, foodstoreddetails, conditionofequipmentotherawc, shishualoy, snpserved, nutritionalstatusobserved, otherinspection,dbid;
    DatabaseHelper helper;
    String idbuilding ="NA";
    String dbbuilding ="NA";
    String Ebuilttyp ="NA";
    String Ebuiltrunin ="NA";
    String Eownbuildfund ="NA";
    String Erentbuildin ="NA";
    String Eothrgovtbuildin ="NA";
    String Ebuildconditn ="NA";
    String Einspctrcmnt ="NA";
    String ownbuildfundTxt ="NA";
    LinearLayout lv8,newSp;
    int selectedPosition;
    public static final int BUILDING_SYNCED_WITH_SERVER = 1;
    public static final int BUILDING_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiver;
    public static final String DATA_SAVED_BROADCAST_BUILDING = "icdswb.in.buildingdatasaved";
    private BuildingNetworkStateCheckerr buildingNetworkStateCheckerr;
    public static final String BUILDINGDETAILS = "http://icdswb.in/inspection_service/update_bulid_inspctn_dtl.php";
    static String buildingidsync ="";
    String dbdistidsync ="";
    String dbprojectidsync ="";
    String dbsectoridsync = "";
    String dbcenteridsync ="";
    String builttypsync ="";
    String builtruninsync ="";
    String ownbuildfundsync ="";
    String rentbuildinsync ="";
    String othrgovtbuildinsync ="";
    String buildconditndb ="";
    String inspctrcmntDb ="";
    String useridsync ="";
    String insidsync ="";
    String curdatesync ="";
    String curtimesync ="";
    String latisacreportsync ="";
    String lstinspctnbuldrepsync="";
    String buildingstatus ="";
    String DistricName;
    String BUILDINGSTATUS = "1";
    static String TABLESTATUSBUILDING = "1";
    private final int REQ_CODE_SPEECH_BUILDINGCOMMAND = 100;
    ImageButton commandspak;
    RelativeLayout rv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_building_details);
        Intent intent = getIntent();
        water = intent.getStringExtra("water");
        cdponame = intent.getStringExtra("cdponame");
        acdpocont = intent.getStringExtra("acdpocont");
        buildstruc  = intent.getStringExtra("buildstruc");
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
        helperno  = intent.getStringExtra("helperno");
        awcs_adrs = intent.getStringExtra("awcs_adrs");
        foodspace = intent.getStringExtra("foodspace");
        helpernm = intent.getStringExtra("helpernm");
        buildon = intent.getStringExtra("buildon");
        adqutspacepse = intent.getStringExtra("adqutspacepse");
        supvsrno = intent.getStringExtra("supvsrno");
        Log.e("AWCALLVALUE",water+" "+cdponame+" "+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+cdpocontact+" "+workerno+" "+worker_nm+" "+toilet+" "+awcslat+" "+supvsrname+" "+awcsslong+" "+helperno+" "+awcs_adrs+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+" ");
        awcsid =  intent.getStringExtra("awcsid");
        awcscode = intent.getStringExtra("awcscode");
        awcsname = intent.getStringExtra("awcsname");
        dbdistid = intent.getStringExtra("dbdistid");
        dbprojectid  = intent.getStringExtra("dbprojectid");
        dbsectorid = intent.getStringExtra("dbsectorid");
        dbcenterid = intent.getStringExtra("dbcenterid");
        projectnamedb = intent.getStringExtra("projectnamedb");
        districnamedb = intent.getStringExtra("districnamedb");
        sectorrnamedb = intent.getStringExtra("sectorrnamedb");
        centernamedb = intent.getStringExtra("centernamedb");
        currentdate = intent.getStringExtra("currentdate");
        Log.e("DISVALUE",awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+currentdate+" ");
        yncdpo = intent.getStringExtra("yncdpo");
        ynacdpio = intent.getStringExtra("ynacdpio");
        YnSupervisor = intent.getStringExtra("YnSupervisor");
        Ynworker = intent.getStringExtra("Ynworker");
        Yhelper = intent.getStringExtra("Yhelper");
        Ynawcs = intent.getStringExtra("Ynawcs");
        planid = intent.getStringExtra("planid");
        dbid = intent.getStringExtra("dbid");
       // Log.e("dbid",dbid);
        Log.e("bp",planid);
        Log.e("ALLYESNO",yncdpo+" "+ynacdpio+" "+YnSupervisor+" "+Ynworker+" "+Yhelper+" "+Ynawcs+" ");
        insid = intent.getStringExtra("insid");
        Log.e("Binsid",insid);
        lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
        lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
        lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
        lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
        lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
        lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
        lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        Log.e("LAST_BREPO",lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep);
        helper = new DatabaseHelper(this);
        buildinglastID = (TextView)findViewById(R.id.buildinglastID);
        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        db = new DatabaseHelper(this);
        sp3 = (Spinner)findViewById(R.id.sp3);
        spp3 = (Spinner)findViewById(R.id.spp3);
        Gbuildingstatus = (RadioGroup)findViewById(R.id.Gbuildingstatus);
        GrentedbuildigID = (RadioGroup)findViewById(R.id.GrentedbuildigID);
        Grententedbuilding =(RadioGroup)findViewById(R.id.Grententedbuilding);
        othergovbuilding = (RadioGroup)findViewById(R.id.othergovbuilding);
        buildingconditionID =(RadioGroup)findViewById(R.id.buildingconditionID);
        LVownbuilding = (LinearLayout)findViewById(R.id.lv8);
        Lvrentedbuildingin = (LinearLayout)findViewById(R.id.iv9);
        Lvothergovtbuildingin = (LinearLayout)findViewById(R.id.lvv1);
        cmdinformation = (EditText)findViewById(R.id.cmdinformation);
        cmdinformation.setImeOptions(EditorInfo.IME_ACTION_DONE);
        cmdinformation.setRawInputType(InputType.TYPE_CLASS_TEXT);
        //showSoftKeyboard(cmdinformation);
        //saveID =(Button)findViewById(R.id.saveID);
       // saveID.setOnClickListener(this);
        lvv2 = (LinearLayout)findViewById(R.id.lvv2);
        lv8 =(LinearLayout)findViewById(R.id.lv8);
        newSp =(LinearLayout)findViewById(R.id.newSp);
        LVownbuilding.setVisibility(View.GONE);
        Lvothergovtbuildingin.setVisibility(View.GONE);
        Lvrentedbuildingin.setVisibility(View.GONE);
        rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });


        String RB = "RB";
        rentedbuildingID = (RadioButton)findViewById(R.id.rentedbuildingID);
        if (RBB.equals(RB)){
            //rentedbuildingID.setChecked(true);
        }
        else {
            Lvrentedbuildingin.setVisibility(View.GONE);
        }
        kutchaID = (RadioButton)findViewById(R.id.kutchaID);
        puccaID = (RadioButton)findViewById(R.id.puccaID);
        ownbuildingID = (RadioButton)findViewById(R.id.ownbuildingID);
        othergovebuildingID = (RadioButton)findViewById(R.id.othergovebuildingID);
        privatebuildingID =(RadioButton)findViewById(R.id.privatebuildingID);
        cludngoID = (RadioButton)findViewById(R.id.cludngoID);
        cooperativeID= (RadioButton)findViewById(R.id.cooperativeID);
        privateID = (RadioButton)findViewById(R.id.privateID);
        otherID =(RadioButton)findViewById(R.id.otherID);
        primaryschoolID = (RadioButton)findViewById(R.id.primaryschoolID);
        sskID = (RadioButton)findViewById(R.id.sskID);
        mskID = (RadioButton)findViewById(R.id.mskID);
        madrashasskId =(RadioButton)findViewById(R.id.madrashasskId);
        madrashamskId = (RadioButton)findViewById(R.id.madrashamskId);
        otherId =(RadioButton)findViewById(R.id.otherId);
        goodId =(RadioButton)findViewById(R.id.goodId);
        averageId =(RadioButton)findViewById(R.id.averageId);
        poorId = (RadioButton)findViewById(R.id.poorId);
        tvbuildonID =(TextView)findViewById(R.id.tvbuildonID);
        tvbuildstrucID = (TextView)findViewById(R.id.tvbuildstrucID);
        latisacreport = buildon+" "+buildstruc;
        Gbuildingstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.kutchaID) {
                    kutcha = "K";
                    builttyp = "K";
                    Log.e("BUILDINGSTATUS",kutcha);
                } else if(checkedId == R.id.puccaID) {
                    pucca = "P";
                    builttyp = "P";
                    Log.e("BUILDINGSTATUS",pucca);
                } else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        GrentedbuildigID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.ownbuildingID){
//                    cludngoID.setChecked(false);
//                    cooperativeID.setChecked(false);
//                    privateID.setChecked(false);
//                    otherID.setChecked(false);
//                    primaryschoolID.setChecked(false);
//                    sskID.setChecked(false);
//                    mskID.setChecked(false);
//                    madrashasskId.setChecked(false);
//                    madrashamskId.setChecked(false);
//                    otherId.setChecked(false);
                    ownbuilding = "OB";
                    builtrunin = "OB";
                    if (isNetworkAvailable()){
                        if (dbbuilding.equals("0")) {
                            ////// own building fund/////////
                           // lv8.setVisibility(View.GONE);
                          //  newSp.setVisibility(View.GONE);
                            ////// own building fund/////////
                            LVownbuilding.setVisibility(View.VISIBLE);
                            lvv2.setVisibility(View.VISIBLE);
                            Lvrentedbuildingin.setVisibility(View.GONE);
                            Lvothergovtbuildingin.setVisibility(View.GONE);
                            Log.e("HI","HI1");

                        }
                        else {
                            ////// own building fund/////////
                          //  lv8.setVisibility(View.GONE);
                           // newSp.setVisibility(View.GONE);
                            ////// own building fund/////////
                            LVownbuilding.setVisibility(View.VISIBLE);
                            lvv2.setVisibility(View.VISIBLE);
                            Lvrentedbuildingin.setVisibility(View.GONE);
                            Lvothergovtbuildingin.setVisibility(View.GONE);
                            Log.e("HI","HI2");
                        }
                    }
                    else {
                        ////// own building fund/////////
                      //  lv8.setVisibility(View.GONE);
                       // newSp.setVisibility(View.GONE);
                        ////// own building fund/////////
                        LVownbuilding.setVisibility(View.VISIBLE);
                        lvv2.setVisibility(View.VISIBLE);
                        Lvrentedbuildingin.setVisibility(View.GONE);
                        Lvothergovtbuildingin.setVisibility(View.GONE);
                        Log.e("HI","HI3");

                    }
                    Log.e("BUILDINGRUNIN",ownbuilding+" "+" "+builtrunin);
                }
                else if (checkedId== R.id.rentedbuildingID){
               //     spinnerMap.clear();
//                    cludngoID.setChecked(false);
//                    cooperativeID.setChecked(false);
//                    privateID.setChecked(false);
//                    otherID.setChecked(false);
//                    primaryschoolID.setChecked(false);
//                    sskID.setChecked(false);
//                    mskID.setChecked(false);
//                    madrashasskId.setChecked(false);
//                    madrashamskId.setChecked(false);
//                    otherId.setChecked(false);
                    rentedbuilding = "RB";
                    builtrunin = "RB";
                    ////// own building fund/////////
                   // newSp.setVisibility(View.GONE);
                   // lv8.setVisibility(View.GONE);
                    ////// own building fund/////////
                    Lvrentedbuildingin.setVisibility(View.VISIBLE);
                    lvv2.setVisibility(View.VISIBLE);
                    LVownbuilding.setVisibility(View.GONE);
                    Lvothergovtbuildingin.setVisibility(View.GONE);


                    Log.e("BUILDINGRUNIN",rentedbuilding+" "+" "+builtrunin);
                }
                else if (checkedId== R.id.othergovebuildingID){
                //    spinnerMap.clear();
//                    cludngoID.setChecked(false);
//                    cooperativeID.setChecked(false);
//                    privateID.setChecked(false);
//                    otherID.setChecked(false);
//                    primaryschoolID.setChecked(false);
//                    sskID.setChecked(false);
//                    mskID.setChecked(false);
//                    madrashasskId.setChecked(false);
//                    madrashamskId.setChecked(false);
//                    otherId.setChecked(false); 53472113012020
                    othergovtbuilding = "OGB";
                    builtrunin = "OGB";
                    ////// own building fund/////////
                  //  newSp.setVisibility(View.GONE);
                 //   lv8.setVisibility(View.GONE);
                    ////// own building fund/////////
                    Lvothergovtbuildingin.setVisibility(View.VISIBLE);
                    lvv2.setVisibility(View.VISIBLE);
                    LVownbuilding.setVisibility(View.GONE);
                    Lvrentedbuildingin.setVisibility(View.GONE);
                    Log.e("BUILDINGRUNIN",othergovtbuilding+" "+" "+builtrunin);
                }
                else if (checkedId== R.id.privatebuildingID){
//                    spinnerMap.clear();
//                    cludngoID.setChecked(false);
//                    cooperativeID.setChecked(false);
//                    privateID.setChecked(false);
//                    otherID.setChecked(false);
//                    primaryschoolID.setChecked(false);
//                    sskID.setChecked(false);
//                    mskID.setChecked(false);
//                    madrashasskId.setChecked(false);
//                    madrashamskId.setChecked(false);
//                    otherId.setChecked(false);
                    privatenonrentedbuilding = "PNR";
                    builtrunin = "PNR";
                    ////// own building fund/////////
                   // newSp.setVisibility(View.GONE);
                  //  lv8.setVisibility(View.GONE);
                    ////// own building fund/////////
                    LVownbuilding.setVisibility(View.GONE);
                    Lvrentedbuildingin.setVisibility(View.GONE);
                    Lvothergovtbuildingin.setVisibility(View.GONE);
                    Log.e("BUILDINGRUNIN",privatenonrentedbuilding+" "+" "+builtrunin);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        Grententedbuilding.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.cludngoID){
                    clubngo = "NGO";
                    rentbuildin = "NGO";
                    Log.e("RENTEDBUILDINGIN",clubngo);
                }
                else if (checkedId== R.id.cooperativeID){
                    cooperative = "CORP";
                    rentbuildin = "CORP";
                    Log.e("RENTEDBUILDINGIN",cooperative);
                }
                else if (checkedId== R.id.privateID){
                    privaterented = "PVT";
                    rentbuildin = "PVT";

                }
                else if (checkedId== R.id.otherID){
                    otherrented = "OTH";
                    rentbuildin = "OTH";
                    Log.e("RENTEDBUILDINGIN",otherrented);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        othergovbuilding.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
              if (checkedId== R.id.primaryschoolID){
                  othrgovtbuildin = "PRY";
                  Log.e("OTHERGOVTBUILDING",othrgovtbuildin);
              }
              else if (checkedId== R.id.sskID){
                  ssk = "SSK";
                  othrgovtbuildin = "SSK";
                  Log.e("OTHERGOVTBUILDING",ssk);
              }
              else if (checkedId== R.id.mskID){
                  msk = "MSK";
                  othrgovtbuildin = "MSK";
                  Log.e("OTHERGOVTBUILDING",msk);
              }
              else if (checkedId== R.id.madrashasskId){
                  madrashassk = "MSSK";
                  othrgovtbuildin = "MSSK";
                  Log.e("OTHERGOVTBUILDING",madrashassk);
              }
              else if (checkedId== R.id.madrashamskId){
                  madrashamsk = "MMSK";
                  othrgovtbuildin = "MMSK";
                  Log.e("OTHERGOVTBUILDING",madrashamsk);
              }
              else if (checkedId== R.id.otherId){
                  Othergovt = "OTH";
                  othrgovtbuildin = "OTH";
                  Log.e("OTHERGOVTBUILDING",Othergovt);
              }
              else {

                  Toast.makeText(getApplicationContext(), "No Selected",
                          Toast.LENGTH_SHORT).show();
              }

            }
        });

        buildingconditionID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.goodId){
                    ggood = "good";
                    buildconditn = "good";
                    Log.e("BUILDINGCONDITION",ggood);
                }
                else if (checkedId== R.id.averageId){
                    aavg = "avg";
                    buildconditn = "avg";
                    Log.e("BUILDINGCONDITION",aavg);
                }
                else if (checkedId== R.id.poorId){
                    ppoor = "poor";
                    buildconditn = "poor";
                    Log.e("BUILDINGCONDITION",ppoor);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Selected",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        sp3.setOnItemSelectedListener(this);
        ownbuildingsetgets = new ArrayList<>();
        ownbildName = new ArrayList<>();
        ownbildID = new ArrayList<>();
        list = new ArrayList<>();
        // Cursor cursor = helper.getReadableDatabase().
        // rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
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
                idbuilding = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                dbbuilding = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILDINGDETAILS));
                Log.e("DBUILDING"," "+idbuilding+" "+dbbuilding);
            }
            while (cursor.moveToNext());
        }

       String query = "SELECT * FROM tablebuildingsync WHERE insidsync=" + insid;
    //    String query = "SELECT * FROM " + TABLE_BUILDINGSYNC + " where " + TABLE_INSIDSYNC + "=" +insid+ " and " +TABLE_STATUSBUILDING+ "=" +BUILDINGSTATUS;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor  cc = db.rawQuery(query,null);
        if (cc.moveToFirst()) {
            do {
                buildingidsync = cc.getString(cc.getColumnIndex(TABLE_BUILDINGIDSYNC));
                dbdistidsync =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DBDISTIDSYNC));
                dbprojectidsync =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DBPROJECTIDSYNC));
                dbsectoridsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DBSECTORIDSYNC));
                dbcenteridsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DBCENTERIDSYNC));
                builttypsync =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_BUILTTYP));
                builtruninsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_BUILTRUNIN));
                ////// own building fund/////////
               // ownbuildfundsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_OWNBUILDFUND));
                ////// own building fund/////////
                rentbuildinsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_RENTBUILDIN));
                othrgovtbuildinsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_OTHRGOVTBUILDIN));
                buildconditndb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_BUILDCONDITN));
                inspctrcmntDb = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_INSPCTRCMNT));
                useridsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USERIDSYNC));
                insidsync = cc.getString(cc.getColumnIndex(TABLE_INSIDSYNC));
                curdatesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CURDATE));
                curtimesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CURTIME));
                latisacreportsync =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LATISACREPORT));
                lstinspctnbuldrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LSTINSPCTNBULDREP));
                buildingstatus = cc.getString(cc.getColumnIndex(TABLE_STATUSBUILDING));
                Log.e("BUILDINGEDITLOG"," "+buildingidsync+" "+dbdistidsync+" "+dbprojectidsync+" "+dbsectoridsync+" "+dbcenteridsync+" "+builttypsync+" "+builtruninsync+" "
                        +ownbuildfundsync+" "+rentbuildinsync+" "+othrgovtbuildinsync+" "+buildconditndb+" "+inspctrcmntDb+" "+useridsync+" "+insidsync+" "+curdatesync+" "+curtimesync+" "+latisacreportsync+" "+lstinspctnbuldrepsync+" "+buildingstatus);
            }
            while (cc.moveToNext());
        }
//        if (buildingstatus.equals("1")){
//            deleteRow(buildingstatus);
//        }
//        else {
//
//        }

         if (isNetworkAvailable()) {
             tvbuildonID.setText(buildon);
             tvbuildstrucID.setText(buildstruc);
             buildinglastID.setText(lstinspctnbuldrep);
             if (dbbuilding.equals("0")) {
                 ownBuildingData();
                 ownBulding();
                 loadSpinnerData();
                 ////// own building fund/////////
                // lv8.setVisibility(View.GONE);
                // newSp.setVisibility(View.GONE);
                 ////// own building fund/////////
             } else {
                // lv8.setVisibility(View.GONE);
               //  newSp.setVisibility(View.GONE);
                 spp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         selectedPosition = spp3.getSelectedItemPosition();
                         Log.e("SPP", String.valueOf(selectedPosition));
                         ownbuildfund = spinnerMap.get(spp3.getSelectedItemPosition());
                        // Toast.makeText(parent.getContext(), ownbuildfund, Toast.LENGTH_LONG).show();
                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> parent) {

                     }

                 });
                 ownBulding();
                 BuildingDetailsEdit();
             }

         }
         else {
             tvbuildonID.setText(buildon);
             tvbuildstrucID.setText(buildstruc);
             buildinglastID.setText(lstinspctnbuldrep);
             ////// own building fund/////////
             //lv8.setVisibility(View.GONE);
             //newSp.setVisibility(View.GONE);
             ////// own building fund/////////
             ownBulding();
             spp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     selectedPosition = spp3.getSelectedItemPosition();
                     Log.e("SPP", String.valueOf(selectedPosition));
                     ownbuildfund = spinnerMap.get(spp3.getSelectedItemPosition());
                     Toast.makeText(parent.getContext(), ownbuildfund, Toast.LENGTH_LONG).show();
                     DistricName = spp3.getSelectedItem().toString();
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parent) {

                 }

             });
             ////// own building fund/////////

//             spinnerArray = new String[ownbuildingsetgets.size()];
//             spinnerMap = new HashMap<Integer, String>();
//             for (int i = 0; i < ownbuildingsetgets.size(); i++)
//             {
//                 spinnerMap.put(i,ownbildID.get(i));
//                 spinnerArray[i] = ownbildName.get(i);
//             }
//             String[] arraySpinner = new String[] {Eownbuildfund};
//             ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray);
//             adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//             spp3.setAdapter(adapter);
//             String fundname = ownbuildfundsync;
//             String fund = fundname.replaceAll("[0-9]","");
//             Log.e("FUNDVALUE",fund);
//             String compareValue = fund;
////             Log.e("ownbuildfundsync",ownbuildfundsync);
//             if (compareValue != null) {
//                 int spinnerPosition = adapter.getPosition(compareValue);
//                 spp3.setSelection(spinnerPosition);
//             }

             ////// own building fund/////////

             if (builttypsync.equals("K")){
                 kutchaID.setChecked(true);
             }
             else if (builttypsync.equals("P")){
                 puccaID.setChecked(true);
             }
             else {
                 kutchaID.setChecked(false);
                 puccaID.setChecked(false);
             }
             if (builtruninsync.equals("OB")){
                 ownbuildingID.setChecked(true);
             }
             else if (builtruninsync.equals("RB")){
                 rentedbuildingID.setChecked(true);
             }
             else if (builtruninsync.equals("OGB")){
                 othergovebuildingID.setChecked(true);
             }
             else if (builtruninsync.equals("PNR")){
                 privatebuildingID.setChecked(true);
             }
             else {
                 ownbuildingID.setChecked(false);
                 rentedbuildingID.setChecked(false);
                 othergovebuildingID.setChecked(false);
                 privatebuildingID.setChecked(false);
             }

             if (rentbuildinsync.equals("NGO")){
                 cludngoID.setChecked(true);
             }
             else if (rentbuildinsync.equals("CORP")){
                 cooperativeID.setChecked(true);
             }
             else if (rentbuildinsync.equals("PVT")){
                 privateID.setChecked(true);
             }
             else if (rentbuildinsync.equals("OTH")){
                 otherID.setChecked(true);
             }
             else {
                 cludngoID.setChecked(false);
                 cooperativeID.setChecked(false);
                 privateID.setChecked(false);
                 otherID.setChecked(false);
             }

             if (othrgovtbuildinsync.equals("PRY")){
                 primaryschoolID.setChecked(true);
             }
             else if (othrgovtbuildinsync.equals("SSK")){
                 sskID.setChecked(true);
             }
             else if (othrgovtbuildinsync.equals("MSK")){
                 mskID.setChecked(true);
             }
             else if (othrgovtbuildinsync.equals("MSSK")){
                 madrashasskId.setChecked(true);
             }
             else if (othrgovtbuildinsync.equals("MMSK")){
                 madrashamskId.setChecked(true);
             }
             else if (othrgovtbuildinsync.equals("OTH")){
                 otherId.setChecked(true);
             }
             else {
                 primaryschoolID.setChecked(false);
                 sskID.setChecked(false);
                 mskID.setChecked(false);
                 madrashasskId.setChecked(false);
                 madrashamskId.setChecked(false);
                 otherId.setChecked(false);
             }

             if (buildconditndb.equals("good")){
                 goodId.setChecked(true);
             }
             else if (buildconditndb.equals("avg")){
                 averageId.setChecked(true);
             }
             else if (buildconditndb.equals("poor")){
                 poorId.setChecked(true);
             }
             else {
                 goodId.setChecked(false);
                 averageId.setChecked(false);
                 poorId.setChecked(false);
             }
             cmdinformation.setText(inspctrcmntDb);
         }
        initToolBar();
         broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
         registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST_BUILDING));
        buildingNetworkStateCheckerr = new BuildingNetworkStateCheckerr();
        registerReceiver(buildingNetworkStateCheckerr, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.homeID:
                                SaveHome();
                                break;
                            case R.id.saveID:
                                BuildingDetailsUpdate();
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
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void showSoftKeyboard(View view) {
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }
    private void promptSpeechHelperNumber(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_command));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_BUILDINGCOMMAND);
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
            case REQ_CODE_SPEECH_BUILDINGCOMMAND: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    cmdinformation.setText(result.get(0));
                }
                break;
            }
        }
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
       // setSupportActionBar(toolbar);
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


    public void ownBuildingData() {
        Cursor res = db.getownbuildingdetails();
        if(res.getCount() == 0) {
            // show message
            Log.e("Error","Nothing found");
          //  Toast.makeText(BuildingDetailsActivity.this,"NO RECORD FUND", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("idfun :"+ res.getString(0)+"\n");
            buffer.append("fid :"+ res.getString(1)+"\n");
            buffer.append("fname :"+ res.getString(2)+"\n");
        }
        Log.e("ownbuildingdata",buffer.toString());
    }

    private void ownBulding(){
        Cursor cursor = db.getownbuildingdetails();
        if (cursor.moveToFirst()){
            do {
                     ownbuildingsetget = new Ownbuildingsetget(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_IDFUN)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_NAME))
                     );

                ownbuildingsetgets.add(ownbuildingsetget);
                ownbildName.add(ownbuildingsetget.getFname());
                ownbildID.add(ownbuildingsetget.getFid());

            }
            while (cursor.moveToNext());
        }
    }
    private void loadSpinnerData() {
        // database handler
       //  db = new DatabaseHelper(getApplicationContext());

        // Spinner Drop down elements
      //  List<String> lables = db.getAllLabels();
         spinnerArray = new String[ownbuildingsetgets.size()];
         spinnerMap = new HashMap<Integer, String>();
        for (int i = 0; i < ownbuildingsetgets.size(); i++)
        {
            spinnerMap.put(i,ownbildID.get(i));
            spinnerArray[i] = ownbildName.get(i);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        Log.e("Namelist", String.valueOf(ownbildName));
        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp3.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//      Log.e("SPP", String.valueOf(+position));
//        String item = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),item, Toast.LENGTH_LONG).show();
        ownbuildfund = spinnerMap.get(sp3.getSelectedItemPosition());
        Toast.makeText(parent.getContext(),ownbuildfund+ "Hi", Toast.LENGTH_LONG).show();
        DistricName = sp3.getSelectedItem().toString();
        Log.e("DISID",DistricName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void BuildingDetailsUpdate(){
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
        cmdinformation.setFilters(new InputFilter[] { filter });
        inspctrcmnt = cmdinformation.getText().toString().trim();
        if (TextUtils.isEmpty(inspctrcmnt)) {
            if (kutchaID.isChecked() || puccaID.isChecked()){
                if (ownbuildingID.isChecked() || rentedbuildingID.isChecked() || othergovebuildingID.isChecked() || privatebuildingID.isChecked()){
                    if(ownbuildingID.isChecked()){
                        ////// own building fund/////////

//                if (ownbuildfund.equals("0")){
//                    Toast.makeText(BuildingDetailsActivity.this,"SELECT OWN BILDING",Toast.LENGTH_SHORT).show();
//                }
//                else {
//
//                }

                        ////// own building fund/////////

                        if (goodId.isChecked() || averageId.isChecked() || poorId.isChecked()){
                            cmdinformation.setError("Please enter Command");
                            cmdinformation.requestFocus();
                        }
                        else {
                            Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING CONDITION",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT BUILDING CONDITION";
                            createDialog(title,msg);

                        }

                    }
                    if (rentedbuildingID.isChecked()){
                        if (cludngoID.isChecked() || cooperativeID.isChecked() || privateID.isChecked() || otherID.isChecked()){
                            if (goodId.isChecked() || averageId.isChecked() || poorId.isChecked()){
                                cmdinformation.setError("Please enter Command");
                                cmdinformation.requestFocus();
                            }
                            else {
                                Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING CONDITION",Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT BUILDING CONDITION";
                                createDialog(title,msg);
                            }
                        }
                        else {
                            Toast.makeText(BuildingDetailsActivity.this,"SELECT RENTED BUILDING IN",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT RENTED BUILDING IN";
                            createDialog(title,msg);
                        }
                    }
                    if (othergovebuildingID.isChecked()){
                        if (primaryschoolID.isChecked() || sskID.isChecked() || mskID.isChecked() || madrashasskId.isChecked() || madrashamskId.isChecked() || otherId.isChecked()){
                            if (goodId.isChecked() || averageId.isChecked() || poorId.isChecked()){
                                cmdinformation.setError("Please enter Command");
                                cmdinformation.requestFocus();
                            }
                            else {
                                Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING CONDITION",Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT BUILDING CONDITION";
                                createDialog(title,msg);
                            }
                        }
                        else {
                            Toast.makeText(BuildingDetailsActivity.this,"SELECT OTHER GOVT BUILDING IN",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT OTHER GOVT BUILDING IN";
                            createDialog(title,msg);
                        }
                    }
                    if (privatebuildingID.isChecked()){
                        if (goodId.isChecked() || averageId.isChecked() || poorId.isChecked()){
                            cmdinformation.setError("Please enter Command");
                            cmdinformation.requestFocus();
                        }
                        else {
                            Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING CONDITION",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT BUILDING CONDITION";
                            createDialog(title,msg);
                        }
                    }
                    else {

                    }


                }
                else {
                    Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING RUNS IN",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT BUILDING RUNS IN";
                    createDialog(title,msg);
                }
            }
            else {
                Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING STATUS",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT BUILDING STATUS";
                createDialog(title,msg);
            }


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
        Log.e("TEST","0");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BUILDINGDETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Building"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("BObJ"," "+array);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                Log.e("message_building"," "+message);
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET buildingdetails='1' WHERE allinspactionid=" +insid);
       //                       db.AllinspactionUpdate(dbid,"1",informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,insid);
                                if (builtrunin.equals("OB")){
                                    DistricNamee = ownbuildfund+DistricName;
                                    rentbuildin = "null";
                                    othrgovtbuildin = "null";
                                    Log.e("DISID",DistricNamee);
                                    syncBuildingsaveDatabase(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                            rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_SYNCED_WITH_SERVER);
                                    UpdateListCheck();
                                }
                                else {
                                   // Toast.makeText(BuildingDetailsActivity.this,DistricNamee,Toast.LENGTH_SHORT).show();
                                }
                                if (builtrunin.equals("RB")){
                                    DistricNamee ="null";
                                    othrgovtbuildin = "null";
                                    syncBuildingsaveDatabase(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                           rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_SYNCED_WITH_SERVER);
                                    UpdateListCheck();
                                }
                                else {
                                    //Toast.makeText(BuildingDetailsActivity.this,rentbuildin,Toast.LENGTH_SHORT).show();
                                }
                                if (builtrunin.equals("OGB")){
                                    rentbuildin = "null";
                                    DistricNamee ="null";
                                    syncBuildingsaveDatabase(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                            rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_SYNCED_WITH_SERVER);
                                    UpdateListCheck();
                                }
                                else {
                                    //Toast.makeText(BuildingDetailsActivity.this,othrgovtbuildin,Toast.LENGTH_SHORT).show();
                                }

                                if (builtrunin.equals("PNR")){
                                    DistricNamee ="null";
                                    rentbuildin = "null";
                                    othrgovtbuildin = "null";
                                    syncBuildingsaveDatabase(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                           rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_SYNCED_WITH_SERVER);
                                    UpdateListCheck();
                                }
                                else {
                                  //  Toast.makeText(BuildingDetailsActivity.this,builtrunin,Toast.LENGTH_SHORT).show();
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
                        if (builtrunin.equals("OB")){
                            DistricNamee = ownbuildfund+DistricName;
                            rentbuildin = "null";
                            othrgovtbuildin = "null";
                            Log.e("DISID",DistricNamee);
                            syncBuildingsaveDatabase(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_NOT_SYNCED_WITH_SERVER);
                            UpdateListCheck();
                        }
                        else {
                          //  Toast.makeText(BuildingDetailsActivity.this,DistricNamee,Toast.LENGTH_SHORT).show();
                        }

                        if (builtrunin.equals("RB")){
                            DistricNamee ="null";
                            othrgovtbuildin = "null";
                            syncBuildingsaveDatabase(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                  rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_NOT_SYNCED_WITH_SERVER);
                            UpdateListCheck();
                        }
                        else {
                           // Toast.makeText(BuildingDetailsActivity.this,rentbuildin,Toast.LENGTH_SHORT).show();
                        }
                        if (builtrunin.equals("OGB")){
                            rentbuildin = "null";
                            DistricNamee ="null";
                            syncBuildingsaveDatabase(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_NOT_SYNCED_WITH_SERVER);
                            UpdateListCheck();
                        }
                        else {
                            //Toast.makeText(BuildingDetailsActivity.this,othrgovtbuildin,Toast.LENGTH_SHORT).show();
                        }

                        if (builtrunin.equals("PNR")){
                            DistricNamee ="null";
                            rentbuildin = "null";
                            othrgovtbuildin = "null";
                            syncBuildingsaveDatabase(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_NOT_SYNCED_WITH_SERVER);
                            UpdateListCheck();
                        }
                        else {
                           // Toast.makeText(BuildingDetailsActivity.this,builtrunin,Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("district",dbdistid);
                params.put("project",dbprojectid);
                params.put("sector",dbsectorid);
                params.put("centre",dbcenterid);
                params.put("built_typ",builttyp);
                params.put("built_run_in",builtrunin);
//                if (builtrunin.equals("OB")){
//                    //// own building fund/////////
//                    params.put("own_build_fund",ownbuildfund);
//                    //// own building fund/////////
//                }
               if (builtrunin.equals("RB")){
                    params.put("rent_build_in",rentbuildin);
                }
                else if (builtrunin.equals("OGB")){
                    params.put("othr_govt_build_in",othrgovtbuildin);
                    Log.e("rentbuildin",""+builtrunin+" "+rentbuildin+" "+othrgovtbuildin);
                }
                params.put("build_conditn",buildconditn);
                params.put("inspctr_cmnt",inspctrcmnt);
                params.put("user_id",userID);
                params.put("inspctn_id",insid);
                params.put("inspctn_date",curDate);
                params.put("inspctn_time",curTime);
                params.put("last_isac_rep",latisacreport);
                params.put("last_inspctn_rep",lstinspctnbuldrep);
                Log.e("BUILDINGUPDATE",dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+" "+builttyp+" "+builtrunin+" "
                        +"own_build_fund off fild"+ownbuildfund+" "+rentbuildin+" "+othrgovtbuildin+" "+buildconditn+" "+inspctrcmnt+" "+userID+" "+curDate+" "+curTime+" "+insid+" "+latisacreport+" "+lstinspctnbuldrep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

     private void syncBuildingsaveDatabase(String dbdistid,String dbprojectid,String dbsectorid,String dbcenterid,String builttyp,String builtrunin,
                                           String rentbuildin,String othrgovtbuildin,String buildconditn,String inspctrcmnt,String userID,String insid,String curDate,String curTime,String latisacreport,String lstinspctnbuldrep, int buildingstatus){
//        boolean isUpdate =     db.BUILDING(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,DistricNamee,rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
//        db = new DatabaseHelper(getApplicationContext());
//        if (isUpdate==true){
//            db.BUILDINGUPDATE(dbid,dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,DistricNamee,rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
//        }
//        else {
//
//        }


         if (dbbuilding.equals("0")){
             db.BUILDING(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                     rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
         }
         else {
             db.BUILDINGUPDATE(dbid,dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                     rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);

         }



        //   db.BUILDING(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,DistricNamee,rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
//         db.BUILDINGUPDATE(dbid,dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,DistricNamee,rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
        Log.e("DataSave", " "+dbid+ " "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+builttyp+" "+builtrunin+" "+DistricNamee+" "+rentbuildin+" "+othrgovtbuildin+" "+buildconditn+" "+inspctrcmnt+" "+userID+" "+insid+" "+curDate+" "+curTime+" "+latisacreport+" "+lstinspctnbuldrep+" "+buildingstatus);
         DataSendNEXTActivity();

     }
     public void UpdateListCheck(){
         helper = new DatabaseHelper(getApplicationContext());
         SQLiteDatabase dbb = helper.getReadableDatabase();
         dbb.execSQL("UPDATE insflag SET buildingdetails='1' WHERE allinspactionid=" +insid);
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

    private void BuildingDetailsEdit(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BUILDINGDETAILSEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("BUILDINGEDIT"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray jsonArray = object.getJSONArray("awcs_build_dtl");
                            for (int i = 0;i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Ebuilttyp = jsonObject.getString("built_typ");
                                Ebuiltrunin = jsonObject.getString("built_run_in");
                                Eownbuildfund = jsonObject.getString("own_build_fund");
                                Erentbuildin = jsonObject.getString("rent_build_in");
                                Eothrgovtbuildin = jsonObject.getString("othr_govt_build_in");
                                Ebuildconditn = jsonObject.getString("build_conditn");
                                Einspctrcmnt = jsonObject.getString("inspctr_cmnt");
                               // ownbuildfundTxt = jsonObject.getString("own_build_fund_Txt");
                                Log.e("BUILDINGEDITLOG"," "+Ebuilttyp+" "+Ebuiltrunin+" "+Eownbuildfund+" "+Erentbuildin+" "+Eothrgovtbuildin+" "+Ebuildconditn+" "+Einspctrcmnt+" "+ownbuildfundTxt);
                                cmdinformation.setText(Einspctrcmnt);
                                if (Ebuilttyp.equals("K")){
                                    kutchaID.setChecked(true);
                                }
                                else if (Ebuilttyp.equals("P")){
                                    puccaID.setChecked(true);
                                }
                                else {
                                    kutchaID.setChecked(false);
                                    puccaID.setChecked(false);
                                }
                                if (Ebuiltrunin.equals("OB")){
                                    ownbuildingID.setChecked(true);
                                }
                                else if (Ebuiltrunin.equals("RB")){
                                    rentedbuildingID.setChecked(true);
                                }
                                else if (Ebuiltrunin.equals("OGB")){
                                    othergovebuildingID.setChecked(true);
                                }
                                else if (Ebuiltrunin.equals("PNR")){
                                    privatebuildingID.setChecked(true);
                                }
                                else {
                                    ownbuildingID.setChecked(false);
                                    rentedbuildingID.setChecked(false);
                                    othergovebuildingID.setChecked(false);
                                    privatebuildingID.setChecked(false);
                                }

                                if (Erentbuildin.equals("NGO")){
                                    cludngoID.setChecked(true);
                                }
                                else if (Erentbuildin.equals("CORP")){
                                    cooperativeID.setChecked(true);
                                }
                                else if (Erentbuildin.equals("PVT")){
                                    privateID.setChecked(true);
                                }
                                else if (Erentbuildin.equals("OTH")){
                                    otherID.setChecked(true);
                                }
                                else {
                                    cludngoID.setChecked(false);
                                    cooperativeID.setChecked(false);
                                    privateID.setChecked(false);
                                    otherID.setChecked(false);
                                }

                                if (Eothrgovtbuildin.equals("PRY")){
                                    primaryschoolID.setChecked(true);
                                }
                                else if (Eothrgovtbuildin.equals("SSK")){
                                    sskID.setChecked(true);
                                }
                                else if (Eothrgovtbuildin.equals("MSK")){
                                    mskID.setChecked(true);
                                }
                                else if (Eothrgovtbuildin.equals("MSSK")){
                                    madrashasskId.setChecked(true);
                                }
                                else if (Eothrgovtbuildin.equals("MMSK")){
                                    madrashamskId.setChecked(true);
                                }
                                else if (Eothrgovtbuildin.equals("OTH")){
                                    otherId.setChecked(true);
                                }
                                else {
                                    primaryschoolID.setChecked(false);
                                    sskID.setChecked(false);
                                    mskID.setChecked(false);
                                    madrashasskId.setChecked(false);
                                    madrashamskId.setChecked(false);
                                    otherId.setChecked(false);
                                }

                                 if (Ebuildconditn.equals("good")){
                                    goodId.setChecked(true);
                                 }
                                 else if (Ebuildconditn.equals("avg")){
                                    averageId.setChecked(true);
                                 }
                                else if (Ebuildconditn.equals("poor")){
                                    poorId.setChecked(true);
                                 }
                                else {
                                    goodId.setChecked(false);
                                    averageId.setChecked(false);
                                    poorId.setChecked(false);
                                }
                            }
                            ////// own building fund/////////
//                            spinnerArray = new String[ownbuildingsetgets.size()];
//                            spinnerMap = new HashMap<Integer, String>();
//                            for (int i = 0; i < ownbuildingsetgets.size(); i++)
//                            {
//                                spinnerMap.put(i,ownbildID.get(i));
//                                spinnerArray[i] = ownbildName.get(i);
//                            }
//                            String[] arraySpinner = new String[] {Eownbuildfund};
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray);
//                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            spp3.setAdapter(adapter);
//                            String compareValue = ownbuildfundTxt;
//                            if (compareValue != null) {
//                                int spinnerPosition = adapter.getPosition(compareValue);
//                                spp3.setSelection(spinnerPosition);
//                            }

                            ////// own building fund/////////
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
    public void DataSendNEXTActivity(){
        Intent intent = new Intent(BuildingDetailsActivity.this, InformationToiletActivity.class);
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
    public void SaveHome(){
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
        cmdinformation.setFilters(new InputFilter[] { filter });
        inspctrcmnt = cmdinformation.getText().toString().trim();
        if (TextUtils.isEmpty(inspctrcmnt)) {
            if (kutchaID.isChecked() || puccaID.isChecked()){
                if (ownbuildingID.isChecked() || rentedbuildingID.isChecked() || othergovebuildingID.isChecked() || privatebuildingID.isChecked()){
                    if(ownbuildingID.isChecked()){
                        ////// own building fund/////////

//                if (ownbuildfund.equals("0")){
//                    Toast.makeText(BuildingDetailsActivity.this,"SELECT OWN BILDING",Toast.LENGTH_SHORT).show();
//                }
//                else {
//
//                }

                        ////// own building fund/////////

                        if (goodId.isChecked() || averageId.isChecked() || poorId.isChecked()){
                            cmdinformation.setError("Please enter Command");
                            cmdinformation.requestFocus();
                        }
                        else {
                            Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING CONDITION",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT BUILDING CONDITION";
                            createDialog(title,msg);

                        }

                    }
                    if (rentedbuildingID.isChecked()){
                        if (cludngoID.isChecked() || cooperativeID.isChecked() || privateID.isChecked() || otherID.isChecked()){
                            if (goodId.isChecked() || averageId.isChecked() || poorId.isChecked()){
                                cmdinformation.setError("Please enter Command");
                                cmdinformation.requestFocus();
                            }
                            else {
                                Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING CONDITION",Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT BUILDING CONDITION";
                                createDialog(title,msg);
                            }
                        }
                        else {
                            Toast.makeText(BuildingDetailsActivity.this,"SELECT RENTED BUILDING IN",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT RENTED BUILDING IN";
                            createDialog(title,msg);
                        }
                    }
                    if (othergovebuildingID.isChecked()){
                        if (primaryschoolID.isChecked() || sskID.isChecked() || mskID.isChecked() || madrashasskId.isChecked() || madrashamskId.isChecked() || otherId.isChecked()){
                            if (goodId.isChecked() || averageId.isChecked() || poorId.isChecked()){
                                cmdinformation.setError("Please enter Command");
                                cmdinformation.requestFocus();
                            }
                            else {
                                Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING CONDITION",Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT BUILDING CONDITION";
                                createDialog(title,msg);
                            }
                        }
                        else {
                            Toast.makeText(BuildingDetailsActivity.this,"SELECT OTHER GOVT BUILDING IN",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT OTHER GOVT BUILDING IN";
                            createDialog(title,msg);
                        }
                    }
                    if (privatebuildingID.isChecked()){
                        if (goodId.isChecked() || averageId.isChecked() || poorId.isChecked()){
                            cmdinformation.setError("Please enter Command");
                            cmdinformation.requestFocus();
                        }
                        else {
                            Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING CONDITION",Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT BUILDING CONDITION";
                            createDialog(title,msg);
                        }
                    }
                    else {

                    }


                }
                else {
                    Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING RUNS IN",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT BUILDING RUNS IN";
                    createDialog(title,msg);
                }
            }
            else {
                Toast.makeText(BuildingDetailsActivity.this,"SELECT BUILDING STATUS",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT BUILDING STATUS";
                createDialog(title,msg);
            }


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
        Log.e("TEST","0");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BUILDINGDETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Building"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("BObJ"," "+array);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                Log.e("message_building"," "+message);
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET buildingdetails='1' WHERE allinspactionid=" +insid);
                                //                       db.AllinspactionUpdate(dbid,"1",informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,insid);
                                if (builtrunin.equals("OB")){
                                    DistricNamee = ownbuildfund+DistricName;
                                    rentbuildin = "null";
                                    othrgovtbuildin = "null";
                                    Log.e("DISID",DistricNamee);
                                    syncBuildingsaveDatabase1(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                            rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_SYNCED_WITH_SERVER);
                                    UpdateListCheck();
                                }
                                else {
                                    // Toast.makeText(BuildingDetailsActivity.this,DistricNamee,Toast.LENGTH_SHORT).show();
                                }
                                if (builtrunin.equals("RB")){
                                    DistricNamee ="null";
                                    othrgovtbuildin = "null";
                                    syncBuildingsaveDatabase1(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                            rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_SYNCED_WITH_SERVER);
                                    UpdateListCheck();
                                }
                                else {
                                    //Toast.makeText(BuildingDetailsActivity.this,rentbuildin,Toast.LENGTH_SHORT).show();
                                }
                                if (builtrunin.equals("OGB")){
                                    rentbuildin = "null";
                                    DistricNamee ="null";
                                    syncBuildingsaveDatabase1(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                            rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_SYNCED_WITH_SERVER);
                                    UpdateListCheck();
                                }
                                else {
                                    //Toast.makeText(BuildingDetailsActivity.this,othrgovtbuildin,Toast.LENGTH_SHORT).show();
                                }

                                if (builtrunin.equals("PNR")){
                                    DistricNamee ="null";
                                    rentbuildin = "null";
                                    othrgovtbuildin = "null";
                                    syncBuildingsaveDatabase1(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                            rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_SYNCED_WITH_SERVER);
                                    UpdateListCheck();
                                }
                                else {
                                    //  Toast.makeText(BuildingDetailsActivity.this,builtrunin,Toast.LENGTH_SHORT).show();
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
                        if (builtrunin.equals("OB")){
                            DistricNamee = ownbuildfund+DistricName;
                            rentbuildin = "null";
                            othrgovtbuildin = "null";
                            Log.e("DISID",DistricNamee);
                            syncBuildingsaveDatabase1(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_NOT_SYNCED_WITH_SERVER);
                            UpdateListCheck();
                        }
                        else {
                            //  Toast.makeText(BuildingDetailsActivity.this,DistricNamee,Toast.LENGTH_SHORT).show();
                        }

                        if (builtrunin.equals("RB")){
                            DistricNamee ="null";
                            othrgovtbuildin = "null";
                            syncBuildingsaveDatabase1(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_NOT_SYNCED_WITH_SERVER);
                            UpdateListCheck();
                        }
                        else {
                            // Toast.makeText(BuildingDetailsActivity.this,rentbuildin,Toast.LENGTH_SHORT).show();
                        }
                        if (builtrunin.equals("OGB")){
                            rentbuildin = "null";
                            DistricNamee ="null";
                            syncBuildingsaveDatabase1(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_NOT_SYNCED_WITH_SERVER);
                            UpdateListCheck();
                        }
                        else {
                            //Toast.makeText(BuildingDetailsActivity.this,othrgovtbuildin,Toast.LENGTH_SHORT).show();
                        }

                        if (builtrunin.equals("PNR")){
                            DistricNamee ="null";
                            rentbuildin = "null";
                            othrgovtbuildin = "null";
                            syncBuildingsaveDatabase1(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,BUILDING_NOT_SYNCED_WITH_SERVER);
                            UpdateListCheck();
                        }
                        else {
                            // Toast.makeText(BuildingDetailsActivity.this,builtrunin,Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("district",dbdistid);
                params.put("project",dbprojectid);
                params.put("sector",dbsectorid);
                params.put("centre",dbcenterid);
                params.put("built_typ",builttyp);
                params.put("built_run_in",builtrunin);
//                if (builtrunin.equals("OB")){
//                    //// own building fund/////////
//                    params.put("own_build_fund",ownbuildfund);
//                    //// own building fund/////////
//                }
                if (builtrunin.equals("RB")){
                    params.put("rent_build_in",rentbuildin);
                }
                else if (builtrunin.equals("OGB")){
                    params.put("othr_govt_build_in",othrgovtbuildin);
                    Log.e("rentbuildin",""+builtrunin+" "+rentbuildin+" "+othrgovtbuildin);
                }
                params.put("build_conditn",buildconditn);
                params.put("inspctr_cmnt",inspctrcmnt);
                params.put("user_id",userID);
                params.put("inspctn_id",insid);
                params.put("inspctn_date",curDate);
                params.put("inspctn_time",curTime);
                params.put("last_isac_rep",latisacreport);
                params.put("last_inspctn_rep",lstinspctnbuldrep);
                Log.e("BUILDINGUPDATE",dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+" "+builttyp+" "+builtrunin+" "
                        +"own_build_fund off fild"+ownbuildfund+" "+rentbuildin+" "+othrgovtbuildin+" "+buildconditn+" "+inspctrcmnt+" "+userID+" "+curDate+" "+curTime+" "+insid+" "+latisacreport+" "+lstinspctnbuldrep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }
    private void syncBuildingsaveDatabase1(String dbdistid,String dbprojectid,String dbsectorid,String dbcenterid,String builttyp,String builtrunin,
                                          String rentbuildin,String othrgovtbuildin,String buildconditn,String inspctrcmnt,String userID,String insid,String curDate,String curTime,String latisacreport,String lstinspctnbuldrep, int buildingstatus){
//        boolean isUpdate =     db.BUILDING(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,DistricNamee,rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
//        db = new DatabaseHelper(getApplicationContext());
//        if (isUpdate==true){
//            db.BUILDINGUPDATE(dbid,dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,DistricNamee,rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
//        }
//        else {
//
//        }


        if (dbbuilding.equals("0")){
            db.BUILDING(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
        }
        else {
            db.BUILDINGUPDATE(dbid,dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,
                    rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);

        }



        //   db.BUILDING(dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,DistricNamee,rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
//         db.BUILDINGUPDATE(dbid,dbdistid,dbprojectid,dbsectorid,dbcenterid,builttyp,builtrunin,DistricNamee,rentbuildin,othrgovtbuildin,buildconditn,inspctrcmnt,userID,insid,curDate,curTime,latisacreport,lstinspctnbuldrep,buildingstatus);
        Log.e("DataSave", " "+dbid+ " "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+builttyp+" "+builtrunin+" "+DistricNamee+" "+rentbuildin+" "+othrgovtbuildin+" "+buildconditn+" "+inspctrcmnt+" "+userID+" "+insid+" "+curDate+" "+curTime+" "+latisacreport+" "+lstinspctnbuldrep+" "+buildingstatus);
        BuildingReturndata();

    }
    public void BuildingReturndata(){
        Intent intent = new Intent(BuildingDetailsActivity.this, InspectionListActivity.class);
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
////                String sql = "UPDATE "+TABLE_ALLINSPECTIONFLAG +" SET " + TABLE_BUILDINGDETAILS+ " = '"+"1"+"' WHERE "+TABLE_ALLINSPECTIONFLAGID+ " = "+"1";
////                Log.e("sql",sql);
//                BuildingDetailsUpdate();
//                break;
//                default:
//        }
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(BuildingDetailsActivity.this,InspectionListActivity.class);
//       startActivity(intent);
//    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("Inside onResume");
        Log.e("LOGBUILD","Inside onResume");


    }

    @Override
    public void onStart(){
        super.onStart();
        System.out.println("Inside onStart");
        Log.e("LOGBUILD","Inside onStart");


    }

    @Override
    public void onRestart(){
        super.onRestart();
        System.out.println("Inside onReStart");
        Log.e("LOGBUILD","Inside onReStart");

    }

    @Override
    public void onPause(){
        super.onPause();
        System.out.println("Inside onPause");
        Log.e("LOGBUILD","Inside onPause");
    }

    @Override
    public void onStop(){
//        unregisterReceiver(broadcastReceiver);
        super.onStop();
        System.out.println("Inside onStop");
        Log.e("LOGBUILD","Inside onStop");
    }


    @Override
    public void onDestroy() {
        System.out.println("Inside onDestroy");
        Log.e("LOGBUILD","Inside onDestroy");
        try{
            if(buildingNetworkStateCheckerr!=null)
                unregisterReceiver(buildingNetworkStateCheckerr);
            if (broadcastReceiver!=null)
                unregisterReceiver(broadcastReceiver);

        }catch(Exception e){}

        super.onDestroy();
    }

    public static class BuildingNetworkStateCheckerr extends BroadcastReceiver{
        private Context context;
        private DatabaseHelper db;
        String fund;
        @Override
        public void onReceive(Context context, Intent intent) {
            this.context = context;
            db = new DatabaseHelper(context);

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            //if there is a network
            if (activeNetwork != null) {
                //if connected to wifi or mobile data plan
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Cursor cursor = db.getUnsyncedBuildingDetails();
                    int i = 1;
                    if (cursor.moveToFirst()){
                        do {
                            BUILDINGSYNC(
                                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILDINGIDSYNC)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBDISTIDSYNC)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBPROJECTIDSYNC)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBSECTORIDSYNC)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBCENTERIDSYNC)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILTTYP)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILTRUNIN)),

                                    ////// own building fund/////////
                                    //cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OWNBUILDFUND)),
                                    ////// own building fund/////////

                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_RENTBUILDIN)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHRGOVTBUILDIN)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILDCONDITN)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPCTRCMNT)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERIDSYNC)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSIDSYNC)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CURDATE)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CURTIME)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LATISACREPORT)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LSTINSPCTNBULDREP)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_STATUSBUILDING))

                            );
                           // Toast.makeText(context, "Network Loop Building "+i,Toast.LENGTH_SHORT).show();
                            i++;

                        }while (cursor.moveToNext());
                    }
//                    String query = "SELECT * FROM " + TABLE_BUILDINGSYNC + " where " + TABLE_BUILDINGIDSYNC + "=" +TABLESTATUSBUILDING;
//                    SQLiteDatabase dbb = db.getReadableDatabase();
//                    Cursor  cc = dbb.rawQuery(query,null);
//                    if (cc.moveToFirst()) {
//                        do {
//                            Log.e("BUILDINGEDITLOG",cc.getString(cc.getColumnIndex(TABLE_STATUSBUILDING)));
//                            removeodIn(cc.getString(cc.getColumnIndex(TABLE_STATUSBUILDING)));
//                        }
//                        while (cc.moveToNext());
//                    }
                }

            }
        }


        private void BUILDINGSYNC(final int buildingidsync, final String dbdistidsync, final String dbprojectidsync, final String dbsectoridsync, final String dbcenteridsync, final String builttypsync,
                                  final String builtruninsync,
                                  final String rentbuildinsync,final String othrgovtbuildinsync,final String buildconditn,final String inspctrcmnt,final String useridsync,final String insidsync,final String curdatesync,final String curtimesync,final String latisacreportsync,final String lstinspctnbuldrepsync,final String buildingstatus) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BuildingDetailsActivity.BUILDINGDETAILS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray array =new JSONArray(response);
                               // removeodIn();
                                db.updateBuildingSyncStatus(buildingidsync, BuildingDetailsActivity.BUILDING_SYNCED_WITH_SERVER);
                                context.sendBroadcast(new Intent(BuildingDetailsActivity.DATA_SAVED_BROADCAST_BUILDING));
                            //   removeodIn(buildingstatus);
                              //  removeodIn();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("district", dbdistidsync);
                    params.put("project", dbprojectidsync);
                    params.put("sector", dbsectoridsync);
                    params.put("centre", dbcenteridsync);
                    params.put("built_typ", builttypsync);
                    params.put("built_run_in", builtruninsync);
//                    if (builtruninsync.equals("OB")){
//                        String fundname = ownbuildfundsync;
//                        fund = fundname.replaceAll("[A-Z]","");
//                        params.put("own_build_fund", fund);
//                    }
                    if (builtruninsync.equals("RB")){
                        params.put("rent_build_in", rentbuildinsync);
                    }
                   else if (builtruninsync.equals("OGB")){
                        params.put("othr_govt_build_in", othrgovtbuildinsync);
                    }
                    params.put("build_conditn", buildconditn);
                    params.put("inspctr_cmnt", inspctrcmnt);
                    params.put("user_id", useridsync);
                    params.put("inspctn_id", insidsync);
                    params.put("inspctn_date", curdatesync);
                    params.put("inspctn_time", curtimesync);
                    params.put("last_isac_rep", latisacreportsync);
                    params.put("last_inspctn_rep", lstinspctnbuldrepsync);
                    Log.e("BUILDINGDETAILS", dbdistidsync + " " + dbprojectidsync + " " + dbsectoridsync + " " + dbcenteridsync + " " + " " + builttypsync + " " + builtruninsync + " " + fund + " " + rentbuildinsync + " " + othrgovtbuildinsync + " " + buildconditn + " " + inspctrcmnt + " " + useridsync + " " + insidsync + " " + curdatesync + " " + curtimesync + " " + latisacreportsync + " " + lstinspctnbuldrepsync+" "+buildingstatus);
                    return params;
                }
            };

            //   VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
            stringRequest.setShouldCache(false);
            volleySingleton.addToRequestQueue(stringRequest);
        }
    }


    public static void removeodIn(){
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        Cursor c = sqlDB.rawQuery("DELETE FROM tablebuildingsync", null);
          //  String query = "DELETE  FROM " + TABLE_BUILDINGSYNC + " where " + TABLE_INSIDSYNC + "=" +insidsync+ " and " +TABLE_STATUSBUILDING+ "=" +TABLESTATUSBUILDING;
           // SQLiteDatabase sqlDB = db.getReadableDatabase();
          //  Cursor  c = sqlDB.rawQuery(query,null);
            Log.e("DELETEUPDATECENRER","DELETEUPDATECENRER");
            String buildingidsync = "";
            String dbdistidsync = "";
            String dbprojectidsync = "";
            String dbsectoridsync = "";
            String dbcenteridsync = "";
            String builttypsync = "";
            String builtruninsync = "";
            String ownbuildfundsync = "";
            String rentbuildinsync = "";
            String othrgovtbuildinsync = "";
            String buildconditn = "";
            String inspctrcmnt = "";
            String useridsync = "";
            String insidsync = "";
            String curdatesync = "";
            String curtimesync = "";
            String latisacreportsync = "";
            String lstinspctnbuldrepsync = "";
            String buildingstatus = "";
            int status = 0;
            if (c.moveToFirst()) {

                while (!c.isAfterLast()) {
                    buildingidsync = c.getString(c.getColumnIndex("buildingidsync"));
                    dbdistidsync = c.getString(c.getColumnIndex("dbdistidsync"));
                    dbprojectidsync = c.getString(c.getColumnIndex("dbprojectidsync"));
                    dbsectoridsync = c.getString(c.getColumnIndex("dbsectoridsync"));
                    dbcenteridsync = c.getString(c.getColumnIndex("dbcenteridsync"));
                    builttypsync = c.getString(c.getColumnIndex("builttypsync"));
                    builtruninsync = c.getString(c.getColumnIndex("builtruninsync"));
                    ownbuildfundsync = c.getString(c.getColumnIndex("ownbuildfundsync"));
                    rentbuildinsync = c.getString(c.getColumnIndex("rentbuildinsync"));
                    othrgovtbuildinsync = c.getString(c.getColumnIndex("othrgovtbuildinsync"));
                    buildconditn = c.getString(c.getColumnIndex("buildconditn"));
                    inspctrcmnt = c.getString(c.getColumnIndex("inspctrcmnt"));
                    useridsync = c.getString(c.getColumnIndex("useridsync"));
                    insidsync = c.getString(c.getColumnIndex("insidsync"));
                    curdatesync = c.getString(c.getColumnIndex("curdatesync"));
                    curtimesync = c.getString(c.getColumnIndex("curtimesync"));
                    latisacreportsync = c.getString(c.getColumnIndex("latisacreportsync"));
                    lstinspctnbuldrepsync = c.getString(c.getColumnIndex("lstinspctnbuldrepsync"));
                    buildingstatus = c.getString(c.getColumnIndex("buildingstatus"));
                    if (!buildingidsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + buildingidsync + "'");
                    }
                    if (!dbdistidsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + dbdistidsync + "'");
                    }
                    if (!dbprojectidsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + dbprojectidsync + "'");
                    }
                    if (!dbsectoridsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + dbsectoridsync + "'");
                    }
                    if (!dbcenteridsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + dbcenteridsync + "'");
                    }
                    if (!builttypsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + builttypsync + "'");
                    }
                    if (!builtruninsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + builtruninsync + "'");
                    }
                    if (!ownbuildfundsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + ownbuildfundsync + "'");
                    }
                    if (!rentbuildinsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + rentbuildinsync + "'");
                    }
                    if (!othrgovtbuildinsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + othrgovtbuildinsync + "'");
                    }
                    if (!buildconditn.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + buildconditn + "'");
                    }
                    if (!inspctrcmnt.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + inspctrcmnt + "'");
                    }
                    if (!useridsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + useridsync + "'");
                    }
                    if (!insidsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + insidsync + "'");
                    }
                    if (!curdatesync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + curdatesync + "'");
                    }
                    if (!curtimesync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + curtimesync + "'");
                    }
                    if (!latisacreportsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + latisacreportsync + "'");
                    }
                    if (!lstinspctnbuldrepsync.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + lstinspctnbuldrepsync + "'");
                    }
                    if (!buildingstatus.equals("android_metadata")) {
                        sqlDB.execSQL("DROP TABLE '" + buildingstatus + "'");
                    }
                    c.moveToNext();
                }

            c.close();
        }
    }


    public static void deleteRow(String buildingidsync)
    {
        Log.e("BUILDINGEDITLOG","DELETE");
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        sqlDB.execSQL("DELETE FROM " + TABLE_BUILDINGSYNC+ " WHERE "+TABLE_BUILDINGIDSYNC+"='"+buildingidsync+"'");
        sqlDB.close();
        Log.e("BUILDINGEDITLOG","DELETE1");
    }

}
