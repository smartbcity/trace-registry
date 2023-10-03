import { Typography, Box } from '@mui/material'
import { Catalog, CatalogGrid, useCatalogFilters } from 'domain-components'
import { useTranslation } from 'react-i18next'
import { AppPage } from 'template'

//@ts-ignore
export const catalog: Catalog = {
    identifier: "1",
    title: "Verra",
    description: `Verra, formerly known as Verified Carbon Standard (VCS), is a leading global standard for the certification of greenhouse gas emission reduction projects. 
    \nIt provides a robust framework for verifying and accounting for the emission reductions achieved by these projects. Verra ensures that projects adhere to rigorous criteria, including additionality, permanence, and transparency, to ensure the integrity and credibility of the certified emission reductions.
    \nBy supporting projects across various sectors and regions, Verra plays a vital role in promoting sustainable development and combating climate change.`,
    img: "/logo_verra.svg",
    themes: [{
        id: "1",
        prefLabels: {
            "en": "Forestry and Land Use",
            "fr": "Foresterie et utilisation des terres"
        }
    }, {
        id: "2",
        prefLabels: {
            "en": "Forestry and Land Use",
            "fr": "Foresterie et utilisation des terres"
        }
    }, {
        id: "3",
        prefLabels: {
            "en": "Forestry and Land Use",
            "fr": "Foresterie et utilisation des terres"
        }
    }, {
        id: "4",
        prefLabels: {
            "en": "Forestry and Land Use",
            "fr": "Foresterie et utilisation des terres"
        }
    }, {
        id: "5",
        prefLabels: {
            "en": "Forestry and Land Use",
            "fr": "Foresterie et utilisation des terres"
        }
    }],
    datasets: []
}

const toArray = () => {
    const catalogs: Catalog[] = []
    for (let i = 0; i < 5; i++) {
        catalogs.push({
            ...catalog,
            identifier: i.toString(),
        })
    }
    return catalogs
}

export const catalogs = toArray()

export const CatalogListPage = () => {
    const { t } = useTranslation()

    const { component } = useCatalogFilters()

    return (
        <AppPage
            title={t("exploreStandards")}
            flexContent
        >
            <Typography
                sx={{ maxWidth: "1000px", alignSelf: "center" }}
            >
                {t("catalogs.exploreDetails")}
            </Typography>
            <Box
                sx={{
                    alignSelf: "center"
                }}
            >
                {component}
            </Box>
            <CatalogGrid catalogs={catalogs} />
        </AppPage>
    )
}
