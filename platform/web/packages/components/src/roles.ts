import { Option } from "@smartb/g2"
import { TFunction } from "i18next"

export const userRoles = [
    "super_admin",
    "admin",
    "user",
] as const

export type UserRoles = typeof userRoles[number]

const mutableUserRoles: UserRoles[] = [...userRoles]

export const userRolesColors: { [roles in UserRoles]: string } = {
    "super_admin": "#d1b00a",
    "admin": "#E56643",
    "user": "#3041DC"
}

export const getUserRolesOptions = (t: TFunction, withSuperAdmin: boolean = false) => {

    const roles: Option[] = []
    for (let key in mutableUserRoles) {
        const role = mutableUserRoles[key]
        if (withSuperAdmin || role !== "super_admin") {
            roles.push({
                key: role,
                label: t(`roles.${role}`) as string,
                color: userRolesColors[role]
            })
        }
    }
    return roles
}

export const orgRoles = [
    "support",
    "fub",
] as const

export type OrgRoles = typeof orgRoles[number]

const mutableOrgRoles: OrgRoles[] = [...orgRoles]

export const orgRolesColors: { [roles in OrgRoles]: string } = {
    "support": "#2EAE62",
    "fub": "#27848f"
}

export const getOrgRolesOptions = (t: TFunction) => {
    return mutableOrgRoles.map(role => ({
        key: role,
        label: t("organizationRoles." + role),
        color: orgRolesColors[role]
    })
    )
}

export const userEffectiveRoles = [...userRoles, ...orgRoles]

export type Roles = typeof userEffectiveRoles[number]