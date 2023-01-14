plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl"
    defaultConfig {
        minSdk = Settings.minSdk
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Settings.composeCompiler
    }
}

dependencies {
    compose()
    koin()
    navigation()
    network()
    viewModel()
    implementation(project(Modules.profile_api))
    implementation(project(Modules.address_api))
    implementation(project(Modules.address_impl))
    implementation(project(Modules.auth_api))
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
}