package icdswb.in.ActivityDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME ="ICDS";
    private static final int DB_VERSION = 1;
    ////////////////////////////LOGIN//////////////////////////////////////////////
    public static final String TABLE_NAMEICDSLOGIN ="icdslogin";
    public static final String TABLE_LOGINID ="loginid";
    public static final String TABLE_USER_lID = "userid";
    public static final String TABLE_USER_TYP= "usertyp";
    public static final String TABLE_USER_DIST = "userdist";
    public static final String TABLE_USER_IMG = "userimg";
    public static final String TABLE_USER_NAME= "username";
    public static final String TABLE_USER_DESIG = "userdesig";
    public static final String TABLE_USER_PHN = "userphn";
    public static final String TABLE_USER_WATSAPP= "userwatsapp";
    public static final String TABLE_USER_MAIL = "usermail";
    public static final String TABLE_USER_PWD = "userpwd";
    public static final String TABLE_DISTID = "distid";
    public static final String TABLE_DISNAME = "disname";
    public static final String TABLE_DISCODE = "discode";
    public static final String TABLE_USERDISTID = "distiduser";
    public static final String TABLE_USERDISTIDNAME = "distnameuser";
    public static final String TABLE_USERPROJECTID = "projiduser";
    public static final String TABLE_USERPROJECTNAME = "projnameuser";
    public static final String TABLE_USERSECTORID = "seciduser";
    public static final String TABLE_USERSECTORNAME = "secnameuser";
    ///////////////////////////////////////////////////////////////////////////
//    inspection id add
    public static final String TABLE_NAMEAWCSDTL ="awcsdtl";
    public static final String TABLE_ID ="id";
    public static final String TABLE_WATER="water";
    public static final String TABLE_CDPONAME="cdponame";
    public static final String TABLE_ACDPOCONT="acdpocont";
    public static final String TABLE_BUILDSTRUC="buildstruc";
    public static final String TABLE_ELECTRIC="electric";
    public static final String TABLE_ACDPONAME="acdponame";
    public static final String TABLE_KITCHEN="kitchen";
    public static final String TABLE_CDPOCONTACT="cdpocontact";
    public static final String TABLE_WORKERNO="workerno";
    public static final String TABLE_WORKERNM="workernm";
    public static final String TABLE_TOILET="toilet";
    public static final String TABLE_AWCSLAT="awcslat";
    public static final String TABLE_SUPVSRNAME="supvsrname";
    public static final String TABLE_AWCSLONG = "awcsslong";
    public static final String TABLE_HELPERNO="helperno";
    public static final String TABLE_AWCSADRS ="awcsadrs";
    public static final String TABLE_FOODSPACE="foodspace";
    public static final String TABLE_HELPERNM="helpernm";
    public static final String TABLE_BUILDON="buildon";
    public static final String TABLE_ADQUTSPACEPSE="adqutspacepse";
    public static final String TABLE_SUPVSRNO="supvsrno";
    public static final String TABLE_AWCSID="awcsid";
    public static final String TABLE_AWCSCODE= "awcscode";
    public static final String TABLE_AWCSNAME="awcsname";
    public static final String TABLE_PLANID ="planid";
    public static final String TABLE_LST_INSPCTN_BULD_REP = "lstinspctnbuldrep";
    public static final String TABLE_LST_INSPCTN_TOILET_REP = "lstinspctntoiletrep";
    public static final String TABLE_LST_INSPCTN_KITCHEN_REP = "lstinspctnkitchenrep";
    public static final String TABLE_LST_INSPCTN_PSE_REP = "lstinspctnpserep";
    public static final String TABLE_LST_INSPCTN_ELECTRIC_REP = "lstinspctnelectricrep";
    public static final String TABLE_LST_INSPCTN_DRNKWATER_REP = "lstinspctndrnkwaterrep";
    public static final String TABLE_LST_INSPCTN_FOOD_REP = "lstinspctnfoodrep";
    public static final String TABLE_AWCSUSERID = "awcsuserid";
    public static final String TABLE_AWCSFLAG = "awcsflag";
    public static final String TABLE_INSPECTIONID = "allinspactionid";
////////////////////////////////////////////////////////////////////
    ////this tale
    public static final String TABLE_PROCESS ="awcsprocess";
    public static final String TABLE_PROCESSID ="idprocess";
    public static final String TABLE_DBDISTID ="dbdistid";
    public static final String TABLE_DBPROJECTID ="dbprojectid";
    public static final String TABLE_DBSECTORID ="dbsectorid";
    public static final String TABLE_DBCENTERID ="dbcenterid";
    public static final String TABLE_PROJECT ="districnamedb";
    public static final String TABLE_DISTRIC ="projectnamedb";
    public static final String TABLE_SECTOR ="sectorrnamedb";
    public static final String TABLE_CENTER ="centernamedb";
    public static final String TABLE_CURRENDATE ="currentdate";
    public static final String TABLE_USERID ="userid";
    public static final String TABLE_FLAG = "flag";
    public static final String TABLE_AWCSLATLOCATION = "awcslatlocation";
    public static final String TABLE_AWCSLONGLOCATION ="awcsslonglocation";
    public static final String TABLE_INSPACTIONID ="inspactionid";
    public static final String TABLE_AWCSCODEID = "awcscodeid";
    public static final String TABLE_AWCSTIME = "awcstime";
    public static final String TABLE_FLAGGRECORD = "flaggrecord";
    ////////////////////////////////////////////////////////
    public static final String TABLE_OWNBUlIDINGFUND ="ownbuildingfun";
    public static final String TABLE_IDFUN = "idfun";
    public static final String TABLE_FID = "fid";
    public static final String TABLE_NAME = "fname";
    ///////////////////////////////////////////////////////
    public static final String TABLE_NAMEISID = "inspactionid";
    public static final String TABLE_IDINSPACTION = "inssid";
    public static final String TABLE_INSID ="insid";
    //////////////////////////////////////////////////////////
    /////////////////////////////////////////////////
    public static final String TABLE_DISTRICSP = "districsp";
    public static final String TABLEID_DISID = "tableisid";
    public static final String TABLE_DISTRICID = "disid";
    public static final String TABLE_DISTRICNAME = "disname";
    ////////////////////////////////////////////////
    public static final String TABLE_ALLINSPECTIONFLAG = "insflag";
    public static final String TABLE_ALLINSPECTIONFLAGID = "insflagid";
    public static final String TABLE_BUILDINGDETAILS= "buildingdetails";
    public static final String TABLE_INFORMATIONOFTOILET = "informationoftoilet";
    public static final String TABLE_INFORMATIONOFKITCHEN = "informationofkitchen";
    public static final String TABLE_ADEQUATESPACEFORPSE = "adequatespaceforpse";
    public static final String TABLE_ELECTRICITY ="electricity";
    public static final String TABLE_DRINKINGWATER = "drinkingwater";
    public static final String TABLE_FOODSTOREDDETAILS = "foodstoreddetails";
    public static final String TABLE_CONDITIONOFEQUIPMENTOTHERAWC = "conditionofequipmentotherawc";
    public static final String TABLE_SHISHUALOY = "shishualoy";
    public static final String TABLE_SNPSERVED = "snpserved";
    public static final String TABLE_NUTRITIONALSTATUSOBSERVED = "nutritionalstatusobserved";
    public static final String TABLE_OTHERINSPECTION = "otherinspection";
    public static final String TABLE_ALLINSPACATIONID= "allinspactionid";
    public static final String TABLE_USERIDFLA = "useridflag";
    //
    /////////////////////////////////////////////////
    public static final String TABLE_INSPECTION_PERSON_PRESENT= "nspectionpersonpresent";
    public static final String TABLE_NSPECTION_PERSON_PRESENT_ID = "Idnspectionpersonpresent";
    public static final String TABLE_DISTRICT = "district";
    public static final String TABLE_PROJECTT= "project";
    public static final String TABLE_SECTORR = "sector";
    public static final String TABLE_CENTRE = "centre";
    public static final String TABLE_CDPO_PRSNT = "cdpoprsnt";
    public static final String TABLE_ACDPO_PRSNT = "acdpoprsnt";
    public static final String TABLE_SUPVSR_PRSNT="supvsrprsnt";
    public static final String TABLE_WORKER_PRSNT = "workerprsnt";
    public static final String TABLE_HELPER_PRSNT ="helperprsnt";
    public static final String TABLE_INSPECTN_DATE = "inspectndate";
    public static final String TABLE_INSPECTN_TIME = "inspectntime";
    public static final String TABLE_AWCS_OPEN = "awcsopen";
    public static final String TABLE_USER_ID = "userid";
    public static final String TABLE_INSPCTN_DATE = "inspctndate";
    public static final String TABLE_INSPCTN_ID = "inspctnid";
    public static final String TABLE_PLAN_ID = "planid";
    ///////////////////////////////////////////////////////
    public static final String TABLE_YESNO = "tableyesno";
    public static final String TABLE_YESNOID = "tableyesnoid";
    public static final String TABLE_CDPOPRSNT = "cdpoprsnt";
    public static final String TABLE_ACDPOPRSNT = "acdpoprsnt";
    public static final String TABLE_SUPVSRPRSNT = "supvsrprsnt";
    public static final String TABLE_WORKERPRSNT = "workerprsnt";
    public static final String TABLE_HELPERPRSNT= "helperprsnt";
    public static final String TABLE_AWCSOPEN= "awcsopen";
   ///////////////////////////////////////////////////////////////
    /////////////////////////////BUILDINGSYNC//////////////////////////
    public static final String TABLE_BUILDINGSYNC ="tablebuildingsync";
    public static final String TABLE_BUILDINGIDSYNC ="buildingidsync";
    public static final String TABLE_DBDISTIDSYNC = "dbdistidsync";
    public static final String TABLE_DBPROJECTIDSYNC = "dbprojectidsync";
    public static final String TABLE_DBSECTORIDSYNC = "dbsectoridsync";
    public static final String TABLE_DBCENTERIDSYNC = "dbcenteridsync";
    public static final String TABLE_BUILTTYP = "builttypsync";
    public static final String TABLE_BUILTRUNIN = "builtruninsync";
    ////// own building fund/////////
   // public static final String TABLE_OWNBUILDFUND= "ownbuildfundsync";
    ////// own building fund/////////
    public static final String TABLE_RENTBUILDIN = "rentbuildinsync";
    public static final String TABLE_OTHRGOVTBUILDIN = "othrgovtbuildinsync";
    public static final String TABLE_BUILDCONDITN = "buildconditn";
    public static final String TABLE_INSPCTRCMNT= "inspctrcmnt";
    public static final String TABLE_USERIDSYNC = "useridsync";
    public static final String TABLE_INSIDSYNC = "insidsync";
    public static final String TABLE_CURDATE = "curdatesync";
    public static final String TABLE_CURTIME = "curtimesync";
    public static final String TABLE_LATISACREPORT= "latisacreportsync";
    public static final String TABLE_LSTINSPCTNBULDREP= "lstinspctnbuldrepsync";
    public static final String TABLE_STATUSBUILDING = "buildingstatus";
    //////////////////////////////////////////////////////
    ////////////////////////TOILETSYNC///////////////////////////
    public static final String TABLE_TOILETSYNC ="toiletsync";
    public static final String TABLE_TOILETIDSYNC = "toiletidsync";
    public static final String TABLE_INSIDTOILETSYNC = "insidtoiletsync";
    public static final String TABLE_YNTOILETSYNC = "yntoiletsync";
    public static final String TABLE_TOILETUSABLESYNC = "toiletusablesync";
    public static final String TABLE_INSPCTRCMNTSYNC = "inspctrcmntsync";
    public static final String TABLE_CURTIMESYNC = "curTimesync";
    public static final String TABLE_LASTISACREPSYNC = "lastisacrepsync";
    public static final String TABLE_LSTINSPCTNTOILETREPSYNC = "lstinspctntoiletrepsync";
    public static final String TABLE_TOILETSTATUS = "toiletstatus";
    ///////////////////////////////////////////////////////////
    /////////////////////INFORMATIONKITCHEN/////////////////////
    public static final String TABLE_KITCHENSYNC = "kitchensync";
    public static final String TABLE_KITCHENIDSYNC = "kitchenidsync";
    public static final String TABLE_KITCHENINSID = "kitcheninsidsync";
    public static final String TABLE_SEPRTKITCHN = "seprtkitchnsync";
    public static final String TABLE_SEPRTCOOKDONEITCHNSYNC ="seprtcookdoneitchnsync";
    public static final String TABLE_TOILETINSPCTRCMNTSYNC = "toiletinspctrcmntsync";
    public static final String TABLE_INSTIMESYNC = "instimesync";
    public static final String TABLE_TOILETLASTISACREPSYNC = "toiletlastisacrepsync";
    public static final String TABLE_LASTINSPCTNREPSYNC = "lastinspctnrepsync";
    public static final String TABLE_KITCHENSTATUS = "kitchenstatus";
    ///////////////////////////////////////////////////////////
    //////////////////////ADEQUATESOACEPSE/////////////////////
    public static final String TABLE_ADEQUATESPPSESYNC = "adequatesppsesync";
    public static final String TABLE_ADEQUATESPPSEID = "adequatesppseid";
    public static final String TABLE_ADEQUTSPACEINSIDSYNC = "adequtspaceinsidsync";
    public static final String TABLE_ADEQUTSPACE  = "adequtspacesync";
    public static final String TABLE_PSEACTVTYTYP = "pseactvtytypsync";
    public static final String TABLE_ADEQUTSPACECURTIMESYNC = "adequtspacecurTimesync";
    public static final String TABLE_ADQUTSPACEINSPCTRCMNT = "adequtspaceinspctrcmntsync";
    public static final String TABLE_ADQUTSPLASTISACREPSYNC = "adqutspacelastisacrepsync";
    public static final String TABLE_ADQUTSPACELASTINSPCTNREPSYNC = "adqutspacelastinspctnrepsync";
    public static final String TABLE_ADEQUATESPPSESTATUS = "adequatesppsestatus";
    //////////////////////////////////////////////////////////
    //////////////////////Elatricity//////////////////////////
    public static final String TABLE_ELECTRITY = "electrity";
    public static final String TABLE_ELECTRITYID = "electrityid";
    public static final String TABLE_ELECTRITYINSIDSYNC = "electrityinsidsync";
    public static final String TABLE_ELECTRICAVAILSYNC = "electricavailsync";
    public static final String TABLE_EMODESYNC = "emodesync";
    public static final String TABLE_LIGHTTYPESYNC = "lighttypesync";
    public static final String TABLE_FANTYPESYNC = "fantypesync";
    public static final String TABLE_PUMPOVRHDSYNC = "pumpovrhdsync";
    public static final String TABLE_ELECRITYINSPCTRCMNTSYNC = "inspctrcmntelectricitysync";
    public static final String TABLE_ELECTRITYCUTTIMESYNC = "cuttimeeletricitysync";
    public static final String TABLE_LASTISACREPELECTRICITYSYNC = "lastisacrepeletricitysync";
    public static final String TABLE_LASTINSPCTNREPELETRICITYSYNC = "lastinspctnrepeletricitysync";
    public static final String TABLE_ELECTRICITYSTATUS = "eletricitystatus";
    ////////////////////////////////////////DRINKINGWATER/////////////////////////////////////////
    public static final String TABLE_DRINKINGWATERSYNC = "drinkingwatersync";
    public static final String TABLE_DRINKINGWATERIDSYNC = "drinkingwateridsync";
    public static final String TABLE_DRINKINGWATERINSID = "drinkingwaterinsid";
    public static final String TABLE_DRINKINGWATEROWNDRINKWATERSYNC = "owndrinkwatersync";
    public static final String TABLE_DRINKINGWATERCURTIMESYNC = "drinkingwatercurtimesync";
    public static final String TABLE_DRINKINGWATERLASTISACREPSYNC = "drinkingwaterlastisacrepsync";
    public static final String TABLE_LASTINSPCTNREPDRINKINGSYNC = "lastinspctnrepdrinkingsync";
    public static final String TABLE_LASTINSPCTNREPDRINKINGCOMMANDSYNC = "drinkwaterremark";
    public static final String TABLE_DRINKINGWATERSTATUS = "drinkingwaterstatus";
    ////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////FOODSTORED/////////////////////////////////////////////////////////
    public static final String TABLE_FOODSTORESYNC = "foodstoresync";
    public static final String TABLE_FOODSTOREIDSYNC = "foodstoreidsync";
    public static final String TABLE_FOODINSIDSYNC = "foodinsidsync";
    public static final String TABLE_FOODSTORESPACESYNC = "foodstorespacesync";
    public static final String TABLE_FOODPHYSICLCHKSYNC = "foodphysiclchksync";
    public static final String TABLE_FOODSTKBOOKCMPARESYNC = "foodstkbookcmparesync";
    public static final String TABLE_FOODSTKSUFICNTSYNC = "foodstksuficntsync";
    public static final String TABLE_FOODRICEDALSYNC = "foodricedalsync";
    public static final String TABLE_FOODDALSYNC = "fooddalbrndsync";

    public static final String TABLE_FOODMAUSTEROILSYNC = "musteroilsysnc";
    public static final String TABLE_FOODSALTSYNC = "saltsync";

    public static final String TABLE_FOODSTOCKLYNGSYNC = "foodstocklyngsync";
    public static final String TABLE_FOODINSTIMESYNC = "foodinstimesync";
    public static final String TABLE_FOODLASTISACREPSYNC = "foodlastisacrepsync";
    public static final String TABLE_FOODLASTINSPCTNREPSYNC = "foodlastinspctnrepsync";
    public static final String TABLE_CMT ="foodcmt";
    public static final String TABLE_FOODSTATUSSYNC = "foodstatussync";
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////CONDITIONOFEQUIPMENTOTHERS///////////////////////////////
    public static final String TABLE_CONDITIONOFEQIPMENT = "conditionofeqipment";
    public static final String TABLE_CONDITIONOFEQIPMENTID = "conditionofeqipmentid";
    public static final String TABLE_CONDITIONOFEQIPMENTINSIDSYNC = "conditionofeqipmentinsidsync";

    public static final String TABLE_CONDITIONUTENSILCONDTNSYNC = "utensilcondtnsync";
    public static final String TABLE_CONDITIONMATCONDITNSYNC = "matconditnsync";
    public static final String TABLE_CONDITIONELEVNREGSTRSYNC = "elevnregstrsync";
    public static final String TABLE_CONDITIONELEVNREGSTRNOSYNC = "elavenregister";

    public static final String TABLE_CONDITIONBABYWMACHINSYNC = "babywmachinsync";
    public static final String TABLE_CONDITIONELEVNREGSTRBABYWMACHINSYNC = "conditionbabyweighingmechinusednotoused";


    public static final String TABLE_CONDITIONADULTWMACHINSYNC = "adultwmachinsync";
    public static final String TABLE_ADUALTWEIGHINGMACHINEUSEDNOTUSED = "adualtweighingmachineusednotused";
    public static final String TABLE_CONDITIONHEIGHTMEASURTYPSYNC = "heightmeasurtypsync";
    public static final String TABLE_HEIGHTMESURINGTYPE ="heightmeasuringtype";
    public static final String TABLE_CONDITIONGROWTHCHRTSYNC = "growthchrtsync";
    public static final String TABLE_PROPERFILLEDUP = "properfilledup";
    public static final String TABLE_CONDITIONSTORECONTAINRSYNC = "storecontainrsync";
    public static final String TABLE_SUFICENT ="suficent";
    public static final String TABLE_CONDITIONHANDWASHSOAPSYNC = "handwashsoapsync";
    public static final String TABLE_HANDWASHINGSOPE ="handwashingsope";


    public static final String TABLE_CONDITIONCURTIMESYNC = "conditioncurtimesync";
    public static final String TABLE_CONDITIONSTATUS = "conditionstatus";
    /////////////////////SHISHUALOY////////////////////////////////////
    public static final String TABLE_SHISHUALOYNAME = "shishualoy";
    public static final String TABLE_SHISHUALOYID = "shishualoyid";
    public static final String TABLE_SHISHUALOYINSID = "hishualoyinsid";
    public static final String TABLE_SISHUALOY = "sishualoy";
    public static final String TABLE_CORNERCGNITV = "cornercgnitv";
    public static final String TABLE_BOOKCORNER = "bookcorner";
    public static final String TABLE_DRAWCORNR = "drawcornr";
    public static final String TABLE_PLAYCORNERS = "playcorners";
    public static final String TABLE_ECCRUN = "eccrun";
