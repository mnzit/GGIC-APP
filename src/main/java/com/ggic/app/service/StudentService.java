package com.ggic.app.service;

import com.ggic.app.dao.StudentDao;
import com.ggic.app.model.Student;

import java.util.List;

public class StudentService {

    private StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void save(Student student) throws Exception{

        if (student != null) {
            studentDao.add(student);
        }else{
            throw new RuntimeException("Cannot save null value");
        }
    }

    public Student findById(Long id) throws Exception{
        Student student = studentDao.getById(id);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        return student;
    }

    public List<Student> findAll() throws Exception{
        List<Student> list = studentDao.getAll();
        if(list != null && !list.isEmpty()){
            return list;
        }else{
            throw new RuntimeException("Students not found");
        }
    }
}
