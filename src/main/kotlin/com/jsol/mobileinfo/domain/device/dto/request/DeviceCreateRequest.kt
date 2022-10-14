package com.jsol.mobileinfo.domain.device.dto.request

import com.jsol.mobileinfo.domain.device.entity.DeviceVolumeType
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank

data class DeviceCreateRequest(
    @NotBlank(message = "단말의 펫네임은 필수입력입니다.")
    @Schema(description = "단말 펫네임", nullable = false, required = true)
    val petName: String,

    @NotBlank(message = "단말의 모델명은 필수입력입니다.")
    @Schema(description = "단말 모델명", nullable = false, required = true)
    val modelName: String,

    @NotBlank(message = "단말의 용량 값은 필수입력입니다.")
    @Schema(description = "단말 용량 값", nullable = false, required = true)
    val volumeValue: Int,

    @NotBlank(message = "단말의 용량 단위는 필수입력입니다.")
    @Schema(description = "단말 용량 단위", nullable = false, required = true)
    val volumeType: DeviceVolumeType,

    @Schema(defaultValue = "0", description = "단말의 현재 출고가")
    val price: Int = 0,

    @Schema(description = "단말 제조사 번호", nullable = true, required = false)
    val makerIdx: Long?,

    )
