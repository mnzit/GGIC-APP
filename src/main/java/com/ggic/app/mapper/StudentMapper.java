package com.ggic.app.mapper;

import com.ggic.app.db.ResultMapper;
import com.ggic.app.model.Student;

import java.sql.ResultSet;

public class StudentMapper implements ResultMapper<Student> {
    @Override
    public Student map(ResultSet resultSet) throws Exception {
        Student student = new Student();
        student.setId(resultSet.getLong("ID"));
        student.setName(resultSet.getString("NAME"));
        student.setDob(resultSet.getDate("DOB"));
        student.setAddress(resultSet.getString("ADDRESS"));
        student.setContactNo(resultSet.getString("CONTACT_NO"));
        return student;
    }
}
