import { Routes } from "@angular/router";
import { LoginComponent } from "./views/login/login.component";
import { BlankLayoutComponent } from "./components/common/layouts/blankLayout.component";
import { HomeComponent } from "./views/home/home.component";
import { BasicLayoutComponent } from "./components/common/layouts/basicLayout.component";
import { ContainerAddComponent } from "./views/container/add/container-add.component";
import { ContainerEditComponent } from "./views/container/edit/container-edit.component";
import { ContainerListComponent } from "./views/container/list/container-list.component";
import { BeerListComponent } from "./views/beer/list/beer-list.component";
import { BeerAddComponent } from "./views/beer/add/beer-add.component";
import { BeerEditComponent } from "./views/beer/edit/beer-edit.component";

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
        ]
    },

    {
        path: 'Contenedores', component: BasicLayoutComponent,
        children: [
            { path: '', component: ContainerListComponent },
            { path: 'Alta', component: ContainerAddComponent },
            { path: ':id/Editar', component: ContainerEditComponent }
        ]
    },

    {
        path: 'Cervezas', component: BasicLayoutComponent,
        children: [
            { path: '', component: BeerListComponent },
            { path: 'Alta', component: BeerAddComponent },
            { path: ':id/Editar', component: BeerEditComponent }
        ]
    },
    
    // Handle all other routes
    { path: '**', redirectTo: 'Home' }
]