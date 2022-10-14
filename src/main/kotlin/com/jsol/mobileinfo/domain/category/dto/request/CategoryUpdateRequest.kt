package com.jsol.mobileinfo.domain.category.dto.request

import com.jsol.mobileinfo.domain.category.entity.CategoryType

data class CategoryUpdateRequest(
    val categoryId: Long,
    val name: String,
    val type: CategoryType?
)