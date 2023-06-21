import { useCallback, useRef, useState } from 'react'
import 'react-pdf/dist/esm/Page/AnnotationLayer.css'
import 'react-pdf/dist/esm/Page/TextLayer.css'
import type { PDFDocumentProxy } from 'pdfjs-dist'
import { Pagination } from '@smartb/g2'

export const useMultiFilePagination = () => {
    const [numPages, setNumPages] = useState(0)
    const [numTotalPages, setNumTotalPages] = useState(0)
    const [currentPage, setCurrentPage] = useState(1)
    const pageRefs = useRef<HTMLCanvasElement[]>([])

    const goToPage = useCallback((pageNumber: number) => {
        setCurrentPage(pageNumber)
        const pageIndex = pageNumber - 1
        if (pageIndex >= 0 && pageIndex < pageRefs.current.length) {
            const pageRef = pageRefs.current[pageIndex]
            if (pageRef) {
                pageRef.scrollIntoView({ behavior: 'smooth' })
            }
        }
    }, [])

    const onDocumentLoadSuccess = useCallback(
        (pdf: PDFDocumentProxy) => {
            setNumPages(pdf.numPages)
            setNumTotalPages((prevNumTotalPages) => prevNumTotalPages + pdf.numPages)
        },
      [],
    )
    
    const setPageRef = useCallback(
        (index: number, ref: HTMLCanvasElement | null) => ref && (pageRefs.current[index] = ref),
      [],
    )
    
    return {
        pagination:
            <Pagination
                page={currentPage}
                totalPage={numPages}
                onPageChange={(newPageNumber) => goToPage(newPageNumber)}
            />
        ,
        numPages,
        setNumPages,
        currentPage,
        setCurrentPage,
        numTotalPages,
        setNumTotalPages,
        pageRefs,
        goToPage,
        onDocumentLoadSuccess,
        setPageRef
    }
}
