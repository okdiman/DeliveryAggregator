plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}


android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.core"

    defaultConfig {
        minSdk = Settings.minSdk
    }
}

dependencies {
    core()
    kotlin()
    network()
    koin()
    security()
}