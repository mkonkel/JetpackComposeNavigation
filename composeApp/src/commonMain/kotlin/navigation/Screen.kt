package navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object First : Screen()

    @Serializable
    data object Second : Screen()

    @Serializable
    data class Third(val greeting: String) : Screen()

    @Serializable
    data class Fourth(val name: String, val surname: String? = null) : Screen()

    @Serializable
    data object Fifth : Screen()

    @Serializable
    data object Sixth : Screen()

    @Serializable
    data object Seventh : Screen()

    @Serializable
    data object Eighth : Screen() {
        @Serializable
        sealed class Tab(val icon: ICON, val label: String) : Screen() {
            @Serializable
            data object Home : Tab(icon = ICON.HOME, label = "Home")

            @Serializable
            data object Edit : Tab(icon = ICON.EDIT, label = "Edit")

            @Serializable
            enum class ICON {
                HOME, EDIT
            }
        }
    }

    @Serializable
    data object Eleventh : Screen()
}