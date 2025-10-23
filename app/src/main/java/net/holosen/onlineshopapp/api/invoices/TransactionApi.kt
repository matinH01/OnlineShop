package net.holosen.onlineshopapp.api.invoices

import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.invoices.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {

    @POST("trx/gotoPayment")
    suspend fun goToPayment(
        @Body data: PaymentTransaction
    ): ApiResponse<String>
}