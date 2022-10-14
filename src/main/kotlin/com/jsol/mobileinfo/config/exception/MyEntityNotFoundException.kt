package com.jsol.mobileinfo.config.exception

class MyEntityNotFoundException(
    entityName: String?,
    message: String = "요청 결과를 찾을 수 없습니다.",
) : BasicException(
    400, "${if(entityName?.isNotBlank() == true)entityName+"_" else ""}not found", message
)
