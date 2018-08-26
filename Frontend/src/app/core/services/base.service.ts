import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configuration } from '../configuration';

@Injectable()
export class BaseService {

    CONTROLLER: string;

    constructor(protected http: HttpClient,
                protected config: Configuration,
                controllerName: string) {

        this.CONTROLLER = controllerName;
    }

    public get(id) {
        return this.http.get<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}/${id}`, { headers: this.config.getHeaders() });
    }

    public getAll() {
        return this.http.get<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}`, { headers: this.config.getHeaders() });
    }

    public post(model) {
        return this.http.post<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}`, model, { headers: this.config.getHeaders() });
    }

    public put(model) {
        return this.http.put<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}`, model, { headers: this.config.getHeaders() });
    }

    public delete(id) {
        return this.http.delete<any>(`${this.config.getBaseUrl()}${this.CONTROLLER}/${id}`, { headers: this.config.getHeaders() });
    }
}
