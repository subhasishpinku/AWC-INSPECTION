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
import icdswb.in.ActivityDatabase.KitchenNetwokchecker;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_KITCHENINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;


public class InformationKitchenActivity extends AppCompatActivity implements View.OnClickListener{
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
    TextView kitchenID,kitchenlastID,awcstvId;
    Button savenextID;
    RadioGroup kitchenradioGroup,GcookingID;
    RadioButton YkitchenID,NkitchenID,firewoodID,keroseneID,lpgID,otherID;
    EditText cmdedittextID;
    String inspctrcmnt = "";
    String cookdone = "";
    String YNkitchen = "";
    String curDate,curTime;
    String planid  = "NA";
    String yncdpo ="NA";
    String ynacdpio ="NA";
    String YnSupervisor ="NA";
    String Ynworker ="NA";
    String Yhelper ="NA";
    String insid;
    String lstinspctnbuldrep = "NA";
    String lstinspctntoiletrep = "NA";
    String lstinspctnkitchenrep = "NA";
    String lstinspctnpserep = "NA";
    String lstinspctnelectricrep = "NA";
    String lstinspctndrnkwaterrep= "NA";
    String lstinspctnfoodrep= "NA";
    String dbid= "NA";
    DatabaseHelper helper;
    String Eseprtkitchn = "NA";
    String Ecookdone = "NA";
    String Einspctrcmnt = "NA";
    String idkitchen = "NA";
    String kitchenInformation = "NA";
    public static final int KITCHEN_SYNCED_WITH_SERVER = 1;
    public static final int KITCHEN_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiverKitchen;
    public static final String DATA_SAVED_BROADCAST_KITCHEN = "icdswb.in.kitcheningdatasaved";
    private KitchenNetwokchecker kitchenNetwokchecker;
    String KITCHENSTATUS ="1";
    String userID;
    private final int REQ_CODE_SPEECH_KITCHEN = 100;
    ImageButton commandspak;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_informationkitchenactivity);
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
        Log.e("Kp", planid);
        Log.e("TOILETINFORMATION", " " + toilet + " " + awcscode + " " + awcsname + " " + dbdistid + " " + dbprojectid + " " + dbsectorid + " " + dbcenterid + " " + projectnamedb + " " + districnamedb + " " + sectorrnamedb + " " + centernamedb + " " + insid + " ");
        //kitchen = intent.getStringExtra("kitchen");
        //adqutspacepse = intent.getStringExtra("adqutspacepse");
       // electric = intent.getStringExtra("electric");
       // water = intent.getStringExtra("water");
       // foodspace = intent.getStringExtra("foodspace");
        Log.e("KITCHENN", " " + kitchen + " " + adqutspacepse + " " + electric + " " + water + " " + foodspace + " ");
      //  lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
       // lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
       // lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
       // lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
       // lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
       // lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
       // lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        Log.e("LAST_INFOKITCHENRREPO", lstinspctnbuldrep + " " + lstinspctntoiletrep + " " + lstinspctnkitchenrep + " " + lstinspctnpserep + " " + lstinspctnelectricrep + " " + lstinspctndrnkwaterrep + " " + lstinspctnfoodrep);
        kitchenlastID = (TextView) findViewById(R.id.kitchenlastID);
        kitchenlastID.setText(lstinspctnkitchenrep);
        kitchenID = (TextView) findViewById(R.id.kitchenID);
        //savenextID = (Button) findViewById(R.id.savenextID);
        //savenextID.setOnClickListener(this);
        kitchenID.setText(kitchen);
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
        initToolBar();
        allradiobutton();
        helper = new DatabaseHelper(this);
     //   Cursor cursor = helper.getReadableDatabase().
        //        rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
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
                idkitchen = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                kitchenInformation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INFORMATIONOFKITCHEN));
                Log.e("KITCHEN", " " + idkitchen + " " + kitchenInformation);
            }
            while (cursor.moveToNext());
        }
        if (kitchenInformation.equals("0")) {

        } else {
            editKitchen();

        }
        broadcastReceiverKitchen = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiverKitchen, new IntentFilter(DATA_SAVED_BROADCAST_KITCHEN));
        kitchenNetwokchecker = new KitchenNetwokchecker();
        registerReceiver(kitchenNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (isNetworkAvailable()) {

        } else {
         //   String query = "SELECT * FROM " + TABLE_KITCHENSYNC + " where " + TABLE_KITCHENINSID + "=" + insid + " and " + TABLE_KITCHENSTATUS + "=" + KITCHENSTATUS;
            String query = "SELECT * FROM kitchensync WHERE kitcheninsidsync=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cc = db.rawQuery(query, null);
            if (cc.moveToFirst()) {
                do {
                    cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_KITCHENIDSYNC));
                    cc.getString(cc.getColumnIndex(TABLE_KITCHENINSID));
                    YNkitchen = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SEPRTKITCHN));
                    cookdone = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_SEPRTCOOKDONEITCHNSYNC));
                    inspctrcmnt = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_TOILETINSPCTRCMNTSYNC));
                    String curtimesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_INSTIMESYNC));
                    String lastisacrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_TOILETLASTISACREPSYNC));
                    String lstinspctntoiletrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LASTINSPCTNREPSYNC));
                    String toiletstatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_KITCHENSTATUS));

                }
                while (cc.moveToNext());
            }
            ShowData();
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
                                update_inspectn_kitchen();
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
            startActivityForResult(intent,REQ_CODE_SPEECH_KITCHEN);
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
            case REQ_CODE_SPEECH_KITCHEN: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    cmdedittextID.setText(result.get(0));
                }
                break;
            }
        }
    }
    public void ShowData(){
        if (YNkitchen.equals("y")){
            YkitchenID.setChecked(true);
        }
        else if (YNkitchen.equals("n")){
            NkitchenID.setChecked(true);
        }
        else {
            YkitchenID.setChecked(false);
            NkitchenID.setChecked(false);
        }
        if (cookdone.equals("firewood")){
            firewoodID.setChecked(true);
        }
        else if (cookdone.equals("kerosene")){
            keroseneID.setChecked(true);
        }
        else if (cookdone.equals("lpg")){
            lpgID.setChecked(true);
        }
        else if (cookdone.equals("other")){
            otherID.setChecked(true);
        }
        else {
            firewoodID.setChecked(false);
            keroseneID.setChecked(false);
            lpgID.setChecked(false);
            otherID.setChecked(false);
        }
        cmdedittextID.setText(inspctrcmnt);

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
    private void allradiobutton(){
        YkitchenID = (RadioButton)findViewById(R.id.YkitchenID);
        NkitchenID = (RadioButton)findViewById(R.id.NkitchenID);
        firewoodID = (RadioButton)findViewById(R.id.firewoodID);
        keroseneID = (RadioButton)findViewById(R.id.keroseneID);
        lpgID = (RadioButton)findViewById(R.id.lpgID);
        otherID = (RadioButton)findViewById(R.id.otherID);
        kitchenradioGroup = (RadioGroup)findViewById(R.id.kitchenradioGroup);
        GcookingID = (RadioGroup)findViewById(R.id.GcookingID);
        kitchenradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YkitchenID){
                    YNkitchen = "y";
                }
                else if (checkedId== R.id.NkitchenID){
                    YNkitchen = "n";
                }
            }
        });
        GcookingID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.firewoodID){
                    cookdone = "firewood";
                    Log.e("cookdone",cookdone);
                }
                else if (checkedId== R.id.keroseneID){
                    cookdone = "kerosene";
                    Log.e("cookdone",cookdone);
                }
                else if (checkedId== R.id.lpgID){
                    cookdone = "lpg";
                    Log.e("cookdone",cookdone);
                }
                else if (checkedId== R.id.otherID){
                    cookdone = "other";
                    Log.e("cookdone",cookdone);
                }

            }
        });

    }
    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.savenextID:
