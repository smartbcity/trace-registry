package city.smartb.registry.program.s2.order.domain.command

import city.smartb.registry.program.s2.order.domain.OrderCommand
import city.smartb.registry.program.s2.order.domain.OrderEvent
import city.smartb.registry.program.s2.order.domain.OrderId
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * @d2 command
 */
@JsExport
interface OrderCancelCommandDTO: OrderCommand {
    /**
     * Id of the order to cancel.
     */
    override val id: OrderId
}

/**
 * @d2 inherit
 */
data class OrderCancelCommand(
    override val id: OrderId
): OrderCommand

@Serializable
data class OrderCanceledEvent(
    override val id: OrderId,
    override val date: Long
): OrderEvent
