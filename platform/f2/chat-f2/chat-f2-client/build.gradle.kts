plugins {
	id("city.smartb.fixers.gradle.kotlin.mpp")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.serialization")
}

dependencies {
	commonMainApi(project(":platform:f2:chat-f2:chat-f2-domain"))

	Dependencies.Mpp.Ktor.Client.logging(::commonMainImplementation)
	Dependencies.Mpp.Ktor.Client.auth(::commonMainImplementation)

	Dependencies.Mpp.f2Client(::commonMainApi)
}

tasks.withType<Test>().configureEach {
	useJUnitPlatform()
}
