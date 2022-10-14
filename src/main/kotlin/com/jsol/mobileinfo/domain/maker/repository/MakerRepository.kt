package com.jsol.mobileinfo.domain.maker.repository

import com.jsol.mobileinfo.domain.maker.entity.Maker
import org.springframework.data.jpa.repository.JpaRepository

interface MakerRepository : JpaRepository<Maker, Long>