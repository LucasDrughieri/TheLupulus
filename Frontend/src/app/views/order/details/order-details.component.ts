import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from '../../../core/services/message.service';
import { Subscription, Observable } from 'rxjs';
import { shareReplay, last } from 'rxjs/operators';
import { OrderService } from '../../../core/services/order.service';
import { Order, Item } from '../../../models/order';

@Component({    
    selector: 'order-details',
    templateUrl: './order-details.component.html'    
})
export class OrderDetailsComponent implements OnInit {
    private subscriptions: any[] = new Array();
    private order$: Observable<Order>;

    constructor(private orderService: OrderService,
                private messageService: MessageService,
                private router: Router,
                private activateRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const routeParams = this.activateRoute.snapshot.params;

        if (!routeParams.id) {
            this.messageService.showError("Pedido no encontrado");
            this.router.navigate(["/Pedidos"]);
            return;
        }

        const orderId = routeParams.id;
        this.getModel(orderId);
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach(v => v.unsubscribe())
    }

    getModel(id){
        this.messageService.showLoading();

        this.order$ = this.orderService.get(id).map(v => v.data).pipe(shareReplay(1));

        this.subscriptions.push(
            this.order$.subscribe(response => this.messageService.closeLoading(), error => {
                this.messageService.closeLoading()
                this.messageService.showError('Ocurrió un error al intentar cargar el detalle del Pedido. Reintente mas tarde.');
            })
        );
    }

    transitionTo(estado) {
        this.messageService.showLoading();

        this.subscriptions.push(
            this.orderService.patch({ status: estado }).subscribe(response => {
                this.messageService.closeLoading();
                this.router.navigate(["/Pedidos"]);
            }, 
            error => {
                this.messageService.closeLoading()
                this.messageService.showError('Ocurrió un error al intentar guardar los cambios del Pedido. Reintente mas tarde.');
            })
        );
    }

    trackPayment() {
        this.messageService.showLoading();

        this.subscriptions.push(
            this.orderService.patch({ pagado: true }).subscribe(response => {
                this.messageService.closeLoading();
                this.router.navigate(["/Pedidos"]);
            }, 
            error => {
                this.messageService.closeLoading()
                this.messageService.showError('Ocurrió un error al intentar guardar los cambios del Pedido. Reintente mas tarde.');
            })
        );
    }
}