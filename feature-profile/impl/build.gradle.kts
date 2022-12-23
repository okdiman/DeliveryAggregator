plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl"
}

dependencies {
    navigation()
    compose()
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
}