plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}


android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.core"

    defaultConfig {
        minSdk = Settings.minSdk
    }
}

dependencies {
    core()
    time()
    kotlin()
    network()
    koin()
    security()
}