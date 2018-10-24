import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgSelectModule, NG_SELECT_DEFAULT_CONFIG } from '@ng-select/ng-select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgDatepickerModule } from 'ng2-datepicker';
import { ToastrModule } from 'ngx-toastr';
import { RouterModule } from '@angular/router';
import { LocationStrategy, PathLocationStrategy, CommonModule } from '@angular/common';

import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { ViewsModule } from './views/views.module';
import { LayoutsModule } from './components/common/layouts/layouts.module';
import { AuthGuard } from './guards/auth.guard';

const SELECT_CONFIG = {
  provide: NG_SELECT_DEFAULT_CONFIG,
  useValue: {
    notFoundText: 'No se encontraron resultados.',
    typeToSearchText: 'Escriba para buscar',
    addTagText: 'Agregar',
    loadingText: 'Cargando...',
    clearAllText: 'Limpiar'
  }
};

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserAnimationsModule,
    NgSelectModule,
    HttpModule,
    ToastrModule.forRoot(),
    RouterModule.forRoot(ROUTES),
    NgDatepickerModule,
    LayoutsModule,
    CoreModule,
    ViewsModule
  ],
  providers: [
    { provide: LocationStrategy, useClass: PathLocationStrategy },
    SELECT_CONFIG,,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
