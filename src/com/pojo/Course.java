package com.pojo;

/**
 * @author Administrator
 */
public class Course {
    private String id;
    private String cname;
    private String coukind;
    private String begintime;
    private String beginspe;
    private int period;
    private String tname;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCoukind() {
        return coukind;
    }

    public void setCoukind(String coukind) {
        this.coukind = coukind;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getBeginspe() {
        return beginspe;
    }

    public void setBeginspe(String beginspe) {
        this.beginspe = beginspe;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Course() {
    }

    public Course(String id, String cname, String coukind, String begintime, String beginspe, int period, String tname, int score) {
        this.id = id;
        this.cname = cname;
        this.coukind = coukind;
        this.begintime = begintime;
        this.beginspe = beginspe;
        this.period = period;
        this.tname = tname;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", cname='" + cname + '\'' +
                ", coukind='" + coukind + '\'' +
                ", begintime='" + begintime + '\'' +
                ", beginspe='" + beginspe + '\'' +
                ", period=" + period +
                ", tname='" + tname + '\'' +
                ", score=" + score +
                '}';
    }
}
