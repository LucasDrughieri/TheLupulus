import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Router } from '@angular/router';

@Injectable()
export class ErrorHandlerService {
    constructor(private messageService: MessageService, private router: Router) { }

    public handleErrors(response) {
        switch (response.status) {
            case 400: this.handle400(response); break;
            case 401: this.handle401(response); break;
            case 500: this.handle500(); break;
            case 0: this.handle0(); break;
            default: break;
        }
    }

    private handle401(response) {
        this.router.navigate(['/Login']);
    }

    private handle400(response) {
        var json = JSON.parse(response._body)
        if (json.messages) this.messageService.showMessages(json.messages);
    }

    private handle500() {
        this.messageService.showError('Ocurrió un error inesperado. Consulte al administrador.');
    }

    private handle0(){
        this.messageService.showError('Por favor intente nuevamente. (Sin Conexion)');
    }
}
