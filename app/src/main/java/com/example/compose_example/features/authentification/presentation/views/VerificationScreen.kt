package com.example.compose_example.features.authentification.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.core.utils.titleTextStyle
import com.example.compose_example.core.widgets.RoundedButton
import com.example.compose_example.features.authentification.data.AuthenticationError
import com.example.compose_example.features.authentification.data.AuthenticationState
import com.example.compose_example.features.authentification.presentation.AuthenticationEvent
import com.example.compose_example.features.authentification.presentation.widgets.ResetPasswordHeader


@Composable
fun VerificationScreen(
    viewState: AuthenticationState,
    navigateToReset: () -> Unit,
    onBackClicked: () -> Unit,
    events: (event: AuthenticationEvent) -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white)),
        ) {
            VerificationConstraints(viewState, navigateToReset, onBackClicked, events)
        }
    }
}

@Composable
private fun VerificationConstraints(
    viewState: AuthenticationState,
    navigateToReset: () -> Unit,
    onBackClicked: () -> Unit,
    events: (event: AuthenticationEvent) -> Unit
) {
    val emptyErrorString = stringResource(id = R.string.empty_value_text)
    val errorString = stringResource(id = R.string.otp_error_value_text)
    val focusManager = LocalFocusManager.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topHeader, title, description, textField, button) = createRefs()
        ResetPasswordHeader(
            headerTitle = stringResource(id = R.string.verification_text),
            onBackPressed = { onBackClicked() },
            modifier = Modifier.constrainAs(topHeader) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
        )
        Text(
            text = stringResource(id = R.string.otp_title_text),
            style = titleTextStyle.copy(
                color = Color.Black,
                fontSize = 25.sp,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(topHeader.bottom)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = 25.dp, vertical = 20.dp),
        )

        Text(
            text = stringResource(id = R.string.otp_description_text),
            style = subTitleTextStyle.copy(
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = 25.dp, vertical = 5.dp),
        )
        OtpTextField(
            otpText = viewState.otpNumber,
            modifier = Modifier
                .constrainAs(textField) {
                    top.linkTo(description.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onOtpTextChange = { value ->
                events(AuthenticationEvent.OnOtpNumberChanged(value))
            },
            focusManager = focusManager,
            errorValue = viewState.otpError
        )

        RoundedButton(
            buttonText = stringResource(
                id = R.string.verify_now_text
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
                if (viewState.otpNumber.isEmpty()) {
                    events(
                        AuthenticationEvent.CheckEmptyValue(
                            AuthenticationError.OTP,
                            emptyErrorString,
                        )
                    )
                } else if (viewState.otpNumber.length < 4) {
                    events(
                        AuthenticationEvent.CheckEmptyValue(
                            AuthenticationError.OTP,
                            errorString,
                        )
                    )
                } else {
                    navigateToReset()
                }
            }
        )
    }
}

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 4,
    focusManager: FocusManager,
    errorValue: String,
    onOtpTextChange: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(
            vertical = 20.dp
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = TextFieldValue(
                otpText,
                selection = TextRange(otpText.length),
            ),
            onValueChange = {
                if (it.text.length <= otpCount) {
                    onOtpTextChange.invoke(it.text)
                }
            },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.NumberPassword
            ),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(otpCount) { index ->
                        CharView(
                            index = index,
                            text = otpText
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        )
        if (errorValue.isNotEmpty()) {
            Text(
                text = errorValue,
                style = subTitleTextStyle.copy(
                    color = Color.Red,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                ),
            )
        }
    }

}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index >= text.length -> ""
        else -> text[index].toString()
    }
    Box(
        modifier = Modifier
            .size(50.dp)
            .border(
                1.dp, when {
                    isFocused -> colorResource(id = R.color.light_pink)
                    else -> Color.LightGray
                }, RoundedCornerShape(8.dp)
            )
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char,
            style = titleTextStyle.copy(
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            ),
        )
    }

}

