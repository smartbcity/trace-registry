import {Paper, Skeleton, Stack, Typography} from '@mui/material'

export const LoadingPdf = () => {
    return (
        <Paper elevation={0}  sx={{ borderRadius: "0px", width: "100%", height: "100%",paddingBottom: "12px", boxSizing: "border-box" }}>
            <Stack spacing={8} alignItems="center" height={1302}>
                <Typography component="div" variant="h3" width="50%">
                    <Skeleton />
                </Typography>
                <Stack direction="row" spacing={2} width="90%">
                    <Skeleton variant="rectangular" width={200} height={80} />
                    <Stack flexGrow={1}>
                        <Skeleton />
                        <Skeleton width="30%" />
                    </Stack>
                </Stack>
                <Stack spacing={3} width="90%">
                    <Stack width="100%">
                        <Skeleton />
                        <Skeleton />
                        <Skeleton />
                    </Stack>
                    <Stack width="100%">
                        <Skeleton />
                        <Skeleton width="30%" />
                    </Stack>
                </Stack><Stack spacing={3} width="90%">
                    <Stack width="100%">
                        <Skeleton />
                        <Skeleton />
                        <Skeleton />
                    </Stack>
                    <Stack width="100%">
                        <Skeleton />
                        <Skeleton width="30%" />
                    </Stack>
                </Stack>
            </Stack>
        </Paper>
    )
}
