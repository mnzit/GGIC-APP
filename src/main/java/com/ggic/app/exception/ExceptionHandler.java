package com.ggic.app.exception;

import com.ggic.app.builder.ResponseBuilder;
import com.ggic.app.controller.Controller;
import com.ggic.app.response.Response;

import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler {

    public static Response handle(ResponseWrapper responseWrapper) {
        try {
            return responseWrapper.process();
        } catch (Exception ex) {
            return ResponseBuilder.serverError();
        }
    }

    public static void handle(VoidWrapper voidWrapper, HttpServletResponse response) {
        try {
            voidWrapper.process();
        } catch (Exception ex) {
            Controller.buildErrorResponse(response);
        }
    }
}
