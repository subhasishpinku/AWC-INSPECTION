package icdswb.in;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.Dbprocess;
import icdswb.in.ActivitySetGet.Myinspaction;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.Activity_Adapter.Myinspactionadapter;
import icdswb.in.Activity_Adapter.PlaningAdapterActivityDB;

public class MyInspactionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Dbprocess> planinglistdb;
    private PlaningAdapterActivityDB adapter1;
    List<Myinspaction> myinspaction;
    private Myinspactionadapter myinspactionadapter;
    ArrayList<Myinspaction> arrlist = new ArrayList<Myinspaction>();
    DatabaseHelper helper;
    String userid;
    String flag;
    String FLAGG = "2";
    Boolean isScrolling = false;
    int currentItem,totalItem,scrollistItem;
    ProgressBar progressBar;
    LinearLayoutManager manager;
    ArrayList list;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.myinspactionactivity);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        initToolBar();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        planinglistdb = new ArrayList<>();
        myinspaction = new ArrayList<>();
        helper = new DatabaseHelper(this);
        Cursor c = helper.getLoginData();
        if (c.moveToFirst()) {
            do {
                userid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
            } while (c.moveToNext());
            Log.e("userid",userid);
        }
        String query = "SELECT * FROM awcsprocess WHERE userid=" + userid;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                flag = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FLAG)); }
            while (cursor.moveToNext());
            Log.e("flag",flag);
        }
        MyDatashow();
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Inspection");
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
    public void MyDatashow(){
       // String query = "SELECT * FROM awcsprocess WHERE userid=" + userid;
        //String query = "SELECT * FROM awcsprocess WHERE userid = userid AND flag = '2'";
        //////////////////////////////
//        String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_USERID + "=" +userid+ " and " +TABLE_FLAG+ "=" +FLAGG;
//
//        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query,null);
//        if (cursor.moveToFirst()){
//            do {
//                Dbprocess planinglistDB = new Dbprocess(
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROCESSID)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBDISTID)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBPROJECTID)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBSECTORID)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBCENTERID)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROJECT)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DISTRIC)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SECTOR)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CENTER)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CURRENDATE)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERID)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FLAG)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLATLOCATION)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLONGLOCATION)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPACTIONID)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSCODEID)),
//                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSTIME))
//                );
//                planinglistdb.add(planinglistDB);
//            }
//            while (cursor.moveToNext());
//        }
//        adapter1 = new PlaningAdapterActivityDB(MyInspactionActivity.this,planinglistdb);
//        recyclerView.setAdapter(adapter1);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.MYINSPACTION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("MyInspaction"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("obj"," "+array);
                            JSONObject jsonObject = array.getJSONObject(0);
                            String msg = jsonObject.getString("msg");
                            JSONObject jsonObject1 = array.getJSONObject(1);
                            JSONArray jsonArray = jsonObject1.getJSONArray("inspectn");
                            for (int i = 0; i<jsonArray.length(); i++){
                                JSONObject obj = jsonArray.getJSONObject(i);
                                String distname = obj.getString("dist_name");
                                String projname = obj.getString("proj_name");
                                String sectorname = obj.getString("sector_name");
                                String centername = obj.getString("center_name");
                                String inspctnid = obj.getString("inspctn_id");
                                String inspctndate = obj.getString("inspctn_date");
                                Log.e("MYIN"," "+distname+" "+projname+" "+sectorname+" "+centername+" "+inspctnid+" "+inspctndate);
                                myinspaction.add(new Myinspaction(
                                        obj.getString("dist_name"),
                                        obj.getString("proj_name"),
                                        obj.getString("sector_name"),
                                        obj.getString("center_name"),
                                        obj.getString("inspctn_id"),
                                        obj.getString("inspctn_date")

                                ));
                                arrlist.addAll(myinspaction);
                                list = new ArrayList(Arrays.asList(myinspaction));
                                myinspactionadapter = new Myinspactionadapter(getApplicationContext(),myinspaction);
                                recyclerView.setAdapter(myinspactionadapter);
                                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                        super.onScrollStateChanged(recyclerView, newState);
                                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                                            isScrolling = true;
                                            Log.e("LODING", "Last Item Wow !");
                                            fetchData();
                                        }
                                    }

                                    @Override
                                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                        super.onScrolled(recyclerView, dx, dy);
                                        currentItem = manager.getChildCount();
                                        totalItem = manager.getItemCount();
                                        scrollistItem = manager.findFirstVisibleItemPosition();
                                        if (isScrolling && (currentItem + scrollistItem == totalItem)){
                                            isScrolling = false;

                                        }
                                    }
                                });
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
                params.put("user_id",userid);
                Log.e("MyInspaction",userid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
    private void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        new  Handler().postDelayed(
                new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<10; i++) {
                    list.add(Math.floor(Math.random() * 100) + "");
                    myinspactionadapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }

            }
        },500
        );
    }
}
