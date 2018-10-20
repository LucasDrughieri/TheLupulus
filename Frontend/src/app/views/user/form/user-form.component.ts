import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../../../models/user';
import { Client } from '../../../models/client';
import { ClientService } from '../../../core/services/client.service';
import { Subscription } from 'rxjs';
import { MessageService } from '../../../core/services/message.service';

@Component({    
    selector: 'user-form',
    templateUrl: './user-form.component.html'    
})
export class UserFormComponent implements OnInit, OnDestroy {
    public model: User;
    
    public roles: any[] = new Array();
    public clients: any[] = new Array();

    getAllClientSubscription: Subscription;

    constructor(private clientService: ClientService, private messageService: MessageService) { }

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

    validate(){
        var hasErrors = false;
        this.messageService.clear();

        if(!this.model.nickname || this.model.nickname == ''){
           hasErrors = true;
           this.messageService.showError('El usuario es requerido');
        }

        if(!this.model.password || this.model.password == ''){
           hasErrors = true;
           this.messageService.showError('La contraseña es requerida');
        }

        if(!this.model.role || this.model.role < 1 || this.model.role > 2){
           hasErrors = true;
           this.messageService.showError('El rol es requerido');
        }
        else{
            if(this.model.role == 2 && (!this.model.clientId || this.model.clientId <= 0)){
                hasErrors = true;
                this.messageService.showError('El cliente es requerido');
            }
        }

        return hasErrors;
   }
}
