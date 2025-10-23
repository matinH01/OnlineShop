package net.holosen.onlineshop.ui.component.app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.holosen.onlineshop.ui.utils.formatPrice

@Composable
fun PriceText(
    price: Long,
    priceColor: Color = Color.White,
    priceSize: TextUnit = 26.sp,
    tomanColor: Color = Color.LightGray,
    tomanSize: TextUnit = 16.sp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            formatPrice(price),
            color = priceColor,
            fontSize = priceSize
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = "تومان",
            color = tomanColor,
            fontSize = tomanSize
        )
    }
}