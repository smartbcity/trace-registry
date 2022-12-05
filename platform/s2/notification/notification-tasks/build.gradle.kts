plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
	kotlin("plugin.serialization")
}

dependencies {
	implementation(project(":platform:s2:notification:notification-api"))
	implementation(project(":platform:s2:project:project-api"))
}
