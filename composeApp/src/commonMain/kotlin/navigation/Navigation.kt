package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

object ROUTE {
    const val ROOT = "root"
    const val MAIN = "main"
    const val NESTED = "nested"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ROUTE.MAIN,
        route = ROUTE.ROOT
    ) {
        main(navController)
        nested(navController)
    }
}