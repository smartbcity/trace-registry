package city.smartb.registry.program.infra.pdf

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import ssm.chaincode.dsl.blockchain.TransactionId
import java.text.SimpleDateFormat
import java.util.Date


object CertificateGenerator {
    const val TEMPLATE_CERTIFICATE = "classpath:certificate_pending.html"
    const val TEMPLATE_CERTIFICATE_PENDING = "classpath:certificate_pending.html"


    private const val FIELD_ISSUEDTO = "(entreprise)"
    private const val FIELD_DATE = "(date)"
    private const val FIELD_CERTIFIED_BY = "(certifiedBy)"
    private const val FIELD_TRANSACTION = "(adresse)"
    private const val FIELD_PROJECT = "(projet)"
    private const val FIELD_CARBON = "(nombre)"
    private const val FIELD_CARBON_INDICATOR = "(indicateur)"
    private const val FIELD_CARBON_INDICATOR_VERB = "(indicateur_verb)"

    fun fillPendingCertificate(
        transactionId: TransactionId,
        date: Long,
        issuedTo: String,
        quantity: BigDecimal,
        indicator: String,
    ): ByteArray {
        val template = TEMPLATE_CERTIFICATE_PENDING
        return fill(TEMPLATE_CERTIFICATE, transactionId, date, issuedTo, quantity, indicator)
    }
    private fun fill(
        template: String,
        transactionId: TransactionId,
        date: Long,
        issuedTo: String,
        quantity: BigDecimal,
        indicator: String,
        certifiedBy: String? = null,
        project: String? = null
    ): ByteArray {
        val template = TEMPLATE_CERTIFICATE

        val verb = if (quantity > BigDecimal.ONE) "will be" else "will be"
        val templateFilled =  PathMatchingResourcePatternResolver().getResource(template)
            .inputStream
            .readAllBytes()
            .decodeToString()
            .replace(FIELD_ISSUEDTO, issuedTo)
            .replace(FIELD_DATE, SimpleDateFormat("MMMMMMMMMMM dd, yyyy").format(Date(date)))
            .replace(FIELD_TRANSACTION, transactionId)
            .replace(FIELD_CARBON, quantity.toPlainString())
            .replace(FIELD_CARBON_INDICATOR, indicator)
            .replace(FIELD_CARBON_INDICATOR_VERB, verb)

        certifiedBy?.let {
            templateFilled.replace(FIELD_CERTIFIED_BY, certifiedBy)
        }

        project?.let {
            templateFilled.replace(FIELD_PROJECT, project)
        }

        return templateFilled.let(HtmlToPdfConverter::htmlToPdfB64)
    }
}
