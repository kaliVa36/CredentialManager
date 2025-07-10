package com.sample.credentialmanager.ui.feature.login

data class LoginData(
    val password: String,
    val googleProfilePictureUrl: String = "",
    val name: String = "",
    val navigationState: NavigationState = NavigationState.None,
    val email: String = "",
)

sealed class NavigationState {
    data object None : NavigationState()
    data object Home: NavigationState()
}
