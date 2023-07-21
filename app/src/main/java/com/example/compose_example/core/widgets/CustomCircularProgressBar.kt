package com.example.compose_example.core.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.compose_example.R


@Composable
fun CustomCircularProgressBar(
    modifier: Modifier = Modifier,
    color: Color = colorResource(id = R.color.white_bg)
) {
    CircularProgressIndicator(
        modifier = modifier.size(50.dp),
        color = color,
        strokeWidth = 3.dp
    )

}