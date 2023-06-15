import { useEffect, useState, useRef } from 'react';
import { Document, Page, pdfjs } from 'react-pdf';
import { LoadingPdf } from './LoadingPdf';
import 'react-pdf/dist/esm/Page/AnnotationLayer.css';
import 'react-pdf/dist/esm/Page/TextLayer.css';

pdfjs.GlobalWorkerOptions.workerSrc = new URL(
    'pdfjs-dist/build/pdf.worker.min.js',
    import.meta.url
).toString();

interface MultiPagePdfDisplayerProps {
    file?: string;
    parentWidth: number;
}

export const MultiPagePdfDisplayer = (props: MultiPagePdfDisplayerProps) => {
    const { file, parentWidth } = props;
    const [numPages, setNumPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const pageRefs = useRef<Array<HTMLDivElement | null>>([]);

    useEffect(() => {
        const loadNextPages = () => {
            setCurrentPage((prevPage) => {
                const nextPage = prevPage + 5;
                if (nextPage > numPages) {
                    return numPages;
                } else {
                    return nextPage;
                }
            });
        };
        if (currentPage <= numPages) {
            loadNextPages();
        }
    }, [currentPage, numPages]);

    const onLoadSuccess = ({ numPages }: { numPages: number }) => {
        setNumPages(numPages);
        console.log(numPages);
    };

    const goToPage = (pageNumber: number) => {
        setCurrentPage(pageNumber);
        const pageIndex = pageNumber - 1;
        if (pageIndex >= 0 && pageIndex < pageRefs.current.length) {
            const pageRef = pageRefs.current[pageIndex];
            if (pageRef) {
                pageRef.scrollIntoView({ behavior: 'smooth' });
            }
        }
    };

    const goToAnnotation = (pageNumber: number, x: number, y: number) => {
        const pageIndex = pageNumber - 1;
        if (pageIndex >= 0 && pageIndex < pageRefs.current.length) {
            const pageRef = pageRefs.current[pageIndex];
            if (pageRef) {
                const annotationContainer = pageRef.querySelector(
                    '.react-pdf__Page__annotations'
                );
                if (annotationContainer) {
                    const annotation = annotationContainer.querySelector(
                        `[data-annotation-id="${x},${y}"]`
                    );
                    if (annotation) {
                        annotation.scrollIntoView({ behavior: 'smooth' });
                        return;
                    }
                }
            }
        }
        console.log(`Annotation not found on page ${pageNumber}`);
    };
    const handleLinkClick = (
        event: React.MouseEvent<HTMLAnchorElement>,
        pageNumber: number,
        x: number,
        y: number
    ) => {
        event.preventDefault();
        goToPage(pageNumber);
        setTimeout(() => {
            goToAnnotation(pageNumber, x, y);
        }, 500); // delai pour chargement page
    };

    return (
        <>
            {file ? (
                <Document file={file} onLoadSuccess={onLoadSuccess}>
                    <a href="#" onClick={(event) => handleLinkClick(event, 16, 10, 1)}>
                        Cliquez ici pour aller à l'annotation avec l'identifiant de données : Page=16 / x=10 / y=32
                    </a>
                    {Array.from({ length: currentPage }, (_, index) => (
                        <Page
                            key={`page_${index + 1}`}
                            pageNumber={index + 1}
                            loading={<LoadingPdf parentWidth={parentWidth} />}
                            width={parentWidth}
                            className="pdfPage"
                            inputRef={(ref) => (pageRefs.current[index] = ref)}
                        />
                    ))}
                </Document>
            ) : (
                <LoadingPdf parentWidth={parentWidth} />
            )}
        </>
    );
};
