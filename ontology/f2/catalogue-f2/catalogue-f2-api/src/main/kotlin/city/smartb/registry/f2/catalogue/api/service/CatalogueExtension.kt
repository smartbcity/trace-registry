package city.smartb.registry.f2.catalogue.api.service

import city.smartb.registry.f2.catalogue.domain.command.CatalogueCreateCommandDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueCreatedEventDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueDeleteCommandDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueDeletedEventDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueLinkCataloguesCommandDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueLinkDatasetsCommandDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueLinkThemesCommandDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueLinkedCataloguesEventDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueLinkedDatasetsEventDTOBase
import city.smartb.registry.f2.catalogue.domain.command.CatalogueLinkedThemesEventDTOBase
import city.smartb.registry.f2.catalogue.domain.dto.CatalogueDTOBase
import city.smartb.registry.f2.catalogue.domain.dto.CatalogueRefDTOBase
import city.smartb.registry.f2.dataset.api.service.DatasetF2FinderService
import city.smartb.registry.program.s2.catalogue.api.CatalogueFinderService
import city.smartb.registry.s2.catalogue.domain.command.CatalogueCreateCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueCreatedEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueDeleteCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueDeletedEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkCataloguesCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkDatasetsCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkThemesCommand
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkedCataloguesEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkedDatasetsEvent
import city.smartb.registry.s2.catalogue.domain.command.CatalogueLinkedThemesEvent
import city.smartb.registry.s2.catalogue.domain.model.CatalogueModel

suspend fun List<CatalogueModel>.toDTO(
    catalogueFinderService: CatalogueFinderService,
    datasetF2FinderService: DatasetF2FinderService
) = map {
    it.toDTO(catalogueFinderService, datasetF2FinderService)
}
suspend fun CatalogueModel.toDTO(
    catalogueFinderService: CatalogueFinderService,
    datasetF2FinderService: DatasetF2FinderService
): CatalogueDTOBase {
    val cataloguesFetched = catalogues?.mapNotNull {
        catalogueFinderService.getOrNull(it)?.toRefDTO()
    }
    val datasetFetched = datasets?.mapNotNull {
        datasetF2FinderService.getById(it).item
    }
    return CatalogueDTOBase(
        id = id,
        identifier = identifier,
        status = status,
        title = title,
        description = description,
        catalogues = cataloguesFetched,
        themes = themes,
        type = type,
        structure = structure,
        homepage = homepage,
        img = img,
        datasets = datasetFetched,
    )
}

fun CatalogueModel.toRefDTO(): CatalogueRefDTOBase {
    return CatalogueRefDTOBase(
        id = id,
        identifier = identifier,
        status = status,
        title = title,
        description = description,
        themes = themes,
        type = type,
        structure = structure,
        homepage = homepage,
        img = img
    )
}
fun CatalogueModel.toSimpleRefDTO(): CatalogueRefDTOBase {
    return CatalogueRefDTOBase(
        id = id,
        identifier = identifier,
        title = title,
        type = type,
    )
}

fun CatalogueCreateCommandDTOBase.toCommand() = CatalogueCreateCommand(
    identifier = identifier,
    title = title,
    description = description,
    catalogues = catalogues,
    themes = themes,
    type = type,
    structure = structure,
    homepage = homepage,
)

fun CatalogueCreatedEvent.toEvent() = CatalogueCreatedEventDTOBase(
    id = id,
    identifier = identifier,
    title = title,
    description = description,
    catalogues = catalogues,
    themes = themes,
    type = type,
    structure = structure,
    homepage = homepage,
)

fun CatalogueLinkCataloguesCommandDTOBase.toCommand() = CatalogueLinkCataloguesCommand(
    id = id,
    catalogues = catalogues
)

fun CatalogueLinkedCataloguesEvent.toEvent() = CatalogueLinkedCataloguesEventDTOBase(
    id = id,
    catalogues = catalogues
)

fun CatalogueLinkDatasetsCommandDTOBase.toCommand() = CatalogueLinkDatasetsCommand(
    id = id,
    datasets = datasets
)

fun CatalogueLinkedDatasetsEvent.toEvent() = CatalogueLinkedDatasetsEventDTOBase(
    id = id,
    datasets = datasets
)

fun CatalogueLinkThemesCommandDTOBase.toCommand() = CatalogueLinkThemesCommand(
    id = id,
    themes = themes
)

fun CatalogueLinkedThemesEvent.toEvent() = CatalogueLinkedThemesEventDTOBase(
    id = id,
    themes = themes
)

fun CatalogueDeleteCommandDTOBase.toCommand() = CatalogueDeleteCommand(
    id = id
)

fun CatalogueDeletedEvent.toEvent() = CatalogueDeletedEventDTOBase(
    id = id
)
