import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { UserService } from '../../../core/services/user.service';
import { Router } from '@angular/router';

@Component({    
    templateUrl: './user-add.component.html'    
})
export class UserAddComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    postSubscription: Subscription;

    constructor(private userService: UserService, 
                private router: Router,
                private messageService: MessageService) { }

    ngOnInit(): void {

    }

    ngOnDestroy(): void {
        if(this.postSubscription) this.postSubscription.unsubscribe();
    }

    save(){
        if(this.form.validate()) return;

        this.messageService.showLoading();
        
        this.postSubscription = this.userService.post(this.form.model).subscribe(() => {
            this.messageService.closeLoading();
            this.router.navigate(["/Usuarios"]);
        },
        () => this.messageService.closeLoading());
    }
}
