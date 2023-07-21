package com.example.compose_example.features.authentification.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.core.utils.titleTextStyle
import com.example.compose_example.core.widgets.BackButtonWidget

@Composable
fun ResetPasswordHeader(
    headerTitle: String,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = modifier.padding(
            start = 25.dp,
            top = 30.dp,
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackButtonWidget(onBackPressed = {
            onBackPressed()
        })
        Text(
            text = headerTitle,
            style = titleTextStyle.copy(
                color = Color.Black,
                fontSize = 25.sp,
            ),
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}
