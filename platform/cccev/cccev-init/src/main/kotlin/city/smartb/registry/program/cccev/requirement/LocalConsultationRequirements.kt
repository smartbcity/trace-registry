package city.smartb.registry.program.cccev.requirement

import  cccev.dsl.model.builder.InformationRequirementBuilder
import cccev.dsl.model.builder.RequirementsLinkedBuilder
import cccev.dsl.model.informationRequirement
import city.smartb.registry.program.cccev.ver.Activities
import city.smartb.registry.program.cccev.ver.Methodology
import city.smartb.registry.program.cccev.ver.ReferenceFramework
import city.smartb.registry.program.cccev.ver.Type

fun RequirementsLinkedBuilder.localConsultationProtocolPreparation(init: InformationRequirementBuilder.() -> Unit) =
    +InformationRequirementBuilder().apply {
        isRequirementOf {
            +Activities.ProtocolPreparation
            +Methodology.VM0011
        }
        isDerivedFrom {
            +ReferenceFramework.REDDPlus
        }
        type = Type.Steps
    }.apply(init).build()
fun RequirementsLinkedBuilder.localConsultationProtocolValidation(init: InformationRequirementBuilder.() -> Unit) =
    +InformationRequirementBuilder().apply {
        isRequirementOf {
            +Activities.ProtocolValidation
            +Methodology.VM0011
        }
        isDerivedFrom {
            +ReferenceFramework.REDDPlus
        }
        type = Type.Steps
    }.apply(init).build()


val LocalConsultation = informationRequirement {
    identifier = "D10X"
    name = "Local consultation"
    description = """
            Local consultation is a process that involves engaging and consulting with local communities 
            and stakeholders in decision-making processes related to REDD+ initiatives. 
            The purpose of local consultation is to ensure that the voices, needs, and interests of local communities 
            and stakeholders are taken into account in the design and implementation of REDD+ projects.
            """
    isRequirementOf {
        +Activities.ProtocolPreparation
        +Methodology.VM0011
    }
    isDerivedFrom {
        +ReferenceFramework.REDDPlus
    }
    hasRequirement {
        localConsultationProtocolPreparation {
            identifier = "D100"
            name = "Conservation concession contract copy"
            description =
                "Obtain a copy of the conservation concession contract to ensure compliance with legal and regulatory requirements."
        }
        localConsultationProtocolPreparation {
            identifier = "D101"
            name = "Terms of reference"
            description =
                "Develop terms of reference to guide the project's implementation and ensure alignment with project goals and objectives."
        }
        localConsultationProtocolPreparation {
            identifier = "D102"
            name = "Local community component presentation"
            description =
                "Present the local community component of the project to ensure that community interests and needs are incorporated into the project design and implementation."
        }
        localConsultationProtocolPreparation {
            identifier = "D103"
            name = "Community delegate minutes"
            description =
                "Obtain minutes from community delegate meetings to ensure that community interests and needs are being represented and addressed in the project."
        }
        localConsultationProtocolPreparation {
            identifier = "D104"
            name = "Concession community projects list"
            description =
                "Obtain a list of community projects within the concession to ensure that the project is aligned with local development priorities and needs."
        }
        localConsultationProtocolPreparation {
            identifier = "D105"
            name = "Forest concession local development fund budget"
            description =
                "Obtain the budget for the forest concession local development fund to ensure that project resources are being allocated effectively."
        }
        localConsultationProtocolPreparation {
            identifier = "D106"
            name = "Amendment negotiation meeting minutes"
            description =
                "Obtain minutes from the meeting to negotiate any amendments to the project protocol to ensure compliance with legal and regulatory requirements."
        }
        localConsultationProtocolPreparation {
            identifier = "D107"
            name = "Local development fund management modalities guide"
            description =
                "Develop a guide for the management of the local development fund to ensure that resources are being allocated effectively and transparently."
        }
        localConsultationProtocolPreparation {
            identifier = "D108"
            name = "Community local governance members installation meeting minutes"
            description =
                "Obtain minutes from the meeting to install members of the community local governance to ensure that community interests and needs are being represented and addressed in the project."
        }
        localConsultationProtocolPreparation {
            identifier = "D109"
            name = "Community local governance internal regulation model"
            description =
                "Develop a model for the internal regulation of the community local governance to ensure effective governance and decision-making."
        }
        localConsultationProtocolPreparation {
            identifier = "D110"
            name = "Collaboration meeting minutes by ethnic group"
            description =
                "Obtain minutes from collaboration meetings with ethnic groups to ensure that the project is inclusive and respectful of cultural diversity."
        }
        localConsultationProtocolPreparation {
            identifier = "D111"
            name = "Investment location coordinates and area shapefile"
            description =
                "Obtain the location coordinates and area shapefile for the REDD+ investment to ensure accurate mapping and spatial analysis."
        }

        localConsultationProtocolPreparation {
            identifier = "D112"
            name = "CLIP"
            description =
                "Develop a Community Land Information Platform (CLIP) to ensure effective land use planning and management within the concession."
        }
    }
}

val LocalConsultationRequirements = buildList {
    add(LocalConsultation)
}