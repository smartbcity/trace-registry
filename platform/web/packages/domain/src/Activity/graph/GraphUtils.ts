import { Node, Position, Edge } from "reactflow"
import { Activity, ActivityId } from "../model";
import dagre from 'dagre'


type SubActivitySelection = (selected: ActivityId, parent?: ActivityId,) => void

export type ActivityData = {
    current: Activity,
    select: SubActivitySelection,
    hasSource?: boolean
    hasTarget?: boolean
    isAncestor?: boolean
    width: number
    height: number
    onChangeSize: (width: number, height: number) => void
}

export type ActivityDataNode = Node<ActivityData>

interface NodesAnEdgesOfActivities {
    nodes: Node<ActivityData>[]
    edges: Edge[]
}

export const getActivitiesOfDinasty = (activities: Activity[], dinasty?: string[]) => {
    if (!dinasty || dinasty.length === 0) return {
        ancestors: undefined,
        activities,
        fixedDynasty: undefined
    }
    let ancestors: Activity[] = []
    for (let i = 0; i < dinasty.length; i++) {
        const activity = activities.find((el) => el.identifier === dinasty[i])
        if (!activity) {
            return {
                ancestors,
                activities: ancestors[ancestors.length - 1]?.hasRequirement ?? activities,
                fixedDynasty: dinasty.slice(0, i)
            }
        }
        ancestors.push(activity)
    }
    return {
        ancestors,
        activities: ancestors[ancestors.length - 1]?.hasRequirement ?? activities,
        fixedDynasty: undefined
    }
}

export const autoLayoutNodes = (edges: Edge<any>[], nodes: Node<ActivityData>[], hasAnscestor: boolean = false) => {
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

export const getNodesAnEdgesOfActivities = (
    activities: Activity[],
    ancestors: Activity[] | undefined,
    select: SubActivitySelection,
    onChangeSize: (edges: Edge<any>[], nodeId: string, width: number, height: number
    ) => void): NodesAnEdgesOfActivities => {
    const edges: Edge<any>[] = toEdges(activities, ancestors)
    const nodes: Node<ActivityData>[] = toNodes(edges, activities, ancestors ? ancestors[ancestors?.length - 1] : undefined, select, onChangeSize)
    if (ancestors && ancestors.length > 0) {
        ancestors.forEach((ancestor, index) => {
            nodes.push({
                id: ancestor.identifier,
                data: {
                    current: ancestor,
                    select: select,
                    hasSource: true,
                    hasTarget: index !== ancestors.length - 1,
                    isAncestor: true,
                    width: 250,
                    height: 80,
                    onChangeSize: (width, number) => onChangeSize(edges, ancestor.identifier, width, number)
                },
                position: {
                    x: 0,
                    y: 0
                },
                type: "Activity",
                sourcePosition: Position.Right,
                targetPosition: Position.Left,
                selectable: false,
            })
        })

    }
    console.log(ancestors)
    autoLayoutNodes(edges, nodes, !!ancestors);
    return {
        nodes,
        edges
    }
}

export const toNodes = (
    edges: Edge[],
    activities: Activity[],
    ancestor: Activity | undefined,
    select: SubActivitySelection,
    onChangeSize: (edges: Edge<any>[], nodeId: string, width: number, height: number) => void,
    level: number = 0,
): Node<ActivityData>[] => {
    const nodes: Node<ActivityData>[] = []
    activities.forEach((obj) => {
        nodes.push({
            id: obj.identifier,
            data: {
                current: obj,
                select: select,
                hasSource: edges.some((edge) => edge.source === obj.identifier),
                hasTarget: edges.some((edge) => edge.target === obj.identifier),
                width: 250,
                height: 80,
                onChangeSize: (width, number) => onChangeSize(edges, obj.identifier, width, number)
            },
            position: {
                x: 0,
                y: 0
            },
            type: obj.type || "Activity",
            sourcePosition: !!obj.hasQualifiedRelation && obj.hasQualifiedRelation.length > 0 ? Position.Bottom : undefined,
            targetPosition: level !== 0 ? Position.Top : (ancestor ? Position.Left : undefined),
        })
    })
    return nodes
}

export const toEdges = (activities: Activity[], ancestors?: Activity[]): Edge[] => {
    const edges: Edge[] = []
    const ancestor = ancestors ? ancestors[ancestors?.length - 1] : undefined
    const currentActivities = ancestor ? ancestor.hasRequirement : activities
    currentActivities.forEach((activity) => {
        if (ancestor) {
            edges.push({
                id: `${ancestor.identifier}-to-${activity.identifier}`,
                source: ancestor.identifier,
                target: activity.identifier,
            })
        }
        activity.hasQualifiedRelation.forEach((identifier) => {
            edges.push({
                id: `${activity.identifier}-to-${identifier}`,
                source: activity.identifier,
                target: identifier,
            })
        })
    })
    if (ancestors && ancestors.length > 1) {
        for (let i = ancestors.length - 1; i > 0; i--) {
            edges.push({
                id: `${ancestors[i].identifier}-to-${ancestors[i + 1].identifier}`,
                source: ancestors[i].identifier,
                target: ancestors[i + 1].identifier,
            })
        }
    }
    return edges
}

export const isDynastyInGraph = (nodes: Node<ActivityData>[], activityDinasty: string[] = []) => {
    for (let i = 0; i < activityDinasty.length; i++) {
        const node = nodes.find((el) => el.data.current.identifier === activityDinasty[i])
        if (!node || !node.data.isAncestor) return false
    }
    return true
}
