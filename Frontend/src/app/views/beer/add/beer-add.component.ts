import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { MessageService } from '../../../core/services/message.service';
import { Subscription } from 'rxjs';
import { BeerService } from '../../../core/services/beer.service';
import { Router } from '@angular/router';

@Component({    
    templateUrl: './beer-add.component.html'    
})
export class BeerAddComponent implements OnInit, OnDestroy {

    @ViewChild("form") form;

    postSubscription: Subscription;

    constructor(private beerService: BeerService, 
                private router: Router,
                private messageService: MessageService) { }

    ngOnInit(): void { }

    ngOnDestroy(): void {
        if(this.postSubscription) this.postSubscription.unsubscribe();
    }

    save(){
        if(this.form.validate()) return;

        this.messageService.showLoading();

        this.postSubscription = this.beerService.post(this.form.model).subscribe(response => {
            this.messageService.closeLoading();
            this.router.navigate(["/Cervezas"]);
        },
        error => this.messageService.closeLoading());
    }
}
