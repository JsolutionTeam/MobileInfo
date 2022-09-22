package com.jsol.mcall.config.exception.entities.jwt;

import com.jsol.mcall.config.exception.BasicException;

public class TokenIsNotValidException extends BasicException {
    public TokenIsNotValidException() {
        super(400, "token_not_valid", "토큰정보가 비정상입니다.");
    }
}