package icdswb.in.ActivitySetGet;

public class User {
    private String userID;
    private String usertyp;
    private String userDist;
    private String userImg;
    private String userName;
    private String userDesig;
    private String userPhone;
    private String userWhatsapp;
    private String usermail;
    private String userPass;
    private String discode;
    private String ddistid;
    private String distnameuser;
    private String projid;
    private String projname;
    private String secid;
    private String secname;
    public User(String userID, String usertyp, String userDist, String userImg, String userName,
                String userDesig, String userPhone,
                String userWhatsapp, String usermail, String userPass,String discode,
                String ddistid,String distnameuser,String projid,String projname,String secid,String secname) {
        this.userID = userID;
        this.usertyp = usertyp;
        this.userDist = userDist;
        this.userImg = userImg;
        this.userName = userName;
        this.userDesig = userDesig;
        this.userPhone = userPhone;
        this.userWhatsapp = userWhatsapp;
        this.usermail = usermail;
        this.userPass =userPass;
        this.discode=discode;
        this.ddistid=ddistid;
        this.distnameuser=distnameuser;
        this.projid=projid;
        this.projname=projname;
        this.secid=secid;
        this.secname=secname;

    }

    public String getUserID() {
        return userID;
    }

    public String getUsertyp() {
        return usertyp;
    }

    public String getUserDist() {
        return userDist;
    }

    public String getUserImg() {
        return userImg;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserDesig() {
        return userDesig;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserWhatsapp() {
        return userWhatsapp;
    }

    public String getUsermail() {
        return usermail;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getDiscode() {
        return discode;
    }

    public String getDdistid() {
        return ddistid;
    }

    public String getDistnameuser() {
        return distnameuser;
    }

    public String getProjid() {
        return projid;
    }

    public String getProjname() {
        return projname;
    }

    public String getSecid() {
        return secid;
    }

    public String getSecname() {
        return secname;
    }


}
