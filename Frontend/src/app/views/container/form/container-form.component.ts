import { Component, OnInit } from '@angular/core';
import { Container } from '../../../models/container';

@Component({    
    selector: 'container-form',
    templateUrl: './container-form.component.html'    
})
export class ContainerFormComponent implements OnInit {
    public model: Container;

    constructor() { }

    ngOnInit(): void {
        this.model = new Container();
    }
}
