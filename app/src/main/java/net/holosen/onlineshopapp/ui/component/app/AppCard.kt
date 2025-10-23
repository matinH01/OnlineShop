package net.holosen.onlineshopapp.ui.component.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.holosen.onlineshopapp.ui.component.graphic.AppGradient

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    image: String? = null,
    title: String? = null,
    subtitle: String? = null,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = { onClick() }
    ) {
        Box(Modifier.fillMaxSize()) {
            AppImage(image ?: "", title ?: "")
            AppGradient(
                Modifier
                    .height(70.dp)
                    .align(Alignment.BottomCenter)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    title ?: "",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                if (subtitle != null) {
                    Text(
                        subtitle ?: "",
                        color = Color.White
                    )
                }
            }
        }
    }
}