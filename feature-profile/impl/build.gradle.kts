plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
    id(Plugins.serialization)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_profile.impl"
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