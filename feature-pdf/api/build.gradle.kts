plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_pdf.api"
    defaultConfig {
        minSdk = Settings.minSdk
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
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
}