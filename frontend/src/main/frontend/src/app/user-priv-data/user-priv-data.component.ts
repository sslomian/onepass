import { Component, Inject, Input, OnInit } from '@angular/core';
import { MdDialog } from '@angular/material';
import { UserData } from "../user-data";
import { Router } from '@angular/router';
import { UserPrivDataService } from '../user-priv-data.service';
import { CryptoService } from "../crypto.service";
import { UserService } from '../user.service';
import { SessionStorageService } from 'ng2-webstorage';
import { MD_DIALOG_DATA } from '@angular/material';
import { FormControl } from "@angular/forms";

@Component({
  selector: 'app-user-priv-data',
  templateUrl: './user-priv-data.component.html',
  styleUrls: ['./user-priv-data.component.css']
})

export class UserPrivDataComponent implements OnInit {


  userPrivDataBefore: UserData[] = [];
  userDataToEncrypt: UserData;
  userPrivData: UserData[] = [];
  newUserPrivData: UserData;
  decryptedKey: String;

  constructor(public dialog: MdDialog, private router: Router, private userPrivDataService: UserPrivDataService, private crypto: CryptoService, private userService: UserService, private sessionStorageService: SessionStorageService) {
  }

  openDialog() {
    let dialogRef = this.dialog.open(UserPrivDataDialog);

    dialogRef.afterClosed().subscribe(
      result => {
        if (result != false) {
          this.addUserPrivData(result);
        }
      }
    );
  }

  addUserPrivData(userPrivData: UserData): void {
    userPrivData.hidden = true;
    this.userDataToEncrypt = this.crypto.encrypt(userPrivData, this.sessionStorageService.retrieve("privateKey"));

    this.userPrivDataService.addUserPrivData(this.userDataToEncrypt).then(res => {
      var add = this.crypto.decryptSingle(res, this.sessionStorageService.retrieve("privateKey"));
      add.hidden = true;
      this.userPrivData.push(add);
    });
  }


  openDialogEdit(userPrivData: UserData) {
    let dialogRef = this.dialog.open(UserPrivDataDialogUpdate, {data: userPrivData});

    dialogRef.afterClosed().subscribe(
      result => {
        if (result != false) {
          this.editUserPrivData(result);
        }
      }
    );
  }

  editUserPrivData(userPrivData: UserData): void {
    userPrivData.hidden = true;
    this.userDataToEncrypt = this.crypto.encrypt(userPrivData, this.sessionStorageService.retrieve("privateKey"));

    this.userPrivDataService.addUserPrivData(this.userDataToEncrypt).then(res => {
      var add = this.crypto.decryptSingle(res, this.sessionStorageService.retrieve("privateKey"));
      add.hidden = true;
      this.userPrivData.splice(this.userPrivData.indexOf(userPrivData), 1, add);
    });
  }

  ngOnInit() {
    this.userService.loggedUser().then(user => {
      if (user.username != null) {
        this.decryptedKey = this.crypto.decryptPrivateKey(user.privateKey, this.sessionStorageService.retrieve("pass")),
          this.sessionStorageService.store('privateKey', this.decryptedKey);
      } else {
        this.router.navigate(['/login']);
      }
    });

    this.userPrivDataService.getAllUserPrivData().then(userPrivData => {
      this.userPrivDataBefore = userPrivData;
      this.userPrivData = this.crypto.decrypt(this.userPrivDataBefore, this.sessionStorageService.retrieve("privateKey"));
      this.userPrivData.forEach(user => user.hidden = true);
    });

    this.newUserPrivData = new UserData();
  }

  showData(userData: UserData) {
    userData.hidden = false;
  }

  hideData(userData: UserData) {
    userData.hidden = true;
  }

  delete(userPrivData: UserData) {
    this.userPrivDataService.deleteUserPrivData(userPrivData);
    this.userPrivData.splice(this.userPrivData.indexOf(userPrivData), 1);
  }

}

@Component({
  selector: 'user-priv-data-dialog',
  templateUrl: 'user-priv-data-dialog.component.html',
})
export class UserPrivDataDialog implements OnInit {

  newUserPrivData: UserData;

  usernameFormControl: FormControl = new FormControl();
  passwordFormControl: FormControl = new FormControl();
  siteFormControl: FormControl = new FormControl();

  constructor() {
  }

  ngOnInit() {
    this.newUserPrivData = new UserData();
  }

}

@Component({
  selector: 'user-priv-data-dialog-update',
  templateUrl: 'user-priv-data-dialog-update.component.html',
})
export class UserPrivDataDialogUpdate implements OnInit {

  editUserPrivData: UserData;

  usernameFormControl: FormControl = new FormControl();
  passwordFormControl: FormControl = new FormControl();
  siteFormControl: FormControl = new FormControl();

  constructor(@Inject(MD_DIALOG_DATA) public data: UserData) {
  }

  ngOnInit() {
    this.editUserPrivData = this.data;
  }

}
