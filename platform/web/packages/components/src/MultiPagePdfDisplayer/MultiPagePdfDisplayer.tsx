import { useEffect, useState, useRef } from 'react'
import { Document, Page, pdfjs } from 'react-pdf'
import { LoadingPdf } from './LoadingPdf'
import 'react-pdf/dist/esm/Page/AnnotationLayer.css'
import 'react-pdf/dist/esm/Page/TextLayer.css'

pdfjs.GlobalWorkerOptions.workerSrc = new URL(
    'pdfjs-dist/build/pdf.worker.min.js',
    import.meta.url
).toString()

interface MultiPagePdfDisplayerProps {
    file?: string
    parentWidth: number
}

export const MultiPagePdfDisplayer = (props: MultiPagePdfDisplayerProps) => {
    const { file, parentWidth } = props
    const [numPages, setNumPages] = useState(0)
    const [currentPage, setCurrentPage] = useState(1)
    const pageRefs = useRef<Array<HTMLDivElement | null>>([])
    const [dataElementIds, setDataElementIds] = useState<Array<Array<string[]>>>([])
    const [textContents, setTextContents] = useState<Array<Array<string>>>([])

    useEffect(() => {
        const loadNextPages = () => {
            setCurrentPage((prevPage) => {
                const nextPage = prevPage + 5
                if (nextPage > numPages) {
                    return numPages
                } else {
                    return nextPage
                }
            })
        }
        if (currentPage <= numPages) {
            loadNextPages()
        }
    }, [currentPage, numPages])

    const onLoadSuccess = ({ numPages }: { numPages: number }) => {
        setNumPages(numPages)
        setDataElementIds(Array.from({ length: numPages }, () => []))
        setTextContents(Array.from({ length: numPages }, () => []))
    }

    const handlePageLoadSuccess = (pageIndex: number) => {
        const textLayer = document.querySelector(".textLayer")
        console.log(textLayer?.textContent)
        console.log(textLayer?.querySelectorAll('span'))
        const pageDataElementIds = Array.from(
            textLayer?.querySelectorAll('span') || []

        ).map((element) => {
            console.log("yop" + element.textContent)
            if (element instanceof HTMLElement) {
                return element.textContent || ''
            }
            return ''
        })

        setTextContents((prevTextContents) => {
            const newTextContents = [...prevTextContents]
            newTextContents[pageIndex] = pageDataElementIds
            return newTextContents
        })
        setDataElementIds((prevDataElementIds) => {
            const newDataElementIds = [...prevDataElementIds]
            newDataElementIds[pageIndex] = pageDataElementIds.map((dataElementId) => [dataElementId])
            return newDataElementIds
        })
    }

    const goToPage = (pageNumber: number) => {
        setCurrentPage(pageNumber)
        const pageIndex = pageNumber - 1
        if (pageIndex >= 0 && pageIndex < pageRefs.current.length) {
            const pageRef = pageRefs.current[pageIndex]
            if (pageRef) {
                pageRef.scrollIntoView({ behavior: 'smooth' })
            }
        }
    }

    const handleLinkClick = (
        event: React.MouseEvent<HTMLAnchorElement>,
        pageNumber: number
    ) => {
        event.preventDefault()
        goToPage(pageNumber)
    }


    return (
        <>
            {file ? (
                <Document file={file} onLoadSuccess={onLoadSuccess}>
                    <a href="#" onClick={(event) => handleLinkClick(event, 16)}>
                        Cliquez ici pour aller à l'annotation avec l'identifiant de données : Page=16
                    </a>
                    {Array.from({ length: numPages }, (_, index) => (
                        <Page
                            key={`page_${index + 1}`}
                            pageNumber={index + 1}
                            loading={<LoadingPdf parentWidth={parentWidth} />}
                            onLoadSuccess={() => handlePageLoadSuccess(index)}
                            width={parentWidth}
                            className="pdfPage"
                            inputRef={(ref) => (pageRefs.current[index] = ref)}
                        >
                            {dataElementIds[index]?.map((dataElementId, dataIndex) => (
                                <div style={{ zIndex: 999 }} key={dataIndex} data-element-id={dataElementId}>
                                {textContents[index][dataIndex]}
                                </div>
                            ))}
                        </Page>
                    ))}
                </Document>
            ) : (
                <LoadingPdf parentWidth={parentWidth} />
            )}
        </>
    )
}
