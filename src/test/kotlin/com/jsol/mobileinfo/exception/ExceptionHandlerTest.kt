package com.jsol.mobileinfo.exception

import org.junit.jupiter.api.Test
import org.springframework.dao.DataIntegrityViolationException

class ExceptionHandlerTest {

    @Test
    fun dataIntegrityViolationException() {
        try {
            throw DataIntegrityViolationException("ddd")
        } catch (ex: DataIntegrityViolationException) {
            println("ex.message = ${ex.message}")
            println("ex.cause = ${ex.cause}")
            println("ex.localizedMessage = ${ex.localizedMessage}")
            println("ex.suppressed = ${ex.suppressed}")
            println("ex.rootCause = ${ex.rootCause}")

            println("ex.rootCause?.message = ${ex.rootCause?.message}")
            println("ex.rootCause?.localizedMessage = ${ex.rootCause?.localizedMessage}")
            println("ex.rootCause?.cause = ${ex.rootCause?.cause}")
        }
    }
}
