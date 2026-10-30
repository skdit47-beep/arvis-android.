plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.jarvis"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.jarvis"
        minSdk = 26
        targetSdk = 35
        versionCode = 2
        versionName = "0.2-wakeword-starter"
    }

    buildFeatures { compose = true }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.02.00"))
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Wake-word engine integration is intentionally not bundled.
    // See README.md: Porcupine requires your own Picovoice access key
    // and a platform-specific .ppn keyword model.
}
