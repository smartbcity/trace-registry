package city.smartb.registry.script.init.catalogue

import city.smartb.registry.s2.catalogue.domain.model.catalogue
import city.smartb.registry.s2.catalogue.domain.model.concept
import city.smartb.registry.s2.catalogue.domain.model.dataService

fun catalogueStandards(debug: String) = catalogue {
    identifier = "standards${debug}"
    title = "Standards"
    type = "grid"
    description = """
        Explore our comprehensive list of recognized standards for environmental project evaluation and certification. 
        Discover diverse opportunities in energy, carbon, water, waste, and more. 
        Choose the standard that aligns with your goals and make a positive environmental impact.
    """.trimIndent()
    services {
        dataService {
            identifier = "standards"
            endpointURL = "https://standardsregistry.verra.org/api/standards"
        }
    }
    catalogues {
        +verraCatalogue(debug)
        +cee(debug)
        +goldStandardCatalogue(debug)
        +cdm(debug)
    }
}

fun getImg(name: String): String? {
    return CatalogueFactory::class.java.getResource("/$name")?.file.also {
        println("getImg: $name - $it")
    }

}

@Suppress("LongMethod")
fun verraCatalogue(debug: String) = catalogue {
    identifier = "verra${debug}"
    homepage = "https://verra.org/"
    title = "Verra"
    type = "standard"
    img = getImg("verra.png")
    description = """
            Verra, formerly known as Verified Carbon Standard (VCS), is a leading global standard 
            for the certification of greenhouse gas emission reduction projects.
            """.trimIndent()
    themes {
        concept {
            id = "EnergyRenewableNonRenewable"
            prefLabels = mutableMapOf(
                "en" to "Energy (renewable/non-renewable)",
                "fr" to "Energie (renouvelable/non renouvelable)"
            )
        }
        concept {
            id = "EnergyDistribution"
            prefLabels = mutableMapOf(
                "en" to "Energy distribution",
                "fr" to "Distribution d'énergie"
            )
        }
        concept {
            id = "EnergyDemand"
            prefLabels = mutableMapOf(
                "en" to "Energy demand",
                "fr" to "Demande d'énergie"
            )
        }
        concept {
            id = "ManufacturingIndustries"
            prefLabels = mutableMapOf(
                "en" to "Manufacturing industries",
                "fr" to "Industries manufacturières"
            )
        }
        concept {
            id = "ChemicalIndustry"
            prefLabels = mutableMapOf(
                "en" to "Chemical industry",
                "fr" to "Industrie chimique"
            )
        }
        concept {
            id = "Construction"
            prefLabels = mutableMapOf(
                "en" to "Construction",
                "fr" to "Construction"
            )
        }
        concept {
            id = "Transport"
            prefLabels = mutableMapOf(
                "en" to "Transport",
                "fr" to "Transport"
            )
        }
        concept {
            id = "MiningMineralProduction"
            prefLabels = mutableMapOf(
                "en" to "Mining/Mineral production",
                "fr" to "Production minière/minérale"
            )
        }
        concept {
            id = "MetalProduction"
            prefLabels = mutableMapOf(
                "en" to "Metal production",
                "fr" to "Production de métaux"
            )
        }
        concept {
            id = "FugitiveEmissionsFromFuels"
            prefLabels = mutableMapOf(
                "en" to "Fugitive emissions – from fuels (solid, oil, and gas)",
                "fr" to "Emissions fugitives - des combustibles (solides, pétrole et gaz)"
            )
        }
        concept {
            id = "FugitiveEmissionsFromIndustrialGases"
            prefLabels = mutableMapOf(
                "en" to "Fugitive emissions – from Industrial gases (halocarbons and sulphur hexafluoride)",
                "fr" to "Emissions fugitives - des gaz industriels (halocarbures et hexafluorure de soufre)"
            )
        }
        concept {
            id = "SolventsUse"
            prefLabels = mutableMapOf(
                "en" to "Solvents use",
                "fr" to "Utilisation de solvants"
            )
        }
        concept {
            id = "WasteHandlingDisposal"
            prefLabels = mutableMapOf(
                "en" to "Waste handling and disposal",
                "fr" to "Gestion et élimination des déchets"
            )
        }
        concept {
            id = "AgricultureForestryAndOtherLandUse"
            prefLabels = mutableMapOf(
                "en" to "Agriculture forestry and other land use (AFOLU)",
                "fr" to "Agriculture foresterie et autres utilisations des terres (AFOLU)"
            )
        }
        concept {
            id = "LivestockManureManagement"
            prefLabels = mutableMapOf(
                "en" to "Livestock and manure management",
                "fr" to "Gestion du bétail et du fumier"
            )
        }
        concept {
            id = "CarbonCaptureStorage"
            prefLabels = mutableMapOf(
                "en" to "Carbon capture and storage",
                "fr" to "Capture et stockage du carbone"
            )
        }
    }
    catalogues {
        +VerraProgram.verraVerifiedCarbonStandard(debug)
        +VerraProgram.plasticWasteReductionProgram(debug)
        +VerraProgram.climateCommunityBiodiversityStandards(debug)
        +VerraProgram.sustainableDevelopmentVerifiedImpactStandard(debug)
        +VerraProgram.californiaOffsetProject(debug)
    }
    datasets {
        dataset {
            identifier = "documents"
            title = "documents"
            type = "document"
        }
        dataset {
            identifier = "projects"
            title = "documents"
            type = "projects"
        }
    }
}
@Suppress("LargeClass")
object VerraProgram {
    @Suppress("LongMethod")
    fun verraVerifiedCarbonStandard(debug: String) = catalogue {
        identifier = "verra-verifiedcarbonctandard${debug}"
        title = "Verified Carbon Standard"
        type = "program"
        img = getImg("verifiedcarbonctandard.png")
        description = """
            The Verified Carbon Standard Program creates tradable carbon credits 
            from certified projects that reduce or remove greenhouse gas emissions.
            """.trimIndent()

        catalogues {
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0001${debug}"
                title = "VM0001"
                type = "methodology"
                description = """
            Infrared Automatic Refrigerant Leak Detection Efficiency Project Methodology, v1.1
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0003${debug}"
                title = "VM0003"
                type = "methodology"
                description = """
            Methodology for Improved Forest Management through Extension of Rotation Age, v1.3
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0004${debug}"
                title = "VM0004"
                type = "methodology"
                description = """
            Methodology for Conservation Projects that Avoid Planned Land-use Conversion in Peat Swamp Forests, v2.0
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0005${debug}"
                title = "VM0005"
                type = "methodology"
                description = """
            Methodology for Conversion of Low-Productive Forest to High-Productive Forest, v1.2
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0006${debug}"
                title = "VM0006"
                type = "methodology"
                description = """
            Methodology for Carbon Accounting for Mosaic and Landscape-scale REDD Projects, v2.2
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0007${debug}"
                title = "VM0007"
                type = "methodology"
                description = """
            REDD+ Methodology Framework (REDD-MF), v1.6
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0008${debug}"
                title = "VM0008"
                type = "methodology"
                description = """
            Weatherization of Single-Family and Multi-Family Buildings, v1.1
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0009${debug}"
                title = "VM0009"
                type = "methodology"
                description = """
            Methodology for Avoided Ecosystem Conversion, v3.0
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0010${debug}"
                title = "VM0010"
                type = "methodology"
                description = """
            Methodology for Improved Forest Management: Conversion from Logged to Protected Forest, v1.3
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0011"
                title = "VM0011"
                type = "methodology"
                description = "Methodology for Calculating GHG Benefits from Preventing Planned Degradation, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0012"
                title = "VM0012"
                type = "methodology"
                description = "Improved Forest Management in Temperate and Boreal Forests (LtPF), v1.2"
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0014"
                title = "VM0014"
                type = "methodology"
                description = "Interception and Destruction of Fugitive Methane from Coal Bed Methane (CBM) Seeps, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0015"
                title = "VM0015"
                type = "methodology"
                description = "Methodology for Avoided Unplanned Deforestation, v1.1"
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0016"
                title = "VM0016"
                type = "methodology"
                description = "Recovery and Destruction of Ozone-Depleting Substances (ODS) from Products, v1.1"
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0018"
                title = "VM0018"
                type = "methodology"
                description = "Energy Efficiency and Solid Waste Diversion Activities within a Sustainable Community, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0019"
                title = "VM0019"
                type = "methodology"
                description = "Fuel Switch from Gasoline to Ethanol in Flex-Fuel Vehicle Fleets, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0022"
                title = "VM0022"
                type = "methodology"
                description = "Quantifying N2O Emissions Reductions in Agricultural Crops through Nitrogen Fertilizer Rate Reduction, v1.1"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0025"
                title = "VM0025"
                type = "methodology"
                description = "Campus Clean Energy and Energy Efficiency, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0026"
                title = "VM0026"
                type = "methodology"
                description = "Methodology for Sustainable Grassland Management (SGM), v1.1"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0032"
                title = "VM0032"
                type = "methodology"
                description = "Methodology for the Adoption of Sustainable Grasslands through Adjustment of Fire and Grazing, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0033"
                title = "VM0033"
                type = "methodology"
                description = "Methodology for Tidal Wetland and Seagrass Restoration, v2.1"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0034"
                title = "VM0034"
                type = "methodology"
                description = "Canadian Forest Carbon Offset Methodology, v2.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0035"
                title = "VM0035"
                type = "methodology"
                description = "Methodology for Improved Forest Management through Reduced Impact Logging v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0036"
                title = "VM0036"
                type = "methodology"
                description = "Methodology for Rewetting Drained Temperate Peatlands, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0037"
                title = "VM0037"
                type = "methodology"
                description = "Methodology for Implementation of REDD+ Activities in Landscapes Affected by Mosaic Deforestation and Degradation, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0038"
                title = "VM0038"
                type = "methodology"
                description = "Methodology for Electric Vehicle Charging Systems, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VM0039"
                title = "VM0039"
                type = "methodology"
                description = "Methodology for Use of Foam Stabilized Base and Emulsion Asphalt Mixtures in Pavement Application, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0041"
                title = "VM0041"
                type = "methodology"
                description = """
            Methodology for the Reduction of Enteric Methane Emissions from Ruminants through the Use of Feed Ingredients, v2.0
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0042"
                title = "VM0042"
                type = "methodology"
                description = """
            Methodology for Improved Agricultural Land Management, v2.0
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0043"
                title = "VM0043"
                type = "methodology"
                description = """
            Methodology for CO2 Utilization in Concrete Production, v1.0
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0044"
                title = "VM0044"
                type = "methodology"
                description = """
            Methodology for Biochar Utilization in Soil and Non-Soil Applications, v1.1
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0045"
                title = "VM0045"
                type = "methodology"
                description = """
            Improved Forest Management Methodology Using Dynamic Matched Baselines from National Forest Inventories, v1.0
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0046"
                title = "VM0046"
                type = "methodology"
                description = """
            Methodology for Reducing Food Loss and Waste, v1.0
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonstandard-VM0047"
                title = "VM0047"
                type = "methodology"
                description = """
            Afforestation, Reforestation, and Revegetation, v1.0
        """.trimIndent()
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VMR0001${debug}"
                title = "VMR0001"
                type = "methodology"
                description = "Revisions to ACM0008 to Include Pre-drainage of Methane from an Active Open Cast Mine as a Methane Emission Reduction Activity, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VMR0002${debug}"
                title = "VMR0002"
                type = "methodology"
                description = "Revisions to ACM0008 to Include Methane Capture and Destruction from Abandoned Coal Mines, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VMR0003${debug}"
                title = "VMR0003"
                type = "methodology"
                description = "Revisions to AMS-III.Y to Include Use of Organic Bedding Material, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VMR0004${debug}"
                title = "VMR0004"
                type = "methodology"
                description = "Revisions to AMS-III.BC to Include Mobile Machinery, v1.0"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VMR0006${debug}"
                title = "VMR0006"
                type = "methodology"
                description = "Energy Efficiency and Fuel Switch Measures in Thermal Applications, v1.2"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VMR0007${debug}"
                title = "VMR0007"
                type = "methodology"
                description = "Revision to AMS-III.AJ.: Recovery and Recycling of Materials from Solid Wastes"
            }
            catalogue {
                identifier = "verra-verifiedcarbonctandard-VMR0008${debug}"
                title = "VMR0008"
                type = "methodology"
                description = "Revision to AMS-III.BA.: Recovery and Recycling of Materials from E-waste"
            }
        }
        themes {
            concept {
                id = "EnergyRenewableNonRenewable"
                prefLabels = mutableMapOf(
                    "en" to "Energy (renewable/non-renewable)",
                    "fr" to "Energie (renouvelable/non renouvelable)"
                )
            }
            concept {
                id = "EnergyDistribution"
                prefLabels = mutableMapOf(
                    "en" to "Energy distribution",
                    "fr" to "Distribution d'énergie"
                )
            }
            concept {
                id = "EnergyDemand"
                prefLabels = mutableMapOf(
                    "en" to "Energy demand",
                    "fr" to "Demande d'énergie"
                )
            }
            concept {
                id = "ManufacturingIndustries"
                prefLabels = mutableMapOf(
                    "en" to "Manufacturing industries",
                    "fr" to "Industries manufacturières"
                )
            }
            concept {
                id = "ChemicalIndustry"
                prefLabels = mutableMapOf(
                    "en" to "Chemical industry",
                    "fr" to "Industrie chimique"
                )
            }
            concept {
                id = "Construction"
                prefLabels = mutableMapOf(
                    "en" to "Construction",
                    "fr" to "Construction"
                )
            }
            concept {
                id = "Transport"
                prefLabels = mutableMapOf(
                    "en" to "Transport",
                    "fr" to "Transport"
                )
            }
            concept {
                id = "MiningMineralProduction"
                prefLabels = mutableMapOf(
                    "en" to "Mining/Mineral production",
                    "fr" to "Production minière/minérale"
                )
            }
            concept {
                id = "MetalProduction"
                prefLabels = mutableMapOf(
                    "en" to "Metal production",
                    "fr" to "Production de métaux"
                )
            }
            concept {
                id = "FugitiveEmissionsFromFuels"
                prefLabels = mutableMapOf(
                    "en" to "Fugitive emissions – from fuels (solid, oil, and gas)",
                    "fr" to "Emissions fugitives - des combustibles (solides, pétrole et gaz)"
                )
            }
            concept {
                id = "FugitiveEmissionsFromIndustrialGases"
                prefLabels = mutableMapOf(
                    "en" to "Fugitive emissions – from Industrial gases (halocarbons and sulphur hexafluoride)",
                    "fr" to "Emissions fugitives - des gaz industriels (halocarbures et hexafluorure de soufre)"
                )
            }
            concept {
                id = "SolventsUse"
                prefLabels = mutableMapOf(
                    "en" to "Solvents use",
                    "fr" to "Utilisation de solvants"
                )
            }
            concept {
                id = "WasteHandlingDisposal"
                prefLabels = mutableMapOf(
                    "en" to "Waste handling and disposal",
                    "fr" to "Gestion et élimination des déchets"
                )
            }
            concept {
                id = "AgricultureForestryAndOtherLandUse"
                prefLabels = mutableMapOf(
                    "en" to "Agriculture, forestry and other land use (AFOLU)",
                    "fr" to "Agriculture, foresterie et autres utilisations des terres (AFOLU)"
                )
            }
            concept {
                id = "LivestockManureManagement"
                prefLabels = mutableMapOf(
                    "en" to "Livestock and manure management",
                    "fr" to "Gestion du bétail et du fumier"
                )
            }
            concept {
                id = "CarbonCaptureStorage"
                prefLabels = mutableMapOf(
                    "en" to "Carbon capture and storage",
                    "fr" to "Capture et stockage du carbone"
                )
            }
        }
        datasets {
            dataset {
                identifier = "documents"
                title = "documents"
                type = "document"
            }
            dataset {
                identifier = "projects"
                title = "Projects"
                type = "projects"
            }
        }
    }

    fun plasticWasteReductionProgram(debug: String) = catalogue {
        identifier = "verra-plasticWasteReductionProgram${debug}"
        title = "The Plastic Waste Reduction Program"
        type = "program"
        img = getImg("plasticWasteReductionProgram.png")
        description = """
           The Plastic Waste Reduction Program finances and expands recycling and collection to divert plastic waste 
           from the environment and support the circular economy.
            """.trimIndent()
        themes {

            concept {
                id = "PlasticWasteCollection"
                prefLabels = mutableMapOf(
                    "en" to "Plastic Waste Collection",
                    "fr" to "Collecte des déchets plastiques"
                )
            }
            concept {
                id = "PlasticWasteRecycling"
                prefLabels = mutableMapOf(
                    "en" to "Plastic Waste Recycling",
                    "fr" to "Recyclage des déchets plastiques"
                )
            }
        }
        datasets {
            dataset {
                identifier = "documents"
                title = "documents"
                type = "document"
            }
            dataset {
                identifier = "projects"
                title = "Projects"
                type = "projects"
            }
        }
    }

    @Suppress("LongMethod")
    fun climateCommunityBiodiversityStandards(debug: String) = catalogue {
        identifier = "verra-climateCommunityBiodiversityStandards${debug}"
        title = "Climate, Community & Biodiversity Standards"
        type = "program"
        img = getImg("climateCommunityBiodiversityStandards.png")
        description = """
          The Climate, Community & Biodiversity Program certifies land management projects that deliver net positive 
          impacts for climate, communities and biodiversity.
            """.trimIndent()
        themes {
            concept {
                id = " AfforestationReforestationRevegetation "
                prefLabels = mutableMapOf(
                    "en" to "Afforestation, Reforestation and Revegetation",
                    "fr" to "Afforestation, reboisement et revégétalisation"
                )
            }
            concept {
                id = "AgriculturalLandManagement"
                prefLabels = mutableMapOf(
                    "en" to "Agricultural Land Management",
                    "fr" to "Gestion des terres agricoles"
                )
            }
            concept {
                id = "AvoidedConversionOfGrasslandsAndShrublands"
                prefLabels = mutableMapOf(
                    "en" to "Avoided Conversion of Grasslands and Shrublands",
                    "fr" to "Eviter la conversion des prairies et des broussailles"
                )
            }
            concept {
                id = "ManufacturingIndustries"
                prefLabels = mutableMapOf(
                    "en" to "Manufacturing industries",
                    "fr" to "Industries manufacturières"
                )
            }
            concept {
                id = "ImprovedForestManagement"
                prefLabels = mutableMapOf(
                    "en" to "Improved Forest Management",
                    "fr" to "Gestion forestière améliorée"
                )
            }
            concept {
                id = "ReducedEmissionsFromDeforestationDegradation"
                prefLabels = mutableMapOf(
                    "en" to "Reduced Emissions from Deforestation and Degradation",
                    "fr" to "Réduction des émissions dues à la déforestation et à la dégradation"
                )
            }
            concept {
                id = "Wetland Rewetting and Conservation"
                prefLabels = mutableMapOf(
                    "en" to "Wetland Rewetting and Conservation",
                    "fr" to "Réhumidification et conservation des zones humides"
                )
            }
        }
        datasets {
            dataset {
                identifier = "documents"
                title = "documents"
                type = "document"
            }
            dataset {
                identifier = "projects"
                title = "Projects"
                type = "projects"
            }
        }
    }
    @Suppress("LongMethod")
    fun sustainableDevelopmentVerifiedImpactStandard(debug: String) = catalogue {
        identifier = "verra-sustainableDevelopmentVerifiedImpactStandard${debug}"
        title = "Sustainable Development Verified Impact Standard"
        type = "program"
        img = getImg("sustainableDevelopmentVerifiedImpactStandard.png")
        description = """
          The Sustainable Development Verified Impact Standard assesses projects delivering social and environmental 
          benefits aligned with the UN Sustainable Development Goals.
            """.trimIndent()
        themes {
            concept {
                id = "AgricultureForestryAndOtherLandUse"
                prefLabels = mutableMapOf(
                    "en" to "Agriculture, Forestry, and Other Land Use",
                    "fr" to "Agriculture, foresterie et autres utilisations des terres"
                )
            }
            concept {
                id = "Transport"
                prefLabels = mutableMapOf(
                    "en" to "Transport",
                    "fr" to "Transport"
                )
            }
            concept {
                id = "ClimateChangeAdaption"
                prefLabels = mutableMapOf(
                    "en" to "Climate Change Adaption",
                    "fr" to "Adaptation au changement climatique"
                )
            }
            concept {
                id = "Energy"
                prefLabels = mutableMapOf(
                    "en" to "Energy",
                    "fr" to "Énergie"
                )
            }
            concept {
                id = "Food"
                prefLabels = mutableMapOf(
                    "en" to "Food",
                    "fr" to "Alimentation"
                )
            }
            concept {
                id = "Livelihoods"
                prefLabels = mutableMapOf(
                    "en" to "Livelihoods",
                    "fr" to "Moyens de subsistance"
                )
            }
            concept {
                id = "WaterAccessAndSanitation"
                prefLabels = mutableMapOf(
                    "en" to "Water Access and Sanitation",
                    "fr" to "Accès à l'eau et assainissement"
                )
            }
            concept {
                id = "WomensEmpowerment"
                prefLabels = mutableMapOf(
                    "en" to "Women’s Empowerment",
                    "fr" to "Autonomisation des femmes"
                )
            }
        }
        datasets {
            dataset {
                identifier = "documents"
                title = "documents"
                type = "document"
            }
            dataset {
                identifier = "projects"
                title = "Projects"
                type = "projects"
            }
        }
    }

    fun californiaOffsetProject(debug: String) = catalogue {
        identifier = "verra-californiaOffsetProject${debug}"
        title = "California Offset Project"
        type = "program"
        img = getImg("californiaOffsetProject.png")
        description = """
            The Verra California Offset Project Registry facilitates the verification and issuance of credits 
            from greenhouse gas reduction projects under California's cap-and-trade program.
            """.trimIndent()
        themes {
            concept {
                id = "MineMethaneCapture"
                prefLabels = mutableMapOf(
                    "en" to "Mine Methane Capture (MMC) Projects",
                    "fr" to "Projets de capture de méthane minier (MMC)"  // Adjust the French translation as needed
                )
            }
            concept {
                id = "USForestProjects"
                prefLabels = mutableMapOf(
                    "en" to "U.S. Forest Projects",
                    "fr" to "Projets forestiers américains"  // Adjust if there's a standard French term for "U.S. Forest Projects"
                )
            }
        }
        datasets {
            dataset {
                identifier = "documents"
                title = "documents"
                type = "document"
            }
            dataset {
                identifier = "projects"
                title = "Projects"
                type = "projects"
            }
        }
    }

}

fun goldStandardCatalogue(debug: String) = catalogue {
    identifier = "goldstandard${debug}"
    homepage = "https://www.goldstandard.org/"
    title = "Gold Standard"
    type = "standard"
    img = getImg("goldstandard.png")
    description = """
           Gold Standard for the Global Goals is a standard that sets requirements to design projects 
           for maximum positive impact in climate and development -- and to measure and report outcomes 
           in the most credible and efficient way.
            """.trimIndent()
    themes {
        concept {
            id = "RenewableEnergySupply"
            prefLabels = mutableMapOf(
                "en" to "Renewable Energy Supply",
                "fr" to "Approvisionnement en énergie renouvelable"  // French translation may need to be adjusted
            )
        }
        concept {
            id = "CommunityServiceProjects"
            prefLabels = mutableMapOf(
                "en" to "Community Service Projects",
                "fr" to "Projets de service communautaire"  // French translation may need to be adjusted
            )

        }
        concept {
            id = "WasteManagement"
            prefLabels = mutableMapOf(
                "en" to "Waste Management",
                "fr" to "Gestion des déchets"
            )
        }
        concept {
            id = "LandUseAndForests"
            prefLabels = mutableMapOf(
                "en" to "Land Use and Forests",
                "fr" to "Utilisation des terres et forêts"
            )
        }
    }
    datasets {
        dataset {
            identifier = "documents"
            title = "documents"
            type = "document"
        }
        dataset {
            identifier = "projects"
            title = "documents"
            type = "projects"
        }
    }
}

fun cee(debug: String) = catalogue {
    identifier = "cee${debug}"
    homepage = "https://www.ecologie.gouv.fr/dispositif-des-certificats-deconomies-denergie"
    title = "CEE"
    type = "standard"
    img = getImg("cee.png")
    description = """
           The CEE (Energy Savings Certificates) programs are initiatives that allow obtaining 
           CEE certificates without directly carrying out energy-saving actions. 
            """.trimIndent()
    themes {
    }
    datasets {
        dataset {
            identifier = "documents"
            title = "documents"
            type = "document"
        }
        dataset {
            identifier = "projects"
            title = "documents"
            type = "projects"
        }
    }
}

fun cdm(debug: String) = catalogue {
    identifier = "cdm${debug}"
    homepage =
        "https://unfccc.int/process-and-meetings/the-kyoto-protocol/mechanisms-under-the-kyoto-protocol/the-clean-development-mechanism"
    title = "CDM"
    type = "standard"
    img = getImg("cdm.png")
    description = """
           The Clean Development Mechanism (CDM) is a key component of the United Nations Framework Convention 
           on Climate Change (UNFCCC) designed to promote emission reduction projects in developing countries. 
            """.trimIndent()
    themes {
    }
    datasets {
        dataset {
            identifier = "documents"
            title = "documents"
            type = "document"
        }
        dataset {
            identifier = "projects"
            title = "documents"
            type = "projects"
        }
    }
}

