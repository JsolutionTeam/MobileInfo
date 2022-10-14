package com.jsol.mobileinfo.config.exception

class UnauthorizedException(
    message: String = "계정 정보 인증에 실패했습니다."
) : BasicException(
    status = 401,
    code = "unauthorized",
    message = message
)
