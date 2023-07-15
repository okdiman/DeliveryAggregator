plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_pdf.impl"
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
    compose()
    navigation()
    viewModel()
    pdf()
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.pdf_api))
}