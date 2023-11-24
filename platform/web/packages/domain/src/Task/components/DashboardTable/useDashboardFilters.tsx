import {useCustomFilters} from 'components'
import {FilterComposableField} from '@smartb/g2'
import {useMemo} from 'react'
import {useTranslation} from 'react-i18next'

export const useDashboardFilters = () => {
    const {t} = useTranslation()
    const filters = useMemo((): FilterComposableField[] => [
        {
            name: 'identifier',
            type: 'textField',
            params: { 
                textFieldType: 'search', 
                placeholder: t("identifier"), 
                style: { width: "170px" }
            }
        },
        {
            name: 'name',
            type: 'textField',
            params: { 
                textFieldType: 'search', 
                placeholder: t("name"), 
                style: { width: "170px" }
            }
        },
    ], [t])

    return useCustomFilters({filters: filters})
}
