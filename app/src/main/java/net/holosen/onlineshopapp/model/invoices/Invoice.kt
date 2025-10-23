package net.holosen.onlineshopapp.model.invoices

import net.holosen.onlineshopapp.model.customer.User

data class Invoice(
    var id: Long?,
    var addDate: String?,
    var paymentDate: String?,
    var status: String?,
    var user: User? = null,
    var items: List<InvoiceItem>? = listOf()
)
