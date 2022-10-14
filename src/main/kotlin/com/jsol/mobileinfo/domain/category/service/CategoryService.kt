package com.jsol.mobileinfo.domain.category.service

import com.jsol.mobileinfo.domain.category.dto.request.CategoryCreateRequest
import com.jsol.mobileinfo.domain.category.dto.request.CategoryUpdateRequest
import com.jsol.mobileinfo.domain.category.dto.response.CategoryResponse
import com.jsol.mobileinfo.domain.category.entity.Category
import com.jsol.mobileinfo.domain.category.repository.CategoryRepository
import com.jsol.mobileinfo.domain.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
) {
    fun createCategory(request: CategoryCreateRequest): Category {
        return categoryRepository.save(
            Category(
                name = request.name,
                type = request.type,
            )
        )
    }

    @Transactional(readOnly = true)
    fun getCategoryById(categoryId: Long): CategoryResponse {
        val category = categoryRepository.findByIdOrThrow(categoryId)
        return CategoryResponse.of(category)
    }

    @Transactional(readOnly = true)
    fun getCategories(): List<CategoryResponse> {
        return categoryRepository.findAll()
            .map(CategoryResponse::of)
    }

    fun updateCategory(request: CategoryUpdateRequest): CategoryResponse {
        val category = categoryRepository.findByIdOrThrow(request.categoryId)
        if (request.name.isNotBlank()) {
            category.updateName(request.name)
        }
        if (request.type != null) {
            category.updateType(request.type)
        }
        return CategoryResponse.of(category)
    }

    fun deleteCategory(categoryId: Long) {
        val category = categoryRepository.findByIdOrThrow(categoryId)
        categoryRepository.delete(category)
    }
}
