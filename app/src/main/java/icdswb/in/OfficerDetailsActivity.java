package icdswb.in;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import icdswb.in.ActivityDatabase.DatabaseHelper;


public class OfficerDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView cdpoName,cdpoNumber,acdpoName,acdpoNumber,supervisorName,supervisorNumber,workerName,workerNumber,helperName,helperNumber,districId,projectID,sectorID,awcstvId;
    String dbid;
    String water,cdponame,acdpocont,buildstruc,electric,acdponame,kitchen,cdpocontact,workerno,worker_nm,toilet,awcslat,supvsrname,awcsslong,helperno,awcs_adrs,foodspace,helpernm,buildon,adqutspacepse,supvsrno,awcsid,awcscode,awcsname,planid,DbprojectID,DbsectorID,DbcenterID,projectnameDB,districnameDB,sectorrnameDB,centernameDB,systenDate;
    String userID,insid,lstinspctnbuldrep,lstinspctntoiletrep,lstinspctnkitchenrep,lstinspctnpserep,lstinspctnelectricrep,lstinspctndrnkwaterrep,lstinspctnfoodrep;
    String processid,dbdistid,dbprojectid,dbsectorid,dbcenterid,districnamedb,projectnamedb,sectorrnamedb,centernamedb,curDate,flag,inspactionid,awcscodeid,curTime;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_officerdetails);
        cdpoName = (TextView) findViewById(R.id.cdpoName);
        cdpoNumber = (TextView) findViewById(R.id.cdpoNumber);
        acdpoName = (TextView) findViewById(R.id.acdpoName);
        acdpoNumber = (TextView) findViewById(R.id.acdpoNumber);
        supervisorName = (TextView) findViewById(R.id.supervisorName);
        supervisorNumber = (TextView) findViewById(R.id.supervisorNumber);
        workerName = (TextView) findViewById(R.id.workerName);
        workerNumber = (TextView) findViewById(R.id.workerNumber);
        helperName = (TextView) findViewById(R.id.helperName);
        helperNumber = (TextView) findViewById(R.id.helperNumber);
        districId =(TextView)findViewById(R.id.districId);
        projectID = (TextView)findViewById(R.id.projectID);
        sectorID = (TextView)findViewById(R.id.sectorID);
        awcstvId =(TextView)findViewById(R.id.awcstvId);
        initToolBar();
        Intent intent = getIntent();
        dbid = intent.getStringExtra("id");
        helper = new DatabaseHelper(this);
        Cursor cursor = helper.getReadableDatabase().
                rawQuery("select * from awcsdtl where id = ?", new String[]{dbid});
        if (cursor.moveToFirst()) {
            do {
                water = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_WATER));
                cdponame = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CDPONAME));
                acdpocont = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ACDPOCONT));
                buildstruc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILDSTRUC));
                electric = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ELECTRIC));
                acdponame = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ACDPONAME));
                kitchen = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_KITCHEN));
                cdpocontact = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_CDPOCONTACT));
                workerno = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_WORKERNO));
                worker_nm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_WORKERNM));
                toilet = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_TOILET));
                awcslat = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLAT));
                supvsrname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SUPVSRNAME));
                awcsslong = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSLONG));
                helperno = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_HELPERNO));
                awcs_adrs = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSADRS));
                foodspace = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_FOODSPACE));
                helpernm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_HELPERNM));
                buildon = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BUILDON));
                adqutspacepse = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_ADQUTSPACEPSE));
                supvsrno = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_SUPVSRNO));
                awcsid = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSID));
                awcscode = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSCODE));
                awcsname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_AWCSNAME));
                planid = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_PLANID));
                lstinspctnbuldrep = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_BULD_REP));
                lstinspctntoiletrep = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_TOILET_REP));
                lstinspctnkitchenrep = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_KITCHEN_REP));
                lstinspctnpserep = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_PSE_REP));
                lstinspctnelectricrep = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_ELECTRIC_REP));
                lstinspctndrnkwaterrep = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_DRNKWATER_REP));
                lstinspctnfoodrep = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_LST_INSPCTN_FOOD_REP));
                cdpoName.setText(cdponame);
                cdpoNumber.setText(cdpocontact);
                acdpoName.setText(acdponame);
                acdpoNumber.setText(acdpocont);
                supervisorName.setText(supvsrname);
                supervisorNumber.setText(supvsrno);
                workerName.setText(worker_nm);
                workerNumber.setText(workerno);
                helperName.setText(helpernm);
                helperNumber.setText(helperno);
                awcstvId.setText(awcsname+" "+ "("+awcscode+")");
            } while (cursor.moveToNext());
            Log.e("awcsid", awcsid + " " + awcscode + " " + awcsname);
            Log.e("ValueDb", water + " " + cdponame + " " + acdpocont + " " + buildstruc + " " + electric + acdponame + " " + kitchen + " " + cdpocontact + workerno + " " + worker_nm + " " + toilet + " " + awcslat + " " + supvsrname + " " + awcsslong + " " + helperno + " " + awcs_adrs + " " + foodspace + " " + helpernm + " " + buildon + " " + adqutspacepse + " " + supvsrno + " ");
            Log.e("AWCSLATLONG", awcslat + " " + awcsslong);
            Log.e("planid", planid);
        }

        Cursor c = helper.getReadableDatabase().
                rawQuery("select * from awcsprocess where idprocess = ?", new String[]{dbid});
        if (c.moveToFirst()) {
            do {
                processid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_PROCESSID));
                dbdistid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_DBDISTID));
                dbprojectid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_DBPROJECTID));
                dbsectorid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_DBSECTORID));
                dbcenterid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_DBCENTERID));
                projectnamedb = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_PROJECT));
                districnamedb = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_DISTRIC));
                sectorrnamedb = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_SECTOR));
                centernamedb = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_CENTER));
                curDate = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_CURRENDATE));
                flag = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_FLAG));
                awcslat = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSLATLOCATION));
                awcsslong = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSLONGLOCATION));
                inspactionid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_INSPACTIONID));
                awcscodeid = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSCODEID));
                curTime = c.getString(c.getColumnIndex(DatabaseHelper.TABLE_AWCSTIME));
                districId.setText(districnamedb);
                projectID.setText(projectnamedb);
                sectorID.setText(sectorrnamedb);
                Log.e("AWCSLATLONG",awcslat+" "+awcsslong+" "+curDate+" "+curTime);
            }while (c.moveToNext());

        }
    }
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ICDS");
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
}
