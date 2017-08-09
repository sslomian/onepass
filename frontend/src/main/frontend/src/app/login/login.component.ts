import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { FormControl } from "@angular/forms";
import { Router } from "@angular/router";
import { SessionStorageService } from 'ng2-webstorage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser: User;

  constructor(private router: Router, private userService: UserService, private sessionStorageService: SessionStorageService) { }

  ngOnInit() {
    this.loginUser = new User();
  }

  logUser(user: User): void {
    this.userService.loginUser(user);
    this.sessionStorageService.store('pass', user.password);
  }
   clear(): void {

   }

}
