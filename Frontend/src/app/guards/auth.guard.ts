import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        var user = localStorage.getItem("user");

        if (user) {
            var userJson = JSON.parse(user);

            if(userJson.id > 0 && userJson.token){

                if(route.data && JSON.stringify(route.data) !== JSON.stringify({})){
                    var data = <any>route.data;

                    if(data.roleId.includes(userJson.userId.role)){
                        return true;
                    }
                    else{
                        this.router.navigate(['/403']);
                        return true;
                    }
                }
                else{
                    return true;
                }
            }
        }
        
        localStorage.clear();

        this.router.navigate(['/Login']);
        return false;
    }
}