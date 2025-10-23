package net.holosen.onlineshop.api.invoices

import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.invoices.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {

    @POST("trx/gotoPayment")
    suspend fun goToPayment(
        @Body data: PaymentTransaction
    ): ApiResponse<String>
}