package com.ggic.app.controller;

import com.ggic.app.builder.ResponseBuilder;
import com.ggic.app.response.Response;
import com.ggic.app.util.JacksonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public abstract class Controller extends HttpServlet {

    public static void buildResponse(HttpServletResponse httpServletResponse, Response response) {
        try {
            final PrintWriter printWriter = httpServletResponse.getWriter();
            httpServletResponse.setStatus(response.getStatusCode());
            httpServletResponse.setContentType("application/json");
            printWriter.write(JacksonUtil.toJson(response));
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public static void buildErrorResponse(HttpServletResponse httpServletResponse) {
        try {
            Response response = ResponseBuilder.serverError();
            final PrintWriter printWriter = httpServletResponse.getWriter();
            httpServletResponse.setStatus(response.getStatusCode());
            httpServletResponse.setContentType("application/json");
            printWriter.write(JacksonUtil.toJson(response));
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
