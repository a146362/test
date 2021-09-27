package com.util;



import com.ann.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ServletUtil {
    /**
     * @param pkg ���ݰ�������ȡ�ð��µ������������
     * @return
     */
    public static List<Class> getAllClass(String pkg) {
        List<Class> list1 = new ArrayList<>();
        //�ҵ�pkg�ľ���·��
        String pk = pkg.replaceAll("\\.", "/");
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(pk);
            //�����ҵ��ĵ�ַ
            while (urls.hasMoreElements()) {
                //��ȡ����·��
                String path = urls.nextElement().getPath();
                //��ȡ��·���µ������ļ�
                File fl = new File(path);
                String[] name = fl.list();
                for (String s : name) {
                    //�������
                    String cname = s.substring(0, s.lastIndexOf("."));
                    Class cls = Class.forName(pkg + "." + cname);
                    //�ж��Ƿ�����Ӧ��ע��
                    if(cls.isAnnotationPresent(Controller.class)){
                        list1.add(cls);
                    }
                }
                return list1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
