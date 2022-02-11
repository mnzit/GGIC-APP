package com.ggic.app.filter;

import com.ggic.app.controller.Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class SqlInjectionFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httprequest = (HttpServletRequest) request;
        // Get all request parameter names
        Enumeration<?> params = httprequest.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            // Get the parameter name
            String name = params.nextElement().toString();
            // Get the corresponding value of the parameter
            String[] value = httprequest.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        // Filtered SQL keywords can be added manually
        String sqlInjectStrList = config.getInitParameter("sqlInjectStrList");
        if (sqlValidate(sql, sqlInjectStrList)) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            Controller.view(httpServletRequest, httpServletResponse, "error/error");
        } else {
            chain.doFilter(request, response);
        }
    }

    protected static boolean sqlValidate(String str, String sqlInjectStrList) {
        // Unified to lowercase
        str = str.toLowerCase();
        // Convert to an array
        String[] badStrs = sqlInjectStrList.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            // search
            System.out.println(str);
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
