package com.example.compose_example.features.onboarding.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.compose_example.R
import com.example.compose_example.features.onboarding.data.PageDescriptionData
import com.example.compose_example.features.onboarding.presentation.widgets.PagerElementWidget
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    toLoginScreen: () -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white_bg))
        ) {
            HorizontalPagerWidget(toLoginScreen)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWidget(
    toDashboardScreen: () -> Unit,
) {
    val pageCount = 3
    val pagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    val mImagesList =
        listOf(
            R.drawable.pager_first_image,
            R.drawable.pager_second_image,
            R.drawable.pager_third_image,
        )
    val mDescriptionList =
        listOf(
            PageDescriptionData(
                stringResource(R.string.first_page_title),
                stringResource(R.string.first_page_sub_title)
            ),
            PageDescriptionData(
                stringResource(R.string.second_page_title),
                stringResource(R.string.second_page_sub_title)
            ),
            PageDescriptionData(
                stringResource(R.string.third_page_title),
                stringResource(R.string.third_page_sub_title)
            ),
        )
    HorizontalPager(
        pageCount,
        state = pagerState
    ) { page ->
        PagerElementWidget(
            pageCount = pageCount,
            imageId = mImagesList[page],
            currentPage = pagerState.currentPage,
            descriptionTitle = mDescriptionList[page].descriptionTitle,
            descriptionBody = mDescriptionList[page].descriptionSubTitle,
            nextButtonAction = {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            skipButtonAction = toDashboardScreen,
            navigateToLoginAction = toDashboardScreen
        )
    }
}

