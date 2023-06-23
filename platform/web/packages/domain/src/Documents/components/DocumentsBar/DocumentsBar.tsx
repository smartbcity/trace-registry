import {IconButton, Stack} from "@mui/material";
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
                position="absolute"
                zIndex={5}
            >
                <IconButton onClick={isOpen ? onClose : onOpen}>
                    {isOpen ? <SubtitlesOffRounded /> : <SubtitlesRounded />}
                </IconButton>
            </Stack>
    )
}