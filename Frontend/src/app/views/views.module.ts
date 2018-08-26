import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { LoginComponent } from "./login/login.component";
import { HomeComponent } from "./home/home.component";
import { RouterModule } from "@angular/router";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";

@NgModule({
    declarations: [
        HomeComponent,
        LoginComponent,
    ],
    imports: [
        BrowserModule,
        CommonModule,
        FormsModule,
        RouterModule
    ]
})
export class ViewsModule { }