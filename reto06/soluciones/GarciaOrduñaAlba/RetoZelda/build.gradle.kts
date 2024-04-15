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
    // Default
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    //Mockk
    testImplementation("io.mockk:mockk:1.13.10")

    //Mordant
    implementation("com.github.ajalt.mordant:mordant:2.3.0")
    implementation("net.java.dev.jna:jna:5.13.0")
    //Logger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    //JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    //BBDD
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    //ScriptRunner
    implementation("org.mybatis:mybatis:3.5.13")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}