plugins {
	id("city.smartb.fixers.gradle.kotlin.mpp")
	kotlin("plugin.serialization")
}

dependencies {
	Dependencies.Mpp.cccevDomain(::commonMainApi)
	Dependencies.Mpp.im(::commonMainApi)
}
