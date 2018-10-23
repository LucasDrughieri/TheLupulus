import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configuration } from '../configuration';

@Injectable()
export class OrderService {

    CONTROLLER: string = "pedido";

    constructor(protected http: HttpClient,
                protected config: Configuration) { }

    public get(id) {
        return this.http.get<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}/${id}`, { headers: this.config.getHeaders() });
    }

    public getAll() {
        return this.http.get<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}`, { headers: this.config.getHeaders() });
    }

    public post(model) {
        return this.http.post<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}`, model, { headers: this.config.getHeaders() });
    }

    public patch(model) {
        return this.http.patch<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}`, model, { headers: this.config.getHeaders() });
    }
}
