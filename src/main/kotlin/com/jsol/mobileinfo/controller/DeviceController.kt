package com.jsol.mobileinfo.controller

import com.jsol.mobileinfo.config.utils.BasicControllerMethod
import com.jsol.mobileinfo.domain.device.dto.request.DeviceCreateRequest
import com.jsol.mobileinfo.domain.device.dto.request.DeviceUpdateRequest
import com.jsol.mobileinfo.domain.device.dto.response.DeviceResponse
import com.jsol.mobileinfo.domain.device.service.DeviceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class DeviceController(
    private val deviceService: DeviceService
) {

    @PostMapping("/device")
    fun createDevice(@RequestBody deviceDto: DeviceCreateRequest): ResponseEntity<DeviceResponse> {
        val device = deviceService.createDevice(deviceDto)
        val response = DeviceResponse.of(device)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/device/{deviceId}")
    fun getDeviceById(@PathVariable(name = "deviceId", required = true) deviceId: Long): ResponseEntity<DeviceResponse> {
        val device = deviceService.getDeviceById(deviceId)
        return ResponseEntity<DeviceResponse>(device, HttpStatus.OK)
    }

    @GetMapping("/device")
    fun getDevices(): ResponseEntity<List<DeviceResponse>> {
        val devices = deviceService.getDevices()
        return ResponseEntity<List<DeviceResponse>>(devices, HttpStatus.OK)
    }

    @PutMapping("/device")
    fun updateDevice(@RequestBody request: DeviceUpdateRequest) {
        deviceService.updateDevice(request)
    }

    @DeleteMapping("/device/{deviceId}")
    fun deleteDevice(@PathVariable deviceId: Long): String {
        return BasicControllerMethod.delete(deviceService.deleteDevice(deviceId).equals(Unit::class))
    }
}
