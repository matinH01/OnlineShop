package net.holosen.onlineshop.ui.screen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.holosen.onlineshop.model.db.BasketEntity
import net.holosen.onlineshop.ui.component.animation.AnimatedSlideIn
import net.holosen.onlineshop.ui.component.app.AppImage
import net.holosen.onlineshop.ui.component.app.PriceText
import net.holosen.onlineshop.ui.component.dialog.AppDialog
import net.holosen.onlineshop.ui.theme.AppGreen
import net.holosen.onlineshop.vm.BasketViewModel

@Composable
fun BasketScreen(
    vm: BasketViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToUserPayment: () -> Unit
) {
    val basket by vm.basket.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<BasketEntity?>(null) }
    val totalPrice = basket.sumOf { (it.price ?: 0) * it.quantity }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("سبد خرید", fontWeight = FontWeight.Bold, fontSize = 24.sp)

        Spacer(Modifier.height(10.dp))

        if (basket.isEmpty()) {
            Text("سبد خرید شما خالی است!")
        } else {
            LazyColumn(Modifier.weight(1f)) {
                itemsIndexed(basket) { index, item ->
                    AnimatedSlideIn(index * 200) {
                        Column {
                            BasketItemRow(
                                item,
                                onIncrease = { vm.increaseQuantity(item) },
                                onDecrease = { vm.decreaseQuantity(item) },
                                onRemove = {
                                    showDialog = true
                                    itemToDelete = item
                                })
                            HorizontalDivider()
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AnimatedSlideIn(1200) {
                    Text("مجموع", fontWeight = FontWeight.Bold)
                }
                AnimatedSlideIn(1400) {
                    PriceText(
                        totalPrice,
                        priceColor = Color.Black,
                        priceSize = 16.sp,
                        tomanColor = Color.Gray,
                        tomanSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            AnimatedSlideIn(1600) {
                Row(Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            onNavigateToHome()
                        },
                        modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("ادامه خرید")
                    }
                    Spacer(Modifier.width(10.dp))
                    Button(
                        onClick = {
                         onNavigateToUserPayment()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppGreen
                        )
                    ) {
                        Text("پرداخت")
                    }
                }
            }
        }
    }

    AppDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onCancel = { showDialog = false },
        onConfirm = {
            showDialog = false
            vm.deleteItem(item = itemToDelete!!)
        })
}

@Composable
fun BasketItemRow(
    item: BasketEntity,
    onIncrease: (BasketEntity) -> Unit,
    onDecrease: (BasketEntity) -> Unit,
    onRemove: (BasketEntity) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AnimatedSlideIn(400) {
                Card(Modifier.size(60.dp)) {
                    AppImage(model = item.image ?: "", item.title ?: "")
                }
            }

            Column {
                AnimatedSlideIn(500) {
                    Text(item.title ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                AnimatedSlideIn(600) {
                    PriceText(
                        price = ((item.price ?: 0) * item.quantity),
                        priceColor = Color.Black,
                        priceSize = 16.sp,
                        tomanColor = Color.Gray,
                        tomanSize = 11.sp
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Column {
                AnimatedSlideIn(700) {
                    Text("سایز:${item.size}", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                AnimatedSlideIn(800) {
                    Card(
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color("#${item.colorHex}".toColorInt())
                        ),
                        modifier = Modifier
                            .height(25.dp)
                            .width(45.dp)
                    ) { }
                }
            }
        }

        AnimatedSlideIn(1000) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        onDecrease(item)
                    }) {
                        Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Decrease")
                    }
                    Text("${item.quantity}", modifier = Modifier.padding(8.dp))
                    IconButton(onClick = {
                        onIncrease(item)
                    }) {
                        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increase")
                    }
                }

                IconButton(onClick = {
                    onRemove(item)
                }) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}







