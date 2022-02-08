package com.ggic.app.dao.impl;

import com.ggic.app.constant.QueryConstant;
import com.ggic.app.dao.StudentDao;
import com.ggic.app.db.JdbcTemplate;
import com.ggic.app.mapper.StudentMapper;
import com.ggic.app.model.Student;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    @Override
    public int add(Student student) {
        return new JdbcTemplate<Student>()
                .update(
                        QueryConstant.Student.add,
                        new Object[]{
                                student.getName(),
                                new Date(student.getDob().getTime()),
                                student.getAddress(),
                                student.getContactNo()
                        }
                );
    }

    @Override
    public Optional<List<Student>> getAll() {
        return new JdbcTemplate<Student>()
                .getAllOptional(
                        QueryConstant.Student.getAll,
                        new StudentMapper()
                );
    }

    @Override
    public Optional<Student> getById(Long id) {
        return new JdbcTemplate<Student>()
                .getOptionalOneByObject(
                        QueryConstant.Student.getById,
                        new StudentMapper(),
                        new Object[]{id}
                );
    }

    @Override
    public int update(Student student) {
        return new JdbcTemplate<Student>()
                .update(
                        QueryConstant.Student.update,
                        new Object[]
                                {
                                        student.getName(),
                                        new Date(student.getDob().getTime()),
                                        student.getAddress(),
                                        student.getContactNo(),
                                        student.getId()
                                }
                );
    }

    @Override
    public int delete(Long id) {
        return new JdbcTemplate<Student>()
                .update(
                        QueryConstant.Student.deleteById,
                        new Object[]{id}
                );
    }
}
