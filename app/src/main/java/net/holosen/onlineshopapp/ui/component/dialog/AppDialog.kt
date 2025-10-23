package net.holosen.onlineshopapp.ui.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import net.holosen.onlineshopapp.ui.theme.iranSans

@Composable
fun AppDialog(
    showDialog: Boolean,
    title: String = "حذف",
    text: String = "آیا میخواهید این آیتم را از سبد خرید خود حذف کنید؟",
    confirmButtonText: String = "بله حذف کن",
    cancelButtonText: String = "خیر",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(title, fontFamily = iranSans) },
            text = { Text(text, fontFamily = iranSans) },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(confirmButtonText, color = Color.Red, fontFamily = iranSans)
                }
            },
            dismissButton = {
                TextButton(onClick = { onCancel() }) {
                    Text(cancelButtonText, fontFamily = iranSans)
                }
            }
        )
    }
}