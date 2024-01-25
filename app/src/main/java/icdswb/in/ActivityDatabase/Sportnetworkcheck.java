package icdswb.in.ActivityDatabase;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import icdswb.in.ActivitySetGet.Dbprocess;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.NavigationDrawerActivity;
import icdswb.in.OtherActivity.Consts;
import icdswb.in.SportInspectionActivityOffline;
import icdswb.in.SportTabView;

import static android.content.Context.MODE_PRIVATE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ACDPOCONT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ACDPONAME;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ACDPOPRSNT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ADQUTSPACEPSE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPACATIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ALLINSPECTIONFLAGID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSADRS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSCODE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSLAT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSLATLOCATION;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSLONG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSNAME;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSOPEN;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_AWCSUSERID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_BUILDON;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_BUILDSTRUC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CDPOCONTACT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CDPONAME;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CDPOPRSNT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CENTER;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DBCENTERID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DBDISTID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DBPROJECTID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DBSECTORID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DISTRIC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ELECTRIC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FINALSUBINS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FINALSUBMITSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FINALSUBTABLE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FOODSPACE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_HELPERNM;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_HELPERNO;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_HELPERPRSNT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPACTIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPECTIONID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_KITCHEN;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_LST_INSPCTN_BULD_REP;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_LST_INSPCTN_DRNKWATER_REP;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_LST_INSPCTN_ELECTRIC_REP;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_LST_INSPCTN_FOOD_REP;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_LST_INSPCTN_KITCHEN_REP;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_LST_INSPCTN_PSE_REP;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_LST_INSPCTN_TOILET_REP;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NAMEAWCSDTL;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PLANID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROJECT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SECTOR;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SPORTSINS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SPORTSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SPORTSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SUPVSRNAME;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SUPVSRNO;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SUPVSRPRSNT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_TOILET;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USERIDFLA;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_WATER;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_WORKERNM;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_WORKERNO;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_WORKERPRSNT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_YESNOID;

