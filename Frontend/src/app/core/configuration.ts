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
            'Access-Control-Allow-Origin': '*',
            'X-AuthToken': '254b5bf6-2aee-4527-8b82-b270f2c93e9a'
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
