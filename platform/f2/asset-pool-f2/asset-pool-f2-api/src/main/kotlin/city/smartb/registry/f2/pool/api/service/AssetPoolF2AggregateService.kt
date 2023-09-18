package city.smartb.registry.f2.pool.api.service

import cccev.dsl.client.CCCEVClient
import cccev.f2.concept.domain.model.InformationConceptDTOBase
import cccev.f2.concept.domain.query.InformationConceptGetByIdentifierQueryDTOBase
import city.smartb.im.commons.auth.AuthenticationProvider
import city.smartb.registry.infra.im.ImService
import city.smartb.registry.s2.asset.api.AssetPoolAggregateService
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolCloseCommand
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolClosedEvent
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolCreateCommand
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolCreatedEvent
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolEmitTransactionCommand
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolEmittedTransactionEvent
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolHeldEvent
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolHoldCommand
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolResumeCommand
import city.smartb.registry.s2.asset.domain.command.pool.AssetPoolResumedEvent
import f2.dsl.fnc.invokeWith
import org.springframework.stereotype.Service

@Service
class AssetPoolF2AggregateService(
    private val assetPoolAggregateService: AssetPoolAggregateService,
    private val cccevClient: CCCEVClient,
    private val imService: ImService,
    private val assetPoolPoliciesEnforcer: AssetPoolPoliciesEnforcer,
) {
    suspend fun create(command: city.smartb.registry.f2.pool.domain.command.AssetPoolCreateCommandDTOBase): AssetPoolCreatedEvent {
        val indicator = findCoeIndicateur(command.indicator)
        val event = AssetPoolCreateCommand(
            indicator = indicator.identifier!!,
            vintage = command.vintage,
            granularity = command.granularity,
            metadata = emptyMap()
//            metadata = mapOf(
//                "project" to project.name,
//                "project_id" to project.id,
//                "certifiedBy" to project.vvb?.name
//            )
        ).let { assetPoolAggregateService.create(it) }


        // TODO Create endpoint ProjectAddAssetPool to do that
//        ProjectAddAssetPoolCommand(
//            id = project.id,
//            poolId = event.id
//        ).let { projectAggregateService.addAssetPool(it) }

        return event
    }
    private suspend fun findCoeIndicateur(indicator: String): InformationConceptDTOBase {
        return InformationConceptGetByIdentifierQueryDTOBase(
            identifier = indicator
        ).invokeWith(cccevClient.informationConceptClient.conceptGetByIdentifier())
            .item!!
    }

    suspend fun hold(command: city.smartb.registry.f2.pool.domain.command.AssetPoolHoldCommandDTOBase): AssetPoolHeldEvent {
        return assetPoolAggregateService.hold(AssetPoolHoldCommand(command.id))
    }

    suspend fun resume(command: city.smartb.registry.f2.pool.domain.command.AssetPoolResumeCommandDTOBase): AssetPoolResumedEvent {
        return assetPoolAggregateService.resume(AssetPoolResumeCommand(command.id))
    }

    suspend fun close(command: city.smartb.registry.f2.pool.domain.command.AssetPoolCloseCommandDTOBase): AssetPoolClosedEvent {
        return assetPoolAggregateService.close(AssetPoolCloseCommand(command.id))
    }

    suspend fun issue(command: city.smartb.registry.f2.pool.domain.command.AssetIssueCommandDTOBase): AssetPoolEmittedTransactionEvent {
        return emitTransaction(command)
    }

    suspend fun transfer(command: city.smartb.registry.f2.pool.domain.command.AssetTransferCommandDTOBase): AssetPoolEmittedTransactionEvent {
        return emitTransaction(command)
    }

    suspend fun offset(command: city.smartb.registry.f2.pool.domain.command.AssetOffsetCommandDTOBase): AssetPoolEmittedTransactionEvent {
        return emitTransaction(command, verifyTo = false)
    }

    suspend fun retire(command: city.smartb.registry.f2.pool.domain.command.AssetRetireCommandDTOBase): AssetPoolEmittedTransactionEvent {
        return emitTransaction(command)
    }

    private suspend fun emitTransaction(
        command: city.smartb.registry.f2.pool.domain.command.AbstractAssetTransactionCommand,
        verifyTo: Boolean = true
    ): AssetPoolEmittedTransactionEvent {
        val emitTransactionCommand = command.toAssetPoolEmitTransactionCommand(verifyTo)
        assetPoolPoliciesEnforcer.checkEmitTransaction(emitTransactionCommand)
        return assetPoolAggregateService.emitTransaction(emitTransactionCommand)
    }

    private suspend fun city.smartb.registry.f2.pool.domain.command.AbstractAssetTransactionCommand.toAssetPoolEmitTransactionCommand(
        verifyTo: Boolean = true
    ): AssetPoolEmitTransactionCommand {
        val to = if (verifyTo) {
            to?.let { imService.getOrganizationByName(it).id }
        } else {
            to
        }
        val memberOf = AuthenticationProvider.getAuthedUser()?.memberOf
            ?: throw IllegalStateException(
                "Authed user[${AuthenticationProvider.getAuthedUser()?.id}] must must have memberOf propoerty"
            )

        return AssetPoolEmitTransactionCommand(
            id = id,
            from = from?.let { imService.getOrganizationByName(it).id },
            to = to,
            by = memberOf,
            quantity = quantity,
            type = type,
        )
    }

}