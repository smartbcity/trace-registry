package city.smartb.registry.f2.activity.domain.model

import cccev.dsl.model.Code
import city.smartb.registry.s2.project.domain.model.CertificationRef
import city.smartb.registry.s2.project.domain.model.CertificationRefDTO
import kotlin.js.JsExport
import kotlinx.serialization.Serializable

/**
 * @d2 hidden
 * @visual json "145f687e-8791-4595-a0f8-7a0824d39190"
 */
typealias ActivityId = String

/**
 * @d2 hidden
 * @visual json "A666"
 */
typealias ActivityIdentifier = String

/**
 * TODO describe all properties with examples
 * @d2 model
 * @parent [city.smartb.registry.f2.activity.domain.D2ActivityF2Page]
 * @order 10
 */
@JsExport
interface ActivityDTO {
    val identifier: ActivityIdentifier
    val certification: CertificationRefDTO?
    val name: String?
    val type: String?
    val description: String?
    val hasQualifiedRelation: List<ActivityIdentifier>

    /**
     * @example [[]]
     */
    val hasRequirement: List<ActivityDTO>
    val progression: Double
}

@Serializable
data class Activity(
    override val identifier: ActivityIdentifier,
    override val certification: CertificationRef?,
    override val name: String?,
    override val type: String?,
    override val description: String?,
    override val hasQualifiedRelation: List<ActivityIdentifier>,
    override val hasRequirement: List<Activity>,
    override val progression: Double,
): ActivityDTO

sealed class RequirementType(val identifier: String): Code {
    object Activity: RequirementType("activity")
    object Step: RequirementType("step")
}
