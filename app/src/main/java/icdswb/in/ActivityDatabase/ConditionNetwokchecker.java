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
import icdswb.in.ConditionOfEquipmentOthersAWCActivity;
import icdswb.in.ElectricityActivity;

public class ConditionNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedconditionDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        CONDITIONSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONOFEQIPMENTID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONOFEQIPMENTINSIDSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONUTENSILCONDTNSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONMATCONDITNSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONELEVNREGSTRSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONELEVNREGSTRNOSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONBABYWMACHINSYNC)),

                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONELEVNREGSTRBABYWMACHINSYNC)),



                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONADULTWMACHINSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADUALTWEIGHINGMACHINEUSEDNOTUSED)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONHEIGHTMEASURTYPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_HEIGHTMESURINGTYPE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONGROWTHCHRTSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROPERFILLEDUP)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONSTORECONTAINRSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SUFICENT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONHANDWASHSOAPSYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_HANDWASHINGSOPE)),


                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONCURTIMESYNC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CONDITIONSTATUS))
                        );
                      //  Toast.makeText(context, "Network Loop Condition "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void CONDITIONSYNC(final int conditionofeqipmentid,final String conditionofeqipmentinsidsync,
                               final String utensilcondtnsync, final String matconditnsync,
                               final String elevnregstrsync,
                               final String elavenregister,
                               final String babywmachinsync,
                               final String conditionbabyweighingmechinusednotoused,
                               final String adultwmachinsync,
                               final String adualtweighingmachineusednotused,
                               final String heightmeasurtypsync,
                               final String heightmeasuringtype,
                               final String growthchrtsync,
                               final String properfilledup,
                               final String storecontainrsync,
                               final String suficent,
                               final String handwashsoapsync,
                               final String handwashingsope,

                               final String conditioncurtimesync,final String conditionstatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Config.CONDITIONEQUIPMENTOTHERSAWC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                           // Toast.makeText(context, "Network Loop OUT Remove Condition",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateCONDITIONSyncStatus(conditionofeqipmentid, ConditionOfEquipmentOthersAWCActivity.CONDITION_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(ConditionOfEquipmentOthersAWCActivity.DATA_SAVED_BROADCAST_CONDITION));

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
            //    params.put("inspctn_id",conditionofeqipmentinsidsync);
//                params.put("utensil_condtn",utensilcondtnsync);
//                params.put("mat_conditn",matconditnsync);
//                params.put("elevn_regstr",elevnregstrsync);
//                params.put("baby_wmachin",babywmachinsync);
//                params.put("adult_wmachin",adultwmachinsync);
//                params.put("muac_tape",muactapesync);
//                params.put("height_measur_typ",heightmeasurtypsync);
//                params.put("growth_chrt",growthchrtsync);
//                params.put("store_containr",storecontainrsync);
//                params.put("hand_wash_soap",handwashsoapsync);
//                params.put("ins_time",conditioncurtimesync);
//                Log.e("CONDITIONSYNC",conditionofeqipmentinsidsync+" "+utensilcondtnsync+" "+matconditnsync+" "+elevnregstrsync+" "+babywmachinsync+" "+adultwmachinsync+" "+muactapesync+" "+heightmeasurtypsync+" "+growthchrtsync+" "+storecontainrsync+" "+handwashsoapsync+" "+conditioncurtimesync);
                params.put("inspctn_id",conditionofeqipmentinsidsync);
                params.put("utensil_condtn",utensilcondtnsync);
                params.put("mat_conditn",matconditnsync);
                params.put("elevn_regstr",elevnregstrsync);
                params.put("elevn_regstr_no",elavenregister);
                params.put("baby_wmachin",babywmachinsync);
                params.put("baby_wmachin_use",conditionbabyweighingmechinusednotoused);
                params.put("adult_wmachin",adultwmachinsync);
                // params.put("adult_wmachin","");
                params.put("adult_wmachin_use",adualtweighingmachineusednotused);
                params.put("height_measur_typ",heightmeasurtypsync);
                params.put("height_measur_typ_use",heightmeasuringtype);
                params.put("growth_chrt",growthchrtsync);
                params.put("growth_chrt_fillup",properfilledup);
                params.put("store_containr",storecontainrsync);
                params.put("suffi_store_containr",suficent);
                params.put("hand_wash_soap",handwashsoapsync);
                params.put("hand_wash_soap_use",handwashingsope);
                params.put("ins_time",conditioncurtimesync);
                Log.e("CONDITIONSYNC",conditionofeqipmentinsidsync+" "
                        +utensilcondtnsync+" "
                        +matconditnsync+" "
                        +elevnregstrsync+" "
                        +elavenregister+" "
                        +babywmachinsync+" "
                        +conditionbabyweighingmechinusednotoused+" "
                        +adultwmachinsync+" "
                        +adualtweighingmachineusednotused+" "
                        +heightmeasurtypsync+" "
                        +heightmeasuringtype+" "
                        +growthchrtsync+" "
                        +properfilledup+" "
                        +storecontainrsync+" "
                        +suficent+" "
                        +handwashsoapsync+" "
                        +handwashingsope+" "
                        +conditioncurtimesync+" "
                         +conditionofeqipmentinsidsync+" ");

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
