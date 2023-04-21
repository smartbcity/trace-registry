package city.smartb.registry.program.s2.project.domain.model

import city.smartb.registry.program.api.commons.model.GeoLocation
import city.smartb.registry.program.api.commons.model.GeoLocationDTO
import city.smartb.registry.program.s2.project.domain.automate.ProjectState
import kotlinx.serialization.Serializable
import s2.dsl.automate.model.WithS2Id
import s2.dsl.automate.model.WithS2State
import kotlin.js.JsExport
import kotlin.js.JsName


/**
 * Unique id of a project.
 * @d2 hidden
 * @visual json "1669154596778x977338172286597000"
 */
typealias ProjectId = String
typealias ProjectIdentifier = String
typealias ActivityIdentifier = String
typealias SdgNumber = Int

@JsExport
@JsName("ProjectDTO")
interface ProjectDTO: WithS2State<ProjectState>, WithS2Id<ProjectId> {
    /**
     * Unique id of the project.
     */
    val id: ProjectId

    /**
     * Project identifier
     * @example "AX-P0"
     */
    val identifier: ProjectIdentifier?

    /**
     * Project name
     * @example "ProjectName"
     */
    val name: String?

    /**
     * Country of the projets
     * @example "ProjectName"
     */
    val country: String?

    /**
     * Date of the start of the crediting period
     * @example "1670255851"
     */
    val creditingPeriodStartDate: DateTime?

    /**
     * Date of the end of the crediting period
     * @example "1670255851"
     */
    val creditingPeriodEndDate: DateTime?

    /**
     * Description of the project
     * @example "description"
     */
    val description: String?

    /**
     * Expected end date of the project.
     * @example "1670255851"
     */
    val dueDate: DateTime?

    /**
     * Quantity expected to be issued
     * @example 69502
     */
    val estimatedReductions: String?

    /**
     * Place where tu project is developped and impact effective
     * @example "2 rue du pavillon 34000 Montpellier"
     */
    val localization: String?

    /**
     * A project proponent refers to an individual, organization, or entity
     * that develops and implements a project that reduces or removes greenhouse gas emissions.
     */
    val proponent: OrganizationRef?

    /**
     * Identifier of the type of the project
     * @example 4
     */
    val type: Int?

    /**
     * The reference year of the project
     * @example "2022"
     */
    val referenceYear: String?

    /**
     * Date of the end of the crediting period
     * @example "1670255851"
     */
    val registrationDate: DateTime?

    /**
     * Link to the project in the root registry
     * @example 2023
     */
    val vintage: String?

    /**
     * Link to the project in the root registry
     * @example "/app/projectDetail/VCS/2366"
     */
    val slug: String?

    /**
     * VVB stands for Voluntary Carbon Unit Validation Body, an independent
     * organization accredited by the relevant carbon registry.
     */
    val vvb: OrganizationRef?

    /**
     * An Assessor is a third-party individual or organization that assesses the validity
     * of a carbon offset project's emissions reduction claims.
     */
    val assessor: OrganizationRef?

    /**
     * GPS location of the project.
     */
    val location: GeoLocationDTO?

    /**
     * List of activities linked to
     */
    val activities: List<ActivityIdentifier>?
    /**
     * Link to a cccev request.
     */
    val certification: CertificationRefDTO?

    /**
     * Status of the project
     * @example "REGISTERED"
     */
    val status: ProjectState

    /**
     * Date of creation.
     * @example "1670255859"
     */
    val creationDate: DateTime?

    /**
     * Date of last modification of the asset.
     * @example "1670255859"
     */
    val lastModificationDate: DateTime?

    /**
     * List of SDGs the project is linked to.
     * @example [[2, 5, 6]]
     */
    val sdgs: List<SdgNumber>?
}

/**
 * @d2 model
 */
@Serializable
data class Project(
    override val id: ProjectId,
    override val identifier: ProjectIdentifier?,
    override val country: String?,
    override val creditingPeriodStartDate: DateTime?,
    override val creditingPeriodEndDate: DateTime?,
    override val description: String?,
    override val dueDate: DateTime?,
    override val estimatedReductions: String?,
    override val localization: String?,
    override val name: String?,
    override val proponent: OrganizationRef?,
    override val type: Int?,
    override val referenceYear: String?,
    override val registrationDate: DateTime?,
    override val vintage: String?,
    override val slug: String?,
    override val vvb: OrganizationRef?,
    override val assessor: OrganizationRef?,
    override val location: GeoLocation?,
    override val creationDate: DateTime?,
    override val lastModificationDate: DateTime?,
    override val status: ProjectState,
    override val activities: List<ActivityIdentifier>?,
    override var sdgs: List<SdgNumber>?,
    override val certification: CertificationRef?
): ProjectDTO {
    override fun s2State() = status
    override fun s2Id() = id
}

typealias DateTime = Long

@JsExport
interface OrganizationRefDTO {
    val id: String
    val name: String
}

@Serializable
data class OrganizationRef(
    override val id: String,
    override val name: String
): OrganizationRefDTO
