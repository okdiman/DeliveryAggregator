import org.gradle.api.artifacts.dsl.DependencyHandler


fun DependencyHandler.core() {
    add("implementation", Libraries.Core.coreKtx)
    add("implementation", Libraries.Core.material)
    add("implementation", Libraries.Core.lifecycle)
}

fun DependencyHandler.koin() {
    add("implementation", Libraries.Koin.core)
    add("implementation", Libraries.Koin.android)
}

fun DependencyHandler.room() {
    add("implementation", Libraries.Database.ktx)
    add("implementation", Libraries.Database.runtime)
    add("kapt", Libraries.Database.compile)
}

fun DependencyHandler.network() {
    add("implementation", Libraries.Retrofit.converter)
    add("implementation", Libraries.Retrofit.core)
    add("implementation", Libraries.Serialization.core)
    add("implementation", Libraries.OkHttp.logging)
    add("implementation", Libraries.OkHttp.base)
}

fun DependencyHandler.kotlin() {
    add("implementation", Libraries.Kotlin.stdLib)
    add("implementation", Libraries.Kotlin.coroutinesAndroid)
    add("implementation", Libraries.Kotlin.coroutinesCore)
    add("implementation", Libraries.Kotlin.coroutinesGms)
}

fun DependencyHandler.compose() {
    add("implementation", Libraries.Compose.composeActivity)
    add("implementation", Libraries.Compose.ui)
    add("implementation", Libraries.Compose.tooling)
    add("implementation", Libraries.Compose.material)
}

fun DependencyHandler.coil() {
    add("implementation", Libraries.Coil.compose)
    add("implementation", Libraries.Coil.core)
}

fun DependencyHandler.splash() {
    add("implementation", Libraries.Core.splashScreen)
}

fun DependencyHandler.json() {
    add("implementation", Libraries.Json.gson)
}

fun DependencyHandler.security() {
    add("implementation", Libraries.Security.crypto)
}

fun DependencyHandler.viewModel() {
    add("implementation", Libraries.ViewModel.compose)
    add("implementation", Libraries.ViewModel.core)
    add("implementation", Libraries.ViewModel.odyssey)
}

fun DependencyHandler.navigation() {
    add("implementation", Libraries.Navigation.compose)
    add("implementation", Libraries.Navigation.core)
}