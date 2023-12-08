package app.halfmouth.android.screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.halfmouth.android.components.ConfirmDialog
import app.halfmouth.android.viewmodel.ProfileViewModel

@Composable
fun DeleteUserAccountScreen(navController: NavHostController) {
    DeleteUserAccountContent(navController)
}

@Composable
fun DeleteUserAccountContent(navController: NavHostController) {

    val viewModel = viewModel<ProfileViewModel>()
    var showDialog: Boolean by remember { mutableStateOf(true) }

    BackHandler { showDialog = false }

    if (showDialog) {
        val message = "Tem certeza de excluir a sua conta no HalfMouth App?"
        ConfirmDialog(
            message = message,
            onClick = {
                viewModel.deleteAccount()
                navController.navigate(ScreenRoute.SplashScreen.route)
            },
            onDismissClick = {
                showDialog = false
                viewModel.isClicked = false
            },
        ) {
            showDialog = it
        }
    }
}
