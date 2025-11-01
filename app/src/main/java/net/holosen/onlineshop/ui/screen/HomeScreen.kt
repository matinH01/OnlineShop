package net.holosen.onlineshop.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.holosen.onlineshop.ui.component.app.ProductCategoriesRow
import net.holosen.onlineshop.ui.component.app.ProductsView
import net.holosen.onlineshop.ui.component.app.SlidersRow
import net.holosen.onlineshop.vm.HomeViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    onNavigateToProducts: (Long, String) -> Unit,
    onNavigateToProduct: (Long) -> Unit
) {
    Column(
        Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SlidersRow(vm)
        Spacer(Modifier.height(10.dp))
        ProductCategoriesRow(vm) { id, title ->
            onNavigateToProducts(id, title)
        }
        Spacer(Modifier.height(10.dp))
        ProductsView(vm) { id ->
            onNavigateToProduct(id)
        }
    }
}