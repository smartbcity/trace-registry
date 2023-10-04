package city.smartb.registry.program.s2.catalogue.api

import city.smartb.registry.program.s2.catalogue.api.config.CatalogueAutomateExecutor
import city.smartb.registry.s2.catalogue.domain.CatalogueAggregate
import city.smartb.registry.s2.catalogue.domain.command.CatalogueCreateCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueCreatedEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueDeleteCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueDeletedEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueUpdateCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueUpdatedEvent
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class CatalogueAggregateService(
    private val automate: CatalogueAutomateExecutor,
): CatalogueAggregate {

	override suspend fun create(cmd: CatalogueCreateCommand): CatalogueCreatedEvent = automate.init(cmd) {
		CatalogueCreatedEvent(
			id = UUID.randomUUID().toString(),
			date = System.currentTimeMillis(),
			identifier = cmd.identifier,
			description = cmd.description,
			title = cmd.title,
			catalogues = arrayListOf(),
			themes = arrayListOf(),
			type = cmd.type,
			img = cmd.img,
			homepage = cmd.homepage,
		)
	}

	override suspend fun update(cmd: CatalogueUpdateCommand): CatalogueUpdatedEvent = automate.transition(cmd) {
		CatalogueUpdatedEvent(
			id = it.id,
			date = System.currentTimeMillis(),
			identifier = cmd.identifier,
			description = cmd.description,
			title = cmd.title,
			type = cmd.type,
			homepage = cmd.homepage,
			img = cmd.img,
		)
	}

	override suspend fun delete(cmd: CatalogueDeleteCommand): CatalogueDeletedEvent = automate.transition(cmd) {
		CatalogueDeletedEvent(
			id = it.id,
			date = System.currentTimeMillis(),
		)
	}
}
