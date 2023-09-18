package city.smartb.registry.script.init.cccev.ver

import  cccev.dsl.model.informationRequirement
import city.smartb.registry.f2.activity.domain.model.RequirementType

object Methodology {
    val VM0011 = informationRequirement {
        identifier = "VM0011"
        name = "VM0011"
        type = RequirementType.Activity
        isDerivedFrom {
            +ReferenceFramework.Verra
            +ReferenceFramework.REDDPlus
        }
    }
}
