plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
    id(Plugins.serialization)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_route.impl"
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
    accompanist()
    compose()
    koin()
    coil()
    time()
    navigation()
    network()
    viewModel()
    implementation(project(Modules.route_api))
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
}