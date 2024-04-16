plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Driver Base de Datos
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    // Scripts Base de Datos
    implementation("org.mybatis:mybatis:3.5.13")
    // Logger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    // Plugin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    // MockK
    testImplementation("io.mockk:mockk:1.13.10")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}