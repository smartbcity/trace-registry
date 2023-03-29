package city.smartb.registry.program.f2.activity.domain.model

import kotlin.js.JsExport
import kotlinx.serialization.Serializable


/**
 * [{
 * 		"identifier": "P0",
 * 		"name": "P0 - LOI",
 * 		"hasQualifiedRelation": ["P1"]
 * 	},
 * 	{
 * 		"identifier": "P1",
 * 		"name": "P1 - Eligibility",
 * 		"hasRequirement": ["R1", "R2"]
 * 	},
 * 	{
 * 		"identifier": "R1",
 * 		"name": "Survey of eligibility",
 * 		"hasQualifiedRelation": ["R1"]
 * 	},
 * 	{
 * 		"identifier": "R2",
 * 		"name": "Identification"
 * 	}, {
 * 		"identifier": "P2",
 * 		"hasRequirement": ["R1"]
 * 	}]
 */

typealias RequirementIdentifier = String
@JsExport
interface ActivityDTO {
    val identifier: RequirementIdentifier
    val name: String?
    val description: String?
    val hasQualifiedRelation: List<RequirementIdentifier>?
    val hasRequirement: List<RequirementIdentifier>?
}

@JsExport
@Serializable
class Activity(
    override val identifier: RequirementIdentifier,
    override val name: String?,
    override val description: String?,
    override val hasQualifiedRelation: List<RequirementIdentifier>?,
    override val hasRequirement: List<RequirementIdentifier>?
): ActivityDTO