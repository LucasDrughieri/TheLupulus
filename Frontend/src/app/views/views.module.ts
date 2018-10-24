import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { LoginComponent } from "./login/login.component";
import { HomeComponent } from "./home/home.component";
import { RouterModule } from "@angular/router";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";
import { ContainerAddComponent } from "./container/add/container-add.component";
import { ICheckModule } from "../components/icheck/icheck.module";
import { ContainerFormComponent } from "./container/form/container-form.component";
import { BeerService } from "../core/services/beer.service";
import { ClientService } from "../core/services/client.service";
import { UserService } from "../core/services/user.service";
import { ContainerService } from "../core/services/container.service";
import { BaseService } from "../core/services/base.service";
import { DataTableService } from "../core/services/datatable.service";
import { ErrorHandlerService } from "../core/services/error-handler.service";
import { MessageService } from "../core/services/message.service";
import { ContainerEditComponent } from "./container/edit/container-edit.component";
import { ContainerListComponent } from "./container/list/container-list.component";
import { BeerAddComponent } from "./beer/add/beer-add.component";
import { BeerFormComponent } from "./beer/form/beer-form.component";
import { BeerEditComponent } from "./beer/edit/beer-edit.component";
import { BeerListComponent } from "./beer/list/beer-list.component";
import { UserAddComponent } from "./user/add/user-add.component";
import { UserEditComponent } from "./user/edit/user-edit.component";
import { UserFormComponent } from "./user/form/user-form.component";
import { UserListComponent } from "./user/list/user-list.component";
import { ClientAddComponent } from "./client/add/client-add.component";
import { ClientFormComponent } from "./client/form/client-form.component";
import { ClientListComponent } from "./client/list/client-list.component";
import { ClientEditComponent } from "./client/edit/client-edit.component";
import { StockComponent } from "./stock/stock.component";
import { OrderAddComponent } from "./order/add/order-add.component";
import { OrderFormComponent } from "./order/form/order-form.component";
import { OrderListComponent } from "./order/list/order-list.component";
import { OrderDetailsComponent } from "./order/details/order-details.component";
import { ForbiddenComponent } from "./errors/403/403.component";

@NgModule({
    declarations: [
        HomeComponent,
        LoginComponent,
        ContainerAddComponent,
        ContainerFormComponent,
        ContainerEditComponent,
        ContainerListComponent,
        BeerAddComponent,
        BeerFormComponent,
        BeerEditComponent,
        BeerListComponent,
        UserAddComponent,
        UserEditComponent,
        UserFormComponent,
        UserListComponent,
        ClientAddComponent,
        ClientFormComponent,
        ClientListComponent,
        ClientEditComponent,
        StockComponent,
        OrderAddComponent,
        OrderFormComponent,
        OrderListComponent,
        OrderDetailsComponent,
        ForbiddenComponent
    ],
    providers: [
        BaseService,
        ClientService,
        BeerService,
        UserService,
        ContainerService,
        DataTableService,
        ErrorHandlerService,
        MessageService
    ],
    imports: [
        BrowserModule,
        CommonModule,
        ICheckModule,
        FormsModule,
        RouterModule
    ]
})
export class ViewsModule { }