import { AppPage, Offset, OffsetPagination, PageQueryResult } from 'template'
import { useTranslation } from "react-i18next"
import {Typography } from "@mui/material"
import { DashboardTable, Task, useDashboardFilters } from 'domain-components'
import { Header } from '@smartb/g2'
import { Fragment, useMemo } from 'react'
import { HandleTasksIcon } from 'components'

const tasks: PageQueryResult<Task> = {
    items: [{
        updateDate: Date.now(),
        type: "Sign in",
        identifier: "SI-10001",
        contact: "DOE",
        name: "Organization A",
        status: "APPROVED",
        accountant: {
            givenName: 'Basile',
            familyName: 'Savouret'
        }
    }, {
        updateDate: Date.now(),
        type: "Project activity",
        identifier: "PA-10001",
        contact: "PM",
        name: "Project A",
        status: "PENDING",
        accountant: {
            givenName: 'Basile',
            familyName: 'Savouret'
        }
    }, {
        updateDate: Date.now(),
        type: "Project Creation",
        identifier: "PC-10001",
        contact: "PM",
        name: "Project B",
        status: "PENDING",
        accountant: {
            givenName: 'Basile',
            familyName: 'Savouret'
        }
    }],
    total: 3
}

export const Dashboard = () => {
    const { component, setOffset, submittedFilters } = useDashboardFilters()
    const { t } = useTranslation()

    const pagination = useMemo((): OffsetPagination => ({ offset: submittedFilters.offset ?? Offset.default.offset, limit: submittedFilters.limit ?? Offset.default.limit }), [submittedFilters.offset, submittedFilters.limit])
    return (
        <AppPage
            title={t("dashboard")}
            flexContent
        >
            <HandleTasksIcon sx={{alignSelf: "center", height: "150px", width: "auto"}} />
            <Typography
            variant="h6"
            align='center'
            >
                {t("tasks.welcomeToDashboard")}
            </Typography>
            <Typography
            variant="body2"
            align='center'
            >
                {t("tasks.dashboardDescription")}
            </Typography>
            <DashboardTable
                        header={
                            <Header
                                content={[
                                    {
                                        leftPart: [
                                            <Fragment key="filters" >{component}</Fragment>
                                        ]
                                    }
                                ]}
                                sx={{
                                    "& .AruiHeader-leftPartContainer": {
                                        width: "100%"
                                    },
                                }}
                            />
                        }
                        page={tasks}
                        pagination={pagination}
                        isLoading={false}
                        onOffsetChange={setOffset}
                    />
        </AppPage>
    )
}
