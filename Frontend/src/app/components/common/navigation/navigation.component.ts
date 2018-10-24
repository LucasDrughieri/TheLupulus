import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { smoothlyMenu } from '../../../app.helper';

declare var jQuery: any;

@Component({
  selector: 'navigation',
  templateUrl: 'navigation.template.html'
})

export class NavigationComponent implements OnInit, OnDestroy {

  public roleId: number = 2;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    var user = JSON.parse(localStorage.getItem('user'));

    if(user){
      this.roleId = user.userId.role;
    }
  }

  ngOnDestroy() {
  }

  goHome(){
    this.router.navigate(['/Home']);
  }

  ngAfterViewInit() {
    jQuery('#side-menu').metisMenu();

    if (jQuery("body").hasClass('fixed-sidebar')) {
      jQuery('.sidebar-collapse').slimscroll({
        height: '100%'
      })
    }
  }

  toggleNavigation(): void {
    jQuery("body").toggleClass("mini-navbar");
    smoothlyMenu();
  }

  activeRoute(routename: string): boolean {
    return this.router.url.indexOf(routename) > -1;
  }
}
