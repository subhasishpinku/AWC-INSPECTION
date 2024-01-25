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
import icdswb.in.DrinkingWaterActivity;
import icdswb.in.InformationToiletActivity;
public class DrinkingNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedDrinkingDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        DRINKINGWATERSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERIDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERINSID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATEROWNDRINKWATERSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERCURTIMESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERLASTISACREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LASTINSPCTNREPDRINKINGSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LASTINSPCTNREPDRINKINGCOMMANDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERSTATUS))

                        );
                      //  Toast.makeText(context, "Network Loop DRINKINGWATER "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }


    }
    private void DRINKINGWATERSYNC(final int drinkingwateridsync,final String drinkingwaterinsid,final String owndrinkwatersync,final String drinkingwatercurtimesync,final String drinkingwaterlastisacrepsync,final String lastinspctnrepdrinkingsync,final String drinkwaterremark,final String drinkingwaterstatus){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DRINKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                          //  Toast.makeText(context, "Network Loop OUT Remove DRINKINGWATER",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateDrinkingSyncStatus(drinkingwateridsync, DrinkingWaterActivity.DRINKINGWATER_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(DrinkingWaterActivity.DATA_SAVED_BROADCAST_DRINLINGWATER));

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
                params.put("inspctn_id",drinkingwaterinsid);
                params.put("own_drink_water",owndrinkwatersync);
                params.put("ins_time",drinkingwatercurtimesync);
                params.put("last_isac_rep",drinkingwaterlastisacrepsync);
                params.put("last_inspctn_rep",lastinspctnrepdrinkingsync);
                params.put("drink_water_remark",drinkwaterremark);
                Log.e("DRINKINGWATERSYNC",drinkingwaterinsid+" "+owndrinkwatersync+" "+drinkingwatercurtimesync+" "+drinkingwaterlastisacrepsync+" "+lastinspctnrepdrinkingsync+" "+drinkwaterremark);
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
