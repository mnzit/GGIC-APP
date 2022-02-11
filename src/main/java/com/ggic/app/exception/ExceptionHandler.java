package com.ggic.app.exception;

import com.ggic.app.builder.ResponseBuilder;
import com.ggic.app.controller.Controller;
import com.ggic.app.response.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler {

    public static Response handle(ResponseWrapper responseWrapper) {
        try {
            return responseWrapper.process();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return ResponseBuilder.serverError();
        }
    }

    public static void handle(VoidWrapper voidWrapper, HttpServletResponse response) {
        try {
            voidWrapper.process();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            Controller.buildErrorResponse(response);
        }
    }

    public static void handle(VoidWrapper voidWrapper, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            voidWrapper.process();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
//            Controller.buildErrorResponse(httpServletResponse);
            Controller.view(httpServletRequest, httpServletResponse, "error/error");
        }
    }
}
