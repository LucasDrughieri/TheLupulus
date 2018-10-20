import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../../../models/user';
import { Client } from '../../../models/client';
import { ClientService } from '../../../core/services/client.service';
import { Subscription } from 'rxjs';

@Component({    
    selector: 'user-form',
    templateUrl: './user-form.component.html'    
})
export class UserFormComponent implements OnInit, OnDestroy {
    public model: User;
    
    public roles: any[] = new Array();
    public clients: any[] = new Array();

    getAllClientSubscription: Subscription;

    constructor(private clientService: ClientService) { }

    ngOnInit(): void {
        this.model = new User();

        this.model.clientId = 0;
        this.model.role = 0;

        this.getAllClients();

        this.roles.push({ id: 0, text: "Seleccione una opción" });
        this.roles.push({ id: 1, text: "Administrador" });
        this.roles.push({ id: 2, text: "Cliente" });
    }

    ngOnDestroy(): void {
        if(this.getAllClientSubscription) this.getAllClientSubscription.unsubscribe();
    }

    private getAllClients() {
        this.getAllClientSubscription = this.clientService.getAll()
            .subscribe(response => {

                this.clients.push({ id: 0, text: "Seleccione una opción" });

                response.data.forEach(element => {
                    this.clients.push({ id: element.id, text: element.businessName }); 
                });;
            });
    }
}
