package icdswb.in;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icdswb.in.ActivityDatabase.AdqueSpaceNetwokchecker;
import icdswb.in.ActivityDatabase.ConditionNetwokchecker;
import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityDatabase.DrinkingNetwokchecker;
import icdswb.in.ActivityDatabase.EletricityNetwokchecker;
import icdswb.in.ActivityDatabase.FinalSubmitNetworkcheck;
import icdswb.in.ActivityDatabase.FoodNetwokchecker;
import icdswb.in.ActivityDatabase.InspactioninsertNetworkercheker;
import icdswb.in.ActivityDatabase.InspectionpersonpresentNetworkcheker;
import icdswb.in.ActivityDatabase.KitchenNetwokchecker;
import icdswb.in.ActivityDatabase.NutritionNetworkChecker;
import icdswb.in.ActivityDatabase.OtherinspactionNetworkchecker;
import icdswb.in.ActivityDatabase.SPNNetwokchecker;
import icdswb.in.ActivityDatabase.ShishuAloyNetwokchecker;
import icdswb.in.ActivityDatabase.ToiletNetwokchecker;
import icdswb.in.ActivitySetGet.AwcssetgetDetails;
import icdswb.in.ActivitySetGet.Dbprocess;
import icdswb.in.ActivitySetGet.PickupActivityList;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.HttpHandler;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.Activity_Adapter.PickupAdapterActivity;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAGID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSLATLOCATION;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSLONGLOCATION;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSTIME;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSUSERID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CURRENDATE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FLAGGRECORD;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPACTIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPECTIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NAMEAWCSDTL;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;

public class OfflineNoSyne  extends Fragment implements View.OnClickListener{
    Button procedId,officerId;
    EditText edt;
    private String TAG = PickupActivity.class.getSimpleName();
    String discode,awcs_code,userid;
    TextView district,project,sector;
    String sectorr,projectt,districtt,centerr;
    String distID,projectID,sectorID,centerID;
    Button searchID;
    RecyclerView recyclerView;
    List<PickupActivityList> pickupActivityLists;
    ArrayList<PickupActivityList> arrlist = new ArrayList<PickupActivityList>();
    private PickupAdapterActivity adapter;
    private PickupAdapter adapter1;
    Context context;
    DatabaseHelper helper;
    String Id = "1";
    List<Dbprocess> planinglistdb;
    List<AwcssetgetDetails>awcssetgetDetailsList;
    private PickupAdapterDatabase adapter2;
    RelativeLayout rvID;
    String awcsid;
    String flagg = "0";
    String flaggg = "1";
    private BuildingDetailsActivity.BuildingNetworkStateCheckerr buildingNetworkStateCheckerr;
    private InspactioninsertNetworkercheker inspactioninsertNetworkercheker;
    private InspectionpersonpresentNetworkcheker inspectionpersonpresentNetworkcheker;
    private ToiletNetwokchecker toiletNetwokchecker;
    private KitchenNetwokchecker kitchenNetwokchecker;
    private AdqueSpaceNetwokchecker adqueSpaceNetwokchecker;
    private EletricityNetwokchecker eletricityNetwokchecker;
    private DrinkingNetwokchecker drinkingNetwokchecker;
    private FoodNetwokchecker foodNetwokchecker;
    private ConditionNetwokchecker conditionNetwokchecker;
    private ShishuAloyNetwokchecker shishuAloyNetwokchecker;
    private SPNNetwokchecker spnNetwokchecker;
    private NutritionNetworkChecker nutriationnetwokchecker;
    private OtherinspactionNetworkchecker otherinspactionNetworkchecker;
    private FinalSubmitNetworkcheck finalSubmitNetworkcheck;
    public static final int FINAL_SUB_WITH_SERVER = 1;
    public static final int FINAL_SUB_WITH_SERVER_NOT = 0;
    public static final String DATA_SAVED_BROADCAST_FINAL = "icdswb.in.othersaved";
    private BroadcastReceiver broadcastReceiverotherr;
    String insidsync;
    String TABLESTATUSBUILDINGG;
    private String mLastUpdateTime;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    private static final int REQUEST_CHECK_SETTINGS = 100;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;
    String llat2,llng2;
    float flat2 = 0;
    float flng2 = 0;
    Toolbar toolbar;
    String userID,otherName;
    String flaggrecord ="2";
    String dist,distname,projectid,projectname,sectorid,sectorname,status,insidd,planIdd;
    String STATUS = "1";
    String value ="0";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastReceiverotherr = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        getActivity().registerReceiver(broadcastReceiverotherr, new IntentFilter(DATA_SAVED_BROADCAST_FINAL));
        finalSubmitNetworkcheck = new FinalSubmitNetworkcheck();
        getActivity().registerReceiver(finalSubmitNetworkcheck, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("TABB","00");

        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.no_syne_offline, container, false);
//        procedId = (Button)findViewById(R.id.procedId);
//        officerId = (Button)findViewById(R.id.officerId);
       // initToolBar();
        rvID = (RelativeLayout)rootView.findViewById(R.id.rvID);
        searchID = (Button)rootView.findViewById(R.id.searchID);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pickupActivityLists = new ArrayList<>();
        planinglistdb = new ArrayList<>();
        awcssetgetDetailsList = new ArrayList<>();
//        procedId.setOnClickListener(this);
//        officerId.setOnClickListener(this);
        searchID.setOnClickListener(this);
        edt = (EditText)rootView.findViewById(R.id.edt);
//        district = (TextView)findViewById(R.id.district);
//        project = (TextView)findViewById(R.id.project);
//        sector = (TextView)findViewById(R.id.sector);
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        discode = String.valueOf(user.getUserDist());
        userid = String.valueOf(user.getUserID());
        Log.e("userid",userid);
        helper = new DatabaseHelper(getContext());
        showdata();
        if (isNetworkAvailable()){

        }
        else {
            rvID.setVisibility(View.GONE);

        }
        Cursor c = helper.getLoginData();
        if (c.moveToFirst()) {
            do {
                userid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                Log.e("userid",userid);
            } while (c.moveToNext());

        }
        String query = "SELECT * FROM awcsprocess WHERE userid=" + userid;
        helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {

            }
            while (cursor.moveToNext());
        }
