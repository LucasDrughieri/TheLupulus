import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { ContainerService } from '../../../core/services/container.service';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({    
    templateUrl: './container-add.component.html'    
})
export class ContainerAddComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    postSubscription: Subscription;

    constructor(private containerService: ContainerService,
                private router: Router, 
                private messageService: MessageService) { }

    ngOnInit(): void { }

    ngOnDestroy(): void {
        if(this.postSubscription) this.postSubscription.unsubscribe();
    }

    save(){
        this.messageService.showLoading();

        this.postSubscription = this.containerService.post(this.form.model).subscribe(response => {
            this.messageService.closeLoading();
            this.router.navigate(["/Contenedores"]);
        },
        error => this.messageService.closeLoading());
    }
}
