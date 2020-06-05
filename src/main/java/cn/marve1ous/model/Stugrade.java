package cn.marve1ous.model;

import java.io.Serializable;

public class Stugrade implements Serializable {
    private static final long serialVersionUID = -6650798271793506430L;
    private String testid;

    private String stuid;

    private String lessonid;

    private Integer testgrade;

    public Stugrade(String testid, String stuid, String lessonid, Integer testgrade) {
        this.testid = testid;
        this.stuid = stuid;
        this.lessonid = lessonid;
        this.testgrade = testgrade;
    }

    public Stugrade() {
        super();
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid == null ? null : testid.trim();
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public String getLessonid() {
        return lessonid;
    }

    public void setLessonid(String lessonid) {
        this.lessonid = lessonid == null ? null : lessonid.trim();
    }

    public Integer getTestgrade() {
        return testgrade;
    }

    public void setTestgrade(Integer testgrade) {
        this.testgrade = testgrade;
    }
}