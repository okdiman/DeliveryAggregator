object Libraries {

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val composeActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object Coil {
        const val core = "io.coil-kt:coil:${Versions.coil}"
        const val compose = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
        const val coroutinesGms =
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.kotlinCoroutines}"
    }

    object Core {
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidxCore}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val splashScreen =
            "androidx.core:core-splashscreen:${Versions.splashScreen}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Detekt {
        const val core = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
    }

    object Database {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compile = "androidx.room:room-compiler:${Versions.room}"
    }

    object Json {
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object Serialization {
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object ViewModel {
        const val core = "com.adeo:kviewmodel:${Versions.kViewModel}"
        const val compose = "com.adeo:kviewmodel-compose:${Versions.kViewModel}"
        const val odyssey = "com.adeo:kviewmodel-odyssey:${Versions.kViewModel}"
    }

    object Navigation {
        const val core = "io.github.alexgladkov:odyssey-core:${Versions.odyssey}"
        const val compose = "io.github.alexgladkov:odyssey-compose:${Versions.odyssey}"
    }

    object OkHttp {
        const val base = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    }
}