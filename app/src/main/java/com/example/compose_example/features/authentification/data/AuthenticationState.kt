package com.example.compose_example.features.authentification.data

data class AuthenticationState(
    val isSignIn: Boolean = true,
    val emailAddress: String = "",
    val password: String = "",
    val name: String = "",
    val showPassword: Boolean = false,
    val showConfirmPassword: Boolean = false,
    val toggleRemember: Boolean = false,
    val emailError: String = "",
    val passwordError: String = "",
    val nameError: String = "",
    val forgetEmailAddress: String = "",
    val forgetError: String = "",
    val otpNumber: String = "",
    val otpError: String = "",
    val newPasswordError: String = "",
    val confirmPasswordError: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val showResetLoader: Boolean = false
)
