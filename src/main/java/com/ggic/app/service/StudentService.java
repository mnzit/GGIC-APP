package com.ggic.app.service;

import com.ggic.app.request.StudentSaveRequest;
import com.ggic.app.request.StudentUpdateRequest;
import com.ggic.app.response.Response;
import com.ggic.app.model.Student;

public interface StudentService {

    Response save(StudentSaveRequest request);

    Response findById(Long id);

    Response findAll();

    Response update(StudentUpdateRequest request);

    Response delete(Long id);

}
