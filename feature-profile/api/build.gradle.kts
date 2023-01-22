plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_profile.api"
    defaultConfig {
        minSdk = Settings.minSdk
    }
}

dependencies {
    network()
    compose()
}