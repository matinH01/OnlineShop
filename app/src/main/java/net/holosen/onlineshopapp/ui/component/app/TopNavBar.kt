package net.holosen.onlineshopapp.ui.component.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.holosen.onlineshopapp.R
import net.holosen.onlineshopapp.ui.component.animation.AnimatedSlideIn
import net.holosen.onlineshopapp.vm.BasketViewModel
import net.holosen.onlineshopapp.vm.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(
    navController: NavHostController,
    basketVM: BasketViewModel = hiltViewModel(),
    userVM: UserViewModel = hiltViewModel()
) {
    val basket by basketVM.basket.collectAsState()
    val currentUser by userVM.currentUser.collectAsState()

    TopAppBar(
        title = {
            AnimatedSlideIn(300) {
                Image(
                    painterResource(if (isSystemInDarkTheme()) R.drawable.logow else R.drawable.logo),
                    contentDescription = "Online Shop",
                    modifier = Modifier.width(160.dp)
                )
            }
        }, actions = {
            AnimatedSlideIn(600) {
                IconButton(onClick = {
                    navController.navigate("basket") {
                        launchSingleTop = true
                    }
                }) {
                    BadgedBox(
                        badge = {
                            if (basket.isNotEmpty()) {
                                Badge {
                                    Text(basket.size.toString())
                                }
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Basket")
                    }
                }
            }
            AnimatedSlideIn(900) {
                IconButton(onClick = {
                    if (currentUser == null) {
                        navController.navigate("login")
                    } else {
                        navController.navigate("profile") {
                            launchSingleTop = true
                        }
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Basket")
                }
            }
        }
    )
}