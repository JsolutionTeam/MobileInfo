package com.jsol.mcall.config.exception.entities.jwt;

import com.jsol.mcall.config.exception.BasicException;

public class TokenExpiredException extends BasicException {
    public TokenExpiredException() {
        super(401, "token_expired", "로그인 세션이 만료되었습니다.");
    }
}