package city.smartb.registry.s2.catalog.domain.command

import city.smartb.registry.s2.catalog.domain.automate.CatalogEvent
import city.smartb.registry.s2.catalog.domain.automate.CatalogId
import city.smartb.registry.s2.catalog.domain.automate.CatalogInitCommand
import kotlinx.serialization.Serializable

data class CatalogUpdateCommand(
    val id: CatalogId,
): CatalogInitCommand

@Serializable
data class CatalogUpdatedEvent(
    override val id: CatalogId,
    override val date: Long,
): CatalogEvent