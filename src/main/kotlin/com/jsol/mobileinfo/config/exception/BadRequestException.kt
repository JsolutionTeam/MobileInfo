package com.jsol.mobileinfo.config.exception

import org.springframework.http.HttpStatus

class BadRequestException(
    entityName: String? = null,
    message: String = "잘못된 요청입니다.",
) : BasicException(status = 400, code = "${if (entityName?.isNotBlank() == true) entityName + "_" else ""}bad request", message = message) {
}
