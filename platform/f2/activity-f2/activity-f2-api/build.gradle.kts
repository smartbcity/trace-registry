plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":platform:f2:activity-f2:activity-f2-domain"))
    implementation(project(":platform:s2:project:project-api"))
    implementation(project(":platform:infra:cccev"))
}
