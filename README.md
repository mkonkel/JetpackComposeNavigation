# JetpackCompose Navigation

This is a Kotlin Multiplatform project targeting Android and iOS where I will showcase the JetpackCompose as the app
navigation. Since the navigation is moved from android to multiplatform project we definitely should give it a
try [link](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html).

Assumptions:

- Application should allow us to navigate from one screen to another.
- Application should allow to pass some parameters from first to second screen.
- Application should handle the screen rotation without loosing data.
- Application should handle the Tab Navigation.
- Application should handle the async operations with coroutines.

In the next posts I will also cover
the [Voyager](https://github.com/mkonkel/VoyagerNavigation), [Apyx](https://github.com/mkonkel/AppyxNavigation) and
[Decompose](https://github.com/mkonkel/DecomposeNavigation) navigation libraries.

### The project:

Base project setup as always is made with [Kotlin Multiplatform Wizard](https://kmp.jetbrains.com), we also need to add
some [navigation-compose](https://developer.android.com/develop/ui/compose/navigation) as it is the core
thing that we would like to examine, according to
the [documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html#sample-project)
we should use `navigation` in version `2.7.0-alpha07`

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

> Note: The recent release
> of [JetpackNavigation](https://developer.android.com/jetpack/androidx/releases/navigation#2.8.0-alpha08)adds `Safe Args`
> which is a convenient way of defining routes with usage
> of  [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - but it's not available yet in compose
> multiplatform `1.6.11`. I hope it changes soon, but for now we need to define routes as plain strings.
>
>Each version of `Compose Multiplatform` is built with different dependencies, and it's at least one version behind
> the `JetpackCompose` for Android. If you are interested what is in the latest
> release [1.6.11](https://github.com/JetBrains/compose-multiplatform/releases/tag/v1.6.11) please check the
> documentation. To mitigate the Type Safety routing we will be using a `sealed class` with string parameter.

### Linear Navigation

Getting started. The question is how do the navigation know where to go - it's simple, every destination has its own
unique `route` (that can be simply described as a URL address) that defines current screen. The destination in most
cases will be a composable function that will be displayed on the screen.

```kotlin
sealed class Screen(val route: String) {
    data object FirstScreen : Screen("firstScreen")
    data object SecondScreen : Screen("secondScreen")
}
```

Now we can create the `Navigation` composable function. Which will hold the `NavHost` and `navigationController`.
The `NavHost` is the container for displaying the current destination and the `navigationController` is the object that
manages the navigation between destinations (screens). The last thing is the `NavGraph` that maps composable
destinations and routes.

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

With the frame built we should implement some screens. The `FirstScreen` will be a simple screen with a button that will
navigate to the `SecondScreen`.

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
We left some TODOs in the screens. If we want to navigate from screen to screen we need to pass the `navConroler` as an
input to our composable and then call `navigate()` method and `popBackStack()` to go back.

```kotlin
Button(onClick = { navController.navigate(Screen.SecondScreen.route) }) { Text("Second Screen") }
```

```kotlin
Button(onClick = { navController.popBackStack() }) { Text("Go Back") }
```

After all that we neet to use our `Navigation()` function in the application entrypoint, for andorid it is
the `MainActivity.kt` and for iOS it is the `MainViewController.kt`.

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation()
        }
    }
}
```

```kotlin
fun MainViewController() = ComposeUIViewController { Navigation() }
```

After running the application we should see the `FirstScreen` with a button that navigates to the `SecondScreen` and a
button that navigates back to the `FirstScreen`.

![Linear Navigation](/blog/linear_navigation_1.gif "linear navigation gif")

### Passing parameters

> Note: The recent release
> of [JetpackNavigation](https://developer.android.com/jetpack/androidx/releases/navigation#2.8.0-alpha08)adds `Safe Args`
> which is a convenient way of passing parameters. Which is not available yet in compose multiplatform `1.6.11`.

There are two types of arguments **required*** and **optional**, optionals should be created with a default value. As
said at the beginning the route looks like the URL address, so we will be passing the parameters as a parts of the
route. We can pass a single or a multiple arguments.
Required arguments should be passed as a `path` in route. Let's create third screen that will accept the `greetings`
parameter.

#### Required Arguments

```kotlin
sealed class Screen(val route: String) {
    ...
    data object ThirdScreen : Screen("thirdScreen/{greetings}")
}
```

Now we need to create the `ThirdScreen` composable function that will accept the `greetings` parameter and provide a way
to pass the arguments.
Since `safe args` are not there, we need to use `NavArgumentBuilder` and explicit define the type of the argument and of
course provide the key for it.

```kotlin
@Composable
fun Navigation() {
    NavHost(...) {
        ...
        composable(
            route = Screen.ThirdScreen.route,
            arguments = listOf(navArgument("greetings") {
                type = NavType.StringType
            })
        ) {
            ThirdScreen(navController)
        }
    }
}
```

Let's modify the `FirstScreen` to navigate to the `ThirdScreen` with the greetings parameter.

```kotlin
@Composable
fun FirstScreen(navController: NavHostController) {
    ...
    Button(
        onClick = {
            val greetings = "Hello from First Screen"
            navController.navigate("thirdScreen/$greetings")
        }
    ) {
        Text("Third Screen")
    }
}
} 
```

We have got the function to call a screen with the argument, now we need to extract the argument from the route and pass
it to the `ThirdScreen` composable.
We need to modify the `ThirdScreen` to accept the `greetings` parameter, and `Navigation` to extract the argument from
the route.

```kotlin
        composable(...) {
    ThirdScreen(navController, it.arguments?.getString("greetings").orEmpty())
}
```

```kotlin
@Composable
fun ThirdScreen(navController: NavHostController, greetings: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Third screen")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Greetings: $greetings")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}
```

![Required Argument](/blog/arguments_2.gif "required argument gif")

There is always place for improvement, we can adjust the `Screen` class to use some const values and functions to help
us with providing right paths and extracting the arguments instead of writing them manually one by one.

```kotlin
sealed class Screen(val route: String) {
    ...
    data object ThirdScreen : Screen(route = "thirdScreen/{${ARGUMENTS.GREETING}}") {
        fun withGreetings(greeting: String) = route.replaceArgumentWithValue(ARGUMENTS.GREETING, greeting)

        object ARGUMENTS {
            const val GREETING = "greeting"
        }
    }

    internal fun String.replaceArgumentWithValue(argument: String, value: String) = this.replace("{$argument}", value)
}
```

We have got a basic route with a single argument, but what if we want to pass multiple arguments? We need to follow same
rules as with one argument - just extend the route with more parts and handle them in `Navigation` and in the desired
screens.

#### Optional Arguments

For optional arguments we will follow same idea as with required arguments. They should be passed as query in the URL
address. They should be preceded by a `?` character and follow the pattern `?key=value`, and if you want to pass
multiple optional parameters they have to be separated with `&` character `?key1=value1&key2=value2`.
You need to remember that for optional argument you need to provide a default value or mark argument as nullable
Let's create the `FourthScreen` that will accept the optional `name` and optional (but nullable) `surname` parameters.

```kotlin
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
```

```kotlin
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
```

```kotlin
fun FirstScreen(navController: NavHostController) {
    ...
    Button(
        onClick = {
            navController.navigate(Screen.FourthScreen.withNameAndSurname("John", "Doe"))
        }
    ) {
        Text("John Doe Screen")
    }

    Button(
        onClick = {
            navController.navigate(Screen.FourthScreen.withName("Michael"))
        }
    ) {
        Text("Michael Screen")
    }
}
```

The FourthScreen should be built in a same way as ThordScreen with proper parameters passed.

![Optional Arguments](/blog/arguments_3.gif "optional argument gif")

