package net.holosen.onlineshop.repository.invoices

import net.holosen.onlineshop.api.invoices.InvoiceApi
import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.invoices.Invoice
import net.holosen.onlineshop.repository.base.BaseRepository
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