package com.example.compose_example.core.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle


@Composable
fun DistanceWidget(
    distance: Double
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            painterResource(id = R.drawable.ic_location),
            contentDescription = null,
            tint = colorResource(id = R.color.dark_red),
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(id = R.string.distance_text, distance),
            style = subTitleTextStyle.copy(
                color = Color.Gray,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}