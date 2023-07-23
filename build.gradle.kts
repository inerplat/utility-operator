import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    id("com.google.devtools.ksp") version "1.9.0-1.0.11"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.9.0"))
    }
}

group = "com.inerplat"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2022.0.3"

dependencies {
    ksp("io.javaoperatorsdk:operator-framework:4.2.8")
    ksp("io.fabric8:crd-generator-apt:6.7.2")
    implementation("io.javaoperatorsdk:operator-framework-spring-boot-starter:4.2.8")
    implementation("io.javaoperatorsdk:operator-framework-kubernetes-spring-boot-starter:4.2.8")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.12")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation("org.springframework.cloud:spring-cloud-starter:4.0.1")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.11")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.8")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
