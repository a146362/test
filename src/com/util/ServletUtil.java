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
     * @param pkg 根据包名，获取该包下的所有类的类型
     * @return
     */
    public static List<Class> getAllClass(String pkg) {
        List<Class> list1 = new ArrayList<>();
        //找到pkg的绝对路径
        String pk = pkg.replaceAll("\\.", "/");
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(pk);
            //遍历找到的地址
            while (urls.hasMoreElements()) {
                //获取绝对路径
                String path = urls.nextElement().getPath();
                //获取该路径下的所有文件
                File fl = new File(path);
                String[] name = fl.list();
                for (String s : name) {
                    //获得类名
                    String cname = s.substring(0, s.lastIndexOf("."));
                    Class cls = Class.forName(pkg + "." + cname);
                    //判断是否有相应的注解
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
