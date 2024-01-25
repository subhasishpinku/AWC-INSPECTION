package icdswb.in.Activity_Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import icdswb.in.ActivitySetGet.SetgetmyInspactionviewListt;
import icdswb.in.CircularImageView;
import icdswb.in.R;
import icdswb.in.constant.Application;

public class AdaptermyInspactionviewList extends RecyclerView.Adapter<AdaptermyInspactionviewList.Viewholder> {
    private Context mCtx;
    private List<SetgetmyInspactionviewListt> setgetmyInspactionviewListss;
    private static int currentPosition = 0;
    private static int currentPositionn = 1;
    String rentbuild,fundname,otherbuild;
    String month1,month2,month3;
    String valuesuting,awcvaluestring,awwroutinfollow;
    String awwroutinfollow1 = "";
    String awwroutinfollow2 ="";
    String awwroutinfollow3 = "";
    String awwroutinfollow4 = "";
    String awwroutinfollow5 = "";
    String awwroutinfollow6 = "";
    String awwroutinfollow7 = "";
    String awwroutinfollow8 = "";
    String awwroutinfollow9 = "";
    public AdaptermyInspactionviewList(Context mCtx, List<SetgetmyInspactionviewListt> setgetmyInspactionviewListss){
        this.mCtx = mCtx;
        this.setgetmyInspactionviewListss = setgetmyInspactionviewListss;
    }

