package com.jsol.mobileinfo.domain.color.repository

import com.jsol.mobileinfo.domain.color.entity.Color
import org.springframework.data.jpa.repository.JpaRepository

interface ColorRepository : JpaRepository<Color, Long>
