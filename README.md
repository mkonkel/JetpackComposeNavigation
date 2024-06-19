# JetpackCompose Navigation

This is a Kotlin Multiplatform project targeting Android and iOS where I will showcase the JetpackCompose as the app
navigation. Since the navigation is moved from android to multiplatform project we definitely should give it a try [link](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html).

Assumptions:

- Application should allow us to navigate from one screen to another.
- Application should allow to pass some parameters from first to second screen.
- Application should handle the screen rotation without loosing data.
- Application should handle the Tab Navigation.
- Application should handle the async operations with coroutines.

In the next posts I will also cover the [Voyager](https://github.com/mkonkel/VoyagerNavigation), [Apyx](https://github.com/mkonkel/AppyxNavigation) and
[Decompose](https://github.com/mkonkel/DecomposeNavigation) navigation libraries.

### The project:

Base project setup as always is made with [Kotlin Multiplatform Wizard](https://kmp.jetbrains.com), we also need to add
some [navigation-compose](https://developer.android.com/develop/ui/compose/navigation) as it is the core
thing that we would like to examine, according to the [documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html#sample-project) we should use `navigation` in version `2.7.0-alpha07`

*libs.versions.toml*

```toml
[versions]
navigation-compose = "2.7.0-alpha07"

[libraries]
navigation-compose = { module = "org.jetbrains.androidx.navigation:navigation-compose", version.ref = "navigation-compose" }
```

Freshly added dependencies needs to be synced with the project and added to the ***build.gradle.kts***

```kotlin 
plugins {
    alias(libs.plugins.kotlinSerialization)
}

sourceSets {
    commonMain.dependencies {
        ...
        implementation(libs.navigation.compose)
    }
}
```

Note: The recent release of [JetpackNavigation](https://developer.android.com/jetpack/androidx/releases/navigation#2.8.0-alpha08) adds `Safe Args` which is a convenient way of defining routes with usage of  [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - but it's not available yet in compose multiplatform `1.6.11`. I hope it changes soon, but for now we need to define routes as plain strings.
Each version of `Compose Multiplatform` is built with different dependencies, and it's at least one version behind the `JetpackCompose` for Android. If you are interested what is in the latest release [1.6.11](https://github.com/JetBrains/compose-multiplatform/releases/tag/v1.6.11) please check the documentation. To mitigate the Type Safety routing we will be using a `sealed class` with string parameter.

Getting started. The question is how do the navigation know where to go - it's simple, every destination has its own unique `route` (that can be simply described as a URL address) that defines current screen. The destination in most cases will be a composable function that will be displayed on the screen.

```kotlin
sealed class Screen(val route: String) {
    data object FirstScreen : Screen("first_screen")
    data object SecondScreen : Screen("second_screen")
}
```

Now we can create the `Navigation` composable function. Which will hold the `NavHost` and `navigationController`.
The `NavHost` is the container for displaying the current destination and the `navigationController` is the object that manages the navigation between destinations (screens). The last thing is the `NavGraph` that maps composable destinations and routes.

```kotlin
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        composable(route = Screen.FirstScreen.route) {
            ...
        }
        composable(route = Screen.SecondScreen.route) {
            ...
        }
    }
}
```

With the frame built we should implement some screens. The `FirstScreen` will be a simple screen with a button that will navigate to the `SecondScreen`.

```kotlin
@Composable
fun FirstScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("First screen")
        Button(onClick = { /*TODO navigate to the second screen*/ }) {
            Text("Second Screen")
        }
    }
}
```

```kotlin
@Composable
fun SecondScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Second screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /*TODO navigate back to first screen*/ }) {
            Text("Go Back")
        }
    }
}
```

Now fill the gaps in `Navigation()` function with created screens.
We left some TODOs in the screens. If we want to navigate from screen to screen we need to pass the `navConroler` as an input to our composable and then call `navigate()` method and `popBackStack()` to go back.

```kotlin
Button(onClick = { navController.navigate(Screen.SecondScreen.route) }) { Text("Second Screen") }
```

```kotlin
Button(onClick = { navController.popBackStack() }) { Text("Go Back") }
```
