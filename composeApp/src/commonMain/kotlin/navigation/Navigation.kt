package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import screens.FirstScreen
import screens.FourthScreen
import screens.SecondScreen
import screens.ThirdScreen

/**
 * This is a Composable function that sets up the navigation for the application.
 * It uses the Jetpack Compose Navigation component to manage navigation within the app.
 */
@Composable
fun Navigation() {
    // Create a NavController to manage app navigation within a NavHost.
    // The NavController keeps track of the current position within the navigation graph
    // and coordinates the swapping of destination content.
    val navController = rememberNavController()

    // NavHost is the core navigation graph where you specify all the destinations
    // that can be reached in your app. It takes two main parameters: navController and startDestination.
    NavHost(
        // The NavController created above.
        navController = navController,
        // The route of the first screen that should be displayed when the app starts.
        startDestination = Screen.FirstScreen.route
    ) {
        // Inside the NavHost, there are composable blocks, each representing a different screen or destination in your app.
        // Each composable block is associated with a route, which is a unique identifier for navigation.
        // When you want to navigate to a different destination, you'll use this route to tell the NavController where to go.
        // The route for each screen is defined in the Screen object.

        // The FirstScreen Composable function represents the UI for the first screen.
        // It takes in the NavController as a parameter, which can be used to navigate to other screens.
        composable(route = Screen.FirstScreen.route) {
            FirstScreen(navController)
        }

        // The SecondScreen Composable function represents the UI for the second screen.
        // It takes in the NavController as a parameter, which can be used to navigate to other screens.
        composable(route = Screen.SecondScreen.route) {
            SecondScreen(navController)
        }

        // This is a composable block for the ThirdScreen.
        // It is associated with a route, which is a unique identifier for navigation.
        // The route for this screen is defined in the Screen object as Screen.ThirdScreen.route.
        // This composable block also takes a list of arguments. In this case, it takes a single argument "greetings" of type StringType.
        composable(
            route = Screen.ThirdScreen.route,
            arguments = listOf(navArgument(Screen.ThirdScreen.ARGUMENTS.GREETING) {
                type = NavType.StringType
            })
        ) {
            ThirdScreen(navController, it.arguments?.getString(Screen.ThirdScreen.ARGUMENTS.GREETING).orEmpty())
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