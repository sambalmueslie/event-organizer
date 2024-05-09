plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
    id("org.jetbrains.kotlin.kapt") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.3.8"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.23"
    id("org.sonarqube") version "5.0.0.4638"
    jacoco
}

version = "0.1"
group = "de.sambalmueslie"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("de.sambalmueslie.eventorg.*")
    }
}

dependencies {
    kapt("io.micronaut.data:micronaut-data-processor")
    kapt("io.micronaut.security:micronaut-security-annotations")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.cache:micronaut-cache-ehcache")
    implementation("io.micronaut.data:micronaut-data-jdbc")
    compileOnly("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("io.micronaut.flyway:micronaut-flyway")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.security:micronaut-security")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.security:micronaut-security-oauth2")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:testcontainers")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("io.mockk:mockk:1.13.10")

    implementation("com.discord4j:discord4j-core:3.2.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.3-native-mt")
}


application {
    mainClass.set("de.sambalmueslie.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("16")
}


jacoco {
    toolVersion = "0.8.7"
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

sonarqube {
    properties {
        property("sonar.projectKey", "sambalmueslie_event-organizer")
        property("sonar.organization", "sambalmueslie")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.jacoco.reportPath", "${project.buildDir}/reports/jacoco/test")
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
    }
}

allOpen {
    annotation("io.micronaut.aop.Around")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "16"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "16"
        }
    }


}
