import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.adapterDelegates() {
    add("implementation", Libraries.AdapterDelegates.core)
    add("implementation", Libraries.AdapterDelegates.viewBindingExt)
}

fun DependencyHandler.core() {
    add("implementation", Libraries.Core.core)
    add("implementation", Libraries.Core.appCompat)
    add("implementation", Libraries.Core.fragmentsKtx)
}

fun DependencyHandler.design() {
    add("implementation", Libraries.Design.cardView)
    add("implementation", Libraries.Design.constraintLayout)
    add("implementation", Libraries.Design.material)
    add("implementation", Libraries.Design.recyclerView)
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

fun DependencyHandler.retrofit() {
    add("implementation", Libraries.Retrofit.converter)
    add("implementation", Libraries.Retrofit.core)
}

fun DependencyHandler.viewModel() {
    add("implementation", Libraries.ViewModel.common)
    add("implementation", Libraries.ViewModel.extensions)
    add("implementation", Libraries.ViewModel.lifecycleScope)
    add("implementation", Libraries.ViewModel.livedataExt)
    add("implementation", Libraries.ViewModel.viewModelScope)
}