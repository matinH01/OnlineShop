package net.holosen.onlineshop.ui.screen

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import net.holosen.onlineshop.model.customer.UserDto
import net.holosen.onlineshop.ui.component.app.AppTextField
import net.holosen.onlineshop.vm.BasketViewModel
import net.holosen.onlineshop.vm.LoginViewModel
import net.holosen.onlineshop.vm.PaymentViewModel
import net.holosen.onlineshop.vm.UserViewModel

@Composable
fun UserPaymentScreen(
    navController: NavHostController,
    basketVM: BasketViewModel = hiltViewModel(),
    payVM: PaymentViewModel = hiltViewModel(),
    loginVM: LoginViewModel = hiltViewModel(),
    userVM: UserViewModel = hiltViewModel()
) {
    val basket by basketVM.basket.collectAsState()
    val currentUser by userVM.currentUser.collectAsState()

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var lastNameError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var phoneError by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var usernameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordError by remember { mutableStateOf(false) }
    var postalCode by remember { mutableStateOf(TextFieldValue("")) }
    var postalCodeError by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var addressError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(currentUser) {
        currentUser?.let {
            firstName = TextFieldValue(it.firstName ?: "")
            lastName = TextFieldValue(it.lastName ?: "")
            phone = TextFieldValue(it.phone ?: "")
            username = TextFieldValue(it.username ?: "")
            postalCode = TextFieldValue(it.postalCode ?: "")
            address = TextFieldValue(it.address ?: "")
        }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(Modifier.width(5.dp))
            Text(
                "اطلاعات خود را تکمیل کنید",
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
        }
        LazyColumn {
            item {
                Column(Modifier.padding(20.dp)) {
                    AppTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            firstNameError = false
                        },
                        label = "نام",
                        focusManager = focusManager,
                        isError = firstNameError,
                        errorText = "لطفا نام خود را وارد کنید"
                    )
                    Spacer(Modifier.height(10.dp))

                    AppTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            lastNameError = false
                        },
                        label = "نام خانوادگی",
                        focusManager = focusManager,
                        isError = lastNameError,
                        errorText = "Please enter your last name"
                    )
                    Spacer(Modifier.height(10.dp))

                    AppTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                            phoneError = false
                        },
                        label = "شماره همراه",
                        focusManager = focusManager,
                        isError = phoneError,
                        errorText = "Please enter your mobile",
                        keyboardType = KeyboardType.Phone
                    )
                    Spacer(Modifier.height(10.dp))

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
                        enabled = currentUser == null
                    )
                    Spacer(Modifier.height(10.dp))

                    if (currentUser == null) {
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
                            visualTransformation = PasswordVisualTransformation()
                        )
                        Spacer(Modifier.height(10.dp))
                    }

                    AppTextField(
                        value = postalCode,
                        onValueChange = {
                            postalCode = it
                            postalCodeError = false
                        },
                        label = "کد پستی",
                        focusManager = focusManager,
                        isError = postalCodeError,
                        errorText = "Please enter your postal code"
                    )
                    Spacer(Modifier.height(10.dp))

                    AppTextField(
                        value = address,
                        onValueChange = {
                            address = it
                            addressError = false
                        },
                        label = "آدرس",
                        focusManager = focusManager,
                        isError = addressError,
                        errorText = "Please enter your address",
                        singleLine = false,
                        imeAction = ImeAction.Done
                    )
                }
            }

            item {
                Box(Modifier.padding(20.dp)) {
                    Button(
                        onClick = {
                            firstNameError = firstName.text.isEmpty()
                            lastNameError = lastName.text.isEmpty()
                            phoneError = phone.text.isEmpty()
                            usernameError = username.text.isEmpty()
                            passwordError = currentUser == null && password.text.isEmpty()
                            postalCodeError = postalCode.text.isEmpty()
                            addressError = address.text.isEmpty()

                            if (firstNameError || lastNameError ||
                                phoneError || usernameError || passwordError ||
                                postalCodeError || addressError
                            ) {
                                return@Button
                            }

                            val userInfo = UserDto(
                                id = if (currentUser == null) null else currentUser?.userId,
                                customerId = if (currentUser == null) null else currentUser?.customerId,
                                username = username.text,
                                password = password.text,
                                firstName = firstName.text,
                                lastName = lastName.text,
                                phone = phone.text,
                                postalCode = postalCode.text,
                                address = address.text
                            )

                            payVM.gotoPayment(
                                userInfo,
                                basket,
                                loginVM,
                                onLoading = { isLoading = true },
                                onError = {
                                    isLoading = false
                                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                                },
                                onSuccess = {
                                    isLoading = false
                                    val intent = Intent(Intent.ACTION_VIEW, it)
                                    navController.navigate("home") {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = false
                                        }
                                        launchSingleTop = true
                                        restoreState = false
                                    }
                                    context.startActivity(intent)
                                }
                            )

                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator()
                        } else {
                            Text("پرداخت")
                        }
                    }
                }
            }
        }
    }
}