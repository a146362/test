package com.pojo;

/**
 * @author Administrator
 */
public class Xuanke {
    private String cid;
    private String sid;
    private String tname;
    private int degree;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
    public Xuanke(){

    }

    public Xuanke(String cid, String sid, String tname, int degree) {
        this.cid = cid;
        this.sid = sid;
        this.tname = tname;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Xuanke{" +
                "cid='" + cid + '\'' +
                ", sid='" + sid + '\'' +
                ", tname='" + tname + '\'' +
                ", degree=" + degree +
                '}';
    }
}
