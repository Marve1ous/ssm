package cn.marve1ous.model;

import java.io.Serializable;

public class Class implements Serializable {
    private static final long serialVersionUID = 7556346491848757070L;
    private String classid;

    private String classname;

    private Integer classyear;

    public Class(String classid, String classname, Integer classyear) {
        this.classid = classid;
        this.classname = classname;
        this.classyear = classyear;
    }

    public Class() {
        super();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Integer getClassyear() {
        return classyear;
    }

    public void setClassyear(Integer classyear) {
        this.classyear = classyear;
    }
}