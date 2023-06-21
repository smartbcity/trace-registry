import {Stack} from "@mui/material"
import {DocumentsBar} from "../DocumentsBar"
import {DocumentsThumbnail} from "../DocumentsThumbnail"
import {DocumentsViewer} from "../DocumentsViewer"
import pdf from "../DocumentsViewer/pdd.pdf"
import {useCallback, useState} from "react"
import {useMultiFilePagination} from "components/src/MultiPagePdfDisplayer/useMultiFilePagination"

export interface DocumentsFilesLayoutProps {
    files?: { name: string, file: any }[]
    reference?: string
    setQuote: (quote: string, fileName: string, pageNumber: number) => void
    isLoading?: boolean
}

export const DocumentsFilesLayout = (props: DocumentsFilesLayoutProps) => {
    const { files, reference, setQuote  } = props

    const [isOpenThumbnails, setOpenThumbnails] = useState<boolean>(false)

    const openThumbnails = useCallback(
        () => {
            setOpenThumbnails(true)
        },
        [],
    )

    const closeThumbnails = useCallback(
        () => {
            setOpenThumbnails(false)
        },
        [],
    )

    const {
        numPages,
        onDocumentLoadSuccess,
        pagination,
        setPageRef,
        goToPage
    } = useMultiFilePagination()

    const filesA = [{name: "lala.pdf", file: pdf},{name: "lola.pdf", file: undefined}]

    return (
        <Stack direction="column" width="100%">
            <DocumentsBar onOpen={openThumbnails} onClose={closeThumbnails} isOpen={isOpenThumbnails} pagination={pagination}/>
            <Stack direction="row" position="relative"
                   height="calc(100vh - 200px)"
            >
                {
                    isOpenThumbnails && <DocumentsThumbnail files={filesA} isLoading={!files} numPages={numPages} goToPage={goToPage}/>
                }
                <DocumentsViewer setPageRef={setPageRef} numPages={numPages} onDocumentLoadSuccess={onDocumentLoadSuccess} reference={reference} setQuote={setQuote} isLoading={!files} files={filesA} />
            </Stack>
        </Stack>
    )
}
