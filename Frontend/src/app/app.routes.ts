import { Routes } from "@angular/router";
import { LoginComponent } from "./views/login/login.component";
import { BlankLayoutComponent } from "./components/common/layouts/blankLayout.component";
import { HomeComponent } from "./views/home/home.component";
import { BasicLayoutComponent } from "./components/common/layouts/basicLayout.component";

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
            { path: 'Home', component: HomeComponent }
        ]
    },
    
    // Handle all other routes
    { path: '**', redirectTo: 'Home' }
]