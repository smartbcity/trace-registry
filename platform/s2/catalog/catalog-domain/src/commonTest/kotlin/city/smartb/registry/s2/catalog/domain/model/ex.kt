package city.smartb.registry.s2.catalog.domain.model

val catalog1 = catalog {
    identifier = "http://example.com/catalog"

    datasets {
        dataset {
            identifier = "http://example.com/datasets/weather"

            distributions {
                distribution {
                    identifier = "http://example.com/datasets/weather/csv"
                    mediaType = "text/csv"
                    format = "CSV"
                }
            }
        }
    }
    catalogs {
        catalog {
            identifier = "http://registry.smartb.city/catalogues/standard/catalogues/verra"
        }
        catalog {
            identifier = "http://example.com/catalog2"
        }
    }
}

val verra =  catalog {
    identifier = "http://registry.smartb.city/catalogues/standard/catalogues/verra"
    type = "Standard"
    title = "Verra"
    description = """Verra, formerly known as Verified Carbon Standard (VCS), is a leading global standard 
            for the certification of greenhouse gas emission reduction projects.""".trimIndent()
    homepage = "https://verra.org/"
    themes {
        theme {
            id = "http://registry.smartb.city/catalogues/standard/themes/ForestryAndLandUse"
            prefLabels {
                "en" toThat "Forestry and Land Use"
                "fr" toThat "Foresterie et utilisation des terres"
            }
        }
    }
    catalogs {
        catalog {
            identifier = "http://registry.smartb.city/catalogues/standard/verra/catalogues/PlasticWasteReductionProgram"
            title = "The Plastic Waste Reduction Program"
            type = "Program"
        }
    }
//    relation {
//        relation = Agent("VERRA")
//        relationType = "StandardAdministrator"
//    }
    datasets {
        dataset {
            identifier = "http://registry.smartb.city/catalogues/standard/verra/datasets/documents"
            title = "Vera Standard Documents Dataset"
            type = "documents"
            description = """
               List of all documents describing the Vera Standard.
            """.trimIndent()
        }
    }
}

val verraCat =  catalog {
    identifier = "http://registry.smartb.city/catalogues/standard/catalogues/verra"
    type = "Standard"
    title = "Verra"
    img = "URL"
    description = """Verra, formerly known as Verified Carbon Standard (VCS), is a leading global standard 
            for the certification of greenhouse gas emission reduction projects.""".trimIndent()
    homepage = "https://verra.org/"
    themes {
        theme {
            id = "http://registry.smartb.city/catalogues/standard/themes/ForestryAndLandUse"
            prefLabels {
                "en" toThat "Forestry and Land Use"
                "fr" toThat "Foresterie et utilisation des terres"
            }
        }
    }

    datasets {
        dataset {
            identifier = "http://registry.smartb.city/catalogues/standard/verra/datasets/documents"
            title = "Vera Standard Documents Dataset"
            type = "documents"
            description = """
               List of all documents describing the Vera Standard.
            """.trimIndent()
        }
        dataset {
            identifier = "http://registry.smartb.city/catalogues/standard/verra/datasets/documents"
            title = "Project"
            type = "projects"
            description = """
               List of all documents describing the Vera Standard.
            """.trimIndent()
            length = 10200
        }
    }
}

val PlasticWasteReductionProgram =  catalog {
    identifier = "http://registry.smartb.city/catalogues/standard/verra/catalogues/PlasticWasteReductionProgram"
    type = "Program"
    title = "The Plastic Waste Reduction Program"
    description = """The Plastic Waste Reduction Program (Plastic Program) enables the robust impact assessment of new or scaled-up waste collection and recycling projects.
                    Projects registered with Verra’s Plastic Program can issue Plastic Credits for their plastic waste collection and/or recycling activities.""".trimIndent()
    homepage = "https://verra.org/programs/verified-carbon-standard/vcs-program-details"
    themes {
        theme {
            id = "http://registry.smartb.city/catalogues/standard/themes/CollectionRecycling"
            prefLabels {
                "en" toThat "Collection, Recycling"
                "fr" toThat "Collecte, recyclage"
            }
        }
    }
    catalogs {
        catalog {
            identifier = "http://registry.smartb.city/catalogues/standard/verra/catalogues/PlasticWasteReductionProgram/catalogue"
            title = "The Plastic Waste Reduction Program"
            type = "Methodologies"
        }
    }
    datasets {
        dataset {
            identifier = "http://registry.smartb.city/catalogues/standard/verra/catalogues/PlasticWasteReductionProgram/datasets/documents"
            title = "Verra Standard Documents Dataset"
            type = "documents"
            description = """
               List of all documents describing the Vera Standard.
            """.trimIndent()
        }
    }
}

val catalog2 = catalog {
    identifier = "http://example.org/catalog1"
    homepage = "http://example.org"
    themes {
        theme {
            id = "http://example.com/animals/vertebrates"
            prefLabels {
                "en" toThat "Vertebrates"
                "fr" toThat "Vertébrés"
            }
            definitions {
                "en" toThat "Animals with a backbone"
                "fr" toThat "Animaux avec une colonne vertébrale"
            }
        }
    }
    datasets {
        dataset {
            identifier = "http://example.org/datasets/weather"
            distributions {

                distribution {
                    identifier = "http://example.org/datasets/weather/csv"
                    mediaType = "text/csv"
                    format = "CSV"
                }
                distribution {
                    identifier = "http://example.org/datasets/weather/json"
                    mediaType = "application/json"
                    format = "JSON"
                }
            }
            spatialCoverage = location {
                geometry = "Polygon((30 10, 40 40, 20 40, 10 20, 30 10))"
            }
            temporalCoverage = periodOfTime {
                startDate = "2018-01-01"
                endDate = "2018-12-31"
            }
        }
        dataset {
            identifier = "http://example.org/datasets/pollution"
            distributions {
                distribution {
                    identifier = "http://example.org/datasets/pollution/csv"
                    mediaType = "text/csv"
                }
            }
        }
    }

    services {
        service {
            identifier = "http://example.org/services/weather"
            endpointURL = "http://example.org/api/weather"
            servesDataset {
                dataset {
                    identifier = "http://example.org/datasets/weather"
                }
            }
        }
    }
}

val schemeDsl = conceptScheme {
    id = "http://example.com/schemes/animals"
    prefLabel {
        "en" toThat "Animal Concept Scheme"
        "fr" toThat "Concept Scheme animal"
    }
    definition {
        "en" toThat "A concept scheme about animals"
        "fr" toThat "Un scheme de concept sur les animaux"
    }
    hasTopConcept = "http://example.com/animals/vertebrates"
    concepts {
        concept {
            id = "http://example.com/animals/vertebrates"
            prefLabels {
                "en" toThat "Vertebrates"
                "fr" toThat "Vertébrés"
            }
            definitions {
                "en" toThat "Animals with a backbone"
                "fr" toThat "Animaux avec une colonne vertébrale"
            }

        }
        concept {
            id = "http://example.com/animals/invertebrates"
            prefLabels {
                "en" toThat "Invertebrates"
                "fr" toThat "Invertébrés"
            }
            definitions {
                "en" toThat "Animals without a backbone"
                "fr" toThat "Animaux sans colonne vertébrale"
            }
        }
    }
}