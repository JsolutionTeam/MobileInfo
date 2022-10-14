package com.jsol.mobileinfo.domain.category.service

import com.jsol.mobileinfo.domain.category.dto.request.CategoryCreateRequest
import com.jsol.mobileinfo.domain.category.dto.request.CategoryUpdateRequest
import com.jsol.mobileinfo.domain.category.entity.Category
import com.jsol.mobileinfo.domain.category.entity.CategoryType
import com.jsol.mobileinfo.domain.category.repository.CategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CategoryServiceTest @Autowired constructor(
    private val categoryService: CategoryService,
    private val categoryRepository: CategoryRepository,
) {

    @AfterEach
    fun clear() {
        categoryRepository.deleteAll()
    }

    @Test
    @DisplayName("유형 저장이 정상 동작한다.")
    fun createCategoryTest() {
        // given
        val name = "이름"
        val request = CategoryCreateRequest(name, CategoryType.IN)
        val request2 = CategoryCreateRequest("아웃", CategoryType.OUT)

        // when
        categoryService.createCategory(request)
        categoryService.createCategory(request2)

        // then
        val results = categoryRepository.findAll()
        println("results[0].type = ${results[0].type}")
        assertThat(results).hasSize(2)
        assertThat(results[0].name).isEqualTo(name)
        assertThat(results[1].name).isEqualTo("아웃")
    }

    @Test
    @DisplayName("유형 단일 조회가 정상 동작한다.")
    fun getCategoryByIdTest() {
        // given
        val category = categoryRepository.save(Category("이름", CategoryType.IN))

        // when
        val result = categoryService.getCategoryById(category.id!!)

        // then
        assertThat(result.categoryId).isEqualTo(category.id)
    }

    @Test
    @DisplayName("유형 전체 조회가 정상 동작한다.")
    fun getCategoriesTest() {
        // given
        categoryRepository.saveAll(
            listOf(
                Category("A", CategoryType.IN),
                Category("B", CategoryType.OUT),
            )
        )

        // when
        val categories = categoryService.getCategories()

        // then
        assertThat(categories).hasSize(2)
        assertThat(categories).extracting("name").containsExactlyInAnyOrder("A", "B")
        assertThat(categories).extracting("type").containsExactlyInAnyOrder(CategoryType.IN, CategoryType.OUT)
    }

    @Test
    @DisplayName("유형 업데이트가 정상 동작한다.")
    fun updateCategoryTest() {
        // given
        val savedCategory = categoryRepository.save(Category("A", CategoryType.IN))
        val request = CategoryUpdateRequest(savedCategory.id!!, "B", null)

        // when
        categoryService.updateCategory(request)

        // then
        val result = categoryRepository.findById(savedCategory.id!!)
        assertThat(result.get().name).isEqualTo("B")
    }

    @Test
    @DisplayName("유형 삭제가 정상 동작한다")
    fun deleteCategoryTest() {
        // given
        val savedCategory = categoryRepository.save(Category("A", CategoryType.OUT))

        // when
        categoryService.deleteCategory(categoryId = savedCategory.id!!)

        // then
        assertThat(categoryRepository.findAll()).isEmpty()
    }
}
