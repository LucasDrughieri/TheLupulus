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
        BeerListComponent
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