//    public static final String TABLE_VALUESUTING = "valuesuting";
    public static final String TABLE_VALUESUTING = "valuesuting";
    public static final String TABLE_ECCACTIVITYTYPE = "eccactvtytyp";
    public static final String TABLE_AWCVALUESSTRING = "awcvaluestring";
    public static final String TABLE_TLAM ="tlam";
    public static final String TABLE_PROPRECCKIT = "proprecckit";
    public static final String TABLE_AVALEINDVSUALCHILDACTIVEYRCD ="avaleindvsualchildactvtyrcd";
    public static final String TABLE_INDVSUALCHILDACTVTYRCD = "indvsualchildactvtyrcd";
    public static final String TABLE_AWCDECORTNFRECCE = "awcdecortnfrecce";
    public static final String TABLE_FITORECCCHECK = "fitorecccheck";
    public static final String TABLE_CHILDENROLLED ="childenrolled";
    public static final String TABLE_CHILDFOUNDTODAYY = "childfoundtodayy";
    public static final String TABLE_ECCEPROCESS = "ecceprocess";
    public static final String TABLE_ASSESMENTCARD = "assesmentcard";
    public static final String TABLE_ASSESMENTCARDUSE = "assesmentcarduse";
    public static final String TABLE_ECCKITDATE ="ecckitdate";
    public static final String TABLE_ECCOBSERDATE ="eccobserdate";
    public static final String TABLE_PSEACTFOUND ="pseactvfound";
    public static final String TABLE_PSEACTIVITYNM = "pseactivitynm";
    public static final String TABLE_CHAILDPARTICIPATPSE = "chldparticipatpse";

    public static final String TABLE_AWWFOLLOWINGTHEROUTING ="wheterawwriting";

    public static final String TABLE_SHISHUALOYINSPECTRCMNT = "shishualoyinspectrcmnt";
    public static final String TABLE_SHISHUALOYCURTIME = "shishualoycurtime";
    public static final String TABLE_SHISHUALOYSTATUS = "shishualoystatus";
    ///////////SPN////////////////////////////////////////////////////////
    public static final String TABLE_SPNTABLE = "spntable";
    public static final String TABLE_SPNTABLEID = "spntableid";
    public static final String TABLE_SPNINSID = "spninsid";
    public static final String TABLE_MRNGSNACKS = "mrngsnacks";
    public static final String TABLE_MRNGSNACKTYP = "mrngsnacktyp";
    public static final String TABLE_SNPSERVE = "snpserve";
    public static final String TABLE_HCMASPERMNU = "hcmaspermnu";
    public static final String TABLE_SNPMENU = "snpmenu";
    public static final String TABLE_CHLDPRSNT = "chldprsnt";
    public static final String TABLE_CHIDPRSNTTODAY ="chldprsnttoday";
    public static final String TABLE_PMLMPRSNT = "pmlmprsnt";
    public static final String TABLE_PMLMPRSNTTODAY ="pmlmprsnttoday";
    public static final String TABLE_AVRGFEEDNGLSTTHREMNTH = "anyfreeinterlastthree";
    public static final String TABLE_FEEDINTRPTLM= "feedintrpt1m";
    public static final String TABLE_ANYFEEDINGINTRUPTNLSTTHREMNTH = "anyfeedingintruptnlstthremnth";
    public static final String TABLE_FEEDINTRPT2M ="feedintrpt2m";
    public static final String TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH2 = "anyfeedingintruptnlstthremnth2";
    public static final String TABLE_FEEDINTRPT3M= "feedintrpt3m";
    public static final String TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH3 ="anyfeedingintruptnlstthremnth3";
    public static final String TABLE_SNPNTSRVREASN = "snpntsrvreasn";
    public static final String TABLE_SPNINSPCTNCMNT = "pninspctncmnt";
    public static final String TABLE_SPNCURTIME = "spncurtime";
    public static final String TABLE_SPNSTATUS ="spnstatus";
    ////////////////NUTRITIONAL///////////////////////////////
    public static final String TABLE_NUTRITIONALTABLE = "nutritionaltable";
    public static final String TABLE_NUTRITIONALTID = "nutritionaltableid";
    public static final String TABLE_NUTRITIONALTINS = "nutritionaltins";
    public static final String TABLE_NUTRITIONALTINTIME = "nutritionaltintime";
    public static final String TABLE_MALNURISHDCHLD = "malnurishdchld";
    public static final String TABLE_SANCHLD = "sanchld";
    public static final String TABLE_COMMANT = "commant";
    public static final String TABLE_NUTRITIONALSTATUS = "nutritionalstatus";
    ////////////////////////////OTHERINSPECTION///////////////////////////////
    public static final String TABLE_OTHERINSPECTIONTABLENAME = "otherinspection";
    public static final String TABLE_OTHERINSPECTIONTABLEID = "otherinspectionid";
    public static final String TABLE_OTHERINSPECTIONINSID = "otherinspectioninsid";
    public static final String TABLE_OTHERINSPECTIONINSCURTIME = "otherinspectioninscurtime";
    public static final String TABLE_CMUNTYPRTICPTNNOTICE = "cmuntyprticptnnotice";
    public static final String TABLE_SUPVISIT = "supvisit";
    public static final String TABLE_LASTSUPUSTIDATE = "lastsupustidate";
    public static final String TABLE_MEDCINKITLSTRCV = "medcinkitlstrcv";
    public static final String TABLE_LASTRECIVED = "lastrecived";
    public static final String TABLE_USEDOFAWC = "usedofawc";
    public static final String TABLE_GENGOTH = "gengoth";
    public static final String TABLE_AWCREMARK = "awcremark";
    public static final String TABLE_OTHERINSPECTIONSTATUS = "otherinspectionstatus";
    //////////////////////////INSPACTION/////////////////////////////////////////////////
    public static final String TABLE_UPDATECENTREPERSNDTL = "updatecentrepersndtl";
    public static final String TABLE_UPDATECENTREPERSNDTLID = "updatecentrepersndtlid";
    public static final String TABLE_UPDATECENTREPERSNDTLDBDISTID = "updatecentrepersndtldbdistid";
    public static final String TABLE_UPDATECENTREPERSNDTLDBPROJECTID = "updatecentrepersndtldbprojectid";
    public static final String TABLE_UPDATECENTREPERSNDTLCDPONAME = "updatecentrepersndtlcdponame";
    public static final String TABLE_UPDATECENTREPERSNDTLCDPONUMBER = "updatecentrepersndtlcdponumber";
    public static final String TABLE_UPDATECENTREPERSNDTLACDPONAME = "updatecentrepersndtlacdponame";
    public static final String TABLE_UPDATECENTREPERSNDTLACDPOCONTRACT = "updatecentrepersndtlacdpocontract";
    public static final String TABLE_UPDATECENTREPERSNDTLDBSECTORID = "updatecentrepersndtldbsectorid";
    public static final String TABLE_UPDATECENTREPERSNDTLSUPERVISORNAME = "updatecentrepersndtlsupervisorname";
    public static final String TABLE_UPDATECENTREPERSNDTLSUPERVISORNO = "updatecentrepersndtlsupervisorno";
    public static final String TABLE_UPDATECENTREPERSNDTLAWCSID = "updatecentrepersndtlawcsid";
    public static final String TABLE_UPDATECENTREPERSNDTLWORKERNAME = "updatecentrepersndtlworkername";
    public static final String TABLE_UPDATECENTREPERSNDTLWORKERNO = "updatecentrepersndtlworkerno";
    public static final String TABLE_UPDATECENTREPERSNDTLHELPERNAME = "updatecentrepersndtlhelpername";
    public static final String TABLE_UPDATECENTREPERSNDTLHELPERNO = "updatecentrepersndtlhelperno";
    public static final String TABLE_UPDATECENTREPERSNDTLSTATUS = "updatecentrepersndtlstatus";
    /////////////////////////////INSPECTIONPERSONPRESENT///////////////////////////////////////////
    public static final String TABLE_INSPECTIONPERSONPRESENT = "inspectionpersonpresent";
    public static final String TABLE_INSPECTIONPERSONPRESENTID = "inspectionpersonpresentid";
    public static final String TABLE_INSPECTIONPERSONPRESENTDBDISTID = "inspectionpersonpresentdbdistid";
    public static final String TABLE_INSPECTIONPERSONPRESENTDBPROJECTID = "inspectionpersonpresentdbprojectid";
    public static final String TABLE_INSPECTIONPERSONPRESENTDBSECTORID = "inspectionpersonpresentdbsectorid";
    public static final String TABLE_INSPECTIONPERSONPRESENTDBCENTERID = "inspectionpersonpresentdbcenterid";
    public static final String TABLE_INSPECTIONPERSONPRESENTYNCDPO = "inspectionpersonpresentyncdpo";
    public static final String TABLE_INSPECTIONPERSONPRESENTYNACDPIO = "inspectionpersonpresentynacdpio";
    public static final String TABLE_INSPECTIONPERSONPRESENTYNSUPERVISOR = "inspectionpersonpresentynsupervisor";
    public static final String TABLE_INSPECTIONPERSONPRESENTYNWORKER = "inspectionpersonpresentynworker";
    public static final String TABLE_INSPECTIONPERSONPRESENTYHELPER = "inspectionpersonpresentyhelper";
    public static final String TABLE_INSPECTIONPERSONPRESENTCURDATE = "inspectionpersonpresentcurdate";
    public static final String TABLE_INSPECTIONPERSONPRESENTCURTIME = "inspectionpersonpresentcurtime";
    public static final String TABLE_INSPECTIONPERSONPRESENTYNAWCS = "inspectionpersonpresentynawcs";
    public static final String TABLE_INSPECTIONPERSONPRESENTUSERID = "inspectionpersonpresentuserid";
    public static final String TABLE_INSPECTIONPERSONPRESENTINSID = "inspectionpersonpresentinsid";
    public static final String TABLE_INSPECTIONPERSONPRESENTPLANID = "inspectionpersonpresentplanid";
    public static final String TABLE_INSPECTIONPERSONPRESENTPLANIDCMT = "inspectionpersonpresentplancmt";

    public static final String TABLE_INSPECTIONPERSONPRESENTSTATUS = "inspectionpersonpresentstatus";
    ///////////////////////////Final Submit////////////////////////////////////////////////////////
    public static final String TABLE_FINALSUBTABLE = "finalsubtable";
    public static final String TABLE_FINALSUBID = "finalsunid";
    public static final String TABLE_FINALSUBINS = "finalsubins";
    public static final String TABLE_FINALSUBMITSTATUS = "finalsubmitstatus";
    /////////////////////OFFLINESYNCDATA////////////////////////////////////////
    public static final String TABLE_SPORTSYNC = "sportsync";
    public static final String TABLE_SPORTSYNCID = "sportsyncid";
    public static final String TABLE_SPORTSINS = "sportins";
    public static final String TABLE_AWCCODE = "awccode";
    public static final String TBALE_SDISTRIC = "sdistric";
    public static final String TABLE_PLANIDSP ="planidsp";
    public static final String TABLE_PLANDATE = "systendateplan";
    public static final String TABLE_PLANTIME = "curtimeplan";
    public static final String TABLE_USERIDPLAN = "userplan";
    public static final String TABLE_SPORTSTATUS = "sportstatus";
    //////////////////////////////////////////////////////////////////////////////////////////////
    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String login = "CREATE TABLE " + TABLE_NAMEICDSLOGIN
                + "(" + TABLE_LOGINID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_USER_lID + " VARCHAR, "
                + TABLE_USER_TYP + " VARCHAR, "
                + TABLE_USER_DIST + " VARCHAR, "
                + TABLE_USER_IMG + " VARCHAR, "
                + TABLE_USER_NAME + " VARCHAR, "
                + TABLE_USER_DESIG + " VARCHAR, "
                + TABLE_USER_PHN + " VARCHAR, "
                + TABLE_USER_WATSAPP + " VARCHAR, "
                + TABLE_USER_MAIL + " VARCHAR, "
                + TABLE_USER_PWD + " VARCHAR, "
                + TABLE_DISTID + " VARCHAR, "
                + TABLE_DISNAME + " VARCHAR, "
                + TABLE_DISCODE + " VARCHAR, "
                + TABLE_USERDISTID + " VARCHAR, "
                + TABLE_USERDISTIDNAME + " VARCHAR, "
                + TABLE_USERPROJECTID + " VARCHAR, "
                + TABLE_USERPROJECTNAME + " VARCHAR, "
                + TABLE_USERSECTORID + " VARCHAR, "
                + TABLE_USERSECTORNAME + " VARCHAR);";
        db.execSQL(login);
        String sql1 = "CREATE TABLE " + TABLE_NAMEAWCSDTL
                + "(" + TABLE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_WATER + " VARCHAR, "
                + TABLE_CDPONAME + " VARCHAR, "
                + TABLE_ACDPOCONT + " VARCHAR, "
                + TABLE_BUILDSTRUC + " VARCHAR, "
                + TABLE_ELECTRIC + " VARCHAR, "
                + TABLE_ACDPONAME + " VARCHAR, "
                + TABLE_KITCHEN + " VARCHAR, "
                + TABLE_CDPOCONTACT + " VARCHAR, "
                + TABLE_WORKERNO + " VARCHAR, "
                + TABLE_WORKERNM + " VARCHAR, "
                + TABLE_TOILET + " VARCHAR, "
                + TABLE_AWCSLAT + " VARCHAR, "
                + TABLE_SUPVSRNAME + " VARCHAR, "
                + TABLE_AWCSLONG + " VARCHAR, "
                + TABLE_HELPERNO + " VARCHAR, "
                + TABLE_AWCSADRS + " VARCHAR, "
                + TABLE_FOODSPACE + " VARCHAR, "
                + TABLE_HELPERNM + " VARCHAR, "
                + TABLE_BUILDON + " VARCHAR, "
                + TABLE_ADQUTSPACEPSE + " VARCHAR, "
                + TABLE_SUPVSRNO + " VARCHAR, "
                + TABLE_AWCSID + " VARCHAR, "
                + TABLE_AWCSCODE + " VARCHAR, "
                + TABLE_AWCSNAME + " VARCHAR, "
                + TABLE_PLANID + " VARCHAR, "
                + TABLE_LST_INSPCTN_BULD_REP + " VARCHAR, "
                + TABLE_LST_INSPCTN_TOILET_REP + " VARCHAR, "
                + TABLE_LST_INSPCTN_KITCHEN_REP + " VARCHAR, "
                + TABLE_LST_INSPCTN_PSE_REP + " VARCHAR, "
                + TABLE_LST_INSPCTN_ELECTRIC_REP + " VARCHAR, "
                + TABLE_LST_INSPCTN_DRNKWATER_REP + " VARCHAR, "
                + TABLE_LST_INSPCTN_FOOD_REP + " VARCHAR, "
                + TABLE_AWCSUSERID + " VARCHAR, "
                + TABLE_AWCSFLAG + " VARCHAR, "
                + TABLE_INSPECTIONID + " VARCHAR);";
                db.execSQL(sql1);
                String sql2 = "CREATE TABLE " + TABLE_PROCESS
                        + "(" + TABLE_PROCESSID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + TABLE_DBDISTID + " VARCHAR, "
                        + TABLE_DBPROJECTID + " VARCHAR, "
                        + TABLE_DBSECTORID + " VARCHAR, "
                        + TABLE_DBCENTERID + " VARCHAR, "
                        + TABLE_DISTRIC + " VARCHAR, "
                        + TABLE_PROJECT + " VARCHAR, "
                        + TABLE_SECTOR + " VARCHAR, "
                        + TABLE_CENTER + " VARCHAR, "
                        + TABLE_CURRENDATE + " VARCHAR, "
                        + TABLE_USERID + " VARCHAR, "
                        + TABLE_FLAG + " VARCHAR, "
                        + TABLE_AWCSLATLOCATION + " VARCHAR, "
                        + TABLE_AWCSLONGLOCATION + " VARCHAR, "
                        + TABLE_INSPACTIONID + " VARCHAR, "
                        + TABLE_AWCSCODEID + " VARCHAR, "
                        + TABLE_AWCSTIME + " VARCHAR, "
                        + TABLE_FLAGGRECORD + " VARCHAR);";
                db.execSQL(sql2);

