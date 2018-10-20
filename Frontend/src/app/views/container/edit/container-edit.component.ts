import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContainerService } from '../../../core/services/container.service';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';

@Component({    
    templateUrl: './container-edit.component.html'    
})
export class ContainerEditComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    private getSubscription: Subscription;
    private putSubscription: Subscription;

    constructor(private containerService: ContainerService,
                private messageService: MessageService,
                private router: Router,
                private activateRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const routeParams = this.activateRoute.snapshot.params;

        if (!routeParams.id) {
            this.messageService.showError("Contenedor no encontrado");
            this.router.navigate(["/Contenedores"]);
            return;
        }

        const contenedorId = routeParams.id;
        this.getModel(contenedorId);
    }

    ngOnDestroy(): void {
        if(this.getSubscription) this.getSubscription.unsubscribe();
        if(this.putSubscription) this.putSubscription.unsubscribe();
    }

    getModel(id){
        this.messageService.showLoading();

        this.getSubscription = this.containerService.get(id).subscribe(response => {
            this.messageService.closeLoading();

            if(response.data){
                this.form.model = response.data;
                this.form.model.id = this.form.model.containerId;
            }
        }, 
        error => this.messageService.closeLoading());
    }

    save(){
        this.messageService.showLoading();

        this.putSubscription = this.containerService.put(this.form.model).subscribe(response => {
            this.messageService.closeLoading();
            this.router.navigate(["/Contenedores"]);
        }, 
        error => this.messageService.closeLoading());
    }
}
