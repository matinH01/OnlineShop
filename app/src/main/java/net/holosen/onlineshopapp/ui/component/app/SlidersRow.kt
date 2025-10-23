package net.holosen.onlineshopapp.ui.component.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.holosen.onlineshopapp.ui.component.animation.AnimatedSlideIn
import net.holosen.onlineshopapp.ui.component.basic.DataUiStateHandler
import net.holosen.onlineshopapp.vm.HomeViewModel

@Composable
fun SlidersRow(vm: HomeViewModel) {
    DataUiStateHandler(
        state = vm.sliders,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { sliders ->
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(sliders) { index, slider ->
                AnimatedSlideIn(index * 100) {
                    AppCard(
                        image = slider.image,
                        title = slider.title,
                        subtitle = slider.subTitle,
                        modifier = Modifier
                            .width(300.dp)
                            .height(200.dp)
                    )
                }
            }
        }
    }
}