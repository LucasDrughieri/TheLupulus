import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { BeerService } from '../../../core/services/beer.service';

@Component({    
    templateUrl: './beer-edit.component.html'    
})
export class BeerEditComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    private getSubscription: Subscription;
    private putSubscription: Subscription;

    constructor(private beerService: BeerService,
                private messageService: MessageService,
                private router: Router,
                private activateRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const routeParams = this.activateRoute.snapshot.params;

        if (!routeParams.id) {
            this.messageService.showError("Cerveza no encontrado");
            this.router.navigate(["/Cervezas"]);
            return;
        }

        const beerId = routeParams.id;
        this.getModel(beerId);
    }

    ngOnDestroy(): void {
        if(this.getSubscription) this.getSubscription.unsubscribe();
        if(this.putSubscription) this.putSubscription.unsubscribe();
    }

    getModel(id){
        this.messageService.showLoading();

        this.getSubscription = this.beerService.get(id).subscribe(response => {
            this.messageService.closeLoading();

            if(response.data){
                this.form.model = response.data;
                this.form.model.id = id;
            }
        }, 
        error => this.messageService.closeLoading());
    }

    save(){
        if(this.form.validate()) return;
        
        this.messageService.showLoading();

        this.putSubscription = this.beerService.put(this.form.model).subscribe(response => {
            this.messageService.closeLoading();
            this.router.navigate(["/Cervezas"]);
        }, 
        error => this.messageService.closeLoading());
    }
}
