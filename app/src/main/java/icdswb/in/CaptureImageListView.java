package icdswb.in;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icdswb.in.ActivityDatabase.DatabaseHelper;
import icdswb.in.ActivitySetGet.Capturesetget;
import icdswb.in.ActivitySetGet.User;
import icdswb.in.ActivityShearepreferance.SharedPrefManager;
import icdswb.in.ActivityVolley.Config;
import icdswb.in.ActivityVolley.HttpHandler;
import icdswb.in.ActivityVolley.RequestHandler;
import icdswb.in.ActivityVolley.VolleySingleton;

public class CaptureImageListView extends AppCompatActivity implements View.OnClickListener{
    RecyclerView recyclerView;
    EditText edt;
    Button searchID,submitID;
    String awcs_code,discode,usertyp,userid;

    String water ="NA";
    String cdponame ="NA";
    String acdpocont ="NA";
    String buildstruc="NA";
    String electric="NA";
    String acdponame="NA";
    String kitchen="NA";
    String cdpocontact="NA";
    String workerno="NA";
    String worker_nm="NA";
    String toilet="NA";
    String awcslat="NA";
    String supvsrname="NA";
    String awcsslong="NA";
    String helperno="NA";
    String awcs_adrs="NA";
    String foodspace="NA";
    String helpernm="NA";
    String buildon="NA";
    String adqutspacepse="NA";
    String supvsrno="NA";
    String awcsid="NA";
    String awcscode="NA";
    String awcsname="NA";
    String lstinspctnbuldrep="NA";
    String lstinspctntoiletrep="NA";
    String lstinspctnkitchenrep="NA";
    String lstinspctnpserep="NA";
    String lstinspctnelectricrep="NA";
    String lstinspctndrnkwaterrep="NA";
    String lstinspctnfoodrep = "NA";
    List<Capturesetget> Captures;
    ArrayList<Capturesetget> arrlist = new ArrayList<Capturesetget>();
    Capturelistview adapter;
    Toolbar toolbar;
    LinearLayout elevendigitVisableNotvisableId;
    ProgressBar progressBar;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.capture_image_listview);
        progressBar =(ProgressBar)findViewById(R.id.progressBar);
        edt = (EditText)findViewById(R.id.edt);
        searchID = (Button)findViewById(R.id.searchID);
        searchID.setOnClickListener(this);
        submitID = (Button)findViewById(R.id.submitID);
        submitID.setOnClickListener(this);
        initToolBar();
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        discode = String.valueOf(user.getUserDist());
        usertyp = String.valueOf(user.getUsertyp());
        userid = String.valueOf(user.getUserID());
        Log.e("usertyp",usertyp+ " "+discode+" "+userid);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Captures = new ArrayList<>();
        arrlist = new ArrayList<>();
        elevendigitVisableNotvisableId = (LinearLayout)findViewById(R.id.elevendigitVisableNotvisableId);
        elevendigitVisableNotvisableId.setVisibility(View.GONE);
        if (usertyp.equals("STA")){
            elevendigitVisableNotvisableId.setVisibility(View.VISIBLE);
        }
        else {
            elevendigitVisableNotvisableId.setVisibility(View.VISIBLE);
        }
        edt.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        TextWatcher m_MobileWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!s.toString().contains("19")) {
//                    edt.setText("19");
//                    Selection.setSelection(edt.getText(), edt.getText().length());
//                }
//            }
//        };
//        edt.addTextChangedListener(m_MobileWatcher);
        helper = new DatabaseHelper(this);
        Cursor cc = helper.getLoginData();
        if (cc.moveToFirst()) {
            do {
                usertyp = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USER_TYP));
                discode = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USER_DIST));
                userid = cc.getString(cc.getColumnIndex(DatabaseHelper.TABLE_USER_lID));
            } while (cc.moveToNext());
        }
        captureimagedata(discode,awcsid,awcscode,userid);
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("View Image List");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchID:
                int elevendigitcode = edt.getText().length();
                if (elevendigitcode>0) {
                    getUserData(discode);
                    hideKeyboard(this);
                }
                else {
                    Toast.makeText(CaptureImageListView.this,"Enter Value",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.submitID:
                Intent intent = new Intent(getApplicationContext(), NearestCenterActivity.class);
                startActivity(intent);
                break;
                default:

        }
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void getUserData(final String discode){
        awcs_code = edt.getText().toString();
        class UserData extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();
                //  String url = "http://202.0.103.141/inspection_service/srch_awcs_dtl_frm_code.php?awcs_code="+awcs_code+"&&dist="+discode;
                String url = "http://icdswb.in/inspection_service/srch_awcs_dtl_frm_code.php?awcs_code="+awcs_code+"&&dist="+discode;
                Log.e("WUL",url);
                String response = sh.makeServiceCall(url);
                Log.e("Response"," " + response);
                if (response != null) {

                    try {
                        JSONObject obj = new JSONObject(response);
                        Log.e("code"," "+obj);
                        JSONObject object = obj.getJSONObject("response");
                        final String message = object.getString("message");
                        String error = object.getString("error");
                        Log.e("awcscode"," "+message+ " "+error+" ");
                        if (message.equals("Invalid AWCS Code Given")){
                            finish();
                            startActivity(getIntent());
                            Captures.clear();
                            edt.setText("");
                        }
                        if (!object.getBoolean("error")){
                            JSONObject awcs_dtl = object.getJSONObject("awcs_dtl");
                            Log.e("awcs_dtl"," "+awcs_dtl);
                            String  ddistrict = awcs_dtl.getString("district");
                            String pproject = awcs_dtl.getString("project");
                            String ssector = awcs_dtl.getString("sector");
                            String awcsname = awcs_dtl.getString("awcs_name");
                            String distID = awcs_dtl.getString("district_id");
                            String projectID = awcs_dtl.getString("project_id");
                            String sectorID  = awcs_dtl.getString("sector_id");
                            String centerID = awcs_dtl.getString("awcs_id");
                            Log.e("SHOWID",ddistrict+" "+" "
                                    +pproject+" "+" "+ssector+" "+" "
                                    +awcsname+" "+" "
                                    +distID+" "+" "+projectID+" "+" "+sectorID+" "+" "+" "+centerID);
                            getproced(userid,distID,projectID,sectorID,centerID,discode);
                        }
                        else {


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

            }
        }
        UserData ru = new UserData();
        ru.execute();
    }
    private void getproced(final String userid,final String distID,final String projectID,final String sectorID,final String centerID,final String discode){
        class Process extends AsyncTask<Void, Void, String> {
            private ProgressBar progressBar;
            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("user_id", userid);
                params.put("dist_id", distID);
                params.put("proj_id", projectID);
                params.put("sectr_id", sectorID);
                params.put("centre_id", centerID);
                Log.e("proTag"," "+userid+" "+distID+" "+projectID+" "+sectorID+" "+centerID+" ");
                //returing the response
                return requestHandler.sendPostRequest(Config.PROCESS_URL2, params);
            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONArray array =new JSONArray(s);
                    Log.e("process"," "+array);
                    JSONObject obj = array.getJSONObject(0);
                    String error = obj.getString("error");
                    String message = obj.getString("message");
                    if (!obj.getBoolean("error")){
                        JSONObject awcs_dtl = obj.getJSONObject("awcs_dtl");
                        Log.e("awcs_dtl"," "+awcs_dtl);
                        water = awcs_dtl.getString("water");
                        cdponame = awcs_dtl.getString("cdpo_name");
                        acdpocont = awcs_dtl.getString("acdpo_cont");
                        buildstruc = awcs_dtl.getString("build_struc");
                        electric = awcs_dtl.getString("electric");
                        acdponame = awcs_dtl.getString("acdpo_name");
                        kitchen  = awcs_dtl.getString("kitchen");
                        cdpocontact = awcs_dtl.getString("cdpo_contact");
                        workerno = awcs_dtl.getString("worker_no");
                        worker_nm = awcs_dtl.getString("worker_nm");
                        //String awcsname = awcs_dtl.getString("awcs_name");
                        toilet = awcs_dtl.getString("toilet");
                        awcslat = awcs_dtl.getString("awcs_lat");
                        supvsrname = awcs_dtl.getString("supvsr_name");
                        awcsslong = awcs_dtl.getString("awcs_long");
                        helperno = awcs_dtl.getString("helper_no");
                        awcs_adrs = awcs_dtl.getString("awcs_adrs");
                        foodspace = awcs_dtl.getString("food_space");
                        helpernm = awcs_dtl.getString("helper_nm");
                        buildon = awcs_dtl.getString("build_on");
                        adqutspacepse = awcs_dtl.getString("adqut_space_pse");
                        supvsrno = awcs_dtl.getString("supvsr_no");
                        awcsid = awcs_dtl.getString("awcs_id");
                        awcscode = awcs_dtl.getString("awcs_code");
                        awcsname = awcs_dtl.getString("awcs_name");
                        lstinspctnbuldrep = awcs_dtl.getString("lst_inspctn_buld_rep");
                        lstinspctntoiletrep = awcs_dtl.getString("lst_inspctn_toilet_rep");
                        lstinspctnkitchenrep = awcs_dtl.getString("lst_inspctn_kitchen_rep");
                        lstinspctnpserep = awcs_dtl.getString("lst_inspctn_pse_rep");
                        lstinspctnelectricrep = awcs_dtl.getString("lst_inspctn_electric_rep");
                        lstinspctndrnkwaterrep = awcs_dtl.getString("lst_inspctn_drnkwater_rep");
                        lstinspctnfoodrep = awcs_dtl.getString("lst_inspctn_food_rep");
                        Log.e("pickudata"," "+water+ " "+cdponame+" "+acdpocont+" "+buildstruc+" "+electric+" "+acdponame+" "+kitchen+" "+cdpocontact+" "+workerno+" "+worker_nm+" "+toilet+" "+awcslat+" "+toilet+ " "+supvsrname+" "+awcsslong+" "+helperno+" "+awcs_adrs+" "+foodspace+" "+helpernm+" "+buildon+" "+adqutspacepse+" "+supvsrno+" "+awcsid+" "+awcscode+" "+awcsname+" ");
                        Log.e("last_in"," "+lstinspctnbuldrep+" "+lstinspctntoiletrep+" "+lstinspctnkitchenrep+" "+lstinspctnpserep+" "+lstinspctnelectricrep+" "+lstinspctndrnkwaterrep+" "+lstinspctnfoodrep+" ");
                        Log.e("awcsid",awcsid+" "+awcscode+" "+awcslat+" "+awcsslong);
                        Captures.clear();
                        captureimagedata(discode,awcsid,awcscode,userid);
                        Captures.clear();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Process ru = new Process();
        ru.execute();
    }
    public void captureimagedata(final String discode,final String awcsid,final String awcscode,final String userid){
        String img = "http://gis2.midland-mi.org/php/sketch.php?PIN=";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PHOTOUPLOADVIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("responseViewData",response);
                        try {
                            JSONArray array = new JSONArray(response);
                            Log.e("viewDATAView"," "+array);
                            JSONObject obj = array.getJSONObject(0);
                            JSONObject obj2 = array.getJSONObject(1);
                            String status = obj2.getString("status");
                            Log.e("statusObject",status);
                            if (status.equals("0")){
                                String title = "Message Box";
                                String msg = "No Record Found";
                                createDialog(title,msg);
                            }
                            JSONArray jsonArray = obj.getJSONArray("dtl");
                            for (int i = 0; i<jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.e("dtl", " " + jsonObject);
                                String centerimage = jsonObject.getString("center_image");
                                String latitude = jsonObject.getString("latitude");
                                String longitude = jsonObject.getString("longitude");
                                String awcscode = jsonObject.getString("awcs_code");
                                String id = jsonObject.getString("id");
                                String builtrunin = jsonObject.getString("built_run_in");
                                String centerid = jsonObject.getString("center_id");
                                String centername = jsonObject.getString("center_name");
                                Log.e("CAPTUREINAMEVIEW", " " + centerimage + " "
                                        + latitude + " " + longitude + " " + awcscode + " " + id+" "+centerid+" "+centername+" "+builtrunin);
                                progressBar.setVisibility(View.GONE);
                                Captures.add(new Capturesetget(
                                        jsonObject.getString("center_image"),
                                        jsonObject.getString("latitude"),
                                        jsonObject.getString("longitude"),
                                        jsonObject.getString("awcs_code"),
                                        jsonObject.getString("id"),
                                        jsonObject.getString("built_run_in"),
                                        jsonObject.getString("center_id"),
                                        jsonObject.getString("center_name")
                                ));
                            }
                            arrlist.addAll(Captures);
                            adapter = new Capturelistview(getApplicationContext(), Captures);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.VISIBLE);
                        String title = "Message Box";
                        String msg = "No Record Found";
                        createDialog(title,msg);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dist_id", discode);
                params.put("awcs_id", awcsid);
                params.put("awcs_code", awcscode);
                params.put("user_id", userid);
                Log.e("PHOTOVIEW", discode+" "+awcsid+" "+awcscode+" "+userid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton volleySingleton = VolleySingleton.getInstance(getApplicationContext());
        stringRequest.setShouldCache(false);
        volleySingleton.addToRequestQueue(stringRequest);
    }
    public AlertDialog createDialog(String title, String msg){
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
    public class Capturelistview extends RecyclerView.Adapter<Capturelistview.CapureviewHolder> {
        private Context mCtx;
        private List<icdswb.in.ActivitySetGet.Capturesetget> Capturesetget;
        String bIdd;
        public Capturelistview(Context mCtx, List<icdswb.in.ActivitySetGet.Capturesetget> Capturesetget) {
            this.mCtx = mCtx;
            this.Capturesetget = Capturesetget;
        }
        @Override
        public CapureviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.capture_log, null);
            return new CapureviewHolder(view);
        }
        @Override
        public void onBindViewHolder(final CapureviewHolder holder, int position) {
            final icdswb.in.ActivitySetGet.Capturesetget capturesetgets = Capturesetget.get(position);
            Glide.with(mCtx)
                    .load(capturesetgets.getImg())
                    .into(holder.imageView);

//            if (capturesetgets.getImg().length() == 0){
//                String img = "http://gis2.midland-mi.org/php/sketch.php?PIN=";
//                Glide.with(mCtx)
//                        .load(img)
//                        .into(holder.imageView);
//
//            }
//            else {
//                Glide.with(mCtx)
//                        .load(capturesetgets.getImg())
//                        .into(holder.imageView);
//            }
            holder.awcscodeId.setText("AWC CODE :" + " " + capturesetgets.getAwcscode());
            holder.latId.setText("Latitudes :" + " " + capturesetgets.getLatitude());
            holder.langId.setText("Longitudes :" + " " + capturesetgets.getLongitude());
            holder.centernameId.setText(capturesetgets.getCentername());
            if (capturesetgets.getBuiltrunin().equals("OB")){
                holder.buildingId.setText("BUILDING RUNS IN :" + " " + "Own Building");
            }
            else if (capturesetgets.getBuiltrunin().equals("RB")){
                holder.buildingId.setText("BUILDING RUNS IN :" + " " + "Rented Building");
            }
            else if (capturesetgets.getBuiltrunin().equals("OGB")){
                holder.buildingId.setText("BUILDING RUNS IN :" + " " + "Other Govt. Building");
            }
            else if (capturesetgets.getBuiltrunin().equals("PNR")){
                holder.buildingId.setText("BUILDING RUNS IN :" + " " + "Private Building / Community");
            }
            else if (capturesetgets.getBuiltrunin().equals("SCH")){
                holder.buildingId.setText("BUILDING RUNS IN :" + " " + "School");
            }
            holder.viewmapId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ViewImageActivity.class);
                    intent.putExtra("awcsid",awcsid);
                    intent.putExtra("awcscode",awcscode);
                    intent.putExtra("discode",discode);
                    intent.putExtra("userid",userid);
                    intent.putExtra("imge",capturesetgets.getImg());
                    startActivity(intent);

                }
            });
        }
        @Override
        public int getItemCount() {
            return Capturesetget.size();
        }

        class CapureviewHolder extends RecyclerView.ViewHolder {
            ImageView imageView,viewmapId;
            TextView awcscodeId;
            TextView latId,langId,buildingId,centernameId;
            public CapureviewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView)itemView.findViewById(R.id.imageView);
                awcscodeId = (TextView)itemView.findViewById(R.id.awcscodeId);
                latId = (TextView)itemView.findViewById(R.id.latId);
                langId =(TextView)itemView.findViewById(R.id.langId);
                buildingId =(TextView)itemView.findViewById(R.id.buildingId);
                viewmapId = (ImageView)itemView.findViewById(R.id.viewmapId);
                centernameId = (TextView)itemView.findViewById(R.id.centernameId);

            }
        }}
}
