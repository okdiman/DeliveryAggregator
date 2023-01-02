pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Wildberries Delivery Aggregator"
include(
    ":app",
    ":core",
    ":core-ui",
    ":navigation",
    ":feature-splash-screen",
    ":feature-auth:api",
    ":feature-auth:impl",
    ":feature-registration:api",
    ":feature-registration:impl",
    ":feature-profile:api",
    ":feature-profile:impl",
    ":feature-order:api",
    ":feature-order:impl",
    ":feature-address:api",
    ":feature-address:impl"
)