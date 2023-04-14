package city.smartb.registry.program.f2.project.domain.query

import city.smartb.registry.program.s2.project.domain.model.ProjectDTO
import f2.dsl.cqrs.page.OffsetPaginationDTO
import f2.dsl.cqrs.page.PageQueryResultDTO
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Get page of project
 *
 * @d2 function
 * @parent [city.smartb.registry.program.s2.project.domain.D2ProjectSectionApi]
 * @child [ProjectPageQueryDTO]
 * @child [ProjectPageResultDTO]
 */
typealias ProjectPageFunction = F2Function<ProjectPageQuery, ProjectPageResult>

/**
 * @d2 query
 */
@JsExport
@JsName("ProjectPageQueryDTO")
interface ProjectPageQueryDTO {
    val limit: Int?
    val offset: Int?
    val identifier: String?
    val name: String?
    val proponent: String?
    val type: Int?
    val estimatedReductions: String?
    val referenceYear: String?
    val vintage: String?
    val origin: String?
    val dueDate: Long?
    val status: String?
}

/**
 * @d2 inherit
 */
data class ProjectPageQuery(
    override val limit: Int?,
    override val offset: Int?,
    override val identifier: String?,
    override val name: String?,
    override val proponent: String?,
    override val type: Int?,
    override val vintage: String?,
    override val origin: String?,
    override val estimatedReductions: String?,
    override val referenceYear: String?,
    override val dueDate: Long?,
    override val status: String?

): ProjectPageQueryDTO

/**
 * Result of the query to get a page of projects.
 * @d2 event
 */
@JsExport
@JsName("ProjectPageResultDTO")
interface ProjectPageResultDTO: PageQueryResultDTO<ProjectDTO>

/**
 * @d2 inherit
 */
@Serializable
data class ProjectPageResult(
    override val items: List<ProjectDTO>,
    override val total: Int,
    override val pagination: OffsetPaginationDTO
): ProjectPageResultDTO
