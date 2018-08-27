import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';
import { BeerService } from './services/beer.service';
import { Configuration } from './configuration';
import { DataTableService } from './services/datatable.service';
import { MessageService } from './services/message.service';
import { BaseService } from './services/base.service';
import { ErrorHandlerService } from './services/error-handler.service';
import { HttpServiceInterceptor } from './interceptors/http-service.interceptor';

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
        BeerService,
        ErrorHandlerService,
        { provide: HTTP_INTERCEPTORS, useClass: HttpServiceInterceptor, multi: true },
    ],
})
export class CoreModule { }