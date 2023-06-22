import { useCallback, useRef, useState, useEffect } from 'react'
import 'react-pdf/dist/esm/Page/AnnotationLayer.css'
import 'react-pdf/dist/esm/Page/TextLayer.css'
import type { PDFDocumentProxy } from 'pdfjs-dist'

export const useMultiFilePagination = (files?: { name: string, file: any }[]) => {
    const [pagesCount, setPagesCount] = useState<{ totalPages: number, pagesNumberPerDocument: number[] }>({
        totalPages: 0,
        pagesNumberPerDocument: [],
    })
    const pageRefs = useRef<Record<string, HTMLCanvasElement>>({})

    useEffect(() => {
        setPagesCount({
            totalPages: 0,
            pagesNumberPerDocument: []
        })
        pageRefs.current = {}
    }, [files])


    const goToPage = useCallback((pageNumber: number, docName: string) => {
        const pageRef = pageRefs.current[`${docName}-${pageNumber}`]
        if (pageRef) {
            pageRef.scrollIntoView({ behavior: 'smooth' })
        }
    }, [])

    const onDocumentLoadSuccess = useCallback(
        (pdf: PDFDocumentProxy, docIndex: number) => {
            setPagesCount(old => {
                const copy = [...old.pagesNumberPerDocument]
                copy[docIndex] = pdf.numPages
                return {
                    totalPages: old.totalPages + pdf.numPages,
                    pagesNumberPerDocument: copy
                }
            })
        },
        [],
    )

    const setPageRef = useCallback(
        (pageNumber: number, docName: string, ref: HTMLCanvasElement | null) => {
            if (ref) {
                pageRefs.current[`${docName}-${pageNumber}`] = ref
            }
        },
        [pagesCount],
    )

    return {
        totalPages: pagesCount.totalPages,
        pagesNumberPerDocument: pagesCount.pagesNumberPerDocument,
        pageRefs,
        goToPage,
        onDocumentLoadSuccess,
        setPageRef
    }
}
