package com.example.compose_example.features.menu.presentation.widgets.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.noRippleClickable
import com.example.compose_example.core.utils.titleTextStyle
import com.example.compose_example.core.widgets.DistanceWidget
import com.example.compose_example.core.widgets.LottieAnimationWidget
import com.example.compose_example.features.menu.data.home.AnimalItem
import com.example.compose_example.features.menu.data.home.HomeState
import com.example.compose_example.features.menu.presentation.HomeEvents

@Composable
fun HomeAnimalsList(
    viewState: HomeState,
    events: (event: HomeEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    LaunchedEffect(viewState.scrollToFirstItem) {
        listState.animateScrollToItem(0)
        events(HomeEvents.ResetScrollValue)
    }
    LazyRow(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 15.dp, bottom = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        itemsIndexed(viewState.filteredAnimalList) { index, item ->
            AnimatedItemWidget(
                modifier = Modifier.padding(
                    end = if (index == (viewState.filteredAnimalList.size - 1)) 20.dp else 0.dp
                ),
                item,
                onClick = {
                    events(HomeEvents.AddToFavorite(it, index))
                },
                viewState,
                viewState.launchFavoriteAnimation && viewState.favoriteAnimationItemIndex == index,
                events
            )
        }
    }
}

@Composable
private fun AnimalItemWidget(
    modifier: Modifier,
    animalItem: AnimalItem,
    onClick: (animalItem: AnimalItem) -> Unit,
    viewState: HomeState
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
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    Text(
                        text = animalItem.name, style = titleTextStyle.copy(
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    )
                    DistanceWidget(animalItem.distance)
                }
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .noRippleClickable {
                            onClick(animalItem)
                        },
                    painter = painterResource(if (viewState.favoriteList.contains(animalItem)) R.drawable.ic_favorite_filled else R.drawable.ic_favorite),
                    contentDescription = null,
                    tint = if (viewState.favoriteList.contains(animalItem)) colorResource(id = R.color.dark_red) else Color.Black,
                )
            }
        }
    }
}

@Composable
private fun AnimatedItemWidget(
    modifier: Modifier,
    animalItem: AnimalItem,
    onClick: (animalItem: AnimalItem) -> Unit,
    viewState: HomeState,
    visibility: Boolean,
    events: (event: HomeEvents) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        AnimalItemWidget(
            modifier = modifier,
            animalItem,
            onClick = {
                onClick(animalItem)
            },
            viewState
        )
        if (visibility)
            LottieAnimationWidget(
                R.raw.animation,
                onAnimationFinished = {
                    events(HomeEvents.ResetFavoriteAnimationValue)
                },
                modifier = Modifier.size(150.dp)
            )
    }
}



