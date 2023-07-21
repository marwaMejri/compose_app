package com.example.compose_example.features.authentification.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.core.utils.titleTextStyle

@Composable
fun AuthenticationHeader(
    headerTitle: String,
    headerSubTitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(vertical = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopLogoImage()
        Text(
            headerTitle,
            modifier = Modifier.padding(
                top = 30.dp
            ),
            style = titleTextStyle.copy(
                colorResource(id = R.color.black),
                fontSize = 25.sp
            ),
        )
        Text(
            headerSubTitle,
            style = subTitleTextStyle.copy(
                color = Color.Gray,
            ),
        )
    }
}

@Composable
fun TopLogoImage() {
    Image(
        painter = painterResource(R.drawable.auth_header_image),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(20.dp))
            .background(colorResource(id = R.color.light_pink).copy(alpha = 0.3f))
    )

}