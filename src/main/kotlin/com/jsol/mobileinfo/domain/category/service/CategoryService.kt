package com.jsol.mobileinfo.domain.category.service

import com.jsol.mobileinfo.domain.category.dto.request.CategoryCreateRequest
import com.jsol.mobileinfo.domain.category.dto.request.CategoryUpdateRequest
import com.jsol.mobileinfo.domain.category.dto.response.CategoryResponse
import com.jsol.mobileinfo.domain.category.entity.Category
import com.jsol.mobileinfo.domain.category.repository.CategoryRepository
import com.jsol.mobileinfo.domain.util.findByIdOrThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    @Autowired
    private val categoryRepository: CategoryRepository
) {
    private final val notFoundById = "id로 유형 조회에 실패했습니다."

    @Transactional
    fun createCategory(request: CategoryCreateRequest) {
        categoryRepository.save(
            Category(
                name = request.name,
                type = request.type,
            )
        )
    }

    @Transactional(readOnly = true)
    fun getCategoryById(categoryId: Long): CategoryResponse{
        val category = categoryRepository.findByIdOrThrow(categoryId, notFoundById)
        return CategoryResponse.of(category)
    }

    @Transactional(readOnly = true)
    fun getCategories(): List<CategoryResponse>{
        return categoryRepository.findAll()
            .map(CategoryResponse::of)
    }

    @Transactional
    fun updateCategory(request: CategoryUpdateRequest){
        val category = categoryRepository.findByIdOrThrow(request.categoryId, notFoundById)
        if(request.name.isNotBlank()){
            category.updateName(request.name)
        }
        if(request.type != null){
            category.updateType(request.type)
        }
    }

    @Transactional
    fun deleteCategory(categoryId: Long){
        val category = categoryRepository.findByIdOrThrow(categoryId, notFoundById)
        categoryRepository.delete(category)
    }
}