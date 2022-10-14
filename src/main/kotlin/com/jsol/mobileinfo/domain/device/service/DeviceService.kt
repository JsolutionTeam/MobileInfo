package com.jsol.mobileinfo.domain.device.service

import com.jsol.mobileinfo.domain.device.dto.request.DeviceCreateRequest
import com.jsol.mobileinfo.domain.device.dto.request.DeviceUpdateRequest
import com.jsol.mobileinfo.domain.device.dto.response.DeviceResponse
import com.jsol.mobileinfo.domain.device.entity.Device
import com.jsol.mobileinfo.domain.device.repository.DeviceRepository
import com.jsol.mobileinfo.domain.maker.entity.Maker
import com.jsol.mobileinfo.domain.maker.repository.MakerRepository
import com.jsol.mobileinfo.domain.util.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeviceService(
    private val deviceRepository: DeviceRepository,
    private val makerRepository: MakerRepository,
) {

    fun createDevice(request: DeviceCreateRequest): Device {
        var maker: Maker? = null
        if(request.makerIdx != null){
            maker = makerRepository.findByIdOrThrow(request.makerIdx, "존재하지 않는 제조사 번호입니다.")
        }
//        val maker: Maker? = request.makerIdx?.let { makerRepository.findByIdOrNull(it) }
        return deviceRepository.save(
            Device(
                petName = request.petName,
                modelName = request.modelName,
                volume = request.volumeValue.toString().plus(request.volumeType.name),
                price = request.price,
                maker = maker,
            )
        )
    }

    @Transactional(readOnly = true)
    fun getDeviceById(deviceId: Long): DeviceResponse {
        val device = deviceRepository.findByIdOrThrow(deviceId)
        return DeviceResponse.of(device)
    }

    @Transactional(readOnly = true)
    fun getDevices(): List<DeviceResponse> {
        return deviceRepository.findAll()
            .map(DeviceResponse::of)
    }

    fun updateDevice(request: DeviceUpdateRequest): DeviceResponse {
        val device = deviceRepository.findByIdOrThrow(request.deviceId)
        device.update(request)
        if(request.makerIdx != null){
            val maker: Maker? = makerRepository.findByIdOrNull(request.makerIdx)
            device.updateMaker(maker)
        }

        return DeviceResponse.of(device)
    }

    fun deleteDevice(deviceId: Long) {
        val device = deviceRepository.findByIdOrThrow(deviceId)
        deviceRepository.delete(device)
    }

}
