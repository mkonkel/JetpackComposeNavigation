package navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import screens.nested.FifthScreen
import screens.nested.SeventhScreen
import screens.nested.SixthScreen

fun NavGraphBuilder.nested(navController: NavHostController) {
    navigation(
        startDestination = Screen.Fifth,
        route = Route.Nested::class
    ) {
        composable<Screen.Fifth> {
            FifthScreen(navController)
        }

        composable<Screen.Sixth> {
            SixthScreen(navController)
        }

        composable<Screen.Seventh> {
            SeventhScreen(navController)
        }
    }
}