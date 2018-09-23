import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configuration } from '../configuration';
import { BaseService } from './base.service';

@Injectable()
export class ClientService extends BaseService {

    constructor(http: HttpClient, config: Configuration) {
        super(http, config, "Client");
    }
}
