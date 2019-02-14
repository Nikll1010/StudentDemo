package com.example.demo.nikll.serviceimpl;

import com.example.demo.nikll.dao.StudentDao;
import com.example.demo.nikll.entity.Student;
import com.example.demo.nikll.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/12/18 15:48
 * @Description:
 */

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    public StudentServiceImpl(){
        System.out.println("service 层的方法调用执行");
    }

    public int getTotal() {
        return studentDao.getTotal();
    }

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }

    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    public Student getStudent(int id) {
        return studentDao.getStudent(id);
    }

    public List<Student> list(int start, int count) {
        return studentDao.list(start, count);
    }


}
