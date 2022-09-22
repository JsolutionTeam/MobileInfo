package com.jsol.mobileinfo.config.exception

import io.swagger.v3.oas.annotations.media.Schema

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "에러")
open class BasicException : RuntimeException() {
    @Schema(description = "상태")
    private val status: Int? = null

    @Schema(description = "코드")
    private val code: String? = null

    @Schema(description = "메세지")
    private override val message: String? = null
}