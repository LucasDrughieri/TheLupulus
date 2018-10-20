import { Component, OnInit } from '@angular/core';
import { Client } from '../../../models/client';
import { MessageService } from '../../../core/services/message.service';

@Component({    
    selector: 'client-form',
    templateUrl: './client-form.component.html'    
})
export class ClientFormComponent implements OnInit {
    public model: Client;
    
    constructor(private messageService: MessageService) { }

    ngOnInit(): void {
        this.model = new Client();
    }

    validate(){
         var hasErrors = false;
         this.messageService.clear();

         if(!this.model.address || this.model.address == ''){
            hasErrors = true;
            this.messageService.showError('La dirección es requerida');
         }

         if(!this.model.businessName || this.model.businessName == ''){
            hasErrors = true;
            this.messageService.showError('La Razon Social es requerida');
         }

         if(!this.model.cuit || this.model.cuit <= 0){
            hasErrors = true;
            this.messageService.showError('El cuit es requerido');
         }

         if(!this.model.email || this.model.email == ''){
            hasErrors = true;
            this.messageService.showError('El mail es requerido');
         }

         if(!this.model.phoneNumber || this.model.phoneNumber <= 0){
            hasErrors = true;
            this.messageService.showError('El telefono es requerido');
         }

         return hasErrors;
    }
}
