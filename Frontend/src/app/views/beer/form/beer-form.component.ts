import { Component, OnInit } from '@angular/core';
import { Beer } from '../../../models/beer';

@Component({    
    selector: 'beer-form',
    templateUrl: './beer-form.component.html'    
})
export class BeerFormComponent implements OnInit {
    public model: Beer;

    constructor() { }

    ngOnInit(): void {
        this.model = new Beer();
    }
}
 