import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {UserService} from '../user.service';
import {FormControl} from "@angular/forms";
import {Router} from "@angular/router";
import {CryptoService} from "../crypto.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  emailFormControl: FormControl = new FormControl();
  usernameFormControl: FormControl = new FormControl();
  passwordFormControl: FormControl = new FormControl();

  registerSuccessPopup: boolean;
  registerEmailFailedPopup: boolean;
  registerUsernameFailedPopup: boolean;

  users: User[];
  newUser: User;

  int: number;
  char: string;
  privateKeyGenerator: String;
  privateKeyToEncrypt: String;
  privateKeyEncrypted: String;


  constructor(private router: Router, private userService: UserService, private cryptoService: CryptoService) {

  }

  ngOnInit() {
    this.newUser = new User();
    this.privateKeyGenerator = new String();
  }

  createUser(user: User): void {
    this.privateKeyToEncrypt = this.generateKey();
    this.privateKeyEncrypted = this.cryptoService.encryptPrivateKey(this.privateKeyToEncrypt, this.newUser.password);
    user.privateKey = this.privateKeyEncrypted;
    this.userService.createUser(user).then(res => {

      this.registerSuccessPopup = false;
      this.registerUsernameFailedPopup = false;
      this.registerEmailFailedPopup = false;

      if (res.length == 0) {
        this.registerEmailFailedPopup = false;
        this.registerUsernameFailedPopup = false;
        this.registerSuccessPopup = true;
        return;
      }
      res.forEach(response => {
          if (response.code == "110") {
            this.registerUsernameFailedPopup = true;
          } else if (response.code == "111") {
            this.registerEmailFailedPopup = true;
          }
        }
      )
    });
  }

  generateKey(): String {
    for (var i = 0; i < 15; i++) {
      this.int = Math.round((Math.random() * 89) + 33);
      this.char = String.fromCharCode(this.int);
      this.privateKeyGenerator = this.privateKeyGenerator.concat(this.char);
    }
    return this.privateKeyGenerator
  }

}
