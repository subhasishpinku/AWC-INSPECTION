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
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.InformationToiletActivity;
public class ToiletNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedToiletDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        TOILETSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_TOILETIDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSIDTOILETSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_YNTOILETSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_TOILETUSABLESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPCTRCMNTSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CURTIMESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LASTISACREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LSTINSPCTNTOILETREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_TOILETSTATUS))

                        );
                     //   Toast.makeText(context, "Network Loop Building Toilet "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void TOILETSYNC(final int toiletidsync, final String insidtoiletsync, final String yntoiletsync, final String toiletusablesync, final String inspctrcmntsync, final String curTimesync, final String lastisacrepsync,final String lstinspctntoiletrepsync,final String toiletstatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, InformationToiletActivity.TOILET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                      //      Toast.makeText(context, "Network Loop OUT Remove Toilet",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateToiletSyncStatus(toiletidsync, InformationToiletActivity.TOILET_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(InformationToiletActivity.DATA_SAVED_BROADCAST_TOILET));

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
                params.put("inspctn_id",insidtoiletsync);
                params.put("toilet",yntoiletsync);
                params.put("toilet_use", toiletusablesync);
                params.put("inspctr_cmnt",inspctrcmntsync);
                params.put("ins_time",curTimesync);
                params.put("last_isac_rep",lastisacrepsync);
                params.put("last_inspctn_rep",lstinspctntoiletrepsync);
                Log.e("TOILETSYNC",insidtoiletsync+" "+yntoiletsync+" "+toiletusablesync+" "+inspctrcmntsync+" "+curTimesync+" "+lastisacrepsync+" "+lstinspctntoiletrepsync);
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