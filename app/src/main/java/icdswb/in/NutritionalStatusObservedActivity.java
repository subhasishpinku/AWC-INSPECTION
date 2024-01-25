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
import icdswb.in.ActivityDatabase.NutritionNetworkChecker;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NUTRITIONALTINS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;


public class NutritionalStatusObservedActivity extends AppCompatActivity implements View.OnClickListener{
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
    Button saveID;
    RadioGroup GnsmcID,mutapeID;
    RadioButton rregistrationID,rcfDayID,mutapeRagistationID,mutapethatdayID;
    String curDate,curTime,userID;
    String malnurishdchld = "NA";
    String sanchld = "NA";
    String dbid = "NA";
    String INnature = "NA";
    String NatureStatus = "NA";
    String Emalnurishdchld = "NA";
    String Esanchld = "NA";
    String Einspctrcmnt = "NA";
    String record_regs = "NA";
    String phy_found = "NA";
    String commant = "NA";
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
    DatabaseHelper helper;
    EditText serveryId,soundserveryIdd,givecmdID;
    public static final int NUTRITATION_SYNCED_WITH_SERVER = 1;
    public static final int NUTRITATION_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceivernutriation;
    public static final String DATA_SAVED_BROADCAST_NUTRITATION = "icdswb.in.nutricationsaved";
    private NutritionNetworkChecker nutriationnetwokchecker;
    String NUTRIONSTATUS ="0";
    String nutritionaltintime ="";
    String malnurishdchldDB = "";
    String sanchldDB ="";
    String commantDB ="";
    TextView awcstvId;
    private final int REQ_CODE_SPEECH_COMMAND= 100;
    private final int REQ_CODE_SPEECH_COMMANDD= 101;
    private final int REQ_CODE_SPEECH_COMMANDDD= 102;
    ImageButton givecmdspak,soundserveryId,NsoundchildfoundId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_nutritionalstatusobserved);
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
      //  Log.e("dbid",dbid);
        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        Log.e("NutritionalP",planid);
        Log.e("Nutritional"," "+toilet+" "+awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+insid+" ");
      //  kitchen = intent.getStringExtra("kitchen");
       // adqutspacepse = intent.getStringExtra("adqutspacepse");
      //  electric = intent.getStringExtra("electric");
       // water = intent.getStringExtra("water");
       // foodspace = intent.getStringExtra("foodspace");
        Log.e("Nutritional"," "+kitchen+" "+adqutspacepse+" "+electric+" "+water+" "+foodspace+" ");
        //saveID = (Button)findViewById(R.id.saveID);
        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        serveryId =(EditText)findViewById(R.id.serveryId);
        soundserveryIdd = (EditText)findViewById(R.id.soundserveryIdd);
        givecmdID = (EditText)findViewById(R.id.givecmdID);
        givecmdID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        givecmdID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        //saveID.setOnClickListener(this);
        helper = new DatabaseHelper(this);
        initToolBar();
        radibuttonId();
        helper = new DatabaseHelper(this);
    //    Cursor cursor = helper.getReadableDatabase().
         //       rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
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
                INnature = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                NatureStatus = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALSTATUSOBSERVED));
                Log.e("NUTRATION"," "+INnature+" "+NatureStatus);
            }
            while (cursor.moveToNext());
        }
        if (NatureStatus.equals("0")){

        }
        else {
            editNature();
        }

        broadcastReceivernutriation = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        registerReceiver(broadcastReceivernutriation, new IntentFilter(DATA_SAVED_BROADCAST_NUTRITATION));
        nutriationnetwokchecker = new NutritionNetworkChecker();
        registerReceiver(nutriationnetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        if (isNetworkAvailable()) {

        } else {
        //    String query = "SELECT * FROM " + TABLE_NUTRITIONALTABLE + " where " + TABLE_NUTRITIONALTINS + "=" + insid + " and " + TABLE_NUTRITIONALSTATUS + "=" + NUTRIONSTATUS;
            String query = "SELECT * FROM nutritionaltable WHERE nutritionaltins=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cc = db.rawQuery(query, null);
            if (cc.moveToFirst()) {
                do {
                    String nutritionaltableid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALTID));
                    String nutritionaltins = cc.getString(cc.getColumnIndex(TABLE_NUTRITIONALTINS));
                    nutritionaltintime = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALTINTIME));
                    malnurishdchldDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_MALNURISHDCHLD));
                    sanchldDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SANCHLD));
                    commantDB = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_COMMANT));
                    String  nutritionalstatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALSTATUS));
                    Log.e("NUTRION",nutritionaltableid+" "+nutritionaltins+" "+nutritionaltintime+" "+malnurishdchldDB+" "+sanchldDB+" "+commantDB+" "+nutritionalstatus);
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
//                                if (rregistrationID.isChecked() || rcfDayID.isChecked()){
//                                    if (mutapeRagistationID.isChecked() || mutapethatdayID.isChecked()){
//
//                                    }
//                                    else {
//                                        Toast.makeText(NutritionalStatusObservedActivity.this,"Select No Of San Children Using Muac Tape",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                else {
//                                    Toast.makeText(NutritionalStatusObservedActivity.this,"Select No Of Severely Malnourished Children",Toast.LENGTH_SHORT).show();
//                                }


                                update_nutritn_serv_inspectn();

                                break;
                            default:
                        }
                        return true;
                    }
                });

        givecmdspak = (ImageButton)findViewById(R.id.givecmdspak);
        givecmdspak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                givecmdspak();

            }
        });
        soundserveryId = (ImageButton)findViewById(R.id.soundserveryId);
        soundserveryId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                givecmdspakk();

            }
        });
        NsoundchildfoundId  = (ImageButton)findViewById(R.id.NsoundchildfoundId);
        NsoundchildfoundId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                givecmdspakkk();

            }
        });
    }

    private void givecmdspak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_command));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_COMMAND);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void givecmdspakk(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_command));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_COMMANDD);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void givecmdspakkk(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_command));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_COMMANDDD);
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
            case REQ_CODE_SPEECH_COMMAND: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    givecmdID.setText(result.get(0));

                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_COMMANDD: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //serveryId.setText(result.get(0));
                    isNumber(result.get(0));
                }
                break;
            }
        }

        switch (requestCode) {
            case REQ_CODE_SPEECH_COMMANDDD: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //soundserveryIdd.setText(result.get(0));
                    isNumberr(result.get(0));
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
            serveryId.setText(word);
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
            soundserveryIdd.setText(word);
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
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
        serveryId.setText(malnurishdchldDB);
        soundserveryIdd.setText(sanchldDB);
        givecmdID.setText(commantDB);

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

    private void radibuttonId(){
        GnsmcID = (RadioGroup)findViewById(R.id.GnsmcID);
        mutapeID = (RadioGroup)findViewById(R.id.mutapeID);
        rregistrationID = (RadioButton)findViewById(R.id.rregistrationID);
        rcfDayID = (RadioButton)findViewById(R.id.rcfDayID);
        mutapeRagistationID = (RadioButton)findViewById(R.id.mutapeRagistationID);
        mutapethatdayID = (RadioButton)findViewById(R.id.mutapethatdayID);
        GnsmcID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rregistrationID.isChecked()){
                    malnurishdchld = "rcd_regstr";
                    Log.e("malnurishdchld",malnurishdchld);
                }
                else if (rcfDayID.isChecked()){
                    malnurishdchld = "no_chld";
                    Log.e("malnurishdchld",malnurishdchld);
                }

            }
        });
        mutapeID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mutapeRagistationID.isChecked()){
                    sanchld = "rcd_regstr";
                    Log.e("malnurishdchld",sanchld);
                }
                else if (mutapethatdayID.isChecked()){
                    sanchld = "no_chld";
                    Log.e("sanchld",sanchld);
                }
            }
        });
    }
    private void editNature(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.NUTRITIONALEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("NATUREID"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray jsonArray = object.getJSONArray("nutritn_inspectn_dtl");
                            for (int i = 0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Emalnurishdchld = jsonObject.getString("malnurishd_chld");
                                Esanchld = jsonObject.getString("chld_found_physicaly");
                                Einspctrcmnt = jsonObject.getString("inspctr_cmnt");
                                serveryId.setText(Emalnurishdchld);
                                soundserveryIdd.setText(Esanchld);
                                givecmdID.setText(Einspctrcmnt);
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
    private void update_nutritn_serv_inspectn(){
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
        serveryId.setFilters(new InputFilter[] { filter2 });
        record_regs = serveryId.getText().toString().trim();
        //  serveryId,soundserveryIdd
        if (TextUtils.isEmpty(record_regs)) {
            serveryId.setError("Please Enter Number");
            serveryId.requestFocus();
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
        soundserveryIdd.setFilters(new InputFilter[] { filter3 });
        phy_found = soundserveryIdd.getText().toString().trim();
        //  serveryId,soundserveryIdd
        if (TextUtils.isEmpty(phy_found)) {
            soundserveryIdd.setError("Please Enter Number");
            soundserveryIdd.requestFocus();
            return;
        }
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
        givecmdID.setFilters(new InputFilter[] { filter1 });
        commant = givecmdID.getText().toString().trim();
      //  serveryId,soundserveryIdd
        if (TextUtils.isEmpty(commant)) {
            givecmdID.setError("Please Enter Command");
            givecmdID.requestFocus();
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.NUTRITIONAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Nutritionalresponse"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("NutritionalaRRAYobj"," "+array);
                            for (int i=0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET nutritionalstatusobserved='1' WHERE allinspactionid=" +insid);
                                syncNutriationDatabase(insid,curTime,record_regs,phy_found,commant,NUTRITATION_SYNCED_WITH_SERVER);
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
                        dbb.execSQL("UPDATE insflag SET nutritionalstatusobserved='1' WHERE allinspactionid=" +insid);
                        syncNutriationDatabase(insid,curTime,record_regs,phy_found,commant,NUTRITATION_NOT_SYNCED_WITH_SERVER);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("ins_time",curTime);
                params.put("malnurishd_chld",record_regs);
                params.put("chld_found_physicaly",phy_found);
                params.put("inspctr_cmnt",commant);
                Log.e("Nutritional",insid+" "+curTime+" "+record_regs+" "+phy_found+" "+commant);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }
 private void syncNutriationDatabase(String insid,String curTime,String malnurishdchld,String sanchld,String commant,int nutritionalstatus){
     if (NatureStatus.equals("0")){
       helper.NUTRITIONALINSERT(insid,curTime,malnurishdchld,sanchld,commant,nutritionalstatus);
     }
     else {
         helper.NUTRITIONALUPDATE(dbid,insid,curTime,malnurishdchld,sanchld,commant,nutritionalstatus);
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
//     Intent intent = new Intent(NutritionalStatusObservedActivity.this,OtherInspectionActivity.class);
//     Bundle bundle = new Bundle();
//     bundle.putString("kitchen",kitchen);
//     bundle.putString("adqutspacepse",adqutspacepse);
//     bundle.putString("electric",electric);
//     bundle.putString("water",water);
//     bundle.putString("foodspace",foodspace);
//     bundle.putString("toilet",toilet);
//     bundle.putString("awcscode",awcscode);
//     bundle.putString("awcsname",awcsname);
//     bundle.putString("dbdistid",dbdistid);
//     bundle.putString("dbprojectid",dbprojectid);
//     bundle.putString("dbsectorid",dbsectorid);
//     bundle.putString("dbcenterid",dbcenterid);
//     bundle.putString("projectnamedb",projectnamedb);
//     bundle.putString("districnamedb",districnamedb);
//     bundle.putString("sectorrnamedb",sectorrnamedb);
//     bundle.putString("centernamedb",centernamedb);
//     bundle.putString("insid",insid);
//     bundle.putString("planid",planid);
//     bundle.putString("dbid",dbid);
//     intent.putExtras(bundle);
//     startActivity(intent);

     Intent intent = new Intent(NutritionalStatusObservedActivity.this, OtherInspectionActivity.class);
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
       serveryId.setFilters(new InputFilter[] { filter2 });
       record_regs = serveryId.getText().toString().trim();
       //  serveryId,soundserveryIdd
       if (TextUtils.isEmpty(record_regs)) {
           serveryId.setError("Please Enter Number");
           serveryId.requestFocus();
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
       soundserveryIdd.setFilters(new InputFilter[] { filter3 });
       phy_found = soundserveryIdd.getText().toString().trim();
       //  serveryId,soundserveryIdd
       if (TextUtils.isEmpty(phy_found)) {
           soundserveryIdd.setError("Please Enter Number");
           soundserveryIdd.requestFocus();
           return;
       }
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
       givecmdID.setFilters(new InputFilter[] { filter1 });
       commant = givecmdID.getText().toString().trim();
       //  serveryId,soundserveryIdd
       if (TextUtils.isEmpty(commant)) {
           givecmdID.setError("Please Enter Command");
           givecmdID.requestFocus();
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
       StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.NUTRITIONAL,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.e("Nutritionalresponse"," "+response);
                       try {
                           JSONArray array =new JSONArray(response);
                           Log.e("NutritionalaRRAYobj"," "+array);
                           for (int i=0; i<array.length(); i++){
                               JSONObject object = array.getJSONObject(i);
                               String message = object.getString("message");
                               Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                               helper = new DatabaseHelper(getApplicationContext());
                               SQLiteDatabase dbb = helper.getReadableDatabase();
                               dbb.execSQL("UPDATE insflag SET nutritionalstatusobserved='1' WHERE allinspactionid=" +insid);
                               syncNutriationDatabase1(insid,curTime,record_regs,phy_found,commant,NUTRITATION_SYNCED_WITH_SERVER);
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
                       dbb.execSQL("UPDATE insflag SET nutritionalstatusobserved='1' WHERE allinspactionid=" +insid);
                       syncNutriationDatabase1(insid,curTime,record_regs,phy_found,commant,NUTRITATION_NOT_SYNCED_WITH_SERVER);

                   }
               }) {
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("inspctn_id",insid);
               params.put("ins_time",curTime);
               params.put("malnurishd_chld",record_regs);
               params.put("chld_found_physicaly",phy_found);
               params.put("inspctr_cmnt",commant);
               Log.e("Nutritional",insid+" "+curTime+" "+record_regs+" "+phy_found+" "+commant);
               return params;
           }
       };
       //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
       stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
       VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
       stringRequest.setShouldCache(false);
       volleySingleton.addToRequestQueue(stringRequest);
   }
    private void syncNutriationDatabase1(String insid,String curTime,String malnurishdchld,String sanchld,String commant,int nutritionalstatus){
        if (NatureStatus.equals("0")){
            helper.NUTRITIONALINSERT(insid,curTime,malnurishdchld,sanchld,commant,nutritionalstatus);
        }
        else {
            helper.NUTRITIONALUPDATE(dbid,insid,curTime,malnurishdchld,sanchld,commant,nutritionalstatus);
        }
        BuildingReturndata();
    }

    public void BuildingReturndata(){
        Intent intent = new Intent(NutritionalStatusObservedActivity.this, InspectionListActivity.class);
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
//                if (rregistrationID.isChecked() || rcfDayID.isChecked()){
//                    if (mutapeRagistationID.isChecked() || mutapethatdayID.isChecked()){
//                        InputFilter filter1 = new InputFilter() {
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
//                        givecmdID.setFilters(new InputFilter[] { filter1 });
//                        commant = givecmdID.getText().toString().trim();
//                        update_nutritn_serv_inspectn();
//                    }
//                    else {
//                        Toast.makeText(NutritionalStatusObservedActivity.this,"Select No Of San Children Using Muac Tape",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//                    Toast.makeText(NutritionalStatusObservedActivity.this,"Select No Of Severely Malnourished Children",Toast.LENGTH_SHORT).show();
//                }
//
//                break;
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
            if(nutriationnetwokchecker!=null)
                unregisterReceiver(nutriationnetwokchecker);
            if (broadcastReceivernutriation!=null)
                unregisterReceiver(broadcastReceivernutriation);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
