plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl"
}

dependencies {
    compose()
    implementation(project(Modules.auth_api))
    implementation(project(Modules.registration_api))
}