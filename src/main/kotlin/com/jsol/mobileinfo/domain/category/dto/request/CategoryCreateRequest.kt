package com.jsol.mobileinfo.domain.category.dto.request

import com.jsol.mobileinfo.domain.category.entity.CategoryType

data class CategoryCreateRequest(
    val name: String,
    val type: CategoryType = CategoryType.IN,
)