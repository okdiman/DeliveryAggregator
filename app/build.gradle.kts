plugins {
    id(Plugins.application)
    id(Plugins.kotlin)
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
    kotlinOptions {
        jvmTarget = Settings.kotlinJwm
    }
}

dependencies {
    core()
    design()
    implementation(project(Modules.navigation))
    implementation(project(Modules.core))
    implementation(project(Modules.auth_api))
    implementation(project(Modules.auth_impl))
    implementation(project(Modules.profile_api))
    implementation(project(Modules.profile_impl))
    implementation(project(Modules.order_api))
    implementation(project(Modules.order_impl))
}