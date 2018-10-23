import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription, Observable } from 'rxjs';
import { shareReplay } from 'rxjs/operators';
import { MessageService } from '../../../core/services/message.service';
import { DataTableService } from '../../../core/services/datatable.service';
import { Order, Item } from '../../../models/order';
import { OrderService } from '../../../core/services/order.service';

@Component({    
    selector: 'order-list',
    templateUrl: './order-list.component.html'    
})
export class OrderListComponent implements OnInit {
    public orders$: Observable<Order[]> = new Observable();

    private subscriptions: any[] = new Array();

    constructor(
        private orderService: OrderService,
        private router: Router,
        private messageService: MessageService,
        private dataTableService: DataTableService) { }

    ngOnInit(): void {
        this.orders$ = this.orderService.getAll().map(v => v.data).pipe(shareReplay(1));

        this.messageService.showLoading();
        this.subscriptions.push(
            this.orders$.subscribe(response => this.messageService.closeLoading(), error => {
                this.messageService.closeLoading()
                this.messageService.showError('Ocurrió un error al intentar cargar la información de Pedidos. Reintente mas tarde.');
            })
        );

        this.initializeDataTable();
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach(v => v.unsubscribe())
    }

    private initializeDataTable() {
        const selector = '#order-table';

        this.dataTableService.destroy(selector);
        this.dataTableService.initialize(selector);
    }

    goToDetail(id){
        this.router.navigate([`/Pedidos/${id}`]);
    }
}