# CredentialManager

This sample Android app demonstrates how to implement **Google Sign-In** using the modern **AndroidX Credential Manager API**. It securely retrieves the user's **ID token** and **profile information**.

## 🔧 Setup Instructions

### 1. Configure Google Sign-In

- Go to [Google Cloud Console](https://console.cloud.google.com/apis/credentials)
- Create an **OAuth 2.0 Client ID** (Type: Android)
- Add your app’s **package name** and **SHA-1**
- Copy the **Web Client ID** – you'll need it in the app

> **Note:** If you don't have a Web Client ID, create a new "Web application" OAuth client in Google Cloud Console and use its generated ID.

### 2. Add Dependencies

In your app-level `build.gradle`:
```
credentials_version = "1.5.0"
googleid_version = "1.1.1"

credentials = { module = "androidx.credentials:credentials", version.ref = "credentials_version" }
credentials-play-services-auth = { module = "androidx.credentials:credentials-play-services-auth", version.ref = "credentials_version" }
googleid = { module = "com.google.android.libraries.identity.googleid:googleid", version.ref = "googleid_version" }
```

### 3. Add Required Permissions

In `AndroidManifest.xml`:

```
<uses-permission android:name="android.permission.INTERNET" />
```

### 4. Build Google ID Token Request

```
val request = GetCredentialRequest.Builder()
    .addCredentialOption(
        GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("YOUR_WEB_CLIENT_ID") // from Google Console
            .build()
    )
    .build()
```

### 5. Handle sign-in

```
val credentialManager = CredentialManager.create(context)

try {
    val result = credentialManager.getCredential(context, request)
    val credential = result.credential

    when (credential) {
        is CustomCredential -> {
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleCred = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleCred.idToken

                // Decode ID Token to extract email, name, etc.
                val decodedJWT = JWT.decode(idToken)
                val email = decodedJWT.getClaim("email").asString()
                val name = decodedJWT.getClaim("name").asString()

                Log.d("Auth", "Email: $email, Name: $name")
            }
        }
        else -> {
            Log.w("Auth", "Unhandled credential type")
        }
    }
} catch (e: GetCredentialCancellationException) {
    Log.w("Auth", "User cancelled login: ${e.message}")
} catch (e: GetCredentialException) {
    Log.e("Auth", "Login failed: ${e.message}")
}
```

👉 Check out the sample app in this repo to see the full implementation in action.
