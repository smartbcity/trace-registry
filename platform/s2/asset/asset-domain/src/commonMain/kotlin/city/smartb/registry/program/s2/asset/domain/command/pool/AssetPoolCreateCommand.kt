package city.smartb.registry.program.s2.asset.domain.command.pool

import cccev.s2.concept.domain.InformationConceptIdentifier
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolEvent
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolId
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolInitCommand
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolState
import kotlinx.serialization.Serializable

data class AssetPoolCreateCommand(
    val vintage: String,
    val indicator: InformationConceptIdentifier,
    val granularity: Double
): AssetPoolInitCommand

@Serializable
data class AssetPoolCreatedEvent(
    override val id: AssetPoolId,
    override val date: Long,
    val status: AssetPoolState,
    val vintage: String,
    val indicator: InformationConceptIdentifier,
    val granularity: Double
): AssetPoolEvent
