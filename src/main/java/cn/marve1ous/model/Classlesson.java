package cn.marve1ous.model;

import java.io.Serializable;

public class Classlesson implements Serializable {
    private static final long serialVersionUID = 2862696809595279597L;
    private String classid;

    private String lessonid;

    public Classlesson(String classid, String lessonid) {
        this.classid = classid;
        this.lessonid = lessonid;
    }

    public Classlesson() {
        super();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getLessonid() {
        return lessonid;
    }

    public void setLessonid(String lessonid) {
        this.lessonid = lessonid == null ? null : lessonid.trim();
    }
}