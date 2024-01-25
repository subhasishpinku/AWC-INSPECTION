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
import icdswb.in.NutritionalStatusObservedActivity;
import icdswb.in.SNPServedActivity;

public class NutritionNetworkChecker extends BroadcastReceiver {

    private Context context;
    private DatabaseHelper db;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        db = new DatabaseHelper(context);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Cursor cursor = db.getUnsyncedNUTRITIONALDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        NUTRITIONYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALTID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALTINS)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALTINTIME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_MALNURISHDCHLD)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SANCHLD)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_COMMANT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALSTATUS))


                        );
               //         Toast.makeText(context, "Network Loop NUTRITION "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void NUTRITIONYNC(final int nutritionaltableid,final String nutritionaltins, final String nutritionaltintime,final String malnurishdchld,final String sanchld,final String commant,final String nutritionalstatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Config.NUTRITIONAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                       //     Toast.makeText(context, "Network Loop OUT Remove NUTRITION",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateNUTRITIONALSyncStatus(nutritionaltableid, NutritionalStatusObservedActivity.NUTRITATION_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(NutritionalStatusObservedActivity.DATA_SAVED_BROADCAST_NUTRITATION));

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
                params.put("inspctn_id",nutritionaltins);
                params.put("ins_time",nutritionaltintime);
                params.put("malnurishd_chld",malnurishdchld);
                params.put("chld_found_physicaly",sanchld);
                params.put("inspctr_cmnt",commant);
                Log.e("NUTRITIONSYNC", nutritionaltins+" "+nutritionaltintime+" "+malnurishdchld+" "+sanchld+" "+commant);
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
