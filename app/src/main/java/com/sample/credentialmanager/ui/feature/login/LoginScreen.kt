package com.sample.credentialmanager.ui.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sample.credentialmanager.R

@Composable
fun LoginScreen(loginData: LoginData, onEmailChange: (String) -> Unit, onPasswordChange: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = loginData.email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.email)) }
        )

        TextField(
            value = loginData.password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.password)) }
        )
    }
}
