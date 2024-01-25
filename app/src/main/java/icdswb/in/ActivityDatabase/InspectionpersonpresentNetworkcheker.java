package icdswb.in.ActivityDatabase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import icdswb.in.ProcedActivityy;

public class InspectionpersonpresentNetworkcheker extends BroadcastReceiver {
    private Context context;
    private DatabaseHelper db;
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
                Cursor cursor = db.getUnsyncedINSPECTIONPERSONPRESENDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        INSPACTIONSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTDBDISTID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTDBPROJECTID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTDBSECTORID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTDBCENTERID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTYNCDPO)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTYNACDPIO)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTYNSUPERVISOR)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTYNWORKER)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTYHELPER)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTCURDATE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTCURTIME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTYNAWCS)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTUSERID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTINSID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTPLANID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTPLANIDCMT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTSTATUS))


                        );
                        Toast.makeText(context, "INSPACTIONPERSONPESENT "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void INSPACTIONSYNC(final int inspectionpersonpresentid, final String inspectionpersonpresentdbdistid,
                                final String inspectionpersonpresentdbprojectid, final String inspectionpersonpresentdbsectorid,
                                final String inspectionpersonpresentdbcenterid, final String inspectionpersonpresentyncdpo,
                                final String inspectionpersonpresentynacdpio,final String inspectionpersonpresentynsupervisor,
                                final String inspectionpersonpresentynworker,final String inspectionpersonpresentyhelper,
                                final String inspectionpersonpresentcurdate,final String inspectionpersonpresentcurtime,
                                final String inspectionpersonpresentynawcs,final String inspectionpersonpresentuserid,
                                final String inspectionpersonpresentinsid,final String cmt,final String  inspectionpersonpresentplanid,
                                final String inspectionpersonpresentstatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.INSPACTIONPERSONPESENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                        //    Toast.makeText(context, "Remove INSPACTIONPERSONPESENT",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateINSPECTIONPERSONPRESENSyncStatus(inspectionpersonpresentid, ProcedActivityy.INSPACTIONPERSONPESENT_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(ProcedActivityy.DATA_SAVED_BROADCAST_INSPACTIONPERSONPESENT));

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
                params.put("district", inspectionpersonpresentdbdistid);
                params.put("project", inspectionpersonpresentdbprojectid);
                params.put("sector", inspectionpersonpresentdbsectorid);
                params.put("centre",inspectionpersonpresentdbcenterid);
                params.put("cdpo_prsnt", inspectionpersonpresentyncdpo);
                params.put("acdpo_prsnt",inspectionpersonpresentynacdpio);
                params.put("supvsr_prsnt", inspectionpersonpresentynsupervisor);
                params.put("worker_prsnt",inspectionpersonpresentynworker);
                params.put("helper_prsnt", inspectionpersonpresentyhelper);
                params.put("inspctn_date",inspectionpersonpresentcurdate);
                params.put("inspectn_time",inspectionpersonpresentcurtime);
                params.put("awcs_open", inspectionpersonpresentynawcs);
                params.put("user_id",inspectionpersonpresentuserid);
                params.put("inspctn_id",inspectionpersonpresentinsid);
                params.put("plan_id",inspectionpersonpresentplanid);
                params.put("cmnt",cmt);
                Log.e("INSPACTIONPERSONPESENT"," "+inspectionpersonpresentdbdistid+" "+inspectionpersonpresentdbprojectid+" "+inspectionpersonpresentdbsectorid+" "+inspectionpersonpresentdbcenterid+" "+inspectionpersonpresentyncdpo+" "+inspectionpersonpresentynacdpio+" "+inspectionpersonpresentynsupervisor+" "+inspectionpersonpresentynworker+" "+inspectionpersonpresentyhelper+" "+inspectionpersonpresentcurdate+" "+inspectionpersonpresentcurtime+" "+inspectionpersonpresentynawcs+" "+inspectionpersonpresentuserid+" "+inspectionpersonpresentinsid+" "+inspectionpersonpresentplanid+" "+cmt);
                return params;
            }
        };
        //   VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }


}
