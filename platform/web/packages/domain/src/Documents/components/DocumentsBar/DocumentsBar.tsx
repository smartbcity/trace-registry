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
                alignItems="center"
                justifyContent="space-between"
                position="absolute"
                sx={{
                    padding: (theme) => theme.spacing(2),
                    zIndex: 5,
                    top: 0,
                    left: 0
                }}
            >
                <IconButton onClick={isOpen ? onClose : onOpen}>
                    {isOpen ? <SubtitlesOffRounded /> : <SubtitlesRounded />}
                </IconButton>
            </Stack>
    )
}