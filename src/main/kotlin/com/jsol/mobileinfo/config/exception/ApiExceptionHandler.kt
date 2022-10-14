package com.jsol.mobileinfo.config.exception

import com.jsol.mobileinfo.config.exception.storage.StorageException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(ApiExceptionHandler::class.java)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ApiErrorResponse> {

        val message = "request body가 처리 할 수 없는 값입니다.. 확인해주세요."
        logger.error("HttpMessageNotReadableException - message : $message")

        val response = ApiErrorResponse
            .create()
            .status(400)
            .code("error-request-body-missing-400")
            .message(message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(StorageException::class)
    fun storageException(ex: StorageException): ResponseEntity<ApiErrorResponse> {
        val message = ex.message ?: "저장 중 에러발생"
        logger.error("StorageException - message : $message")

        val response = ApiErrorResponse
            .create()
            .status(500)
            .code("error-storage-404")
            .message(message)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<*> {
        val message = e.bindingResult
            .allErrors[0]
            .defaultMessage ?: "서버에서 처리 중 에러 발생"
        logger.error("MethodArgumentNotValidException - message : $message")
        return ResponseEntity(message, HttpStatus.BAD_REQUEST)
    }

    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(InvalidParameterException::class)
    protected fun handleInvalidParameterException(e: InvalidParameterException): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse
            .create()
            .status(400)
            .code(e.code)
            .message(e.toString())
            .errors(e.errors)
        return ResponseEntity(response, HttpStatus.resolve(response.status)!!)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun dataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ApiErrorResponse> {
        logger.error("ex.message = ${ex.message}")
        logger.error("ex.cause = ${ex.cause}")
        logger.error("ex.localizedMessage = ${ex.localizedMessage}")
        logger.error("ex.suppressed = ${ex.suppressed}")
        logger.error("ex.rootCause = ${ex.rootCause}")
        logger.error("ex.rootCause?.message = ${ex.rootCause?.message}")
        logger.error("ex.rootCause?.localizedMessage = ${ex.rootCause?.localizedMessage}")
        logger.error("ex.rootCause?.cause = ${ex.rootCause?.cause}")

        val split: List<String>? = ex.rootCause?.localizedMessage?.split(":")
        var message = "데이터베이스 제약조건 오류가 발생했습니다."

        if (!split.isNullOrEmpty()) {
            message = split[0]
            if (message.lowercase().contains("unique") || message.lowercase().contains("primary"))
                message = "기본키/유니크키는 중복될 수 없습니다."
            logger.error("근본 오류 메세지 = $message")
        }

        val response = ApiErrorResponse
            .create()
            .status(400)
            .code("error-value-valid")
            .message(message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BasicException::class)
    fun unauthorizedException(ex: BasicException): ResponseEntity<ApiErrorResponse> {
        ex.printStackTrace()
        val response = ApiErrorResponse
            .create()
            .status(ex.status)
            .code(ex.code)
            .message(ex.message)
        return ResponseEntity(response, HttpStatus.resolve(ex.status)!!)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    protected fun illegalArgumentExceptionHandleException(e: IllegalArgumentException?): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse
            .create()
            .status(500)
            .code(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
            .message("서버 오류")
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    //모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ResponseEntity<ApiErrorResponse> {
        val response = ApiErrorResponse
            .create()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value().toString().toInt())
            .code(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
            .message(e.message ?: "알 수 없는 에러 발생")
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
