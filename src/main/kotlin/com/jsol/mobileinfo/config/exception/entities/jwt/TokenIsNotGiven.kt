package com.jsol.mobileinfo.config.exception.entities.jwt

import com.jsol.mobileinfo.config.exception.BasicException

class TokenIsNotGiven(name: String) : BasicException(400, "token_is_not_null", name + "토큰이 넘어오지 않았습니다.")
