package com.ggic.app.controller;

import com.ggic.app.dao.impl.StudentDaoImpl;
import com.ggic.app.model.Student;
import com.ggic.app.response.Response;
import com.ggic.app.service.StudentService;
import com.ggic.app.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentUIController extends Controller {
    private final StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response = studentService.findAll();
        if(response.getSuccess()){
            req.setAttribute("students", response.getData());
        }else{
            req.setAttribute("failure", response.getDescription());
        }
        req.getRequestDispatcher("/WEB-INF/views/student/index.jsp")
                .forward(req,resp);
    }
}
