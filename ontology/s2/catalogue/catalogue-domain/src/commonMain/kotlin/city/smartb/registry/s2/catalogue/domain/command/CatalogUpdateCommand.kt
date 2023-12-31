package city.smartb.registry.s2.catalogue.domain.command

import city.smartb.registry.s2.catalogue.domain.automate.CatalogueId
import city.smartb.registry.s2.catalogue.domain.automate.CatalogueIdentifier
import city.smartb.registry.s2.structure.domain.model.Structure
import kotlinx.serialization.Serializable

data class CatalogueUpdateCommand(
    override val id: CatalogueId,
    val identifier: CatalogueIdentifier,
    val type: String,
    var structure: Structure? = null,
    val title: String,
    val description: String?,
    val homepage: String?,
    val img: String?,
): CatalogueCommand

@Serializable
data class CatalogueUpdatedEvent(
    override val id: CatalogueId,
    val identifier: CatalogueIdentifier,
    val type: String,
    var structure: Structure? = null,
    val title: String,
    val description: String?,
    val homepage: String?,
    val img: String?,
    override val date: Long,
): CatalogueEvent
