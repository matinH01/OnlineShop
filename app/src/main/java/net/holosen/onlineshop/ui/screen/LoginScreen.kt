package net.holosen.onlineshop.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import net.holosen.onlineshop.ui.component.app.AppTextField
import net.holosen.onlineshop.ui.theme.AppDarkGray
import net.holosen.onlineshop.vm.LoginViewModel

@Composable
fun LoginScreen(
    vm: LoginViewModel = hiltViewModel(),
    onNavigateToProfile: () -> Unit
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var username by remember { mutableStateOf(TextFieldValue("")) }
    var usernameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        AppDarkGray,
                        Color.Black
                    ),
                )
            )
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    "خوش آمدید 👋",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                AppTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        usernameError = false
                    },
                    label = "نام کاربری",
                    focusManager = focusManager,
                    isError = usernameError,
                    errorText = "Please enter your username",
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White
                    )
                )

                AppTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = "رمز عبور",
                    focusManager = focusManager,
                    isError = passwordError,
                    errorText = "Please enter your password",
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    imeAction = ImeAction.Done,
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White
                    )
                )

                Button(
                    onClick = {
                        usernameError = username.text.isEmpty()
                        passwordError = password.text.isEmpty()
                        if (usernameError || passwordError) {
                            return@Button
                        }
                        vm.login(
                            username.text,
                            password.text,
                            onLoading = { isLoading = true },
                            onError = {
                                isLoading = false
                                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                            },
                            onSuccess = {
                                isLoading = false
                                Toast.makeText(
                                    context,
                                    "${it.firstName} عزیز. خوش آمدید.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                onNavigateToProfile()
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Text("ورود")
                    }
                }

            }
        }
    }
}