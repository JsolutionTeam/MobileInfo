package com.jsol.mobileinfo.domain.maker.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    name="tb_maker",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["name"], name = "tb_maker_name_uk")
    ]
)

class Maker(
    var name: String,

    @Id
    @Column(name = "maker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }

    fun updateName(name: String){
        this.name = name
    }
}