package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Root : Route()

    @Serializable
    data object Main : Route()

    @Serializable
    data object Nested : Route()
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Main,
        route = Route.Root::class
    ) {
        main(navController)
        nested(navController)
    }
}