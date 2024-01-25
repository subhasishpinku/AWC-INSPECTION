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
import icdswb.in.SNPServedActivity;
import icdswb.in.ShishuAloyActivity;

public class SPNNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedSPNDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        SNPSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_SPNTABLEID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SPNINSID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_MRNGSNACKS)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_MRNGSNACKTYP)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SNPSERVE)),

                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_HCMASPERMNU)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SNPMENU)),

                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CHLDPRSNT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CHIDPRSNTTODAY)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PMLMPRSNT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PMLMPRSNTTODAY)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AVRGFEEDNGLSTTHREMNTH)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FEEDINTRPTLM)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ANYFEEDINGINTRUPTNLSTTHREMNTH)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FEEDINTRPT2M)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH2)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FEEDINTRPT3M)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH3)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SNPNTSRVREASN)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SPNINSPCTNCMNT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SPNCURTIME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SPNSTATUS))

                        );
                      //  Toast.makeText(context, "Network Loop SNP "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void SNPSYNC(final int spntableid,
                         final String spninsid,
                         final String mrngsnacks,
                         final String mrngsnacktyp,
                         final String snpserve,

                         final String hcmaspermnu,
                         final String snpmenu,

                         final String chldprsnt,
                         final  String chldprsnttoday,
                         final String pmlmprsnt,
                         final String pmlmprsnttoday,

                         final String anyfreeinterlastthree,
                         final String feedintrpt1m,
                         final String anyfeedingintruptnlstthremnth,
                         final String feedintrpt2m,
                         final String anyfeedingintruptnlstthremnth2,
                         final String feedintrpt3m,
                         final String anyfeedingintruptnlstthremnth3,
                         final String snpntsrvreasn,
                         final String pninspctncmnt,
                         final String spncurtime,
                         final String spnstatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Config.SNP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                       //     Toast.makeText(context, "Network Loop OUT Remove SNP",Toast.LENGTH_SHORT).show();
                            Log.e("anyfreeinterlastthree1",anyfreeinterlastthree);
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateSPNSyncStatus(spntableid, SNPServedActivity.SNP_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(SNPServedActivity.DATA_SAVED_BROADCAST_SNP));

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
                params.put("inspctn_id",spninsid);
                params.put("mrng_snacks",mrngsnacks); //y
                params.put("mrng_snack_typ",mrngsnacktyp);
                params.put("snp_serve",snpserve);
                //params.put("snp_menu",snpmenu);
                params.put("snp_serve_typ",hcmaspermnu);
                /////FILD NOT NOT RECURED mrng_snacks_menu
                // params.put("mrng_snacks_menu",snpmenu);
                params.put("hcm_menu_today",snpmenu);
                params.put("chld_prsnt_enroll",chldprsnt);
                params.put("chld_prsnt_today",chldprsnttoday);
                params.put("pm_lm_prsnt_enroll",pmlmprsnt);
                params.put("pm_lm_prsnt_today",pmlmprsnttoday);
                params.put("any_feeding_intruptn_lst_thre_mnth",anyfreeinterlastthree);
                params.put("feed_intrpt_1m",feedintrpt1m);
                params.put("feed_intrpt_1days",anyfeedingintruptnlstthremnth);
                params.put("feed_intrpt_2m",feedintrpt2m);
                params.put("feed_intrpt_2days",anyfeedingintruptnlstthremnth2);
                params.put("feed_intrpt_3m",feedintrpt3m);
                params.put("feed_intrpt_3days",anyfeedingintruptnlstthremnth3);
                // params.put("chld_prsnt",chldprsnt);
                // params.put("pm_lm_prsnt",pmlmprsnt);
                //  params.put("avrg_feedng_lst_thre_mnth",avrgfeednglstthremnth);
                // params.put("any_feeding_intruptn_lst_thre_mnth",anyfeedingintruptnlstthre_mnth);
                params.put("snp_nt_srv_reasn",snpntsrvreasn);
                params.put("inspctn_cmnt",pninspctncmnt);
                params.put("ins_time",spncurtime);
                Log.e("SNPSYNC",spninsid+" "
                        +mrngsnacks+" "
                        +mrngsnacktyp+" "
                        +snpserve+" "
                        +hcmaspermnu+" "
                        +snpmenu+" "
                        +chldprsnt+" "
                        +chldprsnttoday+" "
                        +pmlmprsnt+" "
                        +pmlmprsnttoday+" "
                        +anyfreeinterlastthree+" "
                        +feedintrpt1m+" "
                        +anyfeedingintruptnlstthremnth+" "
                        +feedintrpt2m+" "
                          +anyfeedingintruptnlstthremnth2+" "
                           +feedintrpt3m+" "+anyfeedingintruptnlstthremnth3+" "+snpntsrvreasn+" "+pninspctncmnt+" "+spncurtime+" ");
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
