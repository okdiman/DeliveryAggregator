plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_splash_screen"
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
    viewModel()
    navigation()
    koin()
    implementation(project(Modules.auth_api))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.core))
}