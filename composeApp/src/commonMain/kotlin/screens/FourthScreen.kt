package screens

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
import navigation.Route

@Composable
fun FourthScreen(navController: NavHostController, name: String, surname: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Fourth screen")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Name: $name")
        surname?.let { value ->
            Text("Surname: $value")
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
        Button(
            onClick = {
                navController.navigate(Route.Main) {
                    popUpTo(Route.Main)
                }
            }
        ) {
            Text("MAIN")
        }
    }
}