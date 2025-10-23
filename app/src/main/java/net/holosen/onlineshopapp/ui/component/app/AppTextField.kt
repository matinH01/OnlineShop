package net.holosen.onlineshopapp.ui.component.app

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    focusManager: FocusManager,
    isError: Boolean,
    errorText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(3.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = singleLine,
        shape = shape,
        colors = colors,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        isError = isError,
        visualTransformation = visualTransformation,
        enabled = enabled,
        trailingIcon = {
            if (isError) {
                Icon(
                    Icons.Filled.Warning,
                    tint = Color.Red,
                    contentDescription = "Error"
                )
            }
        }
    )

    if (isError) {
        Text(errorText, color = Color.Red, fontSize = 12.sp)
    }
}