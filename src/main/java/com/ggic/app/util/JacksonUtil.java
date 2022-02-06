package com.ggic.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletInputStream;

public class JacksonUtil {

    private final static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static String toJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T toObject(ServletInputStream servletInputStream, Class<T> classz) throws Exception {
        return objectMapper.readValue(servletInputStream, classz);
    }

}
