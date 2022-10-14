package com.jsol.mobileinfo.config.exception

class ExternalApiRequestException(address: String, message: String)
    : BasicException(500, "요청 url : $address", message)
