package com.servlet;


import com.ann.RequestMapping;
import com.ann.RequestParam;
import com.util.ServletUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myservlet extends HttpServlet {
    //保存方法和类的map集合
    private Map<String, Class> map = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //map集合初始化
        String pkg = "com.controll";
        List<Class> allClass = ServletUtil.getAllClass(pkg);
        //遍历allClass
        for (Class ac : allClass) {
            //获取该类的所有方法
            Method[] mt = ac.getMethods();
            //遍历方法
            for (Method mt1 : mt) {
                //判断是否有RequestMapping注解
                if(mt1.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping rm = mt1.getAnnotation(RequestMapping.class);
                    String aname = rm.value();
                    map.put(aname, ac);
                    System.out.println(aname);
                    System.out.println(map);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //找到对应的类和方法,并且执行该方法url = /xxx.do
        String pt = req.getServletPath();
        //获取方法名 url = xxx
        pt = pt.substring(pt.lastIndexOf("/")+1, pt.lastIndexOf("."));
        System.out.println(pt);
        /*pt = pt.substring(pt.lastIndexOf("/")+1);
        System.out.println(pt);*/
        //获取执行该方法的类型
        Class cls = map.get(pt);
        if(cls == null){
            System.out.println("该请求没有找到对应的方法，请重新定义!");
            return;
        }
        try {
            //获取所有方法对象
            Method[] dms = cls.getDeclaredMethods();
            boolean flag = false;
            //遍历查找要执行的方法
            for (Method dm : dms) {
                if(!dm.isAnnotationPresent(RequestMapping.class)){
                    continue;
                }
                RequestMapping rm = dm.getAnnotation(RequestMapping.class);
                String aname = rm.value();
                if (pt.equals(aname)) { //找到要调用的方法login
                    flag = true;
                    int pp = dm.getParameterCount();
                    Object[] obj = new Object[pp];
                    int index = 0;
                    //获取参数对象
                    Parameter[] pas = dm.getParameters();
                    for (Parameter pa : pas) {
                        //遍历参数,判断是否有RequestParam注解
                        if(pa.isAnnotationPresent(RequestParam.class)){
                            Class pcls = pa.getType();
                            //获取所有属性名
                            Field[] dfs = pcls.getDeclaredFields();
                            //遍历，获取每个属性的内容(网页传值)
                            //给该参数类型创建一个对象
                            Object po = pcls.newInstance();
                            for (Field df : dfs) {
                                //获取属性名
                                String sna = df.getName();
                                //String name = req.getParameter("name");
                                String ps = req.getParameter(sna);
                                //设置权限，可以直接赋值
                                df.setAccessible(true);
                                //给对象属性赋值 u.setName(name);
                                df.set(po, ps);
                            }
                            obj[index++] = po;
                        }else if(pa.getType() == HttpServletRequest.class){
                            obj[index++] = req;
                        }
                    }
                    //调用该方法，并且传参数
                    String ret = (String) dm.invoke(cls.newInstance(),obj);
                    System.out.println("返回===" + ret);
                    if (ret != null) {
                        if (ret.startsWith("redirect:")) {
                            //"redirect:index.jsp";
                            resp.sendRedirect(ret.substring(ret.lastIndexOf(":") + 1));
                            return;
                        } else {
                            //"index.jsp";
                            /*req.getRequestDispatcher(ret).forward(req, resp);*/
                            resp.getWriter().write(ret);
                        }
                    }
                    break;
                }
            }
            if (!flag) {
                System.out.println("未定义该方法" + pt + "请去com.control添加该方法");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("在doPost执行方法有异常");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

