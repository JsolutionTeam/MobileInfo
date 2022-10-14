package com.jsol.mobileinfo.controller

import com.jsol.mobileinfo.domain.category.dto.request.CategoryCreateRequest
import com.jsol.mobileinfo.domain.category.dto.request.CategoryUpdateRequest
import com.jsol.mobileinfo.domain.category.dto.response.CategoryResponse
import com.jsol.mobileinfo.domain.category.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
class CategoryController(
    private val categoryService: CategoryService,
) {

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCategory(@Valid @RequestBody request: CategoryCreateRequest): CategoryResponse {
        val category = categoryService.createCategory(request)
        return CategoryResponse.of(category)
    }

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getCategoryById(@PathVariable id: Long): CategoryResponse {
        return categoryService.getCategoryById(id)
    }

    @GetMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    fun getCategories(): List<CategoryResponse> {
        return categoryService.getCategories()
    }

    @PutMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    fun updateCategory(@RequestBody categoryUpdateRequest: CategoryUpdateRequest): CategoryResponse {
        return categoryService.updateCategory(categoryUpdateRequest)
    }

    @DeleteMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCategoryById(@PathVariable id: Long): String {
        return if (categoryService.deleteCategory(id).equals(Unit::class)) {
            "정상적으로 삭제되었습니다."
        } else {
            "삭제가 정상적으로 실행되지 않았습니다."
        }
    }


}
