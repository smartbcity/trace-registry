package city.smartb.registry.program.s2.protocol.domain

import city.smartb.registry.program.s2.protocol.domain.command.ProtocolUpdateCommand
import city.smartb.registry.program.s2.protocol.domain.command.ProtocolUpdatedEvent
import city.smartb.registry.program.s2.protocol.domain.model.Protocol

interface ProtocolAggregate {
	suspend fun create(command: ProtocolUpdateCommand): ProtocolUpdatedEvent
	suspend fun update(command: ProtocolUpdateCommand): ProtocolUpdatedEvent
}
