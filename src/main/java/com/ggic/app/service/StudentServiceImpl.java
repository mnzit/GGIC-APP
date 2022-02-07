package com.ggic.app.service;

import com.ggic.app.builder.ResponseBuilder;
import com.ggic.app.dao.StudentDao;
import com.ggic.app.request.StudentSaveRequest;
import com.ggic.app.request.StudentUpdateRequest;
import com.ggic.app.response.Response;
import com.ggic.app.exception.ExceptionHandler;
import com.ggic.app.model.Student;
import com.ggic.app.util.MappingUtil;
import com.ggic.app.util.ValidationUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.awt.print.Book;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Response save(StudentSaveRequest request) {
        return ExceptionHandler.handle(() -> {
            Optional<Response> validationResponse = ValidationUtil.validate(request);
            if (validationResponse.isPresent()) {
                return validationResponse.get();
            } else {
                Student student = MappingUtil.map(request, Student.class);
                if (studentDao.add(student) <= 0) {
                    return ResponseBuilder.failure("Student creation failed", HttpServletResponse.SC_NOT_MODIFIED);
                } else {
                    return ResponseBuilder.success("Student saved successfully", HttpServletResponse.SC_CREATED);
                }
            }
        });
    }

    @Override
    public Response findById(Long id) {
        return ExceptionHandler.handle(() -> {
            Optional<Student> optionalStudent = studentDao.getById(id);
            return optionalStudent
                    .map(student -> ResponseBuilder.success("Student fetched successfully", student))
                    .orElseGet(() -> ResponseBuilder.failure("Student not found", HttpServletResponse.SC_NOT_FOUND));
        });
    }

    @Override
    public Response findAll() {
        return ExceptionHandler.handle(() -> {
            Optional<List<Student>> optionalStudents = studentDao.getAll();
            if (optionalStudents.isPresent() && !optionalStudents.get().isEmpty()) {
                return ResponseBuilder.success("Students fetched successfully", optionalStudents.get());
            } else {
                return ResponseBuilder.failure("Students not found", HttpServletResponse.SC_NOT_FOUND);
            }
        });
    }

    @Override
    public Response update(StudentUpdateRequest request) {
        return ExceptionHandler.handle(() -> {
            Optional<Response> validationResponse = ValidationUtil.validate(request);
            if (validationResponse.isPresent()) {
                return validationResponse.get();
            } else {
                Student student = MappingUtil.map(request, Student.class);
                if (studentDao.update(student) <= 0) {
                    return ResponseBuilder.failure("Student update failed", HttpServletResponse.SC_NOT_MODIFIED);
                } else {
                    return ResponseBuilder.success("Student updated successfully");
                }
            }
        });
    }

    @Override
    public Response delete(Long id) {
        return ExceptionHandler.handle(() -> {
            if (studentDao.delete(id) <= 0) {
                return ResponseBuilder.failure("Student delete failed", HttpServletResponse.SC_NOT_MODIFIED);
            } else {
                return ResponseBuilder.success("Student deleted successfully");
            }
        });
    }

}
