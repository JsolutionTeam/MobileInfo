package com.jsol.mobileinfo.config.utils

import org.springframework.web.bind.annotation.RestController

class BasicControllerMethod {
    companion object{
        fun delete(condition: Boolean): String {
            return if(condition){
                ResponseMessages.DELETE_SUCCESSED
            }else{
                ResponseMessages.DELETE_FAILED
            }
        }
    }
}
