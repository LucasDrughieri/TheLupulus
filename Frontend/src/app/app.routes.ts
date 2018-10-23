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
import { UserListComponent } from "./views/user/list/user-list.component";
import { UserAddComponent } from "./views/user/add/user-add.component";
import { UserEditComponent } from "./views/user/edit/user-edit.component";
import { ClientListComponent } from "./views/client/list/client-list.component";
import { ClientAddComponent } from "./views/client/add/client-add.component";
import { ClientEditComponent } from "./views/client/edit/client-edit.component";
import { StockComponent } from "./views/stock/stock.component";
import { OrderAddComponent } from "./views/order/add/order-add.component"
import { OrderListComponent } from "./views/order/list/order-list.component"
import { OrderDetailsComponent } from "./views/order/details/order-details.component"

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
        path: '', component: BasicLayoutComponent,
        children: [
            { path: 'Stock', component: StockComponent },
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

    {
        path: 'Usuarios', component: BasicLayoutComponent,
        children: [
            { path: '', component: UserListComponent },
            { path: 'Alta', component: UserAddComponent },
            { path: ':id/Editar', component: UserEditComponent }
        ]
    },

    {
        path: 'Clientes', component: BasicLayoutComponent,
        children: [
            { path: '', component: ClientListComponent },
            { path: 'Alta', component: ClientAddComponent },
            { path: ':id/Editar', component: ClientEditComponent }
        ]
    },

    {
        path: 'Pedidos', component: BasicLayoutComponent,
        children: [
            { path: '', component: OrderListComponent },
            { path: 'Alta', component: OrderAddComponent },
            { path: ':id', component: OrderDetailsComponent }
        ]
    },
    
    // Handle all other routes
    { path: '**', redirectTo: 'Home' }
]