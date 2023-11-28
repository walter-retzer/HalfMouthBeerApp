package app.halfmouth.android.screen

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.viewmodel.SignInViewModel
import app.halfmouth.utils.AndroidApp.applicationContext
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel = viewModel<SignInViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val composableScope = rememberCoroutineScope()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(
                applicationContext
            )
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                composableScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            Toast.makeText(
                context,
                "Successful",
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(ScreenRoute.HomeScreen.route)
        }
    }

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let {
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            composableScope.launch {
                val signInIntentSender = googleAuthUiClient.signIn()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        }) {
            Text(text = "SignIn")
        }

        Button(onClick = {
            composableScope.launch {
                googleAuthUiClient.signOut()
            }
        }) {
            Text(text = "SignOut")
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            val user = googleAuthUiClient.getSignedInUser()
            if (user?.profilePictureUrl != null) {
                AsyncImage(
                    model = user.profilePictureUrl,
                    contentDescription = "Photo",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}
