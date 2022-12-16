plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
    id(Plugins.serialization)
    id(Plugins.kotlinParcelize)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_auth.api"
}

dependencies {
    network()
}