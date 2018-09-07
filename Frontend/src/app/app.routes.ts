import { Routes } from "@angular/router";
import { LoginComponent } from "./views/login/login.component";
import { BlankLayoutComponent } from "./components/common/layouts/blankLayout.component";
import { HomeComponent } from "./views/home/home.component";
import { BasicLayoutComponent } from "./components/common/layouts/basicLayout.component";
import { ContainerAddComponent } from "./views/container/add/container-add.component";

export const ROUTES: Routes = [
    // Main redirect
    { path: '', redirectTo: 'Home', pathMatch: 'full' },

    {
        path: '', component: BlankLayoutComponent,
        children: [
            { path: 'Login', component: LoginComponent },
        ]
    },

    {
        path: '', component: BasicLayoutComponent,
        children: [
            { path: 'Home', component: HomeComponent },
            { path: 'Container/Add', component: ContainerAddComponent }
        ]
    },
    
    // Handle all other routes
    { path: '**', redirectTo: 'Home' }
]