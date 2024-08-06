plugins {
    id ("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = ProjectConfig.nameSpace
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.navigation)
    implementation(Compose.runtime)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(Compose.icons)
    implementation(Compose.material3)
    implementation(Compose.material3Window)

    debugImplementation(Compose.debugUiToolingPreview)
    debugImplementation(Compose.debugUiTestManifest)

    implementation(VideoPlayer.exoPlayer)
    implementation(VideoPlayer.constraintLayout)

    implementation(Accompanist.viewPager)
    implementation(Accompanist.viewPagerIndicator)
    implementation(Accompanist.swipeRefresh)
    implementation(Accompanist.placeholder)
    implementation(Accompanist.flowLayout)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.paging)
    implementation(AndroidX.composePaging)
    implementation(AndroidX.encPreference)
    implementation(AndroidX.dataStore)
    implementation(AndroidX.workManager)
    implementation(AndroidX.workManagerHilt)
    kapt(AndroidX.hiltCompiler)

    implementation (DateTime.dateTime)

    implementation(Coil.coilCompose)
    implementation(Coil.coilVideo)
    implementation(Coil.coilGif)

    implementation(Google.material)
    implementation(Google.mapsCompose)
    implementation(Google.playServicesMaps)
    implementation(Google.playServicesLocation)

    implementation(Serialization.serialization)

    implementation(Ktor.ktorClientCore)
    implementation(Ktor.ktorClientAndroid)
    implementation(Ktor.ktorSerialization)
    implementation(Ktor.ktorSerializationJson)
    implementation(Ktor.ktorClientLogging)
    implementation(Ktor.ktorClientAuth)
    implementation(Ktor.logBackClassic)

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    implementation(ExtLogger.timber)

    implementation(Coroutine.coroutinesAndroid)

    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.journalData))
    implementation(project(Modules.journalDomain))
    implementation(project(Modules.journalPresentation))
    implementation(project(Modules.reportData))
    implementation(project(Modules.reportDomain))
    implementation(project(Modules.reportPresentation))

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.dexMaker)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)

    implementation(platform(Firebase.firebaseBom))
    implementation(Firebase.firebaseStorage)
    implementation(Firebase.playServiceCoroutine)
}