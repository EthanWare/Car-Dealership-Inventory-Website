import { Component, OnInit } from '@angular/core';
//import { Location } from '@angular/common';
import {Router} from '@angular/router'

import { User } from '../User';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private userName: string;
  private password: string;
  private location: Location;
  private result: number;

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit() {
  }

  login(userName: string, password: string)  {

    var user: User = {
      id: 0,
      userName: userName,
      password: password,
      isAdmin: 0
    }

    this.loginService.getLoginResult(user).subscribe(result => this.result = result);

    if(this.result === 0){
      this.router.navigateByUrl('user');
      //this.location.go('/user')
    }
    else if (this.result === 1){
      this.router.navigateByUrl('admin');
      //this.location.go('/admin')
    }
    else {
      this.router.navigateByUrl('login');
      //this.location.go('/login')
    }
  }

}
