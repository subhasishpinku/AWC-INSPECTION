package icdswb.in;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icdswb.in.ActivitySetGet.SetgetmyInspactionviewList;
import icdswb.in.ActivitySetGet.SetgetmyInspactionviewListt;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;
import icdswb.in.Activity_Adapter.AdaptermyInspactionviewList;


public class MyInspactionView extends AppCompatActivity {
    String insid;
    ArrayList<SetgetmyInspactionviewList> arrlist = new ArrayList<SetgetmyInspactionviewList>();
    ArrayList<SetgetmyInspactionviewListt> arrlistt = new ArrayList<SetgetmyInspactionviewListt>();
    RecyclerView recyclerView;
    List<SetgetmyInspactionviewList> setgetmyInspactionviewLists;
    private AdaptermyInspactionviewList adaptermyInspactionviewList;
    List<SetgetmyInspactionviewListt> setgetmyInspactionviewListss;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.myinspactionview);
        Intent intent = getIntent();
        insid = intent.getStringExtra("insid");
        Log.e("Myinsid",insid);
        MyInspactionViewList(insid);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrlist = new ArrayList<>();
        arrlistt = new ArrayList<>();
        setgetmyInspactionviewLists = new ArrayList<>();
        setgetmyInspactionviewListss = new ArrayList<>();
        initToolBar();
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Inspection List");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }
    public void MyInspactionViewList(final String insid){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.MYINSPACTIONLISTVIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("MYINRES"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("obj"," "+array);
                            JSONObject jsonObject = array.getJSONObject(0);
                            JSONObject jsonObject1 = array.getJSONObject(1);
                            JSONObject jsonObject2 = array.getJSONObject(2);
                            JSONObject jsonObject3 = array.getJSONObject(3);
                            JSONObject jsonObject4 = array.getJSONObject(4);
                            JSONObject jsonObject5 = array.getJSONObject(5);
                            JSONObject jsonObject6 = array.getJSONObject(6);
                            JSONObject jsonObject7 = array.getJSONObject(7);
                            JSONObject jsonObject8 = array.getJSONObject(8);
                            JSONObject jsonObject9 = array.getJSONObject(9);
                            JSONObject jsonObject10 = array.getJSONObject(10);
                            JSONObject jsonObject11 = array.getJSONObject(11);
                            JSONObject jsonObject12 = array.getJSONObject(12);
                            JSONObject jsonObject13 = array.getJSONObject(13);
                            JSONObject jsonObject14 = array.getJSONObject(14);
                            JSONObject jsonObject15 = array.getJSONObject(15);
//                            Log.e("Arr0"," " +jsonObject);
//                            Log.e("Arr1"," " +jsonObject1);
//                            Log.e("Arr2"," " +jsonObject2);
//                            Log.e("Arr3"," " +jsonObject3);
//                            Log.e("Arr4"," " +jsonObject4);
//                            Log.e("Arr5"," " +jsonObject5);
//                            Log.e("Arr6"," " +jsonObject6);
//                            Log.e("Arr7"," " +jsonObject7);
//                            Log.e("Arr8"," " +jsonObject8);
//                            Log.e("Arr9"," " +jsonObject9);
//                            Log.e("Arr10"," " +jsonObject10);
//                            Log.e("Arr11"," " +jsonObject11);
//                            Log.e("Arr12"," " +jsonObject12);
//                            Log.e("Arr13"," " +jsonObject13);
//                            Log.e("Arr14"," " +jsonObject14);
//                            Log.e("Arr15"," " +jsonObject15);;
                            String msg = jsonObject.getString("msg");
                            Log.e("LISTDAT0",msg);
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                            String cname = jsonObject1.getString("name");
                            String distname = jsonObject1.getString("dist_name");
                            String projnm = jsonObject1.getString("proj_nm");
                            String sectrnm = jsonObject1.getString("sectr_nm");
                            String centrename = jsonObject1.getString("centre_name");
                            Log.e("LISTDAT1",cname+" "+distname+" "+projnm+" "+sectrnm+" "+centrename);
                            String oname = jsonObject2.getString("name");
                            String cdponame = jsonObject2.getString("cdpo_name");
                            String cdpophn = jsonObject2.getString("cdpo_phn");
                            String acdponame = jsonObject2.getString("acdpo_name");
                            String acdpophn = jsonObject2.getString("acdpo_phn");
                            String supname = jsonObject2.getString("sup_name");
                            String supphn  = jsonObject2.getString("sup_phn");
                            String workername = jsonObject2.getString("worker_name");
                            String workerphn = jsonObject2.getString("worker_phn");
                            String hlpername = jsonObject2.getString("hlper_name");
                            String hlperphn = jsonObject2.getString("hlper_phn");
                            Log.e("LISTDAT2",oname+" "+cdponame+" "+cdpophn+" "+acdponame+" "+acdpophn+" "+supname+" "+supphn+" "+workername+" "+workerphn+" "+hlpername+" "+hlperphn);
                            String Iname = jsonObject3.getString("name");
                            String userimg = jsonObject3.getString("user_img");
                            String username = jsonObject3.getString("user_name");
                            String userphn  = jsonObject3.getString("user_phn");
                            String usermail = jsonObject3.getString("user_mail");
                            String useradrs = jsonObject3.getString("user_adrs");
                            Log.e("LISTDAT3",Iname+" "+userimg+" "+username+" "+userphn+" "+usermail+" "+useradrs+" ");
                            ///////////////////building/////
                            String Bname = jsonObject4.getString("name");
                            String lstbuildisac = jsonObject4.getString("lst_build_isac");
                            String lstbuildinspctn = jsonObject4.getString("lst_build_inspctn");
                            String buildtyp = jsonObject4.getString("build_typ");
                            String buldrun = jsonObject4.getString("buld_run");
                            ///off fund name
                         //   String fundname = jsonObject4.getString("fund_name");
                            ///off fund name
                            String rentbuild = jsonObject4.getString("rent_build");
                            String othrgovtbuld = jsonObject4.getString("othr_govt_buld");
                            String buldcondtn = jsonObject4.getString("buld_condtn");
                            String buldinspctncmnt = jsonObject4.getString("buld_inspctn_cmnt");
                            Log.e("LISTDAT4",Bname+" "+lstbuildisac+" "+lstbuildinspctn+" "
                                    +buildtyp+" "+buldrun+" "+" "+rentbuild+" "+othrgovtbuld+" "+buldcondtn+" "+buldinspctncmnt+" ");

                            ///////////////////building/////
                            String Tname = jsonObject5.getString("name");
                            String toiltlstisac = jsonObject5.getString("toilt_lst_isac");
                            String toiltlstinspctn = jsonObject5.getString("toilt_lst_inspctn");
                            String toilt = jsonObject5.getString("toilt");
                            String toiltuse = jsonObject5.getString("toilt_use");
                            String toiletcmnt = jsonObject5.getString("toilet_cmnt");
                            Log.e("LISTDAT5",Tname+" "+toiltlstisac+" "+toiltlstinspctn+" "+toilt+" "+toiltuse+" "+toiletcmnt);
                            String kname = jsonObject6.getString("name");
                            String kitchnlstisac = jsonObject6.getString("kitchn_lst_isac");
                            String kitchnlstinspctn = jsonObject6.getString("kitchn_lst_inspctn");
                            String sprtkitchn = jsonObject6.getString("sprt_kitchn");
                            String cookdone = jsonObject6.getString("cook_done");
                            String kitchncmnt = jsonObject6.getString("kitchn_cmnt");
                            Log.e("LISTDAT6",kname+" "+kitchnlstisac+" "+kitchnlstinspctn+" "+sprtkitchn+" "+cookdone+" "+kitchncmnt);
                            String ename = jsonObject7.getString("name");
                            String lstelectricisacrep = jsonObject7.getString("lst_electric_isac_rep");
                            String lstelectricinspctn = jsonObject7.getString("lst_electric_inspctn");
                            String electricavail = jsonObject7.getString("electric_avail");
                            String elctrimode = jsonObject7.getString("elctri_mode");
                            String light = jsonObject7.getString("light");
                            String fan = jsonObject7.getString("fan");
                            String pump = jsonObject7.getString("pump");
                            String electrccmnt = jsonObject7.getString("electrc_cmnt");
                            Log.e("LISTDAT7",ename+" "+lstelectricisacrep+" "+lstelectricinspctn+" "+electricavail+" "+elctrimode+" "+light+" "+fan+" "+pump+" "+electrccmnt);
                            String dname = jsonObject8.getString("name");
                            String drnklstisac =jsonObject8.getString("drnk_lst_isac");
                            String drnklstinspctn = jsonObject8.getString("drnk_lst_inspctn");
                            String owndrnkwater = jsonObject8.getString("own_drnk_water");
                            //////
                            String drinkingwater = jsonObject8.getString("drink_water_remark");
                            Log.e("LISTDAT8",dname+" "+drnklstisac+" "
                                    +drnklstinspctn+" "+owndrnkwater+" "+drinkingwater);

                            /////////////////
                            String fname = jsonObject9.getString("name");
                            String foodlstisac = jsonObject9.getString("food_lst_isac");
                            String foodlstinspctn = jsonObject9.getString("food_lst_inspctn");
                            String storspce = jsonObject9.getString("stor_spce");
                            String physiclchk = jsonObject9.getString("physicl_chk");
                            String stkbookcmpare = jsonObject9.getString("stk_book_cmpare");
                            String stksuficnt = jsonObject9.getString("stk_suficnt");
                            String ricedal = jsonObject9.getString("rice_dal");
                            ///////////
                            String dal = jsonObject9.getString("dal");
                            //////////
                            String soyachunkbrnd = jsonObject9.getString("master_oil_brnd");
                            ///////////////
                            String saltbrnd = jsonObject9.getString("salt_brnd");
                            ////////////
                            String stocklyng = jsonObject9.getString("stock_lyng");
                            Log.e("LISTDAT9",fname+" "+foodlstisac+" "
                                    +foodlstinspctn+" "+storspce+" "+physiclchk+" "
                                    +stkbookcmpare+" "+stksuficnt+" "+ricedal+" "+dal+" "+soyachunkbrnd+" "+saltbrnd+" "+stocklyng);
////////////////////////////////////////////////////////////////////////
                            String awcname = jsonObject10.getString("name");
                            String utensilcondtn = jsonObject10.getString("utensil_condtn");
                            String matcondtn = jsonObject10.getString("mat_condtn");
                            String elvnregstr = jsonObject10.getString("elvn_regstr");
                            /////////
                            String elevnregstrno = jsonObject10.getString("elevn_regstr_no");
                            ////////
                            String babywmachin = jsonObject10.getString("baby_wmachin");
                            String babywmachinuse = jsonObject10.getString("baby_wmachin_use");
                            /////
                            String muactap = jsonObject10.getString("adult_wmachin");
                            String adultwmachinuse = jsonObject10.getString("adult_wmachin_use");
                            //////
                            String heightmeasurtyp = jsonObject10.getString("height_measur_typ");
                            String heightmeasurtypuse = jsonObject10.getString("height_measur_typ_use");
                            String growthchrt = jsonObject10.getString("growth_chrt");
                            String growthchrtfillup = jsonObject10.getString("growth_chrt_fillup");

                            String storecontainr = jsonObject10.getString("store_containr");
                            String suffistorecontainr = jsonObject10.getString("suffi_store_containr");
                            String handwshsoap = jsonObject10.getString("hand_wsh_soap");
                            String handwashsoapuse = jsonObject10.getString("hand_wash_soap_use");

                            Log.e("LISTDAT10",awcname+" "+utensilcondtn+" "
                                    +matcondtn+" "+elvnregstr+" "+elevnregstrno+" "
                                    +babywmachin+" "+babywmachinuse+" "+muactap+" "+adultwmachinuse+" "
                                    +heightmeasurtyp+" "+heightmeasurtypuse+" "+growthchrt+" "+growthchrtfillup+" "+storecontainr+" "
                                    +suffistorecontainr+" "+handwshsoap+" "+handwashsoapuse);
//////////////////////////////////////////////////////////////////////////////////////////////////////
                            String sname = jsonObject11.getString("name");
                            String sishualoy = jsonObject11.getString("sishu_aloy");
                            String blockcornr = jsonObject11.getString("block_cornr");
                            String bookcornr = jsonObject11.getString("book_cornr");
                            String drawcornr = jsonObject11.getString("draw_cornr");
                            String playcornr = jsonObject11.getString("play_cornr");
                            String eccrun = jsonObject11.getString("ecc_run");
                            ///////
                            String eccroutntheme = jsonObject11.getString("ecc_routn_theme");
                            /////
                            String eccactvtytyp = jsonObject11.getString("ecc_actvty_typ");
                            //////
                            String eccactivtyfound = jsonObject11.getString("ecc_activty_found");
                            String awwtlm = jsonObject11.getString("aww_tlm");
                            ///////
                            String proprecckit = jsonObject11.getString("propr_ecc_kit");
                            ////
                            String indvsualchildartwork = jsonObject11.getString("indvsual_child_artwork");
                            ////
                            String indvsualchildactvtyrcd = jsonObject11.getString("indvsual_child_actvty_rcd");
                            String awcdecortnfrecce = jsonObject11.getString("awc_decortn_fr_ecce");
                            /////
                            String awwroutinfollow = jsonObject11.getString("aww_routin_follow");
                            String awwfollowroutine = jsonObject11.getString("aww_follow_routine");
                            String totchldenroll = jsonObject11.getString("tot_chld_enroll");
                            String totchldfoundtoday = jsonObject11.getString("tot_chld_found_today");
                            String chldparticipatecc = jsonObject11.getString("chld_participat_ecc");
                            String assmntcard = jsonObject11.getString("assmnt_card");
                            String assmntcardst =jsonObject11.getString("assmnt_card_st");
                            String lastecckitrcvdt = jsonObject11.getString("last_ecc_kit_rcv_dt");
                            String lasteccdayobserv = jsonObject11.getString("last_ecc_day_observ");
                            //////
                            String pseactivitynm = jsonObject11.getString("pse_activity_nm");
                            String totchldprsn = jsonObject11.getString("tot_chld_prsn");
                            String chldrspnse = jsonObject11.getString("chld_rspnse");
                            String shishualoycmnt = jsonObject11.getString("shishu_aloy_cmnt");
                            ////////////////////no filld//////////////////////////////
                            String pseactvfound = jsonObject11.getString("pse_actv_found");
                            String chldparticipatpse = jsonObject11.getString("chld_participat_pse");

                            Log.e("LISTDAT11",sname+" "+sishualoy+" "
                                    +blockcornr+" "+bookcornr+" "+drawcornr+" "
                                    +playcornr+" "+eccrun+" "+eccroutntheme+" "+eccactvtytyp+" "
                                    +eccactivtyfound+" "+awwtlm+" "+proprecckit+" "+indvsualchildartwork+" "+indvsualchildactvtyrcd+" "
                                    +awcdecortnfrecce+" "+awwroutinfollow+" "
                                    +awwfollowroutine+" "+totchldenroll+" "+totchldfoundtoday+" "
                                    +chldparticipatecc+" "+assmntcard+" "+assmntcardst+" "
                                    +lastecckitrcvdt+" "+lasteccdayobserv+" "+pseactivitynm+" "
                                    +totchldprsn+" "+chldrspnse+" "+shishualoycmnt+" "+pseactvfound+" "+chldparticipatpse);
                           /////////////////////////////////////////////////////////////
                            String snpname =jsonObject12.getString("name");
                            String mrngsnacks = jsonObject12.getString("mrng_snacks");
                            String mrngsnacktyp = jsonObject12.getString("mrng_snack_typ");
                            ///////////////
                            String snpserve = jsonObject12.getString("snp_serve");
                            String snpservetyp = jsonObject12.getString("snp_serve_typ");
                            String chlddprsnt = jsonObject12.getString("chld_prsnt");
                            /////////////////
                            String snpmenu = jsonObject12.getString("snp_serve");

                            String chldprsnt = jsonObject12.getString("chld_prsnt_enroll");
                            String chldprsnttoday = jsonObject12.getString("chld_prsnt_today");
                            //////check
//                            String pmlmprsnt = jsonObject12.getString("pm_lm_prsnt_enroll");
//                            String pmlmprsnttoday = jsonObject12.getString("pm_lm_prsnt_today");
                            String pmlmprsnt = jsonObject12.getString("pm_lm_enroll");
                            String pmlmprsnttoday = jsonObject12.getString("pm_lm_prsnt");
                            ///// check
                            String fdnglstthrmnth = jsonObject12.getString("fdng_lst_thr_mnth");
                            String anyfeedintrptn = jsonObject12.getString("any_feed_intrptn");
                            String snpcmnt = jsonObject12.getString("snp_cmnt");
                            String mrngmenu = jsonObject12.getString("menu_today");
                            //String mrngmenu = jsonObject12.getString("hcm_menu_today");
                            String chldprsntenroll = jsonObject12.getString("chld_prsnt_enroll");
                            String chld_prsnt_today = jsonObject12.getString("chld_prsnt_today");

                            String feedintrpt1m = jsonObject12.getString("feed_intrpt_1m");
                            String feedintrpt1days = jsonObject12.getString("feed_intrpt_1days");
                            String feedintrpt2m = jsonObject12.getString("feed_intrpt_2m");
                            String feedintrpt2days = jsonObject12.getString("feed_intrpt_2days");
                            String feedintrpt3m = jsonObject12.getString("feed_intrpt_3m");
                            String feedintrpt3days = jsonObject12.getString("feed_intrpt_3days");
                            String snpntsrvreasn = jsonObject12.getString("snp_nt_srv_reasn");
                            Log.e("LISTDAT12",snpname+" "+mrngsnacks+" "
                                    +mrngsnacktyp+" "+mrngmenu+" "+snpserve+" "
                                    +snpservetyp+" "+chlddprsnt+" "+snpmenu+" "+chldprsnt+" "
                                    +chldprsnttoday+" "+pmlmprsnt+" "+pmlmprsnttoday+" "+fdnglstthrmnth+" "+anyfeedintrptn+" "
                                    +snpcmnt+" "+"menutoday"+" "
                                    +chldprsntenroll+" "+chld_prsnt_today+" "+feedintrpt1m+
                                    " "+feedintrpt1days+" "+feedintrpt2m+" "+" "+feedintrpt2days+" "+feedintrpt3m+" "+feedintrpt3days+" "+" "+snpntsrvreasn);

                            ////////////////////////////////////////////////////////////////////////////////////////////
                            String nname = jsonObject13.getString("name");
                            String malnurishdchld = jsonObject13.getString("malnurishd_chld");
                            String sanchld = jsonObject13.getString("san_chld");
                            String chldfoundphysicaly = jsonObject13.getString("chld_found_physicaly");
                            String inspctrcmntnutritional = jsonObject13.getString("inspctr_cmnt");

                            Log.e("LISTDAT13",nname+" "+malnurishdchld+" "+sanchld+" "+chldfoundphysicaly);
                            String oawcname = jsonObject14.getString("name");
                            String feedintrupduratn = jsonObject14.getString("feed_intrup_duratn");
                            String supactn = jsonObject14.getString("sup_actn");
                            String cmuntyprticpt = jsonObject14.getString("cmunty_prticpt");
                            String supvisit = jsonObject14.getString("sup_visit");
                            String mthrmeet = jsonObject14.getString("mthr_meet");
                            String awmonitr = jsonObject14.getString("aw_monitr");
                            String othrfunctnvst = jsonObject14.getString("othr_functn_vst");
                            String rcdmaintain = jsonObject14.getString("rcd_maintain");
                            String regularchrg = jsonObject14.getString("regular_chrg");
                            ///fild add a
                          //  String medcnchrglstrcv = jsonObject14.getString("medcn_chrg_lst_rcv");
                            ////fild
                            String preschllstrcv = jsonObject14.getString("pre_schl_lst_rcv");
                            String grwthchrtlstrcv  = jsonObject14.getString("grwth_chrt_lst_rcv");
                            String awcrmrk = jsonObject14.getString("awc_rmrk");
                            String commparticipation = jsonObject14.getString("comm_participation");
                            String lastsupustidate = jsonObject14.getString("last_supusti_date");
                            String medcinkitst = jsonObject14.getString("medcin_kit_st");
                            String medcinkitusest = jsonObject14.getString("medcin_kit_use_st");
                            String medcnchrglstrcv = jsonObject14.getString("awc_gnrl_clr");
                            String medcinkitlstrcv = jsonObject14.getString("medcin_kit_lst_rcv");

                            Log.e("LISTDAT14",oawcname+" "+feedintrupduratn+" "
                                    +supactn+" "+cmuntyprticpt+" "+supvisit+" "+mthrmeet+" "
                                    +awmonitr+" "+othrfunctnvst+" "+rcdmaintain+" "+regularchrg+" "+" "
                                    +preschllstrcv+" "+grwthchrtlstrcv+" "+awcrmrk+" "
                                    +commparticipation+" "+lastsupustidate+" "+medcinkitst+" "+medcinkitusest+" "+medcnchrglstrcv+" "+medcinkitlstrcv);

                            String pesname = jsonObject15.getString("name");
                            String pselstisac = jsonObject15.getString("pse_lst_isac");
                            String pselstinspctn = jsonObject15.getString("pse_lst_inspctn");
                            String pseactvtytyp = jsonObject15.getString("pse_actvty_typ");
                            String pseinspctr = jsonObject15.getString("pse_inspctr");
                            String adequtspace = jsonObject15.getString("adequt_space");
                            Log.e("LISTDAT15",pesname+" "+pselstisac+" "+" "+pselstinspctn+" "+pseactvtytyp+" "+pseinspctr+" "+adequtspace);


//                            Log.e("MYINDATA"," "+msg+" "+cname+" "+distname+" "+projnm+" "+sectrnm+" "+centrename+" "+oname+" "+cdponame+" "
//                                    +cdpophn+" "+acdponame+" "+acdpophn+" "+supname+" "+supphn+workername+" "+workerphn+" "+hlpername+" "+hlperphn+" "+Iname
//                            +userimg+" "+username+" "+userphn+" "+usermail+" "+useradrs);
//                            //Log.e("NEWVALUE",+)

                            SetgetmyInspactionviewList list = new SetgetmyInspactionviewList();
                             list.setMsg(msg);
                             list.setCname(cname);
                             list.setDistname(distname);
                             list.setProjnm(projnm);
                             list.setSectrnm(sectrnm);
                             list.setCentrename(centrename);
                             list.setOname(oname);
                             list.setCdponame(cdponame);
                             list.setCdpophn(cdpophn);
                             list.setAcdponame(acdponame);
                             list.setAcdpophn(acdpophn);
                             list.setSupname(supname);
                             list.setSupphn(supphn);
                             list.setWorkername(workername);
                             list.setWorkerphn(workerphn);
                             list.setHlpername(hlpername);
                             list.setHlperphn(hlperphn);
                             list.setIname(Iname);
                             list.setUserimg(userimg);
                             list.setUsername(username);
                             list.setUserphn(userphn);
                             list.setUsermail(usermail);
                             list.setUseradrs(useradrs);
                             list.setBname(Bname);
                             list.setLstbuildisac(lstbuildisac);
                             list.setLstbuildinspctn(lstbuildinspctn);
                             list.setBuildtyp(buildtyp);
                             list.setBuldrun(buldrun);
                             ///off fund name
                             //list.setFundname(fundname);
                            ///off fund name
                             list.setRentbuild(rentbuild);
                             list.setOthrgovtbuld(othrgovtbuld);
                             list.setBuldcondtn(buldcondtn);
                             list.setBuldinspctncmnt(buldinspctncmnt);
                             list.setTname(Tname);
                             list.setToiltlstisac(toiltlstisac);
                             list.setToiltlstinspctn(toiltlstinspctn);
                             list.setToilt(toilt);
                             list.setToiltuse(toiltuse);
                             list.setToiletcmnt(toiletcmnt);
                             list.setKname(kname);
                             list.setKitchnlstisac(kitchnlstisac);
                             list.setKitchnlstinspctn(kitchnlstinspctn);
                             list.setSprtkitchn(sprtkitchn);
                             list.setCookdone(cookdone);
                             list.setKitchncmnt(kitchncmnt);
                             list.setEname(ename);
                             list.setLstelectricisacrep(lstelectricisacrep);
                             list.setLstelectricinspctn(lstelectricinspctn);
                             list.setElectricavail(electricavail);
                             list.setElctrimode(elctrimode);
                             list.setLight(light);
                             list.setFan(fan);
                             list.setPump(pump);
                             list.setElectrccmnt(electrccmnt);
                             list.setDname(dname);
                             list.setDrnklstisac(drnklstisac);
                             list.setDrnklstinspctn(drnklstinspctn);
                             list.setOwndrnkwater(owndrnkwater);
                             list.setDrinkingwater(drinkingwater);

                             list.setFname(fname);
                             list.setFoodlstisac(foodlstisac);
                             list.setFoodlstinspctn(foodlstinspctn);
                             list.setStorspce(storspce);
                             list.setPhysiclchk(physiclchk);
                             list.setStkbookcmpare(stkbookcmpare);
                             list.setStksuficnt(stksuficnt);
                             list.setRicedal(ricedal);
                             list.setDal(dal);
                             list.setSoyachunkbrnd(soyachunkbrnd);
                             list.setSaltbrnd(saltbrnd);

                             list.setStocklyng(stocklyng);
                             list.setAwcname(awcname);
                             list.setUtensilcondtn(utensilcondtn);
                             list.setMatcondtn(matcondtn);
                             list.setElvnregstr(elvnregstr);
                             list.setElevnregstrno(elevnregstrno);
                             list.setBabywmachin(babywmachin);
                             list.setBabywmachinuse(babywmachinuse);
                             list.setMuactap(muactap);
                             list.setAdultwmachinuse(adultwmachinuse);

                             list.setHeightmeasurtyp(heightmeasurtyp);
                             list.setHeightmeasurtypuse(heightmeasurtypuse);
                             list.setGrowthchrt(growthchrt);
                             list.setGrowthchrtfillup(growthchrtfillup);
                             list.setStorecontainr(storecontainr);
                             list.setSuffistorecontainr(suffistorecontainr);
                             list.setHandwshsoap(handwshsoap);
                             list.setHandwashsoapuse(handwashsoapuse);
                             list.setSname(sname);
                             list.setSishualoy(sishualoy);
                             list.setRicedal(blockcornr);
                             list.setBookcornr(bookcornr);
                             list.setDrawcornr(drawcornr);
                             list.setPlaycornr(playcornr);
                             list.setEccrun(eccrun);
                             list.setEccroutntheme(eccroutntheme);
                             list.setEccactvtytyp(eccactvtytyp);
                             list.setEccactivtyfound(eccactivtyfound);
                             list.setAwwtlm(awwtlm);

                             list.setProprecckit(proprecckit);
                             list.setIndvsualchildartwork(indvsualchildartwork);

                             list.setIndvsualchildactvtyrcd(indvsualchildactvtyrcd);
                             list.setAwcdecortnfrecce(awcdecortnfrecce);
                             list.setAwwroutinfollow(awwroutinfollow);
                             list.setAwwfollowroutine(awwfollowroutine);
                             list.setTotchldenroll(totchldenroll);
                             list.setTotchldfoundtoday(totchldfoundtoday);
                             list.setChldparticipatecc(chldparticipatecc);
                             list.setAssmntcard(assmntcard);
                             list.setAssmntcardst(assmntcardst);
                             list.setLastecckitrcvdt(lastecckitrcvdt);
                             list.setLasteccdayobserv(lasteccdayobserv);

                             list.setPseactivitynm(pseactivitynm);
                             list.setTotchldprsn(totchldprsn);
                             list.setChldrspnse(chldrspnse);
                             list.setShishualoycmnt(shishualoycmnt);
                             list.setSnpname(snpname);
                             list.setMrngsnacks(mrngsnacks);
                             list.setMrngmenu(mrngmenu);
                             list.setSnpserve(snpserve);
                             list.setSnpmenu(snpmenu);
                             list.setChldprsnt(chldprsnt);
                             list.setPmlmprsnt(pmlmprsnt);
                             list.setFdnglstthrmnth(fdnglstthrmnth);
                             list.setAnyfeedintrptn(anyfeedintrptn);
                             list.setSnpcmnt(snpcmnt);
                             list.setNname(nname);
                             list.setMalnurishdchld(malnurishdchld);
                             list.setSanchld(sanchld);
                             list.setOawcname(oawcname);
                             list.setFeedintrupduratn(feedintrupduratn);
                             list.setSupactn(supactn);
                             list.setCmuntyprticpt(cmuntyprticpt);
                             list.setSupvisit(supvisit);
                             list.setMthrmeet(mthrmeet);
                             list.setAwmonitr(awmonitr);
                             list.setOthrfunctnvst(othrfunctnvst);
                             list.setRcdmaintain(rcdmaintain);
                             list.setRegularchrg(regularchrg);
                             list.setMedcnchrglstrcv(medcnchrglstrcv);
                             list.setPreschllstrcv(preschllstrcv);
                             list.setGrwthchrtlstrcv(grwthchrtlstrcv);
                             list.setAwcrmrk(awcrmrk);
                             list.setPesname(pesname);
                             list.setPselstisac(pselstisac);
                             list.setPselstinspctn(pselstinspctn);
                             list.setPseactvtytyp(pseactvtytyp);
                             list.setPseinspctr(pseinspctr);
                             list.setPseactvfound(pseactvfound);
                             list.setChldparticipatpse(chldparticipatpse);
                                list.setMrngsnacktyp(mrngsnacktyp);
                                list.setSnpservetyp(snpservetyp);
                                list.setChlddprsnt(chlddprsnt);
                                list.setChldprsnttoday(chldprsnttoday);
                                list.setPmlmprsnttoday(pmlmprsnttoday);
                                list.setChldprsntenroll(chldprsntenroll);
                                list.setChld_prsnt_today(chld_prsnt_today);
                                list.setFeedintrpt1m(feedintrpt1m);
                                list.setFeedintrpt1days(feedintrpt1days);
                                list.setFeedintrpt2m(feedintrpt2m);
                                list.setFeedintrpt2days(feedintrpt2days);
                                list.setFeedintrpt3m(feedintrpt3m);
                                list.setFeedintrpt3days(feedintrpt3days);
                                list.setSnpntsrvreasn(snpntsrvreasn);
                                list.setSnpntsrvreasn(chldfoundphysicaly);
                                list.setCommparticipation(commparticipation);
                                list.setLastsupustidate(lastsupustidate);
                                list.setMedcinkitst(medcinkitst);
                                list.setMedcinkitusest(medcinkitusest);
                                list.setMedcinkitlstrcv(medcinkitlstrcv);
                                list.setAdequtspace(adequtspace);
                             setgetmyInspactionviewLists.add(list);
                             list.setInspctrcmntnutritional(inspctrcmntnutritional);
                             arrlist.addAll(setgetmyInspactionviewLists);
                             setgetmyInspactionviewListss.add(new SetgetmyInspactionviewListt(
                                    msg,cname,distname,projnm,sectrnm,centrename,oname,cdponame,cdpophn,acdponame,acdpophn,supname,supphn,workername,
                                    workerphn,hlpername,hlperphn,Iname,userimg,username,userphn ,usermail,useradrs,Bname,lstbuildisac,
                                    lstbuildinspctn,buildtyp,buldrun,

                                     rentbuild,othrgovtbuld,buldcondtn,buldinspctncmnt,Tname,
                                    toiltlstisac,toiltlstinspctn,toilt,toiltuse,
                                    toiletcmnt,kname,kitchnlstisac,kitchnlstinspctn,sprtkitchn,cookdone,kitchncmnt,ename,lstelectricisacrep,lstelectricinspctn,
                                     electricavail,elctrimode,light,fan,pump,electrccmnt,dname,drnklstisac,drnklstinspctn,owndrnkwater,
                                     drinkingwater
                                     ,fname,foodlstisac,foodlstinspctn,storspce,physiclchk,stkbookcmpare,stksuficnt,ricedal,dal,soyachunkbrnd,
                                     saltbrnd,
                                     stocklyng
                                     ,awcname,utensilcondtn,matcondtn,elvnregstr,
                                     elevnregstrno,
                                     babywmachin,
                                     babywmachinuse,
                                     muactap,
                                     adultwmachinuse,
                                     heightmeasurtyp,
                                     heightmeasurtypuse,
                                     growthchrt,
                                     growthchrtfillup,
                                     storecontainr,
                                     suffistorecontainr,
                                     handwshsoap,
                                     handwashsoapuse
                                     ,sname,sishualoy,blockcornr,bookcornr,drawcornr,playcornr,eccrun,
                                     eccroutntheme,
                                     eccactvtytyp,
                                     eccactivtyfound,
                                     awwtlm,
                                     proprecckit,
                                     indvsualchildartwork,
                                     indvsualchildactvtyrcd,
                                     awcdecortnfrecce,
                                     awwroutinfollow,
                                     awwfollowroutine,
                                     totchldenroll,
                                     totchldfoundtoday,
                                     chldparticipatecc,
                                     assmntcard,
                                     assmntcardst,
                                     lastecckitrcvdt,
                                     lasteccdayobserv,
                                     pseactivitynm,totchldprsn,chldrspnse,shishualoycmnt,snpname,mrngsnacks,mrngmenu,snpserve,snpmenu
                                     ,chldprsnt,pmlmprsnt,fdnglstthrmnth,anyfeedintrptn,snpcmnt,nname,malnurishdchld,sanchld,oawcname,
                                     feedintrupduratn,supactn,cmuntyprticpt,supvisit,mthrmeet,awmonitr,othrfunctnvst,rcdmaintain,regularchrg
                                     ,medcnchrglstrcv,preschllstrcv,grwthchrtlstrcv,awcrmrk,pesname,pselstisac,pselstinspctn,pseactvtytyp,pseinspctr
                                     ,pseactvfound,chldparticipatpse
                                     ,mrngsnacktyp,
                                     snpservetyp,
                                     chlddprsnt,
                                     chldprsnttoday,
                                     pmlmprsnttoday,
                                     chldprsntenroll,
                                     chld_prsnt_today,
                                     feedintrpt1m,
                                     feedintrpt1days,
                                     feedintrpt2m,
                                     feedintrpt2days,
                                     feedintrpt3m,
                                     feedintrpt3days,
                                     snpntsrvreasn,
                                     chldfoundphysicaly,
                                     commparticipation,
                                     lastsupustidate,
                                     medcinkitst,
                                     medcinkitusest,
                                     medcinkitlstrcv,
                                     adequtspace,
                                     inspctrcmntnutritional

                            ));
                             adaptermyInspactionviewList = new AdaptermyInspactionviewList(getApplicationContext(),setgetmyInspactionviewListss);
                             recyclerView.setAdapter(adaptermyInspactionviewList);
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
                params.put("inspctn_id", insid);
                System.out.println("insid"+params.toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
}
