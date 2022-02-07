package com.ggic.app.dao;

import com.ggic.app.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    int add(Student student);

    Optional<List<Student>> getAll();

    Optional<Student> getById(Long id);

    int update(Student student);

    int delete(Long id);
}
