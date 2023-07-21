package com.example.compose_example.features.authentification.presentation.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.compose_example.R
import com.example.compose_example.core.widgets.OutlinedTextFieldWidget
import com.example.compose_example.features.authentification.data.AuthenticationState
import com.example.compose_example.features.authentification.presentation.AuthenticationEvent


@Composable
fun AuthenticationFormWidget(
    modifier: Modifier = Modifier,
    viewState: AuthenticationState,
    events: (event: AuthenticationEvent) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (!viewState.isSignIn) {
            OutlinedTextFieldWidget(
                inputValue = viewState.name,
                inputChanged = {
                    events(AuthenticationEvent.OnNameChanged(it))
                },
                placeholder = stringResource(id = R.string.name_text),
                leadingIcon = Icons.Default.AccountCircle,
                keyboardType = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                errorMessage = viewState.nameError
            )
        }
        OutlinedTextFieldWidget(
            inputValue = viewState.emailAddress,
            inputChanged = {
                events(AuthenticationEvent.OnEmailChanged(it))
            },
            placeholder = stringResource(id = R.string.email_or_username_text),
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            errorMessage = viewState.emailError,
        )
        OutlinedTextFieldWidget(
            inputValue = viewState.password,
            inputChanged = {
                events(AuthenticationEvent.OnPasswordChanged(it))
            },
            placeholder = stringResource(id = R.string.password_text),
            leadingIcon = Icons.Default.Lock,
            visualTransformation = if (!viewState.showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardType = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            errorMessage = viewState.passwordError,
            trailingIcon = if (!viewState.showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
            onTrailingClicked = {
                events(AuthenticationEvent.OnShowPassword)
            }
        )
    }
}