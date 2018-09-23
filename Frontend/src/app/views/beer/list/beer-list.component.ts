import { Subscription } from 'rxjs/Subscription';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from '../../../core/services/message.service';
import { DataTableService } from '../../../core/services/datatable.service';
import { Beer } from '../../../models/beer';
import { BeerService } from '../../../core/services/beer.service';

@Component({
    templateUrl: './beer-list.component.html'
})
export class BeerListComponent implements OnInit {
    public beers: Beer[] = new Array();

    private getAllSubscription: Subscription;
    private deleteSubscription: Subscription;

    constructor(
        private beerService: BeerService,
        private router: Router,
        private messageService: MessageService,
        private dataTableService: DataTableService) { }

    ngOnInit(): void {
        // this.getAllContainers();
        this.initializeDataTable();
    }

    ngOnDestroy(): void {
        if (this.getAllSubscription) this.getAllSubscription.unsubscribe();
        if (this.deleteSubscription) this.deleteSubscription.unsubscribe();
    }

    public delete(id: number) {
        this.messageService.showLoading();

        this.deleteSubscription = this.beerService.delete(id)
            .subscribe(
                response => {
                    this.messageService.closeLoading();
                    this.getAllContainers();
                },
                error => {
                    this.messageService.closeLoading();
                });
    }

    private getAllContainers() {
        this.messageService.showLoading();

        this.getAllSubscription = this.beerService.getAll()
            .subscribe(
                response => {
                    this.beers = response.data;

                    this.initializeDataTable();
                    this.messageService.closeLoading();
                },
                error => {
                    this.initializeDataTable();
                    this.messageService.closeLoading();
                });
    }

    private initializeDataTable() {
        const selector = '#beer-table';

        this.dataTableService.destroy(selector);
        this.dataTableService.initialize(selector);
    }

    goToDetail(id){
        this.router.navigate([`/Cervezas/${id}/Editar`]);
    }
}
