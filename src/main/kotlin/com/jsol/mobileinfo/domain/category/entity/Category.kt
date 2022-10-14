package com.jsol.mobileinfo.domain.category.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    name = "tb_category",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["name"], name = "tb_category_name_uk")
    ],
)
class Category(
    @Column(name="name")
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(name="type", length = 10)
    var type: CategoryType,

    @Id
    @Column(name = "category_id")
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

    fun updateType(type: CategoryType) {
        this.type = type
    }

    override fun toString(): String{
        return "id=${this.id}, name=${this.name}, type=${this.type}"
    }
}
