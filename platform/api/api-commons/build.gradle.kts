plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    Dependencies.Jvm.f2(::jvmMainImplementation)
    Dependencies.Jvm.fs(::jvmMainImplementation)
    Dependencies.Jvm.jackson(::jvmMainApi)
    Dependencies.Mpp.im(::commonMainApi)
    Dependencies.Mpp.s2(::commonMainApi)

    jvmMainImplementation("city.smartb.s2:s2-spring-boot-starter-utils-logger:${Versions.s2}")
    jvmMainImplementation("city.smartb.i2:i2-spring-boot-starter-auth:${Versions.i2}")
    Dependencies.Jvm.s2StoringData(::jvmMainImplementation)

    jvmMainImplementation("org.springframework.data:spring-data-commons:${Versions.springData}")
    jvmMainImplementation("com.itextpdf:html2pdf:${Versions.html2pdf}")
    jvmMainApi("org.apache.commons:commons-csv:${Versions.apacheCsv}")
}
