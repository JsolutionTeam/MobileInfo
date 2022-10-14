package com.jsol.mobileinfo.domain.maker.dto.response

import com.jsol.mobileinfo.domain.maker.entity.Maker

data class MakerResponse(
    val makerId: Long,
    val name: String,
) {
    companion object {
        fun of(maker: Maker): MakerResponse {
            return MakerResponse(
                makerId = maker.id!!,
                name = maker.name,
            )
        }
    }
}
