plugins {
    id("java")
}

group = "org.xxd.processor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation (project(":my-annotation"))
}

tasks.test {
    useJUnitPlatform()
}