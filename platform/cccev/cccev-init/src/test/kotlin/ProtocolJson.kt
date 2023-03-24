import cccev.dsl.client.CCCEVClient
import cccev.dsl.model.informationRequirement
import city.smartb.registry.program.cccev.requirement.EligibilityRequirements
import city.smartb.registry.program.cccev.requirement.ImplementationRequirements
import city.smartb.registry.program.cccev.requirement.LOIRequirements
import city.smartb.registry.program.cccev.requirement.ReddPlusRequirement
import city.smartb.registry.program.cccev.requirement.VerraVcsRequirement
import com.fasterxml.jackson.annotation.JsonInclude
import kotlinx.coroutines.runBlocking
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


fun main(): Unit = runBlocking {
    val information = informationRequirement {
        name = "AxessImpact's Activities"
        description = "AxessImpact's Activities"
        hasRequirement {
            +LOIRequirements
            +EligibilityRequirements
            +ImplementationRequirements
            +ReddPlusRequirement
            +VerraVcsRequirement
        }
    }
    val mapper = jacksonObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter()

    println(mapper.writeValueAsString(information))
//    println(mapper.writeValueAsString(IdentificationOfProject))
}
