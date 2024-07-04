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
import navigation.ROUTE
import navigation.Screen

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("First screen")
        Button(
            onClick = { navController.navigate(Screen.SecondScreen.route) }
        ) {
            Text("Second Screen")
        }

        Button(
            onClick = {
                val greetings = "Hello from First Screen"
                // Navigate to the ThirdScreen with greetings as an argument
                // the route looks like this: thirdScreen/{greetings}"
                navController.navigate(Screen.ThirdScreen.withGreetings(greetings))
            }
        ) {
            Text("Third Screen")
        }

        Button(
            onClick = {
                navController.navigate(Screen.FourthScreen.withNameAndSurname("John", "Doe"))
            }
        ) {
            Text("John Doe Screen")
        }

        Button(
            onClick = {
                navController.navigate(Screen.FourthScreen.withName("Michael"))
            }
        ) {
            Text("Michael Screen")
        }

        Button(
            onClick = {
                navController.navigate(ROUTE.NESTED)
            }
        ) {
            Text("Nested")
        }

        Button(
            onClick = {
                navController.navigate(Screen.EighthScreen.route)
            }
        ) {
            Text("Bottom")
        }
    }
}