import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { UserService } from '../../../core/services/user.service';

@Component({    
    templateUrl: './user-add.component.html'    
})
export class UserAddComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    postSubscription: Subscription;

    constructor(private userService: UserService, 
                private messageService: MessageService) { }

    ngOnInit(): void { }

    ngOnDestroy(): void {
        if(this.postSubscription) this.postSubscription.unsubscribe();
    }

    save(){
        this.messageService.showLoading();

        this.postSubscription = this.userService.post(this.form.model).subscribe(response => {
            this.messageService.closeLoading();
            //success
        },
        error => this.messageService.closeLoading());
    }
}
