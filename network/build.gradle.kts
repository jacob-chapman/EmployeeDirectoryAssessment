import com.jacobchapman.plugins.Libraries

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("android-module")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}
repositories {
    mavenCentral()
    google()
}

androidmodule {
    configureModule {
        isApp = true
        libraries += mutableListOf(
            Libraries.Retrofit,
            Libraries.Moshi,
            Libraries.MoshiConverter,
            Libraries.MoshiKotlin,
            Libraries.MockServer,
            Libraries.ComposeRuntime,
            Libraries.Junit
        )
        modules += mutableListOf(com.jacobchapman.plugins.Modules.Domain)
    }
}
