import { Component, OnInit } from '@angular/core';
import { Client } from '../../../models/client';

@Component({    
    selector: 'client-form',
    templateUrl: './client-form.component.html'    
})
export class ClientFormComponent implements OnInit {
    public model: Client;
    
    constructor() { }

    ngOnInit(): void {
        this.model = new Client();
    }
}
