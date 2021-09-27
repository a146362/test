package com.controll;

import com.ann.Controller;
import com.ann.RequestMapping;
import com.ann.RequestParam;
import com.pojo.Student;
import com.service.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Controller
public class StudentControll {
    StudentServiceImpl service = new StudentServiceImpl();
    @RequestMapping("lg")
    public String login(@RequestParam Student stu, HttpServletRequest req){
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        Student st = service.queryBySid(name);
        if(st != null){
            return "ok";
        }
        return "error";
    }
}
