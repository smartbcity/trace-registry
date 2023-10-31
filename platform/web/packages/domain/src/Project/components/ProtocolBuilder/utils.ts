import { Node } from "reactflow"
import { BasicNodeData } from "template"

export type AddNodeIconTypes = "entry" | "exit" | "inter"

export type AddNodeIconData = {
    onClick: (id: string, type: AddNodeIconTypes) => void
} & BasicNodeData

export type AddNodeIconNode = Node<AddNodeIconData>