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
        startDestination = Screen.FifthScreen.route,
        route = ROUTE.NESTED
    ) {
        composable(route = Screen.FifthScreen.route) {
            FifthScreen(navController)
        }

        composable(route = Screen.SixthScreen.route) {
            SixthScreen(navController)
        }

        composable(route = Screen.SeventhScreen.route) {
            SeventhScreen(navController)
        }
    }
}