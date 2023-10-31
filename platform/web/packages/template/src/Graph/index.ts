import dagre from 'dagre'
import { Edge, Node } from 'reactflow';

export type BasicNodeData = {
    hasSource?: boolean
    hasTarget?: boolean
    width: number
    height: number
}

export const autoLayoutNodes = (edges: Edge<any>[], nodes: Node<BasicNodeData>[], hasAnscestor: boolean = false) => {
    const dagreGraph = new dagre.graphlib.Graph();
    dagreGraph.setDefaultEdgeLabel(() => ({}));
    dagreGraph.setGraph({ rankdir: hasAnscestor ? "LR" : "TB" });
    console.log(nodes)
    nodes.forEach((node) => {
        dagreGraph.setNode(node.id, { width: node.data.width + 50, height: node.data.height + 20 });
    });
    edges.forEach((edge) => {
        dagreGraph.setEdge(edge.source, edge.target);
    });
    dagre.layout(dagreGraph);
    nodes.forEach((node) => {
        const nodeWithPosition = dagreGraph.node(node.id);

        // We are shifting the dagre node position (anchor=center center) to the top left
        // so it matches the React Flow node anchor point (top left).
        node.position = {
            x: nodeWithPosition.x - (node.data.width + 50) / 2,
            y: nodeWithPosition.y - (node.data.height + 20) / 2,
        };

        return node;
    });
}
