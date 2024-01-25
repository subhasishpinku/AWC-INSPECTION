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

import icdswb.in.ActivityDatabase.AdqueSpaceNetwokchecker;
import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ADEQUTSPACEINSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;

public class AdequateSpacePSEActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    String foodspace ="NA";
    String water ="NA";
    String cdponame ="NA";
    String acdpocont ="NA";
    String buildstruc ="NA";
    String electric ="NA";
    String acdponame ="NA";
    String adqutspacepse ="NA";
    String supvsrno ="NA";
    String awcsid ="NA";
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
    String helpernm ="NA";
    String awcscode ="NA";
    String buildon ="NA";
    String awcsname ="NA";
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
    String curDate,curTime;
    TextView lastisacreportID,awcstvId;
    RadioGroup adespaceforchildID,pseactivityID;
    RadioButton Yade,Nade,aIsID,aosID,aIaosID,IIsaosID,aisiosID;
    Button savenextID;
    String adequtspace = "NA";
    String inspctrcmnt = "NA";
    EditText cmdedittextID;
    String planid = "NA";
    String insid;
    String lstinspctnbuldrep = "NA";
    String lstinspctntoiletrep = "NA";
    String lstinspctnkitchenrep = "NA";
    String lstinspctnelectricrep = "NA";
    String lstinspctndrnkwaterrep = "NA";
    String lstinspctnfoodrep = "NA";
    String dbid = "NA";
    DatabaseHelper helper;
    String Eadequtspace = "NA";
    String Epseactvtytyp = "NA";
    String Einspctrcmnt = "NA";
    String idadqe = "NA";
    String adeque = "NA";
    LinearLayout lv5;
    String pseactvtytyp = "NA";
    String ADQESPACETSTATUS ="0";
    String lstinspctnpserep = "NA";
    public static final int ADESQUSPACE_SYNCED_WITH_SERVER = 1;
    public static final int ADESQUSPACE_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiveradquespace;
    public static final String DATA_SAVED_BROADCAST_ADQUESPACE = "icdswb.in.adqespacesaved";
    private AdqueSpaceNetwokchecker adqueSpaceNetwokchecker;
    String adequtspacesync ="";
    String pseactvtytypsync = "";
    String adequtspaceinspctrcmntsync = "";
    String userID;
    private final int REQ_CODE_PSE = 100;
    ImageButton commandspak;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adequatespacepseactivity);
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
//        planid = intent.getStringExtra("planid");
//        dbid = intent.getStringExtra("dbid");
       //Log.e("dbid", dbid);
        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        Log.e("pseP", planid);
        Log.e("ADEQUATE", " " + toilet + " " + awcscode + " " + awcsname + " " + dbdistid + " " + dbprojectid + " " + dbsectorid + " " + dbcenterid + " " + projectnamedb + " " + districnamedb + " " + sectorrnamedb + " " + centernamedb + " " + insid + " ");
        //kitchen = intent.getStringExtra("kitchen");
       // adqutspacepse = intent.getStringExtra("adqutspacepse");
       // electric = intent.getStringExtra("electric");
       // water = intent.getStringExtra("water");
       // foodspace = intent.getStringExtra("foodspace");
        Log.e("ADEQUATE", " " + kitchen + " " + adqutspacepse + " " + electric + " " + water + " " + foodspace + " ");
        Log.e("adqutspacepse",adqutspacepse);
       // lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
       // lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
       // lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
       // lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
       // lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
      //  lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
       // lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        Log.e("LAST_ADEQPSEREPO", lstinspctnbuldrep + " " + lstinspctntoiletrep + " " + lstinspctnkitchenrep + " " + lstinspctnpserep + " " + lstinspctnelectricrep + " " + lstinspctndrnkwaterrep + " " + lstinspctnfoodrep);
        Log.e("lstinspctnpserep",lstinspctnpserep);
        lastisacreportID = (TextView) findViewById(R.id.lastisacreportID);
        lastisacreportID.setText(adqutspacepse);
        //savenextID = (Button) findViewById(R.id.savenextID);
      //  savenextID.setOnClickListener(this);
        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        cmdedittextID = (EditText) findViewById(R.id.cmdedittextID);
        cmdedittextID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        cmdedittextID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        lv5 = (LinearLayout) findViewById(R.id.lv5);
        lv5.setVisibility(View.GONE);
        helper = new DatabaseHelper(this);
        initToolBar();
        aderadibutton();
    //    Cursor cursor = helper.getReadableDatabase().
           //     rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
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
                idadqe = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                adeque = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADEQUATESPACEFORPSE));
                Log.e("ADQE", " " + idadqe + " " + adeque);
            }
            while (cursor.moveToNext());
        }
        if (adeque.equals("0")) {

        } else {
            editAdequate();
        }

        broadcastReceiveradquespace = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiveradquespace, new IntentFilter(DATA_SAVED_BROADCAST_ADQUESPACE));
        adqueSpaceNetwokchecker = new AdqueSpaceNetwokchecker();
        registerReceiver(adqueSpaceNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (isNetworkAvailable()) {

        } else {
          //  String query = "SELECT * FROM " + TABLE_ADEQUATESPPSESYNC + " where " + TABLE_ADEQUTSPACEINSIDSYNC + "=" + insid + " and " + TABLE_ADEQUATESPPSESTATUS + "=" + ADQESPACETSTATUS;
            String query = "SELECT * FROM adequatesppsesync WHERE adequtspaceinsidsync=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cc = db.rawQuery(query, null);
            if (cc.moveToFirst()) {
                do {
                    String adequatesppseid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ADEQUATESPPSEID));
                    String adequtspaceinsidsync = cc.getString(cc.getColumnIndex(TABLE_ADEQUTSPACEINSIDSYNC));
                    adequtspacesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ADEQUTSPACE));
                    pseactvtytypsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_PSEACTVTYTYP));
                    String  adequtspacecurTimesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ADEQUTSPACECURTIMESYNC));
                    adequtspaceinspctrcmntsync  = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPACEINSPCTRCMNT));
                    String adqutspacelastisacrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPLASTISACREPSYNC));
                    String adqutspacelastinspctnrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPACELASTINSPCTNREPSYNC));
                    String adequatesppsestatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_ADEQUATESPPSESTATUS));
                    Log.e("ADQUESPACEDETAILSSYNC", " " + adequatesppseid + " " + adequtspaceinsidsync + " " + adequtspacesync + " " + pseactvtytypsync + " " + inspctrcmnt + " " + adequtspacecurTimesync + " " + adequtspaceinspctrcmntsync + " " + adqutspacelastisacrepsync + " " + adqutspacelastinspctnrepsync+" "+adequatesppsestatus);
                }
                while (cc.moveToNext());
            }
            Showdata();
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.homeID:
                                homesave();
                                break;
                            case R.id.saveID:
                                update_pse_activity_inspection();
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
            startActivityForResult(intent,REQ_CODE_PSE);
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
            case REQ_CODE_PSE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    cmdedittextID.setText(result.get(0));
                }
                break;
            }
        }
    }

    public void Showdata(){
        if (adequtspacesync.equals("y")){
            Yade.setChecked(true);
        }
        else if (adequtspacesync.equals("n")){
            Nade.setChecked(true);
        }
        else {
            Yade.setChecked(false);
            Nade.setChecked(false);
        }

        if (pseactvtytypsync.equals("AI")){
            aIsID.setChecked(true);
        }
        else if (pseactvtytypsync.equals("AO")){
            aosID.setChecked(true);
        }
        else if (pseactvtytypsync.equals("AIO")){
            aIaosID.setChecked(true);
        }
        else if (pseactvtytypsync.equals("IIAO")){
            IIsaosID.setChecked(true);
        }
        else if (pseactvtytypsync.equals("AIIO")){
            aisiosID.setChecked(true);
        }
        else {
            aIsID.setChecked(false);
            aosID.setChecked(false);
            aIaosID.setChecked(false);
            IIsaosID.setChecked(false);
            aisiosID.setChecked(false);
        }
        cmdedittextID.setText(adequtspaceinspctrcmntsync);

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
    public void aderadibutton(){
        adespaceforchildID = (RadioGroup)findViewById(R.id.adespaceforchildID);
        pseactivityID = (RadioGroup)findViewById(R.id.pseactivityID);
        Yade = (RadioButton)findViewById(R.id.Yade);
        Nade = (RadioButton)findViewById(R.id.Nade);
        aIsID = (RadioButton)findViewById(R.id.aIsID);
        aosID = (RadioButton)findViewById(R.id.aosID);
        aIaosID = (RadioButton)findViewById(R.id.aIaosID);
        IIsaosID = (RadioButton)findViewById(R.id.IIsaosID);
        aisiosID = (RadioButton)findViewById(R.id.aisiosID);
        adespaceforchildID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.Yade){
                    adequtspace = "y";
                    lv5.setVisibility(View.VISIBLE);
                    Log.e("adequtspace",adequtspace);
                }
                else if (checkedId== R.id.Nade){
                    adequtspace = "n";
                    lv5.setVisibility(View.GONE);
                    Log.e("adequtspace",adequtspace);
                    aIsID.setChecked(false);
                    aosID.setChecked(false);
                    aIaosID.setChecked(false);
                    IIsaosID.setChecked(false);
                    aisiosID.setChecked(false);
                }

            }
        });
        pseactivityID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.aIsID){
                    pseactvtytyp = "AI";
                    Log.e("pseactvtytyp",pseactvtytyp);
                }
                else if (checkedId== R.id.aosID){
                    pseactvtytyp = "AO";
                    Log.e("pseactvtytyp",pseactvtytyp);
                }
                else if (checkedId== R.id.aIaosID){
                    pseactvtytyp = "AIO";
                    Log.e("pseactvtytyp",pseactvtytyp);
                }
                else if (checkedId== R.id.IIsaosID){
                    pseactvtytyp = "IIAO";
                    Log.e("pseactvtytyp",pseactvtytyp);
                }
                else if (checkedId== R.id.aisiosID){
                    pseactvtytyp = "AIIO";
                    Log.e("pseactvtytyp",pseactvtytyp);

                }
            }
        });

    }

    private void editAdequate(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PSEEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("ADEQEDIT"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONObject jsonObject = object.getJSONObject("pse_dtl");
                            Eadequtspace = jsonObject.getString("adequt_space");
                            Epseactvtytyp = jsonObject.getString("pse_actvty_typ");
                            Einspctrcmnt = jsonObject.getString("inspctr_cmnt");
                            Log.e("Adeqie"," "+Eadequtspace+" "+Epseactvtytyp+' '+Einspctrcmnt);
                            if (Eadequtspace.equals("y")){
                                Yade.setChecked(true);
                            }
                            else if (Eadequtspace.equals("n")){
                                Nade.setChecked(true);
                            }
                            else {
                                Yade.setChecked(false);
                                Nade.setChecked(false);
                            }

                            if (Epseactvtytyp.equals("AI")){
                                aIsID.setChecked(true);
                            }
                            else if (Epseactvtytyp.equals("AO")){
                                aosID.setChecked(true);
                            }
                            else if (Epseactvtytyp.equals("AIO")){
                                aIaosID.setChecked(true);
                            }
                            else if (Epseactvtytyp.equals("IIAO")){
                                IIsaosID.setChecked(true);
                            }
                            else if (Epseactvtytyp.equals("AIIO")){
                                aisiosID.setChecked(true);
                            }
                            else {
                                aIsID.setChecked(false);
                                aosID.setChecked(false);
                                aIaosID.setChecked(false);
                                IIsaosID.setChecked(false);
                                aisiosID.setChecked(false);
                            }
                            cmdedittextID.setText(Einspctrcmnt);

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

     private void update_pse_activity_inspection(){
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
         if (TextUtils.isEmpty(inspctrcmnt)) {
             if (Yade.isChecked() || Nade.isChecked()){
                 if (aIsID.isChecked() || aosID.isChecked() || aIaosID.isChecked() || IIsaosID.isChecked() || aisiosID.isChecked()){
                     cmdedittextID.setError("Please enter Command");
                     cmdedittextID.requestFocus();
                 }
                 else {
                     Toast.makeText(AdequateSpacePSEActivity.this,"SELECT SPACE",Toast.LENGTH_SHORT).show();
                     String title = "Message Box";
                     String msg = "SELECT SPACE";
                     createDialog(title,msg);
                 }
             }
             else {
                 Toast.makeText(AdequateSpacePSEActivity.this,"SELECT ADEQUATE SPACE FOR CHILDREN",Toast.LENGTH_SHORT).show();
                 String title = "Message Box";
                 String msg = "SELECT ADEQUATE SPACE FOR CHILDREN";
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
         Log.e("TEST","0");
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PSE,
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
                                 dbb.execSQL("UPDATE insflag SET adequatespaceforpse='1' WHERE allinspactionid=" +insid);
                               //  syncAdquespacesaveDatabase(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_SYNCED_WITH_SERVER);
                                 if (adequtspace.equals("y")) {
                                     syncAdquespacesaveDatabase(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_SYNCED_WITH_SERVER);
                                 }
                                 else {
                                 }
                                 if (adequtspace.equals("n")){
                                     pseactvtytyp = "null";
                                     syncAdquespacesaveDatabase(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_SYNCED_WITH_SERVER);
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
                              if (adequtspace.equals("y")) {
                                  syncAdquespacesaveDatabase(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_NOT_SYNCED_WITH_SERVER);
                                  helper = new DatabaseHelper(getApplicationContext());
                                  SQLiteDatabase dbb = helper.getReadableDatabase();
                                  dbb.execSQL("UPDATE insflag SET adequatespaceforpse='1' WHERE allinspactionid=" +insid);
                              }
                              else {

                              }
                              if (adequtspace.equals("n")){
                                  pseactvtytyp = "null";
                                  syncAdquespacesaveDatabase(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_NOT_SYNCED_WITH_SERVER);
                                  helper = new DatabaseHelper(getApplicationContext());
                                  SQLiteDatabase dbb = helper.getReadableDatabase();
                                  dbb.execSQL("UPDATE insflag SET adequatespaceforpse='1' WHERE allinspactionid=" +insid);
                              }
                              else {

                              }
                     }
                 }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();
                 params.put("inspctn_id",insid);
                 params.put("adequt_space",adequtspace);
                 if (adequtspace.equals("y")) {
                     params.put("pse_actvty_typ", pseactvtytyp);
                     Log.e("adequtspace",pseactvtytyp);
                 }
                 params.put("ins_time",curTime);
                 params.put("inspctr_cmnt",inspctrcmnt);
                 params.put("last_isac_rep",adqutspacepse);
                 params.put("last_inspctn_rep",lstinspctnpserep);
                 Log.e("ADQLOG",adqutspacepse);
                 Log.e("ADQLOGG",lstinspctnpserep);
                 Log.e("ADEQUELOG",insid+" "+adequtspace+" "+pseactvtytyp+" "+" "+curTime+" "+inspctrcmnt+" "+adqutspacepse+" "+lstinspctnpserep);
                 return params;
             }
         };
         //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
         stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
         stringRequest.setShouldCache(false);
         volleySingleton.addToRequestQueue(stringRequest);
    }
     private void syncAdquespacesaveDatabase(String insid,String adequtspace,String pseactvtytyp,String curTime,String inspctrcmnt,String adqutspacepse,String lstinspctnpserep,int adequatesppsestatus){
        if (adeque.equals("0")) {
             helper.ADQUESPACEINSERT(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,adequatesppsestatus);
        } else {
             helper.ADQUESPACEUPDATE(dbid,insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,adequatesppsestatus);
        }
         Log.e("DATASAVEADQ",dbid+" "+insid+" "+adequtspace+" "+pseactvtytyp+" "+curTime+" "+inspctrcmnt+" "+adqutspacepse+" "+lstinspctnpserep+" "+adequatesppsestatus);
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
        Intent intent = new Intent(AdequateSpacePSEActivity.this, ElectricityActivity.class);
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
        if (TextUtils.isEmpty(inspctrcmnt)) {
            if (Yade.isChecked() || Nade.isChecked()){
                if (aIsID.isChecked() || aosID.isChecked() || aIaosID.isChecked() || IIsaosID.isChecked() || aisiosID.isChecked()){
                    cmdedittextID.setError("Please enter Command");
                    cmdedittextID.requestFocus();
                }
                else {
                    Toast.makeText(AdequateSpacePSEActivity.this,"SELECT SPACE",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT SPACE";
                    createDialog(title,msg);
                }
            }
            else {
                Toast.makeText(AdequateSpacePSEActivity.this,"SELECT ADEQUATE SPACE FOR CHILDREN",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT ADEQUATE SPACE FOR CHILDREN";
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
        Log.e("TEST","0");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PSE,
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
                                dbb.execSQL("UPDATE insflag SET adequatespaceforpse='1' WHERE allinspactionid=" +insid);
                                //  syncAdquespacesaveDatabase(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_SYNCED_WITH_SERVER);
                                if (adequtspace.equals("y")) {
                                    syncAdquespacesaveDatabase1(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_SYNCED_WITH_SERVER);
                                }
                                else {
                                }
                                if (adequtspace.equals("n")){
                                    pseactvtytyp = "null";
                                    syncAdquespacesaveDatabase1(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_SYNCED_WITH_SERVER);
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
                        if (adequtspace.equals("y")) {
                            syncAdquespacesaveDatabase1(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_NOT_SYNCED_WITH_SERVER);
                            helper = new DatabaseHelper(getApplicationContext());
                            SQLiteDatabase dbb = helper.getReadableDatabase();
                            dbb.execSQL("UPDATE insflag SET adequatespaceforpse='1' WHERE allinspactionid=" +insid);
                        }
                        else {

                        }
                        if (adequtspace.equals("n")){
                            pseactvtytyp = "null";
                            syncAdquespacesaveDatabase1(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,ADESQUSPACE_NOT_SYNCED_WITH_SERVER);
                            helper = new DatabaseHelper(getApplicationContext());
                            SQLiteDatabase dbb = helper.getReadableDatabase();
                            dbb.execSQL("UPDATE insflag SET adequatespaceforpse='1' WHERE allinspactionid=" +insid);
                        }
                        else {

                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("adequt_space",adequtspace);
                if (adequtspace.equals("y")) {
                    params.put("pse_actvty_typ", pseactvtytyp);
                    Log.e("adequtspace",pseactvtytyp);
                }
                params.put("ins_time",curTime);
                params.put("inspctr_cmnt",inspctrcmnt);
                params.put("last_isac_rep",adqutspacepse);
                params.put("last_inspctn_rep",lstinspctnpserep);
                Log.e("ADQLOG",adqutspacepse);
                Log.e("ADQLOGG",lstinspctnpserep);
                Log.e("ADEQUELOG",insid+" "+adequtspace+" "+pseactvtytyp+" "+" "+curTime+" "+inspctrcmnt+" "+adqutspacepse+" "+lstinspctnpserep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }
    private void syncAdquespacesaveDatabase1(String insid,String adequtspace,String pseactvtytyp,String curTime,String inspctrcmnt,String adqutspacepse,String lstinspctnpserep,int adequatesppsestatus){
        if (adeque.equals("0")) {
            helper.ADQUESPACEINSERT(insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,adequatesppsestatus);
        } else {
            helper.ADQUESPACEUPDATE(dbid,insid,adequtspace,pseactvtytyp,curTime,inspctrcmnt,adqutspacepse,lstinspctnpserep,adequatesppsestatus);
        }
        Log.e("DATASAVEADQ",dbid+" "+insid+" "+adequtspace+" "+pseactvtytyp+" "+curTime+" "+inspctrcmnt+" "+adqutspacepse+" "+lstinspctnpserep+" "+adequatesppsestatus);
        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(AdequateSpacePSEActivity.this, InspectionListActivity.class);
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
//                update_pse_activity_inspection();
//            break;
//            default:
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
        super.onDestroy();
        try{
            if(adqueSpaceNetwokchecker!=null)
                unregisterReceiver(adqueSpaceNetwokchecker);
            if (broadcastReceiveradquespace!=null)
                unregisterReceiver(broadcastReceiveradquespace);
        }catch(Exception e){}
        System.out.println("Inside onDestroy");
    }
}
