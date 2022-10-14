package com.jsol.mobileinfo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jsol.mobileinfo.domain.category.dto.request.CategoryCreateRequest
import com.jsol.mobileinfo.domain.category.entity.Category
import com.jsol.mobileinfo.domain.category.entity.CategoryType
import com.jsol.mobileinfo.domain.category.repository.CategoryRepository
import com.jsol.mobileinfo.domain.category.service.CategoryService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [CategoryController::class])
@DisplayName("Category Controller 테스트")
class CategoryControllerTest @Autowired constructor(
    private val mvc: MockMvc,
    @MockBean
    private val categoryService: CategoryService,
    @MockBean
    private val categoryRepository: CategoryRepository,
    private val objectMapper: ObjectMapper,
) {
    private val name = "유형1"
    private val type = CategoryType.OUT
    private val category01 = Category(id = 1L, name = name, type = type)

    @Test
    @DisplayName("유형 생성에 성공한다. 성공 시 201코드")
    fun createCategoryTest(): Unit {
        // given

        val request = CategoryCreateRequest(name = name, type = type)

        given(categoryService.createCategory(request))
            .willReturn(category01)

        val dto = objectMapper.writeValueAsString(
            request
        )

        // when
        val actions: ResultActions =
            mvc.perform(
                post("/api/v1/category")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(dto)
            )

        // then
        actions
            .andExpect(status().isCreated)
            .andExpect(jsonPath("name").value(name))
            .andExpect(jsonPath("type").value(type.name))
    }


    @Test
    @DisplayName("Category 단일 조회 테스트를 진행한다.")
    fun testFindById() {
        // given
        // when
        val actions = mvc.perform(
            get("/api/v1/category/1")
        )

        // then
        actions
            .andDo(print())
            // 200 OK로 넘어왔는지
            .andExpect(status().isOk)
//            // 담당 컨트롤러가 맞는지
//            .andExpect(handler().handlerType(CategoryController::class.java))
//            // 처리 메소드명이 맞는지
//            .andExpect(handler().methodName("getCategoryById"))
    }

    @Test
    @DisplayName("Category 전체 조회 테스트를 진행한다.")
    fun testFindAll() {
        // when
        val actions = mvc.perform(
            get("/api/v1/category")
        )

        // then
        actions
            .andDo(print())
            // 200 OK로 넘어왔는지
            .andExpect(status().isOk)
//            // 담당 컨트롤러가 맞는지
//            .andExpect(handler().handlerType(CategoryController::class.java))
//            // 처리 메소드명이 맞는지
//            .andExpect(handler().methodName("getCategories"))
    }
}
