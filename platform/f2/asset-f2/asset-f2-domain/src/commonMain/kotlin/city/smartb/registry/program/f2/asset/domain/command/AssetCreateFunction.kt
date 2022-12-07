package city.smartb.registry.program.f2.asset.domain.command

import city.smartb.registry.program.s2.asset.domain.command.AssetUpdateCommand
import city.smartb.registry.program.s2.asset.domain.command.AssetUpdatedEvent
import f2.dsl.fnc.F2Function

/**
 * Create Asset
 * @d2 function
 * @parent [city.smartb.registry.program.s2.asset.domain.D2AssetSectionApi]
 * @child [city.smartb.registry.program.s2.asset.domain.command.AssetUpdateCommand]
 * @child [city.smartb.registry.program.s2.asset.domain.command.AssetUpdatedEventDTO]
 */
typealias AssetCreateFunction = F2Function<AssetUpdateCommand, AssetUpdatedEvent>
