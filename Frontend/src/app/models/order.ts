import { Beer } from './beer';
import { Container } from './container';
import { User } from './user';

export class Order {
    idPedido: number;
    items: Item[];
    cliente: User;
    pagado: boolean;
    total: number;
    fecha: string;
    estado: number;

    constructor() {
        this.items = new Array();
    }

    estadoVisible(estado): string {
        switch(estado || this.estado) {
            case 0: return "Finalizado"
            case 1: return "Pendiente"
            case 2: return "En preparación"
            case 3: return "Cancelado"
        }
    }

    transicionesPosibles(): number[] {
        switch(this.estado) {
            case 0: return []
            case 1: return [0, 2, 3]
            case 2: return [0, 3]
            case 3: return []
        }
    }

    controlStock(): Object {
        var cervezas = this.items.map(v => v.cerveza);
        var contenedores = this.items.map(v => v.contenedor);

        var litrajePorCerveza = this.items.reduce((acc, v) => {
            acc[v.cerveza.beerId] = (acc[v.cerveza.beerId] || 0) + (v.cantidad * v.contenedor.capacity); return acc;
        }, new Object());

        var cantidadPorContenedor = this.items.reduce((acc, v) => {
            acc[v.contenedor.id] = (acc[v.contenedor.id] || 0) + v.cantidad; return acc;
        } , new Object());

        var resultado = new Object();
        resultado['faltanteCervezas'] = cervezas.map(v => [v, v.quantity - litrajePorCerveza[v.beerId]]).filter(v => v[1] < 0);
        resultado['faltanteContenedores'] = contenedores.map(v => [v, v.quantity - cantidadPorContenedor[v.id]]).filter(v => v[1] < 0);
        return resultado;
    }
}

export class Item {
    contenedor: Container;
    cerveza: Beer;
    cantidad: number;
    precio: number;
}
