package com.jsol.mobileinfo.domain.device.service

import com.jsol.mobileinfo.domain.device.dto.request.DeviceCreateRequest
import com.jsol.mobileinfo.domain.device.dto.request.DeviceUpdateRequest
import com.jsol.mobileinfo.domain.device.entity.Device
import com.jsol.mobileinfo.domain.device.entity.DeviceVolumeType
import com.jsol.mobileinfo.domain.device.repository.DeviceRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DeviceServiceTest @Autowired constructor(
    private val deviceService: DeviceService,
    private val deviceRepository: DeviceRepository,
) {

    private val device01 = fun(): Device {
        return Device(
            petName = "갤럭시S01 Ultra",
            modelName = "SMG000",
            volume = "128GB",
            price = 1020303,
            maker = null,
        )
    }

    @AfterEach
    fun clean() {
        deviceRepository.deleteAll()
    }

    @Test
    @DisplayName("단말 저장이 정상 동작한다.")
    fun createDevice() {
        // given
        val device01 = DeviceCreateRequest(
            petName = "갤럭시S21 Ultra",
            modelName = "SMG000",
            volumeValue = 128,
            volumeType = DeviceVolumeType.GB,
            price = 1020303,
            makerIdx = null,
        )

        // when
        deviceService.createDevice(device01)

        // then
        val results = deviceRepository.findAll()
        Assertions.assertThat(results).hasSize(1)
        Assertions.assertThat(results[0].petName).isEqualTo("갤럭시S21 Ultra")
        Assertions.assertThat(results[0].modelName).isEqualTo("SMG000")
        Assertions.assertThat(results[0].volume).isEqualTo("128GB")
        Assertions.assertThat(results[0].price).isEqualTo(1020303)
    }

    @Test
    @DisplayName("단말 단일 조회가 정상 동작한다.")
    fun getDeviceById() {
        // given
        val device = deviceRepository.save(device01())
        // when
        val results = deviceService.getDeviceById(device.id!!)

        // then
        assertThat(results.deviceId).isEqualTo(device.id)
    }

    @Test
    @DisplayName("단말 전체 조회가 정상 동작한다.")
    fun getDevices() {
        // given

        deviceRepository.saveAll(
            listOf(
                device01(),
                Device(
                    petName = "갤럭시S02",
                    modelName = "SMG001",
                    volume = "256GB",
                    price = 1010101,
                    maker = null,
                ),
            )
        )

        // when
        val results = deviceService.getDevices()

        // then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("petName").containsExactlyInAnyOrder("갤럭시S01 Ultra", "갤럭시S02")
    }

    @Test
    @DisplayName("단말 업데이트가 정상 동작한다.")
    fun updateDevice() {
        // given
        val saveDevice = deviceRepository.save(device01())
        val request = DeviceUpdateRequest(
            deviceId = saveDevice.id!!,
            petName = "펫네임",
            modelName = "모델명",
            volumeType = DeviceVolumeType.TB,
            volumeValue = 999,
            price = 12345678,
            makerIdx = null
        )

        // when
        deviceService.updateDevice(request)

        // then
        val result = deviceRepository.findAll()[0]
        Assertions.assertThat(result.petName).isEqualTo("펫네임")
        Assertions.assertThat(result.modelName).isEqualTo("모델명")
        Assertions.assertThat(result.volume).isEqualTo("999TB")
        Assertions.assertThat(result.price).isEqualTo(12345678)
        Assertions.assertThat(result.maker).isEqualTo(null)
    }

    @Test
    @DisplayName("단말 삭제가 정상 동작한다.")
    fun deleteDevice() {
        // given
        val device = deviceRepository.save(device01())

        // when
        deviceService.deleteDevice(device.id!!)

        // then
        assertThat(deviceRepository.findAll()).isEmpty()
    }
}
