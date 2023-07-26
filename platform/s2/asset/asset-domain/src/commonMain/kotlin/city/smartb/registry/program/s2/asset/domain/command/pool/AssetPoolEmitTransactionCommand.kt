package city.smartb.registry.program.s2.asset.domain.command.pool

import city.smartb.registry.program.api.commons.model.BigDecimalAsString
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolCommand
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolEvent
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolId
import city.smartb.registry.program.s2.asset.domain.automate.AssetTransactionId
import city.smartb.registry.program.s2.asset.domain.model.AssetTransactionType
import kotlinx.serialization.Serializable

typealias OrderId = String
data class AssetPoolEmitTransactionCommand(
    override val id: AssetPoolId,
    val orderId: OrderId,
    val from: String?,
    val to: String?,
    val by: String,
    val quantity: BigDecimalAsString,
    val type: AssetTransactionType
): AssetPoolCommand

@Serializable
data class AssetPoolEmittedTransactionEvent(
    override val id: AssetPoolId,
    override val date: Long,
    val transactionId: AssetTransactionId
): AssetPoolEvent
