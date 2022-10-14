package com.jsol.mobileinfo.config.exception.entities.jwt

import com.jsol.mobileinfo.config.exception.BasicException

class TokenExpiredException : BasicException(401, "token_expired", "로그인 세션이 만료되었습니다.")
