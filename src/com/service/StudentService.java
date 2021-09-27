package com.service;

import com.pojo.Course;
import com.pojo.Student;
import com.pojo.Xuanke;
import java.util.List;

/**
 * @author Administrator
 */
public interface StudentService {
    /**
     * @param name
     * 通过学生姓名查找学生
     * @return
     */
    List<Student> queryStuByName(String name) ;

    /**
     * @param sid
     * 通过学生学号查询学生信息
     * @return
     */
    Student queryBySid(String sid) ;

    /**
     * @param sid
     * 学生通过学号查询所选课程的情况
     * @return
     */
    List<Xuanke> queryXuanke(String sid) ;


    /**
     * @param stu
     * @return
     */
    String changeStu(Student stu);

    /**
     * @param spe
     * 通过专业名查询所在专业的课程信息
     * @return
     */
    List<Course> queryBySpe(String spe);
}
