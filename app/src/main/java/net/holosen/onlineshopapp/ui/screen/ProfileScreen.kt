package net.holosen.onlineshopapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import net.holosen.onlineshopapp.R
import net.holosen.onlineshopapp.ui.component.app.AppImage
import net.holosen.onlineshopapp.ui.component.app.ProfileActionItem
import net.holosen.onlineshopapp.ui.theme.AppDarkGray
import net.holosen.onlineshopapp.ui.theme.AppGray
import net.holosen.onlineshopapp.ui.theme.AppLightGray
import net.holosen.onlineshopapp.vm.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    userVM: UserViewModel = hiltViewModel()
) {
    val currentUser by userVM.currentUser.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppImage(
                R.drawable.avatar,
                description = "User",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .border(2.dp, AppGray, CircleShape),
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    "${currentUser?.firstName}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("@${currentUser?.username}", color = AppGray, fontSize = 14.sp)
            }
        }

        HorizontalDivider(color = AppLightGray)

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileActionItem(
                "سفارشات",
                icon = Icons.Filled.Star,
                onClick = {
                    navController.navigate("invoices")
                }
            )

            ProfileActionItem(
                "تغییر رمز عبور",
                icon = Icons.Filled.Lock,
                onClick = {}
            )

            ProfileActionItem(
                "درباره",
                icon = Icons.Filled.Info,
                onClick = {}
            )

            ProfileActionItem(
                "خروج",
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                onClick = {
                    userVM.logout()
                    navController.navigate("home") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                },
                color = Color.Red
            )
        }
    }
}