object Libraries {

    object AdapterDelegates {
        const val core =
            "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Versions.adapterDelegates}"
        const val viewBindingExt =
            "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.adapterDelegates}"
    }

    object Coil {
        const val core = "io.coil-kt:coil:${Versions.coil}"
    }

    object Core {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
        const val core = "androidx.core:core-ktx:${Versions.androidxCore}"
        const val customTabs = "androidx.browser:browser:${Versions.androidxBrowser}"
        const val fragmentsKtx =
            "androidx.fragment:fragment-ktx:${Versions.androidxFragments}"
        const val splashScreen =
            "androidx.core:core-splashscreen:${Versions.splashScreen}"
    }

    object Design {
        const val cardView = "androidx.cardview:cardview:${Versions.androidxCardview}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.androidxRecycler}"
        const val material = "com.google.android.material:material:${Versions.material}"
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

    object Navigation {
        const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object ViewModel {
        const val extensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.viewmodel}"
        const val viewModelScope =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleExt}"
        const val lifecycleScope =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleExt}"
        const val livedataExt =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleExt}"
        const val common =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.viewmodel}"
    }
}