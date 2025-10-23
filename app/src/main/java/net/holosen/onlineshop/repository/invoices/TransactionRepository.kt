package net.holosen.onlineshop.repository.invoices

import net.holosen.onlineshop.api.invoices.TransactionApi
import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.invoices.PaymentTransaction
import net.holosen.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val api: TransactionApi
) : BaseRepository() {
    suspend fun goToPayment(data: PaymentTransaction): ApiResponse<String> =
        safeApiCall { api.goToPayment(data) }
}