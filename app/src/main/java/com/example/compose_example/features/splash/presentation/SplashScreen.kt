package com.example.compose_example.features.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.core.utils.titleTextStyle
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    toOnboardingScreen: () -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.light_pink))
        ) {
            ScreenConstraints()
            LaunchNextActivity(toOnboardingScreen)
        }
    }

}

@Composable
fun LaunchNextActivity(toOnboardingScreen: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000)
        toOnboardingScreen()
    }
}

@Composable
fun ScreenConstraints() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (topTextsColumn, image, progressIndicator) = createRefs()
        TopTextsColumn(
            modifier = Modifier.constrainAs(topTextsColumn) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        CentredImage(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(topTextsColumn.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
        CustomCircularProgressBar(
            Modifier
                .constrainAs(progressIndicator) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
        )
    }
}

@Composable
private fun CustomCircularProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.size(50.dp),
        color = colorResource(id = R.color.white_bg),
        strokeWidth = 3.dp
    )

}

@Composable
fun CentredImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.splash_image),
        contentDescription = null,
        modifier = modifier
            .fillMaxHeight(0.5f)
            .aspectRatio(1f)
    )
}

@Composable
fun TopTextsColumn(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.splash_title),
            style = titleTextStyle.copy(
                fontSize = 40.sp,
            ),
        )
        Text(
            text = stringResource(id = R.string.splash_sub_title),
            style = subTitleTextStyle.copy(
                fontSize = 13.sp,
            ),
        )
    }
}

@Preview
@Composable
fun PreviewScreen() {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.light_pink))
        ) {
            ScreenConstraints()
        }
    }
}
