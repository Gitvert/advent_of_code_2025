plugins {
    kotlin("jvm") version "2.2.20"
    application
    id("io.github.rascmatt.z3") version "1.0.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }

}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.microsoft.z3:java-jar:4.11.2")
    implementation("io.github.rascmatt:z3-bootstrap:1.0.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}