package cn.marve1ous.model;

import java.io.Serializable;

public class Teacherclass implements Serializable {
    private static final long serialVersionUID = -2503691834385703095L;
    private String tid;

    private String classid;

    public Teacherclass(String tid, String classid) {
        this.tid = tid;
        this.classid = classid;
    }

    public Teacherclass() {
        super();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }
}