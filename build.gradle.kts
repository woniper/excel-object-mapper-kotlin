plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.5.10"
}

group "org.example"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation ("org.apache.poi:poi:4.1.1")
    implementation ("org.apache.poi:poi-ooxml:4.1.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")
    testImplementation ("org.assertj:assertj-core:3.14.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.3.72")
}
