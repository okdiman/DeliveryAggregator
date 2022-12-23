plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_address.impl"
}

dependencies {
    network()
    koin()
    implementation(project(Modules.address_api))
}