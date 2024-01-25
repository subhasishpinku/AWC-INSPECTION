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
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import icdswb.in.ActivityDatabase.FoodNetwokchecker;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;


public class FoodStoredDetailActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    String toilet = "NA";
    String awcscode = "NA";
    String awcsname = "NA";
    String dbdistid = "NA";
    String dbprojectid ="NA";
    String dbsectorid = "NA";
    String dbcenterid ="NA";
    String projectnamedb = "NA";
    String districnamedb = "NA";
    String sectorrnamedb ="NA";
    String centernamedb ="NA";
    String insid;
    String kitchen = "NA";
    String adqutspacepse = "NA";
    String electric = "NA";
    String water = "NA";
    String cdponame = "NA";
    String acdpocont ="NA";
    String buildstruc ="NA";
    String acdponame ="NA";
    String cdpocontact ="NA";
    String workerno ="NA";
    String worker_nm ="NA";
    String awcslat ="NA";
    String supvsrname ="NA";
    String awcsslong ="NA";
    String helperno ="NA";
    String awcs_adrs ="NA";
    String helpernm ="NA";
    String buildon ="NA";
    String supvsrno ="NA";
    String awcsid ="NA";
    String currentdate ="NA";
    String yncdpo ="NA";
    String ynacdpio ="NA";
    String YnSupervisor = "NA";
    String Ynworker ="NA";
    String Yhelper ="NA";
    String foodspace = "NA";
    TextView foodstorageID,foodlastID,awcstvId;
    RadioGroup GasfsID,GpdID,GcsbID,GssfnID,GrdqID,GspbrandID,GstockisnotlyingIG,GdalId;
    RadioButton YasfsID,NasfsID,YpcID,NpcID,YcsbID,NcsbID,YssfnID,NssfnID,goodID,averageID,poorID,YspbrandID,NspbrandID,wawcID,awwhID,otherplaceID,dalgoodId,dalaverageId,dalpoorId;
    String storespace = "NA";
    String physiclchk ="NA";
    String stkbookcmpare ="NA";
    String stksuficnt ="NA";
    String ricedal = "NA";
    String soyachunkbrnd ="NA";
    String stocklyng ="NA";
    String dalstore = "NA";
    Button saveID;
    String curDate,curTime;
    String planid ="NA";
    String lstinspctnbuldrep  ="NA";
    String lstinspctntoiletrep  ="NA";
    String lstinspctnkitchenrep  ="NA";
    String lstinspctnpserep  ="NA";
    String lstinspctnelectricrep  ="NA";
    String lstinspctndrnkwaterrep  ="NA";
    String lstinspctnfoodrep  ="NA";
    String dbid  ="NA";
    String Idfood  ="NA";
    String food  ="NA";
    String Estorespace  ="NA";
    String Ephysiclchk  ="NA";
    String Estkbookcmpare  ="NA";
    String Estksuficnt  ="NA";
    String Ericedal  ="NA";
    String Esoyachunkbrnd  ="NA";
    String dal  ="NA";
    String moilbrnd  ="NA";
    String saltbrnd  ="NA";
    String Estocklyng  ="NA";
    String cmt;
    DatabaseHelper helper;
    public static final int FOOD_SYNCED_WITH_SERVER = 1;
    public static final int FOOD_NOT_SYNCED_WITH_SERVER = 0;
    private BroadcastReceiver broadcastReceiverfood;
    private FoodNetwokchecker foodNetwokchecker;
    public static final String DATA_SAVED_BROADCAST_FOOD= "icdswb.in.foodsaved";
    String FOODSTATUS = "0";
    String foodstorespacesync = "";
    String foodphysiclchksync ="";
    String foodstkbookcmparesync ="";
    String foodstksuficntsync ="";
    String foodricedalsync = "";
    String fooddalbrndsync ="";
    String musteroilsysnc = "";
    String saltsync = "";
    String foodstocklyngsync ="";
    String userID;
    EditText musteroilId,saltId;
    ImageButton spmusterId,spsaltId;
    private final int REQ_CODE_SPEECH_MASTEROIL= 100;
    private final int REQ_CODE_SPEECH_SALT= 101;
    String saltIdd = "NA";
    String musteroilIdd ="NA";
    String cmd = "NA";
    LinearLayout withinawcId;
    EditText cmdinformation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_foodstoreddetail);
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
        Log.e("Food",planid);
        Log.e("FOODSTORAGE"," "+toilet+" "+awcscode+" "+awcsname+" "+dbdistid+" "+dbprojectid+" "+dbsectorid+" "+dbcenterid+" "+projectnamedb+" "+districnamedb+" "+sectorrnamedb+" "+centernamedb+" "+insid+" ");
        //kitchen = intent.getStringExtra("kitchen");
        //adqutspacepse = intent.getStringExtra("adqutspacepse");
        //electric = intent.getStringExtra("electric");
        //water = intent.getStringExtra("water");
        //foodspace = intent.getStringExtra("foodspace");
        foodstorageID = (TextView)findViewById(R.id.foodstorageID);
        foodstorageID.setText(foodspace);
        Log.e("FOODSTORAGE"," "+kitchen+" "+adqutspacepse+" "+electric+" "+water+" "+foodspace+" ");
