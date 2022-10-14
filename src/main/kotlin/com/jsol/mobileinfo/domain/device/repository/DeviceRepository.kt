package com.jsol.mobileinfo.domain.device.repository

import com.jsol.mobileinfo.domain.device.entity.Device
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceRepository : JpaRepository<Device, Long>
