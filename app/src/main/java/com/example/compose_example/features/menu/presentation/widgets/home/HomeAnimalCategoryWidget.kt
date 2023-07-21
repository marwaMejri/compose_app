package com.example.compose_example.features.menu.presentation.widgets.home

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.noRippleClickable
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.features.menu.data.home.HomeState
import com.example.compose_example.features.menu.presentation.HomeEvents
import com.example.compose_example.features.menu.utils.AnimalCategoryItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeAnimalCategoryWidget(
    modifier: Modifier = Modifier,
    events: (event: HomeEvents) -> Unit,
    viewState: HomeState
) {
    val list = listOf(
        AnimalCategoryItem.Dog,
        AnimalCategoryItem.Cat,
        AnimalCategoryItem.Chick,
        AnimalCategoryItem.Fish,
    )
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        itemsIndexed(list) { index, item ->
            var visible by remember { mutableStateOf(false) }
            val delay = (index + 1) * 500
            LaunchedEffect(visible) {
                delay(delay.toLong())
                visible = true
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(200)) +
                        scaleIn(
                            animationSpec = tween(
                                500,
                                easing = FastOutLinearInEasing
                            )
                        ),
            ) {
                CategoryItem(
                    item = item,
                    isSelected = viewState.clickedCategoryIndex == index,
                    onClick = {
                        events(HomeEvents.OnCategoryClicked(index, item.type))
                    }
                )
            }
        }
    }


}

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    item: AnimalCategoryItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(
                color = if (isSelected) colorResource(id = R.color.dark_red) else Color.White
            )
            .padding(
                horizontal = 7.dp
            )
            .noRippleClickable {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = if (isSelected) 10.dp else 7.dp,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(item.image),
                contentDescription = null,
                modifier = Modifier
                    .size(if (isSelected) 50.dp else 45.dp)
                    .clip(CircleShape)
                    .border(
                        1.dp,
                        colorResource(id = if (isSelected) R.color.white else R.color.dark_red),
                        CircleShape
                    )
                    .background(
                        colorResource(id = R.color.white)
                    ),
                contentScale = ContentScale.Crop,

                )
            Text(
                stringResource(id = item.title), style = subTitleTextStyle.copy(
                    color = if (isSelected) Color.White else Color.Gray,
                    fontSize = 15.sp,
                ),
                modifier = Modifier.padding(
                    top = 10.dp,
                )
            )

        }
    }
}