// Variables del properties
val logging_version: String by project
val logback_version: String by project
val kotlin_serialization_version: String by project
val mybatis_version: String by project
val sqlite_jdbc_version: String by project

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Driver de la base de datos a usar
    // https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
    implementation("org.xerial:sqlite-jdbc:$sqlite_jdbc_version")

    // log
    implementation("org.lighthousegames:logging:$logging_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlin_serialization_version")

    // tests
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // Mock
    testImplementation("io.mockk:mockk:1.13.10")

    // Para cargar scripts de la base de datos
    implementation("org.mybatis:mybatis:$mybatis_version")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}