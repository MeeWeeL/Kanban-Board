import Versions.glide_version
import Versions.koin_version
import Versions.nav_version
import Versions.okhttp_version
import Versions.retrofit_version
import Versions.room_version
import Versions.rx_version
import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.meeweel.kanban_board"
    const val compile_sdk = 32
    const val min_sdk = 23
    const val target_sdk = 32
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val data = ":data"
}

object Versions {

    // Koin
    const val koin_version = "3.2.0"

    //  Room
    const val room_version = "2.4.2"

    // Retrofit
    const val retrofit_version = "2.9.0"

    // OkHttp
    const val okhttp_version = "4.9.3"

    // RxJava 3
    const val rx_version = "3.0.0"

    // Navigation component
    const val  nav_version = "2.4.2"

    // Glide
    const val  glide_version = "4.12.0"
}

object Koin {
    const val core = "io.insert-koin:koin-core:$koin_version"
    const val android = "io.insert-koin:koin-android:$koin_version"
    const val  android_compat = "io.insert-koin:koin-android-compat:$koin_version"
}

object Room {
    const val runtime = "androidx.room:room-runtime:$room_version"
    const val rxjava3 = "androidx.room:room-rxjava3:$room_version"
    const val compiler_kapt = "androidx.room:room-compiler:$room_version"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
    const val adapter_rxjava3 = "com.squareup.retrofit2:adapter-rxjava3:$retrofit_version"
}

object OkHttp {
    const val okhttp3 = "com.squareup.okhttp3:okhttp:$okhttp_version"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
}

object RxJava3 {
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:$rx_version"
    const val rxjava = "io.reactivex.rxjava3:rxjava:$rx_version"
}

object NavComponent {
    const val fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
    const val ui_ktx = "androidx.navigation:navigation-ui-ktx:$nav_version"
}

object ViewPager2 {
    const val view_pager = "androidx.viewpager2:viewpager2:1.1.0-beta01"
    const val dotsindicator = "com.tbuonomo:dotsindicator:4.2"
}

object Glide {
    const val compiler_kapt = "com.github.bumptech.glide:compiler:$glide_version"
    const val glide = "com.github.bumptech.glide:glide:$glide_version"
}

object Tests {
    const val junit_testImplementation = "junit:junit:4.13.2"
    const val junit_androidTestImplementation = "androidx.test.ext:junit:1.1.3"
    const val espresso_androidTestImplementation = "androidx.test.espresso:espresso-core:3.4.0"
}

object Base {
    const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
    const val appcompat = "androidx.appcompat:appcompat:1.4.2"
    const val material = "com.google.android.material:material:1.6.1"
    const val core_ktx = "androidx.core:core-ktx:1.8.0"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:2.1.4"
}