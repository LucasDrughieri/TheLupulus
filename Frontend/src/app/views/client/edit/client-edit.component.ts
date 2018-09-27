import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../core/services/client.service';

@Component({    
    templateUrl: './client-edit.component.html'    
})
export class ClientEditComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    private getSubscription: Subscription;
    private putSubscription: Subscription;

    constructor(private clientService: ClientService,
                private messageService: MessageService,
                private router: Router,
                private activateRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const routeParams = this.activateRoute.snapshot.params;

        if (!routeParams.id) {
            this.messageService.showError("Cliente no encontrado");
            this.router.navigate(["/Clientes"]);
            return;
        }

        const clientId = routeParams.id;
        // this.getModel(clientId);
    }

    ngOnDestroy(): void {
        if(this.getSubscription) this.getSubscription.unsubscribe();
        if(this.putSubscription) this.putSubscription.unsubscribe();
    }

    getModel(id){
        this.messageService.showLoading();

        this.getSubscription = this.clientService.get(id).subscribe(response => {
            this.messageService.closeLoading();

            if(response.data){
                this.form.model = response.data;
            }
        }, 
        error => this.messageService.closeLoading());
    }

    save(){
        this.messageService.showLoading();

        this.putSubscription = this.clientService.put(this.form.model).subscribe(response => {
            this.messageService.closeLoading();
            //success
        }, 
        error => this.messageService.closeLoading());
    }
}
