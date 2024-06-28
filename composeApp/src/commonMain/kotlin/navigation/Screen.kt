package navigation

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

    internal fun String.replaceArgumentWithValue(argument: String, value: String) = this.replace("{$argument}", value)
}