import { Component, OnInit } from '@angular/core';
import { Container } from '../../../models/container';
import { MessageService } from '../../../core/services/message.service';

@Component({    
    selector: 'container-form',
    templateUrl: './container-form.component.html'    
})
export class ContainerFormComponent implements OnInit {
    public model: Container;

    constructor(private messageService: MessageService) { }

    ngOnInit(): void {
        this.model = new Container();
    }

    validate(){
        var hasErrors = false;
        this.messageService.clear();

        if(!this.model.name || this.model.name == ''){
           hasErrors = true;
           this.messageService.showError('El nombre es requerido');
        }

        if(!this.model.material || this.model.material == ''){
           hasErrors = true;
           this.messageService.showError('EL material es requerido');
        }

        if(!this.model.width || this.model.width <= 0){
           hasErrors = true;
           this.messageService.showError('El ancho es requerido');
        }

        if(!this.model.height || this.model.height <= 0){
           hasErrors = true;
           this.messageService.showError('El alto es requerido');
        }

        if(!this.model.capacity || this.model.capacity <= 0){
            hasErrors = true;
            this.messageService.showError('La capacidad es requerida');
         }

         if(!this.model.quantity || this.model.quantity < 0){
            hasErrors = true;
            this.messageService.showError('La cantidad es requerida');
         }

        return hasErrors;
   }
}
