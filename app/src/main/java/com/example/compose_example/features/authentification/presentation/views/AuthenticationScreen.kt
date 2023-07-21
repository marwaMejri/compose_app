package com.example.compose_example.features.authentification.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.compose_example.R
import com.example.compose_example.core.widgets.RoundedButton
import com.example.compose_example.features.authentification.data.AuthenticationError
import com.example.compose_example.features.authentification.data.AuthenticationState
import com.example.compose_example.features.authentification.presentation.AuthenticationEvent
import com.example.compose_example.features.authentification.presentation.widgets.AuthenticationFormWidget
import com.example.compose_example.features.authentification.presentation.widgets.AuthenticationHeader
import com.example.compose_example.features.authentification.presentation.widgets.RememberAndForgetWidget

@Composable
fun AuthenticationScreen(
    viewState: AuthenticationState,
    events: (event: AuthenticationEvent) -> Unit,
    navigateToForget: () -> Unit,
    navigateToHome: () -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white)),
        ) {
            AuthenticationConstraints(viewState, events, navigateToForget = {
                navigateToForget()
            }, navigateToHome = {
                navigateToHome()
            })
        }
    }
}

@Composable
fun AuthenticationConstraints(
    viewState: AuthenticationState,
    events: (event: AuthenticationEvent) -> Unit,
    navigateToForget: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val errorString = stringResource(id = R.string.empty_value_text)
    val signInText = if (viewState.isSignIn) stringResource(
        id = R.string.without_account_text
    ) else stringResource(
        id = R.string.have_account_text
    )
    val descriptionText = if (viewState.isSignIn) stringResource(
        id = R.string.sign_up_text
    ) else stringResource(
        id = R.string.sign_in_text
    )
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        ) {
            append(
                signInText
            )
        }
        withStyle(
            style = SpanStyle(
                color = colorResource(id = R.color.light_pink),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold))
            ),
        ) {
            append(
                descriptionText
            )
        }
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (topHeader, authForm, remember, button, switchText, bottomImage) = createRefs()
        AuthenticationHeader(
            modifier = Modifier
                .constrainAs(topHeader) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            headerTitle = stringResource(id = if (viewState.isSignIn) R.string.welcome_back_text else R.string.sign_up_text),
            headerSubTitle = stringResource(id = if (viewState.isSignIn) R.string.enter_email_password_text else R.string.enter_credentials_text),
        )
        AuthenticationFormWidget(
            modifier = Modifier
                .constrainAs(authForm) {
                    top.linkTo(topHeader.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            viewState,
            events,
        )
        if (viewState.isSignIn)
            RememberAndForgetWidget(
                modifier = Modifier
                    .constrainAs(remember) {
                        top.linkTo(authForm.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                viewState = viewState,
                events = events,
                navigateToForget = { navigateToForget() }
            )
        RoundedButton(
            buttonText = if (viewState.isSignIn) stringResource(id = R.string.sign_in_text) else stringResource(
                id = R.string.sign_up_text
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(button) {
                    top.linkTo(if (viewState.isSignIn) remember.bottom else authForm.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 25.dp),
            onClick = {
                checkEmptyValues(viewState, navigateToHome, events, errorString)
            }
        )
        Text(
            annotatedString,
            modifier = Modifier
                .padding(end = 35.dp, top = 3.dp)
                .clickable {
                    events(AuthenticationEvent.SetErrorsToNull)
                    events(AuthenticationEvent.ToggleToSignIn)
                }
                .constrainAs(switchText) {
                    top.linkTo(button.bottom)
                    end.linkTo(button.end)
                },
        )
        Image(
            painter = painterResource(id = if (viewState.isSignIn) R.drawable.sign_in_image else R.drawable.sign_up_image),
            modifier = Modifier
                .constrainAs(bottomImage) {
                    top.linkTo(switchText.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                },
            contentDescription = null,
        )
    }
}

private fun checkEmptyValues(
    viewState: AuthenticationState,
    navigateToHome: () -> Unit,
    events: (event: AuthenticationEvent) -> Unit,
    errorValue: String,
) {
    if (!viewState.isSignIn && viewState.name.isEmpty()) {
        events(AuthenticationEvent.CheckEmptyValue(AuthenticationError.NAME, errorValue))
    }
    if (viewState.emailAddress.isEmpty()) {
        events(AuthenticationEvent.CheckEmptyValue(AuthenticationError.EMAIL, errorValue))
    }
    if (viewState.password.isEmpty()) {
        events(AuthenticationEvent.CheckEmptyValue(AuthenticationError.PASSWORD, errorValue))
    } else {
        navigateToHome()
    }
}


