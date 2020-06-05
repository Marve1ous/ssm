package cn.marve1ous.model;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = -1012281551044442940L;
    private String resultid;

    private String resultname;

    private String stuid;

    private Integer resulttype;

    private String resultact;

    public Result(String resultid, String resultname, String stuid, Integer resulttype, String resultact) {
        this.resultid = resultid;
        this.resultname = resultname;
        this.stuid = stuid;
        this.resulttype = resulttype;
        this.resultact = resultact;
    }

    public Result() {
        super();
    }

    public String getResultid() {
        return resultid;
    }

    public void setResultid(String resultid) {
        this.resultid = resultid;
    }

    public String getResultname() {
        return resultname;
    }

    public void setResultname(String resultname) {
        this.resultname = resultname == null ? null : resultname.trim();
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public Integer getResulttype() {
        return resulttype;
    }

    public void setResulttype(Integer resulttype) {
        this.resulttype = resulttype;
    }

    public String getResultact() {
        return resultact;
    }

    public void setResultact(String resultact) {
        this.resultact = resultact == null ? null : resultact.trim();
    }
}