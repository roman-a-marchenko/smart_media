plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "kg.ram.out_proxy"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.roman-a-marchenko"
                artifactId = "outline_media"
                version = "1.0.0"
            }
        }
    }
}

dependencies {
    implementation("kg.ram.outline-sdk:outline_sdk:1.0.0")
    implementation(libs.coroutines)
    implementation(libs.okhttp)
    implementation(libs.androidx.webkit)
    implementation(libs.androidx.media3.datasource.okhttp)
    implementation(libs.gson)
    api(libs.androidx.media3.exoplayer)
    api(libs.androidx.media3.ui)
    api(libs.androidx.media3.exoplayer.hls)
}