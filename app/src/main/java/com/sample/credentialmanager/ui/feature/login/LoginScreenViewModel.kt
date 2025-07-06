package com.sample.credentialmanager.ui.feature.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel: ViewModel() {
    private val _loginData = MutableStateFlow(LoginData("", ""))
    val loginData = _loginData.asStateFlow()

    fun email(text: String) {
        _loginData.update { it.copy(email = text) }
    }

    fun password(text: String) {
        _loginData.update { it.copy(password = text) }
    }
}
