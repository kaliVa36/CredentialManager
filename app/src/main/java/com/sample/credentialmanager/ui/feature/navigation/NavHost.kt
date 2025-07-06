package com.sample.credentialmanager.ui.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sample.credentialmanager.ui.feature.login.LoginScreen
import com.sample.credentialmanager.ui.feature.login.LoginScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "LOGIN"
    ) {
        composable("LOGIN") {
            val viewModel = getViewModel<LoginScreenViewModel>()
            val loginData by viewModel.loginData.collectAsStateWithLifecycle()

            LoginScreen(
                loginData = loginData,
                onEmailChange = { viewModel.email(it)},
                onPasswordChange =  viewModel::password,
            )
        }
    }
}
