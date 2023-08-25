import { Divider, Stack, Typography } from '@mui/material'
import { FormComposable, FormComposableField, FormComposableState } from '@smartb/g2'
import { useMemo } from 'react'
import { useTranslation } from 'react-i18next'
import { Project } from '../../model'
import { ProjectStatus } from '../ProjectTable/ProjectStatus'
import { useExtendedAuth } from 'components'

export interface ProjectDetailsProps {
    formState: FormComposableState
}

export const ProjectDetails = (props: ProjectDetailsProps) => {
    const { formState } = props

    const {service} = useExtendedAuth()

    const { t } = useTranslation()

    const fields = useMemo((): FormComposableField<keyof Project | "vvb.name" | "proponent.name" | "assessor.name" | "private">[] => [
        ...(service.is_tr_project_manager() ? [{
        name: "private",
        label: t("visibility"),
        type: "select",
        params: {
            orientation: "horizontal",
            readOnlyType: "chip",
            options: [{
                key: true,
                label: t("private"),
                color: "#353945"
            },{
                key: false,
                label: t("public"),
                color: "#353945"
            }]
        }
    } as FormComposableField<"private">] : []),{
        name: "country",
        label: t("origin"),
        type: "select",
        params: {
            orientation: "horizontal",
        }
    }, {
        name: "proponent.name",
        label: t("proponent"),
        type: "textField",
        params: {
            orientation: "horizontal",
        }
    }, {
        name: "vvb.name",
        label: t("vvb"),
        type: "textField",
        params: {
            orientation: "horizontal",
        }
    }, {
        name: "assessor.name",
        label: t("assessor"),
        type: "textField",
        params: {
            orientation: "horizontal",
        }
    }, {
        name: "name",
        label: t("project"),
        type: "textField",
        params: {
            orientation: "horizontal",
        }
    }, {
        name: "status",
        label: t("status"),
        type: "textField",
        params: {
            orientation: "horizontal",
            readOnlyType: "customElement",
            readOnlyElement: ProjectStatus
        }
    }], [t, service.is_tr_project_manager])

    const description = useMemo((): FormComposableField<keyof Project>[] => [{
        name: "description",
        label: t("projects.details"),
        type: "textField",
        params: {
            multiline: true,
            rows: 8
        }
    }], [t])

    return (
        <Stack
            gap={2}
            divider={<Divider flexItem />}
            sx={{
                flexGrow: 1,
                flexBasis: 0
            }}
        >
            <Typography variant="h6">{t("details")}</Typography>
            <FormComposable
                sx={{
                    "& .AruiForm-field": {
                        justifyContent: "flex-start"
                    },
                    "& .AruiForm-field > *": {
                        flexGrow: 1,
                        flexBasis: 0
                    }
                }}
                formState={formState}
                fields={fields}
            />
            <FormComposable formState={formState} fields={description} />
        </Stack>
    )
}
