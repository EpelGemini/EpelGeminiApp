plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = Modules.reportPresentatioNameSpace

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    "coreLibraryDesugaring"("com.android.tools:desugar_jdk_libs:2.0.3")

    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.reportDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Accompanist.viewPager)
}