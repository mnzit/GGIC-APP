package com.ggic.app.dao;

import com.ggic.app.model.Student;

import java.util.List;

public interface StudentDao {

    void add(Student student) throws Exception;

    List<Student> getAll() throws Exception;

    Student getById(Long id) throws Exception;
}
