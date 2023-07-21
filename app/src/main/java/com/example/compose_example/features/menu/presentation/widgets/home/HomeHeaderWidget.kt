package com.example.compose_example.features.menu.presentation.widgets.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.example.compose_example.core.utils.titleTextStyle


@Composable
fun HomeHeaderWidget(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 30.dp,
                horizontal = 20.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        UserIcon()
        LocationText()
        SearchIcon()
    }
}

@Composable
private fun UserIcon() {
    Image(
        painter = painterResource(R.drawable.ic_user_placeholder),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.light_pink).copy(alpha = 0.3f))
    )

}

@Composable
private fun LocationText() {
    Text(
        "Chicago,US",
        style = titleTextStyle.copy(
            color = Color.Black,
            fontSize = 14.sp
        )
    )
}

@Composable
private fun SearchIcon() {
    Box(
        modifier = Modifier
            .size(50.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.white)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(25.dp)
        )
    }
}

