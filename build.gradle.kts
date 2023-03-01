import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "me.kaybedenler"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://git.inmo.dev/api/packages/InsanusMokrassar/maven")
}

dependencies {
    // type-safe library for tg-bot API
    implementation("dev.inmo:tgbotapi:6.0.2-branch_6.0.2-build1488")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "19"
}
application {
    mainClass.set("MainKt")
}
