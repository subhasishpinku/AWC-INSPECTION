package icdswb.in.ActivitySetGet;

public class Myinspaction {
    private String distname;
    private String projname;
    private String sectorname;
    private String centername;
    private String inspctnid;
    private String inspctndate;

    public Myinspaction(String distname, String projname, String sectorname, String centername, String inspctnid, String inspctndate) {
        this.distname = distname;
        this.projname = projname;
        this.sectorname = sectorname;
        this.centername = centername;
        this.inspctnid = inspctnid;
        this.inspctndate = inspctndate;
    }

    public String getDistname() {
        return distname;
    }

    public String getProjname() {
        return projname;
    }

    public String getSectorname() {
        return sectorname;
    }

    public String getCentername() {
        return centername;
    }

    public String getInspctnid() {
        return inspctnid;
    }

    public String getInspctndate() {
        return inspctndate;
    }
}
