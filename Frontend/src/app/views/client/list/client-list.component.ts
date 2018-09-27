import { Subscription } from 'rxjs/Subscription';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from '../../../core/services/message.service';
import { DataTableService } from '../../../core/services/datatable.service';
import { ClientService } from '../../../core/services/client.service';
import { Client } from '../../../models/client';

@Component({
    templateUrl: './client-list.component.html'
})
export class ClientListComponent implements OnInit {
    public users: Client[] = new Array();

    private getAllSubscription: Subscription;
    private deleteSubscription: Subscription;

    constructor(
        private clientService: ClientService,
        private router: Router,
        private messageService: MessageService,
        private dataTableService: DataTableService) { }

    ngOnInit(): void {
        // this.getAllClients();
        this.initializeDataTable();
    }

    ngOnDestroy(): void {
        if (this.getAllSubscription) this.getAllSubscription.unsubscribe();
        if (this.deleteSubscription) this.deleteSubscription.unsubscribe();
    }

    public delete(id: number) {
        this.messageService.showLoading();

        this.deleteSubscription = this.clientService.delete(id)
            .subscribe(
                response => {
                    this.messageService.closeLoading();
                    this.getAllClients();
                },
                error => {
                    this.messageService.closeLoading();
                });
    }

    private getAllClients() {
        this.messageService.showLoading();

        this.getAllSubscription = this.clientService.getAll()
            .subscribe(
                response => {
                    this.users = response.data;

                    this.initializeDataTable();
                    this.messageService.closeLoading();
                },
                error => {
                    this.initializeDataTable();
                    this.messageService.closeLoading();
                });
    }

    private initializeDataTable() {
        const selector = '#client-table';

        this.dataTableService.destroy(selector);
        this.dataTableService.initialize(selector);
    }

    goToDetail(id){
        this.router.navigate([`/Clientes/${id}/Editar`]);
    }
}
