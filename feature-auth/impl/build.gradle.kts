plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
    id(Plugins.serialization)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_auth.impl"
    defaultConfig {
        minSdk = Settings.minSdk
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Settings.composeCompiler
    }
    flavorDimensions.add(Settings.Flavors.name)
    productFlavors {
        create(Settings.Flavors.client) {
            dimension = Settings.Flavors.name
        }
        create(Settings.Flavors.contractor) {
            dimension = Settings.Flavors.name
        }
    }
}

dependencies {
    koin()
    coil()
    compose()
    navigation()
    network()
    security()
    viewModel()
    implementation(project(Modules.auth_api))
    implementation(project(Modules.registration_api))
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
}