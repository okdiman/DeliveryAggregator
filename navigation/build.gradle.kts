plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.navigation"
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
    navigation()
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.splash))
    implementation(project(Modules.auth_api))
    implementation(project(Modules.auth_impl))
    implementation(project(Modules.profile_api))
    implementation(project(Modules.profile_impl))
    implementation(project(Modules.registration_api))
    implementation(project(Modules.registration_impl))
    implementation(project(Modules.route_api))
    implementation(project(Modules.route_impl))
}