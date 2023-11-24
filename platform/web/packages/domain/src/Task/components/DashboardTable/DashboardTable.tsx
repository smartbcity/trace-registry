import { Typography } from '@mui/material'
import { ColumnFactory, useTable } from '@smartb/g2'
import { Task } from '../../model'
import { useMemo } from "react"
import { OffsetPagination, OffsetTable, OffsetTableProps, PageQueryResult } from "template";
import { useTranslation } from 'react-i18next';

function useProductColumn() {
    const { t } = useTranslation();
    return useMemo(() => ColumnFactory<Task>({
        generateColumns: (generators) => ({
            updatedAt: generators.date({
                header: t("updatedAt"),
                getCellProps: (task) => ({
                    value: task.updateDate
                })
            }),

            type: generators.text({
                header: t("tasks.type"),
                getCellProps: (task) => ({
                    value: task.type,
                }),
            }),

            id: generators.text({
                header: t("identifier"),
                getCellProps: (task) => ({
                    value: task.identifier
                })
            }),

            contact: generators.chip({
                header: t("contactType"),
                getCellProps: (task) => ({
                    options: [{
                        key: "DOE",
                        label: "DOE for validation",
                        color: "#6B55DD"
                    },
                    {
                        key: "PM",
                        label: "Project Manager",
                        color: "#005B47"
                    }],
                    value: task.contact
                })
            }),

            name: generators.text({
                header: t("name"),
                getCellProps: (task) => ({
                    value: task.name
                })
            }),

            status: generators.chip({
                header: t("status"),
                getCellProps: (task) => ({
                    options: [{
                        key: "APPROVED",
                        label: "Approved",
                        color: "#159D74"
                    },
                    {
                        key: "PENDING",
                        label: "Pending approval",
                        color: "#153B9D"
                    }],
                    value: task.status
                })
            }),

            accountant: generators.profile({
                header: t("accountant"),
                getCellProps: (task) => ({
                    value: task.accountant
                })
            }),
        })
    }), [t]);
}

export interface DashboardTableProps extends Partial<OffsetTableProps<Task>> {
    onOffsetChange?: (newOffset: OffsetPagination) => void
    page?: PageQueryResult<Task>
    pagination: OffsetPagination
    isLoading?: boolean
}

export const DashboardTable = (props: DashboardTableProps) => {
    const { isLoading, page, onOffsetChange, pagination, sx, header, ...other } = props
    const { t } = useTranslation()

    const columns = useProductColumn()

    const tableState = useTable({
        data: page?.items ?? [],
        columns: columns,
    })

    // const getRowLink = useCallback(
    //     (row: Row<Task>) => {
    //         return {
    //             to: projectsProjectIdViewTabAll(row.original.identifier)
    //         }
    //     },
    //     [projectsProjectIdViewTabAll],
    // )

     return (
        <>
        { (!page?.items && !isLoading) ?
            <>
                {header}
                <Typography align="center" sx={{ marginTop: "32px" }}>{t("tasks.noData")}</Typography>
            </>
            :
                <OffsetTable<Task>
                    {...other}
                    sx={{
                        overflow: "unset",
                        "& .statusColumn": {
                            maxWidth: "180px"
                        },
                        "& .AruiTable-tableHead": {
                            top: "70px",
                            background: (theme) => theme.palette.background.default + "99"
                        },
                        ...sx
                    }}
                    header={header}
                    tableState={tableState}
                    page={page}
                    pagination={pagination}
                    onOffsetChange={onOffsetChange}
                    isLoading={isLoading}
                    // getRowLink={getRowLink}

                />
        }
        </>
    )
}
