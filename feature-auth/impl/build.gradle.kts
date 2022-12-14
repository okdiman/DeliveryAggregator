import Settings.minSdk

plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_auth.impl"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Settings.composeCompiler
    }
}

dependencies {
    koin()
    coil()
    compose()
    retrofit()
    navigation()
    viewModel()
    implementation(project(Modules.auth_api))
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
}