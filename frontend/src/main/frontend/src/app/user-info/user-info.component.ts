import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';
import {User} from "../user";
import {FormControl} from "@angular/forms";
import {SessionStorageService} from 'ng2-webstorage';
import {CryptoService} from "../crypto.service";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  user: User;
  newPassword: String;
  retypePassword: String;
  showChangePassword: boolean = false;

  newPasswordFormControl: FormControl = new FormControl();
  retypePasswordFormControl: FormControl = new FormControl();

  constructor(private userService: UserService, private sessionStorageService: SessionStorageService,
              private cryptoService: CryptoService) {
  }

  ngOnInit() {
    this.user = new User();
    this.userService.loggedUser().then(res => {
      this.user = res;
    })
  }

  showChange(): void {
    if (this.showChangePassword == false) {
      this.showChangePassword = true;
    } else {
      this.showChangePassword = false;
    }
  }

  saveNewPassword(user: User, newPassword: String) {
    user.password = newPassword;
    this.sessionStorageService.store('pass', user.password);
    user.privateKey = this.cryptoService.encryptPrivateKey(
      this.sessionStorageService.retrieve('privateKey'), newPassword);
    this.userService.updateUser(user);

  }

}
