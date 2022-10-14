package com.jsol.mobileinfo.domain.category.dto.request

import com.jsol.mobileinfo.domain.category.entity.CategoryType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CategoryCreateRequest(
    @NotBlank(message = "name은 필수 입력입니다.",)
    @NotNull(message = "name은 null일 수 없습니다.",)
    val name: String,
    @NotNull(message = "type은 필수 입력입니다.")
    val type: CategoryType = CategoryType.IN,
)
