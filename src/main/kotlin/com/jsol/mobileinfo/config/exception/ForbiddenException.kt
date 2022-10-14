package com.jsol.mobileinfo.config.exception


class ForbiddenException @JvmOverloads constructor(
    //TODO 변경하기
    message: String = "권한이 없습니다.",
//        + AccountServiceImpl.getAccountFromSecurityContext().getRole()
) : BasicException(403, "forbidden", message)
