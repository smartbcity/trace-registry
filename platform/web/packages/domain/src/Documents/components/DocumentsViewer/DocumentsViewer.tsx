import { Box } from "@mui/material";
import { MultiPagePdfDisplayer } from "components";
import { useElementSize } from "@mantine/hooks";
import { useCallback, useState } from "react";
import { useMultiFilePagination } from "components/src/MultiPagePdfDisplayer/useMultiFilePagination";
import { DocumentsBar } from "../DocumentsBar";
import { DocumentsThumbnail } from "../DocumentsThumbnail";


export interface DocumentsViewerProps {
    files?: { name: string, file: any }[]
    reference?: string
    setQuote: (quote: string, fileName: string, pageNumber: number) => void
    isLoading?: boolean
}

export const DocumentsViewer = (props: DocumentsViewerProps) => {

    const { ref, width } = useElementSize();

    const [isOpenThumbnails, setOpenThumbnails] = useState<boolean>(false)

    const toggleThumbnails = useCallback(
        () => {
            setOpenThumbnails(old => !old)
        },
        [],
    )

    const {
        pagesNumberPerDocument,
        onDocumentLoadSuccess,
        setPageRef,
        goToPage
    } = useMultiFilePagination()


    return (
        <Box
            ref={ref}
            bgcolor="#F0EDE6"
            flexGrow={1}
            flexBasis={0}
            sx={{
                padding: (theme) => theme.spacing(1.5),
                width: "100%",
                height: "100%",
                overflow: "auto",
                "& .pdfPage": {
                    marginBottom: "16px"
                },
                "& .mui-utz8u3": {
                    margin: "0"
                }
            }}
        >
            <DocumentsBar onOpen={toggleThumbnails} onClose={toggleThumbnails} isOpen={isOpenThumbnails} />
            <DocumentsThumbnail files={props.files} isOpen={isOpenThumbnails} isLoading={props.isLoading} goToPage={goToPage} />
            <MultiPagePdfDisplayer
                {...props}
                onDocumentLoadSuccess={onDocumentLoadSuccess}
                pagesNumberPerDocument={pagesNumberPerDocument}
                setPageRef={setPageRef}
                parentWidth={width}
            />
        </Box>
    )
}
