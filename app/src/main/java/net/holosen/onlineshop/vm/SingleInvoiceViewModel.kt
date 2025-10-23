package net.holosen.onlineshop.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.holosen.onlineshop.model.invoices.Invoice
import net.holosen.onlineshop.repository.invoices.InvoiceRepository
import javax.inject.Inject

@HiltViewModel
class SingleInvoiceViewModel @Inject constructor(
    private val repository: InvoiceRepository
) : BaseViewModel() {
    var invoice by mutableStateOf<Invoice?>(null)
    var isLoading by mutableStateOf(false)

    fun loadInvoice(id: Long, token: String) {
        loadData(stateSetter = {
            isLoading = it.isLoading
            it.data?.firstOrNull()?.let { i ->
                invoice = i
            }
        }) {
            repository.getInvoiceById(id, token)
        }
    }
}