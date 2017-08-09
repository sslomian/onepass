import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { SessionStorageService } from 'ng2-webstorage';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private userService: UserService, private sessionStorageService: SessionStorageService) { }

  ngOnInit() {
    this.userService.logout();
    this.sessionStorageService.clear("pass");
    this.sessionStorageService.clear("privateKey");
  }

}
