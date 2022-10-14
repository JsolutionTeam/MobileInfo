package com.jsol.mobileinfo.config.exception

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.NoArgsConstructor
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import java.time.LocalDateTime
import java.util.function.Consumer

@Getter
@NoArgsConstructor
class ApiErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    var code: String = "error-unknown",// 예외를 세분화하기 위한 사용자 지정 코드,
    var message: String = "",
    var status: Int = 0, // HTTP 상태 값 저장 400, 404, 500 등..
) {


    //예외처리 메시지
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
    private var customFieldErrors: MutableList<CustomFieldError> = ArrayList()

    fun code(code: String): ApiErrorResponse {
        this.code = code
        return this
    }

    fun status(status: Int): ApiErrorResponse {
        this.status = status
        return this
    }

    fun message(message: String): ApiErrorResponse {
        this.message = message
        return this
    }

    fun errors(errors: Errors): ApiErrorResponse {
        setCustomFieldErrors(errors.fieldErrors)
        return this
    }

    //BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors
    private fun setCustomFieldErrors(fieldErrors: List<FieldError>) {
        customFieldErrors = ArrayList<CustomFieldError>()
        fieldErrors.forEach(Consumer { error: FieldError ->
            customFieldErrors.add(
                CustomFieldError(
                    error.codes!![0],
                    error.rejectedValue,
                    error.defaultMessage
                )
            )
        })
    }

    //parameter 검증에 통과하지 못한 필드가 담긴 클래스이다.
    @NoArgsConstructor
    class CustomFieldError(val field: String, val value: Any?, val reason: String?)
    companion object {
        fun create(): ApiErrorResponse {
            return ApiErrorResponse()
        }
    }
}
