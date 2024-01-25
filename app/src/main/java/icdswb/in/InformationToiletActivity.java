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
import icdswb.in.ActivityDatabase.ToiletNetwokchecker;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSIDTOILETSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;

public class InformationToiletActivity extends AppCompatActivity implements View.OnClickListener{
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
    TextView toiletID,toiletlastID,awcstvId;
    RadioButton YtoiletID,NtoiletID,useableID,NouseableID;
    RadioGroup YNradioGroup,radioGroup1;
    String YNtoilet = "";
    String toiletusable = "";
    EditText cmdedittextID;
    Button savenextID;
    String inspctrcmnt = "";
    String curDate,curTime;
    String planid ="NA";
    String yncdpo ="NA";
    String ynacdpio ="NA";
    String YnSupervisor ="NA";
    String Ynworker ="NA";
    String Yhelper ="NA";
    String insid;
    String lstinspctnbuldrep ="NA";
    String lstinspctntoiletrep ="NA";
    String lstinspctnkitchenrep ="NA";
    String lstinspctnpserep ="NA";
    String lstinspctnelectricrep ="NA";
    String lstinspctndrnkwaterrep ="NA";
    String lstinspctnfoodrep ="NA";
    String dbid ="NA";
    DatabaseHelper helper;
    String idbuilding,toiletInformation,Etoilet,Etoiletuse,Einspctrcmnt;
    LinearLayout lv6;
    public static final int TOILET_SYNCED_WITH_SERVER = 1;
    public static final int TOILET_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceivertoilet;
    public static final String DATA_SAVED_BROADCAST_TOILET = "icdswb.in.toiletsaved";
    public static final String TOILET ="http://icdswb.in/inspection_service/update_toilet_inspection.php";
    private ToiletNetwokchecker toiletNetwokchecker;
    String TOILETSTATUS = "1";
    String userID;
    private final int REQ_CODE_SPEECH_BUILDINKITCHEN = 100;
    ImageButton commandspak;
    RelativeLayout rv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_informationtoilet);
        toiletID = (TextView)findViewById(R.id.toiletID);
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
        Log.e("TOILETINFORMATION"," "+adqutspacepse+" "+kitchen+""+" "+electric+" "+" "+water+""+" "+ " "+" "+foodspace+" "+toilet+" "+awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+insid+" ");
        Log.e("LAST_INFORREPO",lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep);
        //   Log.e("dbid",dbid);
        Log.e("Ip",planid);
        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        toiletID.setText(toilet);
        toiletlastID= (TextView)findViewById(R.id.toiletlastID);
        toiletlastID.setText(lstinspctntoiletrep);
        YtoiletID = (RadioButton)findViewById(R.id.YtoiletID);
        NtoiletID = (RadioButton)findViewById(R.id.NtoiletID);
        YNradioGroup = (RadioGroup)findViewById(R.id.YNradioGroup);
        radioGroup1 =(RadioGroup)findViewById(R.id.radioGroup1);
        useableID = (RadioButton)findViewById(R.id.useableID);
        NouseableID =(RadioButton)findViewById(R.id.NouseableID);
        cmdedittextID = (EditText)findViewById(R.id.cmdedittextID);
        cmdedittextID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        cmdedittextID.setRawInputType(InputType.TYPE_CLASS_TEXT);
        //savenextID =(Button)findViewById(R.id.savenextID);
        lv6 = (LinearLayout)findViewById(R.id.lv6);
       //savenextID.setOnClickListener(this);
        rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        helper = new DatabaseHelper(this);
        broadcastReceivertoilet = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceivertoilet, new IntentFilter(DATA_SAVED_BROADCAST_TOILET));
        toiletNetwokchecker = new ToiletNetwokchecker();
        registerReceiver(toiletNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        lv6.setVisibility(View.GONE);
        YNradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.YtoiletID){
                  YNtoilet = "y";
                    Log.e("YNtoilet",YNtoilet);
                    lv6.setVisibility(View.VISIBLE);
                }
                else if (checkedId== R.id.NtoiletID){
                    YNtoilet = "n";
                    Log.e("YNtoilet",YNtoilet);
                    lv6.setVisibility(View.GONE);
//                    NouseableID.setChecked(false);
//                    useableID.setChecked(false);

                }
            }
        });
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.useableID){
                    toiletusable = "u";
                    Log.e("toiletusable",toiletusable);
                }
                else if (checkedId== R.id.NouseableID){
                    toiletusable = "nu";
                    Log.e("toiletusable",toiletusable);
                }
            }
        });
        initToolBar();
      //  Cursor cursor = helper.getReadableDatabase().
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
                idbuilding = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                toiletInformation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INFORMATIONOFTOILET));
                Log.e("TOILETDETAILS"," "+idbuilding+" "+toiletInformation);
            }
            while (cursor.moveToNext());
        }

        if (toiletInformation.equals("0")) {

        }
        else {
            edittoilet();

        }
       if (isNetworkAvailable()){

       }
       else {
       //    String query = "SELECT * FROM " + TABLE_TOILETSYNC + " where " + TABLE_INSIDTOILETSYNC + "=" +insid+ " and " +TABLE_TOILETSTATUS+ "=" +TOILETSTATUS;
           String query = "SELECT * FROM toiletsync WHERE insidtoiletsync=" + insid;
           SQLiteDatabase db = helper.getReadableDatabase();
           Cursor  cc = db.rawQuery(query,null);
           if (cc.moveToFirst()) {
               do {
                   String toiletid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_TOILETIDSYNC));
                   String insidtoiletsync =  cc.getString(cc.getColumnIndex(TABLE_INSIDTOILETSYNC));
                   YNtoilet = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_YNTOILETSYNC));
                   toiletusable = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_TOILETUSABLESYNC));
                   inspctrcmnt = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_INSPCTRCMNTSYNC));
                   String curtimesync =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CURTIMESYNC));
                   String lastisacrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LASTISACREPSYNC));
                   String lstinspctntoiletrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LSTINSPCTNTOILETREPSYNC));
                   String toiletstatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_TOILETSTATUS));

                   Log.e("TOILETDETAILSSYNC"," "+toiletid+" "+insidtoiletsync+" "+YNtoilet+" "+toiletusable+" "+inspctrcmnt+" "+curtimesync+" "+lastisacrepsync+" "+lstinspctntoiletrepsync+" "+toiletstatus);
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
                                update_toilet_inspection();
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
            startActivityForResult(intent,REQ_CODE_SPEECH_BUILDINKITCHEN);
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
            case REQ_CODE_SPEECH_BUILDINKITCHEN: {
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
        if (YNtoilet.equals("y")){
            YtoiletID.setChecked(true);
        }
        else if (YNtoilet.equals("n")){
            NtoiletID.setChecked(true);
        }
        else {
            YtoiletID.setChecked(false);
            NtoiletID.setChecked(false);
        }
        if (toiletusable.equals("u")){
            useableID.setChecked(true);
        }
        else if (toiletusable.equals("nu")){
            NouseableID.setChecked(true);
        }
        else {
            useableID.setChecked(false);
            NouseableID.setChecked(false);
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

   private void update_toilet_inspection(){
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
           if (YtoiletID.isChecked() || NtoiletID.isChecked()){
            if (YNtoilet.equals("y")){
                if (useableID.isChecked() || NouseableID.isChecked()){
                    cmdedittextID.setError("Please enter Command");
                    cmdedittextID.requestFocus();
                }
                else {
                    Toast.makeText(InformationToiletActivity.this,"SELECT CONDITION",Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT CONDITION";
                    createDialog(title,msg);
                }
            }
            else {
                cmdedittextID.setError("Please enter Command");
                cmdedittextID.requestFocus();
            }
           }
           else {
               Toast.makeText(InformationToiletActivity.this,"SELECT TOLET WITHIN AWC PREMISES",Toast.LENGTH_SHORT).show();
               String title = "Message Box";
               String msg = "SELECT TOLET WITHIN AWC PREMISES";
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
       StringRequest stringRequest = new StringRequest(Request.Method.POST,TOILET,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.e("Toilet"," "+response);
                       try {

                           JSONArray array =new JSONArray(response);
                           Log.e("TObJ"," "+array);
                           for (int i=0; i<array.length(); i++) {
                               JSONObject object = array.getJSONObject(i);
                               String message = object.getString("message");
                               Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                               helper = new DatabaseHelper(getApplicationContext());
                               SQLiteDatabase dbb = helper.getReadableDatabase();
                               dbb.execSQL("UPDATE insflag SET informationoftoilet='1' WHERE allinspactionid=" +insid);
                               syncToiletsaveDatabase(insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,TOILET_SYNCED_WITH_SERVER);
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
                       if (YNtoilet.equals("y")) {
                           syncToiletsaveDatabase(insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,TOILET_NOT_SYNCED_WITH_SERVER);
                           helper = new DatabaseHelper(getApplicationContext());
                           SQLiteDatabase dbb = helper.getReadableDatabase();
                           dbb.execSQL("UPDATE insflag SET informationoftoilet='1' WHERE allinspactionid=" +insid);
                       }
                       else {

                       }
                       if (YNtoilet.equals("n")) {
                           toiletusable = "null";
                           syncToiletsaveDatabase(insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,TOILET_NOT_SYNCED_WITH_SERVER);
                           helper = new DatabaseHelper(getApplicationContext());
                           SQLiteDatabase dbb = helper.getReadableDatabase();
                           dbb.execSQL("UPDATE insflag SET informationoftoilet='1' WHERE allinspactionid=" +insid);
                       }
                       else {

                       }

                   }
               }) {
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("inspctn_id",insid);
               params.put("toilet",YNtoilet);
               if (YNtoilet.equals("y")) {
                   params.put("toilet_use", toiletusable);
               }
               params.put("inspctr_cmnt",inspctrcmnt);
               params.put("ins_time",curTime);
               params.put("last_isac_rep",toilet);
               params.put("last_inspctn_rep",lstinspctntoiletrep);
               Log.e("TOILET",insid+" "+YNtoilet+" "+toiletusable+" "+inspctrcmnt+" "+toilet+" "+toilet+" "+lstinspctntoiletrep);
               return params;
           }
       };
       //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
       stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
       VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
       stringRequest.setShouldCache(false);
       volleySingleton.addToRequestQueue(stringRequest);

    }
   private void syncToiletsaveDatabase(String insid,String YNtoilet,String toiletusable,String inspctrcmnt,String curTime,String toilet,String lstinspctntoiletrep,int toiletstatus){
        if (toiletInformation.equals("0")) {
           helper.TOILETINSERT(insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,toiletstatus);
        }
       else {
           helper.TOILETUPDATE(dbid,insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,toiletstatus);
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
    private void edittoilet(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.TOILETEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TOILETEDIT"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("TOILETEDIT"," "+array);
                            JSONObject object = array.getJSONObject(0);
                            JSONObject jsonObject = object.getJSONObject("toilet_dtl");
                            Etoilet = jsonObject.getString("toilet");
                            Etoiletuse = jsonObject.getString("toilet_use");
                            Einspctrcmnt = jsonObject.getString("inspctr_cmnt");
                            Log.e("TOILETEDDATA"," "+Etoilet+" "+Etoiletuse+" "+Einspctrcmnt);
                            if (Etoilet.equals("y")){
                                YtoiletID.setChecked(true);
                            }
                            else if (Etoilet.equals("n")){
                                NtoiletID.setChecked(true);
                            }
                            else {
                                YtoiletID.setChecked(false);
                                NtoiletID.setChecked(false);
                            }
                            if (Etoiletuse.equals("u")){
                                useableID.setChecked(true);
                            }
                            else if (Etoiletuse.equals("nu")){
                                NouseableID.setChecked(true);
                            }
                            else {
                                useableID.setChecked(false);
                                NouseableID.setChecked(false);
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
    public void DataSendNEXTActivity(){
        Intent intent = new Intent(InformationToiletActivity.this, InformationKitchenActivity.class);
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
//                update_toilet_inspection();
//                break;
//                default:
//        }
    }



//    @Override
//    public void onBackPressed() {
//       Intent intent = new Intent(OtherInspectionActivity.this,NavigationDrawerActivity.class);
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
            if(toiletNetwokchecker!=null)
                unregisterReceiver(toiletNetwokchecker);
            if (broadcastReceivertoilet!=null)
                unregisterReceiver(broadcastReceivertoilet);

        }catch(Exception e){}
        super.onDestroy();
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
            if (YtoiletID.isChecked() || NtoiletID.isChecked()){
                if (YNtoilet.equals("y")){
                    if (useableID.isChecked() || NouseableID.isChecked()){
                        cmdedittextID.setError("Please enter Command");
                        cmdedittextID.requestFocus();
                    }
                    else {
                        Toast.makeText(InformationToiletActivity.this,"SELECT CONDITION",Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT CONDITION";
                        createDialog(title,msg);
                    }
                }
                else {
                    cmdedittextID.setError("Please enter Command");
                    cmdedittextID.requestFocus();
                }
            }
            else {
                Toast.makeText(InformationToiletActivity.this,"SELECT TOLET WITHIN AWC PREMISES",Toast.LENGTH_SHORT).show();
                String title = "Message Box";
                String msg = "SELECT TOLET WITHIN AWC PREMISES";
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST,TOILET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Toilet"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("TObJ"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET informationoftoilet='1' WHERE allinspactionid=" +insid);
                                syncToiletsaveDatabase1(insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,TOILET_SYNCED_WITH_SERVER);
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
                        if (YNtoilet.equals("y")) {
                            syncToiletsaveDatabase1(insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,TOILET_NOT_SYNCED_WITH_SERVER);
                            helper = new DatabaseHelper(getApplicationContext());
                            SQLiteDatabase dbb = helper.getReadableDatabase();
                            dbb.execSQL("UPDATE insflag SET informationoftoilet='1' WHERE allinspactionid=" +insid);
                        }
                        else {

                        }
                        if (YNtoilet.equals("n")) {
                            toiletusable = "null";
                            syncToiletsaveDatabase1(insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,TOILET_NOT_SYNCED_WITH_SERVER);
                            helper = new DatabaseHelper(getApplicationContext());
                            SQLiteDatabase dbb = helper.getReadableDatabase();
                            dbb.execSQL("UPDATE insflag SET informationoftoilet='1' WHERE allinspactionid=" +insid);
                        }
                        else {

                        }

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("toilet",YNtoilet);
                if (YNtoilet.equals("y")) {
                    params.put("toilet_use", toiletusable);
                }
                params.put("inspctr_cmnt",inspctrcmnt);
                params.put("ins_time",curTime);
                params.put("last_isac_rep",toilet);
                params.put("last_inspctn_rep",lstinspctntoiletrep);
                Log.e("TOILET",insid+" "+YNtoilet+" "+toiletusable+" "+inspctrcmnt+" "+toilet+" "+toilet+" "+lstinspctntoiletrep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }
    private void syncToiletsaveDatabase1(String insid,String YNtoilet,String toiletusable,String inspctrcmnt,String curTime,String toilet,String lstinspctntoiletrep,int toiletstatus){
        if (toiletInformation.equals("0")) {
            helper.TOILETINSERT(insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,toiletstatus);
        }
        else {
            helper.TOILETUPDATE(dbid,insid,YNtoilet,toiletusable,inspctrcmnt,curTime,toilet,lstinspctntoiletrep,toiletstatus);
        }
        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(InformationToiletActivity.this, InspectionListActivity.class);
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
}
