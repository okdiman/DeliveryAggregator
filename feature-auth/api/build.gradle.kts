plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
    id(Plugins.serialization)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_auth.api"
    defaultConfig {
        minSdk = Settings.minSdk
    }
}

dependencies {
    network()
    compose()
}