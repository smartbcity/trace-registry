pluginManagement {
	repositories {
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
		maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
		maven { url = uri("https://repo.spring.io/milestone") }
	}
}

rootProject.name = "registry"

include(
	"platform:api:api-commons",
	"platform:api:api-config",
	"platform:api:api-gateway",
	"platform:api:api-init",
)

include(
	"platform:client:client-dsl",
)

include(
	"ontology:dsl:client",
	"ontology:dsl:dcat",
	"ontology:dsl:skos",
)

include(
	"ontology:f2:catalogue-f2:catalogue-f2-api",
	"ontology:f2:catalogue-f2:catalogue-f2-client",
	"ontology:f2:catalogue-f2:catalogue-f2-domain"
)

include(
	"ontology:s2:catalogue:catalogue-api",
	"ontology:s2:catalogue:catalogue-domain",
)

//include(
//	"ontology:s2:dataset:dataset-api",
//	"ontology:s2:dataset:dataset-domain",
//)


include(
	"platform:script:script-init",
	"platform:script:script-gateway",
)

include(
	"platform:test"
)

include(
	"platform:f2:activity-f2:activity-f2-api",
	"platform:f2:activity-f2:activity-f2-client",
	"platform:f2:activity-f2:activity-f2-domain"
)

include(
	"platform:f2:asset-order-f2:asset-order-f2-api",
	"platform:f2:asset-order-f2:asset-order-f2-client",
	"platform:f2:asset-order-f2:asset-order-f2-domain"
)

include(
	"platform:f2:asset-pool-f2:asset-pool-f2-api",
	"platform:f2:asset-pool-f2:asset-pool-f2-client",
	"platform:f2:asset-pool-f2:asset-pool-f2-domain"
)

include(
	"platform:f2:project-f2:project-f2-api",
	"platform:f2:project-f2:project-f2-client",
	"platform:f2:project-f2:project-f2-domain",
)

include(
    "platform:f2:chat-f2:chat-f2-api",
    "platform:f2:chat-f2:chat-f2-client",
    "platform:f2:chat-f2:chat-f2-domain",
)

include(
	"platform:infra:cccev",
	"platform:infra:fs",
	"platform:infra:im",
	"platform:infra:pdf",
	"platform:infra:redis"
)

include(
	"platform:s2:asset:asset-api",
	"platform:s2:asset:asset-domain",
	"platform:s2:commons",
	"platform:s2:order:order-api",
	"platform:s2:order:order-domain",
	"platform:s2:project:project-api",
	"platform:s2:project:project-domain",
)
