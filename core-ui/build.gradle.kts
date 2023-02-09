plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}


android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.core_ui"
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
    viewModel()
    navigation()
    implementation(project(Modules.core))
}