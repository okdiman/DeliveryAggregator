plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_address.api"
    defaultConfig {
        minSdk = Settings.minSdk
    }
}

dependencies {
    network()
    compose()
}