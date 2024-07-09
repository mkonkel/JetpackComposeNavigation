package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import viewmodels.EleventhViewModel

@Composable
fun EleventhScreen(
    navController: NavHostController,
    viewModel: EleventhViewModel = viewModel { EleventhViewModel() },
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Countdown Timer Screen")
        Spacer(modifier = Modifier.height(16.dp))

        Countdown(viewModel)

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Composable
private fun Countdown(viewModel: EleventhViewModel) {
    val countdownText = viewModel.countDownText.collectAsState().value
    Text("COUNTDOWN: $countdownText")
}