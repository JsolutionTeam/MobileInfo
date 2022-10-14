package com.jsol.mobileinfo.domain.device.dto.request

import com.jsol.mobileinfo.domain.device.entity.DeviceVolumeType
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

data class DeviceUpdateRequest(
    @NotBlank(message = "단말의 번호(기본키)는 필수입력입니다.")
    val deviceId: Long,

    @Schema(description = "단말 펫네임")
    val petName: String? = null,

    @Schema(description = "단말 모델명")
    val modelName: String? = null,

    @Schema(description = "단말 용량 값")
    val volumeValue: Int? = null,

    @Schema(description = "단말 용량 단위")
    val volumeType: DeviceVolumeType? = null,

    @Schema(defaultValue = "0", description = "단말의 현재 출고가")
    val price: Int? = 0,

    @Schema(description = "단말 제조사 번호", nullable = true, required = false)
    val makerIdx: Long? = null,

)
