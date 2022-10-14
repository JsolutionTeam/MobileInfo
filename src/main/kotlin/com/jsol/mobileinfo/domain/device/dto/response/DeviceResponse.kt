package com.jsol.mobileinfo.domain.device.dto.response

import com.jsol.mobileinfo.domain.device.entity.Device
import com.jsol.mobileinfo.domain.maker.dto.response.MakerResponse

class DeviceResponse(
    val deviceId: Long,
    val petName: String,
    val modelName: String,
    val volume: String,
    val price: Int,
    val maker: MakerResponse?,
) {


    companion object {
        fun of(device: Device): DeviceResponse {
            return DeviceResponse(
                deviceId = device.id!!,
                petName = device.petName,
                modelName = device.modelName,
                volume = device.volume,
                price = device.price,
                maker = if(device.maker != null){MakerResponse.of(device.maker!!)}else null
            )
        }
    }
}
