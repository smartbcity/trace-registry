import { useCustomFilters } from 'components'
import {FilterComposableField, Option} from '@smartb/g2'
import { useMemo } from 'react'
import { useTranslation } from 'react-i18next'
import {AssetPageQuery, transactionTypeValues} from "domain-components";
import {transactionTypeValuestoColor} from "../ProjectTransactionInformations/ProjectTransactionStatus";

export const useTransactionsFilters = () => {

    const {t} = useTranslation()
     const typesOptions = useMemo(
         () => {
                    const types: Option[] = []
                    for (let key in transactionTypeValues) {
                            types.push({
                                key: key,
                                label: t(key),
                                color: transactionTypeValuestoColor[key]
                            })

                    }
                    return types}, [t])

    const filters = useMemo((): FilterComposableField[] => [
        {
            name: 'id',
            type: 'textField',
            params: {
                textFieldType: 'search',
                placeholder: t("projects.assets.serial")
            }
        },
        {
            name: 'type',
            label: t("types"),
            type: 'select',
            params: {
                options: typesOptions,
                multiple: true
            }
        }
    ], [t])
    return useCustomFilters<AssetPageQuery>({filters: filters})
}