    @NonNull
    @Override
    public AdaptermyInspactionviewList.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myinspactionviewlistadpter, parent, false);
        return new Viewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final AdaptermyInspactionviewList.Viewholder holder, final int position) {
        final int pos = position;
        Log.e("pos", " " + pos);
        SetgetmyInspactionviewListt inspactionviewList = setgetmyInspactionviewListss.get(position);
        holder.centername.setText(inspactionviewList.getCname());
        holder.districnameID.setText(inspactionviewList.getDistname());
        holder.ProjectNameID.setText(inspactionviewList.getProjnm());
        holder.SectorNameID.setText(inspactionviewList.getSectrnm());
        holder.CenterNameID.setText(inspactionviewList.getCentrename());
        holder.textViewofficer.setText(inspactionviewList.getOname());
        holder.CdpoName.setText(inspactionviewList.getCdponame());
        holder.CdpoName.setText(inspactionviewList.getCdponame());
        holder.Cdepophone.setText(inspactionviewList.getCdpophn());
        holder.AcdpoName.setText(inspactionviewList.getAcdponame());
        holder.AcdpoPhone.setText(inspactionviewList.getAcdpophn());
        holder.SupervisorName.setText(inspactionviewList.getSupname());
        holder.Supervisorphone.setText(inspactionviewList.getSupphn());
        holder.workername.setText(inspactionviewList.getWorkername());
        holder.workerphone.setText(inspactionviewList.getWorkerphn());
        holder.helpername.setText(inspactionviewList.getHlpername());
        holder.helperphone.setText(inspactionviewList.getHlperphn());
        holder.textViewinspactor.setText(inspactionviewList.getIname());
        holder.inspactorName.setText(inspactionviewList.getUsername());
        holder.InspactorNumber.setText(inspactionviewList.getUserphn());
        holder.MailId.setText(inspactionviewList.getUsermail());
        holder.address.setText(inspactionviewList.getUseradrs());
        // Glide.with(mCtx).load(inspactionviewList.getUserimg()).into(holder.inspactionimage);
        // int img = Integer.parseInt(inspactionviewList.getUserimg());
        // holder.inspactionimage.setImageDrawable(mCtx.getResources().getDrawable(img));
        ImageLoader imageLoader = Application.getInstance().getImageLoader();
        holder.inspactionimage.setImageUrl(inspactionviewList.getUserimg(), imageLoader);
        holder.textViewbuilding.setText(inspactionviewList.getBname());
        String Lstbuildisac = inspactionviewList.getLstbuildisac();
        Log.e("UImage", inspactionviewList.getUserimg());
        holder.lastisacID.setText(inspactionviewList.getLstbuildisac());
//        holder.lastisacID.append(System.getProperty("line.separator"));
//        holder.lastisacID.append("Line number 2");
        holder.lastinspactionID.setText(inspactionviewList.getLstbuildinspctn());
        holder.buildingID.setText(inspactionviewList.getBuildtyp());
        holder.buildingruninID.setText(inspactionviewList.getBuldrun());
        holder.rententedbuildinginID.setText(inspactionviewList.getRentbuild());
        rentbuild = inspactionviewList.getRentbuild();
        ///off fund name
        //  fundname = inspactionviewList.getFundname();
        //  holder.fundnameID.setText(inspactionviewList.getFundname());
        ///off fund name
        otherbuild = inspactionviewList.getOthrgovtbuld();
        holder.othergovID.setText(inspactionviewList.getOthrgovtbuld());
        holder.buildingconditionID.setText(inspactionviewList.getBuldcondtn());
        holder.CommentID.setText(inspactionviewList.getBuldinspctncmnt());
        holder.textViewtoilet.setText(inspactionviewList.getTname());
        holder.lastisactoiletID.setText(inspactionviewList.getToiltlstisac());
        holder.lastinspactiontoiletId.setText(inspactionviewList.getToiltlstinspctn());
        holder.toiletID.setText(inspactionviewList.getToilt());
        if (inspactionviewList.getToilt().equals("Yes")){
            if (inspactionviewList.getToiltuse().equals("u")) {
                holder.toiletuseID.setText("Usable");
            } else if (inspactionviewList.getToiltuse().equals("nu")) {
                holder.toiletuseID.setText("Not Usable");
            }
        }
        else {
            holder.lvtoilate.setVisibility(View.GONE);
            holder.lvtoilateview.setVisibility(View.GONE);

        }
        holder.ComID.setText(inspactionviewList.getToiletcmnt());
        holder.textViewkitchen.setText(inspactionviewList.getKname());
        holder.lastisackitchenID.setText(inspactionviewList.getKitchnlstisac());
        holder.lastinspactionkitchenID.setText(inspactionviewList.getKitchnlstinspctn());
        holder.havesetkitchenID.setText(inspactionviewList.getSprtkitchn());
        holder.cookindoneID.setText(inspactionviewList.getCookdone());
        holder.ComCookID.setText(inspactionviewList.getKitchncmnt());
        holder.textViewelectriID.setText(inspactionviewList.getEname());
        holder.lastisaceletricty.setText(inspactionviewList.getLstelectricisacrep());
        holder.lastinspactioneletricty.setText(inspactionviewList.getLstelectricinspctn());
        holder.electrictyaveable.setText(inspactionviewList.getElectricavail());

        holder.modeID.setText(inspactionviewList.getElctrimode());
        holder.lightID.setText(inspactionviewList.getLight());
        holder.fanID.setText(inspactionviewList.getFan());
        holder.pumpID.setText(inspactionviewList.getPump());
        holder.ComeleID.setText(inspactionviewList.getElectrccmnt());
        holder.textViewdrinking.setText(inspactionviewList.getDname());
        holder.lastisacdrinking.setText(inspactionviewList.getDrnklstisac());
        holder.lastinspactiondrinking.setText(inspactionviewList.getDrnklstinspctn());
        holder.owndrinkingwaterId.setText(inspactionviewList.getOwndrnkwater());
        holder.owndrinkingwatercommandId.setText(inspactionviewList.getDrinkingwater());
        holder.textViewfood.setText(inspactionviewList.getFname());
        holder.lastisacfood.setText(inspactionviewList.getFoodlstisac());
        holder.lastinspactionfood.setText(inspactionviewList.getFoodlstinspctn());
        holder.adquispace.setText(inspactionviewList.getStorspce());
        holder.phycalcnk.setText(inspactionviewList.getPhysiclchk());
        holder.comperstcok.setText(inspactionviewList.getStkbookcmpare());
        holder.stocksufficent.setText(inspactionviewList.getStksuficnt());
        holder.riceanddal.setText(inspactionviewList.getRicedal());
        holder.daleonlyId.setText(inspactionviewList.getDal());
        holder.soyachukbrind.setText(inspactionviewList.getSoyachunkbrnd());
        holder.soyachukbrind.setText(inspactionviewList.getSoyachunkbrnd());
        holder.saltId.setText(inspactionviewList.getSaltbrnd());
        holder.stocklying.setText(inspactionviewList.getStocklyng());
        if (inspactionviewList.getStocklyng().equals("Within AWC")){

        }
        else {
            holder.tableadquestoreId.setVisibility(View.GONE);
            holder.tablephycallcheckId.setVisibility(View.GONE);
            holder.tablecomparedwithId.setVisibility(View.GONE);
            holder.tablecomparedwithId.setVisibility(View.GONE);
            holder.tablestocksuffientId.setVisibility(View.GONE);
            holder.tablericeId.setVisibility(View.GONE);;
            holder.tabledalId.setVisibility(View.GONE);
            holder.tablemusteroilId.setVisibility(View.GONE);
            holder.tablesaltId.setVisibility(View.GONE);
            holder.viewtableadquestoreId.setVisibility(View.GONE);
            holder.viewtablephycallcheckId.setVisibility(View.GONE);
            holder.viewtablecomparedwithId.setVisibility(View.GONE);
            holder.viewstocksuffientId.setVisibility(View.GONE);
            holder.viewriceid.setVisibility(View.GONE);
            holder.viewdalId.setVisibility(View.GONE);
            holder.viewmusterId.setVisibility(View.GONE);
        }
        holder.textViewawceqipement.setText(inspactionviewList.getAwcname());
        holder.utilishcondition.setText(inspactionviewList.getUtensilcondtn());
        holder.matchati.setText(inspactionviewList.getMatcondtn());
        if (inspactionviewList.getElevnregstrno().equals("0")){
            holder.eleven.setText(inspactionviewList.getElevnregstrno());
        }
        else {
            if (inspactionviewList.getElvnregstr().equals("mutd")){
                holder.eleven.setText(inspactionviewList.getElevnregstrno()+" "+"/"+"Maintained Up to date");
            }
            else if (inspactionviewList.getElvnregstr().equals("nm")){
                holder.eleven.setText(inspactionviewList.getElevnregstrno()+" "+"/"+"Not Maintained");
            }
            else {

            }

        }
        if (inspactionviewList.getBabywmachin().equals("f")){
            if (inspactionviewList.getBabywmachinuse().equals("u")){
                holder.babywmechin.setText("Functioning / Used");
            }
           else if (inspactionviewList.getBabywmachinuse().equals("nu")){
                holder.babywmechin.setText("Functioning / Not Used");
            }
        }
        else if (inspactionviewList.getBabywmachin().equals("nf")){
            holder.babywmechin.setText("Not Functioning");

        }
       else if (inspactionviewList.getBabywmachin().equals("na")){
            holder.babywmechin.setText("Not Available");

        }

        if (inspactionviewList.getMuactap().equals("f")){
            if (inspactionviewList.getAdultwmachinuse().equals("u")){
                holder.muactap.setText("Functioning / Used");
            }
            else if (inspactionviewList.getAdultwmachinuse().equals("nu")){
                holder.muactap.setText("Functioning / Not Used");
            }
        }
        else if (inspactionviewList.getMuactap().equals("nf")){
            holder.muactap.setText("Not Functioning");

        }
        else if (inspactionviewList.getMuactap().equals("na")){
            holder.muactap.setText("Not Available");

        }

        if (inspactionviewList.getHeightmeasurtyp().equals("f")){
            if (inspactionviewList.getHeightmeasurtypuse().equals("u")){
                holder.highttape.setText("Functioning / Used");
            }
            else if (inspactionviewList.getHeightmeasurtypuse().equals("nu")){
                holder.highttape.setText("Functioning / Not Used");
            }
        }
        else if (inspactionviewList.getHeightmeasurtyp().equals("nf")){
            holder.highttape.setText("Not Functioning");

        }
        else if (inspactionviewList.getHeightmeasurtyp().equals("na")){
            holder.highttape.setText("Not Available");

        }

        //holder.grothchart.setText(inspactionviewList.getGrowthchrt());
        if (inspactionviewList.getGrowthchrt().equals("a")){
            if (inspactionviewList.getGrowthchrtfillup().equals("pfu")){
                holder.grothchart.setText("Available / Properly Filled Up");
            }
            else if (inspactionviewList.getGrowthchrtfillup().equals("npfu")){
                holder.grothchart.setText("Available / Not Properly Filled Up");
            }

        }
        else if (inspactionviewList.getGrowthchrt().equals("na")){
            holder.grothchart.setText("Not Available In AWC");

        }
        else {

        }

        if (inspactionviewList.getStorecontainr().equals("a")){
            if (inspactionviewList.getSuffistorecontainr().equals("u")){
                holder.srotingcont.setText("Available / Used");
            }
            else if (inspactionviewList.getSuffistorecontainr().equals("nu")){
                holder.srotingcont.setText("Available / Not Used");
            }

        }
        else if (inspactionviewList.getStorecontainr().equals("na")){
            holder.srotingcont.setText("Not Available");

        }
        else {

        }

        if (inspactionviewList.getHandwshsoap().equals("a")){
            if (inspactionviewList.getHandwashsoapuse().equals("u")){
                holder.handwish.setText("Available / Used");
            }
            else if (inspactionviewList.getHandwashsoapuse().equals("nu")){
                holder.handwish.setText("Available / Not Used");
            }

        }
        else if (inspactionviewList.getHandwshsoap().equals("na")){
            holder.handwish.setText("Not Available");

        }
        else {

        }

        //holder.handwish.setText(inspactionviewList.getHandwshsoap());
        holder.textViewshishualoy.setText(inspactionviewList.getSname());
        if (inspactionviewList.getSishualoy().equals("Yes")){
            holder.shualyyId.setVisibility(View.VISIBLE);
            holder.shualyNId.setVisibility(View.GONE);
            holder.sishualoy.setText(inspactionviewList.getSishualoy());
        }
        if (inspactionviewList.getSishualoy().equals("No")){
            holder.shualyyId.setVisibility(View.GONE);
            holder.shualyNId.setVisibility(View.VISIBLE);
            //////////////////////////Shishualoy no data///////////////
            holder.sishualytextNID.setText(inspactionviewList.getSishualoy());
            if (inspactionviewList.getPseactvfound().equals("y")){
                holder.pseactivityfoundId.setText("Yes"+" "+"/"+inspactionviewList.getPseactivitynm());
            }
            else if (inspactionviewList.getPseactvfound().equals("n")){
                holder.pseactivityfoundId.setText("No"+" "+"/"+inspactionviewList.getPseactivitynm());
            }
            holder.childrenenrollId.setText(inspactionviewList.getTotchldenroll());
            holder.childrenfoundtodayId.setText(inspactionviewList.getTotchldfoundtoday());
            if (inspactionviewList.getChldparticipatpse().equals("y")){
                holder.parcipientpseactivityId.setText("Yes");
            }
            else if (inspactionviewList.getChldparticipatpse().equals("n")){
                holder.parcipientpseactivityId.setText("No");
            }


            if (inspactionviewList.getAssmntcard().equals("y")){
                holder.ifassessmentcardId.setText("Yes"+" "+"/"+inspactionviewList.getAssmntcardst());
            }
           else if (inspactionviewList.getAssmntcard().equals("n")){
                holder.ifassessmentcardId.setText("No");
            }
           if (inspactionviewList.getChldparticipatpse().equals("Y")){
               holder.parcipientpseactivityId.setText("Yes");
           }
           else if (inspactionviewList.getChldparticipatpse().equals("N")){
               holder.parcipientpseactivityId.setText("No");
            }
           holder.nocommandId.setText(inspactionviewList.getShishualoycmnt());
        }

        holder.blockcornerID.setText(inspactionviewList.getBookcornr());
        holder.bookcorner.setText(inspactionviewList.getBookcornr());
        holder.drawingcorner.setText(inspactionviewList.getDrawcornr());
        if (inspactionviewList.getPlaycornr().equals("na")){
            holder.playcorner.setText("Not Available");
        }
        else {
            holder.playcorner.setText(inspactionviewList.getPlaycornr());
        }
        if (inspactionviewList.getEccrun().equals("y")){
            if (inspactionviewList.getEccroutntheme().equals("0")){
                valuesuting = "Select Routine Theme";
            }
            if (inspactionviewList.getEccroutntheme().equals("1")){
                valuesuting = "Me and My Family";
            }
            if (inspactionviewList.getEccroutntheme().equals("2")){
                valuesuting = "Birds and Animals";
            }
            if (inspactionviewList.getEccroutntheme().equals("3")){
                valuesuting = "Trees and Plants";
            }
            if (inspactionviewList.getEccroutntheme().equals("4")){
                valuesuting = "Transport";
            }
            if (inspactionviewList.getEccroutntheme().equals("5")){
                valuesuting = "Natural Enviroment";
            }
            if (inspactionviewList.getEccroutntheme().equals("6")){
                valuesuting = "My Enviroment";
            }
            if (inspactionviewList.getEccroutntheme().equals("7")){
                valuesuting = "Me and my Surroundings";
            }
            if (inspactionviewList.getEccroutntheme().equals("8")){
                valuesuting = "Festival";
            }
            if (inspactionviewList.getEccroutntheme().equals("9")){
                valuesuting = "Others";
            }
            holder.whethereccerunning.setText("Yes"+"/"+valuesuting);
        }
        else if (inspactionviewList.getEccrun().equals("n")){
            holder.whethereccerunning.setText("No");
        }

        if (inspactionviewList.getEccactvtytyp().equals("y")){
            if (inspactionviewList.getEccactivtyfound().equals("0")){
                awcvaluestring = "Select ECCE Activity";
            }
            if (inspactionviewList.getEccactivtyfound().equals("1")){
                awcvaluestring = "Morning circle Time";
            }
            if (inspactionviewList.getEccactivtyfound().equals("2")){
                awcvaluestring = "Guided Activity and Free Play";
            }
            if (inspactionviewList.getEccactivtyfound().equals("3")){
                awcvaluestring = "Outdoor Indoor Activity";
            }
            if (inspactionviewList.getEccactivtyfound().equals("4")){
                awcvaluestring = "Goodbye circle time";
            }
            if (inspactionviewList.getEccactivtyfound().equals("5")){
                awcvaluestring = "School Readiness";
            }
            if (inspactionviewList.getEccactivtyfound().equals("6")){
                awcvaluestring = "Others";
            }
            holder.ecceactivity.setText("Yes"+" "+"/"+awcvaluestring);
        }
       else if (inspactionviewList.getEccactvtytyp().equals("n")){
            holder.ecceactivity.setText("No");
        }
        if (inspactionviewList.getAwwtlm().equals("y")){
            holder.tlamId.setText("Yes");
        }
       else if (inspactionviewList.getAwwtlm().equals("y")){
            holder.tlamId.setText("No");
        }

       if (inspactionviewList.getProprecckit().equals("p")){
           holder.wwusing.setText("Properly");
       }
       else if (inspactionviewList.getProprecckit().equals("np")){
           holder.wwusing.setText("Not Properly");
       }

       if (inspactionviewList.getIndvsualchildartwork().equals("y")){
           holder.artworkId.setText("Yes");
       }
       else if (inspactionviewList.getIndvsualchildartwork().equals("n")){
           holder.artworkId.setText("No");
        }
       if (inspactionviewList.getIndvsualchildactvtyrcd().equals("y")){
           holder.childrenindividal.setText("Yes");
       }
        else if (inspactionviewList.getIndvsualchildactvtyrcd().equals("n")){
           holder.childrenindividal.setText("No");
        }

        if (inspactionviewList.getAwcdecortnfrecce().equals("y")){
            awwroutinfollow = inspactionviewList.getAwwroutinfollow();
            String[] firstArray = awwroutinfollow.split(",");
            List<String> list = new ArrayList<String>();
            for(String s : firstArray) {
                if(s != null && s.length() > 0) {
                    list.add(s);
                    Log.e("FLOW",s);
                    if (s.equals("1")){
                        awwroutinfollow1 = "Chart according to theme";
                        holder.awcdecoration1.setText(awwroutinfollow1);
                        holder.awcdecoration1.setVisibility(View.VISIBLE);
                    }
                     if (s.equals("2")){
                        awwroutinfollow2 ="Labelling of all spaces, corners, items";
                        holder.awcdecoration2.setText(awwroutinfollow2);
                         holder.awcdecoration2.setVisibility(View.VISIBLE);
                    }
                     if (s.equals("3")){
                        awwroutinfollow3 ="Rules in print form";
                        holder.awcdecoration3.setText(awwroutinfollow3);
                         holder.awcdecoration3.setVisibility(View.VISIBLE);}
                     if (s.equals("4")){
                        awwroutinfollow4 ="Routine";
                        holder.awcdecoration4.setText(awwroutinfollow4);
                         holder.awcdecoration4.setVisibility(View.VISIBLE);
                    }
                     if (s.equals("5")){
                        awwroutinfollow5 ="Calendar";
                        holder.awcdecoration5.setText(awwroutinfollow5);
                         holder.awcdecoration5.setVisibility(View.VISIBLE);
                    }
                    if (s.equals("6")){
                        awwroutinfollow6 ="Children's name chart";
                        holder.awcdecoration6.setText(awwroutinfollow6);
                        holder.awcdecoration6.setVisibility(View.VISIBLE);
                    }
                     if (s.equals("7")){
                        awwroutinfollow7 ="Children's folder";
                        holder.awcdecoration7.setText(awwroutinfollow7);
                         holder.awcdecoration7.setVisibility(View.VISIBLE);
                    }
                     if (s.equals("8")){
                        awwroutinfollow8 ="Display of Children's work";
                        holder.awcdecoration8.setText(awwroutinfollow8);
                         holder.awcdecoration8.setVisibility(View.VISIBLE);
                    }
                     if (s.equals("9")){
                        awwroutinfollow9 ="theme based Word wall";
                        holder.awcdecoration9.setText(awwroutinfollow9);
                         holder.awcdecoration9.setVisibility(View.VISIBLE);
                    }
                }
            }
            holder.awcdecoration.setText("Yes");
//            holder.awcdecoration.setText(inspactionviewList.getAwcdecortnfrecce()+" "+"/"+awwroutinfollow1+
//                    ","+awwroutinfollow2+","+awwroutinfollow3+","+awwroutinfollow4+","+awwroutinfollow5+","+awwroutinfollow6
//            +","+awwroutinfollow7+","+awwroutinfollow8+","+awwroutinfollow9);

        }
        else if (inspactionviewList.getAwcdecortnfrecce().equals("n")){
            holder.awcdecoration.setText("No");
            holder.awcdecoration1.setVisibility(View.GONE);
            holder.awcdecoration2.setVisibility(View.GONE);
            holder.awcdecoration3.setVisibility(View.GONE);
            holder.awcdecoration4.setVisibility(View.GONE);
            holder.awcdecoration5.setVisibility(View.GONE);
            holder.awcdecoration6.setVisibility(View.GONE);
            holder.awcdecoration7.setVisibility(View.GONE);
            holder.awcdecoration8.setVisibility(View.GONE);
            holder.awcdecoration9.setVisibility(View.GONE);
        }
        holder.pseactivityseen.setText(inspactionviewList.getAwwfollowroutine());
        Log.e("pseactivityseen",inspactionviewList.getAwwfollowroutine());
        holder.nochildren.setText(inspactionviewList.getTotchldenroll());
        holder.reschild.setText(inspactionviewList.getTotchldfoundtoday());
        if (inspactionviewList.getChldparticipatecc().equals("y")){
            holder.processId.setText("Yes");
        }
        else if (inspactionviewList.getChldparticipatecc().equals("n")){
            holder.processId.setText("No");
        }
        if (inspactionviewList.getAssmntcard().equals("y")){
            if (inspactionviewList.getAssmntcardst().equals("In Use")){
                holder.assementcardId.setText("Yes"+" "+"/"+"In Use");
            }
            else if (inspactionviewList.getAssmntcardst().equals("Not in Use")){
                holder.assementcardId.setText("Yes"+" "+"/"+"Not in Use");
            }
        }
        else if (inspactionviewList.getAssmntcard().equals("n")){
            holder.assementcardId.setText("No");
        }

        holder.eccekitId.setText(inspactionviewList.getLastecckitrcvdt());
        holder.obserId.setText(inspactionviewList.getLasteccdayobserv());

        holder.shisucommand.setText(inspactionviewList.getShishualoycmnt());
        holder.textViewSnp.setText(inspactionviewList.getSnpname());
        if (inspactionviewList.getMrngsnacks().equals("Yes")){
            if (inspactionviewList.getMrngsnacktyp().equals("ASM")) {
                holder.morning.setText(inspactionviewList.getMrngsnacks() + " " + "/" + "As per menu");
            }
            else   if (inspactionviewList.getMrngsnacktyp().equals("NASM")) {
                holder.morning.setText(inspactionviewList.getMrngsnacks() + " " + "/" + "As per menu");
            }
        }
        else if (inspactionviewList.getMrngsnacks().equals("No")){
            holder.morning.setText(inspactionviewList.getMrngsnacks());
        }
        if (inspactionviewList.getSnpserve().equals("Yes")){
            if (inspactionviewList.getSnpservetyp().equals("ASM")){
                holder.hcmservedawcId.setText(inspactionviewList.getSnpserve()+ " " + "/" + "As per menu");
            }
           else  if (inspactionviewList.getSnpservetyp().equals("NASM")){
                holder.hcmservedawcId.setText(inspactionviewList.getSnpserve() + " " + "/" + "As per menu");
            }
        }
       else if (inspactionviewList.getSnpserve().equals("No")){
            holder.hcmservedawcId.setText(inspactionviewList.getSnpserve());
        }
        holder.menumorningsank.setText(inspactionviewList.getMrngmenu());
        holder.snpserveawc.setText(inspactionviewList.getSnpserve());
        holder.snpmenuday.setText(inspactionviewList.getMrngmenu());
        holder.childrenpresentday.setText(inspactionviewList.getChldprsnttoday());
        holder.childrenenrolledId.setText(inspactionviewList.getChldprsntenroll());
        holder.plmchildrenenrolledId.setText(inspactionviewList.getPmlmprsnt());
        holder.pmlmpresentday.setText(inspactionviewList.getPmlmprsnttoday());
        if (inspactionviewList.getAnyfeedintrptn().equals("Y")){
            holder.lastthreeday.setText("Yes");
        }
        else if (inspactionviewList.getAnyfeedintrptn().equals("N")){
            holder.lastthreeday.setText("No");
        }
        if (inspactionviewList.getAnyfeedintrptn().equals("Y")) {
            if (inspactionviewList.getFeedintrpt1m().equals("1")) {
                month1 = "January";

            } else if (inspactionviewList.getFeedintrpt1m().equals("2")) {
                month1 = "February";

            } else if (inspactionviewList.getFeedintrpt1m().equals("3")) {
                month1 = "March";

            } else if (inspactionviewList.getFeedintrpt1m().equals("4")) {
                month1 = "April";

            } else if (inspactionviewList.getFeedintrpt1m().equals("5")) {
                month1 = "May";

            } else if (inspactionviewList.getFeedintrpt1m().equals("6")) {
                month1 = "June";

            } else if (inspactionviewList.getFeedintrpt1m().equals("7")) {
                month1 = "July";

            } else if (inspactionviewList.getFeedintrpt1m().equals("8")) {
                month1 = "August";

            } else if (inspactionviewList.getFeedintrpt1m().equals("9")) {
                month1 = "September";

            } else if (inspactionviewList.getFeedintrpt1m().equals("10")) {
                month1 = "October";

            } else if (inspactionviewList.getFeedintrpt1m().equals("11")) {
                month1 = "November";

            } else if (inspactionviewList.getFeedintrpt1m().equals("12")) {
                month1 = "December";

            } else {

            }

            if (inspactionviewList.getFeedintrpt2m().equals("1")) {
                month2 = "January";

            } else if (inspactionviewList.getFeedintrpt2m().equals("2")) {
                month2 = "February";

            } else if (inspactionviewList.getFeedintrpt2m().equals("3")) {
                month2 = "March";

            } else if (inspactionviewList.getFeedintrpt2m().equals("4")) {
                month2 = "April";

            } else if (inspactionviewList.getFeedintrpt2m().equals("5")) {
                month2 = "May";

            } else if (inspactionviewList.getFeedintrpt2m().equals("6")) {
                month2 = "June";

            } else if (inspactionviewList.getFeedintrpt2m().equals("7")) {
                month2 = "July";

            } else if (inspactionviewList.getFeedintrpt2m().equals("8")) {
                month2 = "August";

            } else if (inspactionviewList.getFeedintrpt2m().equals("9")) {
                month2 = "September";

            } else if (inspactionviewList.getFeedintrpt2m().equals("10")) {
                month2 = "October";

            } else if (inspactionviewList.getFeedintrpt2m().equals("11")) {
                month2 = "November";

            } else if (inspactionviewList.getFeedintrpt2m().equals("12")) {
                month2 = "December";

            } else {

            }

            if (inspactionviewList.getFeedintrpt3m().equals("1")) {
                month3 = "January";

            } else if (inspactionviewList.getFeedintrpt3m().equals("2")) {
                month3 = "February";

            } else if (inspactionviewList.getFeedintrpt3m().equals("3")) {
                month3 = "March";

            } else if (inspactionviewList.getFeedintrpt3m().equals("4")) {
                month3 = "April";

            } else if (inspactionviewList.getFeedintrpt3m().equals("5")) {
                month3 = "May";

            } else if (inspactionviewList.getFeedintrpt3m().equals("6")) {
                month3 = "June";

            } else if (inspactionviewList.getFeedintrpt3m().equals("7")) {
                month3 = "July";

            } else if (inspactionviewList.getFeedintrpt3m().equals("8")) {
                month3 = "August";

            } else if (inspactionviewList.getFeedintrpt3m().equals("9")) {
                month3 = "September";

            } else if (inspactionviewList.getFeedintrpt3m().equals("10")) {
                month3 = "October";

            } else if (inspactionviewList.getFeedintrpt3m().equals("11")) {
                month3 = "November";

            } else if (inspactionviewList.getFeedintrpt3m().equals("12")) {
                month3 = "December";

            } else {

            }
            holder.Anyfeedinginterruption.setText(month1 + " " + "," + month2 + " " + "," + month3);
            holder.Reasonsnp.setText(inspactionviewList.getSnpntsrvreasn());
        }
        else {
            holder.anyviewId.setVisibility(View.GONE);
            holder.resonviewId.setVisibility(View.GONE);
            holder.anyfeedingg.setVisibility(View.GONE);
            holder.resonvId.setVisibility(View.GONE);
        }
        holder.snpcommand.setText(inspactionviewList.getSnpcmnt());
        holder.malnurishdchld.setText(inspactionviewList.getMalnurishdchld());
        holder.nutritionlcommandId.setText(inspactionviewList.getInspctrcmntnutritional());

        holder.obser.setText(inspactionviewList.getChldfoundphysicaly());
        holder.otherIns.setText(inspactionviewList.getOawcname());
        holder.feedintrupduratn.setText(inspactionviewList.getFeedintrupduratn());
        holder.supactn.setText(inspactionviewList.getSupactn());
        holder.cmuntyprticpt.setText(inspactionviewList.getCommparticipation());
        holder.supvisit.setText(inspactionviewList.getSupvisit());
        holder.mthrmeet.setText(inspactionviewList.getLastsupustidate());
        holder.awmonitr.setText(inspactionviewList.getMedcinkitlstrcv());
        if (inspactionviewList.getMedcinkitst().equals("a")){
            if (inspactionviewList.getMedcinkitusest().equals("u")){
                holder.othrfunctnvst.setText("Available"+" "+"/"+"Used for Awc");
            }
           else if (inspactionviewList.getMedcinkitusest().equals("nu")){
                holder.othrfunctnvst.setText("Not Available"+" "+"/"+"Not Used for Awc");
            }
        }
        else if (inspactionviewList.getMedcinkitst().equals("na")){
            holder.othrfunctnvst.setText("Not Available");
        }
        holder.rcdmaintain.setText(inspactionviewList.getMedcnchrglstrcv());
        holder.regularchrg.setText(inspactionviewList.getRegularchrg());
        holder.medcnchrglstrcv.setText(inspactionviewList.getMedcnchrglstrcv());
        holder.preschllstrcv.setText(inspactionviewList.getPreschllstrcv());
        holder.grwthchrtlstrcv.setText(inspactionviewList.getGrwthchrtlstrcv());
        holder.awcrmrk.setText(inspactionviewList.getAwcrmrk());
        holder.psetv.setText(inspactionviewList.getPesname());
        holder.pselstisac.setText(inspactionviewList.getPselstisac());
        holder.pselstinspctn.setText(inspactionviewList.getPselstinspctn());
        holder.pseinspctr.setText(inspactionviewList.getPseinspctr());
        holder.adequepseId.setText(inspactionviewList.getAdequtspace());
        if (inspactionviewList.getAdequtspace().equals("y")){
            holder.pseactvtytyp.setText("Yes"+" "+"/"+inspactionviewList.getPseactvtytyp());
        }
       else if (inspactionviewList.getAdequtspace().equals("y")){
            holder.pseactvtytyp.setText("No");
        }
        holder.linearcenter.setVisibility(View.VISIBLE);
        holder.linearofficer.setVisibility(View.GONE);
        holder.linearinspactor.setVisibility(View.GONE);
        holder.linearbuilding.setVisibility(View.GONE);
        holder.lineartoilet.setVisibility(View.GONE);
        holder.linearkitchen.setVisibility(View.GONE);
        holder.lineareletricity.setVisibility(View.GONE);
        holder.lineardrinking.setVisibility(View.GONE);
        holder.linearfood.setVisibility(View.GONE);
        holder.linearawcins.setVisibility(View.GONE);
        holder.linearawcshishu.setVisibility(View.GONE);
        holder.linearawcssnpserved.setVisibility(View.GONE);
        holder.linearanutrition.setVisibility(View.GONE);
        holder.linearother.setVisibility(View.GONE);
        holder.linearpse.setVisibility(View.GONE);
        if (currentPosition == position) {
//            Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
//            holder.linearcenter.setVisibility(View.VISIBLE);
//            holder.linearcenter.startAnimation(slideDown);
//            holder.linearofficer.setVisibility(View.VISIBLE);
//            holder.linearofficer.startAnimation(slideDown);
        }
        holder.centername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                currentPosition = position;
//                Log.e("currentPosition", String.valueOf(currentPosition));
//                notifyDataSetChanged();
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.VISIBLE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);
            }
        });
        holder.textViewofficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                currentPositionn = position;
