package icdswb.in;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.OtherActivity.Connectivity;

public class InspectionOfflineTabActivity  extends Activity implements View.OnClickListener {
    Toolbar toolbar;
    TabHost tabHost;
    ImageView sportcdID, pickupID;
    LinearLayout lvspotId,lvplanId;
    CoordinatorLayout coordinatorLayout;
    Connectivity connectivity;
    private DatabaseHelper helper;
    FloatingActionButton fab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_offline_tabinspaction);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        toolbar.setTitle("Inspection");
        // setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sportcdID = (ImageView) findViewById(R.id.sportcdID);
        pickupID = (ImageView) findViewById(R.id.pickupID);
        sportcdID.setOnClickListener(this);
        pickupID.setOnClickListener(this);
        lvspotId = (LinearLayout)findViewById(R.id.lvspotId);
        lvplanId =(LinearLayout)findViewById(R.id.lvplanId);
        lvspotId.setOnClickListener(this);
        lvplanId.setOnClickListener(this);
        fab = (FloatingActionButton)findViewById(R.id.fab);
//        tabHost = getTabHost();
//
//        // Set TabChangeListener called when tab changed
//        tabHost.setOnTabChangedListener(this);
//
//        TabHost.TabSpec spec;
//        Intent intent;
//
//        /************* TAB1 ************/
//        // Create  Intents to launch an Activity for the tab (to be reused)
//        intent = new Intent().setClass(this, SportInspectionActivity.class);
//        spec = tabHost.newTabSpec("First").setIndicator("")
//                .setContent(intent);
//        tabHost.addTab(spec);
//
//        /************* TAB2 ************/
//        intent = new Intent().setClass(this, PickupActivity.class);
//        spec = tabHost.newTabSpec("Second").setIndicator("")
//                .setContent(intent);
//        tabHost.addTab(spec);
//
//        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
//        tv.setText("Sport Inspection");
//        tv.setTextSize(10);
//        tv.setTextColor(Color.parseColor("#ffffff"));
//        Typeface tff = Typeface.createFromAsset(getAssets(), "arial.ttf");
//        tv.setTypeface(tff);
//
//        TextView tv1 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
//        tv1.setText("Pickup From Planing");
//        tv1.setTextSize(10);
//        tv1.setTextColor(Color.parseColor("#ffffff"));
//        Typeface tff1 = Typeface.createFromAsset(getAssets(), "arial.ttf");
//        tv1.setTypeface(tff1);
//
//        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.ab);
////
////        // Set Tab1 as Default tab and change image
//        tabHost.getTabWidget().setCurrentTab(0);
//        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.abch);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()){
                    Intent intent = new Intent(getApplicationContext(), SportInspectionActivity.class);
                    startActivity(intent);

                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No Network Device", Snackbar.LENGTH_LONG)
                            .setAction("GO OFFLINE PLANING", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getApplicationContext(), SportInspectionActivityOffline.class);
                                    startActivity(intent);

                                }
                            });

                    snackbar.show();

                }

            }
        });
    }


    //   @Override
    //  public void onTabChanged(String s) {
//        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
//        {
//            tabHost.getTabWidget().getChildAt(i).setPadding(0, 0, 0, 0);
//
//            if(i==0)
//                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.ab);
//
//            else if(i==1)
//                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.ab);
//
//        }
//
//
//        Log.i("tabs", "CurrentTab: "+tabHost.getCurrentTab());
//
//        if(tabHost.getCurrentTab()==0)
//            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.abch);
//        else if(tabHost.getCurrentTab()==1)
//            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.abch);


    // }

    //    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
    public boolean isNetworkAvailable() {
        boolean connect = false;
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            connect = false;

        } else {
            connect = true;
        }

        return connect;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sportcdID:
                if (isNetworkAvailable()){
                Intent intent = new Intent(getApplicationContext(), PickupActivityOnline.class);
                startActivity(intent);

                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No Network Device", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Retry Network!", Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                }
                            });

                    snackbar.show();

                }
               break;

            case R.id.pickupID:
                String title1 = "Message Box";
                String msg1 = "Coming Soon";
               // finalsubmit(title1,msg1);
                Intent ii = new Intent(getApplicationContext(), SportTabView.class);
                startActivity(ii);
                break;
            case R.id.lvspotId:
                if (isNetworkAvailable()){

                }
                else {

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "No Network Device", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Retry Network!", Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                }
                            });

                    snackbar.show();

                }
                break;
            case R.id.lvplanId:
                String title = "Message Box";
                String msg = "Coming Soon";
                //finalsubmit(title,msg);
               Intent iii = new Intent(getApplicationContext(), SportTabView.class);
               startActivity(iii);
                break;
            default:
        }
    }

    public AlertDialog finalsubmit(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.finalsubmitdiolog, null);
        ((TextView)dialogView.findViewById(R.id.dialog_title)).setText(title);
        ((TextView)dialogView.findViewById(R.id.dialog_msg)).setText(msg);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        ((Button)dialogView.findViewById(R.id.yId)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(InspectionOfflineTabActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);

            }
        });
        ((Button)dialogView.findViewById(R.id.nId)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
                //Intent intent = new Intent(InspectionOfflineTabActivity.this,NavigationDrawerActivity.class);
              //  startActivity(intent);
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }


}
