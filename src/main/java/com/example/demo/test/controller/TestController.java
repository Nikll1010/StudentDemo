package com.example.demo.test.controller;

import com.example.demo.nikll.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/show")
    @ResponseBody
    public Object test() {
        return "hhdh";
    }

    @RequestMapping("/hahahah")
    public String aaa(Model model) {
        model.addAttribute("name", "index")
                .addAttribute("hello", "fdsafdas");
        return "index";

    }

    @Autowired
    private StudentService studentService;

    @RequestMapping("id")
    @ResponseBody
    public Object test(Integer id) {
        return studentService.getStudent(id);
    }

}