//                Log.e("currentPosition", String.valueOf(currentPositionn));
//                notifyDataSetChanged();
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.VISIBLE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);

            }
        });
        holder.textViewinspactor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                currentPositionn = position;
//                Log.e("currentPosition", String.valueOf(currentPositionn));
//                notifyDataSetChanged();
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.VISIBLE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);
            }
        });
        holder.textViewbuilding.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                 holder.linearcenter.setVisibility(View.GONE);
                 holder.linearcenter.startAnimation(slideDown);
                 holder.linearofficer.setVisibility(View.GONE);
                 holder.linearofficer.startAnimation(slideDown);
                 holder.linearinspactor.setVisibility(View.GONE);
                 holder.linearinspactor.startAnimation(slideDown);
                 holder.linearbuilding.setVisibility(View.VISIBLE);
                 holder.linearbuilding.startAnimation(slideDown);
                 holder.lineartoilet.setVisibility(View.GONE);
                 holder.lineartoilet.startAnimation(slideDown);
                 holder.linearkitchen.setVisibility(View.GONE);
                 holder.linearkitchen.startAnimation(slideDown);
                 holder.lineareletricity.setVisibility(View.GONE);
                 holder.lineareletricity.startAnimation(slideDown);
                 holder.lineardrinking.setVisibility(View.GONE);
                 holder.lineardrinking.startAnimation(slideDown);
                 holder.linearfood.setVisibility(View.GONE);
                 holder.linearfood.startAnimation(slideDown);
                 holder.linearawcins.setVisibility(View.GONE);
                 holder.linearawcins.startAnimation(slideDown);
                 holder.linearawcshishu.setVisibility(View.GONE);
                 holder.linearawcshishu.startAnimation(slideDown);
                 holder.linearawcssnpserved.setVisibility(View.GONE);
                 holder.linearawcssnpserved.startAnimation(slideDown);
                 holder.linearanutrition.setVisibility(View.GONE);
                 holder.linearanutrition.startAnimation(slideDown);
                 holder.linearother.setVisibility(View.GONE);
                 holder.linearother.startAnimation(slideDown);
                 holder.linearpse.setVisibility(View.GONE);
                 holder.linearpse.startAnimation(slideDown);
                 if (rentbuild.equals("NA")){
                     holder.rententedbuildinginID.setVisibility(View.GONE);
                     holder.rententedbuildinginIDD.setVisibility(View.GONE);
                     holder.rententedbuildinginIDv.setVisibility(View.GONE);
                     holder.rententedbuildinginD.setVisibility(View.GONE);
                 }
                 else {
                     holder.rententedbuildinginID.setVisibility(View.VISIBLE);
                     holder.rententedbuildinginIDD.setVisibility(View.VISIBLE);
                     holder.rententedbuildinginIDv.setVisibility(View.VISIBLE);
                     holder.rententedbuildinginD.setVisibility(View.VISIBLE);
                 }
                 /// off fund name
