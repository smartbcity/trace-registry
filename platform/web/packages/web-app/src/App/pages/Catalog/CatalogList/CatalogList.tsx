import { Typography, Box } from '@mui/material'
import { Catalog, StandardCard, useCatalogFilters } from 'domain-components'
import { useMemo } from 'react'
import { useTranslation } from 'react-i18next'
import { AppPage } from 'template'

//@ts-ignore
const catalog: Catalog = {
    identifier: "1",
    title: "Verra",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    img: "./logo_verra.svg",
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

export const CatalogList = () => {
    const { t } = useTranslation()

    const {component} = useCatalogFilters()

    const catalogs = useMemo(() => {
        const catalogs: Catalog[] = []
        for (let i = 0; i < 5; i++) {
            catalogs.push({
                ...catalog,
                identifier: i.toString(),
            })
        }
        return catalogs
    }, [])

    return (
        <AppPage
            title={t("exploreStandards")}
            flexContent
        >
            <Typography
            sx={{maxWidth: "1000px", alignSelf: "center"}}
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
            <Box
            sx={{
                display: "grid",
                gridTemplateColumns: "repeat(auto-fit, minmax(300px, 1fr))"
            }}
            gap={4}
            >
            {
                catalogs.map((catalog) => (
                    <StandardCard key={catalog.identifier} catalog={catalog} />
                ))
            }
            </Box>
        </AppPage>
    )
}