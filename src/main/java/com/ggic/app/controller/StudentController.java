package com.ggic.app.controller;

import com.ggic.app.dao.StudentDaoJdbcTemplateImpl;
import com.ggic.app.dto.Response;
import com.ggic.app.model.Student;
import com.ggic.app.service.StudentService;
import com.ggic.app.service.StudentServiceImpl;
import com.ggic.app.util.JacksonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentController extends Controller {

    private final StudentService studentService = new StudentServiceImpl(new StudentDaoJdbcTemplateImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] split = req.getRequestURL().toString().split("/");
        if (split[split.length - 1].matches("[1-9]\\d*")) {
            getOne(resp, Long.parseLong(split[split.length - 1]));
        } else {
            getAll(resp);
        }
    }

    public void getOne(HttpServletResponse resp, Long id) {
        try {
            Student student = studentService.findById(id);

            Response response = new Response();
            response.setData(student);
            response.setSuccess(true);
            response.setStatusCode(200);
            response.setDescription("Student fetched successfully");

            buildResponse(resp, response);

        } catch (Exception ex) {
            Response response = new Response();
            response.setSuccess(false);
            response.setStatusCode(500);
            response.setDescription("System Error");
            try {
                buildResponse(resp, response);
            } catch (Exception e) {
                System.out.println("Exception:" + ex.getMessage());
            }
        }
    }

    public void getAll(HttpServletResponse resp) {
        try {
            List<Student> students = studentService.findAll();

            Response response = new Response();
            response.setData(students);
            response.setSuccess(true);
            response.setStatusCode(200);
            response.setDescription("Students fetched successfully");

            buildResponse(resp, response);

        } catch (Exception ex) {
            Response response = new Response();
            response.setSuccess(false);
            response.setStatusCode(500);
            response.setDescription("System Error");
            try {
                buildResponse(resp, response);
            } catch (Exception e) {
                System.out.println("Exception:" + ex.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Student student = JacksonUtil.toObject(req.getInputStream(), Student.class);

            studentService.save(student);
            Response response = new Response();
            response.setSuccess(true);
            response.setStatusCode(200);
            response.setDescription("Student saved successfully");
            buildResponse(resp, response);
        } catch (Exception ex) {
            Response response = new Response();
            response.setSuccess(false);
            response.setStatusCode(500);
            response.setDescription("System Error");
            try {
                buildResponse(resp, response);
            } catch (Exception e) {
                System.out.println("Exception:" + ex.getMessage());
            }
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Student student = JacksonUtil.toObject(req.getInputStream(), Student.class);

            studentService.update(student);
            Response response = new Response();
            response.setSuccess(true);
            response.setStatusCode(200);
            response.setDescription("Student updated successfully");
            buildResponse(resp, response);
        } catch (Exception ex) {
            Response response = new Response();
            response.setSuccess(false);
            response.setStatusCode(500);
            response.setDescription("System Error");
            try {
                buildResponse(resp, response);
            } catch (Exception e) {
                System.out.println("Exception:" + ex.getMessage());
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] split = req.getRequestURL().toString().split("/");
            if (split[split.length - 1].matches("[1-9]\\d*")) {
                studentService.delete(Long.parseLong(split[split.length - 1]));
                Response response = new Response();
                response.setSuccess(true);
                response.setStatusCode(200);
                response.setDescription("Student deleted successfully");
                buildResponse(resp, response);
            } else {
                Response response = new Response();
                response.setSuccess(false);
                response.setStatusCode(403);
                response.setDescription("Invalid format");
                buildResponse(resp, response);
            }

        } catch (Exception ex) {
            Response response = new Response();
            response.setSuccess(false);
            response.setStatusCode(500);
            response.setDescription("System Error");
            try {
                buildResponse(resp, response);
            } catch (Exception e) {
                System.out.println("Exception:" + ex.getMessage());
            }
        }
    }
}
