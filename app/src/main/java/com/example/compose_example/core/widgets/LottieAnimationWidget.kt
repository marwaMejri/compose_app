package com.example.compose_example.core.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimationWidget(
    rawAnimation: Int,
    onAnimationFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(rawAnimation))
    val progress by animateLottieCompositionAsState(composition)
    if (progress == 1.0f) {
        onAnimationFinished()
    }
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier,
    )
}