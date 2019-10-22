import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'
import { FormGroup, FormControl } from '@angular/forms';

import { User } from '../User';
import { LoginService } from '../login.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm = new FormGroup({
    userName: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private loginService: LoginService, private router: Router, private messageService: MessageService) { }

  ngOnInit() {
    this.messageService.clear();
  }

  onSubmit() {
    var user: User = {
      id: 0,
      userName: this.loginForm.controls.userName.value,
      password: this.loginForm.controls.password.value,
      isAdmin: 0
    }

    this.loginService.getLoginResult(user).subscribe(result => {
      if(result === 0){
        this.router.navigateByUrl('user');
      }
      else if (result === 1){
        this.router.navigateByUrl('admin');
      }
      else {
        this.messageService.set('User Name or Password is incorrect');
      }
    });
  }

}