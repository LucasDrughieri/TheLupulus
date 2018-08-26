import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';
import { BeerService } from './services/beer.service';
import { Configuration } from './configuration';
import { DataTableService } from './services/datatable.service';
import { MessageService } from './services/message.service';
import { BaseService } from './services/base.service';

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        HttpClientModule,
        ToastrModule.forRoot()
    ],
    exports: [],
    providers: [
        Configuration,
        DataTableService,
        MessageService,
        BaseService,
        BeerService
    ],
})
export class CoreModule { }