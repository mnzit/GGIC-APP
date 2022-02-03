package com.ggic.app.service;

import com.ggic.app.model.Student;

import java.util.List;

public interface StudentService {

    void save(Student student) throws Exception;

    Student findById(Long id) throws Exception;

    List<Student> findAll() throws Exception;

    void update(Student student) throws Exception;

    void delete(Long id) throws Exception;

}
