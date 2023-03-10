import org.gradle.api.artifacts.dsl.DependencyHandler

private const val IMPLEMENTATION = "implementation"

fun DependencyHandler.core() {
    add(IMPLEMENTATION, Libraries.Core.coreKtx)
    add(IMPLEMENTATION, Libraries.Core.material)
    add(IMPLEMENTATION, Libraries.Core.lifecycle)
    add(IMPLEMENTATION, Libraries.Core.lifecycleExt)
}

fun DependencyHandler.koin() {
    add(IMPLEMENTATION, Libraries.Koin.core)
    add(IMPLEMENTATION, Libraries.Koin.android)
}

fun DependencyHandler.network() {
    add(IMPLEMENTATION, Libraries.Retrofit.converter)
    add(IMPLEMENTATION, Libraries.Retrofit.core)
    add(IMPLEMENTATION, Libraries.Serialization.core)
    add(IMPLEMENTATION, Libraries.OkHttp.logging)
    add(IMPLEMENTATION, Libraries.OkHttp.base)
}

fun DependencyHandler.kotlin() {
    add(IMPLEMENTATION, Libraries.Kotlin.stdLib)
    add(IMPLEMENTATION, Libraries.Kotlin.coroutinesAndroid)
    add(IMPLEMENTATION, Libraries.Kotlin.coroutinesCore)
    add(IMPLEMENTATION, Libraries.Kotlin.coroutinesGms)
}

fun DependencyHandler.compose() {
    add(IMPLEMENTATION, Libraries.Compose.composeActivity)
    add(IMPLEMENTATION, Libraries.Compose.ui)
    add(IMPLEMENTATION, Libraries.Compose.tooling)
    add(IMPLEMENTATION, Libraries.Compose.material)
}

fun DependencyHandler.coil() {
    add(IMPLEMENTATION, Libraries.Coil.compose)
    add(IMPLEMENTATION, Libraries.Coil.core)
}

fun DependencyHandler.splash() {
    add(IMPLEMENTATION, Libraries.Core.splashScreen)
}

fun DependencyHandler.security() {
    add(IMPLEMENTATION, Libraries.Security.crypto)
}

fun DependencyHandler.time() {
    add(IMPLEMENTATION, Libraries.DateTime.time)
}

fun DependencyHandler.accompanist() {
    add(IMPLEMENTATION, Libraries.Accompanist.permissions)
}

fun DependencyHandler.firebase() {
    add(IMPLEMENTATION, Libraries.Firebase.messaging)
    add(IMPLEMENTATION, Libraries.Firebase.crashlytics)
}

fun DependencyHandler.viewModel() {
    add(IMPLEMENTATION, Libraries.ViewModel.compose)
    add(IMPLEMENTATION, Libraries.ViewModel.core)
    add(IMPLEMENTATION, Libraries.ViewModel.odyssey)
}

fun DependencyHandler.navigation() {
    add(IMPLEMENTATION, Libraries.Navigation.compose)
    add(IMPLEMENTATION, Libraries.Navigation.core)
}
