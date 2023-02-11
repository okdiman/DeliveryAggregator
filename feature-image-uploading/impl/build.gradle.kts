plugins {
    id(Plugins.kotlin)
    id(Plugins.library)
}

android {
    compileSdk = Settings.targetSdk
    namespace = "trinity_monsters.delivery_aggregator.feature_image_uploading.impl"
    defaultConfig {
        minSdk = Settings.minSdk
    }
}

dependencies {
    network()
    koin()
    implementation(project(Modules.image_uploading_api))
}