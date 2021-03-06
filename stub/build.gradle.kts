plugins {
    id("com.android.application")
}

android {
    val canary = !Config.version.contains(".")

    defaultConfig {
        applicationId = "com.topjohnwu.magisk"
        versionCode = 1
        versionName = Config.version
        buildConfigField("int", "STUB_VERSION", Config.stubVersion)
        buildConfigField("String", "DEV_CHANNEL", Config["DEV_CHANNEL"] ?: "null")
        buildConfigField("boolean", "CANARY", if (canary) "true" else "false")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles("proguard-rules.pro")
        }
    }

    aaptOptions {
        additionalParameters("--package-id", "0x80")
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":app:shared"))
}
