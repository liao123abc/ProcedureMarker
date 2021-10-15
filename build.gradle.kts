import org.jetbrains.compose.compose
fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("org.jetbrains.intellij") version "1.1.4"
    java
    kotlin("jvm") version "1.5.31"
    // __LATEST_COMPOSE_RELEASE_VERSION__
    id("org.jetbrains.compose") version "1.0.0-alpha4-build361"
    id("idea")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(compose.desktop.currentOs)
    testImplementation("junit", "junit", "4.12")
}

fun getLocalDistributionPath(): String {
    return if (System.getenv("DEV_LINE") != null) {
        "${System.getProperty("user.dir")}/2020.3.1/Contents" //仅用于rdm//aplus编译，控制体积已删除plugins文件夹，不能用于gui启动展示
    } else {
        //本地as路径
        if (org.gradle.internal.os.OperatingSystem.current().isWindows) {
            //"D:\\android-studio-ide-202.7141121-windows\\android-studio"
            "D:\\android-studio-2020.3.1.5-windows\\android-studio"
        } else {
            "/Applications/Android Studio.app"
        }
    }
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
//    version.set("2021.2")
    localPath.set(properties("local"))
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    instrumentCode {
        compilerVersion.set("203.7717.56")
    }
}
tasks.getByName("buildSearchableOptions").onlyIf { false }
