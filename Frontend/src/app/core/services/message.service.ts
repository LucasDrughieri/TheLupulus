import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Message } from '../../models/message';

declare var swal: any;

@Injectable()
export class MessageService {

    constructor(private toastrService: ToastrService) { }

    public showMessages(messages: Message[]) {
        messages.forEach((element, index) => {
            switch (element.type) {
                case 'Success': this.toastrService.success(element.text); break;
                case 'Error': this.toastrService.error(element.text); break;
                case 'Warning': this.toastrService.warning(element.text); break;
            }
        });
    }

    public showError(message) {
        this.toastrService.error(message);
    }

    public showWarning(message) {
        this.toastrService.warning(message);
    }

    public showSuccess(message) {
        this.toastrService.success(message);
    }

    public showLoading() {
        swal({
            title: 'Cargando',
            onOpen: () => {
                swal.showLoading();
            }
        });
    }

    public showProcessing() {
        swal({
            title: 'Processing',
            onOpen: () => {
                swal.showLoading();
            }
        });
    }

    public showLoadingMessage(message: string) {
        swal({
            title: message,
            onOpen: () => {
                swal.showLoading();
            }
        });
    }

    public closeLoading() {
        swal.close();
    }
}
