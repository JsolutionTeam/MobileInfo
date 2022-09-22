package com.jsol.mobileinfo.domain.maker.service

import com.jsol.mobileinfo.domain.maker.dto.request.MakerCreateRequest
import com.jsol.mobileinfo.domain.maker.dto.request.MakerUpdateRequest
import com.jsol.mobileinfo.domain.maker.entity.Maker
import com.jsol.mobileinfo.domain.maker.repository.MakerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MakerServiceTest @Autowired constructor(
    private val makerRepository: MakerRepository,
    private val makerService: MakerService,
) {

    @AfterEach
    fun clean() {
        makerRepository.deleteAll()
    }

    @Test
    @DisplayName("제조사 저장이 정상 동작한다.")
    fun createMakerTest() {
        // given
        val lgName = "LG"
        val lgMakerCreateRequest = MakerCreateRequest(lgName)

        // when
        makerService.createMaker(lgMakerCreateRequest)

        // then
        val results = makerRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo(lgName)
    }

    @Test
    @DisplayName("제조사 단일 조회가 정상 동작한다.")
    fun getMakerByIdTest() {
        // given
        val maker = makerRepository.save(Maker("SM"))

        // when
        val results = makerService.getMakerById(maker.id!!)

        // then
        assertThat(results.makerId).isEqualTo(maker.id)
    }

    @Test
    @DisplayName("제조사 전체 조회가 정상 동작한다.")
    fun getMakersTest() {
        // given
        makerRepository.saveAll(
            listOf(
                Maker("A"),
                Maker("B"),
            )
        )

        // when
        val results = makerService.getMakers()

        // then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A", "B") // ["A", "B"]
    }

    @Test
    @DisplayName("제조사 업데이트가 정상 동작한다.")
    fun updateMakerTest(){
        // given
        val saveMaker = makerRepository.save(Maker("SM"))
        val request = MakerUpdateRequest(saveMaker.id!!, "LG")

        // when
        makerService.updateMaker(request)

        // then
        val result = makerRepository.findAll()[0]
        assertThat(result.name).isEqualTo("LG")
    }

    @Test
    @DisplayName("제조사 삭제가 정상 동작한다.")
    fun deleteMakerTest(){
        // given
        val maker = makerRepository.save(Maker("SM"))

        // when
        makerService.deleteMaker(maker.id!!)

        // then
        assertThat(makerRepository.findAll()).isEmpty()
    }
}