package navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import screens.FirstScreen
import screens.FourthScreen
import screens.SecondScreen
import screens.ThirdScreen
import screens.bottom.EighthScreen

fun NavGraphBuilder.main(navController: NavHostController) {
    navigation(
        startDestination = Screen.First,
        route = Route.Main::class
    ) {
        composable<Screen.First> {
            FirstScreen(navController)
        }

        composable<Screen.Second> {
            SecondScreen(navController)
        }

        composable<Screen.Third> {
            val args = it.toRoute<Screen.Third>()

            ThirdScreen(
                navController = navController,
                greetings = args.greeting
            )
        }

        composable<Screen.Fourth> {
            val args = it.toRoute<Screen.Fourth>()

            FourthScreen(
                navController = navController,
                name = args.name,
                surname = args.surname
            )
        }

        composable<Screen.Eighth> {
            EighthScreen()
        }
    }
}