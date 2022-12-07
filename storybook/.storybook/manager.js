import { addons } from '@storybook/addons';
import {create} from "@storybook/theming";
import logo from "../public/logo.png";

addons.setConfig({
    theme: create({
        base: 'light',
        brandTitle: 'SmartB S2',
        brandUrl: 'https://docs.smartb.city/s2',
        brandImage: logo,
    }),
});
