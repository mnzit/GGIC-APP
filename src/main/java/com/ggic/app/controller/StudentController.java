package com.ggic.app.controller;

import com.ggic.app.dao.StudentDatabase;
import com.ggic.app.enums.Action;
import com.ggic.app.model.Student;
import com.ggic.app.service.StudentService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class StudentController {

    private StudentService studentService = new StudentService(new StudentDatabase());
    private Student student = null;
    private List<Student> students = null;
    private Long id;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void save() throws Exception {
        Scanner scanner = new Scanner(System.in);

        Student student = new Student();

        System.out.println("Enter your Id");
        student.setId(scanner.nextLong());
        System.out.println("Enter your Name");
        student.setName(scanner.next());
        System.out.println("Enter your Address");
        student.setAddress(scanner.next());
        System.out.println("Enter your Contact");
        student.setContactNo(scanner.next());
        System.out.println("Enter your DOB in YYYY-MM-dd");
        student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next()));

        studentService.save(student);
        System.out.println("Student saved successfully");
    }

    public void view() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Student Id");
        id = scanner.nextLong();
        Student student = studentService.findById(id);
        System.out.println(student);
    }

    public void viewAll() throws Exception{
        List<Student> students = studentService.findAll();
        System.out.println(students);
    }

    public void process(Action action) {
        try {
            switch (action) {
                case SAVE:
                    save();
                    break;
                case VIEW:
                    view();
                    break;
                case VIEW_ALL:
                    viewAll();
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
