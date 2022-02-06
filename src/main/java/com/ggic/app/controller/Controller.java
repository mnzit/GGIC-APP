package com.ggic.app.controller;

import com.ggic.app.dto.Response;
import com.ggic.app.util.JacksonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public abstract class Controller extends HttpServlet {

    public void buildResponse(HttpServletResponse httpServletResponse, Response response) throws Exception {
        final PrintWriter printWriter = httpServletResponse.getWriter();
        httpServletResponse.setStatus(response.getStatusCode());
        httpServletResponse.setContentType("application/json");
        printWriter.write(JacksonUtil.toJson(response));

    }
}
