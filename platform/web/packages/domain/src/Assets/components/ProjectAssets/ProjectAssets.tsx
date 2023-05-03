import {Header, useFormComposable} from '@smartb/g2'
import { Row } from '@tanstack/react-table';
import {Stack} from '@mui/material'
import {
    Project,
    useProjectTransactionPageQuery
} from '../../../Project'
import {Fragment, useCallback, useMemo, useState} from "react";
import {Offset, OffsetPagination} from "template";
import {
    ProjectTransactionsTable,
    ProjectBalanceBanner,
    useTransactionsFilters,
    ProjectTransactionPage,
    Transaction
} from "../";

export interface ProjectAssetsProps {
    project?: Project
    isLoading: boolean
}

export const ProjectAssets = (props: ProjectAssetsProps) => {
    const { isLoading, project } = props
    const { component, setOffset, submittedFilters } = useTransactionsFilters()
    const pagination = useMemo((): OffsetPagination => ({ offset: submittedFilters.offset ?? Offset.default.offset, limit: submittedFilters.limit ?? Offset.default.limit }), [submittedFilters.offset, submittedFilters.limit])
    const transactions = useProjectTransactionPageQuery({
        query: submittedFilters
    })
    const formState = useFormComposable({
        onSubmit: () => { },
        isLoading: isLoading,
        readonly: true,
        formikConfig: {
            initialValues: {
                ...project,
                location: project?.location ? { position: { lat: project?.location?.lat, lng: project?.location?.lon } } : undefined,
            }
        }
    })
    const [selectedTransaction, setTransaction] = useState<Transaction | undefined>(undefined)

    const transactionClicked = useCallback(
        (row: Row<Transaction>) => {
            setTransaction(row.original)
        },
        [],
    )
    // projectDetails projectStatusTag
    // fieldStackProps

    return (
        <Stack
            direction="row"
            sx={{
                height: "calc(100vh - 200px)",
                minHeight: "fit-content",
                width: "100%"
            }}
        >
            <Stack
                direction="column"
                sx={{
                    padding: "24px",
                    width: "100%"

                }}
                gap={3}

            >
                <ProjectBalanceBanner formState={formState} />
                <ProjectTransactionsTable
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
                                backgroundColor: "none",
                                "& .AruiHeader-leftPartContainer": {
                                    width: "100%"
                                }
                            }}
                        />
                    }
                    page={{
                        items: [{
                            serial: "SB1-1-KE-SB5801-4-2022-23924-1840-2017",
                            date: "6/30/22",
                            vintage: 2023,
                            quantity: "1 000 000 tons",
                            to: "0xe2f2c31bd29a8dc820ef14969abe89461",
                            type: "Purchase",
                            for: "0xe2f2c31bd29a8dc820ef14969abe03213654",
                            from: "0xe2f2c31bd29a8dc820ef14969abe479841320"
                        },{
                            serial: "SB1-1-KE-SB5801-4-2022-23924-1840-2017",
                            date: "6/30/22",
                            vintage: 2023,
                            quantity: "1 000 000 tons",
                            to: "0xe2f2c31bd29a8dc820ef14969abe89461",
                            type: "Purchase",
                            for: "0xe2f2c31bd29a8dc820ef14969abe03213654",
                            from: "0xe2f2c31bd29a8c820ef14969abe479841320"
                        },{
                            serial: "SB1-1-KE-SB5801-4-2022-23924-1840-2017",
                            date: "6/30/22",
                            vintage: 2023,
                            quantity: "1 000 000 tons",
                            to: "0xe2f2c31bd29a8...dc820ef14969abe89461",
                            type: "Purchase",
                            for: "0xe2f2c31bd29a8...dc820ef14969abe03213654",
                            from: "0xe2f2c31bd29a8...dc820ef14969abe479841320"
                        },{
                            serial: "SB1-1-KE-SB5801-4-2022-23924-1840-2017",
                            date: "6/30/22",
                            vintage: 2023,
                            quantity: "1 000 000 tons",
                            to: "0xe2f2c31bd29a8...dc820ef14969abe89461",
                            type: "Purchase",
                            for: "0xe2f2c31bd29a8...dc820ef14969abe03213654",
                            from: "0xe2f2c31bd29a8...dc820ef14969abe479841320"
                        },{
                            serial: "SB1-1-KE-SB5801-4-2022-23924-1840-2017",
                            date: "6/30/22",
                            vintage: 2023,
                            quantity: "1 000 000 tons",
                            to: "0xe2f2c31bd29a8...dc820ef14969abe89461",
                            type: "Purchase",
                            for: "0xe2f2c31bd29a8...dc820ef14969abe03213654",
                            from: "0xe2f2c31bd29a8...dc820ef14969abe479841320"
                        }
                            ],
                        total: 10
                    }}
                    pagination={pagination}
                    isLoading={transactions.isLoading}
                    onOffsetChange={setOffset}
                    onTransactionClick={transactionClicked}
                />
            </Stack>
            {
                selectedTransaction ?
                    <ProjectTransactionPage isLoading={isLoading} project={project} transaction={selectedTransaction} />
                    : ""
            }
        </Stack>
    )
}
