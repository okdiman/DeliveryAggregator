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
    ":feature-address:api",
    ":feature-address:impl",
    ":feature-profile:api",
    ":feature-profile:impl",
    ":feature-auth:api",
    ":feature-auth:impl",
    ":feature-registration:api",
    ":feature-registration:impl",
    ":feature-route:api",
    ":feature-route:impl",
    ":feature-splash-screen"
)