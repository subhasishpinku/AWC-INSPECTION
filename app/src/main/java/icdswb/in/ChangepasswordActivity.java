package icdswb.in;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.VolleySingleton;

import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_NAMEICDSLOGIN;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USER_PWD;
import static icdswb.in.ActivityDatabase.DatabaseHelper.TABLE_USER_lID;


public class ChangepasswordActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText userId,oldpass,newpass,confirmpass;
    Button submitID;
    String userNamee, userDesigg, userPhonee, userWhatsappp, usermaill, userPasss,userdist,id,usertyp,distID,disName;
    User user;
    private DatabaseHelper db;
    DatabaseHelper helper;
    String loginid,userid,userimg,username,userdesig,userphn,userwatsapp,usermail,
            userpwd,discode,distiduser,distnameuser,projiduser,projnameuser,seciduser,secnameuser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_changepassword);
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Change Password");
         setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        userId = (EditText)findViewById(R.id.userId);
        oldpass = (EditText)findViewById(R.id.oldpass);
        newpass = (EditText)findViewById(R.id.newpass);
        confirmpass = (EditText)findViewById(R.id.confirmpass);
        submitID = (Button)findViewById(R.id.submitID);
        user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        userNamee = String.valueOf(user.getUserName());
        userDesigg = String.valueOf(user.getUserDesig());
        userPhonee = String.valueOf(user.getUserPhone());
        userWhatsappp = String.valueOf(user.getUserWhatsapp());
        usermaill = String.valueOf(user.getUsermail());
        userPasss = String.valueOf(user.getUserPass());
        userdist = String.valueOf(user.getUserDist());
        userid = String.valueOf(user.getUserID());
        usertyp = String.valueOf(user.getUsertyp());
        db = new DatabaseHelper(this);
        helper = new DatabaseHelper(this);
        Log.e("usertyp", userNamee+" "+" "
                +userDesigg+" "+" "
                +userPhonee+" "+" "
                +userWhatsappp+" "
                +usermaill+" "
                +userPasss+" "
                +userdist+" "+" "+userid+" "+usertyp+" "+" ");
        userId.setText(userNamee);
        submitID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changepassword(userid,loginid);
            }
        });
        Cursor cc = helper.getLoginData();
        if (cc.moveToFirst()) {
            do {
                userid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                usertyp = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USER_TYP));
                discode = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_DISCODE));

            } while (cc.moveToNext());
        }

        String query = "SELECT * FROM " + TABLE_NAMEICDSLOGIN + " where " + TABLE_USER_lID + "=" +userid;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                loginid = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LOGINID));
                userid = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
                usertyp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_TYP));
                userdist = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_DIST));
                userimg = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_IMG));
                username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_NAME));
                userdesig = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_DESIG));
                userphn = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_PHN));
                userwatsapp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_WATSAPP));
                usermail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_MAIL));
                userpwd = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_PWD));
                distID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DISTID));
                disName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DISNAME));
                discode = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_DISCODE));
                distiduser = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERDISTID));
                distnameuser = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERDISTIDNAME));
                projiduser = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERPROJECTID));
                projnameuser = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERPROJECTNAME));
                seciduser = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERSECTORID));
                secnameuser = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USERSECTORNAME));
                Log.e("LoginPassworchange",loginid+" "+userid+" "
                        +usertyp+" "+userdist+" "+userimg+" "
                        +username+" "+userdesig+" "+userphn+" "
                        +userwatsapp+" "+usermail+" "+userpwd+" "
                        +discode+" "+distiduser+" "+distnameuser+" "+projiduser+" "+projnameuser+" "+seciduser+" "+secnameuser);

            }
            while (cursor.moveToNext());
        }
    }
    private  void Changepassword(final String id,final String loginid){
        final String uId = userId.getText().toString().trim();
        final String oPass = oldpass.getText().toString().trim();
        final String npass = newpass.getText().toString().trim();
        final String cpass = confirmpass.getText().toString().trim();
        if (TextUtils.isEmpty(uId)) {
            userId.setError("Please enter userId");
            userId.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(oPass)) {
            oldpass.setError("Please enter your Old Password");
            oldpass.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(npass)) {
            newpass.setError("Please enter your New Password");
            newpass.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(cpass)) {
            confirmpass.setError("Please enter your Confirm Password");
            confirmpass.requestFocus();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CHANGEPASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // progressBar.setVisibility(View.GONE);
                        Log.e("response", response);
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            String msgg = obj.getString("msg");
                            int status = obj.getInt("status");
                            distID = obj.getString("dist_id");
                            disName = obj.getString("dist_name");
                            Log.e("change",msgg+" "+status+" "+distID+" "+disName);
                            if (status == 1){
                                Toast.makeText(getApplicationContext(),msgg,Toast.LENGTH_SHORT).show();
                                UserLogin(uId,cpass,distID,disName,loginid);
                            }
                            else if (status == 0){
                              //  Toast.makeText(getApplicationContext(),msgg,Toast.LENGTH_SHORT).show();
                                String title = "Message Box";
                                String msg = msgg;
                                createDialog(title,msg);
                            }

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
                params.put("id", id);
                params.put("old_password", oPass);
                params.put("new_password", npass);
                params.put("confirm_password", cpass);
                Log.e("chPass"," "+uId+" "+oPass+" "+npass+" "+cpass);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    private void UserLogin(final String uId,final String cpass,final String distID,final String disName,final String loginid){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);
                        Log.e("response"," "+response);
                        try {
                            JSONArray array =new JSONArray(response);
                            Log.e("loginchangepass"," "+array);
                            //JSONArray array1 = array.getJSONArray(0);
                            JSONObject obj = array.getJSONObject(0);
                            JSONObject userJson = array.getJSONObject(1);
                            String message = obj.getString("message");
                            String error = obj.getString("error");
                            Log.e("data",message+" "+ error);
                            if (!obj.getBoolean("error")) {
                            //    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                userid = userJson.getString("user_id");
                                usertyp = userJson.getString("user_typ");
                                userdist = userJson.getString("user_dist");
                                userimg = userJson.getString("user_img");
                                username = userJson.getString("user_name");
                                userdesig = userJson.getString("user_desig");
                                userphn = userJson.getString("user_phn");
                                userwatsapp = userJson.getString("user_watsapp");
                                usermail = userJson.getString("user_mail");
                                userpwd = userJson.getString("user_pwd");
                                discode = userJson.getString("dis_code");
                                distiduser = userJson.getString("dist_id");
                                // distname = userJson.getString("dist_name");
                                distnameuser = userJson.optString("dist_name").replace("null", "NA");
                                projiduser = userJson.getString("proj_id");
                                //projname = userJson.getString("proj_name");
                                projnameuser = userJson.optString("proj_name").replace("null", "NA");
                                seciduser = userJson.getString("sec_id");
                                // secname = userJson.getString("sec_name");
                                secnameuser = userJson.optString("sec_name").replace("null", "NA");
                                // usertype = userJson.optString("user_type").replace("null", "NA");
                                // usertype = userJson.getString("user_type");
                                Log.e("UImage",userimg);
                                Log.e("Logindata",userid+" "+usertyp+" "+userdist+" "+userimg+" "+username+" "+userdesig+" "+userphn+" "+userwatsapp+" "+usermail+" "+userpwd+" "+discode);
//                                boolean isUpdate =  db.IcdsLogin(userid,usertyp,userdist,userimg,username,userdesig,userphn,userwatsapp,usermail,userpwd,distID,disName,discode,distiduser,distnameuser,projiduser,projnameuser,seciduser,secnameuser);
//                                db = new DatabaseHelper(getApplicationContext());
//                                if (isUpdate==true){
//                                    helper = new DatabaseHelper(getApplicationContext());
//                                    SQLiteDatabase db = helper.getReadableDatabase();
//                                    db.execSQL("UPDATE icdslogin SET userid = userid,usertyp = usertyp,userdist = userdist,userimg = userimg, username = username,userdesig = userdesig,userphn = userphn,userwatsapp = userwatsapp,usermail = usermail, userpwd = userpwd,distid = distID, disname = disName , discode = discode , distiduser = distiduser , projiduser = projiduser , projnameuser = projnameuser , seciduser = seciduser , secnameuser = secnameuser WHERE loginid=" +"1");
//
//                                }
//                                else {
//
//
//                                }
                                helper = new DatabaseHelper(getApplicationContext());
                                SQLiteDatabase dbb = helper.getReadableDatabase();
                                dbb.execSQL("UPDATE " + TABLE_NAMEICDSLOGIN + " SET " + TABLE_USER_PWD + " = '" + userpwd + "' WHERE " + TABLE_USER_lID + " = " + userid);

//                                helper = new DatabaseHelper(getApplicationContext());
//                                SQLiteDatabase db = helper.getReadableDatabase();
//                                db.execSQL("UPDATE icdslogin SET userid = userid,usertyp = usertyp,userdist = userdist,userimg = userimg, username = username,userdesig = userdesig,userphn = userphn,userwatsapp = userwatsapp,usermail = usermail, userpwd = userpwd,distid = distID, disname = disName , discode = discode , distiduser = distiduser , projiduser = projiduser , projnameuser = projnameuser , seciduser = seciduser , secnameuser = secnameuser WHERE loginid=" +loginid);

                                User user = new User(
                                        userJson.getString("user_id"),
                                        userJson.getString("user_typ"),
                                        userJson.getString("user_dist"),
                                        userJson.getString("user_img"),
                                        userJson.getString("user_name"),
                                        userJson.getString("user_desig"),
                                        userJson.getString("user_phn"),
                                        userJson.getString("user_watsapp"),
                                        userJson.getString("user_mail"),
                                        userJson.getString("user_pwd"),
                                        userJson.getString("dis_code"),
                                        userJson.getString("dist_id"),
                                        userJson.getString("dist_name"),
                                        userJson.getString("proj_id"),
                                        userJson.getString("proj_name"),
                                        userJson.getString("sec_id"),
                                        userJson.getString("sec_name")
                                );
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                //   finish();

                               // startActivity(new Intent(getApplicationContext(), NavigationDrawerActivity.class));
                                SharedPrefManager.getInstance(getApplicationContext()).logout();
                                Intent intent5 = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent5);
                                finish();
                            } else {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(), "No Valid UserName And Password", Toast.LENGTH_SHORT).show();


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", uId);
                params.put("user_pwd", cpass);
                params.put("user_dist", distID);
                Log.e("show",uId+" "+cpass+" "+distID);
                return params;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }

    public AlertDialog createDialog(String title, String msg){
        Log.e("METHOD"," "+title+" "+msg);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.messagedialog, null);
        ((TextView)dialogView.findViewById(R.id.dialog_title)).setText(title);
        ((TextView)dialogView.findViewById(R.id.dialog_msg)).setText(msg);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        ((Button)dialogView.findViewById(R.id.dialog_button)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
            }
        });
        builder.setView(dialogView);
        dialog.show();
        return dialog;
    }
}
