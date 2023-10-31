import { Button, LinkButton } from '@smartb/g2'
import { Typography } from '@mui/material'
import { useTranslation } from 'react-i18next'
import {
    AssetsPage,
    DocumentsPage,
    ProjectActivities,
    ProjectInformationSection,
    ProtocolBuilder,
    useProjectGetQuery
} from 'domain-components'
import { useNavigate, useParams } from 'react-router-dom'
import React, { useCallback, useMemo } from 'react'
import { useExtendedAuth, useRoutesDefinition, useConfirmationPopUp } from 'components'
import { AppPage, SectionTab, Tab } from 'template'
import { ArrowBackIosNewRounded } from '@mui/icons-material'
import { useProjectListFilesQuery } from "domain-components";

export interface ProjectViewProps {
}

export const ProjectView = (_: ProjectViewProps) => {
    const { projectId, tab } = useParams()
    const { projectsProjectIdViewTabAll, projects } = useRoutesDefinition()
    const navigate = useNavigate();
    const { service } = useExtendedAuth()
    const { t } = useTranslation()
    const currentTab = useMemo(() => tab ?? "info", [tab])
    const projectQuery = useProjectGetQuery({ query: { id: projectId! } })
    const fileListQuery = useProjectListFilesQuery({ query: { id: projectId! } })
    const project = projectQuery.data?.item
    if (project) {
        //@ts-ignore
        project.private = false
    }

    const {handleOpen, popup} = useConfirmationPopUp({
        title: t("projects.editVisibility"),
        description: t("projects.editVisibilityDescription")
    })

    const onTabChange = useCallback((_: React.SyntheticEvent<Element, Event>, value: string) => {
        navigate(projectsProjectIdViewTabAll(projectId || "", value))
    }, [])
    const tabs: Tab[] = useMemo(() => {
        const tabs: Tab[] = [{
            key: 'info',
            label: t('information'),
            component: (<ProjectInformationSection project={project} isLoading={projectQuery.isLoading} />)
        }]
        const hasActivity = !!project?.activities && project?.activities.length !== 0
        const hasDocument = (fileListQuery.data?.items?.length ?? 0) > 0
        const hasAssetPools = !!project?.assetPools && project?.assetPools.length !== 0
        hasActivity && tabs.push({
            key: 'activities',
            label: t('activities'),
            component: (project ? <ProjectActivities isLoading={projectQuery.isLoading} project={project} /> : <></>)
        })

        hasAssetPools && tabs.push({
            key: 'assets',
            label: t('assets'),
            component: (project ? <AssetsPage isLoading={projectQuery.isLoading} project={project} /> : <></>)
        })
        hasDocument && tabs.push({
            key: 'documents',
            label: t('documents'),
            component: (<DocumentsPage isLoading={fileListQuery.isLoading} files={fileListQuery.data?.items} />)
        })

        tabs.push({
            key: 'protocol',
            label: "Protocol",
            component: (<ProtocolBuilder />)
        })
        return tabs
    }, [project, projectQuery.isLoading, fileListQuery.data?.items, t])

    return (
        <AppPage
            title={project?.name ?? t("project")}
            //@ts-ignore
            rightPart={service.is_tr_project_manager() ? [<Button onClick={handleOpen} >{project?.private ? t("projects.makePublic") : t("projects.makePrivate")}</Button>] : undefined}
        >
            <SectionTab
                tabs={tabs}
                currentTab={currentTab}
                goBackLink={(<LinkButton sx={{ zIndex: 5 }} to={projects()} key="goBack" variant="text" startIcon={<ArrowBackIosNewRounded />}>{t("projectList")}</LinkButton>)}
                onTabChange={onTabChange}
                sx={{
                    "& .AruiSection-contentContainer": {
                        padding: currentTab !== "info" ? "unset" : undefined
                    }
                }}
            />
            {currentTab === "info" &&  !!project &&
                <Typography align='right' sx={{ marginTop: (theme) => theme.spacing(3), color: "#9E9E9E" }} >
                    {t("lastChanged", { date: new Date(project.lastModificationDate).toLocaleDateString() })}
                </Typography>
            }
            {popup}
        </AppPage>
    )
}
