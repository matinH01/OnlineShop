package net.holosen.onlineshop.ui.component.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.holosen.onlineshop.ui.component.animation.AnimatedSlideIn
import net.holosen.onlineshop.ui.component.basic.DataUiStateHandler
import net.holosen.onlineshop.vm.HomeViewModel

@Composable
fun ProductCategoriesRow(
    vm: HomeViewModel,
    onNavigateToProducts: (Long, String) -> Unit
) {
    DataUiStateHandler(
        state = vm.productCategories,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { categories ->
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(categories) { index, item ->
                AnimatedSlideIn(index * 100) {
                    AppCard(
                        image = item.image,
                        title = item.title,
                        modifier = Modifier
                            .width(160.dp)
                            .height(200.dp),
                        onClick = {
                            onNavigateToProducts(item.id ?: 0, item.title ?: "")
                        }
                    )
                }
            }
        }
    }
}