import city.smartb.registry.program.cccev.AppAuth
import city.smartb.registry.program.cccev.createAssetPool
import city.smartb.registry.program.cccev.createRandomProject
import city.smartb.registry.program.cccev.initIndicatorsCarbon
import city.smartb.registry.program.cccev.initRequirement
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
//    Prod
    val urlAuth ="https://auth.connect.smartb.network/realms/sb"
//    val client = System.getenv("PROD_TRACE_CLIENT_ID")!!
//    val secret = System.getenv("PROD_TRACE_CLIENT_SECRET")!!
    val urlVer = "https://trace.smartb.network/ver"
//    val urlCCCEV = "https://trace.smartb.network/cccev"

//    PreProd
//    val urlAuth = "https://auth.connect.smart-b.io/realms/sb-dev"
//    val clientSb = "tr-smart-b-app"
//    val secretSb = "***REMOVED***"
//    val urlCCCEV = "https://trace.smart-b.io/cccev"
//    val urlVer = "https://trace.smart-b.io/ver"

//    Local
//    val urlAuth = "https://auth.connect.smart-b.io/realms/sb-dev"
//    val urlCCCEV = "http://localhost:8083"
//    val urlVer = "http://localhost:8070"
//    val clientSb = "sb-plateform-app"
//    val secretSb = "secretsecret"

//    val nameIssuer = "Rockfeller"
//    val clientIssuer = "sb-rockfeller-app"
//    val secretIssuer = "***REMOVED***"

//    val nameIssuer = "Smartb"
//    val clientIssuer = "tr-smart-b-app"
//    val secretIssuer = "***REMOVED***"

//    val nameOffseter = "Phease"
//    val clientOffseter = "sb-phease-app"
//    val secretOffseter = "***REMOVED***"

//    val nameOffseter = "CleanChain"
//    val clientOffseter = "sb-cleanchain-app"
//    val secretOffseter = "***REMOVED***"

    val nameIssuer = "Smartb"
    val clientIssuer = "tr-smartb-asset-pool-app"
    val secretIssuer = "***REMOVED***"

    val nameOffseter = "CleanChain"
    val clientOffseter = "tr-cleanchain-asset-app"
    val secretOffseter = "***REMOVED***"


//    tr-smartb-asset-pool-app
//    ***REMOVED***

//    tr-cleanchain-asset-app
//    ***REMOVED***

    val accessTokenIssuer = AppAuth.getActor(urlAuth, nameIssuer, clientIssuer, secretIssuer)
    val accessTokenOffseter = AppAuth.getActor(urlAuth, nameOffseter, clientOffseter, secretOffseter)

//    initRequirement(urlCCCEV)
//    initIndicatorsCarbon(urlCCCEV)
//    createRandomProject(urlVer, accessTokenSb)

    val assetPoolId = createAssetPool(
        urlVer,
        issuer = accessTokenIssuer,
        offsetter = accessTokenOffseter
    )
//    createRandomProject(urlVer, accessTokenSb)
//    createBrazilRockFeller(urlVer, accessTokenSb, accessTokenRockfeller, accessTokenOffseter)
}