public class Sportnetworkcheck extends BroadcastReceiver {
    private Context context;
    private DatabaseHelper db;
    String planId;
    String water ="NA";
    String cdponame ="NA";
    String acdpocont ="NA";
    String buildstruc="NA";
    String electric="NA";
    String acdponame="NA";
    String kitchen="NA";
    String cdpocontact="NA";
    String workerno="NA";
    String worker_nm="NA";
    String toilet="NA";
    // String awcslat="NA";
    String supvsrname="NA";
    //String awcsslong="NA";
    String helperno="NA";
    String awcs_adrs="NA";
    String foodspace="NA";
    String helpernm="NA";
    String buildon="NA";
    String adqutspacepse="NA";
    String supvsrno="NA";
    String awcsid="NA";
    // String awcscode="NA";
    String awcsname="NA";
    String lstinspctnbuldrep="NA";
    String lstinspctntoiletrep="NA";
    String lstinspctnkitchenrep="NA";
    String lstinspctnpserep="NA";
    String lstinspctnelectricrep="NA";
    String lstinspctndrnkwaterrep="NA";
    String lstinspctnfoodrep = "NA";
    String awcscode ="NA";
    String awcslat ="NA";
    String awcsslong = "NA";
    String flag = "0";
    String userID;
    String systenDate,curTime;
    String buildingdetails = "0";
    String informationoftoilet= "0";
    String informationofkitchen= "0";
    String adequatespaceforpse= "0";
    String electricity= "0";
    String drinkingwater= "0";
    String foodstoreddetails= "0";
    String conditionofequipmentotherawc= "0";
    String shishualoy= "0";
    String snpserved= "0";
    String nutritionalstatusobserved= "0";
    String otherinspection= "0";
    String allinspactionid= "0";
    String flaggrecord= "3";
    String flaggrecord2= "2";
    String dist,distname,projectid,projectname,sectorid,sectorname,status,insidd,planIdd;
    String STATUS ="1";
    float flat2,flng2;
    String lat = "0";
    String lang ="0";
    String Dbinsid = "00";
    String  flaggrecord1 ="00";
    String bdawcode ="00";
    SharedPreferences spp;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        db = new DatabaseHelper(context);
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Cursor cursor = db.getsportsync();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        SNPSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_SPORTSYNCID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SPORTSINS)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCCODE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TBALE_SDISTRIC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PLANIDSP)),

                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PLANDATE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PLANTIME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERIDPLAN)),

                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SPORTSTATUS))
                        );
                       // Toast.makeText(context, "Network Loop offline sport "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
            Cursor us = db.getLoginData();
            if (us.moveToFirst()) {
                do {
                    userID = us.getString(us.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                    Log.e("useridBb",userID);
                } while (us.moveToNext());
            }

            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());
            SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
            systenDate = df3.format(c.getTime());
            Calendar ccc = Calendar.getInstance();
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            curTime = time.format(ccc.getTime());
        }
    }
    private void SNPSYNC(final int sportsyncid,
                         final String sportins,
                         final String awccode,
                         final String sdistric,final String planidsp,
                         final  String systenDate,final String curTime,final String userIDint,final String sportstatus){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Config.SPORTDATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONArray array =new JSONArray(response);
                            //     Toast.makeText(context, "Network Loop OUT Remove SNP",Toast.LENGTH_SHORT).show();
                            // Log.e("anyfreeinterlastthree1",anyfreeinterlastthree);
                            //removeodIn();
                            //updating the status in sqlite
                            JSONObject obj = new JSONObject(response);
                            Log.e("response",response);
                            db.sportsubmitSyncStatus(sportsyncid, SportInspectionActivityOffline.SPORT_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(SportInspectionActivityOffline.DATA_SAVED_BROADCAST_SPORT));
                            dist = obj.getString("dist");
                            distname = obj.getString("dist_name");
                            awcslat = obj.getString("latitude");
                            awcsslong = obj.getString("longitude");
                            projectid = obj.getString("project_id");
                            projectname = obj.getString("project_name");
                            acdponame = obj.getString("acdpo_name");
                            acdpocont = obj.getString("acdpo_cont");
                            cdponame = obj.getString("cdpo_name");
                            cdpocontact = obj.getString("cdpo_contact");
                            sectorid = obj.getString("sector_id");
                            sectorname = obj.getString("sector_name");
                            supvsrname = obj.getString("supvsr_name");
                            awcscode = obj.getString("awcs_code");
                            worker_nm = obj.getString("worker_nm");
                            workerno = obj.getString("worker_no");
                            helpernm = obj.getString("helper_nm");
                            helperno = obj.getString("helper_no");
                            status = obj.getString("status");
                            buildon = obj.getString("build_on");
                            electric = obj.getString("electric");
                            water = obj.getString("water");
                            foodspace = obj.getString("food_space");
                            lstinspctnbuldrep = obj.getString("lst_inspctn_buld_rep");
                            lstinspctntoiletrep = obj.getString("lst_inspctn_toilet_rep");
                            lstinspctnkitchenrep = obj.getString("lst_inspctn_kitchen_rep");
                            lstinspctnpserep = obj.getString("lst_inspctn_pse_rep");
                            lstinspctnelectricrep = obj.getString("lst_inspctn_electric_rep");
                            lstinspctndrnkwaterrep = obj.getString("lst_inspctn_drnkwater_rep");
                            lstinspctnfoodrep = obj.getString("lst_inspctn_food_rep");
                            buildstruc = obj.getString("build_struc");
                            toilet = obj.getString("toilet");
                            kitchen  = obj.getString("kitchen");
                            adqutspacepse = obj.getString("adqut_space_pse");
                            awcs_adrs = obj.getString("awcs_adrs");
                            supvsrno = obj.getString("supvsr_no");
                            //awcsid = obj.getString("awcs_id");
                            // awcscode = obj.getString("awcs_code");
                            // awcsname = obj.getString("center_name");
                            awcsid = obj.getString("center_id");
                            awcsname = obj.getString("center_name");
                             insidd = obj.getString("ins_id");
                            planIdd = obj.getString("plan_id");
                            Log.e("Checkdata",dist+" "+distname+" "
                                    +awcslat+" "+awcsslong+" "+projectid+" "
                                    +projectname+" "+sectorid+" "+sectorid+" "
                                    +sectorname+" "+""+" "+""+" "+awcscode+" "+status+
                                    water+" "+cdponame+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+
                                    cdpocontact+" "+workerno+" "+worker_nm+" "
                                    +toilet+" "+awcslat+" "+supvsrname+" "+awcsslong+" "+helperno+" "
                                    +awcs_adrs+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+" "+awcsid+" "
                                    +awcsname+" "+lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "
                                    +lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep+" "+insidd+" "+planIdd);
                            if (status.equals("1")){
                                Log.e("sportins",sportins);
//                                SQLiteDatabase a = db.getReadableDatabase();
//                                a.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_DBDISTID + " = '" + dist + "' WHERE " + TABLE_INSPACTIONID + " = " + sportins);
//                                SQLiteDatabase b = db.getReadableDatabase();
//                                b.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_DBPROJECTID + " = '" + projectid + "' WHERE " + TABLE_INSPACTIONID + " = " + sportins);
//                                SQLiteDatabase c = db.getReadableDatabase();
//                                c.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_DBSECTORID + " = '" + sectorid + "' WHERE " + TABLE_INSPACTIONID + " = " + sportins);
//                                SQLiteDatabase d = db.getReadableDatabase();
//                                d.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_DBCENTERID + " = '" + awcsid + "' WHERE " + TABLE_INSPACTIONID + " = " + sportins);
//                                SQLiteDatabase e = db.getReadableDatabase();
//                                e.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_PROJECT + " = '" + distname + "' WHERE " + TABLE_INSPACTIONID + " = " + sportins);
//                                SQLiteDatabase f = db.getReadableDatabase();
//                                f.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_DISTRIC + " = '" + projectname + "' WHERE " + TABLE_INSPACTIONID + " = " + sportins);
//                                SQLiteDatabase g = db.getReadableDatabase();
//                                g.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_SECTOR + " = '" + sectorname + "' WHERE " + TABLE_INSPACTIONID + " = " + sportins);
//                                SQLiteDatabase h = db.getReadableDatabase();
//                                h.execSQL("UPDATE " + TABLE_PROCESS + " SET " + TABLE_CENTER + " = '" + awcsname + "' WHERE " + TABLE_INSPACTIONID + " = " + sportins);
//                                //////////////////////////////////////////
//                                SQLiteDatabase a1 = db.getReadableDatabase();
//                                a1.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_WATER + " = '" + water + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase b1 = db.getReadableDatabase();
//                                b1.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_CDPONAME + " = '" + cdponame + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase c1 = db.getReadableDatabase();
//                                c1.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_ACDPOCONT + " = '" + acdpocont + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase d1 = db.getReadableDatabase();
//                                d1.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_BUILDSTRUC + " = '" + buildstruc + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase e1 = db.getReadableDatabase();
//                                e1.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_ELECTRIC + " = '" + electric + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase f1 = db.getReadableDatabase();
//                                f1.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_ACDPONAME + " = '" + acdponame + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase g1 = db.getReadableDatabase();
//                                g1.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_KITCHEN + " = '" + kitchen + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h1 = db.getReadableDatabase();
//                                h1.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_CDPOCONTACT + " = '" + cdpocontact + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h11 = db.getReadableDatabase();
//                                h11.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_WORKERNO + " = '" + workerno + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h12 = db.getReadableDatabase();
//                                h12.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_WORKERNM + " = '" + worker_nm + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h13 = db.getReadableDatabase();
//                                h13.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_TOILET + " = '" + toilet + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h14 = db.getReadableDatabase();
//                                h14.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_AWCSLAT + " = '" + awcslat + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h15 = db.getReadableDatabase();
//                                h15.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_SUPVSRNAME + " = '" + supvsrname + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h16 = db.getReadableDatabase();
//                                h16.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_AWCSLONG + " = '" + awcsslong + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h17 = db.getReadableDatabase();
//                                h17.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_HELPERNO + " = '" + helperno + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h18 = db.getReadableDatabase();
//                                h18.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_AWCSADRS + " = '" + awcs_adrs + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h19 = db.getReadableDatabase();
//                                h19.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_FOODSPACE + " = '" + foodspace + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h20 = db.getReadableDatabase();
//                                h20.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_HELPERNM + " = '" + helpernm + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h21 = db.getReadableDatabase();
//                                h21.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_BUILDON + " = '" + buildon + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h22 = db.getReadableDatabase();
//                                h22.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_ADQUTSPACEPSE + " = '" + adqutspacepse + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h23 = db.getReadableDatabase();
//                                h23.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_SUPVSRNO + " = '" + supvsrno + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h24 = db.getReadableDatabase();
//                                h24.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_AWCSID + " = '" + awcsid + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h25 = db.getReadableDatabase();
//                                h25.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_AWCSCODE + " = '" + awcscode + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h26 = db.getReadableDatabase();
//                                h26.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_AWCSNAME + " = '" + awcsname + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h27 = db.getReadableDatabase();
//                                h27.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_PLANID + " = '" + planIdd + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h28 = db.getReadableDatabase();
//                                h28.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_LST_INSPCTN_BULD_REP + " = '" + lstinspctnbuldrep + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h29 = db.getReadableDatabase();
//                                h29.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_LST_INSPCTN_TOILET_REP + " = '" + lstinspctntoiletrep + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h30 = db.getReadableDatabase();
//                                h30.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_LST_INSPCTN_KITCHEN_REP + " = '" + lstinspctnkitchenrep + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h31 = db.getReadableDatabase();
//                                h31.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_LST_INSPCTN_PSE_REP + " = '" + lstinspctnpserep + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h32 = db.getReadableDatabase();
//                                h32.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_LST_INSPCTN_ELECTRIC_REP + " = '" + lstinspctnelectricrep + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h33 = db.getReadableDatabase();
//                                h33.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_LST_INSPCTN_DRNKWATER_REP + " = '" + lstinspctndrnkwaterrep + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);
//                                SQLiteDatabase h34 = db.getReadableDatabase();
//                                h34.execSQL("UPDATE " + TABLE_NAMEAWCSDTL + " SET " + TABLE_LST_INSPCTN_FOOD_REP + " = '" + lstinspctnfoodrep + "' WHERE " + TABLE_INSPECTIONID + " = " + sportins);

//                                String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_INSPACTIONID + "=" +sportins;
//                                db = new DatabaseHelper(context);
//                                SQLiteDatabase dbb = db.getReadableDatabase();
//                                Cursor  cursor = dbb.rawQuery(query,null);
//                                if (cursor.moveToFirst()){
//                                    do {
//                                        lat =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLATLOCATION));
//                                        lang = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLONGLOCATION));
//
//                                    }
//                                    while (cursor.moveToNext());
//                                }
                                db = new DatabaseHelper(context);
                                Cursor cc = db.getReadableDatabase().
                                        rawQuery("select * from awcsprocess where awcscodeid = ? and flaggrecord = ?", new String[]{awccode,flaggrecord});
                                if (cc.moveToFirst()) {
                                    do {
                                        Dbinsid =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_INSPACTIONID));
                                        bdawcode = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_AWCSCODEID));
                                        flaggrecord1  =  cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_FLAGGRECORD));

                                    }while (cc.moveToNext());
                                }
                                Log.e("EqaldataplanDB",Dbinsid+" "+insidd+" "+flaggrecord1+" "+bdawcode);
