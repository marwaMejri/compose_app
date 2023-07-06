package com.example.compose_example.features.onboarding.presentation.widgets

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.core.utils.titleTextStyle

@Composable
fun PagerElementWidget(
    imageId: Int,
    pageCount: Int,
    currentPage: Int,
    descriptionTitle: String,
    descriptionBody: String,
    nextButtonAction: () -> Unit,
    skipButtonAction: () -> Unit,
    navigateToLoginAction: () -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (topImage, indicatorRow, descriptionColumn, skipButton, nextButton) = createRefs()
        TopImageWidget(
            Modifier
                .constrainAs(topImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            imageId = imageId,
        )
        PagerIndicator(
            Modifier
                .constrainAs(indicatorRow) {
                    top.linkTo(topImage.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            currentPage, pageCount,
        )
        CenterDescription(
            Modifier.constrainAs(descriptionColumn) {
                top.linkTo(indicatorRow.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            descriptionTitle = descriptionTitle,
            descriptionBody = descriptionBody,
        )
        SkipButtonWidget(
            modifier = Modifier
                .constrainAs(skipButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            skipButtonAction
        )
        NextButtonWidget(
            modifier = Modifier
                .constrainAs(nextButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            nextButtonAction,
            navigateToLoginAction,
            currentPage == pageCount - 1,
        )
    }
}

@Composable
private fun SkipButtonWidget(modifier: Modifier = Modifier, skipButtonAction: () -> Unit) {
    Text(
        modifier = modifier
            .padding(horizontal = 25.dp, vertical = 25.dp)
            .clickable { skipButtonAction() },
        text = stringResource(id = R.string.skip_text),
        style = subTitleTextStyle.copy(
            color = Color.Gray,
            fontSize = 17.sp,
        ),
    )
}

@Composable
private fun NextButtonWidget(
    modifier: Modifier = Modifier,
    nextButtonAction: () -> Unit,
    navigateToLoginAction: () -> Unit,
    isFinalStep: Boolean
) {
    val extraPadding by animateDpAsState(
        if (isFinalStep) 10.dp else 20.dp
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(horizontal = 25.dp, vertical = 25.dp)
            .wrapContentSize()
            .clip(if (isFinalStep) RoundedCornerShape(8.dp) else CircleShape)
            .background(colorResource(id = R.color.dark_red))
            .clickable { if (isFinalStep) navigateToLoginAction() else nextButtonAction() }
            .padding(extraPadding),
    ) {
        if (!isFinalStep)
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                tint = colorResource(
                    id = R.color.white_bg
                )
            )
        else
            Text(
                stringResource(
                    id = R.string.get_started_text
                ),
                style = subTitleTextStyle, textAlign = TextAlign.Center,
            )
    }
}


@Composable
private fun TopImageWidget(modifier: Modifier = Modifier, imageId: Int) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_rounded_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.light_pink))
        )
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .aspectRatio(1f)
        )
    }
}

@Composable
private fun PagerIndicator(modifier: Modifier = Modifier, currentPage: Int, pageCount: Int) {
    Row(
        modifier
            .height(6.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            val color =
                if (currentPage == index) colorResource(id = R.color.dark_red) else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(width = if (currentPage == index) 30.dp else 15.dp, height = 6.dp)
            )
        }
    }
}

@Composable
private fun CenterDescription(
    modifier: Modifier = Modifier,
    descriptionTitle: String,
    descriptionBody: String,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = descriptionTitle, style = titleTextStyle.copy(
                color = Color.Black,
                fontSize = 25.sp,
            )
        )
        Text(
            text = descriptionBody,
            modifier = Modifier.padding(
                horizontal = 30.dp
            ),
            style = subTitleTextStyle.copy(
                color = Color.Gray,
                fontSize = 18.sp,
            ),
        )
    }
}