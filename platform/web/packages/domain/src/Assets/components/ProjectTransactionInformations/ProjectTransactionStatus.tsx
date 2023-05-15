import { StatusTag } from '@smartb/g2'
import {transactionTypeValues} from "../../model";
import {useTranslation} from "react-i18next";

export interface ProjectTransactionStatusProps {
    value: string
}


const yellow = "#EDBA27"
const blue = "#3C78D8"
const red = "#D33147"
const green = "#159D50"


export const transactionTypeValuestoColor={
    [transactionTypeValues.issue()]: green,
    [transactionTypeValues.transfer()]: blue,
    [transactionTypeValues.withdraw()]: red,
    [transactionTypeValues.offset()]: yellow,
}
export const ProjectTransactionStatus = (props: ProjectTransactionStatusProps) => {
    const { value } = props
    const {t} = useTranslation()

    return (
        <StatusTag label={t(value)} customColor={transactionTypeValuestoColor[value]} />
    )
}