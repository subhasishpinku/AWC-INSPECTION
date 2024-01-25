package icdswb.in.ActivitySetGet;

public class Dbprocess {
    private String idprocess;
    private String dbdistid;
    private String dbprojectid;
    private String dbsectorid;
    private String dbcenterid;
    private String districnamedb;
    private String projectnamedb;
    private String sectorrnamedb;
    private String centernamedb;
    private String currentdate;
    private String userid;
    private String flag;
    private String awcslat;
    private String awcsslong;
    private String inspactionid;
    private String awcsid;
    private String awcstime;
    private String flaggrecord;
    public Dbprocess(String idprocess, String dbdistid, String dbprojectid, String dbsectorid, String dbcenterid, String districnamedb, String projectnamedb,
                     String sectorrnamedb, String centernamedb, String currentdate,String userid,String flag,String awcslat,String awcsslong,
                     String inspactionid,String awcsid,String awcstime,String flaggrecord){
        this.idprocess = idprocess;
        this.dbdistid = dbdistid;
        this.dbprojectid = dbprojectid;
        this.dbsectorid =  dbsectorid;
        this.dbcenterid = dbcenterid;
        this.districnamedb = districnamedb;
        this.projectnamedb = projectnamedb;
        this.sectorrnamedb =  sectorrnamedb;
        this.centernamedb = centernamedb;
        this.currentdate = currentdate;
        this.userid = userid;
        this.flag = flag;
        this.awcslat = awcslat;
        this.awcsslong = awcsslong;
        this.inspactionid = inspactionid;
        this.awcsid = awcsid;
        this.awcstime = awcstime;
        this.flaggrecord=flaggrecord;
    }

    public String getDbdistid() {
        return dbdistid;
    }

    public String getDbprojectid() {
        return dbprojectid;
    }

    public String getDbsectorid() {
        return dbsectorid;
    }

    public String getDbcenterid() {
        return dbcenterid;
    }

    public String getIdprocess() {
        return idprocess;
    }

    public String getDistricnamedb() {
        return districnamedb;
    }

    public String getProjectnamedb() {
        return projectnamedb;
    }

    public String getSectorrnamedb() {
        return sectorrnamedb;
    }

    public String getCenternamedb() {
        return centernamedb;
    }

    public String getCurrentdate(){
        return  currentdate;
    }

    public String getUserid() {
        return userid;
    }

    public String getFlag() {
        return flag;
    }

    public String getAwcslat() {
        return awcslat;
    }

    public String getAwcsslong() {
        return awcsslong;
    }

    public String getInspactionid() {
        return inspactionid;
    }
    public String getAwcsid() {
        return awcsid;
    }

    public String getAwcstime() {
        return awcstime;
    }

    public String getFlaggrecord() {
        return flaggrecord;
    }
}
