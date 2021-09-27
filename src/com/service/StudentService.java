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
     * ͨ��ѧ����������ѧ��
     * @return
     */
    List<Student> queryStuByName(String name) ;

    /**
     * @param sid
     * ͨ��ѧ��ѧ�Ų�ѯѧ����Ϣ
     * @return
     */
    Student queryBySid(String sid) ;

    /**
     * @param sid
     * ѧ��ͨ��ѧ�Ų�ѯ��ѡ�γ̵����
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
     * ͨ��רҵ����ѯ����רҵ�Ŀγ���Ϣ
     * @return
     */
    List<Course> queryBySpe(String spe);
}
