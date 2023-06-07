package city.smartb.registry.program.f2.asset.domain.command

import city.smartb.registry.program.api.commons.model.BigDecimalAsNumber
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolId
import city.smartb.registry.program.s2.asset.domain.automate.TransactionId
import city.smartb.registry.program.s2.asset.domain.model.TransactionType
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * Issue new assets into a pool.
 * @d2 function
 * @parent [city.smartb.registry.program.f2.asset.domain.D2AssetF2Page]
 * @order 100
 */
typealias AssetIssueFunction = F2Function<AssetIssueCommandDTOBase, AssetIssuedEventDTOBase>

/**
 * @d2 command
 * @parent [AssetIssueFunction]
 */
@JsExport
interface AssetIssueCommandDTO {
    /**
     * Id of the pool to issue new assets in.
     */
    val poolId: AssetPoolId

    /**
     * New owner of the issued assets.
     * @example "SmartB"
     */
    val to: String

    /**
     * Quantity of issued asset
     * @example 100.0
     */
    val quantity: BigDecimalAsNumber
}

/**
 * @d2 inherit
 */
@Serializable
data class AssetIssueCommandDTOBase(
    override val poolId: AssetPoolId,
    override val to: String,
    override val quantity: BigDecimalAsNumber
): AssetIssueCommandDTO, AbstractAssetTransactionCommand() {
    override val from: String? = null
    override val type: TransactionType = TransactionType.ISSUED
}

/**
 * @d2 event
 * @parent [AssetIssueFunction]
 */
@JsExport
interface AssetIssuedEventDTO {
    /**
     * Id of the emitted transaction.
     */
    val transactionId: TransactionId
}

/**
 * @d2 inherit
 */
data class AssetIssuedEventDTOBase(
    override val transactionId: TransactionId
): AssetIssuedEventDTO
