plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.navigation"
}

dependencies {
    navigation()
    implementation(project(Modules.auth_impl))
    implementation(project(Modules.registration_impl))
}