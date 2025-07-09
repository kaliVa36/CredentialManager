package com.sample.credentialmanager.google

import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption

object GoogleAuth {
    fun getGoogleId(): GetCredentialRequest {
        val googleIdOption: GetSignInWithGoogleOption =
            GetSignInWithGoogleOption
                .Builder("707009871488-9u06ff7r08adjlkk33gp2jo0uua26vuu.apps.googleusercontent.com")
                .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return request
    }
}
