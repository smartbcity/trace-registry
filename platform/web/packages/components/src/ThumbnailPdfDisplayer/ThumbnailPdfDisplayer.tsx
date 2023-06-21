import { CircularProgress, Stack } from "@mui/material"
import { Document, pdfjs, Thumbnail } from "react-pdf"
import "react-pdf/dist/esm/Page/AnnotationLayer.css"
import "react-pdf/dist/esm/Page/TextLayer.css"
import {useCallback} from "react"

pdfjs.GlobalWorkerOptions.workerSrc = new URL(
    "pdfjs-dist/build/pdf.worker.min.js",
    import.meta.url
).toString()

interface ThumbnailPdfDisplayerProps {
    files?: { name: string; file: any }[]
    isLoading: boolean
    numPages: number
    goToPage: (pageNumber: number) => void
    selectedFile?: string
}

export const ThumbnailPdfDisplayer = (props: ThumbnailPdfDisplayerProps) => {
    const { files, isLoading, numPages, goToPage, selectedFile } = props

    const handleThumbnailClick = useCallback((pageNumber: number) => {
        goToPage(pageNumber)
    },[])

    const renderThumbnails = () => {
        if (files && selectedFile) {
            const selectedDocument = files.find(
                (document) => document.name === selectedFile
            )

            if (selectedDocument) {
                return (
                    <Document file={selectedDocument.file}>
                        {Array.from({length: numPages}, (_, index) => (
                            <Thumbnail
                                key={`thumbnail_${index}`}
                                pageNumber={index + 1}
                                loading={<CircularProgress/>}
                                width={200}
                                className="thumbnailPdfPage"
                                onClick={() => handleThumbnailClick(index + 1)}
                            />
                        ))}
                    </Document>
                )
            }
        }
    }

    return (
        <Stack display="flex" flexDirection="column">
            {isLoading ? (
                <CircularProgress />
            ) : files && selectedFile ? (
                renderThumbnails()
            ) : (
                <CircularProgress />
            )}
        </Stack>
    )
}
