plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    application
    id("org.jetbrains.dokka") version "1.9.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    //mordant
    implementation("com.github.ajalt.mordant:mordant:2.3.0")
    implementation("net.java.dev.jna:jna:5.13.0")
    //logger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    //serialization
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
    jvmToolchain(21)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}