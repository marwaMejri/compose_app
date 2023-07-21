package com.example.compose_example.features.authentification.presentation

import com.example.compose_example.features.authentification.data.AuthenticationError

sealed class AuthenticationEvent {
    object ToggleToSignIn : AuthenticationEvent()
    data class OnEmailChanged(val email: String) : AuthenticationEvent()
    data class OnForgetEmailChanged(val email: String) : AuthenticationEvent()
    data class OnPasswordChanged(val password: String) : AuthenticationEvent()
    data class OnNewPasswordChanged(val password: String) : AuthenticationEvent()
    data class OnConfirmPasswordChanged(val password: String) : AuthenticationEvent()
    data class OnNameChanged(val name: String) : AuthenticationEvent()
    data class OnOtpNumberChanged(val otp: String) : AuthenticationEvent()
    object OnShowPassword : AuthenticationEvent()
    object OnShowConfirmPassword : AuthenticationEvent()
    object SetErrorsToNull : AuthenticationEvent()
    data class OnRememberPressed(val value: Boolean) : AuthenticationEvent()
    data class CheckEmptyValue(
        val field: AuthenticationError,
        val errorString: String
    ) : AuthenticationEvent()

    data class CheckConfirmPassword(
        val errorString: String
    ) : AuthenticationEvent()

    data class UpdateResetLoader(
        val value: Boolean
    ) : AuthenticationEvent()

}