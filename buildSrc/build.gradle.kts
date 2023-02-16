plugins {
	`kotlin-dsl`
}

repositories {
	mavenCentral()
	maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
	mavenLocal()
}

dependencies {
	implementation("city.smartb.fixers.gradle:dependencies:0.12.0")
}
