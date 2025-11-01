package net.holosen.onlineshop.ui.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import net.holosen.onlineshop.ui.theme.iranYekan

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
            title = { Text(title, fontFamily = iranYekan) },
            text = { Text(text, fontFamily = iranYekan) },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(confirmButtonText, color = Color.Red, fontFamily = iranYekan)
                }
            },
            dismissButton = {
                TextButton(onClick = { onCancel() }) {
                    Text(cancelButtonText, fontFamily = iranYekan)
                }
            }
        )
    }
}