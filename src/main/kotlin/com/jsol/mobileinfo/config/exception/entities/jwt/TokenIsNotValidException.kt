package com.jsol.mobileinfo.config.exception.entities.jwt

import com.jsol.mobileinfo.config.exception.BasicException

class TokenIsNotValidException : BasicException(400, "token_not_valid", "토큰정보가 비정상입니다.")
