package net.holosen.onlineshop.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.holosen.onlineshop.model.invoices.InvoiceItem
import net.holosen.onlineshop.ui.component.app.AppImage
import net.holosen.onlineshop.ui.component.app.Loading
import net.holosen.onlineshop.ui.component.app.StatusBadge
import net.holosen.onlineshop.ui.theme.AppDarkGray
import net.holosen.onlineshop.ui.utils.formatPrice
import net.holosen.onlineshop.vm.SingleInvoiceViewModel
import net.holosen.onlineshop.vm.UserViewModel

@Composable
fun SingleInvoiceScreen(
    id: Long,
    navController: NavHostController,
    vm: SingleInvoiceViewModel = hiltViewModel(),
    userVM: UserViewModel = hiltViewModel()
) {
    val currentUser by userVM.currentUser.collectAsState()

    if (currentUser != null) {
        LaunchedEffect(currentUser) {
            vm.loadInvoice(id, currentUser?.token!!)
        }
    }

    if (vm.isLoading || vm.invoice == null) {
        Loading(modifier = Modifier.fillMaxSize())
    } else {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    "سفارش #${vm.invoice?.id}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )

                Spacer(Modifier.height(8.dp))

                StatusBadge(vm.invoice?.status ?: "")

                Spacer(Modifier.height(16.dp))

                Text("اقلام سفارش", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            items(vm.invoice?.items!!) {
                OrderItemCard(it)
                Spacer(Modifier.height(12.dp))
            }

            item {
                Text("تاریخ ثبت سفارش: ${vm.invoice?.addDate}")
                if (vm.invoice?.paymentDate!!.isNotBlank()) {
                    Text("تاریخ پرداخت: ${vm.invoice?.paymentDate}")
                }
            }
        }
    }
}

@Composable
fun OrderItemCard(item: InvoiceItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                if (isSystemInDarkTheme()) AppDarkGray else Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                AppImage(
                    item.product?.image!!,
                    description = item.product?.title!!
                )
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(item.product?.title!!, fontWeight = FontWeight.Bold)
                Text(
                    item.product?.description?.take(80) + "...",
                    fontSize = 12.sp,
                    maxLines = 2,
                    lineHeight = 18.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Qty: ${item.quantity} x ${formatPrice(item.unitPrice ?: 0)}",
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}











