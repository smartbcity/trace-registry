import {Box, Stack, Tabs, Tab} from "@mui/material";
import pdf from "./pdd.pdf";
import { ThumbnailPdfDisplayer } from "components/src/ThumbnailPdfDisplayer";

export interface DocumentsThumbnailProps {
    files?: { name: string; file: any }[];
    isLoading?: boolean;
}

export const DocumentsThumbnail = (props: DocumentsThumbnailProps) => {
    const { files, isLoading } = props;

    return (
        <Stack
        display="flex"
        flexDirection="column"
        position="absolute"
        bgcolor="rgba(240, 237, 230)"

        zIndex={4}
        sx={{
            height: "100%",
        }}
        >
            {files &&
                <Tabs value="lola.pdf">
                    {files.map((document, indexDoc) => (
                    <Tab label={document.name} key={indexDoc}  />
                    ))}
                </Tabs>
            }
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
                />
            </Box>
        </Stack>
    );
};
