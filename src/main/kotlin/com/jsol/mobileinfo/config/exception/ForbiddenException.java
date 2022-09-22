package com.jsol.mobileinfo.config.exception;

import com.jsol.mcall.service.AccountServiceImpl;

public class ForbiddenException extends BasicException {

    public ForbiddenException(String message){
        super(403, "forbidden", message);
    }

    public ForbiddenException() {
        this("권한이 없습니다." + AccountServiceImpl.getAccountFromSecurityContext().getRole());
    }
}