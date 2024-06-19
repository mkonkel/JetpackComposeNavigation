package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import screens.FirstScreen
import screens.SecondScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        composable(route = Screen.FirstScreen.route) {
            FirstScreen(navController)
        }
        composable(route = Screen.SecondScreen.route) {
            SecondScreen(navController)
        }
    }
}