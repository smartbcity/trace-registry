import {useMemo, useCallback} from "react"
import {Roles} from "./roles"
import {insertObjectIdsInsideRoutes, RecordRouteCamelCase} from "./types"
const strictRoutesAuthorizations = {
    "": "open",
    "projects": "open",
    "projects/:projectId/view/:tab?/*": "open",
    "projects/:projectId/transactions/:transactionId/view": "open",
    "projects/create/:step": "open",
    "transactions/:transactionId": "open",
    "transactions": "open",
    "catalogues": "open",
    "catalogues/*": "open",
} as const

export type Routes = keyof typeof strictRoutesAuthorizations

export type RoutesRoles = Roles | "memberOf" | "currentUser"
export type RoutesAuthorizations = { [route: string]: RoutesRoles[] | RoutesRoles[][] | "open" }
//@ts-ignore
export const routesAuthorizations: RoutesAuthorizations = { ...strictRoutesAuthorizations }


type RoutesDefinitions = RecordRouteCamelCase<Routes, (...objectIds: string[]) => string>

//@ts-ignore
let routesDefinitions: RoutesDefinitions = {}

for (let route in strictRoutesAuthorizations) {
    const camelCasedRoute = route
    .replaceAll("?", "")
    .replaceAll("*", "All")
    .replace(/[^a-zA-Z0-9]+(.)/g, (_, chr) => chr.toUpperCase())
    //@ts-ignore
    routesDefinitions[camelCasedRoute] = (...objectIds: string[]) => "/" + insertObjectIdsInsideRoutes(route, ...objectIds)
}

export const useRoutesDefinition = () => {

    const cataloguesAll = useCallback(
      (tab?: string, ...objectIds: string[]) => {
        const ends =  `/${tab ? tab : ""}`
       return  "/" + insertObjectIdsInsideRoutes("catalogues/*", ...objectIds) + ends
      },
      [],
    )
    

    return useMemo(() => ({
        ...routesDefinitions,
        cataloguesAll
    }), [cataloguesAll])
}
