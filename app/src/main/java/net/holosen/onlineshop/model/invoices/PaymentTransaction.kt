package net.holosen.onlineshop.model.invoices

import net.holosen.onlineshop.model.customer.UserDto

data class PaymentTransaction(
    var items: List<InvoiceItem> = listOf(),
    var user: UserDto? = null
)
