package com.jsol.mobileinfo.domain.color.dto.request

import javax.validation.constraints.NotBlank


data class ColorCreateRequest(
    @NotBlank(message = "name은 필수 입력입니다.")
    val name: String,
    @NotBlank(message = "nameEng는 필수 입력입니다.")
    val nameEng: String,
)
