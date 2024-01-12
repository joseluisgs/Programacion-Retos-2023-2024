plugins {
    kotlin("jvm") version "1.9.21"
    id ("org.jetbrains.dokka") version "1.9.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations ["compileClasspath"].forEach { file: File ->
        from (zipTree (file.absoluteFile) )
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}