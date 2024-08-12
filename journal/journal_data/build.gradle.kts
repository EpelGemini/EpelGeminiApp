plugins {
    id("com.android.library")
    kotlin("plugin.serialization") version Kotlin.version
    id("kotlin-kapt")
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"1.1.1.1:8080\"")
        buildConfigField("String", "SQL_PASSWORD", "\"rahasia\"")
    }

    buildFeatures {
        buildConfig = true
    }

    namespace = Modules.journalDataNameSpace

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    "coreLibraryDesugaring"("com.android.tools:desugar_jdk_libs:2.0.3")
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.journalDomain))

    "implementation"(Ktor.ktorClientCore)
    "implementation"(Ktor.ktorClientAndroid)
    "implementation"(Ktor.ktorSerialization)
    "implementation"(Ktor.ktorSerializationJson)
    "implementation"(Ktor.ktorClientLogging)
    "implementation"(Ktor.ktorClientAuth)
    "implementation"(Ktor.logBackClassic)

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)

    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
    "implementation"(Room.roomPaging)
    "kapt"(Room.roomCompiler)

    "implementation"(Room.sqliteChiper)
    "implementation"(Room.sqlite)
}