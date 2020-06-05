package cn.marve1ous.model;

import java.io.Serializable;

public class Studentbak implements Serializable {
    private static final long serialVersionUID = -8637016941012622922L;
    private String stuid;

    private String stuname;

    private Integer stusex;

    private String stunation;

    private String stucard;

    private String stustatus;

    public Studentbak(String stuid, String stuname, Integer stusex, String stunation, String stucard, String stustatus) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.stusex = stusex;
        this.stunation = stunation;
        this.stucard = stucard;
        this.stustatus = stustatus;
    }

    public Studentbak() {
        super();
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

    public Integer getStusex() {
        return stusex;
    }

    public void setStusex(Integer stusex) {
        this.stusex = stusex;
    }

    public String getStunation() {
        return stunation;
    }

    public void setStunation(String stunation) {
        this.stunation = stunation == null ? null : stunation.trim();
    }

    public String getStucard() {
        return stucard;
    }

    public void setStucard(String stucard) {
        this.stucard = stucard == null ? null : stucard.trim();
    }

    public String getStustatus() {
        return stustatus;
    }

    public void setStustatus(String stustatus) {
        this.stustatus = stustatus == null ? null : stustatus.trim();
    }
}