package net.holosen.onlineshopapp.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.holosen.onlineshopapp.model.invoices.Invoice
import net.holosen.onlineshopapp.model.products.Product
import net.holosen.onlineshopapp.repository.customer.UserEntityRepository
import net.holosen.onlineshopapp.repository.invoices.InvoiceRepository
import net.holosen.onlineshopapp.ui.state.DataUiState
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val repository: InvoiceRepository
) : BaseViewModel() {
    var invoices by mutableStateOf<DataUiState<Invoice>>(DataUiState())
        private set

    private var pageIndex = 0
    private val pageSize = 20
    private var endReached = false

    fun loadInvoices(userId: Long, token: String) {
        if (invoices.isLoading || endReached) return

        loadData(stateSetter = {
            if (it.isLoading) {
                invoices = invoices.copy(isLoading = true)
            } else {
                if (it.data?.isEmpty() == true) {
                    endReached = true
                }
                pageIndex++
                val current = invoices.data?.toMutableList() ?: mutableListOf()
                current.addAll(it.data ?: listOf())
                invoices = invoices.copy(isLoading = false, data = current)
            }
        }) {
            repository.getInvoiceByUserId(userId, pageIndex, pageSize, token)
        }
    }
}