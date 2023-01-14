plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
    id(Plugins.serialization)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_profile.api"
    defaultConfig {
        minSdk = Settings.minSdk
    }
}

dependencies {
    network()
    compose()
}