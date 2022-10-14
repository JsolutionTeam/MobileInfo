package com.jsol.mobileinfo.config.exception

import org.springframework.http.HttpStatus

class InternalServerException(entityName: String? = null, message: String = "서버 에러 발생") :
    BasicException(500, "${if(entityName?.isNotBlank() == true)entityName+"_" else ""}internal server error", message) {
}
