import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {SessionStorageService} from 'ng2-webstorage';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: []
})
export class AppComponent implements OnInit {

  constructor(private router: Router, private sessionStorageService: SessionStorageService) {

  }

  ngOnInit() {
  }

  checkIfLogged(): boolean {
    if(this.sessionStorageService.retrieve("pass") == null) {
      return false;
    } else return true;
  }

  logout() {
    this.sessionStorageService.clear("pass");
    this.sessionStorageService.clear("privateKey");
  }


}
