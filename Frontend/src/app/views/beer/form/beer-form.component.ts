import { Component, OnInit } from '@angular/core';
import { Beer } from '../../../models/beer';
import { MessageService } from '../../../core/services/message.service';

@Component({    
    selector: 'beer-form',
    templateUrl: './beer-form.component.html'    
})
export class BeerFormComponent implements OnInit {
    public model: Beer;

    constructor(private messageService: MessageService) { }

    ngOnInit(): void {
        this.model = new Beer();
    }

    validate(){
        var hasErrors = false;
        this.messageService.clear();

        if(!this.model.name || this.model.name == ''){
           hasErrors = true;
           this.messageService.showError('El nombre es requerido');
        }

        if(!this.model.granos || this.model.granos == ''){
           hasErrors = true;
           this.messageService.showError('Los granos son requeridos');
        }

        if(!this.model.density || this.model.density <= 0){
           hasErrors = true;
           this.messageService.showError('La densidad es requerida');
        }

        if(!this.model.color || this.model.color == ''){
           hasErrors = true;
           this.messageService.showError('El color es requerido');
        }

        if(!this.model.ibu || this.model.ibu <= 0){
           hasErrors = true;
           this.messageService.showError('El Ibu es requerido');
        }

        if(!this.model.graduation || this.model.graduation <= 0){
            hasErrors = true;
            this.messageService.showError('El % alcohol es requerido');
         }

        if(!this.model.pricePerLitre || this.model.pricePerLitre <= 0){
            hasErrors = true;
            this.messageService.showError('El Precio por libro es requerido');
         }

         if(!this.model.quantity || this.model.quantity < 0){
            hasErrors = true;
            this.messageService.showError('La cantidad es requerida');
         }

        return hasErrors;
   }
}
 