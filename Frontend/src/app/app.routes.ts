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
import { AuthGuard } from "./guards/auth.guard";
import { ForbiddenComponent } from "./views/errors/403/403.component";

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
        path: '', component: BlankLayoutComponent,
        children: [
            { path: '403', component: ForbiddenComponent },
        ]
    },

    {
        path: '', component: BasicLayoutComponent,
        children: [
            { path: 'Home', component: HomeComponent, canActivate: [AuthGuard], data: { roleId: [1,2] } },
        ]
    },

    {
        path: '', component: BasicLayoutComponent,
        children: [
            { path: 'Stock', component: StockComponent, canActivate: [AuthGuard], data: { roleId: [1] }},
        ]
    },

    {
        path: 'Contenedores', component: BasicLayoutComponent,
        children: [
            { path: '', component: ContainerListComponent, canActivate: [AuthGuard], data: { roleId: [1] } },
            { path: 'Alta', component: ContainerAddComponent, canActivate: [AuthGuard], data: { roleId: [1] } },
            { path: ':id/Editar', component: ContainerEditComponent, canActivate: [AuthGuard], data: { roleId: [1] } }
        ]
    },

    {
        path: 'Cervezas', component: BasicLayoutComponent,
        children: [
            { path: '', component: BeerListComponent, canActivate: [AuthGuard], data: { roleId: [1] } },
            { path: 'Alta', component: BeerAddComponent, canActivate: [AuthGuard], data: { roleId: [1] } },
            { path: ':id/Editar', component: BeerEditComponent, canActivate: [AuthGuard], data: { roleId: [1] } }
        ]
    },

    {
        path: 'Usuarios', component: BasicLayoutComponent,
        children: [
            { path: '', component: UserListComponent, canActivate: [AuthGuard], data: { roleId: [1] } },
            { path: 'Alta', component: UserAddComponent, canActivate: [AuthGuard], data: { roleId: [1] } },
            { path: ':id/Editar', component: UserEditComponent, canActivate: [AuthGuard], data: { roleId: [1] } }
        ]
    },

    {
        path: 'Clientes', component: BasicLayoutComponent,
        children: [
            { path: '', component: ClientListComponent, canActivate: [AuthGuard], data: { roleId: [1] } },
            { path: 'Alta', component: ClientAddComponent, canActivate: [AuthGuard], data: { roleId: [1] } },
            { path: ':id/Editar', component: ClientEditComponent, canActivate: [AuthGuard], data: { roleId: [1] } }
        ]
    },

    {
        path: 'Pedidos', component: BasicLayoutComponent,
        children: [
            { path: '', component: OrderListComponent, canActivate: [AuthGuard], data: { roleId: [1,2] } },
            { path: 'Alta', component: OrderAddComponent, canActivate: [AuthGuard], data: { roleId: [2] } },
            { path: ':id', component: OrderDetailsComponent, canActivate: [AuthGuard], data: { roleId: [1,2] } }
        ]
    },
    
    // Handle all other routes
    { path: '**', redirectTo: 'Home' }
]