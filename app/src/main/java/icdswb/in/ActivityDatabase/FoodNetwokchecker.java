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
import icdswb.in.FoodStoredDetailActivity;

public class FoodNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedFoodDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        FOODSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSTOREIDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODINSIDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSTORESPACESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODPHYSICLCHKSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSTKBOOKCMPARESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSTKSUFICNTSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODRICEDALSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODDALSYNC)),

                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODMAUSTEROILSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSALTSYNC)),


                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSTOCKLYNGSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODINSTIMESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODLASTISACREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODLASTINSPCTNREPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSTATUSSYNC))
                        );
                     //   Toast.makeText(context, "Network Loop Food "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void FOODSYNC(final int foodstoreidsync,final String foodinsidsync,final String foodstorespacesync,final String foodphysiclchksync,final String foodstkbookcmparesync,final String foodstksuficntsync,final String foodricedalsync,final String fooddalbrndsync,
                          final String musteroilsysnc,final String saltsync,
                          final String foodstocklyngsync,final String foodinstimesync,final String foodlastisacrepsync,final String foodlastinspctnrepsync,final String foodstatussync){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.STORE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                        //    Toast.makeText(context, "Network Loop OUT Remove Food",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateFoodSyncStatus(foodstoreidsync,FoodStoredDetailActivity.FOOD_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(FoodStoredDetailActivity.DATA_SAVED_BROADCAST_FOOD));

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
                params.put("inspctn_id",foodinsidsync);
                params.put("store_space",foodstorespacesync);
                params.put("physicl_chk",foodphysiclchksync);
                params.put("stk_book_cmpare",foodstkbookcmparesync);
                params.put("stk_suficnt",foodstksuficntsync);
                params.put("rice_dal",foodricedalsync);
                params.put("dal",fooddalbrndsync);
                params.put("moil_brnd",musteroilsysnc);
                params.put("salt_brnd",saltsync);
                params.put("stock_lyng",foodstocklyngsync);
                params.put("ins_time",foodinstimesync);
                params.put("last_isac_rep",foodlastisacrepsync);
                params.put("last_inspctn_rep",foodlastinspctnrepsync);
                Log.e("FOODSYNC",foodinsidsync+" "+foodstorespacesync+" "+foodphysiclchksync+" "+foodstkbookcmparesync+" "+foodstksuficntsync+" "+foodricedalsync+" "+fooddalbrndsync+" "+musteroilsysnc+" "+" "+saltsync+" "+foodstocklyngsync+" "+foodinstimesync+" "+foodlastisacrepsync+" "+foodlastinspctnrepsync);
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
