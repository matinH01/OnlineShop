package net.holosen.onlineshop.model.invoices.mapper

import net.holosen.onlineshop.model.db.BasketEntity
import net.holosen.onlineshop.model.invoices.InvoiceItem
import net.holosen.onlineshop.model.products.Product
import net.holosen.onlineshop.model.products.ProductColor
import net.holosen.onlineshop.model.products.ProductSize

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