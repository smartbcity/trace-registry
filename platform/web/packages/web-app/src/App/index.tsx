// import {AppLayout, useExtendedAuth} from "components";
// import {useMenu, useUserMenu} from "./menu";
// import logo from '../assets/smartb.png'
// import { useMemo } from "react";
// import { useTranslation } from "react-i18next";
import { CssBaseline } from "@mui/material";
import { Outlet } from "react-router-dom";

export const App = () => {
  // const {t} = useTranslation()
  // const menu = useMenu(t)
  // const {service, keycloak} = useExtendedAuth()
  // const user = useMemo(() => service.getUser(), [service.getUser])
  // const {loggedMenu, notLoggedMenu} = useUserMenu(keycloak.logout, keycloak.login, t)

  return (
    <>
    <CssBaseline />
      <main
        style={{
          flexGrow: 1,
          height: '100vh',
          overflow: 'auto'
        }}
      >
        <Outlet />
      </main>
    </>
  )

//   return (
//       <AppLayout 
//       menu={menu} 
//       logo={logo}
//       userMenuProps= {{
//         currentUser: user ? {
//           givenName: user.firstName ?? "",
//           familyName: user.lastName ?? "",
//           role: "Admin"
//         }: undefined,
//         loggedMenu,
//         notLoggedMenu
//       }}
//       >
//         <Outlet />
//       </AppLayout>
//   );
};
