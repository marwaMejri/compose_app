package com.example.compose_example.features.authentification.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.core.widgets.OutlinedTextFieldWidget
import com.example.compose_example.core.widgets.RoundedButton
import com.example.compose_example.features.authentification.data.AuthenticationError
import com.example.compose_example.features.authentification.data.AuthenticationState
import com.example.compose_example.features.authentification.presentation.AuthenticationEvent
import com.example.compose_example.features.authentification.presentation.widgets.ResetPasswordHeader


@Composable
fun ForgetPasswordScreen(
    viewState: AuthenticationState,
    onSendClicked: () -> Unit,
    onBackClicked: () -> Unit,
    events: (event: AuthenticationEvent) -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white)),
        ) {
            ForgetPasswordConstraints(viewState, onSendClicked, onBackClicked, events)
        }
    }
}

@Composable
private fun ForgetPasswordConstraints(
    viewState: AuthenticationState,
    navigateToVerification: () -> Unit,
    onBackClicked: () -> Unit,
    events: (event: AuthenticationEvent) -> Unit
) {
    val errorString = stringResource(id = R.string.empty_value_text)
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (topHeader, description, textField, button, bottomImage) = createRefs()
        ResetPasswordHeader(
            headerTitle = stringResource(id = R.string.forget_password_text),
            onBackPressed = { onBackClicked() },
            modifier = Modifier.constrainAs(topHeader) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
        )
        Text(
            text = stringResource(id = R.string.forget_password_description_text),
            style = subTitleTextStyle.copy(
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify
            ),
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(topHeader.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 25.dp, vertical = 20.dp),
        )
        OutlinedTextFieldWidget(
            modifier = Modifier
                .constrainAs(textField) {
                    top.linkTo(description.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            inputValue = viewState.forgetEmailAddress,
            inputChanged = {
                events(AuthenticationEvent.OnForgetEmailChanged(it))
            },
            placeholder = stringResource(id = R.string.email_text),
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email
            ),
            errorMessage = viewState.forgetError,
        )
        RoundedButton(
            buttonText = stringResource(
                id = R.string.send_text
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(button) {
                    top.linkTo(textField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 25.dp),
            onClick = {
                if (viewState.forgetEmailAddress.isEmpty()) {
                    events(
                        AuthenticationEvent.CheckEmptyValue(
                            AuthenticationError.FORGET_PASSWORD_EMAIL,
                            errorString,
                        )
                    )
                } else {
                    navigateToVerification()
                }
            }
        )
        Image(
            painter = painterResource(id = R.drawable.forget_image),
            modifier = Modifier
                .constrainAs(bottomImage) {
                    top.linkTo(button.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                },
            contentDescription = null,
        )
    }
}