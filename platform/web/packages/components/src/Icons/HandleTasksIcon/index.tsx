import { ReactComponent } from './handleTasks.svg'
import { SvgIcon, SvgIconProps } from '@mui/material';
export const HandleTasksIcon = (props: SvgIconProps) => {
    const { ...other } = props
    return (
        <SvgIcon
            component={ReactComponent}
            inheritViewBox {...other}
        />
    );
};