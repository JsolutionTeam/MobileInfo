package com.jsol.mcall.config.exception;

import java.util.HashMap;

public class ExternalApiRequestException extends BasicException {
    public ExternalApiRequestException(String address, String message){
        super(500, "요청url : "+address, message);
    }

}