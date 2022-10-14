package com.jsol.mobileinfo.domain.maker.service

import com.jsol.mobileinfo.domain.device.dto.response.DeviceResponse
import com.jsol.mobileinfo.domain.maker.dto.request.MakerCreateRequest
import com.jsol.mobileinfo.domain.maker.dto.request.MakerUpdateRequest
import com.jsol.mobileinfo.domain.maker.dto.response.MakerResponse
import com.jsol.mobileinfo.domain.maker.entity.Maker
import com.jsol.mobileinfo.domain.maker.repository.MakerRepository
import com.jsol.mobileinfo.domain.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MakerService(
    private val makerRepository: MakerRepository,
) {
    private final val notFoundById = "id로 제조사 조회에 실패했습니다."

    fun createMaker(request: MakerCreateRequest): Maker {
        return makerRepository.save(Maker(
            name = request.name
        ))
    }

    @Transactional(readOnly = true)
    fun getMakerById(makerId: Long): MakerResponse {
        val maker = makerRepository.findByIdOrThrow(makerId, notFoundById)
        return MakerResponse.of(maker)
    }

    @Transactional(readOnly = true)
    fun getMakers(): List<MakerResponse> {
        return makerRepository.findAll()
            .map(MakerResponse::of)
    }

    fun updateMaker(request: MakerUpdateRequest): MakerResponse {
        val maker = makerRepository.findByIdOrThrow(request.makerId, notFoundById)
        maker.updateName(request.name)
        makerRepository.save(maker)
        return MakerResponse.of(maker)
    }

    fun deleteMaker(makerId: Long) {
        val maker = makerRepository.findByIdOrThrow(makerId, notFoundById)
        makerRepository.delete(maker)
    }
}
