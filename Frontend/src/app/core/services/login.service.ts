import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configuration } from '../configuration';

@Injectable()
export class LoginService {

    constructor(protected http: HttpClient,
                protected config: Configuration) {
    }

    public login(model) {
        return this.http.post<any>(`${this.config.getBaseUrl()}session/create/`, model, { headers: this.config.getHeaders() });
    }
}