//        String quer= "SELECT * FROM " + TABLE_ALLINSPECTIONFLAG + " where " + TABLE_USERIDFLA + "=" +userid+ " and " +TABLE_FLAGGRECORD+ "=" +flaggrecord;
//        helper = new DatabaseHelper(getApplicationContext());
//        SQLiteDatabase dbb = helper.getReadableDatabase();
//        Cursor  cur = dbb.rawQuery(quer,null);
//        if (cur.moveToFirst()){
//            do {
//              String showrecord =  cur.getString(cur.getColumnIndex(DatabaseHelper.TABLE_FLAGGRECORD));
//              Log.e("showrecord",showrecord);
//              if (showrecord.equals(flaggrecord)){
//
//              }
//
//            }
//            while (cur.moveToNext());
//        }
        showplanigfromdb();
        init();
        restoreValuesFromBundle(savedInstanceState);
        startLocationButtonClick();
        stopLocationButtonClick();

        return rootView;
    }
    public void initToolBar() {
        toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Pickup from Planning");
       // getContext().setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                }
        );
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.procedId:
//                Intent intent = new Intent(this,ProcedActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.officerId:
//                Intent intent1 = new Intent(this,OfficerDetailsActivity.class);
//                startActivity(intent1);
//                break;
            case R.id.searchID:
                if (isNetworkAvailable()){
                    getUserData();
                }
                else {

                }
                break;
            default:
        }
    }
    private void showplanigfromdb(){
        //  Cursor cursor = helper.getawcsprocessInsert();
        //  String query = "SELECT * FROM awcsprocess WHERE userid=" + userid;
        //  String query = "SELECT * FROM awcsprocess WHERE userid = '2' AND flag = '0' OR flag = '1'";
        //     String query = "SELECT  * FROM " + TABLE_PROCESS + "WHERE" +TABLE_USERID+ ;
        // String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid+ " and " +TABLE_FLAG+ "=" +flagg+  " or " +TABLE_FLAG+ "=" +flaggg;
        //String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid+ " and " +TABLE_FLAG+ "=" +flagg+ " or " +TABLE_FLAG+ "=" +flaggg;
        //String query = "SELECT * FROM awcsprocess WHERE flag = '0' OR flag = '1'";
        Log.e("SHOE",userid+" "+" "+flaggrecord+" "+" "+flagg+" "+" "+flaggg);
        //String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid+ " and " +TABLE_FLAGGRECORD+ "=" +flaggrecord+ " and " +TABLE_FLAG+ "=" +flagg+ " or " +TABLE_FLAG+ "=" +flaggg;
        String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid+ " and " +TABLE_FLAGGRECORD+ "=" +flaggrecord;
        helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                Dbprocess planinglistDB = new Dbprocess(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROCESSID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBDISTID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBPROJECTID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBSECTORID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBCENTERID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROJECT)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DISTRIC)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SECTOR)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CENTER)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CURRENDATE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FLAG)),
                        cursor.getString(cursor.getColumnIndex(TABLE_AWCSLATLOCATION)),
                        cursor.getString(cursor.getColumnIndex(TABLE_AWCSLONGLOCATION)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPACTIONID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSCODEID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSTIME)),
                        cursor.getString(cursor.getColumnIndex(TABLE_FLAGGRECORD))
                );
                planinglistdb.add(planinglistDB);
            }
            while (cursor.moveToNext());
        }
        adapter2 = new PickupAdapterDatabase(context,planinglistdb);
        recyclerView.setAdapter(adapter2);
        Cursor c = helper.getAwcDtls();
        if (c.moveToFirst()){
            do {
                AwcssetgetDetails awcssetgetDetails = new AwcssetgetDetails(
                        awcsid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_ID)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_WATER)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_CDPONAME)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_ACDPOCONT)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_BUILDSTRUC)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_ELECTRIC)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_ACDPONAME)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_KITCHEN)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_CDPOCONTACT)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_WORKERNO)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_WORKERNM)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_TOILET)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSLAT)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_SUPVSRNAME)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSLONG)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_HELPERNO)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSADRS)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_FOODSPACE)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_HELPERNM)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_BUILDON)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPACEPSE)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_SUPVSRNO)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSID)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSCODE)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSNAME)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_PLANID)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_BULD_REP)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_TOILET_REP)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_KITCHEN_REP)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_PSE_REP)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_ELECTRIC_REP)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_DRNKWATER_REP)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_FOOD_REP)),
                        c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSUSERID)),
                        c.getString(c.getColumnIndex(TABLE_AWCSFLAG))
                );
                awcssetgetDetailsList.add(awcssetgetDetails);
            }while (c.moveToNext());
        }
