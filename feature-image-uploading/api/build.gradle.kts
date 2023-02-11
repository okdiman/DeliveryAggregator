plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_image_uploading.api"
    defaultConfig {
        minSdk = Settings.minSdk
    }
}