//                                if (Dbinsid.equals(insidd) && flaggrecord.equals(flaggrecord1)){
//
//                                }
                                if (bdawcode.equals(awccode) && flaggrecord.equals(flaggrecord1)){

                                }
                                else {
                                    db.awcsprocessInsert(dist,
                                            projectid,
                                            sectorid,
                                            awcsid,
                                            projectname,
                                            distname,
                                            sectorname,
                                            awcsname,
                                            systenDate,
                                            userID,
                                            flag,
                                            awcslat,
                                            awcsslong,
                                            insidd,
                                            awcscode,
                                            curTime,flaggrecord);
                                    db.AllinspactionInsert(buildingdetails,
                                            informationoftoilet,
                                            informationofkitchen,
                                            adequatespaceforpse,
                                            electricity,
                                            drinkingwater,
                                            foodstoreddetails,
                                            conditionofequipmentotherawc,
                                            shishualoy,
                                            snpserved,
                                            nutritionalstatusobserved,
                                            otherinspection,
                                            insidd,
                                            userID);
                                    db.awcsdtlInsert(water,
                                            cdponame,
                                            acdpocont,
                                            buildstruc,
                                            electric,
                                            acdponame,
                                            kitchen,
                                            cdpocontact,
                                            workerno,
                                            worker_nm,
                                            toilet,
                                            awcslat,
                                            supvsrname,
                                            awcsslong,
                                            helperno,
                                            awcs_adrs,
                                            foodspace,
                                            helpernm,
                                            buildon,
                                            adqutspacepse,
                                            supvsrno,
                                            awcsid,
                                            awcscode,
                                            awcsname,
                                            planIdd,
                                            lstinspctnbuldrep,
                                            lstinspctntoiletrep,
                                            lstinspctnkitchenrep,
                                            lstinspctnpserep,
                                            lstinspctnelectricrep,
                                            lstinspctndrnkwaterrep,
                                            lstinspctnfoodrep,
                                            userID,
                                            flag,
                                            insidd);
                                    Log.e("Checkdata1",dist+" "+distname+" "
                                            +awcslat+" "+awcsslong+" "+projectid+" "
                                            +projectname+" "+sectorid+" "+sectorid+" "
                                            +sectorname+" "+""+" "+""+" "+awcscode+" "+status+
                                            water+" "+cdponame+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+
                                            cdpocontact+" "+workerno+" "+worker_nm+" "
                                            +toilet+" "+awcslat+" "+supvsrname+" "+awcsslong+" "+helperno+" "
                                            +awcs_adrs+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+" "+awcsid+" "
                                            +awcsname+" "+lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "
                                            +lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep+" "+insidd+" "+planIdd);
                                }
                                db = new DatabaseHelper(context);
                                Cursor c = db.getReadableDatabase().
                                        rawQuery("select * from awcsprocess where inspactionid = ? and flaggrecord = ?", new String[]{sportins,flaggrecord2});
                                if (c.moveToFirst()) {
                                    do {
                                        lat =  c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSLATLOCATION));
                                        lang = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSLONGLOCATION));

                                    }while (c.moveToNext());
                                }
                                Log.e("Checkdata",lat+" "+lang+" "+sportins);
                                LatLong(lat,lang,awcslat,awcsslong,planidsp);
                                //////////////////////delete record/////
                            }
                          //  FINALSUBMITSYNC(sportins,sportstatus,userIDint);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dist_id", sdistric);
                params.put("awcs_code", awccode);
                params.put("plan_id", planidsp);
                params.put("ins_id", sportins);
                params.put("plan_date",systenDate);
                params.put("plan_time",curTime);
                params.put("user_id",userIDint);
                Log.e("Checkdata"," "+sdistric+" "+awccode+" "+planidsp+" "+sportins+" "+systenDate+" "+curTime+" "+userIDint);
                return params;
            }
        };
        //   VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
