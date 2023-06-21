import {Box} from "@mui/material"
import {MultiPagePdfDisplayer} from "components"
import { useElementSize } from "@mantine/hooks"


export interface DocumentsViewerProps {
    files?: { name: string, file: any }[]
    reference?: string
    setQuote: (quote: string, fileName: string, pageNumber: number) => void
    isLoading?: boolean
    numPages: number
    onDocumentLoadSuccess: (pdf: any) => void
    setPageRef:  (index: number, ref: (HTMLCanvasElement | null)) => (HTMLCanvasElement | null)
}

export const DocumentsViewer = (props: DocumentsViewerProps) => {
    const {files, numPages, onDocumentLoadSuccess, setPageRef} = props

    const { ref, width } = useElementSize()
    

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
                        marginBottom: (theme) => theme.spacing(2)
                    },
                    "& .mui-utz8u3" : {
                        margin: "0"
                    }
                }}
            >
                <MultiPagePdfDisplayer
                    {...props}
                    files={files}
                    parentWidth={width}
                    numPages={numPages}
                    setPageRef={setPageRef}
                    onDocumentLoadSuccess={onDocumentLoadSuccess}
                    />
            </Box>
    )
}
