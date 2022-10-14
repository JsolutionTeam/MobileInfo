package com.jsol.mobileinfo.domain.maker.entity

import javax.persistence.*

@Entity
@Table(
    name = "tb_maker",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["name"], name = "tb_maker_name_uk")
    ]
)
class Maker(
    @Column(name = "name")
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

    fun updateName(name: String) {
        this.name = name
    }
}
