package cn.marve1ous.model;

import java.io.Serializable;

public class Userrole implements Serializable {
    private static final long serialVersionUID = -6905333041416231101L;
    private String userid;

    private String roleid;

    public Userrole(String userid, String roleid) {
        this.userid = userid;
        this.roleid = roleid;
    }

    public Userrole() {
        super();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }
}