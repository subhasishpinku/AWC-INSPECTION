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
import icdswb.in.InformationKitchenActivity;
import icdswb.in.InformationToiletActivity;

public class KitchenNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedKitchenDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        KITCHENSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_KITCHENIDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_KITCHENINSID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SEPRTKITCHN)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SEPRTCOOKDONEITCHNSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_TOILETINSPCTRCMNTSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSTIMESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_TOILETLASTISACREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LASTINSPCTNREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_KITCHENSTATUS))

                        );
                     //   Toast.makeText(context, "Network Loop Building Kitchen "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void KITCHENSYNC(final int kitchenidsync, final String kitcheninsidsync, final String seprtkitchnsync, final String seprtcookdoneitchnsync, final String toiletinspctrcmntsync, final String instimesync, final String toiletlastisacrepsync,final String lastinspctnrepsync,final String kitchenstatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KITCHEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                        //    Toast.makeText(context, "Network Loop OUT Remove Kitchen",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                           db.updateKitchenSyncStatus(kitchenidsync, InformationKitchenActivity.KITCHEN_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(InformationKitchenActivity.DATA_SAVED_BROADCAST_KITCHEN));

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
                params.put("inspctn_id",kitcheninsidsync);
                params.put("seprt_kitchn",seprtkitchnsync);
                params.put("cook_done",seprtcookdoneitchnsync);
                params.put("inspctr_cmnt",toiletinspctrcmntsync);
                params.put("ins_time",instimesync);
                params.put("last_isac_rep",toiletlastisacrepsync);
                params.put("last_inspctn_rep",lastinspctnrepsync);
                Log.e("TOILETSYNC",kitcheninsidsync+" "+seprtkitchnsync+" "+seprtcookdoneitchnsync+" "+toiletinspctrcmntsync+" "+instimesync+" "+toiletlastisacrepsync+" "+lastinspctnrepsync);
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
