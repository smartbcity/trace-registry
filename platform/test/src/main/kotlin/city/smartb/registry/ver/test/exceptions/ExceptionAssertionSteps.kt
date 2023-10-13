package city.smartb.registry.ver.test.exceptions

import city.smartb.registry.api.commons.exception.ExceptionCodes
import city.smartb.registry.s2.asset.api.exception.GranularityTooSmallException
import city.smartb.registry.s2.asset.api.exception.NegativeTransactionException
import city.smartb.registry.s2.asset.api.exception.NotEnoughAssetsException
import f2.spring.exception.ConflictException
import f2.spring.exception.ForbiddenAccessException
import f2.spring.exception.MessageConverterException
import f2.spring.exception.NotFoundException
import io.cucumber.java8.En
import s2.bdd.S2CucumberStepsDefinition
import s2.bdd.assertion.AssertionBdd
import s2.bdd.assertion.exceptions
import s2.bdd.data.parser.safeExtract

class ExceptionAssertionSteps: En, S2CucumberStepsDefinition()  {
    init {
        DataTableType(::exceptionAssertionParams)

        Then("An exception should be thrown:") { params: ExceptionAssertionParams ->
            step {
                AssertionBdd.exceptions(context)
                    .assertThat(params.code.toExceptionClass())
                    .hasBeenThrown(params.times)
            }
        }

        Then("No exception should be thrown:") { params: ExceptionAssertionParams ->
            step {
                AssertionBdd.exceptions(context)
                    .assertThat(params.code.toExceptionClass())
                    .hasNotBeenThrown()
            }
        }
    }

    @Suppress("MagicNumber")
    private fun Int.toExceptionClass() = when (this) {
        400 -> MessageConverterException::class
        403 -> ForbiddenAccessException::class
        404 -> NotFoundException::class
        409 -> ConflictException::class
        ExceptionCodes.negativeTransaction() -> NegativeTransactionException::class
        ExceptionCodes.notEnoughAssets() -> NotEnoughAssetsException::class
        ExceptionCodes.granularityTooSmall() -> GranularityTooSmallException::class
        else -> throw IllegalArgumentException("Unknown exception code [$this]")
    }

    private fun exceptionAssertionParams(entry: Map<String, String>) = ExceptionAssertionParams(
        code = entry.safeExtract("code").toInt(),
        times = entry["times"]?.toInt() ?: 1
    )

    private data class ExceptionAssertionParams(
        val code: Int,
        val times: Int
    )
}
