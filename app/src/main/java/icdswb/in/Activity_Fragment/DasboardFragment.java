package icdswb.in.Activity_Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import icdswb.in.AMFUNWWEBVIEW.AmfunActivitynWebview;
import icdswb.in.ActivityDatabase.AdqueSpaceNetwokchecker;
import icdswb.in.ActivityDatabase.ConditionNetwokchecker;
import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivityDatabase.DrinkingNetwokchecker;
import icdswb.in.ActivityDatabase.EletricityNetwokchecker;
import icdswb.in.ActivityDatabase.FinalSubmitNetworkcheck;
import icdswb.in.ActivityDatabase.FoodNetwokchecker;
import icdswb.in.ActivityDatabase.InspactioninsertNetworkercheker;
import icdswb.in.ActivityDatabase.InspectionpersonpresentNetworkcheker;
import icdswb.in.ActivityDatabase.KitchenNetwokchecker;
import icdswb.in.ActivityDatabase.NutritionNetworkChecker;
import icdswb.in.ActivityDatabase.OtherinspactionNetworkchecker;
import icdswb.in.ActivityDatabase.SPNNetwokchecker;
import icdswb.in.ActivityDatabase.ShishuAloyNetwokchecker;
import icdswb.in.ActivityDatabase.Sportnetworkcheck;
import icdswb.in.ActivityDatabase.ToiletNetwokchecker;
import icdswb.in.AmfunActivity;
import icdswb.in.BuildingDetailsActivity;
import icdswb.in.CaptureImageListView;
import icdswb.in.ChangepasswordActivity;
import icdswb.in.InspectionTabActivity;
import icdswb.in.MyInspactionActivity;
import icdswb.in.MyprofileActivity;
import icdswb.in.PlaningmainActivity;
import icdswb.in.R;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ADEQUATESPPSESTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ADEQUATESPPSESYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ADEQUTSPACEINSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_BUILDINGSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CONDITIONOFEQIPMENT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CONDITIONOFEQIPMENTINSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_CONDITIONSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DRINKINGWATERINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DRINKINGWATERSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_DRINKINGWATERSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ELECTRICITYSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ELECTRITY;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_ELECTRITYINSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FINALSUBINS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FINALSUBMITSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FINALSUBTABLE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FLAG;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FOODINSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FOODSTATUSSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_FOODSTORESYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSIDSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSIDTOILETSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPECTIONPERSONPRESENT;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_KITCHENINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_KITCHENSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_KITCHENSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NUTRITIONALSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NUTRITIONALTABLE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NUTRITIONALTINS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_OTHERINSPECTIONINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_OTHERINSPECTIONSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_OTHERINSPECTIONTABLENAME;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_PROCESS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SHISHUALOYINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SHISHUALOYNAME;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SHISHUALOYSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SPNINSID;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SPNSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SPNTABLE;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_SPORTSYNC;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_STATUSBUILDING;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_TOILETSTATUS;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_TOILETSYNC;


public class DasboardFragment extends Fragment implements View.OnClickListener{
    public DasboardFragment() {
    }
    CardView card_viewprofile,card_viewplaning,
            card_viewInspection,card_viewNearest,
            card_viewmyInspection,card_viewChangePassword,amfunId;
    private BuildingDetailsActivity.BuildingNetworkStateCheckerr buildingNetworkStateCheckerr;
    private InspactioninsertNetworkercheker inspactioninsertNetworkercheker;
    private InspectionpersonpresentNetworkcheker inspectionpersonpresentNetworkcheker;
    private ToiletNetwokchecker toiletNetwokchecker;
    private KitchenNetwokchecker kitchenNetwokchecker;
    private AdqueSpaceNetwokchecker adqueSpaceNetwokchecker;
    private EletricityNetwokchecker eletricityNetwokchecker;
    private DrinkingNetwokchecker drinkingNetwokchecker;
    private FoodNetwokchecker foodNetwokchecker;
    private ConditionNetwokchecker conditionNetwokchecker;
    private ShishuAloyNetwokchecker shishuAloyNetwokchecker;
    private SPNNetwokchecker spnNetwokchecker;
    private NutritionNetworkChecker nutriationnetwokchecker;
    private OtherinspactionNetworkchecker otherinspactionNetworkchecker;
    private FinalSubmitNetworkcheck finalSubmitNetworkcheck;
    private Sportnetworkcheck sportnetworkcheck;
    DatabaseHelper helper;
    String flagg = "2";
    String insidsync,buildingstatus;
    String STATUS = "1";
    String idd = "2";
    String userID;
    String flaggrecord= "2";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inspactioninsertNetworkercheker = new InspactioninsertNetworkercheker();
        getActivity().registerReceiver(inspactioninsertNetworkercheker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        inspectionpersonpresentNetworkcheker = new InspectionpersonpresentNetworkcheker();
        getActivity().registerReceiver(inspectionpersonpresentNetworkcheker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        buildingNetworkStateCheckerr = new BuildingDetailsActivity.BuildingNetworkStateCheckerr();
        getActivity().registerReceiver(buildingNetworkStateCheckerr, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        toiletNetwokchecker = new ToiletNetwokchecker();
        getActivity().registerReceiver(toiletNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        kitchenNetwokchecker = new KitchenNetwokchecker();
        getActivity().registerReceiver(kitchenNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        adqueSpaceNetwokchecker =new AdqueSpaceNetwokchecker();
        getActivity().registerReceiver(adqueSpaceNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        eletricityNetwokchecker = new EletricityNetwokchecker();
        getActivity().registerReceiver(eletricityNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        drinkingNetwokchecker =new DrinkingNetwokchecker();
        getActivity().registerReceiver(drinkingNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        foodNetwokchecker = new FoodNetwokchecker();
        getActivity().registerReceiver(foodNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        conditionNetwokchecker =new ConditionNetwokchecker();
        getActivity().registerReceiver(conditionNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        shishuAloyNetwokchecker = new ShishuAloyNetwokchecker();
        getActivity().registerReceiver(shishuAloyNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        spnNetwokchecker =new SPNNetwokchecker();
        getActivity().registerReceiver(spnNetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        nutriationnetwokchecker =new NutritionNetworkChecker();
        getActivity().registerReceiver(nutriationnetwokchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        otherinspactionNetworkchecker = new OtherinspactionNetworkchecker();
        getActivity().registerReceiver(otherinspactionNetworkchecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        finalSubmitNetworkcheck = new FinalSubmitNetworkcheck();
        getActivity().registerReceiver(finalSubmitNetworkcheck, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        sportnetworkcheck = new Sportnetworkcheck();
        getActivity().registerReceiver(sportnetworkcheck, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        helper = new DatabaseHelper(getContext());
        String query = "SELECT * FROM " + TABLE_PROCESS + " where " + TABLE_FLAG + "=" +flagg;
        helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROCESSID));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBDISTID));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBPROJECTID));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBSECTORID));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DBCENTERID));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PROJECT));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DISTRIC));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SECTOR));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CENTER));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CURRENDATE));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERID));
                 cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FLAG));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLATLOCATION));
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLONGLOCATION));
                insidsync =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_INSPACTIONID));
                RemoveBuilding(insidsync);
                RemoveToilate(insidsync);
                InformationkitchenRemove(insidsync);
                AdequatesoacepseRemove(insidsync);
                ElatricityRemove(insidsync);
                DrinkingwaterRemove(insidsync);
                FoodstoredRemove(insidsync);
                ConditionofequipmentothersRemove(insidsync);
                ShishualoyRemove(insidsync);
                SpnRemove(insidsync);
                NutritionalRemove(insidsync);
                OtherinspectionRemove(insidsync);
                INSPECTIONPERSONPRESENTREMOVE(insidsync);
                FINALSUBMITSYNC(insidsync);
                Log.e("INSPACTIONID",insidsync);

            }
            while (cursor.moveToNext());

        }

        helper = new DatabaseHelper(getContext());
        Cursor us = helper.getLoginData();
        if (us.moveToFirst()) {
            do {
                userID = us.getString(us.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                Log.e("useridBb",userID);
            } while (us.moveToNext());
        }

        helper = new DatabaseHelper(getContext());
        Cursor c = helper.getReadableDatabase().
                rawQuery("select * from sportsync where userplan = ? and sportstatus = ?", new String[]{userID,STATUS});
        if (c.moveToFirst()) {
            do {
                insidsync = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_SPORTSINS));
                STATUS =  c.getString(c.getColumnIndex(DatabaseHelper.TABLE_SPORTSTATUS));
//                Log.e("userid",userID+" "+insidsync);
//                awcsdtls(insidsync,userID);
//                db.execSQL("delete from "+TABLE_NAMEAWCSDTL+" where allinspactionid='"+insidsync+"'");
//                processDelete_Inspection(insidsync,userID,flaggrecord);
//                db.execSQL("delete from "+TABLE_PROCESS+" where flaggrecord='"+flaggrecord+"'");
//                listcheckboxDelete(insidsync,userID);
//                db.execSQL("delete from "+TABLE_ALLINSPECTIONFLAG+" where allinspactionid='"+insidsync+"'");
               db.execSQL("delete from "+TABLE_SPORTSYNC+" where sportstatus='"+STATUS+"'");
            }while (c.moveToNext());
        }


