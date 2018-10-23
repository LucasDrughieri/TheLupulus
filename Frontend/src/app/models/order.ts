import { Beer } from './beer';
import { Container } from './container';

export class Order {
    id: number;
    items: Item[];

    constructor() {
        this.items = new Array();
    }
}

export class Item {
    contenedor: Container;
    cerveza: Beer;
    cantidad: number;
    precio: number;
}