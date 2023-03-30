import { Box, Divider, Skeleton, Stack, Typography } from '@mui/material'

import {FormComposable, FormComposableField, useFormComposable} from "@smartb/g2";
import {Activity, ActivityStep} from "../../model";

export interface ActivitiesSummaryProps {
    activity?: Activity
    steps?: ActivityStep[]
    isLoading?: boolean
}

export const ActivitiesSummary = (props: ActivitiesSummaryProps) => {
  const { isLoading, steps, activity } = props

  const fields: FormComposableField[] = steps?.map(it => ({
      name: it.identifier,
      label: it.name,
      params: {
        orientation: "horizontal"
      },
      type: "select",
    }
  )) ?? []
  const values = steps?.reduce((a: any, v: ActivityStep) => ({
      ...a,
      [v.identifier]: v.value
    }
  ), {})

  const formState = useFormComposable({
    onSubmit: () => { },
    isLoading: isLoading,
    formikConfig: {
      initialValues: values
    }
  })

  return (
        <Stack
            sx={{
                height: "100%",
                width: "550px",
                padding: "24px 32px",
            }}
            gap={2}
        >
            <Box>
                <Typography variant="h5" >{activity?.name}</Typography>
                <Divider sx={{ marginTop: "8px" }} />
            </Box>
            {
                isLoading ?
                    <Skeleton
                        sx={{
                            width: '100%',
                            height: '200px',
                            transform: 'none'
                        }}
                        animation='wave'
                        variant='text'
                    />
                    :
                    <>
                      <Typography color="text.secondary" >{activity?.description}</Typography>
                      <FormComposable fields={fields} formState={formState} />
                    </>
            }
        </Stack>
    )
}
