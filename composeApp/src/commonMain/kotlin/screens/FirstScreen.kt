package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import navigation.Route
import navigation.Screen

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("First screen")
        Button(onClick = { navController.navigate(Screen.Second) }) {
            Text("Second Screen")
        }

        Button(
            onClick = {
                val greetings = "Hello from First Screen"
                navController.navigate(Screen.Third(greetings))
            }
        ) {
            Text("Third Screen")
        }

        Button(onClick = { navController.navigate(Screen.Fourth(name = "John", surname = "Doe")) }) {
            Text("John Doe Screen")
        }

        Button(onClick = { navController.navigate(Screen.Fourth(name = "Michael")) }) {
            Text("Michael Screen")
        }

        Button(onClick = { navController.navigate(Route.Nested) }) {
            Text("Nested")
        }

        Button(onClick = { navController.navigate(Screen.Eighth) }) {
            Text("Bottom")
        }

        Button(onClick = { navController.navigate(Screen.Eleventh) }) {
            Text("Countdown")
        }
    }
}