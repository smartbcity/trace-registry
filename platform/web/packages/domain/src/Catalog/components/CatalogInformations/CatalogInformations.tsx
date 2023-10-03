
import { Catalog } from '../../model'
import { useTranslation } from 'react-i18next'
import { CatalogDetails } from '../CatalogDetails'
import { TitleDivider } from 'components'
import { CatalogGrid } from '../CatalogGrid'
import { Button } from '@smartb/g2'

export interface CatalogInformationsProps {
    catalog?: Catalog
    isLoading?: boolean
    mostUsedCatalogs?: Catalog[]
}

export const CatalogInformations = (props: CatalogInformationsProps) => {
    const {
        catalog,
        isLoading,
        mostUsedCatalogs
    } = props

    const { t } = useTranslation()
    

    return (
        <>
            <TitleDivider 
            title={t("catalogs.standardDescription")}
            />
            <CatalogDetails 
            catalog={catalog}
            isLoading={isLoading}
            />
            <TitleDivider 
            title={t("catalogs.mostUsedStandards")}
            />
            <CatalogGrid 
            catalogs={mostUsedCatalogs}
            />
            <Button sx={{alignSelf: "flex-end"}}>{t("catalogs.seeAllPrograms")}</Button>
        </>
    )
}