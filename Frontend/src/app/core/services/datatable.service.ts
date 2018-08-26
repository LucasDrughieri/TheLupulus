import { Injectable } from '@angular/core';

declare var $: any;

@Injectable()
export class DataTableService {

    constructor() { }

    public destroy(selector) {
        $(selector).DataTable().destroy();
    }

    public initialize(selector: string) {
        const lang = this.getLanguageEs();

        $(document).ready(function () {
            const dataTableOptions = {
                oSearch: { 'bSmart': false, 'bRegex': true },
                responsive: true,
                scrollX: true,
                language: lang
            };

            const table = $(selector).DataTable(dataTableOptions);
        });
    }

    private getLanguageEs() {
        var language =
        {
            sProcessing: 'Procesando...',
            sLengthMenu: 'Mostrar _MENU_ registros',
            sZeroRecords: 'No se encontraron resultados',
            sEmptyTable: 'Sin información disponible para esta tabla',
            sInfo: 'Mostrando del registro _START_ al _END_ de un total de _TOTAL_',
            sInfoEmpty: 'Mostrando del registro 0 al 0 de un total de 0',
            sInfoFiltered: '(filtrado de un total de _MAX_ registros)',
            sInfoPostFix: '',
            sSearch: 'Buscar:',
            sUrl: '',
            sInfoThousands: ',',
            sLoadingRecords: 'Cargando...',
            oPaginate: {
                sFirst: 'Primero',
                sLast: 'Último',
                sNext: 'Siguiente',
                sPrevious: 'Anterior'
            },
            oAria: {
                sSortAscending: ': Activar para ordenar la columna de manera ascendente',
                sSortDescending: ': Activar para ordenar la columna de manera descendente'
            }
        };

        return language;
    }
}