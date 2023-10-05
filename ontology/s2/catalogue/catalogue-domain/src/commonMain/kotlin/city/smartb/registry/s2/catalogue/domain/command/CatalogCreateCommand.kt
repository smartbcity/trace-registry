package city.smartb.registry.s2.catalogue.domain.command

import city.smartb.registry.s2.catalogue.domain.automate.CatalogueId
import city.smartb.registry.s2.catalogue.domain.automate.CatalogueIdentifier
import city.smartb.registry.s2.catalogue.domain.model.SkosConcept
import city.smartb.registry.s2.catalogue.domain.model.SkosConceptId
import kotlinx.serialization.Serializable

@Serializable
data class CatalogueCreateCommand(
    val identifier: CatalogueIdentifier,
    val type: String,
    val title: String,
    val description: String?,
    val homepage: String?,
    val img: String?,
    val themes: List<SkosConcept>? = null,
    val catalogues: List<CatalogueId>? = null,
): CatalogueInitCommand


@Serializable
data class CatalogueCreatedEvent(
    override val id: CatalogueId,
    val identifier: CatalogueIdentifier,
    val type: String,
    val title: String,
    val description: String? = null,
    val homepage: String? = null,
    val img: String? = null,
    val themes: List<SkosConcept>? = null,
    val catalogues: List<CatalogueId>? = null,
    override val date: Long,
): CatalogueEvent
