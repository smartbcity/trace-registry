import { Box } from '@mui/material'
import { ReactFlow, Background, useEdgesState, useNodesState, Controls, useReactFlow, Edge } from "reactflow"
import 'reactflow/dist/style.css';
import {
    useEffect,
} from 'react';
import { EdgesNode } from './EdgesNode';

const nodeTypes = {
    entry: EdgesNode,
    exit: EdgesNode
};


export const ProtocolBuilder = () => {
    const reactFlowInstance = useReactFlow();



    const [nodes, setNodes, onNodesChange] = useNodesState([]);
    const [edges, setEdges, onEdgesChange] = useEdgesState([]);

    useEffect(() => {
        setNodes(nodes)
        setEdges(edges)
        setTimeout(() => {
            reactFlowInstance.fitView({ nodes: nodes.filter((node) => !node.data.isAncestor) })
        }, 300);
    }, [])


    return (
        <Box
            sx={{
                flexGrow: 1,
                flexBasis: 0,
                height: "100%",
                "& .react-flow__node-Activity": {
                    transition: "0.3s"
                }
            }}
        >
            <ReactFlow
                nodes={nodes}
                edges={edges}
                onNodesChange={onNodesChange}
                onEdgesChange={onEdgesChange}
                nodeTypes={nodeTypes}
                fitView
                attributionPosition="bottom-right"
                style={{
                    background: "#F0EDE6"
                }}
            >
                <Background color={"#9E9E9E"} />
                <Controls />
            </ReactFlow>
        </Box>
    )
}

