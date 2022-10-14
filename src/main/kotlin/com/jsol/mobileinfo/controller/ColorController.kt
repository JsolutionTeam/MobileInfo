package com.jsol.mobileinfo.controller

import com.jsol.mobileinfo.domain.color.dto.request.ColorCreateRequest
import com.jsol.mobileinfo.domain.color.dto.request.ColorUpdateRequest
import com.jsol.mobileinfo.domain.color.dto.response.ColorResponse
import com.jsol.mobileinfo.domain.color.service.ColorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
class ColorController(
    private val colorService: ColorService,
) {

    @PostMapping("/color")
    @ResponseStatus(HttpStatus.CREATED)
    fun createColor(@Valid @RequestBody request: ColorCreateRequest): ColorResponse {
        val color = colorService.createColor(request)
        return ColorResponse.of(color)
    }

    @GetMapping("/color/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getColorById(@PathVariable id: Long): ColorResponse {
        return colorService.getColorById(id)
    }

    @GetMapping("/color")
    @ResponseStatus(HttpStatus.OK)
    fun getCategories(): List<ColorResponse> {
        return colorService.getCategories()
    }

    @PutMapping("/color")
    @ResponseStatus(HttpStatus.OK)
    fun updateColor(@RequestBody colorUpdateRequest: ColorUpdateRequest): ColorResponse {
        return colorService.updateColor(colorUpdateRequest)
    }

    @DeleteMapping("/color/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteColorById(@PathVariable id: Long): String {
        return if (colorService.deleteColor(id).equals(Unit::class)) {
            "정상적으로 삭제되었습니다."
        } else {
            "삭제가 정상적으로 실행되지 않았습니다."
        }
    }
}
