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
import icdswb.in.OtherInspectionActivity;

public class OtherinspactionNetworkchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedOTHERINSPECTIONDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        OTHERSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONTABLEID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONINSID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONINSCURTIME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CMUNTYPRTICPTNNOTICE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SUPVISIT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LASTSUPUSTIDATE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_MEDCINKITLSTRCV)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LASTRECIVED)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USEDOFAWC)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_GENGOTH)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCREMARK)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONSTATUS))


                        );
                    //    Toast.makeText(context, "Network Loop OTHER "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void OTHERSYNC(final int otherinspectionid,
                           final String otherinspectioninsid,
                           final String otherinspectioninscurtime,
                           final String cmuntyprticptnnotice,
                           final String supvisit,
                           final String lastsupustidate,
                           final String medcinkitlstrcv,
                           final String lastrecived,
                           final String usedofawc,
                           final String gengoth,
                           final String awcremark,
                           final String otherinspectionstatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Config.OTHER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                    //        Toast.makeText(context, "Network Loop OUT Remove OTHER",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateOTHERINSPECTIONSyncStatus(otherinspectionid, OtherInspectionActivity.OTHER_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(OtherInspectionActivity.DATA_SAVED_BROADCAST_OTHER));

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
//                params.put("inspctn_id",otherinspectioninsid);
//                params.put("ins_time",otherinspectioninscurtime);
//                params.put("feed_intrup_duratn",feedintrupduratn);
//                params.put("sup_actn",supactn);
//                params.put("cmunty_prticptn_notice",cmuntyprticptnnotice);
//                params.put("sup_visit",supvisit);
//                params.put("mthr_meet",mthrmeet);
//                params.put("aw_monitr",awmonitr);
//                params.put("othr_functn_vst",othrfunctnvst);
//                params.put("rcd_maintain", rcdmaintain);
//                params.put("regulr_chrg_rcv",regulrchrgrcv);
//                params.put("medcin_chrg_lst_rcv",medcinchrglstrcv);
//                params.put("preschl_chrg_lst_rcv",preschlchrglstrcv);
//                params.put("grwth_chrt_lst_rcv",grwthchrtlstrcv);
//                params.put("awc_remark",awcremark);

                params.put("inspctn_id",otherinspectioninsid);
                params.put("ins_time",otherinspectioninscurtime);
                params.put("comm_participation",cmuntyprticptnnotice);
                params.put("sup_visit",supvisit);
                params.put("last_supusti_date",lastsupustidate);
                params.put("medcin_kit_lst_rcv",medcinkitlstrcv);
                params.put("medcin_kit_st",lastrecived);
                params.put("medcin_kit_use_st",usedofawc);
                params.put("awc_gnrl_clr", gengoth);
                params.put("awc_remark",awcremark);
                Log.e("OTHER"," "+otherinspectioninsid+" "
                        +otherinspectioninscurtime+" "
                        +cmuntyprticptnnotice+" "
                        +supvisit+" "
                         +lastsupustidate+" "
                         +medcinkitlstrcv+" "
                          +lastrecived+" "
                           +usedofawc+" "+
                        gengoth+" "+
                         awcremark);
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
