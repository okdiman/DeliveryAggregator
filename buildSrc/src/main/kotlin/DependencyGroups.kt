import org.gradle.api.artifacts.dsl.DependencyHandler

private const val implementation = "implementation"

fun DependencyHandler.core() {
    add(implementation, Libraries.Core.coreKtx)
    add(implementation, Libraries.Core.material)
    add(implementation, Libraries.Core.lifecycle)
    add(implementation, Libraries.Core.lifecycleExt)
}

fun DependencyHandler.koin() {
    add(implementation, Libraries.Koin.core)
    add(implementation, Libraries.Koin.android)
}

fun DependencyHandler.network() {
    add(implementation, Libraries.Retrofit.converter)
    add(implementation, Libraries.Retrofit.core)
    add(implementation, Libraries.Serialization.core)
    add(implementation, Libraries.OkHttp.logging)
    add(implementation, Libraries.OkHttp.base)
}

fun DependencyHandler.kotlin() {
    add(implementation, Libraries.Kotlin.stdLib)
    add(implementation, Libraries.Kotlin.coroutinesAndroid)
    add(implementation, Libraries.Kotlin.coroutinesCore)
    add(implementation, Libraries.Kotlin.coroutinesGms)
}

fun DependencyHandler.compose() {
    add(implementation, Libraries.Compose.composeActivity)
    add(implementation, Libraries.Compose.ui)
    add(implementation, Libraries.Compose.tooling)
    add(implementation, Libraries.Compose.material)
}

fun DependencyHandler.coil() {
    add(implementation, Libraries.Coil.compose)
    add(implementation, Libraries.Coil.core)
}

fun DependencyHandler.splash() {
    add(implementation, Libraries.Core.splashScreen)
}

fun DependencyHandler.json() {
    add(implementation, Libraries.Json.gson)
}

fun DependencyHandler.security() {
    add(implementation, Libraries.Security.crypto)
}

fun DependencyHandler.time() {
    add(implementation, Libraries.DateTime.time)
}

fun DependencyHandler.accompanist() {
    add(implementation, Libraries.Accompanist.permissions)
}

fun DependencyHandler.firebase() {
    add(implementation, Libraries.Firebase.messaging)
    add(implementation, Libraries.Firebase.crashlytics)
}

fun DependencyHandler.viewModel() {
    add(implementation, Libraries.ViewModel.compose)
    add(implementation, Libraries.ViewModel.core)
    add(implementation, Libraries.ViewModel.odyssey)
}

fun DependencyHandler.navigation() {
    add(implementation, Libraries.Navigation.compose)
    add(implementation, Libraries.Navigation.core)
}