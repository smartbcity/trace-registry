
export interface Task {
    updateDate: number
    type: string
    identifier: string
    contact: string
    name: string
    status: string
    accountant: {
        givenName: string
        familyName: string
    }
}