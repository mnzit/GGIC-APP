package com.ggic.app.controller;

import com.ggic.app.dao.impl.StudentDaoImpl;
import com.ggic.app.exception.ExceptionHandler;
import com.ggic.app.model.Student;
import com.ggic.app.request.StudentSaveRequest;
import com.ggic.app.request.StudentUpdateRequest;
import com.ggic.app.response.Response;
import com.ggic.app.service.StudentService;
import com.ggic.app.service.impl.StudentServiceImpl;
import com.ggic.app.util.LogUtil;
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
        ExceptionHandler.handle(() -> {
            String uri = httpServletRequest.getRequestURI().toLowerCase();
            if (uri.contains("delete")) {
                delete(httpServletRequest, httpServletResponse);
            } else if (uri.contains("edit")) {
                detail(httpServletRequest, httpServletResponse, "edit");
            } else if (uri.contains("detail")) {
                detail(httpServletRequest, httpServletResponse, "detail");
            } else if (uri.contains("save")) {
                view(httpServletRequest, httpServletResponse, "students/save");
            } else {
                findAll(httpServletRequest, httpServletResponse);
            }
        }, httpServletRequest, httpServletResponse);
    }

    /**
     * This post method handles for both update and save cases
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ExceptionHandler.handle(() -> {
            String id = httpServletRequest.getParameter("id");
            String name = httpServletRequest.getParameter("name");
            String address = httpServletRequest.getParameter("address");
            String contactNo = httpServletRequest.getParameter("contactNo");
            String dob = httpServletRequest.getParameter("dob");
            Date date = Date.valueOf(dob);
            System.out.println("isSave: " + id == null);
            System.out.println("isUpdate: " + id != null);
            Response response = null;
            if (id == null) {
                StudentSaveRequest studentSaveRequest = new StudentSaveRequest();
                studentSaveRequest.setAddress(address);
                studentSaveRequest.setContactNo(contactNo);
                studentSaveRequest.setDob(date);
                studentSaveRequest.setName(name);
                response = studentService.save(studentSaveRequest);
                LogUtil.responseLogger(response, "save");

            } else {
                StudentUpdateRequest request = new StudentUpdateRequest();
                request.setId(Long.parseLong(id));
                request.setAddress(address);
                request.setContactNo(contactNo);
                request.setDob(date);
                request.setName(name);
                response = studentService.update(request);
                LogUtil.responseLogger(response, "update");
            }

            httpServletRequest.setAttribute("success", response.getSuccess());
            httpServletRequest.setAttribute("message", response.getDescription());

            redirect(httpServletResponse, "/students");
        }, httpServletRequest, httpServletResponse);
    }

    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Response response = studentService.findAll();
        LogUtil.responseLogger(response, "find all");
        httpServletRequest.setAttribute("success", response.getSuccess());
        httpServletRequest.setAttribute("message", response.getDescription());
        if (response.getSuccess()) {
            httpServletRequest.setAttribute("students", response.getData());
        }
        view(httpServletRequest, httpServletResponse, "students/index");
    }

    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURL().toString();
        String[] urlParts = url.split("/");
        boolean isNumber = NumberUtils.isDigits(urlParts[urlParts.length - 1]);
        if (isNumber) {
            Response response = studentService.delete(Long.parseLong(urlParts[urlParts.length - 1]));
            LogUtil.responseLogger(response, "delete");
            httpServletRequest.setAttribute("success", response.getSuccess());
            httpServletRequest.setAttribute("message", response.getDescription());
        }
        redirect(httpServletResponse, "/students");
    }

    public void detail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String view) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURL().toString();
        String[] urlParts = url.split("/");
        boolean isNumber = NumberUtils.isDigits(urlParts[urlParts.length - 1]);
        if (isNumber) {
            Response response = studentService.findById(Long.parseLong(urlParts[urlParts.length - 1]));
            LogUtil.responseLogger(response, "detail");
            httpServletRequest.setAttribute("success", response.getSuccess());
            httpServletRequest.setAttribute("message", response.getDescription());
            if (response.getSuccess()) {
                httpServletRequest.setAttribute("student", response.getData());
            }
        }
        view(httpServletRequest, httpServletResponse, String.format("students/%s", view));
    }
}
