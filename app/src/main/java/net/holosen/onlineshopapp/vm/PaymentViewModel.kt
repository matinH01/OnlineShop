package net.holosen.onlineshopapp.vm

import android.net.Uri
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.holosen.onlineshopapp.api.invoices.TransactionApi
import net.holosen.onlineshopapp.model.customer.UserDto
import net.holosen.onlineshopapp.model.db.BasketEntity
import net.holosen.onlineshopapp.model.invoices.InvoiceItem
import net.holosen.onlineshopapp.model.invoices.PaymentTransaction
import net.holosen.onlineshopapp.model.invoices.mapper.toInvoiceItem
import net.holosen.onlineshopapp.repository.basket.BasketEntityRepository
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val api: TransactionApi,
    private val basketEntityRepository: BasketEntityRepository
) : BaseViewModel() {

    fun gotoPayment(
        userDto: UserDto,
        basketItems: List<BasketEntity>,
        loginVM: LoginViewModel,
        onLoading: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: (Uri) -> Unit
    ) {
        val items = mutableListOf<InvoiceItem>()
        basketItems.forEach {
            items.add(it.toInvoiceItem())
        }
        val payTrx = PaymentTransaction(
            items = items,
            user = userDto
        )

        loadData(
            stateSetter = {
                when {
                    it.isLoading -> onLoading()
                    it.error != null -> onError(it.error)
                    it.data != null -> {
                        //login
                        loginVM.login(
                            userDto.username!!,
                            userDto.password!!,
                            {}, {}, {}
                        )
                        //clear basket
                        viewModelScope.launch(Dispatchers.IO) {
                            basketEntityRepository.deleteAll()
                        }
                        onSuccess(it.data[0].toUri())
                    }
                }
            }
        ) {
            api.goToPayment(payTrx)
        }
    }
}