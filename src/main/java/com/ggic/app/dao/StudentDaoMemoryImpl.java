package com.ggic.app.dao;

import com.ggic.app.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDaoMemoryImpl implements StudentDao{

    private List<Student> list = new ArrayList<>();
    private Map<Long, Student> search = new HashMap<>();

    @Override
    public void add(Student student) {
        list.add(student);
        search.put(student.getId(), student);
    }

    @Override
    public List<Student> getAll() {
        return list;
    }

    @Override
    public Student getById(Long id) {
        return search.get(id);
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Long id) {

    }
}
