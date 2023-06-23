package city.smartb.registry.program.s2.asset.domain.model

import city.smartb.fs.s2.file.domain.model.FilePath
import city.smartb.registry.program.api.commons.model.BigDecimalAsString
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolId
import city.smartb.registry.program.s2.asset.domain.automate.TransactionId
import city.smartb.registry.program.s2.asset.domain.automate.TransactionState

data class Transaction(
    val id: TransactionId,
    val orderId: String,
    val poolId: AssetPoolId,
    val from: String?,
    val to: String?,
    val by: String,
    val quantity: BigDecimalAsString,
    val type: TransactionType,
    val date: Long,
    val file: FilePath?,
    val state: TransactionState
)
