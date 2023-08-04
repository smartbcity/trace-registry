import city.smartb.registry.program.cccev.AppAuth
import city.smartb.registry.program.cccev.createAssetPool
import city.smartb.registry.program.cccev.createRandomProject
import city.smartb.registry.program.cccev.initIndicatorsCarbon
import city.smartb.registry.program.cccev.initRequirement
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
//    Local
    val urlAuth = "https://auth.dev.connect.smart-b.io/realms/sb-dev"
    val urlCCCEV = "http://localhost:8083"
    val urlVer = "http://localhost:8070"

    val nameProjectManager = "Smartb"
    val clientProjectManager = "tr-smartb-ver2-app"
    val secretProjectManager = "***REMOVED***"

    val nameIssuer = "Smartb"
    val clientIssuer = "tr-smartb-ver2-app"
    val secretIssuer = "***REMOVED***"

    val nameOffseter = "CleanChain"
    val clientOffseter = "tr-smartb-ver2-app"
    val secretOffseter = "***REMOVED***"


    val accessTokenProjectManager = AppAuth.getActor(urlAuth, nameProjectManager, clientProjectManager, secretProjectManager)
    val accessTokenIssuer = AppAuth.getActor(urlAuth, nameIssuer, clientIssuer, secretIssuer)
    val accessTokenOffseter = AppAuth.getActor(urlAuth, nameOffseter, clientOffseter, secretOffseter)

//    initRequirement(urlCCCEV)
//    initIndicatorsCarbon(urlCCCEV)

    val assetPoolId = null
//    val assetPoolId = createAssetPool(
//        urlVer,
//        issuer = accessTokenProjectManager,
//        offsetter = accessTokenProjectManager
//    )
    createRandomProject(urlVer, accessTokenProjectManager.accessToken, assetPoolId)
}