//        String sql2 = "CREATE TABLE " + TABLE_PROCESS
//                + "(" + TABLE_INSPACTIONID +
//                " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + TABLE_DBDISTID + " VARCHAR, "
//                + TABLE_DBPROJECTID + " VARCHAR, "
//                + TABLE_DBSECTORID + " VARCHAR, "
//                + TABLE_DBCENTERID + " VARCHAR, "
//                + TABLE_DISTRIC + " VARCHAR, "
//                + TABLE_PROJECT + " VARCHAR, "
//                + TABLE_SECTOR + " VARCHAR, "
//                + TABLE_CENTER + " VARCHAR, "
//                + TABLE_CURRENDATE + " VARCHAR, "
//                + TABLE_USERID + " VARCHAR, "
//                + TABLE_FLAG + " VARCHAR, "
//                + TABLE_AWCSLATLOCATION + " VARCHAR, "
//                + TABLE_AWCSLONGLOCATION + " VARCHAR, "
//                + TABLE_PROCESSID + " VARCHAR, "
//                + TABLE_AWCSCODEID + " VARCHAR, "
//                + TABLE_AWCSTIME + " VARCHAR, "
//                + TABLE_FLAGGRECORD + " VARCHAR);";
//        db.execSQL(sql2);

                String sql3 = "CREATE TABLE " + TABLE_OWNBUlIDINGFUND
                        + "(" + TABLE_IDFUN +
                        " INTEGER PRIMARY KEY, "
                        + TABLE_FID + " VARCHAR, "
                        + TABLE_NAME + " VARCHAR);";
                db.execSQL(sql3);
                 String sql4 = "CREATE TABLE " + TABLE_NAMEISID
                + "(" + TABLE_IDINSPACTION +
                " INTEGER PRIMARY KEY, "
                + TABLE_INSID + " VARCHAR);";
                  db.execSQL(sql4);
                  String sql5 = "CREATE TABLE " + TABLE_DISTRICSP
                + "(" + TABLEID_DISID +
                " INTEGER PRIMARY KEY, "
                + TABLE_DISTRICID + " VARCHAR, "
                + TABLE_DISTRICNAME + " VARCHAR);";
                db.execSQL(sql5);
                String sql6 = "CREATE TABLE " + TABLE_INSPECTION_PERSON_PRESENT
                + "(" + TABLE_NSPECTION_PERSON_PRESENT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_DISTRICT + " VARCHAR, "
                + TABLE_PROJECTT + " VARCHAR, "
                + TABLE_SECTORR + " VARCHAR, "
                + TABLE_CENTRE + " VARCHAR, "
                + TABLE_CDPO_PRSNT + " VARCHAR, "
                + TABLE_ACDPO_PRSNT + " VARCHAR, "
                + TABLE_SUPVSR_PRSNT + " VARCHAR, "
                + TABLE_WORKER_PRSNT + " VARCHAR, "
                + TABLE_HELPER_PRSNT + " VARCHAR, "
                + TABLE_INSPECTN_DATE + " VARCHAR, "
                + TABLE_INSPECTN_TIME + " VARCHAR, "
                + TABLE_AWCS_OPEN + " VARCHAR, "
                + TABLE_USER_ID + " VARCHAR, "
                + TABLE_INSPCTN_DATE + " VARCHAR, "
                + TABLE_INSPCTN_ID + " VARCHAR, "
                + TABLE_PLAN_ID + " VARCHAR);";
               db.execSQL(sql6);

               String sql7 = "CREATE TABLE " + TABLE_ALLINSPECTIONFLAG
                + "(" + TABLE_ALLINSPECTIONFLAGID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_BUILDINGDETAILS + " VARCHAR, "
                + TABLE_INFORMATIONOFTOILET + " VARCHAR, "
                + TABLE_INFORMATIONOFKITCHEN + " VARCHAR, "
                + TABLE_ADEQUATESPACEFORPSE + " VARCHAR, "
                + TABLE_ELECTRICITY + " VARCHAR, "
                + TABLE_DRINKINGWATER + " VARCHAR, "
                + TABLE_FOODSTOREDDETAILS + " VARCHAR, "
                + TABLE_CONDITIONOFEQUIPMENTOTHERAWC + " VARCHAR, "
                + TABLE_SHISHUALOY + " VARCHAR, "
                + TABLE_SNPSERVED + " VARCHAR, "
                + TABLE_NUTRITIONALSTATUSOBSERVED + " VARCHAR, "
                + TABLE_OTHERINSPECTION + " VARCHAR, "
                + TABLE_ALLINSPACATIONID + " VARCHAR, "
                + TABLE_USERIDFLA + " VARCHAR);";
               db.execSQL(sql7);

        String sql8= "CREATE TABLE " + TABLE_YESNO
                + "(" + TABLE_YESNOID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_CDPOPRSNT + " VARCHAR, "
                + TABLE_ACDPOPRSNT + " VARCHAR, "
                + TABLE_SUPVSRPRSNT + " VARCHAR, "
                + TABLE_WORKERPRSNT + " VARCHAR, "
                + TABLE_HELPERPRSNT + " VARCHAR, "
                + TABLE_AWCSOPEN + " VARCHAR);";
        db.execSQL(sql8);
        String sql9 = "CREATE TABLE " + TABLE_BUILDINGSYNC
                + "(" + TABLE_BUILDINGIDSYNC +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_DBDISTIDSYNC + " VARCHAR, "
                + TABLE_DBPROJECTIDSYNC + " VARCHAR, "
                + TABLE_DBSECTORIDSYNC + " VARCHAR, "
                + TABLE_DBCENTERIDSYNC + " VARCHAR, "
                + TABLE_BUILTTYP + " VARCHAR, "
                + TABLE_BUILTRUNIN + " VARCHAR, "
                + TABLE_RENTBUILDIN + " VARCHAR, "
                + TABLE_OTHRGOVTBUILDIN + " VARCHAR, "
                + TABLE_BUILDCONDITN + " VARCHAR, "
                + TABLE_INSPCTRCMNT + " VARCHAR, "
                + TABLE_USERIDSYNC + " VARCHAR, "
                + TABLE_INSIDSYNC + " VARCHAR, "
                + TABLE_CURDATE + " VARCHAR, "
                + TABLE_CURTIME + " VARCHAR, "
                + TABLE_LATISACREPORT + " VARCHAR, "
                + TABLE_LSTINSPCTNBULDREP + " VARCHAR, "
                + TABLE_STATUSBUILDING + " TINYINT);";
        db.execSQL(sql9);

        String sql10 = "CREATE TABLE " + TABLE_TOILETSYNC
                + "(" + TABLE_TOILETIDSYNC +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_INSIDTOILETSYNC + " VARCHAR, "
                + TABLE_YNTOILETSYNC + " VARCHAR, "
                + TABLE_TOILETUSABLESYNC + " VARCHAR, "
                + TABLE_INSPCTRCMNTSYNC + " VARCHAR, "
                + TABLE_CURTIMESYNC + " VARCHAR, "
                + TABLE_LASTISACREPSYNC + " VARCHAR, "
                + TABLE_LSTINSPCTNTOILETREPSYNC + " VARCHAR, "
                + TABLE_TOILETSTATUS + " TINYINT);";
        db.execSQL(sql10);

        String sql11 = "CREATE TABLE " + TABLE_KITCHENSYNC
                + "(" + TABLE_KITCHENIDSYNC +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_KITCHENINSID + " VARCHAR, "
                + TABLE_SEPRTKITCHN + " VARCHAR, "
                + TABLE_SEPRTCOOKDONEITCHNSYNC + " VARCHAR, "
                + TABLE_TOILETINSPCTRCMNTSYNC + " VARCHAR, "
                + TABLE_INSTIMESYNC + " VARCHAR, "
                + TABLE_TOILETLASTISACREPSYNC + " VARCHAR, "
                + TABLE_LASTINSPCTNREPSYNC + " VARCHAR, "
                + TABLE_KITCHENSTATUS + " TINYINT);";
        db.execSQL(sql11);
        String sql12 = "CREATE TABLE " + TABLE_ADEQUATESPPSESYNC
                + "(" + TABLE_ADEQUATESPPSEID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_ADEQUTSPACEINSIDSYNC + " VARCHAR, "
                + TABLE_ADEQUTSPACE + " VARCHAR, "
                + TABLE_PSEACTVTYTYP + " VARCHAR, "
                + TABLE_ADEQUTSPACECURTIMESYNC + " VARCHAR, "
                + TABLE_ADQUTSPACEINSPCTRCMNT + " VARCHAR, "
                + TABLE_ADQUTSPLASTISACREPSYNC + " VARCHAR, "
                + TABLE_ADQUTSPACELASTINSPCTNREPSYNC + " VARCHAR, "
                + TABLE_ADEQUATESPPSESTATUS + " TINYINT);";
        db.execSQL(sql12);

        String sql13 = "CREATE TABLE " + TABLE_ELECTRITY
                + "(" + TABLE_ELECTRITYID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_ELECTRITYINSIDSYNC + " VARCHAR, "
                + TABLE_ELECTRICAVAILSYNC + " VARCHAR, "
                + TABLE_EMODESYNC + " VARCHAR, "
                + TABLE_LIGHTTYPESYNC + " VARCHAR, "
                + TABLE_FANTYPESYNC + " VARCHAR, "
                + TABLE_PUMPOVRHDSYNC + " VARCHAR, "
                + TABLE_ELECRITYINSPCTRCMNTSYNC + " VARCHAR, "
                + TABLE_ELECTRITYCUTTIMESYNC + " VARCHAR, "
                + TABLE_LASTISACREPELECTRICITYSYNC + " VARCHAR, "
                + TABLE_LASTINSPCTNREPELETRICITYSYNC + " VARCHAR, "
                + TABLE_ELECTRICITYSTATUS + " TINYINT);";
        db.execSQL(sql13);

        String sql14 = "CREATE TABLE " + TABLE_DRINKINGWATERSYNC
                + "(" + TABLE_DRINKINGWATERIDSYNC +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_DRINKINGWATERINSID + " VARCHAR, "
                + TABLE_DRINKINGWATEROWNDRINKWATERSYNC + " VARCHAR, "
                + TABLE_DRINKINGWATERCURTIMESYNC + " VARCHAR, "
                + TABLE_DRINKINGWATERLASTISACREPSYNC + " VARCHAR, "
                + TABLE_LASTINSPCTNREPDRINKINGSYNC + " VARCHAR, "
                + TABLE_LASTINSPCTNREPDRINKINGCOMMANDSYNC + " VARCHAR, "
                + TABLE_DRINKINGWATERSTATUS + " TINYINT);";
        db.execSQL(sql14);

        String sql15= "CREATE TABLE " + TABLE_FOODSTORESYNC
                + "(" + TABLE_FOODSTOREIDSYNC +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_FOODINSIDSYNC + " VARCHAR, "
                + TABLE_FOODSTORESPACESYNC + " VARCHAR, "
                + TABLE_FOODPHYSICLCHKSYNC + " VARCHAR, "
                + TABLE_FOODSTKBOOKCMPARESYNC + " VARCHAR, "
                + TABLE_FOODSTKSUFICNTSYNC + " VARCHAR, "
                + TABLE_FOODRICEDALSYNC + " VARCHAR, "
                + TABLE_FOODDALSYNC + " VARCHAR, "
                + TABLE_FOODMAUSTEROILSYNC + " VARCHAR, "
                + TABLE_FOODSALTSYNC + " VARCHAR, "
                + TABLE_FOODSTOCKLYNGSYNC + " VARCHAR, "
                + TABLE_FOODINSTIMESYNC + " VARCHAR, "
                + TABLE_FOODLASTISACREPSYNC + " VARCHAR, "
                + TABLE_FOODLASTINSPCTNREPSYNC + " VARCHAR, "
                + TABLE_CMT + " VARCHAR, "
                + TABLE_FOODSTATUSSYNC + " TINYINT);";
        db.execSQL(sql15);


        String sql16= "CREATE TABLE " + TABLE_CONDITIONOFEQIPMENT
                + "(" + TABLE_CONDITIONOFEQIPMENTID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_CONDITIONOFEQIPMENTINSIDSYNC + " VARCHAR, "
                + TABLE_CONDITIONUTENSILCONDTNSYNC + " VARCHAR, "
                + TABLE_CONDITIONMATCONDITNSYNC + " VARCHAR, "
                + TABLE_CONDITIONELEVNREGSTRSYNC + " VARCHAR, "
                + TABLE_CONDITIONELEVNREGSTRNOSYNC + " VARCHAR, "
                + TABLE_CONDITIONBABYWMACHINSYNC + " VARCHAR, "
                + TABLE_CONDITIONELEVNREGSTRBABYWMACHINSYNC + " VARCHAR, "


                + TABLE_CONDITIONADULTWMACHINSYNC + " VARCHAR, "
                + TABLE_ADUALTWEIGHINGMACHINEUSEDNOTUSED + " VARCHAR, "
                + TABLE_CONDITIONHEIGHTMEASURTYPSYNC + " VARCHAR, "
                + TABLE_HEIGHTMESURINGTYPE + " VARCHAR, "
                + TABLE_CONDITIONGROWTHCHRTSYNC + " VARCHAR, "
                + TABLE_PROPERFILLEDUP + " VARCHAR, "
                + TABLE_CONDITIONSTORECONTAINRSYNC + " VARCHAR, "
                + TABLE_SUFICENT + " VARCHAR, "
                + TABLE_CONDITIONHANDWASHSOAPSYNC + " VARCHAR, "
                + TABLE_HANDWASHINGSOPE + " VARCHAR, "


                + TABLE_CONDITIONCURTIMESYNC + " VARCHAR, "
                + TABLE_CONDITIONSTATUS + " TINYINT);";
        db.execSQL(sql16);

        String sql17= "CREATE TABLE " + TABLE_SHISHUALOYNAME
                + "(" + TABLE_SHISHUALOYID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_SHISHUALOYINSID + " VARCHAR, "
                + TABLE_SISHUALOY + " VARCHAR, "
                + TABLE_CORNERCGNITV + " VARCHAR, "
                + TABLE_BOOKCORNER + " VARCHAR, "
                + TABLE_DRAWCORNR + " VARCHAR, "
                + TABLE_PLAYCORNERS + " VARCHAR, "
                + TABLE_ECCRUN + " VARCHAR, "
                + TABLE_VALUESUTING + " VARCHAR, "
                + TABLE_ECCACTIVITYTYPE + " VARCHAR, "
                + TABLE_AWCVALUESSTRING + " VARCHAR, "
                + TABLE_TLAM + " VARCHAR, "
                + TABLE_PROPRECCKIT + " VARCHAR, "
                + TABLE_AVALEINDVSUALCHILDACTIVEYRCD + " VARCHAR, "
                + TABLE_INDVSUALCHILDACTVTYRCD + " VARCHAR, "
                + TABLE_AWCDECORTNFRECCE + " VARCHAR, "
                + TABLE_FITORECCCHECK + " VARCHAR, "
                + TABLE_CHILDENROLLED + " VARCHAR, "
                + TABLE_CHILDFOUNDTODAYY + " VARCHAR, "
                + TABLE_ECCEPROCESS + " VARCHAR, "
                + TABLE_ASSESMENTCARD + " VARCHAR, "
                + TABLE_ASSESMENTCARDUSE + " VARCHAR, "
                + TABLE_ECCKITDATE + " VARCHAR, "
                + TABLE_ECCOBSERDATE + " VARCHAR, "
                + TABLE_PSEACTFOUND + " VARCHAR, "
                + TABLE_PSEACTIVITYNM + " VARCHAR, "
                + TABLE_CHAILDPARTICIPATPSE + " VARCHAR, "
                + TABLE_AWWFOLLOWINGTHEROUTING + " VARCHAR, "
                + TABLE_SHISHUALOYINSPECTRCMNT + " VARCHAR, "
                + TABLE_SHISHUALOYCURTIME + " VARCHAR, "
                + TABLE_SHISHUALOYSTATUS + " TINYINT);";
        db.execSQL(sql17);
        String sql18= "CREATE TABLE " + TABLE_SPNTABLE
                + "(" + TABLE_SPNTABLEID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_SPNINSID + " VARCHAR, "
                + TABLE_MRNGSNACKS + " VARCHAR, "
                + TABLE_MRNGSNACKTYP + " VARCHAR, "
                + TABLE_SNPSERVE + " VARCHAR, "
                + TABLE_HCMASPERMNU + " VARCHAR, "
                + TABLE_SNPMENU + " VARCHAR, "
                + TABLE_CHLDPRSNT + " VARCHAR, "
                + TABLE_CHIDPRSNTTODAY + " VARCHAR, "

                + TABLE_PMLMPRSNT + " VARCHAR, "
                + TABLE_PMLMPRSNTTODAY + " VARCHAR, "

                + TABLE_AVRGFEEDNGLSTTHREMNTH + " VARCHAR, "
                + TABLE_FEEDINTRPTLM + " VARCHAR, "
                + TABLE_ANYFEEDINGINTRUPTNLSTTHREMNTH + " VARCHAR, "
                + TABLE_FEEDINTRPT2M + " VARCHAR, "
                + TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH2 + " VARCHAR, "
                + TABLE_FEEDINTRPT3M + " VARCHAR, "
                + TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH3 + " VARCHAR, "
                + TABLE_SNPNTSRVREASN + " VARCHAR, "


                + TABLE_SPNINSPCTNCMNT + " VARCHAR, "
                + TABLE_SPNCURTIME + " VARCHAR, "
                + TABLE_SPNSTATUS + " TINYINT);";
        db.execSQL(sql18);
        String sql19= "CREATE TABLE " + TABLE_NUTRITIONALTABLE
                + "(" + TABLE_NUTRITIONALTID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_NUTRITIONALTINS + " VARCHAR, "
                + TABLE_NUTRITIONALTINTIME + " VARCHAR, "
                + TABLE_MALNURISHDCHLD + " VARCHAR, "
                + TABLE_SANCHLD + " VARCHAR, "
                + TABLE_COMMANT + " VARCHAR, "
                + TABLE_NUTRITIONALSTATUS + " TINYINT);";
        db.execSQL(sql19);

        String sql20= "CREATE TABLE " + TABLE_OTHERINSPECTIONTABLENAME
                + "(" + TABLE_OTHERINSPECTIONTABLEID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_OTHERINSPECTIONINSID + " VARCHAR, "
                + TABLE_OTHERINSPECTIONINSCURTIME + " VARCHAR, "
                + TABLE_CMUNTYPRTICPTNNOTICE + " VARCHAR, "
                + TABLE_SUPVISIT + " VARCHAR, "
                + TABLE_LASTSUPUSTIDATE + " VARCHAR, "
                + TABLE_MEDCINKITLSTRCV + " VARCHAR, "
                + TABLE_LASTRECIVED + " VARCHAR, "
                + TABLE_USEDOFAWC + " VARCHAR, "
                + TABLE_GENGOTH + " VARCHAR, "
                + TABLE_AWCREMARK + " VARCHAR, "
                + TABLE_OTHERINSPECTIONSTATUS + " TINYINT);";
        db.execSQL(sql20);

        String sql21= "CREATE TABLE " + TABLE_UPDATECENTREPERSNDTL
                + "(" + TABLE_UPDATECENTREPERSNDTLID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_UPDATECENTREPERSNDTLDBDISTID + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLDBPROJECTID + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLCDPONAME + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLCDPONUMBER + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLACDPONAME + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLACDPOCONTRACT + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLDBSECTORID + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLSUPERVISORNAME + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLSUPERVISORNO + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLAWCSID + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLWORKERNAME + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLWORKERNO + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLHELPERNAME + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLHELPERNO + " VARCHAR, "
                + TABLE_UPDATECENTREPERSNDTLSTATUS + " TINYINT);";
        db.execSQL(sql21);

        String sql22= "CREATE TABLE " + TABLE_INSPECTIONPERSONPRESENT
                + "(" + TABLE_INSPECTIONPERSONPRESENTID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_INSPECTIONPERSONPRESENTDBDISTID + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTDBPROJECTID + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTDBSECTORID + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTDBCENTERID + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTYNCDPO + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTYNACDPIO + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTYNSUPERVISOR + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTYNWORKER + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTYHELPER + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTCURDATE + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTCURTIME + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTYNAWCS + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTUSERID + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTINSID + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTPLANID + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTPLANIDCMT + " VARCHAR, "
                + TABLE_INSPECTIONPERSONPRESENTSTATUS + " TINYINT);";
        db.execSQL(sql22);

        String sql23= "CREATE TABLE " + TABLE_FINALSUBTABLE
                + "(" + TABLE_FINALSUBID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_FINALSUBINS + " VARCHAR, "
                + TABLE_FINALSUBMITSTATUS + " TINYINT);";
        db.execSQL(sql23);

        String sql24= "CREATE TABLE " + TABLE_SPORTSYNC
                + "(" + TABLE_SPORTSYNCID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE_SPORTSINS + " VARCHAR, "
                + TABLE_AWCCODE + " VARCHAR, "
                + TBALE_SDISTRIC + " VARCHAR, "
                + TABLE_PLANIDSP + " VARCHAR, "
                + TABLE_PLANDATE + " VARCHAR, "
                + TABLE_PLANTIME + " VARCHAR, "
                + TABLE_USERIDPLAN + " VARCHAR, "
                + TABLE_SPORTSTATUS + " TINYINT);";
        db.execSQL(sql24);
    }
                @Override
                public void onUpgrade(SQLiteDatabase db, int i, int i1) {
                    String login = "DROP TABLE IF EXISTS TABLE_NAMEICDSLOGIN";
                    db.execSQL(login);
                    String sql1 = "DROP TABLE IF EXISTS TABLE_NAMEAWCSDTL";
                    db.execSQL(sql1);
                    String sql2 = "DROP TABLE IF EXISTS TABLE_PROCESS";
                    db.execSQL(sql2);
                    String sql3 = "DROP TABLE IF EXISTS TABLE_OWNBUlIDINGFUND";
                    db.execSQL(sql3);
                    String sql4 = "DROP TABLE IF EXISTS TABLE_NAMEISID";
                    db.execSQL(sql4);
                    String sql5 = "DROP TABLE IF EXISTS TABLE_DISTRICSP";
                    db.execSQL(sql5);
                    String sql6 = "DROP TABLE IF EXISTS TABLE_ALLINSPECTIONFLAG";
                    db.execSQL(sql6);
                    String sql7 = "DROP TABLE IF EXISTS TABLE_INSPECTION_PERSON_PRESENT";
                    db.execSQL(sql7);
                    String sql8 = "DROP TABLE IF EXISTS TABLE_YESNO";
                    db.execSQL(sql8);
                    String sql9 = "DROP TABLE IF EXISTS TABLE_BUILDINGSYNC";
                    db.execSQL(sql9);
                    String sql10 = "DROP TABLE IF EXISTS TABLE_TOILETSYNC";
                    db.execSQL(sql10);
                    String sql11 = "DROP TABLE IF EXISTS TABLE_KITCHENSYNC";
                    db.execSQL(sql11);
                    String sql12 = "DROP TABLE IF EXISTS TABLE_ADEQUATESPPSESYNC";
                    db.execSQL(sql12);
                    String sql13 = "DROP TABLE IF EXISTS TABLE_ELECTRITY";
                    db.execSQL(sql13);
                    String sql14 = "DROP TABLE IF EXISTS TABLE_DRINKINGWATERSYNC";
                    db.execSQL(sql14);
                    String sql15 = "DROP TABLE IF EXISTS TABLE_FOODSTORESYNC";
                    db.execSQL(sql15);
                    String sql16 = "DROP TABLE IF EXISTS TABLE_CONDITIONOFEQIPMENT";
                    db.execSQL(sql16);
                    String sql17 = "DROP TABLE IF EXISTS TABLE_SHISHUALOYNAME";
                    db.execSQL(sql17);
                    String sql18 = "DROP TABLE IF EXISTS TABLE_SPNTABLE";
                    db.execSQL(sql18);
                    String sql19 = "DROP TABLE IF EXISTS TABLE_NUTRITIONALTABLE";
                    db.execSQL(sql19);
                    String sql20 = "DROP TABLE IF EXISTS TABLE_OTHERINSPECTIONTABLENAME";
                    db.execSQL(sql20);
                    String sql21 = "DROP TABLE IF EXISTS TABLE_UPDATECENTREPERSNDTL";
                    db.execSQL(sql21);
                    String sql22 = "DROP TABLE IF EXISTS TABLE_INSPECTIONPERSONPRESENT";
                    db.execSQL(sql22);
                    String sql23 = "DROP TABLE IF EXISTS TABLE_FINALSUBTABLE";
                    db.execSQL(sql23);
                    String sql24 = "DROP TABLE IF EXISTS TABLE_SPORTSYNC";
                    db.execSQL(sql24);
               }
    public boolean IcdsLogin(String userid,String usertyp,String userdist,
                             String userimg,String username,String userdesig,
                             String userphn,String userwatsapp,String usermail,String userpwd,
                             String distid,String disname,
                             String discode,String distiduser,String distnameuser,String projiduser,
                             String projnameuser,String seciduser,String secnameuser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_USER_lID,userid);
        contentValues.put(TABLE_USER_TYP,usertyp);
        contentValues.put(TABLE_USER_DIST,userdist);
        contentValues.put(TABLE_USER_IMG,userimg);
        contentValues.put(TABLE_USER_NAME,username);
        contentValues.put(TABLE_USER_DESIG,userdesig);
        contentValues.put(TABLE_USER_PHN,userphn);
        contentValues.put(TABLE_USER_WATSAPP,userwatsapp);
        contentValues.put(TABLE_USER_MAIL,usermail);
        contentValues.put(TABLE_USER_PWD,userpwd);
        contentValues.put(TABLE_DISTID,distid);
        contentValues.put(TABLE_DISNAME,disname);
        contentValues.put(TABLE_DISCODE,discode);
        contentValues.put(TABLE_USERDISTID,distiduser);
        contentValues.put(TABLE_USERDISTIDNAME,distnameuser);
        contentValues.put(TABLE_USERPROJECTID,projiduser);
        contentValues.put(TABLE_USERPROJECTNAME,projnameuser);
        contentValues.put(TABLE_USERSECTORID,seciduser);
        contentValues.put(TABLE_USERSECTORNAME,secnameuser);
        db.insert(TABLE_NAMEICDSLOGIN, null, contentValues);
        db.close();
        return true;
    }
    public Cursor getLoginData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAMEICDSLOGIN,null);
        return res;
    }
        public boolean awcsdtlInsert(String water, String cdponame, String acdpocont, String buildstruc, String electric, String acdponame, String kitchen, String cdpocontact, String workerno,
                                     String workernm, String toilet, String awcslat, String supvsrname,
                                     String awcsslong, String helperno, String awcsadrs, String foodspace,
                                     String helpernm, String buildon, String adqutspacepse, String supvsrno,
                                     String awcsid,String awcscode,String awcsname,String planid,
                                     String lstinspctnbuldrep,String lstinspctntoiletrep,String lstinspctnkitchenrep,
                                     String lstinspctnpserep,String lstinspctnelectricrep,String lstinspctndrnkwaterrep,
                                     String lstinspctnfoodrep,String awcsuserid,String awcsflag,String allinspactionid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_WATER,water);
        contentValues.put(TABLE_CDPONAME,cdponame);
        contentValues.put(TABLE_ACDPOCONT,acdpocont);
        contentValues.put(TABLE_BUILDSTRUC,buildstruc);
        contentValues.put(TABLE_ELECTRIC,electric);
        contentValues.put(TABLE_ACDPONAME,acdponame);
        contentValues.put(TABLE_KITCHEN,kitchen);
        contentValues.put(TABLE_CDPOCONTACT,cdpocontact);
        contentValues.put(TABLE_WORKERNO,workerno);
        contentValues.put(TABLE_WORKERNM,workernm);
        contentValues.put(TABLE_TOILET,toilet);
        contentValues.put(TABLE_AWCSLAT,awcslat);
        contentValues.put(TABLE_SUPVSRNAME,supvsrname);
        contentValues.put(TABLE_AWCSLONG,awcsslong);
        contentValues.put(TABLE_HELPERNO,helperno);
        contentValues.put(TABLE_AWCSADRS,awcsadrs);
        contentValues.put(TABLE_FOODSPACE,foodspace);
        contentValues.put(TABLE_HELPERNM,helpernm);
        contentValues.put(TABLE_BUILDON,buildon);
        contentValues.put(TABLE_ADQUTSPACEPSE,adqutspacepse);
        contentValues.put(TABLE_SUPVSRNO,supvsrno);
        contentValues.put(TABLE_AWCSID,awcsid);
        contentValues.put(TABLE_AWCSCODE,awcscode);
        contentValues.put(TABLE_AWCSNAME,awcsname);
        contentValues.put(TABLE_PLANID,planid);
        contentValues.put(TABLE_LST_INSPCTN_BULD_REP,lstinspctnbuldrep);
        contentValues.put(TABLE_LST_INSPCTN_TOILET_REP,lstinspctntoiletrep);
        contentValues.put(TABLE_LST_INSPCTN_KITCHEN_REP,lstinspctnkitchenrep);
        contentValues.put(TABLE_LST_INSPCTN_PSE_REP,lstinspctnpserep);
        contentValues.put(TABLE_LST_INSPCTN_ELECTRIC_REP,lstinspctnelectricrep);
        contentValues.put(TABLE_LST_INSPCTN_DRNKWATER_REP,lstinspctndrnkwaterrep);
        contentValues.put(TABLE_LST_INSPCTN_FOOD_REP,lstinspctnfoodrep);
        contentValues.put(TABLE_AWCSUSERID,awcsuserid);
        contentValues.put(TABLE_AWCSFLAG,awcsflag);
        contentValues.put(TABLE_INSPECTIONID,allinspactionid);
        db.insert(TABLE_NAMEAWCSDTL, null, contentValues);
        db.close();
        return true;
    }

    public boolean awcsdtlupdateData(String id, String water, String cdponame, String acdpocont, String buildstruc, String electric, String acdponame, String kitchen, String cdpocontact, String workerno,
                                     String workernm, String toilet, String awcslat,
                                     String supvsrname, String awcslong, String helperno,
                                     String awcsadrs, String foodspace, String helpernm,
                                     String buildon, String adqutspacepse,
                                     String supvsrno,String awcsid,String awcscode,String awcsname,String planid,
                                     String lstinspctnbuldrep,String lstinspctntoiletrep,String lstinspctnkitchenrep,
                                     String lstinspctnpserep,String lstinspctnelectricrep,String lstinspctndrnkwaterrep,
                                     String lstinspctnfoodrep,String awcsuserid,String awcsflag,String allinspactionid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ID,id);
        contentValues.put(TABLE_WATER,water);
        contentValues.put(TABLE_CDPONAME,cdponame);
        contentValues.put(TABLE_ACDPOCONT,acdpocont);
        contentValues.put(TABLE_BUILDSTRUC,buildstruc);
        contentValues.put(TABLE_ELECTRIC,electric);
        contentValues.put(TABLE_ACDPONAME,acdponame);
        contentValues.put(TABLE_KITCHEN,kitchen);
        contentValues.put(TABLE_CDPOCONTACT,cdpocontact);
        contentValues.put(TABLE_WORKERNO,workerno);
        contentValues.put(TABLE_WORKERNM,workernm);
        contentValues.put(TABLE_TOILET,toilet);
        contentValues.put(TABLE_AWCSLAT,awcslat);
        contentValues.put(TABLE_SUPVSRNAME,supvsrname);
        contentValues.put(TABLE_AWCSLONG,awcslong);
        contentValues.put(TABLE_HELPERNO,helperno);
        contentValues.put(TABLE_AWCSADRS,awcsadrs);
        contentValues.put(TABLE_FOODSPACE,foodspace);
        contentValues.put(TABLE_HELPERNM,helpernm);
        contentValues.put(TABLE_BUILDON,buildon);
        contentValues.put(TABLE_ADQUTSPACEPSE,adqutspacepse);
        contentValues.put(TABLE_SUPVSRNO,supvsrno);
        contentValues.put(TABLE_AWCSID,awcsid);
        contentValues.put(TABLE_AWCSCODE,awcscode);
        contentValues.put(TABLE_AWCSNAME,awcsname);
        contentValues.put(TABLE_PLANID,planid);
        contentValues.put(TABLE_LST_INSPCTN_BULD_REP,lstinspctnbuldrep);
        contentValues.put(TABLE_LST_INSPCTN_TOILET_REP,lstinspctntoiletrep);
        contentValues.put(TABLE_LST_INSPCTN_KITCHEN_REP,lstinspctnkitchenrep);
        contentValues.put(TABLE_LST_INSPCTN_PSE_REP,lstinspctnpserep);
        contentValues.put(TABLE_LST_INSPCTN_ELECTRIC_REP,lstinspctnelectricrep);
        contentValues.put(TABLE_LST_INSPCTN_DRNKWATER_REP,lstinspctndrnkwaterrep);
        contentValues.put(TABLE_LST_INSPCTN_FOOD_REP,lstinspctnfoodrep);
        contentValues.put(TABLE_AWCSUSERID,awcsuserid);
        contentValues.put(TABLE_AWCSFLAG,awcsflag);
        contentValues.put(TABLE_INSPECTIONID,allinspactionid);
        db.update(TABLE_NAMEAWCSDTL, contentValues, "id = ?",new String[] { id });
        return true;
    }

      public Cursor getAwcDtls() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAMEAWCSDTL,null);
        return res;
    }

    public Cursor getAwcdetailID(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAMEAWCSDTL + " where " + TABLE_ID, null);
        return res;
    }
    public boolean awcsprocessInsert(String dbdistid, String dbprojectid, String dbsectorid,
                                     String dbcenterid, String projectnamedb, String districnamedb,
                                     String sectorrnamedb, String centernamedb, String currentdate,
                                     String userid,String flag,String awcslatlocation,String awcsslonglocation,
                                     String inspactionid,String awcscodeid,String awcstime,String flaggrecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_DBDISTID,dbdistid);
        contentValues.put(TABLE_DBPROJECTID,dbprojectid);
        contentValues.put(TABLE_DBSECTORID,dbsectorid);
        contentValues.put(TABLE_DBCENTERID,dbcenterid);
        contentValues.put(TABLE_DISTRIC,projectnamedb);
        contentValues.put(TABLE_PROJECT,districnamedb);

        contentValues.put(TABLE_SECTOR,sectorrnamedb);
        contentValues.put(TABLE_CENTER,centernamedb);
        contentValues.put(TABLE_CURRENDATE,currentdate);
        contentValues.put(TABLE_USERID,userid);
        contentValues.put(TABLE_FLAG,flag);
        contentValues.put(TABLE_AWCSLATLOCATION,awcslatlocation);
        contentValues.put(TABLE_AWCSLONGLOCATION,awcsslonglocation);
        contentValues.put(TABLE_INSPACTIONID,inspactionid);

        contentValues.put(TABLE_AWCSCODEID,awcscodeid);
        contentValues.put(TABLE_AWCSTIME,awcstime);
        contentValues.put(TABLE_FLAGGRECORD,flaggrecord);
        db.insert(TABLE_PROCESS, null, contentValues);
        db.close();
        return true;
    }

    public boolean awcsprocessUpdate(String idprocess, String dbdistid,
                                     String dbprojectid, String dbsectorid,
                                     String dbcenterid, String projectnamedb,
                                     String districnamedb, String sectorrnamedb,
                                     String centernamedb, String currentdate,
                                     String userid,String awcslatlocation,
                                     String awcsslonglocation,String inspactionid) {
        //String awcscodeid,String awcstime,String flaggrecord
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_PROCESSID,idprocess);
        contentValues.put(TABLE_DBDISTID,dbdistid);
        contentValues.put(TABLE_DBPROJECTID,dbprojectid);
        contentValues.put(TABLE_DBSECTORID,dbsectorid);
        contentValues.put(TABLE_DBCENTERID,dbcenterid);
        contentValues.put(TABLE_DISTRIC,projectnamedb);
        contentValues.put(TABLE_PROJECT,districnamedb);
        contentValues.put(TABLE_SECTOR,sectorrnamedb);
        contentValues.put(TABLE_CENTER,centernamedb);
        contentValues.put(TABLE_CURRENDATE,currentdate);

        contentValues.put(TABLE_USERID,userid);
        contentValues.put(TABLE_AWCSLATLOCATION,awcslatlocation);
        contentValues.put(TABLE_AWCSLONGLOCATION,awcsslonglocation);
        contentValues.put(TABLE_INSPACTIONID,inspactionid);
