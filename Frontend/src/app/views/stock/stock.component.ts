import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ContainerService } from '../../core/services/container.service';
import { BeerService } from '../../core/services/beer.service';
import { MessageService } from '../../core/services/message.service';

@Component({    
    selector: 'stock-form',
    templateUrl: './stock.component.html'    
})
export class StockComponent implements OnInit, OnDestroy {
    public containers: any[] = new Array();
    public beers: any[] = new Array();

    beerId: number;
    beerQuantity: number;
    containerId: number;
    containerQuantity: number;

    getAllContainersSubscription: Subscription;
    getAllBeersSubscription: Subscription;
    addBeerSubscription: Subscription;
    addContainerSubscription: Subscription;

    constructor(private containerService: ContainerService,
                private beerService: BeerService, 
                private messageService: MessageService) { }

    ngOnInit(): void {
        this.beerId = 0;
        this.containerId = 0;

        this.getAllContainers();
        this.getAllBeers();
    }

    ngOnDestroy(): void {
        if(this.getAllContainersSubscription) this.getAllContainersSubscription.unsubscribe();
        if(this.getAllBeersSubscription) this.getAllBeersSubscription.unsubscribe();
        if(this.addBeerSubscription) this.addBeerSubscription.unsubscribe();
        if(this.addContainerSubscription) this.addContainerSubscription.unsubscribe();
    }

    private getAllContainers() {
        this.getAllContainersSubscription = this.containerService.getAll()
            .subscribe(response => {

                this.containers.push({ id: 0, text: "Seleccione una opción" });

                response.data.forEach(element => {
                    this.containers.push({ id: element.id, text: element.name }); 
                });;
            });
    }

    private getAllBeers() {
        this.getAllBeersSubscription = this.beerService.getAll()
            .subscribe(response => {

                this.beers.push({ id: 0, text: "Seleccione una opción" });

                response.data.forEach(element => {
                    this.beers.push({ id: element.beerId, text: element.name }); 
                });;
            });
    }

    addBeerStock(){
        var hasErrors = false;

        if(!this.beerId || this.beerId <= 0){
            hasErrors = true;
            this.messageService.showError("La cerveza es requerida");
        }

        if(!this.beerQuantity || this.beerQuantity <= 0){
            hasErrors = true;
            this.messageService.showError("La cantidad de cerveza es requerida");
        }

        if(hasErrors) return;

        this.messageService.showLoading();
        
        this.addBeerSubscription = this.beerService.addStock({ id: this.beerId, quantity: this.beerQuantity }).subscribe(() => {
            this.messageService.closeLoading();
        },
        () => this.messageService.closeLoading());
    }

    addContainerStock(){
        var hasErrors = false;

        if(!this.containerId || this.containerId <= 0){
            hasErrors = true;
            this.messageService.showError("El contenedor es requerido");
        }

        if(!this.containerQuantity || this.containerQuantity <= 0){
            hasErrors = true;
            this.messageService.showError("La cantidad de contenedores es requerida");
        }

        if(hasErrors) return;

        this.messageService.showLoading();
        
        this.addBeerSubscription = this.containerService.addStock({ id: this.containerId, quantity: this.containerQuantity }).subscribe(() => {
            this.messageService.closeLoading();
        },
        () => this.messageService.closeLoading());
    }
}
