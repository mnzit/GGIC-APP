package com.ggic.app.controller;

import com.ggic.app.dao.StudentDaoJdbcTemplateImpl;
import com.ggic.app.enums.Action;
import com.ggic.app.model.Student;
import com.ggic.app.service.StudentService;
import com.ggic.app.service.StudentServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class StudentTerminalBasedController {

    private final StudentService studentService = new StudentServiceImpl(new StudentDaoJdbcTemplateImpl());

    public void save() {
        try {
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

        } catch (Exception ex) {
            System.out.println("Saving student failed");
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public void view() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your Student Id");
            Long id = scanner.nextLong();
            Student student = studentService.findById(id);
            System.out.println(student);
        } catch (Exception ex) {
            System.out.println("Fetching student failed");
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public void viewAll() {
        try {
            List<Student> students = studentService.findAll();
            System.out.println(students);
        } catch (Exception ex) {
            System.out.println("Fetching students failed");
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public void update() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your Id");

            Long studentId = scanner.nextLong();
            Student student = studentService.findById(studentId);

            System.out.println("Do you want to update name? (true/false)");
            boolean updateName = scanner.nextBoolean();
            if (updateName) {
                System.out.println("Enter your new name");
                student.setName(scanner.next());
            }
            System.out.println("Do you want to update address? (true/false)");
            boolean updateAddress = scanner.nextBoolean();
            if (updateAddress) {
                System.out.println("Enter your new address");
                student.setAddress(scanner.next());
            }
            System.out.println("Do you want to update contact? (true/false)");
            boolean updateContact = scanner.nextBoolean();
            if (updateContact) {
                System.out.println("Enter your new contact");
                student.setContactNo(scanner.next());
            }
            System.out.println("Do you want to update dob? (true/false)");
            boolean updateDob = scanner.nextBoolean();
            if (updateDob) {
                System.out.println("Enter your new DOB in YYYY-MM-dd");
                student.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next()));
            }
            studentService.update(student);
            System.out.println("Student updated successfully");
        } catch (Exception ex) {
            System.out.println("Updating student failed");
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public void delete() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your Student Id");
            Long id = scanner.nextLong();
            studentService.delete(id);
            System.out.println("Student deleted successfully");
        } catch (Exception ex) {
            System.out.println("Deleting student failed");
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public void process(Action action) {
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
            case UPDATE:
                update();
                break;
            case DELETE:
                delete();
                break;
        }
    }
}
