plugins {
    id("com.android.library")
    kotlin("plugin.serialization") version Kotlin.version
    id("kotlin-kapt")
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = Modules.coreNameSpace

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"XXX\"")
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    "coreLibraryDesugaring"("com.android.tools:desugar_jdk_libs:2.0.3")
    "implementation"(Ktor.ktorClientCore)
    "implementation"(Ktor.ktorClientAndroid)
    "implementation"(Ktor.ktorSerialization)
    "implementation"(Ktor.ktorSerializationJson)
    "implementation"(Ktor.ktorClientLogging)
    "implementation"(Ktor.ktorClientAuth)
    "implementation"(Ktor.logBackClassic)

    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
    "implementation"(Room.roomPaging)
    "kapt"(Room.roomCompiler)

    "implementation"(Room.sqliteChiper)
    "implementation"(Room.sqlite)

    "implementation"(AndroidX.encPreference)
    "implementation"(AndroidX.dataStore)

    "implementation"(platform(Firebase.firebaseBom))
    "implementation"(Firebase.firebaseStorage)
    "implementation"(Firebase.playServiceCoroutine)
}