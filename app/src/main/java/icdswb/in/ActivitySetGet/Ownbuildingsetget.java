package icdswb.in.ActivitySetGet;

public class Ownbuildingsetget {
    private String idfun;
    private String fid;
    private String fname;

   public Ownbuildingsetget(String idfun,String fid,String fname){
       this.idfun = idfun;
       this.fid = fid;
       this.fname = fname;

   }

    public String getIdfun() {
        return idfun;
    }

    public String getFid() {
        return fid;
    }

    public String getFname() {
        return fname;
    }

    public void setIdfun(String idfun) {
        this.idfun = idfun;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
