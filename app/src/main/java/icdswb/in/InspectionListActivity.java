package icdswb.in;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.Activity_Interface.ItemClickListener;
import icdswb.in.Activity_ListInspaction.ListInspaction;


public class InspectionListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<ListInspaction> listInspactionList;
    private InspectionListAdapter adapter;
    int listview = -1;
    String water = "NA";
    String cdponame ="NA";
    String acdpocont = "NA";
    String buildstruc = "NA";
    String electric ="NA";
    String acdponame ="NA";
    String kitchen ="NA";
    String cdpocontact = "NA";
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
    String awcsid ="NA";
    String awcscode ="NA";
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
    String planid = "NA";
    String yncdpo = "NA";
    String ynacdpio = "NA";
    String YnSupervisor = "NA";
    String Ynworker = "NA";
    String Yhelper = "NA";
    String Ynawcs = "NA";
    String insid;
    String lstinspctnbuldrep = "NA";
    String lstinspctntoiletrep = "NA";
    String lstinspctnkitchenrep = "NA";
    String lstinspctnpserep = "NA";
    String lstinspctnelectricrep = "NA";
    String lstinspctndrnkwaterrep = "NA";
    String lstinspctnfoodrep = "NA";

    String buildingdetails = "NA";
    String informationoftoilet = "NA";
    String informationofkitchen ="NA";
    String adequatespaceforpse = "NA";
    String electricity ="NA";
    String drinkingwater ="NA";
    String foodstoreddetails ="NA";
    String conditionofequipmentotherawc ="NA";
    String shishualoy ="NA";
    String snpserved ="NA";
    String nutritionalstatusobserved ="NA";
    String otherinspection ="NA";
    String allinspactionid ="NA";
    private DatabaseHelper db;
    DatabaseHelper helper;
    String dbid = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inspectionlist);
        //Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        water = intent.getStringExtra("water");
        cdponame = intent.getStringExtra("cdponame");
        acdpocont = intent.getStringExtra("acdpocont");
        buildstruc = intent.getStringExtra("buildstruc");
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
        yncdpo = intent.getStringExtra("yncdpo");
        ynacdpio = intent.getStringExtra("ynacdpio");
        YnSupervisor = intent.getStringExtra("YnSupervisor");
        Ynworker = intent.getStringExtra("Ynworker");
        Yhelper = intent.getStringExtra("Yhelper");
        Ynawcs = intent.getStringExtra("Ynawcs");
        insid = intent.getStringExtra("insid");
        lstinspctnbuldrep = intent.getStringExtra("lstinspctnbuldrep");
        lstinspctntoiletrep = intent.getStringExtra("lstinspctntoiletrep");
        lstinspctnkitchenrep = intent.getStringExtra("lstinspctnkitchenrep");
        lstinspctnpserep = intent.getStringExtra("lstinspctnpserep");
        lstinspctnelectricrep = intent.getStringExtra("lstinspctnelectricrep");
        lstinspctndrnkwaterrep = intent.getStringExtra("lstinspctndrnkwaterrep");
        lstinspctnfoodrep = intent.getStringExtra("lstinspctnfoodrep");
        dbid = intent.getStringExtra("dbid");

