plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.navigation"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Settings.composeCompiler
    }
}

dependencies {
    compose()
    navigation()
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.auth_api))
    implementation(project(Modules.auth_impl))
    implementation(project(Modules.registration_api))
    implementation(project(Modules.registration_impl))
}