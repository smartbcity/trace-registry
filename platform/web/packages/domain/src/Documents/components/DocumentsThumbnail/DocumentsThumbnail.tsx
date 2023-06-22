import {Box, Stack, Tabs, Tab} from "@mui/material"
import { ThumbnailPdfDisplayer } from "components/src/ThumbnailPdfDisplayer"
import { useEffect, useMemo, useState } from "react"

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

    const file = useMemo(() => files?.find((file) => file.name === selectedFile)?.file, [selectedFile])

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
                <ThumbnailPdfDisplayer
                    file={file}
                    isLoading={isLoading ? isLoading : false}
                    goToPage={(number) => goToPage(number, selectedFile!)}
                />
            </Box>
        </Stack>
    )
}
