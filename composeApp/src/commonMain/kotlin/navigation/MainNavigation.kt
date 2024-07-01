package navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import screens.FirstScreen
import screens.FourthScreen
import screens.SecondScreen
import screens.ThirdScreen

fun NavGraphBuilder.main(navController: NavHostController) {
    navigation(
        startDestination = Screen.FirstScreen.route,
        route = ROUTE.MAIN
    ) {
        composable(route = Screen.FirstScreen.route) {
            FirstScreen(navController)
        }

        composable(route = Screen.SecondScreen.route) {
            SecondScreen(navController)
        }

        composable(
            route = Screen.ThirdScreen.route,
            arguments = listOf(navArgument(Screen.ThirdScreen.ARGUMENTS.GREETING) {
                type = NavType.StringType
            })
        ) {
            ThirdScreen(
                navController,
                it.arguments?.getString(Screen.ThirdScreen.ARGUMENTS.GREETING).orEmpty()
            )
        }

        composable(
            route = Screen.FourthScreen.route,
            arguments = listOf(
                navArgument(Screen.FourthScreen.ARGUMENTS.NAME) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(Screen.FourthScreen.ARGUMENTS.SURNAME) {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                },
            )
        ) {
            FourthScreen(
                navController,
                requireNotNull(it.arguments?.getString(Screen.FourthScreen.ARGUMENTS.NAME)),
                it.arguments?.getString(Screen.FourthScreen.ARGUMENTS.SURNAME),
            )
        }
    }
}