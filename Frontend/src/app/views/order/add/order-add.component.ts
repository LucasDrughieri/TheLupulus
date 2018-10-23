import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { OrderService } from '../../../core/services/order.service';
import { Router } from '@angular/router';

@Component({    
    selector: 'order-add',
    templateUrl: './order-add.component.html'    
})
export class OrderAddComponent implements OnInit {

    @ViewChild("form") form;

    postSubscription: Subscription;

    constructor(private orderService: OrderService, 
                private router: Router,
                private messageService: MessageService) { }

    ngOnInit(): void { }

    ngOnDestroy(): void {
        if(this.postSubscription) this.postSubscription.unsubscribe();
    }

    save(){
        if(this.form.validate()) return;

        this.messageService.showLoading();

        this.postSubscription = this.orderService.post(this.form.modelForSave()).subscribe(
            response => {
                this.messageService.closeLoading();
                this.router.navigate(["/Perdidos"]);
            },
            error => {
                this.messageService.closeLoading()
                this.messageService.showError("Oops! Algo salió mal.")
            }
        );
    }
}