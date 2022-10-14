package com.jsol.mobileinfo.domain.color.dto.request

import com.jsol.mobileinfo.domain.category.entity.CategoryType

data class ColorUpdateRequest(
    val colorId: Long,
    val name: String?,
    val nameEng: String?
)
