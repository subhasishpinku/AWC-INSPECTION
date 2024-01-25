package icdswb.in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TabHost;

public class SportTabView extends AppCompatActivity {
    TabLayout tabLayout;
    TabHost tabHost;
    String techid;
    String totpendingcall,totacptcall;
    String value="0";
    ViewPager viewPager;
    private int[] tabicons ={
            R.drawable.pending,
            R.drawable.accept
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sport_tab_view);
      //  Intent intent = getIntent();
       // value = intent.getStringExtra("value");
       // Log.e("TABBV",value);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager =(ViewPager)findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("NO SYNC DATA"));
        tabLayout.addTab(tabLayout.newTab().setText("SYNC DATA"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabsAdapter tabsAdapter = new TabsAdapter(this.getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        // tabLayout = getHost();
        // tabLayout.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.ab);
        // tabLayout.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.ab);
        // tabLayout.getTabWidget().setCurrentTab(0);
        // tabLayout.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.abch);
        tabLayout.getTabAt(0).setIcon(tabicons[0]);
        tabLayout.getTabAt(1).setIcon(tabicons[1]);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0){
                    Log.e("TABB","0");
//                    Intent intent = new Intent(getApplicationContext(), TabsAdapter.class);
//                    Bundle bundle_edit  =   new Bundle();
//                    bundle_edit.putString("value",value);
//                    intent.putExtras(bundle_edit);
//                    startActivity(intent);
                }
                else if (tab.getPosition()==1){
                    Log.e("TABB","1");
//                    Intent intent = new Intent(getApplicationContext(), TabsAdapter.class);
//                    Bundle bundle_edit  =   new Bundle();
//                    bundle_edit.putString("value",value);
//                    intent.putExtras(bundle_edit);
//                    startActivity(intent);
                }
                else {
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }
    public class TabsAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        public TabsAdapter(FragmentManager fm, int NoofTabs){
            super(fm);
            this.mNumOfTabs = NoofTabs;

        }
        @Override
        public int getCount() {
            return mNumOfTabs;
        }
        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    OfflineNoSyne offlineNoSyne = new OfflineNoSyne();
                    return offlineNoSyne;
                case 1:
                    OfflineSyne offlineSyne = new OfflineSyne();
                    return offlineSyne;
                default:
                    return null;
            }
        }
    }
}
