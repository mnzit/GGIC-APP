package com.ggic.app.dao;

import com.ggic.app.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase implements StudentDao {
    @Override
    public void add(Student student) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GGIC";
            String username = "root";
            String password = "Root@12345";
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            String sql = "INSERT INTO STUDENTS(NAME,DOB,ADDRESS,CONTACT_NO) VALUES('%s', '%s', '%s', '%s')";
            sql = String.format(sql, student.getName(), new Date(student.getDob().getTime()), student.getAddress(), student.getContactNo());
            statement.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new RuntimeException("Database error occured");
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }

    @Override
    public List<Student> getAll() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GGIC";
            String username = "root";
            String password = "Root@12345";
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTS");
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("ID"));
                student.setName(resultSet.getString("NAME"));
                student.setDob(resultSet.getDate("DOB"));
                student.setAddress(resultSet.getString("ADDRESS"));
                student.setContactNo(resultSet.getString("CONTACT_NO"));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public Student getById(Long id) {
        return null;
    }
}
