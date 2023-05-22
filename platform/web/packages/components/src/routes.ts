import {useMemo} from "react"
import {Roles} from "./roles"
import {insertObjectIdsInsideRoutes, RecordRouteCamelCase} from "./types"

const IMRoutesAuthorizations = {
    "organizations": "open",
    "organizations/add": "open",
    "organizations/:organizationId/view": "open",
    "organizations/:organizationId/edit": "open",
    "myOrganization": "open",
    "myOrganization/edit": ["admin"],
    "users": "open",
    "users/add": [["memberOf", "admin"]],
    "users/:userId/view": "open",
    "users/:userId/edit": [["memberOf", "admin"]],
    "myProfil": "open",
    "myProfil/edit": "open",
} as const

const strictRoutesAuthorizations = {
    "": "open",
    "projects": "open",
    "projects/:projectId/view/:tab?/*": "open",
    "projects/:projectId/transactions/:transactionId/view": "open",
    ...IMRoutesAuthorizations,
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

export const useRoutesDefinition = (): RoutesDefinitions => {
    // const { service } = useExtendedAuth()

    // const user = useMemo(() => service.getUser(), [service.getUser])

    // const getOrganizationsView = useCallback(
    //     (organizationId?: string) => {
    //         return organizationId === user?.memberOf ? "/myOrganization" : `/organizations/${organizationId}/view`
    //     },
    //     [user?.memberOf],
    // )

    // const getOrganizationsEdit = useCallback(
    //     (organizationId?: string) => {
    //         return organizationId === user?.memberOf ? "/myOrganization/edit" : `/organizations/${organizationId}/edit`
    //     },
    //     [user?.memberOf],
    // )

    // const getUsersView = useCallback(
    //     (userId?: string) => {
    //         return userId === user?.id ? "/myProfil" : `/users/${userId}/view`
    //     },
    //     [user?.id],
    // )

    // const getUsersAdd = useCallback(
    //     (organizationId?: string) => {
    //         return organizationId ? `/users/add?organizationId=${organizationId}` : "/users/add"
    //     },
    //     [],
    // )

    // const getUsersEdit = useCallback(
    //     (userId?: string) => {
    //         return userId === user?.id ? "/myProfil/edit" : `/users/${userId}/edit`
    //     },
    //     [user?.id],
    // )

    return useMemo(() => ({
        ...routesDefinitions,
        // organizationsOrganizationIdView: getOrganizationsView,
        // organizationsOrganizationIdEdit: getOrganizationsEdit,
        // usersUserIdView: getUsersView,
        // usersAdd: getUsersAdd,
        // usersUserIdEdit: getUsersEdit
    }), [/* user */])
}
