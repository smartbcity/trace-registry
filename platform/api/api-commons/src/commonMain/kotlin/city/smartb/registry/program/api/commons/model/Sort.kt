package city.smartb.registry.program.api.commons.model

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SortDTO")
interface SortDTO {
    val property: String
    val ascending: Boolean
    val nullsFirst: Boolean?
}

data class SortDTOBase(
    override val property: String,
    override val ascending: Boolean,
    override val nullsFirst: Boolean
): SortDTO

data class Sort<P: Enum<*>>(
    val property: P,
    val ascending: Boolean,
    val nullsFirst: Boolean
)

inline fun <reified E : Enum<E>> SortDTOBase.toSort() = Sort(
    property = enumValueOf<E>(property),
    ascending = ascending,
    nullsFirst = nullsFirst
)
