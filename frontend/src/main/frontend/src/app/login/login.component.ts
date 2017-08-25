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
  loginSuccessPopup: boolean = false;
  loginFailedPopup: boolean = false;

  usernameFormControl: FormControl = new FormControl();
  passwordFormControl: FormControl = new FormControl();

  constructor(private router: Router, private userService: UserService, private sessionStorageService: SessionStorageService) { }

  ngOnInit() {
    this.loginUser = new User();
  }

  logUser(user: User): void {
    this.userService.loginUser(user).then(res => {
      if(res.length == 0) {
        this.sessionStorageService.store('pass', user.password);
        this.loginFailedPopup = false;
        this.loginSuccessPopup = true;
        setTimeout(() => this.router.navigate(['/userPrivData']), 2000);
        return;
      }
      this.loginFailedPopup = true;
    });
  }

   clear(): void {
    this.loginUser.username = "";
    this.loginUser.password = "";
   }

}
