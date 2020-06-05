package cn.marve1ous.model;

import java.io.Serializable;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 8768099451778365908L;
    private String tid;

    private String tname;

    public Teacher(String tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public Teacher() {
        super();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }
}