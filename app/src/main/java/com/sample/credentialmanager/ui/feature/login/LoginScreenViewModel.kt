package com.sample.credentialmanager.ui.feature.login

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.sample.credentialmanager.google.GoogleAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    private val _loginData = MutableStateFlow(LoginData("", ""))
    val loginData = _loginData.asStateFlow()

    fun email(text: String) {
        _loginData.update { it.copy(email = text) }
    }

    fun password(text: String) {
        _loginData.update { it.copy(password = text) }
    }

    fun resetState() {
        _loginData.update { it.copy(navigationState = NavigationState.None) }
    }

    fun googleSignIn(context: Context) {
        val credentialManager = CredentialManager.create(context)

        viewModelScope.launch {
            try {
                val response = credentialManager.getCredential(context, GoogleAuth.getGoogleId())

                when (val credential = response.credential) {

                    // Passkey credential
                    is PublicKeyCredential -> {
                        Log.e("TAG", "Success")
                    }

                    // Password credential
                    is PasswordCredential -> {
                        // Send ID and password to your server to validate and authenticate.
                        Log.e("TAG", "Success")
                    }

                    // GoogleIdToken credential
                    is CustomCredential -> {
                        if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                            try {
                                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                                _loginData.update { state ->
                                    state.copy(
                                        name = "${googleIdTokenCredential.givenName} ${googleIdTokenCredential.familyName}",
                                        googleProfilePictureUrl = googleIdTokenCredential.profilePictureUri.toString(),
                                        navigationState = NavigationState.Home,
                                    )
                                }
                                Log.e("TAG", "Success ${googleIdTokenCredential.data}")
                            } catch (e: GoogleIdTokenParsingException) {
                                Log.e("TAG", "Received an invalid google id token response", e)
                            }
                        } else {
                            Log.e("TAG", "Unexpected type of credential")
                        }
                    }

                    else -> {
                        Log.e("TAG", "Unexpected type of credential")
                    }
                }
            } catch (e: GetCredentialCancellationException) {
                Log.w("TAG", "User cancelled the sign in process", e)
            } catch (e: Exception) {
                Log.e("TAG", "Error during sign in", e)
            }
        }
    }
}
