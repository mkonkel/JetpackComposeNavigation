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
thing that we would like to examine.
There is also one thing that we need to add to the project and that is
the [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization) plugin.

*libs.versions.toml*

```toml
[versions]
navigation-compose = "2.7.0-alpha07"
serialization = "1.6.3"

[libraries]
navigation-compose = { module = "org.jetbrains.androidx.navigation:navigation-compose", version.ref = "navigation-compose" }
serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "serialization" }

[plugins]
kotlinSerialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
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