import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({    
    templateUrl: './home.component.html'    
})
export class HomeComponent implements OnInit {
    constructor(private router: Router) { }

    public roleId: number = 1;

    ngOnInit(): void {
        var user = JSON.parse(localStorage.getItem('user'));
        this.roleId = user.userId.role;
    }

    navigate(url){
        this.router.navigate([url]);
    }
}
