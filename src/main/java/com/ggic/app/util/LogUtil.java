package com.ggic.app.util;

import com.ggic.app.response.Response;

public class LogUtil {

    public static void responseLogger(Response response, String type) {
        System.out.println("[task type]: " + type);
        System.out.println("status: " + response.getStatusCode());
        System.out.println("description: " + response.getDescription());
    }
}
