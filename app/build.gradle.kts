plugins {
    id(Plugins.application)
    id(Plugins.kotlin)
    id(Plugins.serialization)
    id(Plugins.googleServices)
    id(Plugins.crashlytics)
}

android {
    namespace = "trinity_monsters.delivery_aggregator"
    compileSdk = Settings.targetSdk

    defaultConfig {
        applicationId = Settings.applicationId
        minSdk = Settings.minSdk
        targetSdk = Settings.targetSdk
        versionCode = Settings.versionCode
        versionName = Settings.versionName
    }

    flavorDimensions.add(Settings.Flavors.name)
    productFlavors {
        create(Settings.Flavors.client) {
            dimension = Settings.Flavors.name
            applicationIdSuffix = Settings.Flavors.clientSuffix
        }
        create(Settings.Flavors.contractor) {
            dimension = Settings.Flavors.name
            applicationIdSuffix = Settings.Flavors.contractorSuffix
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            //            proguardFiles getDefaultProguardFile ('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
        debug {
            applicationIdSuffix = Settings.debugApplicationIdSuffix
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Settings.composeCompiler
    }
    kotlinOptions {
        jvmTarget = Settings.kotlinJwm
    }
}

dependencies {
    koin()
    core()
    splash()
    compose()
    navigation()
    firebase()
    time()
    leakCanary()
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.splash))
    implementation(project(Modules.auth_api))
    implementation(project(Modules.auth_impl))
    implementation(project(Modules.registration_api))
    implementation(project(Modules.registration_impl))
    implementation(project(Modules.profile_api))
    implementation(project(Modules.profile_impl))
    implementation(project(Modules.route_api))
    implementation(project(Modules.route_impl))
    implementation(project(Modules.navigation))
    implementation(project(Modules.dev_menu))
    implementation(project(Modules.address_api))
    implementation(project(Modules.address_impl))
    implementation(project(Modules.image_uploading_api))
    implementation(project(Modules.image_uploading_impl))
}