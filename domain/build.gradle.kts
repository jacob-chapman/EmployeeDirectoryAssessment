import com.jacobchapman.plugins.Libraries

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("android-module")
    id("kotlin-kapt")
}
androidmodule {
    configureModule {
        libraries += listOf(Libraries.ComposeRuntime, Libraries.Junit, Libraries.Turbine, Libraries.CoroutinesTest, Libraries.Mockito)
    }
}
