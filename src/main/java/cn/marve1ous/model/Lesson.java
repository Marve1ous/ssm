package cn.marve1ous.model;

import java.io.Serializable;

public class Lesson implements Serializable {
    private static final long serialVersionUID = -5461566833649753419L;
    private String lessonid;

    private String lessonname;

    private Integer lessonyear;

    public Lesson(String lessonid, String lessonname, Integer lessonyear) {
        this.lessonid = lessonid;
        this.lessonname = lessonname;
        this.lessonyear = lessonyear;
    }

    public Lesson() {
        super();
    }

    public String getLessonid() {
        return lessonid;
    }

    public void setLessonid(String lessonid) {
        this.lessonid = lessonid == null ? null : lessonid.trim();
    }

    public String getLessonname() {
        return lessonname;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname == null ? null : lessonname.trim();
    }

    public Integer getLessonyear() {
        return lessonyear;
    }

    public void setLessonyear(Integer lessonyear) {
        this.lessonyear = lessonyear;
    }
}