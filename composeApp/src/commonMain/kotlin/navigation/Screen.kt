package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object FirstScreen : Screen(route = "firstScreen")
    data object SecondScreen : Screen(route = "secondScreen")

    data object ThirdScreen : Screen(route = "thirdScreen/{${ARGUMENTS.GREETING}}") {
        fun withGreetings(greeting: String) = route.replaceArgumentWithValue(ARGUMENTS.GREETING, greeting)

        object ARGUMENTS {
            const val GREETING = "greeting"
        }
    }

    data object FourthScreen : Screen(
        route = "fourthScreen"
                + "?${ARGUMENTS.NAME}={${ARGUMENTS.NAME}}"
                + "&${ARGUMENTS.SURNAME}={${ARGUMENTS.SURNAME}}"
    ) {
        fun withName(name: String) = route.replaceArgumentWithValue(ARGUMENTS.NAME, name)

        fun withNameAndSurname(name: String, surname: String) = route
            .replaceArgumentWithValue(ARGUMENTS.NAME, name)
            .replaceArgumentWithValue(ARGUMENTS.SURNAME, surname)

        object ARGUMENTS {
            const val NAME = "name"
            const val SURNAME = "surname"
        }
    }

    data object FifthScreen : Screen(route = "fifthScreen")
    data object SixthScreen : Screen(route = "sixthScreen")
    data object SeventhScreen : Screen(route = "seventhScreen")

    data object EighthScreen : Screen(route = "eighthScreen")
    sealed class Tab(route: String, val icon: ImageVector, val label: String) : Screen(route) {
        data object NinthScreen : Tab(route = "ninthScreen", icon = Icons.Default.Home, label = "Ninth")
        data object TenthScreen : Tab(route = "tenthScreen", icon = Icons.Default.Edit, label = "Tenth")
    }

    internal fun String.replaceArgumentWithValue(argument: String, value: String) = this.replace("{$argument}", value)
}