plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}


android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.core"
}

dependencies {
    core()
    kotlin()
    network()
    koin()
    security()
}