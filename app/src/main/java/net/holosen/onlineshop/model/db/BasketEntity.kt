package net.holosen.onlineshop.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Long,
    val sizeId: Long,
    val colorId: Long,
    val quantity: Int,
    val image: String?,
    val price: Long?,
    val title: String?,
    val colorHex: String?,
    val size: String?
)