package com.ggic.app.service;

import com.ggic.app.dao.StudentDao;
import com.ggic.app.model.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService{

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void save(Student student) throws Exception{
        if (student != null) {
            studentDao.add(student);
        }else{
            throw new RuntimeException("Cannot save null value");
        }
    }
    @Override
    public Student findById(Long id) throws Exception{
        Student student = studentDao.getById(id);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        return student;
    }
    @Override
    public List<Student> findAll() throws Exception{
        List<Student> list = studentDao.getAll();
        if(list != null && !list.isEmpty()){
            return list;
        }else{
            throw new RuntimeException("Students not found");
        }
    }
    @Override
    public void update(Student student) throws Exception{
        if (student != null) {
            studentDao.update(student);
        }else{
            throw new RuntimeException("Cannot update null value");
        }
    }
    @Override
    public void delete(Long id) throws Exception{
        studentDao.delete(id);
    }

}
