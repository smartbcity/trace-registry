package city.smartb.registry.s2.structure.domain.model

import kotlinx.serialization.Serializable

typealias StructureId = String

@Serializable
data class Structure(
    val type: String,
    val definitions: Map<String, String> = emptyMap(),
)