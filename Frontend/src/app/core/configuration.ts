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
        var user = JSON.parse(localStorage.getItem("user"));

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Authorization': user.token
        });

        return headers;
    }

    getLoginHeaders() {
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
