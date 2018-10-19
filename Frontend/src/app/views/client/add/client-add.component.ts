import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../core/services/client.service';
import { Router } from '@angular/router';

@Component({    
    templateUrl: './client-add.component.html'    
})
export class ClientAddComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    postSubscription: Subscription;

    constructor(private clientService: ClientService,
                private router: Router, 
                private messageService: MessageService) { }

    ngOnInit(): void { }

    ngOnDestroy(): void {
        if(this.postSubscription) this.postSubscription.unsubscribe();
    }

    save(){
        this.messageService.showLoading();

        this.postSubscription = this.clientService.post(this.form.model).subscribe(response => {
            this.messageService.closeLoading();
            this.router.navigate(["/Clientes"]);
        },
        error => this.messageService.closeLoading());
    }
}
