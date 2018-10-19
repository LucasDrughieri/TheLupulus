import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { UserService } from '../../../core/services/user.service';

@Component({    
    templateUrl: './user-edit.component.html'    
})
export class UserEditComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    private getSubscription: Subscription;
    private putSubscription: Subscription;

    constructor(private userService: UserService,
                private messageService: MessageService,
                private router: Router,
                private activateRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const routeParams = this.activateRoute.snapshot.params;

        if (!routeParams.id) {
            this.messageService.showError("Usuario no encontrado");
            this.router.navigate(["/Usuarios"]);
            return;
        }

        const userId = routeParams.id;
        this.getModel(userId);
    }

    ngOnDestroy(): void {
        if(this.getSubscription) this.getSubscription.unsubscribe();
        if(this.putSubscription) this.putSubscription.unsubscribe();
    }

    getModel(id){
        this.messageService.showLoading();

        this.getSubscription = this.userService.get(id).subscribe(response => {
            this.messageService.closeLoading();

            if(response.data){
                this.form.model = response.data;
            }
        }, 
        error => this.messageService.closeLoading());
    }

    save(){
        this.messageService.showLoading();

        this.putSubscription = this.userService.put(this.form.model).subscribe(response => {
            this.messageService.closeLoading();
            //success
        }, 
        error => this.messageService.closeLoading());
    }
}
