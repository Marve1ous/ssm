package cn.marve1ous.model;

import java.io.Serializable;

public class Lessontest implements Serializable {
    private static final long serialVersionUID = -5038575665920449369L;
    private String testid;

    private String lessonid;

    private String lessonname;

    public Lessontest(String testid, String lessonid, String lessonname) {
        this.testid = testid;
        this.lessonid = lessonid;
        this.lessonname = lessonname;
    }

    public Lessontest() {
        super();
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid == null ? null : testid.trim();
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
}