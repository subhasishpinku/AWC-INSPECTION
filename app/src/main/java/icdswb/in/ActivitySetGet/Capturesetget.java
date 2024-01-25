package icdswb.in.ActivitySetGet;

public class Capturesetget {
    private String img;
    private String latitude;
    private String longitude;
    private String awcscode;
    private String id;
    private String builtrunin;
    private String centerid;
    private String centername;
    public Capturesetget(String img, String latitude, String longitude,String awcscode,String id,String builtrunin,String centerid,String centername){
        this.img = img;
        this.latitude = latitude;
        this.longitude = longitude;
        this.awcscode=awcscode;
        this.id=id;
        this.builtrunin=builtrunin;
        this.centerid=centerid;
        this.centername=centername;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAwcscode() {
        return awcscode;
    }

    public void setAwcscode(String awcscode) {
        this.awcscode = awcscode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuiltrunin() {
        return builtrunin;
    }

    public void setBuiltrunin(String builtrunin) {
        this.builtrunin = builtrunin;
    }

    public String getCenterid() {
        return centerid;
    }

    public void setCenterid(String centerid) {
        this.centerid = centerid;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }
}