//        contentValues.put(TABLE_AWCSCODEID,awcscodeid);
//        contentValues.put(TABLE_AWCSTIME,awcstime);
//        contentValues.put(TABLE_FLAGGRECORD,flaggrecord);
        db.update(TABLE_PROCESS, contentValues, "idprocess = ?",new String[] { idprocess });
        db.close();
        return true;
    }

//    public boolean awcsprocessUpdate(String idprocess, String dbdistid,
//                                     String dbprojectid, String dbsectorid,
//                                     String dbcenterid, String projectnamedb,
//                                     String districnamedb, String sectorrnamedb,
//                                     String centernamedb, String currentdate,
//                                     String userid,String awcslatlocation,
//                                     String awcsslonglocation,String inspactionid,String awcscodeid,String awcstime,String flaggrecord) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(TABLE_INSPACTIONID,inspactionid);
//        contentValues.put(TABLE_DBDISTID,dbdistid);
//        contentValues.put(TABLE_DBPROJECTID,dbprojectid);
//        contentValues.put(TABLE_DBSECTORID,dbsectorid);
//        contentValues.put(TABLE_DBCENTERID,dbcenterid);
//        contentValues.put(TABLE_DISTRIC,projectnamedb);
//        contentValues.put(TABLE_PROJECT,districnamedb);
//        contentValues.put(TABLE_SECTOR,sectorrnamedb);
//        contentValues.put(TABLE_CENTER,centernamedb);
//        contentValues.put(TABLE_CURRENDATE,currentdate);
//        contentValues.put(TABLE_USERID,userid);
//        contentValues.put(TABLE_AWCSLATLOCATION,awcslatlocation);
//        contentValues.put(TABLE_AWCSLONGLOCATION,awcsslonglocation);
//        contentValues.put(TABLE_PROCESSID,idprocess);
//        contentValues.put(TABLE_AWCSCODEID,awcscodeid);
//        contentValues.put(TABLE_AWCSTIME,awcstime);
//        contentValues.put(TABLE_FLAGGRECORD,flaggrecord);
//        db.update(TABLE_PROCESS, contentValues, "inspactionid = ?",new String[] { inspactionid });
//        db.close();
//        return true;
//    }
    public Cursor getawcsprocessInsert() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_PROCESS,null);
        return res;
    }

    public boolean ownbuildingdetailsInsert(String funid,String funname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
    //    contentValues.put(TABLE_IDFUN,idfun);
        contentValues.put(TABLE_FID,funid);
        contentValues.put(TABLE_NAME,funname);
        db.insert(TABLE_OWNBUlIDINGFUND, null, contentValues);
        db.close();
        return true;
    }

    public boolean ownbuildingdetailsUpdate(String idfun,String funid,String funname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_IDFUN,idfun);
        contentValues.put(TABLE_FID,funid);
        contentValues.put(TABLE_NAME,funname);
        db.update(TABLE_OWNBUlIDINGFUND, contentValues, "idfun = ?",new String[] { idfun });
        db.close();
        return true;
    }
    public boolean districNameInsert(String disID,String distname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_DISTRICID,disID);
        contentValues.put(TABLE_DISTRICNAME,distname);
        db.insert(TABLE_DISTRICSP, null, contentValues);
        db.close();
        return true;
    }


    public Cursor getownbuildingdetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_OWNBUlIDINGFUND,null);
        return res;
    }
    public Cursor getdistric(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_DISTRICSP,null);
        return res;
    }
    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_OWNBUlIDINGFUND;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return labels;
    }


    public boolean InspactionIdInsert(String insid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_INSID,insid);
        db.insert(TABLE_NAMEISID, null, contentValues);
        db.close();
        return true;
    }
    public boolean InspactionIdUpdate(String inssid,String insid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_IDINSPACTION,inssid);
        contentValues.put(TABLE_INSID,insid);
        db.update(TABLE_NAMEISID, contentValues, "inssid = ?",new String[] { insid });
        db.close();
        return true;
    }
    public Cursor getInspactionId(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAMEISID,null);
        return res;
    }

    public boolean AllinspactionInsert(String buildingdetails,String informationoftoilet,String informationofkitchen,
                                       String adequatespaceforpse,String electricity,String drinkingwater,
                                       String foodstoreddetails,String conditionofequipmentotherawc,
                                       String shishualoy,String snpserved,String nutritionalstatusobserved,
                                       String otherinspection,String allinspactionid,String useridflag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_BUILDINGDETAILS,buildingdetails);
        contentValues.put(TABLE_INFORMATIONOFTOILET,informationoftoilet);
        contentValues.put(TABLE_INFORMATIONOFKITCHEN,informationofkitchen);
        contentValues.put(TABLE_ADEQUATESPACEFORPSE,adequatespaceforpse);
        contentValues.put(TABLE_ELECTRICITY,electricity);
        contentValues.put(TABLE_DRINKINGWATER,drinkingwater);
        contentValues.put(TABLE_FOODSTOREDDETAILS,foodstoreddetails);
        contentValues.put(TABLE_CONDITIONOFEQUIPMENTOTHERAWC,conditionofequipmentotherawc);
        contentValues.put(TABLE_SHISHUALOY,shishualoy);
        contentValues.put(TABLE_SNPSERVED,snpserved);
        contentValues.put(TABLE_NUTRITIONALSTATUSOBSERVED,nutritionalstatusobserved);
        contentValues.put(TABLE_OTHERINSPECTION,otherinspection);
        contentValues.put(TABLE_ALLINSPACATIONID,allinspactionid);
        contentValues.put(TABLE_USERIDFLA,useridflag);
       // contentValues.put(TABLE_FLAGGRECORD,flaggrecord);
        db.insert(TABLE_ALLINSPECTIONFLAG, null, contentValues);
        db.close();
        return true;

    }
    public boolean AllinspactionUpdate(String insflagid,String buildingdetails,String informationoftoilet,String informationofkitchen,String adequatespaceforpse,String electricity,String drinkingwater,String foodstoreddetails,String conditionofequipmentotherawc,String shishualoy,String snpserved,String nutritionalstatusobserved,String otherinspection,String allinspactionid,String useridflag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ALLINSPECTIONFLAGID,insflagid);
        contentValues.put(TABLE_BUILDINGDETAILS,buildingdetails);
        contentValues.put(TABLE_INFORMATIONOFTOILET,informationoftoilet);
        contentValues.put(TABLE_INFORMATIONOFKITCHEN,informationofkitchen);
        contentValues.put(TABLE_ADEQUATESPACEFORPSE,adequatespaceforpse);
        contentValues.put(TABLE_ELECTRICITY,electricity);
        contentValues.put(TABLE_DRINKINGWATER,drinkingwater);
        contentValues.put(TABLE_FOODSTOREDDETAILS,foodstoreddetails);
        contentValues.put(TABLE_CONDITIONOFEQUIPMENTOTHERAWC,conditionofequipmentotherawc);
        contentValues.put(TABLE_SHISHUALOY,shishualoy);
        contentValues.put(TABLE_SNPSERVED,snpserved);
        contentValues.put(TABLE_NUTRITIONALSTATUSOBSERVED,nutritionalstatusobserved);
        contentValues.put(TABLE_OTHERINSPECTION,otherinspection);
        contentValues.put(TABLE_ALLINSPACATIONID,allinspactionid);
        contentValues.put(TABLE_USERIDFLA,useridflag);
        db.update(TABLE_ALLINSPECTIONFLAG, contentValues, "insflagid = ?",new String[] { insflagid });
        db.close();
        return true;

    }
    public Cursor getInspactionData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_ALLINSPECTIONFLAG,null);
        return res;
    }

    public boolean InspactionInsert(String cdpoprsnt,String aacdpoprsnt,String supvsrprsnt,String workerprsnt,String helperprsnt,String awcsopen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_CDPOPRSNT,cdpoprsnt);
        contentValues.put(TABLE_ACDPOPRSNT,aacdpoprsnt);
        contentValues.put(TABLE_SUPVSRPRSNT,supvsrprsnt);
        contentValues.put(TABLE_WORKERPRSNT,workerprsnt);
        contentValues.put(TABLE_HELPERPRSNT,helperprsnt);
        contentValues.put(TABLE_AWCSOPEN,awcsopen);
        db.insert(TABLE_YESNO, null, contentValues);
        db.close();
        return true;

    }
    public boolean InspactionUpdate(String tableyesnoid,String cdpoprsnt,String acdpoprsnt,String supvsrprsnt,String workerprsnt,String helperprsnt,String awcsopen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_YESNOID,tableyesnoid);
        contentValues.put(TABLE_CDPOPRSNT,cdpoprsnt);
        contentValues.put(TABLE_ACDPOPRSNT,acdpoprsnt);
        contentValues.put(TABLE_SUPVSRPRSNT,supvsrprsnt);
        contentValues.put(TABLE_WORKERPRSNT,workerprsnt);
        contentValues.put(TABLE_HELPERPRSNT,helperprsnt);
        contentValues.put(TABLE_AWCSOPEN,awcsopen);
        db.update(TABLE_YESNO, contentValues, "tableyesnoid = ?",new String[] { tableyesnoid });
        db.close();
        return true;
    }
    public Cursor getyesnoInspaction(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_YESNO,null);
        return res;
    }