//        String MY_QUERY = "SELECT * FROM awcsprocess.inspactionid, tablebuildingsync.insidsync  INNER JOIN tablebuildingsync  ON awcsprocess.inspactionid = tablebuildingsync.insidsync WHERE awcsprocess.flag='2' and tablebuildingsync.buildingstatus ='1'";
//        helper = new DatabaseHelper(getContext());
//        SQLiteDatabase inerjoin = helper.getReadableDatabase();
//        Cursor c = inerjoin.rawQuery(MY_QUERY,null);
//        if (c.moveToFirst()){
//            do {
//                buildingstatus =   cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_STATUSBUILDING));
//            }
//            while (c.isAfterLast());
//            Log.e("STATUSBUILDING",buildingstatus);
//        }


        String BULD = "SELECT * FROM tablebuildingsync WHERE buildingstatus=" + STATUS;
        SQLiteDatabase dbd = helper.getReadableDatabase();
        Cursor  cc = dbd.rawQuery(BULD,null);
        if (cc.moveToFirst()) {
            do {
                String Id= cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_BUILDINGIDSYNC));
                buildingstatus = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_STATUSBUILDING));
                Log.e("BUILDINGDELETE"," "+Id+" "+buildingstatus);
                Log.e("STATUSS"," "+Id+" "+buildingstatus);
                    }
            while (cc.moveToNext());
        }
        String TOILATE = "SELECT * FROM toiletsync WHERE toiletstatus=" + STATUS;
        SQLiteDatabase toilate = helper.getReadableDatabase();
        Cursor  ccc = toilate.rawQuery(TOILATE,null);
        if (ccc.moveToFirst()) {
            do {
                String Id= ccc.getString(ccc.getColumnIndex(DatabaseHelper.TABLE_TOILETIDSYNC));
                String stoilate = ccc.getString(ccc.getColumnIndex(DatabaseHelper.TABLE_TOILETSTATUS));
                Log.e("TOILATEDELETE",Id+" "+stoilate);
                Log.e("STATUSS",Id+" "+stoilate);
            }
            while (ccc.moveToNext());
        }
        String KITCHEN = "SELECT * FROM kitchensync WHERE kitchenstatus=" + STATUS;
        SQLiteDatabase KIT = helper.getReadableDatabase();
        Cursor  kit = KIT.rawQuery(KITCHEN,null);
        if (kit.moveToFirst()) {
            do {
                String Id= kit.getString(kit.getColumnIndex(DatabaseHelper.TABLE_KITCHENIDSYNC));
                String kitt = kit.getString(kit.getColumnIndex(DatabaseHelper.TABLE_KITCHENSTATUS));
                Log.e("KITCHENDELETE",Id+" "+kitt);
                Log.e("STATUSS",Id+" "+kitt);
            }
            while (ccc.moveToNext());
        }

        String ADEQUATESOACEPSE = "SELECT * FROM adequatesppsesync WHERE adequatesppsestatus=" + STATUS;
        SQLiteDatabase ADEQ = helper.getReadableDatabase();
        Cursor  ADEQQ = ADEQ.rawQuery(ADEQUATESOACEPSE,null);
        if (ADEQQ.moveToFirst()) {
            do {
                String Id= ADEQQ.getString(ADEQQ.getColumnIndex(DatabaseHelper.TABLE_ADEQUATESPPSEID));
                String adq = ADEQQ.getString(ADEQQ.getColumnIndex(DatabaseHelper.TABLE_ADEQUATESPPSESTATUS));
                Log.e("KITCHENDELETE",Id+" "+adq);
                Log.e("STATUSS",Id+" "+adq);
            }
            while (ccc.moveToNext());
        }

        String Elatricity = "SELECT * FROM electrity WHERE eletricitystatus=" + STATUS;
        SQLiteDatabase ELE = helper.getReadableDatabase();
        Cursor  ELEE = ELE.rawQuery(Elatricity,null);
        if (ELEE.moveToFirst()) {
            do {
                String Id= ELEE.getString(ELEE.getColumnIndex(DatabaseHelper.TABLE_ELECTRITYID));
                String ele = ELEE.getString(ELEE.getColumnIndex(DatabaseHelper.TABLE_ELECTRICITYSTATUS));
                Log.e("KITCHENDELETE",Id+" "+ele);
                Log.e("STATUSS",Id+" "+ele);
            }
            while (ccc.moveToNext());
        }

        String DRINKINGWATER = "SELECT * FROM drinkingwatersync WHERE drinkingwaterstatus=" + STATUS;
        SQLiteDatabase drink = helper.getReadableDatabase();
        Cursor  DRINK = drink.rawQuery(DRINKINGWATER,null);
        if (DRINK.moveToFirst()) {
            do {
                String Id= DRINK.getString(DRINK.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERIDSYNC));
                String drinkk = DRINK.getString(DRINK.getColumnIndex(DatabaseHelper.TABLE_DRINKINGWATERSTATUS));
                Log.e("KITCHENDELETE",Id+" "+drinkk);
                Log.e("STATUSS",Id+" "+drinkk);
            }
            while (ccc.moveToNext());
        }

        String FOODSTORED = "SELECT * FROM foodstoresync WHERE foodstatussync=" + STATUS;
        SQLiteDatabase food = helper.getReadableDatabase();
        Cursor  foodd = food.rawQuery(FOODSTORED,null);
        if (foodd.moveToFirst()) {
            do {
                String Id= foodd.getString(foodd.getColumnIndex(DatabaseHelper.TABLE_FOODSTOREIDSYNC));
                String fo = foodd.getString(foodd.getColumnIndex(DatabaseHelper.TABLE_FOODSTATUSSYNC));
                Log.e("KITCHENDELETE",Id+" "+fo);
                Log.e("STATUSS",Id+" "+fo);
            }
            while (ccc.moveToNext());
        }

        String CONDITIONOFEQUIPMENTOTHERS = "SELECT * FROM conditionofeqipment WHERE conditionstatus=" + STATUS;
        SQLiteDatabase condi = helper.getReadableDatabase();
        Cursor  con = condi.rawQuery(CONDITIONOFEQUIPMENTOTHERS,null);
        if (con.moveToFirst()) {
            do {
                String Id= con.getString(con.getColumnIndex(DatabaseHelper.TABLE_CONDITIONOFEQIPMENTID));
                String coon = con.getString(con.getColumnIndex(DatabaseHelper.TABLE_CONDITIONSTATUS));
                Log.e("KITCHENDELETE",Id+" "+coon);
                Log.e("STATUSS",Id+" "+coon);
            }
            while (ccc.moveToNext());
        }

        String SHISHUALOY = "SELECT * FROM shishualoy WHERE shishualoystatus=" + STATUS;
        SQLiteDatabase shishu = helper.getReadableDatabase();
        Cursor  sishu = shishu.rawQuery(SHISHUALOY,null);
        if (sishu.moveToFirst()) {
            do {
                String Id= sishu.getString(sishu.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYID));
                String sishuu = sishu.getString(sishu.getColumnIndex(DatabaseHelper.TABLE_SHISHUALOYSTATUS));
                Log.e("KITCHENDELETE",Id+" "+sishuu);
                Log.e("STATUSS",Id+" "+sishuu);
            }
            while (ccc.moveToNext());
        }

        String SPN = "SELECT * FROM spntable WHERE spnstatus=" + STATUS;
        SQLiteDatabase snp = helper.getReadableDatabase();
        Cursor  snpp = snp.rawQuery(SPN,null);
        if (snpp.moveToFirst()) {
            do {
                String Id= snpp.getString(snpp.getColumnIndex(DatabaseHelper.TABLE_SPNTABLEID));
                String snnp = snpp.getString(snpp.getColumnIndex(DatabaseHelper.TABLE_SPNSTATUS));
                Log.e("KITCHENDELETE",Id+" "+snnp);
                Log.e("STATUSS",Id+" "+snnp);
            }
            while (ccc.moveToNext());
        }

        String NUTRITIONAL = "SELECT * FROM nutritionaltable WHERE nutritionalstatus=" + STATUS;
        SQLiteDatabase nutr = helper.getReadableDatabase();
        Cursor  nutation = nutr.rawQuery(NUTRITIONAL,null);
        if (nutation.moveToFirst()) {
            do {
                String Id= nutation.getString(nutation.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALTID));
                String nutrat = nutation.getString(nutation.getColumnIndex(DatabaseHelper.TABLE_NUTRITIONALSTATUS));
                Log.e("KITCHENDELETE",Id+" "+nutrat);
                Log.e("STATUSS",Id+" "+nutrat);
            }
            while (ccc.moveToNext());
        }
        String OTHERINSPECTION = "SELECT * FROM otherinspection WHERE otherinspectionstatus=" + STATUS;
        SQLiteDatabase other = helper.getReadableDatabase();
        Cursor  otherr = other.rawQuery(OTHERINSPECTION,null);
        if (otherr.moveToFirst()) {
            do {
                String Id= otherr.getString(otherr.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONTABLEID));
                String otherrr = otherr.getString(otherr.getColumnIndex(DatabaseHelper.TABLE_OTHERINSPECTIONSTATUS));
                Log.e("KITCHENDELETE",Id+" "+otherrr);
                Log.e("STATUSS",Id+" "+otherrr);
            }
            while (ccc.moveToNext());
        }

        String INSPECTIONPERSONPRESENT = "SELECT * FROM inspectionpersonpresent WHERE inspectionpersonpresentstatus=" + STATUS;
        SQLiteDatabase ins = helper.getReadableDatabase();
        Cursor  inss = ins.rawQuery(INSPECTIONPERSONPRESENT,null);
        if (inss.moveToFirst()) {
            do {
                String Id= inss.getString(inss.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTID));
                String insss = inss.getString(inss.getColumnIndex(DatabaseHelper.TABLE_INSPECTIONPERSONPRESENTSTATUS));
                Log.e("KITCHENDELETE",Id+" "+insss);
                Log.e("STATUSSs",Id+" "+insss);
            }
            while (inss.moveToNext());
        }

        Cursor res = helper.getAwcDtls();
        if (res.moveToFirst()) {
            do {
                String Id= res.getString(res.getColumnIndex(DatabaseHelper.TABLE_ID));
                Log.e("STATUSST"," "+Id);
            }
            while (res.moveToNext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_dasbord, container, false);
        card_viewprofile = (CardView)rootView.findViewById(R.id.card_viewprofile);
        card_viewplaning = (CardView)rootView.findViewById(R.id.card_viewplaning);
        card_viewInspection = (CardView)rootView.findViewById(R.id.card_viewInspection);
        card_viewNearest = (CardView)rootView.findViewById(R.id.card_viewNearest);
        card_viewmyInspection = (CardView)rootView.findViewById(R.id.card_viewmyInspection);
        card_viewChangePassword = (CardView)rootView.findViewById(R.id.card_viewChangePassword);
        amfunId = (CardView)rootView.findViewById(R.id.amfunId);
        card_viewprofile.setOnClickListener(this);
        card_viewplaning.setOnClickListener(this);
        card_viewInspection.setOnClickListener(this);
        card_viewNearest.setOnClickListener(this);
        card_viewmyInspection.setOnClickListener(this);
        card_viewChangePassword.setOnClickListener(this);
        amfunId.setOnClickListener(this);
        return rootView;
    }
    public boolean isNetworkAvailable() {
        boolean connect=false;
        ConnectivityManager conMgr =  (ConnectivityManager)getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            connect=false;
        }else{
            connect= true;
        }
        return connect;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_viewprofile:
                if (isNetworkAvailable()){
                    Intent intent = new Intent(getActivity(), MyprofileActivity.class);
                    startActivity(intent);
                }
                else {

                }
                break;
            case R.id.card_viewplaning:
                Intent intent1 = new Intent(getActivity(), PlaningmainActivity.class);
                startActivity(intent1);
                break;
            case R.id.card_viewInspection:
                Intent intent2 = new Intent(getActivity(), InspectionTabActivity.class);
                startActivity(intent2);
                break;
            case R.id.card_viewNearest:
                Intent intent6 = new Intent(getActivity(), CaptureImageListView.class);
                startActivity(intent6);
                break;
            case R.id.card_viewmyInspection:
                Intent intent3 = new Intent(getActivity(), MyInspactionActivity.class);
                startActivity(intent3);
                break;
                case R.id.card_viewChangePassword:
                Intent intent5 = new Intent(getActivity(), ChangepasswordActivity.class);
                startActivity(intent5);
                break;
            case R.id.amfunId:
                Intent intent7 = new Intent(getActivity(), AmfunActivitynWebview.class);
                startActivity(intent7);
                break;
            default:
        }
    }
    public void RemoveBuilding(String insidsync){
        String query = "DELETE  FROM " + TABLE_BUILDINGSYNC + " where " + TABLE_INSIDSYNC + "=" +insidsync+ " and " +TABLE_STATUSBUILDING+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        Log.e("BUILDINGDELETE",insidsync);
        String buildingidsync = "";
        String dbdistidsync = "";
        String dbprojectidsync = "";
        String dbsectoridsync = "";
        String dbcenteridsync = "";
        String builttypsync = "";
        String builtruninsync = "";
        ////// own building fund/////////
       //String ownbuildfundsync = "";
      ////// own building fund/////////
        String rentbuildinsync = "";
        String othrgovtbuildinsync = "";
        String buildconditn = "";
        String inspctrcmnt = "";
        String useridsync = "";
        insidsync = "";
        String curdatesync = "";
        String curtimesync = "";
        String latisacreportsync = "";
        String lstinspctnbuldrepsync = "";
        String buildingstatus = "";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                buildingidsync = c.getString(c.getColumnIndex("buildingidsync"));
                dbdistidsync = c.getString(c.getColumnIndex("dbdistidsync"));
                dbprojectidsync = c.getString(c.getColumnIndex("dbprojectidsync"));
                dbsectoridsync = c.getString(c.getColumnIndex("dbsectoridsync"));
                dbcenteridsync = c.getString(c.getColumnIndex("dbcenteridsync"));
                builttypsync = c.getString(c.getColumnIndex("builttypsync"));
                builtruninsync = c.getString(c.getColumnIndex("builtruninsync"));
                ////// own building fund/////////
               // ownbuildfundsync = c.getString(c.getColumnIndex("ownbuildfundsync"));
                ////// own building fund/////////
                rentbuildinsync = c.getString(c.getColumnIndex("rentbuildinsync"));
                othrgovtbuildinsync = c.getString(c.getColumnIndex("othrgovtbuildinsync"));
                buildconditn = c.getString(c.getColumnIndex("buildconditn"));
                inspctrcmnt = c.getString(c.getColumnIndex("inspctrcmnt"));
                useridsync = c.getString(c.getColumnIndex("useridsync"));
                insidsync = c.getString(c.getColumnIndex("insidsync"));
                curdatesync = c.getString(c.getColumnIndex("curdatesync"));
                curtimesync = c.getString(c.getColumnIndex("curtimesync"));
                latisacreportsync = c.getString(c.getColumnIndex("latisacreportsync"));
                lstinspctnbuldrepsync = c.getString(c.getColumnIndex("lstinspctnbuldrepsync"));
                buildingstatus = c.getString(c.getColumnIndex("buildingstatus"));
                if (!buildingidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + buildingidsync + "'");
                }
                if (!dbdistidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + dbdistidsync + "'");
                }
                if (!dbprojectidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + dbprojectidsync + "'");
                }
                if (!dbsectoridsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + dbsectoridsync + "'");
                }
                if (!dbcenteridsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + dbcenteridsync + "'");
                }
                if (!builttypsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + builttypsync + "'");
                }
                if (!builtruninsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + builtruninsync + "'");
                }
                ////// own building fund/////////
