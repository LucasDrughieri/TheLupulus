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

    public roleId: number = 1;

    constructor(private orderService: OrderService,
                private messageService: MessageService,
                private router: Router,
                private activateRoute: ActivatedRoute) { }

    ngOnInit(): void {
        var user = JSON.parse(localStorage.getItem('user'));
        this.roleId = user.userId.role;

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

    transitionTo(id, estado) {
        this.messageService.showLoading();

        this.subscriptions.push(
            this.orderService.patch(id, { estado: estado }).subscribe(response => {
                this.messageService.closeLoading();
                this.router.navigate(["/Pedidos"]);
            }, 
            error => {
                this.messageService.closeLoading()
                this.messageService.showError('Ocurrió un error al intentar guardar los cambios del Pedido. Reintente mas tarde.');
            })
        );
    }

    trackPayment(id, newValue) {
        this.messageService.showLoading();

        this.subscriptions.push(
            this.orderService.patch(id, { pagado: newValue }).subscribe(response => {
                this.messageService.closeLoading();
                this.router.navigate(["/Pedidos"]);
            }, 
            error => {
                this.messageService.closeLoading()
                this.messageService.showError('Ocurrió un error al intentar guardar los cambios del Pedido. Reintente mas tarde.');
            })
        );
    }
    
    private estadoVisible(estado: number): string {
        switch(estado) {
            case 0: return "Finalizado"
            case 1: return "Pendiente"
            case 2: return "En preparación"
            case 3: return "Cancelado"
        }
    }

    private transicionesPosibles(estado: number): number[] {
        switch(estado) {
            case 0: return []
            case 1: return [0, 2, 3]
            case 2: return [0, 3]
            case 3: return []
        }
    }
}