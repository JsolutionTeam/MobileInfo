package com.jsol.mobileinfo.domain.color.entity

import com.jsol.mobileinfo.domain.category.entity.CategoryType
import com.jsol.mobileinfo.domain.color.dto.request.ColorUpdateRequest
import javax.persistence.*

@Entity
@Table(
    name = "tb_color",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["name"], name = "tb_color_name_uk"),
        UniqueConstraint(columnNames = ["name_eng"], name = "tb_color_name_eng_uk"),
    ],
)
class Color (

    @Column(name = "name")
    var name: String,

    @Column(name = "name_eng")
    var nameEng: String,

    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
){
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
        if (nameEng.isBlank()){
            throw IllegalArgumentException("영문명은 비어 있을 수 없습니다.")
        }
    }

    fun updateName(request: ColorUpdateRequest){
        if (!request.name.isNullOrBlank()) {
            this.name = request.name
        }
        if (!request.nameEng.isNullOrBlank()) {
            this.nameEng = request.nameEng
        }

    }

    override fun toString(): String{
        return "id=${this.id}, name=${this.name}, nameEng=${this.nameEng}"
    }

}
