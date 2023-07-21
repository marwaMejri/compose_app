package com.example.compose_example.features.authentification.presentation

import androidx.lifecycle.ViewModel
import com.example.compose_example.features.authentification.data.AuthenticationError
import com.example.compose_example.features.authentification.data.AuthenticationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthenticationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AuthenticationState())
    val uiState: StateFlow<AuthenticationState> = _uiState.asStateFlow()

    fun handleAuthenticationEvent(event: AuthenticationEvent) {
        when (event) {
            is AuthenticationEvent.ToggleToSignIn -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        isSignIn = !currentState.isSignIn,
                        emailAddress = "",
                        passwordError = "",
                        emailError = "",
                        password = ""
                    )
                }
            }
            is AuthenticationEvent.OnNameChanged -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        name = event.name,
                        nameError = "",
                    )
                }
            }
            is AuthenticationEvent.OnEmailChanged -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        emailAddress = event.email,
                        emailError = ""
                    )
                }
            }
            is AuthenticationEvent.OnPasswordChanged -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        password = event.password,
                        passwordError = ""
                    )
                }
            }
            is AuthenticationEvent.OnOtpNumberChanged -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        otpNumber = event.otp,
                        otpError = ""
                    )
                }
            }
            is AuthenticationEvent.OnShowPassword -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        showPassword = !currentState.showPassword
                    )
                }
            }
            is AuthenticationEvent.OnRememberPressed -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        toggleRemember = event.value
                    )
                }
            }
            is AuthenticationEvent.OnForgetEmailChanged -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        forgetEmailAddress = event.email,
                        forgetError = ""
                    )
                }
            }
            is AuthenticationEvent.CheckEmptyValue -> {
                setErrorMessage(event.field, event.errorString)
            }
            is AuthenticationEvent.SetErrorsToNull -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        forgetError = "",
                        emailError = "",
                        passwordError = "",
                        nameError = "",
                    )
                }
            }
            is AuthenticationEvent.OnConfirmPasswordChanged -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        confirmPasswordError = "",
                        confirmPassword = event.password
                    )
                }
            }
            is AuthenticationEvent.OnNewPasswordChanged -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        newPasswordError = "",
                        newPassword = event.password
                    )
                }
            }
            is AuthenticationEvent.CheckConfirmPassword -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        newPasswordError = event.errorString,
                        confirmPasswordError = event.errorString,
                    )
                }
            }
            AuthenticationEvent.OnShowConfirmPassword -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        showConfirmPassword = !currentState.showConfirmPassword,
                    )
                }
            }
            is AuthenticationEvent.UpdateResetLoader -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        showResetLoader = event.value,
                    )
                }
            }
        }
    }

    private fun setErrorMessage(field: AuthenticationError, errorString: String) {
        _uiState.update { state ->
            when (field) {
                AuthenticationError.FORGET_PASSWORD_EMAIL -> {
                    state.copy(
                        forgetError = errorString
                    )
                }
                AuthenticationError.EMAIL -> {
                    state.copy(
                        emailError = errorString
                    )
                }
                AuthenticationError.PASSWORD -> {
                    state.copy(
                        passwordError = errorString
                    )
                }
                AuthenticationError.NAME -> {
                    state.copy(
                        nameError = errorString
                    )
                }
                AuthenticationError.OTP -> {
                    state.copy(
                        otpError = errorString
                    )
                }
                AuthenticationError.NEW_PASSWORD -> {
                    state.copy(
                        newPasswordError = errorString,
                        showResetLoader = false
                    )
                }
                AuthenticationError.CONFIRM_PASSWORD -> {
                    state.copy(
                        confirmPasswordError = errorString,
                        newPasswordError = errorString,
                        showResetLoader = false
                    )
                }
            }
        }

    }


}