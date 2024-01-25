package icdswb.in;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.OtherActivity.Connectivity;


public class InspectionTabActivity extends Activity implements View.OnClickListener {
    Toolbar toolbar;
    TabHost tabHost;
    ImageView sportcdID, pickupID;
    LinearLayout lvspotId,lvplanId;
    CoordinatorLayout coordinatorLayout;
    Connectivity connectivity;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tabinspaction);
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
//                if (isNetworkAvailable()) {
//                    Intent intent = new Intent(getApplicationContext(), SportInspectionActivity.class);
//                    startActivity(intent);
//                } else {
//                    Snackbar snackbar = Snackbar
//                            .make(coordinatorLayout, "No Network Device", Snackbar.LENGTH_LONG)
//                            .setAction("UNDO", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Retry Network!", Snackbar.LENGTH_SHORT);
//                                    snackbar1.show();
//                                    Intent intent = new Intent(getApplicationContext(), SportInspectionActivityOffline.class);
//                                    startActivity(intent);
//                                }
//                            });
//
//                    snackbar.show();
//                }
                Intent intenttt = new Intent(getApplicationContext(), InspectionOfflineTabActivity.class);
                startActivity(intenttt);
               // Intent intenttt = new Intent(getApplicationContext(), SportInspectionActivity.class);
              //  startActivity(intenttt);
               // Toast.makeText(getApplicationContext(),"Spot Inspection is under Construction Please make Planning for AWC Inspection",Toast.LENGTH_SHORT).show();


//                connectivity = new Connectivity(this);
//                if (connectivity.isConnectedFast(this)){
//                    Intent intent = new Intent(getApplicationContext(), SportInspectionActivity.class);
//                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(),"Slow Network1",Toast.LENGTH_SHORT).show();
//
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Slow Network",Toast.LENGTH_SHORT).show();
//                }

                break;

            case R.id.pickupID:
                Intent intent = new Intent(InspectionTabActivity.this, PickupActivity.class);
                startActivity(intent);
                break;
            case R.id.lvspotId:
//                if (isNetworkAvailable()) {
//                    Intent intentt = new Intent(getApplicationContext(), SportInspectionActivity.class);
//                    startActivity(intentt);
//                } else {
//                    Snackbar snackbar = Snackbar
//                            .make(coordinatorLayout, "No Network Device", Snackbar.LENGTH_LONG)
//                            .setAction("UNDO", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Retry Network!", Snackbar.LENGTH_SHORT);
//                                    snackbar1.show();
//                                    Intent intent = new Intent(getApplicationContext(), SportInspectionActivityOffline.class);
//                                    startActivity(intent);
//                                }
//                            });
//
//                    snackbar.show();
//                }

                Intent intentttt = new Intent(getApplicationContext(), InspectionOfflineTabActivity.class);
                startActivity(intentttt);
               // Intent intentttt = new Intent(getApplicationContext(), SportInspectionActivity.class);
              //  startActivity(intentttt);
               // Toast.makeText(getApplicationContext(),"Spot Inspection is under Construction Please make Planning for AWC Inspection",Toast.LENGTH_SHORT).show();
                break;
            case R.id.lvplanId:
                Intent intentt = new Intent(InspectionTabActivity.this, PickupActivity.class);
                startActivity(intentt);
                break;
            default:
        }
    }


}
