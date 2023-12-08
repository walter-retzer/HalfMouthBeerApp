package app.halfmouth.android.screen

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import app.halfmouth.android.R
import app.halfmouth.android.components.ContactTextField
import app.halfmouth.android.components.ContactTextFieldPassword
import app.halfmouth.android.data.contact.SignInContactEvent
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.security.SecurePreferencesApp
import app.halfmouth.android.utils.Constants.Companion.NAME_MAX_NUMBER
import app.halfmouth.android.utils.Constants.Companion.PASSWORD_MAX_NUMBER
import app.halfmouth.android.utils.Constants.Companion.PHONE_MAX_NUMBER
import app.halfmouth.android.utils.Constants.Companion.USER_DEFAULT_SIGNIN
import app.halfmouth.android.utils.Constants.Companion.USER_GOOGLE_SIGNIN
import app.halfmouth.android.utils.MaskVisualTransformation
import app.halfmouth.android.viewmodel.SignInViewModel
import app.halfmouth.theme.OnYellowSecondaryContainerLight
import app.halfmouth.theme.SurfaceVariantDark
import app.halfmouth.theme.YellowContainerLight
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController
) {
    val pref = SecurePreferencesApp()
    val context = LocalContext.current
    val viewModel = viewModel<SignInViewModel>()
    val state by viewModel.signInState.collectAsStateWithLifecycle()
    val stateFieldError by viewModel.state.collectAsStateWithLifecycle()
    val stateSignInSuccessful by viewModel.signInSuccessful.collectAsStateWithLifecycle()
    val stateSignInError by viewModel.signInError.collectAsStateWithLifecycle()
    val composableScope = rememberCoroutineScope()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(
                context
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
                "Cadastro Realizado com Sucesso",
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(ScreenRoute.HomeScreen.route)
        }
    }

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let {
            Toast.makeText(
                context,
                "Error: $it",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = stateSignInSuccessful) {
        if (stateSignInSuccessful) {
            Toast.makeText(
                context,
                "Cadastro Realizado com Sucesso",
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(ScreenRoute.HomeScreen.route)
        }
    }

    LaunchedEffect(key1 = stateSignInError) {
        if (stateSignInError) Toast.makeText(
            context,
            "Não foi possível realizar o seu cadastro no momento.",
            Toast.LENGTH_LONG
        ).show()
    }

    BackHandler { }

    Surface(color = SurfaceVariantDark, modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
                    text = "Criar uma Conta",
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
                value = viewModel.newContact.firstName,
                placeholder = "Nome",
                error = stateFieldError.firstNameError,
                onValueChanged = {
                    if (it.length <= NAME_MAX_NUMBER) viewModel.onEvent(
                        SignInContactEvent.OnFirstNameChanged(it)
                    )
                },
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
                value = viewModel.newContact.phoneNumber,
                placeholder = "Celular",
                error = stateFieldError.phoneNumberError,
                onValueChanged = {
                    if (it.length <= PHONE_MAX_NUMBER) viewModel.onEvent(
                        SignInContactEvent.OnPhoneNumberChanged(it)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                inputType = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                errorReset = { viewModel.resetErrorInputField() },
                visualTransformation = MaskVisualTransformation(MaskVisualTransformation.PHONE)
            )
            ContactTextField(
                value = viewModel.newContact.email,
                placeholder = "Email",
                error = stateFieldError.emailError,
                onValueChanged = { viewModel.onEvent(SignInContactEvent.OnEmailChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                inputType = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                errorReset = { viewModel.resetErrorInputField() }
            )
            ContactTextFieldPassword(
                value = viewModel.newContact.password,
                placeholder = "Senha",
                error = stateFieldError.passwordError,
                onValueChanged = {
                    if (it.length <= PASSWORD_MAX_NUMBER) viewModel.onEvent(
                        SignInContactEvent.OnPasswordChanged(it)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                inputType = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                ),
                errorReset = { viewModel.resetErrorInputField() }
            )
            ContactTextFieldPassword(
                value = viewModel.newContact.confirmPassword,
                placeholder = "Confirma Senha",
                error = stateFieldError.confirmPasswordError,
                onValueChanged = {
                    if (it.length <= PASSWORD_MAX_NUMBER) viewModel.onEvent(
                        SignInContactEvent.OnConfirmPasswordChanged(it)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                inputType = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                ),
                errorReset = { viewModel.resetErrorInputField() }
            )
            Spacer(Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                onClick = {
                    pref.put(USER_DEFAULT_SIGNIN, true)
                    pref.put(USER_GOOGLE_SIGNIN, false)
                    viewModel.onEvent(SignInContactEvent.SaveContact)
                },
                content = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing
                    Text(text = "Cadastrar", fontSize = 16.sp)
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(OnYellowSecondaryContainerLight),
                onClick = {
                    composableScope.launch {
                        pref.put(USER_GOOGLE_SIGNIN, true)
                        pref.put(USER_DEFAULT_SIGNIN, false)
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                content = {
                    Image(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(
                            id = R.drawable.icon_google
                        ),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing
                    Text("Entrar com o Google", fontSize = 16.sp, color = Color.White)
                }
            )
        }
    }
}
