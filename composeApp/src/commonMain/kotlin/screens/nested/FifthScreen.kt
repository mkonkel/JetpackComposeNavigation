package screens.nested

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import navigation.Screen

/**
 * This is the fifth screen of the nested navigation.
 * It is not used anymore since the new entrypoint to the nested graph is done without it
 */
@Composable
fun FifthScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Seventh screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
        Button(onClick = { navController.navigate(Screen.SixthScreen.route) }) {
            Text("Sixth Screen")
        }
        Button(onClick = { navController.navigate(Screen.SeventhScreen.route) }) {
            Text("Seventh Screen")
        }
    }
}