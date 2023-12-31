plugins {
	id("city.smartb.fixers.gradle.kotlin.mpp")
	id("city.smartb.fixers.gradle.publish")
	kotlin("plugin.serialization")
}

dependencies {
	commonMainApi(project(":platform:s2:project:project-domain"))

	Dependencies.Mpp.f2 { commonMainApi(it) }
	Dependencies.Mpp.fs { commonMainApi(it) }
	Dependencies.Jvm.Cccev.client(::commonMainApi)
	Dependencies.Jvm.Cccev.dsl(::commonMainApi)
}
