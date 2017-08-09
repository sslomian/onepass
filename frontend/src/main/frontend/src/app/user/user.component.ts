import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[];
  loggedUser: User;

  constructor(private router: Router, private userService: UserService) {

  }

  ngOnInit() {
    this.loggedUser = new User();
    this.loggedUser.username = "No logged";
    this.userService.loggedUser().then(user => this.loggedUser = user);
    this.userService.findAll().then(users => this.users = users);
  }

}
