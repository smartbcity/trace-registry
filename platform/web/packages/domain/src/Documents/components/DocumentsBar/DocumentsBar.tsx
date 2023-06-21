import { IconButton, Stack} from "@mui/material";
import {SubtitlesOffRounded, SubtitlesRounded} from "@mui/icons-material";

export interface DocumentsBarProps {
    onOpen : () => void
    onClose : () => void
    isOpen: boolean
    pagination: React.ReactNode
}

export const DocumentsBar = (props: DocumentsBarProps) => {
    const { onOpen, onClose, isOpen, pagination } = props;


    return (
            <Stack
                height="40px"
                direction="row"
                alignItems="center"
                justifyContent="space-between"
                sx={{
                    padding: (theme) => theme.spacing(2),
                    border: "1px solid #E0E0E0",
                    "& .mui-utz8u3" : {
                        margin: "0"
                    }
                }}

            >
                <IconButton onClick={isOpen ? onClose : onOpen}>
                    {isOpen ? <SubtitlesOffRounded /> : <SubtitlesRounded />}
                </IconButton>
                {pagination}
            </Stack>
    )
}