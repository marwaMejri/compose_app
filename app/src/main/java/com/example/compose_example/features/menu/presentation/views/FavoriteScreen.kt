package com.example.compose_example.features.menu.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.titleTextStyle
import com.example.compose_example.core.widgets.LottieAnimationWidget
import com.example.compose_example.features.menu.data.home.HomeState
import com.example.compose_example.features.menu.presentation.HomeEvents
import com.example.compose_example.features.menu.presentation.widgets.favorite.FavoritePetList


@Composable
fun FavoriteScreen(
    viewState: HomeState,
    events: (event: HomeEvents) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FavoriteConstraints(viewState, events)
        AnimatedVisibility(visible = viewState.launchRemoveFavoriteAnimation) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Gray.copy(alpha = 0.2f)
            ) {
                LottieAnimationWidget(
                    R.raw.broken_heart,
                    onAnimationFinished = {
                        events(HomeEvents.UpdateRemoveFavoriteAnimationValue(false))
                    },
                )
            }
        }
    }
}


@Composable
fun FavoriteConstraints(viewState: HomeState, events: (event: HomeEvents) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            stringResource(id = R.string.favorite_pets_text),
            style = titleTextStyle.copy(
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                color = Color.Black

            ),
            modifier = Modifier.padding(
                top = 30.dp,
                start = 20.dp,
            ),
        )
        FavoritePetList(viewState, events)
    }
}