//                 if (fundname.equals("NA")){
//                     holder.fundnameIDD.setVisibility(View.GONE);
//                     holder.fundnameID.setVisibility(View.GONE);
//                     holder.fundnameIDv.setVisibility(View.GONE);
//                     holder.fundnameD.setVisibility(View.GONE);
//                 }
//                 else
//                 {
//                     holder.fundnameIDD.setVisibility(View.VISIBLE);
//                     holder.fundnameID.setVisibility(View.VISIBLE);
//                     holder.fundnameIDv.setVisibility(View.VISIBLE);
//                     holder.fundnameD.setVisibility(View.VISIBLE);
//                 }

                 /// off fund name
                 if (otherbuild.equals("PRY")){
                     holder.othergovIDD.setVisibility(View.VISIBLE);
                     holder.othergovID.setVisibility(View.VISIBLE);
                     holder.othergovIDv.setVisibility(View.VISIBLE);
                     holder.otherD.setVisibility(View.VISIBLE);
                 }
                 else {
                     holder.othergovIDD.setVisibility(View.GONE);
                     holder.othergovID.setVisibility(View.GONE);
                     holder.othergovIDv.setVisibility(View.GONE);
                     holder.otherD.setVisibility(View.GONE);
                 }
                 if (otherbuild.equals("SSK")){
                     holder.othergovIDD.setVisibility(View.VISIBLE);
                     holder.othergovID.setVisibility(View.VISIBLE);
                     holder.othergovIDv.setVisibility(View.VISIBLE);
                     holder.otherD.setVisibility(View.VISIBLE);
                 }
                 else {
                     holder.othergovIDD.setVisibility(View.GONE);
                     holder.othergovID.setVisibility(View.GONE);
                     holder.othergovIDv.setVisibility(View.GONE);
                     holder.otherD.setVisibility(View.GONE);
                 }
                 if (otherbuild.equals("MSK")){
                     holder.othergovIDD.setVisibility(View.VISIBLE);
                     holder.othergovID.setVisibility(View.VISIBLE);
                     holder.othergovIDv.setVisibility(View.VISIBLE);
                     holder.otherD.setVisibility(View.VISIBLE);
                 }
                 else {
                     holder.othergovIDD.setVisibility(View.GONE);
                     holder.othergovID.setVisibility(View.GONE);
                     holder.othergovIDv.setVisibility(View.GONE);
                     holder.otherD.setVisibility(View.GONE);
                 }
                if (otherbuild.equals("MSSK")){
                    holder.othergovIDD.setVisibility(View.VISIBLE);
                    holder.othergovID.setVisibility(View.VISIBLE);
                    holder.othergovIDv.setVisibility(View.VISIBLE);
                    holder.otherD.setVisibility(View.VISIBLE);
                }
                else {
                    holder.othergovIDD.setVisibility(View.GONE);
                    holder.othergovID.setVisibility(View.GONE);
                    holder.othergovIDv.setVisibility(View.GONE);
                    holder.otherD.setVisibility(View.GONE);
                }

                if (otherbuild.equals("OTH")){
                    holder.othergovIDD.setVisibility(View.VISIBLE);
                    holder.othergovID.setVisibility(View.VISIBLE);
                    holder.othergovIDv.setVisibility(View.VISIBLE);
                    holder.otherD.setVisibility(View.VISIBLE);
                }
                else {
                    holder.othergovIDD.setVisibility(View.GONE);
                    holder.othergovID.setVisibility(View.GONE);
                    holder.othergovIDv.setVisibility(View.GONE);
                    holder.otherD.setVisibility(View.GONE);
                }
             }
         });
        holder.textViewtoilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.VISIBLE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);
            }
        });
        holder.textViewkitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.VISIBLE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);
            }
        });
        holder.textViewelectriID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.VISIBLE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);

            }
        });
        holder.textViewdrinking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.VISIBLE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);

            }
        });

        holder.textViewfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.VISIBLE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);

            }
        });
        holder.textViewawceqipement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.VISIBLE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);

            }
        });

        holder.textViewshishualoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.VISIBLE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);


            }
        });
        holder.textViewSnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.VISIBLE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);

            }
        });
        holder.nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.VISIBLE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);

            }
        });
        holder.otherIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.VISIBLE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.GONE);
                holder.linearpse.startAnimation(slideDown);


            }
        });
        holder.psetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(mCtx, R.anim.slide_down);
                holder.linearcenter.setVisibility(View.GONE);
                holder.linearcenter.startAnimation(slideDown);
                holder.linearofficer.setVisibility(View.GONE);
                holder.linearofficer.startAnimation(slideDown);
                holder.linearinspactor.setVisibility(View.GONE);
                holder.linearinspactor.startAnimation(slideDown);
                holder.linearbuilding.setVisibility(View.GONE);
                holder.linearbuilding.startAnimation(slideDown);
                holder.lineartoilet.setVisibility(View.GONE);
                holder.lineartoilet.startAnimation(slideDown);
                holder.linearkitchen.setVisibility(View.GONE);
                holder.linearkitchen.startAnimation(slideDown);
                holder.lineareletricity.setVisibility(View.GONE);
                holder.lineareletricity.startAnimation(slideDown);
                holder.lineardrinking.setVisibility(View.GONE);
                holder.lineardrinking.startAnimation(slideDown);
                holder.linearfood.setVisibility(View.GONE);
                holder.linearfood.startAnimation(slideDown);
                holder.linearawcins.setVisibility(View.GONE);
                holder.linearawcins.startAnimation(slideDown);
                holder.linearawcshishu.setVisibility(View.GONE);
                holder.linearawcshishu.startAnimation(slideDown);
                holder.linearawcssnpserved.setVisibility(View.GONE);
                holder.linearawcssnpserved.startAnimation(slideDown);
                holder.linearanutrition.setVisibility(View.GONE);
                holder.linearanutrition.startAnimation(slideDown);
                holder.linearother.setVisibility(View.GONE);
                holder.linearother.startAnimation(slideDown);
                holder.linearpse.setVisibility(View.VISIBLE);
                holder.linearpse.startAnimation(slideDown);


            }
        });

    }

    @Override
    public int getItemCount() {
        return setgetmyInspactionviewListss.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder {
         ImageView centerimage,officerimage;
         CircularImageView inspactionimage;
            LinearLayout linearcenter,linearofficer,linearinspactor,linearbuilding,linearother,linearpse,
                    lineartoilet,linearkitchen,lineareletricity,lineardrinking,linearfood,linearawcins,linearawcshishu,linearawcssnpserved,linearanutrition;
            TextView centername,districnameID,ProjectNameID,SectorNameID,CenterNameID,textViewofficer,CdpoName,Cdepophone,AcdpoName,AcdpoPhone,
        SupervisorName,Supervisorphone,workername,workerphone,helpername,helperphone,textViewinspactor,inspactorName,InspactorNumber,MailId,address,
        textViewbuilding,lastisacID,lastinspactionID,buildingID,buildingruninID,rententedbuildinginID,rententedbuildinginIDD,buildingconditionID,CommentID,othergovIDD,othergovID
       ,fundnameIDD,fundnameID,fundnameD,rententedbuildinginD,otherD,textViewtoilet,lastisactoiletID,lastinspactiontoiletId,toiletID,toiletuseID,ComID,textViewkitchen
        ,lastisackitchenID,lastinspactionkitchenID,havesetkitchenID,cookindoneID,ComCookID,textViewelectriID,lastisaceletricty,lastinspactioneletricty,electrictyaveable,modeID,lightID,fanID,pumpID,ComeleID
         ,textViewdrinking,lastisacdrinking,lastinspactiondrinking,
                    owndrinkingwaterId,owndrinkingwatercommandId,textViewfood,lastisacfood,lastinspactionfood,adquispace,phycalcnk,comperstcok,
        stocksufficent,riceanddal,daleonlyId,soyachukbrind, saltId,
        stocklying,textViewawceqipement,utilishcondition,matchati,eleven,babywmechin,muactap,highttape,grothchart,srotingcont,handwish
                    ,textViewshishualoy,blockcornerID,bookcorner,drawingcorner,playcorner,whethereccerunning,ecceactivity,tlamId,
                           wwusing,artworkId,
                           childrenindividal,
                    awcdecoration,pseactivityseen,nochildren,reschild,eccekitId,
                    obserId,
                    processId,assementcardId,shisucommand,sishualoy,textViewSnp,morning,menumorningsank,snpserveawc,
                    snpmenuday,childrenpresentday,childrenenrolledId,pmlmpresentday,lastthreeday,Anyfeedinginterruption,Reasonsnp,
                    snpcommand,nutrition,malnurishdchld,obser,otherIns,feedintrupduratn,supactn,
                    cmuntyprticpt,supvisit,mthrmeet,awmonitr,othrfunctnvst,rcdmaintain,regularchrg,
                    medcnchrglstrcv,preschllstrcv,grwthchrtlstrcv,awcrmrk,psetv,pselstisac,pselstinspctn,pseactvtytyp,pseinspctr,plmchildrenenrolledId;
            View fundnameIDv,rententedbuildinginIDv,othergovIDv,anyviewId,resonviewId,lvtoilateview;
            TableLayout shualyyId,shualyNId;
            LinearLayout anyfeedingg,resonvId,lvtoilate;
            TextView sishualytextNID,pseactivityfoundId,childrenenrollId,
                    childrenfoundtodayId,parcipientpseactivityId,ifassessmentcardId,nocommandId,hcmservedawcId,nutritionlcommandId,adequepseId;
          TableRow tableadquestoreId,tablephycallcheckId,tablecomparedwithId,tablestocksuffientId,tablericeId,tabledalId,tablemusteroilId,tablesaltId;
          View viewtableadquestoreId,viewtablephycallcheckId,viewtablecomparedwithId,viewstocksuffientId,viewriceid,viewdalId,viewmusterId;
          TextView awcdecoration1,awcdecoration2,awcdecoration3,awcdecoration4,awcdecoration5,
                  awcdecoration6,awcdecoration7,awcdecoration8,awcdecoration9;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            /////
            tableadquestoreId = (TableRow)itemView.findViewById(R.id.tableadquestoreId);
            tablephycallcheckId = (TableRow)itemView.findViewById(R.id.tablephycallcheckId);
            tablecomparedwithId = (TableRow)itemView.findViewById(R.id.tablecomparedwithId);
            tablecomparedwithId = (TableRow)itemView.findViewById(R.id.tablecomparedwithId);
            tablestocksuffientId = (TableRow)itemView.findViewById(R.id.tablestocksuffientId);
            tablericeId = (TableRow)itemView.findViewById(R.id.tablericeId);
            tabledalId = (TableRow)itemView.findViewById(R.id.tabledalId);
            tablemusteroilId = (TableRow)itemView.findViewById(R.id.tablemusteroilId);
            tablesaltId = (TableRow)itemView.findViewById(R.id.tablesaltId);

            viewtableadquestoreId =(View)itemView.findViewById(R.id.viewtableadquestoreId);
            viewtablephycallcheckId =(View)itemView.findViewById(R.id.viewtablephycallcheckId);
            viewtablecomparedwithId =(View)itemView.findViewById(R.id.viewtablecomparedwithId);
            viewstocksuffientId =(View)itemView.findViewById(R.id.viewstocksuffientId);
            viewriceid =(View)itemView.findViewById(R.id.viewriceid);
            viewdalId =(View)itemView.findViewById(R.id.viewdalId);
            viewmusterId =(View)itemView.findViewById(R.id.viewmusterId);
            awcdecoration1 =(TextView)itemView.findViewById(R.id.awcdecoration1);
            awcdecoration2 =(TextView)itemView.findViewById(R.id.awcdecoration2);
            awcdecoration3 =(TextView)itemView.findViewById(R.id.awcdecoration3);
            awcdecoration4 =(TextView)itemView.findViewById(R.id.awcdecoration4);
            awcdecoration5 =(TextView)itemView.findViewById(R.id.awcdecoration5);
            awcdecoration6 =(TextView)itemView.findViewById(R.id.awcdecoration6);
            awcdecoration7 =(TextView)itemView.findViewById(R.id.awcdecoration7);
            awcdecoration8 =(TextView)itemView.findViewById(R.id.awcdecoration8);
            awcdecoration9 =(TextView)itemView.findViewById(R.id.awcdecoration9);

            /////
            shualyyId =(TableLayout)itemView.findViewById(R.id.shualyyId);
            centername = (TextView) itemView.findViewById(R.id.centername);
            districnameID =(TextView) itemView.findViewById(R.id.districnameID);
            ProjectNameID = (TextView)itemView.findViewById(R.id.ProjectNameID);
            SectorNameID =(TextView)itemView.findViewById(R.id.SectorNameID);
            CenterNameID = (TextView)itemView.findViewById(R.id.CenterNameID);
            linearcenter = (LinearLayout) itemView.findViewById(R.id.linearcenter);
           // officerimage = (ImageView)itemView.findViewById(R.id.officerimage);
            linearofficer = (LinearLayout) itemView.findViewById(R.id.linearofficer);
            textViewofficer = (TextView) itemView.findViewById(R.id.textViewofficer);
            CdpoName = (TextView) itemView.findViewById(R.id.CdpoName);
            Cdepophone = (TextView) itemView.findViewById(R.id.Cdepophone);
            AcdpoName = (TextView) itemView.findViewById(R.id.AcdpoName);
            AcdpoPhone = (TextView) itemView.findViewById(R.id.AcdpoPhone);
            SupervisorName = (TextView) itemView.findViewById(R.id.SupervisorName);
            Supervisorphone = (TextView) itemView.findViewById(R.id.Supervisorphone);
            workername = (TextView) itemView.findViewById(R.id.workername);
            workerphone = (TextView) itemView.findViewById(R.id.workerphone);
            helpername = (TextView) itemView.findViewById(R.id.helpername);
            helperphone = (TextView) itemView.findViewById(R.id.helperphone);
            textViewinspactor = (TextView) itemView.findViewById(R.id.textViewinspactor);
            inspactorName = (TextView) itemView.findViewById(R.id.inspactorName);
            InspactorNumber = (TextView) itemView.findViewById(R.id.InspactorNumber);
            MailId = (TextView) itemView.findViewById(R.id.MailId);
            address = (TextView) itemView.findViewById(R.id.address);
            linearinspactor= (LinearLayout) itemView.findViewById(R.id.linearinspactor);
            inspactionimage = (CircularImageView) itemView.findViewById(R.id.inspactionimage);
            linearbuilding = (LinearLayout)itemView.findViewById(R.id.linearbuilding);
            textViewbuilding = (TextView)itemView.findViewById(R.id.textViewbuilding);
            lastisacID = (TextView)itemView.findViewById(R.id.lastisacID);
            lastinspactionID = (TextView)itemView.findViewById(R.id.lastinspactionID);
            buildingID = (TextView)itemView.findViewById(R.id.buildingID);
            buildingruninID =(TextView)itemView.findViewById(R.id.buildingruninID);
            rententedbuildinginID =(TextView)itemView.findViewById(R.id.rententedbuildinginID);
            rententedbuildinginIDD = (TextView)itemView.findViewById(R.id.rententedbuildinginIDD);
            buildingconditionID =(TextView)itemView.findViewById(R.id.buildingconditionID);
            CommentID = (TextView)itemView.findViewById(R.id.CommentID);
            othergovIDD =(TextView)itemView.findViewById(R.id.othergovIDD);
            othergovID = (TextView)itemView.findViewById(R.id.othergovID);
            fundnameIDD =(TextView)itemView.findViewById(R.id.fundnameIDD);
            ///fundname off
            fundnameID = (TextView)itemView.findViewById(R.id.fundnameID);
            ///fundname off
            fundnameIDv = (View)itemView.findViewById(R.id.fundnameIDv);
            rententedbuildinginIDv = (View)itemView.findViewById(R.id.rententedbuildinginIDv);
            othergovIDv = (View)itemView.findViewById(R.id.othergovIDv);
            fundnameD = (TextView)itemView.findViewById(R.id.fundnameD);
            rententedbuildinginD = (TextView)itemView.findViewById(R.id.rententedbuildinginD);
            otherD = (TextView)itemView.findViewById(R.id.otherD);
            lastisactoiletID = (TextView)itemView.findViewById(R.id.lastisactoiletID);
            lastinspactiontoiletId = (TextView)itemView.findViewById(R.id.lastinspactiontoiletId);
            toiletID = (TextView)itemView.findViewById(R.id.toiletID);
            toiletuseID = (TextView)itemView.findViewById(R.id.toiletuseID);
            ComID = (TextView)itemView.findViewById(R.id.ComID);
            textViewtoilet = (TextView)itemView.findViewById(R.id.textViewtoilet);
            lineartoilet =(LinearLayout)itemView.findViewById(R.id.lineartoilet);
            linearkitchen = (LinearLayout)itemView.findViewById(R.id.linearkitchen);
            textViewkitchen =(TextView)itemView.findViewById(R.id.textViewkitchen);
            lastisackitchenID = (TextView)itemView.findViewById(R.id.lastisackitchenID);
            lastinspactionkitchenID = (TextView)itemView.findViewById(R.id.lastinspactionkitchenID);
            havesetkitchenID = (TextView)itemView.findViewById(R.id.havesetkitchenID);
            cookindoneID = (TextView)itemView.findViewById(R.id.cookindoneID);
            ComCookID = (TextView)itemView.findViewById(R.id.ComCookID);
            lineareletricity =(LinearLayout)itemView.findViewById(R.id.lineareletricity);
            textViewelectriID = (TextView)itemView.findViewById(R.id.textViewelectriID);
            lastisaceletricty = (TextView)itemView.findViewById(R.id.lastisaceletricty);
            lastinspactioneletricty = (TextView)itemView.findViewById(R.id.lastinspactioneletricty);
            electrictyaveable = (TextView)itemView.findViewById(R.id.electrictyaveable);
            modeID = (TextView)itemView.findViewById(R.id.modeID);
            lightID = (TextView)itemView.findViewById(R.id.lightID);
            fanID = (TextView)itemView.findViewById(R.id.fanID);
            pumpID = (TextView)itemView.findViewById(R.id.pumpID);
            ComeleID = (TextView)itemView.findViewById(R.id.ComeleID);
            lineardrinking =(LinearLayout)itemView.findViewById(R.id.lineardrinking);
            textViewdrinking = (TextView)itemView.findViewById(R.id.textViewdrinking);
            lastisacdrinking = (TextView)itemView.findViewById(R.id.lastisacdrinking);
            lastinspactiondrinking =(TextView)itemView.findViewById(R.id.lastinspactiondrinking);
            owndrinkingwaterId = (TextView)itemView.findViewById(R.id.owndrinkingwaterId);
            owndrinkingwatercommandId = (TextView)itemView.findViewById(R.id.owndrinkingwatercommandId);

            linearfood = (LinearLayout)itemView.findViewById(R.id.linearfood);
            textViewfood = (TextView)itemView.findViewById(R.id.textViewfood);
            lastinspactionfood = (TextView)itemView.findViewById(R.id.lastinspactionfood);
            adquispace = (TextView)itemView.findViewById(R.id.adquispace);
            phycalcnk = (TextView)itemView.findViewById(R.id.phycalcnk);
            comperstcok = (TextView)itemView.findViewById(R.id.comperstcok);

            stocksufficent = (TextView)itemView.findViewById(R.id.stocksufficent);
            riceanddal = (TextView)itemView.findViewById(R.id.riceanddal);
            daleonlyId = (TextView)itemView.findViewById(R.id.daleonlyId);
            lastisacfood= (TextView)itemView.findViewById(R.id.lastisacfood);
            soyachukbrind = (TextView)itemView.findViewById(R.id.soyachukbrind);
            stocklying = (TextView)itemView.findViewById(R.id.stocklying);
            saltId = (TextView)itemView.findViewById(R.id.saltId);
            textViewawceqipement = (TextView) itemView.findViewById(R.id.textViewawceqipement);
            linearawcins = (LinearLayout)itemView.findViewById(R.id.linearawcins);
            utilishcondition = (TextView)itemView.findViewById(R.id.utilishcondition);
            matchati = (TextView)itemView.findViewById(R.id.matchati);
            eleven = (TextView)itemView.findViewById(R.id.eleven);
            babywmechin = (TextView)itemView.findViewById(R.id.babywmechin);
            muactap = (TextView)itemView.findViewById(R.id.muactap);
            highttape = (TextView)itemView.findViewById(R.id.highttape);
            grothchart = (TextView)itemView.findViewById(R.id.grothchart);
            srotingcont = (TextView)itemView.findViewById(R.id.srotingcont);
            handwish = (TextView)itemView.findViewById(R.id.handwish);

            linearawcshishu = (LinearLayout)itemView.findViewById(R.id.linearawcshishu);
            sishualoy = (TextView)itemView.findViewById(R.id.sishualoy);
            textViewshishualoy = (TextView)itemView.findViewById(R.id.textViewshishualoy);
            blockcornerID = (TextView)itemView.findViewById(R.id.blockcornerID);
            bookcorner =(TextView)itemView.findViewById(R.id.bookcorner);
            drawingcorner =(TextView)itemView.findViewById(R.id.drawingcorner);
            playcorner = (TextView)itemView.findViewById(R.id.playcorner);
            whethereccerunning = (TextView)itemView.findViewById(R.id.whethereccerunning);
            ecceactivity = (TextView)itemView.findViewById(R.id.ecceactivity);
            tlamId = (TextView)itemView.findViewById(R.id.tlamId);
            wwusing = (TextView)itemView.findViewById(R.id.wwusing);
            artworkId = (TextView)itemView.findViewById(R.id.artworkId);
            childrenindividal = (TextView)itemView.findViewById(R.id.childrenindividal);
            awcdecoration = (TextView)itemView.findViewById(R.id.awcdecoration);
            pseactivityseen = (TextView)itemView.findViewById(R.id.pseactivityseen);
            nochildren = (TextView)itemView.findViewById(R.id.nochildren);
            reschild = (TextView)itemView.findViewById(R.id.reschild);
            processId = (TextView)itemView.findViewById(R.id.processId);

            assementcardId = (TextView)itemView.findViewById(R.id.assementcardId);
            eccekitId = (TextView)itemView.findViewById(R.id.eccekitId);
            obserId = (TextView)itemView.findViewById(R.id.obserId);
            shisucommand = (TextView)itemView.findViewById(R.id.shisucommand);

            linearawcssnpserved = (LinearLayout)itemView.findViewById(R.id.linearawcssnpserved);
            textViewSnp = (TextView)itemView.findViewById(R.id.textViewSnp);
            morning = (TextView)itemView.findViewById(R.id.morning);
            menumorningsank = (TextView)itemView.findViewById(R.id.menumorningsank);
            snpserveawc = (TextView)itemView.findViewById(R.id.snpserveawc);
            snpmenuday = (TextView)itemView.findViewById(R.id.snpmenuday);
            childrenpresentday = (TextView)itemView.findViewById(R.id.childrenpresentday);
            childrenenrolledId =(TextView)itemView.findViewById(R.id.childrenenrolledId);
            pmlmpresentday = (TextView)itemView.findViewById(R.id.pmlmpresentday);
            lastthreeday = (TextView)itemView.findViewById(R.id.lastthreeday);
            Anyfeedinginterruption = (TextView)itemView.findViewById(R.id.Anyfeedinginterruption);
            Reasonsnp =(TextView)itemView.findViewById(R.id.Reasonsnp);
            snpcommand = (TextView)itemView.findViewById(R.id.snpcommand);
            linearanutrition =(LinearLayout)itemView.findViewById(R.id.linearanutrition);
            nutrition = (TextView)itemView.findViewById(R.id.nutrition);
            malnurishdchld = (TextView)itemView.findViewById(R.id.malnurishdchld);
            obser = (TextView)itemView.findViewById(R.id.obser);

            linearother = (LinearLayout)itemView.findViewById(R.id.linearother);
            otherIns = (TextView)itemView.findViewById(R.id.otherIns);
            feedintrupduratn = (TextView)itemView.findViewById(R.id.feedintrupduratn);
            supactn = (TextView)itemView.findViewById(R.id.supactn);
            cmuntyprticpt = (TextView)itemView.findViewById(R.id.cmuntyprticpt);
            supvisit = (TextView)itemView.findViewById(R.id.supvisit);
            mthrmeet = (TextView)itemView.findViewById(R.id.mthrmeet);
            awmonitr = (TextView)itemView.findViewById(R.id.awmonitr);
            othrfunctnvst = (TextView)itemView.findViewById(R.id.othrfunctnvst);
            rcdmaintain = (TextView)itemView.findViewById(R.id.rcdmaintain);
            regularchrg = (TextView)itemView.findViewById(R.id.regularchrg);
            medcnchrglstrcv = (TextView)itemView.findViewById(R.id.medcnchrglstrcv);
            preschllstrcv = (TextView)itemView.findViewById(R.id.preschllstrcv);
            grwthchrtlstrcv = (TextView)itemView.findViewById(R.id.grwthchrtlstrcv);
            awcrmrk = (TextView)itemView.findViewById(R.id.awcrmrk);

            linearpse = (LinearLayout)itemView.findViewById(R.id.linearpse);
            psetv = (TextView)itemView.findViewById(R.id.psetv);
            pselstisac =(TextView)itemView.findViewById(R.id.pselstisac);
            pselstinspctn =(TextView)itemView.findViewById(R.id.pselstinspctn);
            pseactvtytyp =(TextView)itemView.findViewById(R.id.pseactvtytyp);
            pseinspctr =(TextView)itemView.findViewById(R.id.pseinspctr);
            //////////////////////////////shualoy no////////////////////////////
            sishualytextNID = (TextView)itemView.findViewById(R.id.sishualytextNID);
            shualyNId =(TableLayout)itemView.findViewById(R.id.shualyNId);
            pseactivityfoundId  = (TextView)itemView.findViewById(R.id.pseactivityfoundId);
            childrenenrollId = (TextView)itemView.findViewById(R.id.childrenenrollId);
            childrenfoundtodayId  = (TextView)itemView.findViewById(R.id.childrenfoundtodayId);
            parcipientpseactivityId = (TextView)itemView.findViewById(R.id.parcipientpseactivityId);
            ifassessmentcardId = (TextView)itemView.findViewById(R.id.ifassessmentcardId);
            nocommandId = (TextView)itemView.findViewById(R.id.nocommandId);
            hcmservedawcId = (TextView)itemView.findViewById(R.id.hcmservedawcId);
            nutritionlcommandId = (TextView)itemView.findViewById(R.id.nutritionlcommandId);
            plmchildrenenrolledId =(TextView)itemView.findViewById(R.id.plmchildrenenrolledId);
            anyviewId = (View)itemView.findViewById(R.id.anyviewId);
            resonviewId = (View)itemView.findViewById(R.id.resonviewId);
            anyfeedingg = (LinearLayout)itemView.findViewById(R.id.anyfeedingg);
            resonvId = (LinearLayout)itemView.findViewById(R.id.resonvId);
            adequepseId = (TextView)itemView.findViewById(R.id.adequepseId);
            lvtoilateview =(View)itemView.findViewById(R.id.lvtoilateview);
            lvtoilate =(LinearLayout)itemView.findViewById(R.id.lvtoilate);
        }
    }
}
