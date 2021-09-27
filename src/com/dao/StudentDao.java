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
     * @param user ��ѯѧ���Ƿ����
     * @return
     */
    boolean queryIsExist(String user) throws SQLException;

    /**
     * @param name ͨ��ѧ����������ѧ��
     * @return
     */
    List<Student> queryStuByName(String name) throws SQLException;

    /**
     * @param sid ͨ��ѧ��ѧ�Ų�ѯѧ����Ϣ
     * @return
     */
    Student queryBySid(String sid) throws SQLException;

    /**
     * @param sid
     * @return ѧ��ͨ��ѧ�Ų�ѯ��ѡ�γ̵����
     * @throws SQLException
     */
    List<Xuanke> queryXuanke(String sid) throws SQLException;


    /**
     * @param stu
     * @throws SQLException ͨ��ѧ���޸Ķ�Ӧѧ������Ϣ
     */
    void changeStu(Student stu) throws SQLException;

    /**
     * @param spe ͨ��רҵ����ѯ����רҵ�Ŀγ���Ϣ
     * @return
     */
    List<Course> queryBySpe(String spe) throws SQLException;
}
