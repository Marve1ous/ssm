package cn.marve1ous.model;

import java.io.Serializable;


public class Turn implements Serializable {
    private static final long serialVersionUID = -1889293194880045128L;
    private String turnid;

    private String turnname;

    private String stuid;

    public Turn(String turnid, String turnname, String stuid) {
        this.turnid = turnid;
        this.turnname = turnname;
        this.stuid = stuid;
    }

    public Turn() {
        super();
    }

    public String getTurnid() {
        return turnid;
    }

    public void setTurnid(String turnid) {
        this.turnid = turnid == null ? null : turnid.trim();
    }

    public String getTurnname() {
        return turnname;
    }

    public void setTurnname(String turnname) {
        this.turnname = turnname == null ? null : turnname.trim();
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }
}