//          Log.e("awcsid",awcsid);

    }

    public boolean isNetworkAvailable() {
        boolean connect=false;
        ConnectivityManager conMgr =  (ConnectivityManager)getContext().getApplicationContext().getSystemService(getContext().getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            connect=false;
        }else{
            connect= true;
        }
        return connect;
    }
    private void getUserData(){
        awcs_code = edt.getText().toString();
        class UserData extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                String url = "http://icdswb.in/inspection_service/srch_awcs_dtl_frm_code.php?awcs_code="+awcs_code+"&&dist="+discode;
                String response = sh.makeServiceCall(url);
                Log.e(TAG, "Response from url attandence: " + response);
                if (response != null) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        Log.e("code"," "+obj);
                        final JSONObject object = obj.getJSONObject("response");
                        final String message = object.getString("message");
                        String error = object.getString("error");
                        Log.e("awcscode"," "+message+ " "+error+" ");

                        if (!object.getBoolean("error")){
                            JSONObject awcs_dtl = object.getJSONObject("awcs_dtl");
                            Log.e("awcs_dtl"," "+awcs_dtl);
                            districtt = awcs_dtl.getString("district");
                            projectt = awcs_dtl.getString("project");
                            sectorr = awcs_dtl.getString("sector");
                            centerr  = awcs_dtl.getString("awcs_name");
                            distID = awcs_dtl.getString("district_id");
                            projectID = awcs_dtl.getString("project_id");
                            sectorID  = awcs_dtl.getString("sector_id");
                            centerID = awcs_dtl.getString("awcs_id");
                            Log.e("pickudata"," "+districtt+ " "+projectt+" "+sectorr+" "+centerr+" "+distID+" "+projectID+" "+sectorID+" "+centerID+" ");
                            pickupActivityLists.add(new PickupActivityList(
                                    awcs_dtl.getString("district"),
                                    awcs_dtl.getString("project"),
                                    awcs_dtl.getString("sector"),
                                    awcs_dtl.getString("awcs_name"),
                                    awcs_dtl.getString("district_id"),
                                    awcs_dtl.getString("project_id"),
                                    awcs_dtl.getString("sector_id"),
                                    awcs_dtl.getString("awcs_id")

                            ));
                            arrlist.addAll(pickupActivityLists);
                            getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
//                                    adapter = new PickupAdapterActivity(PickupActivity.this,pickupActivityLists );
//                                    recyclerView.setAdapter(adapter);
                                    adapter1 = new PickupAdapter(context,pickupActivityLists);
                                    recyclerView.setAdapter(adapter1);
//                                    sector.setText(sectorr);
//                                    district.setText(districtt);
//                                    project.setText(projectt);
                                }
                            });

                        }
                        else {
                            getActivity().runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
//                                    sector.setText("");
//                                    district.setText("");
//                                    project.setText("");
                                }
                            });


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        }
        UserData ru = new UserData();
        ru.execute();
    }


    public class PickupAdapterDatabase extends RecyclerView.Adapter<PickupAdapterDatabase.databaseViewHolder>{
        SharedPreferences spp;
        public Context mCtx;
        private List<Dbprocess> planinglistdb;
        DatabaseHelper helper;
        String flag;
        String datee,systemdate,sysdate;
        String IdOther,buildingdetails,informationoftoilet,informationofkitchen,
                adequatespaceforpse,electricity,drinkingwater,
                foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,allinspactionid;
        String curDate,curTime;
        String lat = "0";
        String lang = "0";
        String Userid,dbid,date,recordflag;
        String awcslatlocation,awcsslonglocation;
        public PickupAdapterDatabase(Context mCtx,List<Dbprocess> planinglistdb){
            this.mCtx = mCtx;
            this.planinglistdb = planinglistdb;

        }
        @Override
        public PickupAdapterDatabase.databaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pickupdatabase, parent, false);
            return new databaseViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final PickupAdapterDatabase.databaseViewHolder holder, int position) {
            Dbprocess pickupdatabase = planinglistdb.get(position);
            recordflag = pickupdatabase.getFlaggrecord();
            Log.e("recordflag",recordflag);
            holder.districId.setText(pickupdatabase.getDistricnamedb());
            holder.disID.setText(pickupdatabase.getDbdistid());
            holder.projectId.setText(pickupdatabase.getProjectnamedb());
            holder.proID.setText(pickupdatabase.getDbprojectid());
            holder.sectorId.setText(pickupdatabase.getSectorrnamedb());
            holder.secID.setText(pickupdatabase.getDbsectorid());
            holder.centerId.setText(pickupdatabase.getCenternamedb());
            holder.CenID.setText(pickupdatabase.getDbcenterid());
            holder.tableID.setText(pickupdatabase.getIdprocess());
            holder.awcsID.setText(pickupdatabase.getAwcsid());
            holder.dateId.setText(pickupdatabase.getCurrentdate());
            holder.timeID.setText(pickupdatabase.getAwcstime());
            holder.insID.setText(pickupdatabase.getInspactionid());
            holder.userID.setText(pickupdatabase.getUserid());
            holder.flagID.setText(pickupdatabase.getFlag());
            holder.latID.setText(pickupdatabase.getAwcslat());
            holder.longID.setText(pickupdatabase.getAwcsslong());
            holder.rcflagId.setText(pickupdatabase.getFlaggrecord());

            int totalPrice = 0;
            for (int i = 0; i<planinglistdb.size(); i++)
            {
                holder.processId.setText(Integer.toString(position+1));


            }
            Userid = holder.userID.getText().toString();
            dbid  = holder.tableID.getText().toString();
            date = holder.dateId.getText().toString();
            datee = date.replaceAll("[-+.^:,]", "");
            Calendar cc = Calendar.getInstance();
            System.out.println("Current time => " + cc.getTime());
            SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
            systemdate = df3.format(cc.getTime());
            sysdate = systemdate.replaceAll("[-+.^:,]", "");
            Log.e("DATE",sysdate+" "+" "+datee);
            flag =   holder.flagID.getText().toString();
            helper = new DatabaseHelper(mCtx);
            if (flag.equals("0")){
                holder.llvv.setVisibility(View.VISIBLE);
                holder.procedIdd.setVisibility(View.VISIBLE);
                holder.officerIdd.setVisibility(View.VISIBLE);
                holder.editID.setVisibility(View.GONE);
                holder.finalsubmitID.setVisibility(View.GONE);

            }
            else if (flag.equals("1")){
                holder.llvv.setVisibility(View.VISIBLE);
                holder.procedIdd.setVisibility(View.GONE);
                holder.officerIdd.setVisibility(View.VISIBLE);
                holder.editID.setVisibility(View.VISIBLE);
                holder.finalsubmitID.setVisibility(View.VISIBLE);
            }
            else if (flag.equals("2")){
                holder.llvv.setVisibility(View.GONE);
                holder.procedIdd.setVisibility(View.GONE);
                holder.officerIdd.setVisibility(View.GONE);
                holder.editID.setVisibility(View.GONE);
            }
            else {

            }
            if (datee.equals(sysdate)) {
                Log.e("LOGDAT", "NOT UPDATE");
            } else {
                if(flag.equals("1")) {
                    String inspctnid = holder.insID.getText().toString();
                    //////////////////////all activity dat check than submit
                    helper = new DatabaseHelper(getContext());
                    Cursor c = helper.getLoginData();
                    if (c.moveToFirst()){
                        do {
                            userID = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                            Log.e("DBUSERID",userID);
                        }while (c.moveToNext());
                    }
                    String queryy = "SELECT * FROM " + TABLE_ALLINSPECTIONFLAG + " where " + TABLE_ALLINSPACATIONID + "=" +inspctnid+ " and " +TABLE_USERIDFLA+ "=" +userID;
                    SQLiteDatabase dbd = helper.getReadableDatabase();
                    Cursor  cursor = dbd.rawQuery(queryy,null);
                    if (cursor.moveToFirst()){
                        do {
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

                    ///////////////////////////////////////

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
                            && otherName.equals("1")){
                        helper = new DatabaseHelper(getContext());
                        SQLiteDatabase sqldb = helper.getReadableDatabase();
                        sqldb.execSQL("UPDATE awcsprocess SET flag='2' WHERE idprocess=" + dbid);
                        SQLiteDatabase awcs = helper.getReadableDatabase();
                        awcs.execSQL("UPDATE awcsdtl SET awcsflag='2' WHERE id=" +dbid);
                        Log.e("LOGDAT", "UPDATE");
                        awcsdtl(dbid);
                        myInspaction(inspctnid,dbid);
                        processDelete(dbid,Userid);
                        listcheckboxDelete(inspctnid,Userid);
                        awcsdtls(inspctnid,Userid);
                        Log.e("inspctnid_Id", " "+inspctnid+" "+"Userid"+" "+Userid+" "+" "+" "+"dbid"+" "+dbid);
                        Intent intent = new Intent(getContext(), NavigationDrawerActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(),"Save All Data Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(),"Save All Data",Toast.LENGTH_SHORT).show();
                    }

                    //////////////////////all activity dat check than submit
                }
            }

            holder.procedIdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                    startLocationButtonClick();
                    stopLocationButtonClick();
                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => " + c.getTime());
                    SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
                    curDate = df3.format(c.getTime());
                    Calendar ccc = Calendar.getInstance();
                    SimpleDateFormat time = new SimpleDateFormat("HH:mm");
                    curTime = time.format(ccc.getTime());
                    Log.e("time"," "+curDate+" "+curTime);
                    String dbid = holder.tableID.getText().toString();
                    helper = new DatabaseHelper(getContext());
                    SQLiteDatabase database = helper.getReadableDatabase();
                    database.execSQL( "UPDATE "+TABLE_PROCESS +" SET " + TABLE_AWCSTIME+ " = '"+curTime+"' WHERE "+TABLE_PROCESSID+ " = "+dbid);
                    SQLiteDatabase databasee = helper.getReadableDatabase();
                    databasee.execSQL( "UPDATE "+TABLE_PROCESS +" SET " + TABLE_CURRENDATE+ " = '"+curDate+"' WHERE "+TABLE_PROCESSID+ " = "+dbid);
                    String id = holder.tableID.getText().toString();
                    String dist = holder.districId.getText().toString();
                    String project = holder.projectId.getText().toString();
                    String sector = holder.sectorId.getText().toString();
                    String center = holder.centerId.getText().toString();
                    String centerID = holder.CenID.getText().toString();
                    String userID = holder.userID.getText().toString();
                    String date = holder.dateId.getText().toString();
                    String flagg =   holder.flagID.getText().toString();
                    String datee = date.replaceAll("[-+.^:,]", "");
                    String insid = centerID+userID+datee;
                    ////create inspection id///////
                    SQLiteDatabase datab = helper.getReadableDatabase();
                    datab.execSQL( "UPDATE "+TABLE_NAMEAWCSDTL +" SET " + TABLE_INSPECTIONID+ " = '"+insid+"' WHERE "+TABLE_ID+ " = "+id);
                    lat = holder.latID.getText().toString();
                    lang = holder.longID.getText().toString();
                    String dbdistid = holder.disID.getText().toString();
                    String dbprojectid = holder.proID.getText().toString();
                    String dbsectorid =  holder.secID.getText().toString();
                    String dbcenterid = holder.CenID.getText().toString();
                    String awcscode = holder.awcsID.getText().toString();
                    String flaggrecord = holder.rcflagId.getText().toString();
                    Log.e("Checklatlang",lat+" "+lang);
                    LatLong(id,dist,project,sector,center,insid,flagg,lat,lang,dbdistid,dbprojectid,dbsectorid,dbcenterid,awcscode,flaggrecord);
                    /////////////////////////////////////
                    helper = new DatabaseHelper(getContext());
                    Cursor del = helper.getReadableDatabase().
                            rawQuery("select * from sportsync where userplan = ? and sportstatus = ?", new String[]{userID,STATUS});
                    if (del.moveToFirst()) {
                        do {
                            insidsync = del.getString(del.getColumnIndex(DatabaseHelper.TABLE_SPORTSINS));
                            STATUS =  del.getString(del.getColumnIndex(DatabaseHelper.TABLE_SPORTSTATUS));
                                awcsdtl(dbid);
                                awcsdtl_flag_0(dbid);
                                awcsdtls(insid, Userid);
                                processDelete(dbid, Userid);
                                processDelete_Inspection(insid, Userid);
                                listcheckboxDelete(insid, Userid);
                                getActivity().finish();
                                startActivity(getActivity().getIntent());
                                Intent into = new Intent(getContext(), NavigationDrawerActivity.class);
                                startActivity(into);

                            Log.e("Checkdata","OKDelete");
                        }while (del.moveToNext());
                    }
                    //////////////////////delete//////////////////////
                }
            });
            holder.editID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                    startLocationButtonClick();
                    stopLocationButtonClick();
                    String dbid = holder.tableID.getText().toString();
                    String id = holder.tableID.getText().toString();
                    String dist = holder.districId.getText().toString();
                    String project = holder.projectId.getText().toString();
                    String sector = holder.sectorId.getText().toString();
                    String center = holder.centerId.getText().toString();
                    String centerID = holder.CenID.getText().toString();
                    String userID = holder.userID.getText().toString();
                    String date = holder.dateId.getText().toString();
                    String flagg =   holder.flagID.getText().toString();
                    datee = date.replaceAll("[-+.^:,]", "");
                    String insid = centerID+userID+datee;
                    ////create inspection id///////
                    helper = new DatabaseHelper(getContext());
                    SQLiteDatabase datab = helper.getReadableDatabase();
                    datab.execSQL( "UPDATE "+TABLE_NAMEAWCSDTL +" SET " + TABLE_INSPECTIONID+ " = '"+insid+"' WHERE "+TABLE_ID+ " = "+id);
                    lat = holder.latID.getText().toString();
                    lang = holder.longID.getText().toString();
