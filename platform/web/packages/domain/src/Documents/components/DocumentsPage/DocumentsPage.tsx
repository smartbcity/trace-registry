import { DocumentsChatbot, DocumentsList, DocumentsViewer } from "domain-components";
import { Stack } from "@mui/material";
import { useState, useMemo, useCallback, useEffect } from "react";
import { FilePath, useProjectFilesQuery } from "../../api/query";
import { useParams, useSearchParams } from "react-router-dom";
import { Row, RowSelectionState } from '@tanstack/react-table';
import qs from 'qs';

export interface DocumentsPageProps {
    isLoading?: boolean
    files?: FilePath[]
}

export const DocumentsPage = (props: DocumentsPageProps ) => {
    const { isLoading = false, files } = props
    const { projectId } = useParams()
    const [selectedFiles, selectFiles] = useState<FilePath[]>([])
    const [reference, setReference] = useState<string | undefined>(undefined)
    const [quote, setQuote] = useState<{ quote: string, fileName: string, pageNumber: number } | undefined>(undefined)
    const [rowSelection, setRowSelection] = useState<RowSelectionState>({})
    const [searchParams, setSearchParams] = useSearchParams()

    const fileList = files
    const isPreviewMode = useMemo(() => searchParams.toString() !== '' , [searchParams])

    useEffect(() => {
        fileList && fileList.length > 0 && selectFiles([fileList[0]])
    }, [fileList])

    const isAnyFileSlected = () => {
        return Object.values(rowSelection).length > 0;
    }

    const onRowClicked = useCallback(
        (row: Row<FilePath>) => {
            row.toggleSelected();
        }, 
        []
    )

    const toggleDocumentsSelection = useCallback(() => {
        if(!isPreviewMode) {
            setSearchParams(qs.stringify({ files: fileList?.map(file => file.objectId).join(',') }));
        }else {
            setSearchParams()
            setRowSelection({})
        }
    }, [fileList, isPreviewMode])

    const downloadedFiles = useProjectFilesQuery(
      selectedFiles.map((filePath) => (
        { id: projectId!, path: filePath })
      ), { enabled: !!selectedFiles })

    const filteredDownloadedFiles = useMemo(
      () => downloadedFiles.data?.map(
        (file, index) => ({ name: selectedFiles[index].name, file })
      ), [downloadedFiles.data, selectedFiles])
    const onSetQuote = useCallback(
        (quote: string, fileName: string, pageNumber: number) => {
            setQuote({
                quote,
                fileName,
                pageNumber
            })
        },
        [],
    )

        
    const removeQuote = useCallback(
        () => {
            setQuote(undefined)
        },
        [],
    )

    return (
        <Stack
            direction="row"
            position="relative"
            height="calc(100vh - 220px)"
        >
            {
                isPreviewMode 
                ? <DocumentsViewer reference={reference} setQuote={onSetQuote} isLoading={!filteredDownloadedFiles || filteredDownloadedFiles.length === 0} files={filteredDownloadedFiles} />
                : <DocumentsList onRowClicked={onRowClicked} page={fileList} rowSelection={rowSelection} onRowSelectionChange={setRowSelection} isLoading={isLoading} />
            }
            <DocumentsChatbot 
                removeQuote={removeQuote} 
                setReference={setReference} 
                quote={quote} 
                selectedFiles={selectedFiles} 
                toggleDocumentsSelection={toggleDocumentsSelection} 
                disabled={!isAnyFileSlected()}
                isPreviewMode={isPreviewMode}
            />
        </Stack>
    )
}
