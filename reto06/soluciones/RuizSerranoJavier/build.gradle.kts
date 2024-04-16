plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    //logger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    //Json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    //SQLite
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    //Scripts
    implementation("org.mybatis:mybatis:3.5.15")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}