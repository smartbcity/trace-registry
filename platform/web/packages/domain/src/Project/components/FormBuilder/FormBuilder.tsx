import { TripettoBuilder } from "@tripetto/builder/react";
import "@tripetto/block-checkbox";
import "@tripetto/block-date";
import "@tripetto/block-text";
import { Box } from "@mui/material"

export const FormBuilder = () => {
    return (
        <Box
            sx={{
                height: "calc(100vh - 200px)",
                "& .tripettoBuilder": {
                    position: "relative !important",
                    height: "100%"
                }
            }}
        >
            <TripettoBuilder

                onSave={(definition) => {
                    console.log(definition)
                }}
                className="tripettoBuilder"
            />
        </Box>
    )
}
