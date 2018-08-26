import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable()
export class Configuration {

    UrlApi: string;

    constructor() {
        this.UrlApi = environment.urlApi;
    }

    public getHeaders() {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        });

        return headers;
    }

    public getBaseUrl(){
        return this.UrlApi;
    }

    public clearStorage(): void {
        localStorage.clear();
    }
}
