package com.jsol.mobileinfo.domain.color.dto.response

import com.jsol.mobileinfo.domain.color.entity.Color

class ColorResponse(
    val colorId: Long,
    val name: String,
    val nameEng: String,
) {

    companion object {
        fun of(color: Color): ColorResponse {
            return ColorResponse(
                colorId = color.id!!,
                name = color.name,
                nameEng = color.nameEng,
            )
        }
    }

    override fun toString(): String {
        return "id=${this.colorId}, name=${this.name}, nameEng=${this.nameEng}"
    }

}
