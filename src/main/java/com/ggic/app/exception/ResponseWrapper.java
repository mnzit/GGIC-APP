package com.ggic.app.exception;

import com.ggic.app.response.Response;

public interface ResponseWrapper {

    Response process() throws Exception;
}
