package icdswb.in;//package icdswb.in;
//
//import android.content.Intent;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.util.Log;
//
//public class TabsAdapter extends FragmentStatePagerAdapter {
//    int mNumOfTabs;
//    public TabsAdapter(FragmentManager fm, int NoofTabs){
//        super(fm);
//        this.mNumOfTabs = NoofTabs;
//
//    }
//    @Override
//    public int getCount() {
//        return mNumOfTabs;
//    }
//    @Override
//    public Fragment getItem(int position){
//        switch (position){
//            case 0:
//                OfflineNoSyne offlineNoSyne = new OfflineNoSyne();
//                return offlineNoSyne;
//            case 1:
//                OfflineSyne offlineSyne = new OfflineSyne();
//            return offlineSyne;
//            default:
//                return null;
//        }
//    }
//}