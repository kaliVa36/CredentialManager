package com.sample.credentialmanager.ui.feature.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.sample.credentialmanager.R
import com.sample.credentialmanager.ui.feature.navigation.Arguments
import com.sample.credentialmanager.ui.feature.navigation.Screens
import com.sample.credentialmanager.ui.feature.navigation.navigate

@Composable
fun LoginScreen(
    loginData: LoginData,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onGoogleSignInClicked: (Context) -> Unit,
    navHostController: NavHostController,
    resetState: () -> Unit,
) {
    val context = LocalContext.current

    when (loginData.navigationState) {
        NavigationState.Home -> {
            resetState()
            navHostController.currentBackStackEntry?.destination?.parent?.id?.let { parentGraph ->
                navHostController.navigate(
                    route = Screens.HOME,
                    args = bundleOf(
                        Arguments.PROFILE_PICTURE_URL to loginData.googleProfilePictureUrl,
                        Arguments.NAME to loginData.name,
                    ), navOptions = navOptions {
                        popUpTo(
                            id = parentGraph,
                            popUpToBuilder = { inclusive = true }
                        )
                        launchSingleTop = true
                    }
                )
            }
        }

        NavigationState.None -> {}
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 32.sp,
            color = Color.White,
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = loginData.email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = loginData.password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.password)) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(9999.dp))
                .background(Color.White)
                .clickable(
                    onClick = { onGoogleSignInClicked(context) }
                )
                .heightIn(min = 32.dp)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(Modifier.width(12.dp))

                Text(
                    text = "Sign in",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 21.sp,
                    color = Color.Black,
                )
            }
        }
    }
}
