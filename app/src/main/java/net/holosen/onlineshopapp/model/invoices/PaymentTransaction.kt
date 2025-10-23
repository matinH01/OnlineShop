package net.holosen.onlineshopapp.model.invoices

import net.holosen.onlineshopapp.model.customer.UserDto

data class PaymentTransaction(
    var items: List<InvoiceItem> = listOf(),
    var user: UserDto? = null
)
