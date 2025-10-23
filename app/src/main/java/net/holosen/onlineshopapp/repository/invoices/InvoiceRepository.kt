package net.holosen.onlineshopapp.repository.invoices

import net.holosen.onlineshopapp.api.invoices.InvoiceApi
import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.invoices.Invoice
import net.holosen.onlineshopapp.repository.base.BaseRepository
import javax.inject.Inject

class InvoiceRepository @Inject constructor(
    private val api: InvoiceApi
) : BaseRepository() {
    suspend fun getInvoiceById(id: Long, token: String): ApiResponse<Invoice> =
        safeApiCall { api.getInvoiceById(id, prepareToken(token)) }

    suspend fun getInvoiceByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int,
        token: String
    ): ApiResponse<Invoice> =
        safeApiCall { api.getInvoiceByUserId(userId, pageIndex, pageSize, prepareToken(token)) }
}