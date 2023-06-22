import { IconButton, Stack} from "@mui/material";
import {SubtitlesOffRounded, SubtitlesRounded} from "@mui/icons-material";

export interface DocumentsBarProps {
    onOpen : () => void
    onClose : () => void
    isOpen: boolean
}

export const DocumentsBar = (props: DocumentsBarProps) => {
    const { onOpen, onClose, isOpen } = props;


    return (
            <Stack
                height="40px"
                direction="row"
                position="absolute"
                alignItems="center"
                justifyContent="space-between"
                sx={{
                    padding: (theme) => theme.spacing(2),
                    border: "1px solid #E0E0E0",
                    top: 20,
                    left: 20,
                    zIndex: 4,
                    "& .mui-utz8u3" : {
                        margin: "0"
                    }
                }}

            >
                <IconButton onClick={isOpen ? onClose : onOpen}>
                    {isOpen ? <SubtitlesOffRounded /> : <SubtitlesRounded />}
                </IconButton>
            </Stack>
    )
}