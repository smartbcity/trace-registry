package city.smartb.registry.program.f2.project.api.service

import cccev.dsl.client.CCCEVClient
import cccev.f2.certification.domain.query.CertificationGetQueryDTOBase
import cccev.s2.certification.domain.model.Evidence
import city.smartb.fs.s2.file.domain.model.FilePath
import city.smartb.registry.program.s2.project.api.ProjectFinderService
import city.smartb.registry.program.s2.project.domain.automate.ProjectState
import city.smartb.registry.program.s2.project.domain.model.Project
import city.smartb.registry.program.s2.project.domain.model.ProjectId
import city.smartb.registry.program.s2.project.domain.model.ProjectIdentifier
import f2.dsl.cqrs.filter.Match
import f2.dsl.cqrs.page.OffsetPagination
import f2.dsl.cqrs.page.PageDTO
import f2.dsl.fnc.invokeWith
import org.springframework.stereotype.Service

@Service
class ProjectF2FinderService(
    private val cccevClient: CCCEVClient,
    private val projectFinderService: ProjectFinderService
) {
    suspend fun getOrNull(id: ProjectId): Project? {
        return projectFinderService.getOrNull(id)
    }

    suspend fun get(id: ProjectId): Project {
        return projectFinderService.get(id)
    }

    suspend fun page(
        identifier: Match<ProjectIdentifier>? = null,
        name: Match<String>? = null,
        proponent: Match<String>? = null,
        type: Match<Int>? = null,
        estimatedReductions: Match<String>? = null,
        referenceYear: Match<String>? = null,
        vintage: Match<String>? = null,
        dueDate: Match<Long>? = null,
        status: Match<ProjectState>? = null,
        origin: Match<String>? = null,
        offset: OffsetPagination? = null,
    ): PageDTO<Project> {
        return projectFinderService.page(
            identifier = identifier,
            name = name,
            proponent = proponent,
            type = type,
            estimatedReductions = estimatedReductions,
            referenceYear = referenceYear,
            vintage = vintage,
            dueDate = dueDate,
            origin = origin,
            status = status,
            offset = offset
        )
    }

    suspend fun listFiles(id: ProjectId): List<FilePath> {
        val project = projectFinderService.get(id)

        val certificationId = project.certification?.id
            ?: return emptyList()

        val certification = CertificationGetQueryDTOBase(
            id = certificationId
        ).invokeWith(cccevClient.certificationClient.certificationGet()).item

        return certification?.evidences
            .orEmpty()
            .values
            .flatten()
            .mapNotNull(Evidence::file)
    }
}
