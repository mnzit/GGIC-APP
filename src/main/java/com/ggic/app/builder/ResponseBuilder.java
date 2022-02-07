package com.ggic.app.builder;

import com.ggic.app.response.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ResponseBuilder {

    public static Response success(String description){
        Response response = new Response();
        response.setSuccess(true);
        response.setStatusCode(HttpServletResponse.SC_OK);
        response.setDescription(description);
        return response;
    }

    public static Response success(String description, Object data){
        Response response = new Response();
        response.setSuccess(true);
        response.setData(data);
        response.setStatusCode(HttpServletResponse.SC_OK);
        response.setDescription(description);
        return response;
    }

    public static Response success(String description, Integer statusCode){
        Response response = new Response();
        response.setSuccess(true);
        response.setStatusCode(statusCode);
        response.setDescription(description);
        return response;
    }

    public static Response failure(String description, Integer statusCode){
        Response response = new Response();
        response.setSuccess(false);
        response.setStatusCode(statusCode);
        response.setDescription(description);
        return response;
    }

    public static Response failure(String description, Map<String,String> errors){
        Response response = new Response();
        response.setSuccess(false);
        response.setErrors(errors);
        response.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setDescription(description);
        return response;
    }

    public static Response serverError(){
        Response response = new Response();
        response.setSuccess(false);
        response.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setDescription("System Error");
        return response;
    }

    public static Response invalidRequest(){
        Response response = new Response();
        response.setSuccess(false);
        response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
        response.setDescription("Invalid Request");
        return response;
    }
}