//                if (!ownbuildfundsync.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + ownbuildfundsync + "'");
//                }
                ////// own building fund/////////

                if (!rentbuildinsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + rentbuildinsync + "'");
                }
                if (!othrgovtbuildinsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + othrgovtbuildinsync + "'");
                }
                if (!buildconditn.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + buildconditn + "'");
                }
                if (!inspctrcmnt.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspctrcmnt + "'");
                }
                if (!useridsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + useridsync + "'");
                }
                if (!insidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + insidsync + "'");
                }
                if (!curdatesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + curdatesync + "'");
                }
                if (!curtimesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + curtimesync + "'");
                }
                if (!latisacreportsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + latisacreportsync + "'");
                }
                if (!lstinspctnbuldrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctnbuldrepsync + "'");
                }
                if (!buildingstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + buildingstatus + "'");
                }
                c.moveToNext();
                Log.e("BUILDINGDELETE",insidsync+" "+buildingstatus);
            }
            c.close();

        }
    }

    public void RemoveToilate(String insidsync){
        String query = "DELETE  FROM " + TABLE_TOILETSYNC + " where " + TABLE_INSIDTOILETSYNC + "=" +insidsync+ " and " +TABLE_TOILETSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String toiletidsync = "";
        String insidtoiletsync = "";
        String yntoiletsync = "";
        String toiletusablesync = "";
        String inspctrcmntsync = "";
        String curTimesync = "";
        String lastisacrepsync = "";
        String lstinspctntoiletrepsync = "";
        String toiletstatus = "";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                toiletidsync = c.getString(c.getColumnIndex("toiletidsync"));
                insidtoiletsync = c.getString(c.getColumnIndex("insidtoiletsync"));
                yntoiletsync = c.getString(c.getColumnIndex("yntoiletsync"));
                toiletusablesync = c.getString(c.getColumnIndex("toiletusablesync"));
                inspctrcmntsync = c.getString(c.getColumnIndex("inspctrcmntsync"));
                curTimesync = c.getString(c.getColumnIndex("curTimesync"));
                lastisacrepsync = c.getString(c.getColumnIndex("lastisacrepsync"));
                lstinspctntoiletrepsync = c.getString(c.getColumnIndex("lstinspctntoiletrepsync"));
                toiletstatus = c.getString(c.getColumnIndex("toiletstatus"));
                if (!toiletidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + toiletidsync + "'");
                }
                if (!insidtoiletsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + insidtoiletsync + "'");
                }
                if (!yntoiletsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + yntoiletsync + "'");
                }
                if (!toiletusablesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + toiletusablesync + "'");
                }
                if (!inspctrcmntsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspctrcmntsync + "'");
                }
                if (!curTimesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + curTimesync + "'");
                }
                if (!lastisacrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lastisacrepsync + "'");
                }
                if (!lstinspctntoiletrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lstinspctntoiletrepsync + "'");
                }
                if (!toiletstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + toiletstatus + "'");
                }
                c.moveToNext();
                Log.e("TOILATEDELETE",insidsync+" "+toiletstatus);
            }
            c.close();

        }
    }

    public void InformationkitchenRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_KITCHENSYNC + " where " + TABLE_KITCHENINSID + "=" +insidsync+ " and " +TABLE_KITCHENSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String kitchenidsync = "";
        String kitcheninsidsync = "";
        String seprtkitchnsync = "";
        String seprtcookdoneitchnsync = "";
        String toiletinspctrcmntsync = "";
        String instimesync = "";
        String toiletlastisacrepsync = "";
        String lastinspctnrepsync = "";
        String kitchenstatus = "";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                kitchenidsync = c.getString(c.getColumnIndex("kitchenidsync"));
                kitcheninsidsync = c.getString(c.getColumnIndex("kitcheninsidsync"));
                seprtkitchnsync = c.getString(c.getColumnIndex("seprtkitchnsync"));
                seprtcookdoneitchnsync = c.getString(c.getColumnIndex("seprtcookdoneitchnsync"));
                toiletinspctrcmntsync = c.getString(c.getColumnIndex("toiletinspctrcmntsync"));
                instimesync = c.getString(c.getColumnIndex("instimesync"));
                toiletlastisacrepsync = c.getString(c.getColumnIndex("toiletlastisacrepsync"));
                lastinspctnrepsync = c.getString(c.getColumnIndex("lastinspctnrepsync"));
                kitchenstatus = c.getString(c.getColumnIndex("kitchenstatus"));

                if (!kitchenidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + kitchenidsync + "'");
                }
                if (!kitcheninsidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + kitcheninsidsync + "'");
                }
                if (!seprtkitchnsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + seprtkitchnsync + "'");
                }
                if (!seprtcookdoneitchnsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + seprtcookdoneitchnsync + "'");
                }
                if (!toiletinspctrcmntsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + toiletinspctrcmntsync + "'");
                }
                if (!instimesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + instimesync + "'");
                }
                if (!toiletlastisacrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + toiletlastisacrepsync + "'");
                }
                if (!lastinspctnrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lastinspctnrepsync + "'");
                }
                if (!kitchenstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + kitchenstatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }

    public void AdequatesoacepseRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_ADEQUATESPPSESYNC + " where " + TABLE_ADEQUTSPACEINSIDSYNC + "=" +insidsync+ " and " +TABLE_ADEQUATESPPSESTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String adequatesppseid = "";
        String adequtspaceinsidsync = "";
        String adequtspacesync = "";
        String pseactvtytypsync = "";
        String adequtspacecurTimesync = "";
        String adequtspaceinspctrcmntsync = "";
        String adqutspacelastisacrepsync = "";
        String adqutspacelastinspctnrepsync = "";
        String adequatesppsestatus = "";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                adequatesppseid = c.getString(c.getColumnIndex("adequatesppseid"));
                adequtspaceinsidsync = c.getString(c.getColumnIndex("adequtspaceinsidsync"));
                adequtspacesync = c.getString(c.getColumnIndex("adequtspacesync"));
                pseactvtytypsync = c.getString(c.getColumnIndex("pseactvtytypsync"));
                adequtspacecurTimesync = c.getString(c.getColumnIndex("adequtspacecurTimesync"));
                adequtspaceinspctrcmntsync = c.getString(c.getColumnIndex("adequtspaceinspctrcmntsync"));
                adqutspacelastisacrepsync = c.getString(c.getColumnIndex("adqutspacelastisacrepsync"));
                adqutspacelastinspctnrepsync = c.getString(c.getColumnIndex("adqutspacelastinspctnrepsync"));
                adequatesppsestatus = c.getString(c.getColumnIndex("adequatesppsestatus"));

                if (!adequatesppseid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adequatesppseid + "'");
                }
                if (!adequtspaceinsidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adequtspaceinsidsync + "'");
                }
                if (!adequtspacesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adequtspacesync + "'");
                }
                if (!pseactvtytypsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + pseactvtytypsync + "'");
                }
                if (!adequtspacecurTimesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adequtspacecurTimesync + "'");
                }
                if (!adequtspaceinspctrcmntsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adequtspaceinspctrcmntsync + "'");
                }
                if (!adqutspacelastisacrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adqutspacelastisacrepsync + "'");
                }
                if (!adqutspacelastinspctnrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adqutspacelastinspctnrepsync + "'");
                }
                if (!adequatesppsestatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adequatesppsestatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }

    public void ElatricityRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_ELECTRITY + " where " + TABLE_ELECTRITYINSIDSYNC + "=" +insidsync+ " and " +TABLE_ELECTRICITYSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String electrityid = "";
        String electrityinsidsync = "";
        String electricavailsync = "";
        String emodesync = "";
        String lighttypesync = "";
        String fantypesync = "";
        String pumpovrhdsync = "";
        String inspctrcmntelectricitysync = "";
        String cuttimeeletricitysync = "";
        String lastisacrepeletricitysync ="";
        String lastinspctnrepeletricitysync ="";
        String eletricitystatus = "";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                electrityid = c.getString(c.getColumnIndex("electrityid"));
                electrityinsidsync = c.getString(c.getColumnIndex("electrityinsidsync"));
                electricavailsync = c.getString(c.getColumnIndex("electricavailsync"));
                emodesync = c.getString(c.getColumnIndex("emodesync"));
                lighttypesync = c.getString(c.getColumnIndex("lighttypesync"));
                fantypesync = c.getString(c.getColumnIndex("fantypesync"));
                pumpovrhdsync = c.getString(c.getColumnIndex("pumpovrhdsync"));
                inspctrcmntelectricitysync = c.getString(c.getColumnIndex("inspctrcmntelectricitysync"));
                cuttimeeletricitysync = c.getString(c.getColumnIndex("cuttimeeletricitysync"));
                lastisacrepeletricitysync = c.getString(c.getColumnIndex("lastisacrepeletricitysync"));
                lastinspctnrepeletricitysync = c.getString(c.getColumnIndex("lastinspctnrepeletricitysync"));
                eletricitystatus = c.getString(c.getColumnIndex("eletricitystatus"));
                if (!electrityid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + electrityid + "'");
                }
                if (!electrityinsidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + electrityinsidsync + "'");
                }
                if (!electricavailsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + electricavailsync + "'");
                }
                if (!emodesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + emodesync + "'");
                }
                if (!lighttypesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lighttypesync + "'");
                }
                if (!fantypesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + fantypesync + "'");
                }
                if (!pumpovrhdsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + pumpovrhdsync + "'");
                }
                if (!inspctrcmntelectricitysync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspctrcmntelectricitysync + "'");
                }
                if (!cuttimeeletricitysync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + cuttimeeletricitysync + "'");
                }
                if (!lastisacrepeletricitysync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lastisacrepeletricitysync + "'");
                }
                if (!lastinspctnrepeletricitysync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lastinspctnrepeletricitysync + "'");
                }
                if (!eletricitystatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + eletricitystatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }
    public void DrinkingwaterRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_DRINKINGWATERSYNC + " where " + TABLE_DRINKINGWATERINSID + "=" +insidsync+ " and " +TABLE_DRINKINGWATERSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String drinkingwateridsync = "";
        String drinkingwaterinsid = "";
        String owndrinkwatersync = "";
        String drinkingwatercurtimesync = "";
        String drinkingwaterlastisacrepsync = "";
        String lastinspctnrepdrinkingsync = "";
        String drinkwaterremark ="";
        String drinkingwaterstatus = "";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                drinkingwateridsync = c.getString(c.getColumnIndex("drinkingwateridsync"));
                drinkingwaterinsid = c.getString(c.getColumnIndex("drinkingwaterinsid"));
                owndrinkwatersync = c.getString(c.getColumnIndex("electricavailsync"));
                drinkingwatercurtimesync = c.getString(c.getColumnIndex("drinkingwatercurtimesync"));
                drinkingwaterlastisacrepsync = c.getString(c.getColumnIndex("drinkingwaterlastisacrepsync"));
                lastinspctnrepdrinkingsync = c.getString(c.getColumnIndex("lastinspctnrepdrinkingsync"));
                drinkwaterremark = c.getString(c.getColumnIndex("drinkwaterremark"));
                drinkingwaterstatus = c.getString(c.getColumnIndex("drinkingwaterstatus"));
                if (!drinkingwateridsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + drinkingwateridsync + "'");
                }
                if (!drinkingwaterinsid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + drinkingwaterinsid + "'");
                }
                if (!owndrinkwatersync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + owndrinkwatersync + "'");
                }
                if (!drinkingwatercurtimesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + drinkingwatercurtimesync + "'");
                }
                if (!drinkingwaterlastisacrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + drinkingwaterlastisacrepsync + "'");
                }
                if (!lastinspctnrepdrinkingsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lastinspctnrepdrinkingsync + "'");
                }
                if (!drinkwaterremark.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + drinkwaterremark + "'");
                }
                if (!drinkingwaterstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + drinkingwaterstatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }

    public void FoodstoredRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_FOODSTORESYNC + " where " + TABLE_FOODINSIDSYNC + "=" +insidsync+ " and " +TABLE_FOODSTATUSSYNC+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String foodstoreidsync = "";
        String foodinsidsync = "";
        String foodstorespacesync = "";
        String foodphysiclchksync = "";
        String foodstkbookcmparesync = "";
        String foodstksuficntsync = "";
        String foodricedalsync = "";
        String fooddalbrndsync ="";
        String musteroilsysnc ="";
        String saltsync ="";
        String foodstocklyngsync ="";
        String foodinstimesync ="";
        String foodlastisacrepsync ="";
        String foodlastinspctnrepsync ="";
        String foodcmt;
        String foodstatussync="";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                foodstoreidsync = c.getString(c.getColumnIndex("foodstoreidsync"));
                foodinsidsync = c.getString(c.getColumnIndex("foodinsidsync"));
                foodstorespacesync = c.getString(c.getColumnIndex("foodstorespacesync"));
                foodphysiclchksync = c.getString(c.getColumnIndex("foodphysiclchksync"));
                foodstkbookcmparesync = c.getString(c.getColumnIndex("foodstkbookcmparesync"));
                foodstksuficntsync = c.getString(c.getColumnIndex("foodstksuficntsync"));
                foodricedalsync = c.getString(c.getColumnIndex("foodricedalsync"));
                fooddalbrndsync = c.getString(c.getColumnIndex("fooddalbrndsync"));

                musteroilsysnc = c.getString(c.getColumnIndex("musteroilsysnc"));
                saltsync = c.getString(c.getColumnIndex("saltsync"));

                foodstocklyngsync = c.getString(c.getColumnIndex("foodstocklyngsync"));
                foodinstimesync = c.getString(c.getColumnIndex("foodinstimesync"));
                foodlastisacrepsync = c.getString(c.getColumnIndex("foodlastisacrepsync"));
                foodlastinspctnrepsync = c.getString(c.getColumnIndex("foodlastinspctnrepsync"));
                foodcmt  = c.getString(c.getColumnIndex("foodcmt"));
                foodstatussync = c.getString(c.getColumnIndex("foodstatussync"));
                if (!foodstoreidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodstoreidsync + "'");
                }
                if (!foodinsidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodinsidsync + "'");
                }
                if (!foodstorespacesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodstorespacesync + "'");
                }
                if (!foodphysiclchksync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodphysiclchksync + "'");
                }
                if (!foodstkbookcmparesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodstkbookcmparesync + "'");
                }
                if (!foodstksuficntsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodstksuficntsync + "'");
                }
                if (!foodricedalsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodricedalsync + "'");
                }
                if (!fooddalbrndsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + fooddalbrndsync + "'");
                }
                if (!musteroilsysnc.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + musteroilsysnc + "'");
                }
                if (!saltsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + saltsync + "'");
                }
                if (!foodstocklyngsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodstocklyngsync + "'");
                }
                if (!foodinstimesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodinstimesync + "'");
                }
                if (!foodlastisacrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodlastisacrepsync + "'");
                }
                if (!foodlastinspctnrepsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodlastinspctnrepsync + "'");
                }
                if (!foodcmt.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodcmt + "'");
                }
                if (!foodstatussync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + foodstatussync + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }

    public void ConditionofequipmentothersRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_CONDITIONOFEQIPMENT + " where " + TABLE_CONDITIONOFEQIPMENTINSIDSYNC + "=" +insidsync+ " and " +TABLE_CONDITIONSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String conditionofeqipmentid = "";
        String conditionofeqipmentinsidsync = "";
        String utensilcondtnsync = "";

        String matconditnsync = "";
        String elevnregstrsync = "";
        String elavenregister ="";
        String babywmachinsync = "";
        String conditionbabyweighingmechinusednotoused ="";
        String adultwmachinsync = "";
        String adualtweighingmachineusednotused ="";
        String heightmeasurtypsync ="";
        String heightmeasuringtype ="";
        String growthchrtsync ="";
        String properfilledup = "";
        String storecontainrsync ="";
        String suficent ="";
        String handwashsoapsync ="";
        String handwashingsope = "";

        String conditioncurtimesync="";
        String conditionstatus ="";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                conditionofeqipmentid = c.getString(c.getColumnIndex("conditionofeqipmentid"));
                conditionofeqipmentinsidsync = c.getString(c.getColumnIndex("conditionofeqipmentinsidsync"));

                utensilcondtnsync = c.getString(c.getColumnIndex("utensilcondtnsync"));

                matconditnsync = c.getString(c.getColumnIndex("matconditnsync"));

                elevnregstrsync = c.getString(c.getColumnIndex("elevnregstrsync"));
                elavenregister = c.getString(c.getColumnIndex("elavenregister"));
                babywmachinsync = c.getString(c.getColumnIndex("babywmachinsync"));

                conditionbabyweighingmechinusednotoused = c.getString(c.getColumnIndex("conditionbabyweighingmechinusednotoused"));
                adultwmachinsync = c.getString(c.getColumnIndex("adultwmachinsync"));
                adualtweighingmachineusednotused = c.getString(c.getColumnIndex("adualtweighingmachineusednotused"));
                heightmeasurtypsync = c.getString(c.getColumnIndex("heightmeasurtypsync"));
                heightmeasuringtype = c.getString(c.getColumnIndex("heightmeasuringtype"));
                growthchrtsync = c.getString(c.getColumnIndex("growthchrtsync"));
                properfilledup = c.getString(c.getColumnIndex("properfilledup"));
                storecontainrsync = c.getString(c.getColumnIndex("storecontainrsync"));
                suficent = c.getString(c.getColumnIndex("suficent"));
                handwashsoapsync = c.getString(c.getColumnIndex("handwashsoapsync"));
                handwashingsope = c.getString(c.getColumnIndex("handwashingsope"));

                conditioncurtimesync = c.getString(c.getColumnIndex("conditioncurtimesync"));
                conditionstatus = c.getString(c.getColumnIndex("conditionstatus"));
                if (!conditionofeqipmentid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + conditionofeqipmentid + "'");
                }
                if (!conditionofeqipmentinsidsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + conditionofeqipmentinsidsync + "'");
                }
                if (!utensilcondtnsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + utensilcondtnsync + "'");
                }


                if (!matconditnsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + matconditnsync + "'");
                }
                if (!elevnregstrsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + elevnregstrsync + "'");
                }

                if (!elavenregister.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + elavenregister + "'");
                }
                if (!babywmachinsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + babywmachinsync + "'");
                }
                if (!conditionbabyweighingmechinusednotoused.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + conditionbabyweighingmechinusednotoused + "'");
                }

                if (!adultwmachinsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adultwmachinsync + "'");
                }


                if (!adualtweighingmachineusednotused.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + adualtweighingmachineusednotused + "'");
                }
                if (!heightmeasurtypsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + heightmeasurtypsync + "'");
                }
                if (!heightmeasuringtype.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + heightmeasuringtype + "'");
                }
                if (!growthchrtsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + growthchrtsync + "'");
                }
                if (!properfilledup.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + properfilledup + "'");
                }
                if (!storecontainrsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + storecontainrsync + "'");
                }
                if (!suficent.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + suficent + "'");
                }
                if (!handwashsoapsync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + handwashsoapsync + "'");
                }
                if (!handwashingsope.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + handwashingsope + "'");
                }
                if (!conditioncurtimesync.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + conditioncurtimesync + "'");
                }
                if (!conditionstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + conditionstatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }

    }
    public void ShishualoyRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_SHISHUALOYNAME + " where " + TABLE_SHISHUALOYINSID + "=" +insidsync+ " and " +TABLE_SHISHUALOYSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String shishualoyid = "";
        String hishualoyinsid = "";
        String sishualoy = "";
        String cornercgnitv = "";
        String bookcorner = "";
        String drawcornr = "";
        String playcorners = "";
        String eccrun ="";
        // String eccactvtytyprep ="";
        String valuesuting ="";
        String eccactvtytyp ="";
        String awcvaluestring = "";
        String tlam = "";
        String proprecckit ="";
        String avaleindvsualchildactvtyrcd ="";
        String indvsualchildactvtyrcd ="";
        String awcdecortnfrecce ="";
        String fitorecccheck = "";
        String childenrolled ="";
        String childfoundtodayy ="";
        String ecceprocess ="";
        String assesmentcard = "";
        String assesmentcarduse ="";
        String ecckitdate="";
        String eccobserdate="";
        String pseactvfound="";
        String pseactivitynm="";
        String chldparticipatpse ="";
        String wheterawwriting ="";
        String shishualoyinspectrcmnt ="";
        String shishualoycurtime ="";
        String shishualoystatus ="";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                shishualoyid = c.getString(c.getColumnIndex("shishualoyid"));
                hishualoyinsid = c.getString(c.getColumnIndex("hishualoyinsid"));
                sishualoy = c.getString(c.getColumnIndex("sishualoy"));
                cornercgnitv = c.getString(c.getColumnIndex("cornercgnitv"));
                bookcorner = c.getString(c.getColumnIndex("bookcorner"));
                drawcornr = c.getString(c.getColumnIndex("drawcornr"));
                playcorners = c.getString(c.getColumnIndex("playcorners"));
                eccrun = c.getString(c.getColumnIndex("eccrun"));
                //eccactvtytyprep = c.getString(c.getColumnIndex("eccactvtytyprep"));
                valuesuting = c.getString(c.getColumnIndex("valuesuting"));
                eccactvtytyp = c.getString(c.getColumnIndex("eccactvtytyp"));
                awcvaluestring = c.getString(c.getColumnIndex("awcvaluestring"));
                tlam = c.getString(c.getColumnIndex("tlam"));
                proprecckit = c.getString(c.getColumnIndex("proprecckit"));
                avaleindvsualchildactvtyrcd  = c.getString(c.getColumnIndex("avaleindvsualchildactvtyrcd"));
                indvsualchildactvtyrcd = c.getString(c.getColumnIndex("indvsualchildactvtyrcd"));
                awcdecortnfrecce = c.getString(c.getColumnIndex("awcdecortnfrecce"));
                fitorecccheck = c.getString(c.getColumnIndex("fitorecccheck"));
                childenrolled = c.getString(c.getColumnIndex("childenrolled"));
                childfoundtodayy = c.getString(c.getColumnIndex("childfoundtodayy"));
                ecceprocess = c.getString(c.getColumnIndex("ecceprocess"));
                assesmentcard = c.getString(c.getColumnIndex("assesmentcard"));
                assesmentcarduse = c.getString(c.getColumnIndex("assesmentcarduse"));
                ecckitdate = c.getString(c.getColumnIndex("ecckitdate"));
                eccobserdate = c.getString(c.getColumnIndex("eccobserdate"));
                pseactvfound = c.getString(c.getColumnIndex("pseactvfound"));
                pseactivitynm = c.getString(c.getColumnIndex("pseactivitynm"));
                chldparticipatpse = c.getString(c.getColumnIndex("chldparticipatpse"));
                wheterawwriting = c.getString(c.getColumnIndex("wheterawwriting"));
                shishualoyinspectrcmnt = c.getString(c.getColumnIndex("shishualoyinspectrcmnt"));
                shishualoycurtime = c.getString(c.getColumnIndex("shishualoycurtime"));
                shishualoystatus = c.getString(c.getColumnIndex("shishualoystatus"));
                if (!shishualoyid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + shishualoyid + "'");
                }
                if (!hishualoyinsid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + hishualoyinsid + "'");
                }
                if (!sishualoy.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + sishualoy + "'");
                }
                if (!cornercgnitv.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + cornercgnitv + "'");
                }
                if (!bookcorner.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + bookcorner + "'");
                }
                if (!drawcornr.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + drawcornr + "'");
                }
                if (!playcorners.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + playcorners + "'");
                }
                if (!eccrun.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + eccrun + "'");
                }
                if (!valuesuting.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + valuesuting + "'");
                }
                /////////////////////////////////////////////////////////////
                if (!eccactvtytyp.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + eccactvtytyp + "'");
                }
                if (!awcvaluestring.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcvaluestring + "'");
                }
                if (!tlam.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + tlam + "'");
                }
                if (!proprecckit.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + proprecckit + "'");
                }
                if (!avaleindvsualchildactvtyrcd.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + avaleindvsualchildactvtyrcd + "'");
                }
                if (!indvsualchildactvtyrcd.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + indvsualchildactvtyrcd + "'");
                }
                if (!awcdecortnfrecce.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcdecortnfrecce + "'");
                }
                if (!fitorecccheck.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + fitorecccheck + "'");
                }
                if (!childenrolled.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + childenrolled + "'");
                }
                if (!childfoundtodayy.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + childfoundtodayy + "'");
                }
                if (!ecceprocess.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + ecceprocess + "'");
                }
                if (!assesmentcard.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + assesmentcard + "'");
                }
                if (!assesmentcarduse.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + assesmentcarduse + "'");
                }
                if (!ecckitdate.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + ecckitdate + "'");
                }
                if (!eccobserdate.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + eccobserdate + "'");
                }
                if (!pseactvfound.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + pseactvfound + "'");
                }
                if (!pseactivitynm.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + pseactivitynm + "'");
                }
                if (!chldparticipatpse.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + chldparticipatpse + "'");
                }
                if (!wheterawwriting.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + wheterawwriting + "'");
                }
                if (!shishualoyinspectrcmnt.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + shishualoyinspectrcmnt + "'");
                }
                if (!shishualoycurtime.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + shishualoycurtime + "'");
                }
                if (!shishualoystatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + shishualoystatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }

    public void SpnRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_SPNTABLE + " where " + TABLE_SPNINSID + "=" +insidsync+ " and " +TABLE_SPNSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String spntableid = "";
        String spninsid = "";
        String mrngsnacks = "";
        String mrngsnacktyp = "";
        String snpserve = "";
        String snpmenu = "";
        String hcmaspermnu ="";
        String chldprsnt = "";
        String chldprsnttoday ="";
        String pmlmprsnt ="";
        String pmlmprsnttoday ="";
        String anyfreeinterlastthree ="";
        String feedintrpt1m ="";
        String anyfeedingintruptnlstthremnth ="";
        String feedintrpt2m ="";
        String anyfeedingintruptnlstthremnth2 ="";
        String feedintrpt3m ="";
        String anyfeedingintruptnlstthremnth3 ="";
        String snpntsrvreasn ="";
        String pninspctncmnt ="";
        String spncurtime="";
        String spnstatus ="";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                spntableid = c.getString(c.getColumnIndex("spntableid"));
                spninsid = c.getString(c.getColumnIndex("spninsid"));
                mrngsnacks = c.getString(c.getColumnIndex("mrngsnacks"));
                mrngsnacktyp = c.getString(c.getColumnIndex("mrngsnacktyp"));
                snpserve = c.getString(c.getColumnIndex("snpserve"));

                snpmenu = c.getString(c.getColumnIndex("snpmenu"));
                hcmaspermnu = c.getString(c.getColumnIndex("hcmaspermnu"));

                chldprsnt = c.getString(c.getColumnIndex("chldprsnt"));
                chldprsnttoday = c.getString(c.getColumnIndex("chldprsnttoday"));

                pmlmprsnt = c.getString(c.getColumnIndex("pmlmprsnt"));
                pmlmprsnttoday = c.getString(c.getColumnIndex("pmlmprsnttoday"));

                anyfreeinterlastthree = c.getString(c.getColumnIndex("anyfreeinterlastthree"));
                feedintrpt1m = c.getString(c.getColumnIndex("feedintrpt1m"));
                anyfeedingintruptnlstthremnth = c.getString(c.getColumnIndex("anyfeedingintruptnlstthremnth"));
                feedintrpt2m = c.getString(c.getColumnIndex("feedintrpt2m"));
                anyfeedingintruptnlstthremnth2 = c.getString(c.getColumnIndex("anyfeedingintruptnlstthremnth2"));
                feedintrpt3m = c.getString(c.getColumnIndex("feedintrpt3m"));
                anyfeedingintruptnlstthremnth3 = c.getString(c.getColumnIndex("anyfeedingintruptnlstthremnth3"));
                snpntsrvreasn = c.getString(c.getColumnIndex("snpntsrvreasn"));

                pninspctncmnt = c.getString(c.getColumnIndex("pninspctncmnt"));
                spncurtime = c.getString(c.getColumnIndex("spncurtime"));
                spnstatus = c.getString(c.getColumnIndex("spnstatus"));
                if (!spntableid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + spntableid + "'");
                }
                if (!spninsid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + spninsid + "'");
                }
                if (!mrngsnacks.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + mrngsnacks + "'");
                }
                if (!mrngsnacktyp.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + mrngsnacktyp + "'");
                }
                if (!snpserve.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + snpserve + "'");
                }

                if (!snpmenu.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + snpmenu + "'");
                }
                if (!hcmaspermnu.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + hcmaspermnu + "'");
                }


                if (!chldprsnt.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + chldprsnt + "'");
                }
                if (!chldprsnttoday.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + chldprsnttoday + "'");
                }

                if (!pmlmprsnt.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + pmlmprsnt + "'");
                }
                if (!pmlmprsnttoday.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + pmlmprsnttoday + "'");
                }

                if (!anyfreeinterlastthree.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + anyfreeinterlastthree + "'");
                }
                if (!feedintrpt1m.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + feedintrpt1m + "'");
                }

                if (!anyfeedingintruptnlstthremnth.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + anyfeedingintruptnlstthremnth + "'");
                }

                if (!feedintrpt2m.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + feedintrpt2m + "'");
                }

                if (!anyfeedingintruptnlstthremnth2.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + anyfeedingintruptnlstthremnth2 + "'");
                }

                if (!feedintrpt3m.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + feedintrpt3m + "'");

                }

                if (!anyfeedingintruptnlstthremnth3.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + anyfeedingintruptnlstthremnth3 + "'");

                }

                if (!snpntsrvreasn.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + snpntsrvreasn + "'");

                }

                if (!pninspctncmnt.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + pninspctncmnt + "'");
                }
                if (!spncurtime.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + spncurtime + "'");
                }
                if (!spnstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + spnstatus + "'");
                }

                c.moveToNext();
            }
            c.close();

        }
    }
    public void NutritionalRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_NUTRITIONALTABLE + " where " + TABLE_NUTRITIONALTINS + "=" +insidsync+ " and " +TABLE_NUTRITIONALSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String nutritionaltableid = "";
        String nutritionaltins = "";
        String nutritionaltintime = "";
        String malnurishdchld = "";
        String sanchld = "";
        String commant = "";
        String nutritionalstatus = "";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                nutritionaltableid = c.getString(c.getColumnIndex("nutritionaltableid"));
                nutritionaltins = c.getString(c.getColumnIndex("nutritionaltins"));
                nutritionaltintime = c.getString(c.getColumnIndex("nutritionaltintime"));
                malnurishdchld = c.getString(c.getColumnIndex("malnurishdchld"));
                sanchld = c.getString(c.getColumnIndex("sanchld"));
                commant = c.getString(c.getColumnIndex("commant"));
                nutritionalstatus = c.getString(c.getColumnIndex("nutritionalstatus"));

                if (!nutritionaltableid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + nutritionaltableid + "'");
                }
                if (!nutritionaltins.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + nutritionaltins + "'");
                }
                if (!nutritionaltintime.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + nutritionaltintime + "'");
                }
                if (!malnurishdchld.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + malnurishdchld + "'");
                }
                if (!sanchld.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + sanchld + "'");
                }
                if (!commant.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + commant + "'");
                }
                if (!nutritionalstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + nutritionalstatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }
    public void OtherinspectionRemove(String insidsync){
        String query = "DELETE  FROM " + TABLE_OTHERINSPECTIONTABLENAME + " where " + TABLE_OTHERINSPECTIONINSID + "=" +insidsync+ " and " +TABLE_OTHERINSPECTIONSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String otherinspectionid = "";
        String otherinspectioninsid = "";
        String otherinspectioninscurtime = "";
        String cmuntyprticptnnotice = "";
        String supvisit = "";
        String lastsupustidate ="";
        String medcinkitlstrcv ="";
        String lastrecived ="";
        String usedofawc ="";
        String gengoth ="";
        String medcinchrglstrcv="";
        String preschlchrglstrcv ="";
        String grwthchrtlstrcv = "";
        String awcremark = "";
        String otherinspectionstatus ="";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                otherinspectionid = c.getString(c.getColumnIndex("otherinspectionid"));
                otherinspectioninsid = c.getString(c.getColumnIndex("otherinspectioninsid"));
                otherinspectioninscurtime = c.getString(c.getColumnIndex("otherinspectioninscurtime"));
                cmuntyprticptnnotice = c.getString(c.getColumnIndex("cmuntyprticptnnotice"));
                supvisit = c.getString(c.getColumnIndex("supvisit"));
                lastsupustidate = c.getString(c.getColumnIndex("lastsupustidate"));
                medcinkitlstrcv = c.getString(c.getColumnIndex("medcinkitlstrcv"));
                lastrecived = c.getString(c.getColumnIndex("lastrecived"));
                usedofawc = c.getString(c.getColumnIndex("usedofawc"));
                gengoth = c.getString(c.getColumnIndex("gengoth"));
                medcinchrglstrcv = c.getString(c.getColumnIndex("medcinchrglstrcv"));
                preschlchrglstrcv = c.getString(c.getColumnIndex("preschlchrglstrcv"));
                grwthchrtlstrcv = c.getString(c.getColumnIndex("grwthchrtlstrcv"));
                awcremark = c.getString(c.getColumnIndex("awcremark"));
                otherinspectionstatus = c.getString(c.getColumnIndex("otherinspectionstatus"));

                if (!otherinspectionid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + otherinspectionid + "'");
                }
                if (!otherinspectioninsid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + otherinspectioninsid + "'");
                }
                if (!otherinspectioninscurtime.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + otherinspectioninscurtime + "'");
                }
                if (!cmuntyprticptnnotice.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + cmuntyprticptnnotice + "'");
                }
                if (!supvisit.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + supvisit + "'");
                }
                if (!lastsupustidate.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lastsupustidate + "'");
                }
                if (!medcinkitlstrcv.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + medcinkitlstrcv + "'");
                }

                if (!lastrecived.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + lastrecived + "'");

                }
                if (!usedofawc.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + usedofawc + "'");
                }
                if (!gengoth.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + gengoth + "'");
                }
                if (!medcinchrglstrcv.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + medcinchrglstrcv + "'");
                }

                if (!preschlchrglstrcv.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + preschlchrglstrcv + "'");
                }
                if (!grwthchrtlstrcv.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + grwthchrtlstrcv + "'");
                }
                if (!awcremark.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + awcremark + "'");
                }
                if (!otherinspectionstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + otherinspectionstatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }
    }

    public void INSPECTIONPERSONPRESENTREMOVE(String insidsync){
        String query = "DELETE  FROM " + TABLE_INSPECTIONPERSONPRESENT + " where " + TABLE_INSPECTIONPERSONPRESENTINSID + "=" +insidsync+ " and " +TABLE_INSPECTIONPERSONPRESENTSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String inspectionpersonpresentid = "";
        String inspectionpersonpresentdbdistid = "";
        String inspectionpersonpresentdbprojectid = "";
        String inspectionpersonpresentdbsectorid = "";
        String inspectionpersonpresentdbcenterid = "";
        String inspectionpersonpresentyncdpo = "";
        String inspectionpersonpresentynacdpio = "";
        String inspectionpersonpresentynsupervisor ="";
        String inspectionpersonpresentynworker ="";
        String inspectionpersonpresentyhelper ="";
        String inspectionpersonpresentcurdate ="";
        String inspectionpersonpresentcurtime ="";
        String inspectionpersonpresentynawcs="";
        String inspectionpersonpresentuserid ="";
        String inspectionpersonpresentinsid = "";
        String inspectionpersonpresentplanid = "";
        String inspectionpersonpresentplancmt = "";
        String inspectionpersonpresentstatus ="";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                inspectionpersonpresentid = c.getString(c.getColumnIndex("inspectionpersonpresentid"));
                inspectionpersonpresentdbdistid = c.getString(c.getColumnIndex("inspectionpersonpresentdbdistid"));
                inspectionpersonpresentdbprojectid = c.getString(c.getColumnIndex("inspectionpersonpresentdbprojectid"));
                inspectionpersonpresentdbsectorid = c.getString(c.getColumnIndex("inspectionpersonpresentdbsectorid"));
                inspectionpersonpresentdbcenterid = c.getString(c.getColumnIndex("inspectionpersonpresentdbcenterid"));
                inspectionpersonpresentyncdpo = c.getString(c.getColumnIndex("inspectionpersonpresentyncdpo"));
                inspectionpersonpresentynacdpio = c.getString(c.getColumnIndex("inspectionpersonpresentynacdpio"));
                inspectionpersonpresentynsupervisor = c.getString(c.getColumnIndex("inspectionpersonpresentynsupervisor"));
                inspectionpersonpresentynworker = c.getString(c.getColumnIndex("inspectionpersonpresentynworker"));
                inspectionpersonpresentyhelper = c.getString(c.getColumnIndex("inspectionpersonpresentyhelper"));
                inspectionpersonpresentcurdate = c.getString(c.getColumnIndex("inspectionpersonpresentcurdate"));
                inspectionpersonpresentcurtime = c.getString(c.getColumnIndex("inspectionpersonpresentcurtime"));
                inspectionpersonpresentynawcs = c.getString(c.getColumnIndex("inspectionpersonpresentynawcs"));
                inspectionpersonpresentuserid = c.getString(c.getColumnIndex("inspectionpersonpresentuserid"));
                inspectionpersonpresentinsid = c.getString(c.getColumnIndex("inspectionpersonpresentinsid"));
                inspectionpersonpresentplanid = c.getString(c.getColumnIndex("inspectionpersonpresentplanid"));
                inspectionpersonpresentplancmt = c.getString(c.getColumnIndex("inspectionpersonpresentplancmt"));
                inspectionpersonpresentstatus = c.getString(c.getColumnIndex("inspectionpersonpresentstatus"));

                if (!inspectionpersonpresentid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentid + "'");
                }
                if (!inspectionpersonpresentdbdistid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentdbdistid + "'");
                }
                if (!inspectionpersonpresentdbprojectid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentdbprojectid + "'");
                }
                if (!inspectionpersonpresentdbsectorid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentdbsectorid + "'");
                }
                if (!inspectionpersonpresentdbcenterid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentdbcenterid + "'");
                }
                if (!inspectionpersonpresentyncdpo.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentyncdpo + "'");
                }
                if (!inspectionpersonpresentynacdpio.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentynacdpio + "'");
                }
                if (!inspectionpersonpresentynsupervisor.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentynsupervisor + "'");
                }
                if (!inspectionpersonpresentynworker.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentynworker + "'");
                }

                if (!inspectionpersonpresentyhelper.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentyhelper + "'");
                }
                if (!inspectionpersonpresentcurdate.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentcurdate + "'");
                }
                if (!inspectionpersonpresentcurtime.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentcurtime + "'");
                }
                if (!inspectionpersonpresentynawcs.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentynawcs + "'");
                }

                if (!inspectionpersonpresentuserid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentuserid + "'");
                }
                if (!inspectionpersonpresentinsid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentinsid + "'");
                }
                if (!inspectionpersonpresentplanid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentplanid + "'");
                }
                if (!inspectionpersonpresentplancmt.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentplancmt + "'");
                }
                if (!inspectionpersonpresentstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + inspectionpersonpresentstatus + "'");
                }
                c.moveToNext();
            }
            c.close();

        }

    }
    public void FINALSUBMITSYNC(String insidsync){
        String query = "DELETE  FROM " + TABLE_FINALSUBTABLE + " where " + TABLE_FINALSUBINS + "=" +insidsync+ " and " +TABLE_FINALSUBMITSTATUS+ "=" +STATUS;
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor  c = sqlDB.rawQuery(query,null);
        String finalsunid = "";
        String finalsubins = "";
        String finalsubmitstatus = "";
        int status = 0;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                finalsunid = c.getString(c.getColumnIndex("finalsunid"));
                finalsubins = c.getString(c.getColumnIndex("finalsubins"));
                finalsubmitstatus = c.getString(c.getColumnIndex("finalsubmitstatus"));

                if (!finalsunid.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + finalsunid + "'");
                }
                if (!finalsubins.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + finalsubins + "'");
                }
                if (!finalsubmitstatus.equals("android_metadata")) {
                    sqlDB.execSQL("DROP TABLE '" + finalsubmitstatus + "'");
                }
                c.moveToNext();
            }
            c.close();
        }
    }
