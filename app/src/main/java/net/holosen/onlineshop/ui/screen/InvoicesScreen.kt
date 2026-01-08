package net.holosen.onlineshop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.holosen.onlineshop.R
import net.holosen.onlineshop.ui.component.animation.AnimatedSlideIn
import net.holosen.onlineshop.ui.component.app.ProfileActionItem
import net.holosen.onlineshop.ui.theme.AppGreen
import net.holosen.onlineshop.vm.InvoiceViewModel
import net.holosen.onlineshop.vm.UserViewModel

@Composable
fun InvoicesScreen(
    vm: InvoiceViewModel = hiltViewModel(),
    userVM: UserViewModel = hiltViewModel(),
    onNavigateToInvoice: (Long) -> Unit
) {
    val currentUser by userVM.currentUser.collectAsState()

    val listState = rememberLazyListState()

    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisible >= totalItems - 2
        }
    }


    if (currentUser != null) {
        LaunchedEffect(shouldLoadMore) {
            if (shouldLoadMore && !vm.invoices.isLoading) {
                vm.loadInvoices(currentUser?.userId!!, currentUser?.token!!)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        AnimatedSlideIn {
            Text("سفارشات", fontWeight = FontWeight.Bold, fontSize = 26.sp)
        }
        Spacer(Modifier.height(20.dp))
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(vm.invoices.data ?: listOf()) { index, item ->
                AnimatedSlideIn(index * 100) {
                    ProfileActionItem(
                        "${item.addDate ?: "-"} (${item.status ?: "-"})",
                        icon =
                            if (item.status == "NotPayed") painterResource(R.drawable.close)
                            else painterResource(R.drawable.done),
                        onClick = {
                            onNavigateToInvoice(item.id ?: 0)
                        },
                        color = if (item.status == "NotPayed") Color.Red else AppGreen
                    )
                }
            }
        }
    }
}