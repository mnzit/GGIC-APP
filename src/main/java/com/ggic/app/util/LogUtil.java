package com.ggic.app.util;

import com.ggic.app.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {

    public static void responseLogger(Response response, String type) {
        log.info("[task type]: " + type);
        log.info("status: " + response.getStatusCode());
        log.info("description: " + response.getDescription());
    }
}
