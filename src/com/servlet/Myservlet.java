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
    //���淽�������map����
    private Map<String, Class> map = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //map���ϳ�ʼ��
        String pkg = "com.controll";
        List<Class> allClass = ServletUtil.getAllClass(pkg);
        //����allClass
        for (Class ac : allClass) {
            //��ȡ��������з���
            Method[] mt = ac.getMethods();
            //��������
            for (Method mt1 : mt) {
                //�ж��Ƿ���RequestMappingע��
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
        //�ҵ���Ӧ����ͷ���,����ִ�и÷���url = /xxx.do
        String pt = req.getServletPath();
        //��ȡ������ url = xxx
        pt = pt.substring(pt.lastIndexOf("/")+1, pt.lastIndexOf("."));
        System.out.println(pt);
        /*pt = pt.substring(pt.lastIndexOf("/")+1);
        System.out.println(pt);*/
        //��ȡִ�и÷���������
        Class cls = map.get(pt);
        if(cls == null){
            System.out.println("������û���ҵ���Ӧ�ķ����������¶���!");
            return;
        }
        try {
            //��ȡ���з�������
            Method[] dms = cls.getDeclaredMethods();
            boolean flag = false;
            //��������Ҫִ�еķ���
            for (Method dm : dms) {
                if(!dm.isAnnotationPresent(RequestMapping.class)){
                    continue;
                }
                RequestMapping rm = dm.getAnnotation(RequestMapping.class);
                String aname = rm.value();
                if (pt.equals(aname)) { //�ҵ�Ҫ���õķ���login
                    flag = true;
                    int pp = dm.getParameterCount();
                    Object[] obj = new Object[pp];
                    int index = 0;
                    //��ȡ��������
                    Parameter[] pas = dm.getParameters();
                    for (Parameter pa : pas) {
                        //��������,�ж��Ƿ���RequestParamע��
                        if(pa.isAnnotationPresent(RequestParam.class)){
                            Class pcls = pa.getType();
                            //��ȡ����������
                            Field[] dfs = pcls.getDeclaredFields();
                            //��������ȡÿ�����Ե�����(��ҳ��ֵ)
                            //���ò������ʹ���һ������
                            Object po = pcls.newInstance();
                            for (Field df : dfs) {
                                //��ȡ������
                                String sna = df.getName();
                                //String name = req.getParameter("name");
                                String ps = req.getParameter(sna);
                                //����Ȩ�ޣ�����ֱ�Ӹ�ֵ
                                df.setAccessible(true);
                                //���������Ը�ֵ u.setName(name);
                                df.set(po, ps);
                            }
                            obj[index++] = po;
                        }else if(pa.getType() == HttpServletRequest.class){
                            obj[index++] = req;
                        }
                    }
                    //���ø÷��������Ҵ�����
                    String ret = (String) dm.invoke(cls.newInstance(),obj);
                    System.out.println("����===" + ret);
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
                System.out.println("δ����÷���" + pt + "��ȥcom.control��Ӹ÷���");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("��doPostִ�з������쳣");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

