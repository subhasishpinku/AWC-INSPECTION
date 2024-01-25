package icdswb.in.ActivityShearepreferance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import icdswb.in.ActivitySetGet.User;
import icdswb.in.Login;


public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "icds";
    private static final String KEY_USERID= "user_id";
    private static final String KEY_USERTYPE ="user_typ";
    private static final String KEY_USERDIST = "user_dist";
    private static final String KEY_USERIMG = "user_img";
    private static final String KEY_USERNAME = "user_name";
    private static final String KEY_USERDESIG = "user_desig";
    private static final String KEY_USERPHONE = "user_phn";
    private static final String KEY_USERWHATSAPP = "user_watsapp";
    private static final String KEY_USERMAIL = "user_mail";
    private static final String KEY_USERPASS = "user_pwd";
    private static final String KEY_USERDISCODE = "dis_code";
    private static final String KEY_USERDISTID = "dist_id";
    private static final String KEY_USERDISTIDNAME = "dist_name";
    private static final String KEY_USERPROJECTID = "proj_id";
    private static final String KEY_USERPROJECTNAME = "proj_name";
    private static final String KEY_USERSECTORID = "sec_id";
    private static final String KEY_USERSECTORNAME = "sec_name";
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERID, user.getUserID());
        editor.putString(KEY_USERTYPE, user.getUsertyp());
        editor.putString(KEY_USERDIST, user.getUserDist());
        editor.putString(KEY_USERIMG,user.getUserImg());
        editor.putString(KEY_USERNAME,user.getUserName());
        editor.putString(KEY_USERDESIG,user.getUserDesig());
        editor.putString(KEY_USERPHONE, user.getUserPhone());
        editor.putString(KEY_USERWHATSAPP, user.getUserWhatsapp());
        editor.putString(KEY_USERMAIL, user.getUsermail());
        editor.putString(KEY_USERPASS, user.getUserPass());
        editor.putString(KEY_USERDISCODE,user.getDiscode());
        editor.putString(KEY_USERDISTID, user.getDdistid());
        editor.putString(KEY_USERDISTIDNAME, user.getDistnameuser());
        editor.putString(KEY_USERPROJECTID, user.getProjid());
        editor.putString(KEY_USERPROJECTNAME,user.getProjname());
        editor.putString(KEY_USERSECTORID, user.getSecid());
        editor.putString(KEY_USERSECTORNAME, user.getSecname());


        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;

    }
    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USERID, null),
                sharedPreferences.getString(KEY_USERTYPE, null),
                sharedPreferences.getString(KEY_USERDIST, null),
                sharedPreferences.getString(KEY_USERIMG, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_USERDESIG, null),
                sharedPreferences.getString(KEY_USERPHONE, null),
                sharedPreferences.getString(KEY_USERWHATSAPP, null),
                sharedPreferences.getString(KEY_USERMAIL, null),
                sharedPreferences.getString(KEY_USERPASS, null),
                sharedPreferences.getString(KEY_USERDISCODE, null),
                sharedPreferences.getString(KEY_USERDISTID, null),
                sharedPreferences.getString(KEY_USERDISTIDNAME, null),
                sharedPreferences.getString(KEY_USERPROJECTID, null),
                sharedPreferences.getString(KEY_USERPROJECTNAME, null),
                sharedPreferences.getString(KEY_USERSECTORID, null),
                sharedPreferences.getString(KEY_USERSECTORNAME, null)

        );
    }

    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        editor.apply();
        //  mCtx.startActivity(new Intent(mCtx, Login.class));
//        Intent  myIntent = new Intent(mCtx,Login.class);
//        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(mCtx, Login.class);
        mCtx.startActivity(intent);
    }
}
