package net.holosen.onlineshopapp.model.invoices

import net.holosen.onlineshopapp.model.products.Product

data class InvoiceItem(
    var id: Long? = null,
    var product: Product? = null,
    var quantity: Int? = null,
    var unitPrice: Long? = null
)