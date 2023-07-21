package com.example.compose_example.features.menu.presentation.widgets.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.noRippleClickable
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.core.utils.titleTextStyle
import com.example.compose_example.features.menu.data.home.AnimalItem
import com.example.compose_example.features.menu.data.home.HomeState
import com.example.compose_example.features.menu.presentation.HomeEvents
import kotlinx.coroutines.delay

@Composable
fun HomeAnimalsList(
    viewState: HomeState,
    events: (event: HomeEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    val list = listOf(
        AnimalItem(
            name = "Samantha",
            distance = 5.0,
            image = R.drawable.image,
            isFavorite = false
        ),
        AnimalItem(
            name = "Samantha",
            distance = 5.0,
            image = R.drawable.image,
            isFavorite = true
        ),
        AnimalItem(
            name = "Samantha",
            distance = 5.0,
            image = R.drawable.image,
            isFavorite = false
        ),
        AnimalItem(
            name = "Samantha",
            distance = 5.0,
            image = R.drawable.image,
            isFavorite = false
        ),
    )
    LazyRow(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 15.dp, bottom = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        itemsIndexed(list) { index, item ->
            var visible by remember { mutableStateOf(false) }
            val delay = (index + 1) * 700
            LaunchedEffect(visible) {
                delay(delay.toLong())
                visible = true
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    animationSpec = tween(500),
                ) + slideInHorizontally(
                    initialOffsetX = { -500 * (index + 1) },
                    animationSpec = tween(
                        durationMillis = 700,
                        easing = LinearEasing
                    )
                )
            ) {
                AnimalItemWidget(
                    modifier = Modifier.padding(
                        end = if (index == (list.size - 1)) 20.dp else 0.dp
                    ), item, onClick = {
                        events(HomeEvents.AddToFavorite(it))
                    }
                )
            }
        }
    }
}

@Composable
private fun AnimalItemWidget(
    modifier: Modifier,
    animalItem: AnimalItem,
    onClick: (animalItem: AnimalItem) -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.white))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(animalItem.image),
                contentDescription = null,
                modifier = Modifier
                    .size(170.dp)
                    .fillMaxSize()
                    .padding(20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Column {
                    Text(
                        text = animalItem.name, style = titleTextStyle.copy(
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.distance_text, animalItem.distance),
                        style = subTitleTextStyle.copy(
                            color = Color.Gray,
                            fontSize = 13.sp
                        )
                    )
                }
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .noRippleClickable {
                            onClick(animalItem)
                        },
                    painter = painterResource(if (animalItem.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite),
                    contentDescription = null,
                    tint = if (animalItem.isFavorite) colorResource(id = R.color.dark_red) else Color.Black,

                    )
            }
        }
    }
}

