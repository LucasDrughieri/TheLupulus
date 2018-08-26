import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import * as _ from 'lodash';
import { Cookie } from "ng2-cookies/ng2-cookies";
import { smoothlyMenu } from '../../../app.helper';
import { Configuration } from '../../../core/configuration';
declare var jQuery: any;

@Component({
  selector: 'topnavbar',
  templateUrl: 'topnavbar.template.html',
  styleUrls: ['topnavbar.component.scss']
})
export class TopNavbarComponent {
  public userName: string;

  constructor(
    public configService: Configuration,
    // private authService: AuthenticationService,
    private router: Router) {

    this.userName = Cookie.get("username");
  }

  toggleNavigation(): void {
    jQuery("body").toggleClass("mini-navbar");
    smoothlyMenu();
  }

  toggleNavigationBar(): void {
    jQuery("body").toggleClass("mini-navbar");
    smoothlyMenu();
  }

  logout() {
    // this.authService.logout();
    this.router.navigate(['/Login']);
  }
}
