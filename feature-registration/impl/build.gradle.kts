plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Settings.composeCompiler
    }
}

dependencies {
    compose()
    viewModel()
    navigation()
    koin()
    implementation(project(Modules.auth_api))
    implementation(project(Modules.registration_api))
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
}