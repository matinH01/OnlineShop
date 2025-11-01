package net.holosen.onlineshop.ui.screen

import kotlinx.serialization.Serializable

sealed interface AppDestination {
    @Serializable
    data object Home : AppDestination
    @Serializable
    data object Basket : AppDestination
    @Serializable
    data object UserPayment : AppDestination
    @Serializable
    data object Login : AppDestination
    @Serializable
    data object Profile : AppDestination
    @Serializable
    data object Invoices : AppDestination
    @Serializable
    data class Products(
        val catId: Long,
        val title: String
    ) : AppDestination

    @Serializable
    data class Product(
        val id: Long
    ) : AppDestination

    @Serializable
    data class Invoice(
        val id: Long
    ) : AppDestination
}