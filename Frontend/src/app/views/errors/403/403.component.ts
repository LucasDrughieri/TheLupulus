import { Component, } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'forbidden',
  templateUrl: '403.component.html'
})
export class ForbiddenComponent {

    constructor(private router: Router) { }

    goBack(){
        var user = JSON.parse(localStorage.getItem('user'));

        if(user){
            if(user.userId.role == 1){
                this.router.navigate(['/Home']);
            }
            else{
                this.router.navigate(['/Pedidos']);
            }
        }
        else{
            this.router.navigate(['/Login']);
        }
    }
}
