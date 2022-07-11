object Versions {
    const val gradle = "7.2.0"
    const val kotlin = "1.6.10"
    const val compose = "1.1.1"
    const val accompanist = "0.24.10-beta"
    const val navigation = "2.4.2"
    const val activityCompose = "1.4.0"
    const val junit = "4.13.2"
    const val turbine = "0.8.0"
    const val retrofit = "2.9.0"
    const val hilt = "2.42"
    const val moshi = "1.13.0"
    const val okHttp = "4.9.1"
    const val coil = "2.1.0"
    const val coroutines = "1.6.1"
    const val mockito = "4.0.0"
    const val androidHilt = "1.0.0"
    const val material = "1.0.0-alpha01"
    const val coreKtx = "1.7.0"
    const val runtimeKtx = "2.3.1"
}

object Dependencies {

    object Compose {
        const val ui = "androidx.compose:ui:ui:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val preview = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
}