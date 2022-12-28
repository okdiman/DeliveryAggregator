plugins {
    id(Plugins.application)
    id(Plugins.kotlin)
    id(Plugins.serialization)
}

android {
    namespace = Settings.applicationId
    compileSdk = Settings.targetSdk

    defaultConfig {
        applicationId = Settings.applicationId
        minSdk = Settings.minSdk
        targetSdk = Settings.targetSdk
        versionCode = Settings.debugVersionCode
        versionName = Settings.debugVersionName
    }

    buildTypes {
        release {
            isMinifyEnabled = false
//            proguardFiles getDefaultProguardFile ('proguard-android-optimize.txt'), 'proguard-rules.pro'
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
    viewModel()
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.splash))
    implementation(project(Modules.auth_api))
    implementation(project(Modules.auth_impl))
    implementation(project(Modules.registration_api))
    implementation(project(Modules.registration_impl))
    implementation(project(Modules.profile_api))
    implementation(project(Modules.profile_impl))
    implementation(project(Modules.order_api))
    implementation(project(Modules.order_impl))
    implementation(project(Modules.navigation))
    implementation(project(Modules.address_api))
    implementation(project(Modules.address_impl))
}