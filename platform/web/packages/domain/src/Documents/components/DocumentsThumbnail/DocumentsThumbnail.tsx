import {Stack, Tab, Tabs} from "@mui/material"
import {ThumbnailPdfDisplayer} from "components/src/ThumbnailPdfDisplayer"
import {useCallback, useEffect, useState} from "react"

export interface DocumentsThumbnailProps {
    files?: { name: string; file: any }[]
    isLoading?: boolean
    goToPage: (pageNumber: number, docName: string) => void
    isOpen: boolean
    visiblePages: {pageNumber: number, docName: string}[]

}

export const DocumentsThumbnail = (props: DocumentsThumbnailProps) => {
    const { files, isLoading, goToPage, isOpen, visiblePages } = props
    const [selectedFile, setSelectedFile] = useState<string | undefined>(
        files ? files[0]?.name : undefined)

    useEffect(() => {
        files && setSelectedFile(files[0]?.name)
    }, [files])


    const handleTabChange = (_event: React.SyntheticEvent, newFile: string) => {
        setSelectedFile(newFile)
    }

    const isVisiblePage = useCallback(
        (pageNum: number, fileName: string) => {
            return visiblePages.some(
                (page) => page.pageNumber === pageNum && page.docName === fileName
            )
        },
        [visiblePages]
    )


    return (
        <Stack
            display={isOpen ? "flex" : "none"}
            flexDirection="column"
            position="absolute"
            bgcolor="rgba(240, 237, 230)"
            zIndex={4}
            sx={{
                height: "100%",
                top: 0,
            }}
        >
            <Stack
                sx={{
                    overflowY: "auto",
                    padding: (theme) => theme.spacing(1.5)
                }}
                direction="row"
            >
                {files && (
                    <Tabs
                        value={selectedFile}
                        onChange={handleTabChange}
                        orientation="vertical"
                        sx={{
                            marginTop: (theme) => theme.spacing(6),
                            marginRight: (theme) => theme.spacing(1),
                        }}
                    >
                        {files.map((document, indexDoc) => (
                            <Tab label={document.name} value={document.name} key={indexDoc} wrapped />
                        ))}
                    </Tabs>
                )}
                {files?.map((document) => (
                    <ThumbnailPdfDisplayer
                        key={document.name}
                        file={document.file}
                        isLoading={isLoading ? isLoading : false}
                        isOpen={selectedFile === document.name}
                        goToPage={(number) => goToPage(number, selectedFile!)}
                        isVisiblePage={(pageNum : number) => isVisiblePage(pageNum, selectedFile!)}
                    />
                ))}

            </Stack>
        </Stack>
    )
}