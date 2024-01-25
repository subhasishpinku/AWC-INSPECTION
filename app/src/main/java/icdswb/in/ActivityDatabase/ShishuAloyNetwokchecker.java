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
import icdswb.in.ShishuAloyActivity;

public class ShishuAloyNetwokchecker extends BroadcastReceiver {
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
                Cursor cursor = db.getUnsyncedSHISHUALOYDetails();
                int i = 1;
                if (cursor.moveToFirst()){
                    do {
                        SHISHUALOYSYNC(
                                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYINSID)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SISHUALOY)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CORNERCGNITV)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOKCORNER)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DRAWCORNR)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PLAYCORNERS)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ECCRUN)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_VALUESUTING)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ECCACTIVITYTYPE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCVALUESSTRING)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_TLAM)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROPRECCKIT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AVALEINDVSUALCHILDACTIVEYRCD)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INDVSUALCHILDACTVTYRCD)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCDECORTNFRECCE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FITORECCCHECK)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CHILDENROLLED)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CHILDFOUNDTODAYY)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ECCEPROCESS)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ASSESMENTCARD)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ASSESMENTCARDUSE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ECCKITDATE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ECCOBSERDATE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PSEACTFOUND)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PSEACTIVITYNM)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CHAILDPARTICIPATPSE)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWWFOLLOWINGTHEROUTING)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYINSPECTRCMNT)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYCURTIME)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYSTATUS))
                        );
                  //      Toast.makeText(context, "Network Loop Shihualoy "+i,Toast.LENGTH_SHORT).show();
                        i++;
                    }while (cursor.moveToNext());
                }
            }
        }
    }
    private void SHISHUALOYSYNC(final int shishualoyid,
                                final String hishualoyinsid,
                                final String sishualoy,
                                final String cornercgnitv,
                                final String bookcorner,
                                final String drawcornr,
                                final String playcorners,
                                final String eccrun,
                                final String valuesuting,
                                final String eccactvtytyp,
                                final String awcvaluestring,
                                final String tlam,
                                final String proprecckit,
                                final String avaleindvsualchildactvtyrcd,
                                final String indvsualchildactvtyrcd,
                                final String awcdecortnfrecce,
                                final String fitorecccheck,
                                final String childenrolled,
                                final String childfoundtodayy,
                                final String ecceprocess,
                                final String assesmentcard,
                                final String assesmentcarduse,
                                final String ecckitdate,
                                final String eccobserdate,
                                final String pseactvfound,
                                final String pseactivitynm,
                                final String chldparticipatpse,
                                final String wheterawwriting,
                                final String shishualoyinspectrcmnt,
                                final String shishualoycurtime,
                                final String shishualoystatus) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Config.SHISHUALOY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                    //        Toast.makeText(context, "Network Loop OUT Remove Shihualoy",Toast.LENGTH_SHORT).show();
                            //     removeodIn();
                            //updating the status in sqlite
                            db.updateSHISHUALOYSyncStatus(shishualoyid, ShishuAloyActivity.SHISHUALOY_SYNCED_WITH_SERVER);
                            context.sendBroadcast(new Intent(ShishuAloyActivity.DATA_SAVED_BROADCAST_SHISHUALOY));

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
//                params.put("inspctn_id",hishualoyinsid);
//                params.put("sishu_aloy",sishualoy);
//                params.put("corner_cgnitv",cornercgnitv);
//                params.put("book_corner",bookcorner);
//                params.put("draw_cornr",drawcornr);
//                params.put("play_corner",playcorners);
//                params.put("ecc_run",eccrun);
//                params.put("ecc_actvty_typ",eccactvtytyprep);
//                params.put("propr_ecc_kit",proprecckit);
//                params.put("indvsual_child_actvty_rcd",indvsualchildactvtyrcd);
//                params.put("awc_decortn_fr_ecce",awcdecortnfrecce);
//                params.put("pse_activity_nm",pseactivitynm);
//                params.put("tot_chld_prsnt",totchldprsnt);
//                params.put("chld_rspnse",chldrspnse);
//                params.put("inspectr_cmnt",shishualoyinspectrcmnt);
//                params.put("ins_time",shishualoycurtime);
//                Log.e("SHISHUALOYSYNC",hishualoyinsid+" "+sishualoy+" "+cornercgnitv+" "+bookcorner+" "+drawcornr+" "+playcorners+" "+eccrun+" "+eccactvtytyprep+" "+proprecckit+" "+indvsualchildactvtyrcd+" "+awcdecortnfrecce+" "+pseactivitynm+" "+totchldprsnt+" "+chldrspnse+" "+shishualoyinspectrcmnt+" "+shishualoycurtime);
                params.put("inspctn_id",hishualoyinsid);
                params.put("sishu_aloy",sishualoy);
                params.put("corner_cgnitv",cornercgnitv);
                params.put("book_corner",bookcorner);
                params.put("draw_cornr",drawcornr);
                params.put("play_corner",playcorners);
                params.put("ecc_run",eccrun);
                params.put("ecc_routn_theme", valuesuting);
                params.put("ecc_actvty_typ",eccactvtytyp);
                params.put("ecc_activty_found",awcvaluestring);
                params.put("aww_tlm",tlam);
                params.put("propr_ecc_kit",proprecckit);
                params.put("indvsual_child_artwork" ,avaleindvsualchildactvtyrcd);
                params.put("indvsual_child_actvty_rcd",indvsualchildactvtyrcd);
                params.put("awc_decortn_fr_ecce",awcdecortnfrecce);
                params.put("aww_routin_follow",fitorecccheck);
                // params.put("",wheterawwriting);
                params.put("tot_chld_enroll",childenrolled);
                params.put("tot_chld_found_today",childfoundtodayy);
                params.put("chld_participat_ecc",ecceprocess);
                params.put("assmnt_card",assesmentcard);
                params.put("assmnt_card_st",assesmentcarduse);
                params.put("last_ecc_kit_rcv_dt",ecckitdate);
                params.put("last_ecc_day_observ",eccobserdate);
                params.put("pse_actv_found",pseactvfound);
                params.put("pse_activity_nm",pseactivitynm);
                params.put("chld_participat_pse",chldparticipatpse);
                params.put("aww_follow_routine",wheterawwriting);
                params.put("inspectr_cmnt",shishualoyinspectrcmnt);
                params.put("ins_time",shishualoycurtime);
                Log.e("SHISHULAYEDITSYNC","inspctn_id"+" "+"  "+hishualoyinsid+" "+"  "+
                        "sishu_aloy"+" "+"  "+sishualoy+" "+" "+
                        "corner_cgnitv"+" "+cornercgnitv+" "+" "+
                        "book_corner"+" "+" "+bookcorner+" "+" "+
                        "draw_cornr"+"  "+drawcornr+" "+
                        "play_corner"+"   "+playcorners+
                        "ecc_run"+" "+eccrun+" "
                        +"ecc_routn_theme"+"  "+valuesuting+
                        "ecc_actvty_typ"+" "+"  "+" "+eccactvtytyp+
                        "ecc_activty_found"+"   "+awcvaluestring
                        +"aww_tlm"+"  "+tlam
                        +"propr_ecc_kit"+"   "+proprecckit
                        +"indvsual_child_artwork"+"   "+avaleindvsualchildactvtyrcd
                        +"indvsual_child_actvty_rcd"+"   "+indvsualchildactvtyrcd
                        +"awc_decortn_fr_ecce"+"   "+awcdecortnfrecce
                        +"aww_routin_follow"+"   "+fitorecccheck
                        +"tot_chld_enroll"+"   "+childenrolled
                        +"tot_chld_found_today"+"   "+childfoundtodayy
                        +"chld_participat_ecc"+"   "+ecceprocess
                        +"assmnt_card"+"   "+assesmentcard
                        +"assmnt_card_st"+" "+assesmentcarduse
                        +"last_ecc_kit_rcv_dt"+"  "+ecckitdate
                        +"last_ecc_day_observ"+"   "+eccobserdate
                        +"pse_actv_found"+"   "+pseactvfound
                        +"pse_activity_nm"+"   "+pseactivitynm
                        +"chld_participat_pse"+"   "+chldparticipatpse
                         +"aww_follow_routine"+"   "+wheterawwriting
                        +"inspectr_cmnt"+"    "+shishualoyinspectrcmnt
                        +"ins_time"+" "+shishualoycurtime

                );
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
