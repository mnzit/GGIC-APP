package com.ggic.app.constant;

public interface QueryConstant {

    interface Student {
        String add = "INSERT INTO STUDENTS(NAME,DOB,ADDRESS,CONTACT_NO) VALUES(?,?,?,?)";
        String update = "UPDATE STUDENTS SET NAME=?,DOB=?,ADDRESS=?,CONTACT_NO=? WHERE ID=?";
        String getById = "SELECT * FROM STUDENTS WHERE ID = ?";
        String deleteById = "DELETE FROM STUDENTS WHERE ID = ?";
        String getAll = "SELECT * FROM STUDENTS";
    }
}
