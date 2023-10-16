import { Page, Section, Button, FormComposableField, FormComposable, useFormComposable } from "@smartb/g2";
import { Box } from "@mui/material";
import { TableV2, useTable, ColumnFactory } from "@smartb/g2";
import { FilePath } from "../../api";
import { useMemo } from "react"
import { useTranslation } from "react-i18next";
import { TableCellFileName } from "components";
import { Row, OnChangeFn, RowSelectionState } from '@tanstack/react-table';



export interface DocumentsListProps {
    page?: FilePath[],
    onRowClicked: (row: Row<FilePath>) => void,
    rowSelection?: RowSelectionState,
    onRowSelectionChange?: OnChangeFn<RowSelectionState>
}


export const DocumentsList = (props: DocumentsListProps) => {
    const { page, onRowClicked, rowSelection, onRowSelectionChange } = props;
    const { t } = useTranslation();

    const useDocumentsListColumn = () => {

        return useMemo(() => ColumnFactory<FilePath>({
            generateColumns: (generators) => ({
                name: ({
                    header: t("name"),
                    cell: ({ row }) => {
                        return <TableCellFileName text={row.original.name} />
                    }
                })
            })
        }), [])
    }

    const columns = useDocumentsListColumn();
    console.log("page", page)

    const tableState = useTable({
        data: page ?? [],
        columns: columns,
        state: {
            rowSelection
        },
        enableRowSelection: true,
        onRowSelectionChange: onRowSelectionChange
    })

    return (
        <Box
            bgcolor="#F0EDE6"
            flexGrow={1}
            flexBasis={0}
            sx={{
                padding: (theme) => theme.spacing(1.5),
                width: "100%",
                height: "100%",
                overflow: "auto",
                "& .MuiTable-root": {
                    background: "white"
                },
                "& .mui-utz8u3": {
                    margin: "0"
                }
            }}
        >
                <TableV2
                    isLoading={false}
                    tableState={tableState}
                    onRowClicked={onRowClicked}
                />
                </Box>
    )
}