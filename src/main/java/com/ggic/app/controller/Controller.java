package com.ggic.app.controller;

import com.ggic.app.builder.ResponseBuilder;
import com.ggic.app.response.Response;
import com.ggic.app.util.JacksonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    public static void notAValidRequest(HttpServletResponse httpServletResponse) {
        try {
            Response response = ResponseBuilder.invalidRequest();
            final PrintWriter printWriter = httpServletResponse.getWriter();
            httpServletResponse.setStatus(response.getStatusCode());
            httpServletResponse.setContentType("application/json");
            printWriter.write(JacksonUtil.toJson(response));
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public static String viewPrefix = "/WEB-INF/views/";
    public static String viewSuffix = ".jsp";


    public static void view(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String viewName) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(viewPrefix + viewName + viewSuffix)
                .forward(httpServletRequest, httpServletResponse);
    }

    protected void redirect(HttpServletResponse httpServletResponse, String servletPath) throws IOException {
        httpServletResponse.sendRedirect(servletPath);
    }
}
