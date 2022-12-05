package city.smartb.registry.program.s2.protocol.domain

import city.smartb.im.organization.domain.model.OrganizationId
import city.smartb.registry.program.api.commons.model.Match
import f2.dsl.cqrs.page.OffsetPagination
import f2.dsl.cqrs.page.PageDTO
import city.smartb.registry.program.s2.protocol.domain.automate.ProtocolId
import city.smartb.registry.program.s2.protocol.domain.model.Protocol
import i2.keycloak.f2.user.domain.model.UserId

interface ProtocolFinder {
    suspend fun getOrNull(id: ProtocolId): Protocol?
    suspend fun get(id: ProtocolId): Protocol
    suspend fun page(
        id: Match<ProtocolId>? = null,
        offset: OffsetPagination? = null
    ): PageDTO<Protocol>
}
