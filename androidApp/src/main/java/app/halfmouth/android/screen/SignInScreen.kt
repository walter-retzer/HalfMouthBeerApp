package app.halfmouth.android.screen

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.halfmouth.android.components.ContactTextField
import app.halfmouth.android.data.contact.ContactListEvent
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.viewmodel.SignInViewModel
import app.halfmouth.theme.SurfaceVariantDark
import app.halfmouth.theme.YellowContainerLight
import app.halfmouth.utils.AndroidApp.applicationContext
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel = viewModel<SignInViewModel>()
    val state by viewModel.signInState.collectAsStateWithLifecycle()
    val stateFieldError by viewModel.state.collectAsStateWithLifecycle()
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

    BackHandler { }

    Surface(color = SurfaceVariantDark, modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.Black)
            ) {
                androidx.compose.material.Text(
                    text = "Login",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = YellowContainerLight,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
            Spacer(Modifier.height(16.dp))
            ContactTextField(
                value = viewModel.newContact?.firstName ?: "",
                placeholder = "First name",
                error = stateFieldError.firstNameError,
                onValueChanged = { viewModel.onEvent(ContactListEvent.OnFirstNameChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                inputType = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                errorReset = { viewModel.resetErrorInputField() }
            )
            ContactTextField(
                value = viewModel.newContact?.lastName ?: "",
                placeholder = "Last name",
                error = stateFieldError.lastNameError,
                onValueChanged = { viewModel.onEvent(ContactListEvent.OnLastNameChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                inputType = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                errorReset = { viewModel.resetErrorInputField() }
            )
            ContactTextField(
                value = viewModel.newContact?.email ?: "",
                placeholder = "Email",
                error = stateFieldError.emailError,
                onValueChanged = { viewModel.onEvent(ContactListEvent.OnEmailChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                inputType = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                errorReset = { viewModel.resetErrorInputField() }
            )
            ContactTextField(
                value = viewModel.newContact?.phoneNumber ?: "",
                placeholder = "Phone",
                error = stateFieldError.phoneNumberError,
                onValueChanged = { viewModel.onEvent(ContactListEvent.OnPhoneNumberChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                inputType = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                errorReset = { viewModel.resetErrorInputField() }
            )
            Spacer(Modifier.height(65.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                onClick = { viewModel.onEvent(ContactListEvent.SaveContact) }
            ) {
                Text(text = "Salvar")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                onClick = {
                    composableScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing
                    Text("Continuar com o Google", fontSize = 16.sp)
                }
            )
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




