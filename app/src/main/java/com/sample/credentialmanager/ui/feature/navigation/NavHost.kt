package com.sample.credentialmanager.ui.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sample.credentialmanager.ui.feature.home.HomeScreen
import com.sample.credentialmanager.ui.feature.login.LoginScreen
import com.sample.credentialmanager.ui.feature.login.LoginScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = Screens.LOGIN
    ) {
        composable(Screens.LOGIN) {
            val viewModel = getViewModel<LoginScreenViewModel>()
            val loginData by viewModel.loginData.collectAsStateWithLifecycle()

            LoginScreen(
                loginData = loginData,
                onEmailChange = { viewModel.email(it)},
                onPasswordChange =  viewModel::password,
                onGoogleSignInClicked = viewModel::googleSignIn,
                navHostController = navController,
                resetState = viewModel::resetState,
            )
        }

        composable(Screens.HOME) { navBackStackEntry ->
            HomeScreen(
                profilePictureUrl = navBackStackEntry.arguments?.getString(Arguments.PROFILE_PICTURE_URL) ?: "",
                name = navBackStackEntry.arguments?.getString(Arguments.NAME) ?: "",
            )
        }
    }
}
