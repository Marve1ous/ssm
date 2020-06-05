package cn.marve1ous.model;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private static final long serialVersionUID = 4276150310221594509L;
    private String stuid;

    private String stuname;

    private String stuclassid;

    private Integer stusex;

    private String stunation;

    private String stucard;

    private Integer stuyear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return stuid.equals(student.stuid) &&
                stuname.equals(student.stuname) &&
                stuclassid.equals(student.stuclassid) &&
                stusex.equals(student.stusex) &&
                stunation.equals(student.stunation) &&
                stucard.equals(student.stucard) &&
                stuyear.equals(student.stuyear);
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuid='" + stuid + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stuclassid='" + stuclassid + '\'' +
                ", stusex=" + stusex +
                ", stunation='" + stunation + '\'' +
                ", stucard='" + stucard + '\'' +
                ", stuyear=" + stuyear +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuid, stuname, stuclassid, stusex, stunation, stuyear);
    }

    public Student(String stuid, String stuname, String stuclassid, String stunation, String stucard) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.stuclassid = stuclassid;
        this.stunation = stunation;
        this.stucard = stucard;
    }

    public Student(String stuid, String stuname, String stuclassid, Integer stusex, String stunation, String stucard, Integer stuyear) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.stuclassid = stuclassid;
        this.stusex = stusex;
        this.stunation = stunation;
        this.stucard = stucard;
        this.stuyear = stuyear;
    }

    public Student() {
        super();
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

    public String getStuclassid() {
        return stuclassid;
    }

    public void setStuclassid(String stuclassid) {
        this.stuclassid = stuclassid == null ? null : stuclassid.trim();
    }

    public Integer getStusex() {
        return stusex;
    }

    public void setStusex(Integer stusex) {
        this.stusex = stusex;
    }

    public String getStunation() {
        return stunation;
    }

    public void setStunation(String stunation) {
        this.stunation = stunation == null ? null : stunation.trim();
    }

    public String getStucard() {
        return stucard;
    }

    public void setStucard(String stucard) {
        this.stucard = stucard == null ? null : stucard.trim();
    }

    public Integer getStuyear() {
        return stuyear;
    }

    public void setStuyear(Integer stuyear) {
        this.stuyear = stuyear;
    }
}