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
import icdswb.in.ElectricityActivity;
import icdswb.in.InformationToiletActivity;

public class EletricityNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedEletricityDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        TOILETSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECTRITYID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECTRITYINSIDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECTRICAVAILSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_EMODESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LIGHTTYPESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FANTYPESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PUMPOVRHDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECRITYINSPCTRCMNTSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECTRITYCUTTIMESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LASTISACREPELECTRICITYSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LASTINSPCTNREPELETRICITYSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECTRICITYSTATUS))

                        );
                       // Toast.makeText(context, "Network Loop Eletricity "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void TOILETSYNC(final int electrityid,final String electrityinsidsync, final String electricavailsync, final String emodesync, final String lighttypesync, final String fantypesync, final String pumpovrhdsync, final String inspctrcmntelectricitysync,final String cuttimeeletricitysync,final String lastisacrepeletricitysync,final String lastinspctnrepeletricitysync,final String eletricitystatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ELECTRICITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                         //   Toast.makeText(context, "Network Loop OUT Remove Eleyricity",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateEletricitySyncStatus(electrityid, ElectricityActivity.ELETRICITY_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(ElectricityActivity.DATA_SAVED_BROADCAST_ELETRICITY));

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
                params.put("inspctn_id",electrityinsidsync);
                params.put("electric_avail",electricavailsync);
                params.put("e_mode",emodesync);
                params.put("light", lighttypesync);
                params.put("fan",fantypesync);
                params.put("pump_ovrhd",pumpovrhdsync);
                params.put("inspctr_cmnt",inspctrcmntelectricitysync);
                params.put("ins_time",cuttimeeletricitysync);
                params.put("last_isac_rep",lastisacrepeletricitysync);
                params.put("last_inspctn_rep",lastinspctnrepeletricitysync);
                Log.e("ELETRICITYSYNC",electrityinsidsync+" "+electricavailsync+" "+emodesync+" "+lighttypesync+" "+fantypesync+" "+pumpovrhdsync+" "+inspctrcmntelectricitysync+" "+cuttimeeletricitysync+" "+lastisacrepeletricitysync+" "+lastinspctnrepeletricitysync);
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