//    public void FINALSUBMITSYNC(String insidsync,String sportstatus1,String userIDint){
//        Log.e("sportins",insidsync+" "+sportstatus1+" "+userIDint);
//        String query = "DELETE  FROM " + TABLE_SPORTSYNC + " where " + TABLE_SPORTSINS + "=" +insidsync+ " and " +TABLE_SPORTSTATUS+ "=" +STATUS;
//        SQLiteDatabase sqlDB = db.getReadableDatabase();
//        Cursor  c = sqlDB.rawQuery(query,null);
//        String sportsync = "";
//        String sportsyncid = "";
//        String sportins = "";
//        String awccode = "";
//        String sdistric = "";
//        String planidsp = "";
//        String sportstatus = "";
//        int status = 0;
//        if (c.moveToFirst()) {
//            while (!c.isAfterLast()) {
//                sportsync = c.getString(c.getColumnIndex("sportsync"));
//                sportsyncid = c.getString(c.getColumnIndex("sportsyncid"));
//                sportins = c.getString(c.getColumnIndex("sportins"));
//                awccode = c.getString(c.getColumnIndex("awccode"));
//                sdistric = c.getString(c.getColumnIndex("sdistric"));
//                planidsp = c.getString(c.getColumnIndex("planidsp"));
//                sportstatus = c.getString(c.getColumnIndex("sportstatus"));
//                if (!sportsync.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + sportsync + "'");
//                }
//                if (!sportsyncid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + sportsyncid + "'");
//                }
//                if (!sportins.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + sportins + "'");
//                }
//                if (!awccode.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awccode + "'");
//                }
//                if (!sdistric.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + sdistric + "'");
//                }
//                if (!planidsp.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + planidsp + "'");
//                }
//                if (!sportstatus.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + sportstatus + "'");
//                }
//                c.moveToNext();
//            }
//            c.close();
//        }
//        awcsdtls(insidsync,userIDint);
//        processDelete_Inspection(insidsync,userIDint);
//        listcheckboxDelete(insidsync,userIDint);
//    }
    public void LatLong(final String lat,final String lang,final String awcslat,final String awcsslong,final String planidsp){
        Log.e("LatLongFind",lat+" "+lang+" "+awcslat+" "+awcsslong);
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
        flat2 = Float.valueOf(awcslat.trim()).floatValue();
        flng2 = Float.valueOf(awcsslong.trim()).floatValue();
        distance(lat1, lng1, flat2, flng2, planidsp);
    }
    public float distance(float lat1, float lng1, float flat2, float flng2,String planidsp) {
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
            Log.e("Checkdata",lat1+" "+lng1+" "+flat2+" "+flng2+" "+" "+"OK");
            String fflag = "1";
            checklatlonh(planidsp,fflag);
        }
        else {
            Log.e("Checkdata",lat1+" "+lng1+" "+flat2+" "+flng2+" "+" "+"No");
            String flagg = "0";
            checklatlonh(planidsp,flagg);

        }
        return (float) (Radius * c);
        //  return dist;
    }
    public void checklatlonh(final String planidsp,final String flag){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Config.CHECKDISTANCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.e("Checkdata",response);
                            String status = obj.getString("status");
                            if (status.equals("1")){
                                //deleterecord();
                                Intent intent = new Intent(context, SportTabView.class);
                               // Bundle bundle_edit  =   new Bundle();
                               // bundle_edit.putString("water","1");
                               // intent.putExtras(bundle_edit);
                                context.startActivity(intent);
                            }
                            else {
//                                spp = context.getSharedPreferences(Consts.CHECKVALE, MODE_PRIVATE);
//                                SharedPreferences.Editor edit14 = spp.edit();
//                                edit14.putString("CHECKVALE", "1");
//                                edit14.commit();
                                Intent intent = new Intent(context, SportTabView.class);
                              //  Bundle bundle_edit  =   new Bundle();
                             //   bundle_edit.putString("value","1");
                             //   intent.putExtras(bundle_edit);
                                context.startActivity(intent);
                               // deleterecord();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("plan_id", planidsp);
                params.put("flag", flag);
                Log.e("Checkdata",planidsp+" "+flag);
                return params;
            }
        };
        //   VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    public void deleterecord(){
        db = new DatabaseHelper(context);
        Cursor ccc = db.getReadableDatabase().
                rawQuery("select * from sportsync where userplan = ? and sportstatus = ?", new String[]{userID,STATUS});
        if (ccc.moveToFirst()) {
            do {
                String sportins = ccc.getString(ccc.getColumnIndex(DatabaseHelper.TABLE_SPORTSINS));
                STATUS =  ccc.getString(ccc.getColumnIndex(DatabaseHelper.TABLE_SPORTSTATUS));
                Log.e("userid",userID+" "+sportins);
                //awcsdtls(insidsync,userID);
                SQLiteDatabase db2 = db.getReadableDatabase();
                db2.execSQL("delete from "+TABLE_NAMEAWCSDTL+" where allinspactionid='"+sportins+"'");
                //processDelete_Inspection(insidsync,userID,flaggrecord);
                db2.execSQL("delete from "+TABLE_PROCESS+" where flaggrecord='"+flaggrecord2+"'");
                // listcheckboxDelete(insidsync,userID);
                db2.execSQL("delete from "+TABLE_ALLINSPECTIONFLAG+" where allinspactionid='"+sportins+"'");
              //  db2.execSQL("delete from "+TABLE_SPORTSYNC+" where sportstatus='"+STATUS+"'");
            }while (ccc.moveToNext());
        }
    }

