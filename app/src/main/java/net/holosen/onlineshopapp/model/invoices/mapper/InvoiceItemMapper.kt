package net.holosen.onlineshopapp.model.invoices.mapper

import net.holosen.onlineshopapp.model.db.BasketEntity
import net.holosen.onlineshopapp.model.invoices.InvoiceItem
import net.holosen.onlineshopapp.model.products.Product
import net.holosen.onlineshopapp.model.products.ProductColor
import net.holosen.onlineshopapp.model.products.ProductSize

fun BasketEntity.toInvoiceItem(): InvoiceItem {
    return InvoiceItem(
        product = Product(
            id = this.productId,
            colors = listOf(ProductColor(id = this.colorId)),
            sizes = listOf(ProductSize(id = this.sizeId))
        ),
        quantity = this.quantity
    )
}