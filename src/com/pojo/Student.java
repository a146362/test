package com.pojo;

/**
 * @author Administrator
 */
public class Student {
    private String id;
    private String name;
    private String password;
    private String sex;
    private String speciality;
    private String academe;
    private String overcourse;
    private String status;
    private String idcard;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAcademe() {
        return academe;
    }

    public void setAcademe(String academe) {
        this.academe = academe;
    }

    public String getOvercourse() {
        return overcourse;
    }

    public void setOvercourse(String overcourse) {
        this.overcourse = overcourse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //ππ‘Ï

    public Student(){

    }
    public Student(String id,String password){
        this.id = id;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", speciality='" + speciality + '\'' +
                ", academe='" + academe + '\'' +
                ", overcourse='" + overcourse + '\'' +
                ", status='" + status + '\'' +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
