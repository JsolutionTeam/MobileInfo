package com.jsol.mobileinfo.domain.device.entity

import com.jsol.mobileinfo.domain.device.dto.request.DeviceUpdateRequest
import com.jsol.mobileinfo.domain.maker.entity.Maker
import javax.persistence.*

@Entity
@Table(
    name = "tb_device",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["pet_name"], name = "tb_device_pet_name_uk"),
        UniqueConstraint(columnNames = ["model_name"], name = "tb_device_model_name_uk"),
    ],
)
class Device(
    @Column(name="pet_name")
    var petName: String,

    @Column(name="model_name")
    var modelName: String,

    @Column(name="volume", length = 8)
    var volume: String,

    @Column(name="price")
    var price: Int,

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
        ]
    )
    @JoinColumn(name = "maker_id")
    var maker: Maker? = null,

    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    fun update(request: DeviceUpdateRequest) {
        if(!request.petName.isNullOrBlank()){
            this.petName = request.petName
        }
        if(!request.modelName.isNullOrBlank()){
            this.modelName = request.modelName
        }
        if(request.volumeValue != null){
            this.volume = this.volume.replace("[0-9]".toRegex(), request.volumeValue.toString())
        }
        if(request.volumeType != null){
            this.volume = this.volume.replace("[a-z]|[A-Z]".toRegex(), request.volumeType.name)
        }
        if(request.price != null){
            this.price = request.price
        }
    }

    fun updateMaker(maker: Maker?){
        this.maker = maker
    }
}
