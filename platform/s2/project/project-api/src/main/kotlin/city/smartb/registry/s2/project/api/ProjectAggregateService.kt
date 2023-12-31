package city.smartb.registry.s2.project.api

import cccev.dsl.client.CCCEVClient
import cccev.dsl.model.RequirementId
import cccev.f2.requirement.domain.query.RequirementGetByIdentifierQueryDTOBase
import cccev.s2.certification.domain.command.CertificationCreateCommand
import cccev.s2.certification.domain.command.CertificationCreatedEvent
import cccev.s2.requirement.domain.model.RequirementIdentifier
import city.smartb.registry.s2.project.api.config.ProjectAutomateExecutor
import city.smartb.registry.s2.project.api.entity.applyCmd
import city.smartb.registry.s2.project.domain.ProjectAggregate
import city.smartb.registry.s2.project.domain.automate.ProjectState
import city.smartb.registry.s2.project.domain.command.ProjectAbstractMsg
import city.smartb.registry.s2.project.domain.command.ProjectAddAssetPoolCommand
import city.smartb.registry.s2.project.domain.command.ProjectAddedAssetPoolEvent
import city.smartb.registry.s2.project.domain.command.ProjectChangePrivacyCommand
import city.smartb.registry.s2.project.domain.command.ProjectChangedPrivacyEvent
import city.smartb.registry.s2.project.domain.command.ProjectCreateCommand
import city.smartb.registry.s2.project.domain.command.ProjectCreatedEvent
import city.smartb.registry.s2.project.domain.command.ProjectDeleteCommand
import city.smartb.registry.s2.project.domain.command.ProjectDeletedEvent
import city.smartb.registry.s2.project.domain.command.ProjectUpdateCommand
import city.smartb.registry.s2.project.domain.command.ProjectUpdatedEvent
import city.smartb.registry.s2.project.domain.model.CertificationRef
import f2.dsl.fnc.invokeWith
import java.util.UUID
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class ProjectAggregateService(
    private val automate: ProjectAutomateExecutor,
    private val cccevClient: CCCEVClient,
): ProjectAggregate {

	override suspend fun create(cmd: ProjectCreateCommand): ProjectCreatedEvent = automate.init(cmd) {
		cmd.checkType()
		ProjectCreatedEvent(
			id = UUID.randomUUID().toString(),
			date = System.currentTimeMillis(),
			identifier = cmd.identifier,
			name = cmd.name,
			indicator = cmd.indicator,
			isPrivate = cmd.isPrivate
		).applyCmd(cmd)
		.applyCCCEVCertification()
	}

	private fun ProjectAbstractMsg.checkType() {
		check(type != null && type!! > 0 && type!! <= 25) { "Project type is required" }
	}

	private suspend fun ProjectCreatedEvent.applyCCCEVCertification(): ProjectCreatedEvent {
		val requestCreated = createCCCEVCertification()
		return requestCreated?.let { event ->
			copy(certification = CertificationRef(id = event.id, identifier = event.identifier))
		} ?: this
	}

	private suspend fun ProjectCreatedEvent.createCCCEVCertification(): CertificationCreatedEvent? {
		return activities?.asFlow()
			?.mapNotNull { findId(it) }
			?.toList()
			?.ifEmpty { null }
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
		cmd.checkType()
		ProjectUpdatedEvent(
			id = UUID.randomUUID().toString(),
			date = System.currentTimeMillis(),
			status = ProjectState.STAMPED,
			identifier = cmd.identifier,
			name = cmd.name,
			indicator = cmd.indicator
		).applyCmd(cmd)
	}

	override suspend fun delete(cmd: ProjectDeleteCommand): ProjectDeletedEvent = automate.transition(cmd) {
		ProjectDeletedEvent(
			id = it.id,
			date = System.currentTimeMillis(),
		)
	}

	override suspend fun addAssetPool(command: ProjectAddAssetPoolCommand) = automate.transition(command) {
		ProjectAddedAssetPoolEvent(
			id = command.id,
			date = System.currentTimeMillis(),
			poolId = command.poolId
		)
	}

	override suspend fun changePrivacy(command: ProjectChangePrivacyCommand) = automate.transition(command) {
		ProjectChangedPrivacyEvent(
			id = command.id,
			date = System.currentTimeMillis(),
			isPrivate = command.isPrivate
		)
	}
}
