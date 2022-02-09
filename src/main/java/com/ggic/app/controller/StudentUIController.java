package com.ggic.app.controller;

import com.ggic.app.dao.impl.StudentDaoImpl;
import com.ggic.app.model.Student;
import com.ggic.app.request.StudentSaveRequest;
import com.ggic.app.request.StudentUpdateRequest;
import com.ggic.app.response.Response;
import com.ggic.app.service.StudentService;
import com.ggic.app.service.impl.StudentServiceImpl;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

public class StudentUIController extends Controller {
    private final StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String uri = httpServletRequest.getRequestURI().toLowerCase();
        System.out.println(uri);
        if (uri.contains("delete")) {
            delete(httpServletRequest, httpServletResponse);
        } else if (uri.contains("edit")) {
            detail(httpServletRequest, httpServletResponse, "edit");
        } else if (uri.contains("detail")) {
            detail(httpServletRequest, httpServletResponse, "detail");
        } else if (uri.contains("save")) {
            httpServletRequest.getRequestDispatcher("/WEB-INF/views/student/save.jsp")
                    .forward(httpServletRequest, httpServletResponse);
        } else {
            findAll(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        String id = httpServletRequest.getParameter("id");
        String name = httpServletRequest.getParameter("name");
        String address = httpServletRequest.getParameter("address");
        String contactNo = httpServletRequest.getParameter("contactNo");
        Date dob = Date.valueOf(httpServletRequest.getParameter("dob"));
        Response response = null;
        if (id == null) {
            StudentSaveRequest studentSaveRequest = new StudentSaveRequest();
            studentSaveRequest.setAddress(address);
            studentSaveRequest.setContactNo(contactNo);
            studentSaveRequest.setDob(dob);
            studentSaveRequest.setName(name);
            response = studentService.save(studentSaveRequest);
        } else {
            StudentUpdateRequest request = new StudentUpdateRequest();
            request.setId(Long.parseLong(id));
            request.setAddress(address);
            request.setContactNo(contactNo);
            request.setDob(dob);
            request.setName(name);
            response = studentService.update(request);
        }
        httpServletRequest.setAttribute("success", response.getSuccess());
        httpServletRequest.setAttribute("message", response.getDescription());
        httpServletResponse.sendRedirect("/students/");
    }

    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Response response = studentService.findAll();
        httpServletRequest.setAttribute("success", response.getSuccess());
        httpServletRequest.setAttribute("message", response.getDescription());
        if (response.getSuccess()) {
            httpServletRequest.setAttribute("students", response.getData());
        }
        httpServletRequest.getRequestDispatcher("/WEB-INF/views/student/index.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }

    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURL().toString();
        String[] urlParts = url.split("/");
        boolean isNumber = NumberUtils.isDigits(urlParts[urlParts.length - 1]);
        if (isNumber) {
            Response response = studentService.delete(Long.parseLong(urlParts[urlParts.length - 1]));
            httpServletRequest.setAttribute("success", response.getSuccess());
            httpServletRequest.setAttribute("message", response.getDescription());
        }
        httpServletResponse.sendRedirect("/students/");
    }

    public void detail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String view) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURL().toString();
        String[] urlParts = url.split("/");
        boolean isNumber = NumberUtils.isDigits(urlParts[urlParts.length - 1]);
        if (isNumber) {
            Response response = studentService.findById(Long.parseLong(urlParts[urlParts.length - 1]));
            httpServletRequest.setAttribute("success", response.getSuccess());
            httpServletRequest.setAttribute("message", response.getDescription());
            if (response.getSuccess()) {
                httpServletRequest.setAttribute("student", response.getData());
            }
        }
        httpServletRequest.getRequestDispatcher(String.format("/WEB-INF/views/student/%s.jsp", view))
                .forward(httpServletRequest, httpServletResponse);
    }

}
