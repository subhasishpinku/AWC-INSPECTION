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
import icdswb.in.AdequateSpacePSEActivity;
import icdswb.in.InformationToiletActivity;
public class AdqueSpaceNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedAdquespaceDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        ADQESPACESYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_ADEQUATESPPSEID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADEQUTSPACEINSIDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADEQUTSPACE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PSEACTVTYTYP)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADEQUTSPACECURTIMESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPACEINSPCTRCMNT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPLASTISACREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPACELASTINSPCTNREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADEQUATESPPSESTATUS))
                        );
                        //Toast.makeText(context, "Network Loop Adquespace "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void ADQESPACESYNC(final int adequatesppseid, final String adequtspaceinsidsync, final String adequtspacesync, final String pseactvtytypsync, final String adequtspacecurTimesync, final String adequtspaceinspctrcmntsync, final String adqutspacelastisacrepsync,final String adqutspacelastinspctnrepsync,final String adequatesppsestatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PSE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                           // Toast.makeText(context, "Network Loop OUT Remove Adquespace",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateAdquespaceSyncStatus(adequatesppseid, AdequateSpacePSEActivity.ADESQUSPACE_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(AdequateSpacePSEActivity.DATA_SAVED_BROADCAST_ADQUESPACE));
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
                 params.put("inspctn_id",adequtspaceinsidsync);
                 params.put("adequt_space",adequtspacesync);
                 params.put("pse_actvty_typ",pseactvtytypsync);
                 params.put("ins_time",adequtspacecurTimesync);
                 params.put("inspctr_cmnt",adequtspaceinspctrcmntsync);
                 params.put("last_isac_rep",adqutspacelastisacrepsync);
                 params.put("last_inspctn_rep",adqutspacelastinspctnrepsync);
                 Log.e("ADQESPACESYNC",adequtspaceinsidsync+" "+adequtspacesync+" "+pseactvtytypsync+" "+adequtspacecurTimesync+" "+adequtspaceinspctrcmntsync+" "+adqutspacelastisacrepsync+" "+adqutspacelastinspctnrepsync);
                 return params;
            }
        };
        //VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
}
