import {Box, Stack, Tabs, Tab} from "@mui/material"
import pdf from "./pdd.pdf"
import { ThumbnailPdfDisplayer } from "components/src/ThumbnailPdfDisplayer"
import { useState } from "react"

export interface DocumentsThumbnailProps {
    files?: { name: string; file: any }[]
    isLoading?: boolean
    numPages: number
    goToPage: (pageNumber: number) => void
}

export const DocumentsThumbnail = (props: DocumentsThumbnailProps) => {
    const { files, isLoading, numPages, goToPage } = props
    const [selectedFile, setSelectedFile] = useState<string | undefined>(
        files && files.length > 0 ? files[0].name : undefined)

    const handleTabChange = (_event: React.SyntheticEvent, newFile: string) => {
        setSelectedFile(newFile)
    }

    return (
        <Stack
        display="flex"
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
                <Tabs value={selectedFile} onChange={handleTabChange}>
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
                    files={[{ name: "lala.pdf", file: pdf },{name: "lola.pdf", file: pdf}]}
                    isLoading={isLoading ? isLoading : false}
                    numPages={numPages}
                    goToPage={goToPage}
                    selectedFile={selectedFile}
                />
            </Box>
        </Stack>
    )
}
