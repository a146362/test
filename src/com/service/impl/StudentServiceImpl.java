package com.service.impl;

import com.dao.impl.StudentDaoImpl;
import com.pojo.Course;
import com.pojo.Student;
import com.pojo.Xuanke;
import com.service.StudentService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 */
public class StudentServiceImpl implements StudentService {
    StudentDaoImpl dao = new StudentDaoImpl();
    @Override
    public List<Student> queryStuByName(String name) {
        try {
            return dao.queryStuByName(name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Student queryBySid(String sid) {
        try {
            boolean fl = dao.queryIsExist(sid);
            if(fl){
                return dao.queryBySid(sid);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Xuanke> queryXuanke(String sid) {
        try {
            return dao.queryXuanke(sid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String changeStu(Student stu) {
        try {
            boolean fl = dao.queryIsExist(stu.getId());
            if(fl){
                dao.changeStu(stu);
                return "ok";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "error";
    }

    @Override
    public List<Course> queryBySpe(String spe) {
        try {
            return dao.queryBySpe(spe);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