//    public void awcsdtls(String inspctnid,String Userid){
//        Log.e("AWCSDELETE",inspctnid+" "+" "+Userid);
//        String query = "DELETE  FROM " + TABLE_NAMEAWCSDTL + " where " + TABLE_INSPECTIONID + "=" +inspctnid+ " and " +TABLE_AWCSUSERID+ "=" +Userid;
//        SQLiteDatabase sqlDB = db.getReadableDatabase();
//        Cursor  c = sqlDB.rawQuery(query,null);
//        String id = "";
//        String water = "";
//        String cdponame = "";
//        String acdpocont = "";
//        String buildstruc = "";
//        String electric = "";
//        String acdponame = "";
//        String kitchen = "";
//        String cdpocontact = "";
//        String workerno = "";
//        String workernm = "";
//        String toilet = "";
//        String awcslat = "";
//        String supvsrname = "";
//        String awcsslong = "";
//        String helperno = "";
//        String awcsadrs = "";
//        String foodspace = "";
//        String helpernm = "";
//        String buildon ="";
//        String adqutspacepse ="";
//        String supvsrno = "";
//        String awcsid ="";
//        String awcscode ="";
//        String awcsname ="";
//        String planid ="";
//        String lstinspctnbuldrep ="";
//        String lstinspctntoiletrep ="";
//        String lstinspctnkitchenrep ="";
//        String lstinspctnpserep ="";
//        String lstinspctnelectricrep ="";
//        String lstinspctndrnkwaterrep ="";
//        String lstinspctnfoodrep = "";
//        String awcsuserid = "";
//        String awcsflag = "";
//        String allinspactionid ="";
//        int status = 0;
//        if (c.moveToFirst()) {
//            while (!c.isAfterLast()) {
//                id = c.getString(c.getColumnIndex("id"));
//                water = c.getString(c.getColumnIndex("water"));
//                cdponame = c.getString(c.getColumnIndex("cdponame"));
//                acdpocont = c.getString(c.getColumnIndex("acdpocont"));
//                buildstruc = c.getString(c.getColumnIndex("buildstruc"));
//                electric = c.getString(c.getColumnIndex("electric"));
//                acdponame = c.getString(c.getColumnIndex("acdponame"));
//                kitchen = c.getString(c.getColumnIndex("kitchen"));
//                cdpocontact = c.getString(c.getColumnIndex("cdpocontact"));
//                workerno = c.getString(c.getColumnIndex("workerno"));
//                workernm = c.getString(c.getColumnIndex("workernm"));
//                toilet = c.getString(c.getColumnIndex("toilet"));
//                awcslat = c.getString(c.getColumnIndex("awcslat"));
//                supvsrname = c.getString(c.getColumnIndex("supvsrname"));
//                awcsslong = c.getString(c.getColumnIndex("awcsslong"));
//                helperno = c.getString(c.getColumnIndex("helperno"));
//                awcsadrs = c.getString(c.getColumnIndex("awcsadrs"));
//                foodspace = c.getString(c.getColumnIndex("foodspace"));
//                helpernm = c.getString(c.getColumnIndex("helpernm"));
//                buildon = c.getString(c.getColumnIndex("buildon"));
//                adqutspacepse = c.getString(c.getColumnIndex("adqutspacepse"));
//                supvsrno = c.getString(c.getColumnIndex("supvsrno"));
//                awcsid = c.getString(c.getColumnIndex("awcsid"));
//                awcscode = c.getString(c.getColumnIndex("awcscode"));
//                awcsname = c.getString(c.getColumnIndex("awcsname"));
//                planid = c.getString(c.getColumnIndex("planid"));
//                lstinspctnbuldrep = c.getString(c.getColumnIndex("lstinspctnbuldrep"));
//                lstinspctntoiletrep = c.getString(c.getColumnIndex("lstinspctntoiletrep"));
//                lstinspctnkitchenrep = c.getString(c.getColumnIndex("lstinspctnkitchenrep"));
//                lstinspctnpserep = c.getString(c.getColumnIndex("lstinspctnpserep"));
//                lstinspctnelectricrep = c.getString(c.getColumnIndex("lstinspctnelectricrep"));
//                lstinspctndrnkwaterrep = c.getString(c.getColumnIndex("lstinspctndrnkwaterrep"));
//                lstinspctnfoodrep = c.getString(c.getColumnIndex("lstinspctnfoodrep"));
//                awcsuserid = c.getString(c.getColumnIndex("awcsuserid"));
//                awcsflag = c.getString(c.getColumnIndex("awcsflag"));
//                allinspactionid = c.getString(c.getColumnIndex("allinspactionid"));
//                if (!id.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + id + "'");
//                }
//                if (!water.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + water + "'");
//                }
//                if (!cdponame.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + cdponame + "'");
//                }
//                if (!acdpocont.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + acdpocont + "'");
//                }
//                if (!buildstruc.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + buildstruc + "'");
//                }
//                if (!electric.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + electric + "'");
//                }
//                if (!acdponame.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + acdponame + "'");
//                }
//                if (!kitchen.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + kitchen + "'");
//                }
//                if (!cdpocontact.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + cdpocontact + "'");
//                }
//                if (!workerno.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + workerno + "'");
//                }
//                if (!workernm.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + workernm + "'");
//                }
//                if (!toilet.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + toilet + "'");
//                }
//                if (!awcslat.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcslat + "'");
//                }
//                if (!supvsrname.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + supvsrname + "'");
//                }
//                if (!awcsslong.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcsslong + "'");
//                }
//                if (!helperno.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + helperno + "'");
//                }
//                if (!awcsadrs.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcsadrs + "'");
//                }
//                if (!foodspace.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + foodspace + "'");
//                }
//                if (!helpernm.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + helpernm + "'");
//                }
//                if (!buildon.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + buildon + "'");
//                }
//                if (!adqutspacepse.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + adqutspacepse + "'");
//                }
//                if (!supvsrno.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + supvsrno + "'");
//                }
//                if (!awcsid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcsid + "'");
//                    Log.e("AWCS",awcsid);
//                }
//                if (!awcscode.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcscode + "'");
//                }
//                if (!awcsname.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcsname + "'");
//                }
//                if (!planid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + planid + "'");
//                }
//                if (!lstinspctnbuldrep.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + lstinspctnbuldrep + "'");
//                }
//                if (!lstinspctntoiletrep.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + lstinspctntoiletrep + "'");
//                }
//                if (!lstinspctnkitchenrep.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + lstinspctnkitchenrep + "'");
//                }
//                if (!lstinspctnpserep.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + lstinspctnpserep + "'");
//                }
//                if (!lstinspctnelectricrep.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + lstinspctnelectricrep + "'");
//                }
//                if (!lstinspctndrnkwaterrep.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + lstinspctndrnkwaterrep + "'");
//                }
//                if (!lstinspctnfoodrep.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + lstinspctnfoodrep + "'");
//                }
//                if (!awcsuserid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcsuserid + "'");
//                }
//                if (!awcsflag.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcsflag + "'");
//                }
//                if (!allinspactionid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + allinspactionid + "'");
//                }
//                c.moveToNext();
//            }
//            c.close();
//
//        }
//    }
//    public void processDelete_Inspection(String insid,String Userid){
//        Log.e("PROCESSDELETE",insid+" "+Userid);
//        String query = "DELETE  FROM " + TABLE_PROCESS + " where " + TABLE_INSPACTIONID + "=" +insid+ " and " +TABLE_USERID+ "=" +Userid;
//        SQLiteDatabase sqlDB = db.getReadableDatabase();
//        Cursor  c = sqlDB.rawQuery(query,null);
//        String idprocess = "";
//        String dbdistid = "";
//        String dbprojectid = "";
//        String dbsectorid = "";
//        String dbcenterid = "";
//        String districnamedb = "";
//        String projectnamedb = "";
//        String sectorrnamedb = "";
//        String centernamedb = "";
//        String currentdate = "";
//        String userid = "";
//        String flag = "";
//        String awcslatlocation = "";
//        String awcsslonglocation = "";
//        String inspactionid = "";
//        String awcscodeid = "";
//        String awcstime = "";
//        String flaggrecord ="";
//        int status = 0;
//        if (c.moveToFirst()) {
//            while (!c.isAfterLast()) {
//                idprocess = c.getString(c.getColumnIndex("idprocess"));
//                dbdistid = c.getString(c.getColumnIndex("dbdistid"));
//                dbprojectid = c.getString(c.getColumnIndex("dbprojectid"));
//                dbsectorid = c.getString(c.getColumnIndex("dbsectorid"));
//                dbcenterid = c.getString(c.getColumnIndex("dbcenterid"));
//                districnamedb = c.getString(c.getColumnIndex("districnamedb"));
//                projectnamedb = c.getString(c.getColumnIndex("projectnamedb"));
//                sectorrnamedb = c.getString(c.getColumnIndex("sectorrnamedb"));
//                centernamedb = c.getString(c.getColumnIndex("centernamedb"));
//                currentdate = c.getString(c.getColumnIndex("currentdate"));
//                userid = c.getString(c.getColumnIndex("userid"));
//                flag = c.getString(c.getColumnIndex("flag"));
//                awcslatlocation = c.getString(c.getColumnIndex("awcslatlocation"));
//                awcsslonglocation = c.getString(c.getColumnIndex("awcsslonglocation"));
//                inspactionid = c.getString(c.getColumnIndex("inspactionid"));
//                awcscodeid = c.getString(c.getColumnIndex("awcscodeid"));
//                awcstime = c.getString(c.getColumnIndex("awcstime"));
//                flaggrecord = c.getString(c.getColumnIndex("flaggrecord"));
//                if (!idprocess.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + idprocess + "'");
//                }
//                if (!dbdistid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + dbdistid + "'");
//                }
//                if (!dbprojectid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + dbprojectid + "'");
//                }
//                if (!dbsectorid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + dbsectorid + "'");
//                }
//                if (!dbcenterid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + dbcenterid + "'");
//                }
//                if (!districnamedb.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + districnamedb + "'");
//                }
//                if (!projectnamedb.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + projectnamedb + "'");
//                }
//                if (!sectorrnamedb.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + sectorrnamedb + "'");
//                }
//                if (!centernamedb.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + centernamedb + "'");
//                }
//                if (!currentdate.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + currentdate + "'");
//                }
//                if (!userid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + userid + "'");
//                }
//                if (!flag.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + flag + "'");
//                }
//                if (!awcslatlocation.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcslatlocation + "'");
//                }
//                if (!awcsslonglocation.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcsslonglocation + "'");
//                }
//                if (!inspactionid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + inspactionid + "'");
//                }
//                if (!awcscodeid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcscodeid + "'");
//                }
//                if (!awcstime.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcstime + "'");
//                }
//                if (!flaggrecord.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + flaggrecord + "'");
//                }
//                c.moveToNext();
//            }
//            c.close();
//
//        }
//    }
//    public void listcheckboxDelete(String inspctnid,String Userid){
//        Log.e("FLAGDELETE",inspctnid+" "+Userid);
//        String query = "DELETE  FROM " + TABLE_ALLINSPECTIONFLAG + " where " + TABLE_ALLINSPACATIONID + "=" +inspctnid+ " and " +TABLE_USERIDFLA+ "=" +Userid;
//        SQLiteDatabase sqlDB = db.getReadableDatabase();
//        Cursor  c = sqlDB.rawQuery(query,null);
//        String insflagid = "";
//        String buildingdetails = "";
//        String informationoftoilet = "";
//        String informationofkitchen = "";
//        String adequatespaceforpse = "";
//        String electricity = "";
//        String drinkingwater = "";
//        String foodstoreddetails = "";
//        String conditionofequipmentotherawc = "";
//        String shishualoy = "";
//        String snpserved = "";
//        String nutritionalstatusobserved = "";
//        String otherinspection = "";
//        String allinspactionid = "";
//        String useridflag = "";
//        if (c.moveToFirst()) {
//            while (!c.isAfterLast()) {
//                insflagid = c.getString(c.getColumnIndex("insflagid"));
//                buildingdetails = c.getString(c.getColumnIndex("buildingdetails"));
//                informationoftoilet = c.getString(c.getColumnIndex("informationoftoilet"));
//                informationofkitchen = c.getString(c.getColumnIndex("informationofkitchen"));
//                adequatespaceforpse = c.getString(c.getColumnIndex("adequatespaceforpse"));
//                electricity = c.getString(c.getColumnIndex("electricity"));
//                drinkingwater = c.getString(c.getColumnIndex("drinkingwater"));
//                foodstoreddetails = c.getString(c.getColumnIndex("foodstoreddetails"));
//                conditionofequipmentotherawc = c.getString(c.getColumnIndex("conditionofequipmentotherawc"));
//                shishualoy = c.getString(c.getColumnIndex("shishualoy"));
//                snpserved = c.getString(c.getColumnIndex("snpserved"));
//                nutritionalstatusobserved = c.getString(c.getColumnIndex("nutritionalstatusobserved"));
//                otherinspection = c.getString(c.getColumnIndex("otherinspection"));
//                allinspactionid = c.getString(c.getColumnIndex("allinspactionid"));
//                useridflag = c.getString(c.getColumnIndex("useridflag"));
//                if (!insflagid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + insflagid + "'");
//                }
//                if (!buildingdetails.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + buildingdetails + "'");
//                }
//                if (!informationoftoilet.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + informationoftoilet + "'");
//                }
//                if (!informationofkitchen.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + informationofkitchen + "'");
//                }
//                if (!adequatespaceforpse.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + adequatespaceforpse + "'");
//                }
//                if (!electricity.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + electricity + "'");
//                }
//                if (!drinkingwater.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + drinkingwater + "'");
//                }
//                if (!foodstoreddetails.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + foodstoreddetails + "'");
//                }
//                if (!conditionofequipmentotherawc.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + conditionofequipmentotherawc + "'");
//                }
//                if (!shishualoy.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + shishualoy + "'");
//                }
////                if (!userid.equals("android_metadata")) {
////                    sqlDB.execSQL("DROP TABLE '" + userid + "'");
////                }
//                if (!snpserved.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + snpserved + "'");
//                }
//                if (!nutritionalstatusobserved.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + nutritionalstatusobserved + "'");
//                }
//                if (!otherinspection.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + otherinspection + "'");
//                }
//                if (!allinspactionid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + allinspactionid + "'");
//                }
//                if (!useridflag.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + useridflag + "'");
//                }
//                c.moveToNext();
//            }
//            c.close();
//        }
//    }
}