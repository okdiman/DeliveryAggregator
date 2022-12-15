plugins {
    id(Plugins.application).version(Settings.gradle).apply(false)
    id(Plugins.library).version(Settings.gradle).apply(false)
    id(Plugins.kotlin).version(Settings.kotlin).apply(false)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10" apply false
}

apply {
    from("$rootDir/config/detekt.gradle.kts")
}