//////////////////////////////////////////////////////////////
//public void awcsdtls(String inspctnid,String Userid){
//    Log.e("AWCSDELETE",inspctnid+" "+" "+Userid);
//    String query = "DELETE  FROM " + TABLE_NAMEAWCSDTL + " where " + TABLE_INSPECTIONID + "=" +inspctnid+ " and " +TABLE_AWCSUSERID+ "=" +Userid;
//    SQLiteDatabase sqlDB = helper.getReadableDatabase();
//    Cursor  c = sqlDB.rawQuery(query,null);
//    String id = "";
//    String water = "";
//    String cdponame = "";
//    String acdpocont = "";
//    String buildstruc = "";
//    String electric = "";
//    String acdponame = "";
//    String kitchen = "";
//    String cdpocontact = "";
//    String workerno = "";
//    String workernm = "";
//    String toilet = "";
//    String awcslat = "";
//    String supvsrname = "";
//    String awcsslong = "";
//    String helperno = "";
//    String awcsadrs = "";
//    String foodspace = "";
//    String helpernm = "";
//    String buildon ="";
//    String adqutspacepse ="";
//    String supvsrno = "";
//    String awcsid ="";
//    String awcscode ="";
//    String awcsname ="";
//    String planid ="";
//    String lstinspctnbuldrep ="";
//    String lstinspctntoiletrep ="";
//    String lstinspctnkitchenrep ="";
//    String lstinspctnpserep ="";
//    String lstinspctnelectricrep ="";
//    String lstinspctndrnkwaterrep ="";
//    String lstinspctnfoodrep = "";
//    String awcsuserid = "";
//    String awcsflag = "";
//    String allinspactionid ="";
//    int status = 0;
//    if (c.moveToFirst()) {
//        while (!c.isAfterLast()) {
//            id = c.getString(c.getColumnIndex("id"));
//            water = c.getString(c.getColumnIndex("water"));
//            cdponame = c.getString(c.getColumnIndex("cdponame"));
//            acdpocont = c.getString(c.getColumnIndex("acdpocont"));
//            buildstruc = c.getString(c.getColumnIndex("buildstruc"));
//            electric = c.getString(c.getColumnIndex("electric"));
//            acdponame = c.getString(c.getColumnIndex("acdponame"));
//            kitchen = c.getString(c.getColumnIndex("kitchen"));
//            cdpocontact = c.getString(c.getColumnIndex("cdpocontact"));
//            workerno = c.getString(c.getColumnIndex("workerno"));
//            workernm = c.getString(c.getColumnIndex("workernm"));
//            toilet = c.getString(c.getColumnIndex("toilet"));
//            awcslat = c.getString(c.getColumnIndex("awcslat"));
//            supvsrname = c.getString(c.getColumnIndex("supvsrname"));
//            awcsslong = c.getString(c.getColumnIndex("awcsslong"));
//            helperno = c.getString(c.getColumnIndex("helperno"));
//            awcsadrs = c.getString(c.getColumnIndex("awcsadrs"));
//            foodspace = c.getString(c.getColumnIndex("foodspace"));
//            helpernm = c.getString(c.getColumnIndex("helpernm"));
//            buildon = c.getString(c.getColumnIndex("buildon"));
//            adqutspacepse = c.getString(c.getColumnIndex("adqutspacepse"));
//            supvsrno = c.getString(c.getColumnIndex("supvsrno"));
//            awcsid = c.getString(c.getColumnIndex("awcsid"));
//            awcscode = c.getString(c.getColumnIndex("awcscode"));
//            awcsname = c.getString(c.getColumnIndex("awcsname"));
//            planid = c.getString(c.getColumnIndex("planid"));
//            lstinspctnbuldrep = c.getString(c.getColumnIndex("lstinspctnbuldrep"));
//            lstinspctntoiletrep = c.getString(c.getColumnIndex("lstinspctntoiletrep"));
//            lstinspctnkitchenrep = c.getString(c.getColumnIndex("lstinspctnkitchenrep"));
//            lstinspctnpserep = c.getString(c.getColumnIndex("lstinspctnpserep"));
//            lstinspctnelectricrep = c.getString(c.getColumnIndex("lstinspctnelectricrep"));
//            lstinspctndrnkwaterrep = c.getString(c.getColumnIndex("lstinspctndrnkwaterrep"));
//            lstinspctnfoodrep = c.getString(c.getColumnIndex("lstinspctnfoodrep"));
//            awcsuserid = c.getString(c.getColumnIndex("awcsuserid"));
//            awcsflag = c.getString(c.getColumnIndex("awcsflag"));
//            allinspactionid = c.getString(c.getColumnIndex("allinspactionid"));
//            if (!id.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + id + "'");
//            }
//            if (!water.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + water + "'");
//            }
//            if (!cdponame.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + cdponame + "'");
//            }
//            if (!acdpocont.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + acdpocont + "'");
//            }
//            if (!buildstruc.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + buildstruc + "'");
//            }
//            if (!electric.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + electric + "'");
//            }
//            if (!acdponame.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + acdponame + "'");
//            }
//            if (!kitchen.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + kitchen + "'");
//            }
//            if (!cdpocontact.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + cdpocontact + "'");
//            }
//            if (!workerno.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + workerno + "'");
//            }
//            if (!workernm.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + workernm + "'");
//            }
//            if (!toilet.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + toilet + "'");
//            }
//            if (!awcslat.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + awcslat + "'");
//            }
//            if (!supvsrname.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + supvsrname + "'");
//            }
//            if (!awcsslong.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + awcsslong + "'");
//            }
//            if (!helperno.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + helperno + "'");
//            }
//            if (!awcsadrs.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + awcsadrs + "'");
//            }
//            if (!foodspace.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + foodspace + "'");
//            }
//            if (!helpernm.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + helpernm + "'");
//            }
//            if (!buildon.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + buildon + "'");
//            }
//            if (!adqutspacepse.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + adqutspacepse + "'");
//            }
//            if (!supvsrno.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + supvsrno + "'");
//            }
//            if (!awcsid.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + awcsid + "'");
//                Log.e("AWCS",awcsid);
//            }
//            if (!awcscode.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + awcscode + "'");
//            }
//            if (!awcsname.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + awcsname + "'");
//            }
//            if (!planid.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + planid + "'");
//            }
//            if (!lstinspctnbuldrep.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + lstinspctnbuldrep + "'");
//            }
//            if (!lstinspctntoiletrep.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + lstinspctntoiletrep + "'");
//            }
//            if (!lstinspctnkitchenrep.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + lstinspctnkitchenrep + "'");
//            }
//            if (!lstinspctnpserep.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + lstinspctnpserep + "'");
//            }
//            if (!lstinspctnelectricrep.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + lstinspctnelectricrep + "'");
//            }
//            if (!lstinspctndrnkwaterrep.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + lstinspctndrnkwaterrep + "'");
//            }
//            if (!lstinspctnfoodrep.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + lstinspctnfoodrep + "'");
//            }
//            if (!awcsuserid.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + awcsuserid + "'");
//            }
//            if (!awcsflag.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + awcsflag + "'");
//            }
//            if (!allinspactionid.equals("android_metadata")) {
//                sqlDB.execSQL("DROP TABLE '" + allinspactionid + "'");
//            }
//            c.moveToNext();
//        }
//        c.close();
//
//    }
//   }
//    public void processDelete_Inspection(String insid,String Userid,String flaggrecordd){
//        Log.e("PROCESSDELETE",insid+" "+Userid);
//        String query = "DELETE  FROM " + TABLE_PROCESS + " where " + TABLE_INSPACTIONID + "=" +insid+ " and " +TABLE_USERID+ "=" +Userid+ " and " +TABLE_FLAGGRECORD+ "=" +flaggrecordd;
//        SQLiteDatabase sqlDB = helper.getReadableDatabase();
//        Cursor  c = sqlDB.rawQuery(query,null);
//        String idprocess = "";
//        String dbdistid = "";
//        String dbprojectid = "";
//        String dbsectorid = "";
//        String dbcenterid = "";
//        String districnamedb = "";
//        String projectnamedb = "";
//        String sectorrnamedb = "";
//        String centernamedb = "";
//        String currentdate = "";
//        String userid = "";
//        String flag = "";
//        String awcslatlocation = "";
//        String awcsslonglocation = "";
//        String inspactionid = "";
//        String awcscodeid = "";
//        String awcstime = "";
//        String flaggrecord ="";
//        int status = 0;
//        if (c.moveToFirst()) {
//            while (!c.isAfterLast()) {
//                idprocess = c.getString(c.getColumnIndex("idprocess"));
//                dbdistid = c.getString(c.getColumnIndex("dbdistid"));
//                dbprojectid = c.getString(c.getColumnIndex("dbprojectid"));
//                dbsectorid = c.getString(c.getColumnIndex("dbsectorid"));
//                dbcenterid = c.getString(c.getColumnIndex("dbcenterid"));
//                districnamedb = c.getString(c.getColumnIndex("districnamedb"));
//                projectnamedb = c.getString(c.getColumnIndex("projectnamedb"));
//                sectorrnamedb = c.getString(c.getColumnIndex("sectorrnamedb"));
//                centernamedb = c.getString(c.getColumnIndex("centernamedb"));
//                currentdate = c.getString(c.getColumnIndex("currentdate"));
//                userid = c.getString(c.getColumnIndex("userid"));
//                flag = c.getString(c.getColumnIndex("flag"));
//                awcslatlocation = c.getString(c.getColumnIndex("awcslatlocation"));
//                awcsslonglocation = c.getString(c.getColumnIndex("awcsslonglocation"));
//                inspactionid = c.getString(c.getColumnIndex("inspactionid"));
//                awcscodeid = c.getString(c.getColumnIndex("awcscodeid"));
//                awcstime = c.getString(c.getColumnIndex("awcstime"));
//                flaggrecord = c.getString(c.getColumnIndex("flaggrecord"));
//                if (!idprocess.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + idprocess + "'");
//                }
//                if (!dbdistid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + dbdistid + "'");
//                }
//                if (!dbprojectid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + dbprojectid + "'");
//                }
//                if (!dbsectorid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + dbsectorid + "'");
//                }
//                if (!dbcenterid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + dbcenterid + "'");
//                }
//                if (!districnamedb.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + districnamedb + "'");
//                }
//                if (!projectnamedb.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + projectnamedb + "'");
//                }
//                if (!sectorrnamedb.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + sectorrnamedb + "'");
//                }
//                if (!centernamedb.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + centernamedb + "'");
//                }
//                if (!currentdate.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + currentdate + "'");
//                }
//                if (!userid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + userid + "'");
//                }
//                if (!flag.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + flag + "'");
//                }
//                if (!awcslatlocation.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcslatlocation + "'");
//                }
//                if (!awcsslonglocation.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcsslonglocation + "'");
//                }
//                if (!inspactionid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + inspactionid + "'");
//                }
//                if (!awcscodeid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcscodeid + "'");
//                }
//                if (!awcstime.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + awcstime + "'");
//                }
//                if (!flaggrecord.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + flaggrecord + "'");
//                }
//                c.moveToNext();
//            }
//            c.close();
//
//        }
//    }
//    public void listcheckboxDelete(String inspctnid,String Userid){
//        Log.e("FLAGDELETE",inspctnid+" "+Userid);
//        String query = "DELETE  FROM " + TABLE_ALLINSPECTIONFLAG + " where " + TABLE_ALLINSPACATIONID + "=" +inspctnid+ " and " +TABLE_USERIDFLA+ "=" +Userid;
//        SQLiteDatabase sqlDB = helper.getReadableDatabase();
//        Cursor  c = sqlDB.rawQuery(query,null);
//        String insflagid = "";
//        String buildingdetails = "";
//        String informationoftoilet = "";
//        String informationofkitchen = "";
//        String adequatespaceforpse = "";
//        String electricity = "";
//        String drinkingwater = "";
//        String foodstoreddetails = "";
//        String conditionofequipmentotherawc = "";
//        String shishualoy = "";
//        String snpserved = "";
//        String nutritionalstatusobserved = "";
//        String otherinspection = "";
//        String allinspactionid = "";
//        String useridflag = "";
//        if (c.moveToFirst()) {
//            while (!c.isAfterLast()) {
//                insflagid = c.getString(c.getColumnIndex("insflagid"));
//                buildingdetails = c.getString(c.getColumnIndex("buildingdetails"));
//                informationoftoilet = c.getString(c.getColumnIndex("informationoftoilet"));
//                informationofkitchen = c.getString(c.getColumnIndex("informationofkitchen"));
//                adequatespaceforpse = c.getString(c.getColumnIndex("adequatespaceforpse"));
//                electricity = c.getString(c.getColumnIndex("electricity"));
//                drinkingwater = c.getString(c.getColumnIndex("drinkingwater"));
//                foodstoreddetails = c.getString(c.getColumnIndex("foodstoreddetails"));
//                conditionofequipmentotherawc = c.getString(c.getColumnIndex("conditionofequipmentotherawc"));
//                shishualoy = c.getString(c.getColumnIndex("shishualoy"));
//                snpserved = c.getString(c.getColumnIndex("snpserved"));
//                nutritionalstatusobserved = c.getString(c.getColumnIndex("nutritionalstatusobserved"));
//                otherinspection = c.getString(c.getColumnIndex("otherinspection"));
//                allinspactionid = c.getString(c.getColumnIndex("allinspactionid"));
//                useridflag = c.getString(c.getColumnIndex("useridflag"));
//                if (!insflagid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + insflagid + "'");
//                }
//                if (!buildingdetails.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + buildingdetails + "'");
//                }
//                if (!informationoftoilet.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + informationoftoilet + "'");
//                }
//                if (!informationofkitchen.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + informationofkitchen + "'");
//                }
//                if (!adequatespaceforpse.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + adequatespaceforpse + "'");
//                }
//                if (!electricity.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + electricity + "'");
//                }
//                if (!drinkingwater.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + drinkingwater + "'");
//                }
//                if (!foodstoreddetails.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + foodstoreddetails + "'");
//                }
//                if (!conditionofequipmentotherawc.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + conditionofequipmentotherawc + "'");
//                }
//                if (!shishualoy.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + shishualoy + "'");
//                }
////                if (!userid.equals("android_metadata")) {
////                    sqlDB.execSQL("DROP TABLE '" + userid + "'");
////                }
//                if (!snpserved.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + snpserved + "'");
//                }
//                if (!nutritionalstatusobserved.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + nutritionalstatusobserved + "'");
//                }
//                if (!otherinspection.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + otherinspection + "'");
//                }
//                if (!allinspactionid.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + allinspactionid + "'");
//                }
//                if (!useridflag.equals("android_metadata")) {
//                    sqlDB.execSQL("DROP TABLE '" + useridflag + "'");
//                }
//                c.moveToNext();
//            }
//            c.close();
//        }
//    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        try{
            if (inspactioninsertNetworkercheker!=null)
                getActivity().unregisterReceiver(inspactioninsertNetworkercheker);
            if (inspectionpersonpresentNetworkcheker!=null)
                getActivity().unregisterReceiver(inspectionpersonpresentNetworkcheker);
            if(buildingNetworkStateCheckerr!=null)
                getActivity().unregisterReceiver(buildingNetworkStateCheckerr);
            if (toiletNetwokchecker!=null)
                getActivity().unregisterReceiver(toiletNetwokchecker);
            if (kitchenNetwokchecker!=null)
                getActivity().unregisterReceiver(kitchenNetwokchecker);
            if (adqueSpaceNetwokchecker!=null)
                getActivity().unregisterReceiver(adqueSpaceNetwokchecker);
            if (eletricityNetwokchecker!=null)
                getActivity().unregisterReceiver(eletricityNetwokchecker);
            if (drinkingNetwokchecker!=null)
                getActivity().unregisterReceiver(drinkingNetwokchecker);
            if (foodNetwokchecker!=null)
                getActivity().unregisterReceiver(foodNetwokchecker);
            if (conditionNetwokchecker!=null)
                getActivity().unregisterReceiver(conditionNetwokchecker);
            if (shishuAloyNetwokchecker!=null)
                getActivity().unregisterReceiver(shishuAloyNetwokchecker);
            if (spnNetwokchecker!=null)
                getActivity().unregisterReceiver(spnNetwokchecker);
            if (nutriationnetwokchecker!=null)
                getActivity().unregisterReceiver(nutriationnetwokchecker);
            if (otherinspactionNetworkchecker!=null)
                getActivity().unregisterReceiver(otherinspactionNetworkchecker);
            if (finalSubmitNetworkcheck!=null)
                getActivity().unregisterReceiver(finalSubmitNetworkcheck);
           if (sportnetworkcheck!=null)
                getActivity().unregisterReceiver(sportnetworkcheck);
        }catch(Exception e){}
        super.onDestroy();
    }
}
