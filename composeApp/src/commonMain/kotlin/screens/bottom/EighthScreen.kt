package screens.bottom

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import navigation.Screen

@Composable
fun EighthScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController)
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.Eighth.Tab.Home,
        ) {
            composable<Screen.Eighth.Tab.Home> {
                NinthScreen()
            }

            composable<Screen.Eighth.Tab.Edit> {
                TenthScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(navController: NavHostController) {
    val tabs = listOf(
        Screen.Eighth.Tab.Home,
        Screen.Eighth.Tab.Edit,
    )

    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backstackEntry?.destination

    BottomNavigation {
        tabs.forEach { tab ->
            TabItem(tab, currentDestination, navController)
        }
    }
}

@Composable
private fun RowScope.TabItem(
    tab: Screen.Eighth.Tab,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        icon = { Icon(imageVector = tab.icon.toVector(), contentDescription = "navigation_icon_${tab.label}") },
        label = { Text(tab.label) },
        selected = currentDestination?.hierarchy?.any { it == tab } == true,
        onClick = {
            navController.navigate(tab) {
                navController.graph.startDestinationRoute?.let { popUpTo(it) }
                launchSingleTop = true
            }
        },
    )
}

private fun Screen.Eighth.Tab.ICON.toVector() = when (this) {
    Screen.Eighth.Tab.ICON.HOME -> Icons.Default.Home
    Screen.Eighth.Tab.ICON.EDIT -> Icons.Default.Edit
}