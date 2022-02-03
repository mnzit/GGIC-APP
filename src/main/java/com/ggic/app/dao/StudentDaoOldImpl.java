package com.ggic.app.dao;

import com.ggic.app.constant.QueryConstant;
import com.ggic.app.db.DatabaseHelper;
import com.ggic.app.mapper.StudentMapper;
import com.ggic.app.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoOldImpl implements StudentDao {

    private final DatabaseHelper databaseHelper = new DatabaseHelper();

    @Override
    public void add(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GGIC";
            String username = "root";
            String password = "Root@12345";
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(QueryConstant.Student.add);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, new Date(student.getDob().getTime()));
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getContactNo());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) throw new RuntimeException("Student create failed");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new RuntimeException("Database error occurred");
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
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
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GGIC";
            String username = "root";
            String password = "Root@12345";
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(QueryConstant.Student.getAll);
            ResultSet resultSet = preparedStatement.getResultSet();
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
            throw new RuntimeException("Database error occurred");
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
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
    public Student getById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GGIC";
            String username = "root";
            String password = "Root@12345";
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(QueryConstant.Student.getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("ID"));
                student.setName(resultSet.getString("NAME"));
                student.setDob(resultSet.getDate("DOB"));
                student.setAddress(resultSet.getString("ADDRESS"));
                student.setContactNo(resultSet.getString("CONTACT_NO"));
                return student;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new RuntimeException("Database error occurred");
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
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
    public void update(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GGIC";
            String username = "root";
            String password = "Root@12345";
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(QueryConstant.Student.update);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, new Date(student.getDob().getTime()));
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getContactNo());
            preparedStatement.setLong(5, student.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) throw new RuntimeException("Student update failed");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new RuntimeException("Database error occurred");
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
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
    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GGIC";
            String username = "root";
            String password = "Root@12345";
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(QueryConstant.Student.deleteById);
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) throw new RuntimeException("Student delete failed");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new RuntimeException("Database error occurred");
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }
}