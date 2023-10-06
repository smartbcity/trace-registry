package city.smartb.registry.s2.catalogue.domain

import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkThemesCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkedThemesEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueCreateCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueCreatedEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueDeleteCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueDeletedEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkCataloguesCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkedCataloguesEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueUpdateCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueUpdatedEvent

interface CatalogueAggregate {
	suspend fun create(cmd: CatalogueCreateCommand): CatalogueCreatedEvent
	suspend fun linkCatalogues(cmd: CatalogueLinkCataloguesCommand): CatalogueLinkedCataloguesEvent
	suspend fun linkThemes(cmd: CatalogueLinkThemesCommand): CatalogueLinkedThemesEvent
	suspend fun update(cmd: CatalogueUpdateCommand): CatalogueUpdatedEvent
	suspend fun delete(cmd: CatalogueDeleteCommand): CatalogueDeletedEvent
}
