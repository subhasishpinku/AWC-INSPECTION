package icdswb.in.ActivitySetGet;

public class DistricSetGet {
    private String tableisid;
    private String disID;
    private String distname;
    public DistricSetGet(String tableisid,String disID,String distname){
        this.tableisid = tableisid;
        this.disID = disID;
        this.distname = distname;

    }
    public void setTableisid(String tableisid) {
        this.tableisid = tableisid;
    }

    public void setDisID(String disID) {
        this.disID = disID;
    }

    public void setDistname(String distname) {
        this.distname = distname;
    }

    public String getTableisid() {
        return tableisid;
    }

    public String getDisID() {
        return disID;
    }

    public String getDistname() {
        return distname;
    }


}
