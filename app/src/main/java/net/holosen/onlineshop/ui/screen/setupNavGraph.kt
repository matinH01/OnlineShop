package net.holosen.onlineshop.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute

@Composable
fun setupNavGraph(navController: NavHostController, innerPadding: PaddingValues): Boolean {
    var isFullScreen by remember { mutableStateOf(false) }
    NavHost(navController, startDestination = AppDestination.Home) {
        composable<AppDestination.Home> {
            isFullScreen = false
            HomeScreen(
                onNavigateToProducts = { id, title ->
                    navController.navigate(
                        AppDestination.Products(id, title)
                    )
                },
                onNavigateToProduct = { id ->
                    navController.navigate(
                        AppDestination.Product(id)
                    )
                }
            )
        }
        composable<AppDestination.Basket> {
            isFullScreen = false
            BasketScreen(
                onNavigateToHome = {
                    navController.navigate(AppDestination.Home)
                },
                onNavigateToUserPayment = {
                    navController.navigate(AppDestination.UserPayment)
                }
            )
        }
        composable<AppDestination.UserPayment> {
            isFullScreen = false
            UserPaymentScreen(
                onPopBack = { navController.popBackStack() },
                onNavigateToHome = {
                    navController.navigate(AppDestination.Home) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            )
        }
        composable<AppDestination.Login> {
            isFullScreen = true
            LoginScreen {
                navController.navigate(AppDestination.Profile) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        }
        composable<AppDestination.Profile> {
            isFullScreen = false
            ProfileScreen(
                onNavigateToInvoices = {
                    navController.navigate(AppDestination.Invoices)
                },
                onNavigateToHome = {
                    navController.navigate(AppDestination.Home) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            )
        }
        composable<AppDestination.Invoices> {
            isFullScreen = false
            InvoicesScreen { id ->
                navController.navigate(
                    AppDestination.Invoice(id)
                )
            }
        }
        composable<AppDestination.Products> { backStackEntry ->
            isFullScreen = false
            val args = backStackEntry.toRoute<AppDestination.Products>()
            ProductsScreen(args.catId, args.title) { id ->
                navController.navigate(AppDestination.Product(id))
            }
        }
        composable<AppDestination.Product> { backStackEntry ->
            isFullScreen = true
            val args = backStackEntry.toRoute<AppDestination.Product>()
            SingleProductScreen(args.id, innerPadding) {
                navController.popBackStack()
            }
        }
        composable<AppDestination.Invoice>(
            deepLinks = listOf(navDeepLink {
                uriPattern = "app://onlineshopholosen.ir/{id}"
            })
        ) { backStackEntry ->
            isFullScreen = false
            val args = backStackEntry.toRoute<AppDestination.Invoice>()
            SingleInvoiceScreen(args.id)
        }
    }
    return isFullScreen
}