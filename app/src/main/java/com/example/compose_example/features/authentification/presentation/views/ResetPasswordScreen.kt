package com.example.compose_example.features.authentification.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.core.widgets.CustomCircularProgressBar
import com.example.compose_example.core.widgets.OutlinedTextFieldWidget
import com.example.compose_example.core.widgets.RoundedButton
import com.example.compose_example.features.authentification.data.AuthenticationError
import com.example.compose_example.features.authentification.data.AuthenticationState
import com.example.compose_example.features.authentification.presentation.AuthenticationEvent
import com.example.compose_example.features.authentification.presentation.widgets.ResetPasswordHeader
import kotlinx.coroutines.delay

@Composable
fun ResetPasswordScreen(
    viewState: AuthenticationState,
    navigateToHome: () -> Unit,
    onBackClicked: () -> Unit,
    events: (event: AuthenticationEvent) -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white)),
        ) {
            ResetPasswordConstraints(
                viewState,
                navigateToHome = navigateToHome,
                onBackClicked,
                events
            )
        }
    }
}

@Composable
private fun ResetPasswordConstraints(
    viewState: AuthenticationState,
    navigateToHome: () -> Unit,
    onBackClicked: () -> Unit,
    events: (event: AuthenticationEvent) -> Unit
) {
    val emptyErrorString = stringResource(id = R.string.confirm_password_value_text)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topHeader, description, firstTextField, secondTextField, button, progressIndicator) = createRefs()
        ResetPasswordHeader(
            headerTitle = stringResource(id = R.string.new_password_text),
            onBackPressed = { onBackClicked() },
            modifier = Modifier.constrainAs(topHeader) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
        )
        Text(
            text = stringResource(id = R.string.new_password_description_text),
            style = subTitleTextStyle.copy(
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(topHeader.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 25.dp, vertical = 5.dp),
        )
        OutlinedTextFieldWidget(
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(firstTextField) {
                    top.linkTo(description.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            inputValue = viewState.newPassword,
            inputChanged = {
                events(AuthenticationEvent.OnNewPasswordChanged(it))
            },
            placeholder = stringResource(id = R.string.password_text),
            leadingIcon = Icons.Default.Lock,
            visualTransformation = if (!viewState.showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardType = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            errorMessage = viewState.newPasswordError,
            trailingIcon = if (!viewState.showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
            onTrailingClicked = {
                events(AuthenticationEvent.OnShowPassword)
            }
        )

        OutlinedTextFieldWidget(
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(secondTextField) {
                    top.linkTo(firstTextField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            inputValue = viewState.confirmPassword,
            inputChanged = {
                events(AuthenticationEvent.OnConfirmPasswordChanged(it))
            },
            placeholder = stringResource(id = R.string.confirm_password_text),
            leadingIcon = Icons.Default.Lock,
            visualTransformation = if (!viewState.showConfirmPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardType = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            errorMessage = viewState.confirmPasswordError,
            trailingIcon = if (!viewState.showConfirmPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
            onTrailingClicked = {
                events(AuthenticationEvent.OnShowConfirmPassword)
            }
        )
        RoundedButton(
            buttonText = stringResource(
                id = R.string.reset_text
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(button) {
                    top.linkTo(secondTextField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 25.dp),
            onClick = {
                if (viewState.newPassword.isEmpty()||viewState.confirmPassword.isEmpty()||(viewState.confirmPassword != viewState.newPassword)) {
                    events(
                        AuthenticationEvent.CheckEmptyValue(
                            AuthenticationError.CONFIRM_PASSWORD,
                            emptyErrorString,
                        )
                    )
                } else {
                    events(
                        AuthenticationEvent.UpdateResetLoader(
                            true,
                        )
                    )
                }
            }
        )
        if (viewState.showResetLoader) {
            CustomCircularProgressBar(
                Modifier
                    .constrainAs(progressIndicator) {
                        top.linkTo(button.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                color = Color.Black
            )
            LaunchNextActivity(toHomeScreen = navigateToHome)
        }

    }
}

@Composable
fun LaunchNextActivity(toHomeScreen: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000)
        toHomeScreen()
    }
}

