package com.dao.impl;

import com.dao.StudentDao;
import com.pojo.Course;
import com.pojo.Student;
import com.pojo.Xuanke;
import com.util.DbHelp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 */
public class StudentDaoImpl implements StudentDao {
    QueryRunner qr = new QueryRunner();
    Connection con = DbHelp.getConnection();
    String sql ;
    @Override
    public boolean queryIsExist(String user) throws SQLException {
        sql = "select * from t_stu where id = ?";
        Student stu = qr.query(con, sql, new BeanHandler<>(Student.class), user);
        if(stu != null){
            return true;
        }
        return false;
    }

    @Override
    public List<Student> queryStuByName(String name) throws SQLException {
        sql = "select * from t_stu where name = ?";
        return qr.query(con,sql,new BeanListHandler<>(Student.class),name);
    }

    @Override
    public Student queryBySid(String sid) throws SQLException {
        sql = "select * from t_stu where id = ?";
        return qr.query(con,sql,new BeanHandler<>(Student.class),sid);
    }

    @Override
    public List<Xuanke> queryXuanke(String sid) throws SQLException {
        sql = "select * from t_selcourse where sid = ?";
        return qr.query(con,sql,new BeanListHandler<>(Xuanke.class),sid);
    }

    @Override
    public void changeStu(Student stu) throws SQLException {
        sql = "update t_stu set name = ?,password = ?,sex = ?,speciality = ?,academe = ?,idcard = ?,phone = ? where id = ?";
        Object[] pa = {
                stu.getName(),stu.getPassword(),stu.getSex(),stu.getSpeciality(),stu.getAcademe(),stu.getIdcard(),stu.getPhone(),stu.getId()
        };
        qr.update(con,sql,pa);
    }

    @Override
    public List<Course> queryBySpe(String spe) throws SQLException {
        sql = "select * from t_course where beginspe = ?";
        return qr.query(con,sql,new BeanListHandler<>(Course.class),spe);
    }
}
