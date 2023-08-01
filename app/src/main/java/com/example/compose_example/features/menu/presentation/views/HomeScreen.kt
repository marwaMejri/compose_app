package com.example.compose_example.features.menu.presentation.views

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.core.utils.titleTextStyle
import com.example.compose_example.features.menu.data.home.AnimalCategory
import com.example.compose_example.features.menu.data.home.HomeState
import com.example.compose_example.features.menu.presentation.HomeEvents
import com.example.compose_example.features.menu.presentation.widgets.home.HomeAnimalCategoryWidget
import com.example.compose_example.features.menu.presentation.widgets.home.HomeAnimalsList
import com.example.compose_example.features.menu.presentation.widgets.home.HomeCommunityWidget
import com.example.compose_example.features.menu.presentation.widgets.home.HomeHeaderWidget


@Composable
fun HomeScreen(
    viewState: HomeState,
    events: (event: HomeEvents) -> Unit,
) {
    LaunchedEffect(Unit) {
        events(HomeEvents.InitAnimalsList)
    }
    DisposableEffect(Unit) {
        onDispose {
            events(HomeEvents.ToggleAnimation(false))
            events(HomeEvents.ResetFavoriteAnimationValue)
            events(HomeEvents.OnCategoryClicked(0, AnimalCategory.CAT))
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        HomeConstraints(viewState, events)
        events(HomeEvents.ToggleAnimation(true))
    }
}

@Composable
fun HomeConstraints(viewState: HomeState, events: (event: HomeEvents) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeHeaderWidget(
            modifier = Modifier
                .fillMaxWidth()
        )
        ConstraintLayout(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            val (homeTitle, communityCard, category, animalsList) = createRefs()

            HomeTitleWidget(
                modifier = Modifier
                    .constrainAs(homeTitle) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                viewState
            )
            HomeCommunityWidget(
                modifier = Modifier
                    .constrainAs(communityCard) {
                        top.linkTo(homeTitle.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            )

            HomeAnimalCategoryWidget(
                modifier = Modifier
                    .constrainAs(category) {
                        top.linkTo(communityCard.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                viewState = viewState,
                events = events
            )

            HomeAnimalsList(
                viewState,
                events,
                modifier = Modifier
                    .constrainAs(animalsList) {
                        top.linkTo(category.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
            )

        }
    }
}


@Composable
private fun HomeTitleWidget(
    modifier: Modifier,
    viewState: HomeState,
) {
    Column(
        modifier = modifier.padding(
            start = 20.dp
        )
    ) {
        Text(
            stringResource(id = R.string.home_title),
            style = titleTextStyle.copy(
                textAlign = TextAlign.Start,
                fontSize = if (viewState.initAnimation) 16.sp else 0.sp,
                color = Color.Black
            ),
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = 0.75f,
                    stiffness = 50f
                )
            ),
        )
        Text(
            stringResource(id = R.string.home_subtitle),
            style = subTitleTextStyle.copy(
                textAlign = TextAlign.Start,
                fontSize = if (viewState.initAnimation) 18.sp else 0.sp,
                color = Color.Black

            ),
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = 0.75f,
                    stiffness = 50f
                )
            ),
        )
    }

}
