package city.smartb.registry.program.s2.project.api

import cccev.dsl.client.CCCEVClient
import cccev.dsl.model.RequirementId
import cccev.f2.requirement.domain.query.RequirementGetByIdentifierQueryDTOBase
import cccev.s2.certification.domain.command.CertificationCreateCommand
import cccev.s2.certification.domain.command.CertificationCreatedEvent
import cccev.s2.requirement.domain.model.RequirementIdentifier
import city.smartb.registry.program.s2.project.api.config.ProjectAutomateExecutor
import city.smartb.registry.program.s2.project.api.entity.applyCmd
import city.smartb.registry.program.s2.project.domain.ProjectAggregate
import city.smartb.registry.program.s2.project.domain.automate.ProjectState
import city.smartb.registry.program.s2.project.domain.command.ProjectCreateCommand
import city.smartb.registry.program.s2.project.domain.command.ProjectCreatedEvent
import city.smartb.registry.program.s2.project.domain.command.ProjectDeleteCommand
import city.smartb.registry.program.s2.project.domain.command.ProjectDeletedEvent
import city.smartb.registry.program.s2.project.domain.command.ProjectUpdateCommand
import city.smartb.registry.program.s2.project.domain.command.ProjectUpdatedEvent
import city.smartb.registry.program.s2.project.domain.model.CertificationRef
import f2.dsl.fnc.invokeWith
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProjectAggregateService(
	private val cccevClient: CCCEVClient,
	private val automate: ProjectAutomateExecutor,
): ProjectAggregate {

	override suspend fun create(cmd: ProjectCreateCommand): ProjectCreatedEvent = automate.init(cmd) {
		ProjectCreatedEvent(
			id = UUID.randomUUID().toString(),
			identifier = cmd.identifier,
			name = cmd.name
		).applyCmd(cmd)
		.applyCCCEVCertification()
	}

	private suspend fun ProjectCreatedEvent.applyCCCEVCertification(): ProjectCreatedEvent {
		val requestCreated = createCCCEVCertification()
		return requestCreated?.let { event ->
			copy(certification = CertificationRef(id = event.id, identifier = event.identifier))
		} ?: this
	}
	private suspend fun ProjectCreatedEvent.createCCCEVCertification(): CertificationCreatedEvent? {
		return activities?.asFlow()
			?.map { findId(it) }
			?.toList()?.filterNotNull()
			?.takeIf { it.isNotEmpty() }
			?.let { activitiesId ->
				CertificationCreateCommand(
					identifier = identifier.orEmpty(),
					name = name,
					description = description,
					requirements = activitiesId
				).invokeWith(cccevClient.certificationClient.certificationCreate())
			}
	}

	suspend fun findId(identifier: RequirementIdentifier): RequirementId? = RequirementGetByIdentifierQueryDTOBase(
		identifier = identifier,
	).invokeWith(cccevClient.requirementClient.requirementGetByIdentifier())
		.item?.id


	override suspend fun update(cmd: ProjectUpdateCommand): ProjectUpdatedEvent = automate.transition(cmd) {
		ProjectUpdatedEvent(
			id = UUID.randomUUID().toString(),
			status = ProjectState.STAMPED,
			identifier = cmd.identifier,
			name = cmd.name,
		).applyCmd(cmd)
	}

	override suspend fun delete(cmd: ProjectDeleteCommand): ProjectDeletedEvent = automate.transition(cmd) {
		ProjectDeletedEvent(
			id = it.id,
		)
	}
}
