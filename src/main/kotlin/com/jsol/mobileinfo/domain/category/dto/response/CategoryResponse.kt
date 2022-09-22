package com.jsol.mobileinfo.domain.category.dto.response

import com.jsol.mobileinfo.domain.category.entity.Category
import com.jsol.mobileinfo.domain.category.entity.CategoryType

data class CategoryResponse(
    val categoryId: Long,
    val name: String,
    val type: CategoryType,
) {

    companion object {
        fun of(category: Category): CategoryResponse {
            return CategoryResponse(
                categoryId = category.id!!,
                name = category.name,
                type = category.type,
            )
        }
    }
}