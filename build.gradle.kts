plugins {
    id(Plugins.application).version(Settings.gradle).apply(false)
    id(Plugins.library).version(Settings.gradle).apply(false)
    id(Plugins.kotlin).version(Settings.kotlin).apply(false)
    id(Plugins.serialization).version(Settings.serializationPlugin).apply(false)
}

apply {
    from("$rootDir/config/detekt.gradle.kts")
}