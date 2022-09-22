package com.jsol.mobileinfo.domain.maker.repository

import com.querydsl.jpa.impl.JPAQueryFactory

class MakerRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : MakerRepositoryCustom {
}