plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_address.impl"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Settings.composeCompiler
    }
}

dependencies {
    network()
    koin()
    compose()
    navigation()
    implementation(project(Modules.core_ui))
    implementation(project(Modules.address_api))
}