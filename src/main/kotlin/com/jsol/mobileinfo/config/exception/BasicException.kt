package com.jsol.mobileinfo.config.exception

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "에러")
open class BasicException(
    @Schema(description = "상태")
    val status: Int? = null,
    @Schema(description = "코드")
    val code: String? = null,
    @Schema(description = "메세지")
    override val message: String? = null
) : RuntimeException()