import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { LoginComponent } from "./login/login.component";
import { HomeComponent } from "./home/home.component";
import { RouterModule } from "@angular/router";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";
import { ContainerAddComponent } from "./container/add/container-add.component";
import { ICheckModule } from "../components/icheck/icheck.module";

@NgModule({
    declarations: [
        HomeComponent,
        LoginComponent,
        ContainerAddComponent
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