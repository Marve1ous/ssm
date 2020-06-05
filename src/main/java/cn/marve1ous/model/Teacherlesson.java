package cn.marve1ous.model;

import java.io.Serializable;

public class Teacherlesson implements Serializable {
    private static final long serialVersionUID = -6579205433483708607L;
    private String tid;

    private String lessonid;

    public Teacherlesson(String tid, String lessonid) {
        this.tid = tid;
        this.lessonid = lessonid;
    }

    public Teacherlesson() {
        super();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getLessonid() {
        return lessonid;
    }

    public void setLessonid(String lessonid) {
        this.lessonid = lessonid == null ? null : lessonid.trim();
    }
}