//        Log.e("dbid",dbid);
        Log.e("LAST_REPO", lstinspctnbuldrep + " " + lstinspctntoiletrep + " " + lstinspctnkitchenrep + " " + lstinspctnpserep + " " + lstinspctnelectricrep + " " + lstinspctndrnkwaterrep + " " + lstinspctnfoodrep);
       // Log.e("insid", insid);
        Log.e("YESNOFILD", " " + yncdpo + " " + ynacdpio + " " + YnSupervisor + " " + Ynworker + " " + Yhelper + " " + Ynawcs + " ");
        Log.e("ALLPROCESSDATA", water + " " + cdponame + " " + acdpocont + " " + buildstruc + " " + electric + " " + acdponame + " " + kitchen + " " + cdpocontact + " " + workerno + " " + worker_nm + " " + toilet + " " + awcslat + " " + supvsrname + " " + awcsslong + " " + helperno + " " + awcs_adrs + " " + foodspace + " "
                + helpernm + " " + buildon + " "
                + adqutspacepse + " " + supvsrno + awcsid + " " + "" + awcscode + " " + awcsname + " "+" "+" "+dbid);
        Log.e("ACDPODATA", acdponame + " " + acdpocont);
        Log.e("DATADISTRICE", dbdistid + " " + dbprojectid + " " + dbsectorid + " " + dbcenterid + " " + projectnamedb + " " + districnamedb + " " + sectorrnamedb + " " + centernamedb + " " + currentdate + " " + planid + " ");
        db = new DatabaseHelper(this);
        helper = new DatabaseHelper(getApplicationContext());
      //  Cursor cursor = helper.getReadableDatabase().
           //     rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
        String queryy = "SELECT * FROM insflag WHERE allinspactionid=" + insid;
        SQLiteDatabase dbd = helper.getReadableDatabase();
        Cursor  cursor = dbd.rawQuery(queryy,null);
        if (cursor.moveToFirst()){
            do {
                String id =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
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
                otherinspection =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTION));
                allinspactionid = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPACATIONID));
                Log.e("ALLINSFLAG", " "+id+" "+buildingdetails + " " + informationoftoilet + " " + informationofkitchen + " " + adequatespaceforpse + " " + electricity + " " + drinkingwater + " " + foodstoreddetails + " " + conditionofequipmentotherawc + " " + shishualoy + " " + snpserved + " " + nutritionalstatusobserved+" "+otherinspection+" "+allinspactionid);
            }
            while (cursor.moveToNext());
        }
        initToolBar();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listInspactionList = new ArrayList<>();
        recyclerView.setAdapter(adapter);

        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Building Details",
                        R.drawable.home,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));

        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Information of Toilet",
                        R.drawable.toilet,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));

        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Information of Kitchen",
                        R.drawable.pressurecooker,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Space for PSE / ECCE",
                        R.drawable.adequate,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Electricity",
                        R.drawable.electric,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Drinking Water",
                        R.drawable.water,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Food Storage & Stock",
                        R.drawable.supermarket,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Equipment & Others at AWC",
                        R.drawable.utensil,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Shishu Aloy / Normal AWC",
                        R.drawable.sishu,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Hot Cooked Meal",
                        R.drawable.snp,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "Nutritional Status",
                        R.drawable.growthchart,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        listInspactionList.add(
                new ListInspaction(
                        1,
                        "General Observations",
                        R.drawable.search,buildingdetails,informationoftoilet,informationofkitchen,adequatespaceforpse,electricity,drinkingwater,foodstoreddetails,conditionofequipmentotherawc,shishualoy,snpserved,nutritionalstatusobserved,otherinspection,allinspactionid));
        //creating recyclerview adapter
        adapter = new InspectionListAdapter(this, listInspactionList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext()
                , recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ListInspaction list = listInspactionList.get(position);
                listview = position;
                 // Toast.makeText(getApplicationContext(),""+listview, Toast.LENGTH_SHORT).show();
                //      Cursor cursor = db.getInspactionData();
                helper = new DatabaseHelper(getApplicationContext());
              //  Cursor cursor = helper.getReadableDatabase().
              //          rawQuery("select * from insflag where insflagid = ?", new String[]{dbid});
                String queryy = "SELECT * FROM insflag WHERE allinspactionid=" + insid;
                SQLiteDatabase dbd = helper.getReadableDatabase();
                Cursor  cursor = dbd.rawQuery(queryy,null);
                if (cursor.moveToFirst()){
                    do {
                        String id =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPECTIONFLAGID));
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
                        otherinspection =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTION));
                        allinspactionid = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ALLINSPACATIONID));
                        Log.e("ALLINSFLAG", buildingdetails + " " + informationoftoilet + " " + informationofkitchen + " " + adequatespaceforpse + " " + electricity + " " + drinkingwater + " " + foodstoreddetails + " " + conditionofequipmentotherawc + " " + shishualoy + " " + snpserved + " " + nutritionalstatusobserved+" "+otherinspection+" "+allinspactionid);
                    }
                    while (cursor.moveToNext());
                }
                if (listview == 0) {
                    finish();
                    startActivity(getIntent());
                    Intent intent = new Intent(InspectionListActivity.this, BuildingDetailsActivity.class);
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
                if (listview == 1) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent1 = new Intent(InspectionListActivity.this, InformationToiletActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("lstinspctnbuldrep", lstinspctnbuldrep);
//                    bundle.putString("lstinspctntoiletrep", lstinspctntoiletrep);
//                    bundle.putString("lstinspctnkitchenrep", lstinspctnkitchenrep);
//                    bundle.putString("lstinspctnpserep", lstinspctnpserep);
//                    bundle.putString("lstinspctnelectricrep", lstinspctnelectricrep);
//                    bundle.putString("lstinspctndrnkwaterrep", lstinspctndrnkwaterrep);
//                    bundle.putString("lstinspctnfoodrep", lstinspctnfoodrep);
//                    bundle.putString("dbid",dbid);
//                    intent1.putExtras(bundle);
//                    startActivity(intent1);

                    Intent intent1 = new Intent(InspectionListActivity.this, InformationToiletActivity.class);
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
                    intent1.putExtras(bundle_edit);
                    startActivity(intent1);
                }
                if (listview == 2) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent2 = new Intent(InspectionListActivity.this, InformationKitchenActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("lstinspctnbuldrep", lstinspctnbuldrep);
//                    bundle.putString("lstinspctntoiletrep", lstinspctntoiletrep);
//                    bundle.putString("lstinspctnkitchenrep", lstinspctnkitchenrep);
//                    bundle.putString("lstinspctnpserep", lstinspctnpserep);
//                    bundle.putString("lstinspctnelectricrep", lstinspctnelectricrep);
//                    bundle.putString("lstinspctndrnkwaterrep", lstinspctndrnkwaterrep);
//                    bundle.putString("lstinspctnfoodrep", lstinspctnfoodrep);
//                    bundle.putString("dbid",dbid);
//                    intent2.putExtras(bundle);
//                    startActivity(intent2);

                    Intent intent2 = new Intent(InspectionListActivity.this, InformationKitchenActivity.class);
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
                    intent2.putExtras(bundle_edit);
                    startActivity(intent2);
                }
                if (listview == 3) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent3 = new Intent(InspectionListActivity.this, AdequateSpacePSEActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("lstinspctnbuldrep", lstinspctnbuldrep);
//                    bundle.putString("lstinspctntoiletrep", lstinspctntoiletrep);
//                    bundle.putString("lstinspctnkitchenrep", lstinspctnkitchenrep);
//                    bundle.putString("lstinspctnpserep", lstinspctnpserep);
//                    bundle.putString("lstinspctnelectricrep", lstinspctnelectricrep);
//                    bundle.putString("lstinspctndrnkwaterrep", lstinspctndrnkwaterrep);
//                    bundle.putString("lstinspctnfoodrep", lstinspctnfoodrep);
//                    bundle.putString("dbid",dbid);
//                    intent3.putExtras(bundle);
//                    startActivity(intent3);


                    Intent intent3 = new Intent(InspectionListActivity.this, AdequateSpacePSEActivity.class);
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
                    intent3.putExtras(bundle_edit);
                    startActivity(intent3);
                }
                if (listview == 4) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent4 = new Intent(InspectionListActivity.this, ElectricityActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("lstinspctnbuldrep", lstinspctnbuldrep);
//                    bundle.putString("lstinspctntoiletrep", lstinspctntoiletrep);
//                    bundle.putString("lstinspctnkitchenrep", lstinspctnkitchenrep);
//                    bundle.putString("lstinspctnpserep", lstinspctnpserep);
//                    bundle.putString("lstinspctnelectricrep", lstinspctnelectricrep);
//                    bundle.putString("lstinspctndrnkwaterrep", lstinspctndrnkwaterrep);
//                    bundle.putString("lstinspctnfoodrep", lstinspctnfoodrep);
//                    bundle.putString("dbid",dbid);
//                    intent4.putExtras(bundle);
//                    startActivity(intent4);
                    Intent intent4 = new Intent(InspectionListActivity.this, ElectricityActivity.class);
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
                    intent4.putExtras(bundle_edit);
                    startActivity(intent4);
                }
                if (listview == 5) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent5 = new Intent(InspectionListActivity.this, DrinkingWaterActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("lstinspctnbuldrep", lstinspctnbuldrep);
//                    bundle.putString("lstinspctntoiletrep", lstinspctntoiletrep);
//                    bundle.putString("lstinspctnkitchenrep", lstinspctnkitchenrep);
//                    bundle.putString("lstinspctnpserep", lstinspctnpserep);
//                    bundle.putString("lstinspctnelectricrep", lstinspctnelectricrep);
//                    bundle.putString("lstinspctndrnkwaterrep", lstinspctndrnkwaterrep);
//                    bundle.putString("lstinspctnfoodrep", lstinspctnfoodrep);
//                    bundle.putString("dbid",dbid);
//                    intent5.putExtras(bundle);
//                    startActivity(intent5);

                    Intent intent5 = new Intent(InspectionListActivity.this, DrinkingWaterActivity.class);
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
                    intent5.putExtras(bundle_edit);
                    startActivity(intent5);

                }
                if (listview == 6) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent6 = new Intent(InspectionListActivity.this, FoodStoredDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("lstinspctnbuldrep", lstinspctnbuldrep);
//                    bundle.putString("lstinspctntoiletrep", lstinspctntoiletrep);
//                    bundle.putString("lstinspctnkitchenrep", lstinspctnkitchenrep);
//                    bundle.putString("lstinspctnpserep", lstinspctnpserep);
//                    bundle.putString("lstinspctnelectricrep", lstinspctnelectricrep);
//                    bundle.putString("lstinspctndrnkwaterrep", lstinspctndrnkwaterrep);
//                    bundle.putString("lstinspctnfoodrep", lstinspctnfoodrep);
//                    bundle.putString("dbid",dbid);
//                    intent6.putExtras(bundle);
//                    startActivity(intent6);


                    Intent intent6 = new Intent(InspectionListActivity.this, FoodStoredDetailActivity.class);
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
                    intent6.putExtras(bundle_edit);
                    startActivity(intent6);
                }
                if (listview == 7) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent7 = new Intent(InspectionListActivity.this, ConditionOfEquipmentOthersAWCActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("dbid",dbid);
//                    intent7.putExtras(bundle);
//                    startActivity(intent7);


                    Intent intent7 = new Intent(InspectionListActivity.this, ConditionOfEquipmentOthersAWCActivity.class);
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
                    intent7.putExtras(bundle_edit);
                    startActivity(intent7);
                }
                if (listview == 8) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent8 = new Intent(InspectionListActivity.this, ShishuAloyActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("dbid",dbid);
//                    intent8.putExtras(bundle);
//                    startActivity(intent8);


                    Intent intent8 = new Intent(InspectionListActivity.this, ShishuAloyActivity.class);
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
                    intent8.putExtras(bundle_edit);
                    startActivity(intent8);
                }
                if (listview == 9) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent9 = new Intent(InspectionListActivity.this, SNPServedActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("dbid",dbid);
//                    //bundle.putString("currentdate",currentdate);
//                    intent9.putExtras(bundle);
//                    startActivity(intent9);


                    Intent intent9 = new Intent(InspectionListActivity.this, SNPServedActivity.class);
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
                    intent9.putExtras(bundle_edit);
                    startActivity(intent9);
                }
                if (listview == 10) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent10 = new Intent(InspectionListActivity.this, NutritionalStatusObservedActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("dbid",dbid);
//                    intent10.putExtras(bundle);
//                    startActivity(intent10);

                    Intent intent10 = new Intent(InspectionListActivity.this, NutritionalStatusObservedActivity.class);
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
                    intent10.putExtras(bundle_edit);
                    startActivity(intent10);
                }
                if (listview == 11) {
                    finish();
                    startActivity(getIntent());
//                    Intent intent11 = new Intent(InspectionListActivity.this, OtherInspectionActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("kitchen", kitchen);
//                    bundle.putString("adqutspacepse", adqutspacepse);
//                    bundle.putString("electric", electric);
//                    bundle.putString("water", water);
//                    bundle.putString("foodspace", foodspace);
//                    bundle.putString("toilet", toilet);
//                    bundle.putString("awcscode", awcscode);
//                    bundle.putString("awcsname", awcsname);
//                    bundle.putString("dbdistid", dbdistid);
//                    bundle.putString("dbprojectid", dbprojectid);
//                    bundle.putString("dbsectorid", dbsectorid);
//                    bundle.putString("dbcenterid", dbcenterid);
//                    bundle.putString("projectnamedb", projectnamedb);
//                    bundle.putString("districnamedb", districnamedb);
//                    bundle.putString("sectorrnamedb", sectorrnamedb);
//                    bundle.putString("centernamedb", centernamedb);
//                    bundle.putString("insid", insid);
//                    bundle.putString("planid", planid);
//                    bundle.putString("dbid",dbid);
//                    intent11.putExtras(bundle);
//                    startActivity(intent11);

                    Intent intent11 = new Intent(InspectionListActivity.this, OtherInspectionActivity.class);
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
                    intent11.putExtras(bundle_edit);
                    startActivity(intent11);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Inspection");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        Intent intent = new Intent(InspectionListActivity.this, NavigationDrawerActivity.class);
                        startActivity(intent);

                    }
                }
        );
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


    public class InspectionListAdapter extends RecyclerView.Adapter<InspectionListAdapter.ViewHolder> {
        private Context mCtx;
        private List<ListInspaction> listInspactionList;
        private int rowLayout;
        private ItemClickListener clickListener;

        public InspectionListAdapter(Context mCtx, List<ListInspaction> listInspactionList) {
            this.mCtx = mCtx;
            this.listInspactionList = listInspactionList;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_inspaction, parent, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(ViewHolder holder,
                                     int position) {
            ListInspaction listInspactionListadd = listInspactionList.get(position);
            listview = position;
            holder.textViewId.setText(listInspactionListadd.getTitle());
            holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(listInspactionListadd.getImage()));
            String buildingdetails = listInspactionListadd.getBuildingdetails();
          //  System.out.println("buildingdetails"+buildingdetails);
           // Log.e("buildingdetails",buildingdetails);
            String informationoftoilet = listInspactionListadd.getInformationoftoilet();
            String informationofkitchen = listInspactionListadd.getInformationofkitchen();
            String adequatespaceforpse = listInspactionListadd.getAdequatespaceforpse();
            String electricity = listInspactionListadd.getElectricity();
            String drinkingwater = listInspactionListadd.getDrinkingwater();
            String foodstoreddetails = listInspactionListadd.getFoodstoreddetails();
            String conditionofequipmentotherawc = listInspactionListadd.getConditionofequipmentotherawc();
            String shishualoy = listInspactionListadd.getShishualoy();
            String snpserved = listInspactionListadd.getSnpserved();
            String nutritionalstatusobserved = listInspactionListadd.getNutritionalstatusobserved();
            String otherinspection = listInspactionListadd.getOtherinspection();
            String allinspactionid = listInspactionListadd.getAllinspactionid();
            if (listview == 0) {
                if (buildingdetails.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }

            if (listview == 1){
                if (informationoftoilet.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }

            }
            if (listview == 2){
                if (informationofkitchen.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }
            if (listview == 3){
                if (adequatespaceforpse.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }
            if (listview == 4){
                if (electricity.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }
            if (listview == 5){
                if (drinkingwater.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }

            } if (listview == 6){
                if (foodstoreddetails.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }

            }
            if (listview == 7){
                if (conditionofequipmentotherawc.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }
            if (listview == 8){
                if (shishualoy.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }
            if (listview == 9){
                if (snpserved.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }
            if (listview == 10){
                if (nutritionalstatusobserved.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }
            if (listview == 11){
                if (otherinspection.equals("0")) {
                    holder.checkboxbId.setVisibility(View.GONE);
                } else {
                    holder.checkboxbId.setVisibility(View.VISIBLE);
                    holder.checkboxbId.setChecked(true);
                }
            }
        }

        @Override
        public int getItemCount() {
            return listInspactionList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textViewId;
            CheckBox checkboxbId;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                textViewId = itemView.findViewById(R.id.textViewId);
                checkboxbId = itemView.findViewById(R.id.checkboxbId);
            }
        }
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(OtherInspectionActivity.this,NavigationDrawerActivity.class);
//        startActivity(intent);
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
        super.onDestroy();
        System.out.println("Inside onDestroy");
    }
}
