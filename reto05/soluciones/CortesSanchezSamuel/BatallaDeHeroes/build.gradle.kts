val logging_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.0"

}
group = "Samuel Cortes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // log
    implementation("org.lighthousegames:logging:$logging_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}