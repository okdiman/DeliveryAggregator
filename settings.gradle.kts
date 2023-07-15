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
rootProject.name = "Delivery Aggregator"
include(
    ":app",
    ":core",
    ":core-ui",
    ":navigation",
    ":dev-menu",
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
    ":feature-image-uploading:api",
    ":feature-image-uploading:impl",
    ":feature-pdf:api",
    ":feature-pdf:impl",
    ":feature-splash-screen"
)