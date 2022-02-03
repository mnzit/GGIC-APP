package com.ggic.app.dao;

import com.ggic.app.constant.QueryConstant;
import com.ggic.app.db.JdbcTemplate;
import com.ggic.app.mapper.StudentMapper;
import com.ggic.app.model.Student;

import java.sql.Date;
import java.util.List;

public class StudentDaoDatabaseImpl implements StudentDao {

    private final JdbcTemplate<Student> studentJdbcTemplate = new JdbcTemplate<>();

    @Override
    public void add(Student student) {
        int rowsAffected = studentJdbcTemplate
                .update(
                        QueryConstant.Student.add,
                        new Object[]{
                                student.getName(),
                                new Date(student.getDob().getTime()),
                                student.getAddress(),
                                student.getContactNo()
                        }
                );
        if (rowsAffected <= 0) throw new RuntimeException("Student creation failed");
    }

    @Override
    public List<Student> getAll() {
        return studentJdbcTemplate
                .getAll(
                        QueryConstant.Student.getAll,
                        new StudentMapper()
                );
    }

    @Override
    public Student getById(Long id) {
        Student student = studentJdbcTemplate
                .getOneByObject(
                        QueryConstant.Student.getById,
                        new StudentMapper(),
                        new Object[]{id}
                );
        if (student == null) throw new RuntimeException("Student not found");
        return student;
    }

    @Override
    public void update(Student student) {
        int rowsAffected = studentJdbcTemplate
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
        if (rowsAffected <= 0) throw new RuntimeException("Student update failed");
    }

    @Override
    public void delete(Long id) {
        int rowsAffected = studentJdbcTemplate
                .update(
                        QueryConstant.Student.deleteById,
                        new Object[]{id}
                );
        if (rowsAffected <= 0) throw new RuntimeException("Student delete failed");
    }
}
