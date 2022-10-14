package com.jsol.mobileinfo.domain.category.repository

import com.jsol.mobileinfo.domain.category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>