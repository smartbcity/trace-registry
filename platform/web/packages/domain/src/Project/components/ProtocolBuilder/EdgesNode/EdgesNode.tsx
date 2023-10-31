import {  IconButton } from '@mui/material'
import { NodeProps } from "reactflow"
import { useCallback, } from 'react'
import { AddNodeIconData, AddNodeIconTypes } from '../utils'


export const EdgesNode = (props: NodeProps<AddNodeIconData>) => {
    const { data, id, type } = props

    const onClick = useCallback(
        () => {
            data.onClick(id, type as AddNodeIconTypes)
        },
        [data.onClick, id, type],
    )

    return (
        <IconButton
        sx={{
            width: data.width,
            height: data.height,
            bgcolor: "#005B47",
            borderRadius: "12px",
            color: "white"
        }}
        onClick={onClick}
        >

        </IconButton>
    )
}
