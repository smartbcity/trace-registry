package city.smartb.registry.script.init

import city.smartb.registry.script.init.actor.ActorAuth
import city.smartb.registry.script.init.actor.ActorBuilder
import city.smartb.registry.script.init.actor.ActorType
import city.smartb.registry.script.init.asset.createAssetPool
import city.smartb.registry.script.init.project.addAssetPoolToProject
import city.smartb.registry.script.init.project.createRandomProject

class InitScript(
    private val properties: RegistryScriptInitProperties
) {
    suspend fun run() {
        val accessTokenOrchestrator= ActorAuth.getActor(
            properties.auth.url,
            properties.orchestrator.name,
            properties.orchestrator.clientId,
            properties.orchestrator.clientSecret
        )

        val actorFactory = ActorBuilder(properties.im.url,  properties.auth.url, accessTokenOrchestrator)
        val projectManager = actorFactory.create(ActorType.PROJECT_MANAGER)
        val offseter = actorFactory.create(ActorType.OFFSETTER)
        val issuer = actorFactory.create(ActorType.ISSUER)

        initRequirement(properties.cccev.url)
        initIndicatorsCarbon(properties.cccev.url)

        val assetPoolId = createAssetPool(
            properties.registry.url,
            orchestrator = accessTokenOrchestrator,
            projectManager = projectManager,
            issuer = issuer,
            offsetter = offseter
        )
        val projectId = createRandomProject(properties.registry.url, accessTokenOrchestrator, assetPoolId, countRange = 0..properties.nbProject).first()
        addAssetPoolToProject(properties.registry.url, accessTokenOrchestrator, projectId, assetPoolId)
    }
}