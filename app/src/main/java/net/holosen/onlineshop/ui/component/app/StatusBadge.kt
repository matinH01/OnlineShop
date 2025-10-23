package net.holosen.onlineshop.ui.component.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.holosen.onlineshop.ui.theme.AppGray
import net.holosen.onlineshop.ui.theme.AppGreen
import net.holosen.onlineshop.ui.theme.AppRed

@Composable
fun StatusBadge(status: String) {
    val (text, color) = when (status) {
        "NotPayed" -> "در انتظار پرداخت" to AppRed
        "Payed" -> "پرداخت شده" to AppGreen
        else -> status to AppGray
    }

    Box(
        modifier = Modifier
            .background(
                color = color.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text, color = color, fontWeight = FontWeight.Bold)
    }
}