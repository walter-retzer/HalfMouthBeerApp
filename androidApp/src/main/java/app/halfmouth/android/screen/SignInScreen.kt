package app.halfmouth.android.screen

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.halfmouth.android.R
import app.halfmouth.android.components.ContactTextField
import app.halfmouth.android.data.contact.ContactListEvent
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.viewmodel.SignInViewModel
import app.halfmouth.utils.AndroidApp.applicationContext
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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ContactTextField(
            value = "",
            placeholder = "First name",
            error = null,
            onValueChanged = { ContactListEvent.OnFirstNameChanged(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        ContactTextField(
            value = "",
            placeholder = "Last name",
            error = null,
            onValueChanged = { ContactListEvent.OnLastNameChanged(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        ContactTextField(
            value = "",
            placeholder = "Email",
            error = null,
            onValueChanged = { ContactListEvent.OnEmailChanged(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        ContactTextField(
            value = "",
            placeholder = "Phone",
            error = null,
            onValueChanged = { ContactListEvent.OnPhoneNumberChanged(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(85.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {},
            content = {
                Image(
                    painter = painterResource(
                        id = R.drawable.icon_google
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(onClick = {
                            navController.navigate(ScreenRoute.ChartScreen.route)
                        }),
                    alignment = Alignment.BottomEnd,
                )
                Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing
                Text("Continuar com o Google", fontSize = 16.sp)
            }
        )
        Spacer(Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {}
        ) {
            Text(text = "Save")
        }
    }


//        Button(onClick = {
//            composableScope.launch {
//                val signInIntentSender = googleAuthUiClient.signIn()
//                launcher.launch(
//                    IntentSenderRequest.Builder(
//                        signInIntentSender ?: return@launch
//                    ).build()
//                )
//            }
//        }) {
//            Text(text = "SignIn")
//        }
//
//        Button(onClick = {
//            composableScope.launch {
//                googleAuthUiClient.signOut()
//            }
//        }) {
//            Text(text = "SignOut")
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//        ) {
//            val user = googleAuthUiClient.getSignedInUser()
//            if (user?.profilePictureUrl != null) {
//                AsyncImage(
//                    model = user.profilePictureUrl,
//                    contentDescription = "Photo",
//                    modifier = Modifier
//                        .size(150.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//            }
//        }

}




