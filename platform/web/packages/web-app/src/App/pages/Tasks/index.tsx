import { PageRoute } from "App/routes";
import {Dashboard} from "./Dashboard/Dashboard"

export const tasksPages: PageRoute[] = [
    {
      path: "dashboard",
      element: <Dashboard />
    },
  ]
  