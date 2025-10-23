package net.holosen.onlineshop.model.invoices

import net.holosen.onlineshop.model.customer.User

data class Invoice(
    var id: Long?,
    var addDate: String?,
    var paymentDate: String?,
    var status: String?,
    var user: User? = null,
    var items: List<InvoiceItem>? = listOf()
)
