package com.jsol.mobileinfo.domain.category.service

import com.jsol.mobileinfo.domain.category.dto.request.CategoryCreateRequest
import com.jsol.mobileinfo.domain.category.entity.Category
import com.jsol.mobileinfo.domain.category.entity.CategoryType
import com.jsol.mobileinfo.domain.category.repository.CategoryRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CategoryServiceTest @Autowired constructor (
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
    fun getCategoryById() {
        // given
        val category = categoryRepository.save(Category("이름", CategoryType.IN))

        // when
        val result = categoryService.getCategoryById(category.id!!)

        // then
        assertThat(result.categoryId).isEqualTo(category.id)
    }

    @Test
    @DisplayName("유형 전체 조회가 정상 동작한다.")
    fun getCategories() {
        // given
        categoryRepository.saveAll(listOf(
            Category("A", CategoryType.IN),
            Category("B", CategoryType.OUT),
        ))

        // when
        val categories = categoryService.getCategories()

        // then
        assertThat(categories).hasSize(2)
        assertThat(categories).extracting("name").containsExactlyInAnyOrder("A", "B")
        assertThat(categories).extracting("type").containsExactlyInAnyOrder(CategoryType.IN, CategoryType.OUT)
    }

    @Test
    @DisplayName("유형 ")
    fun updateCategory() {
        // given

        // when

        // then
    }

//    @Test
//    @DisplayName
//    fun deleteCategory() {
//        // given
//
//        // when
//
//        // then
//    }
}