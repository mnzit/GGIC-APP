package com.ggic.app.controller;

import com.ggic.app.dao.StudentDaoImpl;
import com.ggic.app.exception.ExceptionHandler;
import com.ggic.app.request.StudentSaveRequest;
import com.ggic.app.request.StudentUpdateRequest;
import com.ggic.app.response.Response;
import com.ggic.app.service.StudentService;
import com.ggic.app.service.StudentServiceImpl;
import com.ggic.app.util.JacksonUtil;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentController extends Controller {

    private final StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());

    public void getAll(HttpServletResponse httpServletResponse) {
        ExceptionHandler.handle(() -> {
            Response response = studentService.findAll();
            buildResponse(httpServletResponse, response);
        }, httpServletResponse);
    }

    public void getOne(HttpServletResponse httpServletResponse, Long id) {
        ExceptionHandler.handle(() -> {
            Response response = studentService.findById(id);
            buildResponse(httpServletResponse, response);
        }, httpServletResponse);
    }

    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ExceptionHandler.handle(() -> {
            StudentSaveRequest request = JacksonUtil.toObject(httpServletRequest.getInputStream(), StudentSaveRequest.class);
            Response response = studentService.save(request);
            buildResponse(httpServletResponse, response);
        }, httpServletResponse);
    }

    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ExceptionHandler.handle(() -> {
            StudentUpdateRequest request = JacksonUtil.toObject(httpServletRequest.getInputStream(), StudentUpdateRequest.class);
            Response response = studentService.update(request);
            buildResponse(httpServletResponse, response);
        }, httpServletResponse);
    }

    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ExceptionHandler.handle(() -> {
            String url = httpServletRequest.getRequestURL().toString();
            String[] urlParts = url.split("/");
            boolean isNumber = NumberUtils.isDigits(urlParts[urlParts.length - 1]);
            if (isNumber) {
                Response response = studentService.delete(Long.parseLong(urlParts[urlParts.length - 1]));
                buildResponse(httpServletResponse, response);
            } else {
                notAValidRequest(httpServletResponse);
            }
        }, httpServletResponse);
    }

    /**
     * Contains logics because we have to handle both getAll and getById
     *
     * @param httpServletRequest
     * @param httpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ExceptionHandler.handle(() -> {
            String url = httpServletRequest.getRequestURL().toString();
            String[] urlParts = url.split("/");
            boolean isGetAll = urlParts[urlParts.length - 1].endsWith("students");
            boolean isGetOne = NumberUtils.isDigits(urlParts[urlParts.length - 1]);
            if (isGetAll) {
                getAll(httpServletResponse);
            } else if (isGetOne) {
                getOne(httpServletResponse, Long.parseLong(urlParts[urlParts.length - 1]));
            } else {
                notAValidRequest(httpServletResponse);
            }
        }, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        save(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        update(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        delete(httpServletRequest, httpServletResponse);
    }
}