//                    Intent intent = new Intent(getApplicationContext(),ProcedActivityy.class);
//                    Bundle bundle_edit  =   new Bundle();
//                    bundle_edit.putString("id",id);
//                    bundle_edit.putString("dist",dist);
//                    bundle_edit.putString("project",project);
//                    bundle_edit.putString("sector",sector);
//                    bundle_edit.putString("center",center);
//                    bundle_edit.putString("insid",insid);
//                    bundle_edit.putString("flag",flagg);
//                    intent.putExtras(bundle_edit);
//                    startActivity(intent);
                    String dbdistid = holder.disID.getText().toString();
                    String dbprojectid = holder.proID.getText().toString();
                    String dbsectorid =  holder.secID.getText().toString();
                    String dbcenterid = holder.CenID.getText().toString();
                    String awcscode = holder.awcsID.getText().toString();
                    String flaggrecord = holder.rcflagId.getText().toString();
                    LatLong(id,dist,project,sector,center,insid,flagg,lat,lang,dbdistid,dbprojectid,dbsectorid,dbcenterid,awcscode,flaggrecord);
                    /////////////////////////////////////
                    helper = new DatabaseHelper(getContext());
                    Cursor del = helper.getReadableDatabase().
                            rawQuery("select * from sportsync where userplan = ? and sportstatus = ?", new String[]{userID,STATUS});
                    if (del.moveToFirst()) {
                        do {
                            insidsync = del.getString(del.getColumnIndex(DatabaseHelper.TABLE_SPORTSINS));
                            STATUS =  del.getString(del.getColumnIndex(DatabaseHelper.TABLE_SPORTSTATUS));
                                awcsdtl(dbid);
                                awcsdtl_flag_0(dbid);
                                awcsdtls(insid,Userid);
                                processDelete(dbid,Userid);
                                processDelete_Inspection(insid,Userid);
                                listcheckboxDelete(insid,Userid);
                                getActivity().finish();
                                startActivity(getActivity().getIntent());
                                Intent into = new Intent(getContext(), NavigationDrawerActivity.class);
                                startActivity(into);
                                Log.e("Checkdata","OKDelete1");


                        }while (del.moveToNext());
                    }
                    //////////////////////delete//////////////////////
                }
            });
            holder.officerIdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = holder.tableID.getText().toString();
                    Intent intent = new Intent(getContext(), OfficerDetailsActivity.class);
                    Bundle bundle_edit  =   new Bundle();
                    bundle_edit.putString("id",id);
                    intent.putExtras(bundle_edit);
                    startActivity(intent);
                }
            });
            holder.finalsubmitID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dbid = holder.tableID.getText().toString();
                    String date = holder.dateId.getText().toString();
                    datee = date.replaceAll("[-+.^:,]", "");
                    Calendar cc = Calendar.getInstance();
                    System.out.println("Current time => " + cc.getTime());
                    SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
                    systemdate = df3.format(cc.getTime());
                    sysdate = systemdate.replaceAll("[-+.^:,]", "");
                    Log.e("DATE",sysdate+" "+" "+datee);
                    Log.e("table",dbid);
                    flag =   holder.flagID.getText().toString();
                    if (datee.equals(sysdate)) {
                        if (flag.equals("1")) {
                            inspactioninsertNetworkercheker = new InspactioninsertNetworkercheker();
                            getActivity().registerReceiver(inspactioninsertNetworkercheker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            inspectionpersonpresentNetworkcheker = new InspectionpersonpresentNetworkcheker();
                            getActivity().registerReceiver(inspectionpersonpresentNetworkcheker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            buildingNetworkStateCheckerr = new BuildingDetailsActivity.BuildingNetworkStateCheckerr();
                            getActivity().registerReceiver(buildingNetworkStateCheckerr, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            toiletNetwokchecker = new ToiletNetwokchecker();
                            getActivity().registerReceiver(toiletNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            kitchenNetwokchecker = new KitchenNetwokchecker();
                            getActivity().registerReceiver(kitchenNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            adqueSpaceNetwokchecker = new AdqueSpaceNetwokchecker();
                            getActivity().registerReceiver(adqueSpaceNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            eletricityNetwokchecker = new EletricityNetwokchecker();
                            getActivity().registerReceiver(eletricityNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            drinkingNetwokchecker = new DrinkingNetwokchecker();
                            getActivity().registerReceiver(drinkingNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            foodNetwokchecker = new FoodNetwokchecker();
                            getActivity().registerReceiver(foodNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            conditionNetwokchecker = new ConditionNetwokchecker();
                            getActivity().registerReceiver(conditionNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            shishuAloyNetwokchecker = new ShishuAloyNetwokchecker();
                            getActivity().registerReceiver(shishuAloyNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            spnNetwokchecker = new SPNNetwokchecker();
                            getActivity().registerReceiver(spnNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            nutriationnetwokchecker = new NutritionNetworkChecker();
                            getActivity().registerReceiver(nutriationnetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            otherinspactionNetworkchecker = new OtherinspactionNetworkchecker();
                            getActivity().registerReceiver(otherinspactionNetworkchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            finalSubmitNetworkcheck = new FinalSubmitNetworkcheck();
                            getActivity().registerReceiver(finalSubmitNetworkcheck, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                            helper = new DatabaseHelper(getContext());
                            String inspctnid = holder.insID.getText().toString();
                            String Userid = holder.userID.getText().toString();
                            //////////////////////all activity dat check than submit
                            helper = new DatabaseHelper(getContext());
                            Cursor c = helper.getLoginData();
                            if (c.moveToFirst()){
                                do {
                                    userID = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                                    Log.e("DBUSERID",userID);
                                }while (c.moveToNext());
                            }
                            String queryy = "SELECT * FROM " + TABLE_ALLINSPECTIONFLAG + " where " + TABLE_ALLINSPACATIONID + "=" +inspctnid+ " and " +TABLE_USERIDFLA+ "=" +userID;
                            SQLiteDatabase dbd = helper.getReadableDatabase();
                            Cursor  cursor = dbd.rawQuery(queryy,null);
                            if (cursor.moveToFirst()){
                                do {
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

                            ///////////////////////////////////////

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
                                    && otherName.equals("1")){
                                SQLiteDatabase sqldb = helper.getReadableDatabase();
                                sqldb.execSQL("UPDATE awcsprocess SET flag='2' WHERE idprocess=" + dbid);
                                awcsdtl(dbid);
                                myInspaction(inspctnid,dbid);
                                processDelete(dbid,Userid);
                                listcheckboxDelete(inspctnid,Userid);
                                awcsdtls(inspctnid,Userid);
                                Log.e("inspctnid_Id", " "+inspctnid+" "+"Userid"+" "+Userid+" "+" "+" "+"dbid"+" "+dbid);
                                Intent intent = new Intent(getContext(), NavigationDrawerActivity.class);
                                startActivity(intent);
                                Toast.makeText(getContext(),"Save All Data Successfully",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getContext(),"Save All Data",Toast.LENGTH_SHORT).show();
                            }

                            //////////////////////all activity dat check than submit


                        } else {
                            Toast.makeText(getContext(), "Please Edit First then Final submit", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        SQLiteDatabase sqldb = helper.getReadableDatabase();
                        sqldb.execSQL("UPDATE awcsprocess SET flag='2' WHERE idprocess=" + dbid);
                        SQLiteDatabase awcs = helper.getReadableDatabase();
                        awcs.execSQL("UPDATE awcsdtl SET awcsflag='2' WHERE id=" +dbid);
                    }
                }
            });

            holder.deleteId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String centerID = holder.CenID.getText().toString();
                    String userID = holder.userID.getText().toString();
                    String date = holder.dateId.getText().toString();
                    String flagg =   holder.flagID.getText().toString();
                    String datee = date.replaceAll("[-+.^:,]", "");
                    String insid = centerID+userID+datee;
                    String dbid = holder.tableID.getText().toString();
                    String inspctnid = holder.insID.getText().toString();
                    String Userid = holder.userID.getText().toString();
                    helper = new DatabaseHelper(getContext());
                    SQLiteDatabase sqldb = helper.getReadableDatabase();
                    sqldb.execSQL("UPDATE awcsprocess SET flag='2' WHERE idprocess=" + dbid);
                    awcsdtl(dbid);
                    awcsdtl_flag_0(dbid);
                    awcsdtls(insid,Userid);
                    processDelete(dbid,Userid);
                    processDelete_Inspection(insid,Userid);
                    listcheckboxDelete(insid,Userid);
                    Log.e("inspctnid_Id_delete", " "+insid+" "+" "+" "+inspctnid+"Userid"+" "+Userid+" "+" "+" "+"dbid"+" "+dbid);
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }
            });
            holder.refresslatlangId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dbdistid = holder.disID.getText().toString();
                    String dbprojectid = holder.proID.getText().toString();
                    String dbsectorid =  holder.secID.getText().toString();
                    String dbcenterid = holder.CenID.getText().toString();
                    String dbid = holder.tableID.getText().toString();
                    lat = holder.latID.getText().toString();
                    lang = holder.longID.getText().toString();
                    Log.e("LATLANG/Upadte"," "+lat+" "+lang);
                    myupdatelatlang(dbdistid,dbcenterid,dbid);

                }
            });
        }

        @Override
        public int getItemCount() {
            return planinglistdb.size();
        }


        class databaseViewHolder extends RecyclerView.ViewHolder {
            TextView districId,projectId,sectorId,centerId,tableID,
                    dateId,userID,disID,proID,secID,CenID,flagID,latID,longID,awcsID,timeID,insID,rcflagId,processId,refresslatlangId;
            Button procedIdd,officerIdd,editID,finalsubmitID,deleteId;
            LinearLayout llvv,llvv1;

            public databaseViewHolder(View itemView) {
                super(itemView);
                districId = itemView.findViewById(R.id.districId);
                projectId = itemView.findViewById(R.id.projectId);
                sectorId = itemView.findViewById(R.id.sectorId);
                centerId = itemView.findViewById(R.id.centerId);
                tableID = itemView.findViewById(R.id.tableID);
                procedIdd = itemView.findViewById(R.id.procedIdd);
                officerIdd = itemView.findViewById(R.id.officerIdd);
                dateId = itemView.findViewById(R.id.dateId);
                userID = itemView.findViewById(R.id.userID);
                disID = itemView.findViewById(R.id.disID);
                proID = itemView.findViewById(R.id.proID);
                secID = itemView.findViewById(R.id.secID);
                CenID = itemView.findViewById(R.id.CenID);
                flagID = itemView.findViewById(R.id.flagID);
                editID = itemView.findViewById(R.id.editID);
                finalsubmitID = itemView.findViewById(R.id.finalsubmitID);
                deleteId = itemView.findViewById(R.id.deleteId);
                llvv = itemView.findViewById(R.id.llvv);
                llvv1 = itemView.findViewById(R.id.llvv1);
                latID = itemView.findViewById(R.id.latID);
                longID = itemView.findViewById(R.id.longID);
                awcsID = itemView.findViewById(R.id.awcsID);
                timeID = itemView.findViewById(R.id.timeID);
                insID = itemView.findViewById(R.id.insID);
                rcflagId = itemView.findViewById(R.id.rcflagId);
                processId = itemView.findViewById(R.id.processId);
                refresslatlangId = itemView.findViewById(R.id.refresslatlangId);
            }
        }
    }


    public class PickupAdapter extends RecyclerView.Adapter<PickupAdapter.OfflineViewHolder> {
        private List<PickupActivityList> pickupActivityLists;
        public Context mCtx;
        public PickupAdapter(Context mCtx, List<PickupActivityList> pickupActivityLists) {
            this.mCtx = mCtx;
            this.pickupActivityLists = pickupActivityLists;
        }

        @Override
        public OfflineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pickuplistadapter, parent, false);
            return new OfflineViewHolder(view);
        }
        @Override
        public void onBindViewHolder(final OfflineViewHolder holder, int position) {
            PickupActivityList pickupListobj = pickupActivityLists.get(position);
            holder.district.setText(pickupListobj.getDistrict());
            holder.project.setText(pickupListobj.getProject());
            holder.sector.setText(pickupListobj.getSector());
            holder.center.setText(pickupListobj.getCenter());
            holder.procedIdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(getContext(), ProcedActivity.class);
                    startActivity(intent1);

                }
            });
            holder.officerIdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(getContext(), OfficerDetailsActivity.class);
                    startActivity(intent1);

                }
            });

        }
        @Override
        public int getItemCount() {
            return pickupActivityLists.size();
        }
        class OfflineViewHolder extends RecyclerView.ViewHolder {
            TextView district,project,sector,center,sectorId,centerId;
            Button procedIdd,officerIdd;
            public OfflineViewHolder(View itemView) {
                super(itemView);
                district = itemView.findViewById(R.id.district);
                project = itemView.findViewById(R.id.project);
                sector = itemView.findViewById(R.id.sector);
                procedIdd = itemView.findViewById(R.id.procedIdd);
                officerIdd = itemView.findViewById(R.id.officerIdd);
                center = itemView.findViewById(R.id.center);
            }
        }
    }
    private void showdata(){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAMEAWCSDTL + " where " +TABLE_ID + "=" + Id, null);
        //Cursor res = helper.getAwcdetailID();
        if (res.getCount() == 0){
            Log.e("Error","Nothing found");
            // Toast.makeText(PickupActivity.this,"NO DATA", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("water :"+ res.getString(1)+"\n");
            buffer.append("cdponame :"+ res.getString(2)+"\n");
            buffer.append("acdpocont :"+ res.getString(3)+"\n\n");
            buffer.append("buildstruc :"+ res.getString(4)+"\n\n");
            buffer.append("electric :"+ res.getString(5)+"\n\n");
            buffer.append("acdponame :"+ res.getString(6)+"\n\n");
            buffer.append("kitchen :"+ res.getString(7)+"\n\n");
            buffer.append("cdpocontact :"+ res.getString(8)+"\n\n");
            buffer.append("workerno :"+ res.getString(9)+"\n\n");
            buffer.append("workernm :"+ res.getString(10)+"\n\n");
            buffer.append("toilet :"+ res.getString(11)+"\n\n");
            buffer.append("awcslat :"+ res.getString(12)+"\n\n");
            buffer.append("supvsr_name :"+ res.getString(13)+"\n\n");
            buffer.append("awcslong :"+ res.getString(14)+"\n\n");
            buffer.append("helperno :"+ res.getString(15)+"\n\n");
            buffer.append("awcsadrs :"+ res.getString(16)+"\n\n");
            buffer.append("foodspace :"+ res.getString(17)+"\n\n");
            buffer.append("helpernm :"+ res.getString(18)+"\n\n");
            buffer.append("buildon :"+ res.getString(19)+"\n\n");
            buffer.append("adqutspacepse :"+ res.getString(20)+"\n\n");
            buffer.append("supvsrno :"+ res.getString(21)+"\n\n");
            buffer.append("awcsid :"+ res.getString(22)+"\n\n");
            buffer.append("awcscode :"+ res.getString(23)+"\n\n");
            buffer.append("awcsname :"+ res.getString(24)+"\n\n");
            buffer.append("planid :"+ res.getString(25)+"\n\n");
            buffer.append("lstinspctnbuldrep :"+ res.getString(26)+"\n\n");
            buffer.append("lstinspctntoiletrep :"+ res.getString(27)+"\n\n");
            buffer.append("lstinspctnkitchenrep :"+ res.getString(28)+"\n\n");
            buffer.append("lstinspctnpserep :"+ res.getString(29)+"\n\n");
            buffer.append("lstinspctnelectricrep :"+ res.getString(29)+"\n\n");
            buffer.append("lstinspctndrnkwaterrep :"+ res.getString(30)+"\n\n");
            buffer.append("lstinspctnfoodrep :"+ res.getString(31)+"\n\n");
            buffer.append("awcsuserid :"+ res.getString(33)+"\n\n");
            buffer.append("awcsflag :"+ res.getString(34)+"\n\n");
            buffer.append("allinspactionid :"+ res.getString(35)+"\n\n");
        }
        Log.e("AllDataShow",buffer.toString());
        processDelete();
    }
    public void processDelete(){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_PROCESS + " where " +TABLE_PROCESSID + "=" + Id, null);
        if (res.getCount() == 0){
            Log.e("Error","Nothing found");
            // Toast.makeText(PickupActivity.this,"NO DATA", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("idprocess :"+ res.getString(0)+"\n");
            buffer.append("dbdistid :"+ res.getString(1)+"\n");
            buffer.append("dbprojectid :"+ res.getString(2)+"\n");
            buffer.append("dbsectorid :"+ res.getString(3)+"\n\n");
            buffer.append("dbcenterid :"+ res.getString(4)+"\n\n");
            buffer.append("districnamedb :"+ res.getString(5)+"\n\n");
            buffer.append("projectnamedb :"+ res.getString(6)+"\n\n");
            buffer.append("sectorrnamedb :"+ res.getString(7)+"\n\n");
            buffer.append("centernamedb :"+ res.getString(8)+"\n\n");
            buffer.append("currentdate :"+ res.getString(9)+"\n\n");
            buffer.append("userid :"+ res.getString(10)+"\n\n");
            buffer.append("flag :"+ res.getString(11)+"\n\n");
            buffer.append("awcslatlocation :"+ res.getString(12)+"\n\n");
            buffer.append("awcsslonglocation :"+ res.getString(13)+"\n\n");
            buffer.append("inspactionid :"+ res.getString(14)+"\n\n");
            buffer.append("awcscodeid :"+ res.getString(15)+"\n\n");
            buffer.append("awcstime :"+ res.getString(16)+"\n\n");
//            buffer.append("flaggrecord :"+ res.getString(17)+"\n\n");
        }
        Log.e("AllDataShowProcess",buffer.toString());
        listcheckboxDeletedata();
    }
    public void listcheckboxDeletedata(){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_ALLINSPECTIONFLAG + " where " +TABLE_ALLINSPECTIONFLAGID + "=" + Id, null);
        if (res.getCount() == 0){
            Log.e("Error","Nothing found");
            // Toast.makeText(PickupActivity.this,"NO DATA", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("insflagid :"+ res.getString(0)+"\n");
            buffer.append("buildingdetails :"+ res.getString(1)+"\n");
            buffer.append("informationoftoilet :"+ res.getString(2)+"\n");
            buffer.append("informationofkitchen :"+ res.getString(3)+"\n\n");
            buffer.append("adequatespaceforpse :"+ res.getString(4)+"\n\n");
            buffer.append("electricity :"+ res.getString(5)+"\n\n");
            buffer.append("drinkingwater :"+ res.getString(6)+"\n\n");
            buffer.append("foodstoreddetails :"+ res.getString(7)+"\n\n");
            buffer.append("conditionofequipmentotherawc :"+ res.getString(8)+"\n\n");
            buffer.append("shishualoy :"+ res.getString(9)+"\n\n");
            buffer.append("snpserved :"+ res.getString(10)+"\n\n");
            buffer.append("nutritionalstatusobserved :"+ res.getString(11)+"\n\n");
            buffer.append("otherinspection :"+ res.getString(12)+"\n\n");
            buffer.append("allinspactionid :"+ res.getString(13)+"\n\n");
            buffer.append("useridflag :"+ res.getString(14)+"\n\n");

        }
        Log.e("AllDataShowCheck",buffer.toString());
    }
    public void awcsdtl(String dbid){
        ///awcsdtl
        String FLAG = "2";
        Log.e("AWCSDELETE",dbid);
        String query = "DELETE  FROM " + TABLE_NAMEAWCSDTL + " where " + TABLE_ID + "=" +dbid+ " and " +TABLE_AWCSFLAG+ "=" +FLAG;
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
                    Log.e("AWCS",awcsid);
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
    public void awcsdtl_flag_0(String dbid){
        ///awcsdtl
        String FLAG = "0";
        Log.e("AWCSDELETE",dbid);
        String query = "DELETE  FROM " + TABLE_NAMEAWCSDTL + " where " + TABLE_ID + "=" +dbid+ " and " +TABLE_AWCSFLAG+ "=" +FLAG;
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
                    Log.e("AWCS",awcsid);
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
    public void awcsdtls(String inspctnid,String Userid){
        Log.e("AWCSDELETE",inspctnid+" "+" "+Userid);
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
                    Log.e("AWCS",awcsid);
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
        String flaggrecord = "";
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
                flaggrecord = c.getString(c.getColumnIndex("flaggrecord"));
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
    public void processDelete_Inspection(String insid,String Userid){
        Log.e("PROCESSDELETE",insid+" "+Userid);
        String query = "DELETE  FROM " + TABLE_PROCESS + " where " + TABLE_INSPACTIONID + "=" +insid+ " and " +TABLE_USERID+ "=" +Userid;
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
                flaggrecord = c.getString(c.getColumnIndex("flaggrecord"));
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
    public void myupdatelatlang(final String dbdistid,final String dbcenterid,final String dbid){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LATLANGSEND,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("latlangsend"," "+response);
                        try {
                            JSONObject object =new JSONObject(response);
                            Log.e("obj"," "+object);
                            String latitude = object.getString("latitude");
                            String longitude = object.getString("longitude");
                            String status = object.getString("status");
                            String msg = object.getString("msg");
                            Log.e("LATLANG/Upadte",latitude+" "+longitude);

                            if (status.equals("1")) {
                                helper = new DatabaseHelper(getContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_AWCSLATLOCATION + " = '" + latitude + "' WHERE " + TABLE_PROCESSID + " = " + dbid);
                                SQLiteDatabase dbbb = helper.getReadableDatabase();
                                dbbb.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_AWCSLONGLOCATION + " = '" + longitude + "' WHERE " + TABLE_PROCESSID + " = " + dbid);
                                getActivity().finish();
                                startActivity(getActivity().getIntent());

                            }
                            if (status.equals("0")){
                                String title = "Message Box";
                                String msgg = msg;
                                createDialog(title,msgg);
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

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dist_id",dbdistid);
                params.put("center_id",dbcenterid);
                Log.e("lat_long_send",dbdistid+" "+dbcenterid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        VolleySingleton volleySingleton = VolleySingleton.getInstance(getContext());
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    public AlertDialog createDialog(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService
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
    public void myInspaction(final String inspctnid,final String dbid){
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
                                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                                syncfinalsub(dbid,inspctnid,FINAL_SUB_WITH_SERVER);
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
                        syncfinalsub(dbid,inspctnid,FINAL_SUB_WITH_SERVER_NOT);
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
        VolleySingleton volleySingleton = VolleySingleton.getInstance(getContext());
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    public void syncfinalsub(String dbid,String inspctnid,int finalsubmitstatus) {
        Cursor c = helper.getLoginData();
        if (c.moveToFirst()){
            do {
                userID = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                Log.e("FINALUSERID",userID);
            }while (c.moveToNext());
        }
        String queryy = "SELECT * FROM " + TABLE_ALLINSPECTIONFLAG + " where " + TABLE_ALLINSPACATIONID + "=" +inspctnid+ " and " +TABLE_USERIDFLA+ "=" +userID;
        SQLiteDatabase dbd = helper.getReadableDatabase();
        Cursor  cursor = dbd.rawQuery(queryy,null);
        if (cursor.moveToFirst()){
            do {
                String  IdOther = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
                otherName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTION));
                Log.e("FINALUSERIDOTHER"," "+IdOther+" "+otherName);
            }
            while (cursor.moveToNext());
        }
        if (otherName.equals("0")) {
            helper.FINALSUBMITINSERT(inspctnid, finalsubmitstatus);
        } else {
            helper.FINALSUBMITUPDATE(dbid, inspctnid, finalsubmitstatus);
        }
    }
    @Override
    public void onDestroy() {
        System.out.println("Inside onDestroy");
        Log.e("LOGBUILD","Inside onDestroy");
        try{
            if (inspactioninsertNetworkercheker!=null)
                getActivity().unregisterReceiver(inspactioninsertNetworkercheker);
            if (inspectionpersonpresentNetworkcheker!=null)
                getActivity().unregisterReceiver(inspectionpersonpresentNetworkcheker);
            if(buildingNetworkStateCheckerr!=null)
                getActivity().unregisterReceiver(buildingNetworkStateCheckerr);
            if (toiletNetwokchecker!=null)
                getActivity().unregisterReceiver(toiletNetwokchecker);
            if (kitchenNetwokchecker!=null)
                getActivity().unregisterReceiver(kitchenNetwokchecker);
            if (adqueSpaceNetwokchecker!=null)
                getActivity().unregisterReceiver(adqueSpaceNetwokchecker);
            if (eletricityNetwokchecker!=null)
                getActivity().unregisterReceiver(eletricityNetwokchecker);
            if (drinkingNetwokchecker!=null)
                getActivity().unregisterReceiver(drinkingNetwokchecker);
            if (foodNetwokchecker!=null)
                getActivity().unregisterReceiver(foodNetwokchecker);
            if (conditionNetwokchecker!=null)
                getActivity().unregisterReceiver(conditionNetwokchecker);
            if (shishuAloyNetwokchecker!=null)
                getActivity().unregisterReceiver(shishuAloyNetwokchecker);
            if (spnNetwokchecker!=null)
                getActivity().unregisterReceiver(spnNetwokchecker);
            if (nutriationnetwokchecker!=null)
                getActivity().unregisterReceiver(nutriationnetwokchecker);
            if (otherinspactionNetworkchecker!=null)
                getActivity().unregisterReceiver(otherinspactionNetworkchecker);
            if (finalSubmitNetworkcheck!=null)
                getActivity().unregisterReceiver(finalSubmitNetworkcheck);
            if(broadcastReceiverotherr!=null)
                getActivity().unregisterReceiver(broadcastReceiverotherr);
        }catch(Exception e){}
        super.onDestroy();
    }
    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mSettingsClient = LocationServices.getSettingsClient(getActivity());

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                updateLocationUI();
            }
        };
        mRequestingLocationUpdates = false;
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }
    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }
            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }
        updateLocationUI();
    }
    private void updateLocationUI() {
        if (mCurrentLocation != null) {
            Log.e("MyLoc","Lat: " + mCurrentLocation.getLatitude() + ", " + "Lng: " + mCurrentLocation.getLongitude()
            );
            llat2 = String.valueOf(mCurrentLocation.getLatitude());
            llng2 = String.valueOf(mCurrentLocation.getLongitude());
            Log.e("second",llat2+"  "+"  "+llng2);
            String sss = llat2;
            String ssss = llng2;
            try {
                flat2 = Float.valueOf(sss.trim()).floatValue();
                System.out.println("float f = " + flat2);
            } catch (NumberFormatException nfe) {
                System.err.println("NumberFormatException: " + nfe.getMessage());
            }
            try {
                flng2 = Float.valueOf(ssss.trim()).floatValue();
                System.out.println("float f = " + flng2);
            } catch (NumberFormatException nfe) {
                System.err.println("NumberFormatException: " + nfe.getMessage());
            }
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);

    }
    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());
                        updateLocationUI();
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }
    public void startLocationButtonClick(){
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            openSettings();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    public void stopLocationButtonClick() {
        mRequestingLocationUpdates = false;
        stopLocationUpdates();
    }
    public void stopLocationUpdates() {
        mFusedLocationClient
                .removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }
    public void showLastKnownLocation() {
        if (mCurrentLocation != null) {
            Toast.makeText(getContext(), "Lat: " + mCurrentLocation.getLatitude()
                    + ", Lng: " + mCurrentLocation.getLongitude(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Last known location is not available!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e(TAG, "User agreed to make required location settings changes.");
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }
        updateLocationUI();
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mRequestingLocationUpdates) {
            stopLocationUpdates(); }
    }
    public void LatLong(String id,String dist,String  project,String sector,String center,String insid,String flagg,String lat,String lang,
                        String dbdistid,String dbprojectid,String dbsectorid,String dbcenterid,String awcscode,String flaggrecord){
        Log.e("LatLong",lat+" "+lang);
        String s = lat;
        String ss = lang;
        float lat1 = 0;
        float lng1 = 0;
        try {
            lat1 = Float.valueOf(s.trim()).floatValue();
            System.out.println("float f = " + lat1);
        } catch (NumberFormatException nfe) {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }
        try {
            lng1 = Float.valueOf(ss.trim()).floatValue();
            System.out.println("float f = " + lng1);
        } catch (NumberFormatException nfe) {
            System.err.println("NumberFormatException: " + nfe.getMessage());
        }
        distance(lat1, lng1, flat2, flng2,id,dist,project,sector,center,insid,flagg,dbdistid,dbprojectid,dbsectorid,dbcenterid,awcscode,flaggrecord);
    }
    public float distance(float lat1, float lng1, float flat2, float flng2,String id,String distt,String project,String sector,
                          String center,String insid,String flagg,String dbdistid,String dbprojectid,
                          String dbsectorid,String dbcenterid,String awcscode,String flaggrecord) {
        //double earthRadius = 6371000; //meters
        // double earthRadius = 127563200; //meters
//       double earthRadius = 6371;
//        double dLat = Math.toRadians(flat2 - lat1);
//        double dLng = Math.toRadians(flng2 - lng1);
//        Log.e("tdis", ""+dLat+  " "+ ""+dLng);
//        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
//                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(flat2)) *
//                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        Log.e("tdis1", ""+c);
//        float dist = (float) (earthRadius * c);
//        Log.e("dist", String.valueOf(dist));
//        float strDouble = Float.parseFloat(String.format("%.0f", dist));
//        System.out.println(strDouble);
//        Log.e("Startloc",""+strDouble);
        //int Radius = 6371;// radius of earth in Km
        int Radius = 6371000;
        //int Radius = 127563200;
        double lat11 = lat1;
        double lat22 = flat2;
        double lon1 = lng1;
        double lon2 = flng2;
        double dLat = Math.toRadians(lat22 - lat11);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat11))
                * Math.cos(Math.toRadians(lat22)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.e("METER", " "+km);
        Log.e("Radius_Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);
        Log.e("DISTANCE"," "+meterInDec);
        if(meterInDec<200){
            Intent intent = new Intent(getContext(), ProcedActivityy.class);
            Bundle bundle_edit  =   new Bundle();
            bundle_edit.putString("id",id);
            bundle_edit.putString("dist",distt);
            bundle_edit.putString("dbdistid",dbdistid);
            bundle_edit.putString("project",project);
            bundle_edit.putString("dbprojectid",dbprojectid);
            bundle_edit.putString("sector",sector);
            bundle_edit.putString("dbsectorid",dbsectorid);
            bundle_edit.putString("center",center);
            bundle_edit.putString("dbcenterid",dbcenterid);
            bundle_edit.putString("awcscode",awcscode);
            bundle_edit.putString("insid",insid);
            bundle_edit.putString("flag",flagg);
            bundle_edit.putString("flaggrecord",flaggrecord);
            intent.putExtras(bundle_edit);
            startActivity(intent);
            Toast.makeText(getContext(), "You are  in Center", Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(getContext(), "You are not in Center", Toast.LENGTH_SHORT).show();
        }
        return (float) (Radius * c);
        //  return dist;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}