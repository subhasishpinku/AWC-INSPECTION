package icdswb.in.ActivitySetGet;

public class OfflineLoginSetGet {
    private String loginid;
    private String userid;
    private String usertyplogin;
    private String userdistlogin;
    private String userimg;
    private String username;
    private String userdesig;
    private String userphn;
    private String userwatsapp;
    private String usermaillogin;
    private String userpwd;
    private String distid;
    private String disname;
    private String discode;
    private String distiduser;
    private String distnameuser;
    private String projiduser;
    private String projnameuser;
    private String seciduser;
    private String secnameuser;

    public OfflineLoginSetGet(String loginid,
                              String userid,String usertyplogin,
                              String userdistlogin,String userimg,
                              String username,String userdesig,String userphn,
                              String userwatsapp,String usermaillogin,String userpwd,String distid,String disname,String discode,
                              String distiduser,String distnameuser,String projiduser,String projnameuser,String seciduser,String secnameuser){
        this.loginid = loginid;
        this.userid = userid;
        this.usertyplogin = usertyplogin;
        this.userdistlogin = userdistlogin;
        this.userimg = userimg;
        this.username = username;
        this.userdesig = userdesig;
        this.userphn = userphn;
        this.userwatsapp = userwatsapp;
        this.usermaillogin = usermaillogin;
        this.userpwd = userpwd;
        this.distid = distid;
        this.disname = disname;
        this.discode=discode;
        this.distiduser=distiduser;
        this.distnameuser=distnameuser;
        this.projiduser=projiduser;
        this.projnameuser=projnameuser;
        this.seciduser=seciduser;
        this.secnameuser=secnameuser;
    }

    public String getLoginid() {
        return loginid;
    }

    public String getUserid() {
        return userid;
    }

    public String getUsertyplogin() {
        return usertyplogin;
    }

    public String getUserdistlogin() {
        return userdistlogin;
    }

    public String getUserimg() {
        return userimg;
    }

    public String getUsername() {
        return username;
    }

    public String getUserdesig() {
        return userdesig;
    }

    public String getUserphn() {
        return userphn;
    }

    public String getUserwatsapp() {
        return userwatsapp;
    }

    public String getUsermaillogin() {
        return usermaillogin;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public String getDistid() {
        return distid;
    }

    public String getDisname() {
        return disname;
    }

    public String getDiscode() {
        return discode;
    }

    public String getDistiduser() {
        return distiduser;
    }

    public String getDistnameuser() {
        return distnameuser;
    }

    public String getProjiduser() {
        return projiduser;
    }

    public String getProjnameuser() {
        return projnameuser;
    }

    public String getSeciduser() {
        return seciduser;
    }

    public String getSecnameuser() {
        return secnameuser;
    }
}
