package com.example.compose_example.core.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.core.utils.subTitleTextStyle


@Composable
fun OutlinedTextFieldWidget(
    inputValue: String,
    modifier: Modifier = Modifier,
    inputChanged: (value: String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector? = null,
    errorMessage: String = "",
    onTrailingClicked: (() -> Unit)? = null,
    keyboardType: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 25.dp,
            )
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(
                    vertical = 10.dp,
                )
                .fillMaxWidth(),
            value = inputValue,
            onValueChange = { newText ->
                inputChanged(newText)
            },
            maxLines = 1,
            placeholder = {
                Text(
                    text = placeholder,
                    style = subTitleTextStyle.copy(
                        color = Color.Gray,
                        fontSize = 14.sp,
                    ),
                )
            },
            keyboardOptions = keyboardType,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp),
                    contentDescription = null,
                )
            },
            isError = errorMessage.isNotEmpty(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red,
            ),
            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                if (trailingIcon != null) {
                    IconButton(onClick = {
                        if (onTrailingClicked != null) {
                            onTrailingClicked()
                        }
                    }) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            },
            visualTransformation = visualTransformation
        )
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                style = subTitleTextStyle.copy(
                    color = Color.Red,
                    fontSize = 13.sp,
                ),
            )
        }
    }

}