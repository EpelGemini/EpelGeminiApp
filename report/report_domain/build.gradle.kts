plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = Modules.reportDomainNameSpace

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    "coreLibraryDesugaring"("com.android.tools:desugar_jdk_libs:2.0.3")
    "implementation"(project(Modules.core))
}