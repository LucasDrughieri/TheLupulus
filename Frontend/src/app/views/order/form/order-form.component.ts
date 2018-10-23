import { Component, OnInit } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { shareReplay } from 'rxjs/operators';
import { forkJoin } from 'rxjs/index';
import { Order, Item } from '../../../models/order';
import { ContainerService } from '../../../core/services/container.service';
import { BeerService } from '../../../core/services/beer.service';
import { MessageService } from '../../../core/services/message.service';

@Component({    
    selector: 'order-form',
    templateUrl: './order-form.component.html'    
})
export class OrderFormComponent implements OnInit {
    public model: Order;

    private containers$: Observable<any[]>;
    private beers$: Observable<any[]>;

    private emptyItem: Item;

    private subscriptions: any[] = new Array();

    constructor(private containerService: ContainerService,
                private beerService: BeerService, 
                private messageService: MessageService) { }

    ngOnInit(): void {
        this.model = new Order();
        
        // Defines an empty item to be used as a template for `addItem`.
        this.emptyItem = new Item();
        this.emptyItem.cantidad = 0;
        this.emptyItem.cerveza = null;
        this.emptyItem.contenedor = null;
        this.model.items.push(Object.assign({}, this.emptyItem));

        // Loads the containers and beers to be shown in the select boxes.
        this.containers$ = this.containerService.getAll().map(v => v.data).pipe(shareReplay(1));
        this.beers$ = this.beerService.getAll().map(v => v.data).pipe(shareReplay(1));

        this.messageService.showLoading();
        this.subscriptions.push(
            forkJoin(this.containers$, this.beers$).subscribe(response => this.messageService.closeLoading(), error => {
                this.messageService.closeLoading();
                this.messageService.showError('Ocurrió un error al intentar cargar la información de Cervezas y Contenedores. Reintente mas tarde.');
            })
        );
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach(v => v.unsubscribe())
    }

    addItem() {
        this.model.items.push(Object.assign({}, this.emptyItem));
    }

    removeItem(item) {
        this.model.items = this.model.items.filter(v => v !== item)
    }

    getTotal(): number {
        var total = 0;
        for (let item of this.model.items) {
            if (item.cantidad >= 0 && (item.cantidad % 1 == 0) && item.cerveza && item.contenedor) {
                total += item.cantidad * item.cerveza.pricePerLitre * item.contenedor.capacity;
            }
        }
        return total;
    }

    validate(): boolean {
        var hasErrors = false;
        this.messageService.clear();

        if (this.model.items.reduce((acc, item) => acc || (item.cantidad <= 0 || item.cantidad % 1 != 0), false)) {
            hasErrors = true;
            this.messageService.showError('La cantidad debe ser un número entero mayor a 0 para todos los items.')
        }

        if (this.model.items.reduce((acc, item) => acc || (!item.cerveza), false)) {
            hasErrors = true;
            this.messageService.showError('Debe seleccionar una cerveza para todos los items.')
        }

        if (this.model.items.reduce((acc, item) => acc || (!item.contenedor), false)) {
            hasErrors = true;
            this.messageService.showError('Debe seleccionar un contenedor para todos los items.')
        }

        if (this.model.items.length <= 0) {
            hasErrors = true;
            this.messageService.showError('Un pedido debe tener al menos un item.')
        }

        if (!hasErrors) {
            var controlStock = this.model.controlStock();
            if (controlStock['faltanteCervezas'].length > 0 || controlStock['faltanteContenedores'].length > 0) {
                hasErrors = true;

                var msg = 'No se puede completar el pedido por falta de stock. Revise los siguientes items:\n'

                if (controlStock['faltanteCervezas'].length > 0) {
                    msg += 'Cervezas: ' + controlStock['faltanteCervezas'].map(v => v[0].name + ': ' + (-v[1])).join(', ') + "\n"
                }
                if ( controlStock['faltanteContenedores'].length > 0) {
                    msg += 'Contenedores: ' + controlStock['faltanteContenedores'].map(v => v[0].name + ': ' + (-v[1])).join(', ')
                }

                this.messageService.showError(msg)
            }
        }

        return hasErrors;
   }

   modelForSave() {
       return { items: this.model.items.map(v => { 
           return { idContenedor: v.contenedor.id, idCerveza: v.cerveza.beerId, cantidad: v.cantidad } 
       }) }
   }

}