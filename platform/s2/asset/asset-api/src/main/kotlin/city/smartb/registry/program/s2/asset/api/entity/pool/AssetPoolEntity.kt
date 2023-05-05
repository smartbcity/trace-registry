package city.smartb.registry.program.s2.asset.api.entity.pool

import cccev.s2.concept.domain.InformationConceptIdentifier
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolId
import city.smartb.registry.program.s2.asset.domain.automate.AssetPoolState
import com.redis.om.spring.annotations.Document
import com.redis.om.spring.annotations.Searchable
import org.springframework.data.annotation.Id
import s2.dsl.automate.model.WithS2Id
import s2.dsl.automate.model.WithS2State

@Document
class AssetPoolEntity: WithS2Id<AssetPoolId>, WithS2State<AssetPoolState> {
    @Id
    lateinit var id: AssetPoolId

    @Searchable(nostem=true)
    lateinit var status: AssetPoolState

    var creationDate: Long = 0

    @Searchable(nostem=true)
    lateinit var vintage: String

    lateinit var indicator: InformationConceptIdentifier

    var granularity: Double = 1.0

    var wallets: MutableMap<String, Double> = mutableMapOf()

    override fun s2Id() = id
    override fun s2State() = status
}