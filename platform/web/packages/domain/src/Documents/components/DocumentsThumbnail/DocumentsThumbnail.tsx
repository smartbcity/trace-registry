import { Box, Stack, Tabs, Tab } from "@mui/material"
import { ThumbnailPdfDisplayer } from "components/src/ThumbnailPdfDisplayer"
import { useEffect, useState } from "react"

export interface DocumentsThumbnailProps {
    files?: { name: string; file: any }[]
    isLoading?: boolean
    goToPage: (pageNumber: number, docName: string) => void
    isOpen: boolean
}

export const DocumentsThumbnail = (props: DocumentsThumbnailProps) => {
    const { files, isLoading, goToPage, isOpen } = props
    const [selectedFile, setSelectedFile] = useState<string | undefined>(
        files ? files[0]?.name : undefined)

    useEffect(() => {
        files && setSelectedFile(files[0]?.name)
    }, [files])


    const handleTabChange = (_event: React.SyntheticEvent, newFile: string) => {
        setSelectedFile(newFile)
    }

    return (
        <Stack
            display={isOpen ? "flex" : "none"}
            flexDirection="column"
            position="absolute"
            bgcolor="rgba(240, 237, 230)"
            alignItems="center"
            zIndex={4}
            sx={{
                height: "100%",
                top: 0,
                paddingTop: (theme) => theme.spacing(7),

            }}
        >
            {files && (
                <Tabs value={selectedFile} onChange={handleTabChange} >
                    {files.map((document, indexDoc) => (
                        <Tab label={document.name} value={document.name} key={indexDoc} />
                    ))}
                </Tabs>
            )}
            <Box
                sx={{
                    overflowY: "auto",
                    padding: (theme) => theme.spacing(1.5),
                    "& .thumbnailPdfPage .react-pdf__Thumbnail__page__canvas": {
                        marginBottom: (theme) => theme.spacing(2),
                    },
                }}
            >
                {files?.map((document) => (
                    <ThumbnailPdfDisplayer
                        key={document.name}
                        file={document.file}
                        isLoading={isLoading ? isLoading : false}
                        isOpen={selectedFile === document.name}
                        goToPage={(number) => goToPage(number, selectedFile!)}
                    />
                ))}

            </Box>
        </Stack>
    )
}