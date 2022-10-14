package com.jsol.mobileinfo.domain.color.service

import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ColorServiceTest(
//    private val colorRepository: ColorRepository,
//    private val colorService: ColorService,
) {

//    @AfterEach
//    fun clean() {
//        colorRepository.deleteAll()
//    }

    /*@Test
    @DisplayName("색상 저장이 정상 동작한다.")
    fun createColorTest() {
        // given
        val name = "골드"
        val nameEng = "gold"
        val lgColorCreateRequest = ColorCreateRequest(lgName)

        // when
        colorService.createColor(lgColorCreateRequest)

        // then
        val results = colorRepository.findAll()
        Assertions.assertThat(results).hasSize(1)
        Assertions.assertThat(results[0].name).isEqualTo(lgName)
    }

    @Test
    @DisplayName("색상 단일 조회가 정상 동작한다.")
    fun getColorByIdTest() {
        // given
        val color = colorRepository.save(Color("SM"))

        // when
        val results = colorService.getColorById(color.id!!)

        // then
        Assertions.assertThat(results.colorId).isEqualTo(color.id)
    }

    @Test
    @DisplayName("색상 전체 조회가 정상 동작한다.")
    fun getColorsTest() {
        // given
        colorRepository.saveAll(
            listOf(
                Color("A"),
                Color("B"),
            )
        )

        // when
        val results = colorService.getColors()

        // then
        Assertions.assertThat(results).hasSize(2)
        Assertions.assertThat(results).extracting("name").containsExactlyInAnyOrder("A", "B") // ["A", "B"]
    }

    @Test
    @DisplayName("색상 업데이트가 정상 동작한다.")
    fun updateColorTest() {
        // given
        val saveColor = colorRepository.save(Color("SM"))
        val request = ColorUpdateRequest(saveColor.id!!, "LG")

        // when
        colorService.updateColor(request)

        // then
        val result = colorRepository.findAll()[0]
        Assertions.assertThat(result.name).isEqualTo("LG")
    }

    @Test
    @DisplayName("색상 삭제가 정상 동작한다.")
    fun deleteColorTest() {
        // given
        val color = colorRepository.save(Color("SM"))

        // when
        colorService.deleteColor(color.id!!)

        // then
        Assertions.assertThat(colorRepository.findAll()).isEmpty()
    }*/
}
