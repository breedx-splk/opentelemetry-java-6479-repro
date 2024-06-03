plugins {
    java
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation("io.opentelemetry:opentelemetry-sdk:1.31.0")
    testImplementation("io.opentelemetry:opentelemetry-sdk-testing:1.31.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("org.assertj:assertj-core:3.26.0")
}