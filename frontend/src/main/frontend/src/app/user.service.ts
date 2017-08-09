import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {Headers, Http} from '@angular/http';

import 'rxjs/add/operator/toPromise';

import {User} from './user';
import {Observable} from "rxjs";

@Injectable()
export class UserService {

  constructor(private http: Http, private router: Router) {
  }

  private usersUrl = '/api/users';
  private loginUrl = '/api/login';
  private logoutUrl = '/api/logout';
  private loggedUserUrl = '/api/loggedUser';
  private isLoggedUrl = '/api/isLogged';
  private headers = new Headers({'Content-Type': 'application/json'});


  findAll(): Promise<User[]> {
    return this.http.get(this.usersUrl)
      .toPromise()
      .then(response => response.json().object as User[])
      .catch(this.handleError);
  }

  createUser(user: User): Promise<User> {
    return this.http
      .post(this.usersUrl + "/save", JSON.stringify(user), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as User)
      .catch(this.handleError);
  }

  loginUser(user: User): Promise<User> {
    return this.http
      .post(this.loginUrl, JSON.stringify(user), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as User)
      .catch(this.handleError);
  }

  loggedUser(): Promise<User> {
    return this.http.get(this.loggedUserUrl)
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }

  logout(): Promise<void> {
    return this.http
      .get(this.logoutUrl)
      .toPromise()
      .then()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error("Error", error);
    return Promise.reject(error.message || error);
  }

}


