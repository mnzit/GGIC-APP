package com.ggic.app.dao;

import com.ggic.app.constant.QueryConstant;
import com.ggic.app.db.DatabaseHelper;
import com.ggic.app.mapper.StudentMapper;
import com.ggic.app.model.Student;

import java.sql.*;
import java.util.List;

public class StudentDatabase implements StudentDao {

    private final DatabaseHelper databaseHelper = new DatabaseHelper();

    @Override
    public void add(Student student) {
        try {
            databaseHelper.connect();
            databaseHelper.initialize(QueryConstant.Student.add, (preparedStatement) -> {
                preparedStatement.setString(1, student.getName());
                preparedStatement.setDate(2, new Date(student.getDob().getTime()));
                preparedStatement.setString(3, student.getAddress());
                preparedStatement.setString(4, student.getContactNo());
            });
            int rowsAffected = databaseHelper.update();
            if (!(rowsAffected > 0)) {
                throw new RuntimeException("Saving student failed");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new RuntimeException("Database error occurred");
        } finally {
            try {
                databaseHelper.close();
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }

    @Override
    public List<Student> getAll() {
        try {
            databaseHelper.connect();
            databaseHelper.initialize(QueryConstant.Student.getAll);
            List<Student> students = databaseHelper.fetchMultiple(new StudentMapper(), Student.class);
            return students;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                databaseHelper.close();
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public Student getById(Long id) {
        try {
            databaseHelper.connect();
            databaseHelper.initialize(QueryConstant.Student.getById, (preparedStatement) -> {
                preparedStatement.setLong(1, id);
            });
            Student student = databaseHelper.fetchOne(new StudentMapper(), Student.class);
            if (student == null) {
                throw new RuntimeException("Student not found");
            }else{
                return student;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                databaseHelper.close();
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Long id) {

    }
}