//        lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
//        lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
//        lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
//        lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
//        lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
//        lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
//        lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        foodlastID = (TextView)findViewById(R.id.foodlastID);
        foodlastID.setText(lstinspctnfoodrep);
        Log.e("LAST_FOOTREPO",lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep);
        //saveID = (Button)findViewById(R.id.saveID);
       // saveID.setOnClickListener(this);
        RelativeLayout rv = (RelativeLayout)findViewById(R.id.rv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =  (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
        cmdinformation =(EditText)findViewById(R.id.cmdinformation);
        withinawcId = (LinearLayout)findViewById(R.id.withinawcId);
        withinawcId.setVisibility(View.GONE);
        musteroilId = (EditText)findViewById(R.id.musteroilId);
        saltId = (EditText)findViewById(R.id.saltId);
        spmusterId = (ImageButton)findViewById(R.id.spmusterId);
        spsaltId = (ImageButton)findViewById(R.id.spsaltId);
        spmusterId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MASTEROIL();
            }
        });
        spsaltId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SALT();
            }
        });
        initToolBar();
        radibuttonall();
        helper =new DatabaseHelper(this);
         //Cursor cursor = helper.getReadableDatabase().
        //rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
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
                Idfood = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                food = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSTOREDDETAILS));
                Log.e("DRINKINGWATER"," "+Idfood+" "+food);
            }
            while (cursor.moveToNext());
        }

        if (food.equals("0")){

        }
        else {
            editFood();
        }

        broadcastReceiverfood= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiverfood, new IntentFilter(DATA_SAVED_BROADCAST_FOOD));
        foodNetwokchecker = new FoodNetwokchecker();
        registerReceiver(foodNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (isNetworkAvailable()){

        }
        else {
        //    String query = "SELECT * FROM " + TABLE_FOODSTORESYNC + " where " + TABLE_FOODINSIDSYNC + "=" +insid+ " and " +TABLE_FOODSTATUSSYNC+ "=" +FOODSTATUS;
            String query = "SELECT * FROM foodstoresync WHERE foodinsidsync=" + insid;
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor  cc = db.rawQuery(query,null);
            if (cc.moveToFirst()) {
                do {
                    String foodstoreidsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODSTOREIDSYNC));
                    String foodinsidsync =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODINSIDSYNC));
                    foodstorespacesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODSTORESPACESYNC));
                    foodphysiclchksync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODPHYSICLCHKSYNC));
                    foodstkbookcmparesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODSTKBOOKCMPARESYNC));
                    foodstksuficntsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODSTKSUFICNTSYNC));
                    foodricedalsync =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODRICEDALSYNC));
                    fooddalbrndsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODDALSYNC));
                    musteroilsysnc = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODMAUSTEROILSYNC));
                    saltsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODSALTSYNC));
                    foodstocklyngsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODSTOCKLYNGSYNC));
                    String foodinstimesync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODINSTIMESYNC));
                    String foodlastisacrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODLASTISACREPSYNC));
                    String foodlastinspctnrepsync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODLASTINSPCTNREPSYNC));
                    cmt = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_CMT));
                    String foodstatussync = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FOODSTATUSSYNC));
                   Log.e("FOODSYNC"," "+foodstoreidsync+" "+foodinsidsync+" "+foodstorespacesync+" "+foodstkbookcmparesync+" "+foodstksuficntsync+" "+foodricedalsync+" "+fooddalbrndsync+" "+foodstocklyngsync+" "+foodinstimesync+" "+foodlastisacrepsync+" "+foodlastinspctnrepsync+" "+foodstatussync);
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
                                savedate();
                                break;
                            default:
                        }
                        return true;
                    }
                });
    }

    public void savedate(){
        if (wawcID.isChecked() || awwhID.isChecked() || otherplaceID.isChecked()) {
            if (stocklyng.equals("within_awc")){
                if (YasfsID.isChecked() || NasfsID.isChecked()) {
                    if (YpcID.isChecked() || NpcID.isChecked()) {
                        if (YcsbID.isChecked() || NcsbID.isChecked()) {
                            if (YssfnID.isChecked() || NssfnID.isChecked()) {
                                if (goodID.isChecked() || averageID.isChecked() || poorID.isChecked()) {
                                    if (dalgoodId.isChecked() || dalaverageId.isChecked() || dalpoorId.isChecked()) {
                                        int  masteroilvalue = musteroilId.getText().length();
                                        int  saltvalue = saltId.getText().length();
                                        if (masteroilvalue>0){
                                            if (saltvalue>0){
                                                update_food_store_inspection();
                                            }
                                            else {
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
                                                saltId.setFilters(new InputFilter[]{filter1});
                                                saltIdd = saltId.getText().toString().trim();
                                                if (TextUtils.isEmpty(saltIdd)) {
                                                    saltId.setError("Please Enter Salt");
                                                    saltId.requestFocus();
                                                    return;
                                                }
                                            }

                                        }
                                        else {
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
                                            musteroilId.setFilters(new InputFilter[]{filter1});
                                            musteroilIdd = musteroilId.getText().toString().trim();
                                            if (TextUtils.isEmpty(musteroilIdd)) {
                                                musteroilId.setError("Please Enter Muster Oil");
                                                musteroilId.requestFocus();
                                                return;
                                            }


                                        }
                                    }
                                    else {
                                        Toast.makeText(FoodStoredDetailActivity.this, "SELECT  DAL QUALITY", Toast.LENGTH_SHORT).show();
                                        String title = "Message Box";
                                        String msg = "SELECT  DAL QUALITY";
                                        createDialog(title,msg);
                                    }

                                } else {
                                    Toast.makeText(FoodStoredDetailActivity.this, "SELECT RICE  QUALITY", Toast.LENGTH_SHORT).show();
                                    String title = "Message Box";
                                    String msg = "SELECT RICE  QUALITY";
                                    createDialog(title,msg);
                                }

                            } else {
                                Toast.makeText(FoodStoredDetailActivity.this, "SELECT STOCK SUFFICIANT FOR NEXT 15 DAYS", Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = "SELECT STOCK SUFFICIANT FOR NEXT 15 DAYS";
                                createDialog(title,msg);
                            }


                        } else {
                            Toast.makeText(FoodStoredDetailActivity.this, "SELECT COMPARED WITH STOCK BOOK", Toast.LENGTH_SHORT).show();
                            String title = "Message Box";
                            String msg = "SELECT COMPARED WITH STOCK BOOK";
                            createDialog(title,msg);
                        }

                    } else {
                        Toast.makeText(FoodStoredDetailActivity.this, "SELECT FOOD STOCK", Toast.LENGTH_SHORT).show();
                        String title = "Message Box";
                        String msg = "SELECT FOOD STOCK";
                        createDialog(title,msg);
                    }
                } else {
                    Toast.makeText(FoodStoredDetailActivity.this, "SELECT ADEQUATE SPACE FOR STORAGE", Toast.LENGTH_SHORT).show();
                    String title = "Message Box";
                    String msg = "SELECT ADEQUATE SPACE FOR STORAGE";
                    createDialog(title,msg);
                }
            }
            else if (stocklyng.equals("aww_resdnc")){
                update_food_store_inspection();
            }

            else if (stocklyng.equals("Othr_plc")){
                update_food_store_inspection();
            }
            else {

            }
        } else {
            Toast.makeText(FoodStoredDetailActivity.this, "SELECT STOCK IS NOT LYING", Toast.LENGTH_SHORT).show();
            String title = "Message Box";
            String msg = "SELECT STOCK IS NOT LYING";
            createDialog(title,msg);

        }
    }
   public void showdata(){
       if (foodstorespacesync.equals("y")){
           YasfsID.setChecked(true);
       }
       else if (foodstorespacesync.equals("n")){
           NasfsID.setChecked(true);
       }
       else {
           YasfsID.setChecked(false);
           NasfsID.setChecked(false);
       }
       if (foodphysiclchksync.equals("y")){
           YpcID.setChecked(true);
       }
       else if (foodphysiclchksync.equals("n")){
           NpcID.setChecked(true);
       }
       else {
           YpcID.setChecked(false);
           NpcID.setChecked(false);
       }
       if (foodstkbookcmparesync.equals("y")){
           YcsbID.setChecked(true);
       }
       else if (foodstkbookcmparesync.equals("n")){
           NcsbID.setChecked(true);
       }
       else {
           YcsbID.setChecked(false);
           NcsbID.setChecked(false);
       }
       if (foodstksuficntsync.equals("y")){
           YssfnID.setChecked(true);
       }
       else if (foodstksuficntsync.equals("n")){
           NssfnID.setChecked(true);
       }
       else {
           YssfnID.setChecked(false);
           NssfnID.setChecked(false);
       }
       if (foodricedalsync.equals("good")){
           goodID.setChecked(true);
       }
       else if (foodricedalsync.equals("average")){
           averageID.setChecked(true);
       }
       else if (foodricedalsync.equals("poor")){
           poorID.setChecked(true);
       }
       else {
           goodID.setChecked(false);
           averageID.setChecked(false);
           poorID.setChecked(false);
       }

//       if (fooddalbrndsync.equals("y")){
//           YspbrandID.setChecked(true);
//       }
//       else if (fooddalbrndsync.equals("n")){
//           NspbrandID.setChecked(true);
//       }
//       else {
//           YspbrandID.setChecked(false);
//           NspbrandID.setChecked(false);
//       }

       if (fooddalbrndsync.equals("good")){
           dalgoodId.setChecked(true);
       }
       else if (fooddalbrndsync.equals("average")){
           dalaverageId.setChecked(true);
       }
       else if (fooddalbrndsync.equals("poor")){
           dalpoorId.setChecked(true);
       }
       else {
           dalgoodId.setChecked(false);
           dalaverageId.setChecked(false);
           dalpoorId.setChecked(false);
       }

       if (foodstocklyngsync.equals("within_awc")){
           wawcID.setChecked(true);
       }
       else if (foodstocklyngsync.equals("aww_resdnc")){
           awwhID.setChecked(true);
       }
       else if (foodstocklyngsync.equals("Othr_plc")){
           otherplaceID.setChecked(true);
       }
       else {
           wawcID.setChecked(false);
           awwhID.setChecked(false);
           otherplaceID.setChecked(false);
       }
       musteroilId.setText(musteroilsysnc);
       saltId.setText(saltsync);
       cmdinformation.setText(cmt);
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
    public void radibuttonall(){
         GasfsID = (RadioGroup)findViewById(R.id.GasfsID);
         GpdID = (RadioGroup)findViewById(R.id.GpdID);
         GcsbID = (RadioGroup)findViewById(R.id.GcsbID);
         GssfnID = (RadioGroup)findViewById(R.id.GssfnID);
         GrdqID = (RadioGroup)findViewById(R.id.GrdqID);
         GspbrandID = (RadioGroup)findViewById(R.id.GspbrandID);
         GstockisnotlyingIG = (RadioGroup)findViewById(R.id.GstockisnotlyingIG);
         GdalId = (RadioGroup)findViewById(R.id.GdalId);
         YasfsID = (RadioButton)findViewById(R.id.YasfsID);
         NasfsID = (RadioButton)findViewById(R.id.NasfsID);
         YpcID = (RadioButton)findViewById(R.id.YpcID);
         NpcID = (RadioButton)findViewById(R.id.NpcID);
         YcsbID = (RadioButton)findViewById(R.id.YcsbID);
         NcsbID = (RadioButton)findViewById(R.id.NcsbID);
         YssfnID = (RadioButton)findViewById(R.id.YssfnID);
         NssfnID = (RadioButton)findViewById(R.id.NssfnID);
         goodID = (RadioButton)findViewById(R.id.goodID);
         averageID =(RadioButton)findViewById(R.id.averageID);
         poorID = (RadioButton)findViewById(R.id.poorID);
         YspbrandID =(RadioButton)findViewById(R.id.YspbrandID);
         NspbrandID = (RadioButton)findViewById(R.id.NspbrandID);
         wawcID = (RadioButton)findViewById(R.id.wawcID);
         awwhID =(RadioButton)findViewById(R.id.awwhID);
         otherplaceID = (RadioButton)findViewById(R.id.otherplaceID);
         dalgoodId = (RadioButton)findViewById(R.id.dalgoodId);
         dalaverageId = (RadioButton)findViewById(R.id.dalaverageId);
         dalpoorId = (RadioButton)findViewById(R.id.dalpoorId);
        GstockisnotlyingIG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.wawcID){
                    stocklyng = "within_awc";
                    withinawcId.setVisibility(View.VISIBLE);
                }
                else if (checkedId== R.id.awwhID){
                    stocklyng = "aww_resdnc";
                    withinawcId.setVisibility(View.GONE);
                    if (YasfsID.isChecked()){
                        YasfsID.setChecked(false);
                        YasfsID.setSelected(false);
                    }
                    if (NasfsID.isChecked()){
                        NasfsID.setChecked(false);
                        NasfsID.setSelected(false);
                    }
                    if (YpcID.isChecked()){
                        YpcID.setChecked(false);
                        YpcID.setSelected(false);
                    }

                    if (NpcID.isChecked()){
                        NpcID.setChecked(false);
                        NpcID.setSelected(false);
                    }

                    if (YcsbID.isChecked()){
                        YcsbID.setChecked(false);
                        YcsbID.setSelected(false);
                    }
                    if (NcsbID.isChecked()){
                        NcsbID.setChecked(false);
                        NcsbID.setSelected(false);
                    }

                    if (YssfnID.isChecked()){
                        YssfnID.setChecked(false);
                        YssfnID.setSelected(false);
                    }

                    if (NssfnID.isChecked()){
                        NssfnID.setChecked(false);
                        NssfnID.setSelected(false);
                    }

                    if (goodID.isChecked()){
                        goodID.setChecked(false);
                        goodID.setSelected(false);
                    }

                    if (averageID.isChecked()){
                        averageID.setChecked(false);
                        averageID.setSelected(false);
                    }
                    if (poorID.isChecked()){
                        poorID.setChecked(false);
                        poorID.setSelected(false);
                    }


                    if (dalgoodId.isChecked()){
                        dalgoodId.setChecked(false);
                        dalgoodId.setSelected(false);
                    }

                    if (dalaverageId.isChecked()){
                        dalaverageId.setChecked(false);
                        dalaverageId.setSelected(false);
                    }
                    if (dalpoorId.isChecked()){
                        dalpoorId.setChecked(false);
                        dalpoorId.setSelected(false);
                    }
                    musteroilId.setText("");
                    saltId.setText("");

                }
                else if(checkedId== R.id.otherplaceID){
                    stocklyng = "Othr_plc";
                    withinawcId.setVisibility(View.GONE);

                    if (YasfsID.isChecked()){
                        YasfsID.setChecked(false);
                        YasfsID.setSelected(false);
                    }
                    if (NasfsID.isChecked()){
                        NasfsID.setChecked(false);
                        NasfsID.setSelected(false);
                    }
                    if (YpcID.isChecked()){
                        YpcID.setChecked(false);
                        YpcID.setSelected(false);
                    }

                    if (NpcID.isChecked()){
                        NpcID.setChecked(false);
                        NpcID.setSelected(false);
                    }

                    if (YcsbID.isChecked()){
                        YcsbID.setChecked(false);
                        YcsbID.setSelected(false);
                    }
                    if (NcsbID.isChecked()){
                        NcsbID.setChecked(false);
                        NcsbID.setSelected(false);
                    }

                    if (YssfnID.isChecked()){
                        YssfnID.setChecked(false);
                        YssfnID.setSelected(false);
                    }

                    if (NssfnID.isChecked()){
                        NssfnID.setChecked(false);
                        NssfnID.setSelected(false);
                    }

                    if (goodID.isChecked()){
                        goodID.setChecked(false);
                        goodID.setSelected(false);
                    }

                    if (averageID.isChecked()){
                        averageID.setChecked(false);
                        averageID.setSelected(false);
                    }
                    if (poorID.isChecked()){
                        poorID.setChecked(false);
                        poorID.setSelected(false);
                    }


                    if (dalgoodId.isChecked()){
                        dalgoodId.setChecked(false);
                        dalgoodId.setSelected(false);
                    }

                    if (dalaverageId.isChecked()){
                        dalaverageId.setChecked(false);
                        dalaverageId.setSelected(false);
                    }
                    if (dalpoorId.isChecked()){
                        dalpoorId.setChecked(false);
                        dalpoorId.setSelected(false);
                    }
                    musteroilId.setText("");
                    saltId.setText("");

                }
            }
        });
         GasfsID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if (checkedId== R.id.YasfsID){
                     storespace = "y";
                 }
                 else if (checkedId== R.id.NasfsID){
                     storespace = "n";
                 }



             }
         });
         GpdID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if (checkedId== R.id.YpcID){
                     physiclchk = "y";
                 }
                else if (checkedId== R.id.NpcID){
                     physiclchk = "n";
                 }

             }
         });
         GcsbID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if (checkedId== R.id.YcsbID){
                     stkbookcmpare = "y";
                 }
                else if (checkedId== R.id.NcsbID){
                     stkbookcmpare = "n";
                 }

             }
         });
         GssfnID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if (checkedId== R.id.YssfnID){
                     stksuficnt ="y";
                 }
                else if (checkedId== R.id.NssfnID){
                     stksuficnt = "n";
                 }

             }
         });
         GrdqID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if (checkedId== R.id.goodID){
                     ricedal = "good";
                 }
                 else if (checkedId== R.id.averageID){
                     ricedal = "average";
                 }

                 else if (checkedId== R.id.poorID){
                     ricedal = "poor";
                 }

             }
         });
        GdalId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.dalgoodId){
                    dalstore = "good";
                }
                else if (checkedId== R.id.dalaverageId){
                    dalstore = "average";
                }

                else if (checkedId== R.id.dalpoorId){
                    dalstore = "poor";
                }

            }
        });
         GspbrandID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 if (checkedId== R.id.YspbrandID){
                    // soyachunkbrnd = "y";
                 }
                 else if (checkedId== R.id.NspbrandID){
                    // soyachunkbrnd = "n";
                 }

             }
         });



     }
    private void editFood(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.STOREEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("FOODEDIT"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            JSONObject object = array.getJSONObject(0);
                            JSONArray fooddtl = object.getJSONArray("food_dtl");
                            for (int i = 0; i<fooddtl.length(); i++){
                                JSONObject jsonObject = fooddtl.getJSONObject(i);
                                Estorespace = jsonObject.getString("store_space");
                                Ephysiclchk = jsonObject.getString("physicl_chk");
                                Estkbookcmpare = jsonObject.getString("stk_book_cmpare");
                                Estksuficnt = jsonObject.getString("stk_suficnt");
                                Ericedal = jsonObject.getString("rice");
                                dal = jsonObject.getString("dal");
                                moilbrnd = jsonObject.getString("moil_brnd");
                                saltbrnd = jsonObject.getString("salt_brnd");
                                Estocklyng = jsonObject.getString("stock_lyng");
                                cmt =  jsonObject.getString("cmnt");
                                Log.e("FOODDATA"," "+Estorespace+" "+" "+Ephysiclchk+" "+Estkbookcmpare+" "+Estksuficnt+" "+Ericedal+" "+Esoyachunkbrnd+" "+moilbrnd+" "+saltbrnd+" "+Estocklyng);
                                if (Estorespace.equals("y")){
                                    YasfsID.setChecked(true);
                                }
                                else if (Estorespace.equals("n")){
                                    NasfsID.setChecked(true);
                                }
                                else {
                                    YasfsID.setChecked(false);
                                    NasfsID.setChecked(false);
                                }
                                if (Ephysiclchk.equals("y")){
                                    YpcID.setChecked(true);
                                }
                                else if (Ephysiclchk.equals("n")){
                                    NpcID.setChecked(true);
                                }
                                else {
                                    YpcID.setChecked(false);
                                    NpcID.setChecked(false);
                                }
                                if (Estkbookcmpare.equals("y")){
                                    YcsbID.setChecked(true);
                                }
                                else if (Estkbookcmpare.equals("n")){
                                    NcsbID.setChecked(true);
                                }
                                else {
                                    YcsbID.setChecked(false);
                                    NcsbID.setChecked(false);
                                }
                                if (Estksuficnt.equals("y")){
                                    YssfnID.setChecked(true);
                                }
                                else if (Estksuficnt.equals("n")){
                                    NssfnID.setChecked(true);
                                }
                                else {
                                    YssfnID.setChecked(false);
                                    NssfnID.setChecked(false);
                                }
                                if (Ericedal.equals("good")){
                                    goodID.setChecked(true);
                                }
                                else if (Ericedal.equals("average")){
                                    averageID.setChecked(true);
                                }
                                else if (Ericedal.equals("poor")){
                                    poorID.setChecked(true);
                                }
                                else {
                                    goodID.setChecked(false);
                                    averageID.setChecked(false);
                                    poorID.setChecked(false);
                                }


//                                if (Esoyachunkbrnd.equals("y")){
//                                    YspbrandID.setChecked(true);
//                                }
//                                else if (Esoyachunkbrnd.equals("n")){
//                                    NspbrandID.setChecked(true);
//                                }
//                                else {
//                                    YspbrandID.setChecked(false);
//                                    NspbrandID.setChecked(false);
//                                }

                                if (dal.equals("good")){
                                    dalgoodId.setChecked(true);
                                }
                                else if (dal.equals("average")){
                                    dalaverageId.setChecked(true);
                                }
                                else if (dal.equals("poor")){
                                    dalpoorId.setChecked(true);
                                }
                                else {
                                    dalgoodId.setChecked(false);
                                    dalaverageId.setChecked(false);
                                    dalpoorId.setChecked(false);
                                }
                                musteroilId.setText(moilbrnd);
                                saltId.setText(saltbrnd);
                                if (Estocklyng.equals("within_awc")){
                                    wawcID.setChecked(true);
                                }
                                else if (Estocklyng.equals("aww_resdnc")){
                                    awwhID.setChecked(true);
                                }
                                else if (Estocklyng.equals("Othr_plc")){
                                    otherplaceID.setChecked(true);
                                }
                                else {
                                    wawcID.setChecked(false);
                                    awwhID.setChecked(false);
                                    otherplaceID.setChecked(false);
                                }
                                cmdinformation.setText(cmt);
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
                Log.e("STOREINS",insid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
     private void update_food_store_inspection(){
         Log.e("TEST","0");
         if (wawcID.isChecked()){
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
             musteroilId.setFilters(new InputFilter[] { filter });
             musteroilIdd = musteroilId.getText().toString().trim();
             if (TextUtils.isEmpty(musteroilIdd)) {
                 musteroilId.setError("Please enter Oil");
                 musteroilId.requestFocus();
                 return;
             }
             ///////////////////////////////////////////////////
             InputFilter filterr = new InputFilter() {
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
             saltId.setFilters(new InputFilter[] { filterr });
             saltIdd = saltId.getText().toString().trim();
             if (TextUtils.isEmpty(saltIdd)) {
                 saltId.setError("Please enter Salt");
                 saltId.requestFocus();
                 return;
             }
             ////////////////////////////////////////////////////
     }
         InputFilter filterrr = new InputFilter() {
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
         cmdinformation.setFilters(new InputFilter[] { filterrr });
         cmd =  cmdinformation.getText().toString().trim();


         Calendar cc = Calendar.getInstance();
         System.out.println("Current time => " + cc.getTime());
         SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
         curDate = df3.format(cc.getTime());
         Calendar ccc = Calendar.getInstance();
         SimpleDateFormat time = new SimpleDateFormat("HH:mm");
         curTime = time.format(ccc.getTime());
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.STORE,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         Log.e("STORE"," "+response);
                         try {
                             JSONArray array =new JSONArray(response);
                             Log.e("STOREOBJ"," "+array);
                             for (int i=0; i<array.length(); i++) {
                                 JSONObject object = array.getJSONObject(i);
                                 String message = object.getString("message");
                                 Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                 helper = new DatabaseHelper(getApplicationContext());
                                 SQLiteDatabase dbb = helper.getReadableDatabase();
                                 dbb.execSQL("UPDATE insflag SET foodstoreddetails='1' WHERE allinspactionid=" +insid);
                                 if (stocklyng.equals("within_awc")){
                                     syncFoodsaveDatabase(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_SYNCED_WITH_SERVER);
                                 }
                                 else if (stocklyng.equals("aww_resdnc")){
                                     storespace = "NNA";
                                     physiclchk = "NNA";
                                     stkbookcmpare ="NNA";
                                     stksuficnt= "NNA";
                                     ricedal = "NNA";
                                     dalstore = "NNA";
                                     musteroilIdd ="NNA";
                                     saltIdd = "NNA";
                                     foodspace = "NNA";
                                     syncFoodsaveDatabase(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_SYNCED_WITH_SERVER);
                                 }
                                 else if (stocklyng.equals("Othr_plc")){
                                     storespace = "NNA";
                                     physiclchk = "NNA";
                                     stkbookcmpare ="NNA";
                                     stksuficnt= "NNA";
                                     ricedal = "NNA";
                                     dalstore = "NNA";
                                     musteroilIdd ="NNA";
                                     saltIdd = "NNA";
                                     foodspace = "NNA";
                                     syncFoodsaveDatabase(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_SYNCED_WITH_SERVER);
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
                         dbb.execSQL("UPDATE insflag SET foodstoreddetails='1' WHERE allinspactionid=" +insid);
                        // syncFoodsaveDatabase(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,FOOD_NOT_SYNCED_WITH_SERVER);

                         if (stocklyng.equals("within_awc")){
                             syncFoodsaveDatabase(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_NOT_SYNCED_WITH_SERVER);
                         }
                         else if (stocklyng.equals("aww_resdnc")){
                             storespace = "NNA";
                             physiclchk = "NNA";
                             stkbookcmpare ="NNA";
                             stksuficnt= "NNA";
                             ricedal = "NNA";
                             dalstore = "NNA";
                             musteroilIdd ="NNA";
                             saltIdd = "NNA";
                             foodspace = "NNA";
                             syncFoodsaveDatabase(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_NOT_SYNCED_WITH_SERVER);
                         }
                         else if (stocklyng.equals("Othr_plc")){
                             storespace = "NNA";
                             physiclchk = "NNA";
                             stkbookcmpare ="NNA";
                             stksuficnt= "NNA";
                             ricedal = "NNA";
                             dalstore = "NNA";
                             musteroilIdd ="NNA";
                             saltIdd = "NNA";
                             foodspace = "NNA";
                             syncFoodsaveDatabase(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_NOT_SYNCED_WITH_SERVER);
                         }
                         else {

                         }
                     }
                 }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();

                 // params.put("soya_chunk_brnd",soyachunkbrnd);
                 params.put("stock_lyng",stocklyng);
                 if (stocklyng.equals("within_awc")){
                     params.put("inspctn_id",insid);
                     params.put("store_space",storespace);
                     params.put("physicl_chk",physiclchk);
                     params.put("stk_book_cmpare",stkbookcmpare);
                     params.put("stk_suficnt",stksuficnt);
                     params.put("rice_dal",ricedal);
                     params.put("dal",dalstore);
                     params.put("moil_brnd",musteroilIdd);
                     params.put("salt_brnd",saltIdd);
                     params.put("ins_time",curTime);
                     params.put("last_isac_rep",foodspace);
                     params.put("last_inspctn_rep",lstinspctnfoodrep);
                     params.put("cmnt",cmd);
                 }
                 else if (stocklyng.equals("aww_resdnc")){
                     storespace = "NNA";
                     physiclchk = "NNA";
                     stkbookcmpare ="NNA";
                     stksuficnt= "NNA";
                     ricedal = "NNA";
                     dalstore = "NNA";
                     musteroilIdd ="NNA";
                     saltIdd = "NNA";
                     foodspace = "NNA";
                     params.put("inspctn_id",insid);
                     params.put("store_space",storespace);
                     params.put("physicl_chk",physiclchk);
                     params.put("stk_book_cmpare",stkbookcmpare);
                     params.put("stk_suficnt",stksuficnt);
                     params.put("rice_dal",ricedal);
                     params.put("dal",dalstore);
                     params.put("moil_brnd",musteroilIdd);
                     params.put("salt_brnd",saltIdd);
                     params.put("ins_time",curTime);
                     params.put("last_isac_rep",foodspace);
                     params.put("last_inspctn_rep",lstinspctnfoodrep);
                     params.put("cmnt",cmd);
                 }
                 else if(stocklyng.equals("Othr_plc")){
                     storespace = "NNA";
                     physiclchk = "NNA";
                     stkbookcmpare ="NNA";
                     stksuficnt= "NNA";
                     ricedal = "NNA";
                     dalstore = "NNA";
                     musteroilIdd ="NNA";
                     saltIdd = "NNA";
                     foodspace = "NNA";
                     params.put("inspctn_id",insid);
                     params.put("store_space",storespace);
                     params.put("physicl_chk",physiclchk);
                     params.put("stk_book_cmpare",stkbookcmpare);
                     params.put("stk_suficnt",stksuficnt);
                     params.put("rice_dal",ricedal);
                     params.put("dal",dalstore);
                     params.put("moil_brnd",musteroilIdd);
                     params.put("salt_brnd",saltIdd);
                     params.put("ins_time",curTime);
                     params.put("last_isac_rep",foodspace);
                     params.put("last_inspctn_rep",lstinspctnfoodrep);
                     params.put("cmnt",cmd);
                 }

                 Log.e("STORINSID",foodspace);
                 Log.e("STORINSIDD",lstinspctnfoodrep);
                 Log.e("STORELOGGG"," "+dalstore+" "+musteroilIdd+" "+saltIdd);
                 Log.e("STORELOG",insid+" "+" "+storespace+" "+physiclchk+" "+stkbookcmpare+" "+stksuficnt+" "+ricedal+" "+dalstore+" "+musteroilIdd+" "+saltIdd+" "+"soyachunkbrnd"+" "+stocklyng+" "+curTime+" "+foodspace+" "+lstinspctnfoodrep);
                 return params;
             }
         };
         //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
         stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
         stringRequest.setShouldCache(false);
         volleySingleton.addToRequestQueue(stringRequest);
     }
     private void syncFoodsaveDatabase(String insid,String storespace,String physiclchk,String stkbookcmpare,String stksuficnt,String ricedal,String dal,String musteroilIdd,String saltIdd,String stocklyng,String curTime,String foodspace,String lstinspctnfoodrep,String cmd,int foodstatussync){
        if (food.equals("0")) {
            helper.FOODINSERT(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dal,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,foodstatussync);
        }
        else {
            helper.FOODUPDATE(dbid,insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dal,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,foodstatussync);

        }
        Log.e("FOODDETAILS",
                dbid+" "+
                insid+" "+
                storespace+" "+
                physiclchk+" "+
                stkbookcmpare+" "+
                stksuficnt+" "+
                ricedal+" "+
                dal+" "+
                musteroilIdd+" "+
                saltIdd+" "+
                stocklyng+" "+
                curTime+" "+
                foodspace+" "+
                lstinspctnfoodrep+" "+
                foodstatussync);

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
//         Intent intent = new Intent(FoodStoredDetailActivity.this,ConditionOfEquipmentOthersAWCActivity.class);
//         Bundle bundle = new Bundle();
//         bundle.putString("kitchen",kitchen);
//         bundle.putString("adqutspacepse",adqutspacepse);
//         bundle.putString("electric",electric);
//         bundle.putString("water",water);
//         bundle.putString("foodspace",foodspace);
//         bundle.putString("toilet",toilet);
//         bundle.putString("awcscode",awcscode);
//         bundle.putString("awcsname",awcsname);
//         bundle.putString("dbdistid",dbdistid);
//         bundle.putString("dbprojectid",dbprojectid);
//         bundle.putString("dbsectorid",dbsectorid);
//         bundle.putString("dbcenterid",dbcenterid);
//         bundle.putString("projectnamedb",projectnamedb);
//         bundle.putString("districnamedb",districnamedb);
//         bundle.putString("sectorrnamedb",sectorrnamedb);
//         bundle.putString("centernamedb",centernamedb);
//         bundle.putString("insid",insid);
//         bundle.putString("planid",planid);
//         bundle.putString("dbid",dbid);
//         intent.putExtras(bundle);
//         startActivity(intent);

         Intent intent = new Intent(FoodStoredDetailActivity.this, ConditionOfEquipmentOthersAWCActivity.class);
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
         if (wawcID.isChecked() || awwhID.isChecked() || otherplaceID.isChecked()) {
             if (stocklyng.equals("within_awc")){
                 if (YasfsID.isChecked() || NasfsID.isChecked()) {
                     if (YpcID.isChecked() || NpcID.isChecked()) {
                         if (YcsbID.isChecked() || NcsbID.isChecked()) {
                             if (YssfnID.isChecked() || NssfnID.isChecked()) {
                                 if (goodID.isChecked() || averageID.isChecked() || poorID.isChecked()) {
                                     if (dalgoodId.isChecked() || dalaverageId.isChecked() || dalpoorId.isChecked()) {
                                         int  masteroilvalue = musteroilId.getText().length();
                                         int  saltvalue = saltId.getText().length();
                                         if (masteroilvalue>0){
                                             if (saltvalue>0){
                                                 update_food_store_inspection1();
                                             }
                                             else {
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
                                                 saltId.setFilters(new InputFilter[]{filter1});
                                                 saltIdd = saltId.getText().toString().trim();
                                                 if (TextUtils.isEmpty(saltIdd)) {
                                                     saltId.setError("Please Enter Salt");
                                                     saltId.requestFocus();
                                                     return;
                                                 }
                                             }

                                         }
                                         else {
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
                                             musteroilId.setFilters(new InputFilter[]{filter1});
                                             musteroilIdd = musteroilId.getText().toString().trim();
                                             if (TextUtils.isEmpty(musteroilIdd)) {
                                                 musteroilId.setError("Please Enter Muster Oil");
                                                 musteroilId.requestFocus();
                                                 return;
                                             }


                                         }
                                     }
                                     else {
                                         Toast.makeText(FoodStoredDetailActivity.this, "SELECT  DAL QUALITY", Toast.LENGTH_SHORT).show();
                                         String title = "Message Box";
                                         String msg = "SELECT  DAL QUALITY";
                                         createDialog(title,msg);
                                     }

                                 } else {
                                     Toast.makeText(FoodStoredDetailActivity.this, "SELECT RICE  QUALITY", Toast.LENGTH_SHORT).show();
                                     String title = "Message Box";
                                     String msg = "SELECT RICE  QUALITY";
                                     createDialog(title,msg);
                                 }

                             } else {
                                 Toast.makeText(FoodStoredDetailActivity.this, "SELECT STOCK SUFFICIANT FOR NEXT 15 DAYS", Toast.LENGTH_SHORT).show();
                                 String title = "Message Box";
                                 String msg = "SELECT STOCK SUFFICIANT FOR NEXT 15 DAYS";
                                 createDialog(title,msg);
                             }


                         } else {
                             Toast.makeText(FoodStoredDetailActivity.this, "SELECT COMPARED WITH STOCK BOOK", Toast.LENGTH_SHORT).show();
                             String title = "Message Box";
                             String msg = "SELECT COMPARED WITH STOCK BOOK";
                             createDialog(title,msg);
                         }

                     } else {
                         Toast.makeText(FoodStoredDetailActivity.this, "SELECT FOOD STOCK", Toast.LENGTH_SHORT).show();
                         String title = "Message Box";
                         String msg = "SELECT FOOD STOCK";
                         createDialog(title,msg);
                     }
                 } else {
                     Toast.makeText(FoodStoredDetailActivity.this, "SELECT ADEQUATE SPACE FOR STORAGE", Toast.LENGTH_SHORT).show();
                     String title = "Message Box";
                     String msg = "SELECT ADEQUATE SPACE FOR STORAGE";
                     createDialog(title,msg);
                 }
             }
             else if (stocklyng.equals("aww_resdnc")){
                 update_food_store_inspection1();
             }

             else if (stocklyng.equals("Othr_plc")){
                 update_food_store_inspection1();
             }
             else {

             }
         } else {
             Toast.makeText(FoodStoredDetailActivity.this, "SELECT STOCK IS NOT LYING", Toast.LENGTH_SHORT).show();
             String title = "Message Box";
             String msg = "SELECT STOCK IS NOT LYING";
             createDialog(title,msg);

         }

     }
    private void update_food_store_inspection1(){
        Log.e("TEST","0");
        if (wawcID.isChecked()){
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
            musteroilId.setFilters(new InputFilter[] { filter });
            musteroilIdd = musteroilId.getText().toString().trim();
            if (TextUtils.isEmpty(musteroilIdd)) {
                musteroilId.setError("Please enter Oil");
                musteroilId.requestFocus();
                return;
            }
            ///////////////////////////////////////////////////
            InputFilter filterr = new InputFilter() {
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
            saltId.setFilters(new InputFilter[] { filterr });
            saltIdd = saltId.getText().toString().trim();
            if (TextUtils.isEmpty(saltIdd)) {
                saltId.setError("Please enter Salt");
                saltId.requestFocus();
                return;
            }
            ////////////////////////////////////////////////////
        }
        InputFilter filterrr = new InputFilter() {
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
        cmdinformation.setFilters(new InputFilter[] { filterrr });
        cmd =  cmdinformation.getText().toString().trim();


        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => " + cc.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        curDate = df3.format(cc.getTime());
        Calendar ccc = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        curTime = time.format(ccc.getTime());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.STORE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("STORE"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("STOREOBJ"," "+array);
                            for (int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String message = object.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE insflag SET foodstoreddetails='1' WHERE allinspactionid=" +insid);
                                if (stocklyng.equals("within_awc")){
                                    syncFoodsaveDatabase1(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_SYNCED_WITH_SERVER);
                                }
                                else if (stocklyng.equals("aww_resdnc")){
                                    storespace = "NNA";
                                    physiclchk = "NNA";
                                    stkbookcmpare ="NNA";
                                    stksuficnt= "NNA";
                                    ricedal = "NNA";
                                    dalstore = "NNA";
                                    musteroilIdd ="NNA";
                                    saltIdd = "NNA";
                                    foodspace = "NNA";
                                    syncFoodsaveDatabase1(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_SYNCED_WITH_SERVER);
                                }
                                else if (stocklyng.equals("Othr_plc")){
                                    storespace = "NNA";
                                    physiclchk = "NNA";
                                    stkbookcmpare ="NNA";
                                    stksuficnt= "NNA";
                                    ricedal = "NNA";
                                    dalstore = "NNA";
                                    musteroilIdd ="NNA";
                                    saltIdd = "NNA";
                                    foodspace = "NNA";
                                    syncFoodsaveDatabase1(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_SYNCED_WITH_SERVER);
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
                        dbb.execSQL("UPDATE insflag SET foodstoreddetails='1' WHERE allinspactionid=" +insid);
                        // syncFoodsaveDatabase(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,FOOD_NOT_SYNCED_WITH_SERVER);

                        if (stocklyng.equals("within_awc")){
                            syncFoodsaveDatabase1(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_NOT_SYNCED_WITH_SERVER);
                        }
                        else if (stocklyng.equals("aww_resdnc")){
                            storespace = "NNA";
                            physiclchk = "NNA";
                            stkbookcmpare ="NNA";
                            stksuficnt= "NNA";
                            ricedal = "NNA";
                            dalstore = "NNA";
                            musteroilIdd ="NNA";
                            saltIdd = "NNA";
                            foodspace = "NNA";
                            syncFoodsaveDatabase1(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_NOT_SYNCED_WITH_SERVER);
                        }
                        else if (stocklyng.equals("Othr_plc")){
                            storespace = "NNA";
                            physiclchk = "NNA";
                            stkbookcmpare ="NNA";
                            stksuficnt= "NNA";
                            ricedal = "NNA";
                            dalstore = "NNA";
                            musteroilIdd ="NNA";
                            saltIdd = "NNA";
                            foodspace = "NNA";
                            syncFoodsaveDatabase1(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dalstore,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,FOOD_NOT_SYNCED_WITH_SERVER);
                        }
                        else {

                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                // params.put("soya_chunk_brnd",soyachunkbrnd);
                params.put("stock_lyng",stocklyng);
                if (stocklyng.equals("within_awc")){
                    params.put("inspctn_id",insid);
                    params.put("store_space",storespace);
                    params.put("physicl_chk",physiclchk);
                    params.put("stk_book_cmpare",stkbookcmpare);
                    params.put("stk_suficnt",stksuficnt);
                    params.put("rice_dal",ricedal);
                    params.put("dal",dalstore);
                    params.put("moil_brnd",musteroilIdd);
                    params.put("salt_brnd",saltIdd);
                    params.put("ins_time",curTime);
                    params.put("last_isac_rep",foodspace);
                    params.put("last_inspctn_rep",lstinspctnfoodrep);
                    params.put("cmnt",cmd);
                }
                else if (stocklyng.equals("aww_resdnc")){
                    storespace = "NNA";
                    physiclchk = "NNA";
                    stkbookcmpare ="NNA";
                    stksuficnt= "NNA";
                    ricedal = "NNA";
                    dalstore = "NNA";
                    musteroilIdd ="NNA";
                    saltIdd = "NNA";
                    foodspace = "NNA";
                    params.put("inspctn_id",insid);
                    params.put("store_space",storespace);
                    params.put("physicl_chk",physiclchk);
                    params.put("stk_book_cmpare",stkbookcmpare);
                    params.put("stk_suficnt",stksuficnt);
                    params.put("rice_dal",ricedal);
                    params.put("dal",dalstore);
                    params.put("moil_brnd",musteroilIdd);
                    params.put("salt_brnd",saltIdd);
                    params.put("ins_time",curTime);
                    params.put("last_isac_rep",foodspace);
                    params.put("last_inspctn_rep",lstinspctnfoodrep);
                    params.put("cmnt",cmd);
                }
                else if(stocklyng.equals("Othr_plc")){
                    storespace = "NNA";
                    physiclchk = "NNA";
                    stkbookcmpare ="NNA";
                    stksuficnt= "NNA";
                    ricedal = "NNA";
                    dalstore = "NNA";
                    musteroilIdd ="NNA";
                    saltIdd = "NNA";
                    foodspace = "NNA";
                    params.put("inspctn_id",insid);
                    params.put("store_space",storespace);
                    params.put("physicl_chk",physiclchk);
                    params.put("stk_book_cmpare",stkbookcmpare);
                    params.put("stk_suficnt",stksuficnt);
                    params.put("rice_dal",ricedal);
                    params.put("dal",dalstore);
                    params.put("moil_brnd",musteroilIdd);
                    params.put("salt_brnd",saltIdd);
                    params.put("ins_time",curTime);
                    params.put("last_isac_rep",foodspace);
                    params.put("last_inspctn_rep",lstinspctnfoodrep);
                    params.put("cmnt",cmd);
                }

                Log.e("STORINSID",foodspace);
                Log.e("STORINSIDD",lstinspctnfoodrep);
                Log.e("STORELOGGG"," "+dalstore+" "+musteroilIdd+" "+saltIdd);
                Log.e("STORELOG",insid+" "+" "+storespace+" "+physiclchk+" "+stkbookcmpare+" "+stksuficnt+" "+ricedal+" "+dalstore+" "+musteroilIdd+" "+saltIdd+" "+"soyachunkbrnd"+" "+stocklyng+" "+curTime+" "+foodspace+" "+lstinspctnfoodrep);
                return params;
            }
        };
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    private void syncFoodsaveDatabase1(String insid,String storespace,String physiclchk,String stkbookcmpare,String stksuficnt,String ricedal,String dal,String musteroilIdd,String saltIdd,String stocklyng,String curTime,String foodspace,String lstinspctnfoodrep,String cmd,int foodstatussync){
        if (food.equals("0")) {
            helper.FOODINSERT(insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dal,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,foodstatussync);
        }
        else {
            helper.FOODUPDATE(dbid,insid,storespace,physiclchk,stkbookcmpare,stksuficnt,ricedal,dal,musteroilIdd,saltIdd,stocklyng,curTime,foodspace,lstinspctnfoodrep,cmd,foodstatussync);

        }
        Log.e("FOODDETAILS",
                dbid+" "+
                        insid+" "+
                        storespace+" "+
                        physiclchk+" "+
                        stkbookcmpare+" "+
                        stksuficnt+" "+
                        ricedal+" "+
                        dal+" "+
                        musteroilIdd+" "+
                        saltIdd+" "+
                        stocklyng+" "+
                        curTime+" "+
                        foodspace+" "+
                        lstinspctnfoodrep+" "+
                        foodstatussync);

        BuildingReturndata();
    }
    public void BuildingReturndata(){
        Intent intent = new Intent(FoodStoredDetailActivity.this, InspectionListActivity.class);
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
//
//                if (YasfsID.isChecked() || NasfsID.isChecked()) {
//                    if (YpcID.isChecked() || NpcID.isChecked()) {
//                        if (YcsbID.isChecked() || NcsbID.isChecked()) {
//                            if (YssfnID.isChecked() || NssfnID.isChecked()) {
//                                if (goodID.isChecked() || averageID.isChecked() || poorID.isChecked()) {
//                                    if (YspbrandID.isChecked() || NspbrandID.isChecked()) {
//                                        if (wawcID.isChecked() || awwhID.isChecked() || otherplaceID.isChecked()) {
//                                            update_food_store_inspection();
//
//                                        } else {
//                                            Toast.makeText(FoodStoredDetailActivity.this, "SELECT STOCK IS NOT LYING", Toast.LENGTH_SHORT).show();
//
//                                        }
//
//                                    } else {
//                                        Toast.makeText(FoodStoredDetailActivity.this, "SELECT SPECIFIED BRAND", Toast.LENGTH_SHORT).show();
//                                    }
//
//
//                                } else {
//                                    Toast.makeText(FoodStoredDetailActivity.this, "SELECT RICE DAL QUALITY", Toast.LENGTH_SHORT).show();
//                                }
//
//                            } else {
//                                Toast.makeText(FoodStoredDetailActivity.this, "SELECT STOCK SUFFICIANT FOR NEXT 15 DAYS", Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        } else {
//                            Toast.makeText(FoodStoredDetailActivity.this, "SELECT COMpARED WITH STOCK BOOK", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } else {
//                        Toast.makeText(FoodStoredDetailActivity.this, "SELECT FOOD STOCK", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(FoodStoredDetailActivity.this, "SELECT ADEQUATE SPACE FOR STORAGE", Toast.LENGTH_SHORT).show();
//                }
//
//                break;
//                default:
//        }
    }

    private void MASTEROIL(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_command));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_MASTEROIL);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void SALT(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_command));
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_SALT);
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
            case REQ_CODE_SPEECH_MASTEROIL: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    musteroilId.setText(result.get(0));
                }
                break;
            }
            case REQ_CODE_SPEECH_SALT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    saltId.setText(result.get(0));
                }
                break;
            }
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
            if(foodNetwokchecker!=null)
                unregisterReceiver(foodNetwokchecker);
            if (broadcastReceiverfood!=null)
                unregisterReceiver(broadcastReceiverfood);

        }catch(Exception e){}
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
