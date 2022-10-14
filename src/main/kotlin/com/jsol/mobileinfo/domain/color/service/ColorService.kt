package com.jsol.mobileinfo.domain.color.service

import com.jsol.mobileinfo.domain.color.dto.request.ColorCreateRequest
import com.jsol.mobileinfo.domain.color.dto.request.ColorUpdateRequest
import com.jsol.mobileinfo.domain.color.dto.response.ColorResponse
import com.jsol.mobileinfo.domain.color.entity.Color
import com.jsol.mobileinfo.domain.color.repository.ColorRepository
import com.jsol.mobileinfo.domain.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ColorService(
    private val colorRepository: ColorRepository,
) {

    fun createColor(request: ColorCreateRequest): Color {
        return colorRepository.save(
            Color(
                name = request.name,
                nameEng = request.nameEng,
            )
        )
    }

    @Transactional(readOnly = true)
    fun getColorById(colorId: Long): ColorResponse {
        val color = colorRepository.findByIdOrThrow(colorId)
        return ColorResponse.of(color)
    }

    @Transactional(readOnly = true)
    fun getCategories(): List<ColorResponse> {
        return colorRepository.findAll()
            .map(ColorResponse::of)
    }

    fun updateColor(request: ColorUpdateRequest): ColorResponse {
        val color = colorRepository.findByIdOrThrow(request.colorId)
        color.updateName(request)
        return ColorResponse.of(color)
    }

    fun deleteColor(colorId: Long) {
        val color = colorRepository.findByIdOrThrow(colorId)
        colorRepository.delete(color)
    }
}
