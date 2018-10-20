import { Subscription } from 'rxjs/Subscription';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from '../../../core/services/message.service';
import { DataTableService } from '../../../core/services/datatable.service';
import { UserService } from '../../../core/services/user.service';
import { User } from '../../../models/user';

@Component({
    templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
    public users: User[] = new Array();

    private getAllSubscription: Subscription;
    private deleteSubscription: Subscription;

    constructor(
        private userService: UserService,
        private router: Router,
        private messageService: MessageService,
        private dataTableService: DataTableService) { }

    ngOnInit(): void {
        this.getAllUsers();
        this.initializeDataTable();
    }

    ngOnDestroy(): void {
        if (this.getAllSubscription) this.getAllSubscription.unsubscribe();
        if (this.deleteSubscription) this.deleteSubscription.unsubscribe();
    }

    public delete(id: number) {
        this.messageService.showLoading();

        this.deleteSubscription = this.userService.delete(id)
            .subscribe(
                response => {
                    this.messageService.closeLoading();
                    this.getAllUsers();
                },
                error => {
                    this.messageService.closeLoading();
                });
    }

    private getAllUsers() {
        this.messageService.showLoading();

        this.getAllSubscription = this.userService.getAll()
            .subscribe(
                response => {
                    this.users = response.data.map(item => {
                        item.visible = true;
                        return item;
                    });;

                    this.initializeDataTable();
                    this.messageService.closeLoading();
                },
                error => {
                    this.initializeDataTable();
                    this.messageService.closeLoading();
                });
    }

    private initializeDataTable() {
        const selector = '#user-table';

        this.dataTableService.destroy(selector);
        this.dataTableService.initialize(selector);
    }

    goToDetail(id){
        this.router.navigate([`/Usuarios/${id}/Editar`]);
    }
}
