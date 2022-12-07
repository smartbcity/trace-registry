package city.smartb.registry.program.f2.project.domain

import city.smartb.registry.program.f2.project.domain.query.ProjectGetFunction
import city.smartb.registry.program.f2.project.domain.query.ProjectPageFunction

interface ProjectQueryApi {
    /**
     * Get a project by Id
     */
    fun projectGet(): ProjectGetFunction
    /**
     * Get a page of project
     */
    fun projectPage(): ProjectPageFunction
}
