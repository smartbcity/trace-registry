package city.smartb.registry.program.f2.project.domain.query

import city.smartb.registry.program.s2.project.domain.model.ProjectDTO
import f2.dsl.cqrs.page.OffsetPagination
import f2.dsl.cqrs.page.PageDTO
import f2.dsl.fnc.F2Function
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
    val id: String?
    val name: String?
    val proponent: String?
    val type: String?
    val estimatedReductions: String?
    val referenceYear: String?
    val dueDate: Long?
    val status: String?
    val offset: Int?
    val limit: Int?
}

/**
 * @d2 inherit
 */
data class ProjectPageQuery(
    override val id: String?,
    override val name: String?,
    override val offset: Int?,

    override val limit: Int?,
    override val proponent: String?,
    override val type: String?,
    override val estimatedReductions: String?,
    override val referenceYear: String?,
    override val dueDate: Long?,
    override val status: String?,
): ProjectPageQueryDTO

/**
 * Result of the query to get a page of projects.
 * @d2 event
 */
@JsExport
@JsName("ProjectPageResultDTO")
interface ProjectPageResultDTO: PageDTO<ProjectDTO>

/**
 * @d2 inherit
 */
data class ProjectPageResult(
    override val items: List<ProjectDTO>,
    override val total: Int
): ProjectPageResultDTO
