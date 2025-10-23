package net.holosen.onlineshopapp.repository.invoices

import net.holosen.onlineshopapp.api.invoices.TransactionApi
import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.invoices.PaymentTransaction
import net.holosen.onlineshopapp.repository.base.BaseRepository
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val api: TransactionApi
) : BaseRepository() {
    suspend fun goToPayment(data: PaymentTransaction): ApiResponse<String> =
        safeApiCall { api.goToPayment(data) }
}