import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { LoginModel } from '../../models/login';
import { MessageService } from '../../core/services/message.service';

@Component({
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

    public model: LoginModel;
    public loading = false;
    public returnUrl: string;

    private loginSubscription: Subscription;

    constructor(
        private router: Router,
        private messageService: MessageService) {

        this.model = new LoginModel();
    }

    ngOnDestroy(): void {
        if (this.loginSubscription) {
            this.loginSubscription.unsubscribe();
        }
    }
    ngOnInit(): void {

    }

    login() {
        this.onLoginSuccess();
        //this.messageService.showLoading();

        // this.loginSubscription = this.authService.post(this.model)
        //     .subscribe(
        //         response => {
        //             this.messageService.closeLoading();
        //             this.onLoginSuccess(response.data);
        //         },
        //         response => {
        //             this.messageService.closeLoading();
        //             if (response.error && response.error.messages) {
        //                 this.messageService.showMessages(response.error.messages);
        //             }
        //             else {
        //                 this.messageService.showError("Ha ocurrido un error al ingresar al sistema");
        //             }

        //         });
    }

    onLoginSuccess() {
        this.router.navigate(['/Home']);
    }

    onSubmit() {
        this.login();
    }
}