/////////////////////////////////////////////SYNC///////////////////////////////////////////////////////////
public boolean BUILDING(String dbdistidsync,String dbprojectidsync,String dbsectoridsync,String dbcenteridsync,String builttypsync,String builtruninsync,
                        String rentbuildinsync,String othrgovtbuildinsync,String buildconditn ,String inspctrcmnt, String  userIDsync,String insidsync,String curDatesync,String curTime,String latisacreportsync,String lstinspctnbuldrepsync,int buildingstatus) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(TABLE_DBDISTIDSYNC, dbdistidsync);
    contentValues.put(TABLE_DBPROJECTIDSYNC, dbprojectidsync);
    contentValues.put(TABLE_DBSECTORIDSYNC, dbsectoridsync);
    contentValues.put(TABLE_DBCENTERIDSYNC, dbcenteridsync);
    contentValues.put(TABLE_BUILTTYP, builttypsync);
    contentValues.put(TABLE_BUILTRUNIN, builtruninsync);
    ////// own building fund/////////
  //  contentValues.put(TABLE_OWNBUILDFUND, ownbuildfundsync);

    contentValues.put(TABLE_RENTBUILDIN, rentbuildinsync);
    contentValues.put(TABLE_OTHRGOVTBUILDIN, othrgovtbuildinsync);
    contentValues.put(TABLE_BUILDCONDITN, buildconditn);
    contentValues.put(TABLE_INSPCTRCMNT, inspctrcmnt);
    contentValues.put(TABLE_USERIDSYNC,userIDsync);
    contentValues.put(TABLE_INSIDSYNC,insidsync);
    contentValues.put(TABLE_CURDATE,curDatesync);
    contentValues.put(TABLE_CURTIME,curTime);
    contentValues.put(TABLE_LATISACREPORT,latisacreportsync);
    contentValues.put(TABLE_LSTINSPCTNBULDREP,lstinspctnbuldrepsync);
    contentValues.put(TABLE_STATUSBUILDING,buildingstatus);
    db.insert(TABLE_BUILDINGSYNC, null, contentValues);
    db.close();
    return true;
}

    public boolean BUILDINGUPDATE(String buildingidsync,String dbdistidsync,String dbprojectidsync,String dbsectoridsync,String dbcenteridsync,String builttypsync,String builtruninsync,
                                  String rentbuildinsync,String othrgovtbuildinsync,String buildconditn ,String inspctrcmnt, String  userIDsync,String insidsync,String curDatesync,String curTime,String latisacreportsync,String lstinspctnbuldrepsync,int buildingstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_BUILDINGIDSYNC,buildingidsync);
        contentValues.put(TABLE_DBDISTIDSYNC, dbdistidsync);
        contentValues.put(TABLE_DBPROJECTIDSYNC, dbprojectidsync);
        contentValues.put(TABLE_DBSECTORIDSYNC, dbsectoridsync);
        contentValues.put(TABLE_DBCENTERIDSYNC, dbcenteridsync);
        contentValues.put(TABLE_BUILTTYP, builttypsync);
        contentValues.put(TABLE_BUILTRUNIN, builtruninsync);
        ////// own building fund/////////
     //   contentValues.put(TABLE_OWNBUILDFUND, ownbuildfundsync);

        contentValues.put(TABLE_RENTBUILDIN, rentbuildinsync);
        contentValues.put(TABLE_OTHRGOVTBUILDIN, othrgovtbuildinsync);
        contentValues.put(TABLE_BUILDCONDITN, buildconditn);
        contentValues.put(TABLE_INSPCTRCMNT, inspctrcmnt);
        contentValues.put(TABLE_USERIDSYNC,userIDsync);
        contentValues.put(TABLE_INSIDSYNC,insidsync);
        contentValues.put(TABLE_CURDATE,curDatesync);
        contentValues.put(TABLE_CURTIME,curTime);
        contentValues.put(TABLE_LATISACREPORT,latisacreportsync);
        contentValues.put(TABLE_LSTINSPCTNBULDREP,lstinspctnbuldrepsync);
        contentValues.put(TABLE_STATUSBUILDING,buildingstatus);
        db.update(TABLE_BUILDINGSYNC, contentValues, "buildingidsync = ?",new String[] { buildingidsync });
