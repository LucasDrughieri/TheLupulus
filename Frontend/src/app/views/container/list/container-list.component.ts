import { Subscription } from 'rxjs/Subscription';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ContainerService } from '../../../core/services/container.service';
import { MessageService } from '../../../core/services/message.service';
import { DataTableService } from '../../../core/services/datatable.service';
import { Container } from '@angular/compiler/src/i18n/i18n_ast';

@Component({
    templateUrl: './container-list.component.html'
})
export class ContainerListComponent implements OnInit {
    public containers: Container[] = new Array();

    private getAllSubscription: Subscription;
    private deleteSubscription: Subscription;

    constructor(
        private containerService: ContainerService,
        private router: Router,
        private messageService: MessageService,
        private dataTableService: DataTableService) { }

    ngOnInit(): void {
        this.getAllContainers();
        this.initializeDataTable();
    }

    ngOnDestroy(): void {
        if (this.getAllSubscription) this.getAllSubscription.unsubscribe();
        if (this.deleteSubscription) this.deleteSubscription.unsubscribe();
    }

    public delete(id: number) {
        this.messageService.showLoading();

        this.deleteSubscription = this.containerService.delete(id)
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

        this.getAllSubscription = this.containerService.getAll()
            .subscribe(
                response => {
                    this.containers = response.data.map(item => {
                        item.visible = true;
                        item.id = item.containerId;
                        return item;
                    });

                    this.initializeDataTable();
                    this.messageService.closeLoading();
                },
                error => {
                    this.initializeDataTable();
                    this.messageService.closeLoading();
                });
    }

    private initializeDataTable() {
        const selector = '#container-table';

        this.dataTableService.destroy(selector);
        this.dataTableService.initialize(selector);
    }

    goToDetail(id){
        this.router.navigate([`/Contenedores/${id}/Editar`]);
    }
}
