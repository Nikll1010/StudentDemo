package com.example.demo.nikll.controller;


import com.example.demo.nikll.entity.Student;
import com.example.demo.nikll.service.StudentService;
import com.example.demo.nikll.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Student 控制器
 *
 * @author: @我没有三颗心脏
 * @create: 2018-04-23-下午 13:27
 */
@Controller
public class StudentController {
    public StudentController(){
        System.out.println("Controller 层的方法调用执行");
    }
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    String name = "jiangbei";

    @Autowired
    private StudentService studentService;

    @RequestMapping("/name")
    public String hello(Model model) {
        model.addAttribute("name", name);
        return "listStudent";
    }

//    @Autowired
//    Student student;
    @RequestMapping("/addStudent")
    public String addStudent(HttpServletRequest request, HttpServletResponse response) {

        Student student = new Student();

        int studentID = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        Date birthday = null;
        // String 类型按照 yyyy-MM-dd 的格式转换为 java.util.Date 类
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        student.setStudent_id(studentID);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setBirthday(birthday);

        studentService.addStudent(student);

        return "listStudent";
    }

    @RequestMapping("/listStudent")
    public String listStudent(HttpServletRequest request, HttpServletResponse response) {

        // 获取分页参数
        int start = 0;
        int count = 3;

        try {
            start = Integer.parseInt(request.getParameter("page.start"));
            count = Integer.parseInt(request.getParameter("page.count"));
        } catch (Exception e) {
        }

        Page page = new Page(start, count);

        List<Student> students = studentService.list(page.getStart(),page.getCount());
        int total = studentService.getTotal();
//        System.out.println(total);
        page.setTotal(total);

        request.setAttribute("students", students);
//        System.out.println(students);
        request.setAttribute("page", page);
        request.setAttribute("name", name);
//
        return "listStudent";
    }

    @RequestMapping("/deleteStudent")
    public String deleteStudent(int id) {
        studentService.deleteStudent(id);
        return "listStudent";
    }

    @RequestMapping("/editStudent")
    public ModelAndView editStudent(int id) {
        ModelAndView mav = new ModelAndView("editStudent");
        Student student = studentService.getStudent(id);
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping("/updateStudent")
    public String updateStudent(HttpServletRequest request, HttpServletResponse response) {

        Student student = new Student();

        int id = Integer.parseInt(request.getParameter("id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = simpleDateFormat.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        student.setId(id);
        student.setStudent_id(student_id);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setBirthday(birthday);

        studentService.updateStudent(student);
        return "listStudent";
    }
}


