package icdswb.in;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.Dbprocess;
import icdswb.in.ActivitySetGet.PlaningList;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.RequestHandler;
import icdswb.in.Activity_Adapter.PlaningAdapterActivity;
import icdswb.in.Activity_Adapter.PlaningAdapterActivityDB;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERID;


public class PlaningmainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<PlaningList> planinglist;
    ArrayList<PlaningList> arrlist = new ArrayList<PlaningList>();
    List<Dbprocess> planinglistdb;
    private PlaningAdapterActivity adapter;
    private PlaningAdapterActivityDB adapter1;
    String formattedDate3,day;
    FloatingActionButton fab;
    String discode,userid;
    DatabaseHelper helper;
    CoordinatorLayout coordinatorLayout;
    String FLAGG = "1";
    String FLAG = "0";
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.planingmainactivity);
        initToolBar();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        discode = String.valueOf(user.getUserDist());
        userid = String.valueOf(user.getUserID());
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate3 = df3.format(c.getTime());
        SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE");
        Date date =new Date();
        day= parseFormat.format(date);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        planinglist = new ArrayList<>();
        planinglistdb = new ArrayList<>();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()){
                    Intent intent = new Intent(getApplicationContext(), PlaningActivity.class);
                    startActivity(intent);

                }
                else {

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Internet Connection is not found", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Retry Network!", Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                }
                            });

                    snackbar.show();

                }

            }
        });
        helper = new DatabaseHelper(this);

//        recyclerView.setAdapter(adapter);
//        planinglist.add(
//                new PlaningList(
//                        formattedDate3,day,"KOLKATA","BELGACHIA","Zone-04","PAIKPARA NETAJI SPORTING CLUB"));
//
//        adapter = new PlaningAdapterActivity(this, planinglist);
//
//        //setting adapter to recyclerview
//        recyclerView.setAdapter(adapter);

        if (isNetworkAvailable()){
         //   getplaning();

        }
        else {

        }

        showPlan();

    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Planning");
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
    public void showPlan(){
        // Cursor cursor = helper.getawcsprocessInsert();
        // String query = "SELECT * FROM awcsprocess WHERE userid=" + userid;
      //  String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid+ " and " +TABLE_FLAG+ "=" +FLAG+ " or " +TABLE_FLAG+ "=" +FLAGG;
          String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid;
         helper = new DatabaseHelper(getApplicationContext());
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
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLATLOCATION)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLONGLOCATION)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPACTIONID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSCODEID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSTIME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FLAGGRECORD))
                );
                planinglistdb.add(planinglistDB);
            }
            while (cursor.moveToNext());
        }
        adapter1 = new PlaningAdapterActivityDB(PlaningmainActivity.this,planinglistdb);
        recyclerView.setAdapter(adapter1);

    }

    public boolean isNetworkAvailable() {
        boolean connect=false;
        ConnectivityManager conMgr =  (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            connect=false;
        }else{
            connect= true;
        }
        return connect;
    }

    private void getplaning(){
        class planing extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("dist", discode);
                params.put("user_id", userid);
                return requestHandler.sendPostRequest(Config.PLANING_URL, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                //progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                //     progressBar.setVisibility(View.GONE);
                try {
                    JSONObject obj =new JSONObject(s);
                    Log.e("PLANING"," "+obj);
                    JSONArray centre = obj.getJSONArray("centre");
                    JSONObject centerObj = centre.getJSONObject(0);
                    String projectId = centerObj.getString("project_id");
                    String projectName = centerObj.getString("project_name");
                    String sectorId = centerObj.getString("sector_id");
                    String sectorName = centerObj.getString("sector_name");
                    String centreId = centerObj.getString("centre_id");
                    String centreName = centerObj.getString("centre_name");
                    String planDT = centerObj.getString("plan_dt");
                    Log.e("PLAN"," "+projectId+" "+projectName+" "+sectorId+" "+sectorName+" "+centreId+" "+centreName+" "+planDT+" ");
                    planinglist.add(new PlaningList(
                            centerObj.getString("project_id"),
                            centerObj.getString("project_name"),
                            centerObj.getString("sector_id"),
                            centerObj.getString("sector_name"),
                            centerObj.getString("centre_id"),
                            centerObj.getString("centre_name"),
                            centerObj.getString("plan_dt")

                    ));
                    arrlist.addAll(planinglist);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            adapter = new PlaningAdapterActivity(PlaningmainActivity.this,planinglist );
                            recyclerView.setAdapter(adapter);

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        planing ru = new planing();
        ru.execute();
    }

    }


