package com.jsol.mobileinfo.domain.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(msg: String): Nothing {
    throw IllegalArgumentException(msg)
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID, msg: String = "id로 데이터 조회에 실패했습니다. id : $id"): T {
    return this.findByIdOrNull(id) ?: fail(msg)
}
