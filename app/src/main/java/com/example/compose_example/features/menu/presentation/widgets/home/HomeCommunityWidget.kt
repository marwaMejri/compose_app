package com.example.compose_example.features.menu.presentation.widgets.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.titleTextStyle

@Composable
fun HomeCommunityWidget(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        backgroundColor = colorResource(id = R.color.bg_pink),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 15.dp,
                horizontal = 20.dp,
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(
                start = 10.dp,
                top = 15.dp,
            )
        ) {
            Text(
                stringResource(id = R.string.join_community_text),
                style = titleTextStyle.copy(
                    color = colorResource(id = R.color.dark_red),
                    fontSize = 17.sp
                ),
                modifier = Modifier.padding(
                    bottom = 15.dp,
                ),
            )
            Image(
                painter = painterResource(id = R.drawable.home_bg),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxSize()
            )
        }
    }

}
