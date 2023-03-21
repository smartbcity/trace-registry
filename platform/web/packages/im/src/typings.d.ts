/**
 * Default CSS definition for typescript,
 * will be overridden with file-specific definitions by rollup
 */
declare module "*.css" {
  const content: { [className: string]: string };
  export default content;
}

interface SvgrComponent extends React.FunctionComponent<React.SVGProps<SVGSVGElement>> {}

declare module "*.svg" {
  const svgUrl: string;
  const svgComponent: SvgrComponent;
  export default svgUrl;
  export { svgComponent as ReactComponent };
}

declare module "*.png" {
  const value: any;
  export = value;
}

declare module "*.jpg" {
  const value: any;
  export = value;
}

declare module "*.pdf" {
  const value: any;
  export = value;
}

declare type Nullable<T> = T | undefined;
declare type Array<T> = T[];
declare namespace kotlin.collections {
  type List<T> = T[];
}
declare namespace kotlin {
  type Long = number;
}

import 'i18next';

declare module 'i18next' {
  interface CustomTypeOptions {
    returnNull: false;
  }
}