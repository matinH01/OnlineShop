package net.holosen.onlineshop.ui.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.holosen.onlineshop.ui.component.animation.AnimatedSlideIn
import net.holosen.onlineshop.ui.component.graphic.AppGradient
import net.holosen.onlineshop.ui.component.app.AppImage
import net.holosen.onlineshop.ui.component.app.PriceText
import net.holosen.onlineshop.vm.BasketViewModel
import net.holosen.onlineshop.vm.SingleProductViewModel

@Composable
fun SingleProductScreen(
    id: Long,
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: SingleProductViewModel = hiltViewModel(),
    basketVM: BasketViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(id) {
        vm.loadProduct(id)
    }

    Box(Modifier.fillMaxSize()) {
        AppImage(vm.product?.image ?: "", description = vm.product?.title ?: "")
        AppGradient(
            modifier = Modifier
                .height(800.dp)
                .align(Alignment.BottomCenter)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }, modifier = Modifier.align(Alignment.TopStart)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                AnimatedSlideIn {
                    Text(
                        vm.product?.title ?: "",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(10.dp))
                AnimatedSlideIn(200) {
                    PriceText(vm.product?.price ?: 0)
                }
                Spacer(Modifier.height(25.dp))

                ProductSizesRow(vm)

                Spacer(Modifier.height(25.dp))

                ProductColorsRow(vm)

                Spacer(Modifier.height(25.dp))

                AnimatedSlideIn(1800, durationMillis = 3000) {
                    Text(
                        vm.product?.description ?: "",
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                }

                Spacer(Modifier.height(30.dp))

                AnimatedSlideIn(3000) {
                    Button(
                        onClick = {
                            basketVM.addToBasket(vm.product, vm.selectedColor, vm.selectedSize)
                            Toast.makeText(
                                context,
                                "به سبد خرید اضافه شد",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.popBackStack()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            "اضافه به سبد خرید",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun ProductSizesRow(vm: SingleProductViewModel) {
    AnimatedSlideIn(400) {
        Text(
            "سایز",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(Modifier.height(10.dp))
    LazyRow {
        itemsIndexed(vm.product?.sizes ?: listOf()) { index, item ->
            AnimatedSlideIn(600 + (index * 200)) {
                TextButton(
                    onClick = {
                        vm.selectedSize = item
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (vm.selectedSize == item) Color.White else Color.Transparent,
                        contentColor = if (vm.selectedSize == item) Color.Black else Color.White
                    )
                ) {
                    Text(item.title ?: "", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.width(5.dp))
        }
    }
}

@Composable
fun ProductColorsRow(vm: SingleProductViewModel) {
    AnimatedSlideIn(1000) {
        Text(
            "رنگ بندی",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(Modifier.height(10.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(vm.product?.colors ?: listOf()) { index, item ->
            AnimatedSlideIn(1200 + (index * 200)) {
                TextButton(
                    onClick = {
                        vm.selectedColor = item
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color("#${item.hexValue}".toColorInt())
                    ),
                    modifier = Modifier.size(40.dp),
                    border = BorderStroke(
                        2.dp,
                        if (vm.selectedColor == item) Color.Red else Color.White
                    )
                ) {
                    if (vm.selectedColor == item) {
                        Icon(Icons.Filled.Check, contentDescription = "Selected Color")
                    }
                }
            }
        }
    }
}
