package icdswb.in.ActivityDatabase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.util.HashMap;
import java.util.Map;

import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.InformationToiletActivity;
import icdswb.in.ProcedActivityy;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_UPDATECENTREPERSNDTL;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_UPDATECENTREPERSNDTLAWCSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_UPDATECENTREPERSNDTLSTATUS;

public class InspactioninsertNetworkercheker extends BroadcastReceiver {
    private Context context;
    private DatabaseHelper db;
    String UPDATECENTREPERSNDTLSTATUS ="1";
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        db = new DatabaseHelper(context);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //if there is a network
        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Cursor cursor = db.getUnsyncedINSPACTIONDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        INSPACTIONSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLDBDISTID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLDBPROJECTID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLCDPONAME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLCDPONUMBER)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLACDPONAME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLACDPOCONTRACT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLDBSECTORID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLSUPERVISORNAME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLSUPERVISORNO)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLAWCSID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLWORKERNAME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLWORKERNO)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLHELPERNAME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLHELPERNO)),

                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_UPDATECENTREPERSNDTLSTATUS))


                        );
                      //  Toast.makeText(context, "UPDATECENRER "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void INSPACTIONSYNC(final int updatecentrepersndtlid, final String updatecentrepersndtldbdistid, final String updatecentrepersndtldbprojectid, final String updatecentrepersndtlcdponame, final String updatecentrepersndtlcdponumber, final String updatecentrepersndtlacdponame, final String updatecentrepersndtlacdpocontract,final String updatecentrepersndtldbsectorid,final String updatecentrepersndtlsupervisorname,final String updatecentrepersndtlsupervisorno,final String updatecentrepersndtlawcsid,final String updatecentrepersndtlworkername,final String updatecentrepersndtlworkerno,final String updatecentrepersndtlhelpername,final String updatecentrepersndtlhelperno,final String  updatecentrepersndtlstatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.UPDATECENRER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                            //Toast.makeText(context, " Remove UPDATECENRER",Toast.LENGTH_SHORT).show();
                            // removeodIn(updatecentrepersndtlawcsid);
                            removeodIn();
                            //updating the status in sqlite
                           db.updateINSPACTIONSyncStatus(updatecentrepersndtlid, ProcedActivityy.UPDATECENTREPERSNDTL_SYNCED_WITH_SERVER);
                           context.sendBroadcast(new Intent(ProcedActivityy.DATA_SAVED_BROADCAST_UPDATECENTREPERSNDTL));

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
                params.put("dist_id", updatecentrepersndtldbdistid);
                params.put("project_id", updatecentrepersndtldbprojectid);
                params.put("cdpo_nm", updatecentrepersndtlcdponame);
                params.put("cdpo_cont",updatecentrepersndtlcdponumber);
                params.put("acdpo_nm", updatecentrepersndtlacdponame);
                params.put("acdpo_cont", updatecentrepersndtlacdpocontract);
                params.put("sector_id", updatecentrepersndtldbsectorid);
                params.put("supervisor_name", updatecentrepersndtlsupervisorname);
                params.put("supvsr_no", updatecentrepersndtlsupervisorno);
                params.put("awcs_id",updatecentrepersndtlawcsid);
                params.put("angan_wrkr",updatecentrepersndtlworkername);
                params.put("worker_no", updatecentrepersndtlworkerno);
                params.put("angan_hlpr", updatecentrepersndtlhelpername);
                params.put("hlpr_no", updatecentrepersndtlhelperno);
                Log.e("UPDATECENRER",updatecentrepersndtldbdistid+" "+updatecentrepersndtldbprojectid+" "+updatecentrepersndtlcdponame+" "+updatecentrepersndtlcdponumber+" "+updatecentrepersndtlacdponame+" "+updatecentrepersndtlacdpocontract+" "+updatecentrepersndtldbsectorid+" "+updatecentrepersndtlsupervisorname+" "+updatecentrepersndtlsupervisorno+" "+updatecentrepersndtlawcsid+" "+updatecentrepersndtlworkername+" "+updatecentrepersndtlworkerno+" "+updatecentrepersndtlhelpername+" "+updatecentrepersndtlhelperno);
                return params;
            }
        };
        //   VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

    public void removeodIn(){
//        Toast.makeText(context,"Delete Successfully",Toast.LENGTH_SHORT).show();
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        Cursor c = sqlDB.rawQuery("DELETE FROM updatecentrepersndtl", null);
//     //   Log.e("DELETEUPDATECENRER",updatecentrepersndtlawcsid);

//        String query = "DELETE  FROM " + TABLE_UPDATECENTREPERSNDTL + " where " + TABLE_UPDATECENTREPERSNDTLAWCSID + "=" +updatecentrepersndtlawcsid+ " and " +TABLE_UPDATECENTREPERSNDTLSTATUS+ "=" +UPDATECENTREPERSNDTLSTATUS;
//        Log.e("DELETEUPDATECENRER",updatecentrepersndtlawcsid);
//        SQLiteDatabase sqlDB = db.getReadableDatabase();
//        Cursor  c = sqlDB.rawQuery(query,null);
        String updatecentrepersndtlid="";
        String updatecentrepersndtldbdistid= "";
        String updatecentrepersndtldbprojectid ="";
        String updatecentrepersndtlcdponame = "";
        String updatecentrepersndtlcdponumber ="";
        String updatecentrepersndtlacdponame="";
        String updatecentrepersndtlacdpocontract ="";
        String updatecentrepersndtldbsectorid ="";
        String updatecentrepersndtlsupervisorname ="";
        String updatecentrepersndtlsupervisorno = "";
        String  updatecentrepersndtlawcsidd= "";
        String updatecentrepersndtlworkername= "";
        String updatecentrepersndtlworkerno ="";
        String updatecentrepersndtlhelpername = "";
        String updatecentrepersndtlhelperno ="";
        String updatecentrepersndtlstatus ="";
        int status = 0;
        if (c.moveToFirst()) {

            while ( !c.isAfterLast() ) {
                updatecentrepersndtlid = c.getString( c.getColumnIndex("updatecentrepersndtlid"));
                updatecentrepersndtldbdistid = c.getString( c.getColumnIndex("updatecentrepersndtldbdistid"));
                updatecentrepersndtldbprojectid = c.getString( c.getColumnIndex("updatecentrepersndtldbprojectid"));
                updatecentrepersndtlcdponame = c.getString( c.getColumnIndex("updatecentrepersndtlcdponame"));
                updatecentrepersndtlcdponumber = c.getString( c.getColumnIndex("updatecentrepersndtlcdponumber"));
                updatecentrepersndtlacdponame = c.getString( c.getColumnIndex("updatecentrepersndtlacdponame"));
                updatecentrepersndtlacdpocontract = c.getString( c.getColumnIndex("updatecentrepersndtlacdpocontract"));
                updatecentrepersndtldbsectorid = c.getString( c.getColumnIndex("updatecentrepersndtldbsectorid"));
                updatecentrepersndtlsupervisorname = c.getString( c.getColumnIndex("updatecentrepersndtlsupervisorname"));
                updatecentrepersndtlsupervisorno = c.getString( c.getColumnIndex("updatecentrepersndtlsupervisorno"));
                updatecentrepersndtlawcsidd = c.getString( c.getColumnIndex("updatecentrepersndtlawcsid"));
                updatecentrepersndtlworkername = c.getString( c.getColumnIndex("updatecentrepersndtlworkername"));
                updatecentrepersndtlworkerno = c.getString(c.getColumnIndex("updatecentrepersndtlworkerno"));
                updatecentrepersndtlhelpername = c.getString(c.getColumnIndex("updatecentrepersndtlhelpername"));
                updatecentrepersndtlhelperno = c.getString(c.getColumnIndex("updatecentrepersndtlhelperno"));
                updatecentrepersndtlstatus = c.getString(c.getColumnIndex("updatecentrepersndtlstatus"));
                if(!updatecentrepersndtlid.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlid+"'");
                }
                if(!updatecentrepersndtldbdistid.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtldbdistid+"'");
                }
                if(!updatecentrepersndtldbprojectid.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtldbprojectid+"'");
                }
                if(!updatecentrepersndtlcdponame.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlcdponame+"'");
                }
                if(!updatecentrepersndtlcdponumber.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlcdponumber+"'");
                }
                if(!updatecentrepersndtlacdponame.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlacdponame+"'");
                }
                if(!updatecentrepersndtlacdpocontract.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlacdpocontract+"'");
                }
                if(!updatecentrepersndtldbsectorid.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtldbsectorid+"'");
                }
                if(!updatecentrepersndtlsupervisorname.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlsupervisorname+"'");
                }
                if(!updatecentrepersndtlsupervisorno.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlsupervisorno+"'");
                }
                if(!updatecentrepersndtlawcsidd.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlawcsidd+"'");
                }
                if(!updatecentrepersndtlworkername.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlworkername+"'");
                }
                if(!updatecentrepersndtlworkerno.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlworkerno+"'");
                }
                if(!updatecentrepersndtlhelpername.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlhelpername+"'");
                }
                if(!updatecentrepersndtlhelperno.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlhelperno+"'");
                }
                if(!updatecentrepersndtlstatus.equals("android_metadata")){
                    sqlDB.execSQL("DROP TABLE '"+updatecentrepersndtlstatus+"'");
                }
                c.moveToNext();


            }
        }

        c.close();
    }

}
