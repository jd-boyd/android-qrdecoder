plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"


}

android {
    namespace = "com.boydtechnicalsolutions.qrdecoder"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.boydtechnicalsolutions.qrdecoder"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")
    implementation("androidx.activity:activity-compose:1.11.0")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2025.10.00"))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // CameraX
    val cameraxVersion = "1.3.0"
    implementation("androidx.camera:camera-core:$cameraxVersion")
    implementation("androidx.camera:camera-camera2:$cameraxVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
    implementation("androidx.camera:camera-view:$cameraxVersion")

    // ML Kit Barcode Scanning
    implementation("com.google.mlkit:barcode-scanning:17.2.0")
}

tasks.named("preBuild") {
    dependsOn("ktlintCheck")
}

ktlint {
    version.set("1.0.1")
    debug.set(false)
    verbose.set(true)
    android.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(true) // Don't fail build on formatting issues for now

    // Exclude generated files
    filter {
        exclude("**/generated/**")
        exclude("**/build/**")
    }
}

// Custom formatting tasks
tasks.register("formatCode") {
    description = "Format Kotlin code with ktlint"
    group = "formatting"
    dependsOn("ktlintFormat")
}

tasks.register("checkFormat") {
    description = "Check Kotlin code formatting with ktlint"
    group = "verification"
    dependsOn("ktlintCheck")
}
