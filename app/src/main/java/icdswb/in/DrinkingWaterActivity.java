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
import icdswb.in.ActivityDatabase.DrinkingNetwokchecker;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DRINKINGWATERINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;

public class DrinkingWaterActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    String cdponame = "NA";
    String acdpocont ="NA";
    String buildstruc ="NA";
    String acdponame ="NA";
    String cdpocontact ="NA";
    String workerno ="NA";
    String worker_nm ="NA";
    String awcslat = "NA";
    String supvsrname ="NA";
    String awcsslong ="NA";
    String helperno ="NA";
    String awcs_adrs ="NA";
    String helpernm ="NA";
    String buildon = "NA";
    String supvsrno = "NA";
    String awcsid ="NA";
    String currentdate ="NA";
    String yncdpo ="NA";
    String ynacdpio ="NA";
    String YnSupervisor ="NA";
    String Ynworker = "NA";
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
    String kitchen = "NA";
    String adqutspacepse = "NA";
    String electric = "NA";
    String water = "NA";
    String foodspace = "NA";
    Button savenextID;
    TextView drinkID,drinkingID,awcstvId;
    RadioGroup GwondrinkingID;
    RadioButton avalableID,notavableID;
    String owndrinkwater = "NA";
    String curDate,curTime;
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
    String iddrikingwater,drinkingwater,owndrink;
    public static final int DRINKINGWATER_SYNCED_WITH_SERVER = 1;
    public static final int TDRINKINGWATER_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiverdrinkingwater;
    private DrinkingNetwokchecker drinkingNetwokchecker;
    public static final String DATA_SAVED_BROADCAST_DRINLINGWATER = "icdswb.in.drinkingwatersaved";
    String DRINKINGWATERSTATUS = "0";
    String owndrinkwatersync ="";
    String userID;
    private final int REQ_DRINKINGWATER = 100;
    ImageButton commandspak;
    EditText cmdedittextID;
    String drinkwaterremark;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_drinkingwateractivity);
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
       // Log.e("dbid",dbid);
        awcstvId =(TextView)findViewById(R.id.awcstvId);
        awcstvId.setText(awcsname+" "+ "("+awcscode+")");
        Log.e("Dwp",planid);
        Log.e("DRINKINGWATER"," "+toilet+" "+awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+insid+" ");
        //kitchen = intent.getStringExtra("kitchen");
       // adqutspacepse = intent.getStringExtra("adqutspacepse");
       // electric = intent.getStringExtra("electric");
       // water = intent.getStringExtra("water");
       // foodspace = intent.getStringExtra("foodspace");
        Log.e("DRINKINGWATER"," "+kitchen+" "+adqutspacepse+" "+electric+" "+water+" "+foodspace+" ");
       // lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
      //  lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
       // lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
       // lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
       // lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
       // lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
       // lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        Log.e("LAST_DRINKWATERREPO",lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep);

        //savenextID = (Button)findViewById(R.id.savenextID);
        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        drinkID = (TextView)findViewById(R.id.drinkID);
        drinkID.setText(water);
        drinkingID =(TextView)findViewById(R.id.drinkingID);
        drinkingID.setText(lstinspctndrnkwaterrep);
      //  savenextID.setOnClickListener(this);
        helper =new DatabaseHelper(this);
        initToolBar();
        drinkingwaterradiogroupe();
    //    Cursor cursor = helper.getReadableDatabase().
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
        if (cursor.moveToFirst()){
            do {
                iddrikingwater = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                drinkingwater = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATER));
                Log.e("DRINKINGWATER"," "+iddrikingwater+" "+drinkingwater);
            }
            while (cursor.moveToNext());
        }
        if (drinkingwater.equals("0")){

        }
        else {
            editDrinkingwater();
        }

        broadcastReceiverdrinkingwater = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiverdrinkingwater, new IntentFilter(DATA_SAVED_BROADCAST_DRINLINGWATER));
        drinkingNetwokchecker = new DrinkingNetwokchecker();
        registerReceiver(drinkingNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (isNetworkAvailable()){

        }
        else {
         //   String query = "SELECT * FROM " + TABLE_DRINKINGWATERSYNC + " where " + TABLE_DRINKINGWATERINSID + "=" +insid+ " and " +TABLE_DRINKINGWATERSTATUS+ "=" +DRINKINGWATERSTATUS;
            String query = "SELECT * FROM drinkingwatersync WHERE drinkingwaterinsid=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor  cc = db.rawQuery(query,null);
            if (cc.moveToFirst()) {
                do {
                    String drinkingwateridsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERIDSYNC));
                    String drinkingwaterinsid =  cc.getString(cc.getColumnIndex(TABLE_DRINKINGWATERINSID));
                    owndrinkwatersync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATEROWNDRINKWATERSYNC));
                    String drinkingwatercurtimesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERCURTIMESYNC));
                    String  drinkingwaterlastisacrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERLASTISACREPSYNC));
                    String lastinspctnrepdrinkingsync =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_LASTINSPCTNREPDRINKINGSYNC));
                    String drinkingwaterstatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERSTATUS));
                    Log.e("DRINKINGWATERSYNC"," "+drinkingwateridsync+" "+drinkingwaterinsid+" "+owndrinkwatersync+" "+drinkingwatercurtimesync+" "+drinkingwaterlastisacrepsync+" "+lastinspctnrepdrinkingsync+" "+drinkingwaterstatus);
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
                                savedata();
                                break;
                            default:
                        }
                        return true;
                    }
                });
        cmdedittextID = (EditText)findViewById(R.id.cmdedittextID);
        cmdedittextID.setImeOptions(EditorInfo.IME_ACTION_DONE);
        cmdedittextID.setRawInputType(InputType.TYPE_CLASS_TEXT);
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
            startActivityForResult(intent,REQ_DRINKINGWATER);
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
            case REQ_DRINKINGWATER: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    cmdedittextID.setText(result.get(0));
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

    public void showdata(){
        if (owndrinkwatersync.equals("y")){
            avalableID.setChecked(true);
        }
        else if (owndrinkwatersync.equals("n")){
            notavableID.setChecked(true);
        }
        else {
            avalableID.setChecked(false);
            notavableID.setChecked(false);
        }
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
     public void drinkingwaterradiogroupe(){
         GwondrinkingID = (RadioGroup)findViewById(R.id.GwondrinkingID);
         avalableID = (RadioButton)findViewById(R.id.avalableID);
         notavableID = (RadioButton)findViewById(R.id.notavableID);
         GwondrinkingID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if (checkedId== R.id.avalableID){
                     owndrinkwater = "y";
                 }
                 else if (checkedId== R.id.notavableID){
                     owndrinkwater = "n";
                 }
             }
         });

    }

    private void editDrinkingwater(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DRINKINGEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("DRINKINGWATER"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray jsonArray = object.getJSONArray("drnk_dtl");
                            for (int i = 0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                owndrink = jsonObject.getString("own_drink");
                                String drinkwaterremark = jsonObject.getString("drink_water_remark");
                                cmdedittextID.setText(drinkwaterremark);
                                Log.e("owndrink"," "+owndrink+" "+drinkwaterremark) ;
                                if (owndrink.equals("y")){
                                    avalableID.setChecked(true);
                                }
                                else if (owndrink.equals("n")){
                                    notavableID.setChecked(true);
                                }
                                else {
                                    avalableID.setChecked(false);
                                    notavableID.setChecked(false);
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
                Log.e("INSSS",insid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
      public void savedata(){
          if (avalableID.isChecked() || notavableID.isChecked()){
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
              drinkwaterremark = cmdedittextID.getText().toString().trim();
              if (TextUtils.isEmpty(drinkwaterremark)) {
                  cmdedittextID.setError("Please Enter Comment");
                  cmdedittextID.requestFocus();
                  return;
              }
              update_drink_water_inspection();
          }
          else {
              Toast.makeText(DrinkingWaterActivity.this,"SELECT OWN DRINKING WATER SOURCE",Toast.LENGTH_SHORT).show();
              String title = "Message Box";
              String msg = "SELECT OWN DRINKING WATER SOURCE";
              createDialog(title,msg);

          }

      }
     private void update_drink_water_inspection(){

         Calendar cc = Calendar.getInstance();
         System.out.println("Current time => " + cc.getTime());
         SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
         curDate = df3.format(cc.getTime());
         Calendar ccc = Calendar.getInstance();
         SimpleDateFormat time = new SimpleDateFormat("HH:mm");
         curTime = time.format(ccc.getTime());
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DRINKING,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         Log.e("DRINKING"," "+response);
                         try {

                             JSONArray array =new JSONArray(response);
                             Log.e("DRINKINGOBJ"," "+array);
                             for (int i=0; i<array.length(); i++) {
                                 JSONObject object = array.getJSONObject(i);
                                 String message = object.getString("message");
                                 Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                 helper = new DatabaseHelper(getApplicationContext());
                                 SQLiteDatabase dbb = helper.getReadableDatabase();
                                 dbb.execSQL("UPDATE insflag SET drinkingwater='1' WHERE allinspactionid=" +insid);
                                 syncDrinkingwatersaveDatabase(insid,owndrinkwater,curTime,water,lstinspctndrnkwaterrep,drinkwaterremark,DRINKINGWATER_SYNCED_WITH_SERVER);

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
                         dbb.execSQL("UPDATE insflag SET drinkingwater='1' WHERE allinspactionid=" +insid);
                         syncDrinkingwatersaveDatabase(insid,owndrinkwater,curTime,water,lstinspctndrnkwaterrep,drinkwaterremark,TDRINKINGWATER_NOT_SYNCED_WITH_SERVER);
                     }
                 }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();
                 params.put("inspctn_id",insid);
                 params.put("own_drink_water",owndrinkwater);
                 params.put("ins_time",curTime);
                 params.put("last_isac_rep",water);
                 params.put("last_inspctn_rep",lstinspctndrnkwaterrep);
                 params.put("drink_water_remark",drinkwaterremark);
                 Log.e("DRINKINGWATERACTIVITY",insid+" "+owndrinkwater+" "+curTime+" "+water+" "+lstinspctndrnkwaterrep+" "+drinkwaterremark);
                 return params;
             }
         };
         //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
         stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
         stringRequest.setShouldCache(false);
         volleySingleton.addToRequestQueue(stringRequest);

     }
    private void syncDrinkingwatersaveDatabase(String insid,String owndrinkwater,String curTime,String water,String lstinspctndrnkwaterrep,String drinkwaterremark,int drinkingwaterstatus){
        if (drinkingwater.equals("0")) {
            helper.DRINKINGWATERINSERT(insid,owndrinkwater,curTime,water,lstinspctndrnkwaterrep,drinkwaterremark,drinkingwaterstatus);
        }
        else {
            helper.DRINKINGWATERUPDATE(dbid,insid,owndrinkwater,curTime,water,lstinspctndrnkwaterrep,drinkwaterremark,drinkingwaterstatus);
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
        Intent intent = new Intent(DrinkingWaterActivity.this, FoodStoredDetailActivity.class);
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
        if (avalableID.isChecked() || notavableID.isChecked()){
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
            drinkwaterremark = cmdedittextID.getText().toString().trim();
            if (TextUtils.isEmpty(drinkwaterremark)) {
                cmdedittextID.setError("Please Enter Comment");
                cmdedittextID.requestFocus();
                return;
            }
            update_drink_water_inspection1();
        }
        else {
            Toast.makeText(DrinkingWaterActivity.this,"SELECT OWN DRINKING WATER SOURCE",Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT OWN DRINKING WATER SOURCE";
            createDialog(title,msg);

        }
    }
    public void update_drink_water_inspection1(){
        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DRINKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("DRINKING"," "+response);
                        try {

                            JSONArray array =new JSONArray(response);
                            Log.e("DRINKINGOBJ"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET drinkingwater='1' WHERE allinspactionid=" +insid);
                                syncDrinkingwatersaveDatabase1(insid,owndrinkwater,curTime,water,lstinspctndrnkwaterrep,drinkwaterremark,DRINKINGWATER_SYNCED_WITH_SERVER);

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
                        dbb.execSQL("UPDATE insflag SET drinkingwater='1' WHERE allinspactionid=" +insid);
                        syncDrinkingwatersaveDatabase1(insid,owndrinkwater,curTime,water,lstinspctndrnkwaterrep,drinkwaterremark,TDRINKINGWATER_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inspctn_id",insid);
                params.put("own_drink_water",owndrinkwater);
                params.put("ins_time",curTime);
                params.put("last_isac_rep",water);
                params.put("last_inspctn_rep",lstinspctndrnkwaterrep);
                params.put("drink_water_remark",drinkwaterremark);
                Log.e("DRINKINGWATERACTIVITY",insid+" "+owndrinkwater+" "+curTime+" "+water+" "+lstinspctndrnkwaterrep+" "+drinkwaterremark);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);

    }
    private void syncDrinkingwatersaveDatabase1(String insid,String owndrinkwater,String curTime,String water,String lstinspctndrnkwaterrep,String drinkwaterremark,int drinkingwaterstatus){
        if (drinkingwater.equals("0")) {
            helper.DRINKINGWATERINSERT(insid,owndrinkwater,curTime,water,lstinspctndrnkwaterrep,drinkwaterremark,drinkingwaterstatus);
        }
        else {
            helper.DRINKINGWATERUPDATE(dbid,insid,owndrinkwater,curTime,water,lstinspctndrnkwaterrep,drinkwaterremark,drinkingwaterstatus);
        }
        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(DrinkingWaterActivity.this, InspectionListActivity.class);
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
//            case R.id.savenextID:
//               if (avalableID.isChecked() || notavableID.isChecked()){
//                   update_drink_water_inspection();
//               }
//               else {
//                   Toast.makeText(DrinkingWaterActivity.this,"SELECT OWN DRINKING WATER SOURCE",Toast.LENGTH_SHORT).show();
//
//               }
//                break;
//                default:
        }
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
            if(drinkingNetwokchecker!=null)
                unregisterReceiver(drinkingNetwokchecker);
            if (broadcastReceiverdrinkingwater!=null)
                unregisterReceiver(broadcastReceiverdrinkingwater);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
