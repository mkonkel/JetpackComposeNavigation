package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import screens.bottom.EighthScreen

object ROUTE {
    const val ROOT = "root"
    const val MAIN = "main"
    const val NESTED = "nested"
    const val BOTTOM = "bottom"
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