//                update_inspectn_kitchen();
//                break;
//                default:
//        }
    }

    private void editKitchen(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KITCHENEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("KTCHENEDIT"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONObject jsonObject = object.getJSONObject("kitchen_dtl");
                            Eseprtkitchn = jsonObject.getString("seprt_kitchn");
                            Ecookdone = jsonObject.getString("cook_done");
                            Einspctrcmnt = jsonObject.getString("inspctr_cmnt");
                            Log.e("KITCHENDATA"," "+Eseprtkitchn+" "+Ecookdone+" "+Einspctrcmnt);
                            if (Eseprtkitchn.equals("y")){
                                YkitchenID.setChecked(true);
                            }
                            else if (Eseprtkitchn.equals("n")){
                                NkitchenID.setChecked(true);
                            }
                            else {
                                YkitchenID.setChecked(false);
                                NkitchenID.setChecked(false);
                            }
                            if (Ecookdone.equals("firewood")){
                                firewoodID.setChecked(true);
                            }
                            else if (Ecookdone.equals("kerosene")){
                                keroseneID.setChecked(true);
                            }
                            else if (Ecookdone.equals("lpg")){
                                lpgID.setChecked(true);
                            }
                            else if (Ecookdone.equals("other")){
                                otherID.setChecked(true);
                            }
                            else {
                                firewoodID.setChecked(false);
                                keroseneID.setChecked(false);
                                lpgID.setChecked(false);
                                otherID.setChecked(false);
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

    private void update_inspectn_kitchen(){
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
            if (YkitchenID.isChecked() || NkitchenID.isChecked()){
                if (firewoodID.isChecked() || keroseneID.isChecked() || lpgID.isChecked() || otherID.isChecked()){
                    cmdedittextID.setError("Please enter Command");
                    cmdedittextID.requestFocus();
                }
                else {
                    Toast.makeText(InformationKitchenActivity.this,"SELECT COOKING DONE THROUGH",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT COOKING DONE THROUGH";
                    createDialog(title,msg);
                }
            }
            else {
                Toast.makeText(InformationKitchenActivity.this,"SELECT SEPARATE KITCHEN SHED",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT SEPARATE KITCHEN SHED";
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KITCHEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Kitchen"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("KOBJ"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET informationofkitchen='1' WHERE allinspactionid=" +insid);
                                syncKitchensaveDatabase(insid,YNkitchen,cookdone,inspctrcmnt,curTime,kitchen,lstinspctnkitchenrep,KITCHEN_SYNCED_WITH_SERVER);
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
                        dbb.execSQL("UPDATE insflag SET informationofkitchen='1' WHERE allinspactionid=" +insid);
                        syncKitchensaveDatabase(insid,YNkitchen,cookdone,inspctrcmnt,curTime,kitchen,lstinspctnkitchenrep,KITCHEN_NOT_SYNCED_WITH_SERVER);


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("seprt_kitchn",YNkitchen);
                params.put("cook_done",cookdone);
                params.put("inspctr_cmnt",inspctrcmnt);
                params.put("ins_time",curTime);
                params.put("last_isac_rep",kitchen);
                params.put("last_inspctn_rep",lstinspctnkitchenrep);
                Log.e("KITCHEN",insid+" "+YNkitchen+" "+cookdone+" "+inspctrcmnt+" "+curTime+" "+kitchen+" "+lstinspctnkitchenrep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    private void syncKitchensaveDatabase(String insid,String YNkitchen,String cookdone,String inspctrcmnt,String curTime,String kitchen,String lstinspctnkitchenrep,int kitchenstatus){
        if (kitchenInformation.equals("0")) {
            helper.KITCHENINSERT(insid,YNkitchen,cookdone,inspctrcmnt,curTime,kitchen,lstinspctnkitchenrep,kitchenstatus);

        } else {
            helper.KITCHENUPDATE(dbid,insid,YNkitchen,cookdone,inspctrcmnt,curTime,kitchen,lstinspctnkitchenrep,kitchenstatus);
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
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(getApplicationContext(),NavigationDrawerActivity.class);
//        startActivity(intent);
//    }
    public void DataSendNEXTActivity(){
        Intent intent = new Intent(InformationKitchenActivity.this,AdequateSpacePSEActivity.class);
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
            if (YkitchenID.isChecked() || NkitchenID.isChecked()){
                if (firewoodID.isChecked() || keroseneID.isChecked() || lpgID.isChecked() || otherID.isChecked()){
                    cmdedittextID.setError("Please enter Command");
                    cmdedittextID.requestFocus();
                }
                else {
                    Toast.makeText(InformationKitchenActivity.this,"SELECT COOKING DONE THROUGH",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT COOKING DONE THROUGH";
                    createDialog(title,msg);
                }
            }
            else {
                Toast.makeText(InformationKitchenActivity.this,"SELECT SEPARATE KITCHEN SHED",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT SEPARATE KITCHEN SHED";
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KITCHEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Kitchen"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("KOBJ"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET informationofkitchen='1' WHERE allinspactionid=" +insid);
                                syncKitchensaveDatabase1(insid,YNkitchen,cookdone,inspctrcmnt,curTime,kitchen,lstinspctnkitchenrep,KITCHEN_SYNCED_WITH_SERVER);
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
                        dbb.execSQL("UPDATE insflag SET informationofkitchen='1' WHERE allinspactionid=" +insid);
                        syncKitchensaveDatabase1(insid,YNkitchen,cookdone,inspctrcmnt,curTime,kitchen,lstinspctnkitchenrep,KITCHEN_NOT_SYNCED_WITH_SERVER);


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("seprt_kitchn",YNkitchen);
                params.put("cook_done",cookdone);
                params.put("inspctr_cmnt",inspctrcmnt);
                params.put("ins_time",curTime);
                params.put("last_isac_rep",kitchen);
                params.put("last_inspctn_rep",lstinspctnkitchenrep);
                Log.e("KITCHEN",insid+" "+YNkitchen+" "+cookdone+" "+inspctrcmnt+" "+curTime+" "+kitchen+" "+lstinspctnkitchenrep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }
    private void syncKitchensaveDatabase1(String insid,String YNkitchen,String cookdone,String inspctrcmnt,String curTime,String kitchen,String lstinspctnkitchenrep,int kitchenstatus){
        if (kitchenInformation.equals("0")) {
            helper.KITCHENINSERT(insid,YNkitchen,cookdone,inspctrcmnt,curTime,kitchen,lstinspctnkitchenrep,kitchenstatus);

        } else {
            helper.KITCHENUPDATE(dbid,insid,YNkitchen,cookdone,inspctrcmnt,curTime,kitchen,lstinspctnkitchenrep,kitchenstatus);
        }
        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(InformationKitchenActivity.this, InspectionListActivity.class);
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
            if(kitchenNetwokchecker!=null)
                unregisterReceiver(kitchenNetwokchecker);
            if (broadcastReceiverKitchen!=null)
                unregisterReceiver(broadcastReceiverKitchen);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