//        db.update(TABLE_BUILDINGSYNC, contentValues, "insidsync = ?",new String[] { insidsync });
        db.close();
        return true;
    }

    public boolean updateBuildingSyncStatus(int buildingidsync, int buildingstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_STATUSBUILDING, buildingstatus);
        db.update(TABLE_BUILDINGSYNC, contentValues, TABLE_BUILDINGIDSYNC + "=" + buildingidsync, null);
        db.close();
        return true;
    }

    public Cursor getBuildingSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_BUILDINGSYNC + " ORDER BY " + TABLE_BUILDINGIDSYNC + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getUnsyncedBuildingDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_BUILDINGSYNC + " WHERE " + TABLE_STATUSBUILDING + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    ///////////////////////////////////////TOILET///////////////////////////////////////////////////////////////
    public boolean TOILETINSERT(String insidtoiletsync,String yntoiletsync,String toiletusablesync,String inspctrcmntsync,String curTimesync,String lastisacrepsync,String lstinspctntoiletrepsync,int toiletstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_INSIDTOILETSYNC, insidtoiletsync);
        contentValues.put(TABLE_YNTOILETSYNC,yntoiletsync);
        contentValues.put(TABLE_TOILETUSABLESYNC,toiletusablesync);
        contentValues.put(TABLE_INSPCTRCMNTSYNC,inspctrcmntsync);
        contentValues.put(TABLE_CURTIMESYNC,curTimesync);
        contentValues.put(TABLE_LASTISACREPSYNC,lastisacrepsync);
        contentValues.put(TABLE_LSTINSPCTNTOILETREPSYNC,lstinspctntoiletrepsync);
        contentValues.put(TABLE_TOILETSTATUS,toiletstatus);
        db.insert(TABLE_TOILETSYNC, null, contentValues);
        db.close();
        return true;
    }

    public boolean TOILETUPDATE(String toiletidsync,String insidtoiletsync,String yntoiletsync,String toiletusablesync,String inspctrcmntsync,String curTimesync,String lastisacrepsync,String lstinspctntoiletrepsync,int toiletstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_TOILETIDSYNC,toiletidsync);
        contentValues.put(TABLE_INSIDTOILETSYNC, insidtoiletsync);
        contentValues.put(TABLE_YNTOILETSYNC,yntoiletsync);
        contentValues.put(TABLE_TOILETUSABLESYNC,toiletusablesync);
        contentValues.put(TABLE_INSPCTRCMNTSYNC,inspctrcmntsync);
        contentValues.put(TABLE_CURTIMESYNC,curTimesync);
        contentValues.put(TABLE_LASTISACREPSYNC,lastisacrepsync);
        contentValues.put(TABLE_LSTINSPCTNTOILETREPSYNC,lstinspctntoiletrepsync);
        contentValues.put(TABLE_TOILETSTATUS,toiletstatus);
        db.update(TABLE_TOILETSYNC, contentValues, "toiletidsync = ?",new String[] { toiletidsync });
        db.close();
        return true;
    }

    public boolean updateToiletSyncStatus(int toiletidsync, int toiletstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_TOILETSTATUS, toiletstatus);
        db.update(TABLE_TOILETSYNC, contentValues, TABLE_TOILETIDSYNC + "=" + toiletidsync, null);
        db.close();
        return true;
    }
    public Cursor getToiletSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_TOILETSYNC + " ORDER BY " + TABLE_TOILETIDSYNC + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedToiletDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_TOILETSYNC + " WHERE " + TABLE_TOILETSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    /////////////////////////KITCHEN/////////////////////////////////////////////////////////////
    public boolean KITCHENINSERT(String kitcheninsidsync,String seprtkitchnsync,String seprtcookdoneitchnsync,String toiletinspctrcmntsync,String instimesync,String toiletlastisacrepsync,String lastinspctnrepsync,int kitchenstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_KITCHENINSID, kitcheninsidsync);
        contentValues.put(TABLE_SEPRTKITCHN,seprtkitchnsync);
        contentValues.put(TABLE_SEPRTCOOKDONEITCHNSYNC,seprtcookdoneitchnsync);
        contentValues.put(TABLE_TOILETINSPCTRCMNTSYNC,toiletinspctrcmntsync);
        contentValues.put(TABLE_INSTIMESYNC,instimesync);
        contentValues.put(TABLE_TOILETLASTISACREPSYNC,toiletlastisacrepsync);
        contentValues.put(TABLE_LASTINSPCTNREPSYNC,lastinspctnrepsync);
        contentValues.put(TABLE_KITCHENSTATUS,kitchenstatus);
        db.insert(TABLE_KITCHENSYNC, null, contentValues);
        db.close();
        return true;
    }

    public boolean KITCHENUPDATE(String kitchenidsync,String kitcheninsidsync,String seprtkitchnsync,String seprtcookdoneitchnsync,String toiletinspctrcmntsync,String instimesync,String toiletlastisacrepsync,String lastinspctnrepsync,int kitchenstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_KITCHENIDSYNC,kitchenidsync);
        contentValues.put(TABLE_KITCHENINSID, kitcheninsidsync);
        contentValues.put(TABLE_SEPRTKITCHN,seprtkitchnsync);
        contentValues.put(TABLE_SEPRTCOOKDONEITCHNSYNC,seprtcookdoneitchnsync);
        contentValues.put(TABLE_TOILETINSPCTRCMNTSYNC,toiletinspctrcmntsync);
        contentValues.put(TABLE_INSTIMESYNC,instimesync);
        contentValues.put(TABLE_TOILETLASTISACREPSYNC,toiletlastisacrepsync);
        contentValues.put(TABLE_LASTINSPCTNREPSYNC,lastinspctnrepsync);
        contentValues.put(TABLE_KITCHENSTATUS,kitchenstatus);
        db.update(TABLE_KITCHENSYNC, contentValues, "kitchenidsync = ?",new String[] { kitchenidsync });
        db.close();
        return true;
    }
    public boolean updateKitchenSyncStatus(int kitchenidsync, int kitchenstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_KITCHENSTATUS, kitchenstatus);
        db.update(TABLE_KITCHENSYNC, contentValues, TABLE_KITCHENIDSYNC + "=" + kitchenidsync, null);
        db.close();
        return true;
    }
    public Cursor getKitchenSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_KITCHENSYNC + " ORDER BY " + TABLE_KITCHENIDSYNC + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedKitchenDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_KITCHENSYNC + " WHERE " + TABLE_KITCHENSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
   /////////////////////////////////////////////////ADQUSPACE//////////////////////////////////////////////
   public boolean ADQUESPACEINSERT(String adequtspaceinsidsync,String adequtspacesync,String pseactvtytypsync,String adequtspacecurTimesync,String adequtspaceinspctrcmntsync,String adqutspacelastisacrepsync,String adqutspacelastinspctnrepsync,int adequatesppsestatus) {
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put(TABLE_ADEQUTSPACEINSIDSYNC, adequtspaceinsidsync);
       contentValues.put(TABLE_ADEQUTSPACE,adequtspacesync);
       contentValues.put(TABLE_PSEACTVTYTYP,pseactvtytypsync);
       contentValues.put(TABLE_ADEQUTSPACECURTIMESYNC,adequtspacecurTimesync);
       contentValues.put(TABLE_ADQUTSPACEINSPCTRCMNT,adequtspaceinspctrcmntsync);
       contentValues.put(TABLE_ADQUTSPLASTISACREPSYNC,adqutspacelastisacrepsync);
       contentValues.put(TABLE_ADQUTSPACELASTINSPCTNREPSYNC,adqutspacelastinspctnrepsync);
       contentValues.put(TABLE_ADEQUATESPPSESTATUS,adequatesppsestatus);
       db.insert(TABLE_ADEQUATESPPSESYNC, null, contentValues);
       db.close();
       return true;
   }

    public boolean ADQUESPACEUPDATE(String adequatesppseid,String adequtspaceinsidsync,String adequtspacesync,String pseactvtytypsync,String adequtspacecurTimesync,String adequtspaceinspctrcmntsync,String adqutspacelastisacrepsync,String adqutspacelastinspctnrepsync,int adequatesppsestatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ADEQUATESPPSEID,adequatesppseid);
        contentValues.put(TABLE_ADEQUTSPACEINSIDSYNC, adequtspaceinsidsync);
        contentValues.put(TABLE_ADEQUTSPACE,adequtspacesync);
        contentValues.put(TABLE_PSEACTVTYTYP,pseactvtytypsync);
        contentValues.put(TABLE_ADEQUTSPACECURTIMESYNC,adequtspacecurTimesync);
        contentValues.put(TABLE_ADQUTSPACEINSPCTRCMNT,adequtspaceinspctrcmntsync);
        contentValues.put(TABLE_ADQUTSPLASTISACREPSYNC,adqutspacelastisacrepsync);
        contentValues.put(TABLE_ADQUTSPACELASTINSPCTNREPSYNC,adqutspacelastinspctnrepsync);
        contentValues.put(TABLE_ADEQUATESPPSESTATUS,adequatesppsestatus);
        db.update(TABLE_ADEQUATESPPSESYNC, contentValues, "adequatesppseid = ?",new String[] { adequatesppseid });
        db.close();
        return true;
    }
    public boolean updateAdquespaceSyncStatus(int adequatesppseid, int adequatesppsestatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ADEQUATESPPSESTATUS,adequatesppsestatus);
        db.update(TABLE_ADEQUATESPPSESYNC, contentValues, TABLE_ADEQUATESPPSEID + "=" + adequatesppseid, null);
        db.close();
        return true;
    }

    public Cursor getAdquespaceSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_ADEQUATESPPSESYNC + " ORDER BY " + TABLE_ADEQUATESPPSEID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedAdquespaceDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_ADEQUATESPPSESYNC + " WHERE " + TABLE_ADEQUATESPPSESTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    ////////////////////////////////////////ELETRICITY////////////////////////////////////////////////////////////////
    public boolean ELETRICITYINSERT(String electrityinsidsync,String electricavailsync,String emodesync,String lighttypesync,String fantypesync,String pumpovrhdsync,String inspctrcmntelectricitysync,String cuttimeeletricitysync,String lastisacrepeletricitysync,String lastinspctnrepeletricitysync,int eletricitystatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ELECTRITYINSIDSYNC,electrityinsidsync);
        contentValues.put(TABLE_ELECTRICAVAILSYNC, electricavailsync);
        contentValues.put(TABLE_EMODESYNC,emodesync);
        contentValues.put(TABLE_LIGHTTYPESYNC,lighttypesync);
        contentValues.put(TABLE_FANTYPESYNC,fantypesync);
        contentValues.put(TABLE_PUMPOVRHDSYNC,pumpovrhdsync);
        contentValues.put(TABLE_ELECRITYINSPCTRCMNTSYNC,inspctrcmntelectricitysync);
        contentValues.put(TABLE_ELECTRITYCUTTIMESYNC,cuttimeeletricitysync);
        contentValues.put(TABLE_LASTISACREPELECTRICITYSYNC,lastisacrepeletricitysync);
        contentValues.put(TABLE_LASTINSPCTNREPELETRICITYSYNC,lastinspctnrepeletricitysync);
        contentValues.put(TABLE_ELECTRICITYSTATUS,eletricitystatus);
        db.insert(TABLE_ELECTRITY, null, contentValues);
        db.close();
        return true;
    }
    public boolean ELETRICITYUPDATE(String electrityid,String electrityinsidsync,String electricavailsync,String emodesync,String lighttypesync,String fantypesync,String pumpovrhdsync,String inspctrcmntelectricitysync,String cuttimeeletricitysync,String lastisacrepeletricitysync,String lastinspctnrepeletricitysync,int eletricitystatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ELECTRITYID,electrityid);
        contentValues.put(TABLE_ELECTRITYINSIDSYNC,electrityinsidsync);
        contentValues.put(TABLE_ELECTRICAVAILSYNC, electricavailsync);
        contentValues.put(TABLE_EMODESYNC,emodesync);
        contentValues.put(TABLE_LIGHTTYPESYNC,lighttypesync);
        contentValues.put(TABLE_FANTYPESYNC,fantypesync);
        contentValues.put(TABLE_PUMPOVRHDSYNC,pumpovrhdsync);
        contentValues.put(TABLE_ELECRITYINSPCTRCMNTSYNC,inspctrcmntelectricitysync);
        contentValues.put(TABLE_ELECTRITYCUTTIMESYNC,cuttimeeletricitysync);
        contentValues.put(TABLE_LASTISACREPELECTRICITYSYNC,lastisacrepeletricitysync);
        contentValues.put(TABLE_LASTINSPCTNREPELETRICITYSYNC,lastinspctnrepeletricitysync);
        contentValues.put(TABLE_ELECTRICITYSTATUS,eletricitystatus);
        db.update(TABLE_ELECTRITY, contentValues, "electrityid = ?",new String[] { electrityid });
        db.close();
        return true;
    }

    public boolean updateEletricitySyncStatus(int electrityid, int eletricitystatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ELECTRICITYSTATUS,eletricitystatus);
        db.update(TABLE_ELECTRITY, contentValues, TABLE_ELECTRITYID + "=" + electrityid, null);
        db.close();
        return true;
    }
    public Cursor getEletricitySync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_ELECTRITY + " ORDER BY " + TABLE_ELECTRITYID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedEletricityDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_ELECTRITY + " WHERE " + TABLE_ELECTRICITYSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
  ///////////////////////////////ELETRICITY/////////////////////////////////////////////////////////////
  public boolean DRINKINGWATERINSERT(String drinkingwaterinsid,String owndrinkwatersync,String drinkingwatercurtimesync,String drinkingwaterlastisacrepsync,String lastinspctnrepdrinkingsync,String drinkwaterremark,int drinkingwaterstatus) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(TABLE_DRINKINGWATERINSID,drinkingwaterinsid);
      contentValues.put(TABLE_DRINKINGWATEROWNDRINKWATERSYNC, owndrinkwatersync);
      contentValues.put(TABLE_DRINKINGWATERCURTIMESYNC,drinkingwatercurtimesync);
      contentValues.put(TABLE_DRINKINGWATERLASTISACREPSYNC,drinkingwaterlastisacrepsync);
      contentValues.put(TABLE_LASTINSPCTNREPDRINKINGSYNC,lastinspctnrepdrinkingsync);
      contentValues.put(TABLE_LASTINSPCTNREPDRINKINGCOMMANDSYNC,drinkwaterremark);
      contentValues.put(TABLE_DRINKINGWATERSTATUS,drinkingwaterstatus);
      db.insert(TABLE_DRINKINGWATERSYNC, null, contentValues);
      db.close();
      return true;
  }
    public boolean DRINKINGWATERUPDATE(String drinkingwateridsync,String drinkingwaterinsid,String owndrinkwatersync,String drinkingwatercurtimesync,String drinkingwaterlastisacrepsync,String lastinspctnrepdrinkingsync,String drinkwaterremark,int drinkingwaterstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_DRINKINGWATERIDSYNC,drinkingwateridsync);
        contentValues.put(TABLE_DRINKINGWATERINSID,drinkingwaterinsid);
        contentValues.put(TABLE_DRINKINGWATEROWNDRINKWATERSYNC, owndrinkwatersync);
        contentValues.put(TABLE_DRINKINGWATERCURTIMESYNC,drinkingwatercurtimesync);
        contentValues.put(TABLE_DRINKINGWATERLASTISACREPSYNC,drinkingwaterlastisacrepsync);
        contentValues.put(TABLE_LASTINSPCTNREPDRINKINGSYNC,lastinspctnrepdrinkingsync);
        contentValues.put(TABLE_LASTINSPCTNREPDRINKINGCOMMANDSYNC,drinkwaterremark);
        contentValues.put(TABLE_DRINKINGWATERSTATUS,drinkingwaterstatus);
        db.update(TABLE_DRINKINGWATERSYNC, contentValues, "drinkingwateridsync = ?",new String[] { drinkingwateridsync });
        db.close();
        return true;
    }
    public boolean updateDrinkingSyncStatus(int drinkingwateridsync, int drinkingwaterstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_DRINKINGWATERSTATUS,drinkingwaterstatus);
        db.update(TABLE_DRINKINGWATERSYNC, contentValues, TABLE_DRINKINGWATERIDSYNC + "=" + drinkingwateridsync, null);
        db.close();
        return true;
    }

    public Cursor getDrinkingySync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_DRINKINGWATERSYNC + " ORDER BY " + TABLE_DRINKINGWATERIDSYNC + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getUnsyncedDrinkingDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_DRINKINGWATERSYNC + " WHERE " + TABLE_DRINKINGWATERSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    ///////////////////////FOODSTORE//////////////////////////////////////////////////////////////////////////////////
    public boolean FOODINSERT(String foodinsidsync,String foodstorespacesync,
                              String foodphysiclchksync,String foodstkbookcmparesync,
                              String foodstksuficntsync,String foodricedalsync,
                              String fooddalbrndsync,String musteroilsysnc,String saltsync,
                              String foodstocklyngsync,String foodinstimesync,String foodlastisacrepsync,
                              String foodlastinspctnrepsync,String foodcmt,int foodstatussync) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_FOODINSIDSYNC,foodinsidsync);
        contentValues.put(TABLE_FOODSTORESPACESYNC, foodstorespacesync);
        contentValues.put(TABLE_FOODPHYSICLCHKSYNC,foodphysiclchksync);
        contentValues.put(TABLE_FOODSTKBOOKCMPARESYNC,foodstkbookcmparesync);
        contentValues.put(TABLE_FOODSTKSUFICNTSYNC,foodstksuficntsync);
        contentValues.put(TABLE_FOODRICEDALSYNC,foodricedalsync);
        contentValues.put(TABLE_FOODDALSYNC,fooddalbrndsync);

        contentValues.put(TABLE_FOODMAUSTEROILSYNC,musteroilsysnc);
        contentValues.put(TABLE_FOODSALTSYNC,saltsync);

        contentValues.put(TABLE_FOODSTOCKLYNGSYNC,foodstocklyngsync);
        contentValues.put(TABLE_FOODINSTIMESYNC,foodinstimesync);
        contentValues.put(TABLE_FOODLASTISACREPSYNC,foodlastisacrepsync);
        contentValues.put(TABLE_FOODLASTINSPCTNREPSYNC,foodlastinspctnrepsync);
        contentValues.put(TABLE_CMT,foodcmt);
        contentValues.put(TABLE_FOODSTATUSSYNC,foodstatussync);
        db.insert(TABLE_FOODSTORESYNC, null, contentValues);
        db.close();
        return true;
    }

    public boolean FOODUPDATE(String foodstoreidsync,String foodinsidsync,String foodstorespacesync,
                              String foodphysiclchksync,String foodstkbookcmparesync,String foodstksuficntsync,
                              String foodricedalsync,String fooddalbrndsync,String musteroilsysnc,String saltsync,
                              String foodstocklyngsync,String foodinstimesync,String foodlastisacrepsync,String  foodlastinspctnrepsync,String foodcmt,int foodstatussync) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_FOODSTOREIDSYNC,foodstoreidsync);
        contentValues.put(TABLE_FOODINSIDSYNC,foodinsidsync);
        contentValues.put(TABLE_FOODSTORESPACESYNC, foodstorespacesync);
        contentValues.put(TABLE_FOODPHYSICLCHKSYNC,foodphysiclchksync);
        contentValues.put(TABLE_FOODSTKBOOKCMPARESYNC,foodstkbookcmparesync);
        contentValues.put(TABLE_FOODSTKSUFICNTSYNC,foodstksuficntsync);
        contentValues.put(TABLE_FOODRICEDALSYNC,foodricedalsync);
        contentValues.put(TABLE_FOODDALSYNC,fooddalbrndsync);

        contentValues.put(TABLE_FOODMAUSTEROILSYNC,musteroilsysnc);
        contentValues.put(TABLE_FOODSALTSYNC,saltsync);

        contentValues.put(TABLE_FOODSTOCKLYNGSYNC,foodstocklyngsync);
        contentValues.put(TABLE_FOODINSTIMESYNC,foodinstimesync);
        contentValues.put(TABLE_FOODLASTISACREPSYNC,foodlastisacrepsync);
        contentValues.put(TABLE_FOODLASTINSPCTNREPSYNC,foodlastinspctnrepsync);
        contentValues.put(TABLE_CMT,foodcmt);
        contentValues.put(TABLE_FOODSTATUSSYNC,foodstatussync);
        db.update(TABLE_FOODSTORESYNC, contentValues, "foodstoreidsync = ?",new String[] { foodstoreidsync });
        db.close();
        return true;
    }
    public boolean updateFoodSyncStatus(int foodstoreidsync, int foodstatussync){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_FOODSTATUSSYNC,foodstatussync);
        db.update(TABLE_FOODSTORESYNC, contentValues, TABLE_FOODSTOREIDSYNC + "=" + foodstoreidsync, null);
        db.close();
        return true;
    }
    public Cursor getFoodySync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_FOODSTORESYNC + " ORDER BY " + TABLE_FOODSTOREIDSYNC + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedFoodDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_FOODSTORESYNC + " WHERE " + TABLE_FOODSTATUSSYNC + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    /////////////////////////////////CONDITIONOFEQUIPMENTOTHERS///////////////////////////////////////////////
    public boolean CONDITIONINSERT(String conditionofeqipmentinsidsync,
                                   String utensilcondtnsync,
                                   String matconditnsync,
                                   String elevnregstrsync,
                                   String elavenregister,
                                   String babywmachinsync,
                                   String conditionbabyweighingmechinusednotoused,
                                   String adultwmachinsync,
                                   String adualtweighingmachineusednotused,
                                   String heightmeasurtypsync,
                                   String heightmeasuringtype,
                                   String growthchrtsync,
                                   String properfilledup,
                                   String storecontainrsync,
                                   String suficent,
                                   String handwashsoapsync,
                                   String handwashingsope,

                                   String conditioncurtimesync,int conditionstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_CONDITIONOFEQIPMENTINSIDSYNC,conditionofeqipmentinsidsync);
        contentValues.put(TABLE_CONDITIONUTENSILCONDTNSYNC, utensilcondtnsync);
        contentValues.put(TABLE_CONDITIONMATCONDITNSYNC,matconditnsync);
        contentValues.put(TABLE_CONDITIONELEVNREGSTRSYNC,elevnregstrsync);
        contentValues.put(TABLE_CONDITIONELEVNREGSTRNOSYNC,elavenregister);
        contentValues.put(TABLE_CONDITIONBABYWMACHINSYNC,babywmachinsync);
        contentValues.put(TABLE_CONDITIONELEVNREGSTRBABYWMACHINSYNC,conditionbabyweighingmechinusednotoused);
        contentValues.put(TABLE_CONDITIONADULTWMACHINSYNC,adultwmachinsync);


        contentValues.put(TABLE_ADUALTWEIGHINGMACHINEUSEDNOTUSED,adualtweighingmachineusednotused);
        contentValues.put(TABLE_CONDITIONHEIGHTMEASURTYPSYNC,heightmeasurtypsync);
        contentValues.put(TABLE_HEIGHTMESURINGTYPE,heightmeasuringtype);
        contentValues.put(TABLE_CONDITIONGROWTHCHRTSYNC,growthchrtsync);
        contentValues.put(TABLE_PROPERFILLEDUP,properfilledup);
        contentValues.put(TABLE_CONDITIONSTORECONTAINRSYNC,storecontainrsync);
        contentValues.put(TABLE_SUFICENT,suficent);
        contentValues.put(TABLE_CONDITIONHANDWASHSOAPSYNC,handwashsoapsync);
        contentValues.put(TABLE_HANDWASHINGSOPE,handwashingsope);

        contentValues.put(TABLE_CONDITIONCURTIMESYNC,conditioncurtimesync);
        contentValues.put(TABLE_CONDITIONSTATUS,conditionstatus);
        db.insert(TABLE_CONDITIONOFEQIPMENT, null, contentValues);
        db.close();
        return true;
    }


    public boolean CONDITIONUPDATE(String conditionofeqipmentid,String conditionofeqipmentinsidsync,
                                   String utensilcondtnsync,
                                   String matconditnsync,
                                   String elevnregstrsync,
                                   String elavenregister,
                                   String babywmachinsync,
                                   String conditionbabyweighingmechinusednotoused,
                                   String adultwmachinsync,
                                   String adualtweighingmachineusednotused,

                                   String heightmeasurtypsync,
                                   String heightmeasuringtype,
                                   String growthchrtsync,
                                   String properfilledup,
                                   String storecontainrsync,
                                   String suficent,
                                   String handwashsoapsync,
                                   String handwashingsope,

                                   String conditioncurtimesync,
                                   int conditionstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_CONDITIONOFEQIPMENTID,conditionofeqipmentid);
        contentValues.put(TABLE_CONDITIONOFEQIPMENTINSIDSYNC,conditionofeqipmentinsidsync);
        contentValues.put(TABLE_CONDITIONUTENSILCONDTNSYNC, utensilcondtnsync);
        contentValues.put(TABLE_CONDITIONMATCONDITNSYNC,matconditnsync);
        contentValues.put(TABLE_CONDITIONELEVNREGSTRSYNC,elevnregstrsync);
        contentValues.put(TABLE_CONDITIONELEVNREGSTRNOSYNC,elavenregister);

        contentValues.put(TABLE_CONDITIONBABYWMACHINSYNC,babywmachinsync);
        contentValues.put(TABLE_CONDITIONELEVNREGSTRBABYWMACHINSYNC,conditionbabyweighingmechinusednotoused);


        contentValues.put(TABLE_CONDITIONADULTWMACHINSYNC,adultwmachinsync);
        contentValues.put(TABLE_ADUALTWEIGHINGMACHINEUSEDNOTUSED,adualtweighingmachineusednotused);
        contentValues.put(TABLE_CONDITIONHEIGHTMEASURTYPSYNC,heightmeasurtypsync);
        contentValues.put(TABLE_HEIGHTMESURINGTYPE,heightmeasuringtype);
        contentValues.put(TABLE_CONDITIONGROWTHCHRTSYNC,growthchrtsync);
        contentValues.put(TABLE_PROPERFILLEDUP,properfilledup);
        contentValues.put(TABLE_CONDITIONSTORECONTAINRSYNC,storecontainrsync);
        contentValues.put(TABLE_SUFICENT,suficent);
        contentValues.put(TABLE_CONDITIONHANDWASHSOAPSYNC,handwashsoapsync);
        contentValues.put(TABLE_HANDWASHINGSOPE,handwashingsope);


        contentValues.put(TABLE_CONDITIONCURTIMESYNC,conditioncurtimesync);
        contentValues.put(TABLE_CONDITIONSTATUS,conditionstatus);
        db.update(TABLE_CONDITIONOFEQIPMENT, contentValues, "conditionofeqipmentid = ?",new String[] { conditionofeqipmentid });
        db.close();
        return true;
    }

    public boolean updateCONDITIONSyncStatus(int conditionofeqipmentid, int conditionstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_CONDITIONSTATUS,conditionstatus);
        db.update(TABLE_CONDITIONOFEQIPMENT, contentValues, TABLE_CONDITIONOFEQIPMENTID + "=" + conditionofeqipmentid, null);
        db.close();
        return true;
    }
    public Cursor getconditionSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CONDITIONOFEQIPMENT + " ORDER BY " + TABLE_CONDITIONOFEQIPMENTID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedconditionDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CONDITIONOFEQIPMENT + " WHERE " + TABLE_CONDITIONSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    /////////////////////SHISHUALOY////////////////////////////////////
    public boolean SHISHUALOYINSERT(String hishualoyinsid,String sishualoy,String cornercgnitv,String bookcorner,String drawcornr,String playcorners,String eccrun,String valuesuting,
                                    String eccactvtytyp,
                                    String awcvaluestring,
                                    String tlam,
                                    String proprecckit,
                                    String avaleindvsualchildactvtyrcd,
                                    String indvsualchildactvtyrcd,
                                    String awcdecortnfrecce,
                                    String fitorecccheck,
                                    String childenrolled,
                                    String childfoundtodayy,
                                    String ecceprocess,
                                    String assesmentcard,
                                    String assesmentcarduse,
                                    String ecckitdate,
                                    String eccobserdate,
                                    String pseactvfound,
                                    String pseactivitynm,
                                    String chldparticipatpse,
                                    String wheterawwriting,
                                    String shishualoyinspectrcmnt,
                                    String shishualoycurtime,
                                    int shishualoystatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SHISHUALOYINSID,hishualoyinsid);
        contentValues.put(TABLE_SISHUALOY, sishualoy);
        contentValues.put(TABLE_CORNERCGNITV,cornercgnitv);
        contentValues.put(TABLE_BOOKCORNER,bookcorner);
        contentValues.put(TABLE_DRAWCORNR,drawcornr);
        contentValues.put(TABLE_PLAYCORNERS,playcorners);
        contentValues.put(TABLE_ECCRUN,eccrun);
        contentValues.put(TABLE_VALUESUTING,valuesuting);
        contentValues.put(TABLE_ECCACTIVITYTYPE,eccactvtytyp);
        contentValues.put(TABLE_AWCVALUESSTRING,awcvaluestring);
        contentValues.put(TABLE_TLAM,tlam);
        contentValues.put(TABLE_PROPRECCKIT,proprecckit);
        contentValues.put(TABLE_AVALEINDVSUALCHILDACTIVEYRCD,avaleindvsualchildactvtyrcd);
        contentValues.put(TABLE_INDVSUALCHILDACTVTYRCD,indvsualchildactvtyrcd);
        contentValues.put(TABLE_AWCDECORTNFRECCE,awcdecortnfrecce);
        contentValues.put(TABLE_FITORECCCHECK,fitorecccheck);
        contentValues.put(TABLE_CHILDENROLLED,childenrolled);
        contentValues.put(TABLE_CHILDFOUNDTODAYY,childfoundtodayy);
        contentValues.put(TABLE_ECCEPROCESS,ecceprocess);
        contentValues.put(TABLE_ASSESMENTCARD,assesmentcard);
        contentValues.put(TABLE_ASSESMENTCARDUSE,assesmentcarduse);
        contentValues.put(TABLE_ECCKITDATE,ecckitdate);
        contentValues.put(TABLE_ECCOBSERDATE,eccobserdate);
        contentValues.put(TABLE_PSEACTFOUND,pseactvfound);
        contentValues.put(TABLE_PSEACTIVITYNM,pseactivitynm);
        contentValues.put(TABLE_CHAILDPARTICIPATPSE,chldparticipatpse);
        contentValues.put(TABLE_AWWFOLLOWINGTHEROUTING,wheterawwriting);
        contentValues.put(TABLE_SHISHUALOYINSPECTRCMNT,shishualoyinspectrcmnt);
        contentValues.put(TABLE_SHISHUALOYCURTIME,shishualoycurtime);
        contentValues.put(TABLE_SHISHUALOYSTATUS,shishualoystatus);
        db.insert(TABLE_SHISHUALOYNAME, null, contentValues);
        db.close();
        return true;
    }


    public boolean SHISHUALOYUPDATE(String shishualoyid,String hishualoyinsid,String sishualoy,String cornercgnitv,String bookcorner,String drawcornr,String playcorners,String eccrun,
                                    String valuesuting,
                                    String eccactvtytyp,
                                    String awcvaluestring,
                                    String tlam,
                                    String proprecckit,
                                    String avaleindvsualchildactvtyrcd,
                                    String indvsualchildactvtyrcd,
                                    String awcdecortnfrecce,
                                    String fitorecccheck,
                                    String childenrolled,
                                    String childfoundtodayy,
                                    String ecceprocess,
                                    String assesmentcard,
                                    String assesmentcarduse,
                                    String ecckitdate,
                                    String eccobserdate,
                                    String pseactvfound,
                                    String pseactivitynm,
                                    String chldparticipatpse,
                                    String wheterawwriting,
                                    String shishualoyinspectrcmnt,
                                    String shishualoycurtime,
                                    int shishualoystatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SHISHUALOYID,shishualoyid);
        contentValues.put(TABLE_SHISHUALOYINSID,hishualoyinsid);
        contentValues.put(TABLE_SISHUALOY, sishualoy);
        contentValues.put(TABLE_CORNERCGNITV,cornercgnitv);
        contentValues.put(TABLE_BOOKCORNER,bookcorner);
        contentValues.put(TABLE_DRAWCORNR,drawcornr);
        contentValues.put(TABLE_PLAYCORNERS,playcorners);
        contentValues.put(TABLE_ECCRUN,eccrun);
        contentValues.put(TABLE_VALUESUTING,valuesuting);
        contentValues.put(TABLE_ECCACTIVITYTYPE,eccactvtytyp);
        contentValues.put(TABLE_AWCVALUESSTRING,awcvaluestring);
        contentValues.put(TABLE_TLAM,tlam);
        contentValues.put(TABLE_PROPRECCKIT,proprecckit);
        contentValues.put(TABLE_AVALEINDVSUALCHILDACTIVEYRCD,avaleindvsualchildactvtyrcd);
        contentValues.put(TABLE_INDVSUALCHILDACTVTYRCD,indvsualchildactvtyrcd);
        contentValues.put(TABLE_AWCDECORTNFRECCE,awcdecortnfrecce);
        contentValues.put(TABLE_FITORECCCHECK,fitorecccheck);
        contentValues.put(TABLE_CHILDENROLLED,childenrolled);
        contentValues.put(TABLE_CHILDFOUNDTODAYY,childfoundtodayy);
        contentValues.put(TABLE_ECCEPROCESS,ecceprocess);
        contentValues.put(TABLE_ASSESMENTCARD,assesmentcard);
        contentValues.put(TABLE_ASSESMENTCARDUSE,assesmentcarduse);
        contentValues.put(TABLE_ECCKITDATE,ecckitdate);
        contentValues.put(TABLE_ECCOBSERDATE,eccobserdate);
        contentValues.put(TABLE_PSEACTFOUND,pseactvfound);
        contentValues.put(TABLE_PSEACTIVITYNM,pseactivitynm);
        contentValues.put(TABLE_CHAILDPARTICIPATPSE,chldparticipatpse);
        contentValues.put(TABLE_AWWFOLLOWINGTHEROUTING,wheterawwriting);
        contentValues.put(TABLE_SHISHUALOYINSPECTRCMNT,shishualoyinspectrcmnt);
        contentValues.put(TABLE_SHISHUALOYCURTIME,shishualoycurtime);
        contentValues.put(TABLE_SHISHUALOYSTATUS,shishualoystatus);
        db.update(TABLE_SHISHUALOYNAME, contentValues, "shishualoyid = ?",new String[] { shishualoyid });
        db.close();
        return true;
    }
    public boolean updateSHISHUALOYSyncStatus(int shishualoyid, int shishualoystatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SHISHUALOYSTATUS,shishualoystatus);
        db.update(TABLE_SHISHUALOYNAME, contentValues, TABLE_SHISHUALOYID + "=" + shishualoyid, null);
        db.close();
        return true;
    }
    public Cursor getSHISHUALOYSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SHISHUALOYNAME + " ORDER BY " + TABLE_SHISHUALOYID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedSHISHUALOYDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SHISHUALOYNAME + " WHERE " + TABLE_SHISHUALOYSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    ///////////////////////////////////SPN///////////////////////////////////////////////////////////////////////////
    public boolean SPNINSERT(String spninsid,String mrngsnacks,String mrngsnacktyp,String snpserve,
                             String hcmaspermnu,
                             String snpmenu,
                             String chldprsnt,
                             String chldprsnttoday,

                             String pmlmprsnt,
                             String pmlmprsnttoday,
                             String anyfreeinterlastthree,
                             String feedintrpt1m,
                             String anyfeedingintruptnlstthremnth,
                             String feedintrpt2m,
                             String anyfeedingintruptnlstthremnth2,
                             String feedintrpt3m,
                             String anyfeedingintruptnlstthremnth3,
                             String snpntsrvreasn,

                             String pninspctncmnt,
                             String spncurtime,
                             int spnstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SPNINSID,spninsid);
        contentValues.put(TABLE_MRNGSNACKS, mrngsnacks);
        contentValues.put(TABLE_MRNGSNACKTYP,mrngsnacktyp);
        contentValues.put(TABLE_SNPSERVE,snpserve);
        contentValues.put(TABLE_HCMASPERMNU,hcmaspermnu);
        contentValues.put(TABLE_SNPMENU,snpmenu);
        contentValues.put(TABLE_CHLDPRSNT,chldprsnt);
        contentValues.put(TABLE_CHIDPRSNTTODAY,chldprsnttoday);
        contentValues.put(TABLE_PMLMPRSNT,pmlmprsnt);
        contentValues.put(TABLE_PMLMPRSNTTODAY,pmlmprsnttoday);

        contentValues.put(TABLE_AVRGFEEDNGLSTTHREMNTH,anyfreeinterlastthree);
        contentValues.put(TABLE_FEEDINTRPTLM,feedintrpt1m);
        contentValues.put(TABLE_ANYFEEDINGINTRUPTNLSTTHREMNTH,anyfeedingintruptnlstthremnth);
        contentValues.put(TABLE_FEEDINTRPT2M,feedintrpt2m);
        contentValues.put(TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH2,anyfeedingintruptnlstthremnth2);
        contentValues.put(TABLE_FEEDINTRPT3M,feedintrpt3m);
        contentValues.put(TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH3,anyfeedingintruptnlstthremnth3);
        contentValues.put(TABLE_SNPNTSRVREASN,snpntsrvreasn);

        contentValues.put(TABLE_SPNINSPCTNCMNT,pninspctncmnt);
        contentValues.put(TABLE_SPNCURTIME,spncurtime);
        contentValues.put(TABLE_SPNSTATUS,spnstatus);
        db.insert(TABLE_SPNTABLE, null, contentValues);
        db.close();
        return true;
    }

    public boolean SPNUPDATE(String spntableid,String spninsid,String mrngsnacks,String mrngsnacktyp,String snpserve,
                             String hcmaspermnu,
                             String snpmenu,String chldprsnt,
                             String chldprsnttoday,
                             String pmlmprsnt,
                             String pmlmprsnttoday,
                             String anyfreeinterlastthree,
                             String feedintrpt1m,
                             String anyfeedingintruptnlstthremnth,
                             String feedintrpt2m,
                             String anyfeedingintruptnlstthremnth2,
                             String feedintrpt3m,
                             String anyfeedingintruptnlstthremnth3,
                             String snpntsrvreasn,

                             String pninspctncmnt,
                             String spncurtime,int spnstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SPNTABLEID,spntableid);
        contentValues.put(TABLE_SPNINSID,spninsid);
        contentValues.put(TABLE_MRNGSNACKS, mrngsnacks);
        contentValues.put(TABLE_MRNGSNACKTYP,mrngsnacktyp);
        contentValues.put(TABLE_SNPSERVE,snpserve);
        contentValues.put(TABLE_HCMASPERMNU,hcmaspermnu);
        contentValues.put(TABLE_SNPMENU,snpmenu);
        contentValues.put(TABLE_CHLDPRSNT,chldprsnt);
        contentValues.put(TABLE_CHIDPRSNTTODAY,chldprsnttoday);

        contentValues.put(TABLE_PMLMPRSNT,pmlmprsnt);
        contentValues.put(TABLE_PMLMPRSNTTODAY,pmlmprsnttoday);
        contentValues.put(TABLE_AVRGFEEDNGLSTTHREMNTH,anyfreeinterlastthree);
        contentValues.put(TABLE_FEEDINTRPTLM,feedintrpt1m);
        contentValues.put(TABLE_ANYFEEDINGINTRUPTNLSTTHREMNTH,anyfeedingintruptnlstthremnth);
        contentValues.put(TABLE_FEEDINTRPT2M,feedintrpt2m);
        contentValues.put(TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH2,anyfeedingintruptnlstthremnth2);
        contentValues.put(TABLE_FEEDINTRPT3M,feedintrpt3m);
        contentValues.put(TABLE_ANYFEEDINGINTRUPTNLSTTHREEMONTH3,anyfeedingintruptnlstthremnth3);
        contentValues.put(TABLE_SNPNTSRVREASN,snpntsrvreasn);


        contentValues.put(TABLE_SPNINSPCTNCMNT,pninspctncmnt);
        contentValues.put(TABLE_SPNCURTIME,spncurtime);
        contentValues.put(TABLE_SPNSTATUS,spnstatus);
        db.update(TABLE_SPNTABLE, contentValues, "spntableid = ?",new String[] { spntableid });
        db.close();
        return true;
    }
    public boolean updateSPNSyncStatus(int spntableid, int spnstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SPNSTATUS,spnstatus);
        db.update(TABLE_SPNTABLE, contentValues, TABLE_SPNTABLEID + "=" + spntableid, null);
        db.close();
        return true;
    }

    public Cursor getSPNSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SPNTABLE + " ORDER BY " + TABLE_SPNTABLEID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedSPNDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SPNTABLE + " WHERE " + TABLE_SPNSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    /////////////////////////////////////NUTRITIONALTABLE//////////////////////////////////
    public boolean NUTRITIONALINSERT(String nutritionaltins,String nutritionaltintime,String malnurishdchld,String sanchld,String commant,int nutritionalstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NUTRITIONALTINS,nutritionaltins);
        contentValues.put(TABLE_NUTRITIONALTINTIME, nutritionaltintime);
        contentValues.put(TABLE_MALNURISHDCHLD,malnurishdchld);
        contentValues.put(TABLE_SANCHLD,sanchld);
        contentValues.put(TABLE_COMMANT,commant);
        contentValues.put(TABLE_NUTRITIONALSTATUS,nutritionalstatus);
        db.insert(TABLE_NUTRITIONALTABLE, null, contentValues);
        db.close();
        return true;
    }

    public boolean NUTRITIONALUPDATE(String nutritionaltableid,String nutritionaltins,String nutritionaltintime,String malnurishdchld,String sanchld,String commant,int nutritionalstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NUTRITIONALTID,nutritionaltableid);
        contentValues.put(TABLE_NUTRITIONALTINS,nutritionaltins);
        contentValues.put(TABLE_NUTRITIONALTINTIME, nutritionaltintime);
        contentValues.put(TABLE_MALNURISHDCHLD,malnurishdchld);
        contentValues.put(TABLE_SANCHLD,sanchld);
        contentValues.put(TABLE_COMMANT,commant);
        contentValues.put(TABLE_NUTRITIONALSTATUS,nutritionalstatus);
        db.update(TABLE_NUTRITIONALTABLE, contentValues, "nutritionaltableid = ?",new String[] { nutritionaltableid });
        db.close();
        return true;
    }
    public boolean updateNUTRITIONALSyncStatus(int nutritionaltableid, int nutritionalstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NUTRITIONALSTATUS,nutritionalstatus);
        db.update(TABLE_NUTRITIONALTABLE, contentValues, TABLE_NUTRITIONALTID + "=" + nutritionaltableid, null);
        db.close();
        return true;
    }
    public Cursor getNUTRITIONALSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NUTRITIONALTABLE + " ORDER BY " + TABLE_NUTRITIONALTID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedNUTRITIONALDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NUTRITIONALTABLE + " WHERE " + TABLE_NUTRITIONALSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    ////////////////////////////OTHERINSPECTION///////////////////////////////
    public boolean OTHERINSPECTIONINSERT(String otherinspectioninsid,
                                         String otherinspectioninscurtime,String cmuntyprticptnnotice,String supvisit,
                                         String lastsupustidate,
                                         String medcinkitlstrcv,String lastrecived,
                                         String usedofawc,String gengoth,String awcremark,int otherinspectionstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_OTHERINSPECTIONINSID,otherinspectioninsid);
        contentValues.put(TABLE_OTHERINSPECTIONINSCURTIME, otherinspectioninscurtime);
        contentValues.put(TABLE_CMUNTYPRTICPTNNOTICE,cmuntyprticptnnotice);
        contentValues.put(TABLE_SUPVISIT,supvisit);
        contentValues.put(TABLE_LASTSUPUSTIDATE,lastsupustidate);
        contentValues.put(TABLE_MEDCINKITLSTRCV,medcinkitlstrcv);
        contentValues.put(TABLE_LASTRECIVED,lastrecived);
        contentValues.put(TABLE_USEDOFAWC,usedofawc);
        contentValues.put(TABLE_GENGOTH,gengoth);
        contentValues.put(TABLE_AWCREMARK,awcremark);
        contentValues.put(TABLE_OTHERINSPECTIONSTATUS,otherinspectionstatus);
        db.insert(TABLE_OTHERINSPECTIONTABLENAME, null, contentValues);
        db.close();
        return true;
    }

    public boolean OTHERINSPECTIONUPDATE(String otherinspectionid,String otherinspectioninsid,String otherinspectioninscurtime,
                                         String cmuntyprticptnnotice,String supvisit,
                                         String lastsupustidate,String medcinkitlstrcv,String lastrecived,String usedofawc,String gengoth,String awcremark,int otherinspectionstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_OTHERINSPECTIONTABLEID,otherinspectionid);
        contentValues.put(TABLE_OTHERINSPECTIONINSID,otherinspectioninsid);
        contentValues.put(TABLE_OTHERINSPECTIONINSCURTIME, otherinspectioninscurtime);
        contentValues.put(TABLE_CMUNTYPRTICPTNNOTICE,cmuntyprticptnnotice);
        contentValues.put(TABLE_SUPVISIT,supvisit);
        contentValues.put(TABLE_LASTSUPUSTIDATE,lastsupustidate);
        contentValues.put(TABLE_MEDCINKITLSTRCV,medcinkitlstrcv);
        contentValues.put(TABLE_LASTRECIVED,lastrecived);
        contentValues.put(TABLE_USEDOFAWC,usedofawc);
        contentValues.put(TABLE_GENGOTH,gengoth);
        contentValues.put(TABLE_AWCREMARK,awcremark);
        contentValues.put(TABLE_OTHERINSPECTIONSTATUS,otherinspectionstatus);
        db.update(TABLE_OTHERINSPECTIONTABLENAME, contentValues, "otherinspectionid = ?",new String[] { otherinspectionid });
        db.close();
        return true;
    }
    public boolean updateOTHERINSPECTIONSyncStatus(int otherinspectionid, int otherinspectionstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_OTHERINSPECTIONSTATUS,otherinspectionstatus);
        db.update(TABLE_OTHERINSPECTIONTABLENAME, contentValues, TABLE_OTHERINSPECTIONTABLEID + "=" + otherinspectionid, null);
        db.close();
        return true;
    }
    public Cursor getOTHERINSPECTIONSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_OTHERINSPECTIONTABLENAME + " ORDER BY " + TABLE_OTHERINSPECTIONTABLEID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedOTHERINSPECTIONDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_OTHERINSPECTIONTABLENAME + " WHERE " + TABLE_OTHERINSPECTIONSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    //////////////////////////INSPACTION/////////////////////////////////////////////////
    public boolean INSPACTIONINSERT(String updatecentrepersndtldbdistid,String updatecentrepersndtldbprojectid,String updatecentrepersndtlcdponame,String updatecentrepersndtlcdponumber,String updatecentrepersndtlacdponame,String updatecentrepersndtlacdpocontract,String updatecentrepersndtldbsectorid,String updatecentrepersndtlsupervisorname,String updatecentrepersndtlsupervisorno,String updatecentrepersndtlawcsid,String updatecentrepersndtlworkername,String updatecentrepersndtlworkerno,String updatecentrepersndtlhelpername,String updatecentrepersndtlhelperno,int updatecentrepersndtlstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_UPDATECENTREPERSNDTLDBDISTID,updatecentrepersndtldbdistid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLDBPROJECTID, updatecentrepersndtldbprojectid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLCDPONAME,updatecentrepersndtlcdponame);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLCDPONUMBER,updatecentrepersndtlcdponumber);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLACDPONAME,updatecentrepersndtlacdponame);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLACDPOCONTRACT,updatecentrepersndtlacdpocontract);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLDBSECTORID,updatecentrepersndtldbsectorid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLSUPERVISORNAME,updatecentrepersndtlsupervisorname);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLSUPERVISORNO,updatecentrepersndtlsupervisorno);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLAWCSID,updatecentrepersndtlawcsid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLWORKERNAME,updatecentrepersndtlworkername);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLWORKERNO,updatecentrepersndtlworkerno);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLHELPERNAME,updatecentrepersndtlhelpername);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLHELPERNO,updatecentrepersndtlhelperno);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLSTATUS,updatecentrepersndtlstatus);
        db.insert(TABLE_UPDATECENTREPERSNDTL, null, contentValues);
        db.close();
        return true;
    }

    public boolean INSPACTIONUPDATE(String updatecentrepersndtlid,String updatecentrepersndtldbdistid,String updatecentrepersndtldbprojectid,String updatecentrepersndtlcdponame,String updatecentrepersndtlcdponumber,String updatecentrepersndtlacdponame,String updatecentrepersndtlacdpocontract,String updatecentrepersndtldbsectorid,String updatecentrepersndtlsupervisorname,String updatecentrepersndtlsupervisorno,String updatecentrepersndtlawcsid,String updatecentrepersndtlworkername,String updatecentrepersndtlworkerno,String updatecentrepersndtlhelpername,String updatecentrepersndtlhelperno,int updatecentrepersndtlstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_UPDATECENTREPERSNDTLID,updatecentrepersndtlid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLDBDISTID,updatecentrepersndtldbdistid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLDBPROJECTID, updatecentrepersndtldbprojectid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLCDPONAME,updatecentrepersndtlcdponame);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLCDPONUMBER,updatecentrepersndtlcdponumber);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLACDPONAME,updatecentrepersndtlacdponame);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLACDPOCONTRACT,updatecentrepersndtlacdpocontract);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLDBSECTORID,updatecentrepersndtldbsectorid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLSUPERVISORNAME,updatecentrepersndtlsupervisorname);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLSUPERVISORNO,updatecentrepersndtlsupervisorno);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLAWCSID,updatecentrepersndtlawcsid);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLWORKERNAME,updatecentrepersndtlworkername);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLWORKERNO,updatecentrepersndtlworkerno);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLHELPERNAME,updatecentrepersndtlhelpername);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLHELPERNO,updatecentrepersndtlhelperno);
        contentValues.put(TABLE_UPDATECENTREPERSNDTLSTATUS,updatecentrepersndtlstatus);
        db.update(TABLE_UPDATECENTREPERSNDTL, contentValues, "updatecentrepersndtlid = ?",new String[] { updatecentrepersndtlid });
        db.close();
        return true;
    }
    public boolean updateINSPACTIONSyncStatus(int updatecentrepersndtlid, int updatecentrepersndtlstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_UPDATECENTREPERSNDTLSTATUS,updatecentrepersndtlstatus);
        db.update(TABLE_UPDATECENTREPERSNDTL, contentValues, TABLE_UPDATECENTREPERSNDTLID + "=" + updatecentrepersndtlid, null);
        db.close();
        return true;
    }
    public Cursor getNSPACTIONSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_UPDATECENTREPERSNDTL + " ORDER BY " + TABLE_UPDATECENTREPERSNDTLID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getUnsyncedINSPACTIONDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_UPDATECENTREPERSNDTL + " WHERE " + TABLE_UPDATECENTREPERSNDTLSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    /////////////////////////////INSPECTIONPERSONPRESENT///////////////////////////////////////////
    public boolean INSPECTIONPERSONPRESENTINSERT(String inspectionpersonpresentdbdistid,String inspectionpersonpresentdbprojectid,
                                                 String inspectionpersonpresentdbsectorid,String inspectionpersonpresentdbcenterid,
                                                 String inspectionpersonpresentyncdpo,String inspectionpersonpresentynacdpio,
                                                 String inspectionpersonpresentynsupervisor,String inspectionpersonpresentynworker,
                                                 String inspectionpersonpresentyhelper,String inspectionpersonpresentcurdate,
                                                 String inspectionpersonpresentcurtime,String inspectionpersonpresentynawcs,
                                                 String inspectionpersonpresentuserid,String inspectionpersonpresentinsid,
                                                 String inspectionpersonpresentplanid,String inspectionpersonpresentplancmt,int inspectionpersonpresentstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTDBDISTID,inspectionpersonpresentdbdistid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTDBPROJECTID, inspectionpersonpresentdbprojectid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTDBSECTORID,inspectionpersonpresentdbsectorid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTDBCENTERID,inspectionpersonpresentdbcenterid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNCDPO,inspectionpersonpresentyncdpo);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNACDPIO,inspectionpersonpresentynacdpio);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNSUPERVISOR,inspectionpersonpresentynsupervisor);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNWORKER,inspectionpersonpresentynworker);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYHELPER,inspectionpersonpresentyhelper);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTCURDATE,inspectionpersonpresentcurdate);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTCURTIME,inspectionpersonpresentcurtime);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNAWCS,inspectionpersonpresentynawcs);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTUSERID,inspectionpersonpresentuserid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTINSID,inspectionpersonpresentinsid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTPLANID,inspectionpersonpresentplanid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTPLANIDCMT,inspectionpersonpresentplancmt);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTSTATUS,inspectionpersonpresentstatus);
        db.insert(TABLE_INSPECTIONPERSONPRESENT, null, contentValues);
        db.close();
        return true;
    }

    public boolean INSPECTIONPERSONPRESENTUPDATE(String inspectionpersonpresentid,
                                                 String inspectionpersonpresentdbdistid,
                                                 String inspectionpersonpresentdbprojectid,
                                                 String inspectionpersonpresentdbsectorid,
                                                 String inspectionpersonpresentdbcenterid,
                                                 String inspectionpersonpresentyncdpo,
                                                 String inspectionpersonpresentynacdpio,
                                                 String inspectionpersonpresentynsupervisor,
                                                 String inspectionpersonpresentynworker,
                                                 String inspectionpersonpresentyhelper,
                                                 String inspectionpersonpresentcurdate,
                                                 String inspectionpersonpresentcurtime,
                                                 String inspectionpersonpresentynawcs,
                                                 String inspectionpersonpresentuserid,
                                                 String inspectionpersonpresentinsid,
                                                 String inspectionpersonpresentplanid,String inspectionpersonpresentplancmt,int inspectionpersonpresentstatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTID,inspectionpersonpresentid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTDBDISTID,inspectionpersonpresentdbdistid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTDBPROJECTID, inspectionpersonpresentdbprojectid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTDBSECTORID,inspectionpersonpresentdbsectorid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTDBCENTERID,inspectionpersonpresentdbcenterid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNCDPO,inspectionpersonpresentyncdpo);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNACDPIO,inspectionpersonpresentynacdpio);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNSUPERVISOR,inspectionpersonpresentynsupervisor);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNWORKER,inspectionpersonpresentynworker);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYHELPER,inspectionpersonpresentyhelper);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTCURDATE,inspectionpersonpresentcurdate);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTCURTIME,inspectionpersonpresentcurtime);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTYNAWCS,inspectionpersonpresentynawcs);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTUSERID,inspectionpersonpresentuserid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTINSID,inspectionpersonpresentinsid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTPLANID,inspectionpersonpresentplanid);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTPLANIDCMT,inspectionpersonpresentplancmt);
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTSTATUS,inspectionpersonpresentstatus);
        db.update(TABLE_INSPECTIONPERSONPRESENT, contentValues, "inspectionpersonpresentid = ?",new String[] { inspectionpersonpresentid });
        db.close();
        return true;
    }
    public boolean updateINSPECTIONPERSONPRESENSyncStatus(int inspectionpersonpresentid, int inspectionpersonpresentstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_INSPECTIONPERSONPRESENTSTATUS,inspectionpersonpresentstatus);
        db.update(TABLE_INSPECTIONPERSONPRESENT, contentValues, TABLE_INSPECTIONPERSONPRESENTID + "=" + inspectionpersonpresentid, null);
        db.close();
        return true;
    }

    public Cursor getINSPECTIONPERSONPRESENSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_INSPECTIONPERSONPRESENT + " ORDER BY " + TABLE_INSPECTIONPERSONPRESENTID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getUnsyncedINSPECTIONPERSONPRESENDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_INSPECTIONPERSONPRESENT + " WHERE " + TABLE_INSPECTIONPERSONPRESENTSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public boolean FINALSUBMITINSERT(String finalsubins, int finalsubmitstatus){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(TABLE_FINALSUBINS,finalsubins);
    contentValues.put(TABLE_FINALSUBMITSTATUS,finalsubmitstatus);
    db.insert(TABLE_FINALSUBTABLE, null, contentValues);
    db.close();
    return true;
}
    public boolean FINALSUBMITUPDATE(String finalsunid,String finalsubins,int finalsubmitstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_FINALSUBID,finalsunid);
        contentValues.put(TABLE_FINALSUBINS,finalsubins);
        contentValues.put(TABLE_FINALSUBMITSTATUS,finalsubmitstatus);
        db.update(TABLE_FINALSUBTABLE, contentValues, "finalsunid = ?",new String[] { finalsunid });
        db.close();
        return true;
    }

    public boolean updatefinalsubmitSyncStatus(int finalsunid, int finalsubmitstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_FINALSUBMITSTATUS,finalsubmitstatus);
        db.update(TABLE_FINALSUBTABLE, contentValues, TABLE_FINALSUBID + "=" + finalsunid, null);
        db.close();
        return true;
    }

    public Cursor getfinalSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_FINALSUBTABLE + " ORDER BY " + TABLE_FINALSUBID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getUnsyncedfinalDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_FINALSUBTABLE + " WHERE " + TABLE_FINALSUBMITSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    ////////////////////////////////////////////sport tale start///////////////////////////////////////////////
    public boolean SPORTINSER(String sportins,String awccode,String sdistric,String planidsp,
                              String systenDate,String curTime,String userIDint,
                              int sportstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SPORTSINS,sportins);
        contentValues.put(TABLE_AWCCODE,awccode);
        contentValues.put(TBALE_SDISTRIC,sdistric);
        contentValues.put(TABLE_PLANIDSP,planidsp);
        contentValues.put(TABLE_PLANDATE,systenDate);
        contentValues.put(TABLE_PLANTIME,curTime);
        contentValues.put(TABLE_USERIDPLAN,userIDint);
        contentValues.put(TABLE_SPORTSTATUS,sportstatus);
        db.insert(TABLE_SPORTSYNC, null, contentValues);
        db.close();
        return true;
    }
    public boolean SPORTUPDATE(String sportsyncid,String
            sportins,String awccode,String sdistric,String planidsp, int sportstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SPORTSYNCID,sportsyncid);
        contentValues.put(TABLE_SPORTSINS,sportins);
        contentValues.put(TABLE_AWCCODE,awccode);
        contentValues.put(TBALE_SDISTRIC,sdistric);
        contentValues.put(TABLE_PLANIDSP,planidsp);
//        contentValues.put(TABLE_SSECTOR,ssector);
//        contentValues.put(TABLE_SCENTER,scenter);
        contentValues.put(TABLE_SPORTSTATUS,sportstatus);
        db.update(TABLE_SPORTSYNC, contentValues, "sportsyncid = ?",new String[] { sportsyncid });
        db.close();
        return true;
    }
    public boolean sportsubmitSyncStatus(int sportsyncid, int sportstatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_SPORTSTATUS,sportstatus);
        db.update(TABLE_SPORTSYNC, contentValues, TABLE_SPORTSYNCID + "=" + sportsyncid, null);
        db.close();
        return true;
    }

    public Cursor getsportSync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SPORTSYNC + " ORDER BY " + TABLE_SPORTSYNCID + " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
    public Cursor getsportsync() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SPORTSYNC + " WHERE " + TABLE_SPORTSTATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
