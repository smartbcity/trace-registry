import {  FormComposable, FormComposableField, useFormComposable } from '@smartb/g2'
import { useEffect, useMemo } from 'react'
import { requiredString, User } from '@smartb/g2-i2-v2'

export interface UserDomainDetailsProps {
    readOnly?: boolean
    onSubmit: (values: Partial<User>) => void
    registerSubmitter: (submitForm: () => Promise<any>, validateForm: (values?: any) => Promise<any>) => () => void
    isLoading?: boolean
}

export const UserDomainDetails = (props: UserDomainDetailsProps) => {
    const { readOnly, onSubmit, registerSubmitter, isLoading } = props

    const formState = useFormComposable({
        onSubmit: onSubmit,
    })

    useEffect(registerSubmitter(formState.submitForm, formState.validateForm), [formState.submitForm, formState.validateForm])

    const fields = useMemo((): FormComposableField[] => [{
        name: "example",
        type: "textField",
        label: "This is an example",
        validator: (value) => requiredString("this field is required", value)
    }], [])

    if (readOnly && !isLoading) return <></>
    return (
        <FormComposable fields={fields} formState={formState} readOnly={readOnly} isLoading={isLoading} />
    )
}
