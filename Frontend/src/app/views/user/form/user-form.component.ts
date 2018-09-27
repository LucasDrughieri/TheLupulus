import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';

@Component({    
    selector: 'user-form',
    templateUrl: './user-form.component.html'    
})
export class UserFormComponent implements OnInit {
    public model: User;
    
    public roles: any[] = new Array();

    constructor() { }

    ngOnInit(): void {
        this.model = new User();

        this.roles.push({ id: 1, text: "Administrador" });
        this.roles.push({ id: 2, text: "Comprador" });
        this.roles.push({ id: 3, text: "Vendedor" });
    }
}
