package com.example.compose_example.features.menu.presentation.widgets.favorite

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.noRippleClickable
import com.example.compose_example.core.utils.titleTextStyle
import com.example.compose_example.core.widgets.DistanceWidget
import com.example.compose_example.features.menu.data.home.AnimalItem
import com.example.compose_example.features.menu.data.home.HomeState
import com.example.compose_example.features.menu.presentation.HomeEvents


@Composable
fun FavoritePetList(
    viewState: HomeState,
    events: (event: HomeEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        itemsIndexed(viewState.favoriteList) { index, item ->
            FavoritePetItem(
                item,
                onPhoneClicked = {
                },
                onFavoriteClicked = {
                    events(HomeEvents.AddToFavorite(item))
                    events(HomeEvents.UpdateRemoveFavoriteAnimationValue(true))
                },
                modifier = Modifier.padding(
                    bottom = if (index == (viewState.favoriteList.size - 1)) 60.dp else 10.dp
                ),
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun FavoritePetItem(
    animalItem: AnimalItem,
    onPhoneClicked: (phoneNumber: String) -> Unit,
    onFavoriteClicked: (animalItem: AnimalItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(color = colorResource(id = R.color.white))
            .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(20.dp)
                ),
        ) {
            Image(
                painter = painterResource(animalItem.image),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = animalItem.name, style = titleTextStyle.copy(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
                DistanceWidget(animalItem.distance)
                Surface(
                    modifier = Modifier
                        .size(40.dp)
                        .fillMaxSize()
                        .noRippleClickable {
                            onPhoneClicked(animalItem.owner.phoneNumber)
                        },
                    color = colorResource(id = R.color.light_pink).copy(alpha = 0.3f),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_phone),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .fillMaxSize()
                            .padding(7.dp),
                        colorFilter = ColorFilter.tint(color = colorResource(id = R.color.dark_red)),
                        alignment = Alignment.Center
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.white_bg))
                .fillMaxSize()
                .noRippleClickable {
                    onFavoriteClicked(animalItem)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_favorite_filled),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .fillMaxSize(),
                colorFilter = ColorFilter.tint(color = colorResource(id = R.color.dark_red))
            )
        }
    }
}


