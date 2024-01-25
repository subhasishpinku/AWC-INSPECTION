//package icdswb.in.ActivityDatabase;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import icdswb.in.ActivityVolley.Config;
//import icdswb.in.ActivityVolley.VolleySingleton;
//import icdswb.in.BuildingDetailsActivity;
//
//public class BuildingNetworkStateChecker extends BroadcastReceiver {
//    private Context context;
//    private DatabaseHelper db;
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        this.context = context;
//        db = new DatabaseHelper(context);
//
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//
//        //if there is a network
//        if (activeNetwork != null) {
//            //if connected to wifi or mobile data plan
//            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                Cursor cursor = db.getUnsyncedBuildingDetails();
//                int i = 1;
//                if (cursor.moveToFirst()){
//                    do {
//                        BUILDINGSYNC(
//                              cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILDINGIDSYNC)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBDISTIDSYNC)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBPROJECTIDSYNC)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBSECTORIDSYNC)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBCENTERIDSYNC)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILTTYP)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILTRUNIN)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OWNBUILDFUND)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_RENTBUILDIN)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHRGOVTBUILDIN)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILDCONDITN)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPCTRCMNT)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERIDSYNC)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSIDSYNC)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CURDATE)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CURTIME)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LATISACREPORT)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LSTINSPCTNBULDREP)),
//                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_STATUSBUILDING))
//                        );
//                        Toast.makeText(context, "Network Loop Building "+i,Toast.LENGTH_SHORT).show();
//                        i++;
//                    }while (cursor.moveToNext());
//                }
//            }
//        }
//    }
//
//
//    private void BUILDINGSYNC(final int buildingidsync, final String dbdistidsync, final String dbprojectidsync, final String dbsectoridsync, final String dbcenteridsync, final String builttypsync, final String builtruninsync, final String ownbuildfundsync, final String rentbuildinsync,final String othrgovtbuildinsync,final String buildconditn,final String inspctrcmnt,final String useridsync,final String insidsync,final String curdatesync,final String curtimesync,final String latisacreportsync,final String lstinspctnbuldrepsync,final String buildingstatus) {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, BuildingDetailsActivity.BUILDINGDETAILS,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONArray array =new JSONArray(response);
//                               Toast.makeText(context, "Network Loop OUT Remove ",Toast.LENGTH_SHORT).show();
//                       //     removeodIn();
//
//                                //updating the status in sqlite
//                               db.updateBuildingSyncStatus(buildingidsync, BuildingDetailsActivity.BUILDING_SYNCED_WITH_SERVER);
//                               context.sendBroadcast(new Intent(BuildingDetailsActivity.DATA_SAVED_BROADCAST_BUILDING));
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("district", dbdistidsync);
//                params.put("project", dbprojectidsync);
//                params.put("sector", dbsectoridsync);
//                params.put("centre", dbcenteridsync);
//                params.put("built_typ", builttypsync);
//                params.put("built_run_in", builtruninsync);
//                params.put("own_build_fund", ownbuildfundsync);
//                params.put("rent_build_in", rentbuildinsync);
//                params.put("othr_govt_build_in", othrgovtbuildinsync);
//                params.put("build_conditn", buildconditn);
//                params.put("inspctr_cmnt", inspctrcmnt);
//                params.put("user_id", useridsync);
//                params.put("inspctn_id", insidsync);
//                params.put("inspctn_date", curdatesync);
//                params.put("inspctn_time", curtimesync);
//                params.put("last_isac_rep", latisacreportsync);
//                params.put("last_inspctn_rep", lstinspctnbuldrepsync);
//                Log.e("BUILDINGDETAILS", dbdistidsync + " " + dbprojectidsync + " " + dbsectoridsync + " " + dbcenteridsync + " " + " " + builttypsync + " " + builtruninsync + " " + ownbuildfundsync + " " + rentbuildinsync + " " + othrgovtbuildinsync + " " + buildconditn + " " + inspctrcmnt + " " + useridsync + " " + insidsync + " " + curdatesync + " " + curtimesync + " " + latisacreportsync + " " + lstinspctnbuldrepsync);
//                return params;
//            }
//        };
//
//     //   VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton volleySingleton = VolleySingleton.getInstance(context);
//        stringRequest.setShouldCache(false);
//        volleySingleton.addToRequestQueue(stringRequest);
//    }
//
//}
