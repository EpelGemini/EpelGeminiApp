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

rootProject.name = "EpelGeminiApp"
include(":app")
include(":core")
include(":core_ui")
include(":journal")
include(":journal:journal_presentation")
include(":journal:journal_domain")
include(":journal:journal_data")
