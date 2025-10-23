package net.holosen.onlineshopapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.holosen.onlineshopapp.ui.component.app.ProductCategoriesRow
import net.holosen.onlineshopapp.ui.component.app.ProductsView
import net.holosen.onlineshopapp.ui.component.app.SlidersRow
import net.holosen.onlineshopapp.vm.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    vm: HomeViewModel = hiltViewModel()
) {
    Column(
        Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SlidersRow(vm)
        Spacer(Modifier.height(10.dp))
        ProductCategoriesRow(vm, navController)
        Spacer(Modifier.height(10.dp))
        ProductsView(vm, navController)
    }
}