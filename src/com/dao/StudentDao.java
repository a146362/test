package com.dao;

import com.pojo.Course;
import com.pojo.Student;
import com.pojo.Xuanke;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 */
public interface StudentDao {
    /**
     * @param user 查询学号是否存在
     * @return
     */
    boolean queryIsExist(String user) throws SQLException;

    /**
     * @param name 通过学生姓名查找学生
     * @return
     */
    List<Student> queryStuByName(String name) throws SQLException;

    /**
     * @param sid 通过学生学号查询学生信息
     * @return
     */
    Student queryBySid(String sid) throws SQLException;

    /**
     * @param sid
     * @return 学生通过学号查询所选课程的情况
     * @throws SQLException
     */
    List<Xuanke> queryXuanke(String sid) throws SQLException;


    /**
     * @param stu
     * @throws SQLException 通过学号修改对应学生的信息
     */
    void changeStu(Student stu) throws SQLException;

    /**
     * @param spe 通过专业名查询所在专业的课程信息
     * @return
     */
    List<Course> queryBySpe(String spe) throws SQLException;
}
