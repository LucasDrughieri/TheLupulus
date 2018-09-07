import { Component, OnInit } from '@angular/core';
import { Container } from '../../../models/container';

@Component({    
    templateUrl: './container-add.component.html'    
})
export class ContainerAddComponent implements OnInit {
    public model: Container;

    constructor() { }

    ngOnInit(): void {
        this.model = new Container();
    }

    save(){
        
    }
}
