import {CircularProgress, Stack} from "@mui/material";
import {Document, pdfjs, Thumbnail} from "react-pdf";
import 'react-pdf/dist/esm/Page/AnnotationLayer.css'
import 'react-pdf/dist/esm/Page/TextLayer.css'
import {useMultiFilePagination} from "../MultiPagePdfDisplayer/useMultiFilePagination";

pdfjs.GlobalWorkerOptions.workerSrc = new URL(
    'pdfjs-dist/build/pdf.worker.min.js',
    import.meta.url,
).toString()

interface ThumbnailPdfDisplayerProps {
    files?: { name: string, file: any }[]
    isLoading: boolean
}

export const ThumbnailPdfDisplayer = (props: ThumbnailPdfDisplayerProps) => {
    const { files, isLoading } = props
    const {
        numPages,
        onDocumentLoadSuccess
    } = useMultiFilePagination()

    return (
        isLoading ? (
            <CircularProgress />
        ) : (
            files ? (
                <Stack display="flex" flexDirection="column">
                    {files.map(((document, indexDoc) => (
                            <Document key={`doc_${indexDoc}`} file={document.file} onLoadSuccess={onDocumentLoadSuccess}>
                                {Array.from({ length: numPages }, (_, index) => (
                                    <Thumbnail
                                        pageNumber={index + 1}
                                        loading={<CircularProgress />}
                                        width={200}
                                        className="thumbnailPdfPage"
                                    />
                                ))}
                            </Document>
                        ))
                    )}
                </Stack>
            ) : (
                <CircularProgress />
            )
        )
    )
}
