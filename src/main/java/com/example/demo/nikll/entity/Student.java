package com.example.demo.nikll.entity;



import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
public class Student {

    private int id;
    private int student_id;
    private String name;
    private int age;
    private String sex;
    private Date birthday;

}
