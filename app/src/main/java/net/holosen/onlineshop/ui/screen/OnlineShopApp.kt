package net.holosen.onlineshop.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import net.holosen.onlineshop.ui.component.app.TopNavBar

@Composable
fun OnlineShopApp() {
    val navController = rememberNavController()
    var isFullScreen by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            if (!isFullScreen) {
                TopNavBar(
                    onNavigateToBasket = {
                        navController.navigate(AppDestination.Basket) {
                            launchSingleTop = true
                        }
                    },
                    onNavigateToLogin = {
                        navController.navigate(AppDestination.Login)
                    },
                    onNavigateToProfile = {
                        navController.navigate(AppDestination.Profile) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(if (!isFullScreen) innerPadding else PaddingValues(0.dp))
        ) {
            isFullScreen = setupNavGraph(navController, innerPadding)
        }
    }
}