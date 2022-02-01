package com.ggic.app.constant;

public interface QueryConstant {

    interface Student {
        String add = "INSERT INTO STUDENTS(NAME,DOB,ADDRESS,CONTACT_NO) VALUES(?,?,?,?)";
        String getById = "SELECT * FROM STUDENTS WHERE ID = ?";
        String getAll = "SELECT * FROM STUDENTS";
    }
}
